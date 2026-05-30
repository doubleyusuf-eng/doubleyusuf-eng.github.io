import json

# Define the clinical mapping of the 50 spots based on high-yield guidelines
raw_spots = [
    # 001 - FRC
    {
        "id": "edaic_a_spot_001",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Functional Residual Capacity",
        "tr": "FRC; supin pozisyon, obezite, gebelik ve genel anestezi ile azalır.",
        "en": "FRC decreases with supine positioning, obesity, pregnancy, and general anesthesia.",
        "importance": "high",
        "tags": ["frc", "respiratory_physiology", "general_anesthesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 002 - Closing Capacity
    {
        "id": "edaic_a_spot_002",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Closing Capacity",
        "tr": "Kapanma kapasitesi FRC’yi aşarsa küçük havayolları normal tidal solunum sırasında kapanmaya başlar.",
        "en": "When closing capacity exceeds FRC, small airways begin to close during normal tidal breathing.",
        "importance": "high",
        "tags": ["closing_capacity", "frc", "respiratory_physiology"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 003 - GA and FRC
    {
        "id": "edaic_a_spot_003",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Functional Residual Capacity",
        "tr": "Genel anestezi FRC’yi yaklaşık %15–20 azaltabilir ve atelektazi riskini artırır.",
        "en": "General anesthesia may reduce FRC by approximately 15–20% and increase the risk of atelectasis.",
        "importance": "high",
        "tags": ["frc", "general_anesthesia", "atelectasis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 004 - HPV
    {
        "id": "edaic_a_spot_004",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Pulmonary Circulation",
        "tr": "Hipoksik pulmoner vazokonstriksiyon, ventilasyonu azalmış akciğer bölgelerine kan akımını azaltarak V/Q uyumunu korur.",
        "en": "Hypoxic pulmonary vasoconstriction preserves V/Q matching by reducing blood flow to poorly ventilated lung regions.",
        "importance": "high",
        "tags": ["hpv", "vq_matching", "pulmonary_circulation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 005 - Volatiles and HPV
    {
        "id": "edaic_a_spot_005",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Pulmonary Circulation",
        "tr": "Volatil anestezikler hipoksik pulmoner vazokonstriksiyonu doza bağımlı olarak baskılayabilir.",
        "en": "Volatile anesthetics may inhibit hypoxic pulmonary vasoconstriction in a dose-dependent manner.",
        "importance": "high",
        "tags": ["hpv", "volatile_anesthetics", "pulmonary_circulation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 006 - Shunt vs low V/Q
    {
        "id": "edaic_a_spot_006",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Ventilation Perfusion",
        "tr": "Şantta oksijen tedavisine yanıt sınırlıdır; düşük V/Q durumunda oksijen yanıtı daha belirgindir.",
        "en": "True shunt has a limited response to oxygen therapy; low V/Q states respond more clearly to oxygen.",
        "importance": "high",
        "tags": ["shunt", "vq_ratio", "oxygen_therapy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 007 - Dead space ventilation
    {
        "id": "edaic_a_spot_007",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Dead Space",
        "tr": "Ölü boşluk ventilasyonu artarsa PaCO₂ yükselir veya aynı PaCO₂ için dakika ventilasyonu artırılmalıdır.",
        "en": "Increased dead space ventilation raises PaCO₂ or requires higher minute ventilation to maintain the same PaCO₂.",
        "importance": "high",
        "tags": ["dead_space", "paco2", "minute_ventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 008 - Anatomical dead space
    {
        "id": "edaic_a_spot_008",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Dead Space",
        "tr": "Anatomik ölü boşluk yaklaşık 2 mL/kg kabul edilir.",
        "en": "Anatomical dead space is commonly estimated as approximately 2 mL/kg.",
        "importance": "high",
        "tags": ["dead_space", "anatomical_dead_space"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 009 - CO2 elimination
    {
        "id": "edaic_a_spot_009",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Alveolar Ventilation",
        "tr": "CO₂ eliminasyonu esas olarak alveoler ventilasyona bağlıdır, FiO₂’ye bağlı değildir.",
        "en": "CO₂ elimination depends mainly on alveolar ventilation, not on FiO₂.",
        "importance": "high",
        "tags": ["co2_elimination", "alveolar_ventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 010 - PaCO2 and alveolar ventilation
    {
        "id": "edaic_a_spot_010",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Alveolar Ventilation",
        "tr": "PaCO₂ ile alveoler ventilasyon ters orantılıdır; alveoler ventilasyon yarıya düşerse PaCO₂ yaklaşık iki katına çıkar.",
        "en": "PaCO₂ is inversely related to alveolar ventilation; if alveolar ventilation halves, PaCO₂ approximately doubles.",
        "importance": "high",
        "tags": ["paco2", "alveolar_ventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 011 - Oxygen dissociation curve shift
    {
        "id": "edaic_a_spot_011",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Oxygen Transport",
        "tr": "Oksijen dissosiasyon eğrisinin sağa kayması hemoglobinin oksiyene afinitesini azaltır.",
        "en": "A right shift of the oxygen dissociation curve decreases hemoglobin affinity for oxygen.",
        "importance": "high",
        "tags": ["oxygen_dissociation_curve", "hemoglobin_affinity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 012 - Right shift factors
    {
        "id": "edaic_a_spot_012",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Oxygen Transport",
        "tr": "Ateş, asidoz, hiperkapni ve artmış 2,3-DPG oksijen dissosiasyon eğrisini sağa kaydırır.",
        "en": "Fever, acidosis, hypercapnia, and increased 2,3-DPG shift the oxygen dissociation curve to the right.",
        "importance": "high",
        "tags": ["oxygen_dissociation_curve", "right_shift"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 013 - Left shift factors
    {
        "id": "edaic_a_spot_013",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Oxygen Transport",
        "tr": "Hipotermi, alkaloz, hipokapni ve düşük 2,3-DPG oksijen dissosiasyon eğrisini sola kaydırır.",
        "en": "Hypothermia, alkalosis, hypocapnia, and reduced 2,3-DPG shift the oxygen dissociation curve to the left.",
        "importance": "high",
        "tags": ["oxygen_dissociation_curve", "left_shift"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 014 - Pulse oximeter estimation
    {
        "id": "edaic_a_spot_014",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Pulse oksimetre PaO₂’yi değil, hemoglobin oksijen satürasyonunu tahmin eder.",
        "en": "Pulse oximetry estimates hemoglobin oxygen saturation, not PaO₂.",
        "importance": "high",
        "tags": ["pulse_oximetry", "saturation", "oxygenation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 015 - Plateau of oxygen dissociation curve
    {
        "id": "edaic_a_spot_015",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Oxygen Transport",
        "tr": "Oksijen dissosiasyon eğrisinin plato kısmında SpO₂ küçük değişirken PaO₂ belirgin değişebilir.",
        "en": "On the plateau portion of the oxygen dissociation curve, PaO₂ may change substantially while SpO₂ changes only slightly.",
        "importance": "high",
        "tags": ["oxygen_dissociation_curve", "spo2", "pao2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 016 - PaO2 60 corresponds to SpO2 90%
    {
        "id": "edaic_a_spot_016",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Oxygen Transport",
        "tr": "PaO₂ yaklaşık 60 mmHg olduğunda SpO₂ genellikle yaklaşık %90’dır.",
        "en": "When PaO₂ is approximately 60 mmHg, SpO₂ is usually about 90%.",
        "importance": "high",
        "tags": ["pao2", "spo2", "oxygenation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 017 - Carbon monoxide poisoning
    {
        "id": "edaic_a_spot_017",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Karbonmonoksit zehirlenmesinde pulse oksimetre yanlış yüksek SpO₂ gösterebilir.",
        "en": "In carbon monoxide poisoning, pulse oximetry may show a falsely high SpO₂.",
        "importance": "high",
        "tags": ["pulse_oximetry", "carbon_monoxide", "carboxyhemoglobin"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 018 - Methemoglobinemia
    {
        "id": "edaic_a_spot_018",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Pulse Oximetry",
        "tr": "Methemoglobinemide pulse oksimetre değeri genellikle yaklaşık %85 civarına yaklaşır.",
        "en": "In methemoglobinemia, pulse oximetry often tends toward approximately 85%.",
        "importance": "high",
        "tags": ["pulse_oximetry", "methemoglobinemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 019 - Capnography
    {
        "id": "edaic_a_spot_019",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Kapnografi ventilasyon, perfüzyon ve metabolizmanın birlikte değerlendirilmesini sağlar.",
        "en": "Capnography reflects the combined effects of ventilation, perfusion, and metabolism.",
        "importance": "high",
        "tags": ["capnography", "etco2", "ventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 020 - Sudden loss of ETCO2
    {
        "id": "edaic_a_spot_020",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Ani ETCO₂ kaybı; tüp çıkması, devre ayrılması, kardiyak arrest veya ciddi pulmoner emboli ile ilişkili olabilir.",
        "en": "Sudden loss of ETCO₂ may indicate tube dislodgement, circuit disconnection, cardiac arrest, or massive pulmonary embolism.",
        "importance": "high",
        "tags": ["capnography", "etco2", "cardiac_arrest"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 021 - PaCO2-ETCO2 gradient
    {
        "id": "edaic_a_spot_021",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "ETCO₂ ile PaCO₂ arasındaki fark ölü boşluk artışında genişler.",
        "en": "The PaCO₂–ETCO₂ gradient widens when dead space increases.",
        "importance": "high",
        "tags": ["capnography", "dead_space", "paco2_etco2_gradient"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 022 - Obstructive lung disease shark-fin
    {
        "id": "edaic_a_spot_022",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Capnography",
        "tr": "Obstrüktif akciğer hastalığında kapnografi dalgası “shark-fin” görünümü alabilir.",
        "en": "In obstructive lung disease, the capnography waveform may show a “shark-fin” appearance.",
        "importance": "high",
        "tags": ["capnography", "obstructive_lung_disease", "shark_fin"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 023 - Compliance
    {
        "id": "edaic_a_spot_023",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "Compliance",
        "tr": "Kompliyans, hacim değişikliğinin basınç değişikliğine oranıdır.",
        "en": "Compliance is the ratio of volume change to pressure change.",
        "importance": "high",
        "tags": ["compliance", "ventilation_mechanics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 024 - Elastance
    {
        "id": "edaic_a_spot_024",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "Elastance",
        "tr": "Elastans kompliyansın tersidir; elastans arttıkça akciğer daha sert hale gelir.",
        "en": "Elastance is the inverse of compliance; increased elastance means a stiffer lung.",
        "importance": "high",
        "tags": ["elastance", "ventilation_mechanics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 025 - Peak vs Plateau
    {
        "id": "edaic_a_spot_025",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "Airway Pressures",
        "tr": "Peak basınç direnç ve kompliyanstan etkilenir; plateau basınç daha çok alveoler basıncı yansıtır.",
        "en": "Peak pressure is affected by both airway resistance and compliance; plateau pressure better reflects alveolar pressure.",
        "importance": "high",
        "tags": ["peak_pressure", "plateau_pressure", "airway_pressures"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 026 - High peak, normal plateau
    {
        "id": "edaic_a_spot_026",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "Airway Pressures",
        "tr": "Peak basınç yüksek, plateau normal ise artmış havayolu direnci düşünülür.",
        "en": "High peak pressure with normal plateau pressure suggests increased airway resistance.",
        "importance": "high",
        "tags": ["peak_pressure", "airway_resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 027 - High peak and plateau
    {
        "id": "edaic_a_spot_027",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "Airway Pressures",
        "tr": "Peak ve plateau basınç birlikte yüksekse azalmış akciğer/toraks kompliyansı düşünülür.",
        "en": "High peak and high plateau pressures suggest reduced lung or chest wall compliance.",
        "importance": "high",
        "tags": ["peak_pressure", "plateau_pressure", "compliance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 028 - Auto-PEEP
    {
        "id": "edaic_a_spot_028",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "PEEP",
        "tr": "Auto-PEEP özellikle obstrüktif akciğer hastalığında yetersiz ekspirasyon süresi ile gelişir.",
        "en": "Auto-PEEP develops especially in obstructive lung disease when expiratory time is insufficient.",
        "importance": "high",
        "tags": ["auto_peep", "peep", "obstructive_lung_disease"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 029 - Reducing auto-PEEP
    {
        "id": "edaic_a_spot_029",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "PEEP",
        "tr": "Auto-PEEP’i azaltmak için solunum frekansı düşürülür ve ekspirasyon süresi uzatılır.",
        "en": "To reduce auto-PEEP, lower the respiratory rate and prolong expiratory time.",
        "importance": "high",
        "tags": ["auto_peep", "peep", "expiratory_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 030 - PEEP effects
    {
        "id": "edaic_a_spot_030",
        "exam": "EDAIC_PAPER_A",
        "topic": "Ventilation Mechanics",
        "subtopic": "PEEP",
        "tr": "Solunumsal PEEP FRC’yi artırabilir, atelektaziyi azaltabilir ancak venöz dönüşü azaltabilir.",
        "en": "PEEP may increase FRC and reduce atelectasis, but it can decrease venous return.",
        "importance": "high",
        "tags": ["peep", "frc", "venous_return"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 031 - Laplace's law
    {
        "id": "edaic_a_spot_031",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Laplace Law",
        "tr": "Laplace yasasına göre alveolü açık tutmak için gereken basınç yüzey gerilimi ile artar, yarıçap ile azalır.",
        "en": "According to Laplace’s law, the pressure required to keep an alveolus open increases with surface tension and decreases with radius.",
        "importance": "high",
        "tags": ["laplace_law", "surface_tension", "alveoli"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 032 - Surfactant action
    {
        "id": "edaic_a_spot_032",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Surfactant",
        "tr": "Sürfaktan yüzey gerilimini azaltır ve küçük alveollerin kollapsını önler.",
        "en": "Surfactant reduces surface tension and prevents collapse of small alveoli.",
        "importance": "high",
        "tags": ["surfactant", "surface_tension", "alveoli"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 033 - Type II pneumocytes
    {
        "id": "edaic_a_spot_033",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Surfactant",
        "tr": "Tip II pnömositler sürfaktan üretir.",
        "en": "Type II pneumocytes produce surfactant.",
        "importance": "high",
        "tags": ["surfactant", "type_ii_pneumocytes"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 034 - V/Q ratio apex vs base
    {
        "id": "edaic_a_spot_034",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Ventilation Perfusion",
        "tr": "Ventilasyon/perfüzyon oranı akciğer apeksinde daha yüksek, bazalde daha düşüktür.",
        "en": "The ventilation/perfusion ratio is higher at the lung apex and lower at the base.",
        "importance": "high",
        "tags": ["vq_ratio", "lung_zones"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 035 - Pulmonary blood flow gravity
    {
        "id": "edaic_a_spot_035",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Pulmonary Circulation",
        "tr": "Pulmoner kan akımı yerçekimi etkisiyle akciğer bazallerinde daha fazladır.",
        "en": "Pulmonary blood flow is greater in the lung bases due to gravity.",
        "importance": "high",
        "tags": ["pulmonary_blood_flow", "gravity", "lung_zones"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 036 - Atelectasis dependent lung regions
    {
        "id": "edaic_a_spot_036",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Atelectasis",
        "tr": "Anestezi indüksiyonundan sonra atelektazi en sık bağımlı akciğer bölgelerinde gelişir.",
        "en": "After induction of anesthesia, atelectasis most commonly develops in dependent lung regions.",
        "importance": "high",
        "tags": ["atelectasis", "dependent_lung_regions", "general_anesthesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 037 - Preoxygenation and nitrogen washout
    {
        "id": "edaic_a_spot_037",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Preoxygenation",
        "tr": "Preoksijenasyon, akciğerdeki azotu oksijenle değiştirerek güvenli apne süresini uzatır.",
        "en": "Preoxygenation prolongs safe apnea time by replacing nitrogen in the lungs with oxygen.",
        "importance": "high",
        "tags": ["preoxygenation", "apnea_time", "nitrogen_washout"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 038 - Safe apnea time factors
    {
        "id": "edaic_a_spot_038",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Preoxygenation",
        "tr": "Obezite, gebelik ve pediatrik yaş güvenli apne süresini kısaltır.",
        "en": "Obesity, pregnancy, and pediatric age shorten safe apnea time.",
        "importance": "high",
        "tags": ["apnea_time", "obesity", "pregnancy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 039 - Children desaturate faster
    {
        "id": "edaic_a_spot_039",
        "exam": "EDAIC_PAPER_A",
        "topic": "Respiratory Physiology",
        "subtopic": "Preoxygenation",
        "tr": "Çocuklarda oksijen tüketimi daha yüksek, FRC daha düşük olduğu için desatürasyon daha hızlı gelişir.",
        "en": "Children desaturate faster because they have higher oxygen consumption and lower FRC.",
        "importance": "high",
        "tags": ["pediatrics", "oxygen_consumption", "frc"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 040 - Laminar flow resistance
    {
        "id": "edaic_a_spot_040",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Laminer akımda direnç yarıçapın dördüncü kuvveti ile ters orantılıdır.",
        "en": "In laminar flow, resistance is inversely proportional to the fourth power of the radius.",
        "importance": "high",
        "tags": ["laminar_flow", "resistance", "airway_resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 041 - Tube diameter resistance
    {
        "id": "edaic_a_spot_041",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Küçük bir tüp çapı azalması havayolu direncini belirgin artırır.",
        "en": "A small reduction in tube diameter markedly increases airway resistance.",
        "importance": "high",
        "tags": ["resistance", "tube_diameter", "airway_resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 042 - Turbulent flow resistance
    {
        "id": "edaic_a_spot_042",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Türbülanslı akımda direnç gaz yoğunluğundan etkilenir.",
        "en": "In turbulent flow, resistance is influenced by gas density.",
        "importance": "high",
        "tags": ["turbulent_flow", "density", "resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 043 - Helium low density
    {
        "id": "edaic_a_spot_043",
        "exam": "EDAIC_PAPER_A",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Dynamics",
        "tr": "Helyum düşük yoğunluğu nedeniyle türbülanslı akımda direnci azaltabilir.",
        "en": "Helium can reduce resistance during turbulent flow because of its low density.",
        "importance": "high",
        "tags": ["helium", "density", "turbulent_flow"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 044 - Cardiac output formula
    {
        "id": "edaic_a_spot_044",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Cardiac Output",
        "tr": "Kalp debisi, kalp hızı ile atım hacminin çarpımına eşittir.",
        "en": "Cardiac output equals heart rate multiplied by stroke volume.",
        "importance": "high",
        "tags": ["cardiac_output", "heart_rate", "stroke_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 045 - Stroke volume factors
    {
        "id": "edaic_a_spot_045",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Stroke Volume",
        "tr": "Atım hacmi preload, afterload ve kontraktiliteden etkilenir.",
        "en": "Stroke volume is influenced by preload, afterload, and contractility.",
        "importance": "high",
        "tags": ["stroke_volume", "preload", "afterload", "contractility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 046 - Frank-Starling mechanism
    {
        "id": "edaic_a_spot_046",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Preload",
        "tr": "Frank-Starling mekanizmasına göre belirli sınıra kadar preload artışı atım hacmini artırır.",
        "en": "According to the Frank-Starling mechanism, increasing preload increases stroke volume up to a physiological limit.",
        "importance": "high",
        "tags": ["frank_starling", "preload", "stroke_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 047 - Afterload and stroke volume
    {
        "id": "edaic_a_spot_047",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Afterload",
        "tr": "Afterload artışı sol ventrikül atım hacmini azaltabilir.",
        "en": "Increased afterload may reduce left ventricular stroke volume.",
        "importance": "high",
        "tags": ["afterload", "stroke_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 048 - Coronary perfusion
    {
        "id": "edaic_a_spot_048",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Coronary Circulation",
        "tr": "Koroner perfüzyon esas olarak diyastolde gerçekleşir.",
        "en": "Coronary perfusion occurs mainly during diastole.",
        "importance": "high",
        "tags": ["coronary_perfusion", "diastole", "coronary_circulation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 049 - Tachycardia and coronary perfusion
    {
        "id": "edaic_a_spot_049",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Coronary Circulation",
        "tr": "Taşikardi diyastol süresini kısaltarak koroner perfüzyonu azaltabilir.",
        "en": "Tachycardia shortens diastolic time and may reduce coronary perfusion.",
        "importance": "high",
        "tags": ["tachycardia", "diastolic_time", "coronary_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 050 - MAP calculation formula
    {
        "id": "edaic_a_spot_050",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Mean Arterial Pressure",
        "tr": "MAP yaklaşık olarak diyastolik basınç + nabız basıncının üçte biri şeklinde hesaplanabilir.",
        "en": "MAP can be approximated as diastolic pressure plus one-third of the pulse pressure.",
        "importance": "high",
        "tags": ["map", "diastolic_pressure", "pulse_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

# Write to board_spot_notes.json directly, completely replacing placeholder data
output_path = "app/src/main/assets/board_spot_notes.json"
with open(output_path, "w", encoding="utf-8") as f:
    json.dump(raw_spots, f, ensure_ascii=False, indent=2)

print(f"Successfully wrote {len(raw_spots)} spot notes to {output_path}")
