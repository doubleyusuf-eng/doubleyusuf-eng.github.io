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
    # 051
    {
        "id": "edaic_a_spot_051",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Afterload",
        "tr": "Sistemik vasküler direnç, MAP ile kardiyak debi arasındaki ilişkiyi belirleyen temel afterload bileşenidir.",
        "en": "Systemic vascular resistance is a major afterload component determining the relationship between MAP and cardiac output.",
        "importance": "high",
        "tags": ["svr", "afterload", "cardiac_output"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 052
    {
        "id": "edaic_a_spot_052",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Mean Arterial Pressure",
        "tr": "MAP yaklaşık olarak kalp debisi ile sistemik vasküler direncin çarpımına bağlıdır.",
        "en": "MAP is approximately determined by the product of cardiac output and systemic vascular resistance.",
        "importance": "high",
        "tags": ["map", "cardiac_output", "svr"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 053
    {
        "id": "edaic_a_spot_053",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Central Venous Pressure",
        "tr": "Santral venöz basınç tek başına sıvı yanıtını güvenilir şekilde öngörmez.",
        "en": "Central venous pressure alone does not reliably predict fluid responsiveness.",
        "importance": "high",
        "tags": ["cvp", "fluid_responsiveness", "preload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 054
    {
        "id": "edaic_a_spot_054",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Hemodynamic Monitoring",
        "tr": "Stroke volume variation ve pulse pressure variation kontrollü ventilasyon ve düzenli ritim altında daha anlamlıdır.",
        "en": "Stroke volume variation and pulse pressure variation are more meaningful during controlled ventilation and regular cardiac rhythm.",
        "importance": "high",
        "tags": ["svv", "ppv", "fluid_responsiveness"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 055
    {
        "id": "edaic_a_spot_055",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Hemodynamic Monitoring",
        "tr": "Aritmi, spontan solunum ve düşük tidal volüm dinamik preload indekslerinin güvenilirliğini azaltır.",
        "en": "Arrhythmia, spontaneous breathing, and low tidal volume reduce the reliability of dynamic preload indices.",
        "importance": "high",
        "tags": ["svv", "ppv", "preload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 056
    {
        "id": "edaic_a_spot_056",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Arterial Line",
        "tr": "Arteriyel basınç dalga formunda aşırı sönümlenme sistolik basıncı düşük, diyastolik basıncı yüksek gösterebilir.",
        "en": "Overdamping of an arterial waveform may underestimate systolic pressure and overestimate diastolic pressure.",
        "importance": "high",
        "tags": ["arterial_line", "overdamping", "systolic_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 057
    {
        "id": "edaic_a_spot_057",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Arterial Line",
        "tr": "Az sönümlenmiş arteriyel sistem sistolik basıncı olduğundan yüksek gösterebilir.",
        "en": "Underdamping of an arterial system may overestimate systolic pressure.",
        "importance": "high",
        "tags": ["arterial_line", "underdamping", "systolic_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 058
    {
        "id": "edaic_a_spot_058",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Arterial Line",
        "tr": "Arteriyel transdüser sağ atriyum seviyesinde sıfırlanmalıdır.",
        "en": "The arterial transducer should be zeroed at the level of the right atrium.",
        "importance": "high",
        "tags": ["arterial_line", "transducer_level", "phlebostatic_axis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 059
    {
        "id": "edaic_a_spot_059",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Arterial Line",
        "tr": "Transdüser kalp seviyesinin altında kalırsa ölçülen arteriyel basınç olduğundan yüksek görünür.",
        "en": "If the transducer is below heart level, the measured arterial pressure appears falsely high.",
        "importance": "high",
        "tags": ["arterial_line", "transducer_level", "measurement_error"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 060
    {
        "id": "edaic_a_spot_060",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hypovolemia",
        "tr": "Hipovolemi genellikle dar nabız basıncı ve taşikardi ile ilişkilidir.",
        "en": "Hypovolemia is commonly associated with narrow pulse pressure and tachycardia.",
        "importance": "high",
        "tags": ["hypovolemia", "pulse_pressure", "tachycardia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 061
    {
        "id": "edaic_a_spot_061",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Sepsis",
        "tr": "Septik vazodilatasyonda nabız basıncı genişleyebilir ve sistemik vasküler direnç azalır.",
        "en": "In septic vasodilation, pulse pressure may widen and systemic vascular resistance decreases.",
        "importance": "high",
        "tags": ["sepsis", "vasodilation", "pulse_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 062
    {
        "id": "edaic_a_spot_062",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Coronary Perfusion",
        "tr": "Sol ventrikül koroner perfüzyon basıncı yaklaşık olarak diyastolik aort basıncı ile LVEDP farkına bağlıdır.",
        "en": "Left ventricular coronary perfusion pressure depends approximately on diastolic aortic pressure minus LVEDP.",
        "importance": "high",
        "tags": ["coronary_perfusion", "lvedp", "diastolic_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 063
    {
        "id": "edaic_a_spot_063",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Ventricular Function",
        "tr": "Sağ ventrikül afterload artışına sol ventrikülden daha duyarlıdır.",
        "en": "The right ventricle is more sensitive to increased afterload than the left ventricle.",
        "importance": "high",
        "tags": ["right_ventricle", "afterload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 064
    {
        "id": "edaic_a_spot_064",
        "exam": "EDAIC_PAPER_A",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Pulmonary Circulation",
        "tr": "Pulmoner vasküler direnç hipoksi, hiperkapni ve asidoz ile artar.",
        "en": "Pulmonary vascular resistance increases with hypoxia, hypercapnia, and acidosis.",
        "importance": "high",
        "tags": ["pulmonary_vascular_resistance", "hypoxia", "acidosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 065
    {
        "id": "edaic_a_spot_065",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Cerebral Blood Flow",
        "tr": "Hipokapni serebral vazokonstriksiyon yaparak serebral kan akımını azaltır.",
        "en": "Hypocapnia causes cerebral vasoconstriction and reduces cerebral blood flow.",
        "importance": "high",
        "tags": ["hypocapnia", "cerebral_blood_flow", "vasoconstriction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 066
    {
        "id": "edaic_a_spot_066",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Cerebral Blood Flow",
        "tr": "Hiperkapni serebral vazodilatasyon yaparak serebral kan akımını artırır.",
        "en": "Hypercapnia causes cerebral vasodilation and increases cerebral blood flow.",
        "importance": "high",
        "tags": ["hypercapnia", "cerebral_blood_flow", "vasodilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 067
    {
        "id": "edaic_a_spot_067",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Cerebral Autoregulation",
        "tr": "Serebral otoregülasyon belirli MAP aralığında serebral kan akımını sabit tutar.",
        "en": "Cerebral autoregulation maintains relatively constant cerebral blood flow within a defined MAP range.",
        "importance": "high",
        "tags": ["cerebral_blood_flow", "autoregulation", "map"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 068
    {
        "id": "edaic_a_spot_068",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Cerebral Autoregulation",
        "tr": "Kronik hipertansiyonda serebral otoregülasyon eğrisi sağa kayabilir.",
        "en": "In chronic hypertension, the cerebral autoregulation curve may shift to the right.",
        "importance": "high",
        "tags": ["hypertension", "autoregulation", "right_shift"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 069
    {
        "id": "edaic_a_spot_069",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Cerebral Perfusion Pressure",
        "tr": "İntrakraniyal basınç artışında serebral perfüzyon basıncı azalır.",
        "en": "Increased intracranial pressure reduces cerebral perfusion pressure.",
        "importance": "high",
        "tags": ["icp", "cpp", "cerebral_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 070
    {
        "id": "edaic_a_spot_070",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Cerebral Perfusion Pressure",
        "tr": "Serebral perfüzyon basıncı, MAP eksi intrakraniyal basınç olarak hesaplanır.",
        "en": "Cerebral perfusion pressure is calculated as MAP minus intracranial pressure.",
        "importance": "high",
        "tags": ["cpp", "map", "icp"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 071
    {
        "id": "edaic_a_spot_071",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Anesthetic Effects",
        "tr": "Volatil anestezikler genellikle serebral vazodilatasyon yapar ve dozla serebral kan akımını artırabilir.",
        "en": "Volatile anesthetics generally cause cerebral vasodilation and may increase cerebral blood flow in a dose-dependent manner.",
        "importance": "high",
        "tags": ["volatile_anesthetics", "cerebral_blood_flow", "vasodilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 072
    {
        "id": "edaic_a_spot_072",
        "exam": "EDAIC_PAPER_A",
        "topic": "Neurophysiology",
        "subtopic": "Anesthetic Effects",
        "tr": "Propofol serebral metabolik hızı ve serebral kan akımını azaltır.",
        "en": "Propofol reduces cerebral metabolic rate and cerebral blood flow.",
        "importance": "high",
        "tags": ["propofol", "cmro2", "cerebral_blood_flow"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 073
    {
        "id": "edaic_a_spot_073",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Minimum alveoler konsantrasyon yaşla azalır.",
        "en": "Minimum alveolar concentration decreases with age.",
        "importance": "high",
        "tags": ["mac", "aging", "volatile_anesthetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 074
    {
        "id": "edaic_a_spot_074",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "MAC, hastaların %50’sinde cerrahi uyarana hareket yanıtını önleyen alveoler konsantrasyondur.",
        "en": "MAC is the alveolar concentration that prevents movement to surgical stimulus in 50% of patients.",
        "importance": "high",
        "tags": ["mac", "volatile_anesthetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 075
    {
        "id": "edaic_a_spot_075",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Hipotermi, gebelik, opioidler, benzodiazepinler ve alfa-2 agonistler MAC’i azaltır.",
        "en": "Hypothermia, pregnancy, opioids, benzodiazepines, and alpha-2 agonists reduce MAC.",
        "importance": "high",
        "tags": ["mac", "mac_reduction", "hypothermia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 076
    {
        "id": "edaic_a_spot_076",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Hipertermi ve kronik alkol kullanımı MAC’i artırabilir.",
        "en": "Hyperthermia and chronic alcohol use may increase MAC.",
        "importance": "high",
        "tags": ["mac", "mac_increase", "alcoholism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 077
    {
        "id": "edaic_a_spot_077",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Azot protoksit ikinci gaz etkisi ile eş zamanlı verilen volatil ajan alımını hızlandırabilir.",
        "en": "Nitrous oxide may accelerate uptake of a concurrently administered volatile agent through the second gas effect.",
        "importance": "high",
        "tags": ["second_gas_effect", "nitrous_oxide", "gas_uptake"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 078
    {
        "id": "edaic_a_spot_078",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Düşük kan/gaz çözünürlüğü olan volatil ajanların indüksiyon ve derlenmesi daha hızlıdır.",
        "en": "Volatile agents with low blood/gas solubility have faster induction and recovery.",
        "importance": "high",
        "tags": ["solubility", "blood_gas_coefficient", "induction_speed"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 079
    {
        "id": "edaic_a_spot_079",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Desfluranın kan/gaz çözünürlüğü düşüktür; bu nedenle derlenme hızlıdır.",
        "en": "Desflurane has low blood/gas solubility, resulting in rapid recovery.",
        "importance": "high",
        "tags": ["desflurane", "solubility", "recovery_speed"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 080
    {
        "id": "edaic_a_spot_080",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Sevofluranın düşük irritan özelliği inhalasyon indüksiyonu için avantaj sağlar.",
        "en": "The low airway irritability of sevoflurane makes it useful for inhalational induction.",
        "importance": "high",
        "tags": ["sevoflurane", "inhalational_induction", "airway_irritability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 081
    {
        "id": "edaic_a_spot_081",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "İnhalasyon ajanlarının alveoler konsantrasyonu arttıkça beyin parsiyel basıncı da zamanla artar.",
        "en": "As alveolar concentration of an inhaled agent increases, brain partial pressure increases over time.",
        "importance": "high",
        "tags": ["alveolar_concentration", "brain_concentration", "pharmacokinetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 082
    {
        "id": "edaic_a_spot_082",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Yüksek alveoler ventilasyon volatil ajan indüksiyonunu hızlandırır.",
        "en": "High alveolar ventilation accelerates induction with volatile anesthetics.",
        "importance": "high",
        "tags": ["alveolar_ventilation", "induction_speed", "volatile_anesthetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 083
    {
        "id": "edaic_a_spot_083",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Yüksek kardiyak debi volatil ajanların alveoler konsantrasyon yükselişini yavaşlatır.",
        "en": "High cardiac output slows the rise in alveolar concentration of volatile anesthetics.",
        "importance": "high",
        "tags": ["cardiac_output", "volatile_anesthetics", "gas_uptake"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 084
    {
        "id": "edaic_a_spot_084",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Düşük çözünürlüklü ajanlarda kardiyak debinin indüksiyon hızına etkisi daha az belirgindir.",
        "en": "For poorly soluble agents, cardiac output has a smaller effect on induction speed.",
        "importance": "high",
        "tags": ["cardiac_output", "solubility", "induction_speed"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 085
    {
        "id": "edaic_a_spot_085",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Propofol GABA-A reseptör aktivitesini artırarak hipnoz oluşturur.",
        "en": "Propofol produces hypnosis by enhancing GABA-A receptor activity.",
        "importance": "high",
        "tags": ["propofol", "gaba_a", "hypnosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 086
    {
        "id": "edaic_a_spot_086",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Propofol vazodilatasyon ve miyokard depresyonu ile hipotansiyona neden olabilir.",
        "en": "Propofol can cause hypotension through vasodilation and myocardial depression.",
        "importance": "high",
        "tags": ["propofol", "hypotension", "myocardial_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 087
    {
        "id": "edaic_a_spot_087",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Etomidat hemodynamik stabilite sağlar ancak adrenal steroid sentezini baskılayabilir.",
        "en": "Etomidate provides hemodynamic stability but may suppress adrenal steroid synthesis.",
        "importance": "high",
        "tags": ["etomidate", "hemodynamic_stability", "adrenal_suppression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 088
    {
        "id": "edaic_a_spot_088",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Ketamin NMDA reseptör antagonisti olup analjezi ve dissosiyatif anestezi sağlar.",
        "en": "Ketamine is an NMDA receptor antagonist that provides analgesia and dissociative anesthesia.",
        "importance": "high",
        "tags": ["ketamine", "nmda_receptor", "analgesia", "dissociative_anesthesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 089
    {
        "id": "edaic_a_spot_089",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Ketamin sempatik aktivasyonu artırabilir; taşikardi ve hipertansiyon yapabilir.",
        "en": "Ketamine may increase sympathetic activity, causing tachycardia and hypertension.",
        "importance": "high",
        "tags": ["ketamine", "sympathetic_stimulation", "tachycardia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 090
    {
        "id": "edaic_a_spot_090",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Sedatives",
        "tr": "Benzodiazepinler GABA-A aracılı inhibitör etkiyi artırır.",
        "en": "Benzodiazepines enhance GABA-A mediated inhibitory activity.",
        "importance": "high",
        "tags": ["benzodiazepines", "gaba_a", "sedation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 091
    {
        "id": "edaic_a_spot_091",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Sedatives",
        "tr": "Midazolam suda çözünür formüle edilir ancak fizyolojik pH’da lipofilik hale gelir.",
        "en": "Midazolam is formulated as water-soluble but becomes lipophilic at physiological pH.",
        "importance": "high",
        "tags": ["midazolam", "ph_dependency", "solubility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 092
    {
        "id": "edaic_a_spot_092",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Opioids",
        "tr": "Opioidler esas olarak mü reseptör aktivasyonu ile analjezi sağlar.",
        "en": "Opioids provide analgesia mainly through mu receptor activation.",
        "importance": "high",
        "tags": ["opioids", "mu_receptor", "analgesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 093
    {
        "id": "edaic_a_spot_093",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Opioids",
        "tr": "Remifentanil esteraslarla metabolize olduğu için etki süresi infüzyon süresinden az etkilenir.",
        "en": "Remifentanil is metabolized by esterases, so its duration is minimally affected by infusion duration.",
        "importance": "high",
        "tags": ["remifentanil", "esterase_metabolism", "context_sensitive_half_life"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 094
    {
        "id": "edaic_a_spot_094",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Opioids",
        "tr": "Morfin histamin salınımına ve venodilatasyona neden olabilir.",
        "en": "Morphine may cause histamine release and venodilation.",
        "importance": "high",
        "tags": ["morphine", "histamine_release", "venodilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 095
    {
        "id": "edaic_a_spot_095",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Opioids",
        "tr": "Fentanil morfinden daha lipofiliktir ve daha hızlı etki başlangıcına sahiptir.",
        "en": "Fentanyl is more lipophilic than morphine and has a faster onset of action.",
        "importance": "high",
        "tags": ["fentanyl", "lipophilicity", "onset_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 096
    {
        "id": "edaic_a_spot_096",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Nöromüsküler kavşakta asetilkolin nikotinik reseptörleri aktive ederek kas kontraksiyonu başlatır.",
        "en": "At the neuromuscular junction, acetylcholine activates nicotinic receptors to initiate muscle contraction.",
        "importance": "high",
        "tags": ["nmj", "acetylcholine", "nicotinic_receptors"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 097
    {
        "id": "edaic_a_spot_097",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Nondepolarizan kas gevşeticiler nikotinik asetilkolin reseptörlerinde kompetitif antagonizma yapar.",
        "en": "Nondepolarizing neuromuscular blockers competitively antagonize nicotinic acetylcholine receptors.",
        "importance": "high",
        "tags": ["nondepolarizing_blockers", "competitive_antagonism", "nicotinic_receptors"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 098
    {
        "id": "edaic_a_spot_098",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Süksinilkolin depolarizan nöromüsküler blokerdir ve faz I blok oluşturur.",
        "en": "Succinylcholine is a depolarizing neuromuscular blocker that produces a phase I block.",
        "importance": "high",
        "tags": ["succinylcholine", "depolarizing_blocker", "phase_i_block"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 099
    {
        "id": "edaic_a_spot_099",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Süksinilkolin serum potasyumunu artırabilir; yanık, denervasyon ve nöromüsküler hastalıklarda risk artar.",
        "en": "Succinylcholine may increase serum potassium; the risk is higher in burns, denervation, and neuromuscular disease.",
        "importance": "high",
        "tags": ["succinylcholine", "potassium_release", "hyperkalemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 100
    {
        "id": "edaic_a_spot_100",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Roküronyum aminosteroid yapıda nondepolarizan kas gevşeticidir ve sugammadeks ile geri döndürülebilir.",
        "en": "Rocuronium is an aminosteroid nondepolarizing neuromuscular blocker and can be reversed with sugammadex.",
        "importance": "high",
        "tags": ["rocuronium", "sugammadex", "reversal_agent"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

combined = spot_notes + new_spots

# Save updated list
with open(existing_path, "w", encoding="utf-8") as f:
    json.dump(combined, f, ensure_ascii=False, indent=2)

print(f"Successfully combined and wrote {len(combined)} spot notes to {existing_path}")
