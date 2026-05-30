import json
import os

existing_path = "app/src/main/assets/board_spot_notes.json"

# Load existing
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
        "id": "edaic_a_spot_151",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Boyle yasasına göre sabit sıcaklıkta gaz hacmi basınçla ters orantılıdır.",
        "en": "According to Boyle’s law, gas volume is inversely proportional to pressure at constant temperature.",
        "importance": "high",
        "tags": ["boyles_law", "gas_laws", "pressure_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 152
    {
        "id": "edaic_a_spot_152",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Charles yasasına göre sabit basınçta gaz hacmi mutlak sıcaklıkla doğru orantılıdır.",
        "en": "According to Charles’s law, gas volume is directly proportional to absolute temperature at constant pressure.",
        "importance": "high",
        "tags": ["charless_law", "gas_laws", "temperature_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 153
    {
        "id": "edaic_a_spot_153",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Dalton yasasına göre gaz karışımının toplam basıncı, gazların parsiyel basınçlarının toplamıdır.",
        "en": "According to Dalton’s law, the total pressure of a gas mixture equals the sum of the partial pressures of its gases.",
        "importance": "high",
        "tags": ["daltons_law", "gas_laws", "partial_pressures"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 154
    {
        "id": "edaic_a_spot_154",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Henry yasasına göre sıvıda çözünen gaz miktarı, gazın parsiyel basıncı ile doğru orantılıdır.",
        "en": "According to Henry’s law, the amount of gas dissolved in a liquid is directly proportional to its partial pressure.",
        "importance": "high",
        "tags": ["henrys_law", "gas_laws", "solubility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 155
    {
        "id": "edaic_a_spot_155",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Kritik sıcaklık üzerinde bir gaz yalnızca basınç artırılarak sıvılaştırılamaz.",
        "en": "Above its critical temperature, a gas cannot be liquefied by pressure alone.",
        "importance": "high",
        "tags": ["critical_temperature", "liquefaction", "pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 156
    {
        "id": "edaic_a_spot_156",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Cylinders",
        "tr": "Oksijen tüp basıncı tüpte kalan oksijen miktarını yaklaşık olarak gösterir.",
        "en": "Oxygen cylinder pressure approximately reflects the amount of oxygen remaining in the cylinder.",
        "importance": "high",
        "tags": ["oxygen_cylinder", "pressure_gauge", "cylinder_content"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 157
    {
        "id": "edaic_a_spot_157",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Cylinders",
        "tr": "Azot protoksit tüp basıncı, sıvı faz tamamen bitene kadar tüpte kalan miktarı güvenilir göstermez.",
        "en": "Nitrous oxide cylinder pressure does not reliably indicate remaining content until the liquid phase is exhausted.",
        "importance": "high",
        "tags": ["nitrous_oxide", "liquid_phase", "cylinder_content"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 158
    {
        "id": "edaic_a_spot_158",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Cylinders",
        "tr": "Azot protoksit tüpünde basınç düşmeye başladıysa sıvı faz büyük ölçüde tükenmiştir.",
        "en": "If nitrous oxide cylinder pressure begins to fall, the liquid phase is largely depleted.",
        "importance": "high",
        "tags": ["nitrous_oxide", "cylinder_depletion", "pressure_drop"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 159
    {
        "id": "edaic_a_spot_159",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Laminer akımda Poiseuille yasası geçerlidir ve direnç tüp yarıçapının dördüncü kuvveti ile ters orantılıdır.",
        "en": "In laminar flow, Poiseuille’s law applies and resistance is inversely proportional to the fourth power of tube radius.",
        "importance": "high",
        "tags": ["poiseuilles_law", "laminar_flow", "resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 160
    {
        "id": "edaic_a_spot_160",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Türbülanslı akımda direnç akım hızı ve gaz yoğunluğundan daha fazla etkilenir.",
        "en": "In turbulent flow, resistance is more strongly influenced by flow rate and gas density.",
        "importance": "high",
        "tags": ["turbulent_flow", "gas_density", "resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 161
    {
        "id": "edaic_a_spot_161",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Reynolds sayısı arttıkça türbülanslı akım gelişme olasılığı artar.",
        "en": "As Reynolds number increases, the likelihood of turbulent flow increases.",
        "importance": "high",
        "tags": ["reynolds_number", "turbulent_flow", "laminar_flow"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 162
    {
        "id": "edaic_a_spot_162",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Daralma, yüksek akım hızı ve keskin kıvrımlar türbülansı artırır.",
        "en": "Narrowing, high flow velocity, and sharp bends increase turbulence.",
        "importance": "high",
        "tags": ["turbulent_flow", "narrowing", "flow_velocity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 163
    {
        "id": "edaic_a_spot_163",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Helyoks, düşük yoğunluğu nedeniyle üst havayolu obstrüksiyonunda solunum iş yükünü azaltabilir.",
        "en": "Heliox can reduce work of breathing in upper airway obstruction because of its low density.",
        "importance": "high",
        "tags": ["heliox", "density", "work_of_breathing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 164
    {
        "id": "edaic_a_spot_164",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Vaporizers",
        "tr": "Vaporizatörler belirli sıcaklık ve akım koşullarında kontrollü volatil ajan konsantrasyonu sağlar.",
        "en": "Vaporizers deliver controlled volatile agent concentrations under defined temperature and flow conditions.",
        "importance": "high",
        "tags": ["vaporizer", "volatile_anesthetics", "concentration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 165
    {
        "id": "edaic_a_spot_165",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Vaporizers",
        "tr": "Volatil ajanların buhar basıncı sıcaklık arttıkça artar.",
        "en": "The vapor pressure of volatile agents increases as temperature rises.",
        "importance": "high",
        "tags": ["vapor_pressure", "temperature", "latent_heat"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 166
    {
        "id": "edaic_a_spot_166",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Vaporizers",
        "tr": "Vaporizatör sıcaklık kompanzasyonu, ortam sıcaklığı değişse bile çıkış konsantrasyonunu sabit tutmaya yardım eder.",
        "en": "Temperature compensation in vaporizers helps maintain stable output concentration despite ambient temperature changes.",
        "importance": "high",
        "tags": ["vaporizer", "temperature_compensation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 167
    {
        "id": "edaic_a_spot_167",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Vaporizers",
        "tr": "Desfluranın yüksek buhar basıncı nedeniyle özel ısıtılmış ve basınç kontrollü vaporizatör gerekir.",
        "en": "Desflurane requires a special heated and pressure-controlled vaporizer because of its high vapor pressure.",
        "importance": "high",
        "tags": ["desflurane", "vaporizer", "heated_vaporizer"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 168
    {
        "id": "edaic_a_spot_168",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Anesthesia Circuit",
        "tr": "Düşük taze gaz akımı volatil ajan tüketimini ve çevresel atığı azaltır.",
        "en": "Low fresh gas flow reduces volatile agent consumption and environmental waste.",
        "importance": "high",
        "tags": ["fresh_gas_flow", "low_flow_anesthesia", "volatile_agents"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 169
    {
        "id": "edaic_a_spot_169",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "CO2 Absorption",
        "tr": "Soda lime karbondioksiti absorbe eder; absorbent tükenirse inspire CO₂ artabilir.",
        "en": "Soda lime absorbs carbon dioxide; exhausted absorbent may increase inspired CO₂.",
        "importance": "high",
        "tags": ["soda_lime", "co2_absorption", "rebreathing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 170
    {
        "id": "edaic_a_spot_170",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "CO2 Absorption",
        "tr": "CO₂ absorbentinin kuruması karbonmonoksit oluşum riskini artırabilir.",
        "en": "Desiccated CO₂ absorbent may increase the risk of carbon monoxide formation.",
        "importance": "high",
        "tags": ["soda_lime", "desiccated_absorbent", "carbon_monoxide"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 171
    {
        "id": "edaic_a_spot_171",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Anesthesia Circuit",
        "tr": "Düşük akım anestezide devre sızıntıları ve FiO₂ daha dikkatli izlenmelidir.",
        "en": "During low-flow anesthesia, circuit leaks and FiO₂ must be monitored more carefully.",
        "importance": "high",
        "tags": ["low_flow_anesthesia", "leak_monitoring", "fio2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 172
    {
        "id": "edaic_a_spot_172",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Pulse oksimetre iki dalga boyu ışık kullanarak oksijenlenmiş ve deoksijenlenmiş hemoglobini ayırt eder.",
        "en": "Pulse oximetry uses two wavelengths of light to distinguish oxygenated from deoxygenated hemoglobin.",
        "importance": "high",
        "tags": ["pulse_oximetry", "wavelengths", "hemoglobin"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 173
    {
        "id": "edaic_a_spot_173",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Hareket artefaktı, düşük perfüzyon ve oje pulse oksimetre doğruluğunu azaltabilir.",
        "en": "Motion artifact, low perfusion, and nail polish may reduce pulse oximeter accuracy.",
        "importance": "high",
        "tags": ["pulse_oximetry", "artifacts", "nail_polish"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 174
    {
        "id": "edaic_a_spot_174",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Kapnografide faz III alveoler plato olarak adlandırılır.",
        "en": "Phase III of the capnogram is called the alveolar plateau.",
        "importance": "high",
        "tags": ["capnography", "phase_iii", "alveolar_plateau"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 175
    {
        "id": "edaic_a_spot_175",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Kapnografide inspirasyon fazında CO₂ görülmesi rebreathing düşündürür.",
        "en": "CO₂ during the inspiratory phase of capnography suggests rebreathing.",
        "importance": "high",
        "tags": ["capnography", "inspiratory_co2", "rebreathing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 176
    {
        "id": "edaic_a_spot_176",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Standards",
        "tr": "Anestezi sırasında standart monitörizasyon oksijenasyon, ventilasyon, dolaşım ve sıcaklığı kapsar.",
        "en": "Standard monitoring during anesthesia includes oxygenation, ventilation, circulation, and temperature.",
        "importance": "high",
        "tags": ["standard_monitoring", "safety_standards"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 177
    {
        "id": "edaic_a_spot_177",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "ECG",
        "tr": "EKG miyokard iskemisini ve aritmileri saptamada kullanılır ancak kardiyak debiyi doğrudan ölçmez.",
        "en": "ECG is used to detect myocardial ischemia and arrhythmias, but it does not directly measure cardiac output.",
        "importance": "high",
        "tags": ["ecg", "ischemia", "cardiac_arrhythmia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 178
    {
        "id": "edaic_a_spot_178",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "ECG",
        "tr": "Beş derivasyonlu EKG sistemi iskemi takibinde üç derivasyona göre daha fazla bilgi sağlar.",
        "en": "A five-lead ECG system provides more information for ischemia monitoring than a three-lead system.",
        "importance": "high",
        "tags": ["ecg", "five_lead_ecg", "ischemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 179
    {
        "id": "edaic_a_spot_179",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Neuromuscular Monitoring",
        "tr": "Periferik sinir stimülasyonunda ulnar sinir–adduktor pollicis monitörizasyonu sık kullanılır.",
        "en": "Ulnar nerve–adductor pollicis monitoring is commonly used for peripheral nerve stimulation.",
        "importance": "high",
        "tags": ["ulnar_nerve", "adductor_pollicis", "nerve_stimulator"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 180
    {
        "id": "edaic_a_spot_180",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Neuromuscular Monitoring",
        "tr": "Fasiyal sinir–orbikülaris okuli monitörizasyonu entübasyon koşullarını adduktor pollicisten daha iyi yansıtabilir.",
        "en": "Facial nerve–orbicularis oculi monitoring may better reflect intubating conditions than adductor pollicis monitoring.",
        "importance": "high",
        "tags": ["facial_nerve", "orbicularis_oculi", "intubating_conditions"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 181
    {
        "id": "edaic_a_spot_181",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Neuromuscular Monitoring",
        "tr": "Adduktor pollicis derlenme takibinde klinik olarak yararlıdır.",
        "en": "Adductor pollicis monitoring is clinically useful for assessing recovery.",
        "importance": "high",
        "tags": ["adductor_pollicis", "recovery", "extubation_criteria"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 182
    {
        "id": "edaic_a_spot_182",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Hypothermia",
        "tr": "Hipotermi koagülopati, enfeksiyon riski ve ilaç etki süresinde uzama ile ilişkilidir.",
        "en": "Hypothermia is associated with coagulopathy, infection risk, and prolonged drug effects.",
        "importance": "high",
        "tags": ["hypothermia", "coagulopathy", "shivering"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 183
    {
        "id": "edaic_a_spot_183",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Hypothermia",
        "tr": "Genel anestezi vazodilatasyon ve ısı redistribüsyonu ile erken hipotermiye neden olabilir.",
        "en": "General anesthesia may cause early hypothermia through vasodilation and heat redistribution.",
        "importance": "high",
        "tags": ["general_anesthesia", "hypothermia", "vasodilatation", "heat_redistribution"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 184
    {
        "id": "edaic_a_spot_184",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Hypothermia",
        "tr": "Zorunlu hava ısıtıcılar perioperatif hipotermiyi önlemede etkilidir.",
        "en": "Forced-air warming devices are effective in preventing perioperative hypothermia.",
        "importance": "high",
        "tags": ["forced_air_warmers", "temperature_management"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 185
    {
        "id": "edaic_a_spot_185",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi tetikleyicileri volatil anestezikler ve süksinilkolindir.",
        "en": "Triggers of malignant hyperthermia include volatile anesthetics and succinylcholine.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "volatile_anesthetics", "succinylcholine"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 186
    {
        "id": "edaic_a_spot_186",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermide erken bulgulardan biri açıklanamayan ETCO₂ artışıdır.",
        "en": "One early sign of malignant hyperthermia is an unexplained rise in ETCO₂.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "etco2", "early_signs"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 187
    {
        "id": "edaic_a_spot_187",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Dantrolen malign hipertermi tedavisinde ryanodin reseptörü üzerinden kalsiyum salınımını azaltır.",
        "en": "Dantrolene treats malignant hyperthermia by reducing calcium release through the ryanodine receptor.",
        "importance": "high",
        "tags": ["dantrolene", "ryanodine_receptor", "calcium_release"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 188
    {
        "id": "edaic_a_spot_188",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Thermoregulation",
        "tr": "Isı üretimi metabolizma, kas aktivitesi ve titreme ile artar.",
        "en": "Heat production increases with metabolism, muscle activity, and shivering.",
        "importance": "high",
        "tags": ["heat_production", "shivering", "metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 189
    {
        "id": "edaic_a_spot_189",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Heat Loss",
        "tr": "Radyasyon ameliyathanede ısı kaybının önemli yollarından biridir.",
        "en": "Radiation is one of the important mechanisms of heat loss in the operating room.",
        "importance": "high",
        "tags": ["radiation", "heat_loss"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 190
    {
        "id": "edaic_a_spot_190",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Heat Loss",
        "tr": "Konveksiyon hava hareketiyle ısı kaybıdır.",
        "en": "Convection is heat loss through air movement.",
        "importance": "high",
        "tags": ["convection", "heat_loss"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 191
    {
        "id": "edaic_a_spot_191",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Heat Loss",
        "tr": "Kondüksiyon doğrudan temasla ısı transferidir.",
        "en": "Conduction is heat transfer through direct contact.",
        "importance": "high",
        "tags": ["conduction", "heat_loss"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 192
    {
        "id": "edaic_a_spot_192",
        "exam": "EDAIC_PAPER_A",
        "topic": "Thermoregulation",
        "subtopic": "Heat Loss",
        "tr": "Buharlaşma açık cerrahi alan ve solunum yoluyla ısı kaybına katkı sağlar.",
        "en": "Evaporation contributes to heat loss through open surgical fields and the respiratory tract.",
        "importance": "high",
        "tags": ["evaporation", "heat_loss"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 193
    {
        "id": "edaic_a_spot_193",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Duyarlılık, hastalığı olan bireylerde testin pozitif olma olasılığıdır.",
        "en": "Sensitivity is the probability that a test is positive in individuals who have the disease.",
        "importance": "high",
        "tags": ["sensitivity", "statistics", "diagnostic_tests"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 194
    {
        "id": "edaic_a_spot_194",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Özgüllük, hastalığı olmayan bireylerde testin negatif olma olasılığıdır.",
        "en": "Specificity is the probability that a test is negative in individuals who do not have the disease.",
        "importance": "high",
        "tags": ["specificity", "statistics", "diagnostic_tests"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 195
    {
        "id": "edaic_a_spot_195",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Pozitif prediktif değer, test pozitif olduğunda hastalığın gerçekten var olma olasılığıdır.",
        "en": "Positive predictive value is the probability that disease is truly present when the test is positive.",
        "importance": "high",
        "tags": ["ppv", "statistics", "predictive_values"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 196
    {
        "id": "edaic_a_spot_196",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Negatif prediktif değer, test negatif olduğunda hastalığın gerçekten olmama olasılığıdır.",
        "en": "Negative predictive value is the probability that disease is truly absent when the test is negative.",
        "importance": "high",
        "tags": ["npv", "statistics", "predictive_values"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 197
    {
        "id": "edaic_a_spot_197",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Hypothesis Testing",
        "tr": "P değeri, sıfır hipotezi doğruysa gözlenen veya daha uç bir sonucun görülme olasılığıdır.",
        "en": "The p value is the probability of observing the result, or a more extreme result, if the null hypothesis is true.",
        "importance": "high",
        "tags": ["p_value", "statistics", "null_hypothesis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 198
    {
        "id": "edaic_a_spot_198",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Hypothesis Testing",
        "tr": "Güven aralığı, tahmin edilen etkinin belirsizliğini gösterir.",
        "en": "A confidence interval shows the uncertainty around an estimated effect.",
        "importance": "high",
        "tags": ["confidence_interval", "statistics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 199
    {
        "id": "edaic_a_spot_199",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Hypothesis Testing",
        "tr": "Tip I hata doğru olan sıfır hipotezini yanlışlıkla reddetmektir.",
        "en": "A type I error is incorrectly rejecting a true null hypothesis.",
        "importance": "high",
        "tags": ["type_i_error", "statistics", "null_hypothesis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 200
    {
        "id": "edaic_a_spot_200",
        "exam": "EDAIC_PAPER_A",
        "topic": "Statistics",
        "subtopic": "Hypothesis Testing",
        "tr": "Tip II hata yanlış olan sıfır hipotezini reddedememektir.",
        "en": "A type II error is failing to reject a false null hypothesis.",
        "importance": "high",
        "tags": ["type_ii_error", "statistics", "null_hypothesis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

combined = spot_notes + new_spots

# Save updated list
with open(existing_path, "w", encoding="utf-8") as f:
    json.dump(combined, f, ensure_ascii=False, indent=2)

print(f"Successfully combined and wrote {len(combined)} spot notes to {existing_path}")
