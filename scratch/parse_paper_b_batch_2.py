import json
import os

existing_path = "app/src/main/assets/board_spot_notes.json"

# Load existing 250 notes
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
        "id": "edaic_b_spot_051",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Physiology",
        "tr": "Gebelikte FRC azalır ve oksijen tüketimi artar; bu nedenle desatürasyon daha hızlı gelişir.",
        "en": "In pregnancy, FRC decreases and oxygen consumption increases, so desaturation occurs more rapidly.",
        "importance": "high",
        "tags": ["pregnancy_physiology", "frc", "desaturation_speed"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 052
    {
        "id": "edaic_b_spot_052",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Physiology",
        "tr": "Gebelerde aspirasyon riski artmıştır; gastrik boşalma gecikmesi ve alt özofagus sfinkter tonusunda azalma önemlidir.",
        "en": "Aspiration risk is increased in pregnancy due to delayed gastric emptying and reduced lower esophageal sphincter tone.",
        "importance": "high",
        "tags": ["aspiration_risk", "pregnancy", "gastric_emptying"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 053
    {
        "id": "edaic_b_spot_053",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Aortocaval Compression",
        "tr": "Aortokaval bası maternal hipotansiyon ve uteroplasental perfüzyon azalmasına yol açabilir.",
        "en": "Aortocaval compression may cause maternal hypotension and reduced uteroplacental perfusion.",
        "importance": "high",
        "tags": ["aortocaval_compression", "maternal_hypotension", "uteroplasental_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 054
    {
        "id": "edaic_b_spot_054",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Aortocaval Compression",
        "tr": "Sezaryen sırasında sol uterin yer değiştirme aortokaval basıyı azaltmaya yardımcı olur.",
        "en": "Left uterine displacement during cesarean delivery helps reduce aortocaval compression.",
        "importance": "high",
        "tags": ["left_uterine_displacement", "aortocaval_compression", "cesarean"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 055
    {
        "id": "edaic_b_spot_055",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Complications",
        "tr": "Spinal anesteziye bağlı hipotansiyon sezaryende sık görülür ve hızlı tedavi gerektirir.",
        "en": "Spinal anesthesia-induced hypotension is common during cesarean delivery and requires rapid treatment.",
        "importance": "high",
        "tags": ["spinal_hypotension", "cesarean", "obstetric_anesthesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 056
    {
        "id": "edaic_b_spot_056",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Complications",
        "tr": "Obstetrik spinal hipotansiyonda fenilefrin fetal asidoz riskini azaltma açısından sık tercih edilir.",
        "en": "Phenylephrine is often preferred in obstetric spinal hypotension because it may reduce the risk of fetal acidosis.",
        "importance": "high",
        "tags": ["phenylephrine", "fetal_acidosis", "spinal_hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 057
    {
        "id": "edaic_b_spot_057",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Difficult Airway",
        "tr": "Gebede zor havayolu riski ödem, kilo alımı ve meme dokusu nedeniyle artabilir.",
        "en": "The risk of difficult airway in pregnancy may increase due to edema, weight gain, and breast tissue.",
        "importance": "high",
        "tags": ["difficult_airway", "pregnancy", "edema"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 058
    {
        "id": "edaic_b_spot_058",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Difficult Airway",
        "tr": "Gebede başarısız entübasyonda oksijenasyonu sürdürmek ve erken yardım çağırmak temel önceliktir.",
        "en": "In failed intubation during pregnancy, maintaining oxygenation and calling for help early are key priorities.",
        "importance": "high",
        "tags": ["failed_intubation", "pregnancy", "oxygenation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 059
    {
        "id": "edaic_b_spot_059",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Preeclampsia",
        "tr": "Preeklampside laringoskopi yanıtı abartılı hipertansiyon ve serebral kanama riskini artırabilir.",
        "en": "In preeclampsia, the laryngoscopic response may cause exaggerated hypertension and increase the risk of cerebral hemorrhage.",
        "importance": "high",
        "tags": ["preeclampsia", "laryngoscopy_response", "hypertension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 060
    {
        "id": "edaic_b_spot_060",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Preeclampsia",
        "tr": "Preeklampside magnezyum sülfat nöbet profilaksisi için kullanılır ve kas gevşeticilerin etkisini artırabilir.",
        "en": "Magnesium sulfate is used for seizure prophylaxis in preeclampsia and may potentiate neuromuscular blockers.",
        "importance": "high",
        "tags": ["magnesium_sulfate", "neuromuscular_blockade", "preeclampsia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 061
    {
        "id": "edaic_b_spot_061",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Eclampsia",
        "tr": "Eklampside ilk öncelikler havayolu güvenliği, oksijenasyon, nöbet kontrolü ve kan basıncı kontrolüdür.",
        "en": "In eclampsia, initial priorities are airway safety, oxygenation, seizure control, and blood pressure control.",
        "importance": "high",
        "tags": ["eclampsia", "seizure_control", "airway_safety"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 062
    {
        "id": "edaic_b_spot_062",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Postpartum Hemorrhage",
        "tr": "Postpartum kanamada uterin atoni en sık nedenlerden biridir.",
        "en": "Uterine atony is one of the most common causes of postpartum hemorrhage.",
        "importance": "high",
        "tags": ["pph", "uterine_atony", "obstetric_hemorrhage"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 063
    {
        "id": "edaic_b_spot_063",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Postpartum Hemorrhage",
        "tr": "Uterotonik ilaçlar postpartum kanama yönetiminde temel tedavi bileşenidir.",
        "en": "Uterotonic drugs are a core component of postpartum hemorrhage management.",
        "importance": "high",
        "tags": ["pph", "uterotonics", "uterine_atony"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 064
    {
        "id": "edaic_b_spot_064",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Postpartum Hemorrhage",
        "tr": "Oksitosin hızlı bolus verildiğinde hipotansiyon ve taşikardi yapabilir.",
        "en": "Rapid bolus administration of oxytocin may cause hypotension and tachycardia.",
        "importance": "high",
        "tags": ["oxytocin", "bolus_side_effects", "hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 065
    {
        "id": "edaic_b_spot_065",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Physiology",
        "tr": "Pediatrik hastalarda oksijen tüketimi yüksek ve FRC düşük olduğu için hipoksemi hızla gelişir.",
        "en": "Pediatric patients develop hypoxemia rapidly because oxygen consumption is high and FRC is low.",
        "importance": "high",
        "tags": ["pediatric_physiology", "hypoxemia", "frc"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 066
    {
        "id": "edaic_b_spot_066",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Physiology",
        "tr": "Çocuklarda bradikardi genellikle hipoksinin geç ve ciddi bir bulgusudur.",
        "en": "In children, bradycardia is often a late and serious sign of hypoxia.",
        "importance": "high",
        "tags": ["bradycardia", "hypoxia", "pediatric_crisis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 067
    {
        "id": "edaic_b_spot_067",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Airway",
        "tr": "Pediatrik havayolunda büyük oksiput, göreceli büyük dil ve yüksek larenks ventilasyonu etkileyebilir.",
        "en": "In the pediatric airway, a large occiput, relatively large tongue, and high larynx may affect ventilation.",
        "importance": "high",
        "tags": ["pediatric_airway", "airway_anatomy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 068
    {
        "id": "edaic_b_spot_068",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Airway",
        "tr": "Bebeklerde en dar havayolu bölgesi krikoid düzey olarak klasik bilgi şeklinde öğretilir.",
        "en": "In infants, the narrowest airway region is classically taught to be at the cricoid level.",
        "importance": "high",
        "tags": ["cricoid", "infant_airway", "narrowest_airway"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 069
    {
        "id": "edaic_b_spot_069",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Airway",
        "tr": "Çocuklarda uygun tüp boyutu ve derinliği yaşa ve kiloya göre dikkatle seçilmelidir.",
        "en": "In children, appropriate tube size and depth must be selected carefully according to age and weight.",
        "importance": "high",
        "tags": ["tube_size", "intubation_depth", "pediatric_intubation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 070
    {
        "id": "edaic_b_spot_070",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Airway",
        "tr": "Pediatrik laringospazm hızla desatürasyon ve bradikardiye ilerleyebilir.",
        "en": "Pediatric laryngospasm may rapidly progress to desaturation and bradycardia.",
        "importance": "high",
        "tags": ["laryngospasm", "desaturation", "bradycardia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 071
    {
        "id": "edaic_b_spot_071",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Fluid Therapy",
        "tr": "Pediatrik sıvı tedavisinde hipotonik solüsyonlar hiponatremi riskini artırabilir.",
        "en": "Hypotonic solutions in pediatric fluid therapy may increase the risk of hyponatremia.",
        "importance": "high",
        "tags": ["fluid_therapy", "hypotonic_solutions", "hyponatremia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 072
    {
        "id": "edaic_b_spot_072",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Neonatal Resuscitation",
        "tr": "Neonatal resüsitasyonda ventilasyon kalitesi kalp hızındaki düzelmenin ana belirleyicisidir.",
        "en": "In neonatal resuscitation, ventilation quality is the main determinant of heart rate improvement.",
        "importance": "high",
        "tags": ["neonatal_resuscitation", "ventilation", "heart_rate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 073
    {
        "id": "edaic_b_spot_073",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Neonatal Resuscitation",
        "tr": "Yenidoğanda kalp hızı resüsitasyon başarısını değerlendirmede en önemli göstergedir.",
        "en": "Heart rate is the most important indicator for evaluating neonatal resuscitation success.",
        "importance": "high",
        "tags": ["neonatal_resuscitation", "heart_rate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 074
    {
        "id": "edaic_b_spot_074",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pharmacology",
        "tr": "Pediatrik hastada ilaç dozları genellikle kilogram başına hesaplanır ve maksimum erişkin dozları aşılmamalıdır.",
        "en": "In pediatric patients, drug doses are usually calculated per kilogram and should not exceed maximum adult doses.",
        "importance": "high",
        "tags": ["pediatric_dosing", "weight_based_dosing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 075
    {
        "id": "edaic_b_spot_075",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "LAST Prevention",
        "tr": "Rejyonal anestezide lokal anestezik sistemik toksisitesi için aspirasyon, fraksiyone enjeksiyon ve monitörizasyon önemlidir.",
        "en": "In regional anesthesia, aspiration, incremental injection, and monitoring are important to reduce local anesthetic systemic toxicity risk.",
        "importance": "high",
        "tags": ["last_prevention", "aspiration", "incremental_injection"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 076
    {
        "id": "edaic_b_spot_076",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Ultrasound",
        "tr": "Ultrason rehberliği blok başarısını artırabilir ve damar ponksiyonu riskini azaltabilir.",
        "en": "Ultrasound guidance may improve block success and reduce the risk of vascular puncture.",
        "importance": "high",
        "tags": ["ultrasound_guidance", "nerve_block", "vascular_puncture"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 077
    {
        "id": "edaic_b_spot_077",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Periferik sinir bloğunda enjeksiyon sırasında yüksek basınç veya şiddetli ağrı intranöral enjeksiyonu düşündürebilir.",
        "en": "High injection pressure or severe pain during peripheral nerve block may suggest intraneural injection.",
        "importance": "high",
        "tags": ["intraneural_injection", "enjeksiyon_basinci", "nerve_injury"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 078
    {
        "id": "edaic_b_spot_078",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Antikoagülan kullanan hastada nöroaksiyel blok kararı ilaç tipi, son doz zamanı ve renal fonksiyona göre verilmelidir.",
        "en": "In patients receiving anticoagulants, neuraxial block decisions should be based on drug type, time since last dose, and renal function.",
        "importance": "high",
        "tags": ["neuraxial_block", "anticoagulants", "safety_timing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 079
    {
        "id": "edaic_b_spot_079",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Epidural hematom şüphesinde erken görüntüleme ve cerrahi değerlendirme nörolojik sonuç için kritiktir.",
        "en": "If epidural hematoma is suspected, early imaging and surgical evaluation are critical for neurological outcome.",
        "importance": "high",
        "tags": ["epidural_hematoma", "neurological_outcome", "emergency_decompression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 080
    {
        "id": "edaic_b_spot_080",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Spinal Anesthesia",
        "tr": "Spinal anestezi hızlı başlangıçlı, yoğun ve öngörülebilir blok sağlar.",
        "en": "Spinal anesthesia provides rapid-onset, dense, and predictable block.",
        "importance": "high",
        "tags": ["spinal_anesthesia", "block_onset", "predictable_block"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 081
    {
        "id": "edaic_b_spot_081",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Epidural Anesthesia",
        "tr": "Epidural anestezide blok başlangıcı spinal anesteziye göre daha yavaştır ancak titrasyon avantajı vardır.",
        "en": "Epidural anesthesia has a slower onset than spinal anesthesia but offers the advantage of titration.",
        "importance": "high",
        "tags": ["epidural_anesthesia", "block_titration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 082
    {
        "id": "edaic_b_spot_082",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Yüksek spinal blok hipotansiyon, bradikardi, solunum yetmezliği ve bilinç kaybına neden olabilir.",
        "en": "High spinal block may cause hypotension, bradycardia, respiratory failure, and loss of consciousness.",
        "importance": "high",
        "tags": ["high_spinal", "bradycardia", "respiratory_failure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 083
    {
        "id": "edaic_b_spot_083",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Total spinal anestezide havayolu desteği, ventilasyon ve dolaşım desteği gerekebilir.",
        "en": "Total spinal anesthesia may require airway support, ventilation, and circulatory support.",
        "importance": "high",
        "tags": ["total_spinal", "airway_support", "cardiovascular_collapse"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 084
    {
        "id": "edaic_b_spot_084",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Postdural ponksiyon baş ağrısı tipik olarak ortostatik özellik gösterir.",
        "en": "Postdural puncture headache typically has an orthostatic character.",
        "importance": "high",
        "tags": ["pdph", "post_dural_puncture_headache", "orthostatic_headache"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 085
    {
        "id": "edaic_b_spot_085",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Epidural kan yaması dirençli postdural ponksiyon baş ağrısında etkili tedavidir.",
        "en": "Epidural blood patch is an effective treatment for persistent postdural puncture headache.",
        "importance": "high",
        "tags": ["pdph", "epidural_blood_patch"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 086
    {
        "id": "edaic_b_spot_086",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Risk Assessment",
        "tr": "Perioperatif kardiyak risk değerlendirmesinde cerrahinin aciliyeti, fonksiyonel kapasite ve aktif kardiyak durumlar önemlidir.",
        "en": "Perioperative cardiac risk assessment depends on surgical urgency, functional capacity, and active cardiac conditions.",
        "importance": "high",
        "tags": ["cardiac_risk_assessment", "surgical_urgency", "functional_capacity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 087
    {
        "id": "edaic_b_spot_087",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Risk Assessment",
        "tr": "Düşük fonksiyonel kapasite artmış perioperatif kardiyak riskle ilişkilidir.",
        "en": "Poor functional capacity is associated with increased perioperative cardiac risk.",
        "importance": "high",
        "tags": ["functional_capacity", "cardiac_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 088
    {
        "id": "edaic_b_spot_088",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Risk Assessment",
        "tr": "Aktif akut koroner sendrom, dekompanse kalp yetmezliği ve ciddi aritmi elektif cerrahinin ertelenmesini gerektirebilir.",
        "en": "Active acute coronary syndrome, decompensated heart failure, and serious arrhythmias may require postponement of elective surgery.",
        "importance": "high",
        "tags": ["active_cardiac_conditions", "acute_coronary_syndrome", "surgery_delay"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 089
    {
        "id": "edaic_b_spot_089",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Risk Assessment",
        "tr": "RCRI; iskemik kalp hastalığı, kalp yetmezliği, serebrovasküler hastalık, insülin tedavisi, kreatinin yüksekliği ve yüksek riskli cerrahiyi içerir.",
        "en": "RCRI includes ischemic heart disease, heart failure, cerebrovascular disease, insulin therapy, elevated creatinine, and high-risk surgery.",
        "importance": "high",
        "tags": ["rcri", "lee_index", "cardiac_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 090
    {
        "id": "edaic_b_spot_090",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Stents",
        "tr": "Stentli hastada antiplatelet tedavinin kesilmesi stent trombozu riskini artırabilir.",
        "en": "Stopping antiplatelet therapy in a patient with a coronary stent may increase the risk of stent thrombosis.",
        "importance": "high",
        "tags": ["stent_thrombosis", "antiplatelets", "stent_safety"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 091
    {
        "id": "edaic_b_spot_091",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Stents",
        "tr": "Aspirin kesilmesi veya devamı cerrahi kanama riski ve trombotik risk birlikte değerlendirilerek planlanır.",
        "en": "Stopping or continuing aspirin should be planned by weighing surgical bleeding risk against thrombotic risk.",
        "importance": "high",
        "tags": ["aspirin", "bleeding_risk", "thrombotic_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 092
    {
        "id": "edaic_b_spot_092",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Ciddi aort stenozunda hipotansiyon, taşikardi ve düşük preload kötü tolere edilir.",
        "en": "In severe aortic stenosis, hypotension, tachycardia, and low preload are poorly tolerated.",
        "importance": "high",
        "tags": ["aortic_stenosis", "preload", "afterload", "tachycardia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 093
    {
        "id": "edaic_b_spot_093",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Ciddi aort stenozunda sinüs ritmi, yeterli preload ve sistemik vasküler direnç korunmalıdır.",
        "en": "In severe aortic stenosis, sinus rhythm, adequate preload, and systemic vascular resistance should be maintained.",
        "importance": "high",
        "tags": ["aortic_stenosis", "sinus_rhythm", "svr"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 094
    {
        "id": "edaic_b_spot_094",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Mitral stenozda taşikardi diyastolik doluş süresini azaltarak pulmoner konjesyonu artırabilir.",
        "en": "In mitral stenosis, tachycardia shortens diastolic filling time and may worsen pulmonary congestion.",
        "importance": "high",
        "tags": ["mitral_stenosis", "tachycardia", "pulmonary_congestion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 095
    {
        "id": "edaic_b_spot_095",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Mitral yetersizlikte aşırı afterload artışı regürjitasyonu kötüleştirebilir.",
        "en": "In mitral regurgitation, excessive afterload increase may worsen regurgitation.",
        "importance": "high",
        "tags": ["mitral_regurgitation", "afterload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 096
    {
        "id": "edaic_b_spot_096",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Aort yetersizliğinde bradikardi regürjitan volümü artırabilir.",
        "en": "In aortic regurgitation, bradycardia may increase regurgitant volume.",
        "importance": "high",
        "tags": ["aortic_regurgitation", "bradycardia", "regurgitant_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 097
    {
        "id": "edaic_b_spot_097",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Pulmoner hipertansiyonda hipoksi, hiperkapni, asidoz ve ağrı pulmoner vasküler direnci artırır.",
        "en": "In pulmonary hypertension, hypoxia, hypercapnia, acidosis, and pain increase pulmonary vascular resistance.",
        "importance": "high",
        "tags": ["pulmonary_hypertension", "pulmonary_vascular_resistance", "hypoxia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 098
    {
        "id": "edaic_b_spot_098",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Pulmoner hipertansiyonda sağ ventrikül yetmezliği perioperatif mortalitenin önemli nedenidir.",
        "en": "In pulmonary hypertension, right ventricular failure is an important cause of perioperative mortality.",
        "importance": "high",
        "tags": ["pulmonary_hypertension", "right_view", "mortality"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 099
    {
        "id": "edaic_b_spot_099",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Devices",
        "tr": "Kalp pili veya ICD bulunan hastada cihaz tipi, bağımlılık durumu ve elektromanyetik girişim riski değerlendirilmelidir.",
        "en": "In patients with a pacemaker or ICD, device type, dependency, and electromagnetic interference risk should be assessed.",
        "importance": "high",
        "tags": ["pacemaker", "icd", "electromagnetic_interference"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 100
    {
        "id": "edaic_b_spot_100",
        "exam": "EDAIC_PAPER_B",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Devices",
        "tr": "Monopolar koter ICD şoklarını tetikleyebilir; perioperatif cihaz planı önceden yapılmalıdır.",
        "en": "Monopolar cautery may trigger ICD shocks; a perioperative device plan should be made in advance.",
        "importance": "high",
        "tags": ["icd", "monopolar_cautery", "pacemaker"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

combined = spot_notes + new_spots

# Save updated list
with open(existing_path, "w", encoding="utf-8") as f:
    json.dump(combined, f, ensure_ascii=False, indent=2)

print(f"Successfully combined and wrote {len(combined)} spot notes to {existing_path}")
