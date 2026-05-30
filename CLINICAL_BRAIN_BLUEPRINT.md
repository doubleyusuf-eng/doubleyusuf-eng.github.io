# Anesthesia Briefs - Clinical Brain & Architecture Blueprint
### Complete Development Archive & FlutterFlow Migration Specification

This document serves as the permanent clinical and technical archive of the **Anesthesia Briefs** project development. It compiles all design tokens, deterministic math formulas, offline seed databases, dynamic decision trees, safety guidelines, and complete step-by-step instructions to allow seamless continuation of coding sessions or immediate porting to **FlutterFlow** and **Supabase**.

---

## 1. Project Strategy & Core Architecture

The **Anesthesia Briefs** application is designed as an offline-first, safety-critical medical decision support system. The current reference implementation is built as a native Android application (**Target API 35**) using **Kotlin** and **Jetpack Compose**, adhering to **Clean Architecture** and **MVVM** design principles.

### Core Architectural Pillars
1. **100% Deterministic Calculations**: No AI or LLM is involved in any mathematical computations, dosing guidelines, or risk scoring. All equations are hardcoded locally in standard Kotlin routines and covered by automated unit tests.
2. **Offline-Resilient Operations**: Operating rooms (OR) and sterile environments suffer from zero internet connectivity. The clinical brain is pre-bundled with offline repositories for 12 key anesthesia drug monographs, step-by-step emergency algorithms, and reference datasets.
3. **Structured JSON AI Assistant**: When internet is available, the AI Assistant calls a secure proxy, cleans patient identifiers client-side (anonymization filters), and parses structured JSON responses into visual UI elements (Safety Badges, Red Flags, Escalation Cards) instead of plain text.
4. **"Warm Minimalist Clinical" Theme (Material 3)**: Tailored high-contrast interfaces designed for high readability in operating rooms under various lighting conditions.

---

## 2. Color Palette & Typography (Design Tokens)

The styling system implements the curated **Warm Minimalist Clinical** palette. In FlutterFlow or Vanilla CSS, these specific color hex codes should be registered:

| Token Name | Hex Code | Clinical Purpose / Description |
| :--- | :--- | :--- |
| **Deep Navy** | `#2C4A6E` | Primary brand color, headers, action buttons, highly professional. |
| **Clinical Teal** | `#3D7A8A` | Secondary theme color, positive highlights, safe action triggers. |
| **Soft Gold** | `#C9A961` | Tertiary accent, premium badging, high-value clinical indicators. |
| **Warm Sand** | `#FAF7F2` | Global background canvas color, light, relaxing, reduces eye strain. |
| **Linen** | `#EDE5DA` | Surface cards background, high contrast against Warm Sand. |
| **Border Sand** | `#D8CFC2` | Subtle outlines, card boundaries, separating elements cleanly. |
| **Critical Red** | `#C0392B` | High-visibility alerts, GCS entübasyon warnings, MAP < 65, LAST alarms. |
| **Alert Amber** | `#D4850A` | Medium cautions, NPO waiting timer, Mallampati Class III warnings. |
| **Safe Green** | `#2E7D56` | Safe statuses, NPO suitable clearance, low-risk airway classifications. |
| **Dark Navy** | `#1C2B3A` | Dark theme canvas background. |
| **Card Dark** | `#243447` | Dark theme card surface background. |

### Typography Guidelines
- **Font Family**: Inter, Roboto, or Outfit (Google Fonts). Default system serif/sans-serif default styles are avoided.
- **Header Styles**: Display sizes (32sp-36sp) with Bold weights for clinical result numbers. Title sizes (18sp-22sp) for sections.
- **Body & Captions**: Body text (13sp-15sp) for monographs and descriptions, Captions (11sp) for source references and cautions.

---

## 3. Deterministic Clinical Mathematics (Kotlin Equations)

All calculations are located in `MedicalCalculators.kt`. The exact mathematical equations implemented are:

### A. Body & Dosing
- **Body Mass Index (BMI)**:
  $$\text{BMI} = \frac{\text{Weight (kg)}}{\left(\text{Height (m)}\right)^2}$$
- **Body Surface Area (BSA - Mosteller Formula)**:
  $$\text{BSA} = \sqrt{\frac{\text{Weight (kg)} \times \text{Height (cm)}}{3600}}$$
