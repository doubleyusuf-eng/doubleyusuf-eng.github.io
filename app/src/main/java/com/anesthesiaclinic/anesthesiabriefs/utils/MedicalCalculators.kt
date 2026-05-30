package com.anesthesiaclinic.anesthesiabriefs.utils

import kotlin.math.pow
import kotlin.math.sqrt

object MedicalCalculators {

    // ==========================================
    // A. Body & Dosing
    // ==========================================

    fun calculateBMI(weightKg: Double, heightCm: Double): Double {
        if (weightKg <= 0.0 || heightCm <= 0.0) return 0.0
        val heightM = heightCm / 100.0
        return weightKg / heightM.pow(2)
    }

    fun getCategoryFromBMI(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Zayıf (Underweight)"
            bmi < 25.0 -> "Normal Kilolu (Normal)"
            bmi < 30.0 -> "Fazla Kilolu (Overweight)"
            bmi < 35.0 -> "Sınıf I Obez (Obese Class I)"
            bmi < 40.0 -> "Sınıf II Obez (Obese Class II)"
            else -> "Sınıf III Morbid Obez (Obese Class III)"
        }
    }

    fun calculateBSA(weightKg: Double, heightCm: Double): Double {
        if (weightKg <= 0.0 || heightCm <= 0.0) return 0.0
        // Mosteller formula: BSA = sqrt( (W * H) / 3600 )
        return sqrt((weightKg * heightCm) / 3600.0)
    }

    fun calculateIBW(sex: String, heightCm: Double): Double {
        if (heightCm < 120.0) return 0.0
        // Devine Formula
        // Male: 50.0 + 2.3 * ( (heightCm / 2.54) - 60 ) -> simplified to: 50.0 + 0.9 * (heightCm - 152.4)
        // Female: 45.5 + 2.3 * ( (heightCm / 2.54) - 60 ) -> simplified to: 45.5 + 0.9 * (heightCm - 152.4)
        val baseWeight = if (sex.lowercase() == "male") 50.0 else 45.5
        val calculated = baseWeight + 0.9 * (heightCm - 152.4)
        return if (calculated < 0.0) 0.0 else calculated
    }

    fun calculatePBW(sex: String, heightCm: Double): Double {
        if (heightCm < 120.0) return 0.0
        // ARDSNet formula (uses 0.91 instead of 0.9)
        val baseWeight = if (sex.lowercase() == "male") 50.0 else 45.5
        val calculated = baseWeight + 0.91 * (heightCm - 152.4)
        return if (calculated < 0.0) 0.0 else calculated
    }

    fun calculateAdjustedBodyWeight(actualWeightKg: Double, ibwKg: Double, correctionFactor: Double = 0.4): Double {
        if (actualWeightKg <= ibwKg) return ibwKg
        return ibwKg + correctionFactor * (actualWeightKg - ibwKg)
    }

    fun calculateLeanBodyWeight(sex: String, weightKg: Double, heightCm: Double): Double {
        if (weightKg <= 0.0 || heightCm <= 0.0) return 0.0
        val bmi = calculateBMI(weightKg, heightCm)
        if (bmi <= 0.0) return 0.0
        return if (sex.lowercase() == "male") {
            (9270.0 * weightKg) / (6680.0 + 216.0 * bmi)
        } else {
            (9270.0 * weightKg) / (8780.0 + 244.0 * bmi)
        }
    }

    // ==========================================
    // B. Airway
    // ==========================================

    fun calculatePediatricETTSize(ageYears: Double, cuffed: Boolean): Double {
        if (ageYears < 1.0 || ageYears > 12.0) return 0.0
        return if (cuffed) {
            (ageYears / 4.0) + 3.5
        } else {
            (ageYears / 4.0) + 4.0
        }
    }

    fun calculatePediatricETTDepth(ageYears: Double, ettId: Double): Pair<Double, Double> {
        val formula1 = (ageYears / 2.0) + 12.0 // (Age / 2) + 12 cm
        val formula2 = ettId * 3.0 // ETT_ID * 3 cm
        return Pair(formula1, formula2)
    }

    fun selectLMASize(weightKg: Double): String {
        return when {
            weightKg < 5.0 -> "Boyut 1 (<5 kg)"
            weightKg < 10.0 -> "Boyut 1.5 (5-10 kg)"
            weightKg < 20.0 -> "Boyut 2 (10-20 kg)"
            weightKg < 30.0 -> "Boyut 2.5 (20-30 kg)"
            weightKg < 50.0 -> "Boyut 3 (30-50 kg)"
            weightKg < 70.0 -> "Boyut 4 (50-70 kg)"
            weightKg < 100.0 -> "Boyut 5 (70-100 kg)"
            else -> "Boyut 6 (>100 kg)"
        }
    }

    // ==========================================
    // C. Ventilation
    // ==========================================

    fun calculateTidalVolume(pbwKg: Double, mlPerKg: Double): Double {
        if (pbwKg <= 0.0 || mlPerKg <= 0.0) return 0.0
        return pbwKg * mlPerKg
    }

    fun calculatePFRatio(pao2: Double, fio2: Double): Double {
        // fio2 as percentage (e.g. 40.0) or fraction (e.g. 0.40)
        val fractionalFiO2 = if (fio2 > 1.0) fio2 / 100.0 else fio2
        if (fractionalFiO2 <= 0.0 || pao2 <= 0.0) return 0.0
        return pao2 / fractionalFiO2
    }

    fun calculateDrivingPressure(pplat: Double, peep: Double): Double {
        return pplat - peep
    }

    fun calculateAaGradient(fio2: Double, pao2: Double, paco2: Double, age: Double, barometricPressure: Double = 760.0): Double {
        val fractionalFiO2 = if (fio2 > 1.0) fio2 / 100.0 else fio2
        if (fractionalFiO2 <= 0.0) return 0.0
        val pAO2 = (fractionalFiO2 * (barometricPressure - 47.0)) - (paco2 / 0.8)
        return pAO2 - pao2
    }

    // ==========================================
    // D. Hemodynamics
    // ==========================================

    fun calculateMAP(sbp: Double, dbp: Double): Double {
        if (sbp <= dbp || sbp <= 0.0 || dbp <= 0.0) return 0.0
        return (sbp + 2.0 * dbp) / 3.0
    }

    fun calculateShockIndex(hr: Double, sbp: Double): Double {
        if (sbp <= 0.0 || hr <= 0.0) return 0.0
        return hr / sbp
    }

    fun calculateModifiedShockIndex(hr: Double, map: Double): Double {
        if (map <= 0.0 || hr <= 0.0) return 0.0
        return hr / map
    }

    // ==========================================
    // E. Fluids & Blood
    // ==========================================

    fun calculateEBV(weightKg: Double, category: String): Double {
        if (weightKg <= 0.0) return 0.0
        val factor = when (category.lowercase()) {
            "adult_male" -> 70.0
            "adult_female" -> 65.0
            "child" -> 75.0
            "infant" -> 80.0
            "neonate" -> 85.0
            else -> 70.0
        }
        return weightKg * factor
    }

    fun calculateAllowableBloodLoss(ebvMl: Double, initialHb: Double, lowestHb: Double): Double {
        if (ebvMl <= 0.0 || initialHb <= lowestHb || initialHb <= 0.0) return 0.0
        return ebvMl * (initialHb - lowestHb) / initialHb
    }

    fun calculateMaintenanceFluid421(weightKg: Double): Double {
        if (weightKg <= 0.0) return 0.0
        return when {
            weightKg <= 10.0 -> weightKg * 4.0
            weightKg <= 20.0 -> 40.0 + (weightKg - 10.0) * 2.0
            else -> 60.0 + (weightKg - 20.0) * 1.0
        }
    }

    fun calculateFluidDeficit(maintenanceRate: Double, fastingHours: Double): Double {
        if (maintenanceRate <= 0.0 || fastingHours <= 0.0) return 0.0
        return maintenanceRate * fastingHours
    }

    // ==========================================
    // F. Drug Dosing & Infusion
    // ==========================================

    fun calculateLocalAnestheticMaxVolume(weightKg: Double, maxMgKg: Double, concentrationPercent: Double): Double {
        if (weightKg <= 0.0 || maxMgKg <= 0.0 || concentrationPercent <= 0.0) return 0.0
        val maxDoseMg = weightKg * maxMgKg
        val mgPerMl = concentrationPercent * 10.0 // 1% = 10 mg/mL
        return maxDoseMg / mgPerMl
    }

    fun calculateInfusionRateMcgKgMin(doseMcgKgMin: Double, weightKg: Double, concentrationMcgPerMl: Double): Double {
        if (doseMcgKgMin <= 0.0 || weightKg <= 0.0 || concentrationMcgPerMl <= 0.0) return 0.0
        // mL/hour = (dose * weight * 60) / concentration
        return (doseMcgKgMin * weightKg * 60.0) / concentrationMcgPerMl
    }

    fun calculateInfusionRateMgKgHour(doseMgKgHour: Double, weightKg: Double, concentrationMgPerMl: Double): Double {
        if (doseMgKgHour <= 0.0 || weightKg <= 0.0 || concentrationMgPerMl <= 0.0) return 0.0
        // mL/hour = (dose * weight) / concentration
        return (doseMgKgHour * weightKg) / concentrationMgPerMl
    }

    fun calculateLipidRescueDose(weightKg: Double): Pair<Double, Double> {
        if (weightKg <= 0.0) return Pair(0.0, 0.0)
        // 20% Lipid Emulsion:
        // Bolus: 1.5 mL/kg over 1 min
        // Infusion: 0.25 mL/kg/min (usually for 20-60 mins)
        val bolusMl = 1.5 * weightKg
        val infusionRateMlMin = 0.25 * weightKg
        return Pair(bolusMl, infusionRateMlMin)
    }

    fun calculateLipidRescue(weightKg: Double): Pair<Double, Double> {
        return calculateLipidRescueDose(weightKg)
    }

    // ==========================================
    // G. ICU / Electrolytes & Sevo
    // ==========================================

    fun calculateCorrectedSodium(measuredNa: Double, glucoseMgDl: Double, correctionFactor: Double = 1.6): Double {
        if (measuredNa <= 0.0) return 0.0
        if (glucoseMgDl <= 100.0) return measuredNa
        return measuredNa + correctionFactor * ((glucoseMgDl - 100.0) / 100.0)
    }

    fun calculateAnionGap(na: Double, cl: Double, hco3: Double, albumin: Double? = null): Double {
        val basicGap = na - (cl + hco3)
        if (albumin == null) return basicGap
        // Corrected for albumin: Anion Gap + 2.5 * (4.0 - Albumin)
        return basicGap + 2.5 * (4.0 - albumin)
    }

    fun calculateSerumOsmolality(na: Double, glucoseMgDl: Double, bunMgDl: Double): Double {
        if (na <= 0.0) return 0.0
        // Osmolality = 2*Na + glucose/18 + BUN/2.8
        return (2.0 * na) + (glucoseMgDl / 18.0) + (bunMgDl / 2.8)
    }

    fun calculateCorrectedCalcium(measuredCa: Double, albumin: Double): Double {
        if (measuredCa <= 0.0 || albumin <= 0.0) return 0.0
        // Corrected Ca = Measured Ca + 0.8 * (4.0 - Albumin)
        return measuredCa + 0.8 * (4.0 - albumin)
    }

    fun calculateAgeAdjustedMAC(macAtAge40: Double, ageYears: Double): Double {
        if (macAtAge40 <= 0.0 || ageYears <= 0.0) return 0.0
        // MAC_age = MAC_40 * 10^(-0.00269 * (age - 40))
        return macAtAge40 * 10.0.pow(-0.00269 * (ageYears - 40.0))
    }

    // ==========================================
    // H. Clinical Scores
    // ==========================================

    fun calculateApfelScore(female: Boolean, nonSmoker: Boolean, historyPONV: Boolean, postOpOpioids: Boolean): Pair<Int, Int> {
        var score = 0
        if (female) score++
        if (nonSmoker) score++
        if (historyPONV) score++
        if (postOpOpioids) score++

        val riskPercent = when (score) {
            0 -> 10
            1 -> 21
            2 -> 39
            3 -> 61
            4 -> 79
            else -> 0
        }
        return Pair(score, riskPercent)
    }

    fun calculateqSOFA(rrGe22: Boolean, alteredMentation: Boolean, sbpLe100: Boolean): Int {
        var score = 0
        if (rrGe22) score++
        if (alteredMentation) score++
        if (sbpLe100) score++
        return score
    }

    fun calculateGCS(eyeOpen: Int, verbalResponse: Int, motorResponse: Int): Pair<Int, String> {
        val total = (eyeOpen.coerceIn(1, 4)) + (verbalResponse.coerceIn(1, 5)) + (motorResponse.coerceIn(1, 6))
        val severity = when {
            total >= 13 -> "Hafif (Mild) Kafa Travması"
            total >= 9 -> "Orta (Moderate) Kafa Travması"
            else -> "Ağır (Severe) Kafa Travması - Entübasyon Endike Olabilir!"
        }
        return Pair(total, severity)
    }

    // ==========================================
    // I. Preoperative Fasting (NPO)
    // ==========================================
    fun calculateNpoSafety(
        intakeType: String,
        lastIntakeHour: Int,
        lastIntakeMinute: Int,
        plannedHour: Int,
        plannedMinute: Int,
        patientGroup: String,
        hasRiskFlags: Boolean
    ): NpoResult {
        // Calculate difference in hours
        var diffMinutes = (plannedHour * 60 + plannedMinute) - (lastIntakeHour * 60 + lastIntakeMinute)
        if (diffMinutes < 0) {
            // Account for next day
            diffMinutes += 24 * 60
        }
        val elapsedHours = diffMinutes.toDouble() / 60.0

        val requiredFastingHours = when (intakeType.lowercase()) {
            "clear_liquid" -> 2.0
            "breast_milk" -> 4.0
            "infant_formula" -> 6.0
            "nonhuman_milk" -> 6.0
            "light_meal" -> 6.0
            "fatty_meal" -> 8.0
            else -> 6.0
        }

        val remainingHours = requiredFastingHours - elapsedHours

        val status = when {
            patientGroup.lowercase() == "emergency" || patientGroup.lowercase() == "high_aspiration_risk" || hasRiskFlags -> {
                "high_risk_review_required"
            }
            elapsedHours >= requiredFastingHours -> {
                "suitable"
            }
            else -> {
                "wait"
            }
        }

        return NpoResult(
            elapsedHours = elapsedHours,
            requiredHours = requiredFastingHours,
            remainingHours = if (remainingHours < 0.0) 0.0 else remainingHours,
            status = status
        )
    }

    // ==========================================
    // J. Difficult Airway Screening
    // ==========================================
    fun scoreDifficultAirway(
        mallampati: Int,
        mouthOpeningCm: Double,
        thyromentalDistanceCm: Double,
        neckMobilityLimited: Boolean,
        upperLipBiteClass: Int,
        prevDifficultAirway: Boolean,
        hasOsa: Boolean,
        isObese: Boolean,
        hasBeard: Boolean,
        isEdentulous: Boolean,
        hasCervicalLimit: Boolean,
        isPregnant: Boolean,
        hasAirwayPathology: Boolean
    ): AirwayRiskResult {
        var score = 0
        if (mallampati >= 3) score += 2
        if (mouthOpeningCm < 3.0) score += 3
        if (thyromentalDistanceCm < 6.5) score += 2
        if (neckMobilityLimited) score += 2
        if (upperLipBiteClass >= 2) score += 1
        if (prevDifficultAirway) score += 4
        if (hasOsa) score += 1
        if (isObese) score += 1
        if (hasCervicalLimit) score += 2
        if (hasAirwayPathology) score += 5

        // Count minor indicators
        var minorCount = 0
        if (hasBeard) minorCount++
        if (isEdentulous) minorCount++
        if (isPregnant) minorCount++

        val riskLevel = when {
            hasAirwayPathology || prevDifficultAirway || mouthOpeningCm < 3.0 || mallampati == 4 -> "high"
            mallampati == 3 || neckMobilityLimited || thyromentalDistanceCm < 6.5 || score >= 5 || minorCount >= 2 -> "caution"
            else -> "low"
        }

        // Determine affected domains
        val domains = mutableListOf<String>()
        if (mouthOpeningCm < 3.0 || neckMobilityLimited || hasCervicalLimit || hasAirwayPathology) {
            domains.add("Zor Laringoskopi / Entübasyon")
        }
        if (hasBeard || isEdentulous || isObese || hasOsa || neckMobilityLimited) {
            domains.add("Zor Maske Ventilasyonu")
        }
        if (mouthOpeningCm < 3.0 || hasAirwayPathology) {
            domains.add("Zor Supraglottik Havayolu (SGA) Yerleşimi")
        }
        if (hasAirwayPathology || hasCervicalLimit || thyromentalDistanceCm < 6.0) {
            domains.add("Zor Ön Boyun Erişimi (Cerrahi Havayolu)")
        }

        val recommendations = mutableListOf<String>()
        if (riskLevel == "high") {
            recommendations.add("Videolaringoskop (VL) ve Fiberoptik Bronkoskop (FOB) hazır bulundurun.")
            recommendations.add("Ek kıdemli anestezi hekimi ve cerrahi havayolu setini ameliyathanede hazır tutun.")
            recommendations.add("Uyanık Fiberoptik Entübasyon (Awake FOI) endikasyonunu değerlendirin.")
        } else if (riskLevel == "caution") {
            recommendations.add("Alternatif laringoskop bıçakları (Macintosh, Miller, D-Blade) hazır edin.")
            recommendations.add("Supraglottik havayolu cihazı (LMA) ve entübasyon tüpü kılavuzlarını (bougie/stile) el altında bulundurun.")
        } else {
            recommendations.add("Standart havayolu ekipmanları (maske, airway, laringoskop) yeterlidir.")
        }

        return AirwayRiskResult(
            score = score,
            riskLevel = riskLevel,
            affectedDomains = domains,
            recommendations = recommendations
        )
    }

    // ==========================================
    // K. Pediatric Emergency & Induction Dosing
    // ==========================================
    fun calculatePediatricDoses(weightKg: Double): PediatricDoseTable {
        if (weightKg <= 0.0) return PediatricDoseTable(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

        val adrMg = weightKg * 0.01 // 10 mcg/kg
        val adrMl = weightKg * 0.1 // 1:10000 dilution (0.1 mg/mL)

        val atrMgRaw = weightKg * 0.02
        val atrMg = atrMgRaw.coerceIn(0.1, 0.5)
        val atrMl = atrMg / 0.1 // Diluted to 0.1 mg/mL

        val propMg = weightKg * 2.5
        val propMl = propMg / 10.0 // 1% = 10 mg/mL

        val ketMg = weightKg * 2.0
        val ketMl = ketMg / 10.0 // Diluted to 10 mg/mL

        val fenMcg = weightKg * 1.5
        val fenMl = fenMcg / 50.0 // 50 mcg/mL standard ampoule

        val rocMg = weightKg * 0.6
        val rocMl = rocMg / 10.0 // 10 mg/mL standard ampoule

        val succMg = weightKg * 1.5
        val succMl = succMg / 20.0 // 20 mg/mL standard ampoule

        return PediatricDoseTable(
            adrenalinMg = adrMg, adrenalinMl = adrMl,
            atropinMg = atrMg, atropinMl = atrMl,
            propofolMg = propMg, propofolMl = propMl,
            ketaminMg = ketMg, ketaminMl = ketMl,
            fentanylMcg = fenMcg, fentanylMl = fenMl,
            rocuroniumMg = rocMg, rocuroniumMl = rocMl,
            succinylcholineMg = succMg, succinylcholineMl = succMl
        )
    }

    // ==========================================
    // L. Cardiovascular Support Perfusion
    // ==========================================
    fun calculateInfusionRate(
        drug: String,
        weightKg: Double,
        syringeMg: Double,
        syringeMl: Double,
        targetDose: Double
    ): Double {
        if (weightKg <= 0.0 || syringeMg <= 0.0 || syringeMl <= 0.0 || targetDose <= 0.0) return 0.0

        val concMcgMl = (syringeMg * 1000.0) / syringeMl

        return if (drug.lowercase() == "nitrogliserin") {
            // Target dose is in mcg/min
            (targetDose * 60.0) / concMcgMl
        } else {
            // Target dose is in mcg/kg/min
            (targetDose * weightKg * 60.0) / concMcgMl
        }
    }

    // ==========================================
    // M. Malignant Hyperthermia Dantrolene
    // ==========================================
    fun calculateDantroleneRescue(weightKg: Double): DantroleneResult {
        if (weightKg <= 0.0) return DantroleneResult(0.0, 0, 0.0)

        val bolusMg = weightKg * 2.5
        val totalVials = kotlin.math.ceil(bolusMg / 20.0).toInt()
        val sterileWaterMl = totalVials * 60.0 // 60 mL per vial

        return DantroleneResult(
            bolusMg = bolusMg,
            totalVials = totalVials,
            sterileWaterMl = sterileWaterMl
        )
    }
}

data class NpoResult(
    val elapsedHours: Double,
    val requiredHours: Double,
    val remainingHours: Double,
    val status: String // "suitable", "wait", "high_risk_review_required"
)

data class AirwayRiskResult(
    val score: Int,
    val riskLevel: String, // "low", "caution", "high"
    val affectedDomains: List<String>,
    val recommendations: List<String>
)

data class PediatricDoseTable(
    val adrenalinMg: Double,
    val adrenalinMl: Double,
    val atropinMg: Double,
    val atropinMl: Double,
    val propofolMg: Double,
    val propofolMl: Double,
    val ketaminMg: Double,
    val ketaminMl: Double,
    val fentanylMcg: Double,
    val fentanylMl: Double,
    val rocuroniumMg: Double,
    val rocuroniumMl: Double,
    val succinylcholineMg: Double,
    val succinylcholineMl: Double
)

data class DantroleneResult(
    val bolusMg: Double,
    val totalVials: Int,
    val sterileWaterMl: Double
)


