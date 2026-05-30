package com.anesthesiaclinic.anesthesiabriefs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String,
    val fullName: String,
    val email: String,
    val specialty: String,
    val institution: String,
    val diplomaNumber: String? = null,
    val isVerifiedClinician: Boolean = false,
    val isPremium: Boolean = false,
    val premiumUntil: String? = null,
    val language: String = "tr",
    val themeMode: String = "system",
    val unitSystem: String = "metric",
    val disclaimerAcceptedAt: String? = null
)

@Serializable
data class Drug(
    val drugId: String,
    val nameTr: String,
    val nameEn: String,
    val genericName: String,
    val brandNames: List<String> = emptyList(),
    val category: String,
    val atcCode: String? = null,
    val drugClass: String? = null,
    val mechanismTr: String? = null,
    val adultDoses: Map<String, DoseInfo> = emptyMap(),
    val pediatricDoses: Map<String, DoseInfo> = emptyMap(),
    val specialPopulations: Map<String, String> = emptyMap(),
    val pharmacokinetics: PharmacokineticsInfo? = null,
    val contraindications: List<String> = emptyList(),
    val warnings: List<String> = emptyList(),
    val sideEffectsCommon: List<String> = emptyList(),
    val sideEffectsSerious: List<String> = emptyList(),
    val clinicalPearls: List<String> = emptyList(),
    val storage: String? = null,
    val preparation: String? = null,
    val maxMgkgWithEpi: Double? = null,
    val maxMgkgWithoutEpi: Double? = null,
    val absoluteMaxMg: Double? = null,
    val sources: List<String> = emptyList(),
    val lastReviewed: String? = null,
    val reviewer: String? = null,
    val requiresClinicalValidation: Boolean = true,
    val isPremium: Boolean = false
)

@Serializable
data class DoseInfo(
    val dose: String,
    val notes: String? = null
)

@Serializable
data class PharmacokineticsInfo(
    val onset: String? = null,
    val peak: String? = null,
    val duration: String? = null,
    val halfLife: String? = null,
    val elimination: String? = null
)

@Serializable
data class Algorithm(
    val algorithmId: String,
    val titleTr: String,
    val titleEn: String,
    val category: String,
    val urgencyLevel: String, // "critical", "high", "moderate", "routine"
    val estimatedMinutes: Int,
    val startNode: String,
    val nodes: Map<String, AlgorithmNode> = emptyMap(),
    val sources: List<String> = emptyList(),
    val lastReviewed: String? = null,
    val reviewer: String? = null,
    val requiresClinicalValidation: Boolean = true,
    val isPremium: Boolean = false
)

@Serializable
data class AlgorithmNode(
    val type: String, // "question", "action", "monitor", "end"
    val textTr: String,
    val textEn: String? = null,
    val options: List<AlgorithmOption> = emptyList(),
    val relatedDrugs: List<String> = emptyList(),
    val relatedCalculators: List<String> = emptyList(),
    val warningLevel: String? = null, // "none", "moderate", "critical"
    val next: String? = null
)

@Serializable
data class AlgorithmOption(
    val labelTr: String,
    val labelEn: String? = null,
    val next: String
)

@Serializable
data class Article(
    val id: Int,
    val pmid: String? = null,
    val doi: String? = null,
    val titleTr: String,
    val titleEn: String,
    val authors: List<String> = emptyList(),
    val journal: String,
    val publicationDate: String,
    val keyMessageTr: String,
    val studyDesignTr: String,
    val mainFindingsTr: String,
    val clinicalTakeawayTr: String,
    val limitationsTr: String? = null,
    val practiceImpactTr: String? = null,
    val impactRating: Int = 3,
    val aiGeneratedDraft: Boolean = false,
    val aiCommentaryTr: String? = null,
    val physicianReviewed: Boolean = false,
    val reviewer: String? = null,
    val lastReviewed: String? = null,
    val requiresClinicalValidation: Boolean = true,
    val categories: List<String> = emptyList(),
    val isFeatured: Boolean = false,
    val isPublished: Boolean = true,
    val readingTimeMinutes: Int = 4,
    val sourceUrl: String? = null,
    val instagramPostUrl: String? = null,
    val instagramImageUrl: String? = null
)

@Serializable
data class Calculator(
    val slug: String,
    val category: String,
    val functionName: String,
    val titleTr: String,
    val titleEn: String,
    val descriptionTr: String,
    val descriptionEn: String? = null,
    val formula: String? = null,
    val formulaExplanationTr: String? = null,
    val warningTr: String? = null,
    val populationRestrictions: List<String> = emptyList(),
    val isPremium: Boolean = false,
    val references: List<String> = emptyList()
)

// AI Assistant structured responses
@Serializable
data class AiStructuredResponse(
    val safetyLevel: String, // "routine", "urgent", "emergency"
    val patientIdentifierDetected: Boolean = false,
    val userAppearsToBeClinician: Boolean = true,
    val notASubstituteWarning: String,
    val conversationalText: String? = null,
    val immediateRedFlags: List<String> = emptyList(),
    val missingCriticalInformation: List<String> = emptyList(),
    val likelyClinicalCategoriesToConsider: List<String> = emptyList(),
    val suggestedNextStepsGeneral: List<String> = emptyList(),
    val relatedAlgorithms: List<AiSuggestionItem> = emptyList(),
    val relatedCalculators: List<AiSuggestionItem> = emptyList(),
    val relatedDrugCards: List<AiSuggestionItem> = emptyList(),
    val doNotMiss: List<String> = emptyList(),
    val whenToEscalate: List<String> = emptyList(),
    val referencesToShow: List<String> = emptyList(),
    val followUpQuestions: List<String> = emptyList()
)

@Serializable
data class AiSuggestionItem(
    val id: String,
    val titleTr: String
)

@Serializable
data class Message(
    val id: Int,
    val role: String, // "user", "assistant"
    val content: String,
    val structuredResponse: AiStructuredResponse? = null,
    val timestamp: Long = System.currentTimeMillis()
)
