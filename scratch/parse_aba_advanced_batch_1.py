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
        "id": "aba_advanced_spot_001",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Beklenen zor havayolunda uyanık entübasyon, oksijenasyonu kaybetmeden havayolunu güvenceye alma avantajı sağlar.",
        "en": "In anticipated difficult airway, awake intubation allows airway control without losing oxygenation.",
        "importance": "high",
        "tags": ["anticipated_difficult_airway", "awake_intubation", "oxygenation_preservation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 002
    {
        "id": "aba_advanced_spot_002",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Zor havayolu planında primer teknik, yedek teknik ve cerrahi havayolu planı önceden belirlenmelidir.",
        "en": "A difficult airway plan should include a primary technique, backup technique, and surgical airway plan.",
        "importance": "high",
        "tags": ["difficult_airway_plan", "backup_technique", "surgical_airway_plan"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 003
    {
        "id": "aba_advanced_spot_003",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "CICO",
        "tr": "CICO durumunda temel hedef entübasyon değil, acil oksijenasyonun yeniden sağlanmasıdır.",
        "en": "In CICO, the main goal is not intubation but urgent restoration of oxygenation.",
        "importance": "high",
        "tags": ["cico", "oxygenation_restoration", "cannot_intubate_cannot_oxygenate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 004
    {
        "id": "aba_advanced_spot_004",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Complications",
        "tr": "Tekrarlayan başarısız entübasyon girişimleri kanama, ödem ve ventilasyon güçlüğünü artırır.",
        "en": "Repeated failed intubation attempts increase bleeding, edema, and difficulty with ventilation.",
        "importance": "high",
        "tags": ["failed_intubation_attempts", "airway_trauma", "edema", "bleeding"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 005
    {
        "id": "aba_advanced_spot_005",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Intubation Techniques",
        "tr": "Videolaringoskopi iyi glottik görüntü sağlayabilir ancak tüp ilerletme güçlüğünü tamamen ortadan kaldırmaz.",
        "en": "Videolaryngoscopy may provide a good glottic view but does not eliminate difficulty with tube delivery.",
        "importance": "high",
        "tags": ["videolaryngoscopy", "glottic_view", "tube_delivery_difficulty"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 006
    {
        "id": "aba_advanced_spot_006",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Intubation Techniques",
        "tr": "Uyanık fiberoptik entübasyonda yeterli topikal anestezi hasta toleransını ve başarıyı artırır.",
        "en": "Adequate topical anesthesia improves tolerance and success during awake fiberoptic intubation.",
        "importance": "high",
        "tags": ["awake_fiberoptic_intubation", "topical_anesthesia", "patient_tolerance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 007
    {
        "id": "aba_advanced_spot_007",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Difficult Airway",
        "tr": "Üst havayolu tümörü olan hastada sedasyon obstrüksiyonu ağırlaştırabileceği için dikkatli planlanmalıdır.",
        "en": "In patients with upper airway tumors, sedation must be planned carefully because it may worsen obstruction.",
        "importance": "high",
        "tags": ["upper_airway_tumors", "sedation_hazard", "airway_obstruction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 008
    {
        "id": "aba_advanced_spot_008",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Complications",
        "tr": "Boyun hematomu gelişen hastada havayolu hızla kaybedilebilir ve erken müdahale gerekir.",
        "en": "In neck hematoma, the airway can be lost rapidly and early intervention is required.",
        "importance": "high",
        "tags": ["neck_hematoma", "rapid_airway_loss", "early_intervention"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 009
    {
        "id": "aba_advanced_spot_009",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "CICO",
        "tr": "Acil cerrahi havayolu kararı gecikirse hipoksik hasar riski artar.",
        "en": "Delaying emergency surgical airway increases the risk of hypoxic injury.",
        "importance": "high",
        "tags": ["surgical_airway", "delay_consequences", "hypoxic_injury", "cico"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 010
    {
        "id": "aba_advanced_spot_010",
        "exam": "ABA_ADVANCED",
        "topic": "Airway Management",
        "subtopic": "Complications",
        "tr": "Aspirasyon sonrası ilk yaklaşım oksijenasyon, havayolu temizliği, bronkospazm tedavisi ve destekleyici bakımdır.",
        "en": "Initial management after aspiration includes oxygenation, airway clearance, bronchospasm treatment, and supportive care.",
        "importance": "high",
        "tags": ["aspiration_management", "airway_clearance", "bronchospasm_treatment", "supportive_care"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 011
    {
        "id": "aba_advanced_spot_011",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Ciddi aort stenozunda taşikardi, hipotansiyon ve düşük preload miyokard iskemisini tetikleyebilir.",
        "en": "In severe aortic stenosis, tachycardia, hypotension, and low preload may trigger myocardial ischemia.",
        "importance": "high",
        "tags": ["aortic_stenosis", "tachycardia", "hypotension", "myocardial_ischemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 012
    {
        "id": "aba_advanced_spot_012",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Ciddi aort stenozunda sinüs ritmi ve sistemik vasküler direnç korunmalıdır.",
        "en": "In severe aortic stenosis, sinus rhythm and systemic vascular resistance should be maintained.",
        "importance": "high",
        "tags": ["aortic_stenosis", "sinus_rhythm", "svr"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 013
    {
        "id": "aba_advanced_spot_013",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Aort yetersizliğinde bradikardi regürjitasyon süresini artırarak hemodinamiyi kötüleştirebilir.",
        "en": "In aortic regurgitation, bradycardia may worsen hemodynamics by prolonging regurgitation time.",
        "importance": "high",
        "tags": ["aortic_regurgitation", "bradycardia", "regurgitation_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 014
    {
        "id": "aba_advanced_spot_014",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Mitral stenozda taşikardi diyastolik doluşu azaltır ve pulmoner ödem riskini artırır.",
        "en": "In mitral stenosis, tachycardia reduces diastolic filling and increases the risk of pulmonary edema.",
        "importance": "high",
        "tags": ["mitral_stenosis", "tachycardia", "pulmonary_edema"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 015
    {
        "id": "aba_advanced_spot_015",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Valvular Disease",
        "tr": "Mitral yetersizlikte aşırı afterload artışı regürjitan volümü artırabilir.",
        "en": "In mitral regurgitation, excessive afterload may increase regurgitant volume.",
        "importance": "high",
        "tags": ["mitral_regurgitation", "afterload", "regurgitant_volume"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 016
    {
        "id": "aba_advanced_spot_016",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Pulmoner hipertansiyonda hipoksi, hiperkapni, asidoz, hipotermi ve ağrı pulmoner vasküler direnci artırır.",
        "en": "In pulmonary hypertension, hypoxia, hypercapnia, acidosis, hypothermia, and pain increase pulmonary vascular resistance.",
        "importance": "high",
        "tags": ["pulmonary_hypertension", "pulmonary_vascular_resistance", "hypoxia", "hypercapnia", "acidosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 017
    {
        "id": "aba_advanced_spot_017",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Pulmonary Hypertension",
        "tr": "Pulmoner hipertansiyonda sağ ventrikül preload, ritim ve afterload değişikliklerine duyarlıdır.",
        "en": "In pulmonary hypertension, the right ventricle is sensitive to changes in preload, rhythm, and afterload.",
        "importance": "high",
        "tags": ["pulmonary_hypertension", "right_ventricle_sensitivity", "preload", "afterload"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 018
    {
        "id": "aba_advanced_spot_018",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Right Ventricle",
        "tr": "Sağ ventrikül yetmezliğinde sistemik hipotansiyon koroner perfüzyonu bozarak döngüyü kötüleştirebilir.",
        "en": "In right ventricular failure, systemic hypotension may impair coronary perfusion and worsen the cycle.",
        "importance": "high",
        "tags": ["right_ventricular_failure", "systemic_hypotension", "coronary_perfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 019
    {
        "id": "aba_advanced_spot_019",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Right Ventricle",
        "tr": "İntraoperatif sağ ventrikül krizinde oksijenasyon, normokapni, asidoz düzeltilmesi ve pulmoner vazodilatörler düşünülür.",
        "en": "In an intraoperative right ventricular crisis, consider oxygenation, normocapnia, correction of acidosis, and pulmonary vasodilators.",
        "importance": "high",
        "tags": ["right_ventricular_crisis", "oxygenation", "normocapnia", "pulmonary_vasodilators"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 020
    {
        "id": "aba_advanced_spot_020",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Heart Failure",
        "tr": "Kalp yetmezliği olan hastada anestezi indüksiyonu yavaş titrasyon ve hemodinamik destek gerektirebilir.",
        "en": "In heart failure, anesthetic induction may require slow titration and hemodynamic support.",
        "importance": "high",
        "tags": ["heart_failure", "anesthetic_induction", "slow_titration", "hemodynamic_support"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 021
    {
        "id": "aba_advanced_spot_021",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Heart Failure",
        "tr": "Düşük ejeksiyon fraksiyonunda miyokard depresyonu yapan ilaçlar dikkatle titre edilmelidir.",
        "en": "In low ejection fraction, myocardial depressant drugs should be carefully titrated.",
        "importance": "high",
        "tags": ["low_ef", "myocardial_depressants", "drug_titration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 022
    {
        "id": "aba_advanced_spot_022",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Heart Failure",
        "tr": "Diyastolik disfonksiyonda taşikardi ve volüm eksikliği doluşu bozarak hipotansiyona neden olabilir.",
        "en": "In diastolic dysfunction, tachycardia and volume depletion may impair filling and cause hypotension.",
        "importance": "high",
        "tags": ["diastolic_dysfunction", "tachycardia", "volume_depletion", "hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 023
    {
        "id": "aba_advanced_spot_023",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Artery Disease",
        "tr": "Koroner arter hastalığında miyokard oksijen sunumu ve tüketimi arasındaki denge korunmalıdır.",
        "en": "In coronary artery disease, the balance between myocardial oxygen supply and demand should be maintained.",
        "importance": "high",
        "tags": ["cad", "oxygen_supply_demand", "myocardial_balance"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 024
    {
        "id": "aba_advanced_spot_024",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Artery Disease",
        "tr": "Taşikardi, hipertansiyon, hipotansiyon ve anemi miyokard iskemisi riskini artırabilir.",
        "en": "Tachycardia, hypertension, hypotension, and anemia may increase the risk of myocardial ischemia.",
        "importance": "high",
        "tags": ["myocardial_ischemia_triggers", "tachycardia", "hypertension", "hypotension", "anemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 025
    {
        "id": "aba_advanced_spot_025",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Artery Disease",
        "tr": "Akut ST elevasyonu veya yeni ciddi iskemi elektif cerrahinin ertelenmesini gerektirebilir.",
        "en": "New severe ischemia or acute ST elevation may require postponement of elective surgery.",
        "importance": "high",
        "tags": ["st_elevation", "myocardial_ischemia", "surgery_delay"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 026
    {
        "id": "aba_advanced_spot_026",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Coronary Stents",
        "tr": "Koroner stentli hastada antiplatelet tedavi kararı stent trombozu ve cerrahi kanama riski birlikte değerlendirilerek verilir.",
        "en": "In patients with coronary stents, antiplatelet therapy decisions should balance stent thrombosis risk and surgical bleeding risk.",
        "importance": "high",
        "tags": ["coronary_stents", "antiplatelet_therapy", "stent_thrombosis", "bleeding_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 027
    {
        "id": "aba_advanced_spot_027",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Devices",
        "tr": "Monopolar koter, pacemaker ve ICD fonksiyonlarını etkileyebileceği için perioperatif cihaz planı gerekir.",
        "en": "Monopolar cautery may interfere with pacemaker and ICD function, so a perioperative device plan is required.",
        "importance": "high",
        "tags": ["monopolar_cautery", "pacemaker", "icd_interference", "device_plan"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 028
    {
        "id": "aba_advanced_spot_028",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Devices",
        "tr": "Pacemaker bağımlı hastada elektromanyetik girişim ciddi bradikardi veya asistoliye yol açabilir.",
        "en": "In pacemaker-dependent patients, electromagnetic interference may cause severe bradycardia or asystole.",
        "importance": "high",
        "tags": ["pacemaker_dependency", "electromagnetic_interference", "asystole", "bradycardia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 029
    {
        "id": "aba_advanced_spot_029",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Devices",
        "tr": "ICD bulunan hastada şok tedavisinin kapatılması gerekiyorsa eksternal defibrilasyon hazır bulundurulmalıdır.",
        "en": "If ICD shock therapy is disabled, external defibrillation must be immediately available.",
        "importance": "high",
        "tags": ["icd", "shock_disabled", "external_defibrillation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 030
    {
        "id": "aba_advanced_spot_030",
        "exam": "ABA_ADVANCED",
        "topic": "Cardiovascular Care",
        "subtopic": "Cardiac Tamponade",
        "tr": "Kardiyak tamponadda preload ve kalp hızı korunmalı, vazodilatasyon ve pozitif basınç etkileri dikkatle yönetilmelidir.",
        "en": "In cardiac tamponade, preload and heart rate should be maintained, and vasodilation and positive-pressure effects managed carefully.",
        "importance": "high",
        "tags": ["cardiac_tamponade", "preload", "heart_rate", "positive_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 031
    {
        "id": "aba_advanced_spot_031",
        "exam": "ABA_ADVANCED",
        "topic": "Hemodynamic Management",
        "subtopic": "Hypotension",
        "tr": "Hipotansiyon yönetiminde tedavi, kan basıncından çok doku perfüzyonunu hedeflemelidir.",
        "en": "Hypotension management should target tissue perfusion rather than blood pressure alone.",
        "importance": "high",
        "tags": ["hypotension_management", "tissue_perfusion", "blood_pressure"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 032
    {
        "id": "aba_advanced_spot_032",
        "exam": "ABA_ADVANCED",
        "topic": "Hemodynamic Management",
        "subtopic": "Vasoactive Drugs",
        "tr": "Vazodilatasyona bağlı şokta noradrenalin sistemik vasküler direnci artırmak için sık kullanılır.",
        "en": "In vasodilatory shock, norepinephrine is commonly used to increase systemic vascular resistance.",
        "importance": "high",
        "tags": ["vasodilatory_shock", "norepinephrine", "svr"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 033
    {
        "id": "aba_advanced_spot_033",
        "exam": "ABA_ADVANCED",
        "topic": "Hemodynamic Management",
        "subtopic": "Cardiogenic Shock",
        "tr": "Kardiyojenik şokta yalnızca vazokonstriksiyon kardiyak debiyi daha da azaltabilir.",
        "en": "In cardiogenic shock, vasoconstriction alone may further reduce cardiac output.",
        "importance": "high",
        "tags": ["cardiogenic_shock", "vasoconstriction_risk", "cardiac_output"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 034
    {
        "id": "aba_advanced_spot_034",
        "exam": "ABA_ADVANCED",
        "topic": "Hemodynamic Management",
        "subtopic": "Vasoactive Drugs",
        "tr": "İnotrop gereksinimi düşük kardiyak debi ve yetersiz doku perfüzyonu varlığında düşünülür.",
        "en": "Inotropes are considered when low cardiac output and inadequate tissue perfusion are present.",
        "importance": "high",
        "tags": ["inotropes", "low_cardiac_output", "tissue_hypoperfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 035
    {
        "id": "aba_advanced_spot_035",
        "exam": "ABA_ADVANCED",
        "topic": "Monitoring",
        "subtopic": "Hemodynamic Monitoring",
        "tr": "Dinamik preload indeksleri kontrollü mekanik ventilasyon, yeterli tidal volüm ve düzenli ritimde daha güvenilidir.",
        "en": "Dynamic preload indices are more reliable during controlled mechanical ventilation, adequate tidal volume, and regular rhythm.",
        "importance": "high",
        "tags": ["dynamic_preload_indices", "controlled_mechanical_ventilation", "regular_rhythm"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 036
    {
        "id": "aba_advanced_spot_036",
        "exam": "ABA_ADVANCED",
        "topic": "Monitoring",
        "subtopic": "Hemodynamic Monitoring",
        "tr": "Aritmi, spontan solunum ve açık toraks, pulse pressure variation yorumunu sınırlar.",
        "en": "Arrhythmia, spontaneous breathing, and open chest limit interpretation of pulse pressure variation.",
        "importance": "high",
        "tags": ["ppv_limitations", "arrhythmia", "spontaneous_breathing", "open_chest"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 037
    {
        "id": "aba_advanced_spot_037",
        "exam": "ABA_ADVANCED",
        "topic": "Monitoring",
        "subtopic": "Echocardiography",
        "tr": "TEE, kardiyak cerrahi ve instabil hemodinamide yapısal ve fonksiyonel değerlendirme sağlar.",
        "en": "TEE provides structural and functional assessment in cardiac surgery and unstable hemodynamics.",
        "importance": "high",
        "tags": ["tee", "cardiac_surgery", "hemodynamic_instability"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 038
    {
        "id": "aba_advanced_spot_038",
        "exam": "ABA_ADVANCED",
        "topic": "Monitoring",
        "subtopic": "Echocardiography",
        "tr": "TEE ile ventrikül dolumu, kontraktilite, kapak fonksiyonu ve hava embolisi değerlendirilebilir.",
        "en": "TEE can assess ventricular filling, contractility, valve function, and air embolism.",
        "importance": "high",
        "tags": ["tee_assessment", "ventricular_filling", "contractility", "valve_function", "air_embolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 039
    {
        "id": "aba_advanced_spot_039",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Hypotension",
        "tr": "Ani intraoperatif hipotansiyonda kanama, anafilaksi, emboli, aritmi ve ilaç etkisi hızla ayırt edilmelidir.",
        "en": "In sudden intraoperative hypotension, bleeding, anaphylaxis, embolism, arrhythmia, and drug effects should be rapidly distinguished.",
        "importance": "high",
        "tags": ["sudden_hypotension", "differential_diagnosis", "anaphylaxis", "embolism", "bleeding"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 040
    {
        "id": "aba_advanced_spot_040",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilakside deri bulgularının olmaması tanıyı dışlamaz.",
        "en": "Absence of skin signs does not exclude anaphylaxis.",
        "importance": "high",
        "tags": ["anaphylaxis", "skin_signs_absent"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 041
    {
        "id": "aba_advanced_spot_041",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Anaphylaxis",
        "tr": "Perioperatif anafilakside adrenalin geciktirilmemelidir.",
        "en": "Epinephrine should not be delayed in perioperative anaphylaxis.",
        "importance": "high",
        "tags": ["anaphylaxis_treatment", "epinephrine_timing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 042
    {
        "id": "aba_advanced_spot_042",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Anaphylaxis",
        "tr": "Anafilakside büyük miktarda sıvı ihtiyacı kapiller kaçak ve vazodilatasyona bağlıdır.",
        "en": "Large fluid requirements in anaphylaxis are due to capillary leak and vasodilation.",
        "importance": "high",
        "tags": ["anaphylaxis_resuscitation", "fluid_requirements", "capillary_leak", "vasodilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 043
    {
        "id": "aba_advanced_spot_043",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermide açıklanamayan ETCO₂ artışı genellikle hiperterminin öncesinde ortaya çıkar.",
        "en": "In malignant hyperthermia, unexplained ETCO₂ rise usually appears before hyperthermia.",
        "importance": "high",
        "tags": ["malignant_hyperthermia", "etco2_elevation", "prodromal_signs"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 044
    {
        "id": "aba_advanced_spot_044",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Malign hipertermi tedavisinde tetikleyicilerin kesilmesi, dantrolen ve aktif soğutma temel basamaklardır.",
        "en": "Core treatment of malignant hyperthermia includes stopping triggers, dantrolene, and active cooling.",
        "importance": "high",
        "tags": ["malignant_hyperthermia_treatment", "dantrolene", "cooling"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 045
    {
        "id": "aba_advanced_spot_045",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Malignant Hyperthermia",
        "tr": "Dantrolen uygulaması klinik düzelme sağlanana kadar tekrarlanan dozlar gerektirebilir.",
        "en": "Dantrolene may require repeated dosing until clinical improvement occurs.",
        "importance": "high",
        "tags": ["dantrolene_dosing", "repeated_dosing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 046
    {
        "id": "aba_advanced_spot_046",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Massive Transfusion",
        "tr": "Masif kanamada erken hemostaz, ısı korunması ve dengeli transfüzyon sağkalımı etkiler.",
        "en": "In massive bleeding, early hemostasis, temperature preservation, and balanced transfusion affect survival.",
        "importance": "high",
        "tags": ["massive_bleeding", "hemostasis", "temperature_preservation", "balanced_transfusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 047
    {
        "id": "aba_advanced_spot_047",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Massive Transfusion",
        "tr": "Masif transfüzyonda hipokalsemi kardiyak kontraktiliteyi ve koagülasyonu bozabilir.",
        "en": "During massive transfusion, hypocalcemia can impair cardiac contractility and coagulation.",
        "importance": "high",
        "tags": ["massive_transfusion", "hypocalcemia_effects", "cardiac_contractility", "coagulation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 048
    {
        "id": "aba_advanced_spot_048",
        "exam": "ABA_ADVANCED",
        "topic": "Perioperative Crises",
        "subtopic": "Coagulation",
        "tr": "Fibrinojen travmatik kanamada erken düşebilir ve kanama kontrolünde kritik rol oynar.",
        "en": "Fibrinogen may fall early in traumatic bleeding and plays a critical role in bleeding control.",
        "importance": "high",
        "tags": ["fibrinogen", "traumatic_bleeding", "bleeding_control"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 049
    {
        "id": "aba_advanced_spot_049",
        "exam": "ABA_ADVANCED",
        "topic": "Monitoring",
        "subtopic": "Coagulation Monitoring",
        "tr": "ROTEM/TEG hedefe yönelik koagülasyon tedavisi için kullanılabilir.",
        "en": "ROTEM/TEG can be used for goal-directed coagulation therapy.",
        "importance": "high",
        "tags": ["rotem", "teg", "viscoelastic_tests", "goal_directed_coagulation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 050
    {
        "id": "aba_advanced_spot_050",
        "exam": "ABA_ADVANCED",
        "topic": "Pharmacology",
        "subtopic": "Antifibrinolytics",
        "tr": "Traneksamik asit, uygun travma hastasında erken verildiğinde fibrinolizi azaltabilir.",
        "en": "Tranexamic acid can reduce fibrinolysis when given early in appropriate trauma patients.",
        "importance": "high",
        "tags": ["txa", "tranexamic_acid", "fibrinolysis_reduction", "trauma"],
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
