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
        "id": "edaic_viva_spot_051",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Physiological Changes & General Anesthesia",
        "tr": "Gebede genel anestezi planlarken hızlı desatürasyon, aspirasyon riski ve zor havayolu ihtimalini özellikle vurgularım.",
        "en": "When planning general anesthesia in pregnancy, I emphasize rapid desaturation, aspiration risk, and the possibility of difficult airway.",
        "importance": "high",
        "tags": ["pregnancy_general_anesthesia", "aspiration_risk", "difficult_airway", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 052
    {
        "id": "edaic_viva_spot_052",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Spinal Hypotension",
        "tr": "Sezaryende spinal hipotansiyon gelişirse uteroplasental perfüzyonu korumak için hızlı vazopressör ve sıvı yönetimi uygularım.",
        "en": "If spinal hypotension occurs during cesarean delivery, I rapidly manage vasopressors and fluids to preserve uteroplacental perfusion.",
        "importance": "high",
        "tags": ["spinal_hypotension", "cesarean_delivery", "vasopressor_management", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 053
    {
        "id": "edaic_viva_spot_053",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Physiological Changes & General Anesthesia",
        "tr": "Obstetrik hastada aortokaval basıyı azaltmak için sol uterin yer değiştirmeyi erken uygularım.",
        "en": "In obstetric patients, I apply left uterine displacement early to reduce aortocaval compression.",
        "importance": "high",
        "tags": ["left_uterine_displacement", "aortocaval_compression", "hemodynamic_optimization", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 054
    {
        "id": "edaic_viva_spot_054",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Preeclampsia & Eclampsia",
        "tr": "Preeklampside cevabım kan basıncı kontrolü, nöbet profilaksisi, organ disfonksiyonu ve havayolu ödemi riskini içermelidir.",
        "en": "In preeclampsia, my answer should include blood pressure control, seizure prophylaxis, organ dysfunction, and airway edema risk.",
        "importance": "high",
        "tags": ["preeclampsia", "seizure_prophylaxis", "airway_edema", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 055
    {
        "id": "edaic_viva_spot_055",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Preeclampsia & Eclampsia",
        "tr": "Preeklamptik hastada laringoskopi yanıtının ciddi hipertansiyon ve serebral kanama riski oluşturabileceğini söylerim.",
        "en": "In preeclamptic patients, I state that the laryngoscopic response may cause severe hypertension and risk of cerebral hemorrhage.",
        "importance": "high",
        "tags": ["laryngoscopic_response", "hypertensive_crisis", "cerebral_hemorrhage_risk", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 056
    {
        "id": "edaic_viva_spot_056",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Preeclampsia & Eclampsia",
        "tr": "Eklampside ilk önceliklerim havayolu güvenliği, oksijenasyon, magnezyumla nöbet kontrolü ve ciddi hipertansiyonun tedavisidir.",
        "en": "In eclampsia, my first priorities are airway safety, oxygenation, seizure control with magnesium, and treatment of severe hypertension.",
        "importance": "high",
        "tags": ["eclampsia", "airway_safety", "magnesium_sulfate", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 057
    {
        "id": "edaic_viva_spot_057",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "HELLP Syndrome",
        "tr": "HELLP sendromunda nöroaksiyel karar için trombosit sayısı, trendi, koagülasyon ve klinik kanama bulgularını birlikte değerlendiririm.",
        "en": "In HELLP syndrome, I assess platelet count, trend, coagulation, and clinical bleeding signs together before neuraxial decisions.",
        "importance": "high",
        "tags": ["hellp_syndrome", "platelet_count_trend", "coagulation", "neuraxial_anesthesia_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 058
    {
        "id": "edaic_viva_spot_058",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Obstetric Hemorrhage",
        "tr": "Postpartum kanamada cevabım uterin atoni, travma, doku kalıntısı ve koagülopatiyi sistematik olarak düşünür.",
        "en": "In postpartum hemorrhage, my answer systematically considers uterine atony, trauma, retained tissue, and coagulopathy.",
        "importance": "high",
        "tags": ["postpartum_hemorrhage", "uterine_atony", "coagulopathy", "viva_differential_diagnosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 059
    {
        "id": "edaic_viva_spot_059",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Obstetric Hemorrhage",
        "tr": "Postpartum kanamada oksitosin, ek uterotonikler, traneksamik asit, kan ürünleri ve cerrahi kontrolü birlikte planlarım.",
        "en": "In postpartum hemorrhage, I plan oxytocin, additional uterotonics, tranexamic acid, blood products, and surgical control together.",
        "importance": "high",
        "tags": ["postpartum_hemorrhage_management", "uterotonics", "tranexamic_acid", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 060
    {
        "id": "edaic_viva_spot_060",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Obstetric Hemorrhage",
        "tr": "Plasenta akreta şüphesinde masif kanama hazırlığı, multidisipliner ekip ve invaziv monitörizasyonu önceden planlarım.",
        "en": "When placenta accreta is suspected, I plan massive bleeding preparation, a multidisciplinary team, and invasive monitoring in advance.",
        "importance": "high",
        "tags": ["placenta_accreta", "massive_hemorrhage_prep", "multidisciplinary_team", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 061
    {
        "id": "edaic_viva_spot_061",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Maternal CPR",
        "tr": "Gebede kardiyak arrestte kaliteli CPR, sol uterin yer değiştirme ve erken perimortem sezaryen olasılığını belirtirim.",
        "en": "In maternal cardiac arrest, I mention high-quality CPR, left uterine displacement, and possible early perimortem cesarean delivery.",
        "importance": "high",
        "tags": ["maternal_cardiac_arrest", "cpr_quality", "left_uterine_displacement", "perimortem_cesarean"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 062
    {
        "id": "edaic_viva_spot_062",
        "exam": "EDAIC_VIVA",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Amniotic Fluid Embolism",
        "tr": "Amniyotik sıvı embolisinde ani hipoksemi, hipotansiyon ve koagülopati triadını akla getiririm.",
        "en": "In amniotic fluid embolism, I consider the triad of sudden hypoxemia, hypotension, and coagulopathy.",
        "importance": "high",
        "tags": ["amniotic_fluid_embolism", "afe_triad", "hypoxemia", "coagulopathy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 063
    {
        "id": "edaic_viva_spot_063",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Physiology",
        "tr": "Pediatrik anestezide cevabımı hızlı desatürasyon riski ve kilogram başına doz hesaplama prensibi üzerine kurarım.",
        "en": "In pediatric anesthesia, I frame my answer around rapid desaturation risk and per-kilogram dosing.",
        "importance": "high",
        "tags": ["pediatric_anesthesia_basics", "rapid_desaturation", "weight_based_dosing", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 064
    {
        "id": "edaic_viva_spot_064",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Physiology",
        "tr": "Çocukta bradikardiyi genellikle hipoksinin ciddi bir bulgusu olarak değerlendiririm.",
        "en": "In children, I usually treat bradycardia as a serious sign of hypoxia.",
        "importance": "high",
        "tags": ["pediatric_bradycardia", "hypoxia_indicator", "safety_first_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 065
    {
        "id": "edaic_viva_spot_065",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Laryngospasm",
        "tr": "Pediatrik laringospazmda hızlıca %100 oksijen, CPAP, jaw thrust ve gerekirse süksinilkolin planlarım.",
        "en": "In pediatric laryngospasm, I rapidly plan 100% oxygen, CPAP, jaw thrust, and succinylcholine if needed.",
        "importance": "high",
        "tags": ["pediatric_laryngospasm", "jaw_thrust", "cpap", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 066
    {
        "id": "edaic_viva_spot_066",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "URI & Anesthesia",
        "tr": "ÜSYE olan çocukta elektif cerrahi kararını ateş, sekresyon, wheezing, yaş ve cerrahi aciliyete göre değerlendiririm.",
        "en": "In a child with upper respiratory infection, I evaluate elective surgery based on fever, secretions, wheezing, age, and urgency.",
        "importance": "high",
        "tags": ["upper_respiratory_infection", "elective_surgery_cancellation", "airway_irritability", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 067
    {
        "id": "edaic_viva_spot_067",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Fluid Management",
        "tr": "Pediatrik hastada sıvı ve kan kaybı değerlendirmesinde kilo, vital bulgular ve klinik perfüzyonu birlikte izlerim.",
        "en": "In pediatric patients, I assess fluid and blood loss using weight, vital signs, and clinical perfusion together.",
        "importance": "high",
        "tags": ["pediatric_fluid_loss", "blood_loss_estimation", "clinical_perfusion_monitoring", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 068
    {
        "id": "edaic_viva_spot_068",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Neonatal Resuscitation",
        "tr": "Neonatal resüsitasyonda kalp hızındaki düzelmenin etkili ventilasyonu gösteren en önemli bulgu olduğunu söylerim.",
        "en": "In neonatal resuscitation, I state that heart rate improvement is the most important sign of effective ventilation.",
        "importance": "high",
        "tags": ["neonatal_resuscitation", "effective_ventilation_marker", "heart_rate_response", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 069
    {
        "id": "edaic_viva_spot_069",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Prematurity",
        "tr": "Prematüre bebekte postoperatif apne, hipoglisemi ve hipotermi riskini özellikle belirtirim.",
        "en": "In premature infants, I specifically mention postoperative apnea, hypoglycemia, and hypothermia risks.",
        "importance": "high",
        "tags": ["premature_infants", "postoperative_apnea", "hypoglycemia", "hypothermia_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 070
    {
        "id": "edaic_viva_spot_070",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Complications",
        "tr": "Duchenne musküler distrofide süksinilkolinden kaçınır ve hiperkalemi/kardiyak arrest riskini vurgularım.",
        "en": "In Duchenne muscular dystrophy, I avoid succinylcholine and emphasize hyperkalemia and cardiac arrest risk.",
        "importance": "high",
        "tags": ["duchenne_muscular_dystrophy", "avoid_succinylcholine", "hyperkalemia_hazard", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 071
    {
        "id": "edaic_viva_spot_071",
        "exam": "EDAIC_VIVA",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pediatric Complications",
        "tr": "Malign hipertermi duyarlılığı olan çocukta tetikleyicisiz anestezi, temiz devre ve dantrolen erişimini planlarım.",
        "en": "In a child susceptible to malignant hyperthermia, I plan trigger-free anesthesia, a clean circuit, and access to dantrolene.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "trigger_free_anesthesia", "clean_circuit_prep", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 072
    {
        "id": "edaic_viva_spot_072",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "Anticoagulants",
        "tr": "Nöroaksiyel anestezi öncesi antikoagülan kullanımını son doz zamanı, ilaç tipi, renal fonksiyon ve işlem riskine göre değerlendiririm.",
        "en": "Before neuraxial anesthesia, I assess anticoagulant use according to last dose timing, drug type, renal function, and procedural risk.",
        "importance": "high",
        "tags": ["neuraxial_anesthesia", "anticoagulants_safety", "renal_function", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 073
    {
        "id": "edaic_viva_spot_073",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "High Spinal",
        "tr": "Spinal anestezi sonrası ciddi hipotansiyonda sempatik blok, venöz dönüş azalması ve yüksek blok olasılığını düşünürüm.",
        "en": "In severe hypotension after spinal anesthesia, I consider sympathetic block, reduced venous return, and high block.",
        "importance": "high",
        "tags": ["spinal_hypotension", "sympathetic_block", "high_spinal_suspiction", "viva_differential_diagnosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 074
    {
        "id": "edaic_viva_spot_074",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "High Spinal",
        "tr": "Yüksek spinal blokta cevabım havayolu desteği, ventilasyon, vazopressör ve bradikardi tedavisini içermelidir.",
        "en": "In high spinal block, my answer should include airway support, ventilation, vasopressors, and treatment of bradycardia.",
        "importance": "high",
        "tags": ["high_spinal_management", "airway_support", "bradycardia_treatment", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 075
    {
        "id": "edaic_viva_spot_075",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "High Spinal",
        "tr": "Total spinal anestezide hızlı havayolu, solunum ve dolaşım desteği gerektiğini net söylerim.",
        "en": "In total spinal anesthesia, I clearly state that rapid airway, respiratory, and circulatory support is required.",
        "importance": "high",
        "tags": ["total_spinal_anesthesia", "resuscitation_priority", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 076
    {
        "id": "edaic_viva_spot_076",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "Neuraxial Complications",
        "tr": "Postdural ponksiyon baş ağrısında ortostatik karakteri sorgular ve dirençli olguda epidural kan yamasını düşünürüm.",
        "en": "In postdural puncture headache, I ask about orthostatic character and consider epidural blood patch in persistent cases.",
        "importance": "high",
        "tags": ["pdph", "postdural_puncture_headache", "epidural_blood_patch", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 077
    {
        "id": "edaic_viva_spot_077",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "Neuraxial Complications",
        "tr": "Nöroaksiyel işlem sonrası yeni motor defisit varsa epidural hematomu dışlamak için acil görüntüleme gerektiğini belirtirim.",
        "en": "If new motor deficit occurs after a neuraxial procedure, I state that urgent imaging is needed to exclude epidural hematoma.",
        "importance": "high",
        "tags": ["spinal_hematoma", "new_motor_deficit", "urgent_mri_request", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 078
    {
        "id": "edaic_viva_spot_078",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "Nerve Blocks & LAST",
        "tr": "Periferik sinir bloğunda şiddetli ağrı veya yüksek enjeksiyon basıncı olursa enjeksiyonu durdurup iğne pozisyonunu yeniden değerlendiririm.",
        "en": "If severe pain or high injection pressure occurs during peripheral nerve block, I stop injection and reassess needle position.",
        "importance": "high",
        "tags": ["intraneural_injection_prevention", "high_injection_pressure", "stop_injection", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 079
    {
        "id": "edaic_viva_spot_079",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "Nerve Blocks & LAST",
        "tr": "Rejyonal anestezide LAST riskini azaltmak için aspirasyon, fraksiyone enjeksiyon ve doz hesabını vurgularım.",
        "en": "In regional anesthesia, I emphasize aspiration, incremental injection, and dose calculation to reduce LAST risk.",
        "importance": "high",
        "tags": ["last_prevention", "incremental_injection", "aspiration", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 080
    {
        "id": "edaic_viva_spot_080",
        "exam": "EDAIC_VIVA",
        "topic": "Regional Anesthesia",
        "subtopic": "Nerve Blocks & LAST",
        "tr": "Kalıcı nörolojik defisit gelişirse erken nörolojik değerlendirme ve dokümantasyon yapılması gerektiğini söylerim.",
        "en": "If persistent neurological deficit occurs, I state that early neurological evaluation and documentation are required.",
        "importance": "high",
        "tags": ["neurological_injury", "early_neurology_consult", "documentation_safety", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 081
    {
        "id": "edaic_viva_spot_081",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Ciddi aort stenozunda sinüs ritmi, preload ve sistemik vasküler direnci korumayı hedeflerim.",
        "en": "In severe aortic stenosis, I aim to maintain sinus rhythm, preload, and systemic vascular resistance.",
        "importance": "high",
        "tags": ["aortic_stenosis_viva", "sinus_rhythm", "svr_maintenance", "preload_optimization"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 082
    {
        "id": "edaic_viva_spot_082",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Aort stenozlu hastada hipotansiyon ve taşikardinin miyokard iskemisini tetikleyebilir olduğunu açıklarım.",
        "en": "In aortic stenosis, I explain that hypotension and tachycardia may trigger myocardial ischemia.",
        "importance": "high",
        "tags": ["aortic_stenosis", "myocardial_ischemia", "tachycardia_hazard", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 083
    {
        "id": "edaic_viva_spot_083",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Mitral stenozda taşikardiden kaçınır, yeterli doluş zamanı ve pulmoner ödem riskini vurgularım.",
        "en": "In mitral stenosis, I avoid tachycardia and emphasize adequate filling time and pulmonary edema risk.",
        "importance": "high",
        "tags": ["mitral_stenosis_viva", "avoid_tachycardia", "diastolic_filling_time", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 084
    {
        "id": "edaic_viva_spot_084",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Aort yetersizliğinde ağır bradikardiden kaçınır ve ileri afterload artışının zararlı olabileceğini belirtirim.",
        "en": "In aortic regurgitation, I avoid severe bradycardia and state that marked afterload increase may be harmful.",
        "importance": "high",
        "tags": ["aortic_regurgitation", "avoid_bradycardia", "afterload_reduction", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 085
    {
        "id": "edaic_viva_spot_085",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Pulmoner hipertansiyonda hipoksi, hiperkapni, asidoz, hipotermi ve ağrıyı önlemeyi hedeflerim.",
        "en": "In pulmonary hypertension, I aim to prevent hypoxia, hypercapnia, acidosis, hypothermia, and pain.",
        "importance": "high",
        "tags": ["pulmonary_hypertension_viva", "avoid_hypoxia", "pvr_elevation_factors", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 086
    {
        "id": "edaic_viva_spot_086",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Sağ ventrikül krizinde oksijenasyon, normokapni, asidoz tedavisi, sistemik basınç desteği ve pulmoner vazodilatörleri düşünürüm.",
        "en": "In right ventricular crisis, I consider oxygenation, normocapnia, acidosis treatment, systemic pressure support, and pulmonary vasodilators.",
        "importance": "high",
        "tags": ["right_ventricular_crisis", "pulmonary_vasodilators", "rv_perfusion", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 087
    {
        "id": "edaic_viva_spot_087",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Artery Disease",
        "tr": "Koroner arter hastasında miyokard oksijen sunumu ve tüketimi dengesini korumaya odaklanırım.",
        "en": "In coronary artery disease, I focus on maintaining the balance between myocardial oxygen supply and demand.",
        "importance": "high",
        "tags": ["coronary_artery_disease", "oxygen_supply_demand_balance", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 088
    {
        "id": "edaic_viva_spot_088",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Artery Disease",
        "tr": "Koroner stentli hastada antiplatelet tedavi kararı cerrah, kardiyolog ve hasta riskleriyle birlikte ele alırım.",
        "en": "In patients with coronary stents, I address antiplatelet decisions together with the surgeon, cardiologist, and patient risks.",
        "importance": "high",
        "tags": ["coronary_stents", "antiplatelet_management", "stent_thrombosis_vs_bleeding", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 089
    {
        "id": "edaic_viva_spot_089",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Devices",
        "tr": "Pacemaker veya ICD olan hastada cihaz bağımlılığı, elektromanyetik girişim ve perioperatif cihaz planını sorgularım.",
        "en": "In patients with pacemaker or ICD, I ask about device dependency, electromagnetic interference, and the perioperative device plan.",
        "importance": "high",
        "tags": ["pacemaker", "icd", "electromagnetic_interference", "device_interrogation_viva"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 090
    {
        "id": "edaic_viva_spot_090",
        "exam": "EDAIC_VIVA",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Devices",
        "tr": "ICD şok fonksiyonu kapatılırsa eksternal defibrilasyonun hazır olması gerektiğini belirtirim.",
        "en": "If ICD shock therapy is disabled, I state that external defibrillation must be immediately available.",
        "importance": "high",
        "tags": ["icd_deactivation", "external_defibrillator_ready", "verbal_viva_milestone"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 091
    {
        "id": "edaic_viva_spot_091",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda hipoksemi gelişirse önce tüp veya bloker pozisyonunu doğrularım.",
        "en": "If hypoxemia occurs during one-lung ventilation, I first confirm tube or blocker position.",
        "importance": "high",
        "tags": ["one_lung_ventilation", "hypoxemia", "confirm_dlt_position", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 092
    {
        "id": "edaic_viva_spot_092",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "One-Lung Ventilation",
        "tr": "Tek akciğer ventilasyonunda oksijenasyonu düzeltmek için bağımlı akciğere PEEP ve ventile edilmeyen akciğere CPAP seçeneklerini düşünürüm.",
        "en": "To improve oxygenation during one-lung ventilation, I consider PEEP to the dependent lung and CPAP to the nonventilated lung.",
        "importance": "high",
        "tags": ["olv_hypoxemia", "dependent_lung_peep", "nonventilated_lung_cpap", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 093
    {
        "id": "edaic_viva_spot_093",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Pneumonectomy Complications",
        "tr": "Pnömonektomi sonrası aşırı sıvı yüklenmesinin akut akciğer hasarı riskini artırabileceğini söylerim.",
        "en": "I state that excessive fluid loading after pneumonectomy may increase the risk of acute lung injury.",
        "importance": "high",
        "tags": ["pneumonectomy_complications", "fluid_restriction", "acute_lung_injury_risk", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 094
    {
        "id": "edaic_viva_spot_094",
        "exam": "EDAIC_VIVA",
        "topic": "Thoracic Anesthesia",
        "subtopic": "Mediastinal Mass",
        "tr": "Mediastinal kitlede sedasyon ve supin pozisyonun havayolu veya kardiyovasküler kollaps yapabileceğini vurgularım.",
        "en": "In mediastinal mass, I emphasize that sedation and supine positioning may cause airway or cardiovascular collapse.",
        "importance": "high",
        "tags": ["mediastinal_mass", "supine_position_danger", "sedation_risk", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 095
    {
        "id": "edaic_viva_spot_095",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Robotic & Positioning",
        "tr": "Robotik cerrahide hastaya erişim kısıtlı olacağından tüp, damar yolu ve pozisyonlamayı cerrahi başlamadan güvenceye alırım.",
        "en": "In robotic surgery, I secure the tube, vascular access, and positioning before surgery starts because patient access will be limited.",
        "importance": "high",
        "tags": ["robotic_surgery", "limited_patient_access", "pre_surgical_checklist", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 096
    {
        "id": "edaic_viva_spot_096",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Laparoscopy",
        "tr": "Pnömoperitoneumda artmış havayolu basıncı, hiperkapni ve hemodinamik değişiklikleri beklerim.",
        "en": "During pneumoperitoneum, I expect increased airway pressure, hypercapnia, and hemodynamic changes.",
        "importance": "high",
        "tags": ["pneumoperitoneum_effects", "increased_airway_pressure", "hypercapnia", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 097
    {
        "id": "edaic_viva_spot_097",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Laparoscopy",
        "tr": "Trendelenburg pozisyonunda havayolu ödemi, yüz ödemi ve akciğer kompliyansında azalmayı izlerim.",
        "en": "In Trendelenburg position, I monitor for airway edema, facial edema, and reduced lung compliance.",
        "importance": "high",
        "tags": ["trendelenburg_complications", "airway_edema", "lung_compliance_reduction", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 098
    {
        "id": "edaic_viva_spot_098",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Orthopedic Complications",
        "tr": "Turnike açıldığında hipotansiyon, asidoz ve potasyum değişikliklerini bekleyip hazırlıklı olurum.",
        "en": "When a tourniquet is released, I anticipate hypotension, acidosis, and potassium changes.",
        "importance": "high",
        "tags": ["tourniquet_release_effects", "hypotension", "metabolic_acidosis", "viva_verbal_strategy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 099
    {
        "id": "edaic_viva_spot_099",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "Orthopedic Complications",
        "tr": "Kemik çimentosu implantasyon sendromunda ani hipoksi, hipotansiyon ve kardiyovasküler kollapsı hızlı tanırım.",
        "en": "In bone cement implantation syndrome, I rapidly recognize sudden hypoxia, hypotension, and cardiovascular collapse.",
        "importance": "high",
        "tags": ["bone_cement_implantation_syndrome", "bcis", "cardiovascular_collapse", "first_viva_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 100
    {
        "id": "edaic_viva_spot_100",
        "exam": "EDAIC_VIVA",
        "topic": "Specialized Surgery",
        "subtopic": "MRI Anesthesia",
        "tr": "MRI anestezisinde havayolu erişiminin kısıtlı ve ekipman uyumluluğunun kritik olduğunu özellikle belirtirim.",
        "en": "In MRI anesthesia, I specifically mention limited airway access and the critical need for compatible equipment.",
        "importance": "high",
        "tags": ["mri_anesthesia_safety", "limited_airway_access", "compatible_equipment", "viva_verbal_strategy"],
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
