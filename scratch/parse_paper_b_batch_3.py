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
        "id": "edaic_b_spot_101",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Intracranial Pressure",
        "tr": "Kafa içi basınç artışında hipoksi, hiperkapni, hipotansiyon ve venöz drenaj bozukluğu önlenmelidir.",
        "en": "In increased intracranial pressure, hypoxia, hypercapnia, hypotension, and impaired venous drainage should be avoided.",
        "importance": "high",
        "tags": ["icp", "hypoxia", "hypercapnia", "venous_drainage"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 102
    {
        "id": "edaic_b_spot_102",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Cerebral Perfusion Pressure",
        "tr": "Serebral perfüzyon basıncı MAP eksi intrakraniyal basınç olarak hesaplanır.",
        "en": "Cerebral perfusion pressure is calculated as MAP minus intracranial pressure.",
        "importance": "high",
        "tags": ["cpp", "map", "icp", "calculation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 103
    {
        "id": "edaic_b_spot_103",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Cerebral Blood Flow",
        "tr": "Hiperkapni serebral vazodilatasyon yaparak intrakraniyal basıncı artırabilir.",
        "en": "Hypercapnia may increase intracranial pressure by causing cerebral vasodilation.",
        "importance": "high",
        "tags": ["hypercapnia", "vasodilation", "icp"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 104
    {
        "id": "edaic_b_spot_104",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Hyperventilation",
        "tr": "Kısa süreli kontrollü hiperventilasyon intrakraniyal basıncı azaltabilir ancak aşırı hipokapni serebral iskemiye yol açabilir.",
        "en": "Short-term controlled hyperventilation may reduce intracranial pressure, but excessive hypocapnia can cause cerebral ischemia.",
        "importance": "high",
        "tags": ["hyperventilation", "hypocapnia", "cerebral_ischemia", "icp"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 105
    {
        "id": "edaic_b_spot_105",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Pharmacology",
        "tr": "Propofol serebral metabolik hızı ve serebral kan akımını azaltarak nöroanestezide avantaj sağlar.",
        "en": "Propofol is useful in neuroanesthesia because it reduces cerebral metabolic rate and cerebral blood flow.",
        "importance": "high",
        "tags": ["propofol", "cmro2", "cbf", "neuroanesthesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 106
    {
        "id": "edaic_b_spot_106",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Pharmacology",
        "tr": "Volatil anestezikler doza bağlı serebral vazodilatasyon yapabilir.",
        "en": "Volatile anesthetics may cause dose-dependent cerebral vasodilation.",
        "importance": "high",
        "tags": ["volatile_anesthetics", "vasodilation", "cbf"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 107
    {
        "id": "edaic_b_spot_107",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Venous Air Embolism",
        "tr": "Oturur pozisyondaki nörocerrahide venöz hava embolisi riski artar.",
        "en": "The sitting position in neurosurgery increases the risk of venous air embolism.",
        "importance": "high",
        "tags": ["sitting_position", "vae", "venous_air_embolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 108
    {
        "id": "edaic_b_spot_108",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Venous Air Embolism",
        "tr": "Venöz hava embolisinde ani ETCO₂ düşüşü, hipoksemi ve hemodinamik bozulma görülebilir.",
        "en": "Venous air embolism may present with sudden ETCO₂ decrease, hypoxemia, and hemodynamic instability.",
        "importance": "high",
        "tags": ["vae", "etco2_drop", "hypoxemia", "hemodynamic_instability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 109
    {
        "id": "edaic_b_spot_109",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Carotid Endarterectomy",
        "tr": "Karotis cerrahisinde serebral perfüzyon ve kan basıncı yönetimi nörolojik sonuç açısından kritiktir.",
        "en": "During carotid surgery, cerebral perfusion and blood pressure management are critical for neurological outcome.",
        "importance": "high",
        "tags": ["carotid_surgery", "cerebral_perfusion", "blood_pressure", "neurological_outcome"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 110
    {
        "id": "edaic_b_spot_110",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuroanesthesia",
        "subtopic": "Awake Craniotomy",
        "tr": "Uyanık kraniyotomide hasta iş birliği, havayolu erişimi ve nöbet yönetimi önceden planlanmalıdır.",
        "en": "In awake craniotomy, patient cooperation, airway access, and seizure management should be planned in advance.",
        "importance": "high",
        "tags": ["awake_craniotomy", "patient_cooperation", "airway_access", "seizure_management"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 111
    {
        "id": "edaic_b_spot_111",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda hipoksemi en sık V/Q uyumsuzluğu ve şant artışına bağlıdır.",
        "en": "During one-lung ventilation, hypoxemia is most commonly due to V/Q mismatch and increased shunt.",
        "importance": "high",
        "tags": ["olv", "hypoxemia", "vq_mismatch", "shunt"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 112
    {
        "id": "edaic_b_spot_112",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda tüp pozisyonu hipoksemi değerlendirmesinde erken kontrol edilmelidir.",
        "en": "During one-lung ventilation, tube position should be checked early when evaluating hypoxemia.",
        "importance": "high",
        "tags": ["olv", "tube_position", "hypoxemia_evaluation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 113
    {
        "id": "edaic_b_spot_113",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda bağımlı akciğere PEEP ve ventilasyonsuz akciğere CPAP oksijenasyonu iyileştirebilir.",
        "en": "During one-lung ventilation, PEEP to the dependent lung and CPAP to the nonventilated lung may improve oxygenation.",
        "importance": "high",
        "tags": ["olv", "peep", "cpap", "oxygenation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 114
    {
        "id": "edaic_b_spot_114",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Lateral Decubitus",
        "tr": "Toraks cerrahisinde lateral dekübit pozisyon ventilasyon ve perfüzyon dağılımını değiştirir.",
        "en": "In thoracic surgery, the lateral decubitus position changes the distribution of ventilation and perfusion.",
        "importance": "high",
        "tags": ["lateral_decubitus", "ventilation_perfusion", "thoracic_surgery"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 115
    {
        "id": "edaic_b_spot_115",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Double-Lumen Tube",
        "tr": "Çift lümenli tüp pozisyonu fiberoptik bronkoskopi ile doğrulanmalıdır.",
        "en": "Double-lumen tube position should be confirmed with fiberoptic bronchoscopy.",
        "importance": "high",
        "tags": ["dlt", "double_lumen_tube", "fiberoptic_bronchoscopy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 116
    {
        "id": "edaic_b_spot_116",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Complications",
        "tr": "Pnömotoraks veya tansiyon pnömotoraks ani hipoksemi ve yüksek havayolu basıncı yapabilir.",
        "en": "Pneumothorax or tension pneumothorax may cause sudden hypoxemia and high airway pressure.",
        "importance": "high",
        "tags": ["pneumothorax", "tension_pneumothorax", "hypoxemia", "airway_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 117
    {
        "id": "edaic_b_spot_117",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Analgesia",
        "tr": "Torasik epidural analjezi torakotomi sonrası ağrı kontrolünde etkili olabilir.",
        "en": "Thoracic epidural analgesia can be effective for pain control after thoracotomy.",
        "importance": "high",
        "tags": ["thoracic_epidural", "thoracotomy", "pain_control"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 118
    {
        "id": "edaic_b_spot_118",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "Obstructive Sleep Apnea",
        "tr": "Obstrüktif uyku apnesi olan hastalarda opioidler solunum depresyonu riskini artırır.",
        "en": "In patients with obstructive sleep apnea, opioids increase the risk of respiratory depression.",
        "importance": "high",
        "tags": ["osa", "opioids", "respiratory_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 119
    {
        "id": "edaic_b_spot_119",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "Multimodal Analgesia",
        "tr": "Postoperatif ağrı tedavisinde multimodal analjezi opioid gereksinimini azaltabilir.",
        "en": "Multimodal analgesia may reduce opioid requirements in postoperative pain management.",
        "importance": "high",
        "tags": ["multimodal_analgesia", "opioid_sparing", "postoperative_pain"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 120
    {
        "id": "edaic_b_spot_120",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "Multimodal Analgesia",
        "tr": "Parasetamol, NSAİİ ve rejyonal teknikler multimodal analjezinin temel bileşenleri olabilir.",
        "en": "Paracetamol, NSAIDs, and regional techniques may be key components of multimodal analgesia.",
        "importance": "high",
        "tags": ["paracetamol", "nsaids", "regional_techniques", "analgesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 121
    {
        "id": "edaic_b_spot_121",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "NSAIDs",
        "tr": "NSAİİ kullanımı renal yetmezlik, kanama riski ve gastrointestinal yan etkiler açısından değerlendirilmelidir.",
        "en": "NSAID use should be evaluated for renal impairment, bleeding risk, and gastrointestinal adverse effects.",
        "importance": "high",
        "tags": ["nsaids", "renal_impairment", "bleeding_risk", "gi_effects"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 122
    {
        "id": "edaic_b_spot_122",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "Patient-Controlled Analgesia",
        "tr": "PCA kullanımında hasta eğitimi ve solunum depresyonu takibi önemlidir.",
        "en": "Patient education and monitoring for respiratory depression are important during PCA use.",
        "importance": "high",
        "tags": ["pca", "patient_education", "respiratory_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 123
    {
        "id": "edaic_b_spot_123",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "Epidural Opioids",
        "tr": "Epidural opioidler geç solunum depresyonu yapabilir.",
        "en": "Epidural opioids may cause delayed respiratory depression.",
        "importance": "high",
        "tags": ["epidural_opioids", "delayed_respiratory_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 124
    {
        "id": "edaic_b_spot_124",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "Opioid Tolerance",
        "tr": "Kronik opioid kullanan hastalarda opioid toleransı nedeniyle postoperatif analjezi gereksinimi artabilir.",
        "en": "Patients with chronic opioid use may require more postoperative analgesia due to opioid tolerance.",
        "importance": "high",
        "tags": ["chronic_opioids", "opioid_tolerance", "postoperative_analgesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 125
    {
        "id": "edaic_b_spot_125",
        "exam": "EDAIC_PAPER_B",
        "topic": "PONV Prophylaxis",
        "subtopic": "Risk Factors",
        "tr": "PONV risk faktörleri kadın cinsiyet, sigara içmeme, PONV/taşıt tutması öyküsü ve postoperatif opioid kullanımıdır.",
        "en": "PONV risk factors include female sex, nonsmoking status, history of PONV or motion sickness, and postoperative opioid use.",
        "importance": "high",
        "tags": ["ponv", "risk_factors", "opioids", "motion_sickness"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 126
    {
        "id": "edaic_b_spot_126",
        "exam": "EDAIC_PAPER_B",
        "topic": "PONV Prophylaxis",
        "subtopic": "Prophylaxis",
        "tr": "PONV profilaksisi risk düzeyine göre tekli veya multimodal antiemetiklerle planlanmalıdır.",
        "en": "PONV prophylaxis should be planned with single or multimodal antiemetics according to risk level.",
        "importance": "high",
        "tags": ["ponv_prophylaxis", "antiemetics", "multimodal"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 127
    {
        "id": "edaic_b_spot_127",
        "exam": "EDAIC_PAPER_B",
        "topic": "PONV Prophylaxis",
        "subtopic": "Pharmacology",
        "tr": "Ondansetron 5-HT3 reseptör antagonisti olarak PONV profilaksisi ve tedavisinde kullanılır.",
        "en": "Ondansetron is a 5-HT3 receptor antagonist used for PONV prophylaxis and treatment.",
        "importance": "high",
        "tags": ["ondansetron", "5ht3_antagonist", "ponv_treatment"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 128
    {
        "id": "edaic_b_spot_128",
        "exam": "EDAIC_PAPER_B",
        "topic": "PONV Prophylaxis",
        "subtopic": "Pharmacology",
        "tr": "Deksametazon PONV profilaksisinde indüksiyon sonrası erken dönemde verildiğinde etkilidir.",
        "en": "Dexamethasone is effective for PONV prophylaxis when given early after induction.",
        "importance": "high",
        "tags": ["dexamethasone", "ponv_prophylaxis", "induction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 129
    {
        "id": "edaic_b_spot_129",
        "exam": "EDAIC_PAPER_B",
        "topic": "PONV Prophylaxis",
        "subtopic": "Prophylaxis",
        "tr": "Total intravenöz anestezi ve opioid azaltıcı stratejiler PONV riskini azaltabilir.",
        "en": "Total intravenous anesthesia and opioid-sparing strategies may reduce PONV risk.",
        "importance": "high",
        "tags": ["tiva", "opioid_sparing", "ponv_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 130
    {
        "id": "edaic_b_spot_130",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "Sedation",
        "tr": "Yoğun bakım hastasında sedasyon hedefi düzenli olarak değerlendirilmeli ve gereksiz derin sedasyondan kaçınılmalıdır.",
        "en": "In ICU patients, sedation goals should be assessed regularly and unnecessary deep sedation should be avoided.",
        "importance": "high",
        "tags": ["sedation_goals", "deep_sedation", "icu"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 131
    {
        "id": "edaic_b_spot_131",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "Delirium",
        "tr": "Deliryum riski yaşlılık, sepsis, hipoksi, ağrı, benzodiazepinler ve uyku bozukluğu ile artar.",
        "en": "Delirium risk increases with advanced age, sepsis, hypoxia, pain, benzodiazepines, and sleep disturbance.",
        "importance": "high",
        "tags": ["delirium", "sepsis", "hypoxia", "benzodiazepines"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 132
    {
        "id": "edaic_b_spot_132",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "ARDS’de düşük tidal volüm ve sınırlı plato basıncı akciğer koruyucu ventilasyonun temelidir.",
        "en": "In ARDS, low tidal volume and limited plateau pressure are the basis of lung-protective ventilation.",
        "importance": "high",
        "tags": ["ards", "low_tidal_volume", "plateau_pressure", "lung_protective_ventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 133
    {
        "id": "edaic_b_spot_133",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "ARDS",
        "tr": "ARDS’de prone pozisyon ağır hipoksemide oksijenasyonu ve sağkalımı iyileştirebilir.",
        "en": "In ARDS, prone positioning may improve oxygenation and survival in severe hypoxemia.",
        "importance": "high",
        "tags": ["ards", "prone_positioning", "oxygenation", "survival"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 134
    {
        "id": "edaic_b_spot_134",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "Sepsis",
        "tr": "Sepsiste erken antibiyotik, sıvı resüsitasyonu ve vazopressör tedavi zamanında başlatılmalıdır.",
        "en": "In sepsis, early antibiotics, fluid resuscitation, and vasopressor therapy should be initiated promptly.",
        "importance": "high",
        "tags": ["sepsis", "antibiotics", "fluid_resuscitation", "vasopressors"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 135
    {
        "id": "edaic_b_spot_135",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "Septic Shock",
        "tr": "Septik şokta ilk tercih vazopressör genellikle noradrenalindir.",
        "en": "In septic shock, norepinephrine is usually the first-choice vasopressor.",
        "importance": "high",
        "tags": ["septic_shock", "norepinephrine", "vasopressor"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 136
    {
        "id": "edaic_b_spot_136",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "Monitoring",
        "tr": "Laktat yüksekliği doku hipoperfüzyonu ve hastalık şiddeti hakkında bilgi verebilir.",
        "en": "Elevated lactate may provide information about tissue hypoperfusion and disease severity.",
        "importance": "high",
        "tags": ["lactate", "tissue_hypoperfusion", "severity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 137
    {
        "id": "edaic_b_spot_137",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "Acute Kidney Injury",
        "tr": "Akut böbrek hasarında nefrotoksik ilaçlardan kaçınmak ve renal perfüzyonu korumak önemlidir.",
        "en": "In acute kidney injury, avoiding nephrotoxic drugs and maintaining renal perfusion are important.",
        "importance": "high",
        "tags": ["aki", "nephrotoxic_drugs", "renal_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 138
    {
        "id": "edaic_b_spot_138",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intensive Care",
        "subtopic": "Electrolyte Imbalances",
        "tr": "Hiperkalemi EKG değişiklikleri ile birlikteyse acil kalsiyum tedavisi düşünülmelidir.",
        "en": "If hyperkalemia is associated with ECG changes, emergency calcium therapy should be considered.",
        "importance": "high",
        "tags": ["hyperkalemia", "ecg_changes", "calcium_therapy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 139
    {
        "id": "edaic_b_spot_139",
        "exam": "EDAIC_PAPER_B",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Diabetic Ketoacidosis",
        "tr": "Diyabetik ketoasidozda sıvı tedavisi, insülin ve potasyum takibi temel yaklaşımdır.",
        "en": "In diabetic ketoacidosis, fluid therapy, insulin, and potassium monitoring are the core approach.",
        "importance": "high",
        "tags": ["dka", "diabetic_ketoacidosis", "insulin", "potassium"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 140
    {
        "id": "edaic_b_spot_140",
        "exam": "EDAIC_PAPER_B",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Hyperglycemia",
        "tr": "Perioperatif hiperglisemi enfeksiyon ve yara iyileşme sorunları ile ilişkilidir.",
        "en": "Perioperative hyperglycemia is associated with infection and impaired wound healing.",
        "importance": "high",
        "tags": ["hyperglycemia", "perioperative_infection", "wound_healing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 141
    {
        "id": "edaic_b_spot_141",
        "exam": "EDAIC_PAPER_B",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Adrenal Insufficiency",
        "tr": "Adrenal yetmezliği olan veya uzun süre steroid kullanan hastalarda perioperatif steroid ihtiyacı değerlendirilmelidir.",
        "en": "Perioperative steroid requirement should be assessed in patients with adrenal insufficiency or long-term steroid use.",
        "importance": "high",
        "tags": ["adrenal_insufficiency", "steroids", "stress_dose"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 142
    {
        "id": "edaic_b_spot_142",
        "exam": "EDAIC_PAPER_B",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Thyroid Storm",
        "tr": "Tiroid fırtınası hipertermi, taşikardi, hipertansiyon ve mental durum değişikliği ile seyredebilir.",
        "en": "Thyroid storm may present with hyperthermia, tachycardia, hypertension, and altered mental status.",
        "importance": "high",
        "tags": ["thyroid_storm", "hyperthermia", "tachycardia", "hypertension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 143
    {
        "id": "edaic_b_spot_143",
        "exam": "EDAIC_PAPER_B",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Pheochromocytoma",
        "tr": "Feokromositoma cerrahisinde preoperatif alfa blokaj ve volüm optimizasyonu önemlidir.",
        "en": "In pheochromocytoma surgery, preoperative alpha blockade and volume optimization are important.",
        "importance": "high",
        "tags": ["pheochromocytoma", "alpha_blockade", "volume_optimization"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 144
    {
        "id": "edaic_b_spot_144",
        "exam": "EDAIC_PAPER_B",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Carcinoid Syndrome",
        "tr": "Karsinoid sendromda tümör manipülasyonu ciddi hipotansiyon, bronkospazm ve flushing oluşturabilir.",
        "en": "In carcinoid syndrome, tumor manipulation may cause severe hypotension, bronchospasm, and flushing.",
        "importance": "high",
        "tags": ["carcinoid_syndrome", "bronchospasm", "flushing", "hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 145
    {
        "id": "edaic_b_spot_145",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thermoregulation",
        "subtopic": "Hypothermia",
        "tr": "Hipotermi koagülopati, enfeksiyon ve ilaç etkilerinde uzama riskini artırır.",
        "en": "Hypothermia increases the risk of coagulopathy, infection, and prolonged drug effects.",
        "importance": "high",
        "tags": ["hypothermia", "coagulopathy", "perioperative_infection", "prolonged_drug_effects"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 146
    {
        "id": "edaic_b_spot_146",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thermoregulation",
        "subtopic": "Shivering",
        "tr": "Postoperatif titreme oksijen tüketimini ve kardiyak yükü artırabilir.",
        "en": "Postoperative shivering may increase oxygen consumption and cardiac workload.",
        "importance": "high",
        "tags": ["shivering", "oxygen_consumption", "cardiac_workload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 147
    {
        "id": "edaic_b_spot_147",
        "exam": "EDAIC_PAPER_B",
        "topic": "Geriatric Anesthesia",
        "subtopic": "Pharmacodynamics",
        "tr": "Yaşlı hastalarda farmakodinamik duyarlılık artabilir ve ilaç dozları titrasyonla verilmelidir.",
        "en": "In elderly patients, pharmacodynamic sensitivity may increase and drug doses should be titrated.",
        "importance": "high",
        "tags": ["geriatric_pharmacology", "drug_titration", "pharmacodynamics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 148
    {
        "id": "edaic_b_spot_148",
        "exam": "EDAIC_PAPER_B",
        "topic": "Geriatric Anesthesia",
        "subtopic": "Frailty",
        "tr": "Frailty perioperatif komplikasyon ve taburculuk sonrası fonksiyon kaybı riskini artırır.",
        "en": "Frailty increases the risk of perioperative complications and functional decline after discharge.",
        "importance": "high",
        "tags": ["frailty", "perioperative_complications", "functional_decline"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 149
    {
        "id": "edaic_b_spot_149",
        "exam": "EDAIC_PAPER_B",
        "topic": "Ambulatory Anesthesia",
        "subtopic": "Discharge Criteria",
        "tr": "Günübirlik cerrahide taburculuk kararı ağrı, bulantı, mobilizasyon ve vital bulgulara göre verilmelidir.",
        "en": "In ambulatory surgery, discharge decisions should be based on pain, nausea, mobilization, and vital signs.",
        "importance": "high",
        "tags": ["ambulatory_surgery", "discharge_criteria", "aldrete_score"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 150
    {
        "id": "edaic_b_spot_150",
        "exam": "EDAIC_PAPER_B",
        "topic": "Respiratory Care",
        "subtopic": "Respiratory Depression",
        "tr": "Postoperatif solunum depresyonu riski opioidler, OSA, yaşlılık ve sedatiflerle artar.",
        "en": "The risk of postoperative respiratory depression increases with opioids, OSA, advanced age, and sedatives.",
        "importance": "high",
        "tags": ["respiratory_depression", "opioids", "osa", "advanced_age"],
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
