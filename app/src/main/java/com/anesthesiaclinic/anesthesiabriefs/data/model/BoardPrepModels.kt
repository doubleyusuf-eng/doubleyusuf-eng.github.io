package com.anesthesiaclinic.anesthesiabriefs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LocalizedText(
    val en: String,
    val tr: String
) {
    fun get(isTurkish: Boolean): String = if (isTurkish) tr else en
}

@Serializable
data class EdaicMtfStatement(
    val label: String, // A, B, C, D, E
    val text: LocalizedText,
    val correctAnswer: Boolean,
    val explanation: LocalizedText
)

@Serializable
data class EdaicMtfQuestion(
    val id: String,
    val boardType: String, // "EDAIC_PART_I"
    val paper: String, // "PAPER_A", "PAPER_B"
    val category: String,
    val topic: String,
    val difficulty: String, // "easy", "medium", "hard"
    val stem: LocalizedText,
    val statements: List<EdaicMtfStatement>,
    val overallExplanation: LocalizedText,
    val keyLearningPoint: LocalizedText,
    val commonTrap: LocalizedText? = null,
    val tags: List<String> = emptyList(),
    val sourceType: String = "ORIGINAL_BOARD_STYLE",
    val referenceNotes: List<String>? = null,
    val officialEndorsement: Boolean = false,
    val version: Int,
    val isActive: Boolean = true,
    val isDeprecated: Boolean = false,
    val deprecatedReason: LocalizedText? = null,
    val replacedByQuestionId: String? = null,
    val isBookmarked: Boolean = false
)

@Serializable
data class SingleBestAnswerQuestion(
    val id: String,
    val boardType: String, // "ABA_BASIC", "ABA_ADVANCED", "EDAIC_SINGLE"
    val category: String,
    val topic: String,
    val difficulty: String, // "easy", "medium", "hard"
    val stem: LocalizedText,
    val options: List<LocalizedText>,
    val correctAnswerIndex: Int,
    val optionExplanations: List<LocalizedText>,
    val overallExplanation: LocalizedText,
    val keyLearningPoint: LocalizedText,
    val commonTrap: LocalizedText? = null,
    val tags: List<String> = emptyList(),
    val sourceType: String = "ORIGINAL_BOARD_STYLE",
    val referenceNotes: List<String>? = null,
    val officialEndorsement: Boolean = false,
    val version: Int,
    val isActive: Boolean = true,
    val isDeprecated: Boolean = false,
    val deprecatedReason: LocalizedText? = null,
    val replacedByQuestionId: String? = null,
    val isBookmarked: Boolean = false
)

@Serializable
data class VivaScenarioQuestion(
    val id: String,
    val boardType: String, // "EDAIC_VIVA", "ABA_APPLIED_STYLE"
    val category: String,
    val topic: String,
    val difficulty: String, // "easy", "medium", "hard"
    val scenario: LocalizedText,
    val examinerQuestions: List<LocalizedText>,
    val expectedAnswerPoints: List<LocalizedText>,
    val redFlags: List<LocalizedText>,
    val followUpQuestions: List<LocalizedText>,
    val keyLearningPoint: LocalizedText,
    val commonTrap: LocalizedText? = null,
    val tags: List<String> = emptyList(),
    val sourceType: String = "ORIGINAL_BOARD_STYLE",
    val referenceNotes: List<String>? = null,
    val officialEndorsement: Boolean = false,
    val version: Int,
    val isActive: Boolean = true,
    val isDeprecated: Boolean = false,
    val deprecatedReason: LocalizedText? = null,
    val replacedByQuestionId: String? = null,
    val isBookmarked: Boolean = false
)

@Serializable
data class BoardPrepPackManifest(
    val packId: String,
    val titleEn: String,
    val titleTr: String,
    val boardType: String,
    val version: Int,
    val questionCount: Int,
    val languages: List<String>,
    val isActive: Boolean,
    val isMandatory: Boolean,
    val storagePath: String,
    val checksumSha256: String,
    val changelogEn: String,
    val changelogTr: String,
    val minAppVersion: Int = 1,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class BoardPrepPack(
    val packId: String,
    val version: Int,
    val boardType: String,
    val languages: List<String>,
    val edaicMtfQuestions: List<EdaicMtfQuestion> = emptyList(),
    val sbaQuestions: List<SingleBestAnswerQuestion> = emptyList(),
    val vivaQuestions: List<VivaScenarioQuestion> = emptyList()
)

@Serializable
data class BoardSpotNote(
    val id: String,
    val exam: String, // "EDAIC_PAPER_A", "EDAIC_PAPER_B", "ABA_BASIC", "ABA_ADVANCED", "EDAIC_VIVA"
    val topic: String,
    val subtopic: String,
    val tr: String,
    val en: String,
    val importance: String, // "high", "medium", "low"
    val tags: List<String> = emptyList(),
    val sourceType: String = "board_review",
    val lastReviewed: String? = null
)

@Serializable
data class DailyClinicalPearl(
    val id: String,
    val category: String,
    val subcategory: String,
    val tr: String,
    val en: String,
    val clinicalUse: String,
    val importance: String,
    val tags: List<String> = emptyList(),
    val sourceType: String = "clinical_review",
    val lastReviewed: String? = null
)

