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
    # 051
    {
        "id": "aba_advanced_spot_051",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Intracranial Pressure",
        "tr": "Artmış intrakraniyal basınçta hipoksi, hiperkapni, hipotansiyon ve juguler venöz drenaj engeli önlenmelidir.",
        "en": "In increased intracranial pressure, hypoxia, hypercapnia, hypotension, and impaired jugular venous drainage should be avoided.",
        "importance": "high",
        "tags": ["icp_elevation", "jugular_venous_drainage", "cerebral_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 052
    {
        "id": "aba_advanced_spot_052",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Cerebral Perfusion",
        "tr": "Serebral perfüzyon basıncı MAP eksi intrakraniyal basınç olarak hesaplanır.",
        "en": "Cerebral perfusion pressure is calculated as MAP minus intracranial pressure.",
        "importance": "high",
        "tags": ["cpp_calculation", "map", "icp", "cerebral_perfusion_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 053
    {
        "id": "aba_advanced_spot_053",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Cerebral Physiology",
        "tr": "Hiperkapni serebral vazodilatasyon yaparak intrakraniyal basıncı artırabilir.",
        "en": "Hypercapnia may increase intracranial pressure by causing cerebral vasodilation.",
        "importance": "high",
        "tags": ["hypercapnia_effects", "cerebral_vasodilation", "icp_elevation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 054
    {
        "id": "aba_advanced_spot_054",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Cerebral Physiology",
        "tr": "Aşırı hiperventilasyon serebral vazokonstriksiyon ile iskemi riskini artırabilir.",
        "en": "Excessive hyperventilation may increase ischemia risk through cerebral vasoconstriction.",
        "importance": "high",
        "tags": ["excessive_hyperventilation", "cerebral_vasoconstriction", "ischemia_hazard"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 055
    {
        "id": "aba_advanced_spot_055",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Neuropharmacology",
        "tr": "Propofol serebral metabolik hızı ve serebral kan akımını azaltır.",
        "en": "Propofol reduces cerebral metabolic rate and cerebral blood flow.",
        "importance": "high",
        "tags": ["propofol", "cmro2_reduction", "cbf_reduction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 056
    {
        "id": "aba_advanced_spot_056",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Neuropharmacology",
        "tr": "Volatil anestezikler doza bağlı serebral vazodilatasyon yapabilir.",
        "en": "Volatile anesthetics may cause dose-dependent cerebral vasodilation.",
        "importance": "high",
        "tags": ["volatile_anesthetics", "cerebral_vasodilation", "dose_dependent"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 057
    {
        "id": "aba_advanced_spot_057",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Brain Relaxation",
        "tr": "Kraniyotomi sırasında beyin gevşekliği ventilasyon, venöz drenaj, anestezik seçim ve osmoterapi ile etkilenir.",
        "en": "Brain relaxation during craniotomy is influenced by ventilation, venous drainage, anesthetic choice, and osmotherapy.",
        "importance": "high",
        "tags": ["brain_relaxation", "craniotomy", "venous_drainage", "anesthetic_choice"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 058
    {
        "id": "aba_advanced_spot_058",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Osmotherapy",
        "tr": "Mannitol osmoterapi sağlar ancak hipovolemi ve elektrolit bozukluğu oluşturabilir.",
        "en": "Mannitol provides osmotherapy but may cause hypovolemia and electrolyte disturbances.",
        "importance": "high",
        "tags": ["mannitol", "osmotherapy", "hypovolemia_risk", "electrolyte_imbalance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 059
    {
        "id": "aba_advanced_spot_059",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Osmotherapy",
        "tr": "Hipertonik salin intrakraniyal basıncı azaltabilir ve hipotansif hastada mannitole alternatif olabilir.",
        "en": "Hypertonic saline can reduce intracranial pressure and may be an alternative to mannitol in hypotensive patients.",
        "importance": "high",
        "tags": ["hypertonic_saline", "icp_reduction", "hypotensive_patient"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 060
    {
        "id": "aba_advanced_spot_060",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Venous Air Embolism",
        "tr": "Oturur pozisyondaki nörocerrahide venöz hava embolisi riski artar.",
        "en": "The sitting position in neurosurgery increases the risk of venous air embolism.",
        "importance": "high",
        "tags": ["sitting_position", "neurosurgery", "venous_air_embolism", "vae_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 061
    {
        "id": "aba_advanced_spot_061",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Venous Air Embolism",
        "tr": "Venöz hava embolisinde ani ETCO₂ düşüşü, hipoksemi ve hipotansiyon görülebilir.",
        "en": "Venous air embolism may present with sudden ETCO₂ decrease, hypoxemia, and hypotension.",
        "importance": "high",
        "tags": ["venous_air_embolism", "sudden_etco2_drop", "hypoxemia", "hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 062
    {
        "id": "aba_advanced_spot_062",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Awake Craniotomy",
        "tr": "Uyanık kraniyotomide hasta seçimi, iletişim, havayolu erişimi ve nöbet yönetimi kritik öneme sahiptir.",
        "en": "In awake craniotomy, patient selection, communication, airway access, and seizure management are critical.",
        "importance": "high",
        "tags": ["awake_craniotomy", "patient_selection", "airway_access", "seizure_management"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 063
    {
        "id": "aba_advanced_spot_063",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Cerebral Perfusion",
        "tr": "Karotis cerrahisinde serebral perfüzyonun korunması ve kan basıncı hedefleri nörolojik sonucu etkiler.",
        "en": "During carotid surgery, preservation of cerebral perfusion and blood pressure targets affect neurological outcome.",
        "importance": "high",
        "tags": ["carotid_surgery", "cerebral_perfusion", "blood_pressure_targets"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 064
    {
        "id": "aba_advanced_spot_064",
        "exam": "ABA_ADVANCED",
        "topic": "Neuroanesthesia",
        "subtopic": "Cerebral Perfusion",
        "tr": "İnme riski olan hastada hipotansiyondan kaçınmak serebral perfüzyon için önemlidir.",
        "en": "Avoiding hypotension is important for cerebral perfusion in patients at risk of stroke.",
        "importance": "high",
        "tags": ["stroke_risk", "avoiding_hypotension", "cerebral_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 065
    {
        "id": "aba_advanced_spot_065",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda hipokseminin erken nedenlerinden biri çift lümenli tüp veya bronşiyal bloker malpozisyonudur.",
        "en": "During one-lung ventilation, an early cause of hypoxemia is malposition of the double-lumen tube or bronchial blocker.",
        "importance": "high",
        "tags": ["one_lung_ventilation", "double_lumen_tube", "malposition", "hypoxemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 066
    {
        "id": "aba_advanced_spot_066",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda fiberoptik bronkoskopi tüp pozisyonunu doğrulamak için kullanılmalıdır.",
        "en": "During one-lung ventilation, fiberoptic bronchoscopy should be used to confirm tube position.",
        "importance": "high",
        "tags": ["one_lung_ventilation", "fiberoptic_bronchoscopy", "tube_position_confirmation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 067
    {
        "id": "aba_advanced_spot_067",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Bağımlı akciğere uygun PEEP oksijenasyonu iyileştirebilir ancak aşırı PEEP kan akımını bozabilir.",
        "en": "Appropriate PEEP to the dependent lung may improve oxygenation, but excessive PEEP may impair blood flow.",
        "importance": "high",
        "tags": ["dependent_lung_peep", "oxygenation_improvement", "excessive_peep"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 068
    {
        "id": "aba_advanced_spot_068",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Ventile edilmeyen akciğere CPAP uygulamak tek akciğer ventilasyonunda hipoksemiyi azaltabilir.",
        "en": "CPAP to the nonventilated lung may reduce hypoxemia during one-lung ventilation.",
        "importance": "high",
        "tags": ["nonventilated_lung_cpap", "hypoxemia_reduction", "one_lung_ventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 069
    {
        "id": "aba_advanced_spot_069",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Patient Positioning",
        "tr": "Toraks cerrahisinde lateral dekübit pozisyon ventilasyon ve perfüzyon dağılımını değiştirir.",
        "en": "In thoracic surgery, the lateral decubitus position changes ventilation and perfusion distribution.",
        "importance": "high",
        "tags": ["lateral_decubitus", "ventilation_perfusion_mismatch", "thoracic_surgery"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 070
    {
        "id": "aba_advanced_spot_070",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Pneumonectomy",
        "tr": "Pnömonektomi sonrası aşırı sıvı yükü akut akciğer hasarı riskini artırabilir.",
        "en": "After pneumonectomy, excessive fluid loading may increase the risk of acute lung injury.",
        "importance": "high",
        "tags": ["pneumonectomy", "fluid_overload", "acute_lung_injury", "ali_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 071
    {
        "id": "aba_advanced_spot_071",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Postoperative Analgesia",
        "tr": "Torakotomi sonrası etkili analjezi solunum fonksiyonunu ve öksürmeyi iyileştirir.",
        "en": "Effective analgesia after thoracotomy improves respiratory function and coughing.",
        "importance": "high",
        "tags": ["post_thoracotomy_analgesia", "respiratory_function", "coughing_efficacy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 072
    {
        "id": "aba_advanced_spot_072",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Postoperative Analgesia",
        "tr": "Torasik epidural ve paravertebral blok toraks cerrahisi sonrası analjezi seçenekleridir.",
        "en": "Thoracic epidural and paravertebral block are analgesic options after thoracic surgery.",
        "importance": "high",
        "tags": ["thoracic_epidural", "paravertebral_block", "analgesia_options"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 073
    {
        "id": "aba_advanced_spot_073",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Mediastinal Mass",
        "tr": "Mediastinal kitlede supin pozisyon ve sedasyon havayolu veya kardiyovasküler kollapsı tetikleyebilir.",
        "en": "In mediastinal mass, supine positioning and sedation may trigger airway or cardiovascular collapse.",
        "importance": "high",
        "tags": ["mediastinal_mass", "supine_hazard", "sedation_hazard", "airway_collapse", "cardiovascular_collapse"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 074
    {
        "id": "aba_advanced_spot_074",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Mediastinal Mass",
        "tr": "Mediastinal kitlede spontan ventilasyonun korunması bazı yüksek riskli hastalarda tercih edilebilir.",
        "en": "Maintaining spontaneous ventilation may be preferred in some high-risk patients with mediastinal mass.",
        "importance": "high",
        "tags": ["mediastinal_mass", "spontaneous_ventilation", "high_risk_patients"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 075
    {
        "id": "aba_advanced_spot_075",
        "exam": "ABA_ADVANCED",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Pulmoner hipertansiyonlu hastada hipoksi, hiperkapni, asidoz ve yüksek havayolu basınçlarından kaçınılmalıdır.",
        "en": "In patients with pulmonary hypertension, hypoxia, hypercapnia, acidosis, and high airway pressures should be avoided.",
        "importance": "high",
        "tags": ["pulmonary_hypertension", "pvr_elevation_avoidance", "hypoxia", "acidosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 076
    {
        "id": "aba_advanced_spot_076",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Preeclampsia",
        "tr": "Preeklampside hipertansif yanıt, trombositopeni, renal disfonksiyon ve pulmoner ödem riski değerlendirilmelidir.",
        "en": "In preeclampsia, hypertensive response, thrombocytopenia, renal dysfunction, and pulmonary edema risk should be assessed.",
        "importance": "high",
        "tags": ["preeclampsia", "hypertensive_response", "thrombocytopenia", "pulmonary_edema_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 077
    {
        "id": "aba_advanced_spot_077",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Eclampsia",
        "tr": "Eklampside ilk hedefler havayolu güvenliği, oksijenasyon, nöbet kontrolü ve ciddi hipertansiyonun tedavisidir.",
        "en": "In eclampsia, initial goals are airway safety, oxygenation, seizure control, and treatment of severe hypertension.",
        "importance": "high",
        "tags": ["eclampsia", "airway_safety", "seizure_control", "severe_hypertension_treatment"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 078
    {
        "id": "aba_advanced_spot_078",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Magnesium Therapy",
        "tr": "Magnezyum sülfat nöromüsküler blok süresini uzatabilir ve solunum depresyonu yapabilir.",
        "en": "Magnesium sulfate may prolong neuromuscular blockade and cause respiratory depression.",
        "importance": "high",
        "tags": ["magnesium_sulfate", "neuromuscular_blockade_prolongation", "respiratory_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 079
    {
        "id": "aba_advanced_spot_079",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "HELLP Syndrome",
        "tr": "HELLP sendromunda trombosit sayısı ve koagülasyon durumu nöroaksiyel anestezi öncesi değerlendirilmelidir.",
        "en": "In HELLP syndrome, platelet count and coagulation status should be assessed before neuraxial anesthesia.",
        "importance": "high",
        "tags": ["hellp_syndrome", "platelet_count", "neuraxial_contraindications", "coagulation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 080
    {
        "id": "aba_advanced_spot_080",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Obstetric Hemorrhage",
        "tr": "Obstetrik kanamada uterin atoni, plasenta akreta spektrumu ve koagülopati hızla düşünülmelidir.",
        "en": "In obstetric hemorrhage, uterine atony, placenta accreta spectrum, and coagulopathy should be considered quickly.",
        "importance": "high",
        "tags": ["obstetric_hemorrhage", "uterine_atony", "placenta_accreta", "coagulopathy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 081
    {
        "id": "aba_advanced_spot_081",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Obstetric Hemorrhage",
        "tr": "Plasenta akreta spektrumunda masif kanama riski nedeniyle multidisipliner planlama gerekir.",
        "en": "Placenta accreta spectrum requires multidisciplinary planning because of massive bleeding risk.",
        "importance": "high",
        "tags": ["placenta_accreta_spectrum", "massive_bleeding_risk", "multidisciplinary_planning"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 082
    {
        "id": "aba_advanced_spot_082",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Amniotic Fluid Embolism",
        "tr": "Amniyotik sıvı embolisi ani hipoksemi, hipotansiyon ve koagülasyon bozukluğu (koagülopati) ile ortaya çıkabilir.",
        "en": "Amniotic fluid embolism may present with sudden hypoxemia, hypotension, and coagulopathy.",
        "importance": "high",
        "tags": ["amniotic_fluid_embolism", "afe", "hypoxemia", "hypotension", "coagulopathy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 083
    {
        "id": "aba_advanced_spot_083",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Maternal Resuscitation",
        "tr": "Gebede kardiyak arrestte sol uterin yer değiştirme ve erken perimortem sezaryen düşünülmelidir.",
        "en": "In maternal cardiac arrest, left uterine displacement and early perimortem cesarean delivery should be considered.",
        "importance": "high",
        "tags": ["maternal_cardiac_arrest", "left_uterine_displacement", "perimortem_cesarean_delivery"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 084
    {
        "id": "aba_advanced_spot_084",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Airway Management",
        "tr": "Gebede aspirasyon riski ve hızlı desatürasyon nedeniyle hava yolu planı önceden net olmalıdır.",
        "en": "In pregnancy, the airway plan must be clear in advance because aspiration risk and rapid desaturation are increased.",
        "importance": "high",
        "tags": ["pregnancy_airway", "aspiration_risk", "rapid_desaturation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 085
    {
        "id": "aba_advanced_spot_085",
        "exam": "ABA_ADVANCED",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Spinal Hypotension",
        "tr": "Sezaryende spinal hipotansiyon uteroplasental perfüzyonu azaltabileceği için hızla tedavi edilmelidir.",
        "en": "Spinal hypotension during cesarean delivery should be treated rapidly because it may reduce uteroplacental perfusion.",
        "importance": "high",
        "tags": ["spinal_hypotension", "cesarean_delivery", "uteroplacental_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 086
    {
        "id": "aba_advanced_spot_086",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Physiology",
        "tr": "Pediatrik hastada bradikardi genellikle hipoksiye bağlı ciddi bir uyarı bulgusudur.",
        "en": "In pediatric patients, bradycardia is often a serious warning sign of hypoxia.",
        "importance": "high",
        "tags": ["pediatric_bradycardia", "hypoxia_indicator", "critical_sign"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 087
    {
        "id": "aba_advanced_spot_087",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Physiology",
        "tr": "Bebek ve küçük çocuklarda düşük FRC ve yüksek oksijen tüketimi hızlı desatürasyona neden olur.",
        "en": "In infants and small children, low FRC and high oxygen consumption cause rapid desaturation.",
        "importance": "high",
        "tags": ["pediatric_frc", "oxygen_consumption", "rapid_desaturation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 088
    {
        "id": "aba_advanced_spot_088",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Laryngospasm",
        "tr": "Pediatrik laringospazmda hızlı oksijenasyon, CPAP, jaw thrust ve gerekirse süksinilkolin gerekir.",
        "en": "Pediatric laryngospasm requires rapid oxygenation, CPAP, jaw thrust, and succinylcholine if needed.",
        "importance": "high",
        "tags": ["laryngospasm_management", "cpap", "jaw_thrust", "succinylcholine"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 089
    {
        "id": "aba_advanced_spot_089",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Hemorrhage",
        "tr": "Pediatrik kanamada dolaşım kompansasyonu uzun süre korunabilir; hipotansiyon geç bulgudur.",
        "en": "In pediatric bleeding, circulatory compensation may persist for a long time; hypotension is a late sign.",
        "importance": "high",
        "tags": ["pediatric_bleeding", "circulatory_compensation", "hypotension_late_sign"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 090
    {
        "id": "aba_advanced_spot_090",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Neonatal Resuscitation",
        "tr": "Neonatal resüsitasyonda kalp hızındaki düzelmenin en önemli belirleyicisi etkili ventilasyondur.",
        "en": "In neonatal resuscitation, effective ventilation is the most important determinant of heart rate improvement.",
        "importance": "high",
        "tags": ["neonatal_resuscitation", "effective_ventilation", "heart_rate_improvement"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 091
    {
        "id": "aba_advanced_spot_091",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Prematurity",
        "tr": "Prematüre bebeklerde apne, hipotermi ve hipoglisemi riski perioperatif dönemde artar.",
        "en": "Premature infants have increased perioperative risk of apnea, hypothermia, and hypoglycemia.",
        "importance": "high",
        "tags": ["premature_infants", "apnea_risk", "hypothermia", "hypoglycemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 092
    {
        "id": "aba_advanced_spot_092",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Pharmacology",
        "tr": "Pediatrik hastada ilaç dozları kilogram başına hesaplanmalı ve maksimum dozlar kontrol edilmelidir.",
        "en": "In pediatric patients, drug doses should be calculated per kilogram and maximum doses checked.",
        "importance": "high",
        "tags": ["pediatric_dosing", "weight_based_dosing", "maximum_dosage_limits"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 093
    {
        "id": "aba_advanced_spot_093",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi duyarlılığı olan çocukta tetikleyicisiz anestezi planlanmalıdır.",
        "en": "In children susceptible to malignant hyperthermia, trigger-free anesthesia should be planned.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "trigger_free_anesthesia", "pediatric_mh"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 094
    {
        "id": "aba_advanced_spot_094",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Neuromuscular Diseases",
        "tr": "Duchenne musküler distrofide süksinilkolin ciddi hiperkalemi ve kardiyak arrest riski taşır.",
        "en": "In Duchenne muscular dystrophy, succinylcholine carries a risk of severe hyperkalemia and cardiac arrest.",
        "importance": "high",
        "tags": ["duchenne_muscular_dystrophy", "succinylcholine_hazard", "hyperkalemia", "cardiac_arrest"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 095
    {
        "id": "aba_advanced_spot_095",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Upper Respiratory Infection",
        "tr": "Üst solunum yolu enfeksiyonu olan çocukta laringospazm, bronkospazm ve desatürasyon riski artar.",
        "en": "In children with upper respiratory tract infection, the risk of laryngospasm, bronchospasm, and desaturation increases.",
        "importance": "high",
        "tags": ["upper_respiratory_infection", "uri", "laryngospasm_risk", "bronchospasm_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 096
    {
        "id": "aba_advanced_spot_096",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Ambulatory Discharge",
        "tr": "Pediatrik günübirlik cerrahide taburculuk kararı solunum, ağrı, bulantı ve hidrasyon durumuna göre verilmelidir.",
        "en": "In pediatric ambulatory surgery, discharge decisions should be based on respiration, pain, nausea, and hydration status.",
        "importance": "high",
        "tags": ["pediatric_ambulatory_discharge", "discharge_criteria", "pain", "nausea"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 097
    {
        "id": "aba_advanced_spot_097",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Obesity & OSA",
        "tr": "Obez çocuklarda OSA ve postoperatif havayolu obstrüksiyonu riski artabilir.",
        "en": "In obese children, the risk of OSA and postoperative airway obstruction may increase.",
        "importance": "high",
        "tags": ["pediatric_obesity", "osa_risk", "airway_obstruction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 098
    {
        "id": "aba_advanced_spot_098",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Regional Anesthesia",
        "tr": "Pediatrik nöroaksiyel veya periferik bloklarda lokal anestezik maksimum dozları dikkatle hesaplanmalıdır.",
        "en": "In pediatric neuraxial or peripheral blocks, maximum local anesthetic doses must be carefully calculated.",
        "importance": "high",
        "tags": ["pediatric_regional_anesthesia", "local_anesthetic_dosing", "maximum_limits"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 099
    {
        "id": "aba_advanced_spot_099",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Congenital Heart Disease",
        "tr": "Konjenital kalp hastalığında pulmoner ve sistemik vasküler direnç dengesi hemodinamiyi belirler.",
        "en": "In congenital heart disease, the balance between pulmonary and systemic vascular resistance determines hemodynamics.",
        "importance": "high",
        "tags": ["congenital_heart_disease", "pvr_svr_balance", "hemodynamics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 100
    {
        "id": "aba_advanced_spot_100",
        "exam": "ABA_ADVANCED",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Shunts & Embolism",
        "tr": "Sağdan sola şantı olan çocuklarda hava embolisi riski nedeniyle tüm damar yolları dikkatle havasızlandırılmalıdır.",
        "en": "In children with right-to-left shunt, all intravenous lines must be carefully de-aired because of air embolism risk.",
        "importance": "high",
        "tags": ["right_to_left_shunt", "air_embolism_prevention", "intravenous_lines"],
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
