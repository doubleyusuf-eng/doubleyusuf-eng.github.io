package com.anesthesiaclinic.anesthesiabriefs.data.sync

import android.content.Context
import android.util.Log
import com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDatabase
import com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity
import com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity
import com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack
import com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPackManifest
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.security.MessageDigest

object FirebaseSyncService {

    private const val TAG = "FirebaseSyncService"
    
    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    // Standard SHA-256 Checksum calculation
    fun calculateSha256(content: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(content.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    // Helper to check if Firebase is initialized
    fun isFirebaseAvailable(context: Context): Boolean {
        return try {
            FirebaseApp.getInstance()
            true
        } catch (e: IllegalStateException) {
            false
        }
    }

    /**
     * Checks if there's a newer version of the pack.
     * Returns the remote manifest if a newer version is available, otherwise null.
     */
    suspend fun checkForUpdates(context: Context, packId: String): BoardPrepPackManifest? = withContext(Dispatchers.IO) {
        if (!isFirebaseAvailable(context)) {
            Log.w(TAG, "Firebase not initialized. Simulating update check for $packId.")
            return@withContext getSimulatedManifest(packId, context)
        }

        try {
            val db = FirebaseFirestore.getInstance()
            val document = db.collection("board_prep_manifests").document(packId).get().await()
            
            if (!document.exists()) return@withContext null
            
            // Map document fields manually to BoardPrepPackManifest to be resilient
            val remotePackId = document.getString("packId") ?: packId
            val titleEn = document.getString("titleEn") ?: ""
            val titleTr = document.getString("titleTr") ?: ""
            val boardType = document.getString("boardType") ?: ""
            val version = document.getLong("version")?.toInt() ?: 1
            val questionCount = document.getLong("questionCount")?.toInt() ?: 0
            val languages = (document.get("languages") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()
            val isActive = document.getBoolean("isActive") ?: true
            val isMandatory = document.getBoolean("isMandatory") ?: false
            val storagePath = document.getString("storagePath") ?: ""
            val checksumSha256 = document.getString("checksumSha256") ?: ""
            val changelogEn = document.getString("changelogEn") ?: ""
            val changelogTr = document.getString("changelogTr") ?: ""
            val minAppVersion = document.getLong("minAppVersion")?.toInt() ?: 1
            val createdAt = document.getString("createdAt") ?: ""
            val updatedAt = document.getString("updatedAt") ?: ""

            val manifest = BoardPrepPackManifest(
                packId = remotePackId,
                titleEn = titleEn,
                titleTr = titleTr,
                boardType = boardType,
                version = version,
                questionCount = questionCount,
                languages = languages,
                isActive = isActive,
                isMandatory = isMandatory,
                storagePath = storagePath,
                checksumSha256 = checksumSha256,
                changelogEn = changelogEn,
                changelogTr = changelogTr,
                minAppVersion = minAppVersion,
                createdAt = createdAt,
                updatedAt = updatedAt
            )

            // Get local metadata
            val localDao = BoardPrepDatabase.getDatabase(context).boardPrepDao()
            val localMetadata = localDao.getPackMetadata(packId)
            
            if (localMetadata == null || manifest.version > localMetadata.version) {
                return@withContext manifest
            }
            return@withContext null
        } catch (e: Exception) {
            Log.e(TAG, "Error checking for Firebase updates for $packId", e)
            return@withContext null
        }
    }

    /**
     * Downloads and imports the specified pack.
     * Executes SHA-256 validation. Throws exception if validation fails.
     */
    suspend fun syncPack(context: Context, manifest: BoardPrepPackManifest, forceLocal: Boolean = false): Boolean = withContext(Dispatchers.IO) {
        val database = BoardPrepDatabase.getDatabase(context)
        val dao = database.boardPrepDao()
        
        try {
            val jsonContent: String
            if (forceLocal || !isFirebaseAvailable(context)) {
                Log.w(TAG, "Loading local/simulated pack for ${manifest.packId}.")
                jsonContent = getSimulatedUpdatedPackJson(manifest.packId, context)
            } else {
                jsonContent = try {
                    val storage = FirebaseStorage.getInstance()
                    val ref = storage.getReference(manifest.storagePath)
                    val bytes = ref.getBytes(1024 * 1024 * 15).await() // up to 15MB
                    String(bytes, Charsets.UTF_8)
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to download ${manifest.packId} from Firebase Storage, falling back to local asset.", e)
                    getSimulatedUpdatedPackJson(manifest.packId, context)
                }
            }

            // 1. Calculate and validate SHA-256 Checksum
            val calculatedSha = calculateSha256(jsonContent)
            if (calculatedSha.lowercase() != manifest.checksumSha256.lowercase()) {
                val errorMsg = "Checksum validation failed for pack ${manifest.packId}. Expected: ${manifest.checksumSha256}, Got: $calculatedSha"
                Log.e(TAG, errorMsg)
                throw SecurityException(errorMsg)
            }

            // 2. Deserialize Pack JSON
            val boardPack = jsonConfig.decodeFromString<BoardPrepPack>(jsonContent)
            
            // 3. Convert questions to entities
            val questionEntities = mutableListOf<QuestionEntity>()
            
            boardPack.edaicMtfQuestions.forEach { q ->
                questionEntities.add(
                    QuestionEntity(
                        id = q.id,
                        packId = boardPack.packId,
                        boardType = q.boardType,
                        questionType = "EDAIC_MTF",
                        category = q.category,
                        topic = q.topic,
                        difficulty = q.difficulty,
                        stemEn = q.stem.en,
                        stemTr = q.stem.tr,
                        sourceType = q.sourceType,
                        version = q.version,
                        isBookmarked = false, // starts unbookmarked, dao transaction preserves existing progress bookmark
                        tagsJson = jsonConfig.encodeToString(q.tags),
                        questionJson = jsonConfig.encodeToString(q)
                    )
                )
            }

            boardPack.sbaQuestions.forEach { q ->
                questionEntities.add(
                    QuestionEntity(
                        id = q.id,
                        packId = boardPack.packId,
                        boardType = q.boardType,
                        questionType = "SINGLE_BEST_ANSWER",
                        category = q.category,
                        topic = q.topic,
                        difficulty = q.difficulty,
                        stemEn = q.stem.en,
                        stemTr = q.stem.tr,
                        sourceType = q.sourceType,
                        version = q.version,
                        isBookmarked = false,
                        tagsJson = jsonConfig.encodeToString(q.tags),
                        questionJson = jsonConfig.encodeToString(q)
                    )
                )
            }

            boardPack.vivaQuestions.forEach { q ->
                questionEntities.add(
                    QuestionEntity(
                        id = q.id,
                        packId = boardPack.packId,
                        boardType = q.boardType,
                        questionType = "VIVA_SCENARIO",
                        category = q.category,
                        topic = q.topic,
                        difficulty = q.difficulty,
                        stemEn = q.scenario.en,
                        stemTr = q.scenario.tr,
                        sourceType = q.sourceType,
                        version = q.version,
                        isBookmarked = false,
                        tagsJson = jsonConfig.encodeToString(q.tags),
                        questionJson = jsonConfig.encodeToString(q)
                    )
                )
            }

            // 4. Transactionally commit questions and update metadata
            val metadataEntity = PackMetadataEntity(
                packId = manifest.packId,
                version = manifest.version,
                checksumSha256 = manifest.checksumSha256,
                installedAt = System.currentTimeMillis()
            )
            
            dao.importPack(questionEntities, metadataEntity)
            Log.d(TAG, "Successfully synced and imported pack: ${manifest.packId}")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to sync pack ${manifest.packId}", e)
            throw e
        }
    }

    // Mock/Simulated responses for offline demonstration
    private fun getSimulatedManifest(packId: String, context: Context): BoardPrepPackManifest? {
        val database = BoardPrepDatabase.getDatabase(context)
        val currentMeta = database.boardPrepDao().getPackMetadata(packId)
        val currentVersion = currentMeta?.version ?: 1
        
        // We simulate a version update if called (e.g. currentVersion + 1)
        val nextVersion = currentVersion + 1
        val dummyJson = getSimulatedUpdatedPackJson(packId, context)
        val sha256 = calculateSha256(dummyJson)

        return BoardPrepPackManifest(
            packId = packId,
            titleEn = when (packId) {
                "edaic_part_i" -> "EDAIC Part I Question Pack (Updated)"
                "aba_basic" -> "ABA BASIC Board Review (Updated)"
                "aba_advanced" -> "ABA ADVANCED Board Review (Updated)"
                "viva_scenarios" -> "Viva/Oral Exam Scenarios (Updated)"
                else -> "Anesthesia Board Prep Pack"
            },
            titleTr = when (packId) {
                "edaic_part_i" -> "EDAIC Part I Soru Paketi (Güncellendi)"
                "aba_basic" -> "ABA BASIC Sınav Hazırlığı (Güncellendi)"
                "aba_advanced" -> "ABA ADVANCED Sınav Hazırlığı (Güncellendi)"
                "viva_scenarios" -> "Viva / Sözlü Sınav Senaryoları (Güncellendi)"
                else -> "Anestezi Sınav Hazırlık Paketi"
            },
            boardType = when (packId) {
                "edaic_part_i" -> "EDAIC_PART_I"
                "aba_basic" -> "ABA_BASIC"
                "aba_advanced" -> "ABA_ADVANCED"
                "viva_scenarios" -> "EDAIC_VIVA"
                else -> "UNKNOWN"
            },
            version = nextVersion,
            questionCount = 3,
            languages = listOf("en", "tr"),
            isActive = true,
            isMandatory = false,
            storagePath = "board_prep/$packId.json",
            checksumSha256 = sha256,
            changelogEn = "Added new original anesthesia clinical prep questions, fixed typos in explanations.",
            changelogTr = "Yeni özgün anestezi klinik hazırlık soruları eklendi, açıklamalardaki yazım hataları düzeltildi.",
            minAppVersion = 1,
            createdAt = "2026-05-30T00:00:00Z",
            updatedAt = "2026-05-30T12:00:00Z"
        )
    }

    private fun getSimulatedUpdatedPackJson(packId: String, context: Context): String {
        // Return a slightly modified seed data JSON depending on the packId
        // This simulates a full update from Firebase Storage
        return try {
            val filename = when (packId) {
                "edaic_part_i" -> "seed_board_prep_edaic_part_i.json"
                "aba_basic" -> "seed_board_prep_aba_basic.json"
                "aba_advanced" -> "seed_board_prep_aba_advanced.json"
                "viva_scenarios" -> "seed_board_prep_viva_scenario.json"
                "edaic_paper_a_pack_1" -> "seed_board_prep_edaic_paper_a_pack_1.json"
                "edaic_paper_b_pack_1" -> "seed_board_prep_edaic_paper_b_pack_1.json"
                "aba_basic_pack_1" -> "seed_board_prep_aba_basic_pack_1.json"
                "aba_advanced_pack_1" -> "seed_board_prep_aba_advanced_pack_1.json"
                "edaic_viva_pack_1" -> "seed_board_prep_edaic_viva_pack_1.json"
                else -> throw IllegalArgumentException("Unknown packId $packId")
            }
            context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            // Safe fallback inline representation
            "{ \"packId\": \"$packId\", \"version\": 2, \"boardType\": \"UNKNOWN\", \"languages\": [\"en\", \"tr\"] }"
        }
    }
}
