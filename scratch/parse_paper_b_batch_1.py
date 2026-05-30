import json
import os

existing_path = "app/src/main/assets/board_spot_notes.json"

# Load existing 200 Paper A notes
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
        "id": "edaic_b_spot_001",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Airway Assessment",
        "tr": "Beklenen zor havayolunda temel karar, hastanın güvenle uyutulup uyutulamayacağıdır.",
        "en": "In anticipated difficult airway, the key decision is whether the patient can be safely induced.",
        "importance": "high",
        "tags": ["difficult_airway", "awake_intubation", "airway_assessment"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 002
    {
        "id": "edaic_b_spot_002",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Awake Intubation",
        "tr": "Zor maske ventilasyonu ve zor entübasyon birlikte bekleniyorsa uyanık entübasyon güçlü şekilde düşünülmelidir.",
        "en": "If difficult mask ventilation and difficult intubation are both expected, awake intubation should be strongly considered.",
        "importance": "high",
        "tags": ["difficult_airway", "awake_intubation", "mask_ventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 003
    {
        "id": "edaic_b_spot_003",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Airway Management",
        "tr": "Zor havayolu yönetiminde oksijenasyon, entübasyon başarısından daha önceliklidir.",
        "en": "In difficult airway management, oxygenation is more important than successful intubation.",
        "importance": "high",
        "tags": ["oxygenation", "difficult_intubation", "difficult_airway"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 004
    {
        "id": "edaic_b_spot_004",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Airway Trauma",
        "tr": "Başarısız entübasyonda aynı tekniği tekrar tekrar denemek havayolu travması ve ödem riskini artırır.",
        "en": "Repeated attempts with the same failed technique increase the risk of airway trauma and edema.",
        "importance": "high",
        "tags": ["repeated_attempts", "airway_trauma", "difficult_intubation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 005
    {
        "id": "edaic_b_spot_005",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Crisis Management",
        "tr": "Beklenmeyen zor entübasyonda yardım çağırmak erken basamak olmalıdır.",
        "en": "Calling for help should be an early step in unexpected difficult intubation.",
        "importance": "high",
        "tags": ["unexpected_difficult_intubation", "calling_for_help"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 006
    {
        "id": "edaic_b_spot_006",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Videolaringoscopy",
        "tr": "Videolaringoskopi glottik görüntüyü iyileştirebilir ancak tüp ilerletme zorluğu devam edebilir.",
        "en": "Videolaryngoscopy may improve the glottic view, but tube delivery can still be difficult.",
        "importance": "high",
        "tags": ["videolaryngoscopy", "tube_delivery", "glottic_view"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 007
    {
        "id": "edaic_b_spot_007",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Can't Intubate Can't Oxygenate",
        "tr": "CICO durumunda gecikmeden front-of-neck access hazırlığı yapılmalıdır.",
        "en": "In CICO, preparation for front-of-neck access should not be delayed.",
        "importance": "high",
        "tags": ["cico", "fona", "emergency_airway"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 008
    {
        "id": "edaic_b_spot_008",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Can't Intubate Can't Oxygenate",
        "tr": "Erişkin CICO yönetiminde skalpel-buji-tüp tekniği sık önerilen acil yaklaşımdır.",
        "en": "In adult CICO management, the scalpel-bougie-tube technique is a commonly recommended emergency approach.",
        "importance": "high",
        "tags": ["cico", "fona", "scalpel_bougie_tube"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 009
    {
        "id": "edaic_b_spot_009",
        "exam": "EDAIC_PAPER_B",
        "topic": "Difficult Airway",
        "subtopic": "Supraglottic Airway",
        "tr": "Supraglottik havayolu, başarısız entübasyonda oksijenasyonu kurtarmak için kullanılabilir.",
        "en": "A supraglottic airway can be used to rescue oxygenation after failed intubation.",
        "importance": "high",
        "tags": ["sad", "rescue_oxygenation", "failed_intubation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 010
    {
        "id": "edaic_b_spot_010",
        "exam": "EDAIC_PAPER_B",
        "topic": "Perioperative Care",
        "subtopic": "Rapid Sequence Induction",
        "tr": "RSI’nin amacı aspirasyon riskini azaltırken hızlı ve güvenli trakeal entübasyon sağlamaktır.",
        "en": "The purpose of RSI is to reduce aspiration risk while achieving rapid and safe tracheal intubation.",
        "importance": "high",
        "tags": ["rsi", "aspiration_risk", "intubation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 011
    {
        "id": "edaic_b_spot_011",
        "exam": "EDAIC_PAPER_B",
        "topic": "Perioperative Care",
        "subtopic": "Rapid Sequence Induction",
        "tr": "Tok hasta, barsak obstrüksiyonu, gebelik ve aktif kusma aspirasyon riskini artırır.",
        "en": "Full stomach, bowel obstruction, pregnancy, and active vomiting increase aspiration risk.",
        "importance": "high",
        "tags": ["aspiration_risk", "full_stomach", "pregnancy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 012
    {
        "id": "edaic_b_spot_012",
        "exam": "EDAIC_PAPER_B",
        "topic": "Perioperative Care",
        "subtopic": "Rapid Sequence Induction",
        "tr": "RSI sırasında zor havayolu ihtimali varsa alternatif oksijenasyon ve kurtarma planı önceden hazırlanmalıdır.",
        "en": "If difficult airway is possible during RSI, alternative oxygenation and rescue plans must be prepared in advance.",
        "importance": "high",
        "tags": ["rsi", "difficult_airway", "rescue_plan"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 013
    {
        "id": "edaic_b_spot_013",
        "exam": "EDAIC_PAPER_B",
        "topic": "Perioperative Care",
        "subtopic": "Rapid Sequence Induction",
        "tr": "Krikoid bası laringoskopik görüntüyü veya ventilasyonu bozarsa azaltılmalı veya kaldırılmalıdır.",
        "en": "If cricoid pressure worsens laryngoscopic view or ventilation, it should be reduced or released.",
        "importance": "high",
        "tags": ["cricoid_pressure", "sellick_maneuver", "laryngoscopic_view"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 014
    {
        "id": "edaic_b_spot_014",
        "exam": "EDAIC_PAPER_B",
        "topic": "Perioperative Care",
        "subtopic": "Preoxygenation",
        "tr": "Preoksijenasyon, indüksiyon sonrası güvenli apne süresini uzatır.",
        "en": "Preoxygenation prolongs safe apnea time after induction.",
        "importance": "high",
        "tags": ["preoxygenation", "apnea_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 015
    {
        "id": "edaic_b_spot_015",
        "exam": "EDAIC_PAPER_B",
        "topic": "Perioperative Care",
        "subtopic": "Preoxygenation",
        "tr": "Obez, gebe ve pediatrik hastalarda desatürasyon daha hızlı gelişir.",
        "en": "Obese, pregnant, and pediatric patients desaturate more rapidly.",
        "importance": "high",
        "tags": ["desaturation_speed", "obesity", "pregnancy", "pediatrics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 016
    {
        "id": "edaic_b_spot_016",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Hypoxemia",
        "tr": "Hipoksemi geliştiğinde ilk adım FiO₂’yi 1.0 yapmak ve oksijenasyonu kurtarmaktır.",
        "en": "When hypoxemia develops, the first step is to set FiO₂ to 1.0 and rescue oxygenation.",
        "importance": "high",
        "tags": ["hypoxemia", "oxygenation", "fio2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 017
    {
        "id": "edaic_b_spot_017",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Capnography",
        "tr": "Ani ETCO₂ kaybı tüp çıkması, devre ayrılması veya kardiyak arrest bulgusu olabilir.",
        "en": "Sudden loss of ETCO₂ may indicate tube dislodgement, circuit disconnection, or cardiac arrest.",
        "importance": "high",
        "tags": ["etco2", "disconnection", "cardiac_arrest"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 018
    {
        "id": "edaic_b_spot_018",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intraoperative Ventilation",
        "subtopic": "Airway Pressures",
        "tr": "Yüksek peak basınçta manuel ventilasyona geçmek direnç ve kompliyansı değerlendirmeyi kolaylaştırır.",
        "en": "In high peak airway pressure, switching to manual ventilation helps assess resistance and compliance.",
        "importance": "high",
        "tags": ["peak_pressure", "manual_ventilation", "compliance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 019
    {
        "id": "edaic_b_spot_019",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intraoperative Ventilation",
        "subtopic": "Airway Pressures",
        "tr": "Peak basınç yüksek, plateau basınç normal ise tüp kıvrılması, sekresyon veya bronkospazm düşünülür.",
        "en": "High peak pressure with normal plateau pressure suggests tube kinking, secretions, or bronchospasm.",
        "importance": "high",
        "tags": ["peak_pressure", "plateau_pressure", "resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 020
    {
        "id": "edaic_b_spot_020",
        "exam": "EDAIC_PAPER_B",
        "topic": "Intraoperative Ventilation",
        "subtopic": "Airway Pressures",
        "tr": "Peak ve plateau basınç birlikte yüksekse azalmış akciğer/toraks kompliyansı düşünülür.",
        "en": "High peak and plateau pressures together suggest reduced lung or chest wall compliance.",
        "importance": "high",
        "tags": ["peak_pressure", "plateau_pressure", "compliance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 021
    {
        "id": "edaic_b_spot_021",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Bronchospasm",
        "tr": "Bronkospazmda uzamış ekspirasyon, wheezing, yüksek peak basınç ve shark-fin kapnografi görülebilir.",
        "en": "Bronchospasm may cause prolonged expiration, wheezing, high peak pressure, and a shark-fin capnogram.",
        "importance": "high",
        "tags": ["bronchospasm", "wheezing", "shark_fin"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 022
    {
        "id": "edaic_b_spot_022",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Bronchospasm",
        "tr": "Şiddetli bronkospazmda sessiz akciğer çok ciddi hava akımı obstrüksiyonunu gösterir.",
        "en": "Silent chest in severe bronchospasm indicates critical airflow obstruction.",
        "importance": "high",
        "tags": ["bronchospasm", "silent_chest", "obstruction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 023
    {
        "id": "edaic_b_spot_023",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Laryngospasm",
        "tr": "Laringospazmda ilk yaklaşım uyarıyı durdurmak, %100 oksijen, CPAP ve jaw thrust uygulamaktır.",
        "en": "Initial management of laryngospasm includes stopping stimulation, 100% oxygen, CPAP, and jaw thrust.",
        "importance": "high",
        "tags": ["laryngospasm", "cpap", "jaw_thrust"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 024
    {
        "id": "edaic_b_spot_024",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Laryngospasm",
        "tr": "Tam laringospazm ve ventilasyon yokluğunda süksinilkolin gerekebilir.",
        "en": "Complete laryngospasm with inability to ventilate may require succinylcholine.",
        "importance": "high",
        "tags": ["laryngospasm", "succinylcholine", "intubation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 025
    {
        "id": "edaic_b_spot_025",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Pulmonary Edema",
        "tr": "Negatif basınçlı pulmoner ödem, laringospazm sonrası gelişebilir.",
        "en": "Negative-pressure pulmonary edema may occur after laryngospasm.",
        "importance": "high",
        "tags": ["laryngospasm", "negative_pressure_pulmonary_edema"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 026
    {
        "id": "edaic_b_spot_026",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Hypotension",
        "tr": "İntraoperatif hipotansiyonda önce ölçüm doğrulanmalı ve perfüzyon değerlendirilmelidir.",
        "en": "In intraoperative hypotension, first confirm the measurement and assess perfusion.",
        "importance": "high",
        "tags": ["hypotension", "perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 027
    {
        "id": "edaic_b_spot_027",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Hypotension",
        "tr": "Hipotansiyonun nedenleri arasında anestezi derinliği, hipovolemi, kanama, vazodilatasyon, aritmi ve anafilaksi bulunur.",
        "en": "Causes of hypotension include anesthetic depth, hypovolemia, bleeding, vasodilation, arrhythmia, and anaphylaxis.",
        "importance": "high",
        "tags": ["hypotension", "anesthetic_depth", "hypovolemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 028
    {
        "id": "edaic_b_spot_028",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Hypotension",
        "tr": "Hipotansiyonda tedavi, altta yatan nedene göre sıvı, vazopressör veya inotrop seçimiyle yapılmalıdır.",
        "en": "Treatment of hypotension should target the underlying cause with fluids, vasopressors, or inotropes as appropriate.",
        "importance": "high",
        "tags": ["hypotension", "vasopressors", "inotropes"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 029
    {
        "id": "edaic_b_spot_029",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Hypotension",
        "tr": "Hipovolemide sıvı ve kanama kontrolü, izole vazopressörden daha temel tedavidir.",
        "en": "In hypovolemia, fluid replacement and bleeding control are more fundamental than isolated vasopressor therapy.",
        "importance": "high",
        "tags": ["hypovolemia", "fluid_resuscitation", "bleeding_control"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 030
    {
        "id": "edaic_b_spot_030",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Hypotension",
        "tr": "Vazodilatasyona bağlı hipotansiyonda fenilefrin, efedrin veya noradrenalin klinik duruma göre seçilebilir.",
        "en": "In vasodilatory hypotension, phenylephrine, ephedrine, or norepinephrine may be selected according to the clinical situation.",
        "importance": "high",
        "tags": ["vasodilation", "phenylephrine", "ephedrine", "norepinephrine"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 031
    {
        "id": "edaic_b_spot_031",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Hypotension",
        "tr": "Bradikardi ile birlikte hipotansiyon varsa kalp hızı ve perfüzyon birlikte hedeflenmelidir.",
        "en": "When hypotension is associated with bradycardia, both heart rate and perfusion should be targeted.",
        "importance": "high",
        "tags": ["hypotension", "bradycardia", "perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 032
    {
        "id": "edaic_b_spot_032",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilakside hipotansiyon, bronkospazm ve deri bulguları birlikte görülebilir ancak deri bulguları olmayabilir.",
        "en": "Anaphylaxis may present with hypotension, bronchospasm, and skin signs, but skin signs may be absent.",
        "importance": "high",
        "tags": ["anaphylaxis", "bronchospasm", "skin_signs"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 033
    {
        "id": "edaic_b_spot_033",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Anaphylaxis",
        "tr": "Perioperatif anafilakside temel tedavi adrenalin, yüksek FiO₂, sıvı resüsitasyonu ve tetikleyicinin kesilmesidir.",
        "en": "Core treatment of perioperative anaphylaxis includes epinephrine, high FiO₂, fluid resuscitation, and stopping the trigger.",
        "importance": "high",
        "tags": ["anaphylaxis", "epinephrine", "adrenaline"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 034
    {
        "id": "edaic_b_spot_034",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilakside gecikmiş adrenalin uygulaması kötü prognozla ilişkilidir.",
        "en": "Delayed epinephrine administration in anaphylaxis is associated with poor outcome.",
        "importance": "high",
        "tags": ["anaphylaxis", "epinephrine", "prognosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 035
    {
        "id": "edaic_b_spot_035",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermide açıklanamayan ETCO₂ yükselmesi erken ve önemli bir bulgudur.",
        "en": "In malignant hyperthermia, unexplained ETCO₂ rise is an early and important sign.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "etco2", "early_signs"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 036
    {
        "id": "edaic_b_spot_036",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi şüphesinde volatil ajanlar ve süksinilkolin derhal kesilmelidir.",
        "en": "If malignant hyperthermia is suspected, volatile agents and succinylcholine must be stopped immediately.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "triggers", "volatile_anesthetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 037
    {
        "id": "edaic_b_spot_037",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi tedavisinde dantrolen, aktif soğutma ve metabolik bozuklukların düzeltilmesi gerekir.",
        "en": "Treatment of malignant hyperthermia requires dantrolene, active cooling, and correction of metabolic abnormalities.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "dantrolene", "cooling"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 038
    {
        "id": "edaic_b_spot_038",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Local Anesthetic Systemic Toxicity",
        "tr": "Lokal anestezik sistemik toksisitesinde nörolojik belirtiler kardiyak kollapstan önce ortaya çıkabilir.",
        "en": "In local anesthetic systemic toxicity, neurological symptoms may occur before cardiovascular collapse.",
        "importance": "high",
        "tags": ["last", "toxicity", "neurological_symptoms"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 039
    {
        "id": "edaic_b_spot_039",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Local Anesthetic Systemic Toxicity",
        "tr": "LAST tedavisinde nöbet kontrolü, kardiyopulmoner destek ve lipid emülsiyon tedavisi önemlidir.",
        "en": "LAST treatment includes seizure control, cardiopulmonary support, and lipid emulsion therapy.",
        "importance": "high",
        "tags": ["last", "toxicity", "lipid_emulsion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 040
    {
        "id": "edaic_b_spot_040",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Local Anesthetic Systemic Toxicity",
        "tr": "LAST durumunda büyük doz propofol kardiyovasküler depresyonu artırabileceği için dikkatli kullanılmalıdır.",
        "en": "In LAST, large doses of propofol should be used cautiously because they may worsen cardiovascular depression.",
        "importance": "high",
        "tags": ["last", "propofol", "cardiovascular_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 041
    {
        "id": "edaic_b_spot_041",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Massive Transfusion",
        "tr": "Masif kanamada erken cerrahi kanama kontrolü, dengeli transfüzyon ve hipoterminin önlenmesi esastır.",
        "en": "In massive bleeding, early surgical control, balanced transfusion, and prevention of hypothermia are essential.",
        "importance": "high",
        "tags": ["massive_bleeding", "transfusion", "hypothermia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 042
    {
        "id": "edaic_b_spot_042",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Massive Transfusion",
        "tr": "Masif transfüzyonda hipokalsemi sitrat yüküne bağlı gelişebilir.",
        "en": "Hypocalcemia during massive transfusion may develop due to citrate load.",
        "importance": "high",
        "tags": ["massive_transfusion", "citrate", "hypocalcemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 043
    {
        "id": "edaic_b_spot_043",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Massive Transfusion",
        "tr": "Hipotermi, asidoz ve koagülopati masif kanamada mortaliteyi artıran ölümcül triadı oluşturur.",
        "en": "Hypothermia, acidosis, and coagulopathy form the lethal triad that increases mortality in massive bleeding.",
        "importance": "high",
        "tags": ["lethal_triad", "acidosis", "coagulopathy", "hypothermia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 044
    {
        "id": "edaic_b_spot_044",
        "exam": "EDAIC_PAPER_B",
        "topic": "Crisis Management",
        "subtopic": "Massive Transfusion",
        "tr": "Traneksamik asit fibrinolizi azaltır ve uygun hastada erken dönemde faydalı olabilir.",
        "en": "Tranexamic acid reduces fibrinolysis and may be beneficial early in appropriate patients.",
        "importance": "high",
        "tags": ["txa", "tranexamic_acid", "fibrinolysis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 045
    {
        "id": "edaic_b_spot_045",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Nöroaksiyel blok öncesi antikoagülan kullanımı mutlaka zamanlama ve renal fonksiyon açısından değerlendirilmelidir.",
        "en": "Before neuraxial block, anticoagulant use must be evaluated in terms of timing and renal function.",
        "importance": "high",
        "tags": ["neuraxial_block", "anticoagulants", "safety_timing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 046
    {
        "id": "edaic_b_spot_046",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Spinal hematom nadirdir ancak kalıcı nörolojik hasara neden olabilir.",
        "en": "Spinal hematoma is rare but may cause permanent neurological injury.",
        "importance": "high",
        "tags": ["spinal_hematoma", "neurological_injury"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 047
    {
        "id": "edaic_b_spot_047",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Yeni nörolojik defisit, nöroaksiyel işlem sonrası acil değerlendirme gerektirir.",
        "en": "New neurological deficit after neuraxial procedure requires urgent evaluation.",
        "importance": "high",
        "tags": ["neurological_deficit", "neuraxial_procedure", "urgent_evaluation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 048
    {
        "id": "edaic_b_spot_048",
        "exam": "EDAIC_PAPER_B",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Spinal anestezi sonrası hipotansiyon sempatik blok ve venöz dönüş azalmasına bağlıdır.",
        "en": "Hypotension after spinal anesthesia is caused by sympathetic block and reduced venous return.",
        "importance": "high",
        "tags": ["spinal_anesthesia", "hypotension", "sympathetic_block"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 049
    {
        "id": "edaic_b_spot_049",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Complications",
        "tr": "Obstetrik spinal hipotansiyonda uteroplasental perfüzyonu korumak için hızlı tedavi gerekir.",
        "en": "Obstetric spinal hypotension requires rapid treatment to preserve uteroplacental perfusion.",
        "importance": "high",
        "tags": ["obstetric_anesthesia", "spinal_hypotension", "uteroplasental_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 050
    {
        "id": "edaic_b_spot_050",
        "exam": "EDAIC_PAPER_B",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Aortocaval Compression",
        "tr": "Sezaryende sol uterin yer değiştirme aortokaval basıyı azaltmaya yardımcı olur.",
        "en": "Left uterine displacement during cesarean delivery helps reduce aortocaval compression.",
        "importance": "high",
        "tags": ["left_uterine_displacement", "aortocaval_compression", "cesarean"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

combined = spot_notes + new_spots

# Save back to main database file
with open(existing_path, "w", encoding="utf-8") as f:
    json.dump(combined, f, ensure_ascii=False, indent=2)

print(f"Successfully combined and wrote {len(combined)} spot notes to {existing_path}")
