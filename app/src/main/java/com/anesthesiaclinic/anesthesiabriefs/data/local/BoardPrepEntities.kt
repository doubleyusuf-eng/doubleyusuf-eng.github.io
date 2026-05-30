package com.anesthesiaclinic.anesthesiabriefs.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "board_questions")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val packId: String, // associated packId
    val boardType: String, // "EDAIC_PART_I", "EDAIC_VIVA", "ABA_BASIC", "ABA_ADVANCED"
    val questionType: String, // "EDAIC_MTF", "SINGLE_BEST_ANSWER", "VIVA_SCENARIO"
    val category: String,
    val topic: String,
    val difficulty: String,
    val stemEn: String,
    val stemTr: String,
    val sourceType: String, // "ORIGINAL_BOARD_STYLE"
    val version: Int,
    val isBookmarked: Boolean,
    val tagsJson: String, // list of strings
    val questionJson: String // serialized EdaicMtfQuestion, SingleBestAnswerQuestion or VivaScenarioQuestion
)

@Entity(tableName = "board_user_progress")
data class UserProgressEntity(
    @PrimaryKey val questionId: String,
    val selectedAnswersJson: String, // user choice representation
    val isCorrectStrict: Boolean, // strict mode correctness
    val statementsCorrectCount: Int, // per-statement correctness count (out of 5 for EDAIC MTF, otherwise 0 or 1)
    val attemptCount: Int,
    val lastAnsweredAt: Long,
    val bookmarked: Boolean,
    val markedDifficult: Boolean,
    val weakTopicTagsJson: String, // list of strings
    val vivaSelfRating: String? = null // self-rating: POOR, BORDERLINE, GOOD, EXCELLENT
)

@Entity(tableName = "board_pack_metadata")
data class PackMetadataEntity(
    @PrimaryKey val packId: String,
    val version: Int,
    val checksumSha256: String,
    val installedAt: Long
)
