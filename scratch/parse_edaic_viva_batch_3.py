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
        "id": "edaic_viva_spot_101",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Intracranial Pressure & Perfusion",
        "tr": "Artmış intrakraniyal basınçta cevabımı hipoksi, hiperkapni, hipotansiyon ve venöz drenaj bozukluğunu önleme üzerine kurarım.",
        "en": "In increased intracranial pressure, I frame my answer around preventing hypoxia, hypercapnia, hypotension, and impaired venous drainage.",
        "importance": "high",
        "tags": ["increased_icp_verbal", "venous_drainage_preservation", "avoid_hypocenia_hypotension", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 102
    {
        "id": "edaic_viva_spot_102",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Intracranial Pressure & Perfusion",
        "tr": "Serebral perfüzyon basıncını MAP eksi intrakraniyal basınç olarak tanımlar ve her iki bileşeni de optimize etmeye çalışırım.",
        "en": "I define cerebral perfusion pressure as MAP minus intracranial pressure and try to optimize both components.",
        "importance": "high",
        "tags": ["cpp_definition", "map_optimization", "icp_control", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 103
    {
        "id": "edaic_viva_spot_103",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Intracranial Pressure & Perfusion",
        "tr": "Hiperkapninin serebral vazodilatasyonla intrakraniyal basıncı artırabileceğini belirtirim.",
        "en": "I state that hypercapnia may increase intracranial pressure through cerebral vasodilation.",
        "importance": "high",
        "tags": ["hypercapnia_danger", "cerebral_vasodilation", "icp_elevation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 104
    {
        "id": "edaic_viva_spot_104",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Intracranial Pressure & Perfusion",
        "tr": "Kontrollü hiperventilasyonu yalnızca geçici bir köprü olarak kullanır, aşırı hipokapniden kaçınırım.",
        "en": "I use controlled hyperventilation only as a temporary bridge and avoid excessive hypocapnia.",
        "importance": "high",
        "tags": ["controlled_hyperventilation", "ischemia_hazard", "temporary_bridge", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 105
    {
        "id": "edaic_viva_spot_105",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Intracranial Pressure & Perfusion",
        "tr": "Nöroanestezide hipotansiyonun serebral perfüzyonu bozabileceğini ve hızla düzeltilmesi gerektiğini vurgularım.",
        "en": "In neuroanesthesia, I emphasize that hypotension can impair cerebral perfusion and should be corrected rapidly.",
        "importance": "high",
        "tags": ["neuroanesthesia_hypotension", "cerebral_perfusion_loss", "rapid_restoration", "safety_first_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 106
    {
        "id": "edaic_viva_spot_106",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Brain Relaxation & Osmotherapy",
        "tr": "Kraniyotomi de beyin gevşekliği için baş pozisyonu, venöz drenaj, ventilasyon ve osmoterapiyi birlikte değerlendiririm.",
        "en": "For brain relaxation during craniotomy, I assess head position, venous drainage, ventilation, and osmotherapy together.",
        "importance": "high",
        "tags": ["brain_relaxation_verbal", "head_positioning", "venous_outflow", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 107
    {
        "id": "edaic_viva_spot_107",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Brain Relaxation & Osmotherapy",
        "tr": "Mannitol verirken hipovolemi, hipotansiyon ve elektrolit bozukluklarını takip ederim.",
        "en": "When giving mannitol, I monitor for hypovolemia, hypotension, and electrolyte disturbances.",
        "importance": "high",
        "tags": ["mannitol_monitoring", "hypovolemia", "electrolyte_imbalance", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 108
    {
        "id": "edaic_viva_spot_108",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Brain Relaxation & Osmotherapy",
        "tr": "Hipertonik salinin intrakraniyal basıncı azaltırken intravasküler volümü daha iyi koruyabileceğini belirtirim.",
        "en": "I state that hypertonic saline may reduce intracranial pressure while better preserving intravascular volume.",
        "importance": "high",
        "tags": ["hypertonic_saline_advantage", "icp_reduction", "intravascular_volume_preservation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 109
    {
        "id": "edaic_viva_spot_109",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Venous Air Embolism",
        "tr": "Oturur pozisyonda nörocerrahide venöz hava embolisi riskini aktif olarak bekler ve monitörize ederim.",
        "en": "In sitting neurosurgery, I actively anticipate and monitor for venous air embolism.",
        "importance": "high",
        "tags": ["sitting_neurosurgery", "venous_air_embolism_prevention", "active_monitoring", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 110
    {
        "id": "edaic_viva_spot_110",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Awake Craniotomy",
        "tr": "Uyanık kraniyotomide havayolu erişimi, hasta kooperasyonu, analjezi ve nöbet yönetimini önceden planlarım.",
        "en": "In awake craniotomy, I plan airway access, patient cooperation, analgesia, and seizure management in advance.",
        "importance": "high",
        "tags": ["awake_craniotomy_prep", "airway_access", "seizure_management", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 111
    {
        "id": "edaic_viva_spot_111",
        "exam": "EDAIC_VIVA",
        "topic": "Neuroanesthesia",
        "subtopic": "Carotid Surgery",
        "tr": "Karotis cerrahisinde nörolojik sonucu korumak için serebral perfüzyon ve kan basıncı hedefleri net belirlerim.",
        "en": "In carotid surgery, I clearly define cerebral perfusion and blood pressure targets to protect neurological outcome.",
        "importance": "high",
        "tags": ["carotid_endarterectomy", "cerebral_perfusion", "blood_pressure_targets", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 112
    {
        "id": "edaic_viva_spot_112",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda hipoksemide cevabım önce tüp pozisyonu, sonra ventilasyon ve perfüzyon stratejilerini kontrol etmek olur.",
        "en": "During hypoxemia in one-lung ventilation, my answer is to check tube position first, then ventilation and perfusion strategies.",
        "importance": "high",
        "tags": ["olv_hypoxemia_management", "confirm_tube_position", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 113
    {
        "id": "edaic_viva_spot_113",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Çift lümenli tüp pozisyonunu fiberoptik bronkoskopi ile doğrulamayı belirtirim.",
        "en": "I mention confirming double-lumen tube position with fiberoptic bronchoscopy.",
        "importance": "high",
        "tags": ["dlt_placement_verification", "fiberoptic_bronchoscopy", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 114
    {
        "id": "edaic_viva_spot_114",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda bağımlı akciğere PEEP uygularken hemodinami ve kan akımı etkisini izlerim.",
        "en": "When applying PEEP to the dependent lung during one-lung ventilation, I monitor hemodynamics and blood flow effects.",
        "importance": "high",
        "tags": ["dependent_peep", "hemodynamic_monitoring", "shunt_fraction", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 115
    {
        "id": "edaic_viva_spot_115",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Ventile edilmeyen akciğere CPAP uygulamanın oksijenasyonu artırabileceğini ancak cerrahi alanı etkileyebileceğini söylerim.",
        "en": "I state that CPAP to the nonventilated lung may improve oxygenation but can affect the surgical field.",
        "importance": "high",
        "tags": ["nonventilated_cpap", "shunt_reduction", "surgical_exposure_conflict", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 116
    {
        "id": "edaic_viva_spot_116",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Postoperative Analgesia",
        "tr": "Toraks cerrahisinde etkili analjezinin öksürme, derin solunum ve pulmoner komplikasyonları etkilediğini vurgularım.",
        "en": "In thoracic surgery, I emphasize that effective analgesia affects coughing, deep breathing, and pulmonary complications.",
        "importance": "high",
        "tags": ["thoracotomy_analgesia", "cough_efficacy", "pulmonary_hygiene", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 117
    {
        "id": "edaic_viva_spot_117",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Postoperative Analgesia",
        "tr": "Torasik epidural veya paravertebral blok seçeneklerini cerrahi, antikoagülasyon ve hasta riskine göre değerlendiririm.",
        "en": "I assess thoracic epidural or paravertebral block options according to surgery, anticoagulation, and patient risk.",
        "importance": "high",
        "tags": ["thoracic_epidural", "paravertebral_block", "coagulation_profile_viva", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 118
    {
        "id": "edaic_viva_spot_118",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Mediastinal Mass",
        "tr": "Mediastinal kitlede en büyük korkum sedasyon veya supin pozisyonla hava yolu ya da kardiyovasküler kollapstır.",
        "en": "In mediastinal mass, my greatest concern is airway or cardiovascular collapse with sedation or supine positioning.",
        "importance": "high",
        "tags": ["mediastinal_mass_dangers", "supine_hazard", "sedation_induction_danger", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 119
    {
        "id": "edaic_viva_spot_119",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Mediastinal Mass",
        "tr": "Mediastinal kitlede spontan ventilasyonun korunması ve acil kurtarma planı hazırlanmasını tartışırım.",
        "en": "In mediastinal mass, I discuss maintaining spontaneous ventilation and preparing an emergency rescue plan.",
        "importance": "high",
        "tags": ["spontaneous_ventilation_preservation", "emergency_rigid_bronchoscopy_plan", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 120
    {
        "id": "edaic_viva_spot_120",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Pneumonectomy Complications",
        "tr": "Pnömonektomi sonrası aşırı sıvı yükünden kaçınmayı ve postoperatif akciğer hasarı riskini vurgularım.",
        "en": "After pneumonectomy, I emphasize avoiding excessive fluid loading and the risk of postoperative lung injury.",
        "importance": "high",
        "tags": ["pneumonectomy", "fluid_restriction_strategy", "postoperative_ali_prevention", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 121
    {
        "id": "edaic_viva_spot_121",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Sepsiste cevabımı erken antibiyotik, kaynak kontrolü, sıvı yanıtı değerlendirmesi ve vazopressör desteği üzerine kurarım.",
        "en": "In sepsis, I frame my answer around early antibiotics, source control, assessment of fluid responsiveness, and vasopressor support.",
        "importance": "high",
        "tags": ["sepsis_verbal_flowchart", "source_control", "fluid_responsiveness", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 122
    {
        "id": "edaic_viva_spot_122",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Septik şokta noradrenalinin sıklıkla ilk tercih vazopressör olduğunu belirtirim.",
        "en": "In septic shock, I state that norepinephrine is often the first-choice vasopressor.",
        "importance": "high",
        "tags": ["septic_shock", "norepinephrine_first_line", "vasopressor_pharmacology", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 123
    {
        "id": "edaic_viva_spot_123",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Laktat yüksekliğini tek başına değil, perfüzyon ve klinik bağlamla birlikte yorumlarım.",
        "en": "I interpret elevated lactate together with perfusion and clinical context, not in isolation.",
        "importance": "high",
        "tags": ["lactate_clearance", "perfusion_marker", "clinical_context_interpretation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 124
    {
        "id": "edaic_viva_spot_124",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "ARDS’de akciğer koruyucu ventilasyonu düşük tidal volüm, sınırlı plato basıncı ve uygun PEEP ile açıklarım.",
        "en": "In ARDS, I explain lung-protective ventilation with low tidal volume, limited plateau pressure, and appropriate PEEP.",
        "importance": "high",
        "tags": ["ards_ventilation_guidelines", "low_tidal_volume", "plateau_pressure_ceiling", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 125
    {
        "id": "edaic_viva_spot_125",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "Ağır ARDS’de prone pozisyonun oksijenasyonu ve sağkalımı iyileştirebileceğini belirtirim.",
        "en": "In severe ARDS, I state that prone positioning may improve oxygenation and survival.",
        "importance": "high",
        "tags": ["severe_ards", "prone_positioning_benefits", "v/q_matching", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 126
    {
        "id": "edaic_viva_spot_126",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "Refrakter hipoksemide önce tüp pozisyonu, sekresyon, pnömotoraks ve ventilatör ayarlarını hızla kontrol ederim.",
        "en": "In refractory hypoxemia, I rapidly check tube position, secretions, pneumothorax, and ventilator settings first.",
        "importance": "high",
        "tags": ["refractory_hypoxemia_verbal", "tube_patency", "troubleshooting_vent", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 127
    {
        "id": "edaic_viva_spot_127",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "ICU Delirium & Sedation",
        "tr": "Yoğun bakım sedasyonunda hedefim gereksiz derin sedasyondan kaçınmak ve düzenli sedasyon hedefi belirlemektir.",
        "en": "In ICU sedation, my goal is to avoid unnecessary deep sedation and set regular sedation targets.",
        "importance": "high",
        "tags": ["light_sedation_target", "sedation_scores_rass", "avoid_over_sedation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 128
    {
        "id": "edaic_viva_spot_128",
        "exam": "EDAIC_VIVA",
        "topic": "Intensive Care",
        "subtopic": "ICU Delirium & Sedation",
        "tr": "Deliryum riskini azaltmak için ağrı kontrolü, uyku düzeni, erken mobilizasyon ve benzodiazepinden kaçınmayı söylerim.",
        "en": "To reduce delirium risk, I mention pain control, sleep optimization, early mobilization, and avoidance of benzodiazepines.",
        "importance": "high",
        "tags": ["delirium_reduction_bundle", "early_mobilization", "avoid_benzodiazepines_icu", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 129
    {
        "id": "edaic_viva_spot_129",
        "exam": "EDAIC_VIVA",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure & Hyperkalemia",
        "tr": "Akut böbrek hasarında renal perfüzyonu korur, nefrotoksinlerden kaçınır ve ilaç dozlarını yeniden değerlendiririm.",
        "en": "In acute kidney injury, I maintain renal perfusion, avoid nephrotoxins, and reassess drug doses.",
        "importance": "high",
        "tags": ["aki_renal_protection", "hemodynamic_support", "nephrotoxins_avoidance", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 130
    {
        "id": "edaic_viva_spot_130",
        "exam": "EDAIC_VIVA",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure & Hyperkalemia",
        "tr": "Diyaliz hastasında son diyaliz zamanı, potasyum, volüm durumu ve damar yolu erişimini mutlaka sorgularım.",
        "en": "In dialysis patients, I always ask about timing of last dialysis, potassium, volume status, and vascular access.",
        "importance": "high",
        "tags": ["dialysis_patient_check", "potassium_clearance", "av_fistula_care", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 131
    {
        "id": "edaic_viva_spot_131",
        "exam": "EDAIC_VIVA",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure & Hyperkalemia",
        "tr": "Üremik hastada trombosit fonksiyon bozukluğu nedeniyle kanama riskini değerlendiririm.",
        "en": "In uremic patients, I assess bleeding risk due to platelet dysfunction.",
        "importance": "high",
        "tags": ["uremic_platelet_dysfunction", "bleeding_diathesis", "desmopressin_therapy", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 132
    {
        "id": "edaic_viva_spot_132",
        "exam": "EDAIC_VIVA",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Failure & Hyperkalemia",
        "tr": "Hiperkalemi EKG değişikliğiyle birlikteyse önce membran stabilizasyonu için kalsiyum vermeyi belirtirim.",
        "en": "If hyperkalemia occurs with ECG changes, I first mention calcium for membrane stabilization.",
        "importance": "high",
        "tags": ["hyperkalemia_ecg_changes", "calcium_gluconate_first", "cardiac_membrane_stabilization", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 133
    {
        "id": "edaic_viva_spot_133",
        "exam": "EDAIC_VIVA",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Failure",
        "tr": "Karaciğer yetmezliğinde koagülopati, hipoglisemi, ensefalopati ve ilaç metabolizmasında gecikmeyi vurgularım.",
        "en": "In liver failure, I emphasize coagulopathy, hypoglycemia, encephalopathy, and delayed drug metabolism.",
        "importance": "high",
        "tags": ["liver_failure_implications", "coagulopathy_monitoring", "altered_drug_clearance", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 134
    {
        "id": "edaic_viva_spot_134",
        "exam": "EDAIC_VIVA",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Failure",
        "tr": "Sirozlu hastada düşük albüminin protein bağlanan ilaçların serbest fraksiyonunu artırabileceğini söylerim.",
        "en": "In cirrhosis, I state that low albumin may increase the free fraction of protein-bound drugs.",
        "importance": "high",
        "tags": ["cirrhosis", "hypoalbuminemia_effects", "protein_binding_pharmacology", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 135
    {
        "id": "edaic_viva_spot_135",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Organ Transplant",
        "tr": "Karaciğer naklinde reperfüzyon döneminde hipotansiyon, hiperkalemi, asidoz ve aritmiye hazırlıklı olurum.",
        "en": "During liver transplantation reperfusion, I am prepared for hypotension, hyperkalemia, acidosis, and arrhythmia.",
        "importance": "high",
        "tags": ["liver_transplant_reperfusion_syndrome", "hyperkalemia", "post_reperfusion_shock", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 136
    {
        "id": "edaic_viva_spot_136",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Organ Transplant",
        "tr": "Böbrek naklinde yeni greft perfüzyonu için yeterli volüm, basınç ve hemodinamik stabiliteyi hedeflerim.",
        "en": "In kidney transplantation, I target adequate volume, pressure, and hemodynamic stability for new graft perfusion.",
        "importance": "high",
        "tags": ["kidney_transplant_perfusion", "target_map", "intravascular_volume_optimization", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 137
    {
        "id": "edaic_viva_spot_137",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Pheochromocytoma",
        "tr": "Feokromositoma cerrahisinde preoperatif alfa blokaj ve volüm optimizasyonunun önemini vurgularım.",
        "en": "In pheochromocytoma surgery, I emphasize preoperative alpha blockade and volume optimization.",
        "importance": "high",
        "tags": ["pheochromocytoma_prep", "alfa_blockade_rozen_criteria", "volume_restoration", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 138
    {
        "id": "edaic_viva_spot_138",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Pheochromocytoma",
        "tr": "Feokromositomada tümör manipülasyonu sırasında hipertansiyon ve aritmi, çıkarılma sonrası hipotansiyon beklerim.",
        "en": "In pheochromocytoma, I expect hypertension and arrhythmia during tumor manipulation and hypotension after removal.",
        "importance": "high",
        "tags": ["pheochromocytoma_manipulation", "post_resection_hypotension", "catecholamine_withdrawal", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 139
    {
        "id": "edaic_viva_spot_139",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Thyroid Crisis",
        "tr": "Tiroid fırtınasında hipertermi, taşikardi, hipertansiyon ve bilinç değişikliğini hızlı tanırım.",
        "en": "In thyroid storm, I rapidly recognize hyperthermia, tachycardia, hypertension, and altered mental status.",
        "importance": "high",
        "tags": ["thyroid_storm_signs", "hyperthermia", "altered_consciousness", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 140
    {
        "id": "edaic_viva_spot_140",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Thyroid Crisis",
        "tr": "Tiroid fırtınası tedavisinde beta blokaj, antitiroid ilaç, iyot, steroid ve destek tedavisini birlikte söylerim.",
        "en": "In thyroid storm treatment, I mention beta blockade, antithyroid drug, iodine, steroids, and supportive care together.",
        "importance": "high",
        "tags": ["thyroid_storm_cocktail", "beta_blockers", "propylthiouracil", "hydrocortisone", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 141
    {
        "id": "edaic_viva_spot_141",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Thyroid Crisis",
        "tr": "Miksödem komasında hipoventilasyon, hipotermi, bradikardi ve ilaçlara aşırı duyarlılığı beklerim.",
        "en": "In myxedema coma, I expect hypoventilation, hypothermia, bradycardia, and increased drug sensitivity.",
        "importance": "high",
        "tags": ["myxedema_coma_presentation", "bradycardia", "drug_clearance_depression", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 142
    {
        "id": "edaic_viva_spot_142",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Diabetic Complications",
        "tr": "Diyabetik ketoasidozda sıvı, insülin ve potasyum takibinin temel olduğunu belirtirim.",
        "en": "In diabetic ketoacidosis, I state that fluids, insulin, and potassium monitoring are fundamental.",
        "importance": "high",
        "tags": ["dka_verbal_protocol", "insulin_infusion", "potassium_replacement", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 143
    {
        "id": "edaic_viva_spot_143",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Diabetic Complications",
        "tr": "Hipogliseminin anestezi altında maskelenebileceğini ve nörolojik hasar oluşturabileceğini vurgularım.",
        "en": "I emphasize that hypoglycemia may be masked under anesthesia and can cause neurological injury.",
        "importance": "high",
        "tags": ["masked_hypoglycemia", "intraoperative_glucose_checks", "brain_damage_prevention", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 144
    {
        "id": "edaic_viva_spot_144",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Adrenal Insufficiency",
        "tr": "Adrenal yetmezlikte refrakter hipotansiyon ve hipoglisemi gelişebileceğini, steroid replasmanını düşünürüm.",
        "en": "In adrenal insufficiency, I consider refractory hypotension and hypoglycemia and think of steroid replacement.",
        "importance": "high",
        "tags": ["adrenal_crisis_treatment", "refractory_hypotension", "hydrocortisone_substitution", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 145
    {
        "id": "edaic_viva_spot_145",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Carcinoid Crisis",
        "tr": "Karsinoid krizde flushing, bronkospazm ve ciddi hemodinamik instabiliteyi beklerim.",
        "en": "In carcinoid crisis, I expect flushing, bronchospasm, and severe hemodynamic instability.",
        "importance": "high",
        "tags": ["carcinoid_crisis_presentation", "bronchospasm", "vasodilatory_hypotension", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 146
    {
        "id": "edaic_viva_spot_146",
        "exam": "EDAIC_VIVA",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Carcinoid Crisis",
        "tr": "Karsinoid sendromda tümör manipülasyonu öncesi oktreotid hazırlığını tartışırım.",
        "en": "In carcinoid syndrome, I discuss octreotide preparation before tumor manipulation.",
        "importance": "high",
        "tags": ["carcinoid_syndrome", "octreotide_infusion", "crisis_prevention", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 147
    {
        "id": "edaic_viva_spot_147",
        "exam": "EDAIC_VIVA",
        "topic": "Special Populations",
        "subtopic": "Obesity",
        "tr": "Obez hastada havayolu, preoksijenasyon, ventilasyon ve postoperatif solunum depresyonu riskini birlikte ele alırım.",
        "en": "In obese patients, I address airway, preoxygenation, ventilation, and postoperative respiratory depression risk together.",
        "importance": "high",
        "tags": ["obesity_viva_flow", "ramped_positioning", "atelectasis_prevention", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 148
    {
        "id": "edaic_viva_spot_148",
        "exam": "EDAIC_VIVA",
        "topic": "Special Populations",
        "subtopic": "Obesity",
        "tr": "OSA hastasında opioid ve sedatifleri azaltmayı, postoperatif monitörizasyonu artırmayı planlarım.",
        "en": "In OSA patients, I plan to reduce opioids and sedatives and increase postoperative monitoring.",
        "importance": "high",
        "tags": ["obstructive_sleep_apnea", "opioid_sparing_analgesia", "continuous_monitoring", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 149
    {
        "id": "edaic_viva_spot_149",
        "exam": "EDAIC_VIVA",
        "topic": "Special Populations",
        "subtopic": "Geriatrics",
        "tr": "Yaşlı hastada frailty, polifarmasi, deliryum ve fonksiyon kaybı riskini özellikle sorgularım.",
        "en": "In elderly patients, I specifically ask about frailty, polypharmacy, delirium, and functional decline risk.",
        "importance": "high",
        "tags": ["geriatric_frailty_indices", "polypharmacy_hazards", "cognitive_decline", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 150
    {
        "id": "edaic_viva_spot_150",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Care",
        "subtopic": "Advanced Management",
        "tr": "Yüksek riskli hastada perioperatif planımı yalnızca anestezi tekniğine değil, organ perfüzyonu ve postoperatif bakım hedeflerine göre kurarım.",
        "en": "In high-risk patients, I base my perioperative plan not only on anesthetic technique but also on organ perfusion and postoperative care goals.",
        "importance": "high",
        "tags": ["high_risk_surgery_strategy", "oxygen_delivery_preservation", "icu_postop_plan", "verbal_viva_milestone"],
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
