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
    # 151
    {
        "id": "aba_basic_spot_151",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Boyle yasasına göre sabit sıcaklıkta gaz hacmi basınçla ters orantılıdır.",
        "en": "According to Boyle’s law, gas volume is inversely proportional to pressure at constant temperature.",
        "importance": "high",
        "tags": ["boyle_law", "gas_laws", "volume_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 152
    {
        "id": "aba_basic_spot_152",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Charles yasasına göre sabit basınçta gaz hacmi mutlak sıcaklıkla doğru orantılıdır.",
        "en": "According to Charles’s law, gas volume is directly proportional to absolute temperature at constant pressure.",
        "importance": "high",
        "tags": ["charles_law", "gas_laws", "volume_temperature"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 153
    {
        "id": "aba_basic_spot_153",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Dalton yasasına göre toplam basınç, gazların parsiyel basınçlarının toplamıdır.",
        "en": "According to Dalton’s law, total pressure equals the sum of the partial pressures of gases.",
        "importance": "high",
        "tags": ["dalton_law", "gas_laws", "partial_pressures"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 154
    {
        "id": "aba_basic_spot_154",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Laws",
        "tr": "Henry yasasına göre sıvıda çözünen gaz miktarı gazın parsiyel basıncı ile doğru orantılıdır.",
        "en": "According to Henry’s law, the amount of gas dissolved in a liquid is directly proportional to its partial pressure.",
        "importance": "high",
        "tags": ["henry_law", "gas_laws", "solubility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 155
    {
        "id": "aba_basic_spot_155",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Cylinders",
        "tr": "Oksijen tüp basıncı tüpte kalan oksijen miktarını yaklaşık olarak gösterir.",
        "en": "Oxygen cylinder pressure approximately reflects the amount of oxygen remaining in the cylinder.",
        "importance": "high",
        "tags": ["oxygen_cylinder", "cylinder_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 156
    {
        "id": "aba_basic_spot_156",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Gas Cylinders",
        "tr": "Azot protoksit tüp basıncı, sıvı faz bitene kadar tüpte kalan miktarı güvenilir göstermez.",
        "en": "Nitrous oxide cylinder pressure does not reliably indicate remaining content until the liquid phase is exhausted.",
        "importance": "high",
        "tags": ["nitrous_oxide_cylinder", "cylinder_pressure", "liquid_phase"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 157
    {
        "id": "aba_basic_spot_157",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Anesthesia Machine",
        "tr": "Düşük taze gaz akımı volatil ajan tüketimini ve ameliyathane kirliliğini azaltır.",
        "en": "Low fresh gas flow reduces volatile agent consumption and operating room pollution.",
        "importance": "high",
        "tags": ["low_flow_anesthesia", "fresh_gas_flow", "agent_consumption"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 158
    {
        "id": "aba_basic_spot_158",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Vaporizers",
        "tr": "Vaporizatörler volatil anesteziklerin kontrollü konsantrasyonda verilmesini sağlar.",
        "en": "Vaporizers deliver volatile anesthetics at controlled concentrations.",
        "importance": "high",
        "tags": ["vaporizers", "volatile_delivery"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 159
    {
        "id": "aba_basic_spot_159",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Vaporizers",
        "tr": "Volatil ajanların buhar basıncı sıcaklık arttıkça artar.",
        "en": "The vapor pressure of volatile agents increases as temperature rises.",
        "importance": "high",
        "tags": ["vapor_pressure", "temperature_effects"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 160
    {
        "id": "aba_basic_spot_160",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Vaporizers",
        "tr": "Desfluran yüksek buhar basıncı nedeniyle özel vaporizatör gerektirir.",
        "en": "Desflurane requires a special vaporizer because of its high vapor pressure.",
        "importance": "high",
        "tags": ["desflurane", "desflurane_vaporizer", "vapor_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 161
    {
        "id": "aba_basic_spot_161",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Carbon Dioxide Absorption",
        "tr": "CO₂ absorbenti tükenirse inspire CO₂ artabilir ve rebreathing gelişebilir.",
        "en": "If CO₂ absorbent is exhausted, inspired CO₂ may increase and rebreathing may occur.",
        "importance": "high",
        "tags": ["co2_absorbent", "inspired_co2", "rebreathing", "soda_lime"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 162
    {
        "id": "aba_basic_spot_162",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Carbon Dioxide Absorption",
        "tr": "Kurumuş CO₂ absorbenti karbonmonoksit oluşum riskini artırabilir.",
        "en": "Desiccated CO₂ absorbent may increase the risk of carbon monoxide formation.",
        "importance": "high",
        "tags": ["desiccated_co2_absorbent", "carbon_monoxide", "soda_lime", "safety"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 163
    {
        "id": "aba_basic_spot_163",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Anesthesia Machine",
        "tr": "Anestezi devresi kaçağı düşük tidal volüm, yetersiz ventilasyon veya düşük basınç alarmına neden olabilir.",
        "en": "An anesthesia circuit leak may cause low tidal volume, inadequate ventilation, or a low-pressure alarm.",
        "importance": "high",
        "tags": ["circuit_leak", "low_tidal_volume", "low_pressure_alarm"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 164
    {
        "id": "aba_basic_spot_164",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Anesthesia Machine",
        "tr": "APL valfi manuel ventilasyonda devre basıncı ve gaz çıkışını kontrol eder.",
        "en": "The APL valve controls circuit pressure and gas release during manual ventilation.",
        "importance": "high",
        "tags": ["apl_valve", "manual_ventilation", "circuit_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 165
    {
        "id": "aba_basic_spot_165",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Anesthesia Machine",
        "tr": "Oksijen flush düğmesi yüksek akımlı oksijen verir ve barotravma riski oluşturabilir.",
        "en": "The oxygen flush button delivers high-flow oxygen and may create a risk of barotrauma.",
        "importance": "high",
        "tags": ["oxygen_flush", "high_flow_oxygen", "barotrauma"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 166
    {
        "id": "aba_basic_spot_166",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Mechanics",
        "tr": "Laminer akımda direnç tüp yarıçapının dördüncü kuvveti ile ters orantılıdır.",
        "en": "In laminar flow, resistance is inversely proportional to the fourth power of tube radius.",
        "importance": "high",
        "tags": ["laminar_flow", "poiseuille_law", "tube_radius"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 167
    {
        "id": "aba_basic_spot_167",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Mechanics",
        "tr": "Turbülan akımda direnç gaz yoğunluğu ve akım hızı ile daha fazla ilişkilidir.",
        "en": "In turbulent flow, resistance is more closely related to gas density and flow rate.",
        "importance": "high",
        "tags": ["turbulent_flow", "density", "flow_rate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 168
    {
        "id": "aba_basic_spot_168",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Mechanics",
        "tr": "Küçük tüp çapı azalması havayolu direncini belirgin artırır.",
        "en": "A small reduction in tube diameter markedly increases airway resistance.",
        "importance": "high",
        "tags": ["tube_diameter", "airway_resistance", "poiseuille_law"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 169
    {
        "id": "aba_basic_spot_169",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Mechanics",
        "tr": "Reynolds sayısı arttıkça türbülan akım olasılığı artar.",
        "en": "As Reynolds number increases, the likelihood of turbulent flow increases.",
        "importance": "high",
        "tags": ["reynolds_number", "turbulent_flow", "flow_transition"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 170
    {
        "id": "aba_basic_spot_170",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Fluid Mechanics",
        "tr": "Heliox düşük yoğunluğu nedeniyle türbülan akım direncini azaltabilir.",
        "en": "Heliox may reduce resistance during turbulent flow because of its low density.",
        "importance": "high",
        "tags": ["heliox", "density_reduction", "turbulent_flow_resistance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 171
    {
        "id": "aba_basic_spot_171",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Electrical Safety",
        "tr": "Elektrik güvenliğinde izolasyon ve kaçak akım kontrolü hasta yanığı ve elektrik çarpması riskini azaltır.",
        "en": "Electrical isolation and leakage current control reduce the risk of patient burns and electric shock.",
        "importance": "high",
        "tags": ["electrical_safety", "isolation", "leakage_current", "burns_prevention"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 172
    {
        "id": "aba_basic_spot_172",
        "exam": "ABA_BASIC",
        "topic": "Physics & Equipment",
        "subtopic": "Electrical Safety",
        "tr": "Monopolar koter pacemaker veya ICD fonksiyonunu etkileyebilir.",
        "en": "Monopolar cautery may interfere with pacemaker or ICD function.",
        "importance": "high",
        "tags": ["monopolar_cautery", "pacemaker", "icd_interference"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 173
    {
        "id": "aba_basic_spot_173",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi tetikleyicileri volatil anestezikler ve süksinilkolindir.",
        "en": "Triggers of malignant hyperthermia include volatile anesthetics and succinylcholine.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "trigger_agents", "succinylcholine", "volatile_anesthetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 174
    {
        "id": "aba_basic_spot_174",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermide açıklanamayan ETCO₂ artışı erken bulgulardan biridir.",
        "en": "An unexplained rise in ETCO₂ is one of the early signs of malignant hyperthermia.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "etco2_elevation", "early_signs"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 175
    {
        "id": "aba_basic_spot_175",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi şüphesinde tetikleyici ajanlar kesilmeli, dantrolen verilmeli ve aktif soğutma başlanmalıdır.",
        "en": "If malignant hyperthermia is suspected, triggering agents should be stopped, dantrolene given, and active cooling started.",
        "importance": "high",
        "tags": ["malignant_hyperthermia_treatment", "dantrolene", "cooling"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 176
    {
        "id": "aba_basic_spot_176",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilakside temel tedavi adrenalin, yüksek FiO₂, sıvı resüsitasyonu ve tetikleyicinin kesilmesidir.",
        "en": "Core treatment of anaphylaxis includes epinephrine, high FiO₂, fluid resuscitation, and stopping the trigger.",
        "importance": "high",
        "tags": ["anaphylaxis", "epinephrine", "fluid_resuscitation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 177
    {
        "id": "aba_basic_spot_177",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Anaphylaxis",
        "tr": "Perioperatif anafilakside deri bulguları olmayabilir; hipotansiyon ve bronkospazm baskın olabilir.",
        "en": "Skin signs may be absent in perioperative anaphylaxis; hypotension and bronchospasm may predominate.",
        "importance": "high",
        "tags": ["perioperative_anaphylaxis", "bronchospasm", "hypotension", "skin_signs_absent"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 178
    {
        "id": "aba_basic_spot_178",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Care",
        "subtopic": "Bronchospasm",
        "tr": "Bronkospazmda yüksek peak basınç, uzamış ekspirasyon ve shark-fin kapnografi görülebilir.",
        "en": "Bronchospasm may cause high peak pressure, prolonged expiration, and a shark-fin capnogram.",
        "importance": "high",
        "tags": ["bronchospasm", "peak_pressure", "shark_fin_waveform"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 179
    {
        "id": "aba_basic_spot_179",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Complications",
        "tr": "Laringospazmda ilk yaklaşım %100 oksijen, CPAP, jaw thrust ve uyarının kesilmesidir.",
        "en": "Initial management of laryngospasm includes 100% oxygen, CPAP, jaw thrust, and stopping stimulation.",
        "importance": "high",
        "tags": ["laryngospasm", "oxygenation", "cpap", "jaw_thrust"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 180
    {
        "id": "aba_basic_spot_180",
        "exam": "ABA_BASIC",
        "topic": "Airway Management",
        "subtopic": "Complications",
        "tr": "Tam laringospazm ve ventilasyon yokluğunda süksinilkolin gerekebilir.",
        "en": "Complete laryngospasm with inability to ventilate may require succinylcholine.",
        "importance": "high",
        "tags": ["complete_laryngospasm", "succinylcholine", "emergency"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 181
    {
        "id": "aba_basic_spot_181",
        "exam": "ABA_BASIC",
        "topic": "Respiratory Care",
        "subtopic": "Pulmonary Edema",
        "tr": "Negatif basınçlı pulmoner ödem üst havayolu obstrüksiyonu sonrası gelişebilir.",
        "en": "Negative-pressure pulmonary edema may occur after upper airway obstruction.",
        "importance": "high",
        "tags": ["nppe", "negative_pressure_pulmonary_edema", "airway_obstruction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 182
    {
        "id": "aba_basic_spot_182",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Postoperatif hipoksemi atelektazi, opioid etkisi, rezidüel blok veya aspirasyona bağlı olabilir.",
        "en": "Postoperative hypoxemia may be due to atelectasis, opioid effect, residual block, or aspiration.",
        "importance": "high",
        "tags": ["postoperative_hypoxemia", "atelectasis", "opioid_effect", "residual_block", "aspiration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 183
    {
        "id": "aba_basic_spot_183",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Rezidüel nöromüsküler blok havayolu obstrüksiyonu ve aspirasyon riskini artırır.",
        "en": "Residual neuromuscular blockade increases the risk of airway obstruction and aspiration.",
        "importance": "high",
        "tags": ["residual_block", "airway_obstruction", "aspiration_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 184
    {
        "id": "aba_basic_spot_184",
        "exam": "ABA_BASIC",
        "topic": "PONV Prophylaxis",
        "subtopic": "Risk Factors",
        "tr": "PONV risk faktörleri kadın cinsiyet, sigara içmeme, PONV öyküsü ve postoperatif opioid kullanımıdır.",
        "en": "PONV risk factors include female sex, nonsmoking status, history of PONV, and postoperative opioid use.",
        "importance": "high",
        "tags": ["ponv", "risk_factors", "opioids", "motion_sickness"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 185
    {
        "id": "aba_basic_spot_185",
        "exam": "ABA_BASIC",
        "topic": "PONV Prophylaxis",
        "subtopic": "Prophylaxis",
        "tr": "Multimodal antiemetik profilaksi yüksek PONV riskli hastalarda daha etkilidir.",
        "en": "Multimodal antiemetic prophylaxis is more effective in patients at high risk for PONV.",
        "importance": "high",
        "tags": ["ponv_prophylaxis", "antiemetics", "multimodal"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 186
    {
        "id": "aba_basic_spot_186",
        "exam": "ABA_BASIC",
        "topic": "Pain Management",
        "subtopic": "Obstructive Sleep Apnea",
        "tr": "Obstrüktif uyku apnesi olan hastalarda opioid ve sedatifler postoperatif solunum depresyonu riskini artırır.",
        "en": "In patients with obstructive sleep apnea, opioids and sedatives increase the risk of postoperative respiratory depression.",
        "importance": "high",
        "tags": ["osa", "postoperative_opioids", "respiratory_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 187
    {
        "id": "aba_basic_spot_187",
        "exam": "ABA_BASIC",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Aspirasyon pnömonisi hipoksemi, bronkospazm ve akciğer infiltratları ile seyredebilir.",
        "en": "Aspiration pneumonitis may present with hypoxemia, bronchospasm, and pulmonary infiltrates.",
        "importance": "high",
        "tags": ["aspiration_pneumonitis", "hypoxemia", "bronchospasm"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 188
    {
        "id": "aba_basic_spot_188",
        "exam": "ABA_BASIC",
        "topic": "Geriatric Anesthesia",
        "subtopic": "Pharmacodynamics",
        "tr": "Yaşlı hastalarda ilaçlara duyarlılık artabilir ve dozlar dikkatle titre edilmelidir.",
        "en": "Elderly patients may have increased drug sensitivity, and doses should be carefully titrated.",
        "importance": "high",
        "tags": ["geriatric_pharmacology", "drug_titration", "pharmacodynamics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 189
    {
        "id": "aba_basic_spot_189",
        "exam": "ABA_BASIC",
        "topic": "Pediatric Anesthesia",
        "subtopic": "Pharmacology",
        "tr": "Pediatrik hastalarda ilaç dozları genellikle kilogram başına hesaplanır.",
        "en": "In pediatric patients, drug doses are usually calculated per kilogram.",
        "importance": "high",
        "tags": ["pediatric_dosing", "weight_based_dosing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 190
    {
        "id": "aba_basic_spot_190",
        "exam": "ABA_BASIC",
        "topic": "Obstetric Anesthesia",
        "subtopic": "Physiology",
        "tr": "Gebelerde hızlı desatürasyon, zor havayolu ve aspirasyon riski artmıştır.",
        "en": "Pregnant patients have increased risks of rapid desaturation, difficult airway, and aspiration.",
        "importance": "high",
        "tags": ["pregnancy_risks", "rapid_desaturation", "difficult_airway", "aspiration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 191
    {
        "id": "aba_basic_spot_191",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Duyarlılık hastalığı olan kişilerde testin pozitif olma olasılığıdır.",
        "en": "Sensitivity is the probability that a test is positive in people who have the disease.",
        "importance": "high",
        "tags": ["sensitivity", "diagnostic_tests", "true_positive"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 192
    {
        "id": "aba_basic_spot_192",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Özgüllük hastalığı olmayan kişilerde testin negatif olma olasılığıdır.",
        "en": "Specificity is the probability that a test is negative in people who do not have the disease.",
        "importance": "high",
        "tags": ["specificity", "diagnostic_tests", "true_negative"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 193
    {
        "id": "aba_basic_spot_193",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Pozitif prediktif değer, pozitif test sonucunda hastalığın gerçekten var olma olasılığıdır.",
        "en": "Positive predictive value is the probability that disease is truly present when the test is positive.",
        "importance": "high",
        "tags": ["ppv", "positive_predictive_value"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 194
    {
        "id": "aba_basic_spot_194",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Diagnostic Tests",
        "tr": "Negatif prediktif değer, negatif test sonucunda hastalığın gerçekten olmama olasılığıdır.",
        "en": "Negative predictive value is the probability that disease is truly absent when the test is negative.",
        "importance": "high",
        "tags": ["npv", "negative_predictive_value"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 195
    {
        "id": "aba_basic_spot_195",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Hypothesis Testing",
        "tr": "Tip I hata doğru sıfır hipotezini yanlışlıkla reddetmektir.",
        "en": "A type I error is incorrectly rejecting a true null hypothesis.",
        "importance": "high",
        "tags": ["type_1_error", "alpha_error", "null_hypothesis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 196
    {
        "id": "aba_basic_spot_196",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Hypothesis Testing",
        "tr": "Tip II hata yanlış sıfır hipotezini reddedememektir.",
        "en": "A type II error is failing to reject a false null hypothesis.",
        "importance": "high",
        "tags": ["type_2_error", "beta_error", "null_hypothesis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 197
    {
        "id": "aba_basic_spot_197",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Hypothesis Testing",
        "tr": "P değeri, sıfır hipotezi doğruysa gözlenen veya daha uç bir sonucun görülme olasılığıdır.",
        "en": "The p value is the probability of observing the result, or a more extreme result, if the null hypothesis is true.",
        "importance": "high",
        "tags": ["p_value", "statistical_significance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 198
    {
        "id": "aba_basic_spot_198",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Estimation",
        "tr": "Güven aralığı tahminin belirsizliğini gösterir.",
        "en": "A confidence interval shows the uncertainty around an estimate.",
        "importance": "high",
        "tags": ["confidence_interval", "uncertainty"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 199
    {
        "id": "aba_basic_spot_199",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Study Design",
        "tr": "Randomizasyon gruplar arasında bilinen ve bilinmeyen karıştırıcıları dengelemeyi amaçlar.",
        "en": "Randomization aims to balance known and unknown confounders between groups.",
        "importance": "high",
        "tags": ["randomization", "confounding_factors", "clinical_trials"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 200
    {
        "id": "aba_basic_spot_200",
        "exam": "ABA_BASIC",
        "topic": "Statistics",
        "subtopic": "Study Design",
        "tr": "Körleme gözlemci ve performans yanlılığını azaltmak için kullanılır.",
        "en": "Blinding is used to reduce observer and performance bias.",
        "importance": "high",
        "tags": ["blinding", "bias_reduction", "placebo_control"],
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
