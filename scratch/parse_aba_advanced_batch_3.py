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
    # 101
    {
        "id": "aba_advanced_spot_101",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Septik şokta erken antibiyotik, kaynak kontrolü, sıvı resüsitasyonu ve vazopressör tedavi temel yaklaşımdır.",
        "en": "In septic shock, early antibiotics, source control, fluid resuscitation, and vasopressor therapy are core treatments.",
        "importance": "high",
        "tags": ["septic_shock", "early_antibiotics", "source_control", "fluid_resuscitation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 102
    {
        "id": "aba_advanced_spot_102",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Septik şokta ilk tercih vazopressör genellikle noradrenalindir.",
        "en": "In septic shock, norepinephrine is usually the first-choice vasopressor.",
        "importance": "high",
        "tags": ["septic_shock", "first_choice_vasopressor", "norepinephrine"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 103
    {
        "id": "aba_advanced_spot_103",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Laktat yüksekliği doku hipoperfüzyonu, adrenerjik stres ve hastalık şiddeti hakkında bilgi verebilir.",
        "en": "Elevated lactate may reflect tissue hypoperfusion, adrenergic stress, and disease severity.",
        "importance": "high",
        "tags": ["elevated_lactate", "tissue_hypoperfusion", "adrenergic_stress", "disease_severity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 104
    {
        "id": "aba_advanced_spot_104",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Sepsiste sıvı yanıtı klinik, dinamik indeksler ve perfüzyon bulgularıyla birlikte değerlendirilmelidir.",
        "en": "In sepsis, fluid responsiveness should be assessed using clinical findings, dynamic indices, and perfusion markers together.",
        "importance": "high",
        "tags": ["sepsis_resuscitation", "fluid_responsiveness", "dynamic_indices"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 105
    {
        "id": "aba_advanced_spot_105",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Vazopressör gereksinimi devam eden septik şokta santral venöz erişim ve invaziv arteriyel monitörizasyon düşünülür.",
        "en": "In septic shock with ongoing vasopressor need, central venous access and invasive arterial monitoring should be considered.",
        "importance": "high",
        "tags": ["septic_shock", "central_venous_access", "arterial_monitoring"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 106
    {
        "id": "aba_advanced_spot_106",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "ARDS’de akciğer koruyucu ventilasyon düşük tidal volüm ve sınırlı plato basıncına dayanır.",
        "en": "Lung-protective ventilation in ARDS is based on low tidal volume and limited plateau pressure.",
        "importance": "high",
        "tags": ["ards", "lung_protective_ventilation", "low_tidal_volume", "plateau_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 107
    {
        "id": "aba_advanced_spot_107",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "ARDS’de yüksek plateau basıncı ventilatör ilişkili akciğer hasarı riskini artırır.",
        "en": "High plateau pressure in ARDS increases the risk of ventilator-induced lung injury.",
        "importance": "high",
        "tags": ["plateau_pressure", "ards", "ventilator_induced_lung_injury", "vili"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 108
    {
        "id": "aba_advanced_spot_108",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "ARDS’de prone pozisyon ağır hipoksemide oksijenasyonu ve sağkalımı iyileştirebilir.",
        "en": "In ARDS, prone positioning may improve oxygenation and survival in severe hypoxemia.",
        "importance": "high",
        "tags": ["ards_management", "prone_positioning", "oxygenation", "survival"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 109
    {
        "id": "aba_advanced_spot_109",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "ARDS’de PEEP seçimi oksijenasyon faydası ile hemodinamik etkiler dengelenerek yapılmalıdır.",
        "en": "PEEP selection in ARDS should balance oxygenation benefit against hemodynamic effects.",
        "importance": "high",
        "tags": ["peep_selection", "ards", "oxygenation_benefit", "hemodynamics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 110
    {
        "id": "aba_advanced_spot_110",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "Refrakter hipoksemide tüp pozisyonu, sekresyon, pnömotoraks ve ventilatör ayarları hızla kontrol edilmelidir.",
        "en": "In refractory hypoxemia, tube position, secretions, pneumothorax, and ventilator settings should be checked rapidly.",
        "importance": "high",
        "tags": ["refractory_hypoxemia", "tube_position", "secretions", "pneumothorax", "ventilator_settings"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 111
    {
        "id": "aba_advanced_spot_111",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "ICU Delirium & Sedation",
        "tr": "Yoğun bakımda gereksiz derin sedasyon mekanik ventilasyon süresini ve deliryum riskini artırabilir.",
        "en": "Unnecessary deep sedation in the ICU may increase duration of mechanical ventilation and delirium risk.",
        "importance": "high",
        "tags": ["icu_sedation", "deep_sedation_hazards", "mechanical_ventilation_duration", "delirium_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 112
    {
        "id": "aba_advanced_spot_112",
        "exam": "ABA_ADVANCED",
        "topic": "Intensive Care",
        "subtopic": "ICU Delirium & Sedation",
        "tr": "Deliryum önlenmesinde ağrı kontrolü, uyku düzeni, erken mobilizasyon ve benzodiazepinden kaçınma önemlidir.",
        "en": "Delirium prevention includes pain control, sleep optimization, early mobilization, and avoidance of benzodiazepines.",
        "importance": "high",
        "tags": ["delirium_prevention", "pain_control", "sleep_optimization", "early_mobilization", "avoid_benzodiazepines"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 113
    {
        "id": "aba_advanced_spot_113",
        "exam": "ABA_ADVANCED",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure",
        "tr": "Akut böbrek hasarında renal perfüzyonu korumak, nefrotoksinlerden kaçınmak ve volüm durumunu optimize etmek gerekir.",
        "en": "In acute kidney injury, renal perfusion should be maintained, nephrotoxins avoided, and volume status optimized.",
        "importance": "high",
        "tags": ["acute_kidney_injury", "aki_prevention", "renal_perfusion", "avoid_nephrotoxins"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 114
    {
        "id": "aba_advanced_spot_114",
        "exam": "ABA_ADVANCED",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure",
        "tr": "Böbrek yetmezliğinde ilaç dozları renal eliminasyon ve aktif metabolitler açısından yeniden değerlendirilmelidir.",
        "en": "In renal failure, drug doses should be reassessed for renal elimination and active metabolites.",
        "importance": "high",
        "tags": ["renal_failure_pharmacology", "drug_dosing", "renal_elimination", "active_metabolites"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 115
    {
        "id": "aba_advanced_spot_115",
        "exam": "ABA_ADVANCED",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure",
        "tr": "Diyaliz hastasında son diyaliz zamanı, potasyum, volüm durumu ve vasküler erişim preoperatif olarak kontrol edilmelidir.",
        "en": "In dialysis patients, timing of last dialysis, potassium, volume status, and vascular access should be checked preoperatively.",
        "importance": "high",
        "tags": ["dialysis_patients", "potassium_levels", "volume_status", "vascular_access"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 116
    {
        "id": "aba_advanced_spot_116",
        "exam": "ABA_ADVANCED",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure",
        "tr": "Üremi trombosit fonksiyon bozukluğu yaparak kanama riskini artırabilir.",
        "en": "Uremia may cause platelet dysfunction and increase bleeding risk.",
        "importance": "high",
        "tags": ["uremia", "platelet_dysfunction", "bleeding_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 117
    {
        "id": "aba_advanced_spot_117",
        "exam": "ABA_ADVANCED",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Failure",
        "tr": "Karaciğer yetmezliğinde koagülopati, hipoglisemi, ensefalopati ve ilaç metabolizmasında gecikme görülebilir.",
        "en": "Liver failure may cause coagulopathy, hypoglycemia, encephalopathy, and delayed drug metabolism.",
        "importance": "high",
        "tags": ["liver_failure", "coagulopathy", "hypoglycemia", "encephalopathy", "delayed_metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 118
    {
        "id": "aba_advanced_spot_118",
        "exam": "ABA_ADVANCED",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Failure",
        "tr": "Sirozda düşük albümin protein bağlanan ilaçların serbest fraksiyonunu artırabilir.",
        "en": "In cirrhosis, low albumin may increase the free fraction of protein-bound drugs.",
        "importance": "high",
        "tags": ["cirrhosis", "low_albumin", "protein_bound_drugs", "free_fraction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 119
    {
        "id": "aba_advanced_spot_119",
        "exam": "ABA_ADVANCED",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Failure",
        "tr": "Portal hipertansiyon varis kanaması, asit ve hemodinamik instabilite riski ile ilişkilidir.",
        "en": "Portal hypertension is associated with variceal bleeding, ascites, and risk of hemodynamic instability.",
        "importance": "high",
        "tags": ["portal_hypertension", "variceal_bleeding", "ascites", "hemodynamic_instability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 120
    {
        "id": "aba_advanced_spot_120",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Organ Transplant",
        "tr": "Karaciğer naklinde reperfüzyon sendromu hipotansiyon, aritmi ve metabolik bozukluklarla seyredebilir.",
        "en": "During liver transplantation, reperfusion syndrome may present with hypotension, arrhythmia, and metabolic abnormalities.",
        "importance": "high",
        "tags": ["liver_transplantation", "reperfusion_syndrome", "hypotension", "arrhythmia", "metabolic_abnormalities"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 121
    {
        "id": "aba_advanced_spot_121",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Organ Transplant",
        "tr": "Reperfüzyon döneminde hiperkalemi, asidoz ve hipotermi kardiyovasküler kollapsa katkı sağlayabilir.",
        "en": "During reperfusion, hyperkalemia, acidosis, and hypothermia may contribute to cardiovascular collapse.",
        "importance": "high",
        "tags": ["liver_transplant_reperfusion", "hyperkalemia", "acidosis", "hypothermia", "cardiovascular_collapse"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 122
    {
        "id": "aba_advanced_spot_122",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Organ Transplant",
        "tr": "Böbrek naklinde yeni greft perfüzyonu için yeterli intravasküler volüm ve hemodinamik stabilite önemlidir.",
        "en": "In kidney transplantation, adequate intravascular volume and hemodynamic stability are important for new graft perfusion.",
        "importance": "high",
        "tags": ["kidney_transplantation", "graft_perfusion", "intravascular_volume", "hemodynamic_stability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 123
    {
        "id": "aba_advanced_spot_123",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Pheochromocytoma",
        "tr": "Feokromositoma cerrahisinde preoperatif alfa blokaj ve volüm replasmanı hemodinamik stabilite için kritiktir.",
        "en": "In pheochromocytoma surgery, preoperative alpha blockade and volume replacement are critical for hemodynamic stability.",
        "importance": "high",
        "tags": ["pheochromocytoma", "preoperative_alpha_blockade", "volume_replacement", "hemodynamic_stability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 124
    {
        "id": "aba_advanced_spot_124",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Pheochromocytoma",
        "tr": "Feokromositomada tümör manipülasyonu sırasında ani hipertansiyon ve aritmi gelişebilir.",
        "en": "In pheochromocytoma, tumor manipulation may cause sudden hypertension and arrhythmia.",
        "importance": "high",
        "tags": ["pheochromocytoma_crisis", "tumor_manipulation", "sudden_hypertension", "arrhythmia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 125
    {
        "id": "aba_advanced_spot_125",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Pheochromocytoma",
        "tr": "Feokromositoma çıkarıldıktan sonra vazodilatasyon ve katekolamin azalmasına bağlı hipotansiyon gelişebilir.",
        "en": "After pheochromocytoma removal, hypotension may occur due to vasodilation and reduced catecholamines.",
        "importance": "high",
        "tags": ["pheochromocytoma_resection", "post_resection_hypotension", "vasodilation", "reduced_catecholamines"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 126
    {
        "id": "aba_advanced_spot_126",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Thyroid Crisis",
        "tr": "Tiroid fırtınası hipertermi, taşikardi, hipertansiyon ve bilinç değişikliği ile seyredebilir.",
        "en": "Thyroid storm may present with hyperthermia, tachycardia, hypertension, and altered mental status.",
        "importance": "high",
        "tags": ["thyroid_storm", "hyperthermia", "tachycardia", "hypertension", "altered_mental_status"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 127
    {
        "id": "aba_advanced_spot_127",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Thyroid Crisis",
        "tr": "Tiroid fırtınasında beta blokaj, antitiroid tedavi, iyot, steroid ve destek tedavisi birlikte planlanır.",
        "en": "Treatment of thyroid storm includes beta blockade, antithyroid therapy, iodine, steroids, and supportive care.",
        "importance": "high",
        "tags": ["thyroid_storm_treatment", "beta_blockade", "antithyroid_therapy", "steroids"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 128
    {
        "id": "aba_advanced_spot_128",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Thyroid Crisis",
        "tr": "Miksödem koması hipotermi, hipoventilasyon, bradikardi ve mental durum bozukluğu ile seyredebilir.",
        "en": "Myxedema coma may present with hypothermia, hypoventilation, bradycardia, and altered mental status.",
        "importance": "high",
        "tags": ["myxedema_coma", "hypothermia", "hypoventilation", "bradycardia", "altered_mental_status"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 129
    {
        "id": "aba_advanced_spot_129",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Diabetic Complications",
        "tr": "Diyabetik ketoasidozda sıvı replasmanı, insülin infüzyonu ve potasyum takibi temel basamaklardır.",
        "en": "In diabetic ketoacidosis, fluid replacement, insulin infusion, and potassium monitoring are core steps.",
        "importance": "high",
        "tags": ["dka_management", "fluid_replacement", "insulin_infusion", "potassium_monitoring"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 130
    {
        "id": "aba_advanced_spot_130",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Diabetic Complications",
        "tr": "Hipoglisemi anestezi altında fark edilmeyebilir ve nörolojik hasara yol açabilir.",
        "en": "Hypoglycemia may be missed under anesthesia and can cause neurological injury.",
        "importance": "high",
        "tags": ["hypoglycemia_under_anesthesia", "masked_hypoglycemia", "neurological_injury_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 131
    {
        "id": "aba_advanced_spot_131",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Adrenal Insufficiency",
        "tr": "Adrenal yetmezlik perioperatif dönemde refrakter hipotansiyon ve hipoglisemi ile kendini gösterebilir.",
        "en": "Adrenal insufficiency may present perioperatively with refractory hypotension and hypoglycemia.",
        "importance": "high",
        "tags": ["adrenal_insufficiency", "refractory_hypotension", "hypoglycemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 132
    {
        "id": "aba_advanced_spot_132",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Carcinoid Crisis",
        "tr": "Karsinoid krizde flushing, bronkospazm ve ciddi hemodinamik instabilite görülebilir.",
        "en": "Carcinoid crisis may cause flushing, bronchospasm, and severe hemodynamic instability.",
        "importance": "high",
        "tags": ["carcinoid_crisis", "flushing", "bronchospasm", "hemodynamic_instability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 133
    {
        "id": "aba_advanced_spot_133",
        "exam": "ABA_ADVANCED",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Carcinoid Crisis",
        "tr": "Karsinoid sendromda tümör manipülasyonu kriz tetikleyebilir ve oktreotid hazırlığı gerekebilir.",
        "en": "In carcinoid syndrome, tumor manipulation may trigger crisis and octreotide preparation may be required.",
        "importance": "high",
        "tags": ["carcinoid_syndrome", "tumor_manipulation", "crisis_trigger", "octreotide_prevention"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 134
    {
        "id": "aba_advanced_spot_134",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Cardiovascular Complications",
        "tr": "Postoperatif miyokard hasarı sessiz seyredebilir; yüksek riskli hastalarda troponin takibi yararlı olabilir.",
        "en": "Postoperative myocardial injury may be silent; troponin monitoring may be useful in high-risk patients.",
        "importance": "high",
        "tags": ["postoperative_myocardial_injury", "silent_ischemia", "troponin_monitoring", "high_risk_patients"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 135
    {
        "id": "aba_advanced_spot_135",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Cardiovascular Complications",
        "tr": "Postoperatif göğüs ağrısında miyokard iskemisi, pulmoner emboli, pnömotoraks ve aort patolojisi düşünülmelidir.",
        "en": "In postoperative chest pain, myocardial ischemia, pulmonary embolism, pneumothorax, and aortic pathology should be considered.",
        "importance": "high",
        "tags": ["postoperative_chest_pain", "differential_diagnosis", "myocardial_ischemia", "pulmonary_embolism", "pneumothorax"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 136
    {
        "id": "aba_advanced_spot_136",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Respiratory Complications",
        "tr": "Postoperatif hipoksemi atelektazi, opioid etkisi, rezidüel blok, aspirasyon veya pulmoner emboliye bağlı olabilir.",
        "en": "Postoperative hypoxemia may be due to atelectasis, opioid effect, residual block, aspiration, or pulmonary embolism.",
        "importance": "high",
        "tags": ["postoperative_hypoxemia", "atelectasis", "opioid_effect", "residual_block", "aspiration", "pulmonary_embolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 137
    {
        "id": "aba_advanced_spot_137",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Respiratory Complications",
        "tr": "Rezidüel nöromüsküler blok hava yolu obstrüksiyonu, aspirasyon ve hipoksemi riskini artırır.",
        "en": "Residual neuromuscular blockade increases the risk of airway obstruction, aspiration, and hypoxemia.",
        "importance": "high",
        "tags": ["residual_neuromuscular_blockade", "airway_obstruction", "aspiration_risk", "hypoxemia_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 138
    {
        "id": "aba_advanced_spot_138",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Respiratory Complications",
        "tr": "TOF oranının 0.9’un altında olması klinik olarak anlamlı rezidüel blok riskini gösterir.",
        "en": "A TOF ratio below 0.9 indicates risk of clinically significant residual blockade.",
        "importance": "high",
        "tags": ["tof_ratio", "residual_blockade", "clinical_significance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 139
    {
        "id": "aba_advanced_spot_139",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Delirium & Agitation",
        "tr": "Postoperatif deliryum yaşlı hastalarda morbidite, mortalite ve hastanede kalış süresini artırır.",
        "en": "Postoperative delirium increases morbidity, mortality, and hospital length of stay in elderly patients.",
        "importance": "high",
        "tags": ["postoperative_delirium", "elderly_patients", "morbidity", "mortality", "hospital_stay"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 140
    {
        "id": "aba_advanced_spot_140",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Delirium & Agitation",
        "tr": "Postoperatif ajitasyon hipoksi, hipoglisemi, ağrı, mesane distansiyonu veya deliryumdan kaynaklanabilir.",
        "en": "Postoperative agitation may be caused by hypoxia, hypoglycemia, pain, bladder distension, or delirium.",
        "importance": "high",
        "tags": ["postoperative_agitation", "differential_diagnosis", "hypoxia", "hypoglycemia", "pain", "bladder_distension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 141
    {
        "id": "aba_advanced_spot_141",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "PONV",
        "tr": "PONV aspirasyon, dehidratasyon, yara ayrılması ve taburculuk gecikmesine neden olabilir.",
        "en": "PONV may cause aspiration, dehydration, wound dehiscence, and delayed discharge.",
        "importance": "high",
        "tags": ["ponv_consequences", "aspiration_risk", "dehydration", "wound_dehiscence", "delayed_discharge"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 142
    {
        "id": "aba_advanced_spot_142",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "PONV",
        "tr": "Yüksek PONV riskinde farklı mekanizmalardan etki eden multimodal antiemetikler tercih edilir.",
        "en": "In high PONV risk, multimodal antiemetics with different mechanisms are preferred.",
        "importance": "high",
        "tags": ["ponv_prevention", "high_risk_ponv", "multimodal_antiemetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 143
    {
        "id": "aba_advanced_spot_143",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Obesity & OSA",
        "tr": "OSA hastalarında opioid, benzodiazepin ve derin sedasyon postoperatif solunum depresyonu riskini artırır.",
        "en": "In patients with OSA, opioids, benzodiazepines, and deep sedation increase postoperative respiratory depression risk.",
        "importance": "high",
        "tags": ["osa", "postoperative_respiratory_depression", "opioid_sensitivity", "avoid_benzodiazepines"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 144
    {
        "id": "aba_advanced_spot_144",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Obesity & OSA",
        "tr": "Obez hastada postoperatif hipoventilasyon ve havayolu obstrüksiyonu riski artmıştır.",
        "en": "In obese patients, the risk of postoperative hypoventilation and airway obstruction is increased.",
        "importance": "high",
        "tags": ["obese_patients", "postoperative_hypoventilation", "airway_obstruction_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 145
    {
        "id": "aba_advanced_spot_145",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Geriatric Frailty",
        "tr": "Yaşlı hastalarda frailty (kırılganlık), postoperatif komplikasyon ve fonksiyon kaybı açısından güçlü bir risk göstergesidir.",
        "en": "Frailty is a strong risk marker for postoperative complications and functional decline in elderly patients.",
        "importance": "high",
        "tags": ["elderly_frailty", "postoperative_complications", "functional_decline"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 146
    {
        "id": "aba_advanced_spot_146",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "ERAS",
        "tr": "ERAS protokolleri erken mobilizasyon, multimodal analjezi ve gereksiz tüp/drenlerden kaçınmayı destekler.",
        "en": "ERAS protocols support early mobilization, multimodal analgesia, and avoidance of unnecessary tubes and drains.",
        "importance": "high",
        "tags": ["eras", "early_mobilization", "multimodal_analgesia", "tubes_and_drains"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 147
    {
        "id": "aba_advanced_spot_147",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Urinary Retention",
        "tr": "Postoperatif üriner retansiyon spinal anestezi, opioid kullanımı ve ileri yaşla ilişkili olabilir.",
        "en": "Postoperative urinary retention may be associated with spinal anesthesia, opioid use, and advanced age.",
        "importance": "high",
        "tags": ["postoperative_urinary_retention", "spinal_anesthesia_complications", "opioid_effects", "advanced_age"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 148
    {
        "id": "aba_advanced_spot_148",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Thermoregulation & Shivering",
        "tr": "Postoperatif titreme oksijen tüketimini ve kardiyak yükü artırabilir.",
        "en": "Postoperative shivering may increase oxygen consumption and cardiac workload.",
        "importance": "high",
        "tags": ["postoperative_shivering", "oxygen_consumption", "cardiac_workload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 149
    {
        "id": "aba_advanced_spot_149",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Thermoregulation & Shivering",
        "tr": "Hipotermi koagülopati, yara enfeksiyonu ve ilaç etkilerinde uzama riskini artırır.",
        "en": "Hypothermia increases the risk of coagulopathy, wound infection, and prolonged drug effects.",
        "importance": "high",
        "tags": ["hypothermia_hazards", "coagulopathy", "wound_infection", "prolonged_drug_effects"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 150
    {
        "id": "aba_advanced_spot_150",
        "exam": "ABA_ADVANCED",
        "topic": "Postoperative Care",
        "subtopic": "Postoperative Handover",
        "tr": "Güvenli postoperatif devir teslim hava yolu, solunum, dolaşım, analjezi, kanama ve özel riskleri içermelidir.",
        "en": "Safe postoperative handover should include airway, breathing, circulation, analgesia, bleeding, and special risks.",
        "importance": "high",
        "tags": ["postoperative_handover", "patient_safety", "clinical_communication"],
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
