package com.anesthesiaclinic.anesthesiabriefs.data.repository

import android.content.Context
import com.anesthesiaclinic.anesthesiabriefs.data.local.*
import com.anesthesiaclinic.anesthesiabriefs.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

object BoardPrepRepository {

    private lateinit var database: BoardPrepDatabase
    private lateinit var dao: BoardPrepDao
    private var isInitialized = false

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    fun initialize(context: Context) {
        if (isInitialized) return
        database = BoardPrepDatabase.getDatabase(context)
        dao = database.boardPrepDao()
        isInitialized = true
    }

    private fun getDao(context: Context): BoardPrepDao {
        initialize(context)
        return dao
    }

    // Helper to inflate an Entity back to its corresponding Kotlin Serializable class
    fun inflateQuestion(entity: QuestionEntity): Any {
        return when (entity.questionType) {
            "EDAIC_MTF" -> jsonConfig.decodeFromString<EdaicMtfQuestion>(entity.questionJson)
            "SINGLE_BEST_ANSWER" -> jsonConfig.decodeFromString<SingleBestAnswerQuestion>(entity.questionJson)
            "VIVA_SCENARIO" -> jsonConfig.decodeFromString<VivaScenarioQuestion>(entity.questionJson)
            else -> throw IllegalArgumentException("Unknown question type: ${entity.questionType}")
        }
    }

    // Fetch all questions for a specific board type
    suspend fun getQuestionsByBoardType(context: Context, boardType: String): List<Any> = withContext(Dispatchers.IO) {
        val list = getDao(context).getQuestionsByBoardType(boardType)
        list.map { inflateQuestion(it) }
    }

    // Fetch a single question by its ID
    suspend fun getQuestionById(context: Context, id: String): Any? = withContext(Dispatchers.IO) {
        val entity = getDao(context).getQuestionById(id) ?: return@withContext null
        inflateQuestion(entity)
    }

    // Fetch all bookmarked questions
    suspend fun getBookmarkedQuestions(context: Context): List<Any> = withContext(Dispatchers.IO) {
        val list = getDao(context).getBookmarkedQuestions()
        list.map { inflateQuestion(it) }
    }

    // Toggle bookmark status in both question catalog and user progress
    suspend fun toggleBookmark(context: Context, questionId: String, isBookmarked: Boolean) = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        
        // Update question record
        activeDao.updateQuestionBookmark(questionId, isBookmarked)
        
