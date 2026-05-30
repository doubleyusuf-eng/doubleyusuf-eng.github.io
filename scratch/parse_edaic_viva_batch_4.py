import json
import os

existing_path = "app/src/main/assets/board_spot_notes.json"

# Load existing notes
if os.path.exists(existing_path):
    with open(existing_path, "r", encoding="utf-8") as f:
        spot_notes = json.load(f)
    print(f"Loaded {len(spot_notes)} existing spot notes.")
else:
    spot_notes = []
    print("No existing spot notes found. Starting fresh.")

new_spots = [
    # 151
    {
        "id": "edaic_viva_spot_151",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Travma hastasında ilk cevabımı havayolu, servikal omurga korunması, solunum ve dolaşımın hızlı değerlendirilmesi üzerine kurarım.",
        "en": "In trauma, I base my first answer on rapid assessment of airway, cervical spine protection, breathing, and circulation.",
        "importance": "high",
        "tags": ["trauma_resuscitation_verbal", "atls_abcde_flow", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 152
    {
        "id": "edaic_viva_spot_152",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Hemorajik şokta normal kan basıncının ciddi kan kaybını dışlamayacağını, özellikle genç hastalarda kompansasyon olabileceğini söylerim.",
        "en": "In hemorrhagic shock, I state that normal blood pressure does not exclude major blood loss, especially in young patients.",
        "importance": "high",
        "tags": ["hemorrhagic_shock", "compensatory_hypotension", "occult_hypovolemia", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 153
    {
        "id": "edaic_viva_spot_153",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Travmada hipotermi, asidoz ve koagülopatinin birbirini kötüleştirerek mortaliteyi artırdığını vurgularım.",
        "en": "In trauma, I emphasize that hypothermia, acidosis, and coagulopathy worsen each other and increase mortality.",
        "importance": "high",
        "tags": ["trauma_lethal_triad", "hypothermia_coagulopathy", "asidosis_myocardial_depression", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 154
    {
        "id": "edaic_viva_spot_154",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Hasar kontrol resüsitasyonunda kanama kontrolü, dengeli transfüzyon ve kristalloid yükünden kaçınmayı belirtirim.",
        "en": "In damage control resuscitation, I mention bleeding control, balanced transfusion, and avoidance of excessive crystalloid.",
        "importance": "high",
        "tags": ["damage_control_resuscitation", "restrictive_crystalloids", "balanced_blood_ratios", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 155
    {
        "id": "edaic_viva_spot_155",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Travmatik beyin hasarında hipoksi ve hipotansiyondan kaçınmayı sekonder beyin hasarını önlemek için önceliklendiririm.",
        "en": "In traumatic brain injury, I prioritize avoiding hypoxia and hypotension to prevent secondary brain injury.",
        "importance": "high",
        "tags": ["tbi_secondary_prevention", "avoid_hypoxia", "cpp_maintenance", "safety_first_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 156
    {
        "id": "edaic_viva_spot_156",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Burn Management",
        "tr": "Yanık hastasında inhalasyon hasarı ve havayolu ödemi şüphesi varsa erken entübasyonu tartışırım.",
        "en": "In burn patients, I discuss early intubation if inhalation injury or airway edema is suspected.",
        "importance": "high",
        "tags": ["burn_airway_edema", "early_intubation_threshold", "inhalation_injury", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 157
    {
        "id": "edaic_viva_spot_157",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Burn Management",
        "tr": "Yanık sonrası belirli dönemden sonra süksinilkolin kullanımının hiperkalemi riski nedeniyle tehlikeli olduğunu belirtirim.",
        "en": "I state that after a certain period following burns, succinylcholine is dangerous because of hyperkalemia risk.",
        "importance": "high",
        "tags": ["succinylcholine_burns", "extrajunctional_receptors", "hyperkalemia_cardiac_arrest", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 158
    {
        "id": "edaic_viva_spot_158",
        "exam": "EDAIC_VIVA",
        "topic": "Trauma & Emergency",
        "subtopic": "Burn Management",
        "tr": "Major yanıkta sıvı resüsitasyonunu formül, idrar çıkışı ve klinik perfüzyonla birlikte takip ederim.",
        "en": "In major burns, I monitor fluid resuscitation using formulas, urine output, and clinical perfusion together.",
        "importance": "high",
        "tags": ["burn_fluid_resuscitation", "parkland_formula", "urine_output_target", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 159
    {
        "id": "edaic_viva_spot_159",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Orthopedic Complications",
        "tr": "Yağ embolisi sendromunda hipoksemi, nörolojik bulgular ve peteşiyi birlikte düşünürüm.",
        "en": "In fat embolism syndrome, I consider hypoxemia, neurological signs, and petechiae together.",
        "importance": "high",
        "tags": ["fat_embolism_triad", "hypoxemia", "petechial_rash", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 160
    {
        "id": "edaic_viva_spot_160",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Orthopedic Complications",
        "tr": "Kemik çimentosu implantasyon sendromunda ani hipoksi, hipotansiyon ve kardiyovasküler kollapsı hızlı tanırım.",
        "en": "In bone cement implantation syndrome, I rapidly recognize sudden hypoxia, hypotension, and cardiovascular collapse.",
        "importance": "high",
        "tags": ["bcis", "bone_cement_complications", "cardiovascular_collapse", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 161
    {
        "id": "edaic_viva_spot_161",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "Respiratory Complications & TOF",
        "tr": "Postoperatif hipoksemide atelektazi, opioid etkisi, rezidüel blok, aspirasyon ve pulmoner emboliyi sistematik düşünürüm.",
        "en": "In postoperative hypoxemia, I systematically consider atelectasis, opioid effect, residual block, aspiration, and pulmonary embolism.",
        "importance": "high",
        "tags": ["postoperative_hypoxemia", "differential_diagnosis", "atelectasis", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 162
    {
        "id": "edaic_viva_spot_162",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "Respiratory Complications & TOF",
        "tr": "Postoperatif solunum depresyonunda opioid, sedatif, OSA, rezidüel blok ve hipoventilasyonu değerlendiririm.",
        "en": "In postoperative respiratory depression, I assess opioids, sedatives, OSA, residual block, and hypoventilation.",
        "importance": "high",
        "tags": ["postoperative_respiratory_depression", "opioid_induced", "osa_exacerbation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 163
    {
        "id": "edaic_viva_spot_163",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "Respiratory Complications & TOF",
        "tr": "Rezidüel nöromüsküler bloktan şüphelenirsem kantitatif TOF değerlendirmesi ve uygun reversali vurgularım.",
        "en": "If I suspect residual neuromuscular blockade, I emphasize quantitative TOF assessment and appropriate reversal.",
        "importance": "high",
        "tags": ["residual_neuromuscular_blockade", "quantitative_tof_monitoring", "sugammadex_neostigmine", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 164
    {
        "id": "edaic_viva_spot_164",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "Cardiovascular Complications",
        "tr": "Postoperatif göğüs ağrısında miyokard iskemisi, pulmoner emboli, pnömotoraks ve aort patolojisini dışlamaya çalışırım.",
        "en": "In postoperative chest pain, I try to exclude myocardial ischemia, pulmonary embolism, pneumothorax, and aortic pathology.",
        "importance": "high",
        "tags": ["postoperative_chest_pain", "differential_diagnosis", "myocardial_ischemia", "safety_first_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 165
    {
        "id": "edaic_viva_spot_165",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "Cardiovascular Complications",
        "tr": "Postoperatif miyokard hasarının sessiz olabileceğini ve yüksek riskli hastada troponin takibinin yararlı olabileceğini söylerim.",
        "en": "I state that postoperative myocardial injury may be silent and troponin monitoring may be useful in high-risk patients.",
        "importance": "high",
        "tags": ["silent_myocardial_injury", "postoperative_troponin", "mins", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 166
    {
        "id": "edaic_viva_spot_166",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "Delirium & Agitation",
        "tr": "Postoperatif ajitasyonda önce hipoksi, hipoglisemi, ağrı ve mesane distansiyonu gibi düzeltilebilir nedenleri ararım.",
        "en": "In postoperative agitation, I first look for reversible causes such as hypoxia, hypoglycemia, pain, and bladder distension.",
        "importance": "high",
        "tags": ["postoperative_agitation", "reversible_causes", "bladder_distension", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 167
    {
        "id": "edaic_viva_spot_167",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "Delirium & Agitation",
        "tr": "Postoperatif deliryumda yaşlılık, frailty, enfeksiyon, ağrı, benzodiazepin ve uyku bozukluğunu risk faktörü olarak belirtirim.",
        "en": "In postoperative delirium, I mention advanced age, frailty, infection, pain, benzodiazepines, and sleep disturbance as risk factors.",
        "importance": "high",
        "tags": ["postoperative_delirium", "risk_factors", "frailty", "benzodiazepines_hazard", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 168
    {
        "id": "edaic_viva_spot_168",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "PONV & Pain",
        "tr": "PONV yönetiminde risk faktörlerini belirler ve farklı mekanizmalardan multimodal profilaksi veya tedavi seçerim.",
        "en": "In PONV management, I identify risk factors and choose multimodal prophylaxis or treatment using different mechanisms.",
        "importance": "high",
        "tags": ["ponv_management", "apfel_score", "multimodal_prophylaxis", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 169
    {
        "id": "edaic_viva_spot_169",
        "exam": "EDAIC_VIVA",
        "topic": "Postoperative Care",
        "subtopic": "PONV & Pain",
        "tr": "Postoperatif ağrı tedavisinde multimodal analjezi ile opioid ihtiyacını azaltmayı hedeflerim.",
        "en": "In postoperative pain management, I aim to reduce opioid requirement with multimodal analgesia.",
        "importance": "high",
        "tags": ["multimodal_analgesia", "opioid_sparing", "pain_management", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 170
    {
        "id": "edaic_viva_spot_170",
        "exam": "EDAIC_VIVA",
        "topic": "Pain Management",
        "subtopic": "Opioid Tolerance",
        "tr": "Kronik opioid kullanan hastada tolerans, hiperaljezi ve yetersiz analjezi riskini önceden planlarım.",
        "en": "In chronic opioid users, I plan ahead for tolerance, hyperalgesia, and risk of inadequate analgesia.",
        "importance": "high",
        "tags": ["chronic_opioid_use", "tolerance_hyperalgesia", "regional_analgesia_adjuncts", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 171
    {
        "id": "edaic_viva_spot_171",
        "exam": "EDAIC_VIVA",
        "topic": "Ambulatory Anesthesia",
        "subtopic": "Discharge & Deterioration",
        "tr": "Günübirlik cerrahide taburculuk kararını vital bulgular, ağrı, bulantı, mobilizasyon ve ev desteğine göre veririm.",
        "en": "In ambulatory surgery, I base discharge decisions on vital signs, pain, nausea, mobilization, and home support.",
        "importance": "high",
        "tags": ["ambulatory_discharge_criteria", "aldrete_score", "home_support_check", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 172
    {
        "id": "edaic_viva_spot_172",
        "exam": "EDAIC_VIVA",
        "topic": "Ambulatory Anesthesia",
        "subtopic": "Discharge & Deterioration",
        "tr": "Günübirlik cerrahide güvenli taburculuk kadar evde kötüleşme durumunda ulaşılacak planı da önemserim.",
        "en": "In ambulatory surgery, I value not only safe discharge but also a plan for deterioration at home.",
        "importance": "high",
        "tags": ["ambulatory_anesthesia_safety", "deterioration_plan", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 173
    {
        "id": "edaic_viva_spot_173",
        "exam": "EDAIC_VIVA",
        "topic": "Special Populations",
        "subtopic": "Geriatrics",
        "tr": "Yaşlı hastada dozları titrasyonla verir, deliryum ve fonksiyon kaybı riskini planıma dahil ederim.",
        "en": "In elderly patients, I titrate doses and include delirium and functional decline risk in my plan.",
        "importance": "high",
        "tags": ["geriatric_dose_titration", "delirium_prevention", "functional_decline", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 174
    {
        "id": "edaic_viva_spot_174",
        "exam": "EDAIC_VIVA",
        "topic": "Special Populations",
        "subtopic": "Obesity & OSA",
        "tr": "Obez hastada ramped pozisyon, etkili preoksijenasyon ve postoperatif solunum izlemine özellikle dikkat ederim.",
        "en": "In obese patients, I pay special attention to ramped positioning, effective preoxygenation, and postoperative respiratory monitoring.",
        "importance": "high",
        "tags": ["obesity_airway_verbal", "ramped_positioning", "atelectasis_prevention", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 175
    {
        "id": "edaic_viva_spot_175",
        "exam": "EDAIC_VIVA",
        "topic": "Special Populations",
        "subtopic": "Obesity & OSA",
        "tr": "OSA hastasında opioid azaltıcı strateji, CPAP kullanımı ve uygun postoperatif monitörizasyonu planlarım.",
        "en": "In OSA patients, I plan opioid-sparing strategies, CPAP use, and appropriate postoperative monitoring.",
        "importance": "high",
        "tags": ["osa_postop_management", "opioid_sparing", "cpap_compliance", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 176
    {
        "id": "edaic_viva_spot_176",
        "exam": "EDAIC_VIVA",
        "topic": "Clinical Procedures",
        "subtopic": "Central Lines",
        "tr": "Zor damar yolu beklenen hastada ultrason rehberliğini erken kullanmayı ve yedek erişim planını belirtirim.",
        "en": "In patients with expected difficult vascular access, I mention early ultrasound guidance and a backup access plan.",
        "importance": "high",
        "tags": ["difficult_iv_access", "ultrasound_guidance", "backup_plan", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 177
    {
        "id": "edaic_viva_spot_177",
        "exam": "EDAIC_VIVA",
        "topic": "Clinical Procedures",
        "subtopic": "Central Lines",
        "tr": "Santral venöz kateterizasyon komplikasyonları olarak arter ponksiyonu, pnömotoraks, hematom, enfeksiyon ve hava embolisini sayarım.",
        "en": "I list arterial puncture, pneumothorax, hematoma, infection, and air embolism as central venous catheter complications.",
        "importance": "high",
        "tags": ["cvc_complications", "pneumothorax", "arterial_punction", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 178
    {
        "id": "edaic_viva_spot_178",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Air Embolism",
        "tr": "Hava embolisi riski olan hastada tüm damar yollarının havasızlandırılmasını ve cerrahi ekip ile iletişimi vurgularım.",
        "en": "In patients at risk of air embolism, I emphasize de-airing all intravenous lines and communicating with the surgical team.",
        "importance": "high",
        "tags": ["air_embolism_prevention", "de_airing_lines", "surgical_communication", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 179
    {
        "id": "edaic_viva_spot_179",
        "exam": "EDAIC_VIVA",
        "topic": "Immunology & Allergy",
        "subtopic": "Latex & Drug Allergy",
        "tr": "Lateks alerjisinde latekssiz ortam, ekipman ve ameliyathane hazırlığını açıkça belirtirim.",
        "en": "In latex allergy, I clearly state the need for a latex-free environment, equipment, and operating room preparation.",
        "importance": "high",
        "tags": ["latex_allergy", "latex_free_or", "equipment_safety", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 180
    {
        "id": "edaic_viva_spot_180",
        "exam": "EDAIC_VIVA",
        "topic": "Immunology & Allergy",
        "subtopic": "Latex & Drug Allergy",
        "tr": "İlaç alerjisi öyküsünde gerçek anafilaksi, yan etki ve intolerans ayrımını sorgularım.",
        "en": "In drug allergy history, I ask to distinguish true anaphylaxis, adverse effect, and intolerance.",
        "importance": "high",
        "tags": ["allergy_history", "true_anaphylaxis_vs_intolerance", "preop_assessment", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 181
    {
        "id": "edaic_viva_spot_181",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "Kan ürünü vermeden önce hasta kimliği, ürün uygunluğu ve yatak başı kontrolünü kritik güvenlik basamağı olarak belirtirim.",
        "en": "Before giving blood products, I state that patient identity, product compatibility, and bedside checks are critical safety steps.",
        "importance": "high",
        "tags": ["blood_transfusion_safety", "bedside_verification", "patient_identity", "safety_first_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 182
    {
        "id": "edaic_viva_spot_182",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "İlaç hatalarını azaltmak için etiketleme, standart konsantrasyon, çift kontrol ve benzer isimli ilaçlara dikkat etmeyi söylerim.",
        "en": "To reduce medication errors, I mention labeling, standard concentration, double checking, and attention to look-alike/sound-alike drugs.",
        "importance": "high",
        "tags": ["medication_safety", "labeling_standards", "double_checking", "lasa_drugs", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 183
    {
        "id": "edaic_viva_spot_183",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "Kritik ilaç uygulamasında doz, konsantrasyon, yol ve hasta kimliğini yüksek sesle doğrularım.",
        "en": "During critical drug administration, I verbally confirm dose, concentration, route, and patient identity.",
        "importance": "high",
        "tags": ["critical_drug_administration", "verbal_confirmation", "patient_identity", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 184
    {
        "id": "edaic_viva_spot_184",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Care",
        "subtopic": "Patient Transport",
        "tr": "Güvenli hasta transportunda havayolu, oksijen, monitör, damar yolu, ilaç ve acil ekipmanı önceden hazırlarım.",
        "en": "For safe patient transport, I prepare airway, oxygen, monitor, IV access, drugs, and emergency equipment in advance.",
        "importance": "high",
        "tags": ["patient_transport_safety", "monitoring_during_transit", "emergency_bag", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 185
    {
        "id": "edaic_viva_spot_185",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "ICU Handover",
        "tr": "Yoğun bakıma devirde hava yolu, ventilasyon, hemodinami, sıvı-kan ürünleri, ilaçlar ve özel riskleri açıkça aktarırım.",
        "en": "During ICU handover, I clearly communicate airway, ventilation, hemodynamics, fluids and blood products, medications, and special risks.",
        "importance": "high",
        "tags": ["icu_handover", "sbar_protocol", "patient_safety", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 186
    {
        "id": "edaic_viva_spot_186",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Post-Crisis Disclosure",
        "tr": "Kritik olay sonrasında dokümantasyon, ekip bilgilendirmesi, hasta/yakın açıklaması ve kalite değerlendirmesini planlarım.",
        "en": "After a critical event, I plan documentation, team debriefing, patient or family disclosure, and quality review.",
        "importance": "high",
        "tags": ["post_crisis_management", "disclosure", "debriefing", "documentation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 187
    {
        "id": "edaic_viva_spot_187",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Care",
        "subtopic": "Surgical Safety Checklist",
        "tr": "Cerrahi güvenlik kontrol listesinin doğru hasta, doğru işlem, doğru taraf ve ekip hazırlığını desteklediğini belirtirim.",
        "en": "I state that the surgical safety checklist supports correct patient, correct procedure, correct side, and team readiness.",
        "importance": "high",
        "tags": ["who_surgical_safety_checklist", "wrong_site_surgery_prevention", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 188
    {
        "id": "edaic_viva_spot_188",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Crisis Communication",
        "tr": "Kapalı döngü iletişimin, verilen kritik komutların doğru anlaşılıp uygulandığını doğruladığını söylerim.",
        "en": "Closed-loop communication confirms that critical commands are correctly understood and performed.",
        "importance": "high",
        "tags": ["closed_loop_communication", "crm_principles", "team_safety", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 189
    {
        "id": "edaic_viva_spot_189",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Crisis Communication",
        "tr": "Krizde liderin görev dağıtması, zamanı takip etmesi ve genel resmi koruması gerektiğini vurgularım.",
        "en": "In a crisis, I emphasize that the leader should allocate tasks, track time, and maintain the overall picture.",
        "importance": "high",
        "tags": ["crisis_leadership", "situational_awareness", "crm_principles", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 190
    {
        "id": "edaic_viva_spot_190",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Viva’da bilmediğim bir noktada güvenli prensiplere dönerek hastayı stabilize etme yaklaşımını anlatırım.",
        "en": "In viva, when I do not know a specific point, I return to safe principles and describe stabilizing the patient.",
        "importance": "high",
        "tags": ["viva_tactics", "safe_principles_recovery", "patient_safety_default", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 191
    {
        "id": "edaic_viva_spot_191",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Informed Consent & presumptive consent",
        "tr": "Bilgilendirilmiş onamda hastaya önerilen işlem, alternatifler, riskler ve reddetme hakkı açıklanmalıdır.",
        "en": "In informed consent, the proposed procedure, alternatives, risks, and right to refuse should be explained.",
        "importance": "high",
        "tags": ["informed_consent", "autonomy", "risk_disclosure", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 192
    {
        "id": "edaic_viva_spot_192",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Informed Consent & presumptive consent",
        "tr": "Acil ve hayatı tehdit eden durumda hasta karar veremiyorsa, gerekli tedavi varsayılan rıza ile yapılabilir.",
        "en": "In an emergency life-threatening situation where the patient lacks capacity, necessary treatment may proceed under presumed consent.",
        "importance": "high",
        "tags": ["emergency_treatment_consent", "presumed_consent", "life_saving_priority", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 193
    {
        "id": "edaic_viva_spot_193",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Transfusion Refusal",
        "tr": "Kan transfüzyonunu reddeden hastada özerkliğe saygı gösterir, kan koruma stratejilerini ve kabul edilebilir seçenekleri tartışırım.",
        "en": "In patients refusing blood transfusion, I respect autonomy and discuss blood conservation strategies and acceptable options.",
        "importance": "high",
        "tags": ["jehovahs_witness_management", "blood_transfusion_refusal", "patient_autonomy", "blood_conservation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 194
    {
        "id": "edaic_viva_spot_194",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "DNR Decision",
        "tr": "DNR kararı olan hastada perioperatif dönemde hedeflerin ve sınırların yeniden konuşulması gerektiğini belirtirim.",
        "en": "In patients with a DNR decision, perioperative goals and limits should be re-discussed.",
        "importance": "high",
        "tags": ["dnr_orders_perioperative", "re_discussion", "treatment_boundaries", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 195
    {
        "id": "edaic_viva_spot_195",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Capacity Assessment",
        "tr": "Kapasite değerlendirmesinde hastanın bilgiyi anlama, değerlendirme, karar verme ve kararını ifade etme yetisini sorgularım.",
        "en": "In capacity assessment, I evaluate the patient’s ability to understand, weigh information, decide, and communicate the decision.",
        "importance": "high",
        "tags": ["mental_capacity_assessment", "decision_making_capacity", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 196
    {
        "id": "edaic_viva_spot_196",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Medical Error & Disclosure",
        "tr": "Tıbbi hatada dürüst açıklama, hasta güvenliği ve kurumsal bildirim süreçlerini vurgularım.",
        "en": "In medical error, I emphasize honest disclosure, patient safety, and institutional reporting processes.",
        "importance": "high",
        "tags": ["medical_error_management", "honest_disclosure", "safety_culture", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 197
    {
        "id": "edaic_viva_spot_197",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Resource Limitation",
        "tr": "Kaynak kısıtlı durumda önceliklendirme klinik aciliyet, fayda olasılığı ve adalet ilkeleriyle yapılmalıdır.",
        "en": "In resource-limited situations, prioritization should be based on clinical urgency, likelihood of benefit, and justice.",
        "importance": "high",
        "tags": ["resource_limitation", "triage_ethics", "justice_principle", "clinical_urgency", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 198
    {
        "id": "edaic_viva_spot_198",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Viva cevabımda önce hastayı güvene alır, sonra tanı, tedavi, iletişim ve takip basamaklarını yapılandırırım.",
        "en": "In viva answers, I first make the patient safe, then structure diagnosis, treatment, communication, and follow-up steps.",
        "importance": "high",
        "tags": ["viva_structuring", "safety_first_model", "diagnostic_flowchart", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 199
    {
        "id": "edaic_viva_spot_199",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Oral sınavda cevaplarımı kısa, sistematik ve önceliklendirilmiş şekilde vermeye çalışırım.",
        "en": "In oral exams, I try to give my answers in a short, systematic, and prioritized way.",
        "importance": "high",
        "tags": ["viva_tactics", "systematic_answers", "prioritization", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 200
    {
        "id": "edaic_viva_spot_200",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "İyi bir viva cevabı yalnızca doğru bilgiyi değil, güvenli klinik düşünme ve ekip yönetimini de göstermelidir.",
        "en": "A good viva answer should demonstrate not only correct knowledge but also safe clinical reasoning and team management.",
        "importance": "high",
        "tags": ["viva_excellence", "clinical_reasoning", "crisis_resource_management", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

combined = spot_notes + new_spots

# Double-check duplicates or incorrect formats in-memory first
seen_ids = set()
duplicates = []
for item in combined:
    note_id = item["id"]
    if note_id in seen_ids:
        duplicates.append(note_id)
    seen_ids.add(note_id)

if duplicates:
    print(f"ERROR: Duplicate IDs found before saving: {duplicates}")
    exit(1)

# Save updated list
with open(existing_path, "w", encoding="utf-8") as f:
    json.dump(combined, f, ensure_ascii=False, indent=2)

print(f"Successfully combined and wrote {len(combined)} spot notes to {existing_path}")
