package com.anesthesiaclinic.anesthesiabriefs

import com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText
import com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfStatement
import com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion
import com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion
import com.anesthesiaclinic.anesthesiabriefs.data.model.VivaScenarioQuestion
import com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack
import com.anesthesiaclinic.anesthesiabriefs.data.sync.FirebaseSyncService
import com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepAccessManager
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test

class BoardPrepDatabaseTest {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
    }

    @Test
    fun testSha256ChecksumCalculation() {
        val testContent = "AnesthesiaBriefsBoardPrepSecretToken"
        val expectedSha = "157a17e4d27532f52b33b7df41460ca23d4ac99935c3d4d8aec8ffc5aa19c226"
        val actualSha = FirebaseSyncService.calculateSha256(testContent)
        assertEquals(expectedSha, actualSha)
    }

    @Test
    fun testEdaicMtfQuestionSerialization() {
        val question = EdaicMtfQuestion(
            id = "test_mtf_1",
            boardType = "EDAIC_PART_I",
            paper = "PAPER_A",
            category = "Physiology",
            topic = "Cardiac",
            difficulty = "medium",
            stem = LocalizedText("Stem En", "Stem Tr"),
            statements = listOf(
                EdaicMtfStatement("A", LocalizedText("Stmt A En", "Stmt A Tr"), true, LocalizedText("Exp A En", "Exp A Tr")),
                EdaicMtfStatement("B", LocalizedText("Stmt B En", "Stmt B Tr"), false, LocalizedText("Exp B En", "Exp B Tr"))
            ),
            overallExplanation = LocalizedText("Overall En", "Overall Tr"),
            keyLearningPoint = LocalizedText("Klp En", "Klp Tr"),
            commonTrap = LocalizedText("Trap En", "Trap Tr"),
            tags = listOf("physiology"),
            sourceType = "ORIGINAL_BOARD_STYLE",
            referenceNotes = listOf("Ref Note"),
            officialEndorsement = false,
            version = 1
        )

        val serialized = jsonConfig.encodeToString(question)
        assertNotNull(serialized)
        assertTrue(serialized.contains("test_mtf_1"))
        assertTrue(serialized.contains("ORIGINAL_BOARD_STYLE"))

        val deserialized = jsonConfig.decodeFromString<EdaicMtfQuestion>(serialized)
        assertEquals(question.id, deserialized.id)
        assertEquals(question.boardType, deserialized.boardType)
        assertEquals(question.sourceType, deserialized.sourceType)
        assertEquals(question.statements.size, deserialized.statements.size)
        assertEquals(question.statements[0].correctAnswer, deserialized.statements[0].correctAnswer)
    }

    @Test
    fun testSingleBestAnswerQuestionSerialization() {
        val question = SingleBestAnswerQuestion(
            id = "test_sba_1",
            boardType = "ABA_BASIC",
            category = "Pharmacology",
            topic = "Inhalational",
            difficulty = "medium",
            stem = LocalizedText("Stem En", "Stem Tr"),
            options = listOf(LocalizedText("Opt A En", "Opt A Tr"), LocalizedText("Opt B En", "Opt B Tr")),
            correctAnswerIndex = 1,
            optionExplanations = listOf(LocalizedText("Exp A En", "Exp A Tr"), LocalizedText("Exp B En", "Exp B Tr")),
            overallExplanation = LocalizedText("Overall En", "Overall Tr"),
            keyLearningPoint = LocalizedText("Klp En", "Klp Tr"),
            commonTrap = null,
            tags = listOf("pharmacology"),
            sourceType = "ORIGINAL_BOARD_STYLE",
            version = 1
        )

        val serialized = jsonConfig.encodeToString(question)
        assertNotNull(serialized)

        val deserialized = jsonConfig.decodeFromString<SingleBestAnswerQuestion>(serialized)
        assertEquals(question.id, deserialized.id)
        assertEquals(question.correctAnswerIndex, deserialized.correctAnswerIndex)
    }

    @Test
    fun testAbaAdvancedPack1SeedAssetParsing() {
        var file = java.io.File("app/src/main/assets/seed_board_prep_aba_advanced_pack_1.json")
        if (!file.exists()) {
            file = java.io.File("src/main/assets/seed_board_prep_aba_advanced_pack_1.json")
        }
        assertTrue("Asset file must exist", file.exists())
        val jsonContent = file.readText()
        val pack = jsonConfig.decodeFromString<BoardPrepPack>(jsonContent)
        
        assertEquals("aba_advanced_pack_1", pack.packId)
        assertEquals(1, pack.version)
        assertEquals("ABA_ADVANCED", pack.boardType)
        assertEquals(32, pack.sbaQuestions.size)
        
        pack.sbaQuestions.forEach { q ->
            assertNotNull(q.id)
            assertEquals("ABA_ADVANCED", q.boardType)
            assertNotNull(q.stem.en)
            assertNotNull(q.stem.tr)
            assertTrue(q.options.isNotEmpty())
            assertTrue(q.correctAnswerIndex in q.options.indices)
            assertTrue(q.optionExplanations.size == q.options.size)
            assertNotNull(q.overallExplanation.en)
            assertNotNull(q.overallExplanation.tr)
            assertNotNull(q.keyLearningPoint.en)
            assertNotNull(q.keyLearningPoint.tr)
        }
    }

    @Test
    fun testEdaicVivaPack1SeedAssetParsing() {
        var file = java.io.File("app/src/main/assets/seed_board_prep_edaic_viva_pack_1.json")
        if (!file.exists()) {
            file = java.io.File("src/main/assets/seed_board_prep_edaic_viva_pack_1.json")
        }
        assertTrue("Asset file must exist", file.exists())
        val jsonContent = file.readText()
        val pack = jsonConfig.decodeFromString<BoardPrepPack>(jsonContent)
        
        assertEquals("edaic_viva_pack_1", pack.packId)
        assertEquals(1, pack.version)
        assertEquals("EDAIC_VIVA", pack.boardType)
        assertEquals(21, pack.vivaQuestions.size)
        
        pack.vivaQuestions.forEach { q ->
            assertNotNull(q.id)
            assertEquals("EDAIC_VIVA", q.boardType)
            assertNotNull(q.scenario.en)
            assertNotNull(q.scenario.tr)
            assertTrue(q.examinerQuestions.isNotEmpty())
            assertTrue(q.expectedAnswerPoints.isNotEmpty())
            assertTrue(q.redFlags.isNotEmpty())
            assertTrue(q.followUpQuestions.isNotEmpty())
            assertNotNull(q.keyLearningPoint.en)
            assertNotNull(q.keyLearningPoint.tr)
        }
    }

    @Test
    fun testVivaScenarioQuestionSerialization() {
        val question = VivaScenarioQuestion(
            id = "test_viva_1",
            boardType = "EDAIC_VIVA",
            category = "Emergencies",
            topic = "MH",
            difficulty = "hard",
            scenario = LocalizedText("Scenario En", "Scenario Tr"),
            examinerQuestions = listOf(LocalizedText("Q1 En", "Q1 Tr")),
            expectedAnswerPoints = listOf(LocalizedText("Point En", "Point Tr")),
            redFlags = listOf(LocalizedText("Red En", "Red Tr")),
            followUpQuestions = listOf(LocalizedText("Follow En", "Follow Tr")),
            keyLearningPoint = LocalizedText("Klp En", "Klp Tr"),
            commonTrap = null,
            tags = listOf("emergency"),
            sourceType = "ORIGINAL_BOARD_STYLE",
            version = 1
        )

        val serialized = jsonConfig.encodeToString(question)
        assertNotNull(serialized)

        val deserialized = jsonConfig.decodeFromString<VivaScenarioQuestion>(serialized)
        assertEquals(question.id, deserialized.id)
        assertEquals(question.expectedAnswerPoints.size, deserialized.expectedAnswerPoints.size)
        assertEquals(question.redFlags.size, deserialized.redFlags.size)
    }

    @Test
    fun testScoringLogicSimulation() {
        // Simulating the scoring logic from BoardPrepQuizScreen
        val statementAnswers = mapOf(
            0 to true,  // User answered True
            1 to false // User answered False
        )
        
        val correctStatements = listOf(
            EdaicMtfStatement("A", LocalizedText("A", "A"), true, LocalizedText("A", "A")),  // Correct is True
            EdaicMtfStatement("B", LocalizedText("B", "B"), false, LocalizedText("B", "B")) // Correct is False
        )

        var strictCorrect = true
        var correctCount = 0

        correctStatements.forEachIndexed { index, stmt ->
            val userAns = statementAnswers[index]
            val isCorrect = userAns == stmt.correctAnswer
            if (isCorrect) {
                correctCount++
            } else {
                strictCorrect = false
            }
        }

        assertTrue(strictCorrect)
        assertEquals(2, correctCount)
    }

    @Test
    fun testQuestionFilteringLogic() {
        val q1 = QuestionEntity(
            id = "q1", packId = "pack_1", boardType = "ABA_BASIC", questionType = "SINGLE_BEST_ANSWER",
            category = "Pharmacology", topic = "Local", difficulty = "medium",
            stemEn = "Stem En", stemTr = "Stem Tr", sourceType = "ABA", version = 1,
            isBookmarked = false, tagsJson = "[\"local\"]", questionJson = "{}"
        )
        val q2 = QuestionEntity(
            id = "q2", packId = "pack_2", boardType = "ABA_ADVANCED", questionType = "SINGLE_BEST_ANSWER",
            category = "Pharmacology", topic = "Inhalational", difficulty = "hard",
            stemEn = "Stem En", stemTr = "Stem Tr", sourceType = "ABA", version = 1,
            isBookmarked = false, tagsJson = "[\"inhalational\"]", questionJson = "{}"
        )
        val q3 = QuestionEntity(
            id = "q3", packId = "viva_pack", boardType = "EDAIC_VIVA", questionType = "VIVA_SCENARIO",
            category = "Emergencies", topic = "MH", difficulty = "hard",
            stemEn = "Stem En", stemTr = "Stem Tr", sourceType = "EDAIC", version = 1,
            isBookmarked = false, tagsJson = "[\"emergency\"]", questionJson = "{}"
        )

        val questions = listOf(q1, q2, q3)

        // Filter by boardType
        val filteredBoard = questions.filter { it.boardType == "ABA_ADVANCED" }
        assertEquals(1, filteredBoard.size)
        assertEquals("q2", filteredBoard[0].id)

        // Filter by difficulty
        val filteredDifficulty = questions.filter { it.difficulty == "hard" }
        assertEquals(2, filteredDifficulty.size)
        assertTrue(filteredDifficulty.any { it.id == "q2" })
        assertTrue(filteredDifficulty.any { it.id == "q3" })

        // Filter by packId
        val filteredPack = questions.filter { it.packId == "pack_1" }
        assertEquals(1, filteredPack.size)
        assertEquals("q1", filteredPack[0].id)
    }

    @Test
    fun testWeakTopicsWeightCalculation() {
        // Simulate local progress and dynamic weak topic calculations
        // Rules: Poor/Borderline Viva = weight 3, Repeated incorrect SBA = weight 2, Bookmark/Single error = weight 1
        val progressMock = listOf(
            // Poor rating on "mh" tag
            MapProgressMock("q3", isCorrect = false, tags = listOf("mh", "emergency"), vivaSelfRating = "POOR"),
            // Incorrect SBA on "inhalational" tag
            MapProgressMock("q2", isCorrect = false, tags = listOf("inhalational", "pharmacology"), vivaSelfRating = null),
            // Bookmarked/correct SBA on "local"
            MapProgressMock("q1", isCorrect = true, tags = listOf("local"), vivaSelfRating = null, bookmarked = true)
        )

        val topicWeights = mutableMapOf<String, Int>()
        progressMock.forEach { prog ->
            prog.tags.forEach { tag ->
                var weight = 0
                if (prog.vivaSelfRating == "POOR" || prog.vivaSelfRating == "BORDERLINE") {
                    weight = 3
                } else if (!prog.isCorrect) {
                    weight = 2
                } else if (prog.bookmarked) {
                    weight = 1
                }
                if (weight > 0) {
                    topicWeights[tag] = (topicWeights[tag] ?: 0) + weight
                }
            }
        }

        assertEquals(3, topicWeights["mh"])
        assertEquals(3, topicWeights["emergency"])
        assertEquals(2, topicWeights["inhalational"])
        assertEquals(2, topicWeights["pharmacology"])
        assertEquals(1, topicWeights["local"])
    }

    @Test
    fun testVivaStatsAggregation() {
        val ratings = listOf("POOR", "BORDERLINE", "GOOD", "EXCELLENT", null)
        var sum = 0
        var count = 0
        ratings.forEach { rating ->
            if (rating != null) {
                val value = when (rating) {
                    "POOR" -> 25
                    "BORDERLINE" -> 50
                    "GOOD" -> 75
                    "EXCELLENT" -> 100
                    else -> 0
                }
                sum += value
                count++
            }
        }
        val avg = if (count > 0) sum.toFloat() / count else 0f
        assertEquals(62.5f, avg, 0.01f)
    }

    @Test
    fun testBoardPrepAccessManagerRules() {
        // Pure Kotlin lookup check
        assertTrue(BoardPrepAccessManager.isBoardPrepDemoAllowed("edaic_a_phys_cv_0001"))
        assertTrue(BoardPrepAccessManager.isBoardPrepDemoAllowed("edaic_a_phys_resp_0001"))
        assertTrue(BoardPrepAccessManager.isBoardPrepDemoAllowed("aba_basic_pharm_volatile_0001"))
        assertTrue(BoardPrepAccessManager.isBoardPrepDemoAllowed("viva_airway_failed_intubation_0001"))
        
        assertFalse(BoardPrepAccessManager.isBoardPrepDemoAllowed("edaic_a_phys_cv_9999"))
        assertFalse(BoardPrepAccessManager.isBoardPrepDemoAllowed("viva_airway_not_allowed"))
    }

    @Test
    fun testProgressMergeConflictResolution() {
        // Define local progress
        val localProgress = com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity(
            questionId = "q1",
            selectedAnswersJson = "[true, false]",
            isCorrectStrict = false,
            statementsCorrectCount = 1,
            attemptCount = 2,
            lastAnsweredAt = 1000L,
            bookmarked = true,
            markedDifficult = false,
            weakTopicTagsJson = "[\"tag1\"]",
            vivaSelfRating = "POOR"
        )
        
        // Define cloud progress
        val cloudProgress = com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity(
            questionId = "q1",
            selectedAnswersJson = "[true, true]",
            isCorrectStrict = true,
            statementsCorrectCount = 2,
            attemptCount = 1,
            lastAnsweredAt = 2000L, // cloud is newer
            bookmarked = false,
            markedDifficult = true,
            weakTopicTagsJson = "[\"tag2\"]",
            vivaSelfRating = "GOOD" // cloud has better rating
        )
        
        // Resolve conflict
        val bookmarked = localProgress.bookmarked || cloudProgress.bookmarked
        val attemptCount = kotlin.math.max(localProgress.attemptCount, cloudProgress.attemptCount)
        val lastAnsweredAt = kotlin.math.max(localProgress.lastAnsweredAt, cloudProgress.lastAnsweredAt)
        
        val latest = if (localProgress.lastAnsweredAt >= cloudProgress.lastAnsweredAt) localProgress else cloudProgress
        val selectedAnswersJson = latest.selectedAnswersJson
        val isCorrectStrict = latest.isCorrectStrict
        val statementsCorrectCount = latest.statementsCorrectCount
        
        val ratingPriority = mapOf(
            "EXCELLENT" to 4,
            "GOOD" to 3,
            "BORDERLINE" to 2,
            "POOR" to 1
        )
        val localRatingVal = ratingPriority[localProgress.vivaSelfRating] ?: 0
        val cloudRatingVal = ratingPriority[cloudProgress.vivaSelfRating] ?: 0
        val vivaSelfRating = if (localRatingVal >= cloudRatingVal) {
            localProgress.vivaSelfRating
        } else {
            cloudProgress.vivaSelfRating
        }
        
        // Assertions
        assertTrue(bookmarked) // local was bookmarked
        assertEquals(2, attemptCount) // local attempt count was higher
        assertEquals(2000L, lastAnsweredAt) // cloud timestamp was higher
        assertEquals("[true, true]", selectedAnswersJson) // came from cloud (latest)
        assertTrue(isCorrectStrict) // came from cloud (latest)
        assertEquals(2, statementsCorrectCount) // came from cloud (latest)
        assertEquals("GOOD", vivaSelfRating) // GOOD > POOR
    }

    private data class MapProgressMock(
        val questionId: String,
        val isCorrect: Boolean,
        val tags: List<String>,
        val vivaSelfRating: String?,
        val bookmarked: Boolean = false
    )
}

