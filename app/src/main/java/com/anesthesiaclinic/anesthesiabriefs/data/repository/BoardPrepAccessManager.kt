package com.anesthesiaclinic.anesthesiabriefs.data.repository

import android.content.Context

object BoardPrepAccessManager {

    private const val PREFS_NAME = "anesthesia_briefs_prefs"
    private const val KEY_PREMIUM_UNLOCKED = "board_prep_premium_unlocked"

    // Deterministic free preview/demo IDs
    private val DEMO_QUESTION_IDS = setOf(
        // First 5 EDAIC MTF questions from EDAIC Paper A Pack 1
        "edaic_a_phys_cv_0001",
        "edaic_a_phys_resp_0001",
        "edaic_a_pharm_volatiles_0001",
        "edaic_a_monitor_capno_0001",
        "edaic_a_equipment_vaporizers_0001",

        // First 5 ABA BASIC SBA questions
        "aba_basic_pharm_volatile_0001",
        "aba_basic_pharm_iv_0001",
        "aba_basic_pharm_iv_0002",
        "aba_basic_pharm_opioid_0001",
        "aba_basic_nmba_0001",

        // First 2 EDAIC Viva scenarios
        "viva_airway_failed_intubation_0001",
        "viva_airway_cico_0001"
    )

    fun isPremiumUser(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_PREMIUM_UNLOCKED, false)
    }

    // TODO: Replace with Google Play Billing / validated entitlement before production release.
    fun setPremiumUnlocked(context: Context, unlocked: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_PREMIUM_UNLOCKED, unlocked).apply()
    }

    fun isBoardPrepDemoAllowed(questionId: String): Boolean {
        return DEMO_QUESTION_IDS.contains(questionId)
    }

    fun canAccessFullBoardPrep(context: Context): Boolean {
        return isPremiumUser(context)
    }

    fun canAccessExamMode(context: Context): Boolean {
        return isPremiumUser(context)
    }

    fun canAccessWeakTopics(context: Context): Boolean {
        return isPremiumUser(context)
    }

    fun canAccessAnalytics(context: Context): Boolean {
        return isPremiumUser(context)
    }

    fun canAccessFullViva(context: Context): Boolean {
        return isPremiumUser(context)
    }

    fun canAccessCloudSync(context: Context): Boolean {
        return isPremiumUser(context)
    }
}