- **Ideal Body Weight (IBW - Devine Formula)**:
  - Male: $50.0 + 0.9 \times (\text{Height in cm} - 152.4)$
  - Female: $45.5 + 0.9 \times (\text{Height in cm} - 152.4)$
- **Predicted Body Weight (PBW - ARDSNet)**:
  - Male: $50.0 + 0.91 \times (\text{Height in cm} - 152.4)$
  - Female: $45.5 + 0.91 \times (\text{Height in cm} - 152.4)$
- **Adjusted Body Weight (AdjBW)**:
  $$\text{AdjBW} = \text{IBW} + 0.4 \times (\text{Actual Weight} - \text{IBW})$$
- **Lean Body Weight (LBW - Janmahasatian Formula)**:
  - Male: $\frac{9270 \times \text{Weight (kg)}}{6680 + 216 \times \text{BMI}}$
  - Female: $\frac{9270 \times \text{Weight (kg)}}{8780 + 244 \times \text{BMI}}$

### B. Ventilation & Hemodynamics
- **Tidal Volume**:
  $$\text{Tidal Volume} = \text{PBW (kg)} \times \text{Target Volume (ml/kg)}$$
- **Mean Arterial Pressure (MAP)**:
  $$\text{MAP} = \frac{\text{SBP} + 2 \times \text{DBP}}{3}$$
- **Shock Index**:
  $$\text{Shock Index} = \frac{\text{Heart Rate (BPM)}}{\text{Systolic BP (mmHg)}}$$
- **Modified Shock Index**:
  $$\text{Modified Shock Index} = \frac{\text{Heart Rate (BPM)}}{\text{MAP (mmHg)}}$$

### C. Fluids & Blood
- **Estimated Blood Volume (EBV)**:
  $$\text{EBV} = \text{Weight (kg)} \times \text{Category Factor}$$
  - Category Factors: Adult Male = 70, Adult Female = 65, Child = 75, Infant = 80, Neonate = 85.
- **Allowable Blood Loss (ABL)**:
  $$\text{ABL} = \frac{\text{EBV} \times (\text{Initial Hb} - \text{Target Hb})}{\text{Initial Hb}}$$
- **Hourly Maintenance Rate (4-2-1 Rule)**:
  - Weight $\le$ 10 kg: $4 \times \text{Weight}$
  - Weight 10 to 20 kg: $40 + 2 \times (\text{Weight} - 10)$
  - Weight $>$ 20 kg: $60 + 1 \times (\text{Weight} - 20)$
- **Fluid Deficit**:
  $$\text{Fluid Deficit} = \text{Maintenance Rate} \times \text{Fasting Hours}$$

### D. Airway & Pediatric Dosing
- **Pediatric ETT Size Selector**:
  - Cuffed Tube ID: $\frac{\text{Age (years)}}{4} + 3.5$
  - Uncuffed Tube ID: $\frac{\text{Age (years)}}{4} + 4.0$
- **ETT Depth Selector**:
  - Formula 1: $\frac{\text{Age (years)}}{2} + 12\text{ cm}$
  - Formula 2: $\text{ETT ID} \times 3\text{ cm}$

### E. Risk & Safety Scores
- **Apfel Score (PONV Risk)**:
  - Score (0 to 4): +1 for Female, +1 for Non-Smoker, +1 for History of PONV/Motion Sickness, +1 for Postoperative Opioids.
  - Risk Percentages: 0 pt = 10%, 1 pt = 21%, 2 pt = 39%, 3 pt = 61%, 4 pt = 79%.
- **Glasgow Coma Scale (GCS)**:
  - GCS = Eye Open (1-4) + Verbal (1-5) + Motor (1-6).
  - GCS $\le$ 8 prompts critical warning: *"🚨 Ağır Kafa Travması - Hava yolu koruması (Entübasyon) düşünülmelidir!"*

---

## 4. Preoperative & Safety Tools (Phase 8 Integrations)

These newly added MVP clinical systems contain deep safety validation logic:

