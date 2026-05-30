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
    # 001
    {
        "id": "edaic_viva_spot_001",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Beklenen zor havayolunda cevabımı, oksijenasyonu kaybetmeden havayolunu güvenceye alma hedefi üzerine kurarım.",
        "en": "In anticipated difficult airway, I frame my answer around securing the airway without losing oxygenation.",
        "importance": "high",
        "tags": ["anticipated_difficult_airway", "oxygenation_preservation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 002
    {
        "id": "edaic_viva_spot_002",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Zor havayolu planımda her zaman primer plan, yedek plan ve acil ön boyun erişimi planı bulunur.",
        "en": "My difficult airway plan always includes a primary plan, a backup plan, and an emergency front-of-neck access plan.",
        "importance": "high",
        "tags": ["difficult_airway_plan", "front_of_neck_access", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 003
    {
        "id": "edaic_viva_spot_003",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Zor havayolunda entübasyon başarısından önce oksijenasyonun sürdürülmesini vurgularım.",
        "en": "In difficult airway management, I emphasize maintaining oxygenation before successful intubation.",
        "importance": "high",
        "tags": ["oxygenation_priority", "difficult_airway", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 004
    {
        "id": "edaic_viva_spot_004",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Uyanık entübasyonu özellikle hem zor entübasyon hem zor ventilasyon beklenen hastada düşünürüm.",
        "en": "I consider awake intubation especially when both difficult intubation and difficult ventilation are expected.",
        "importance": "high",
        "tags": ["awake_intubation", "difficult_ventilation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 005
    {
        "id": "edaic_viva_spot_005",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Başarısız entübasyonda aynı tekniği tekrarlamak yerine erken strateji değişikliğini savunurum.",
        "en": "After failed intubation, I advocate early change of strategy rather than repeating the same technique.",
        "importance": "high",
        "tags": ["failed_intubation", "change_of_strategy", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 006
    {
        "id": "edaic_viva_spot_006",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "CICO",
        "tr": "CICO senaryosunda cevabım, gecikmeden acil ön boyun erişimine geçilmesi gerektiği üzerine olmalıdır.",
        "en": "In a CICO scenario, my answer should focus on moving without delay to emergency front-of-neck access.",
        "importance": "high",
        "tags": ["cico", "front_of_neck_access", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 007
    {
        "id": "edaic_viva_spot_007",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Laryngospasm",
        "tr": "Laringospazmda ilk cevabım uyarıyı kesmek, %100 oksijen, CPAP ve jaw thrust uygulamaktır.",
        "en": "In laryngospasm, my first response is to stop stimulation, apply 100% oxygen, CPAP, and jaw thrust.",
        "importance": "high",
        "tags": ["laryngospasm", "jaw_thrust", "cpap", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 008
    {
        "id": "edaic_viva_spot_008",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Laryngospasm",
        "tr": "Tam laringospazmda ventilasyon sağlanamıyorsa küçük doz süksinilkolin veya tam paralizi gerekebileceğini belirtirim.",
        "en": "In complete laryngospasm with inability to ventilate, I state that small-dose succinylcholine or full paralysis may be required.",
        "importance": "high",
        "tags": ["complete_laryngospasm", "succinylcholine", "paralysis_step", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 009
    {
        "id": "edaic_viva_spot_009",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Aspiration",
        "tr": "Aspirasyon şüphesinde önceliğim oksijenasyon, havayolunun temizlenmesi ve bronkospazmın tedavisidir.",
        "en": "In suspected aspiration, my priorities are oxygenation, airway clearance, and treatment of bronchospasm.",
        "importance": "high",
        "tags": ["aspiration", "airway_clearance", "bronchospasm_treatment", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 010
    {
        "id": "edaic_viva_spot_010",
        "exam": "EDAIC_VIVA",
        "topic": "Airway Management",
        "subtopic": "Aspiration",
        "tr": "Aspirasyon sonrası antibiyotiğin rutin değil, klinik enfeksiyon bulgularına göre değerlendirileceğini söylerim.",
        "en": "After aspiration, I state that antibiotics are not routine and should depend on clinical signs of infection.",
        "importance": "high",
        "tags": ["aspiration", "antibiotics_rationale", "clinical_infection_signs", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 011
    {
        "id": "edaic_viva_spot_011",
        "exam": "EDAIC_VIVA",
        "topic": "Respiratory Care",
        "subtopic": "Hypoxemia",
        "tr": "İntraoperatif hipoksemide ilk yaklaşımım FiO₂’yi 1.0 yapmak ve hastayı manuel ventile ederek değerlendirmektir.",
        "en": "In intraoperative hypoxemia, my first approach is to set FiO₂ to 1.0 and assess the patient with manual ventilation.",
        "importance": "high",
        "tags": ["hypoxemia", "manual_ventilation", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 012
    {
        "id": "edaic_viva_spot_012",
        "exam": "EDAIC_VIVA",
        "topic": "Respiratory Care",
        "subtopic": "Hypoxemia",
        "tr": "Hipoksemide tüp pozisyonu, devre, ventilasyon, bronkospazm, pnömotoraks ve aspirasyonu sistematik olarak kontrol ederim.",
        "en": "In hypoxemia, I systematically check tube position, circuit, ventilation, bronchospasm, pneumothorax, and aspiration.",
        "importance": "high",
        "tags": ["hypoxemia", "systematic_check", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 013
    {
        "id": "edaic_viva_spot_013",
        "exam": "EDAIC_VIVA",
        "topic": "Monitoring",
        "subtopic": "ETCO₂ Loss",
        "tr": "Ani ETCO₂ kaybında tüp çıkması, devre ayrılması, kardiyak arrest ve masif pulmoner emboliyi hızla düşünürüm.",
        "en": "In sudden loss of ETCO₂, I rapidly consider tube dislodgement, circuit disconnection, cardiac arrest, and massive pulmonary embolism.",
        "importance": "high",
        "tags": ["etco2_loss", "cardiac_arrest", "circuit_disconnection", "viva_differential_diagnosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 014
    {
        "id": "edaic_viva_spot_014",
        "exam": "EDAIC_VIVA",
        "topic": "Monitoring",
        "subtopic": "Airway Pressures",
        "tr": "Yüksek havayolu basıncında peak ve plateau basınç ayrımıyla direnç ve kompliyans sorununu ayırırım.",
        "en": "In high airway pressure, I use the peak and plateau pressure distinction to separate resistance from compliance problems.",
        "importance": "high",
        "tags": ["airway_pressure_elevation", "peak_pressure", "plateau_pressure", "differential_diagnosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 015
    {
        "id": "edaic_viva_spot_015",
        "exam": "EDAIC_VIVA",
        "topic": "Respiratory Care",
        "subtopic": "Bronkospazm",
        "tr": "Bronkospazm cevabımda derin anestezi, inhale beta-agonist, %100 oksijen ve tetikleyicinin kaldırılmasını vurgularım.",
        "en": "In bronchospasm, I emphasize deepening anesthesia, inhaled beta-agonist, 100% oxygen, and removal of the trigger.",
        "importance": "high",
        "tags": ["bronchospasm_treatment", "beta_agonist", "deepening_anesthesia", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 016
    {
        "id": "edaic_viva_spot_016",
        "exam": "EDAIC_VIVA",
        "topic": "Respiratory Care",
        "subtopic": "Bronkospazm",
        "tr": "Şiddetli bronkospazmda sessiz akciğerin iyiye değil, kritik obstrüksiyona işaret edebileceğini söylerim.",
        "en": "In severe bronchospasm, I state that a silent chest may indicate critical obstruction rather than improvement.",
        "importance": "high",
        "tags": ["silent_chest", "severe_bronchospasm", "critical_obstruction", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 017
    {
        "id": "edaic_viva_spot_017",
        "exam": "EDAIC_VIVA",
        "topic": "Respiratory Care",
        "subtopic": "Tension Pneumothorax",
        "tr": "Tansiyon pnömotoraksta hipoksemi, hipotansiyon ve yüksek havayolu basıncının birlikte olabileceğini belirtirim.",
        "en": "In tension pneumothorax, hypoxemia, hypotension, and high airway pressure may occur together.",
        "importance": "high",
        "tags": ["tension_pneumothorax", "hypotension", "high_airway_pressures", "clinical_triad"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 018
    {
        "id": "edaic_viva_spot_018",
        "exam": "EDAIC_VIVA",
        "topic": "Respiratory Care",
        "subtopic": "Tension Pneumothorax",
        "tr": "Tansiyon pnömotoraks şüphesinde görüntüleme beklemeden dekompresyon gerekebileceğini söylerim.",
        "en": "If tension pneumothorax is suspected, I state that decompression may be required without waiting for imaging.",
        "importance": "high",
        "tags": ["tension_pneumothorax", "needle_decompression", "clinical_diagnosis", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 019
    {
        "id": "edaic_viva_spot_019",
        "exam": "EDAIC_VIVA",
        "topic": "Hemodynamic Management",
        "subtopic": "Hypotension",
        "tr": "İntraoperatif hipotansiyonda ilk olarak ölçümü doğrular, perfüzyonu değerlendirir ve cerrahi alanı kontrol ederim.",
        "en": "In intraoperative hypotension, I first confirm the measurement, assess perfusion, and check the surgical field.",
        "importance": "high",
        "tags": ["hypotension", "measurement_confirmation", "surgical_field_check", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 020
    {
        "id": "edaic_viva_spot_020",
        "exam": "EDAIC_VIVA",
        "topic": "Hemodynamic Management",
        "subtopic": "Hypotension",
        "tr": "Hipotansiyon ayırıcı tanımda hipovolemi, kanama, anestezi derinliği, vazodilatasyon, aritmi ve anafilaksi bulunur.",
        "en": "My differential diagnosis for hypotension includes hypovolemia, bleeding, anesthetic depth, vasodilation, arrhythmia, and anaphylaxis.",
        "importance": "high",
        "tags": ["hypotension", "viva_differential_diagnosis", "hemodynamic_instability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 021
    {
        "id": "edaic_viva_spot_021",
        "exam": "EDAIC_VIVA",
        "topic": "Hemodynamic Management",
        "subtopic": "Hypotension",
        "tr": "Hipotansiyonda yalnızca kan basıncını değil, doku perfüzyonunu ve altta yatan nedeni hedeflerim.",
        "en": "In hypotension, I target not only blood pressure but also tissue perfusion and the underlying cause.",
        "importance": "high",
        "tags": ["hypotension_management", "tissue_perfusion", "causal_treatment", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 022
    {
        "id": "edaic_viva_spot_022",
        "exam": "EDAIC_VIVA",
        "topic": "Hemodynamic Management",
        "subtopic": "Hemorrhage",
        "tr": "Kanamaya bağlı hipotansiyonda cerrahi kontrol, dengeli transfüzyon ve ısı korunmasını birlikte planlarım.",
        "en": "In bleeding-related hypotension, I plan surgical control, balanced transfusion, and temperature preservation together.",
        "importance": "high",
        "tags": ["hemorrhagic_shock", "surgical_control", "balanced_transfusion", "thermoregulation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 023
    {
        "id": "edaic_viva_spot_023",
        "exam": "EDAIC_VIVA",
        "topic": "Hemodynamic Management",
        "subtopic": "Vasoactive Support",
        "tr": "Vazodilatasyona bağlı hipotansiyonda vazopressör seçimini kalp hızı, kontraktilite ve klinik bağlama göre yaparım.",
        "en": "In vasodilatory hypotension, I choose the vasopressor according to heart rate, contractility, and clinical context.",
        "importance": "high",
        "tags": ["vasodilatory_shock", "vasopressor_selection", "clinical_context", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 024
    {
        "id": "edaic_viva_spot_024",
        "exam": "EDAIC_VIVA",
        "topic": "Hemodynamic Management",
        "subtopic": "Vasoactive Support",
        "tr": "Bradikardi ve hipotansiyon birlikteyse atropin, vazopressör veya adrenalin ihtiyacını klinik ciddiyete göre değerlendiririm.",
        "en": "If bradycardia and hypotension occur together, I assess the need for atropine, vasopressor, or epinephrine according to severity.",
        "importance": "high",
        "tags": ["bradycardia_hypotension", "atropine", "epinephrine_need", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 025
    {
        "id": "edaic_viva_spot_025",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilaksi şüphesinde deri bulguları olmasa bile tanıyı dışlamam.",
        "en": "In suspected anaphylaxis, I do not exclude the diagnosis even if skin signs are absent.",
        "importance": "high",
        "tags": ["anaphylaxis", "skin_signs_absent", "clinical_intuition", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 026
    {
        "id": "edaic_viva_spot_026",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Anaphylaxis",
        "tr": "Perioperatif anafilakside ilk tedavim adrenalin, %100 oksijen, hızlı sıvı ve tetikleyicinin kesilmesidir.",
        "en": "In perioperative anaphylaxis, my first treatment is epinephrine, 100% oxygen, rapid fluids, and stopping the trigger.",
        "importance": "high",
        "tags": ["anaphylaxis_treatment", "epinephrine", "rapid_fluid_resuscitation", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 027
    {
        "id": "edaic_viva_spot_027",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilakside adrenalin uygulamasını geciktirmem; antihistaminik ve steroidleri yardımcı tedavi olarak görürüm.",
        "en": "I do not delay epinephrine in anaphylaxis; antihistamines and steroids are adjunctive treatments.",
        "importance": "high",
        "tags": ["anaphylaxis", "epinephrine_priority", "adjunctive_therapy", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 028
    {
        "id": "edaic_viva_spot_028",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilaksi sonrası serum triptaz örneğinin tanıyı destekleyebileceğini ama akut tedaviyi geciktirmemesi gerektiğini belirtirim.",
        "en": "I state that serum tryptase sampling after anaphylaxis may support diagnosis but must not delay acute treatment.",
        "importance": "high",
        "tags": ["anaphylaxis_diagnosis", "serum_tryptase", "treatment_priority", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 029
    {
        "id": "edaic_viva_spot_029",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermide erken ipucu olarak açıklanamayan ETCO₂ artışını mutlaka söylerim.",
        "en": "In malignant hyperthermia, I always mention unexplained ETCO₂ rise as an early clue.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "etco2_elevation", "early_diagnostic_clue", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 030
    {
        "id": "edaic_viva_spot_030",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi şüphesinde volatil ajanları ve süksinilkolini derhal keserim.",
        "en": "If malignant hyperthermia is suspected, I immediately stop volatile agents and succinylcholine.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "stopping_triggers", "volatile_agents", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 031
    {
        "id": "edaic_viva_spot_031",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi yönetiminde dantrolen, aktif soğutma, hiperkalemi tedavisi ve yoğun bakım takibini vurgularım.",
        "en": "In malignant hyperthermia management, I emphasize dantrolene, active cooling, treatment of hyperkalemia, and ICU follow-up.",
        "importance": "high",
        "tags": ["malignant_hyperthermia_treatment", "dantrolene", "active_cooling", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 032
    {
        "id": "edaic_viva_spot_032",
        "exam": "EDAIC_VIVA",
        "topic": "Perioperative Crises",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermide asıl hedefin tetikleyiciyi kesmek ve kas içi kalsiyum salınımını azaltmak olduğunu açıklarım.",
        "en": "In malignant hyperthermia, I explain that the key goals are stopping the trigger and reducing intracellular calcium release.",
        "importance": "high",
        "tags": ["pathophysiology_goal", "intracellular_calcium", "dantrolene_mechanism", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 033
    {
        "id": "edaic_viva_spot_033",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "LAST",
        "tr": "LAST şüphesinde lokal anestezik uygulamasını durdurur, yardım çağırır ve havayolu ile dolaşımı desteklerim.",
        "en": "In suspected LAST, I stop local anesthetic injection, call for help, and support airway and circulation.",
        "importance": "high",
        "tags": ["last", "local_anesthetic_toxicity", "support_airway_circulation", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 034
    {
        "id": "edaic_viva_spot_034",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "LAST",
        "tr": "LAST tedavisinde lipid emülsiyonun erken düşünülmesi gerektiğini belirtirim.",
        "en": "In LAST treatment, I state that lipid emulsion should be considered early.",
        "importance": "high",
        "tags": ["last_management", "lipid_emulsion_therapy", "early_consideration", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 035
    {
        "id": "edaic_viva_spot_035",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "LAST",
        "tr": "LAST sırasında nöbet kontrolünde kardiyovasküler depresyonu artırabilecek yüksek doz ajanlardan kaçınmayı vurgularım.",
        "en": "During LAST, I emphasize avoiding high doses of agents that may worsen cardiovascular depression during seizure control.",
        "importance": "high",
        "tags": ["last_seizure_control", "avoid_cardiac_depressants", "anticonvulsants", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 036
    {
        "id": "edaic_viva_spot_036",
        "exam": "EDAIC_VIVA",
        "topic": "Transfusion Medicine",
        "subtopic": "Massive Transfusion",
        "tr": "Masif kanamada cevabım hasar kontrol resüsitasyonu, ısı korunması ve koagülasyonun hedefe yönelik yönetimi üzerine olur.",
        "en": "In massive bleeding, my answer focuses on damage control resuscitation, temperature preservation, and goal-directed coagulation management.",
        "importance": "high",
        "tags": ["massive_bleeding", "damage_control_resuscitation", "goal_directed_coagulation", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 037
    {
        "id": "edaic_viva_spot_037",
        "exam": "EDAIC_VIVA",
        "topic": "Transfusion Medicine",
        "subtopic": "Massive Transfusion",
        "tr": "Masif transfüzyonda hipokalsemi, hipotermi, asidoz ve koagülopatiyi aktif olarak takip ederim.",
        "en": "During massive transfusion, I actively monitor hypocalcemia, hypothermia, acidosis, and coagulopathy.",
        "importance": "high",
        "tags": ["massive_transfusion_complications", "hypocalcemia", "coagulopathy_monitoring", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 038
    {
        "id": "edaic_viva_spot_038",
        "exam": "EDAIC_VIVA",
        "topic": "Transfusion Medicine",
        "subtopic": "Transfusion Reactions",
        "tr": "Transfüzyon reaksiyonu şüphesinde transfüzyonu durdurur, damar yolunu açık tutar ve kan bankasını bilgilendiririm.",
        "en": "If transfusion reaction is suspected, I stop the transfusion, keep IV access open, and notify the blood bank.",
        "importance": "high",
        "tags": ["transfusion_reaction", "stopping_transfusion", "notify_blood_bank", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 039
    {
        "id": "edaic_viva_spot_039",
        "exam": "EDAIC_VIVA",
        "topic": "Transfusion Medicine",
        "subtopic": "Transfusion Reactions",
        "tr": "TRALI ile TACO ayrımında volüm yükü, kardiyak bulgular ve klinik bağlamı değerlendiririm.",
        "en": "To distinguish TRALI from TACO, I assess fluid overload, cardiac findings, and clinical context.",
        "importance": "high",
        "tags": ["trali_vs_taco", "fluid_overload_assessment", "differential_diagnosis", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 040
    {
        "id": "edaic_viva_spot_040",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Pulmonary Embolism",
        "tr": "Pulmoner emboli şüphesinde ani ETCO₂ düşüşü, hipoksemi, hipotansiyon ve sağ kalp yüklenmesini birlikte düşünürüm.",
        "en": "In suspected pulmonary embolism, I consider sudden ETCO₂ fall, hypoxemia, hypotension, and right heart strain together.",
        "importance": "high",
        "tags": ["pulmonary_embolism", "etco2_fall", "right_heart_strain", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 041
    {
        "id": "edaic_viva_spot_041",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Venous Air Embolism",
        "tr": "Venöz hava embolisinde cerraha haber verir, alanın suyla kapatılmasını ve hava girişinin durdurulmasını isterim.",
        "en": "In venous air embolism, I inform the surgeon and ask for flooding of the field and stopping air entry.",
        "importance": "high",
        "tags": ["venous_air_embolism", "surgical_communication", "flooding_surgical_field", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 042
    {
        "id": "edaic_viva_spot_042",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Venous Air Embolism",
        "tr": "Venöz hava embolisinde %100 oksijen, hemodinamik destek ve mümkünse aspirasyon seçeneklerini belirtirim.",
        "en": "In venous air embolism, I mention 100% oxygen, hemodynamic support, and aspiration if feasible.",
        "importance": "high",
        "tags": ["venous_air_embolism_management", "oxygenation", "air_aspiration", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 043
    {
        "id": "edaic_viva_spot_043",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Crisis Communication",
        "tr": "Kriz yönetiminde yüksek sesle düşünme, görev dağılımı ve kapalı döngü iletişimi kullanırım.",
        "en": "In crisis management, I use thinking aloud, task allocation, and closed-loop communication.",
        "importance": "high",
        "tags": ["crisis_management", "thinking_aloud", "closed_loop_communication", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 044
    {
        "id": "edaic_viva_spot_044",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Viva cevabında önce hayatı tehdit eden nedenleri dışlar, sonra ayrıntılı ayırıcı tanıya geçerim.",
        "en": "In viva answers, I first exclude life-threatening causes, then move to a detailed differential diagnosis.",
        "importance": "high",
        "tags": ["viva_tactics", "prioritized_differential", "life_threatening_first", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 045
    {
        "id": "edaic_viva_spot_045",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Her intraoperatif krizde monitör hatası olasılığını kontrol ederim ancak gerçek hasta bozulmasını varsayarak tedaviyi geciktirmem.",
        "en": "In every intraoperative crisis, I check for monitor error but do not delay treatment while assuming true patient deterioration.",
        "importance": "high",
        "tags": ["monitor_artifact_check", "patient_deterioration_first", "safety_first_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 046
    {
        "id": "edaic_viva_spot_046",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Kötüleşen hastada yardım istemeyi zayıflık değil, güvenli anestezi yönetiminin parçası olarak ifade ederim.",
        "en": "In a deteriorating patient, I present calling for help as part of safe anesthetic management, not as weakness.",
        "importance": "high",
        "tags": ["calling_for_help", "patient_safety_culture", "crm_principles", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 047
    {
        "id": "edaic_viva_spot_047",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Crisis Communication",
        "tr": "Kritik olayda cerrahla iletişim kurar, cerrahi stimülasyonun veya kanamanın etkisini hızla değerlendiririm.",
        "en": "During a critical event, I communicate with the surgeon and rapidly assess the effect of surgical stimulation or bleeding.",
        "importance": "high",
        "tags": ["surgeon_communication", "surgical_stimulation", "bleeding_rate_check", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 048
    {
        "id": "edaic_viva_spot_048",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Viva’da tedavi planımı ABC yaklaşımıyla yapılandırmak cevabımı netleştirir.",
        "en": "Structuring my treatment plan with an ABC approach makes my viva answer clearer.",
        "importance": "high",
        "tags": ["abc_approach", "structured_answering", "viva_tactics", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 049
    {
        "id": "edaic_viva_spot_049",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Ciddi komplikasyon sonrası hastayı stabilize ettikten sonra dokümantasyon, bilgilendirme ve takip planını belirtirim.",
        "en": "After stabilizing the patient following a serious complication, I mention documentation, disclosure, and follow-up planning.",
        "importance": "high",
        "tags": ["post_stabilization_plan", "documentation", "disclosure", "follow_up_care", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 050
    {
        "id": "edaic_viva_spot_050",
        "exam": "EDAIC_VIVA",
        "topic": "Crisis Management",
        "subtopic": "Viva Tactics",
        "tr": "Oral sınavda net, önceliklendirilmiş ve güvenlik odaklı cevap vermek, ezber bilgi kadar önemlidir.",
        "en": "In oral exams, giving clear, prioritized, and safety-focused answers is as important as factual knowledge.",
        "importance": "high",
        "tags": ["viva_success_factors", "safety_mindset", "clear_prioritized_answers", "viva_verbal_strategy"],
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
