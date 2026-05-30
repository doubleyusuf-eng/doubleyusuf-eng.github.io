package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator
import com.anesthesiaclinic.anesthesiabriefs.data.repository.FirestoreRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*
import com.anesthesiaclinic.anesthesiabriefs.utils.MedicalCalculators
import com.anesthesiaclinic.anesthesiabriefs.utils.TranslationHelper
import java.util.Locale
import android.content.Context
import android.content.Intent
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorDetailScreen(
    slug: String,
    currentLanguage: String,
    unitSystem: String,
    onBackClick: () -> Unit,
    onAskAiContext: (String) -> Unit
) {
    val calculator = remember(slug) { FirestoreRepository.getCalculatorBySlug(slug) } ?: return
    val context = LocalContext.current
    val isTurkish = currentLanguage == "tr"
    fun t(text: String): String = TranslationHelper.translate(text, currentLanguage)
    fun t(tr: String, en: String): String = if (isTurkish) tr else en

    val isImperial = unitSystem == "imperial"

    // Weight helpers (1 kg = 2.20462 lb)
    fun toDisplayWeight(kg: Double): Double = if (isImperial) kg * 2.20462 else kg
    fun toInternalWeight(lb: Double): Double = if (isImperial) lb / 2.20462 else lb
    val weightUnit = if (isImperial) "lb" else "kg"

    // Height/Distance helpers (1 inch = 2.54 cm)
    fun toDisplayDist(cm: Double): Double = if (isImperial) cm / 2.54 else cm
    fun toInternalDist(inch: Double): Double = if (isImperial) inch * 2.54 else inch
    val distUnit = if (isImperial) t("inç", "in") else "cm"



    // ----------------------------------------------------
    // Standard Calculator State Variables
    // ----------------------------------------------------
    var inputVal1 by remember(slug) { mutableStateOf(if (slug == "gcs") "4" else if (slug == "ebv_abl") "70.0" else if (slug == "bmi" || slug == "bsa") "70.0" else if (slug == "maintenance_421") "70.0" else "70.0") }
    var inputVal2 by remember(slug) { mutableStateOf(if (slug == "gcs") "5" else if (slug == "ebv_abl") "14.0" else if (slug == "bmi" || slug == "bsa") "170.0" else "170.0") }
    var inputVal3 by remember(slug) { mutableStateOf(if (slug == "gcs") "6" else if (slug == "ebv_abl") "9.0" else "10.0") }
    var selectedCategory by remember { mutableStateOf("adult_male") }

    var resultText by remember { mutableStateOf("") }
    var interpretationText by remember { mutableStateOf("") }
    var calculatedValue by remember { mutableStateOf(0.0) }
    var hasCalculated by remember { mutableStateOf(false) }

    var isFormulaExpanded by remember { mutableStateOf(false) }
    var isSourcesExpanded by remember { mutableStateOf(false) }

    // ----------------------------------------------------
    // ASA Physical Status State Variables
    // ----------------------------------------------------
    var selectedAsaClass by remember { mutableStateOf("ASA I") }
    var isEmergencyModifier by remember { mutableStateOf(false) }

    // ----------------------------------------------------
    // NPO Fasting Timer State Variables
    // ----------------------------------------------------
    var selectedNpoIntakeType by remember { mutableStateOf("clear_liquid") }
    var lastIntakeHour by remember { mutableStateOf(8) }
    var lastIntakeMinute by remember { mutableStateOf(0) }
    var plannedSurgeryHour by remember { mutableStateOf(14) }
    var plannedSurgeryMinute by remember { mutableStateOf(0) }
    var selectedNpoPatientGroup by remember { mutableStateOf("adult") }
    
    // NPO Risk Flags
    var npoDiabetes by remember { mutableStateOf(false) }
    var npoGastroparesis by remember { mutableStateOf(false) }
    var npoGerd by remember { mutableStateOf(false) }
    var npoObesity by remember { mutableStateOf(false) }
    var npoOpioids by remember { mutableStateOf(false) }
    var npoBowelObstruction by remember { mutableStateOf(false) }
    var npoPregnancy by remember { mutableStateOf(false) }
    var npoGlp1Weekly by remember { mutableStateOf(false) }
    var npoGlp1Daily by remember { mutableStateOf(false) }
    var npoGlp1GiEscalation by remember { mutableStateOf(false) }

    // ----------------------------------------------------
    // Difficult Airway State Variables
    // ----------------------------------------------------
    var selectedMallampati by remember { mutableStateOf(1) }
    var mouthOpeningCm by remember { mutableStateOf(4.5) }
    var thyromentalDistanceCm by remember { mutableStateOf(7.5) }
    var isNeckMobilityLimited by remember { mutableStateOf(false) }
    var selectedUpperLipBiteClass by remember { mutableStateOf(1) }
    
    // Checklist secondary parameters
    var airwayPrevDifficult by remember { mutableStateOf(false) }
    var airwayOsa by remember { mutableStateOf(false) }
    var airwayObese by remember { mutableStateOf(false) }
    var airwayBeard by remember { mutableStateOf(false) }
    var airwayEdentulous by remember { mutableStateOf(false) }
    var airwayCervicalLimit by remember { mutableStateOf(false) }
    var airwayPregnancy by remember { mutableStateOf(false) }
    var airwayPathology by remember { mutableStateOf(false) }

    // ----------------------------------------------------
    // ASRA Coagulation Table State Variables
    // ----------------------------------------------------
    var coagSearchQuery by remember { mutableStateOf("") }

    // ----------------------------------------------------
    // Phase 9 Custom Module State Variables
    // ----------------------------------------------------
    var pediatricWeightKg by remember { mutableStateOf(15.0) }
    
    var selectedInfusionDrug by remember { mutableStateOf("noradrenalin") }
    var infusionWeightKg by remember { mutableStateOf(70.0) }
    var infusionSyringeMg by remember { mutableStateOf(4.0) } // Noradrenaline 4 mg in 50 mL default
    var infusionSyringeMl by remember { mutableStateOf(50.0) }
    var infusionTargetDose by remember { mutableStateOf(0.1) } // 0.1 mcg/kg/min default

    var selectedMixtureTab by remember { mutableStateOf("labor_epidural") }
    
    var dantroleneWeightKg by remember { mutableStateOf(70.0) }
    var lipidWeightKg by remember { mutableStateOf(70.0) }

    // ----------------------------------------------------
    // 10 New Calculators State Variables
    // ----------------------------------------------------
    var apfelFemale by remember { mutableStateOf(false) }
    var apfelNonSmoker by remember { mutableStateOf(false) }
    var apfelHistoryPONV by remember { mutableStateOf(false) }
    var apfelPostOpOpioids by remember { mutableStateOf(false) }

    var aldreteActivity by remember { mutableStateOf(2) }
    var aldreteRespiration by remember { mutableStateOf(2) }
    var aldreteCirculation by remember { mutableStateOf(2) }
    var aldreteConsciousness by remember { mutableStateOf(2) }
    var aldreteO2Sat by remember { mutableStateOf(2) }

    var rcriHighRiskSurgery by remember { mutableStateOf(false) }
    var rcriIschemicHeart by remember { mutableStateOf(false) }
    var rcriHeartFailure by remember { mutableStateOf(false) }
    var rcriCerebrovascular by remember { mutableStateOf(false) }
    var rcriInsulin by remember { mutableStateOf(false) }
    var rcriCreatinine by remember { mutableStateOf(false) }

    var stopSnoring by remember { mutableStateOf(false) }
    var stopTiredness by remember { mutableStateOf(false) }
    var stopApnea by remember { mutableStateOf(false) }
    var stopPressure by remember { mutableStateOf(false) }
    var stopBmi by remember { mutableStateOf(false) }
    var stopAge by remember { mutableStateOf(false) }
    var stopNeck by remember { mutableStateOf(false) }
    var stopGender by remember { mutableStateOf(false) }

    var laWeightKg by remember { mutableStateOf(70.0) }
    var laSelectedAgent by remember { mutableStateOf("lidocaine") }
    var laWithEpinephrine by remember { mutableStateOf(false) }
    var laConcentrationPercent by remember { mutableStateOf(1.0) }

    var sugammadexWeightKg by remember { mutableStateOf(70.0) }
    var sugammadexTOFLevel by remember { mutableStateOf("moderate") }

    var ettAgeYears by remember { mutableStateOf(5.0) }
    var ettCuffed by remember { mutableStateOf(true) }

    var tvSex by remember { mutableStateOf("male") }
    var tvHeightCm by remember { mutableStateOf(170.0) }
    var tvTargetMlPerKg by remember { mutableStateOf(6.0) }

    var gfrAgeYears by remember { mutableStateOf(50.0) }
    var gfrWeightKg by remember { mutableStateOf(70.0) }
    var gfrSex by remember { mutableStateOf("male") }
    var gfrCreatinine by remember { mutableStateOf(1.0) }

    var opioidType by remember { mutableStateOf("fentanyl") }
    var opioidAmount by remember { mutableStateOf(100.0) }


    // Standard Math Calculation Engine
    fun runCalculation() {
        val val1 = inputVal1.toDoubleOrNull() ?: 0.0
        val val2 = inputVal2.toDoubleOrNull() ?: 0.0
        val val3 = inputVal3.toDoubleOrNull() ?: 0.0

        when (calculator.slug) {
            "bmi" -> {
                val bmi = MedicalCalculators.calculateBMI(val1, val2)
                calculatedValue = bmi
                resultText = String.format(Locale.US, "%.1f kg/m²", bmi)
                interpretationText = if (isTurkish) {
                    MedicalCalculators.getCategoryFromBMI(bmi)
                } else {
                    TranslationHelper.translate(MedicalCalculators.getCategoryFromBMI(bmi), currentLanguage)
                }
            }
            "bsa" -> {
                val bsa = MedicalCalculators.calculateBSA(val1, val2)
                calculatedValue = bsa
                resultText = String.format(Locale.US, "%.2f m²", bsa)
                
                val displayWeight = if (isImperial) toDisplayWeight(val1) else val1
                val displayHeight = if (isImperial) toDisplayDist(val2) else val2
                val weightLabel = weightUnit
                val heightLabel = distUnit
                
                interpretationText = t(
                    "Vücut Yüzey Alanı (BSA): ${String.format(Locale.US, "%.2f", bsa)} m²\nBoy: ${String.format(Locale.US, "%.1f", displayHeight)} $heightLabel / Ağırlık: ${String.format(Locale.US, "%.1f", displayWeight)} $weightLabel\nBu parametreyi hemodinamik indeksler ve böbrek/kemoterapi dozlama hesaplamalarında güvenle kullanın.",
                    "Body Surface Area (BSA): ${String.format(Locale.US, "%.2f", bsa)} m²\nHeight: ${String.format(Locale.US, "%.1f", displayHeight)} $heightLabel / Weight: ${String.format(Locale.US, "%.1f", displayWeight)} $weightLabel\nUse this parameter safely for hemodynamic indexes and renal/chemotherapy dosage calculations."
                )
            }
            "map" -> {
                val map = MedicalCalculators.calculateMAP(val1, val2)
                calculatedValue = map
                resultText = String.format(Locale.US, "%.1f mmHg", map)
                interpretationText = if (map >= 65.0) {
                    t("Normal Organ Perfüzyonu", "Normal Organ Perfusion")
                } else {
                    t("🚨 Kritik Perfüzyon Seviyesi (MAP < 65 mmHg)!", "🚨 Critical Perfusion Level (MAP < 65 mmHg)!")
                }
            }
            "ebv_abl" -> {
                val ebv = MedicalCalculators.calculateEBV(val1, selectedCategory)
                val abl = MedicalCalculators.calculateAllowableBloodLoss(ebv, val2, val3)
                calculatedValue = abl
                resultText = String.format(Locale.US, "%.0f mL", abl)
                val formattedEbv = String.format(Locale.US, "%.0f", ebv)
                interpretationText = t(
                    "Tahmini Kan Hacmi: $formattedEbv mL\nHedef Hb sınırına kadar tolere edilebilir kayıptır.",
                    "Estimated Blood Volume: $formattedEbv mL\nThis is the allowable loss tolerated until the target Hb limit is reached."
                )
            }
            "maintenance_421" -> {
                val rate = MedicalCalculators.calculateMaintenanceFluid421(val1)
                calculatedValue = rate
                resultText = if (isTurkish) String.format(Locale.US, "%.0f mL/saat", rate) else String.format(Locale.US, "%.0f mL/hour", rate)
                interpretationText = t("Saatlik standart idame hidrasyon hızı.", "Standard hourly maintenance hydration rate.")
            }
            "gcs" -> {
                val gcsVal = val1.toInt() + val2.toInt() + val3.toInt()
                val severity = when {
                    gcsVal >= 13 -> t("Hafif Travma", "Mild Trauma")
                    gcsVal >= 9 -> t("Orta Travma", "Moderate Trauma")
                    else -> t("🚨 Ağır Kafa Travması - Hava yolu koruması (Entübasyon) düşünülmelidir!", "🚨 Severe Head Trauma - Airway protection (Intubation) should be considered!")
                }
                calculatedValue = gcsVal.toDouble()
                resultText = "$gcsVal / 15"
                interpretationText = severity
            }
            "apfel_score" -> {
                val (score, risk) = MedicalCalculators.calculateApfelScore(apfelFemale, apfelNonSmoker, apfelHistoryPONV, apfelPostOpOpioids)
                calculatedValue = score.toDouble()
                resultText = "$score / 4"
                
                val approachTr = when (score) {
                    0 -> "Genelde rutin antiemetik gerekmez."
                    1 -> "Düşük risk; tek ajan düşünülebilir. (Güncel konsensus: 1-2 risk faktöründe genellikle 2 ajan profilaksisi önerilmektedir)."
                    2 -> "Profilaksi önerilir. Farklı sınıflardan 2 ajan kombinasyonu tercih edilmelidir."
                    3 -> "Kombine profilaksi önerilir. En az 2 veya daha fazla farklı sınıftan ajan kombinasyonu ve opioid koruyucu protokoller (TIVA) düşünülmelidir."
                    4 -> "Multimodal / 2-3 ajan profilaksisi önerilir. 3 veya daha fazla farklı sınıftan ajan kombinasyonu, opioid-dışı multimodal analjezi ve TIVA düşünülmelidir."
                    else -> ""
                }
                
                val approachEn = when (score) {
                    0 -> "Routine antiemetic is generally not required."
                    1 -> "Low risk; a single agent may be considered. (Modern consensus: 2-agent prophylaxis is often recommended for 1-2 risk factors)."
                    2 -> "Prophylaxis is recommended. A combination of 2 agents from different classes should be preferred."
                    3 -> "Combined prophylaxis is recommended. A combination of 2 or more agents from different classes and opioid-sparing protocols (TIVA) should be considered."
                    4 -> "Multimodal / 2-3 agent prophylaxis is recommended. A combination of 3 or more agents from different classes, non-opioid multimodal analgesia, and TIVA should be considered."
                    else -> ""
                }
                
                interpretationText = t(
                    """Apfel PONV Skoru: $score
Tahmini PONV Riski: %$risk
Yaklaşım: $approachTr""",
                    """Apfel PONV Score: $score
Estimated PONV Risk: $risk%
Clinical Approach: $approachEn"""
                )
            }
            "aldrete_score" -> {
                val total = aldreteActivity + aldreteRespiration + aldreteCirculation + aldreteConsciousness + aldreteO2Sat
                calculatedValue = total.toDouble()
                resultText = "$total / 10"
                interpretationText = if (total >= 9) {
                    t("Derlenme Skoru Yeterli (>=9): Servise veya taburculuğa uygundur.", "Recovery Score Adequate (>=9): Suitable for ward or discharge.")
                } else {
                    t("🚨 Derlenme Skoru Yetersiz (<9): Yakın PACU takibi, solunum ve hemodinami desteği sürdürülmelidir!", "🚨 Recovery Score Inadequate (<9): Close PACU monitoring, respiratory and hemodynamic support must be maintained!")
                }
            }
            "rcri_score" -> {
                var score = 0
                if (rcriHighRiskSurgery) score++
                if (rcriIschemicHeart) score++
                if (rcriHeartFailure) score++
                if (rcriCerebrovascular) score++
                if (rcriInsulin) score++
                if (rcriCreatinine) score++
                calculatedValue = score.toDouble()
                val (riskClass, riskPercent) = when (score) {
                    0 -> t("Sınıf I", "Class I") to "0.4%"
                    1 -> t("Sınıf II", "Class II") to "0.9%"
                    2 -> t("Sınıf III", "Class III") to "6.6%"
                    else -> t("Sınıf IV", "Class IV") to "11.0%"
                }
                resultText = "$score / 6"
                interpretationText = t(
                    """MACE Riski Sınıfı: $riskClass
30 Günlük Majör Kardiyak Komplikasyon Riski: $riskPercent""",
                    """MACE Risk Class: $riskClass
30-Day Major Cardiac Complication Risk: $riskPercent"""
                )
            }
            "stop_bang_score" -> {
                var score = 0
                if (stopSnoring) score++
                if (stopTiredness) score++
                if (stopApnea) score++
                if (stopPressure) score++
                if (stopBmi) score++
                if (stopAge) score++
                if (stopNeck) score++
                if (stopGender) score++
                calculatedValue = score.toDouble()
                val risk = when {
                    score <= 2 -> t("Düşük OSAS Riski", "Low OSAS Risk")
                    score <= 4 -> t("Orta OSAS Riski", "Moderate OSAS Risk")
                    else -> t("🚨 Yüksek OSAS Riski - Postoperatif solunum depresyonu riskine dikkat edilmelidir!", "🚨 High OSAS Risk - Postoperative respiratory depression risk must be closely monitored!")
                }
                resultText = "$score / 8"
                interpretationText = t(
                    """STOP-Bang Skoru: $score
Değerlendirme: $risk""",
                    """STOP-Bang Score: $score
Assessment: $risk"""
                )
            }
            "la_max_dose" -> {
                val maxMgKg = when (laSelectedAgent.lowercase()) {
                    "lidocaine" -> if (laWithEpinephrine) 7.0 else 4.5
                    "bupivacaine" -> if (laWithEpinephrine) 2.5 else 2.0
                    "ropivacaine" -> 3.0
                    "prilocaine" -> if (laWithEpinephrine) 8.0 else 6.0
                    "levobupivacaine" -> if (laWithEpinephrine) 3.0 else 2.5
                    else -> 3.0
                }
                val maxVol = MedicalCalculators.calculateLocalAnestheticMaxVolume(laWeightKg, maxMgKg, laConcentrationPercent)
                calculatedValue = maxVol
                resultText = String.format(Locale.US, "%.1f mL", maxVol)
                val agentLabel = t(if (laWithEpinephrine) "Epinefrinli" else "Sade", if (laWithEpinephrine) "with Epinephrine" else "Plain")
                val agentName = t(
                    when (laSelectedAgent.lowercase()) {
                        "lidocaine" -> "Lidokain"
                        "bupivacaine" -> "Bupivakain"
                        "ropivacaine" -> "Ropivakain"
                        "prilocaine" -> "Prilokain"
                        "levobupivacaine" -> "Levobupivakain"
                        else -> laSelectedAgent.uppercase()
                    },
                    when (laSelectedAgent.lowercase()) {
                        "lidocaine" -> "Lidocaine"
                        "bupivacaine" -> "Bupivacaine"
                        "ropivacaine" -> "Ropivacaine"
                        "prilocaine" -> "Prilocaine"
                        "levobupivacaine" -> "Levobupivacaine"
                        else -> laSelectedAgent.replaceFirstChar { it.uppercase() }
                    }
                )
                interpretationText = t(
                    """Ajan: $agentName ($agentLabel)
Maksimum Güvenli Doz: ${String.format(Locale.US, "%.1f", laWeightKg * maxMgKg)} mg
Maks Hacim: ${String.format(Locale.US, "%.1f", maxVol)} mL (Limit aşılmamalıdır)""",
                    """Agent: $agentName ($agentLabel)
Maximum Safe Dose: ${String.format(Locale.US, "%.1f", laWeightKg * maxMgKg)} mg
Max Volume: ${String.format(Locale.US, "%.1f", maxVol)} mL (Limit must not be exceeded)"""
                )
            }
            "sugammadex_dose" -> {
                val factor = when (sugammadexTOFLevel) {
                    "moderate" -> 2.0
                    "deep" -> 4.0
                    "immediate" -> 16.0
                    else -> 2.0
                }
                val doseMg = sugammadexWeightKg * factor
                val vials = kotlin.math.ceil(doseMg / 200.0).toInt()
                calculatedValue = doseMg
                resultText = String.format(Locale.US, "%.0f mg", doseMg)
                val tofLabel = t(
                    when (sugammadexTOFLevel) {
                        "moderate" -> "Orta Dereceli Blok (TOF 2-4)"
                        "deep" -> "Derin Blok (Post-Tetanic Count 1-2)"
                        "immediate" -> "Acil Geri Çevirme (İndüksiyondan 3 dk sonra)"
                        else -> sugammadexTOFLevel.uppercase()
                    },
                    sugammadexTOFLevel.uppercase()
                )
                interpretationText = t(
                    """TOF Durumu: $tofLabel
Hacim Dozu: ${String.format(Locale.US, "%.1f", doseMg / 100.0)} mL (100 mg/mL solüsyondan)
Gereken Flakon: $vials flakon Bridion (200 mg / 2 mL)""",
                    """TOF Status: $tofLabel
Volume Dose: ${String.format(Locale.US, "%.1f", doseMg / 100.0)} mL (from 100 mg/mL solution)
Required Vials: $vials vial(s) of Bridion (200 mg / 2 mL)"""
                )
            }
            "pediatric_ett" -> {
                val size = MedicalCalculators.calculatePediatricETTSize(ettAgeYears, ettCuffed)
                val (depth1, depth2) = MedicalCalculators.calculatePediatricETTDepth(ettAgeYears, size)
                calculatedValue = size
                resultText = String.format(Locale.US, "ID %.1f mm", size)
                val ettCuffedLabel = t(if (ettCuffed) "Kaf'lı" else "Kaf'sız", if (ettCuffed) "Cuffed" else "Uncuffed")
                interpretationText = t(
                    """Önerilen ETT Çapı: ID ${String.format(Locale.US, "%.1f", size)} mm ($ettCuffedLabel)
Yerleşim Derinliği (Dudak Hattı):
- (Yaş / 2) + 12 formülüne göre: ${String.format(Locale.US, "%.1f", depth1)} cm
- (Çap * 3) formülüne göre: ${String.format(Locale.US, "%.1f", depth2)} cm""",
                    """Recommended ETT Size: ID ${String.format(Locale.US, "%.1f", size)} mm ($ettCuffedLabel)
Insertion Depth (Lip Line):
- According to (Age / 2) + 12 formula: ${String.format(Locale.US, "%.1f", depth1)} cm
- According to (ID * 3) formula: ${String.format(Locale.US, "%.1f", depth2)} cm"""
                )
            }
            "ibw_tidal_volume" -> {
                val pbw = MedicalCalculators.calculatePBW(tvSex, tvHeightCm)
                val tv = MedicalCalculators.calculateTidalVolume(pbw, tvTargetMlPerKg)
                calculatedValue = tv
                resultText = String.format(Locale.US, "%.0f mL", tv)
                interpretationText = t(
                    """Tahmini İdeal Ağırlık (PBW): ${String.format(Locale.US, "%.1f", toDisplayWeight(pbw))} $weightUnit
Seçilen Koruyucu Ventilasyon Hacmi: ${tvTargetMlPerKg.toInt()} mL/kg
Hedef Tidal Hacim: ${String.format(Locale.US, "%.0f", tv)} mL""",
                    """Estimated Predicted Body Weight (PBW): ${String.format(Locale.US, "%.1f", toDisplayWeight(pbw))} $weightUnit
Selected Protective Ventilation Volume: ${tvTargetMlPerKg.toInt()} mL/kg
Target Tidal Volume: ${String.format(Locale.US, "%.0f", tv)} mL"""
                )
            }
            "creatinine_clearance" -> {
                val genderFactor = if (gfrSex == "female") 0.85 else 1.0
                val crCl = if (gfrCreatinine > 0.0) {
                    ((140.0 - gfrAgeYears) * gfrWeightKg) / (72.0 * gfrCreatinine) * genderFactor
                } else 0.0
                calculatedValue = crCl
                resultText = String.format(Locale.US, "%.1f mL/dk", crCl)
                interpretationText = t(
                    """Kreatinin Klirensi (CrCl): ${String.format(Locale.US, "%.1f", crCl)} mL/dk
Nöromüsküler blokörler ve antibiyotiklerin doz aralıklarını böbrek fonksiyonuna göre ayarlayın.""",
                    """Creatinine Clearance (CrCl): ${String.format(Locale.US, "%.1f", crCl)} mL/min
Adjust dosage intervals of neuromuscular blockers and antibiotics based on renal function."""
                )
            }
            "opioid_equivalence" -> {
                val equivMorphineMg = when (opioidType.lowercase()) {
                    "fentanyl" -> (opioidAmount / 1000.0) * 100.0
                    "remifentanil" -> (opioidAmount / 1000.0) * 100.0
                    "tramadol" -> opioidAmount * 0.1
                    "pethidine" -> opioidAmount * (10.0 / 75.0)
                    else -> opioidAmount
                }
                calculatedValue = equivMorphineMg
                resultText = if (isTurkish) String.format(Locale.US, "%.1f mg IV Morfin", equivMorphineMg) else String.format(Locale.US, "%.1f mg IV Morphine", equivMorphineMg)
                interpretationText = t(
                    """İlaç: ${opioidType.uppercase()} ($opioidAmount)
IV Morfin Eşdeğer Dozu: ${String.format(Locale.US, "%.1f", equivMorphineMg)} mg
PACU veya postoperatif opioid solunum depresyonu riskine karşı dikkatli olun.""",
                    """Drug: ${opioidType.uppercase()} ($opioidAmount)
IV Morphine Equivalent Dose: ${String.format(Locale.US, "%.1f", equivMorphineMg)} mg
Exercise caution regarding PACU or postoperative opioid respiratory depression risk."""
                )
            }

        }
        hasCalculated = true
    }

    // Run initial calculations
    LaunchedEffect(slug) {
        if (slug == "gcs") {
            inputVal1 = "4"
            inputVal2 = "5"
            inputVal3 = "6"
        } else if (slug == "ebv_abl") {
            inputVal1 = "70.0"
            inputVal2 = "14.0"
            inputVal3 = "9.0"
        } else if (slug == "bmi" || slug == "bsa") {
            inputVal1 = "70.0"
            inputVal2 = "170.0"
        } else if (slug == "maintenance_421") {
            inputVal1 = "70.0"
        }
        if (slug !in listOf("asa_ps", "npo_fasting", "difficult_airway", "asra_coagulation")) {
            runCalculation()
        }
    }

    LaunchedEffect(
        slug,
        selectedAsaClass, isEmergencyModifier,
        selectedNpoIntakeType, lastIntakeHour, lastIntakeMinute, plannedSurgeryHour, plannedSurgeryMinute, selectedNpoPatientGroup,
        npoDiabetes, npoGastroparesis, npoGerd, npoObesity, npoOpioids,
        npoGlp1Weekly, npoGlp1Daily, npoGlp1GiEscalation,
        selectedMallampati, mouthOpeningCm, thyromentalDistanceCm, isNeckMobilityLimited, selectedUpperLipBiteClass,
        airwayPrevDifficult, airwayOsa, airwayObese, airwayBeard, airwayEdentulous, airwayCervicalLimit, airwayPregnancy, airwayPathology,
        coagSearchQuery,
        selectedInfusionDrug, infusionWeightKg, infusionSyringeMg, infusionSyringeMl, infusionTargetDose,
        selectedMixtureTab,
        dantroleneWeightKg,
        lipidWeightKg,
        currentLanguage
    ) {
        when (slug) {
            "asa_ps" -> {
                val currentAsaDef = when (selectedAsaClass) {
                    "ASA I" -> t("Normal, sistemik hastalığı olmayan tamamen sağlıklı hasta.", "A normal healthy patient with no systemic disease.")
                    "ASA II" -> t("Hafif sistemik hastalığı olan, fonksiyonel sınırlaması bulunmayan hasta.", "A patient with mild systemic disease, with no functional limitations.")
                    "ASA III" -> t("Ciddi sistemik hastalığı olan, fonksiyonel sınırlamaya yol açan hasta.", "A patient with severe systemic disease that causes functional limitations.")
                    "ASA IV" -> t("Sürekli hayati tehdit oluşturan, ileri derecede ciddi sistemik hastalığı olan hasta.", "A patient with severe systemic disease that is a constant threat to life.")
                    "ASA V" -> t("Ameliyat yapılsa da yapılmasa da 24 saat içinde ölmesi beklenen moribund hasta.", "A moribund patient who is not expected to survive without the operation.")
                    else -> t("Beyin ölümü gerçekleşmiş, organları alınacak donör hasta.", "A declared brain-dead patient whose organs are being removed for donor purposes.")
                }
                resultText = "$selectedAsaClass${if (isEmergencyModifier) " E" else ""}"
                interpretationText = currentAsaDef
            }
            "npo_fasting" -> {
                val hasRiskFlags = npoDiabetes || npoGastroparesis || npoGerd || npoObesity || npoOpioids || selectedNpoPatientGroup == "pregnant"
                val isGlp1HighRisk = npoGlp1Weekly || npoGlp1Daily || npoGlp1GiEscalation
                val actualRiskFlags = hasRiskFlags || isGlp1HighRisk
                val res = MedicalCalculators.calculateNpoSafety(
                    intakeType = selectedNpoIntakeType,
                    lastIntakeHour = lastIntakeHour,
                    lastIntakeMinute = lastIntakeMinute,
                    plannedHour = plannedSurgeryHour,
                    plannedMinute = plannedSurgeryMinute,
                    patientGroup = selectedNpoPatientGroup,
                    hasRiskFlags = actualRiskFlags
                )
                resultText = when {
                    isGlp1HighRisk || res.status == "high_risk_review_required" -> t("🚨 HASSAS DEĞERLENDİRME & RSI GEREKLİ", "🚨 SENSITIVE ASSESSMENT & RSI REQUIRED")
                    res.status == "suitable" -> t("ANESTEZİ İÇİN UYGUNDUR (NPO OK)", "SUITABLE FOR ANESTHESIA (NPO OK)")
                    else -> t("BEKLENMESİ GEREKİYOR (Yetersiz Açlık)", "WAITING REQUIRED (Insufficient Fasting)")
                }
                val standardInfo = if (isTurkish) {
                    "Açlık: ${String.format(Locale.US, "%.1f", res.elapsedHours)} sa / Gereken: ${res.requiredHours} sa"
                } else {
                    "Fasting: ${String.format(Locale.US, "%.1f", res.elapsedHours)} hrs / Required: ${res.requiredHours} hrs"
                }
                val glp1Warning = if (isGlp1HighRisk) {
                    val warningDetails = mutableListOf<String>()
                    if (npoGlp1Weekly) {
                        warningDetails.add(t(
                            "- Haftalık GLP-1 Agonisti (Semaglutide/Tirzepatide) son 1 hafta içinde kullanıldı (katı gıda mide boşalması yarılanma ömrünü %45/36 dk geciktirir, 4. saatte mide retansiyon oranını %7'den %37'ye çıkarır; ASA 2023 uyarınca kesilmelidir).",
                            "- Weekly GLP-1 agonist (Semaglutide/Tirzepatide) was taken within last 1 week (delays solid gastric emptying half-time by ~45%/36 min, increases 4h gastric retention rate from 7% to 37%; should be held for 1 week according to ASA 2023)."
                        ))
                    }
                    if (npoGlp1Daily) {
                        warningDetails.add(t(
                            "- Günlük GLP-1 Agonisti (Liraglutide) son 24 saat içinde kullanıldı (işlem günü kesilmelidir; periprosedürel mide içeriği retansiyon oranını ~6 kat artırır).",
                            "- Daily GLP-1 agonist (Liraglutide) was taken within last 24 hours (should be held on the day of the procedure; increases periprocedural gastric retention by ~6-fold)."
                        ))
                    }
                    if (npoGlp1GiEscalation) {
                        warningDetails.add(t(
                            "- Aktif Gİ Semptomlar veya İlaç Eskalasyon Fazı mevcut (Mide boşalmasında aşırı gecikme; Çoklu Toplum 2024 Kılavuzuna göre yüksek aspirasyon riski).",
                            "- Active GI symptoms or drug escalation phase present (extreme delay in gastric emptying; high aspiration risk under 2024 Multi-Society Guidelines)."
                        ))
                    }
                    val recommendation = t(
                        "\n\n🚨 GLP-1 KLİNİK REHBERİ:\nElektif işlemleri ertelemeyi düşünün. Devam edilecekse ultrason eşliğinde mide hacmini (POCUS) değerlendirin veya 'Dolu Mide' önlemleriyle (RSI ve endotrakeal entübasyon) ilerleyin.",
                        "\n\n🚨 GLP-1 CLINICAL ADVOCATION:\nConsider delaying elective procedures. If proceeding, assess gastric volume using POCUS or manage with full-stomach precautions (Rapid Sequence Induction - RSI and endotracheal intubation)."
                    )
                    "\n\n" + t("⚠️ GLP-1 İlaç/Aspirasyon Riskleri:", "⚠️ GLP-1 Drug/Aspiration Risks:") + "\n" + warningDetails.joinToString("\n") + recommendation
                } else ""
                
                interpretationText = standardInfo + glp1Warning
            }
            "difficult_airway" -> {
                val res = MedicalCalculators.scoreDifficultAirway(
                    mallampati = selectedMallampati,
                    mouthOpeningCm = mouthOpeningCm,
                    thyromentalDistanceCm = thyromentalDistanceCm,
                    neckMobilityLimited = isNeckMobilityLimited,
                    upperLipBiteClass = selectedUpperLipBiteClass,
                    prevDifficultAirway = airwayPrevDifficult,
                    hasOsa = airwayOsa,
                    isObese = airwayObese,
                    hasBeard = airwayBeard,
                    isEdentulous = airwayEdentulous,
                    hasCervicalLimit = airwayCervicalLimit,
                    isPregnant = airwayPregnancy,
                    hasAirwayPathology = airwayPathology
                )
                val riskStr = when (res.riskLevel) {
                    "high" -> t("🚨 YÜKSEK ZOR HAVA YOLU RİSKİ", "🚨 HIGH DIFFICULT AIRWAY RISK")
                    "caution" -> t("⚠️ ORTA / DİKKAT SEVİYESİ RİSK", "⚠️ MODERATE / CAUTION RISK LEVEL")
                    else -> t("DÜŞÜK HAVA YOLU RİSKİ", "LOW DIFFICULT AIRWAY RISK")
                }
                resultText = "${t("Skor", "Score")}: ${res.score} ($riskStr)"
                val domainsTrans = res.affectedDomains.map { TranslationHelper.translate(it, currentLanguage) }
                interpretationText = if (isTurkish) {
                    "Zorluk Beklenen Alanlar: ${domainsTrans.joinToString(", ")}"
                } else {
                    "Expected Difficulty Areas: ${domainsTrans.joinToString(", ")}"
                }
            }
            "asra_coagulation" -> {
                resultText = t("Antikoagülan Kılavuzu", "Anticoagulant Guide")
                interpretationText = t(
                    "Bölgesel anestezi öncesi antikoagülan ilaç kesme ve kateter süreleri rehberi.",
                    "Guide for anticoagulant drug discontinuation and catheter timing before regional anesthesia."
                )
            }
            "infusion_vasopressors" -> {
                val rate = MedicalCalculators.calculateInfusionRate(
                    drug = selectedInfusionDrug,
                    weightKg = infusionWeightKg,
                    syringeMg = infusionSyringeMg,
                    syringeMl = infusionSyringeMl,
                    targetDose = infusionTargetDose
                )
                resultText = if (isTurkish) String.format(Locale.US, "%.1f mL/saat", rate) else String.format(Locale.US, "%.1f mL/hour", rate)
                val concText = String.format(Locale.US, "%.1f mcg/mL", (infusionSyringeMg * 1000.0) / infusionSyringeMl)
                interpretationText = t(
                    "Enjektör Çözelti Derişimi: $concText. Perfüzyör hızını bu değere ayarlayın.",
                    "Syringe Solution Concentration: $concText. Adjust the perfusor rate to this value."
                )
            }
            "regional_mixtures" -> {
                val mixTitle = when (selectedMixtureTab) {
                    "labor_epidural" -> t("Epidural Ağrısız Doğum (Labor Analgesia) Karışımı", "Labor Epidural Analgesia Mixture")
                    "postop_epidural" -> t("Postoperatif Epidural İnfüzyon Karışımı", "Postoperative Epidural Infusion Mixture")
                    "spinal_caesarean" -> t("Spinal Sezaryen (Obstetrik) Dozajlama", "Spinal Cesarean (Obstetric) Dosing")
                    else -> t("Spinal Klasik Cerrahi Dozajlama", "Spinal Classic Surgical Dosing")
                }
                resultText = mixTitle
                interpretationText = t("Klinik bölgesel anestezi hazırlama ve idame protokolü.", "Clinical regional anesthesia preparation and maintenance protocol.")
            }
            "dantrolene_rescue" -> {
                val res = MedicalCalculators.calculateDantroleneRescue(dantroleneWeightKg)
                resultText = String.format(Locale.US, "%.0f mg bolus", res.bolusMg)
                interpretationText = if (isTurkish) {
                    "Flakon: ${res.totalVials} flakon, Steril Su: ${String.format(Locale.US, "%.0f", res.sterileWaterMl)} mL"
                } else {
                    "Vials: ${res.totalVials} vial(s), Sterile Water: ${String.format(Locale.US, "%.0f", res.sterileWaterMl)} mL"
                }
            }
            "lipid_rescue" -> {
                val res = MedicalCalculators.calculateLipidRescue(lipidWeightKg)
                resultText = String.format(Locale.US, "%.1f mL bolus", res.first)
                val rateHr = res.second * 60.0
                interpretationText = if (isTurkish) {
                    "İnfüzyon Hızı: ${String.format(Locale.US, "%.1f", rateHr)} mL/saat"
                } else {
                    "Infusion Rate: ${String.format(Locale.US, "%.1f", rateHr)} mL/hour"
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(t(calculator.titleTr, calculator.titleEn), style = MaterialTheme.typography.titleLarge, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = t("Geri", "Back"), tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WarmSand)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(
                text = if (isTurkish) calculator.descriptionTr else (calculator.descriptionEn ?: TranslationHelper.translate(calculator.descriptionTr, currentLanguage)),
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondaryLight,
                modifier = Modifier.padding(bottom = 20.dp)
            )


            // --------------------------------------------------------------------------------
            // 1. ASA PHYSICAL STATUS CLASSIFICATION SPECIAL VIEW
            // --------------------------------------------------------------------------------
            if (calculator.slug == "asa_ps") {
                Text(
                    text = t("ASA Sınıfını Seçin", "Select ASA Class"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Grid layout for ASA I - VI
                val asaClasses = listOf(
                    "ASA I" to t("Normal, sağlıklı", "Normal, healthy"),
                    "ASA II" to t("Hafif sistemik hastalık", "Mild systemic disease"),
                    "ASA III" to t("Ciddi sistemik hastalık", "Severe systemic disease"),
                    "ASA IV" to t("Hayatı tehdit eden hastalık", "Life-threatening disease"),
                    "ASA V" to t("24 saat yaşam beklentisi zor", "Moribund, survival < 24 hrs"),
                    "ASA VI" to t("Beyin ölümü (Donör)", "Brain-dead (Donor)")
                )

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    asaClasses.chunked(2).forEach { rowItems ->
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            rowItems.forEach { (asaCode, shortDef) ->
                                val isSelected = selectedAsaClass == asaCode
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isSelected) DeepNavy else Linen
                                    ),
                                    border = BorderStroke(1.dp, if (isSelected) SoftGold else BorderSand),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(76.dp)
                                        .clickable { selectedAsaClass = asaCode }
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize().padding(8.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = asaCode,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = if (isSelected) Color.White else DeepNavy
                                        )
                                        Text(
                                            text = shortDef,
                                            fontSize = 10.sp,
                                            color = if (isSelected) TextSecondaryDark else TextSecondaryLight,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                      // Emergency Modifier Switch
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(t("Acil Cerrahi Modifiyeri (E)", "Emergency Surgery Modifier (E)"), fontWeight = FontWeight.Bold, color = DeepNavy)
                            Text(t("Ameliyatın acil şartlarda planlandığını gösterir.", "Indicates that the surgery is planned under emergency conditions."), fontSize = 11.sp, color = TextSecondaryLight)
                        }
                        Switch(
                            checked = isEmergencyModifier,
                            onCheckedChange = { isEmergencyModifier = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = ClinicalTeal,
                                checkedTrackColor = ClinicalTeal.copy(alpha = 0.4f)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // ASA PS Custom Diagnostic Result Card
                val asaClassTitle = "$selectedAsaClass${if (isEmergencyModifier) " E" else ""}"
                val currentAsaDef = when (selectedAsaClass) {
                    "ASA I" -> t("Normal, sistemik hastalığı olmayan tamamen sağlıklı hasta.", "A normal healthy patient with no systemic disease.")
                    "ASA II" -> t("Hafif sistemik hastalığı olan, fonksiyonel sınırlaması bulunmayan hasta.", "A patient with mild systemic disease, with no functional limitations.")
                    "ASA III" -> t("Ciddi sistemik hastalığı olan, fonksiyonel sınırlamaya yol açan hasta.", "A patient with severe systemic disease that causes functional limitations.")
                    "ASA IV" -> t("Sürekli hayati tehdit oluşturan, ileri derecede ciddi sistemik hastalığı olan hasta.", "A patient with severe systemic disease that is a constant threat to life.")
                    "ASA V" -> t("Ameliyat yapılsa da yapılmasa da 24 saat içinde ölmesi beklenen moribund hasta.", "A moribund patient who is not expected to survive without the operation.")
                    else -> t("Beyin ölümü gerçekleşmiş, organları alınacak donör hasta.", "A declared brain-dead patient whose organs are being removed for donor purposes.")
                }

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isEmergencyModifier || selectedAsaClass in listOf("ASA IV", "ASA V")) Color(0xFFFDF2F2) else Color(0xFFE8F5E9)
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (isEmergencyModifier || selectedAsaClass in listOf("ASA IV", "ASA V")) CriticalRed else SafeGreen
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (isEmergencyModifier || selectedAsaClass in listOf("ASA IV", "ASA V")) Icons.Default.Warning else Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = if (isEmergencyModifier || selectedAsaClass in listOf("ASA IV", "ASA V")) CriticalRed else SafeGreen
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = t("GÜNCEL SINIFLANDIRMA DEĞERİ", "CURRENT CLASSIFICATION VALUE"),
                                style = MaterialTheme.typography.labelLarge,
                                color = if (isEmergencyModifier || selectedAsaClass in listOf("ASA IV", "ASA V")) CriticalRed else SafeGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = asaClassTitle,
                            style = MaterialTheme.typography.displayLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = currentAsaDef,
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextPrimaryLight,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (isEmergencyModifier) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = t("⚠️ ACİL CERRAHİ UYARISI: Acil cerrahi anestezi riskini bağımsız olarak katlar. Dikkatli hemodinamik takip önerilir.", "⚠️ EMERGENCY SURGERY WARNING: Emergency conditions independently increase anesthesia risk. Close hemodynamic monitoring is recommended."),
                                color = CriticalRed,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Detailed clinical cohort examples
                Text(
                    text = t("Klinik Örnek Vakalar", "Clinical Cohort Examples"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                val (adultEx, pedEx, obEx) = when (selectedAsaClass) {
                    "ASA I" -> Triple(
                        t("Sigara içmeyen, alkol kullanmayan sağlıklı erişkin.", "Healthy, non-smoking, no or minimal alcohol use."),
                        t("Gelişimi ve büyüme eğrileri normal, sağlıklı çocuk (akut veya kronik hastalığı yok).", "Healthy (no acute or chronic disease), normal BMI percentile for age."),
                        t("Uygulanamaz (Gebelik fizyolojik değişiklikler nedeniyle en az ASA II olarak sınıflandırılır)", "Not applicable (Pregnancy is classified as at least ASA PS II due to physiological changes)")
                    )
                    "ASA II" -> Triple(
                        t("Hafif sistemik hastalık (fonksiyonel kısıtlama yok, örn. aktif sigara içicisi, sosyal alkol tüketicisi, obezite (30<VKİ<40), iyi kontrollü DM/HT, hafif akciğer hastalığı).", "Mild diseases only without substantive functional limitations (e.g. current smoker, social alcohol drinker, pregnancy, obesity (30<BMI<40), well-controlled DM or HTN, mild lung disease)."),
                        t("Asemptomatik siyanotik olmayan konjenital kalp hastalığı, kontrol altında aritmi/astım/epilepsi, anormal VKİ persentili, hafif/orta OSA.", "Asymptomatic non cyanotic congenital cardiac disease, well controlled dysrhythmias, asthma without exacerbation, well controlled epilepsy, abnormal BMI percentile for age, mild/moderate OSA."),
                        t("Normal gebelik, iyi kontrollü gestasyonel HTN, diyetle kontrol edilen gestasyonel DM.", "Normal pregnancy, well controlled gestational HTN, diet-controlled gestational DM.")
                    )
                    "ASA III" -> Triple(
                        t("Belirgin fonksiyonel kısıtlılık yapan ciddi sistemik hastalık (örn. morbid obezite (VKİ >= 40), pacemaker, diyaliz alan SDBY, >3 ay MI/İnme öyküsü, kötü kontrollü DM/HT).", "Substantive functional limitations; one or more moderate to severe diseases (e.g. COPD, morbid obesity (BMI >=40), functional implanted pacemaker, ESRD undergoing scheduled dialysis, history (>3 months) of MI/CVA, poorly controlled DM or HTN)."),
                        t("Düzeltilmemiş stabil konjenital kalp anomalisi, alevlenmiş astım, kontrolsüz epilepsi, morbid obezite, prematüre bebek (postkonseptüel <60 hafta).", "Uncorrected stable congenital cardiac abnormality, asthma with exacerbation, poorly controlled epilepsy, morbid obesity, premature infant PCA <60 weeks."),
                        t("Ağır özellikli olan veya olmayan preeklampsi, kötü kontrollü gestasyonel diyabet, antikoagülasyon gerektiren trombofili.", "Preeclampsia with or without severe features, gestational diabetes poorly controlled or with complications or high insulin requirements, a thrombophilic disease requiring anticoagulation.")
                    )
                    "ASA IV" -> Triple(
                        t("Hayatı sürekli tehdit eden ciddi sistemik hastalık (örn. <3 ay MI/İnme/SVO, kararsız angina, ciddi kapak disfonksiyonu, sepsis, DIC, ARDS, diyaliz almayan SDBY).", "A patient with severe systemic disease that is a constant threat to life (e.g. recent (<3 months) MI, CVA, TIA, ongoing cardiac ischemia or severe valve dysfunction, severe reduction of EF, shock, sepsis, DIC, ARDS)."),
                        t("Semptomatik konjenital kalp anomalisi, konjestif kalp yetmezliği, prematüre sekelleri, mekanik ventilatör bağımlılığı, ağır travma/solunum sıkıntısı.", "Symptomatic congenital cardiac abnormality, congestive heart failure, active sequelae of prematurity, shock, sepsis, automatic implantable cardioverter-defibrillator, ventilator dependence, severe trauma."),
                        t("HELLP sendromu veya diğer komplikasyonlarla seyreden ağır preeklampsi, peripartum kardiyomiyopati, dekompanse kalp hastalığı.", "Preeclampsia with severe features complicated by HELLP or other adverse event, peripartum cardiomyopathy, uncorrected/decompensated heart disease.")
                    )
                    "ASA V" -> Triple(
                        t("Cerrahi olmadan yaşaması beklenmeyen moribund hasta (örn. rüptüre aort anevrizması, masif travma, kitle etkili intrakranial kanama, çoklu organ disfonksiyonu).", "A moribund patient who is not expected to survive without the operation (e.g. ruptured abdominal or thoracic aneurysm, massive trauma, intracranial bleed with mass effect, ischemic bowel, multiple organ/system dysfunction)."),
                        t("Masif travma, kitle etkili kafa içi kanama, ECMO gereksinimi, solunum arresti, septik şok, dekompanse kalp yetmezliği.", "Massive trauma, intracranial hemorrhage with mass effect, patient requiring ECMO, respiratory failure or arrest, decompensated congestive heart failure."),
                        t("Uterus rüptürü, amniyotik sıvı embolisi.", "Uterine rupture, amniotic fluid embolism.")
                    )
                    else -> Triple(
                        t("Organ transplantasyonu donörü (Beyin ölümü gerçekleşmiş).", "Organ transplantation donor (Declared brain-dead)."),
                        t("Organ transplantasyonu donörü pediatrik vaka.", "Organ transplantation donor pediatric case."),
                        t("Gebelik ilişkili beyin ölümü vakaları (Donör).", "Pregnancy-related brain death cases (Donor).")
                    )
                }

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        CohortItem(label = t("Erişkin (Adult) Örneği", "Adult Example"), text = adultEx)
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        CohortItem(label = t("Pediatrik (Pediatric) Örneği", "Pediatric Example"), text = pedEx)
                        if (selectedAsaClass != "ASA VI") {
                            Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                            CohortItem(label = t("Obstetrik (Pregnant) Örneği", "Obstetric (Pregnant) Example"), text = obEx)
                        }
                    }
                }

            // --------------------------------------------------------------------------------
            // 2. NPO FASTING TIMER / COUNTDOWN SPECIAL VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "npo_fasting") {
                Text(
                    text = t("Açlık ve Alım Parametreleri", "Fasting & Intake Parameters"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Last Intake Type Selector
                        Text(t("Son Alınan Gıda / Sıvı Tipi", "Type of Last Intake"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Spacer(modifier = Modifier.height(6.dp))
                        
                        val intakeTypes = listOf(
                            "clear_liquid" to t("Berrak Sıvı (2 sa)", "Clear Liquid (2 hrs)"),
                            "breast_milk" to t("Anne Sütü (4 sa)", "Breast Milk (4 hrs)"),
                            "infant_formula" to t("Formül (6 sa)", "Infant Formula (6 hrs)"),
                            "nonhuman_milk" to t("Hayvansal Süt (6 sa)", "Non-human Milk (6 hrs)"),
                            "light_meal" to t("Hafif Öğün (6 sa)", "Light Meal (6 hrs)"),
                            "fatty_meal" to t("Ağır/Yağlı Öğün (8 sa)", "Heavy/Fatty Meal (8 hrs)")
                        )

                        // 3x2 grid of intake types
                        intakeTypes.chunked(2).forEach { row ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                row.forEach { (typeKey, typeLabel) ->
                                    val isSel = selectedNpoIntakeType == typeKey
                                    Button(
                                        onClick = { selectedNpoIntakeType = typeKey },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (isSel) ClinicalTeal else BorderSand.copy(alpha = 0.3f),
                                            contentColor = if (isSel) Color.White else TextPrimaryLight
                                        ),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.weight(1f).height(40.dp)
                                    ) {
                                        Text(typeLabel, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Time pickers via sliders for smooth, error-free sandbox input
                        Text(t("Son Gıda Alım Saati", "Last Intake Time"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Column(modifier = Modifier.weight(1f)) {
                                FormSlider(label = t("Saat", "Hour"), value = lastIntakeHour.toDouble(), range = 0f..23f, unit = "") {
                                    lastIntakeHour = it.toInt()
                                }
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                FormSlider(label = t("Dakika", "Minute"), value = lastIntakeMinute.toDouble(), range = 0f..55f, unit = "") {
                                    lastIntakeMinute = (it / 5).toInt() * 5
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(t("Planlanan Cerrahi Saati", "Planned Surgery Time"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Column(modifier = Modifier.weight(1f)) {
                                FormSlider(label = t("Saat", "Hour"), value = plannedSurgeryHour.toDouble(), range = 0f..23f, unit = "") {
                                    plannedSurgeryHour = it.toInt()
                                }
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                FormSlider(label = t("Dakika", "Minute"), value = plannedSurgeryMinute.toDouble(), range = 0f..55f, unit = "") {
                                    plannedSurgeryMinute = (it / 5).toInt() * 5
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(t("Hasta Tipi", "Patient Type"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf("adult" to t("Erişkin", "Adult"), "pediatric" to t("Pediatrik", "Pediatric"), "pregnant" to t("Gebe", "Pregnant"), "emergency" to t("Acil", "Emergency")).forEach { (pk, plabel) ->
                                val isSel = selectedNpoPatientGroup == pk
                                Button(
                                    onClick = { selectedNpoPatientGroup = pk },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSel) DeepNavy else BorderSand.copy(alpha = 0.3f),
                                        contentColor = if (isSel) Color.White else TextPrimaryLight
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f).height(36.dp),
                                    contentPadding = PaddingValues(horizontal = 2.dp)
                                ) {
                                    Text(plabel, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Risk factors grid
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(t("Aspirasyon Risk Faktörleri", "Aspiration Risk Factors"), fontWeight = FontWeight.Bold, color = DeepNavy)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoDiabetes, onCheckedChange = { npoDiabetes = it })
                            Text(t("Diyabet (Gastroparezis riski)", "Diabetes (Gastroparesis risk)"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoGastroparesis, onCheckedChange = { npoGastroparesis = it })
                            Text(t("Aktif Gastroparezis / Gastrointestinal Obstrüksiyon", "Active Gastroparesis / Gastrointestinal Obstruction"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoGerd, onCheckedChange = { npoGerd = it })
                            Text(t("Ciddi GERD / Reflü (Aktif semptomatik)", "Severe GERD / Reflux (Active symptomatic)"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoObesity, onCheckedChange = { npoObesity = it })
                            Text(t("Morbid Obezite (VKİ >= 40)", "Morbid Obesity (BMI >= 40)"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoOpioids, onCheckedChange = { npoOpioids = it })
                            Text(t("Son 6 Saatte Opioid / Narkotik Kullanımı", "Opioid / Narcotic Use in Last 6 Hours"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoGlp1Weekly, onCheckedChange = { npoGlp1Weekly = it })
                            Text(t("Haftalık GLP-1 Agonisti (Semaglutide/Tirzepatide) - Son 1 hafta içinde alım", "Weekly GLP-1 Agonist (Semaglutide/Tirzepatide) - Taken within last 1 week"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoGlp1Daily, onCheckedChange = { npoGlp1Daily = it })
                            Text(t("Günlük GLP-1 Agonisti (Liraglutide) - Son 24 saat içinde alım", "Daily GLP-1 Agonist (Liraglutide) - Taken within last 24 hours"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = npoGlp1GiEscalation, onCheckedChange = { npoGlp1GiEscalation = it })
                            Text(t("Aktif Gİ Semptomlar (bulantı/reflü/şişkinlik) veya Doz Artış Fazı", "Active GI Symptoms (nausea/reflux/bloating) or Dose Escalation Phase"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Perform NPO Safety Math
                val isGlp1HighRisk = npoGlp1Weekly || npoGlp1Daily || npoGlp1GiEscalation
                val hasRiskFlags = npoDiabetes || npoGastroparesis || npoGerd || npoObesity || npoOpioids || selectedNpoPatientGroup == "pregnant" || isGlp1HighRisk
                val npoResult = MedicalCalculators.calculateNpoSafety(
                    intakeType = selectedNpoIntakeType,
                    lastIntakeHour = lastIntakeHour,
                    lastIntakeMinute = lastIntakeMinute,
                    plannedHour = plannedSurgeryHour,
                    plannedMinute = plannedSurgeryMinute,
                    patientGroup = selectedNpoPatientGroup,
                    hasRiskFlags = hasRiskFlags
                )

                // Result display
                val cardColor: Color
                val borderColor: Color
                val titleColor: Color
                val mainText: String
                val subText: String

                when (npoResult.status) {
                    "suitable" -> {
                        cardColor = Color(0xFFE8F5E9)
                        borderColor = SafeGreen
                        titleColor = SafeGreen
                        mainText = t("ANESTEZİ İÇİN UYGUNDUR (NPO OK)", "SUITABLE FOR ANESTHESIA (NPO OK)")
                        subText = if (isTurkish) {
                            "Hastanın açlık süresi (${String.format(Locale.US, "%.1f", npoResult.elapsedHours)} saat) elektif kılavuz süresini (${npoResult.requiredHours} saat) güvenle aşmaktadır."
                        } else {
                            "The patient's fasting duration (${String.format(Locale.US, "%.1f", npoResult.elapsedHours)} hours) safely exceeds the elective guideline interval (${npoResult.requiredHours} hours)."
                        }
                    }
                    "wait" -> {
                        cardColor = Color(0xFFFFFDE7)
                        borderColor = AlertAmber
                        titleColor = AlertAmber
                        mainText = t("BEKLENMESİ GEREKİYOR (Yetersiz Açlık)", "WAITING REQUIRED (Insufficient Fasting)")
                        subText = if (isTurkish) {
                            "Hasta şu ana kadar ${String.format(Locale.US, "%.1f", npoResult.elapsedHours)} saat aç kaldı. Elektif anestezi için en az ${String.format(Locale.US, "%.1f", npoResult.remainingHours)} saat daha beklenmesi gerekmektedir."
                        } else {
                            "The patient has been fasting for ${String.format(Locale.US, "%.1f", npoResult.elapsedHours)} hours so far. A waiting period of at least ${String.format(Locale.US, "%.1f", npoResult.remainingHours)} more hours is required for elective anesthesia."
                        }
                    }
                    else -> {
                        cardColor = Color(0xFFFDF2F2)
                        borderColor = CriticalRed
                        titleColor = CriticalRed
                        mainText = t("🚨 HASSAS DEĞERLENDİRME & RSI GEREKLİ", "🚨 SENSITIVE ASSESSMENT & RSI REQUIRED")
                        subText = if (isTurkish) {
                            "Acil durum, yüksek aspirasyon riski veya gastrointestinal boşalmayı geciktiren risk faktörü saptanmıştır. Salt açlık sürelerine güvenilmemelidir. Hızlı Ardışık İndüksiyon (RSI) ve aspirasyon profilaksisi düşünülmelidir."
                        } else {
                            "An emergency, high aspiration risk, or risk factors delaying gastric emptying have been detected. Do not rely solely on fasting intervals. Rapid Sequence Induction (RSI) and aspiration prophylaxis should be considered."
                        }
                    }
                }

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    border = BorderStroke(1.dp, borderColor),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(mainText, fontWeight = FontWeight.Bold, color = titleColor, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTurkish) "${String.format(Locale.US, "%.1f", npoResult.elapsedHours)} Saat / ${npoResult.requiredHours} Saat" else "${String.format(Locale.US, "%.1f", npoResult.elapsedHours)} Hours / ${npoResult.requiredHours} Hours",
                            style = MaterialTheme.typography.displayLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(subText, color = TextPrimaryLight, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

            // --------------------------------------------------------------------------------
            // 3. DIFFICULT AIRWAY SCREENING CHECKLIST SPECIAL VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "difficult_airway") {
                Text(
                    text = t("Havayolu Taraması", "Airway Screening"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Mallampati selector
                        Text(t("Mallampati Sınıfı", "Mallampati Class"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            (1..4).forEach { cls ->
                                val isSel = selectedMallampati == cls
                                Button(
                                    onClick = { selectedMallampati = cls },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSel) ClinicalTeal else BorderSand.copy(alpha = 0.3f),
                                        contentColor = if (isSel) Color.White else TextPrimaryLight
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(t("Sınıf $cls", "Class $cls"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Sliders for mouth opening and thyromental distance
                        FormSlider(label = t("Ağız Açıklığı (İnterinsizör)", "Mouth Opening (Interincisor)"), value = toDisplayDist(mouthOpeningCm), range = toDisplayDist(1.0).toFloat()..toDisplayDist(6.0).toFloat(), unit = distUnit) {
                            mouthOpeningCm = toInternalDist(it.toDouble())
                        }
                        
                        Spacer(modifier = Modifier.height(10.dp))

                        FormSlider(label = t("Tiromental Mesafe (TMD)", "Thyromental Distance (TMD)"), value = toDisplayDist(thyromentalDistanceCm), range = toDisplayDist(3.0).toFloat()..toDisplayDist(12.0).toFloat(), unit = distUnit) {
                            thyromentalDistanceCm = toInternalDist(it.toDouble())
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Segmented upper lip bite class
                        Text(t("Üst Dudak Isırma Testi (ULBT)", "Upper Lip Bite Test (ULBT)"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            (1..3).forEach { cl ->
                                val isSel = selectedUpperLipBiteClass == cl
                                Button(
                                    onClick = { selectedUpperLipBiteClass = cl },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSel) ClinicalTeal else BorderSand.copy(alpha = 0.3f),
                                        contentColor = if (isSel) Color.White else TextPrimaryLight
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(t("Sınıf $cl", "Class $cl"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Patient checklists secondary factors
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(t("Hasta Klinik Risk Faktörleri", "Patient Clinical Risk Factors"), fontWeight = FontWeight.Bold, color = DeepNavy)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = isNeckMobilityLimited, onCheckedChange = { isNeckMobilityLimited = it })
                            Text(t("Sınırlı Boyun Hareketliliği (Neck Mobility Limited)", "Limited Neck Mobility (Neck Mobility Limited)"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = airwayPrevDifficult, onCheckedChange = { airwayPrevDifficult = it })
                            Text(t("Geçmişte Zor Entübasyon / Havayolu Öyküsü", "History of Difficult Intubation / Airway"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = airwayOsa, onCheckedChange = { airwayOsa = it })
                            Text(t("Ciddi OSA / STOP-Bang Skoru >= 5", "Severe OSA / STOP-Bang Score >= 5"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = airwayObese, onCheckedChange = { airwayObese = it })
                            Text(t("Morbid Obezite (VKİ >= 40)", "Morbid Obesity (BMI >= 40)"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = airwayBeard, onCheckedChange = { airwayBeard = it })
                            Text(t("Sakal (Maske ventilasyonu engelleyici)", "Beard (Impedes mask ventilation)"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = airwayEdentulous, onCheckedChange = { airwayEdentulous = it })
                            Text(t("Dişsiz Hasta (Edentulous)", "Edentulous (Toothless) Patient"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = airwayCervicalLimit, onCheckedChange = { airwayCervicalLimit = it })
                            Text(t("Servikal Omurga Kısıtlılığı / Boyun Fıtığı / Fiksasyon", "Cervical Spine Limitation / Disc Hernia / Fixation"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = airwayPathology, onCheckedChange = { airwayPathology = it })
                            Text(t("Hava Yolu Kitlesi / Tümör / Yüz Travması", "Airway Mass / Tumor / Facial Trauma"), fontSize = 12.sp, color = TextPrimaryLight)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Score the difficult airway
                val airwayResult = MedicalCalculators.scoreDifficultAirway(
                    mallampati = selectedMallampati,
                    mouthOpeningCm = mouthOpeningCm,
                    thyromentalDistanceCm = thyromentalDistanceCm,
                    neckMobilityLimited = isNeckMobilityLimited,
                    upperLipBiteClass = selectedUpperLipBiteClass,
                    prevDifficultAirway = airwayPrevDifficult,
                    hasOsa = airwayOsa,
                    isObese = airwayObese,
                    hasBeard = airwayBeard,
                    isEdentulous = airwayEdentulous,
                    hasCervicalLimit = airwayCervicalLimit,
                    isPregnant = airwayPregnancy,
                    hasAirwayPathology = airwayPathology
                )

                val (statColor, borderC, badgeText) = when (airwayResult.riskLevel) {
                    "high" -> Triple(Color(0xFFFDF2F2), CriticalRed, t("🚨 YÜKSEK ZOR HAVA YOLU RİSKİ", "🚨 HIGH DIFFICULT AIRWAY RISK"))
                    "caution" -> Triple(Color(0xFFFFFDE7), AlertAmber, t("⚠️ ORTA / DİKKAT SEVİYESİ RİSK", "⚠️ MODERATE / CAUTION RISK LEVEL"))
                    else -> Triple(Color(0xFFE8F5E9), SafeGreen, t("DÜŞÜK HAVA YOLU RİSKİ", "LOW DIFFICULT AIRWAY RISK"))
                }

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = statColor),
                    border = BorderStroke(1.dp, borderC),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(badgeText, fontWeight = FontWeight.Bold, color = borderC, fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(t("Risk Derecesi Skoru: ${airwayResult.score} puan", "Risk Severity Score: ${airwayResult.score} points"), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = DeepNavy)
                        
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(t("Zorluk Beklenen Alanlar (Risk Domains):", "Expected Difficulty Areas (Risk Domains):"), fontWeight = FontWeight.Bold, fontSize = 12.sp, color = DeepNavy)
                        airwayResult.affectedDomains.forEach { domain ->
                            Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Dangerous, contentDescription = null, tint = borderC, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(TranslationHelper.translate(domain, currentLanguage), fontSize = 12.sp, color = TextPrimaryLight)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(t("Önerilen Havayolu Hazırlığı:", "Recommended Airway Preparation:"), fontWeight = FontWeight.Bold, fontSize = 12.sp, color = DeepNavy)
                        airwayResult.recommendations.forEach { rec ->
                            Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SafeGreen, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(TranslationHelper.translate(rec, currentLanguage), fontSize = 12.sp, color = TextPrimaryLight)
                            }
                        }
                    }
                }

            // --------------------------------------------------------------------------------
            // 4. ASRA COAGULATION GUIDE SPECIAL TABLE VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "asra_coagulation") {
                Text(
                    text = t("İlaç Arama ve Filtreleme", "Drug Search & Filtering"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                OutlinedTextField(
                    value = coagSearchQuery,
                    onValueChange = { coagSearchQuery = it },
                    placeholder = { Text(t("Antikoagülan ilaç arayın (örn. LMWH, Aspirin, Apixaban)...", "Search anticoagulant drugs (e.g. LMWH, Aspirin, Apixaban)..."), color = TextSecondaryLight) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondaryLight) },
                    trailingIcon = {
                        if (coagSearchQuery.isNotEmpty()) {
                            IconButton(onClick = { coagSearchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null, tint = TextSecondaryLight)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Linen,
                        unfocusedContainerColor = Linen,
                        focusedBorderColor = SoftGold,
                        unfocusedBorderColor = BorderSand
                    )
                )

                 val asraDataset = listOf(
                    CoagDrugInfo(
                        t("Aspirin (Düşük Doz)", "Aspirin (Low Dose)"),
                        t("0 Gün (Kesmeye gerek yok)", "0 Days (No need to discontinue)"),
                        t("Güvenle takılabilir/çekilebilir", "Can be safely placed/removed"),
                        t("Hemen başlanabilir", "Can be started immediately"),
                        t("Tek başına profilaktik aspirin rejyonel için engel oluşturmaz.", "Low-dose prophylactic aspirin alone is not a contraindication for regional anesthesia.")
                    ),
                    CoagDrugInfo(
                        t("Klopidogrel (Plavix)", "Clopidogrel (Plavix)"),
                        t("5 - 7 Gün", "5 - 7 Days"),
                        t("Kateter takılıyken kullanılmamalı", "Should not be used with catheter in place"),
                        t("Kateter çekildikten 6 saat sonra", "6 hours after catheter removal"),
                        t("Kardiyak stent varlığında kardiyoloji onayı şarttır.", "Cardiology clearance is mandatory in the presence of cardiac stents.")
                    ),
                    CoagDrugInfo(
                        t("Prasugrel (Effient)", "Prasugrel (Effient)"),
                        t("7 - 10 Gün", "7 - 10 Days"),
                        t("Kateter takılıyken kullanılmamalı", "Should not be used with catheter in place"),
                        t("Kateter çekildikten 6 saat sonra", "6 hours after catheter removal"),
                        t("Ciddi kanama riski nedeniyle sıkı takip gerekir.", "Close monitoring is required due to severe bleeding risk.")
                    ),
                    CoagDrugInfo(
                        t("Ticagrelor (Brilinta)", "Ticagrelor (Brilinta)"),
                        t("5 Gün", "5 Days"),
                        t("Kateter takılıyken kullanılmamalı", "Should not be used with catheter in place"),
                        t("Kateter çekildikten 24 saat sonra", "24 hours after catheter removal"),
                        t("Geri dönüşümlü platelet ADP reseptör antagonisti.", "Reversible platelet ADP receptor antagonist.")
                    ),
                    CoagDrugInfo(
                        t("LMWH Profilaktik (Enoxaparin)", "LMWH Prophylactic (Enoxaparin)"),
                        t("12 Saat", "12 Hours"),
                        t("Çekilmeden en az 12 saat önce durmalı", "Must be stopped at least 12 hours prior to removal"),
                        t("Çekildikten 4 saat sonra", "4 hours after removal"),
                        t("Böbrek klirensi normal ise geçerlidir.", "Valid if renal clearance is normal.")
                    ),
                    CoagDrugInfo(
                        t("LMWH Terapötik (Enoxaparin)", "LMWH Therapeutic (Enoxaparin)"),
                        t("24 Saat", "24 Hours"),
                        t("Kateter takılıyken asla kullanılmamalı", "Never use with catheter in place"),
                        t("Çekildikten 4 saat sonra", "4 hours after removal"),
                        t("Terapötik dozlarda spinal hematom riski yüksektir.", "High risk of spinal hematoma at therapeutic doses.")
                    ),
                    CoagDrugInfo(
                        t("Warfarin (Coumadin)", "Warfarin (Coumadin)"),
                        t("5 Gün (INR < 1.5 olmalı)", "5 Days (INR must be < 1.5)"),
                        t("INR normalleşmeden çekilmemeli", "Should not be removed until INR normalizes"),
                        t("Çekildikten 24 saat sonra", "24 hours after removal"),
                        t("Spinal anesteziden hemen önce INR kontrol edilmelidir.", "INR must be checked immediately prior to spinal anesthesia.")
                    ),
                    CoagDrugInfo(
                        t("Apixaban (Eliquis)", "Apixaban (Eliquis)"),
                        t("72 Saat (3 Gün)", "72 Hours (3 Days)"),
                        t("Kateter takılıyken kullanılmamalı", "Should not be used with catheter in place"),
                        t("Kateter çekildikten 6 saat sonra", "6 hours after catheter removal"),
                        t("Böbrek fonksiyonlarına göre doz ve süre ayarlanır.", "Dose and duration adjusted according to renal functions.")
                    ),
                    CoagDrugInfo(
                        t("Rivaroxaban (Xarelto)", "Rivaroxaban (Xarelto)"),
                        t("72 Saat (3 Gün)", "72 Hours (3 Days)"),
                        t("Kateter takılıyken kullanılmamalı", "Should not be used with catheter in place"),
                        t("Kateter çekildikten 6 saat sonra", "6 hours after catheter removal"),
                        t("Yeni nesil oral Xa inhibitörü.", "New generation oral Xa inhibitor.")
                    ),
                    CoagDrugInfo(
                        t("Dabigatran (Pradaxa)", "Dabigatran (Pradaxa)"),
                        t("96 Saat (4 Gün)", "96 Hours (4 Days)"),
                        t("Kateter takılıyken kullanılmamalı", "Should not be used with catheter in place"),
                        t("Kateter çekildikten 6 saat sonra", "6 hours after catheter removal"),
                        t("Renal yetmezliği olanlarda süre 5 güne kadar uzatılır.", "Duration extended up to 5 days in patients with renal impairment.")
                    )
                )

                val filteredDataset = asraDataset.filter {
                    it.name.contains(coagSearchQuery, ignoreCase = true) ||
                    it.clinicalNotes.contains(coagSearchQuery, ignoreCase = true)
                }

                if (filteredDataset.isEmpty()) {
                    Text(t("Aranan kritere uygun ilaç bulunamadı.", "No matching drug found for search criteria."), style = MaterialTheme.typography.bodyLarge, color = TextSecondaryLight)
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        filteredDataset.forEach { drug ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Linen),
                                border = BorderStroke(1.dp, BorderSand),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text(drug.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = DeepNavy)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    
                                    CoagRowItem(label = t("Blok Öncesi Kesme Süresi:", "Pre-Block Discontinuation Time:"), value = drug.pauseBefore, valueColor = CriticalRed)
                                    CoagRowItem(label = t("Kateter Yönetimi:", "Catheter Management:"), value = drug.catheterManagement, valueColor = AlertAmber)
                                    CoagRowItem(label = t("Tekrar Başlama Süresi:", "Restart Time:"), value = drug.restartAfter, valueColor = SafeGreen)
                                    
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = t("Klinik Not", "Clinical Note") + ": " + drug.clinicalNotes,
                                        fontSize = 11.sp,
                                        color = TextSecondaryLight,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }

            // --------------------------------------------------------------------------------
            // 4.1. PEDIATRIC EMERGENCY & INDUCTION DOSING SPECIAL VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "pediatric_dosing") {
                Text(
                    text = t("Pediatrik Doz Hesaplayıcı", "Pediatric Dose Calculator"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        FormSlider(label = t("Çocuk Vücut Ağırlığı", "Child Body Weight"), value = toDisplayWeight(pediatricWeightKg), range = toDisplayWeight(2.0).toFloat()..toDisplayWeight(60.0).toFloat(), unit = weightUnit) {
                            pediatricWeightKg = toInternalWeight(it.toDouble())
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                val pedDoseTable = MedicalCalculators.calculatePediatricDoses(pediatricWeightKg)

                Text(
                    text = t("Hesaplanan Acil & İndüksiyon Dozları", "Calculated Emergency & Induction Doses"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        PediatricDoseItem(
                            name = t("Adrenalin (CPR / Resüsitasyon)", "Epinephrine (CPR / Resuscitation)"),
                            mgDose = "${String.format(Locale.US, "%.3f", pedDoseTable.adrenalinMg)} mg (${String.format(Locale.US, "%.0f", pedDoseTable.adrenalinMg * 1000)} mcg)",
                            mlDose = "${String.format(Locale.US, "%.2f", pedDoseTable.adrenalinMl)} mL",
                            preparation = t("1 mg Adrenalin ampulünü 10 mL saline sulandırın (1:10.000). Bu çözeltiden 0.1 mL/kg uygulayın.", "Dilute 1 mg Epinephrine ampoule with 10 mL saline (1:10,000). Administer 0.1 mL/kg of this solution."),
                            currentLanguage = currentLanguage
                        )
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        PediatricDoseItem(
                            name = t("Atropin (Bradikardi)", "Atropine (Bradycardia)"),
                            mgDose = "${String.format(Locale.US, "%.2f", pedDoseTable.atropinMg)} mg",
                            mlDose = "${String.format(Locale.US, "%.2f", pedDoseTable.atropinMl)} mL",
                            preparation = t("0.5 mg Atropin ampulünü 5 mL saline sulandırın (0.1 mg/mL). Min 0.1 mg, Max 0.5 mg doz uygulanır.", "Dilute 0.5 mg Atropine ampoule in 5 mL saline (0.1 mg/mL). Min 0.1 mg, Max 0.5 mg is administered."),
                            currentLanguage = currentLanguage
                        )
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        PediatricDoseItem(
                            name = t("Propofol %1 (İndüksiyon)", "Propofol 1% (Induction)"),
                            mgDose = "${String.format(Locale.US, "%.1f", pedDoseTable.propofolMg)} mg",
                            mlDose = "${String.format(Locale.US, "%.2f", pedDoseTable.propofolMl)} mL",
                            preparation = t("Propofol %1 (10 mg/mL) solüsyonu sulandırılmadan doğrudan 2.5 mg/kg olarak çekilir.", "Propofol 1% (10 mg/mL) solution is drawn directly at 2.5 mg/kg without dilution."),
                            currentLanguage = currentLanguage
                        )
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        PediatricDoseItem(
                            name = t("Ketamin (İndüksiyon / Sedasyon)", "Ketamine (Induction / Sedation)"),
                            mgDose = "${String.format(Locale.US, "%.1f", pedDoseTable.ketaminMg)} mg",
                            mlDose = "${String.format(Locale.US, "%.2f", pedDoseTable.ketaminMl)} mL",
                            preparation = t("50 mg/mL Ketamin ampulünü 5 mL saline sulandırın (10 mg/mL). Bu çözeltiden 2.0 mg/kg çekilir.", "Dilute 50 mg/mL Ketamine ampoule in 5 mL saline (10 mg/mL). Draw 2.0 mg/kg from this solution."),
                            currentLanguage = currentLanguage
                        )
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        PediatricDoseItem(
                            name = t("Fentanil (Analjezi)", "Fentanyl (Analgesia)"),
                            mgDose = "${String.format(Locale.US, "%.1f", pedDoseTable.fentanylMcg)} mcg",
                            mlDose = "${String.format(Locale.US, "%.2f", pedDoseTable.fentanylMl)} mL",
                            preparation = t("50 mcg/mL standard Fentanil ampulü doğrudan 1.5 mcg/kg dozunda çekilir.", "Standard 50 mcg/mL Fentanyl ampoule is drawn directly at a dose of 1.5 mcg/kg."),
                            currentLanguage = currentLanguage
                        )
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        PediatricDoseItem(
                            name = t("Roküronyum (Gevşetici)", "Rocuronium (Relaxant)"),
                            mgDose = "${String.format(Locale.US, "%.1f", pedDoseTable.rocuroniumMg)} mg",
                            mlDose = "${String.format(Locale.US, "%.2f", pedDoseTable.rocuroniumMl)} mL",
                            preparation = t("10 mg/mL standard Roküronyum ampulü sulandırılmadan 0.6 mg/kg dozunda çekilir.", "Standard 10 mg/mL Rocuronium ampoule is drawn undiluted at a dose of 0.6 mg/kg."),
                            currentLanguage = currentLanguage
                        )
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        PediatricDoseItem(
                            name = t("Süksinilkolin (Acil / RSI Gevşetici)", "Succinylcholine (Emergency / RSI Relaxant)"),
                            mgDose = "${String.format(Locale.US, "%.1f", pedDoseTable.succinylcholineMg)} mg",
                            mlDose = "${String.format(Locale.US, "%.2f", pedDoseTable.succinylcholineMl)} mL",
                            preparation = t("20 mg/mL standard Süksinilkolin ampulü 1.5 mg/kg acil entübasyon dozunda çekilir.", "Standard 20 mg/mL Succinylcholine ampoule is drawn at 1.5 mg/kg emergency intubation dose."),
                            currentLanguage = currentLanguage
                        )
                    }
                }

            // --------------------------------------------------------------------------------
            // 4.2. CARDIOVASCULAR SUPPORT PERFUSION SPECIAL VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "infusion_vasopressors") {
                Text(
                    text = t("Vazopressör & İnfüzyon Parametreleri", "Vasopressor & Infusion Parameters"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(t("İnfüzyon İlacı", "Infusion Drug"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf("noradrenalin" to t("Norad.", "Norepi."), "adrenalin" to t("Adren.", "Epi."), "dobutamin" to "Dobut.", "dopamin" to "Dopam.", "nitrogliserin" to "NTG").forEach { (dk, dlabel) ->
                                val isSel = selectedInfusionDrug == dk
                                Button(
                                    onClick = {
                                        selectedInfusionDrug = dk
                                        when (dk) {
                                            "noradrenalin", "adrenalin" -> {
                                                infusionSyringeMg = 4.0
                                                infusionTargetDose = 0.1
                                            }
                                            "nitrogliserin" -> {
                                                infusionSyringeMg = 10.0
                                                infusionTargetDose = 20.0
                                            }
                                            else -> {
                                                infusionSyringeMg = 250.0
                                                infusionTargetDose = 5.0
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSel) DeepNavy else BorderSand.copy(alpha = 0.3f),
                                        contentColor = if (isSel) Color.White else TextPrimaryLight
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f).height(36.dp),
                                    contentPadding = PaddingValues(horizontal = 2.dp)
                                ) {
                                    Text(dlabel, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        FormSlider(label = t("Hasta Vücut Ağırlığı", "Patient Body Weight"), value = toDisplayWeight(infusionWeightKg), range = toDisplayWeight(30.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                            infusionWeightKg = toInternalWeight(it.toDouble())
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val mgMax = if (selectedInfusionDrug in listOf("noradrenalin", "adrenalin", "nitrogliserin")) 20f else 500f
                        FormSlider(label = t("Ampul Miktarı (Şırıngadaki)", "Drug Amount in Syringe"), value = infusionSyringeMg, range = 1f..mgMax, unit = "mg") {
                            infusionSyringeMg = it
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        FormSlider(label = t("Toplam Enjektör Hacmi", "Total Syringe Volume"), value = infusionSyringeMl, range = 10f..100f, unit = "mL") {
                            infusionSyringeMl = it
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val doseMax = if (selectedInfusionDrug in listOf("noradrenalin", "adrenalin")) 2f else if (selectedInfusionDrug == "nitrogliserin") 100f else 20f
                        val doseUnit = if (selectedInfusionDrug == "nitrogliserin") t("mcg/dk", "mcg/min") else t("mcg/kg/dk", "mcg/kg/min")
                        FormSlider(label = t("Hedef İnfüzyon Dozu", "Target Infusion Dose"), value = infusionTargetDose, range = 0.01f..doseMax, unit = doseUnit) {
                            infusionTargetDose = it
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                val infusionRate = MedicalCalculators.calculateInfusionRate(
                    drug = selectedInfusionDrug,
                    weightKg = infusionWeightKg,
                    syringeMg = infusionSyringeMg,
                    syringeMl = infusionSyringeMl,
                    targetDose = infusionTargetDose
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    border = BorderStroke(1.dp, SafeGreen),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(t("PERFÜZYÖR SAATLİK AKIŞ HIZI", "PERFUSOR HOURLY FLOW RATE"), fontWeight = FontWeight.Bold, color = SafeGreen, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTurkish) "${String.format(Locale.US, "%.1f", infusionRate)} mL/saat" else "${String.format(Locale.US, "%.1f", infusionRate)} mL/hour",
                            style = MaterialTheme.typography.displayLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        val concText = String.format(Locale.US, "%.1f mcg/mL", (infusionSyringeMg * 1000.0) / infusionSyringeMl)
                        Text(
                            text = t("Enjektör Çözelti Derişimi", "Syringe Solution Concentration") + ": $concText. " + t("Perfüzyör hızını bu değere ayarlayın.", "Adjust the perfusor rate to this value."),
                            fontSize = 11.sp,
                            color = TextPrimaryLight,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

            // --------------------------------------------------------------------------------
            // 4.3. REGIONAL MIXTURES GUIDE SPECIAL VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "regional_mixtures") {
                Text(
                    text = t("Rejyonel Anestezi Karışımları", "Regional Anesthesia Mixtures"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(t("Klinik Karışım Tipi", "Clinical Mixture Type"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf("labor_epidural" to t("Ağrısız Doğum", "Labor Analg."), "postop_epidural" to t("Postop Epid.", "Postop Epid."), "spinal_caesarean" to t("Spinal Sezar.", "Spinal Cesarean"), "spinal_classic" to t("Spinal Klas.", "Spinal Classic")).forEach { (tk, tlabel) ->
                                val isSel = selectedMixtureTab == tk
                                Button(
                                    onClick = { selectedMixtureTab = tk },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSel) DeepNavy else BorderSand.copy(alpha = 0.3f),
                                        contentColor = if (isSel) Color.White else TextPrimaryLight
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f).height(36.dp),
                                    contentPadding = PaddingValues(horizontal = 2.dp)
                                ) {
                                    Text(tlabel, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                val mixDetails = when (selectedMixtureTab) {
                    "labor_epidural" -> MixtureRecipe(
                        t("Epidural Ağrısız Doğum (Labor Analgesia) Karışımı", "Labor Epidural Analgesia Mixture"),
                        t(
                            "Saline 50 mL + Bupivakain 0.5% (Marcaine) 10 mL + Fentanil (100 mcg) 2 mL.\nElde edilen nihai derişim: %0.08 Bupivakain + 1.6 mcg/mL Fentanil.",
                            "Saline 50 mL + Bupivacaine 0.5% (Marcaine) 10 mL + Fentanyl (100 mcg) 2 mL.\nFinal concentration obtained: 0.08% Bupivacaine + 1.6 mcg/mL Fentanyl."
                        ),
                        t("Toplam Karışım Hacmi: 62 mL", "Total Mixture Volume: 62 mL"),
                        t(
                            "Önerilen Bazal İnfüzyon Hızı: 8-12 mL/saat. PCEA bolusu: 5 mL (Kilit süresi: 15-20 dk).",
                            "Recommended Basal Infusion Rate: 8-12 mL/hour. PCEA bolus: 5 mL (Lockout: 15-20 min)."
                        ),
                        t(
                            "3 mL Test Dozu (Lidokain %1.5 + Adrenalin 1:200.000) uygulandıktan sonra, fraksiyonlar halinde 10-15 mL Bupivakain %0.0625 - %0.125 + Fentanil 50-100 mcg yüklemesi yapılır.",
                            "After administering a 3 mL Test Dose (Lidocaine 1.5% + Epinephrine 1:200,000), loading of 10-15 mL Bupivacaine 0.0625% - 0.125% + Fentanyl 50-100 mcg is performed in divided doses."
                        ),
                        listOf(
                            "Practice Guidelines for Obstetric Anesthesia: An Updated Report by the ASA and SOAP. Anesthesiology 2016; 124(2):270-300. PMID: 26575101.",
                            "Anim-Somuah M, et al.: Epidural versus non-epidural or no analgesia for pain management in labour. Cochrane Database of Systematic Reviews 2018; (5):CD000352. PMID: 29780515.",
                            "Chestnut's Obstetric Anesthesia: Principles and Practice, 6th Edition, Chapter 20 (Labor Analgesia: Epidural and Spinal Techniques)."
                        )
                    )
                    "postop_epidural" -> MixtureRecipe(
                        t("Postoperatif Epidural İnfüzyon Karışımı", "Postoperative Epidural Infusion Mixture"),
                        t(
                            "Saline 40 mL + Bupivakain 0.5% (Marcaine) 20 mL + Fentanil (100 mcg) 2 mL.\nElde edilen nihai derişim: %0.16 Bupivakain + 1.6 mcg/mL Fentanil.",
                            "Saline 40 mL + Bupivacaine 0.5% (Marcaine) 20 mL + Fentanyl (100 mcg) 2 mL.\nFinal concentration obtained: 0.16% Bupivacaine + 1.6 mcg/mL Fentanyl."
                        ),
                        t("Toplam Karışım Hacmi: 62 mL", "Total Mixture Volume: 62 mL"),
                        t(
                            "Önerilen İnfüzyon Hızı: Cerrahi seviyeye göre 4-8 mL/saat (Yakın duyu/motor takibi şarttır).",
                            "Recommended Infusion Rate: 4-8 mL/hour based on surgical level (Close sensory/motor monitoring is mandatory)."
                        ),
                        t(
                            "3 mL Test Dozu sonrası, cerrahi seviyeye ve segmental blok hedefine göre bölünmüş dozlar halinde 6-10 mL Bupivakain %0.125 - %0.16 + Fentanil 2 mcg/mL yüklenir.",
                            "After a 3 mL Test Dose, loading of 6-10 mL Bupivacaine 0.125% - 0.16% + Fentanyl 2 mcg/mL is performed in divided doses based on surgical level and segmental block target."
                        ),
                        listOf(
                            "Horlocker TT, et al.: Regional Anesthesia in the Patient Receiving Antithrombotic or Thrombolytic Therapy: ASRA Pain Medicine Evidence-Based Guidelines (4th Edition). Regional Anesthesia & Pain Medicine 2018; 43(3):263-309. PMID: 29356773."
                        )
                    )
                    "spinal_caesarean" -> MixtureRecipe(
                        t("Spinal Sezaryen (Obstetrik) Dozajlama", "Spinal Cesarean (Obstetric) Dosing"),
                        t(
                            "Heavy Bupivakain 0.5% (Bupivacaine spinal heavy) 1.8 mL (9 mg) + Fentanil (100 mcg/2mL standard) 0.2 mL (10 mcg) + Morfin (preservative-free) 0.1 mL (100 mcg).",
                            "Heavy Bupivacaine 0.5% (Bupivacaine spinal heavy) 1.8 mL (9 mg) + Fentanyl (100 mcg/2mL standard) 0.2 mL (10 mcg) + Morphine (preservative-free) 0.1 mL (100 mcg)."
                        ),
                        t("Toplam Karışım Hacmi: 2.1 mL", "Total Mixture Volume: 2.1 mL"),
                        t(
                            "Doğrudan intratekal (spinal) bolus olarak verilir. Postoperatif 24 saat solunum depresyonu takibi önerilir.",
                            "Administered directly as intrathecal (spinal) bolus. Postoperative 24-hour respiratory depression monitoring is recommended."
                        ),
                        t(
                            "Doğrudan Tek Seferlik Spinal Bolus: Heavy Bupivakain %0.5 1.8 mL (9 mg) + Fentanil 10 mcg (0.2 mL) + Koruyucusuz Morfin 100 mcg (0.1 mL).",
                            "Direct Single Spinal Bolus: Heavy Bupivacaine 0.5% 1.8 mL (9 mg) + Fentanyl 10 mcg (0.2 mL) + Preservative-free Morphine 100 mcg (0.1 mL)."
                        ),
                        listOf(
                            "Kinsella SM, et al.: Consensus Statement on the Management of Hypotension with Caesarean Section under Spinal Anaesthesia. Anaesthesia 2018; 73(1):71-92. PMID: 29116606.",
                            "ACOG Practice Bulletin No. 209: Obstetric Analgesia and Anesthesia. Obstetrics & Gynecology 2019; 133(3):e208-e225. PMID: 30801474."
                        )
                    )
                    else -> MixtureRecipe(
                        t("Spinal Klasik Cerrahi Dozajlama", "Spinal Classic Surgical Dosing"),
                        t(
                            "Heavy Bupivakain 0.5% (Bupivacaine spinal heavy) 2.5 - 3.0 mL (12.5 - 15 mg) + Fentanil (50 mcg/mL standard) 0.5 mL (25 mcg).",
                            "Heavy Bupivacaine 0.5% (Bupivacaine spinal heavy) 2.5 - 3.0 mL (12.5 - 15 mg) + Fentanyl (50 mcg/mL standard) 0.5 mL (25 mcg)."
                        ),
                        t("Toplam Karışım Hacmi: 3.0 - 3.5 mL", "Total Mixture Volume: 3.0 - 3.5 mL"),
                        t(
                            "Doğrudan intratekal (spinal) bolus olarak verilir. Alt batın, ürolojik ve alt ekstremite cerrahileri için idealdir.",
                            "Administered directly as intrathecal (spinal) bolus. Ideal for lower abdominal, urological, and lower extremity surgeries."
                        ),
                        t(
                            "Doğrudan Tek Seferlik Spinal Bolus: Heavy Bupivakain %0.5 2.5 - 3.0 mL (12.5 - 15 mg) + Fentanil 25 mcg (0.5 mL).",
                            "Direct Single Spinal Bolus: Heavy Bupivacaine 0.5% 2.5 - 3.0 mL (12.5 - 15 mg) + Fentanyl 25 mcg (0.5 mL)."
                        ),
                        listOf(
                            "Spinal Anesthesia: Practical Guide and Standard Dosage. Barash Clinical Anesthesia, 8th Edition, Chapter 38."
                        )
                    )
                }

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(mixDetails.title, fontWeight = FontWeight.Bold, color = DeepNavy, fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        CohortItem(label = t("Hazırlama Reçetesi (Recipe):", "Preparation Recipe:"), text = mixDetails.recipe)
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        CohortItem(label = t("Başlangıç Yükleme / Test Dozu:", "Initial Loading / Test Dose:"), text = mixDetails.initialDose)
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        CohortItem(label = t("Nihai Karışım Hacmi:", "Final Mixture Volume:"), text = mixDetails.totalVol)
                        Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                        CohortItem(label = t("İdame Hız / Uygulama Önerileri:", "Maintenance Rate / Admin Guidelines:"), text = mixDetails.rates)
                        
                        if (mixDetails.publications.isNotEmpty()) {
                            Divider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                            Text(
                                text = t("Referans Yayınlar (Citations):", "Reference Publications (Citations):"),
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            mixDetails.publications.forEachIndexed { index, pub ->
                                Text(
                                    text = "${index + 1}. $pub",
                                    fontSize = 11.sp,
                                    color = TextPrimaryLight,
                                    lineHeight = 14.sp,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }
                }

            // --------------------------------------------------------------------------------
            // 4.4. MALIGNANT HYPERTHERMIA CRISIS SPECIAL VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "dantrolene_rescue") {
                Text(
                    text = t("Malign Hipertermi Dantrolen Düzeyi", "Malignant Hyperthermia Dantrolene Dose"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        FormSlider(label = t("Hasta Vücut Ağırlığı", "Patient Body Weight"), value = toDisplayWeight(dantroleneWeightKg), range = toDisplayWeight(5.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                            dantroleneWeightKg = toInternalWeight(it.toDouble())
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                val dantroleneResult = MedicalCalculators.calculateDantroleneRescue(dantroleneWeightKg)

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF2F2)),
                    border = BorderStroke(1.dp, CriticalRed),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(t("🚨 MALİGN HİPERTERMİ ACİL DANTROLEN DOZU", "🚨 MALIGNANT HYPERTHERMIA EMERGENCY DANTROLENE DOSE"), fontWeight = FontWeight.Bold, color = CriticalRed, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${String.format(Locale.US, "%.0f", dantroleneResult.bolusMg)} mg bolus",
                            style = MaterialTheme.typography.displayLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(t("Açılması Gereken Flakon Sayısı:", "Number of Vials to Open:"), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                            Text("${dantroleneResult.totalVials} " + t("Flakon", "Vials"), fontWeight = FontWeight.Bold, color = CriticalRed, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(t("Gereken Steril Distile Su Hacmi:", "Required Sterile Distilled Water Volume:"), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                            Text("${String.format(Locale.US, "%.0f", dantroleneResult.sterileWaterMl)} mL", fontWeight = FontWeight.Bold, color = DeepNavy, fontSize = 14.sp)
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = t(
                                "Kritik Protokol Adımları:\n1. Tetikleyici ajanları (Tüm inhalasyon anestezikleri ve Süksinilkolin) DERHAL kapatın.\n2. Ameliyathaneyi %100 Oksijen ile yüksek akışta havalandırın. Aktif karbon filtresi takın.\n3. Flakon başına 60 mL sıcak steril distile su (koruyucu içermeyen) ekleyin ve berraklaşana kadar hızla çalkalayın. IV bolus olarak 2.5 mg/kg hızla uygulayın. Semptomlar dinene kadar her 5-10 dakikada bir tekrarlayın (max 10 mg/kg).\n4. Hastayı soğutmaya başlayın (soğuk IV sıvılar, batın yıkama, buz torbaları). Ciddi asidoz ve hiperkalemi kontrolü yapın.",
                                "Critical Protocol Steps:\n1. IMMEDIATELY discontinue triggering agents (All inhalation anesthetics and Succinylcholine).\n2. Ventilate the OR with 100% Oxygen at high flow. Insert activated charcoal filter.\n3. Add 60 mL of warm sterile distilled water (preservative-free) per vial and shake quickly until clear. Administer 2.5 mg/kg IV bolus rapidly. Repeat every 5-10 minutes until symptoms subside (max 10 mg/kg).\n4. Begin cooling the patient (cold IV fluids, abdominal lavage, ice packs). Manage severe acidosis and hyperkalemia."
                            ),
                            fontSize = 11.sp,
                            color = TextPrimaryLight,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

            // --------------------------------------------------------------------------------
            // 4.5. LAST LIPIDRESCUE CRISIS SPECIAL VIEW
            // --------------------------------------------------------------------------------
            } else if (calculator.slug == "lipid_rescue") {
                Text(
                    text = t("LAST LipidRescue Düzeyleri", "LAST LipidRescue Levels"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        FormSlider(label = t("Hasta Vücut Ağırlığı", "Patient Body Weight"), value = toDisplayWeight(lipidWeightKg), range = toDisplayWeight(5.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                            lipidWeightKg = toInternalWeight(it.toDouble())
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                val lipidRescueResult = MedicalCalculators.calculateLipidRescue(lipidWeightKg)

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF2F2)),
                    border = BorderStroke(1.dp, CriticalRed),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(t("🚨 LAST ACİL %20 LİPİD EMÜLSİYONU RESCUE DOZU", "🚨 LAST EMERGENCY 20% LIPID EMULSION RESCUE DOSE"), fontWeight = FontWeight.Bold, color = CriticalRed, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${String.format(Locale.US, "%.1f", lipidRescueResult.first)} mL bolus",
                            style = MaterialTheme.typography.displayLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(t("1 dakikada IV bolus olarak yavaşça uygulayın.", "Administer slowly as IV bolus over 1 minute."), fontSize = 12.sp, color = TextPrimaryLight, fontWeight = FontWeight.SemiBold)

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(t("Perfüzyör Saatlik İnfüzyon Hızı:", "Perfusor Hourly Infusion Rate:"), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                            Text(if (isTurkish) "${String.format(Locale.US, "%.1f", lipidRescueResult.second * 60.0)} mL/saat" else "${String.format(Locale.US, "%.1f", lipidRescueResult.second * 60.0)} mL/hour", fontWeight = FontWeight.Bold, color = DeepNavy, fontSize = 14.sp)
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = t(
                                "Kritik Klinik Notlar:\n- Bolus ve sürekli infüzyona hemodinamik stabilite sağlanana kadar veya maksimum lipid dozu (12 mL/kg) aşılana kadar devam edilir.\n- Hemodinamik instabilite sürerse bolus doz 5 dakika arayla 1 veya 2 kez tekrarlanabilir; sürekli infüzyon hızı 0.5 mL/kg/dk seviyesine iki katına çıkarılabilir.\n- Lipid enjeksiyonu ile eş zamanlı olarak standart KPR ve hava yolu desteğine devam edilmelidir. Hemodinamik resüsitasyonda propofol veya vazopressörlerin (özellikle yüksek doz adrenalin) hemodinamik bozulmayı derinleştirebileceğini unutmayın.",
                                "Critical Clinical Notes:\n- Continue bolus and continuous infusion until hemodynamic stability is achieved or until the maximum lipid dose (12 mL/kg) is exceeded.\n- If hemodynamic instability persists, the bolus dose may be repeated 1-2 times at 5-minute intervals; the continuous infusion rate may be doubled to 0.5 mL/kg/min.\n- Continue standard CPR and airway support concurrently with lipid injection. Remember that propofol or vazopressors (especially high-dose epinephrine) may exacerbate hemodynamic compromise during resuscitation."
                            ),
                            fontSize = 11.sp,
                            color = TextPrimaryLight,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

            // --------------------------------------------------------------------------------
            // 5. STANDARD MATHEMATICAL CALCULATORS VIEW
            // --------------------------------------------------------------------------------
            } else {
                // Input Form Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = t("Giriş Parametreleri", "Input Parameters"),
                            style = MaterialTheme.typography.titleLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Conditional Input Fields based on slug
                        when (calculator.slug) {
                            "bmi", "bsa" -> {
                                FormSlider(label = t("Vücut Ağırlığı", "Body Weight"), value = toDisplayWeight(inputVal1.toDoubleOrNull() ?: 70.0), range = toDisplayWeight(30.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                                    inputVal1 = String.format(Locale.US, "%.1f", toInternalWeight(it.toDouble()))
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                FormSlider(label = t("Boy Uzunluğu", "Height"), value = toDisplayDist(inputVal2.toDoubleOrNull() ?: 170.0), range = toDisplayDist(100.0).toFloat()..toDisplayDist(220.0).toFloat(), unit = distUnit) {
                                    inputVal2 = String.format(Locale.US, "%.1f", toInternalDist(it.toDouble()))
                                    runCalculation()
                                }
                            }
                            "map" -> {
                                FormSlider(label = t("Sistolik Kan Basıncı (SBP)", "Systolic Blood Pressure (SBP)"), value = inputVal1.toDoubleOrNull() ?: 120.0, range = 50f..200f, unit = "mmHg") {
                                    inputVal1 = String.format(Locale.US, "%.1f", it)
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                FormSlider(label = t("Diyastolik Kan Basıncı (DBP)", "Diastolic Blood Pressure (DBP)"), value = inputVal2.toDoubleOrNull() ?: 80.0, range = 30f..130f, unit = "mmHg") {
                                    inputVal2 = String.format(Locale.US, "%.1f", it)
                                    runCalculation()
                                }
                            }
                            "ebv_abl" -> {
                                FormSlider(label = t("Vücut Ağırlığı", "Body Weight"), value = toDisplayWeight(inputVal1.toDoubleOrNull() ?: 70.0), range = toDisplayWeight(5.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                                    inputVal1 = String.format(Locale.US, "%.1f", toInternalWeight(it.toDouble()))
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(t("Hasta Kategorisi", "Patient Category"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("Yetişkin Erkek", "Adult Male"), selectedCategory == "adult_male") { selectedCategory = "adult_male"; runCalculation() }
                                    CategoryButton(t("Yetişkin Kadın", "Adult Female"), selectedCategory == "adult_female") { selectedCategory = "adult_female"; runCalculation() }
                                    CategoryButton(t("Çocuk", "Child"), selectedCategory == "child") { selectedCategory = "child"; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                FormSlider(label = t("Başlangıç Hemoglobin (Hb)", "Initial Hemoglobin (Hb)"), value = inputVal2.toDoubleOrNull() ?: 14.0, range = 5f..18f, unit = "g/dL") {
                                    inputVal2 = String.format(Locale.US, "%.1f", it)
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                FormSlider(label = t("Kabul Edilebilir En Düşük Hb", "Allowable Minimum Hb"), value = inputVal3.toDoubleOrNull() ?: 9.0, range = 5f..12f, unit = "g/dL") {
                                    inputVal3 = String.format(Locale.US, "%.1f", it)
                                    runCalculation()
                                }
                            }
                            "maintenance_421" -> {
                                FormSlider(label = t("Vücut Ağırlığı", "Body Weight"), value = toDisplayWeight(inputVal1.toDoubleOrNull() ?: 70.0), range = toDisplayWeight(1.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                                    inputVal1 = String.format(Locale.US, "%.1f", toInternalWeight(it.toDouble()))
                                    runCalculation()
                                }
                            }
                            "gcs" -> {
                                Text(t("Göz Açma Yanıtı (1-4)", "Eye Opening Response (1-4)"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    (1..4).forEach { score ->
                                        ScoreBtn(score.toString(), inputVal1 == score.toString()) { inputVal1 = score.toString(); runCalculation() }
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(t("Sözel Yanıt (1-5)", "Verbal Response (1-5)"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    (1..5).forEach { score ->
                                        ScoreBtn(score.toString(), inputVal2 == score.toString()) { inputVal2 = score.toString(); runCalculation() }
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(t("Motor Yanıt (1-6)", "Motor Response (1-6)"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    (1..6).forEach { score ->
                                        ScoreBtn(score.toString(), inputVal3 == score.toString()) { inputVal3 = score.toString(); runCalculation() }
                                    }
                                }
                            }
                            "apfel_score" -> {
                                Text(t("Apfel Risk Faktörleri", "Apfel Risk Factors"), style = MaterialTheme.typography.titleMedium, color = DeepNavy, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { apfelFemale = !apfelFemale; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = apfelFemale, onCheckedChange = { apfelFemale = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Kadın Cinsiyet (Female Gender)", "Female Gender"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { apfelNonSmoker = !apfelNonSmoker; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = apfelNonSmoker, onCheckedChange = { apfelNonSmoker = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Sigara İçmeme (Non-smoker)", "Non-smoker"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { apfelHistoryPONV = !apfelHistoryPONV; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = apfelHistoryPONV, onCheckedChange = { apfelHistoryPONV = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("PONV veya Hareket Hastalığı Öyküsü (History of PONV/Motion Sickness)", "History of PONV/Motion Sickness"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { apfelPostOpOpioids = !apfelPostOpOpioids; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = apfelPostOpOpioids, onCheckedChange = { apfelPostOpOpioids = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Postoperatif Opioid Kullanım Planı (Post-op Opioids)", "Postoperative Opioid Use Plan (Post-op Opioids)"), color = TextPrimaryLight)
                                }
                            }
                            "aldrete_score" -> {
                                Text(t("Aldrete Kriterleri", "Aldrete Criteria"), style = MaterialTheme.typography.titleMedium, color = DeepNavy, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                Text(t("1. Aktivite (Activity)", "1. Activity"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("0 (İmmobil)", "0 (Immobile)"), aldreteActivity == 0) { aldreteActivity = 0; runCalculation() }
                                    CategoryButton(t("1 (2 Ekstremite)", "1 (2 Extremities)"), aldreteActivity == 1) { aldreteActivity = 1; runCalculation() }
                                    CategoryButton(t("2 (4 Ekstremite)", "2 (4 Extremities)"), aldreteActivity == 2) { aldreteActivity = 2; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                Text(t("2. Solunum (Respiration)", "2. Respiration"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("0 (Apne)", "0 (Apnea)"), aldreteRespiration == 0) { aldreteRespiration = 0; runCalculation() }
                                    CategoryButton(t("1 (Dispne/Dispneik)", "1 (Dyspnea/Dyspneic)"), aldreteRespiration == 1) { aldreteRespiration = 1; runCalculation() }
                                    CategoryButton(t("2 (Normal/Öksürebiliyor)", "2 (Normal/Able to cough)"), aldreteRespiration == 2) { aldreteRespiration = 2; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                Text(t("3. Dolaşım (Circulation - Kan Basıncı)", "3. Circulation - Blood Pressure"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("0 (Preop %50+ Fark)", "0 (Preop %50+ Diff)"), aldreteCirculation == 0) { aldreteCirculation = 0; runCalculation() }
                                    CategoryButton(t("1 (Preop %20-50 Fark)", "1 (Preop %20-50 Diff)"), aldreteCirculation == 1) { aldreteCirculation = 1; runCalculation() }
                                    CategoryButton(t("2 (Preop %20 Farktan Az)", "2 (Preop <20% Diff)"), aldreteCirculation == 2) { aldreteCirculation = 2; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                Text(t("4. Bilinç (Consciousness)", "4. Consciousness"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("0 (Yanıtsız)", "0 (Unresponsive)"), aldreteConsciousness == 0) { aldreteConsciousness = 0; runCalculation() }
                                    CategoryButton(t("1 (Sesle Uyarılıyor)", "1 (Arousable by Voice)"), aldreteConsciousness == 1) { aldreteConsciousness = 1; runCalculation() }
                                    CategoryButton(t("2 (Tam Uyanık)", "2 (Fully Awake)"), aldreteConsciousness == 2) { aldreteConsciousness = 2; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                Text(t("5. O2 Satürasyonu (O2 Saturation)", "5. O2 Saturation"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("0 (Oda Havasında <90)", "0 (Room Air <90%)"), aldreteO2Sat == 0) { aldreteO2Sat = 0; runCalculation() }
                                    CategoryButton(t("1 (O2 ile >90)", "1 (With O2 >90%)"), aldreteO2Sat == 1) { aldreteO2Sat = 1; runCalculation() }
                                    CategoryButton(t("2 (Oda Havasında >92)", "2 (Room Air >92%)"), aldreteO2Sat == 2) { aldreteO2Sat = 2; runCalculation() }
                                }
                            }
                            "rcri_score" -> {
                                Text(t("RCRI Risk Faktörleri", "RCRI Risk Factors"), style = MaterialTheme.typography.titleMedium, color = DeepNavy, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { rcriHighRiskSurgery = !rcriHighRiskSurgery; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = rcriHighRiskSurgery, onCheckedChange = { rcriHighRiskSurgery = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Yüksek Riskli Cerrahi (İntratorasik, peritoneal, suprainguinal)", "High-Risk Surgery (Intrathoracic, intraperitoneal, suprainguinal)"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { rcriIschemicHeart = !rcriIschemicHeart; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = rcriIschemicHeart, onCheckedChange = { rcriIschemicHeart = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("İskemik Kalp Hastalığı Öyküsü (MI, Angina, Q dalgası)", "History of Ischemic Heart Disease (MI, Angina, Q wave)"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { rcriHeartFailure = !rcriHeartFailure; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = rcriHeartFailure, onCheckedChange = { rcriHeartFailure = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Konjestif Kalp Yetmezliği Öyküsü (S3, Raller, Pulmoner Ödem)", "History of Congestive Heart Failure (S3, Rales, Pulmonary Edema)"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { rcriCerebrovascular = !rcriCerebrovascular; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = rcriCerebrovascular, onCheckedChange = { rcriCerebrovascular = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Serebrovasküler Hastalık Öyküsü (TİA, İnme)", "History of Cerebrovascular Disease (TIA, Stroke)"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { rcriInsulin = !rcriInsulin; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = rcriInsulin, onCheckedChange = { rcriInsulin = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Preoperatif İnsülin Gerektiren Diyabet", "Preoperative Diabetes Requiring Insulin"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { rcriCreatinine = !rcriCreatinine; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = rcriCreatinine, onCheckedChange = { rcriCreatinine = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Preoperatif Serum Kreatinin > 2.0 mg/dL (177 mcmol/L)", "Preoperative Serum Creatinine > 2.0 mg/dL (177 mcmol/L)"), color = TextPrimaryLight)
                                }
                            }
                            "stop_bang_score" -> {
                                Text(t("STOP-Bang Kriterleri", "STOP-Bang Criteria"), style = MaterialTheme.typography.titleMedium, color = DeepNavy, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopSnoring = !stopSnoring; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopSnoring, onCheckedChange = { stopSnoring = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Horlama (Snoring) - Uykuda yüksek sesle horlama", "Snoring - Loud snoring during sleep"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopTiredness = !stopTiredness; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopTiredness, onCheckedChange = { stopTiredness = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Yorgunluk (Tiredness) - Gün içinde yorgunluk/uyuklama", "Tiredness - Fatigue or sleepiness during the day"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopApnea = !stopApnea; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopApnea, onCheckedChange = { stopApnea = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Uykuda Solunum Durması Gözlendi (Observed Apnea)", "Observed Apnea - Stopped breathing during sleep"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopPressure = !stopPressure; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopPressure, onCheckedChange = { stopPressure = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Yüksek Tansiyon (Blood Pressure) tedavisi alıyor veya tansiyonu >140/90", "High Blood Pressure - Treated or SBP/DBP >140/90 mmHg"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopBmi = !stopBmi; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopBmi, onCheckedChange = { stopBmi = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Vücut Kitle İndeksi (BMI) > 35 kg/m²", "Body Mass Index (BMI) > 35 kg/m²"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopAge = !stopAge; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopAge, onCheckedChange = { stopAge = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Yaş (Age) > 50 yaş", "Age > 50 years"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopNeck = !stopNeck; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopNeck, onCheckedChange = { stopNeck = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Boyun Çevresi (Neck Circumference) > 40 cm", "Neck Circumference > 40 cm"), color = TextPrimaryLight)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { stopGender = !stopGender; runCalculation() }.padding(vertical = 4.dp)) {
                                    Checkbox(checked = stopGender, onCheckedChange = { stopGender = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Cinsiyet (Gender) - Erkek", "Gender - Male"), color = TextPrimaryLight)
                                }
                            }
                            "la_max_dose" -> {
                                FormSlider(label = t("Vücut Ağırlığı", "Body Weight"), value = toDisplayWeight(laWeightKg), range = toDisplayWeight(10.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                                    laWeightKg = toInternalWeight(it.toDouble())
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                Text(t("Lokal Anestezik Ajan", "Local Anesthetic Agent"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(vertical = 6.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        CategoryButton(t("Lidokain", "Lidocaine"), laSelectedAgent == "lidocaine") { laSelectedAgent = "lidocaine"; runCalculation() }
                                        CategoryButton(t("Bupivakain", "Bupivacaine"), laSelectedAgent == "bupivacaine") { laSelectedAgent = "bupivacaine"; runCalculation() }
                                        CategoryButton(t("Ropivakain", "Ropivacaine"), laSelectedAgent == "ropivacaine") { laSelectedAgent = "ropivacaine"; runCalculation() }
                                    }
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        CategoryButton(t("Prilokain", "Prilocaine"), laSelectedAgent == "prilocaine") { laSelectedAgent = "prilocaine"; runCalculation() }
                                        CategoryButton(t("Levobupivakain", "Levobupivacaine"), laSelectedAgent == "levobupivacaine") { laSelectedAgent = "levobupivacaine"; runCalculation() }
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { laWithEpinephrine = !laWithEpinephrine; runCalculation() }) {
                                    Checkbox(checked = laWithEpinephrine, onCheckedChange = { laWithEpinephrine = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Vazokonstrüktör (Epinefrin) Ekle", "Add Vasoconstrictor (Epinephrine)"), color = TextPrimaryLight)
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                FormSlider(label = t("Hazırlanan İlacın Konsantrasyonu", "Concentration of Prepared Drug"), value = laConcentrationPercent, range = 0.25f..2.0f, unit = "%") {
                                    laConcentrationPercent = it
                                    runCalculation()
                                }
                            }
                            "sugammadex_dose" -> {
                                FormSlider(label = t("Vücut Ağırlığı (Gerçek Kilo)", "Body Weight (Actual Weight)"), value = toDisplayWeight(sugammadexWeightKg), range = toDisplayWeight(10.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                                    sugammadexWeightKg = toInternalWeight(it.toDouble())
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                Text(t("TOF/PTC Blok Derinliği (TOF count)", "TOF/PTC Block Depth (TOF count)"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { sugammadexTOFLevel = "moderate"; runCalculation() }) {
                                        RadioButton(selected = sugammadexTOFLevel == "moderate", onClick = { sugammadexTOFLevel = "moderate"; runCalculation() })
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(t("TOF 2/4 (Orta Dereceli Blok - 2 mg/kg)", "TOF 2/4 (Moderate Block - 2 mg/kg)"), color = TextPrimaryLight)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { sugammadexTOFLevel = "deep"; runCalculation() }) {
                                        RadioButton(selected = sugammadexTOFLevel == "deep", onClick = { sugammadexTOFLevel = "deep"; runCalculation() })
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(t("TOF 0/4 ve PTC >= 1-2 (Derin Blok - 4 mg/kg)", "TOF 0/4 and PTC >= 1-2 (Deep Block - 4 mg/kg)"), color = TextPrimaryLight)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { sugammadexTOFLevel = "immediate"; runCalculation() }) {
                                        RadioButton(selected = sugammadexTOFLevel == "immediate", onClick = { sugammadexTOFLevel = "immediate"; runCalculation() })
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(t("İndüksiyon Sonrası Acil Reversal (CICO - 16 mg/kg)", "Immediate Reversal Post-Induction (CICO - 16 mg/kg)"), color = TextPrimaryLight)
                                    }
                                }
                            }
                            "pediatric_ett" -> {
                                FormSlider(label = t("Çocuğun Yaşı", "Child's Age"), value = ettAgeYears, range = 1.0f..12.0f, unit = t("yaş", "years")) {
                                    ettAgeYears = it
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { ettCuffed = !ettCuffed; runCalculation() }) {
                                    Checkbox(checked = ettCuffed, onCheckedChange = { ettCuffed = it; runCalculation() })
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(t("Balonlu Tüp (Cuffed ETT) Tercih Edildi", "Cuffed Tube (Cuffed ETT) Preferred"), color = TextPrimaryLight)
                                }
                            }
                            "ibw_tidal_volume" -> {
                                Text(t("Hastanın Cinsiyeti", "Patient's Gender"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("Erkek", "Male"), tvSex == "male") { tvSex = "male"; runCalculation() }
                                    CategoryButton(t("Kadın", "Female"), tvSex == "female") { tvSex = "female"; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                FormSlider(label = t("Boy Uzunluğu", "Height"), value = toDisplayDist(tvHeightCm), range = toDisplayDist(120.0).toFloat()..toDisplayDist(220.0).toFloat(), unit = distUnit) {
                                    tvHeightCm = toInternalDist(it.toDouble())
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                FormSlider(label = t("Hedef Tidal Hacim Çarpanı", "Target Tidal Volume Multiplier"), value = tvTargetMlPerKg, range = 4.0f..10.0f, unit = "mL/kg") {
                                    tvTargetMlPerKg = it
                                    runCalculation()
                                }
                            }
                            "creatinine_clearance" -> {
                                FormSlider(label = t("Yaş", "Age"), value = gfrAgeYears, range = 18f..100f, unit = t("yaş", "years")) {
                                    gfrAgeYears = it
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                FormSlider(label = t("Vücut Ağırlığı", "Body Weight"), value = toDisplayWeight(gfrWeightKg), range = toDisplayWeight(30.0).toFloat()..toDisplayWeight(150.0).toFloat(), unit = weightUnit) {
                                    gfrWeightKg = toInternalWeight(it.toDouble())
                                    runCalculation()
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                Text(t("Cinsiyet", "Gender"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    CategoryButton(t("Erkek", "Male"), gfrSex == "male") { gfrSex = "male"; runCalculation() }
                                    CategoryButton(t("Kadın", "Female"), gfrSex == "female") { gfrSex = "female"; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                FormSlider(label = t("Serum Kreatinin Seviyesi", "Serum Creatinine Level"), value = gfrCreatinine, range = 0.3f..5.0f, unit = "mg/dL") {
                                    gfrCreatinine = it
                                    runCalculation()
                                }
                            }
                            "opioid_equivalence" -> {
                                Text(t("Opioid Tipi", "Opioid Type"), style = MaterialTheme.typography.labelLarge, color = TextPrimaryLight)
                                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    CategoryButton(t("Fentanil (mcg)", "Fentanyl (mcg)"), opioidType == "fentanyl") { opioidType = "fentanyl"; runCalculation() }
                                    CategoryButton(t("Remifentanil (mcg)", "Remifentanil (mcg)"), opioidType == "remifentanil") { opioidType = "remifentanil"; runCalculation() }
                                    CategoryButton(t("Tramadol (mg)", "Tramadol (mg)"), opioidType == "tramadol") { opioidType = "tramadol"; runCalculation() }
                                    CategoryButton(t("Pethidine (mg)", "Pethidine (mg)"), opioidType == "pethidine") { opioidType = "pethidine"; runCalculation() }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                
                                val amountUnit = if (opioidType == "fentanyl" || opioidType == "remifentanil") "mcg" else "mg"
                                val maxVal = if (opioidType == "fentanyl" || opioidType == "remifentanil") 500f else 300f
                                
                                FormSlider(label = t("Alınan Opioid Miktarı", "Administered Opioid Dose"), value = opioidAmount, range = 5f..maxVal, unit = amountUnit) {
                                    opioidAmount = it
                                    runCalculation()
                                }
                            }

                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Result Display Card
                if (hasCalculated) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (calculator.slug == "map" && calculatedValue < 65.0) Color(0xFFFDF2F2) else Color(0xFFE8F5E9)
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (calculator.slug == "map" && calculatedValue < 65.0) CriticalRed else SafeGreen
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = t("HESAPLANAN SONUÇ", "CALCULATED RESULT"),
                                style = MaterialTheme.typography.labelLarge,
                                color = if (calculator.slug == "map" && calculatedValue < 65.0) CriticalRed else SafeGreen,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = resultText,
                                style = MaterialTheme.typography.displayLarge,
                                color = if (calculator.slug == "map" && calculatedValue < 65.0) CriticalRed else DeepNavy,
                                fontWeight = FontWeight.Bold,
                                fontSize = 36.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = interpretationText,
                                style = MaterialTheme.typography.bodyLarge,
                                color = TextPrimaryLight,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                    }
                    if (calculator.slug == "creatinine_clearance") {
                        Spacer(modifier = Modifier.height(16.dp))
                        RenalDosingSafetyCard(crCl = calculatedValue, currentLanguage = currentLanguage)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Critical Cautions Card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDE7)),
                border = BorderStroke(1.dp, AlertAmber.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = AlertAmber, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = t("Klinik Uyarı", "Clinical Warning"),
                            fontWeight = FontWeight.Bold,
                            color = AlertAmber,
                            fontSize = 13.sp
                        )
                        Text(
                            text = t(calculator.warningTr ?: "Hesaplanan değerler yardımcı niteliktedir. Tedavi kararını klinik değerlendirmeler doğrultusunda veriniz."),
                            color = TextPrimaryLight,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contextual AI Button
            Button(
                onClick = {
                    val textVal = resultText
                    val interp = interpretationText
                    val title = if (currentLanguage == "tr") calculator.titleTr else (calculator.titleEn ?: calculator.titleTr)
                    val contextInfo = if (currentLanguage == "tr") {
                        "Merhaba, '${title}' hesaplayıcısını kullandım. Sonuç: '${textVal}' (${interp}). Bu sonuç ve hasta yönetimi hakkında klinik değerlendirme yapmak istiyorum."
                    } else {
                        "Hello, I used the '${title}' calculator. Result: '${textVal}' (${interp}). I would like to discuss this result and patient management."
                    }
                    onAskAiContext(contextInfo)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SoftGold, contentColor = DeepNavy),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(t("Bu Sonucu AI ile Tartış", "Discuss this Result with AI"), fontWeight = FontWeight.Bold)
            }

            // Export Anonymous calculation reports (Phase 2 Action Buttons)
            Spacer(modifier = Modifier.height(12.dp))
            
            val isTurkish = currentLanguage == "tr"
            val formattedInputs = when (calculator.slug) {
                "asa_ps" -> t("• Seçilen Sınıf: $selectedAsaClass\n• Acil Durum Modifikatörü (E): ${if (isEmergencyModifier) "Evet" else "Hayır"}", "• Selected Class: $selectedAsaClass\n• Emergency Modifier (E): ${if (isEmergencyModifier) "Yes" else "No"}")
                "npo_fasting" -> {
                    val typeStr = when (selectedNpoIntakeType) {
                        "clear_liquid" -> t("Berrak Sıvılar", "Clear Liquids")
                        "breast_milk" -> t("Anne Sütü", "Breast Milk")
                        "infant_formula" -> t("Bebek Maması", "Infant Formula")
                        "non_human_milk" -> t("Hayvansal Süt", "Non-human Milk")
                        "light_meal" -> t("Hafif Yemek", "Light Meal")
                        "heavy_meal" -> t("Ağır Yemek", "Heavy/Fatty Meal")
                        else -> selectedNpoIntakeType
                    }
                    val flags = mutableListOf<String>()
                    if (npoDiabetes) flags.add(t("Diyabet", "Diabetes"))
                    if (npoGastroparesis) flags.add(t("Gastroparezi", "Gastroparesis"))
                    if (npoGerd) flags.add(t("GÖRH", "GERD"))
                    if (npoObesity) flags.add(t("Obezite", "Obesity"))
                    if (npoOpioids) flags.add(t("Opioid Kullanımı", "Opioid Use"))
                    if (npoBowelObstruction) flags.add(t("Bağırsak Tıkanıklığı", "Bowel Obstruction"))
                    if (npoPregnancy) flags.add(t("Gebelik", "Pregnancy"))
                    if (npoGlp1Weekly) flags.add(t("Haftalık GLP-1", "Weekly GLP-1"))
                    if (npoGlp1Daily) flags.add(t("Günlük GLP-1", "Daily GLP-1"))
                    if (npoGlp1GiEscalation) flags.add(t("GLP-1 Gİ Semptom Artışı", "GLP-1 GI Escalation"))
                    val flagsStr = if (flags.isNotEmpty()) flags.joinToString(", ") else t("Yok", "None")
                    t("• Alınan Gıda: $typeStr\n• Son Alım: ${String.format(Locale.US, "%02d:%02d", lastIntakeHour, lastIntakeMinute)}\n• Planlanan Ameliyat: ${String.format(Locale.US, "%02d:%02d", plannedSurgeryHour, plannedSurgeryMinute)}\n• Hasta Grubu: ${if (selectedNpoPatientGroup == "pediatric") "Pediatrik" else "Yetişkin"}\n• Eşlik Eden Riskler: $flagsStr",
                      "• Intake Type: $typeStr\n• Last Intake: ${String.format(Locale.US, "%02d:%02d", lastIntakeHour, lastIntakeMinute)}\n• Planned Surgery: ${String.format(Locale.US, "%02d:%02d", plannedSurgeryHour, plannedSurgeryMinute)}\n• Patient Group: ${if (selectedNpoPatientGroup == "pediatric") "Pediatric" else "Adult"}\n• Concomitant Risks: $flagsStr")
                }
                "difficult_airway" -> {
                    val checklists = mutableListOf<String>()
                    if (airwayPrevDifficult) checklists.add(t("Zor Havayolu Öyküsü", "History of Difficult Airway"))
                    if (airwayOsa) checklists.add(t("Uyku Apnesi (OSA)", "Sleep Apnea (OSA)"))
                    if (airwayObese) checklists.add(t("Obezite", "Obesity"))
                    if (airwayBeard) checklists.add(t("Sakal", "Beard"))
                    if (airwayEdentulous) checklists.add(t("Dişsizlik", "Edentulous"))
                    if (airwayCervicalLimit) checklists.add(t("Boyun Kısıtlılığı", "Cervical Spine Limitation"))
                    if (airwayPregnancy) checklists.add(t("Gebelik", "Pregnancy"))
                    if (airwayPathology) checklists.add(t("Tümör/Patoloji", "Airway Pathology/Tumor"))
                    val checklistsStr = if (checklists.isNotEmpty()) checklists.joinToString(", ") else t("Yok", "None")
                    t("• Mallampati: Sınıf $selectedMallampati\n• Ağız Açıklığı: $mouthOpeningCm cm\n• Tiromental Mesafe: $thyromentalDistanceCm cm\n• Boyun Hareketi Kısıtlı: ${if (isNeckMobilityLimited) "Evet" else "Hayır"}\n• Üst Dudak Isırma Sınıfı: $selectedUpperLipBiteClass\n• Ek Riskler: $checklistsStr",
                      "• Mallampati: Class $selectedMallampati\n• Mouth Opening: $mouthOpeningCm cm\n• Thyromental Distance: $thyromentalDistanceCm cm\n• Neck Mobility Limited: ${if (isNeckMobilityLimited) "Yes" else "No"}\n• Upper Lip Bite Class: $selectedUpperLipBiteClass\n• Co-markers: $checklistsStr")
                }
                "ebv_abl" -> {
                    t("• Hasta Ağırlığı: $inputVal1 kg\n• Başlangıç Hct (Hct0): $inputVal2%\n• Hedef/Minimum Hct (Hct1): $inputVal3%\n• Hasta Kategorisi: ${TranslationHelper.translate(selectedCategory, currentLanguage)}",
                      "• Weight: $inputVal1 kg\n• Initial Hct (Hct0): $inputVal2%\n• Target/Minimum Hct (Hct1): $inputVal3%\n• Patient Class: ${TranslationHelper.translate(selectedCategory, currentLanguage)}")
                }
                "maintenance_421" -> {
                    t("• Hasta Kilosu: $inputVal1 kg", "• Patient Weight: $inputVal1 kg")
                }
                "pediatric_ett" -> {
                    t("• Yaş: $ettAgeYears yaş\n• Kaf Durumu: ${if (ettCuffed) "Kaf'lı" else "Kaf'sız"}", "• Age: $ettAgeYears years\n• Cuff Status: ${if (ettCuffed) "Cuffed" else "Uncuffed"}")
                }
                "infusion_vasopressors" -> {
                    val drugStr = when (selectedInfusionDrug) {
                        "noradrenalin" -> t("Noradrenalin", "Norepinephrine")
                        "adrenalin" -> t("Adrenalin", "Epinephrine")
                        "dopamin" -> t("Dopamin", "Dopamine")
                        "dobutamin" -> t("Dobutamin", "Dobutamine")
                        "nitrogliserin" -> t("Nitrogliserin", "Nitroglycerin")
                        else -> selectedInfusionDrug
                    }
                    t("• Seçilen İlaç: $drugStr\n• Hasta Ağırlığı: $infusionWeightKg kg\n• Şırıngadaki Etkin Madde: $infusionSyringeMg mg\n• Şırınga Hacmi: $infusionSyringeMl mL\n• Hedef Doz: $infusionTargetDose mcg/kg/dk",
                      "• Selected Drug: $drugStr\n• Patient Weight: $infusionWeightKg kg\n• Active Substance in Syringe: $infusionSyringeMg mg\n• Syringe Volume: $infusionSyringeMl mL\n• Target Dose: $infusionTargetDose mcg/kg/min")
                }
                "regional_mixtures" -> {
                    val mixStr = when (selectedMixtureTab) {
                        "labor_epidural" -> t("Doğum Epidurali (Labor Epidural)", "Labor Epidural")
                        "postop_epidural" -> t("Postoperatif Epidural", "Postoperative Epidural")
                        "spinal_caesarean" -> t("Sezaryen Spinal Anestezi", "Caesarean Spinal")
                        else -> selectedMixtureTab
                    }
                    t("• Seçilen Karışım Protokolü: $mixStr", "• Selected Mixture Protocol: $mixStr")
                }
                "dantrolene_rescue" -> {
                    t("• Hasta Kilosu: $dantroleneWeightKg kg", "• Patient Weight: $dantroleneWeightKg kg")
                }
                "lipid_rescue" -> {
                    t("• Hasta Kilosu: $lipidWeightKg kg", "• Patient Weight: $lipidWeightKg kg")
                }
                "apfel_score" -> {
                    t("• Kadın Cinsiyet: ${if (apfelFemale) "Evet" else "Hayır"}\n• Sigara Kullanmıyor: ${if (apfelNonSmoker) "Evet" else "Hayır"}\n• PONV / Taşıt Tutması Öyküsü: ${if (apfelHistoryPONV) "Evet" else "Hayır"}\n• Postoperatif Opioid Planı: ${if (apfelPostOpOpioids) "Evet" else "Hayır"}",
                      "• Female Gender: ${if (apfelFemale) "Yes" else "No"}\n• Non-Smoker: ${if (apfelNonSmoker) "Yes" else "No"}\n• History of PONV/Motion Sickness: ${if (apfelHistoryPONV) "Yes" else "No"}\n• Postoperative Opioid Planned: ${if (apfelPostOpOpioids) "Yes" else "No"}")
                }
                "aldrete_score" -> {
                    t("• Aktivite: $aldreteActivity/2\n• Solunum: $aldreteRespiration/2\n• Dolaşım (Kan Basıncı): $aldreteCirculation/2\n• Bilinç: $aldreteConsciousness/2\n• O2 Saturasyonu: $aldreteO2Sat/2",
                      "• Activity: $aldreteActivity/2\n• Respiration: $aldreteRespiration/2\n• Circulation: $aldreteCirculation/2\n• Consciousness: $aldreteConsciousness/2\n• O2 Saturation: $aldreteO2Sat/2")
                }
                "rcri_score" -> {
                    val items = mutableListOf<String>()
                    if (rcriHighRiskSurgery) items.add(t("Yüksek Riskli Cerrahi", "High-risk Surgery"))
                    if (rcriIschemicHeart) items.add(t("İskemik Kalp Hastalığı", "Ischemic Heart Disease"))
                    if (rcriHeartFailure) items.add(t("Kalp Yetmezliği", "Heart Failure"))
                    if (rcriCerebrovascular) items.add(t("Serebrovasküler Hastalık", "Cerebrovascular Disease"))
                    if (rcriInsulin) items.add(t("İnsülin Tedavisi", "Insulin Treatment"))
                    if (rcriCreatinine) items.add(t("Kreatinin > 2.0 mg/dL", "Creatinine > 2.0 mg/dL"))
                    val itemsStr = if (items.isNotEmpty()) items.joinToString(", ") else t("Yok", "None")
                    t("• Seçilen Risk Faktörleri: $itemsStr", "• Selected Risk Factors: $itemsStr")
                }
                "stop_bang_score" -> {
                    val items = mutableListOf<String>()
                    if (stopSnoring) items.add(t("Horlama", "Snoring"))
                    if (stopTiredness) items.add(t("Gündüz Yorgunluğu", "Daytime Tiredness"))
                    if (stopApnea) items.add(t("Tanıklı Apne", "Observed Apnea"))
                    if (stopPressure) items.add(t("Hipertansiyon", "Hypertension"))
                    if (stopBmi) items.add(t("BMI > 35 kg/m²", "BMI > 35 kg/m²"))
                    if (stopAge) items.add(t("Yaş > 50", "Age > 50"))
                    if (stopNeck) items.add(t("Boyun Çevresi > 40 cm", "Neck Circumference > 40 cm"))
                    if (stopGender) items.add(t("Erkek Cinsiyet", "Male Gender"))
                    val itemsStr = if (items.isNotEmpty()) items.joinToString(", ") else t("Yok", "None")
                    t("• Pozitif Kriterler: $itemsStr", "• Positive Criteria: $itemsStr")
                }
                "la_max_dose" -> {
                    val agentStr = when (laSelectedAgent) {
                        "lidocaine" -> t("Lidokain", "Lidocaine")
                        "bupivacaine" -> t("Bupivakain", "Bupivacaine")
                        "ropivacaine" -> t("Ropivakain", "Ropivacaine")
                        "levobupivacaine" -> t("Levobupivakain", "Levobupivacaine")
                        "prilocaine" -> t("Prilokain", "Prilocaine")
                        else -> laSelectedAgent
                    }
                    t("• Seçilen Lokal Anestezik: $agentStr\n• Hasta Ağırlığı: $laWeightKg kg\n• Epinefrin Katkılı: ${if (laWithEpinephrine) "Evet" else "Hayır"}\n• Çözelti Derişimi: $laConcentrationPercent%",
                      "• Selected Local Anesthetic: $agentStr\n• Patient Weight: $laWeightKg kg\n• with Epinephrine: ${if (laWithEpinephrine) "Yes" else "No"}\n• Solution Concentration: $laConcentrationPercent%")
                }
                "sugammadex_dose" -> {
                    val tofStr = when (sugammadexTOFLevel) {
                        "moderate" -> t("Orta Dereceli Blok (TOF 2-4)", "Moderate Block (TOF 2-4)")
                        "deep" -> t("Derin Blok (PTC 1-2)", "Deep Block (PTC 1-2)")
                        "immediate" -> t("Acil Geri Çevirme (İndüksiyondan 3 dk sonra)", "Immediate Reversal (3 min post-induction)")
                        else -> sugammadexTOFLevel
                    }
                    t("• Hasta Kilosu: $sugammadexWeightKg kg\n• TOF Durumu: $tofStr", "• Patient Weight: $sugammadexWeightKg kg\n• TOF Status: $tofStr")
                }
                "ibw_tidal_volume" -> {
                    t("• Hasta Boyu: $tvHeightCm cm\n• Cinsiyet: ${if (tvSex == "male") "Erkek" else "Kadın"}\n• Hedef Akciğer Hacmi (mL/kg): $tvTargetMlPerKg",
                      "• Patient Height: $tvHeightCm cm\n• Gender: ${if (tvSex == "male") "Male" else "Female"}\n• Target Lung Volume (mL/kg): $tvTargetMlPerKg")
                }
                "creatinine_clearance" -> {
                    t("• Yaş: $gfrAgeYears\n• Hasta Kilosu: $gfrWeightKg kg\n• Cinsiyet: ${if (gfrSex == "male") "Erkek" else "Kadın"}\n• Serum Kreatinin: $gfrCreatinine mg/dL",
                      "• Age: $gfrAgeYears\n• Patient Weight: $gfrWeightKg kg\n• Gender: ${if (gfrSex == "male") "Male" else "Female"}\n• Serum Creatinine: $gfrCreatinine mg/dL")
                }
                "opioid_equivalence" -> {
                    val drugStr = when (opioidType) {
                        "fentanyl" -> t("Fentanil (mcg)", "Fentanyl (mcg)")
                        "remifentanil" -> t("Remifentanil (mcg)", "Remifentanil (mcg)")
                        "morphine" -> t("Morfin (mg)", "Morphine (mg)")
                        "pethidine" -> t("Petidin (mg)", "Pethidine (mg)")
                        "tramadol" -> t("Tramadol (mg)", "Tramadol (mg)")
                        else -> opioidType
                    }
                    t("• Seçilen Opioid: $drugStr\n• Giriş Miktarı: $opioidAmount", "• Selected Opioid: $drugStr\n• Input Amount: $opioidAmount")
                }
                else -> {
                    val inputs = mutableListOf<String>()
                    if (inputVal1.isNotEmpty()) inputs.add(t("• Değer 1: $inputVal1", "• Value 1: $inputVal1"))
                    if (inputVal2.isNotEmpty()) inputs.add(t("• Değer 2: $inputVal2", "• Value 2: $inputVal2"))
                    if (inputVal3.isNotEmpty()) inputs.add(t("• Değer 3: $inputVal3", "• Value 3: $inputVal3"))
                    if (inputs.isNotEmpty()) inputs.joinToString("\n") else t("• Girdiler: Standart", "• Inputs: Standard")
                }
            }

            val formulaText = when (calculator.slug) {
                "opioid_equivalence" -> t("Klinik Eşdeğerlik Dönüşüm Katsayıları", "Clinical Equivalence Conversion Coefficients")
                "infusion_vasopressors" -> t("mL/saat = (Doz (mcg/kg/dk) * Kilo * 60) / Derişim (mcg/mL)", "mL/hour = (Dose (mcg/kg/min) * Weight * 60) / Concentration (mcg/mL)")
                "ibw_tidal_volume" -> t("PBW (Erkek) = 50.0 + 0.91*(Boy - 152.4)\nPBW (Kadın) = 45.5 + 0.91*(Boy - 152.4)", "PBW (Male) = 50.0 + 0.91(Height - 152.4)\nPBW (Female) = 45.5 + 0.91(Height - 152.4)")
                "sugammadex_dose" -> t("Doz = Kilo * (2, 4, veya 16 mg/kg)", "Dose = Weight × (2, 4, or 16 mg/kg)")
                else -> t(calculator.formula ?: "Gelişmiş Formül", TranslationHelper.translate(calculator.formula ?: "Gelişmiş Formül", currentLanguage))
            }

            val formulaExpText = when (calculator.slug) {
                "opioid_equivalence" -> t(
                    "IV Morfin eşdeğerlik katsayıları: Fentanil x 0.1, Remifentanil x 0.1, Tramadol x 0.1, Petidin x 0.13.",
                    "IV Morphine equivalence coefficients: Fentanyl x 0.1, Remifentanil x 0.1, Tramadol x 0.1, Pethidine x 0.13."
                )
                else -> t(calculator.formulaExplanationTr ?: "Klinik standartlara uygun matematiksel modelleme.", TranslationHelper.translate(calculator.formulaExplanationTr ?: "Klinik standartlara uygun matematiksel modelleme.", currentLanguage))
            }

            val warningText = if (!calculator.warningTr.isNullOrEmpty()) {
                t("⚠️ " + calculator.warningTr, "⚠️ " + TranslationHelper.translate(calculator.warningTr ?: "", currentLanguage))
            } else ""

            val referencesText = if (calculator.references.isNotEmpty()) {
                calculator.references.mapIndexed { index, ref -> "${index + 1}. $ref" }.joinToString("\n")
            } else ""

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = {
                        val shareText = """
                            [AnesthesiaBriefs] ${t(calculator.titleTr, calculator.titleEn)}
                            --------------------------------------------------
                            ${t("SEÇİLEN SEÇENEKLER & PARAMETRELER", "SELECTED OPTIONS & PARAMETERS")}:
                            $formattedInputs
                            
                            --------------------------------------------------
                            ${t("SONUÇ", "RESULT")}: $resultText
                            
                            ${t("ÖNERİLER & KLİNİK YORUM", "RECOMMENDATIONS & CLINICAL INTERPRETATION")}:
                            $interpretationText
                            ${if (warningText.isNotEmpty()) "\n--------------------------------------------------\n$warningText" else ""}
                            --------------------------------------------------
                            ${t("FORMÜL & KLİNİK AÇIKLAMA", "FORMULA & CLINICAL EXPLANATION")}:
                            $formulaText
                            
                            $formulaExpText
                            ${if (referencesText.isNotEmpty()) "\n--------------------------------------------------\n" + t("AKADEMİK KAYNAKLAR", "ACADEMIC REFERENCES") + ":\n" + referencesText else ""}
                            --------------------------------------------------
                            ${t("Güvenli ve anonim olarak paylaşılmıştır.", "Shared securely and anonymously.")}
                        """.trimIndent()
                        
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, t(calculator.titleTr, calculator.titleEn))
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(shareIntent, t("Raporu Paylaş", "Share Report")))
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(44.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepNavy),
                    border = BorderStroke(1.dp, BorderSand)
                ) {
                    Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(t("Anonim Paylaş", "Share Anonymously"), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                OutlinedButton(
                    onClick = {
                        val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
                        val webView = WebView(context)
                        val htmlContent = """
                            <html>
                            <head>
                                <meta charset="utf-8">
                                <style>
                                    body { font-family: sans-serif; padding: 20px; color: #1E293B; background: #FAF9F6; line-height: 1.5; }
                                    h1 { color: #0B1E36; border-bottom: 2px solid #E2E8F0; padding-bottom: 10px; margin-bottom: 20px; }
                                    .section { margin-top: 20px; background: #FFFFFF; padding: 15px; border-radius: 8px; border: 1px solid #E2E8F0; }
                                    .label { font-weight: bold; color: #0B1E36; border-bottom: 1px solid #F1F5F9; padding-bottom: 4px; margin-bottom: 8px; font-size: 1.1em; }
                                    .value { font-size: 1.2em; color: #0EA5E9; font-weight: bold; }
                                    .pre-text { white-space: pre-wrap; color: #334155; margin-top: 5px; font-family: sans-serif; }
                                    .warning-box { background: #FDF2F2; border: 1.5px solid #F87171; color: #991B1B; padding: 12px; border-radius: 8px; font-weight: bold; margin-top: 15px; }
                                    .footer { margin-top: 50px; font-size: 0.8em; color: #94A3B8; text-align: center; border-top: 1px solid #E2E8F0; padding-top: 10px; }
                                </style>
                            </head>
                            <body>
                                <h1>AnesthesiaBriefs - ${t("Klinik Rapor", "Clinical Report")}</h1>
                                
                                <div class="section">
                                    <div class="label">${t("Hesaplayıcı", "Calculator")}</div>
                                    <div class="value">${t(calculator.titleTr, calculator.titleEn)}</div>
                                </div>
                                
                                <div class="section">
                                    <div class="label">${t("Seçilen Seçenekler & Girdiler", "Selected Options & Inputs")}</div>
                                    <div class="pre-text">$formattedInputs</div>
                                </div>
                                
                                <div class="section">
                                    <div class="label">${t("Hesaplanan Sonuç", "Calculated Result")}</div>
                                    <div class="value">$resultText</div>
                                </div>
                                
                                <div class="section">
                                    <div class="label">${t("Öneriler & Klinik Yorum", "Recommendations & Clinical Interpretation")}</div>
                                    <div class="pre-text">$interpretationText</div>
                                </div>
                                
                                ${if (warningText.isNotEmpty()) """
                                <div class="warning-box">
                                    $warningText
                                </div>
                                """ else ""}
                                
                                <div class="section">
                                    <div class="label">${t("Formül & Klinik Açıklama", "Formula & Clinical Explanation")}</div>
                                    <div style="font-weight: bold; color: #0B1E36;">$formulaText</div>
                                    <div class="pre-text" style="margin-top: 8px;">$formulaExpText</div>
                                </div>
                                
                                ${if (referencesText.isNotEmpty()) """
                                <div class="section">
                                    <div class="label">${t("Akademik Kaynaklar", "Academic References")}</div>
                                    <div class="pre-text">$referencesText</div>
                                </div>
                                """ else ""}
                                
                                <div class="footer">
                                    Generated by AnesthesiaBriefs. Confirmed Clinical Decision Support Tool.
                                </div>
                            </body>
                            </html>
                        """.trimIndent()
                        
                        webView.webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                val jobName = "${t(calculator.titleTr, calculator.titleEn)} Report"
                                val printAdapter = webView.createPrintDocumentAdapter(jobName)
                                printManager.print(jobName, printAdapter, PrintAttributes.Builder().build())
                            }
                        }
                        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null)
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(44.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepNavy),
                    border = BorderStroke(1.dp, BorderSand)
                ) {
                    Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(t("PDF Rapor Al", "Get PDF Report"), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Collapsible Formula Section
            CollapsibleSection(
                title = t("Formül & Klinik Açıklama", "Formula & Clinical Explanation"),
                isExpanded = isFormulaExpanded,
                onToggle = { isFormulaExpanded = !isFormulaExpanded }
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    val formulaVal = when (calculator.slug) {
                        "opioid_equivalence" -> t("Klinik Eşdeğerlik Dönüşüm Katsayıları", "Clinical Equivalence Conversion Coefficients")
                        "infusion_vasopressors" -> t("mL/saat = (Doz (mcg/kg/dk) * Kilo * 60) / Derişim (mcg/mL)", "mL/hour = (Dose (mcg/kg/min) * Weight * 60) / Concentration (mcg/mL)")
                        "ibw_tidal_volume" -> t("PBW (Erkek) = 50.0 + 0.91*(Boy - 152.4)\nPBW (Kadın) = 45.5 + 0.91*(Boy - 152.4)", "PBW (Male) = 50.0 + 0.91(Height - 152.4)\nPBW (Female) = 45.5 + 0.91(Height - 152.4)")
                        "sugammadex_dose" -> t("Doz = Kilo * (2, 4, veya 16 mg/kg)", "Dose = Weight × (2, 4, or 16 mg/kg)")
                        else -> t(calculator.formula ?: "Gelişmiş Formül", TranslationHelper.translate(calculator.formula ?: "Gelişmiş Formül", currentLanguage))
                    }
                    Text(
                        text = formulaVal,
                        style = MaterialTheme.typography.titleLarge,
                        color = ClinicalTeal,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val formulaExp = when (calculator.slug) {
                        "opioid_equivalence" -> t(
                            "IV Morfin eşdeğerlik katsayıları: Fentanil x 0.1, Remifentanil x 0.1, Tramadol x 0.1, Petidin x 0.13.",
                            "IV Morphine equivalence coefficients: Fentanyl x 0.1, Remifentanil x 0.1, Tramadol x 0.1, Pethidine x 0.13."
                        )
                        else -> t(calculator.formulaExplanationTr ?: "Klinik standartlara uygun matematiksel modelleme.", TranslationHelper.translate(calculator.formulaExplanationTr ?: "Klinik standartlara uygun matematiksel modelleme.", currentLanguage))
                    }
                    Text(
                        text = formulaExp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextPrimaryLight
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Collapsible Sources Section
            CollapsibleSection(
                title = t("Akademik Kaynaklar", "Academic References"),
                isExpanded = isSourcesExpanded,
                onToggle = { isSourcesExpanded = !isSourcesExpanded }
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    if (calculator.references.isNotEmpty()) {
                        calculator.references.forEachIndexed { index, ref ->
                            Text(
                                text = "${index + 1}. $ref",
                                color = TextPrimaryLight,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    } else {
                        Text(
                            text = t("Bu hesaplayıcı için doğrulanmış kılavuz referansı bulunmamaktadır.", "No verified guidelines or references are available for this calculator."),
                            color = TextSecondaryLight,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CohortItem(label: String, text: String) {
    Column {
        Text(text = label, fontWeight = FontWeight.Bold, color = ClinicalTeal, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = text, fontSize = 13.sp, color = TextPrimaryLight)
    }
}

@Composable
fun CoagRowItem(label: String, value: String, valueColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 12.sp, color = TextPrimaryLight, fontWeight = FontWeight.SemiBold)
        Text(value, fontSize = 12.sp, color = valueColor, fontWeight = FontWeight.Bold)
    }
}

data class CoagDrugInfo(
    val name: String,
    val pauseBefore: String,
    val catheterManagement: String,
    val restartAfter: String,
    val clinicalNotes: String
)

@Composable
fun FormSlider(
    label: String,
    value: Double,
    range: ClosedFloatingPointRange<Float>,
    unit: String,
    onValueChange: (Double) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.bodyLarge, color = TextPrimaryLight, fontWeight = FontWeight.SemiBold)
            Text("${String.format(Locale.US, "%.1f", value)} $unit", style = MaterialTheme.typography.bodyLarge, color = DeepNavy, fontWeight = FontWeight.Bold)
        }
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toDouble()) },
            valueRange = range,
            colors = SliderDefaults.colors(
                thumbColor = ClinicalTeal,
                activeTrackColor = ClinicalTeal,
                inactiveTrackColor = BorderSand
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RowScope.CategoryButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) DeepNavy else BorderSand.copy(alpha = 0.3f),
            contentColor = if (isSelected) Color.White else TextPrimaryLight
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.weight(1f)
    ) {
        Text(text, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun RowScope.ScoreBtn(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) DeepNavy else BorderSand.copy(alpha = 0.3f),
            contentColor = if (isSelected) Color.White else TextPrimaryLight
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),
        modifier = Modifier.weight(1f)
    ) {
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CollapsibleSection(
    title: String,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Linen),
        border = BorderStroke(1.dp, BorderSand),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggle)
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, style = MaterialTheme.typography.titleLarge, color = DeepNavy, fontWeight = FontWeight.SemiBold)
                Icon(
                    if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = DeepNavy
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Box(modifier = Modifier.fillMaxWidth().background(Color.White)) {
                    content()
                }
            }
        }
    }
}

@Composable
fun PediatricDoseItem(
    name: String,
    mgDose: String,
    mlDose: String,
    preparation: String,
    currentLanguage: String = "tr"
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name, fontWeight = FontWeight.Bold, color = DeepNavy, fontSize = 13.sp, modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(mgDose, fontWeight = FontWeight.Bold, color = ClinicalTeal, fontSize = 12.sp)
                Text(mlDose, fontWeight = FontWeight.SemiBold, color = TextPrimaryLight, fontSize = 11.sp)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        val label = if (currentLanguage == "tr") "Uygulama/Hazırlama" else "Administration/Preparation"
        Text(
            text = "$label: $preparation",
            fontSize = 11.sp,
            color = TextSecondaryLight,
            lineHeight = 15.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

data class MixtureRecipe(
    val title: String,
    val recipe: String,
    val totalVol: String,
    val rates: String,
    val initialDose: String,
    val publications: List<String>
)

@Composable
fun RenalDosingSafetyCard(crCl: Double, currentLanguage: String) {
    val isTr = currentLanguage == "tr"
    fun t(tr: String, en: String): String = if (isTr) tr else en

    val severityLevel = when {
        crCl >= 90.0 -> "normal"
        crCl >= 60.0 -> "mild"
        crCl >= 30.0 -> "moderate"
        crCl >= 15.0 -> "severe"
        else -> "failure"
    }

    val severityLabel = when (severityLevel) {
        "normal" -> t("Böbrek Fonksiyonu Normal (GFR >= 90)", "Kidney Function Normal (GFR >= 90)")
        "mild" -> t("Hafif Böbrek Yetmezliği (GFR 60-89)", "Mild Renal Impairment (GFR 60-89)")
        "moderate" -> t("Orta Böbrek Yetmezliği (GFR 30-59)", "Moderate Renal Impairment (GFR 30-59)")
        "severe" -> t("Ağır Böbrek Yetmezliği (GFR 15-29)", "Severe Renal Impairment (GFR 15-29)")
        else -> t("🚨 Terminal Böbrek Yetmezliği / Diyaliz (GFR < 15)", "🚨 End-Stage Renal Failure / Dialysis (GFR < 15)")
    }

    val cardBgColor = when (severityLevel) {
        "normal" -> Color(0xFFE8F5E9)
        "mild" -> Color(0xFFE8F5E9)
        "moderate" -> Color(0xFFFFFDE7)
        else -> Color(0xFFFDF2F2)
    }

    val cardBorderColor = when (severityLevel) {
        "normal" -> SafeGreen
        "mild" -> SafeGreen
        "moderate" -> AlertAmber
        else -> CriticalRed
    }

    val warningDrugs = mutableListOf<Triple<String, String, String>>()

    if (crCl < 30.0) {
        warningDrugs.add(Triple(
            "Sugammadex (Bridion)",
            "ÖNERİLMEZ. Ağır renal yetmezlikte (CrCl < 30 mL/dk) birikim riski nedeniyle kullanımı önerilmemektedir. Alternatif olarak Neostigmin düşünülebilir.",
            "NOT RECOMMENDED. Avoid use in severe renal impairment (CrCl < 30 mL/min) due to accumulation of the drug-complex. Consider Neostigmine as alternative."
        ))
    } else if (crCl < 60.0) {
        warningDrugs.add(Triple(
            "Sugammadex (Bridion)",
            "GÜVENLİ (Doz Değişimi Yok). Rutin dozlar (2-4 mg/kg) uygulanabilir ancak klirens süresi normal bireylere kıyasla uzayabilir.",
            "SAFE (No dose adjustment). Standard doses (2-4 mg/kg) can be given, though clearance of the complex may be prolonged."
        ))
    }

    if (crCl < 30.0) {
        warningDrugs.add(Triple(
            "Morphine (Morfin)",
            "🚨 KAÇININ VEYA AZALTIN (%50-75). Aktif metaboliti Morfin-6-Glukuronid böbrekten atılır; yetmezlik durumunda birikerek ölümcül geç solunum depresyonuna yol açar.",
            "🚨 AVOID OR REDUCE (50-75%). Active metabolite Morphine-6-Glucuronide is renally cleared; severe accumulation risks delayed, fatal respiratory depression."
        ))
    } else if (crCl < 60.0) {
        warningDrugs.add(Triple(
            "Morphine (Morfin)",
            "DİKKAT (Doz %25-50 Azaltılmalıdır). Doz aralıkları açılmalı ve solunum hızı yakından izlenmelidir.",
            "CAUTION (Reduce dose by 25-50%). Extend dosing intervals and monitor respiratory status closely."
        ))
    }

    warningDrugs.add(Triple(
        "Cisatracurium (Sisatrakuryum)",
        "✅ GÜVENLİ (Doz Ayarı Gerekmez). Hofmann eliminasyonu (plazmada spontan kimyasal parçalanma) ile atıldığı için böbrek fonksiyonundan tamamen bağımsızdır. Renal yetmezlikte ilk tercihtir.",
        "✅ SAFE (No adjustment). Hofmann elimination (spontaneous chemical breakdown in plasma) makes it completely independent of renal clearance. Preferred choice."
    ))

    if (crCl < 30.0) {
        warningDrugs.add(Triple(
            "Rocuronium & Vecuronium",
            "DİKKAT (Etki Süresi Uzayabilir). Klinik etki süresi %30-50 oranında uzar. İdame dozları azaltılmalı ve TOF ile mutlaka monitörize edilmelidir.",
            "CAUTION (Prolonged Duration). Clinical effect duration is prolonged by 30-50%. Reduce maintenance doses and monitor strictly with TOF."
        ))
    } else if (crCl < 60.0) {
        warningDrugs.add(Triple(
            "Rocuronium & Vecuronium",
            "HAFİF UZAMA. Blok etki derinliği ve süresinde hafif artış beklenebilir; TOF kılavuzluğunda idame yapılması önerilir.",
            "MILD PROLONGATION. A mild increase in duration is possible; titration based on TOF monitoring is recommended."
        ))
    }

    if (crCl < 30.0) {
        warningDrugs.add(Triple(
            "NSAIDs (Ketorolak, Diklofenak, İbuprofen vb.)",
            "🚨 KONTRENDİKE. Böbrek kan akımını azaltarak akut böbrek hasarı (AKI) riskini tetikler ve renal fonksiyonu tamamen bozabilir.",
            "🚨 CONTRAINDICATED. Decreases renal blood flow, predisposing the patient to Acute Injury (AKI) and worsening renal failure."
        ))
    } else if (crCl < 60.0) {
        warningDrugs.add(Triple(
            "NSAIDs (Ketorolak, Diklofenak, İbuprofen vb.)",
            "🚨 KAÇINILMALIDIR. Zorunlu hallerde en düşük efektif dozda, kısa süreli ve hidrasyon sağlanarak kullanılabilir.",
            "🚨 AVOID. If mandatory, use the lowest effective dose for the shortest duration, ensuring excellent hydration."
        ))
    }

    if (crCl < 30.0) {
        warningDrugs.add(Triple(
            "Cefazolin (Sefazolin)",
            "DİKKAT (Doz Ayarı). İntraoperatif tek profilaktik doz (2g veya 3g) normalde değiştirilmez. Ancak ameliyat >4 saat sürüp ek doz gerekecekse veya postoperatif doz sürdürülecekse doz aralıkları 12-24 saate açılmalıdır.",
            "CAUTION (Dosing Adjustment). Single intraoperative prophylactic dose (2g or 3g) is unchanged. If surgery >4h and re-dosing is needed, or for postop dosing, extend interval to 12-24h."
        ))
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBgColor),
        border = BorderStroke(1.dp, cardBorderColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocalHospital,
                    contentDescription = null,
                    tint = DeepNavy,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = t("BÖBREK YETMEZLİĞİ İLAÇ DOZ REHBERİ", "RENAL DRUG DOSING GUIDE"),
                    style = MaterialTheme.typography.labelLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = severityLabel,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DeepNavy
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = t(
                    "Bu hastada anestezi yönetiminde renal klirens ve birikim riski taşıyan ilaçlar için kılavuz önerileri aşağıdadır:",
                    "Guideline recommendations for anesthetic drugs with renal clearance or accumulation risk in this patient:"
                ),
                fontSize = 11.sp,
                color = TextPrimaryLight
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                warningDrugs.forEach { (drugName, trWarning, enWarning) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White.copy(alpha = 0.5f))
                            .border(1.dp, BorderSand.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = drugName,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = t(trWarning, enWarning),
                            fontSize = 11.sp,
                            color = TextPrimaryLight,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }
    }
}

