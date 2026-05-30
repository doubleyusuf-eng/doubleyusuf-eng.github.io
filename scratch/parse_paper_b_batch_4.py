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
        "id": "edaic_b_spot_151",
        "exam": "EDAIC_PAPER_B",
        "topic": "Trauma & Emergency",
        "subtopic": "Airway Management",
        "tr": "Travma hastasında hava yolu yönetimi servikal omurga korunarak planlanmalıdır.",
        "en": "Airway management in trauma should be planned while protecting the cervical spine.",
        "importance": "high",
        "tags": ["trauma", "cervical_spine", "airway_management"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 152
    {
        "id": "edaic_b_spot_152",
        "exam": "EDAIC_PAPER_B",
        "topic": "Trauma & Emergency",
        "subtopic": "Hemorrhagic Shock",
        "tr": "Hemorajik şokta taşikardi, hipotansiyon ve mental durum değişikliği geç bulgular olabilir.",
        "en": "In hemorrhagic shock, tachycardia, hypotension, and altered mental status may be late signs.",
        "importance": "high",
        "tags": ["hemorrhagic_shock", "tachycardia", "hypotension", "shock_signs"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 153
    {
        "id": "edaic_b_spot_153",
        "exam": "EDAIC_PAPER_B",
        "topic": "Trauma & Emergency",
        "subtopic": "Lethal Triad",
        "tr": "Travmada hipotermi, asidoz ve koagülopati mortaliteyi artıran ölümcül triadı oluşturur.",
        "en": "In trauma, hypothermia, acidosis, and coagulopathy form the lethal triad that increases mortality.",
        "importance": "high",
        "tags": ["lethal_triad", "hypothermia", "acidosis", "coagulopathy", "trauma"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 154
    {
        "id": "edaic_b_spot_154",
        "exam": "EDAIC_PAPER_B",
        "topic": "Trauma & Emergency",
        "subtopic": "Massive Transfusion",
        "tr": "Masif kanamada kristalloid yükünü sınırlamak ve dengeli kan ürünleri kullanmak önemlidir.",
        "en": "In massive bleeding, limiting crystalloid load and using balanced blood products are important.",
        "importance": "high",
        "tags": ["massive_bleeding", "crystalloid_restriction", "balanced_transfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 155
    {
        "id": "edaic_b_spot_155",
        "exam": "EDAIC_PAPER_B",
        "topic": "Trauma & Emergency",
        "subtopic": "Massive Transfusion",
        "tr": "Masif transfüzyonda kalsiyum düzeyi sitrat yükü nedeniyle düşebilir.",
        "en": "During massive transfusion, calcium levels may fall because of citrate load.",
        "importance": "high",
        "tags": ["massive_transfusion", "citrate_toxicity", "hypocalcemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 156
    {
        "id": "edaic_b_spot_156",
        "exam": "EDAIC_PAPER_B",
        "topic": "Fluid & Electrolytes",
        "subtopic": "Hypocalcemia",
        "tr": "Hipokalsemi hipotansiyon, koagülopati ve miyokard kontraktilitesinde azalmaya katkı sağlayabilir.",
        "en": "Hypocalcemia may contribute to hypotension, coagulopathy, and reduced myocardial contractility.",
        "importance": "high",
        "tags": ["hypocalcemia", "hypotension", "coagulopathy", "myocardial_contractility"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 157
    {
        "id": "edaic_b_spot_157",
        "exam": "EDAIC_PAPER_B",
        "topic": "Transfusion Medicine",
        "subtopic": "Transfusion Reactions",
        "tr": "Transfüzyon reaksiyonunda ateş, hipotansiyon, bronkospazm, hemoglobinüri veya yaygın kanama görülebilir.",
        "en": "Transfusion reactions may present with fever, hypotension, bronchospasm, hemoglobinuria, or diffuse bleeding.",
        "importance": "high",
        "tags": ["transfusion_reaction", "fever", "hypotension", "bronchospasm"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 158
    {
        "id": "edaic_b_spot_158",
        "exam": "EDAIC_PAPER_B",
        "topic": "Transfusion Medicine",
        "subtopic": "Transfusion Reactions",
        "tr": "Akut hemolitik transfüzyon reaksiyonunda transfüzyon derhal durdurulmalı ve destek tedavisi başlanmalıdır.",
        "en": "In acute hemolytic transfusion reaction, transfusion must be stopped immediately and supportive treatment initiated.",
        "importance": "high",
        "tags": ["acute_hemolytic_reaction", "transfusion_safety", "emergency_stop"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 159
    {
        "id": "edaic_b_spot_159",
        "exam": "EDAIC_PAPER_B",
        "topic": "Transfusion Medicine",
        "subtopic": "TRALI",
        "tr": "TRALI genellikle transfüzyondan sonraki ilk 6 saat içinde akut hipoksemi ve nonkardiyojenik pulmoner ödem ile seyreder.",
        "en": "TRALI usually presents within the first 6 hours after transfusion with acute hypoxemia and noncardiogenic pulmonary edema.",
        "importance": "high",
        "tags": ["trali", "transfusion_complications", "noncardiogenic_pulmonary_edema", "hypoxemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 160
    {
        "id": "edaic_b_spot_160",
        "exam": "EDAIC_PAPER_B",
        "topic": "Transfusion Medicine",
        "subtopic": "TACO",
        "tr": "TACO volüm yüklenmesine bağlı pulmoner ödemdir ve kalp yetmezliği olan hastalarda daha olasıdır.",
        "en": "TACO is pulmonary edema due to volume overload and is more likely in patients with heart failure.",
        "importance": "high",
        "tags": ["taco", "volume_overload", "pulmonary_edema", "heart_failure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 161
    {
        "id": "edaic_b_spot_161",
        "exam": "EDAIC_PAPER_B",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Disease",
        "tr": "Karaciğer hastalığında koagülasyon bozukluğu, hipoglisemi ve ilaç metabolizmasında gecikme görülebilir.",
        "en": "Liver disease may cause coagulopathy, hypoglycemia, and delayed drug metabolism.",
        "importance": "high",
        "tags": ["liver_disease", "coagulopathy", "hypoglycemia", "drug_metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 162
    {
        "id": "edaic_b_spot_162",
        "exam": "EDAIC_PAPER_B",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Disease",
        "tr": "Sirozda albümin düşüklüğü serbest ilaç fraksiyonunu artırabilir.",
        "en": "In cirrhosis, low albumin may increase the free fraction of drugs.",
        "importance": "high",
        "tags": ["cirrhosis", "hypoalbuminemia", "free_drug_fraction", "pharmacokinetics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 163
    {
        "id": "edaic_b_spot_163",
        "exam": "EDAIC_PAPER_B",
        "topic": "Organ Disease Basics",
        "subtopic": "Liver Disease",
        "tr": "Portal hipertansiyon varis kanaması ve asit ile ilişkili olabilir.",
        "en": "Portal hypertension may be associated with variceal bleeding and ascites.",
        "importance": "high",
        "tags": ["portal_hypertension", "variceal_bleeding", "ascites"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 164
    {
        "id": "edaic_b_spot_164",
        "exam": "EDAIC_PAPER_B",
        "topic": "Organ Disease Basics",
        "subtopic": "Renal Failure",
        "tr": "Böbrek yetmezliğinde potasyum, volüm durumu ve ilaç eliminasyonu dikkatle değerlendirilmelidir.",
        "en": "In renal failure, potassium, volume status, and drug elimination must be assessed carefully.",
        "importance": "high",
        "tags": ["renal_failure", "hyperkalemia", "volume_status", "drug_elimination"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 165
    {
        "id": "edaic_b_spot_165",
        "exam": "EDAIC_PAPER_B",
        "topic": "Organ Disease Basics",
        "subtopic": "Renal Failure",
        "tr": "Diyaliz hastasında son diyaliz zamanı, elektrolitler ve damar yolu erişimi preoperatif değerlendirmede önemlidir.",
        "en": "In dialysis patients, time since last dialysis, electrolytes, and vascular access are important in preoperative assessment.",
        "importance": "high",
        "tags": ["dialysis", "preoperative_assessment", "electrolytes", "vascular_access"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 166
    {
        "id": "edaic_b_spot_166",
        "exam": "EDAIC_PAPER_B",
        "topic": "Organ Disease Basics",
        "subtopic": "Renal Failure",
        "tr": "Üremi trombosit fonksiyon bozukluğu yaparak kanama riskini artırabilir.",
        "en": "Uremia may cause platelet dysfunction and increase bleeding risk.",
        "importance": "high",
        "tags": ["uremia", "platelet_dysfunction", "bleeding_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 167
    {
        "id": "edaic_b_spot_167",
        "exam": "EDAIC_PAPER_B",
        "topic": "Respiratory Care",
        "subtopic": "COPD",
        "tr": "KOAH hastasında dinamik hiperinflasyon ve auto-PEEP gelişme riski vardır.",
        "en": "Patients with COPD are at risk of dynamic hyperinflation and auto-PEEP.",
        "importance": "high",
        "tags": ["copd", "dynamic_hyperinflation", "auto_peep"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 168
    {
        "id": "edaic_b_spot_168",
        "exam": "EDAIC_PAPER_B",
        "topic": "Respiratory Care",
        "subtopic": "COPD",
        "tr": "KOAH’tavantilasyonda uzun ekspirasyon süresi ve düşük solunum frekansı gerekebilir.",
        "en": "In COPD, ventilation may require a long expiratory time and low respiratory rate.",
        "importance": "high",
        "tags": ["copd", "ventilator_settings", "expiratory_time", "respiratory_rate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 169
    {
        "id": "edaic_b_spot_169",
        "exam": "EDAIC_PAPER_B",
        "topic": "Respiratory Care",
        "subtopic": "Asthma",
        "tr": "Astımda aktif wheezing elektif cerrahi öncesi optimize edilmelidir.",
        "en": "Active wheezing in asthma should be optimized before elective surgery.",
        "importance": "high",
        "tags": ["asthma", "wheezing", "preoperative_optimization"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 170
    {
        "id": "edaic_b_spot_170",
        "exam": "EDAIC_PAPER_B",
        "topic": "Respiratory Care",
        "subtopic": "Bronchospasm",
        "tr": "Bronkospazm riskini azaltmak için yeterli anestezi derinliği ve havayolu irritasyonundan kaçınma önemlidir.",
        "en": "Adequate anesthetic depth and avoidance of airway irritation are important to reduce bronchospasm risk.",
        "importance": "high",
        "tags": ["bronchospasm_prevention", "anesthetic_depth", "airway_irritation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 171
    {
        "id": "edaic_b_spot_171",
        "exam": "EDAIC_PAPER_B",
        "topic": "Special Populations",
        "subtopic": "Obesity",
        "tr": "Obez hastalarda maske ventilasyonu, entübasyon ve postoperatif hipoventilasyon riski artabilir.",
        "en": "In obese patients, the risks of difficult mask ventilation, intubation, and postoperative hypoventilation may increase.",
        "importance": "high",
        "tags": ["obesity", "difficult_mask_ventilation", "difficult_intubation", "hypoventilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 172
    {
        "id": "edaic_b_spot_172",
        "exam": "EDAIC_PAPER_B",
        "topic": "Special Populations",
        "subtopic": "Obesity",
        "tr": "Obez hastada ramped pozisyon preoksijenasyon ve laringoskopiyi kolaylaştırabilir.",
        "en": "In obese patients, the ramped position may facilitate preoxygenation and laryngoscopy.",
        "importance": "high",
        "tags": ["ramped_position", "preoxygenation", "laryngoscopy", "obesity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 173
    {
        "id": "edaic_b_spot_173",
        "exam": "EDAIC_PAPER_B",
        "topic": "Pain Management",
        "subtopic": "Obstructive Sleep Apnea",
        "tr": "OSA hastalarında postoperatif opioid ve sedatifler solunum depresyonu riskini artırır.",
        "en": "In patients with OSA, postoperative opioids and sedatives increase the risk of respiratory depression.",
        "importance": "high",
        "tags": ["osa", "postoperative_opioids", "respiratory_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 174
    {
        "id": "edaic_b_spot_174",
        "exam": "EDAIC_PAPER_B",
        "topic": "Ambulatory Anesthesia",
        "subtopic": "Patient Selection",
        "tr": "Günübirlik cerrahide hasta seçimi komorbidite, cerrahi tipi, sosyal destek ve postoperatif risklere göre yapılmalıdır.",
        "en": "Patient selection for ambulatory surgery depends on comorbidity, surgical type, social support, and postoperative risks.",
        "importance": "high",
        "tags": ["ambulatory_surgery", "patient_selection", "comorbidities"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 175
    {
        "id": "edaic_b_spot_175",
        "exam": "EDAIC_PAPER_B",
        "topic": "Ambulatory Anesthesia",
        "subtopic": "Discharge Criteria",
        "tr": "Günübirlik cerrahi sonrası taburculuk için stabil vital bulgular, yeterli analjezi ve kontrol altında bulantı gerekir.",
        "en": "Discharge after ambulatory surgery requires stable vital signs, adequate analgesia, and controlled nausea.",
        "importance": "high",
        "tags": ["discharge_criteria", "postoperative_pain", "nausea"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 176
    {
        "id": "edaic_b_spot_176",
        "exam": "EDAIC_PAPER_B",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Postoperatif hipoksemi atelektazi, opioid etkisi, rezidüel blok veya aspirasyona bağlı olabilir.",
        "en": "Postoperative hypoxemia may be due to atelectasis, opioid effect, residual block, or aspiration.",
        "importance": "high",
        "tags": ["postoperative_hypoxemia", "atelectasis", "opioid_effect", "residual_block", "aspiration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 177
    {
        "id": "edaic_b_spot_177",
        "exam": "EDAIC_PAPER_B",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Rezidüel nöromüsküler blok postoperatif havayolu obstrüksiyonu ve hipoksemi riskini artırır.",
        "en": "Residual neuromuscular block increases the risk of postoperative airway obstruction and hypoxemia.",
        "importance": "high",
        "tags": ["residual_block", "airway_obstruction", "hypoxemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 178
    {
        "id": "edaic_b_spot_178",
        "exam": "EDAIC_PAPER_B",
        "topic": "Neuromuscular Monitoring",
        "subtopic": "Residual Block",
        "tr": "TOF oranı 0.9’un altında ise klinik olarak anlamlı rezidüel blok riski vardır.",
        "en": "A TOF ratio below 0.9 indicates a clinically significant risk of residual block.",
        "importance": "high",
        "tags": ["tof_ratio", "residual_block", "neuromuscular_monitoring"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 179
    {
        "id": "edaic_b_spot_179",
        "exam": "EDAIC_PAPER_B",
        "topic": "Postoperative Care",
        "subtopic": "Agitation & Delirium",
        "tr": "Postoperatif ajitasyon hipoksi, ağrı, mesane distansiyonu, deliryum veya ilaç etkisiyle ilişkili olabilir.",
        "en": "Postoperative agitation may be related to hypoxia, pain, bladder distension, delirium, or drug effects.",
        "importance": "high",
        "tags": ["postoperative_agitation", "hypoxia", "pain", "bladder_distension", "delirium"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 180
    {
        "id": "edaic_b_spot_180",
        "exam": "EDAIC_PAPER_B",
        "topic": "Geriatric Anesthesia",
        "subtopic": "Delirium",
        "tr": "Postoperatif deliryum yaşlı hastalarda morbidite ve hastanede kalış süresini artırır.",
        "en": "Postoperative delirium increases morbidity and length of hospital stay in elderly patients.",
        "importance": "high",
        "tags": ["postoperative_delirium", "geriatric_anesthesia", "morbidity", "hospital_stay"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 181
    {
        "id": "edaic_b_spot_181",
        "exam": "EDAIC_PAPER_B",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Postoperatif göğüs ağrısında miyokard iskemisi, pulmoner emboli ve pnömotoraks dışlanmalıdır.",
        "en": "In postoperative chest pain, myocardial ischemia, pulmonary embolism, and pneumothorax should be excluded.",
        "importance": "high",
        "tags": ["chest_pain", "myocardial_ischemia", "pulmonary_embolism", "pneumothorax"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 182
    {
        "id": "edaic_b_spot_182",
        "exam": "EDAIC_PAPER_B",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Postoperatif miyokard hasarı sessiz seyredebilir ve troponin takibi yüksek riskli hastalarda yararlı olabilir.",
        "en": "Postoperative myocardial injury may be silent, and troponin monitoring may be useful in high-risk patients.",
        "importance": "high",
        "tags": ["myocardial_injury", "silent_ischemia", "troponin_monitoring"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 183
    {
        "id": "edaic_b_spot_183",
        "exam": "EDAIC_PAPER_B",
        "topic": "PONV Prophylaxis",
        "subtopic": "Complications",
        "tr": "Postoperatif bulantı-kusma aspirasyon, dehidratasyon ve taburculuk gecikmesine neden olabilir.",
        "en": "Postoperative nausea and vomiting may cause aspiration, dehydration, and delayed discharge.",
        "importance": "high",
        "tags": ["ponv", "aspiration_risk", "dehydration", "delayed_discharge"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 184
    {
        "id": "edaic_b_spot_184",
        "exam": "EDAIC_PAPER_B",
        "topic": "Postoperative Care",
        "subtopic": "Complications",
        "tr": "Postoperatif üriner retansiyon spinal/epidural anestezi, opioidler ve yaşlı erkek hastalarda daha sık görülür.",
        "en": "Postoperative urinary retention is more common after spinal/epidural anesthesia, opioid use, and in elderly male patients.",
        "importance": "high",
        "tags": ["urinary_retansiyon", "spinal_anesthesia", "opioids", "elderly_male"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 185
    {
        "id": "edaic_b_spot_185",
        "exam": "EDAIC_PAPER_B",
        "topic": "Thermoregulation",
        "subtopic": "Shivering",
        "tr": "Hipotermi sonrası titreme oksijen tüketimini artırır ve kardiyak hastalarda zararlı olabilir.",
        "en": "Shivering after hypothermia increases oxygen consumption and may be harmful in cardiac patients.",
        "importance": "high",
        "tags": ["shivering", "oxygen_consumption", "cardiac_workload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 186
    {
        "id": "edaic_b_spot_186",
        "exam": "EDAIC_PAPER_B",
        "topic": "Infection Control",
        "subtopic": "Antibiotic Prophylaxis",
        "tr": "Perioperatif antibiyotik profilaksisi genellikle insizyondan önce uygun zaman aralığında uygulanmalıdır.",
        "en": "Perioperative antibiotic prophylaxis should usually be administered within the appropriate time window before incision.",
        "importance": "high",
        "tags": ["antibiotic_prophylaxis", "surgical_site_infection", "timing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 187
    {
        "id": "edaic_b_spot_187",
        "exam": "EDAIC_PAPER_B",
        "topic": "Infection Control",
        "subtopic": "Antibiotic Prophylaxis",
        "tr": "Temiz cerrahide antibiyotik profilaksisi her zaman gerekli değildir; cerrahi ve hasta riskine göre karar verilir.",
        "en": "Antibiotic prophylaxis is not always required in clean surgery; the decision depends on surgical and patient risk.",
        "importance": "high",
        "tags": ["clean_surgery", "antibiotic_prophylaxis", "patient_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 188
    {
        "id": "edaic_b_spot_188",
        "exam": "EDAIC_PAPER_B",
        "topic": "Immunology & Allergy",
        "subtopic": "Allergy History",
        "tr": "Alerji öyküsünde gerçek anafilaksi ile intolerans veya yan etki ayrımı yapılmalıdır.",
        "en": "In allergy history, true anaphylaxis should be distinguished from intolerance or adverse effects.",
        "importance": "high",
        "tags": ["allergy_history", "anaphylaxis", "intolerance", "adverse_effects"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 189
    {
        "id": "edaic_b_spot_189",
        "exam": "EDAIC_PAPER_B",
        "topic": "Immunology & Allergy",
        "subtopic": "Latex Allergy",
        "tr": "Lateks alerjisi olan hastada latekssiz ortam ve ekipman hazırlanmalıdır.",
        "en": "In patients with latex allergy, a latex-free environment and equipment should be prepared.",
        "importance": "high",
        "tags": ["latex_allergy", "latex_free_environment", "equipment"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 190
    {
        "id": "edaic_b_spot_190",
        "exam": "EDAIC_PAPER_B",
        "topic": "Clinical Procedures",
        "subtopic": "Vascular Access",
        "tr": "Zor damar yolu beklenen hastada ultrason rehberliği erken düşünülmelidir.",
        "en": "In patients with expected difficult venous access, ultrasound guidance should be considered early.",
        "importance": "high",
        "tags": ["difficult_vascular_access", "ultrasound_guidance", "venous_access"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 191
    {
        "id": "edaic_b_spot_191",
        "exam": "EDAIC_PAPER_B",
        "topic": "Clinical Procedures",
        "subtopic": "Central Venous Catheterization",
        "tr": "Santral venöz kateter komplikasyonları arasında pnömotoraks, arter ponksiyonu, hematom ve enfeksiyon bulunur.",
        "en": "Complications of central venous catheterization include pneumothorax, arterial puncture, hematoma, and infection.",
        "importance": "high",
        "tags": ["cvc_complications", "pneumothorax", "arterial_puncture", "hematoma", "infection"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 192
    {
        "id": "edaic_b_spot_192",
        "exam": "EDAIC_PAPER_B",
        "topic": "Clinical Procedures",
        "subtopic": "Central Venous Catheterization",
        "tr": "Ultrason rehberliği santral venöz kateterizasyon başarısını artırabilir ve komplikasyonları azaltabilir.",
        "en": "Ultrasound guidance may improve central venous catheterization success and reduce complications.",
        "importance": "high",
        "tags": ["ultrasound_guidance", "cvc", "success_rate", "complications_reduction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 193
    {
        "id": "edaic_b_spot_193",
        "exam": "EDAIC_PAPER_B",
        "topic": "Laparoscopic Surgery",
        "subtopic": "Pneumoperitoneum",
        "tr": "Pnömoperitoneum venöz dönüşü, akciğer kompliyansını ve havayolu basınçlarını etkileyebilir.",
        "en": "Pneumoperitoneum may affect venous return, lung compliance, and airway pressures.",
        "importance": "high",
        "tags": ["pneumoperitoneum", "venous_return", "lung_compliance", "airway_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 194
    {
        "id": "edaic_b_spot_194",
        "exam": "EDAIC_PAPER_B",
        "topic": "Clinical Procedures",
        "subtopic": "Patient Positioning",
        "tr": "Trendelenburg pozisyonu havayolu ödemi, artmış intrakraniyal basınç ve azalmış akciğer kompliyansı ile ilişkili olabilir.",
        "en": "Trendelenburg position may be associated with airway edema, increased intracranial pressure, and reduced lung compliance.",
        "importance": "high",
        "tags": ["trendelenburg_position", "airway_edema", "icp", "lung_compliance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 195
    {
        "id": "edaic_b_spot_195",
        "exam": "EDAIC_PAPER_B",
        "topic": "Specialized Surgery",
        "subtopic": "Robotic Surgery",
        "tr": "Robotik cerrahide hastaya erişim sınırlı olacağı için pozisyon, tüp ve damar yolları baştan güvenli hale getirilmelidir.",
        "en": "In robotic surgery, positioning, the tube, and vascular access must be secured early because patient access will be limited.",
        "importance": "high",
        "tags": ["robotic_surgery", "patient_positioning", "limited_access", "airway_security"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 196
    {
        "id": "edaic_b_spot_196",
        "exam": "EDAIC_PAPER_B",
        "topic": "Clinical Procedures",
        "subtopic": "Tourniquet Use",
        "tr": "Turnike kullanımı ağrı, hipertansiyon, metabolik değişiklikler ve turnike açılması sonrası hipotansiyon yapabilir.",
        "en": "Tourniquet use may cause pain, hypertension, metabolic changes, and hypotension after release.",
        "importance": "high",
        "tags": ["tourniquet", "tourniquet_pain", "hypertension", "hypotension_after_release"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 197
    {
        "id": "edaic_b_spot_197",
        "exam": "EDAIC_PAPER_B",
        "topic": "Trauma & Emergency",
        "subtopic": "Fat Embolism Syndrome",
        "tr": "Yağ embolisi sendromu hipoksemi, nörolojik bulgular ve peteşi triadı ile ilişkili olabilir.",
        "en": "Fat embolism syndrome may be associated with the triad of hypoxemia, neurological signs, and petechiae.",
        "importance": "high",
        "tags": ["fat_embolism_syndrome", "hypoxemia", "neurological_signs", "petechiae"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 198
    {
        "id": "edaic_b_spot_198",
        "exam": "EDAIC_PAPER_B",
        "topic": "Specialized Surgery",
        "subtopic": "Bone Cement Implantation Syndrome",
        "tr": "Kemik çimentosu implantasyon sendromunda hipoksi, hipotansiyon ve kardiyovasküler kollaps gelişebilir.",
        "en": "Bone cement implantation syndrome may cause hypoxia, hypotension, and cardiovascular collapse.",
        "importance": "high",
        "tags": ["bcis", "bone_cement", "hypoxia", "hypotension", "cardiovascular_collapse"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 199
    {
        "id": "edaic_b_spot_199",
        "exam": "EDAIC_PAPER_B",
        "topic": "Trauma & Emergency",
        "subtopic": "Emergency Surgery",
        "tr": "Acil cerrahide risk azaltma, tam optimizasyondan çok kritik fizyolojik sorunların hızlı düzeltilmesine odaklanır.",
        "en": "In emergency surgery, risk reduction focuses on rapid correction of critical physiological problems rather than complete optimization.",
        "importance": "high",
        "tags": ["emergency_surgery", "risk_reduction", "rapid_correction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 200
    {
        "id": "edaic_b_spot_200",
        "exam": "EDAIC_PAPER_B",
        "topic": "Postoperative Care",
        "subtopic": "Patient Handover",
        "tr": "Postoperatif güvenli devir teslim; hava yolu, solunum, dolaşım, analjezi, kanama ve özel risklerin açık aktarımını gerektirir.",
        "en": "Safe postoperative handover requires clear communication of airway, breathing, circulation, analgesia, bleeding, and special risks.",
        "importance": "high",
        "tags": ["handover", "pacu", "safe_communication", "transfer"],
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
