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
        "id": "aba_advanced_spot_151",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Travma hastasında havayolu yönetimi servikal omurga korunarak ve aspirasyon riski göz önünde bulundurularak planlanmalıdır.",
        "en": "Airway management in trauma should be planned with cervical spine protection and aspiration risk in mind.",
        "importance": "high",
        "tags": ["trauma_airway", "cervical_spine_protection", "aspiration_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 152
    {
        "id": "aba_advanced_spot_152",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Hemorajik şokta normal kan basıncı, özellikle genç hastalarda ciddi kan kaybını dışlamaz.",
        "en": "Normal blood pressure in hemorrhagic shock does not exclude major blood loss, especially in young patients.",
        "importance": "high",
        "tags": ["hemorrhagic_shock", "compensatory_mechanisms", "blood_loss", "blood_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 153
    {
        "id": "aba_advanced_spot_153",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Travmada hipotermi, asidoz ve koagülopati birbirini kötüleştirerek mortaliteyi artırır.",
        "en": "In trauma, hypothermia, acidosis, and coagulopathy worsen each other and increase mortality.",
        "importance": "high",
        "tags": ["lethal_triad", "hypothermia", "acidosis", "coagulopathy", "trauma_mortality"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 154
    {
        "id": "aba_advanced_spot_154",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Damage Control",
        "tr": "Hasar kontrol resüsitasyonu kanama kontrolü, dengeli transfüzyon ve kristalloid yükünden kaçınmayı hedefler.",
        "en": "Damage control resuscitation aims for bleeding control, balanced transfusion, and avoidance of excessive crystalloid.",
        "importance": "high",
        "tags": ["damage_control_resuscitation", "bleeding_control", "balanced_transfusion", "avoid_crystalloids"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 155
    {
        "id": "aba_advanced_spot_155",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Traumatic Brain Injury",
        "tr": "Travmatik beyin hasarında hipoksi ve hipotansiyon sekonder beyin hasarını artırır.",
        "en": "In traumatic brain injury, hypoxia and hypotension increase secondary brain injury.",
        "importance": "high",
        "tags": ["tbi", "secondary_brain_injury", "hypoxia", "hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 156
    {
        "id": "aba_advanced_spot_156",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Traumatic Brain Injury",
        "tr": "Travmatik beyin hasarında serebral perfüzyon korunmalı ve hiperkapniden kaçınılmalıdır.",
        "en": "In traumatic brain injury, cerebral perfusion should be maintained and hypercapnia avoided.",
        "importance": "high",
        "tags": ["tbi", "cerebral_perfusion", "avoid_hypercapnia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 157
    {
        "id": "aba_advanced_spot_157",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Burn Management",
        "tr": "Major yanık hastasında erken dönemde havayolu ödemi hızla ilerleyebilir.",
        "en": "In major burns, airway edema may progress rapidly in the early period.",
        "importance": "high",
        "tags": ["major_burns", "airway_edema", "early_progression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 158
    {
        "id": "aba_advanced_spot_158",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Burn Management",
        "tr": "İnhalasyon hasarı şüphesinde erken entübasyon gecikmiş acil havayolundan daha güvenli olabilir.",
        "en": "When inhalation injury is suspected, early intubation may be safer than delayed emergency airway management.",
        "importance": "high",
        "tags": ["inhalation_injury", "early_intubation", "airway_safety"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 159
    {
        "id": "aba_advanced_spot_159",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Burn Management",
        "tr": "Yanık sonrası süksinilkolin kullanımı hiperkalemi riski nedeniyle belirli dönemden sonra tehlikelidir.",
        "en": "After burns, succinylcholine becomes dangerous after a certain period because of hyperkalemia risk.",
        "importance": "high",
        "tags": ["burn_patients", "succinylcholine_hazard", "hyperkalemia", "acetylcholine_receptors"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 160
    {
        "id": "aba_advanced_spot_160",
        "exam": "ABA_ADVANCED",
        "topic": "Trauma & Emergency",
        "subtopic": "Burn Management",
        "tr": "Yanık hastasında sıvı resüsitasyonu yanık yüzdesi, zaman ve klinik perfüzyona göre yönlendirilir.",
        "en": "Fluid resuscitation in burn patients is guided by burn size, time, and clinical perfusion.",
        "importance": "high",
        "tags": ["burn_resuscitation", "fluid_therapy", "parkland_formula", "clinical_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 161
    {
        "id": "aba_advanced_spot_161",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Orthopedic Complications",
        "tr": "Kırık uzun kemik cerrahisinde yağ embolisi hipoksemi, nörolojik bulgular ve peteşi ile ilişkili olabilir.",
        "en": "In long-bone fracture surgery, fat embolism may be associated with hypoxemia, neurological signs, and petechiae.",
        "importance": "high",
        "tags": ["fat_embolism_syndrome", "long_bone_fracture", "hypoxemia", "petechiae"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 162
    {
        "id": "aba_advanced_spot_162",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Orthopedic Complications",
        "tr": "Kemik çimentosu implantasyon sendromunda ani hipoksi, hipotansiyon ve kardiyovasküler kollaps gelişebilir.",
        "en": "Bone cement implantation syndrome may cause sudden hypoxia, hypotension, and cardiovascular collapse.",
        "importance": "high",
        "tags": ["bone_cement_implantation_syndrome", "bcis", "hypoxia", "hypotension", "cardiovascular_collapse"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 163
    {
        "id": "aba_advanced_spot_163",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Orthopedic Complications",
        "tr": "Turnike açılması sonrası asit metabolitler ve potasyum dolaşıma geçerek hipotansiyon oluşturabilir.",
        "en": "After tourniquet release, acidic metabolites and potassium may enter the circulation and cause hypotension.",
        "importance": "high",
        "tags": ["tourniquet_release", "acidic_metabolites", "potassium_release", "hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 164
    {
        "id": "aba_advanced_spot_164",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Robotic & Positioning",
        "tr": "Robotik cerrahide hasta erişimi sınırlı olduğundan tüp, damar yolu ve pozisyonlama önceden güvenceye alınmalıdır.",
        "en": "In robotic surgery, the tube, vascular access, and positioning must be secured in advance because patient access is limited.",
        "importance": "high",
        "tags": ["robotic_surgery", "limited_patient_access", "airway_security", "positioning"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 165
    {
        "id": "aba_advanced_spot_165",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Robotic & Positioning",
        "tr": "Uzun Trendelenburg pozisyonu havayolu ödemi, yüz ödemi ve akciğer kompliyansında azalma ile ilişkili olabilir.",
        "en": "Prolonged Trendelenburg position may be associated with airway edema, facial edema, and reduced lung compliance.",
        "importance": "high",
        "tags": ["prolonged_trendelenburg", "airway_edema", "facial_edema", "lung_compliance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 166
    {
        "id": "aba_advanced_spot_166",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Laparoscopy",
        "tr": "Pnömoperitoneum intraabdominal basıncı artırarak venöz dönüş, ventilasyon ve hemodinamiyi etkileyebilir.",
        "en": "Pneumoperitoneum increases intra-abdominal pressure and may affect venous return, ventilation, and hemodynamics.",
        "importance": "high",
        "tags": ["pneumoperitoneum", "intra_abdominal_pressure", "venous_return", "hemodynamics"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 167
    {
        "id": "aba_advanced_spot_167",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "Laparoscopy",
        "tr": "Laparoskopide CO₂ absorbsiyonu hiperkapniye ve sempatik aktivasyona katkı sağlayabilir.",
        "en": "During laparoscopy, CO₂ absorption may contribute to hypercapnia and sympathetic activation.",
        "importance": "high",
        "tags": ["co2_absorption", "hypercapnia", "sympathetic_activation", "laparoscopy_complications"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 168
    {
        "id": "aba_advanced_spot_168",
        "exam": "ABA_ADVANCED",
        "topic": "Special Populations",
        "subtopic": "Obesity",
        "tr": "Morbid obez hastada ramped pozisyon, preoksijenasyon ve laringoskopi başarısını artırabilir.",
        "en": "In morbid obesity, ramped positioning may improve preoxygenation and laryngoscopy success.",
        "importance": "high",
        "tags": ["morbid_obesity", "ramped_positioning", "preoxygenation_efficacy", "laryngoscopy_success"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 169
    {
        "id": "aba_advanced_spot_169",
        "exam": "ABA_ADVANCED",
        "topic": "Special Populations",
        "subtopic": "Obesity",
        "tr": "Obezite hipoventilasyon, OSA, zor havayolu ve postoperatif solunum komplikasyonu riskini artırır.",
        "en": "Obesity increases the risk of hypoventilation, OSA, difficult airway, and postoperative respiratory complications.",
        "importance": "high",
        "tags": ["obesity_hazards", "hypoventilation_syndrome", "osa", "postoperative_pulmonary_complications"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 170
    {
        "id": "aba_advanced_spot_170",
        "exam": "ABA_ADVANCED",
        "topic": "Ambulatory Anesthesia",
        "subtopic": "Discharge & Pain",
        "tr": "Günübirlik cerrahide hasta seçimi komorbidite stabilitesi, cerrahi tipi ve ev desteğine göre yapılmalıdır.",
        "en": "Patient selection for ambulatory surgery should consider comorbidity stability, surgical type, and home support.",
        "importance": "high",
        "tags": ["ambulatory_patient_selection", "comorbidity_stability", "home_support"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 171
    {
        "id": "aba_advanced_spot_171",
        "exam": "ABA_ADVANCED",
        "topic": "Ambulatory Anesthesia",
        "subtopic": "Discharge & Pain",
        "tr": "Ambulatuvar anestezide hızlı derlenme kadar ağrı, bulantı ve güvenli taburculuk da planlanmalıdır.",
        "en": "In ambulatory anesthesia, pain control, nausea control, and safe discharge must be planned in addition to rapid recovery.",
        "importance": "high",
        "tags": ["ambulatory_anesthesia", "rapid_recovery", "pain_control", "nausea_control", "safe_discharge"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 172
    {
        "id": "aba_advanced_spot_172",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Neuraxial Complications",
        "tr": "Nöroaksiyel anestezi öncesi antikoagülan zamanlaması, renal fonksiyon ve işlem riski birlikte değerlendirilmelidir.",
        "en": "Before neuraxial anesthesia, anticoagulant timing, renal function, and procedural risk should be assessed together.",
        "importance": "high",
        "tags": ["neuraxial_anesthesia", "anticoagulants_timing", "renal_function", "epidural_hematoma_prevention"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 173
    {
        "id": "aba_advanced_spot_173",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Neuraxial Complications",
        "tr": "Spinal veya epidural hematom şüphesinde yeni motor defisit acil görüntüleme ve cerrahi değerlendirme gerektirir.",
        "en": "New motor deficit with suspected spinal or epidural hematoma requires urgent imaging and surgical evaluation.",
        "importance": "high",
        "tags": ["spinal_hematoma", "epidural_hematoma", "new_motor_deficit", "urgent_decompression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 174
    {
        "id": "aba_advanced_spot_174",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Neuraxial Complications",
        "tr": "Postdural ponksiyon baş ağrısı tipik olarak ortostatik karakterdedir.",
        "en": "Postdural puncture headache typically has an orthostatic character.",
        "importance": "high",
        "tags": ["pdph", "postdural_puncture_headache", "orthostatic_headache"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 175
    {
        "id": "aba_advanced_spot_175",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Neuraxial Complications",
        "tr": "Epidural kan yaması dirençli postdural ponksiyon baş ağrısında etkili tedavi seçeneğidir.",
        "en": "Epidural blood patch is an effective treatment option for persistent postdural puncture headache.",
        "importance": "high",
        "tags": ["epidural_blood_patch", "pdph_treatment", "refractory_headache"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 176
    {
        "id": "aba_advanced_spot_176",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "High Spinal",
        "tr": "Yüksek spinal blok hipotansiyon, bradikardi, solunum güçlüğü ve bilinç kaybına yol açabilir.",
        "en": "High spinal block may cause hypotension, bradycardia, respiratory difficulty, and loss of consciousness.",
        "importance": "high",
        "tags": ["high_spinal_block", "cardiovascular_depression", "bradycardia", "respiratory_arrest_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 177
    {
        "id": "aba_advanced_spot_177",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "High Spinal",
        "tr": "Total spinal anestezide havayolu, ventilasyon ve dolaşım desteği hızla sağlanmalıdır.",
        "en": "In total spinal anesthesia, airway, ventilation, and circulatory support must be provided rapidly.",
        "importance": "high",
        "tags": ["total_spinal_anesthesia", "airway_security", "circulatory_support"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 178
    {
        "id": "aba_advanced_spot_178",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Nerve Injury",
        "tr": "Periferik sinir bloğunda yüksek enjeksiyon basıncı veya şiddetli ağrı intranöral enjeksiyonu düşündürebilir.",
        "en": "High injection pressure or severe pain during peripheral nerve block may suggest intraneural injection.",
        "importance": "high",
        "tags": ["peripheral_nerve_block", "high_injection_pressure", "intraneural_injection_hazard", "severe_pain"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 179
    {
        "id": "aba_advanced_spot_179",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Nerve Injury",
        "tr": "Ultrason rehberliği blok başarısını artırabilir ancak LAST riskini tamamen ortadan kaldırmaz.",
        "en": "Ultrasound guidance may improve block success but does not eliminate the risk of LAST.",
        "importance": "high",
        "tags": ["ultrasound_guidance", "block_success", "last_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 180
    {
        "id": "aba_advanced_spot_180",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "LAST",
        "tr": "LAST yönetiminde nöbet kontrolü, lipid emülsiyon tedavisi ve modifiye kardiyak yaşam desteği gerekir.",
        "en": "LAST management requires seizure control, lipid emulsion therapy, and modified cardiac life support.",
        "importance": "high",
        "tags": ["last_management", "local_anesthetic_systemic_toxicity", "lipid_emulsion", "acls_modifications"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 181
    {
        "id": "aba_advanced_spot_181",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Rejyonal anestezi sonrası kalıcı nörolojik defisit acil değerlendirme gerektirir.",
        "en": "Persistent neurological deficit after regional anesthesia requires urgent evaluation.",
        "importance": "high",
        "tags": ["persistent_neurological_deficit", "urgent_evaluation", "regional_complications"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 182
    {
        "id": "aba_advanced_spot_182",
        "exam": "ABA_ADVANCED",
        "topic": "Regional Anesthesia",
        "subtopic": "Complications",
        "tr": "Kanama riski yüksek hastada rejyonal teknik seçimi kompresibilite ve olası hematom sonuçlarına göre yapılmalıdır.",
        "en": "In patients with high bleeding risk, regional technique choice should consider compressibility and consequences of hematoma.",
        "importance": "high",
        "tags": ["bleeding_risk_patients", "regional_techniques", "compressibility", "hematoma_consequences"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 183
    {
        "id": "aba_advanced_spot_183",
        "exam": "ABA_ADVANCED",
        "topic": "Immunology & Allergy",
        "subtopic": "Anaphylaxis & Latex",
        "tr": "Alerji öyküsünde gerçek IgE aracılı reaksiyon ile yan etki veya intolerans ayrımı yapılmalıdır.",
        "en": "In allergy history, true IgE-mediated reaction should be distinguished from adverse effect or intolerance.",
        "importance": "high",
        "tags": ["allergy_history", "ige_mediated_reaction", "drug_intolerance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 184
    {
        "id": "aba_advanced_spot_184",
        "exam": "ABA_ADVANCED",
        "topic": "Immunology & Allergy",
        "subtopic": "Anaphylaxis & Latex",
        "tr": "Lateks alerjisinde latekssiz ortam, ekipman ve ameliyathane planlaması gerekir.",
        "en": "Latex allergy requires a latex-free environment, equipment, and operating room planning.",
        "importance": "high",
        "tags": ["latex_allergy", "latex_free_environment", "operating_room_planning"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 185
    {
        "id": "aba_advanced_spot_185",
        "exam": "ABA_ADVANCED",
        "topic": "Immunology & Allergy",
        "subtopic": "Anaphylaxis & Latex",
        "tr": "Anafilaksi sonrası serum triptaz örneklemesi tanıyı destekleyebilir ancak akut tedaviyi geciktirmemelidir.",
        "en": "Serum tryptase sampling after anaphylaxis may support diagnosis but must not delay acute treatment.",
        "importance": "high",
        "tags": ["anaphylaxis_diagnosis", "serum_tryptase_sampling", "acute_treatment_priority"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 186
    {
        "id": "aba_advanced_spot_186",
        "exam": "ABA_ADVANCED",
        "topic": "Clinical Procedures",
        "subtopic": "Central Lines",
        "tr": "Zor damar yolu beklenen hastada ultrason rehberli periferik veya santral erişim erken planlanmalıdır.",
        "en": "In patients with expected difficult vascular access, ultrasound-guided peripheral or central access should be planned early.",
        "importance": "high",
        "tags": ["difficult_vascular_access", "ultrasound_guided_access", "early_planning"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 187
    {
        "id": "aba_advanced_spot_187",
        "exam": "ABA_ADVANCED",
        "topic": "Clinical Procedures",
        "subtopic": "Central Lines",
        "tr": "Santral venöz kateterizasyon komplikasyonları arasında arter ponksiyonu, pnömotoraks, hematom ve enfeksiyon bulunur.",
        "en": "Complications of central venous catheterization include arterial puncture, pneumothorax, hematoma, and infection.",
        "importance": "high",
        "tags": ["central_venous_catheterization", "catheter_complications", "pneumothorax", "arterial_puncture"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 188
    {
        "id": "aba_advanced_spot_188",
        "exam": "ABA_ADVANCED",
        "topic": "Crisis Management",
        "subtopic": "Air Embolism",
        "tr": "Hava embolisi riski olan cerrahilerde damar yolları havasızlandırılmalı ve cerrahi alanla iletişim sürdürülmelidir.",
        "en": "In surgeries with air embolism risk, intravenous lines should be de-aired and communication with the surgical field maintained.",
        "importance": "high",
        "tags": ["air_embolism_prevention", "de_airing_lines", "surgical_communication"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 189
    {
        "id": "aba_advanced_spot_189",
        "exam": "ABA_ADVANCED",
        "topic": "Monitoring",
        "subtopic": "Hypotension Verification",
        "tr": "Hipotansiyon gelişen hastada monitör, transdüser seviyesi ve gerçek perfüzyon bulguları birlikte kontrol edilmelidir.",
        "en": "In hypotension, the monitor, transducer level, and actual perfusion signs should be checked together.",
        "importance": "high",
        "tags": ["hypotension_assessment", "transducer_leveling", "perfusion_findings"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 190
    {
        "id": "aba_advanced_spot_190",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "Hasta güvenliğinde kapalı döngü iletişim, kritik komutların doğru anlaşılmasını artırır.",
        "en": "Closed-loop communication improves correct understanding of critical commands in patient safety.",
        "importance": "high",
        "tags": ["closed_loop_communication", "patient_safety", "clinical_communication"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 191
    {
        "id": "aba_advanced_spot_191",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "Cerrahi güvenlik kontrol listesi doğru hasta, doğru işlem ve ekip hazırlığını destekler.",
        "en": "The surgical safety checklist supports correct patient, correct procedure, and team readiness.",
        "importance": "high",
        "tags": ["surgical_safety_checklist", "wrong_site_surgery_prevention", "team_readiness"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 192
    {
        "id": "aba_advanced_spot_192",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "İlaç hatalarını azaltmak için etiketleme, konsantrasyon standardizasyonu ve çift kontrol kullanılmalıdır.",
        "en": "Labeling, concentration standardization, and double checking should be used to reduce medication errors.",
        "importance": "high",
        "tags": ["medication_errors_prevention", "labeling", "concentration_standardization", "double_checking"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 193
    {
        "id": "aba_advanced_spot_193",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "Yüksek riskli ilaçlarda benzer isim ve benzer ambalaj karışıklığı ciddi hasta güvenliği sorunudur.",
        "en": "Look-alike and sound-alike high-risk medications are a serious patient safety issue.",
        "importance": "high",
        "tags": ["lasa_medications", "high_risk_drugs", "patient_safety_hazards"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 194
    {
        "id": "aba_advanced_spot_194",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "Kan ürünü uygulamasında hasta kimliği ve ürün uygunluğu yatak başında doğrulanmalıdır.",
        "en": "During blood product administration, patient identity and product compatibility must be verified at the bedside.",
        "importance": "high",
        "tags": ["blood_administration_safety", "bedside_verification", "patient_identity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 195
    {
        "id": "aba_advanced_spot_195",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "Patient Safety",
        "tr": "Güvenli transport için havayolu, oksijen, monitörizasyon, ilaçlar ve acil ekipman önceden hazırlanmalıdır.",
        "en": "For safe transport, airway, oxygen, monitoring, medications, and emergency equipment should be prepared in advance.",
        "importance": "high",
        "tags": ["safe_patient_transport", "transport_monitoring", "emergency_equipment_readiness"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 196
    {
        "id": "aba_advanced_spot_196",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "MRI Anesthesia",
        "tr": "MRI ortamında ferromanyetik ekipman ciddi projectile yaralanmasına neden olabilir.",
        "en": "In the MRI environment, ferromagnetic equipment may cause serious projectile injury.",
        "importance": "high",
        "tags": ["mri_safety", "ferromagnetic_hazards", "projectile_injury"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 197
    {
        "id": "aba_advanced_spot_197",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "MRI Anesthesia",
        "tr": "MRI anestezisinde havayoluna erişim kısıtlı olacağı için planlama ve uyumlu ekipman kritiktir.",
        "en": "In MRI anesthesia, planning and compatible equipment are critical because airway access is limited.",
        "importance": "high",
        "tags": ["mri_anesthesia", "limited_airway_access", "compatible_equipment"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 198
    {
        "id": "aba_advanced_spot_198",
        "exam": "ABA_ADVANCED",
        "topic": "Specialized Surgery",
        "subtopic": "ECT Anesthesia",
        "tr": "Elektrokonvülsif tedavide kısa etkili anestezi ve uygun kas gevşemesi hedeflenir.",
        "en": "During electroconvulsive therapy, short-acting anesthesia and appropriate muscle relaxation are targeted.",
        "importance": "high",
        "tags": ["ect_anesthesia", "short_acting_anesthetic", "muscle_relaxation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 199
    {
        "id": "aba_advanced_spot_199",
        "exam": "ABA_ADVANCED",
        "topic": "Special Populations",
        "subtopic": "Geriatrics",
        "tr": "Yaşlı hastalarda bilişsel bozulma, frailty ve polifarmasi perioperatif riski artırır.",
        "en": "In elderly patients, cognitive impairment, frailty, and polypharmacy increase perioperative risk.",
        "importance": "high",
        "tags": ["geriatric_risk_factors", "cognitive_impairment", "frailty", "polypharmacy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 200
    {
        "id": "aba_advanced_spot_200",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Care",
        "subtopic": "Advanced Management",
        "tr": "İleri perioperatif yönetimde amaç tek bir vital bulguyu düzeltmek değil, oksijen sunumu, perfüzyon ve organ fonksiyonunu birlikte korumaktır.",
        "en": "In advanced perioperative management, the goal is not to correct a single vital sign but to preserve oxygen delivery, perfusion, and organ function together.",
        "importance": "high",
        "tags": ["advanced_perioperative_management", "oxygen_delivery_preservation", "perfusion_preservation", "organ_function_preservation"],
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