### A. ASA Physical Status (ASA PS) Sınıflaması
Visual classification aid detailing standard definitions and example cohorts across three distinct patient populations:
- **ASA I**: Healthy, non-smoking, minimal alcohol.
- **ASA II**: Mild systemic disease (controlled HT/DM, active smoker, mild asthma).
- **ASA III**: Severe systemic disease, functional limitations (uncontrolled DM/HT, morbid obesity, <3 months post MI/stroke/TIA, pacemaker).
- **ASA IV**: Constant threat to life (unstable angina, sepsis, ARDS, son evre renal diyaliz dışı diyaliz, diyaliz alanlar stabilse ASA III'tür).
- **ASA V**: Moribund patient not expected to survive 24 hours (ruptured aneurysm, massive trauma).
- **ASA VI**: Brain-dead donor.
- **Emergency modifier ("E")**: Toggles alert red status warnings warning that emergency status exponentially multiplies perioperative risk.

### B. Preoperative Fasting (NPO) Timer
Determines safe elective surgery timings based on food intake types:
- **Clear Liquids**: 2.0 hours.
- **Breast Milk**: 4.0 hours.
- **Infant Formula / Hayvansal Süt / Hafif Öğün**: 6.0 hours.
- **Ağır / Yağlı Öğün**: 8.0 hours or more.
- **Aspiration Risk flags**: Checks for gastroparesis, GERD, morbid obesity, opioid usage, bowel obstruction, and pregnancy. If any flag is checked OR the patient group is marked "Emergency", the status shifts to `high_risk_review_required`, displaying:
  *“🚨 ÖZEL DEĞERLENDİRME & RSI GEREKLİ. Risk faktörleri mevcuttur. Salt açlık sürelerine güvenilmemelidir. Hızlı ardışık indüksiyon ve aspirasyon profilaksisi düşünülmelidir.”*

### C. Difficult Airway Screening Checklist
Checklist aggregating physical indicators and patient factors:
- **Major Risk Factors**: Mallampati Class IV, Mouth Opening (interincisor) < 3.0 cm, Thyromental Distance (TMD) < 6.0 cm, airway mass/tumors.
- **Minor Risk Factors**: Mallampati Class III, TMD < 6.5 cm, limited neck mobility, STOP-Bang score $\ge$ 5, previous difficult airway history, obesity, beard, edentulous, cervical limitation, pregnancy.
- **Risk Level Outputs**:
  - **High**: Red badge, suggests VL + Fiberoptic Bronchoscopy (FOB) ready, backup clinician, and awake FOI consideration.
  - **Caution**: Amber badge, suggests alternative blades (Miller, Macintosh), bougie/stylus, and SGA standby.
  - **Low**: Green badge, standard preparation.
- **Risk Domains Mapping**:
  - *Mask ventilation, Laryngoscopy/Intubation, SGA placement, and Front-of-neck access*.

---

## 5. ASRA Anticoagulant Reference Table (Kılavuz Veri Seti)

A highly filterable quick reference mapping antithrombotic pause guidelines prior to performing neuraxial blocks:

| Medication | Neuraxial Pause (Blok Öncesi) | Catheter Management (Kateter Çekme) | Restart Interval (Tekrar Başlama) | Clinical Notes |
| :--- | :--- | :--- | :--- | :--- |
| **Aspirin (Düşük Doz)** | 0 Gün (Kesmeye gerek yok) | Güvenle takılabilir/çekilebilir | Hemen başlanabilir | Tek başına profilaktik aspirin rejyonel blok için engel değildir. |
| **Klopidogrel (Plavix)** | 5 - 7 Gün | Kateter takılıyken kullanılmamalı | Çekildikten 6 saat sonra | Kardiyak stent varlığında kardiyoloji onayı şarttır. |
| **Prasugrel (Effient)** | 7 - 10 Gün | Kateter takılıyken kullanılmamalı | Çekildikten 6 saat sonra | Ciddi kanama riski nedeniyle sıkı takip gerekir. |
| **Ticagrelor (Brilinta)** | 5 Gün | Kateter takılıyken kullanılmamalı | Çekildikten 24 saat sonra | Geri dönüşümlü platelet ADP reseptör antagonisti. |
| **LMWH Profilaktik** | 12 Saat | Çekilmeden en az 12 saat önce durmalı | Çekildikten 4 saat sonra | Böbrek klirensi normal ise geçerlidir. |
| **LMWH Terapötik** | 24 Saat | Kateter takılıyken asla kullanılmamalı | Çekildikten 4 saat sonra | Terapötik dozlarda spinal hematom riski yüksektir. |
| **Warfarin (Coumadin)** | 5 Gün (INR < 1.5 olmalı) | INR normalleşmeden çekilmemeli | Çekildikten 24 saat sonra | Spinal anesteziden hemen önce INR kontrol edilmelidir. |
| **Apixaban (Eliquis)** | 72 Saat (3 Gün) | Kateter takılıyken kullanılmamalı | Çekildikten 6 saat sonra | Böbrek fonksiyonlarına göre doz ve süre ayarlanır. |
| **Rivaroxaban (Xarelto)** | 72 Saat (3 Gün) | Kateter takılıyken kullanılmamalı | Çekildikten 6 saat sonra | Yeni nesil oral Xa inhibitörü. |
| **Dabigatran (Pradaxa)** | 96 Saat (4 Gün) | Kateter takılıyken kullanılmamalı | Çekildikten 6 saat sonra | Renal yetmezliği olanlarda süre 5 güne kadar uzatılır. |

---

## 6. Offline Drug Databases (12 Key Monographs)

Fully seeded in `SeedDataDrugs.kt` with adult/pediatric dosages, kinetics, warnings, and preparation details:
1. **Propofol**: Indüksiyon 1.5-2.5 mg/kg, idame 4-12 mg/kg/saat, infüzyon sendromu (PRIS) uyarısı.
2. **Ketamine**: İndüksiyon 1-2 mg/kg IV, bronkodilatör etki, disosiyatif anestezi, nistagmus.
3. **Etomidate**: İndüksiyon 0.2-0.3 mg/kg, adrenal supresyon riski, stabil hemodinami.
4. **Midazolam**: Sedasyon 0.02-0.1 mg/kg, çocuk premedikasyonu 0.5 mg/kg oral.
5. **Fentanyl**: Analjezi 1-3 mcg/kg, anestezi indüksiyonu 2-10 mcg/kg, göğüs duvarı rijilitesi riski.
6. **Remifentanil**: İnfüzyon 0.05-2 mcg/kg/dk, ultrahızlı eliminasyon (plazma esterazları).
7. **Morphine**: Postop analjezi 0.05-0.15 mg/kg IV, histamin salınımı riski.
8. **Süksinilkolin**: Nöromüsküler blok 1-1.5 mg/kg IV, malign hipertermi ve hiperkalemi uyarısı.
9. **Sisatrakuryum**: İnfüzyon/Bolus 0.15-0.2 mg/kg, Hofmann eliminasyonu (karaciğer/böbrek bağımsız).
10. **Ephedrine**: Vazopressör bolus 5-10 mg IV, taşikardik etkisi vardır.
11. **Phenylephrine**: Vazopressör bolus 40-100 mcg IV, refleks bradikardi uyarısı.
12. **Norepinephrine**: İnfüzyon 0.05-1.5 mcg/kg/dk, santral yoldan uygulama önerisi.

---

## 7. Automated Safety Validation (JUnit Tests Coverage)

JUnit tests located in `MedicalCalculatorsTest.kt` protect the clinical math against regression:
- **`testCalculateBMI`**: Validates correct BMI values and catches negative or zero heights/weights.
- **`testCalculateMAP`**: Verifies MAP arithmetic and asserts that `0.0` is returned if SBP $\le$ DBP.
- **`testCalculateNpoSafety`**: Asserts clear liquids are safe after 3 hours, light meals fail at 4 hours, and risk flags correctly raise `high_risk_review_required`.
- **`testScoreDifficultAirway`**: Asserts high-risk classifications for Mallampati class 4 or mouth openings < 3.0cm.

---

## 8. FlutterFlow & Supabase Migration Guidelines (Blueprints)

When you choose to migrate this Kotlin "Clinical Brain" into a cross-platform FlutterFlow and Supabase environment, follow this structured translation path:

### A. Supabase Database Schema
Store all monographs and reference guides in clean relational tables. Enable offline synchronization features (Local caching / SQLite replication in app).

#### `drugs` Table
```sql
CREATE TABLE public.drugs (
    drug_id text PRIMARY KEY,
    name_tr text NOT NULL,
    name_en text,
    generic_name text NOT NULL,
    brand_names text[] DEFAULT '{}'::text[],
    category text NOT NULL,
    atc_code text,
    drug_class text,
    mechanism_tr text,
    adult_doses jsonb DEFAULT '{}'::jsonb,
    pediatric_doses jsonb DEFAULT '{}'::jsonb,
    warnings text[] DEFAULT '{}'::text[],
    clinical_pearls text[] DEFAULT '{}'::text[],
    requires_clinical_validation boolean DEFAULT true,
    is_premium boolean DEFAULT false
);
```

#### `anticoagulants` Table
```sql
CREATE TABLE public.anticoagulants (
    id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name text NOT NULL,
    pause_before text NOT NULL,
    catheter_management text NOT NULL,
    restart_after text NOT NULL,
    clinical_notes text,
    guideline_source text DEFAULT 'ASRA 2025'
);
```

### B. Translating Kotlin Math to Dart (Custom Functions)
Inside FlutterFlow, register **Custom Functions** containing the deterministic math. For example, translating the NPO fasting logic:

```dart
Map<String, dynamic> calculateNpoSafety(
  String intakeType,
  int lastIntakeHour,
  int lastIntakeMinute,
  int plannedHour,
  int plannedMinute,
  String patientGroup,
  bool hasRiskFlags,
) {
  int diffMinutes = (plannedHour * 60 + plannedMinute) - (lastIntakeHour * 60 + lastIntakeMinute);
  if (diffMinutes < 0) {
    diffMinutes += 24 * 60;
  }
  double elapsedHours = diffMinutes / 60.0;

  double requiredHours = 6.0;
  switch (intakeType.toLowerCase()) {
    case 'clear_liquid':
      requiredHours = 2.0;
      break;
    case 'breast_milk':
      requiredHours = 4.0;
      break;
    case 'infant_formula':
    case 'nonhuman_milk':
    case 'light_meal':
      requiredHours = 6.0;
      break;
    case 'fatty_meal':
      requiredHours = 8.0;
      break;
  }

  double remainingHours = requiredHours - elapsedHours;
  String status = 'suitable';

  if (patientGroup.toLowerCase() == 'emergency' ||
      patientGroup.toLowerCase() == 'high_aspiration_risk' ||
      hasRiskFlags) {
    status = 'high_risk_review_required';
  } else if (elapsedHours >= requiredHours) {
    status = 'suitable';
  } else {
    status = 'wait';
  }

  return {
    'elapsedHours': elapsedHours,
    'requiredHours': requiredHours,
    'remainingHours': remainingHours < 0.0 ? 0.0 : remainingHours,
    'status': status,
  };
}
```

### C. UI Rendering in FlutterFlow
- **Gradients**: Implement Container backgrounds using linear gradients matching Deep Navy (`#2C4A6E`) to Deep Navy copy (`#2C4A6E` with 0.95 opacity) for header modules.
- **Sliders**: Map FlutterFlow slider widgets directly to local page state parameters, triggering custom action updates on change.
- **Rate-Limits**: Build a local action flow for the AI Clinical Assistant checking the count of user messages inside a SQLite table or local app state. If the message count $\ge$ 3 and the user's `isPremium` field is false, trigger a bottom sheet prompting the premium upgrade.
- **Patient Anonymizer**: Write a Dart custom function utilizing regular expressions to clean strings prior to sending requests to the API proxy:
  ```dart
  String anonymizeInput(String input) {
    // Clean T.C. Identity numbers
    String result = input.replaceAll(RegExp(r'\b\d{11}\b'), '[MASKED_ID]');
    // Clean phone numbers
    result = result.replaceAll(RegExp(r'\b(05\d{9}|5\d{9}|\+905\d{9})\b'), '[MASKED_PHONE]');
    return result;
  }
  ```

---

## 9. Pediatric Dosing, Cardiovascular Support & Crisis Calculations (Phase 9 Specification)

These clinical calculations cover pediatric emergency/induction guidelines, vasoactive syringe infusion pump flows, regional mixtures recipes, and high-stakes crisis protocols.

### A. Pediatric Emergency & Induction Dosing
Determines child dosage ranges and dilutions:
- **CPR Adrenalin**: $0.01 \text{ mg/kg}$ IV ($10 \text{ mcg/kg}$). Prepared in a 1:10,000 dilution ($0.1 \text{ mg/mL}$), equating to $0.1 \text{ mL/kg}$.
- **Bradikardi Atropin**: $0.02 \text{ mg/kg}$ IV (clamped between $0.1 \text{ mg}$ minimum and $0.5 \text{ mg}$ maximum). Diluted to $0.1 \text{ mg/mL}$.
- **İndüksiyon Propofol**: $2.5 \text{ mg/kg}$ IV. Standard %1 ($10 \text{ mg/mL}$) solution.
- **İndüksiyon Ketamin**: $2.0 \text{ mg/kg}$ IV. Diluted to $10 \text{ mg/mL}$.
- **Analjezi Fentanil**: $1.5 \text{ mcg/kg}$ IV. Standard $50 \text{ mcg/mL}$ ampoule.
- **Gevşetici Roküronyum**: $0.6 \text{ mg/kg}$ IV. Standard $10 \text{ mg/mL}$ ampoule.
- **Süksinilkolin**: $1.5 \text{ mg/kg}$ IV. Standard $20 \text{ mg/mL}$ ampoule.

#### Dart Translation (Custom Function)
```dart
Map<String, dynamic> calculatePediatricDoses(double weightKg) {
  if (weightKg <= 0.0) {
    return {
      'adrenalinMg': 0.0, 'adrenalinMl': 0.0,
      'atropinMg': 0.0, 'atropinMl': 0.0,
      'propofolMg': 0.0, 'propofolMl': 0.0,
      'ketaminMg': 0.0, 'ketaminMl': 0.0,
      'fentanylMcg': 0.0, 'fentanylMl': 0.0,
      'rocuroniumMg': 0.0, 'rocuroniumMl': 0.0,
      'succinylcholineMg': 0.0, 'succinylcholineMl': 0.0,
    };
  }

  double adrMg = weightKg * 0.01;
  double adrMl = weightKg * 0.1;

  double atrMgRaw = weightKg * 0.02;
  double atrMg = atrMgRaw < 0.1 ? 0.1 : (atrMgRaw > 0.5 ? 0.5 : atrMgRaw);
  double atrMl = atrMg / 0.1;

  double propMg = weightKg * 2.5;
  double propMl = propMg / 10.0;

  double ketMg = weightKg * 2.0;
  double ketMl = ketMg / 10.0;

  double fenMcg = weightKg * 1.5;
  double fenMl = fenMcg / 50.0;

  double rocMg = weightKg * 0.6;
  double rocMl = rocMg / 10.0;

  double succMg = weightKg * 1.5;
  double succMl = succMg / 20.0;

  return {
    'adrenalinMg': adrMg, 'adrenalinMl': adrMl,
    'atropinMg': atrMg, 'atropinMl': atrMl,
    'propofolMg': propMg, 'propofolMl': propMl,
    'ketaminMg': ketMg, 'ketaminMl': ketMl,
    'fentanylMcg': fenMcg, 'fentanylMl': fenMl,
    'rocuroniumMg': rocMg, 'rocuroniumMl': rocMl,
    'succinylcholineMg': succMg, 'succinylcholineMl': succMl,
  };
}
```

### B. Vasoactive Perfusion Flow Rates
Calculates syringe-pump hourly flow rates ($mL/hour$):
- Derivation of Concentration ($conc$):
  $$conc = \frac{\text{Syringe Mg} \times 1000}{\text{Syringe mL}} \quad (\text{mcg/mL})$$
- Flow Rate:
  - For **Nitroglycerin** (target dose in $mcg/min$):
    $$\text{Rate} = \frac{\text{Target Dose} \times 60}{conc} \quad (\text{mL/hour})$$
  - For **Noradrenaline / Adrenaline / Dobutamine / Dopamine** (target dose in $mcg/kg/min$):
    $$\text{Rate} = \frac{\text{Target Dose} \times \text{Weight (kg)} \times 60}{conc} \quad (\text{mL/hour})$$

#### Dart Translation (Custom Function)
```dart
double calculateInfusionRate(
  String drug,
  double weightKg,
  double syringeMg,
  double syringeMl,
  double targetDose,
) {
  if (weightKg <= 0.0 || syringeMg <= 0.0 || syringeMl <= 0.0 || targetDose <= 0.0) {
    return 0.0;
  }
  double concMcgMl = (syringeMg * 1000.0) / syringeMl;
  if (drug.toLowerCase() == 'nitrogliserin') {
    return (targetDose * 60.0) / concMcgMl;
  } else {
    return (targetDose * weightKg * 60.0) / concMcgMl;
  }
}
```

### C. Malignant Hyperthermia Dantrolene Rescue
Urgent protocol calculation based on Devine or actual pediatric/adult weights:
- **Emergency Bolus Doze**: $2.5\text{ mg/kg}$ IV.
- **Vial Calculations**: Each vial provides $20\text{ mg}$ of Dantrolene.
  $$\text{Total Vials} = \lceil \frac{\text{Weight (kg)} \times 2.5}{20} \rceil$$
- **Reconstitution Hacmi**: Each vial requires $60\text{ mL}$ preservative-free warm sterile water.
  $$\text{Sterile Water (mL)} = \text{Total Vials} \times 60$$

#### Dart Translation (Custom Function)
```dart
Map<String, dynamic> calculateDantroleneRescue(double weightKg) {
  if (weightKg <= 0.0) {
    return {
      'bolusMg': 0.0,
      'totalVials': 0,
      'sterileWaterMl': 0.0,
    };
  }
  double bolusMg = weightKg * 2.5;
  int totalVials = (bolusMg / 20.0).ceil();
  double sterileWaterMl = totalVials * 60.0;

  return {
    'bolusMg': bolusMg,
    'totalVials': totalVials,
    'sterileWaterMl': sterileWaterMl,
  };
}
```

### D. Local Anesthetic Systemic Toksisitesi (LAST) LipidRescue
Emergency Lipid Rescue protocol for local anesthetic cardiotoxicity:
- **First bolus volume** (%20 Lipid emulsion - $1.5\text{ mL/kg}$ over 1 min):
  $$\text{Bolus Hacmi (mL)} = 1.5 \times \text{Weight (kg)}$$
- **Continuous Infusion rate** ($0.25\text{ mL/kg/min}$):
  $$\text{Infusion Rate (mL/min)} = 0.25 \times \text{Weight (kg)}$$
  $$\text{Flow Rate (mL/hour)} = \text{Infusion Rate (mL/min)} \times 60$$

#### Dart Translation (Custom Function)
```dart
Map<String, double> calculateLipidRescue(double weightKg) {
  if (weightKg <= 0.0) {
    return {
      'bolusMl': 0.0,
      'infusionRateMlMin': 0.0,
      'infusionRateMlHr': 0.0,
    };
  }
  double bolusMl = 1.5 * weightKg;
  double infusionRateMlMin = 0.25 * weightKg;
  double infusionRateMlHr = infusionRateMlMin * 60.0;

  return {
    'bolusMl': bolusMl,
    'infusionRateMlMin': infusionRateMlMin,
    'infusionRateMlHr': infusionRateMlHr,
  };
}
```

### E. Regional Anesthesia Mixture Guidelines
Clinically established mixtures and concentrations for epidural infusion pumps:
1. **Epidural Ağrısız Doğum (Labor Analgesia)**: Saline $50\text{ mL}$ + Bupivacaine 0.5% (Marcaine) $10\text{ mL}$ + Fentanyl ($100\text{ mcg}$) $2\text{ mL}$. Total: $62\text{ mL}$. Final Concentration: **%0.08 Bupivacaine + 1.6 mcg/mL Fentanyl**. Basal rate: $8\text{-}12\text{ mL/hr}$, PCEA bolus $5\text{ mL}$ with $15\text{-}20\text{ mins}$ lock-out.
2. **Postoperatif Epidural**: Saline $40\text{ mL}$ + Bupivacaine 0.5% (Marcaine) $20\text{ mL}$ + Fentanyl ($100\text{ mcg}$) $2\text{ mL}$. Total: $62\text{ mL}$. Final Concentration: **%0.16 Bupivacaine + 1.6 mcg/mL Fentanyl**. Rate: $4\text{-}8\text{ mL/hr}$.
3. **Obstetrik Spinal**: Heavy Bupivacaine 0.5% $1.8\text{ mL}$ ($9\text{ mg}$) + Fentanyl $0.2\text{ mL}$ ($10\text{ mcg}$) + Morphine $0.1\text{ mL}$ ($100\text{ mcg}$). Total Volume: $2.1\text{ mL}$.
4. **Klasik Spinal**: Heavy Bupivacaine 0.5% $2.5\text{ - }3.0\text{ mL}$ ($12.5\text{ - }15\text{ mg}$) + Fentanyl $0.5\text{ mL}$ ($25\text{ mcg}$). Total Volume: $3.0\text{ - }3.5\text{ mL}$.

---
