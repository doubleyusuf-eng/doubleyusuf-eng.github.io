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
        "id": "aba_basic_spot_101",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hemodynamics",
        "tr": "Kalp debisi, kalp hızı ile atım hacminin çarpımına eşittir.",
        "en": "Cardiac output equals heart rate multiplied by stroke volume.",
        "importance": "high",
        "tags": ["cardiac_output", "heart_rate", "stroke_volume", "calculation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 102
    {
        "id": "aba_basic_spot_102",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hemodynamics",
        "tr": "Atım hacmi preload, afterload ve kontraktiliteden etkilenir.",
        "en": "Stroke volume is influenced by preload, afterload, and contractility.",
        "importance": "high",
        "tags": ["stroke_volume", "preload", "afterload", "contractility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 103
    {
        "id": "aba_basic_spot_103",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hemodynamics",
        "tr": "MAP yaklaşık olarak kardiyak debi ile sistemik vasküler direncin çarpımına bağlıdır.",
        "en": "MAP is approximately related to the product of cardiac output and systemic vascular resistance.",
        "importance": "high",
        "tags": ["map", "cardiac_output", "svr", "calculation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 104
    {
        "id": "aba_basic_spot_104",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hemodynamics",
        "tr": "Hipotansiyonun temel nedenleri hipovolemi, vazodilatasyon, miyokard depresyonu ve aritmidir.",
        "en": "Major causes of hypotension include hypovolemia, vasodilation, myocardial depression, and arrhythmia.",
        "importance": "high",
        "tags": ["hypotension_causes", "hypovolemia", "vasodilation", "myocardial_depression", "arrhythmia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 105
    {
        "id": "aba_basic_spot_105",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hemodynamics",
        "tr": "Hipovolemide taşikardi ve dar nabız basıncı görülebilir.",
        "en": "Hypovolemia may present with tachycardia and narrow pulse pressure.",
        "importance": "high",
        "tags": ["hypovolemia", "tachycardia", "narrow_pulse_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 106
    {
        "id": "aba_basic_spot_106",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hemodynamics",
        "tr": "Vazodilatasyonda sistemik vasküler direnç azalır ve nabız basıncı genişleyebilir.",
        "en": "In vasodilation, systemic vascular resistance decreases and pulse pressure may widen.",
        "importance": "high",
        "tags": ["vasodilation", "svr", "pulse_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 107
    {
        "id": "aba_basic_spot_107",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Vasoactive Drugs",
        "tr": "Fenilefrin alfa-1 agonisttir ve sistemik vasküler direnci artırır.",
        "en": "Phenylephrine is an alpha-1 agonist that increases systemic vascular resistance.",
        "importance": "high",
        "tags": ["phenylephrine", "alpha_1_agonist", "svr"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 108
    {
        "id": "aba_basic_spot_108",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Vasoactive Drugs",
        "tr": "Efedrin kalp hızını ve kontraktiliteyi artırabilir; indirekt sempatomimetik etkisi vardır.",
        "en": "Ephedrine may increase heart rate and contractility and has indirect sympathomimetic activity.",
        "importance": "high",
        "tags": ["ephedrine", "indirect_sympathomimetic", "heart_rate", "contractility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 109
    {
        "id": "aba_basic_spot_109",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Vasoactive Drugs",
        "tr": "Noradrenalin güçlü alfa etkisiyle vazokonstriksiyon yapar ve beta-1 etkisi de taşır.",
        "en": "Norepinephrine causes vasoconstriction through strong alpha effects and also has beta-1 activity.",
        "importance": "high",
        "tags": ["norepinephrine", "vasoconstriction", "alpha_effects", "beta_1"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 110
    {
        "id": "aba_basic_spot_110",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Vasoactive Drugs",
        "tr": "Dobutamin beta-1 agonist etkiyle kontraktiliteyi artırır.",
        "en": "Dobutamine increases contractility through beta-1 agonist activity.",
        "importance": "high",
        "tags": ["dobutamine", "beta_1_agonist", "contractility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 111
    {
        "id": "aba_basic_spot_111",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Vasoactive Drugs",
        "tr": "Adrenalin düşük dozda beta etkileri, yüksek dozda alfa etkileri daha belirgin gösterir.",
        "en": "Epinephrine has more prominent beta effects at low doses and more prominent alpha effects at higher doses.",
        "importance": "high",
        "tags": ["epinephrine", "beta_effects", "alpha_effects", "dose_dependent"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 112
    {
        "id": "aba_basic_spot_112",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Hemodynamics",
        "tr": "Bradikardi ve hipotansiyon birlikteyse perfüzyon bozulabilir ve hızlı tedavi gerekebilir.",
        "en": "When bradycardia and hypotension occur together, perfusion may be impaired and rapid treatment may be required.",
        "importance": "high",
        "tags": ["bradycardia", "hypotension", "perfusion_impairment", "treatment"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 113
    {
        "id": "aba_basic_spot_113",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Coronary Circulation",
        "tr": "Koroner perfüzyon esas olarak diyastolde gerçekleşir.",
        "en": "Coronary perfusion occurs mainly during diastole.",
        "importance": "high",
        "tags": ["coronary_perfusion", "diastole"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 114
    {
        "id": "aba_basic_spot_114",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Coronary Circulation",
        "tr": "Taşikardi diyastol süresini kısaltarak koroner perfüzyonu azaltabilir.",
        "en": "Tachycardia shortens diastolic time and may reduce coronary perfusion.",
        "importance": "high",
        "tags": ["tachycardia", "diastolic_time", "coronary_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 115
    {
        "id": "aba_basic_spot_115",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Ciddi aort stenozunda preload, sinüs ritmi ve sistemik vasküler direnç korunmalıdır.",
        "en": "In severe aortic stenosis, preload, sinus rhythm, and systemic vascular resistance should be maintained.",
        "importance": "high",
        "tags": ["aortic_stenosis", "preload", "sinus_rhythm", "svr"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 116
    {
        "id": "aba_basic_spot_116",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Mitral stenozda taşikardi pulmoner konjesyonu kötüleştirebilir.",
        "en": "In mitral stenosis, tachycardia may worsen pulmonary congestion.",
        "importance": "high",
        "tags": ["mitral_stenosis", "tachycardia", "pulmonary_congestion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 117
    {
        "id": "aba_basic_spot_117",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Aort yetersizliğinde ağır bradikardi regürjitan volümü artırabilir.",
        "en": "In aortic regurgitation, severe bradycardia may increase regurgitant volume.",
        "importance": "high",
        "tags": ["aortic_regurgitation", "bradycardia", "regurgitant_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 118
    {
        "id": "aba_basic_spot_118",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Care",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Pulmoner hipertansiyonda hipoksi, hiperkapni ve asidoz pulmoner vasküler direnci artırır.",
        "en": "In pulmonary hypertension, hypoxia, hypercapnia, and acidosis increase pulmonary vascular resistance.",
        "importance": "high",
        "tags": ["pulmonary_hypertension", "pulmonary_vascular_resistance", "hypoxia", "hypercapnia", "acidosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 119
    {
        "id": "aba_basic_spot_119",
        "exam": "ABA_BASIC",
        "topic": "Cardiovascular Physiology",
        "subtopic": "Right Ventricle",
        "tr": "Sağ ventrikül ani afterload artışını kötü tolere eder.",
        "en": "The right ventricle poorly tolerates a sudden increase in afterload.",
        "importance": "high",
        "tags": ["right_ventricle", "afterload", "pulmonary_vascular_resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 120
    {
        "id": "aba_basic_spot_120",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Invasive Blood Pressure",
        "tr": "Arteriyel hat, sürekli kan basıncı takibi ve kan gazı örneklemesi için kullanılır.",
        "en": "An arterial line is used for continuous blood pressure monitoring and blood gas sampling.",
        "importance": "high",
        "tags": ["arterial_line", "blood_pressure_monitoring", "blood_gas_sampling"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 121
    {
        "id": "aba_basic_spot_121",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Central Venous Pressure",
        "tr": "Santral venöz basınç tek başına sıvı yanıtını güvenilir şekilde göstermez.",
        "en": "Central venous pressure alone does not reliably indicate fluid responsiveness.",
        "importance": "high",
        "tags": ["cvp", "fluid_responsiveness"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 122
    {
        "id": "aba_basic_spot_122",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Hemodynamic Monitoring",
        "tr": "Dinamik preload indeksleri kontrollü ventilasyon ve düzenli ritimde daha güvenilidir.",
        "en": "Dynamic preload indices are more reliable during controlled ventilation and regular rhythm.",
        "importance": "high",
        "tags": ["dynamic_preload_indices", "controlled_ventilation", "regular_rhythm"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 123
    {
        "id": "aba_basic_spot_123",
        "exam": "ABA_BASIC",
        "topic": "Monitoring",
        "subtopic": "Hemodynamic Monitoring",
        "tr": "Aritmi ve spontan solunum, pulse pressure variation ve stroke volume variation yorumunu zorlaştırır.",
        "en": "Arrhythmia and spontaneous breathing make interpretation of pulse pressure variation and stroke volume variation difficult.",
        "importance": "high",
        "tags": ["ppv", "svv", "arrhythmia", "spontaneous_breathing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 124
    {
        "id": "aba_basic_spot_124",
        "exam": "ABA_BASIC",
        "topic": "Renal Physiology",
        "subtopic": "Renal Blood Flow",
        "tr": "Böbrek kan akımı kardiyak debinin yaklaşık %20–25’ini alır.",
        "en": "Renal blood flow receives approximately 20–25% of cardiac output.",
        "importance": "high",
        "tags": ["renal_blood_flow", "cardiac_output", "kidney_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 125
    {
        "id": "aba_basic_spot_125",
        "exam": "ABA_BASIC",
        "topic": "Organ Disease Basics",
        "subtopic": "Renal Failure",
        "tr": "Prerenal akut böbrek hasarı genellikle renal perfüzyon azalmasına bağlıdır.",
        "en": "Prerenal acute kidney injury is usually due to reduced renal perfusion.",
        "importance": "high",
        "tags": ["prerenal_aki", "renal_perfusion", "acute_kidney_injury"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 126
    {
        "id": "aba_basic_spot_126",
        "exam": "ABA_BASIC",
        "topic": "Organ Disease Basics",
        "subtopic": "Renal Failure",
        "tr": "Kreatinin akut böbrek hasarında geç yükselebilir ve kas kitlesinden etkilenir.",
        "en": "Creatinine may rise late in acute kidney injury and is affected by muscle mass.",
        "importance": "high",
        "tags": ["creatinine", "acute_kidney_injury", "muscle_mass", "renal_function"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 127
    {
        "id": "aba_basic_spot_127",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Hyperkalemia",
        "tr": "Hiperkalemi EKG’de sivri T dalgaları ve QRS genişlemesi yapabilir.",
        "en": "Hyperkalemia may cause peaked T waves and QRS widening on ECG.",
        "importance": "high",
        "tags": ["hyperkalemia", "peaked_t_waves", "qrs_widening", "ecg_changes"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 128
    {
        "id": "aba_basic_spot_128",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Hyperkalemia",
        "tr": "EKG değişikliği olan hiperkalemide kalsiyum membran stabilizasyonu için acil verilir.",
        "en": "In hyperkalemia with ECG changes, calcium is given urgently for membrane stabilization.",
        "importance": "high",
        "tags": ["hyperkalemia", "calcium", "membrane_stabilization", "ecg_changes"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 129
    {
        "id": "aba_basic_spot_129",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Hyperkalemia",
        "tr": "İnsülin ve glukoz potasyumu hücre içine kaydırarak hiperkalemiyi geçici olarak azaltır.",
        "en": "Insulin and glucose temporarily reduce hyperkalemia by shifting potassium into cells.",
        "importance": "high",
        "tags": ["hyperkalemia_treatment", "insulin_glucose", "potassium_shift"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 130
    {
        "id": "aba_basic_spot_130",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Acid-Base Balance",
        "tr": "Metabolik asidozda primer bozukluk bikarbonat azalmasıdır.",
        "en": "In metabolic acidosis, the primary disturbance is decreased bicarbonate.",
        "importance": "high",
        "tags": ["metabolic_acidosis", "bicarbonate_decrease"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 131
    {
        "id": "aba_basic_spot_131",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Acid-Base Balance",
        "tr": "Respiratuvar asidozda primer bozukluk PaCO₂ artışıdır.",
        "en": "In respiratory acidosis, the primary disturbance is increased PaCO₂.",
        "importance": "high",
        "tags": ["respiratory_acidosis", "paco2_increase"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 132
    {
        "id": "aba_basic_spot_132",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Acid-Base Balance",
        "tr": "Metabolik alkaloz hidrojen iyonu kaybı veya bikarbonat artışı ile ilişkilidir.",
        "en": "Metabolic alkalosis is related to hydrogen ion loss or increased bicarbonate.",
        "importance": "high",
        "tags": ["metabolic_alkalosis", "hydrogen_loss", "bicarbonate_increase"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 133
    {
        "id": "aba_basic_spot_133",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Acid-Base Balance",
        "tr": "Respiratuvar alkalozda primer bozukluk PaCO₂ azalmasıdır.",
        "en": "In respiratory alkalosis, the primary disturbance is decreased PaCO₂.",
        "importance": "high",
        "tags": ["respiratory_alkalosis", "paco2_decrease"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 134
    {
        "id": "aba_basic_spot_134",
        "exam": "ABA_BASIC",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Acid-Base Balance",
        "tr": "Laktat yüksekliği doku hipoperfüzyonu, sepsis veya artmış metabolik stres ile ilişkili olabilir.",
        "en": "Elevated lactate may be associated with tissue hypoperfusion, sepsis, or increased metabolic stress.",
        "importance": "high",
        "tags": ["lactate", "tissue_hypoperfusion", "sepsis", "metabolic_stress"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 135
    {
        "id": "aba_basic_spot_135",
        "exam": "ABA_BASIC",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Diabetic Ketoacidosis",
        "tr": "Diyabetik ketoasidozda sıvı, insülin ve potasyum takibi temel tedavi bileşenleridir.",
        "en": "In diabetic ketoacidosis, fluid, insulin, and potassium monitoring are core treatment components.",
        "importance": "high",
        "tags": ["dka", "fluid_therapy", "insulin", "potassium"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 136
    {
        "id": "aba_basic_spot_136",
        "exam": "ABA_BASIC",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Hypoglycemia",
        "tr": "Hipoglisemi anestezi altında fark edilmeyebilir ve nörolojik hasara yol açabilir.",
        "en": "Hypoglycemia may be missed under anesthesia and can cause neurological injury.",
        "importance": "high",
        "tags": ["hypoglycemia", "general_anesthesia", "neurological_injury"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 137
    {
        "id": "aba_basic_spot_137",
        "exam": "ABA_BASIC",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Adrenal Insufficiency",
        "tr": "Uzun süre steroid kullanan hastalarda adrenal supresyon ve perioperatif steroid ihtiyacı değerlendirilmelidir.",
        "en": "In patients receiving long-term steroids, adrenal suppression and perioperative steroid requirement should be assessed.",
        "importance": "high",
        "tags": ["adrenal_suppression", "steroid_dependence", "perioperative_steroids"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 138
    {
        "id": "aba_basic_spot_138",
        "exam": "ABA_BASIC",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Hyperthyroidism",
        "tr": "Hipertiroidi taşikardi, hipertansiyon ve artmış metabolik gereksinimle ilişkilidir.",
        "en": "Hyperthyroidism is associated with tachycardia, hypertension, and increased metabolic demand.",
        "importance": "high",
        "tags": ["hyperthyroidism", "tachycardia", "hypertension", "metabolic_demand"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 139
    {
        "id": "aba_basic_spot_139",
        "exam": "ABA_BASIC",
        "topic": "Endocrine & Metabolic",
        "subtopic": "Hypothyroidism",
        "tr": "Hipotiroidi ilaçlara duyarlılığı artırabilir ve hipotermiye yatkınlık oluşturabilir.",
        "en": "Hypothyroidism may increase sensitivity to drugs and predispose to hypothermia.",
        "importance": "high",
        "tags": ["hypothyroidism", "drug_sensitivity", "hypothermia_predisposition"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 140
    {
        "id": "aba_basic_spot_140",
        "exam": "ABA_BASIC",
        "topic": "Thermoregulation",
        "subtopic": "Hypothermia",
        "tr": "Genel anestezi periferik vazodilatasyon ve ısı redistribüsyonu ile erken hipotermiye neden olabilir.",
        "en": "General anesthesia may cause early hypothermia through peripheral vasodilation and heat redistribution.",
        "importance": "high",
        "tags": ["general_anesthesia", "hypothermia_mechanism", "peripheral_vasodilation", "heat_redistribution"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 141
    {
        "id": "aba_basic_spot_141",
        "exam": "ABA_BASIC",
        "topic": "Thermoregulation",
        "subtopic": "Hypothermia",
        "tr": "Hipotermi koagülopati, yara enfeksiyonu ve ilaç etkilerinde uzama ile ilişkilidir.",
        "en": "Hypothermia is associated with coagulopathy, wound infection, and prolonged drug effects.",
        "importance": "high",
        "tags": ["hypothermia_complications", "coagulopathy", "wound_infection", "prolonged_drug_effects"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 142
    {
        "id": "aba_basic_spot_142",
        "exam": "ABA_BASIC",
        "topic": "Thermoregulation",
        "subtopic": "Hypothermia",
        "tr": "Zorunlu hava ısıtma sistemleri perioperatif hipotermiyi azaltmada etkilidir.",
        "en": "Forced-air warming systems are effective in reducing perioperative hypothermia.",
        "importance": "high",
        "tags": ["forced_air_warming", "hypothermia_prevention"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 143
    {
        "id": "aba_basic_spot_143",
        "exam": "ABA_BASIC",
        "topic": "Thermoregulation",
        "subtopic": "Shivering",
        "tr": "Postoperatif titreme oksijen tüketimini artırır ve kardiyak hastalarda zararlı olabilir.",
        "en": "Postoperative shivering increases oxygen consumption and may be harmful in cardiac patients.",
        "importance": "high",
        "tags": ["shivering", "oxygen_consumption", "cardiac_workload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 144
    {
        "id": "aba_basic_spot_144",
        "exam": "ABA_BASIC",
        "topic": "Transfusion Medicine",
        "subtopic": "Transfusion Safety",
        "tr": "Transfüzyon öncesi doğru hasta, doğru kan ürünü ve uygunluk kontrolü kritik güvenlik basamaklarıdır.",
        "en": "Before transfusion, correct patient, correct blood product, and compatibility checks are critical safety steps.",
        "importance": "high",
        "tags": ["transfusion_safety", "compatibility_check", "patient_identification"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 145
    {
        "id": "aba_basic_spot_145",
        "exam": "ABA_BASIC",
        "topic": "Transfusion Medicine",
        "subtopic": "Transfusion Reactions",
        "tr": "Akut hemolitik transfüzyon reaksiyonunda transfüzyon derhal durdurulmalıdır.",
        "en": "In acute hemolytic transfusion reaction, the transfusion must be stopped immediately.",
        "importance": "high",
        "tags": ["acute_hemolytic_reaction", "transfusion_safety", "emergency_stop"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 146
    {
        "id": "aba_basic_spot_146",
        "exam": "ABA_BASIC",
        "topic": "Transfusion Medicine",
        "subtopic": "Massive Transfusion",
        "tr": "Masif transfüzyonda hipokalsemi sitrat yüküne bağlı gelişebilir.",
        "en": "During massive transfusion, hypocalcemia may develop due to citrate load.",
        "importance": "high",
        "tags": ["massive_transfusion", "citrate_toxicity", "hypocalcemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 147
    {
        "id": "aba_basic_spot_147",
        "exam": "ABA_BASIC",
        "topic": "Transfusion Medicine",
        "subtopic": "TRALI",
        "tr": "TRALI akut hipoksemi ve nonkardiyojenik pulmoner ödem ile seyreder.",
        "en": "TRALI presents with acute hypoxemia and noncardiogenic pulmonary edema.",
        "importance": "high",
        "tags": ["trali", "transfusion_complications", "noncardiogenic_pulmonary_edema", "hypoxemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 148
    {
        "id": "aba_basic_spot_148",
        "exam": "ABA_BASIC",
        "topic": "Transfusion Medicine",
        "subtopic": "TACO",
        "tr": "TACO volüm yüklenmesine bağlı pulmoner ödemdir.",
        "en": "TACO is pulmonary edema due to volume overload.",
        "importance": "high",
        "tags": ["taco", "volume_overload", "pulmonary_edema"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 149
    {
        "id": "aba_basic_spot_149",
        "exam": "ABA_BASIC",
        "topic": "Transfusion Medicine",
        "subtopic": "Blood Components",
        "tr": "Taze donmuş plazma pıhtılaşma faktörlerini yerine koymak için kullanılır.",
        "en": "Fresh frozen plasma is used to replace coagulation factors.",
        "importance": "high",
        "tags": ["ffp", "fresh_frozen_plasma", "coagulation_factors"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 150
    {
        "id": "aba_basic_spot_150",
        "exam": "ABA_BASIC",
        "topic": "Transfusion Medicine",
        "subtopic": "Blood Components",
        "tr": "Trombosit transfüzyonu trombosit sayısı veya fonksiyon bozukluğuna bağlı kanamada gerekebilir.",
        "en": "Platelet transfusion may be required for bleeding due to low platelet count or platelet dysfunction.",
        "importance": "high",
        "tags": ["platelet_transfusion", "thrombocytopenia", "platelet_dysfunction", "bleeding"],
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
