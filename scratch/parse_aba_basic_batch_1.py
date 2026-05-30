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
        "id": "aba_basic_spot_001",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "General Principles",
        "tr": "Preoperatif değerlendirmenin amacı riskleri belirlemek, optimize etmek ve güvenli anestezi planı oluşturmaktır.",
        "en": "The purpose of preoperative assessment is to identify risks, optimize the patient, and create a safe anesthetic plan.",
        "importance": "high",
        "tags": ["preoperative_assessment", "optimization", "risk_reduction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 002
    {
        "id": "aba_basic_spot_002",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "ASA Classification",
        "tr": "ASA fiziksel durum sınıflaması hastanın sistemik hastalık yükünü özetler ancak tek başına cerrahi riski tam belirlemez.",
        "en": "ASA physical status summarizes systemic disease burden but does not fully determine surgical risk by itself.",
        "importance": "high",
        "tags": ["asa_class", "systemic_disease", "surgical_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 003
    {
        "id": "aba_basic_spot_003",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "Emergency Surgery",
        "tr": "Acil cerrahi, ASA sınıfından bağımsız olarak perioperatif riski artırır.",
        "en": "Emergency surgery increases perioperative risk regardless of ASA class.",
        "importance": "high",
        "tags": ["emergency_surgery", "perioperative_risk", "asa_class"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 004
    {
        "id": "aba_basic_spot_004",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "Cardiac Risk",
        "tr": "Fonksiyonel kapasite düşük olan hastalarda perioperatif kardiyak risk artabilir.",
        "en": "Poor functional capacity may be associated with increased perioperative cardiac risk.",
        "importance": "high",
        "tags": ["functional_capacity", "cardiac_risk", "mets"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 005
    {
        "id": "aba_basic_spot_005",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "Cardiac Risk",
        "tr": "Aktif kardiyak durumlar elektif cerrahi öncesinde değerlendirme ve gerekirse erteleme gerektirir.",
        "en": "Active cardiac conditions require evaluation and possible postponement before elective surgery.",
        "importance": "high",
        "tags": ["active_cardiac_conditions", "surgery_delay", "preoperative_evaluation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 006
    {
        "id": "aba_basic_spot_006",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "Fasting Guidelines",
        "tr": "Preoperatif açlık kuralları aspirasyon riskini azaltmayı hedefler.",
        "en": "Preoperative fasting guidelines aim to reduce aspiration risk.",
        "importance": "high",
        "tags": ["npo_guidelines", "aspiration_prevention", "fasting"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 007
    {
        "id": "aba_basic_spot_007",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "Fasting Guidelines",
        "tr": "Berrak sıvılar genellikle katı gıdalardan daha hızlı mideyi terk eder.",
        "en": "Clear liquids generally empty from the stomach faster than solid food.",
        "importance": "high",
        "tags": ["clear_liquids", "gastric_emptying", "npo"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 008
    {
        "id": "aba_basic_spot_008",
        "exam": "ABA_BASIC",
        "topic": "Preoperative Assessment",
        "subtopic": "Aspiration Risk",
        "tr": "Dolu mide, gebelik, barsak obstrüksiyonu ve gastroparezi aspirasyon riskini artırır.",
        "en": "Full stomach, pregnancy, bowel obstruction, and gastroparesis increase aspiration risk.",
        "importance": "high",
        "tags": ["aspiration_risk", "full_stomach", "pregnancy", "gastroparesis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 009
    {
        "id": "aba_basic_spot_009",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Airway Assessment",
        "tr": "Zor havayolu değerlendirmesi ağız açıklığı, boyun hareketi, mandibula yapısı ve önceki havayolu öyküsünü içerir.",
        "en": "Difficult airway assessment includes mouth opening, neck mobility, mandibular anatomy, and previous airway history.",
        "importance": "high",
        "tags": ["difficult_airway", "mouth_opening", "neck_mobility", "airway_history"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 010
    {
        "id": "aba_basic_spot_010",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Airway Assessment",
        "tr": "Mallampati skoru tek başına zor entübasyonu güvenilir şekilde öngörmez.",
        "en": "Mallampati score alone does not reliably predict difficult intubation.",
        "importance": "high",
        "tags": ["mallampati", "difficult_intubation", "airway_prediction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 011
    {
        "id": "aba_basic_spot_011",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Airway Management",
        "tr": "Zor havayolu yönetiminde oksijenasyonu sürdürmek entübasyondan daha önceliklidir.",
        "en": "In difficult airway management, maintaining oxygenation is more important than intubation.",
        "importance": "high",
        "tags": ["difficult_airway", "oxygenation", "intubation_priority"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 012
    {
        "id": "aba_basic_spot_012",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Preoxygenation",
        "tr": "Preoksijenasyon, akciğerdeki azotu oksijenle değiştirerek güvenli apne süresini uzatır.",
        "en": "Preoxygenation prolongs safe apnea time by replacing nitrogen in the lungs with oxygen.",
        "importance": "high",
        "tags": ["preoxygenation", "denitrogenation", "safe_apnea_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 013
    {
        "id": "aba_basic_spot_013",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Preoxygenation",
        "tr": "Obez, gebe ve pediatrik hastalarda güvenli apne süresi daha kısadır.",
        "en": "Obese, pregnant, and pediatric patients have a shorter safe apnea time.",
        "importance": "high",
        "tags": ["safe_apnea_time", "obesity", "pregnancy", "pediatrics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 014
    {
        "id": "aba_basic_spot_014",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Patient Positioning",
        "tr": "Başın yükseltilmiş veya ramped pozisyonu obez hastada laringoskopiyi ve preoksijenasyonu kolaylaştırabilir.",
        "en": "Head-elevated or ramped positioning may facilitate laryngoscopy and preoxygenation in obese patients.",
        "importance": "high",
        "tags": ["ramped_position", "obese_airway", "laryngoscopy", "preoxygenation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 015
    {
        "id": "aba_basic_spot_015",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Mask Ventilation",
        "tr": "Maske ventilasyonunda iki elle maske tutma ve oral airway kullanımı hava kaçağını azaltabilir.",
        "en": "Two-handed mask ventilation and use of an oral airway may reduce mask leak.",
        "importance": "high",
        "tags": ["mask_ventilation", "two_handed_mask", "oral_airway", "mask_leak"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 016
    {
        "id": "aba_basic_spot_016",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Rescue Devices",
        "tr": "Supraglottik havayolu cihazları başarısız maske ventilasyonu veya başarısız entübasyonda oksijenasyonu kurtarabilir.",
        "en": "Supraglottic airway devices may rescue oxygenation after failed mask ventilation or failed intubation.",
        "importance": "high",
        "tags": ["sad", "lma", "failed_intubation", "rescue_airway"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 017
    {
        "id": "aba_basic_spot_017",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Verification",
        "tr": "Endotrakeal tüp yerleşiminin en güvenilir doğrulama yöntemi sürekli dalga formlu kapnografidir.",
        "en": "Continuous waveform capnography is the most reliable method to confirm endotracheal tube placement.",
        "importance": "high",
        "tags": ["intubation_verification", "capnography", "etco2_waveform"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 018
    {
        "id": "aba_basic_spot_018",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Verification",
        "tr": "Özofageal entübasyonda kalıcı ETCO₂ dalga formu beklenmez.",
        "en": "Persistent ETCO₂ waveform is not expected with esophageal intubation.",
        "importance": "high",
        "tags": ["esophageal_intubation", "capnography", "etco2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 019
    {
        "id": "aba_basic_spot_019",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Ani ETCO₂ kaybı tüp çıkması, devre ayrılması, ciddi hipotansiyon veya kardiyak arrest ile ilişkili olabilir.",
        "en": "Sudden loss of ETCO₂ may be associated with tube dislodgement, circuit disconnection, severe hypotension, or cardiac arrest.",
        "importance": "high",
        "tags": ["etco2_loss", "circuit_disconnection", "hypotension", "cardiac_arrest"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 020
    {
        "id": "aba_basic_spot_020",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Functional Residual Capacity",
        "tr": "FRC genel anestezi, supin pozisyon, obezite ve gebelikte azalır.",
        "en": "FRC decreases with general anesthesia, supine positioning, obesity, and pregnancy.",
        "importance": "high",
        "tags": ["frc", "general_anesthesia", "supine_position", "obesity", "pregnancy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 021
    {
        "id": "aba_basic_spot_021",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Functional Residual Capacity",
        "tr": "FRC azalması atelektazi ve hipoksemi riskini artırır.",
        "en": "Reduced FRC increases the risk of atelectasis and hypoxemia.",
        "importance": "high",
        "tags": ["frc_reduction", "atelectasis", "hypoxemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 022
    {
        "id": "aba_basic_spot_022",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Pediatric Physiology",
        "tr": "Çocuklarda oksijen tüketimi yüksek ve FRC düşük olduğu için desatürasyon hızlı gelişir.",
        "en": "Children desaturate rapidly because oxygen consumption is high and FRC is low.",
        "importance": "high",
        "tags": ["pediatric_desaturation", "oxygen_consumption", "frc"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 023
    {
        "id": "aba_basic_spot_023",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Ventilation",
        "tr": "Alveoler ventilasyon PaCO₂’nin temel belirleyicisidir.",
        "en": "Alveolar ventilation is the main determinant of PaCO₂.",
        "importance": "high",
        "tags": ["alveolar_ventilation", "paco2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 024
    {
        "id": "aba_basic_spot_024",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Ventilation",
        "tr": "Alveoler ventilasyon azalırsa PaCO₂ yükselir.",
        "en": "If alveolar ventilation decreases, PaCO₂ increases.",
        "importance": "high",
        "tags": ["alveolar_ventilation", "hypoventilation", "paco2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 025
    {
        "id": "aba_basic_spot_025",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Ventilation",
        "tr": "Dakika ventilasyonu tidal volüm ile solunum frekansının çarpımıdır.",
        "en": "Minute ventilation is the product of tidal volume and respiratory rate.",
        "importance": "high",
        "tags": ["minute_ventilation", "tidal_volume", "respiratory_rate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 026
    {
        "id": "aba_basic_spot_026",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Dead Space",
        "tr": "Ölü boşluk ventilasyonu gaz değişimine katılmayan ventilasyondur.",
        "en": "Dead space ventilation is ventilation that does not participate in gas exchange.",
        "importance": "high",
        "tags": ["dead_space", "gas_exchange"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 027
    {
        "id": "aba_basic_spot_027",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Dead Space",
        "tr": "Anatomik ölü boşluk yaklaşık 2 mL/kg kabul edilir.",
        "en": "Anatomical dead space is commonly estimated as approximately 2 mL/kg.",
        "importance": "high",
        "tags": ["anatomical_dead_space", "calculation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 028
    {
        "id": "aba_basic_spot_028",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Shunt & V/Q Mismatch",
        "tr": "Şantta oksijen tedavisine yanıt sınırlıdır; düşük V/Q durumunda yanıt daha iyidir.",
        "en": "True shunt has a limited response to oxygen therapy, whereas low V/Q states respond better.",
        "importance": "high",
        "tags": ["true_shunt", "vq_mismatch", "oxygen_response"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 029
    {
        "id": "aba_basic_spot_029",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Hypoxic Pulmonary Vasoconstriction",
        "tr": "Hipoksik pulmoner vazokonstriksiyon ventilasyonu azalmış alanlara kan akımını azaltır.",
        "en": "Hypoxic pulmonary vasoconstriction reduces blood flow to poorly ventilated lung regions.",
        "importance": "high",
        "tags": ["hpv", "pulmonary_blood_flow", "vq_matching"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 030
    {
        "id": "aba_basic_spot_030",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Hypoxic Pulmonary Vasoconstriction",
        "tr": "Volatil anestezikler hipoksik pulmoner vazokonstriksiyonu baskılayabilir.",
        "en": "Volatile anesthetics may inhibit hypoxic pulmonary vasoconstriction.",
        "importance": "high",
        "tags": ["hpv_inhibition", "volatile_anesthetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 031
    {
        "id": "aba_basic_spot_031",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Compliance",
        "tr": "Kompliyans, hacim değişikliğinin basınç değişikliğine oranıdır.",
        "en": "Compliance is the ratio of volume change to pressure change.",
        "importance": "high",
        "tags": ["compliance", "pressure_volume_ratio"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 032
    {
        "id": "aba_basic_spot_032",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Physiology",
        "subtopic": "Compliance",
        "tr": "Düşük kompliyans için daha yüksek basınçla daha az hacim elde edilir.",
        "en": "With low compliance, less volume is delivered for a given pressure.",
        "importance": "high",
        "tags": ["low_compliance", "pressure", "volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 033
    {
        "id": "aba_basic_spot_033",
        "exam": "ABA_BASIC",
        "topic": "Ventilation",
        "subtopic": "Airway Pressures",
        "tr": "Peak inspiratuvar basınç havayolu direnci ve solunum sistemi kompliyansından etkilenir.",
        "en": "Peak inspiratory pressure is affected by airway resistance and respiratory system compliance.",
        "importance": "high",
        "tags": ["pip", "airway_resistance", "lung_compliance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 034
    {
        "id": "aba_basic_spot_034",
        "exam": "ABA_BASIC",
        "topic": "Ventilation",
        "subtopic": "Airway Pressures",
        "tr": "Plateau basınç alveoler basınca daha yakın bir göstergedir.",
        "en": "Plateau pressure is a closer indicator of alveolar pressure.",
        "importance": "high",
        "tags": ["plateau_pressure", "alveolar_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 035
    {
        "id": "aba_basic_spot_035",
        "exam": "ABA_BASIC",
        "topic": "Ventilation",
        "subtopic": "Airway Pressures",
        "tr": "Peak basınç yüksek, plateau normal ise artmış havayolu direnci düşünülür.",
        "en": "High peak pressure with normal plateau pressure suggests increased airway resistance.",
        "importance": "high",
        "tags": ["pip_elevation", "airway_resistance", "bronchospasm"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 036
    {
        "id": "aba_basic_spot_036",
        "exam": "ABA_BASIC",
        "topic": "Ventilation",
        "subtopic": "Airway Pressures",
        "tr": "Peak ve plateau basınç birlikte yüksekse azalmış kompliyans düşünülür.",
        "en": "High peak and plateau pressures together suggest reduced compliance.",
        "importance": "high",
        "tags": ["pip_and_plateau", "low_compliance", "pneumothorax", "atelectasis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 037
    {
        "id": "aba_basic_spot_037",
        "exam": "ABA_BASIC",
        "topic": "Ventilation",
        "subtopic": "Complications",
        "tr": "Auto-PEEP özellikle obstrüktif akciğer hastalığında yetersiz ekspirasyon süresiyle gelişir.",
        "en": "Auto-PEEP develops especially in obstructive lung disease when expiratory time is insufficient.",
        "importance": "high",
        "tags": ["auto_peep", "intrinsic_peep", "obstructive_lung_disease"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 038
    {
        "id": "aba_basic_spot_038",
        "exam": "ABA_BASIC",
        "topic": "Ventilation",
        "subtopic": "Complications",
        "tr": "Auto-PEEP’i azaltmak için solunum frekansı azaltılabilir ve ekspirasyon süresi uzatılabilir.",
        "en": "To reduce auto-PEEP, respiratory rate may be decreased and expiratory time prolonged.",
        "importance": "high",
        "tags": ["auto_peep_management", "respiratory_rate", "expiratory_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 039
    {
        "id": "aba_basic_spot_039",
        "exam": "ABA_BASIC",
        "topic": "Ventilation",
        "subtopic": "PEEP",
        "tr": "PEEP atelektaziyi azaltabilir ancak venöz dönüşü ve kardiyak debiyi azaltabilir.",
        "en": "PEEP may reduce atelectasis but can decrease venous return and cardiac output.",
        "importance": "high",
        "tags": ["peep", "atelectasis_reduction", "venous_return", "cardiac_output"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 040
    {
        "id": "aba_basic_spot_040",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Pulse oksimetre PaO₂’yi değil hemoglobin oksijen satürasyonunu tahmin eder.",
        "en": "Pulse oximetry estimates hemoglobin oxygen saturation, not PaO₂.",
        "importance": "high",
        "tags": ["pulse_oximetry", "spo2", "pao2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 041
    {
        "id": "aba_basic_spot_041",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Karbonmonoksit zehirlenmesinde pulse oksimetre yanlış yüksek SpO₂ gösterebilir.",
        "en": "In carbon monoxide poisoning, pulse oximetry may show a falsely high SpO₂.",
        "importance": "high",
        "tags": ["carbon_monoxide", "carboxyhemoglobin", "spo2_accuracy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 042
    {
        "id": "aba_basic_spot_042",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Methemoglobinemide pulse oksimetre değeri yaklaşık %85 civarına yaklaşabilir.",
        "en": "In methemoglobinemia, pulse oximetry may tend toward approximately 85%.",
        "importance": "high",
        "tags": ["methemoglobinemia", "spo2_limit"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 043
    {
        "id": "aba_basic_spot_043",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Kapnografi ventilasyon, perfüzyon ve metabolizmanın birlikte değerlendirilmesini sağlar.",
        "en": "Capnography reflects the combined effects of ventilation, perfusion, and metabolism.",
        "importance": "high",
        "tags": ["capnography", "ventilation", "perfusion", "metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 044
    {
        "id": "aba_basic_spot_044",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Obstrüktif akciğer hastalığında kapnografi dalgası shark-fin görünümü alabilir.",
        "en": "In obstructive lung disease, the capnography waveform may show a shark-fin appearance.",
        "importance": "high",
        "tags": ["shark_fin_waveform", "obstructive_lung_disease", "copd", "bronchospasm"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 045
    {
        "id": "aba_basic_spot_045",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Pulse oksimetre hareket, düşük perfüzyon ve ortam ışığından etkilenebilir.",
        "en": "Pulse oximetry may be affected by motion, low perfusion, and ambient light.",
        "importance": "high",
        "tags": ["pulse_oximetry_limitations", "low_perfusion", "motion_artifacts"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 046
    {
        "id": "aba_basic_spot_046",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Noninvasive Blood Pressure",
        "tr": "Noninvaziv kan basıncı ölçümü manşon boyutu hatalarından etkilenebilir.",
        "en": "Noninvasive blood pressure measurement may be affected by cuff size errors.",
        "importance": "high",
        "tags": ["nibp", "cuff_size_error"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 047
    {
        "id": "aba_basic_spot_047",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Invasive Blood Pressure",
        "tr": "Arteriyel transdüser sağ atriyum seviyesinde sıfırlanmalıdır.",
        "en": "The arterial transducer should be zeroed at the level of the right atrium.",
        "importance": "high",
        "tags": ["arterial_line", "transducer_zeroing", "phlebostatic_axis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 048
    {
        "id": "aba_basic_spot_048",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Invasive Blood Pressure",
        "tr": "Transdüser kalp seviyesinin altında kalırsa ölçülen basınç olduğundan yüksek görünür.",
        "en": "If the transducer is below heart level, the measured pressure appears falsely high.",
        "importance": "high",
        "tags": ["transducer_level", "pressure_errors", "arterial_line"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 049
    {
        "id": "aba_basic_spot_049",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "ECG",
        "tr": "EKG ritim ve iskemi takibinde kullanılır ancak kardiyak debiyi doğrudan ölçmez.",
        "en": "ECG is used for rhythm and ischemia monitoring but does not directly measure cardiac output.",
        "importance": "high",
        "tags": ["ecg", "rhythm_monitoring", "ischemia_monitoring"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 050
    {
        "id": "aba_basic_spot_050",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Standard Monitoring",
        "tr": "Standart intraoperatif monitörizasyon oksijenasyon, ventilasyon, dolaşım ve sıcaklık takibini içerir.",
        "en": "Standard intraoperative monitoring includes oxygenation, ventilation, circulation, and temperature.",
        "importance": "high",
        "tags": ["standard_monitoring", "asa_standards", "intraoperative_monitoring"],
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
