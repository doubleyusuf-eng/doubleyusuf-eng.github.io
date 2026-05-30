import sys

def main():
    file_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataAlgorithms.kt"
    
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()
        
    # Locate the end of the diabetic_glucose_management algorithm and the closing parenthesis of the listOf(...)
    # In the file, the end of the last algorithm has:
    #                 "end" to AlgorithmNode(
    #                     type = "end",
    #                     textTr = "Perioperatif kan şekeri hedef aralıkta (100-180 mg/dL) stabilize edildi.",
    #                     warningLevel = "none"
    #                 )
    #             ),
    #             requiresClinicalValidation = false
    #         ),
    # )
    # }
    
    # We want to find the last occurrence of ")," before the list closing.
    # Let's see: we can replace:
    #             requiresClinicalValidation = false
    #         ),
    # )
    # }
    # with our new algorithms appended inside the list!
    
    target = """            requiresClinicalValidation = false
        ),
)
}"""

    # Let's double check if this target is in the content.
    if target not in content:
        # Let's try with a more flexible matching or look at the end of the file.
        print("FAIL: Exact target signature not found in file.")
        sys.exit(1)
        
    print("Found target signature.")
    
    # Let's define the 10 new algorithms in Kotlin syntax
    new_algs_kotlin = """            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "tension_pneumothorax",
            titleTr = "Tansiyon Pnömotoraks Yönetimi",
            titleEn = "Tension Pneumothorax / Perioperative Pneumothorax",
            category = "airway",
            urgencyLevel = "emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada ani SpO₂ düşüşü, havayolu basıncında artış, hipotansiyon ve tek taraflı solunum seslerinde azalma var mı?",
                    textEn = "Is there sudden hypoxemia, increased airway pressure, hypotension, and unilateral decreased breath sounds?",
                    options = listOf(
                        AlgorithmOption("Evet (Tansiyon Pnömotoraks Şüphesi)", "Yes (Tension Pneumothorax Suspected)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Yardım çağırın ve cerrahı bilgilendirin. 2. FiO₂'yi %100 yapın. 3. Pozitif basınçlı ventilasyonu durdurun/manuel ventilasyona geçip basıncı düşürün. 4. Hemodinamik desteğe başlayın.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Call for help and inform surgeon. 2. Set FiO₂ to 100%. 3. Stop positive pressure ventilation/switch to manual with low pressures. 4. Initiate hemodynamic support.",
                    next = "q_decompression"
                ),
                "q_decompression" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta stabil değilse (şok veya arrest riski), görüntüleme beklenmeden iğne torakosentez dekompresyonu uygulansın mı?",
                    textEn = "If patient is unstable (risk of shock or arrest), should needle thoracocentesis be performed without waiting for imaging?",
                    options = listOf(
                        AlgorithmOption("Evet (Acil Dekompresyon)", "Yes (Emergency Decompression)", "action_decompression"),
                        AlgorithmOption("Hayır (Önce USG/Grafi ile doğrula)", "No (Verify via USG/X-ray first)", "action_imaging")
                    )
                ),
                "action_decompression" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İĞNE DEKOMPRESYONU: 2. veya 5. interkostal aralıktan kalın bir iğne/kateter ile girin (havanın fışkırmasını görün/duyun). Ardından acil göğüs tüpü yerleştirilmesini planlayın.",
                    textEn = "🚨 NEEDLE DECOMPRESSION: Insert large-bore angiocath in 2nd or 5th intercostal space. Watch for rush of air. Plan immediate chest tube insertion.",
                    next = "end"
                ),
                "action_imaging" to AlgorithmNode(
                    type = "action",
                    textTr = "Hızlı yatak başı USG veya akciğer grafisi çekin. Pnömotoraks doğrulanırsa acil göğüs tüpü yerleştirin.",
                    textEn = "Perform rapid bedside USG or chest X-ray. If pneumothorax is confirmed, insert immediate chest tube.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Tansiyon pnömotoraks dekompresyonu sağlandı. Akciğer re-ekspansiyonunu ve solunum seslerini takip edin. Postoperatif yoğun bakım (ICU) takibi planlayın. Unutma: Şüphe yüksekse görüntüleme beklenmemelidir.",
                    textEn = "Tension pneumothorax decompressed. Monitor lung re-expansion and breath sounds. Plan postoperative ICU admission. Do not forget: If suspicion is high, do not wait for imaging.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "pulmonary_embolism_crisis",
            titleTr = "Akut Pulmoner Emboli Krizi",
            titleEn = "Pulmonary Embolism",
            category = "hemodynamics",
            urgencyLevel = "emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada ani ETCO₂ düşüşü, hipoksemi, hipotansiyon, taşikardi veya sağ ventrikül yüklenmesi bulguları var mı?",
                    textEn = "Is there sudden ETCO₂ drop, hypoxemia, hypotension, tachycardia, or signs of right ventricular strain?",
                    options = listOf(
                        AlgorithmOption("Evet (Pulmoner Emboli Şüphesi)", "Yes (Pulmoner Emboli Suspected)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Yardım çağırın. 2. %100 O₂ verin ve anestezi derinliğini azaltın. 3. Cerrahı bilgilendirin. 4. Hemodinamik instabilite için hızlı sıvı bolusu ve inotrop/vazopressör desteğine (Noradrenalin, Adrenalin) başlayın.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Call for help. 2. Set FiO₂ to 100% and reduce anesthetic depth. 3. Inform surgeon. 4. Start fluid bolus and inotrope/vasopressor support (Norepinephrine, Epinephrine) for hemodynamic instability.",
                    next = "q_diagnosis"
                ),
                "q_diagnosis" to AlgorithmNode(
                    type = "question",
                    textTr = "Ameliyathanede yatak başı transtorasik veya transözofageal ekokardiyografi (TTE/TEE) imkanı var mı?",
                    textEn = "Is bedside transthoracic or transesophageal echocardiography (TTE/TEE) available in the OR?",
                    options = listOf(
                        AlgorithmOption("Evet (EKO yapılabilir)", "Yes (ECHO available)", "action_echo"),
                        AlgorithmOption("Hayır", "No", "action_no_echo")
                    )
                ),
                "action_echo" to AlgorithmNode(
                    type = "action",
                    textTr = "EKO ile sağ ventrikül dilatasyonu, pulmoner hipertansiyon ve McConnell bulgusu (sağ ventrikül serbest duvar hipokinezisi) taraması yapın. Sağ ventrikül yetmezliğini teyit edin.",
                    textEn = "Screen with ECHO for RV dilation, pulmonary hypertension, and McConnell's sign (RV free wall hypokinesis). Confirm right ventricular failure.",
                    next = "q_refractory_shock"
                ),
                "action_no_echo" to AlgorithmNode(
                    type = "action",
                    textTr = "Klinik bulgularla (açıklanamayan ciddi hipotansiyon, hipoksemi, ani ETCO₂ kaybı) sağ ventrikül yetmezliği ve tıkayıcı şok varsayımıyla devam edin.",
                    textEn = "Proceed on clinical grounds (unexplained severe hypotension, hypoxemia, sudden loss of ETCO₂) assuming right ventricular failure and obstructive shock.",
                    next = "q_refractory_shock"
                ),
                "q_refractory_shock" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada dirençli şok veya kardiyak arrest tablosu gelişiyor mu?",
                    textEn = "Is the patient developing refractory shock or cardiac arrest?",
                    options = listOf(
                        AlgorithmOption("Evet (Kritik arrest / şok)", "Yes (Critical arrest / shock)", "action_thrombolysis"),
                        AlgorithmOption("Hayır (Hemodinamik olarak toparlıyor)", "No (Hemodynamically recovering)", "end")
                    )
                ),
                "action_thrombolysis" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 KRİTİK TEDAVİ: 1. Arrest durumunda yüksek kaliteli CPR başlatın. 2. Multidisipliner ekiple görüşerek trombolitik (tPA) tedavisini veya acil cerrahi embolektomi / ECMO desteğini derhal başlatın.",
                    textEn = "🚨 CRITICAL MANAGEMENT: 1. Start high-quality CPR in case of arrest. 2. Discuss with multidisciplinary team to immediately initiate thrombolytic (tPA) therapy or emergency surgical embolectomy / ECMO support.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Müdahale tamamlandı. Hastayı yoğun bakım ünitesine (ICU) transfer edin. Unutma: Ayırıcı tanıda hava embolisi, anafilaksi, masif kanama ve pnömotoraks mutlaka dışlanmalıdır.",
                    textEn = "Intervention completed. Transfer the patient to the ICU. Do not forget: Air embolism, anaphylaxis, massive hemorrhage, and pneumothorax must be ruled out in the differential diagnosis.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "venous_air_embolism",
            titleTr = "Venöz Hava Embolisi Yönetimi",
            titleEn = "Venous Air Embolism",
            category = "hemodynamics",
            urgencyLevel = "emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Özellikle oturur pozisyonda cerrahi, kraniyotomi, büyük venöz açıklıklar veya obstetrik cerrahide ani ETCO₂ düşüşü, hipoksemi, hipotansiyon veya üfürüm (mill-wheel) var mı?",
                    textEn = "In surgeries like sitting position, craniotomy, open venous sites, or obstetrics, is there sudden ETCO₂ drop, hypoxemia, hypotension, or a mill-wheel murmur?",
                    options = listOf(
                        AlgorithmOption("Evet (Hava Embolisi Şüphesi)", "Yes (Air Embolism Suspected)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Yardım çağırın. 2. Cerrahı hemen bilgilendirin: Cerrahi sahayı serum fizyolojikle yıkamasını ve hava giriş yerini kapatmasını isteyin. 3. %100 O₂ verin. 4. Santral venöz kateter varsa havayı aspirasyon ile çekmeyi deneyin.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Call for help. 2. Inform surgeon immediately: Ask to flood surgical field with saline and occlude air entry sites. 3. Set FiO₂ to 100%. 4. If central venous catheter is present, attempt to aspirate air.",
                    next = "q_positioning"
                ),
                "q_positioning" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastanın cerrahi alanı ve pozisyonu sol lateral Trendelenburg (Durant manevrası) pozisyonuna geçirilmesine uygun mu?",
                    textEn = "Is the surgical field and patient positioning suitable to change to left lateral Trendelenburg (Durant's maneuver) position?",
                    options = listOf(
                        AlgorithmOption("Evet (Durant Pozisyonuna Al)", "Yes (Put in Durant Position)", "action_durant"),
                        AlgorithmOption("Hayır (Pozisyon değiştirilemez)", "No (Cannot change position)", "action_hemodynamics")
                    )
                ),
                "action_durant" to AlgorithmNode(
                    type = "action",
                    textTr = "Hastayı sol lateral dekübit ve Trendelenburg pozisyonuna getirin (havanın sağ ventrikül çıkış yolunu tıkamasını engellemek için). Hemodinamik desteğe Noradrenalin ile devam edin.",
                    textEn = "Place patient in left lateral decubitus and Trendelenburg position (to prevent air from blocking the RV outflow tract). Continue hemodynamic support with Norepinephrine.",
                    next = "end"
                ),
                "action_hemodynamics" to AlgorithmNode(
                    type = "action",
                    textTr = "Pozisyonu değiştirmeyin. Agresif inotrop ve vazopressör desteğine (Noradrenalin, Adrenalin) başlayın. Cerrahi sahada venöz basıncı artırmak için hafif juguler ven kompresyonu uygulayabilirsiniz.",
                    textEn = "Do not change position. Start aggressive inotrope and vasopressor support (Norepinephrine, Epinephrine). Consider gentle jugular venous compression to increase CVP and prevent further air entry.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Hava embolisi yönetimi tamamlandı. Ritim bozuklukları ve sağ yetmezlik açısından hastayı izleyin. Postoperatif yoğun bakım (ICU) takibi yapın. Unutma: Cerraha haber vermek en kritik basamaktır.",
                    textEn = "Air embolism management completed. Monitor for arrhythmias and right heart failure. Plan postoperative ICU admission. Do not forget: Informing the surgeon is the most critical step.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "severe_perioperative_shock",
            titleTr = "Perioperatif Şiddetli Şok Protokolü",
            titleEn = "Severe Perioperative Shock",
            category = "hemodynamics",
            urgencyLevel = "emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada derin hipotansiyon (OAB / MAP <50 mmHg), perfüzyon bozukluğu, soğuk soluk cilt veya laktat artışı var mı?",
                    textEn = "Does the patient have severe hypotension (MAP <50 mmHg), impaired perfusion, cold pale skin, or elevated lactate?",
                    options = listOf(
                        AlgorithmOption("Evet (Ciddi Şok Tablosu)", "Yes (Severe Shock)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Yardım çağırın. 2. FiO₂'yi %100 yapın ve anestezi derinliğini azaltın. 3. Cerrahı bilgilendirin. 4. Damar yollarını kontrol edin. 5. Noradrenalin infüzyonunu veya bolusunu (10-20 mcg IV) geciktirmeden başlatın.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Call for help. 2. Set FiO₂ to 100% and reduce anesthetic depth. 3. Inform surgeon. 4. Verify vascular access. 5. Immediately start Norepinephrine infusion or bolus (10-20 mcg IV).",
                    next = "q_shock_type"
                ),
                "q_shock_type" to AlgorithmNode(
                    type = "question",
                    textTr = "Şokun ana kaynağı nedir? (Sistematik ayırım yapın)",
                    textEn = "What is the primary etiology of the shock? (Differentiate systematically)",
                    options = listOf(
                        AlgorithmOption("Hipovolemik (Kanama / Sıvı kaybı)", "Hypovolemic (Bleeding / Fluid loss)", "action_hypovolemic"),
                        AlgorithmOption("Vazoplejik (Anestezi, Sepsis, Anafilaksi)", "Vasoplegic (Anesthesia, Sepsis, Anaphylaxis)", "action_vasoplegic"),
                        AlgorithmOption("Kardiyojenik (Miyokard İskemisi, Aritmi)", "Cardiogenic (Myocardial Ischemia, Arrhythmia)", "action_cardiogenic"),
                        AlgorithmOption("Obstrüktif (Tansiyon Pnömotoraks, PE)", "Obstructive (Tension Pneumothorax, PE)", "action_obstructive")
                    )
                ),
                "action_hypovolemic" to AlgorithmNode(
                    type = "action",
                    textTr = "1. Cerrahi kanama kontrolü isteyin. 2. Masif transfüzyon protokolünü (MTP) aktif edin. 3. Kristalloid ve kan ürünlerini hızlıca gönderin, kalsiyum takviyesi yapın.",
                    textEn = "1. Request surgical hemostasis. 2. Activate massive transfusion protocol (MTP). 3. Rapidly administer crystalloids and blood products, and supplement calcium.",
                    next = "end"
                ),
                "action_vasoplegic" to AlgorithmNode(
                    type = "action",
                    textTr = "1. Noradrenalin dozunu titre edin. 2. Refrakter anafilaksi ise IV Adrenalin (10-50 mcg) uygulayın. 3. Sepsis şüphesinde erken antibiyotik ve kaynak kontrolü uygulayın.",
                    textEn = "1. Titrate Norepinephrine dose. 2. If refractory anaphylaxis, administer IV Epinephrine (10-50 mcg). 3. If sepsis is suspected, start early antibiotics and source control.",
                    next = "end"
                ),
                "action_cardiogenic" to AlgorithmNode(
                    type = "action",
                    textTr = "1. İnotropik destek (Dobutamin 2-10 mcg/kg/dk) ekleyin. 2. 12 derivasyonlu EKG çekin, ST değişikliklerini inceleyin. 3. Acil kardiyoloji konsültasyonu ve koroner anjiyografi planlayın.",
                    textEn = "1. Add inotropic support (Dobutamine 2-10 mcg/kg/min). 2. Obtain 12-lead ECG, review ST changes. 3. Plan urgent cardiology consult and coronary angiography.",
                    next = "end"
                ),
                "action_obstructive" to AlgorithmNode(
                    type = "action",
                    textTr = "1. Pnömotoraks şüphesinde derhal dekompresyon yapın. 2. Hava veya pulmoner emboli için spesifik tedavi algoritmalarını uygulayın.",
                    textEn = "1. In case of suspected pneumothorax, decompress immediately. 2. Apply specific treatment algorithms for air or pulmonary embolism.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Şok yönetimi stabilize edildi. MAP tek başına hedef değildir; perfüzyon, idrar çıkışı ve laktat takibi ile oksijen sunumunu optimize edin. Postoperatif yoğun bakım (ICU) takibine alın.",
                    textEn = "Shock management stabilized. MAP alone is not the target; optimize oxygen delivery by monitoring perfusion, urine output, and lactate. Admit to the ICU postoperatively.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "rv_failure_ph_crisis",
            titleTr = "Sağ Ventrikül Yetmezliği & Pulmoner Hipertansiyon",
            titleEn = "Right Ventricular Failure / Pulmonary Hypertension Crisis",
            category = "hemodynamics",
            urgencyLevel = "emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Pulmoner hipertansiyon veya sağ ventrikül yetmezliği olan hastada ani hipotansiyon, CVP artışı, hipoksemi veya EKO'da sağ ventrikül disfonksiyonu var mı?",
                    textEn = "In a patient with PH or RV failure, is there sudden hypotension, increased CVP, hypoxemia, or RV dysfunction on ECHO?",
                    options = listOf(
                        AlgorithmOption("Evet (RV / PH Krizi)", "Yes (RV / PH Crisis)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Yardım çağırın. 2. FiO₂'yi %100 yapın. 3. Solunumsal asidoz ve hiperkapniden kaçınmak için ventilasyonu optimize edin (hiperkapni pulmoner vasküler direnci (PVR) belirgin artırır). 4. Aşırı PEEP'ten kaçının (RV preload'u korumak için).",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Call for help. 2. Set FiO₂ to 100%. 3. Optimize ventilation to avoid respiratory acidosis and hypercapnia (hypercapnia significantly increases PVR). 4. Avoid excessive PEEP (to preserve RV preload).",
                    next = "q_perfusion"
                ),
                "q_perfusion" to AlgorithmNode(
                    type = "question",
                    textTr = "Sistemik arteriyel kan basıncını korumak ve koroner perfüzyonu devam ettirmek için inotrop/vazopressör başlansın mı?",
                    textEn = "Should inotrope/vasopressor support be started to maintain systemic arterial blood pressure and coronary perfusion?",
                    options = listOf(
                        AlgorithmOption("Evet (Noradrenalin + Dobutamin/Milrinon)", "Yes (Norepinephrine + Dobutamine/Milrinone)", "action_inotropes"),
                        AlgorithmOption("Sadece Noradrenalin başla", "Start Norepinephrine only", "action_norepi_only")
                    )
                ),
                "action_inotropes" to AlgorithmNode(
                    type = "action",
                    textTr = "Noradrenalin (sistemik basıncı korumak için) ve Dobutamin / Milrinon (sağ ventrikül kontraktilitesini artırmak için) kombine infüzyonunu başlatın. PVR'yi düşürmek için inhale Nitrik Oksit (iNO) veya inhale İloprost düşünün.",
                    textEn = "Start combined infusion of Norepinephrine (to protect systemic pressure) and Dobutamine / Milrinone (to increase RV contractility). Consider inhaled Nitric Oxide (iNO) or inhaled Iloprost to reduce PVR.",
                    next = "end"
                ),
                "action_norepi_only" to AlgorithmNode(
                    type = "action",
                    textTr = "Öncelikle Noradrenalin infüzyonuna başlayın. Sağ koroner perfüzyon basıncını MAP > 65 mmHg düzeyinde tutarak koruyun. Asidoz, hipotermi ve ağrıyı aktif olarak tedavi edin.",
                    textEn = "Start Norepinephrine infusion first. Protect right coronary perfusion pressure by maintaining MAP > 65 mmHg. Actively treat acidosis, hypothermia, and pain.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "RV/PH krizi yönetimi tamamlandı. İntraoperatif sıvı yüklemesinden kaçının (aşırı sıvı sağ ventrikülü daha da dilate edip yetmezliği derinleştirebilir). Postoperatif yoğun bakım (ICU) takibine alın.",
                    textEn = "RV/PH crisis management completed. Avoid excessive intraoperative fluid loading (volume overload can further dilate the RV and worsen failure). Admit to the ICU postoperatively.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "eclampsia_preeclampsia_crisis",
            titleTr = "Eklampsi & Şiddetli Preeklampsi Yönetimi",
            titleEn = "Eclampsia / Severe Preeclampsia Crisis",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Gebede tonik-klonik nöbet (eklampsi), ciddi hipertansiyon (SAB >=160 veya DAB >=110 mmHg), şiddetli baş ağrısı, görme bozukluğu veya epigastrik ağrı var mı?",
                    textEn = "In a pregnant patient, is there tonic-clonic seizure (eclampsia), severe hypertension (SBP >=160 or DBP >=110 mmHg), severe headache, visual disturbance, or epigastric pain?",
                    options = listOf(
                        AlgorithmOption("Evet (Eklampsi / Ciddi Preeklampsi)", "Yes (Eclampsia / Severe Preeclampsia)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Yardım çağırın ve obstetri ekibini bilgilendirin. 2. Havayolunu koruyun: Hastayı sol lateral pozisyona getirin, aspirasyonu önleyin ve maske ile %100 O₂ verin. 3. Magnezyum Sülfat (MgSO₄) yükleme dozunu planlayın.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Call for help and inform obstetrics team. 2. Protect airway: Place patient in left lateral position, prevent aspiration, and give 100% O₂ via mask. 3. Plan Magnesium Sulfate (MgSO₄) loading dose.",
                    next = "q_seizure_control"
                ),
                "q_seizure_control" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta aktif olarak nöbet geçirmeye devam ediyor mu?",
                    textEn = "Is the patient actively continuing to seize?",
                    options = listOf(
                        AlgorithmOption("Evet (Nöbet devam ediyor)", "Yes (Seizure continuing)", "action_seizure_tx"),
                        AlgorithmOption("Hayır (Nöbet durdu, profilaksi)", "No (Seizure stopped, prophylaxis)", "action_prophylaxis")
                    )
                ),
                "action_seizure_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 NÖBET TEDAVİSİ: 1. MgSO₄ 4-6 gram IV bolus (15-20 dakikada) uygulayın. 2. Nöbet devam ederse ek olarak 2 gram daha MgSO₄ IV bolus yapın. 3. Refrakter nöbette IV Diazepam (5-10 mg) veya Propofol (1-2 mg/kg IV) düşünün ve acil zor havayolu entübasyonu hazırlığı yapın.",
                    textEn = "🚨 SEIZURE TREATMENT: 1. Give MgSO₄ 4-6 grams IV bolus over 15-20 min. 2. If seizure continues, add MgSO₄ 2 grams IV bolus. 3. If refractory, consider IV Diazepam (5-10 mg) or Propofol (1-2 mg/kg IV) and prepare for emergency difficult airway intubation.",
                    next = "q_hypertension"
                ),
                "action_prophylaxis" to AlgorithmNode(
                    type = "action",
                    textTr = "Nöbet kontrol altında. MgSO₄ infüzyonuna başlayın (1-2 gram/saat). Derin tendon reflekslerini, idrar çıkışını ve solunum sayısını magnezyum toksisitesi açısından yakından izleyin.",
                    textEn = "Seizure under control. Start MgSO₄ infusion (1-2 grams/hour). Closely monitor deep tendon reflexes, urine output, and respiratory rate for magnesium toxicity.",
                    next = "q_hypertension"
                ),
                "q_hypertension" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastanın sistolik tansiyonu >= 160 mmHg veya diyastolik tansiyonu >= 110 mmHg düzeyinde mi? (Ciddi Hipertansiyon)",
                    textEn = "Is the patient's SBP >= 160 mmHg or DBP >= 110 mmHg? (Severe Hypertension)",
                    options = listOf(
                        AlgorithmOption("Evet (Ciddi Hipertansiyon)", "Yes (Severe Hypertension)", "action_antihypertensives"),
                        AlgorithmOption("Hayır (Tansiyon kabul edilebilir)", "No (Acceptable blood pressure)", "end")
                    )
                ),
                "action_antihypertensives" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 HİPERTANSİYON TEDAVİSİ: 1. IV Labetalol (20 mg IV bolus, gerekirse 10 dakikada bir 40-80 mg artırarak, maks 300 mg) veya IV Hidralazin (5-10 mg IV bolus) uygulayın. 2. Hedef tansiyon: Sistolik 140-150 mmHg, Diyastolik 90-100 mmHg olmalıdır (plasental perfüzyonu korumak için ani düşüşlerden kaçının).",
                    textEn = "🚨 HYPERTENSION TREATMENT: 1. Give IV Labetalol (20 mg IV bolus, repeat with 40-80 mg every 10 min as needed, max 300 mg) or IV Hydralazine (5-10 mg IV bolus). 2. Target BP: SBP 140-150 mmHg, DBP 90-100 mmHg (avoid sudden drops to protect placental perfusion).",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Kriz kontrol altına alındı. Eklamptik gebede havayolu ödemi ve aşırı zor havayolu entübasyonu riski çok yüksektir! Doğum planlaması ve postoperatif yoğun bakım (ICU) transferini obstetri ekibiyle koordineli yapın.",
                    textEn = "Crisis stabilized. Airway edema and extreme difficult airway intubation risk are very high in eclamptic patients! Coordinate delivery planning and postoperative ICU transfer with the obstetrics team.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "postpartum_hemorrhage",
            titleTr = "Postpartum Kanama (PPH) Protokolü",
            titleEn = "Postpartum Hemorrhage",
            category = "fluids",
            urgencyLevel = "emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Doğum veya sezaryen sonrası aktif masif vajinal kanama, hipotansiyon, taşikardi veya rahim gevşekliği (atoni) var mı?",
                    textEn = "After delivery or cesarean section, is there active massive vaginal bleeding, hypotension, tachycardia, or uterine laxity (atony)?",
                    options = listOf(
                        AlgorithmOption("Evet (Postpartum Kanama Şüphesi)", "Yes (PPH Suspected)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Yardım çağırın ve masif kanama protokolünü başlatın. 2. %100 O₂ verin ve hemodinamik takibi sıklaştırın. 3. Cerrahi ekibe hemen haber verin ve manuel uterin masaja başlamalarını sağlayın. 4. IV Traneksamik Asit (1 gram IV) uygulayın.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Call for help and initiate massive hemorrhage protocol. 2. Set FiO₂ to 100% and intensify hemodynamic monitoring. 3. Inform surgical team immediately to begin bimanual uterine massage. 4. Administer IV Tranexamic Acid (1 gram IV).",
                    next = "q_atony_treatment"
                ),
                "q_atony_treatment" to AlgorithmNode(
                    type = "question",
                    textTr = "Kanamanın ana nedeni rahim atonisi (Tonus) mi? (Uterotonik tedavisi planlanıyor)",
                    textEn = "Is the primary cause of hemorrhage uterine atony (Tone)? (Uterotonic therapy planned)",
                    options = listOf(
                        AlgorithmOption("Evet (Uterotonik Protokolü Başlat)", "Yes (Start Uterotonic Protocol)", "action_uterotonics"),
                        AlgorithmOption("Hayır (Diğer 4T nedenleri: Travma, Doku, Trombin)", "No (Other 4T causes: Trauma, Tissue, Thrombin)", "action_other_4t")
                    )
                ),
                "action_uterotonics" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 UTEROTONİK PROTOKOLÜ: 1. Oksitosin (20 Ünite 500 mL serum içinde IV infüzyon, hızlı bolustan kaçının!). 2. Metilergometrin (Methergin) (0.2 mg IM, HT varsa kaçının!). 3. Karboprost (0.25 mg IM, astım varsa kaçının!). 4. Misoprostol (800-1000 mcg rektal/sublingual).",
                    textEn = "🚨 UTEROTONIC PROTOCOL: 1. Oxytocin (20 Units in 500 mL saline IV infusion, avoid rapid IV bolus!). 2. Methylergonovine (0.2 mg IM, avoid if hypertensive!). 3. Carboprost (0.25 mg IM, avoid if asthmatic!). 4. Misoprostol (800-1000 mcg rectal/sublingual).",
                    next = "q_bleeding_control"
                ),
                "action_other_4t" to AlgorithmNode(
                    type = "action",
                    textTr = "Cerrahi ekibin yırtık, plasenta retansiyonu veya plasenta akreta yönünden değerlendirme yapmasını sağlayın. Koagülopati varsa taze donmuş plazma, kriyopresipitat ve fibrinojen konsantresi uygulayın.",
                    textEn = "Ensure surgical team evaluates for lacerations, retained placenta, or placenta accreta. If coagulopathy is present, administer FFP, cryoprecipitate, and fibrinogen concentrate.",
                    next = "q_bleeding_control"
                ),
                "q_bleeding_control" to AlgorithmNode(
                    type = "question",
                    textTr = "Uterotonik ve ilk cerrahi önlemlere rağmen kanama devam ediyor mu?",
                    textEn = "Is bleeding continuing despite uterotonics and initial surgical measures?",
                    options = listOf(
                        AlgorithmOption("Evet (Dirençli Kanama)", "Yes (Refractory Bleeding)", "action_refractory"),
                        AlgorithmOption("Hayır (Kanama kontrol altında)", "No (Bleeding controlled)", "end")
                    )
                ),
                "action_refractory" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 DİRENÇLİ KANAMA: Bakri balonu yerleştirilmesi, uterin arter ligasyonu, B-Lynch sütürü veya acil Histerektomi cerrahi adımlarını derhal başlatın. Agresif kan transfüzyonu ve kalsiyum desteğine devam edin.",
                    textEn = "🚨 REFRACTORY HEMORRHAGE: Immediately initiate surgical steps like Bakri balloon insertion, uterine artery ligation, B-Lynch suture, or emergency Hysterectomy. Continue aggressive blood transfusion and calcium support.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Postpartum kanama yönetimi sonlandırıldı. Ölümcül triad (hipotermi, asidoz, koagülopati) ve hipokalsemiyi aktif olarak önleyin. Postoperatif yoğun bakım (ICU) takibine alın.",
                    textEn = "PPH management completed. Actively prevent the lethal triad (hypothermia, acidosis, coagulopathy) and hypocalcemia. Admit to the ICU postoperatively.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "opioid_respiratory_depression",
            titleTr = "Opioid İlişkili Solunum Depresyonu",
            titleEn = "Opioid-Induced Respiratory Depression",
            category = "drugs",
            urgencyLevel = "emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "PACU veya serviste hastada ciddi sedasyon, düşük solunum sayısı (Solunum < 8/dk), hiperkapni, hipoksemi veya iğne başı pupil var mı?",
                    textEn = "In PACU or ward, does the patient have deep sedation, low respiratory rate (< 8/min), hypercapnia, hypoxemia, or pin-point pupils?",
                    options = listOf(
                        AlgorithmOption("Evet (Opioid Depresyonu)", "Yes (Opioid Depression)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Hastayı fiziksel ve sözel olarak uyarın (sarsın, seslenin). 2. Havayolunu açın (Jaw thrust, oral airway). 3. %100 O₂ verin ve balon-maske ventilasyonu (Ambu) ile solunumu destekleyin. 4. Tüm opioid infüzyonlarını/dozlarını hemen durdurun.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. Stimulate the patient physically and verbally (shake, shout). 2. Open airway (Jaw thrust, oral airway). 3. Set FiO₂ to 100% and support ventilation with bag-valve-mask (BVM). 4. Immediately stop all opioid infusions/dosing.",
                    next = "q_naloxone"
                ),
                "q_naloxone" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada ventilasyon desteğine rağmen solunum düzelmedi veya solunum arresti tablosu devam ediyor mu?",
                    textEn = "Is there no improvement in respiration despite ventilatory support, or does respiratory arrest persist?",
                    options = listOf(
                        AlgorithmOption("Evet (Nalokson Endikasyonu)", "Yes (Naloxone Indicated)", "action_naloxone"),
                        AlgorithmOption("Hayır (Spontan solunum toparladı)", "No (Spontaneous respiration recovered)", "end")
                    )
                ),
                "action_naloxone" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 NALOKSON TEDAVİSİ: 1. Nalokson (0.4 mg ampulü 10 mL SF ile sulandırarak 0.04 mg yani 1 mL olacak şekilde enjektöre çekin). 2. 1-2 dakikada bir 0.04 mg (40 mcg) IV bolus şeklinde titre ederek uygulayın (ciddi akut ağrı ve sempatik krizi tetiklemekten kaçınmak için yavaş titre edin). 3. Damar yolu yoksa 0.4 mg IM uygulayın.",
                    textEn = "🚨 NALOXONE THERAPY: 1. Dilute Naloxone 0.4 mg ampoule in 10 mL saline to get 40 mcg/mL. 2. Administer 40 mcg (1 mL) IV bolus slowly every 1-2 min, titrating to effect (to avoid triggering severe acute pain and sympathetic crisis). 3. If no IV access, give 0.4 mg IM.",
                    next = "q_monitoring"
                ),
                "q_monitoring" to AlgorithmNode(
                    type = "question",
                    textTr = "Nalokson sonrası hastada yeterli solunum sağlandı mı?",
                    textEn = "Has adequate respiration been achieved after Naloxone administration?",
                    options = listOf(
                        AlgorithmOption("Evet (Solunum stabil)", "Yes (Respiration stable)", "action_watch_rebound"),
                        AlgorithmOption("Hayır (Dirençli depresyon)", "No (Refractory depression)", "action_intubate")
                    )
                ),
                "action_watch_rebound" to AlgorithmNode(
                    type = "action",
                    textTr = "Hasta uyandı. Ancak unutmayın: Nalokson yarı ömrü (30-90 dk) birçok opioidin (örn. Morfin, Fentanil) etkisinden kısadır. Rebound solunum depresyonuna karşı hastayı en az 2-4 saat yakından izleyin, gerekirse nalokson infüzyonu planlayın.",
                    textEn = "Patient woke up. But do not forget: Naloxone half-life (30-90 min) is shorter than many opioids (e.g., Morphine, Fentanyl). Monitor closely for rebound respiratory depression for at least 2-4 hours; consider infusion if needed.",
                    next = "end"
                ),
                "action_intubate" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 DİRENÇLİ ARREST / DEPRESYON: Gecikmeden hastayı entübe edin, mekanik ventilasyon desteğine başlayın ve yoğun bakım (ICU) transferi planlayın.",
                    textEn = "🚨 REFRACTORY ARREST / DEPRESSION: Intubate the patient without delay, start mechanical ventilation, and plan ICU transfer.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Solunum depresyonu kontrol altına alındı. OSA, yaşlılık, böbrek yetmezliği ve benzodiazepin kombinasyonu olan hastaların yüksek risk altında olduğunu unutmayın.",
                    textEn = "Respiratory depression controlled. Remember that OSA, advanced age, renal failure, and benzodiazepine combinations carry high risk.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "delayed_emergence_pacu",
            titleTr = "Derlenme Gecikmesi (PACU'da Uyanamayan Hasta)",
            titleEn = "Delayed Emergence / Unresponsive Patient in PACU",
            category = "critical",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Anestezi sonlandırılmasından sonraki 30-60 dakika içinde hastanın bilinç düzeyi yerine gelmedi veya uyandırılamıyor mu?",
                    textEn = "Has the patient failed to regain consciousness or wake up within 30-60 minutes after cessation of anesthesia?",
                    options = listOf(
                        AlgorithmOption("Evet (Uyanamayan Hasta)", "Yes (Failed to wake up)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK ADIMLAR: 1. Havayolu, solunum ve dolaşımı (ABC) güvenceye alın. 2. %100 O₂ uygulayın. 3. Yatak başı kapiller glukoz ölçün (hipoglisemiyi dışlayın). 4. Vücut sıcaklığını kontrol edin (ciddi hipotermiyi dışlayın). 5. Arteriyel kan gazı (ABG) analizi yapın.",
                    textEn = "🚨 INITIAL STEPS: 1. Secure airway, breathing, and circulation (ABC). 2. Administer 100% O₂. 3. Measure bedside capillary glucose (rule out hypoglycemia). 4. Measure body temperature (rule out severe hypothermia). 5. Obtain arterial blood gas (ABG) analysis.",
                    next = "q_abg_metabolic"
                ),
                "q_abg_metabolic" to AlgorithmNode(
                    type = "question",
                    textTr = "Kan şekeri, sıcaklık veya kan gazı değerlerinde (hiperkapni, asidoz, elektrolit bozukluğu) saptanan patoloji var mı?",
                    textEn = "Is there a pathology detected in glucose, temperature, or blood gas values (hypercapnia, acidosis, electrolyte imbalance)?",
                    options = listOf(
                        AlgorithmOption("Evet (Metabolik patoloji var)", "Yes (Metabolic pathology present)", "action_correct_metabolic"),
                        AlgorithmOption("Hayır (Metabolik değerler normal)", "No (Metabolic values normal)", "q_pharmacological")
                    )
                ),
                "action_correct_metabolic" to AlgorithmNode(
                    type = "action",
                    textTr = "Saptanan metabolik bozukluğu düzeltin: 1. Hipoglisemi ise IV Dekstroz uygulayın. 2. Hipotermi ise aktif ısıtma battaniyeleri kullanın. 3. Hiperkapni / Solunumsal asidoz varsa mekanik ventilasyon desteğiyle CO₂'yi düşürün. 4. Elektrolitleri optimize edin.",
                    textEn = "Correct the detected metabolic disorder: 1. If hypoglycemia, give IV Dextrose. 2. If hypothermia, use active warming blankets. 3. If hypercapnia / respiratory acidosis, support ventilation to blow off CO₂. 4. Optimize electrolytes.",
                    next = "end"
                ),
                "q_pharmacological" to AlgorithmNode(
                    type = "question",
                    textTr = "Farmakolojik veya nöromüsküler blok etkisi şüphesi var mı? (Rezidüel blok veya sedatif birikimi)",
                    textEn = "Is there a suspicion of pharmacological or neuromuscular blockade effect? (Residual block or sedative accumulation)",
                    options = listOf(
                        AlgorithmOption("Evet (İlaç / Blok etkisi var)", "Yes (Drug / Block effect present)", "action_reversal"),
                        AlgorithmOption("Hayır", "No", "q_neurological")
                    )
                ),
                "action_reversal" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 GERİ DÖNDÜRME TEDAVİSİ: 1. TOF monitörü ile rezidüel bloğu kontrol edin, gerekirse Sugammadeks veya Neostigmin uygulayın. 2. Opioid birikimi için Nalokson (40-80 mcg boluslarla titre ederek) uygulayın. 3. Benzodiazepin etkisi için Flumazenil (0.2 mg IV, maks 1 mg) uygulayın.",
                    textEn = "🚨 REVERSAL THERAPY: 1. Check residual block via TOF monitor, administer Sugammadex or Neostigmine if needed. 2. Titrate Naloxone (40-80 mcg boluses) for opioid accumulation. 3. Give Flumazenil (0.2 mg IV, max 1 mg) for benzodiazepines.",
                    next = "end"
                ),
                "q_neurological" to AlgorithmNode(
                    type = "question",
                    textTr = "Farmakolojik ve metabolik nedenler dışlandı. Hastada fokal nörolojik defisit, pupillerde asimetri veya ani fokal nörolojik bulgu var mı? (Kardiyovasküler inme veya intrakraniyal kanama şüphesi)",
                    textEn = "Pharmacological and metabolic causes ruled out. Does the patient have a focal neurological deficit, pupillary asymmetry, or sudden focal sign? (Stroke or intracranial bleed suspected)",
                    options = listOf(
                        AlgorithmOption("Evet (Nörolojik acil)", "Yes (Neurological emergency)", "action_neuro_imaging"),
                        AlgorithmOption("Hayır (Yakın takibe devam)", "No (Continue close monitoring)", "end")
                    )
                ),
                "action_neuro_imaging" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 NÖROLOJİK ACİL: Derhal nöroloji/beyin cerrahisi konsültasyonu isteyin. Solunumu koruyun ve acil beyin BT / MRG görüntülemesi planlayın. Yoğun bakım ünitesine transfer edin.",
                    textEn = "🚨 NEUROLOGICAL EMERGENCY: Immediately request neurology/neurosurgery consult. Protect respiration and schedule urgent brain CT / MRI imaging. Transfer to the ICU.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Uyanma gecikmesi yönetimi tamamlandı. Hastayı toparlayana kadar PACU'da monitorize etmeye devam edin. Unutma: Beklenmeyen uyanamama durumlarında glukoz ve TOF kontrolü ilk yapılması gereken adımlardır.",
                    textEn = "Delayed emergence management completed. Monitor the patient in the PACU until recovery. Do not forget: Capillary glucose and TOF assessment are the first critical steps in unexpected delayed emergence.",
                    warningLevel = "moderate"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "medication_error_response",
            titleTr = "Perioperatif İlaç Hatası Yönetimi",
            titleEn = "Perioperative Medication Error / Wrong Drug Response",
            category = "drugs",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Ameliyathanede yanlış hastaya, yanlış ilaç, yanlış doz, yanlış konsantrasyon veya yanlış yolla ilaç uygulandığı fark edildi mi?",
                    textEn = "In the OR, was a medication administered to the wrong patient, or was the wrong drug, dose, concentration, or route given?",
                    options = listOf(
                        AlgorithmOption("Evet (İlaç Hatası Mevcut)", "Yes (Medication Error Present)", "action_initial"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 İLK MÜDAHALE: 1. Şüpheli ilaç enjeksiyonunu/infüzyonunu DERHAL durdurun. 2. Hastayı stabilize edin: Solunum, dolaşım ve havayolu (ABC) güvenliğini sağlayın, %100 O₂ başlayın. 3. Kıdemli hekim çağırın ve cerrahı bilgilendirin.",
                    textEn = "🚨 IMMEDIATE ACTIONS: 1. IMMEDIATELY stop the suspected drug injection/infusion. 2. Stabilize the patient: Secure airway, breathing, and circulation (ABC) and start 100% O₂. 3. Call for a senior physician and inform the surgeon.",
                    next = "q_specific_antidote"
                ),
                "q_specific_antidote" to AlgorithmNode(
                    type = "question",
                    textTr = "Hatalı verilen ilacın bilinen spesifik bir antidotu veya geri döndürücü ajanı var mı? (Örn. Opioid için Nalokson, kas gevşetici için Sugammadeks, heparin için Protamin)",
                    textEn = "Does the erroneously administered drug have a known specific antidote or reversal agent? (e.g., Naloxone for opioids, Sugammadex for paralytics, Protamine for heparin)",
                    options = listOf(
                        AlgorithmOption("Evet (Antidotu uygula)", "Yes (Apply antidote)", "action_antidote"),
                        AlgorithmOption("Hayır (Destekleyici tedavi uygulayın)", "No (Apply supportive care)", "action_supportive")
                    )
                ),
                "action_antidote" to AlgorithmNode(
                    type = "action",
                    textTr = "Spesifik antidotu / geri döndürücü ajanı gecikmeden uygulayın. İlacın farmakolojik yan etkilerini (örn. anafilaksi, lokal anestezik toksisitesi) ve hastanın yaşamsal bulgularını yakından izleyin.",
                    textEn = "Administer the specific antidote / reversal agent without delay. Closely monitor for pharmacological adverse effects of the drug (e.g., anaphylaxis, LAST) and the patient's vital signs.",
                    next = "end"
                ),
                "action_supportive" to AlgorithmNode(
                    type = "action",
                    textTr = "Spesifik antidot yok. Agresif destekleyici ve semptomatik tedaviye başlayın: 1. Hipotansiyon için sıvı ve vazopressör desteği. 2. Aritmi veya kardiyak depresyon için ACLS adımlarını uygulayın. 3. Gerekirse mekanik ventilasyon desteği verin.",
                    textEn = "No specific antidote. Apply aggressive supportive and symptomatic care: 1. Fluids and vasopressors for hypotension. 2. Apply ACLS algorithms for arrhythmias or cardiac depression. 3. Provide mechanical ventilation support if needed.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Hasta stabilize edildi. Klinik hatayı şeffaf bir şekilde anestezi dosyasına belgeleyin. Ekip bilgilendirmesi (debriefing) yapın ve kurumsal hasta güvenliği komitesine (defansif olmadan, sistem iyileştirme odaklı) hata bildiriminde bulunun.",
                    textEn = "Patient stabilized. Transparently document the clinical error in the anesthesia record. Perform a team debriefing and report the error to the institutional patient safety committee focusing on system improvement, not defensive posturing.",
                    warningLevel = "moderate"
                )
            ),
            requiresClinicalValidation = false
        ),
)
}"""

    # We will replace the closing target with the new algorithms and close the file
    new_content = content.replace(target, new_algs_kotlin)
    
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(new_content)
        
    print("SUCCESS: SeedDataAlgorithms.kt has been updated with the 10 new algorithms.")

if __name__ == "__main__":
    main()