        // Update progress record
        val progress = activeDao.getUserProgress(questionId)
        if (progress != null) {
            val updated = progress.copy(bookmarked = isBookmarked)
            activeDao.insertUserProgress(updated)
        } else {
            val dummyProgress = UserProgressEntity(
                questionId = questionId,
                selectedAnswersJson = "[]",
                isCorrectStrict = false,
                statementsCorrectCount = 0,
                attemptCount = 0,
                lastAnsweredAt = System.currentTimeMillis(),
                bookmarked = isBookmarked,
                markedDifficult = false,
                weakTopicTagsJson = "[]"
            )
            activeDao.insertUserProgress(dummyProgress)
        }
    }

    // Fetch user progress for a single question
    suspend fun getUserProgress(context: Context, questionId: String): UserProgressEntity? = withContext(Dispatchers.IO) {
        getDao(context).getUserProgress(questionId)
    }

    // Save or update user progress, preserving bookmarks and attempt history
    suspend fun saveUserProgress(
        context: Context,
        questionId: String,
        selectedAnswersJson: String,
        isCorrectStrict: Boolean,
        statementsCorrectCount: Int,
        isBookmarkToggleOnly: Boolean = false,
        markedDifficult: Boolean = false,
        tags: List<String> = emptyList()
    ) = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        val existing = activeDao.getUserProgress(questionId)
        
        val newAttemptCount = (existing?.attemptCount ?: 0) + (if (isBookmarkToggleOnly) 0 else 1)
        val isBookmarked = existing?.bookmarked ?: false
        
        // If incorrect, add all question tags to weak topic tags
        val weakTags = if (!isCorrectStrict && !isBookmarkToggleOnly) {
            val currentWeak = try {
                if (existing != null) jsonConfig.decodeFromString<List<String>>(existing.weakTopicTagsJson) else emptyList()
            } catch (e: Exception) {
                emptyList()
            }
            (currentWeak + tags).distinct()
        } else {
            try {
                if (existing != null) jsonConfig.decodeFromString<List<String>>(existing.weakTopicTagsJson) else emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }

        val updatedProgress = UserProgressEntity(
            questionId = questionId,
            selectedAnswersJson = selectedAnswersJson,
            isCorrectStrict = isCorrectStrict,
            statementsCorrectCount = statementsCorrectCount,
            attemptCount = newAttemptCount,
            lastAnsweredAt = System.currentTimeMillis(),
            bookmarked = isBookmarked,
            markedDifficult = markedDifficult,
            weakTopicTagsJson = jsonConfig.encodeToString(weakTags),
            vivaSelfRating = null // Standard signature fallback
        )
        activeDao.insertUserProgress(updatedProgress)
    }

    // Overloaded saveUserProgress that includes vivaSelfRating
    suspend fun saveUserProgressWithRating(
        context: Context,
        questionId: String,
        selectedAnswersJson: String,
        isCorrectStrict: Boolean,
        statementsCorrectCount: Int,
        isBookmarkToggleOnly: Boolean = false,
        markedDifficult: Boolean = false,
        tags: List<String> = emptyList(),
        vivaSelfRating: String? = null
    ) = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        val existing = activeDao.getUserProgress(questionId)
        
        val newAttemptCount = (existing?.attemptCount ?: 0) + (if (isBookmarkToggleOnly) 0 else 1)
        val isBookmarked = existing?.bookmarked ?: false
        
        // If incorrect, add all question tags to weak topic tags
        val weakTags = if (!isCorrectStrict && !isBookmarkToggleOnly) {
            val currentWeak = try {
                if (existing != null) jsonConfig.decodeFromString<List<String>>(existing.weakTopicTagsJson) else emptyList()
            } catch (e: Exception) {
                emptyList()
            }
            (currentWeak + tags).distinct()
        } else {
            try {
                if (existing != null) jsonConfig.decodeFromString<List<String>>(existing.weakTopicTagsJson) else emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }

        val updatedProgress = UserProgressEntity(
            questionId = questionId,
            selectedAnswersJson = selectedAnswersJson,
            isCorrectStrict = isCorrectStrict,
            statementsCorrectCount = statementsCorrectCount,
            attemptCount = newAttemptCount,
            lastAnsweredAt = System.currentTimeMillis(),
            bookmarked = isBookmarked,
            markedDifficult = markedDifficult,
            weakTopicTagsJson = jsonConfig.encodeToString(weakTags),
            vivaSelfRating = vivaSelfRating ?: existing?.vivaSelfRating
        )
        activeDao.insertUserProgress(updatedProgress)
    }

    // Dynamic dynamic query filtering for custom quiz sessions
    suspend fun getQuestionsFiltered(
        context: Context,
        boardType: String? = null,
        packId: String? = null,
        category: String? = null,
        difficulty: String? = null,
        bookmarkedOnly: Boolean = false,
        unansweredOnly: Boolean = false,
        incorrectOnly: Boolean = false
    ): List<Any> = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        val allEntities = if (boardType != null && boardType != "ALL" && boardType != "BOOKMARKS") {
            activeDao.getQuestionsByBoardType(boardType)
        } else {
            activeDao.getAllQuestions()
        }
        val allProgress = activeDao.getAllUserProgress().associateBy { it.questionId }
        
        val filtered = allEntities.filter { entity ->
            // Filter by boardType
            if (boardType != null && boardType != "ALL" && boardType != "BOOKMARKS" && entity.boardType != boardType) return@filter false
            
            // Filter by packId
            if (packId != null && packId != "ALL" && entity.packId != packId) return@filter false
            
            // Filter by category
            if (category != null && category != "ALL" && entity.category != category) return@filter false
            
            // Filter by difficulty
            if (difficulty != null && difficulty != "ALL" && entity.difficulty.lowercase() != difficulty.lowercase()) return@filter false
            
            // Get progress metrics
            val prog = allProgress[entity.id]
            val isAnswered = (prog?.attemptCount ?: 0) > 0
            val isCorrect = prog?.isCorrectStrict ?: false
            
            // Filter by bookmarked
            if (bookmarkedOnly && !entity.isBookmarked && !(prog?.bookmarked ?: false)) return@filter false
            
            // Filter by unanswered
            if (unansweredOnly && isAnswered) return@filter false
            
            // Filter by incorrect
            if (incorrectOnly && (!isAnswered || isCorrect)) return@filter false
            
            true
        }
        
        filtered.map { inflateQuestion(it) }
    }

    // Aggregates top weak topics across all failed attempts and bookmarked questions
    suspend fun getWeakTopics(context: Context): List<String> = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        val allProgress = activeDao.getAllUserProgress()
        val allQuestions = activeDao.getAllQuestions().associateBy { it.id }
        val tagCounts = mutableMapOf<String, Int>()
        
        for (p in allProgress) {
            val q = allQuestions[p.questionId]
            
            // Trigger factors: low accuracy, repeated incorrect answers, bookmarked questions, low confidence viva
            val isLowAccuracy = !p.isCorrectStrict
            val isVivaConfidenceLow = p.vivaSelfRating == "POOR" || p.vivaSelfRating == "BORDERLINE"
            val isBookmarked = p.bookmarked
            
            if (isLowAccuracy || isVivaConfidenceLow || isBookmarked) {
                // Incorporate both explicit weak topic tags and question tags
                val qTags = if (q != null) {
                    try {
                        jsonConfig.decodeFromString<List<String>>(q.tagsJson)
                    } catch (e: Exception) {
                        emptyList()
                    }
                } else emptyList()
                
                val pWeakTags = try {
                    jsonConfig.decodeFromString<List<String>>(p.weakTopicTagsJson)
                } catch (e: Exception) {
                    emptyList()
                }
                
                val combinedTags = (qTags + pWeakTags).distinct()
                for (t in combinedTags) {
                    val weight = when {
                        isVivaConfidenceLow -> 3 // high weight for poor confidence
                        isLowAccuracy && p.attemptCount > 1 -> 2 // repeated incorrect answers
                        else -> 1
                    }
                    tagCounts[t] = (tagCounts[t] ?: 0) + weight
                }
            }
        }
        // Return tags ordered by count descending
        tagCounts.entries.sortedByDescending { it.value }.map { it.key }
    }

    // Reset local questions and manifests without erasing user progress/history
    suspend fun resetLocalCache(context: Context) = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        activeDao.deleteAllQuestions()
        activeDao.deleteAllPackMetadata()
    }

    // Complete reset of everything including user progress
    suspend fun clearAllProgress(context: Context) = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        activeDao.deleteAllUserProgress()
    }

    // Load statistics for the hub dashboard
    suspend fun getStats(context: Context): Map<String, Any> = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        val allQuestions = activeDao.getAllQuestions()
        val allProgress = activeDao.getAllUserProgress()
        val progressMap = allProgress.associateBy { it.questionId }
        
        val totalAttempts = allProgress.sumOf { it.attemptCount }
        val correctAttempts = allProgress.count { it.isCorrectStrict && it.attemptCount > 0 }
        val totalBookmarks = activeDao.getBookmarkedQuestions().size
        
        // Dynamic dynamic counting
        val totalQuestions = allQuestions.size
        
        val edaicCount = allQuestions.count { it.boardType == "EDAIC_PART_I" }
        val edaicSolved = allQuestions.count { it.boardType == "EDAIC_PART_I" && (progressMap[it.id]?.attemptCount ?: 0) > 0 }
        
        val edaicVivaCount = allQuestions.count { it.boardType == "EDAIC_VIVA" }
        val edaicVivaSolved = allQuestions.count { it.boardType == "EDAIC_VIVA" && (progressMap[it.id]?.attemptCount ?: 0) > 0 }
        
        val abaBasicCount = allQuestions.count { it.boardType == "ABA_BASIC" }
        val abaBasicSolved = allQuestions.count { it.boardType == "ABA_BASIC" && (progressMap[it.id]?.attemptCount ?: 0) > 0 }
        
        val abaAdvancedCount = allQuestions.count { it.boardType == "ABA_ADVANCED" }
        val abaAdvancedSolved = allQuestions.count { it.boardType == "ABA_ADVANCED" && (progressMap[it.id]?.attemptCount ?: 0) > 0 }
        
        // Bookmark progress tracking
        val bookmarkedSolved = allQuestions.count { it.isBookmarked && (progressMap[it.id]?.attemptCount ?: 0) > 0 }
        
        // MTF Progress tracking details
        val mtfAttempts = allProgress.filter { prog ->
            val q = allQuestions.find { it.id == prog.questionId }
            q?.questionType == "EDAIC_MTF" && prog.attemptCount > 0
        }
        val mtfStrictAccuracy = if (mtfAttempts.isNotEmpty()) {
            (mtfAttempts.count { it.isCorrectStrict }.toDouble() / mtfAttempts.size * 100).toInt()
        } else 0
        val mtfStatementAccuracy = if (mtfAttempts.isNotEmpty()) {
            val totalStmts = mtfAttempts.size * 5
            val correctStmts = mtfAttempts.sumOf { it.statementsCorrectCount }
            (correctStmts.toDouble() / totalStmts * 100).toInt()
        } else 0
        
        // SBA Progress tracking details
        val sbaAttempts = allProgress.filter { prog ->
            val q = allQuestions.find { it.id == prog.questionId }
            q?.questionType == "SINGLE_BEST_ANSWER" && prog.attemptCount > 0
        }
        val sbaAccuracy = if (sbaAttempts.isNotEmpty()) {
            (sbaAttempts.count { it.isCorrectStrict }.toDouble() / sbaAttempts.size * 100).toInt()
        } else 0
        
        // Viva confidence tracking details
        val vivaAttempts = allProgress.filter { prog ->
            val q = allQuestions.find { it.id == prog.questionId }
            q?.questionType == "VIVA_SCENARIO" && prog.attemptCount > 0
        }
        val vivaConfidence = if (vivaAttempts.isNotEmpty()) {
            val totalWeight = vivaAttempts.sumOf { prog ->
                when (prog.vivaSelfRating) {
                    "POOR" -> 25
                    "BORDERLINE" -> 50
                    "GOOD" -> 75
                    "EXCELLENT" -> 100
                    else -> 50 // default medium
                }
            }
            totalWeight / vivaAttempts.size
        } else 0
        
        val weakTopics = getWeakTopics(context)
        
        mapOf(
            "totalQuestions" to totalQuestions,
            "totalAttempts" to totalAttempts,
            "correctAttempts" to correctAttempts,
            "totalBookmarks" to totalBookmarks,
            "edaicCount" to edaicCount,
            "edaicSolved" to edaicSolved,
            "edaicVivaCount" to edaicVivaCount,
            "edaicVivaSolved" to edaicVivaSolved,
            "abaBasicCount" to abaBasicCount,
            "abaBasicSolved" to abaBasicSolved,
            "abaAdvancedCount" to abaAdvancedCount,
            "abaAdvancedSolved" to abaAdvancedSolved,
            "bookmarkedSolved" to bookmarkedSolved,
            "mtfStrictAccuracy" to mtfStrictAccuracy,
            "mtfStatementAccuracy" to mtfStatementAccuracy,
            "sbaAccuracy" to sbaAccuracy,
            "vivaConfidence" to vivaConfidence,
            "weakTopics" to weakTopics
        )
    }

    // Load list of all unique categories from the database for dynamic filtering
    suspend fun getAllCategories(context: Context): List<String> = withContext(Dispatchers.IO) {
        val questions = getDao(context).getAllQuestions()
        questions.map { it.category }.distinct().sorted()
    }

    // Query installed packs with rich status mapping
    suspend fun getInstalledPacksRich(context: Context): List<Map<String, Any>> = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        val metadataList = activeDao.getAllPackMetadata()
        val allQuestions = activeDao.getAllQuestions()
        val allProgress = activeDao.getAllUserProgress().associateBy { it.questionId }
        
        metadataList.map { meta ->
            val packQuestions = allQuestions.filter { it.packId == meta.packId }
            val count = packQuestions.size
            val solved = packQuestions.count { (allProgress[it.id]?.attemptCount ?: 0) > 0 }
            
            val format = when (meta.packId) {
                "edaic_part_i", "edaic_paper_a_pack_1", "edaic_paper_b_pack_1" -> "MTF"
                "aba_basic", "aba_basic_pack_1", "aba_advanced", "aba_advanced_pack_1" -> "SBA"
                "viva_scenarios", "edaic_viva_pack_1" -> "Viva"
                else -> "Mixed"
            }
            
            val titleEn = when (meta.packId) {
                "edaic_part_i" -> "EDAIC Part I Seed Pack"
                "aba_basic" -> "ABA BASIC Seed Pack"
                "aba_advanced" -> "ABA ADVANCED Seed Pack"
                "viva_scenarios" -> "EDAIC Viva Seed Pack"
                "edaic_paper_a_pack_1" -> "EDAIC Paper A Pack 1"
                "edaic_paper_b_pack_1" -> "EDAIC Paper B Pack 1"
                "aba_basic_pack_1" -> "ABA BASIC Pack 1"
                "aba_advanced_pack_1" -> "ABA ADVANCED Pack 1"
                "edaic_viva_pack_1" -> "EDAIC Viva Pack 1"
                else -> meta.packId.uppercase().replace("_", " ")
            }

            val titleTr = when (meta.packId) {
                "edaic_part_i" -> "EDAIC Part I Tohum Paketi"
                "aba_basic" -> "ABA BASIC Tohum Paketi"
                "aba_advanced" -> "ABA ADVANCED Tohum Paketi"
                "viva_scenarios" -> "EDAIC Viva Tohum Paketi"
                "edaic_paper_a_pack_1" -> "EDAIC Paper A Paket 1"
                "edaic_paper_b_pack_1" -> "EDAIC Paper B Paket 1"
                "aba_basic_pack_1" -> "ABA BASIC Paket 1"
                "aba_advanced_pack_1" -> "ABA ADVANCED Paket 1"
                "edaic_viva_pack_1" -> "EDAIC Viva Paket 1"
                else -> meta.packId.uppercase().replace("_", " ")
            }
            
            val boardType = when (meta.packId) {
                "edaic_part_i", "edaic_paper_a_pack_1", "edaic_paper_b_pack_1" -> "EDAIC_PART_I"
                "aba_basic", "aba_basic_pack_1" -> "ABA_BASIC"
                "aba_advanced", "aba_advanced_pack_1" -> "ABA_ADVANCED"
                "viva_scenarios", "edaic_viva_pack_1" -> "EDAIC_VIVA"
                else -> "UNKNOWN"
            }

            mapOf(
                "packId" to meta.packId,
                "titleEn" to titleEn,
                "titleTr" to titleTr,
                "boardType" to boardType,
                "version" to "v${meta.version}",
                "questionCount" to count,
                "solvedCount" to solved,
                "format" to format,
                "sha256" to if (meta.checksumSha256.length > 10) meta.checksumSha256.take(8) + "..." else "N/A"
            )
        }
    }

    // Local-to-Cloud merge function (Requirement 14)
    suspend fun mergeLocalAndCloudProgress(
        context: Context,
        cloudProgressList: List<UserProgressEntity>
    ) = withContext(Dispatchers.IO) {
        val activeDao = getDao(context)
        
        for (cloud in cloudProgressList) {
            val local = activeDao.getUserProgress(cloud.questionId)
            if (local == null) {
                // If no local progress exists, write cloud progress
                activeDao.insertUserProgress(cloud)
                // Also update the question bookmark state
                activeDao.updateQuestionBookmark(cloud.questionId, cloud.bookmarked)
            } else {
                // Resolve conflict:
                val bookmarked = local.bookmarked || cloud.bookmarked
                val attemptCount = kotlin.math.max(local.attemptCount, cloud.attemptCount)
                val lastAnsweredAt = kotlin.math.max(local.lastAnsweredAt, cloud.lastAnsweredAt)
                
                // For selectedAnswersJson, isCorrectStrict, and statementsCorrectCount:
                // We preserve whichever has the latest answer history by timestamp
                val latest = if (local.lastAnsweredAt >= cloud.lastAnsweredAt) local else cloud
                val selectedAnswersJson = latest.selectedAnswersJson
                val isCorrectStrict = latest.isCorrectStrict
                val statementsCorrectCount = latest.statementsCorrectCount
                
                // Viva Self Rating priority: EXCELLENT > GOOD > BORDERLINE > POOR
                val ratingPriority = mapOf(
                    "EXCELLENT" to 4,
                    "GOOD" to 3,
                    "BORDERLINE" to 2,
                    "POOR" to 1
                )
                val localRatingVal = ratingPriority[local.vivaSelfRating] ?: 0
                val cloudRatingVal = ratingPriority[cloud.vivaSelfRating] ?: 0
                val vivaSelfRating = if (localRatingVal >= cloudRatingVal) {
                    local.vivaSelfRating
                } else {
                    cloud.vivaSelfRating
                }
                
                val merged = UserProgressEntity(
                    questionId = cloud.questionId,
                    selectedAnswersJson = selectedAnswersJson,
                    isCorrectStrict = isCorrectStrict,
                    statementsCorrectCount = statementsCorrectCount,
                    attemptCount = attemptCount,
                    lastAnsweredAt = lastAnsweredAt,
                    bookmarked = bookmarked,
                    markedDifficult = local.markedDifficult || cloud.markedDifficult,
                    weakTopicTagsJson = local.weakTopicTagsJson,
                    vivaSelfRating = vivaSelfRating
                )
                activeDao.insertUserProgress(merged)
                activeDao.updateQuestionBookmark(cloud.questionId, bookmarked)
            }
        }
    }

    // Load all board spot notes from assets
    suspend fun getBoardSpotNotes(context: Context): List<BoardSpotNote> = withContext(Dispatchers.IO) {
        try {
            val jsonContent = context.assets.open("board_spot_notes.json").bufferedReader().use { it.readText() }
            jsonConfig.decodeFromString<List<BoardSpotNote>>(jsonContent)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    // Load all daily clinical pearls from assets
    suspend fun getDailyClinicalPearls(context: Context): List<DailyClinicalPearl> = withContext(Dispatchers.IO) {
        try {
            val jsonContent = context.assets.open("daily_clinical_pearls.json").bufferedReader().use { it.readText() }
            jsonConfig.decodeFromString<List<DailyClinicalPearl>>(jsonContent)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
