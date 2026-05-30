package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.*

object SeedDataAlgorithms {

    val algorithmsList = listOf(
        Algorithm(
            algorithmId = "asa_ps_evaluation",
            titleTr = "ASA Fiziksel Durum Sınıflaması",
            titleEn = "ASA Physical Status Classification",
            category = "scores",
            urgencyLevel = "elective",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada sistemik bir hastalık veya organ disfonksiyonu mevcut mu?",
                    textEn = "Does the patient have a systemic disease or organ dysfunction?",
                    options = listOf(
                        AlgorithmOption("Hayır (Sistemik hastalık yok)", "No (No systemic disease)", "q_lifestyle"),
                        AlgorithmOption("Evet (Sistemik hastalık var)", "Yes (Systemic disease present)", "q2")
                    )
                ),
                "q_lifestyle" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta gebe mi, aktif sigara kullanıcısı mı veya sosyal alkol tüketicisi mi?",
                    textEn = "Is the patient pregnant, a current smoker, or a social alcohol drinker?",
                    options = listOf(
                        AlgorithmOption("Evet (Biri veya daha fazlası mevcut)", "Yes (One or more present)", "asa2"),
                        AlgorithmOption("Hayır (Hiçbiri)", "No (None)", "asa1")
                    )
                ),
                "q2" to AlgorithmNode(
                    type = "question",
                    textTr = "Sistemik hastalık günlük aktivitelerde fonksiyonel kısıtlılığa yol açıyor mu?",
                    options = listOf(
                        AlgorithmOption("Hayır (Fonksiyonel kısıtlama yok)", "No", "asa2"),
                        AlgorithmOption("Evet (Fonksiyonel kısıtlama var)", "Yes", "q3")
                    )
                ),
                "q3" to AlgorithmNode(
                    type = "question",
                    textTr = "Sistemik hastalık hayatı sürekli tehdit eden bir durum yaratıyor mu?",
                    options = listOf(
                        AlgorithmOption("Hayır (Ciddi ama sabit hastalık)", "No", "asa3"),
                        AlgorithmOption("Evet (Hayatı tehdit ediyor)", "Yes", "q4")
                    )
                ),
                "q4" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta ameliyat olsa da olmasa da 24 saat içinde yaşamını yitirmesi beklenen bir durumda mı?",
                    options = listOf(
                        AlgorithmOption("Hayır (Kritik düzey)", "No", "asa4"),
                        AlgorithmOption("Evet (Ölümcül moribund)", "Yes", "asa5")
                    )
                ),
                "asa1" to AlgorithmNode(
                    type = "end",
                    textTr = "ASA I: Tamamen sağlıklı, sigara içmeyen, minimal alkol tüketen birey. Elektif cerrahi için anestezi riski minimumdur.",
                    warningLevel = "none"
                ),
                "asa2" to AlgorithmNode(
                    type = "end",
                    textTr = "ASA II: Hafif sistemik hastalık (örn. kontrol altında HT/DM, sigara içimi, gebelik, hafif obezite). Günlük aktiviteler kısıtlanmamıştır.",
                    warningLevel = "moderate"
                ),
                "asa3" to AlgorithmNode(
                    type = "end",
                    textTr = "ASA III: Belirgin fonksiyonel kısıtlılık yapan ciddi sistemik hastalık (örn. kontrolsüz DM/HT, KOAH, morbid obezite VKİ >= 40, pacemaker, son 3 ayda geçirilmiş MI/İnme).",
                    warningLevel = "moderate"
                ),
                "asa4" to AlgorithmNode(
                    type = "end",
                    textTr = "🚨 ASA IV: Hayatı sürekli tehdit eden ciddi sistemik hastalık (örn. <3 ay MI/SVO, kararsız angina, ciddi kapak yetmezliği, sepsis, ARDS, son evre böbrek yetmezliği). Yakın hemodinamik takip gereklidir.",
                    warningLevel = "critical"
                ),
                "asa5" to AlgorithmNode(
                    type = "end",
                    textTr = "🚨 ASA V: Cerrahi müdahale yapılsa da yapılmasa da 24 saat içinde ölmesi beklenen moribund hasta (örn. rüptüre aort anevrizması, masif kafa travması, çoklu organ yetmezliği).",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "preop_npo_evaluation",
            titleTr = "Preoperatif NPO / Açlık Değerlendirmesi",
            titleEn = "Preoperative NPO / Fasting Evaluation",
            category = "fluids",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta elektif şartlarda mı ameliyata alınıyor yoksa acil cerrahi mi planlanıyor?",
                    options = listOf(
                        AlgorithmOption("Elektif Cerrahi", "Elective", "q2"),
                        AlgorithmOption("Acil Cerrahi (Yüksek Apirasyon Riski)", "Emergency", "rsi_needed")
                    )
                ),
                "q2" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastanın en son aldığı gıda/sıvı tipi nedir?",
                    options = listOf(
                        AlgorithmOption("Berrak Sıvılar (Su, çay, posasız meyve suyu)", "Clear Liquids", "npo2"),
                        AlgorithmOption("Anne Sütü", "Breast Milk", "npo4"),
                        AlgorithmOption("Formül mama / İnek sütü / Hafif öğün", "Formula / Light Meal", "npo6"),
                        AlgorithmOption("Yağlı / Ağır katı gıdalar", "Fatty Meal", "npo8")
                    )
                ),
                "npo2" to AlgorithmNode(
                    type = "end",
                    textTr = "Berrak sıvılar için gereken minimum açlık süresi 2 saattir. Süre tamamlandıysa anestezi indüksiyonu uygundur.",
                    warningLevel = "none"
                ),
                "npo4" to AlgorithmNode(
                    type = "end",
                    textTr = "Anne sütü için gereken minimum açlık süresi 4 saattir. Bu süre dolmadan elektif girişim ertelenmelidir.",
                    warningLevel = "none"
                ),
                "npo6" to AlgorithmNode(
                    type = "end",
                    textTr = "Bebek formül mamaları, hayvansal sütler ve hafif karbonhidratlı öğünler (örn. tost, çay) için gereken açlık süresi minimum 6 saattir.",
                    warningLevel = "moderate"
                ),
                "npo8" to AlgorithmNode(
                    type = "end",
                    textTr = "🚨 Yağlı gıdalar, kızartmalar veya et içeren ağır katı öğünler için gereken açlık süresi minimum 8 saattir. Mide boşalması geciken hastalarda bu süre daha da uzatılabilir.",
                    warningLevel = "moderate"
                ),
                "rsi_needed" to AlgorithmNode(
                    type = "end",
                    textTr = "🚨 ACİL CERRAHİ UYARISI: Hasta tam aç kabul edilemez! Elektif açlık süreleri beklenemiyorsa, aspirasyon profilaksisi (antiasit + prokinetik) uygulayın ve entübasyonda Hızlı Ardışık İndüksiyon (RSI) ve krikoid bası (Sellick manevrası) uygulayın.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "stop_bang_osas",
            titleTr = "STOP-Bang / OSAS Risk Taraması",
            titleEn = "STOP-Bang / OSAS Risk Screening",
            category = "scores",
            urgencyLevel = "elective",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "STOP parametreleri sorgulanıyor: \n1. Uyurken yüksek sesle horlama (Snoring)? \n2. Gün içinde yorgunluk/uyuklama (Tiredness)? \n3. Uyku sırasında nefes durması gözlemlendi mi (Observed apnea)? \n4. Yüksek tansiyon (Pressure) tedavisi alıyor mu?",
                    options = listOf(
                        AlgorithmOption("0-1 Evet", "0-1 Yes", "q2_low"),
                        AlgorithmOption("2 veya daha fazla Evet", "2+ Yes", "q2_high")
                    )
                ),
                "q2_low" to AlgorithmNode(
                    type = "question",
                    textTr = "Bang parametreleri sorgulanıyor: \n5. VKİ >35 kg/m² mi? \n6. Yaş >50 mi? \n7. Boyun çevresi >40 cm mi? \n8. Cinsiyet erkek mi?",
                    options = listOf(
                        AlgorithmOption("Toplam skor 0 - 2 (Evet sayısı)", "Total 0-2", "low_risk"),
                        AlgorithmOption("Toplam skor 3 - 4 (Evet sayısı)", "Total 3-4", "intermediate_risk")
                    )
                ),
                "q2_high" to AlgorithmNode(
                    type = "question",
                    textTr = "Bang parametreleri sorgulanıyor: \n5. VKİ >35 kg/m² mi? \n6. Yaş >50 mi? \n7. Boyun çevresi >40 cm mi? \n8. Cinsiyet erkek mi?",
                    options = listOf(
                        AlgorithmOption("Toplam skor 3 - 4 (Evet sayısı)", "Total 3-4", "intermediate_risk"),
                        AlgorithmOption("Toplam skor 5 veya daha fazla Evet", "Total 5+", "high_risk")
                    )
                ),
                "low_risk" to AlgorithmNode(
                    type = "end",
                    textTr = "Düşük Obstrüktif Uyku Apnesi (OSAS) Riski: Ek anestezi hazırlığına gerek yoktur. Standart perioperatif takip yeterlidir.",
                    warningLevel = "none"
                ),
                "intermediate_risk" to AlgorithmNode(
                    type = "end",
                    textTr = "⚠️ Orta OSAS Riski: Postoperatif solunum depresyonu riskine karşı dikkatli olunmalıdır. Rejyonel anestezi imkan varsa genel anesteziye tercih edilmelidir. Opioid dozları sınırlandırılmalıdır.",
                    warningLevel = "moderate"
                ),
                "high_risk" to AlgorithmNode(
                    type = "end",
                    textTr = "🚨 YÜKSEK OSAS RİSKİ: Postoperatif dönemde solunum obstrüksiyonu, desatürasyon ve aritmi riski çok yüksektir! \n1. Entübasyonda zor havayolu hazırlığı yapın. \n2. Ekstübasyonu hasta tamamen uyanık ve yarı oturur pozisyonda gerçekleştirin. \n3. Postoperatif dönemde hastayı yakından izleyin, gerekirse CPAP/BiPAP desteğini hazır bulundurun.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "preop_cardiac_rcri",
            titleTr = "Revised Cardiac Risk Index (RCRI)",
            titleEn = "Revised Cardiac Risk Index (RCRI)",
            category = "scores",
            urgencyLevel = "elective",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada kaç adet RCRI risk faktörü mevcut? \n(1. Yüksek riskli cerrahi, 2. İskemik kalp hastalığı öyküsü, 3. Konjestif kalp yetmezliği öyküsü, 4. Serebrovasküler hastalık öyküsü, 5. İnsülin kullanan DM, 6. Preoperatif kreatinin >2.0 mg/dL)",
                    options = listOf(
                        AlgorithmOption("0 Risk Faktörü", "0 Factors", "rcri_class_1"),
                        AlgorithmOption("1 Risk Faktörü", "1 Factor", "rcri_class_2"),
                        AlgorithmOption("2 Risk Faktörü", "2 Factors", "rcri_class_3"),
                        AlgorithmOption("3 veya daha fazla Faktör", "3+ Factors", "rcri_class_4")
                    )
                ),
                "rcri_class_1" to AlgorithmNode(
                    type = "action",
                    textTr = "RCRI Sınıf I (0 Puan): Major kardiyak olay riski %0.4. Ek kardiyak tetkik veya araştırma yapılmasına gerek yoktur. Standart anestezi protokolü ile devam edebilirsiniz.",
                    warningLevel = "none",
                    next = "end"
                ),
                "rcri_class_2" to AlgorithmNode(
                    type = "action",
                    textTr = "RCRI Sınıf II (1 Puan): Major kardiyak olay riski %1.0. Hastanın fonksiyonel kapasitesi (METS >= 4) yeterli ise doğrudan cerrahiye geçilebilir. Yetersiz ise EKG ve kardiyoloji konsültasyonu önerilir.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "rcri_class_3" to AlgorithmNode(
                    type = "action",
                    textTr = "RCRI Sınıf III (2 Puan): Major kardiyak olay riski %2.4. Ameliyat öncesi EKG ve Transtorasik Ekokardiyografi (TTE) ile sol ventrikül fonksiyon değerlendirmesi önerilir. Beta bloker ve statin tedavisi perioperatif devam edilmelidir.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "rcri_class_4" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 RCRI Sınıf IV (>=3 Puan): Major kardiyak olay riski %5.4! Cerrahi erteleme veya kardiyoloji tarafından revaskülarizasyon (PCI, CABG) ihtiyacının değerlendirilmesi açısından kapsamlı konsültasyon şarttır. İntraoperatif invaziv arteriyel monitörizasyon önerilir.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Değerlendirme tamamlandı. Klinik risk analizini anestezi preoperatif formuna kaydedin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "coagulation_management",
            titleTr = "Preoperatif Antikoagülan / Antiagregan Yönetimi",
            titleEn = "Preoperative Anticoagulant / Antiplatelet Management",
            category = "drugs",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta ne tür bir kan sulandırıcı kullanıyor?",
                    options = listOf(
                        AlgorithmOption("Antiagregan (Aspirin, Klopidogrel, Ticagrelor)", "Antiplatelet", "q_antiplatelet"),
                        AlgorithmOption("Oral Antikoagülan (Warfarin, Apixaban, Rivaroxaban)", "Anticoagulant", "q_anticoagulant"),
                        AlgorithmOption("LMWH / Heparin (Clexane vb.)", "LMWH / Heparin", "q_heparin")
                    )
                ),
                "q_antiplatelet" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta koroner stent taşıyor mu ve çiftli antiplatelet tedavisi (DAPT) altında mı?",
                    options = listOf(
                        AlgorithmOption("Evet (Koroner stentli, DAPT altında)", "Yes (Stent, DAPT)", "stent_alert"),
                        AlgorithmOption("Hayır (Profilaktik veya tekli kullanım)", "No (Single agent)", "single_antiplatelet_guideline")
                    )
                ),
                "stent_alert" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 KRİTİK KORONER RİSK: Kardiyoloji onayı olmadan antiplatelet ilaçları (özellikle klopidogrel/ticagrelor) asla kesilmemelidir! Elektif cerrahi, stentin takılma tarihine göre (çıplak metal stent için en az 1 ay, ilaç salınımlı stent için en az 6 ay) ertelenmelidir. Cerrahi çok acil ise aspirin devam ettirilerek operasyona girilir.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "single_antiplatelet_guideline" to AlgorithmNode(
                    type = "action",
                    textTr = "Tekli ilaç yönetimi: \n1. Aspirin: Genellikle kesilmesine gerek yoktur (beyin/omurilik ve prostat cerrahileri hariç). \n2. Klopidogrel (Plavix): Ameliyattan 5-7 gün önce kesilmelidir. \n3. Ticagrelor (Brilinta): Ameliyattan 5 gün önce kesilmelidir.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "q_anticoagulant" to AlgorithmNode(
                    type = "question",
                    textTr = "İlaç tipi nedir?",
                    options = listOf(
                        AlgorithmOption("Warfarin (Coumadin)", "Warfarin", "warfarin_guideline"),
                        AlgorithmOption("DOAC (Apixaban, Rivaroxaban, Dabigatran)", "DOAC", "doac_guideline")
                    )
                ),
                "warfarin_guideline" to AlgorithmNode(
                    type = "action",
                    textTr = "Warfarin (Coumadin) yönetimi: \n1. Ameliyattan 5 gün önce kesilmelidir. \n2. Yüksek tromboemboli riski olan hastalarda (metalik kalp kapağı, atriyal fibrilasyon) LMWH ile köprüleme (bridging) tedavisi başlanmalıdır. \n3. Ameliyat sabahı INR kontrol edilmeli ve INR < 1.5 ise cerrahiye izin verilmelidir.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "doac_guideline" to AlgorithmNode(
                    type = "action",
                    textTr = "Yeni Nesil Oral Antikoagülan (DOAC) yönetimi: \n1. Cerrahi kanama riskine ve böbrek fonksiyonuna (kreatinin klirensi) göre kesilmelidir. \n2. Düşük kanama riskli cerrahide: 24-48 saat önce kesilir. \n3. Yüksek kanama riskli cerrahide (örneğin rejyonel bloklar): Ameliyattan 72 saat önce kesilmelidir. Köprüleme tedavisi genellikle önerilmez.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "q_heparin" to AlgorithmNode(
                    type = "action",
                    textTr = "LMWH ve Heparin yönetimi: \n1. Profilaktik LMWH (örn. Clexane 4000 IU): Bloktan/ameliyattan en az 12 saat önce durdurulmalıdır. \n2. Terapötik LMWH (örn. Clexane 2x0.6 mL veya 1x120 mg): Ameliyattan en az 24 saat önce durdurulmalıdır. \n3. IV Standart Heparin: İnfüzyon ameliyattan 4-6 saat önce kesilmeli, aPTT kontrol edilerek normale döndüğü teyit edilmelidir.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Antikoagülan yönetimi tamamlandı. ASRA 2025 kılavuzuna göre blok öncesi ve kateter çekme sonrası bekleme sürelerine harfiyen uyunuz.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "expected_difficult_airway",
            titleTr = "Zor Havayolu Algoritması (Beklenen)",
            titleEn = "Expected Difficult Airway Algorithm",
            category = "airway",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Preoperatif muayenede zor havayolu bulguları saptandı (Mallampati Class 4, TMD <6cm, obezite). Temel strateji ne olmalıdır?",
                    options = listOf(
                        AlgorithmOption("Uyanık Entübasyon (Awake Intubation)", "Awake Intubation", "awake_intubation_step"),
                        AlgorithmOption("Genel Anestezi Altında Entübasyon", "Intubation Under GA", "ga_difficult_prep")
                    )
                ),
                "awake_intubation_step" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 UYANIK FİBEROPTİK ENTÜBASYON (Altın Standart): \n1. Hastaya detaylı bilgi verin ve onayını alın. \n2. Üst solunum yoluna lokal anestezi (lidokain sprey, bloklar) uygulayın. \n3. Sedasyon için hafif doz remifentanil infüzyonu veya dexmedetomidine planlayın (solunumu baskılamadan). \n4. Fiberoptik bronkoskop (FOB) veya videolaringoskop yardımıyla uyanık entübasyonu gerçekleştirin ve tüp yerini kapnografiyle doğrulayıp ardından indüksiyon yapın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "ga_difficult_prep" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ GENEL ANESTEZİ ALTINDA ENTEGRAL YÖNETİM: \n1. En az bir deneyimli anestezi uzmanı hazır bulundurulmalıdır. \n2. Videolaringoskop (VL), Bougie, LMA ve acil krikotirotomi setini başucunda hazır bulundurun. \n3. Hastayı 3-5 dakika boyunca %100 O2 ile preoksijenize edin. \n4. Kısa etkili indüksiyon ajanları seçin. Havayolunu güvene alana kadar hastanın spontan solunumunu korumayı düşünebilirsiniz.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Havayolu güvenliği sağlandıktan sonra operasyona başlayın. Ekstübasyon planını da mutlaka zor ekstübasyon algoritmasına göre yapın.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "unexpected_difficult_intubation",
            titleTr = "Beklenmeyen Zor Entübasyon",
            titleEn = "Unexpected Difficult Intubation",
            category = "airway",
            urgencyLevel = "emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Laringoskopi yapıldı ancak vokal kordlar görüntülenemedi (Cormack-Lehane Grade 3-4). Maske ventilasyonu yeterli mi?",
                    options = listOf(
                        AlgorithmOption("Evet (Maskeleniyor)", "Yes", "action_call_help"),
                        AlgorithmOption("Hayır (CICO Şüphesi)", "No", "cico_action")
                    )
                ),
                "action_call_help" to AlgorithmNode(
                    type = "action",
                    textTr = "Kıdemli hekim ve zor havayolu arabasını çağırın. O2 düzeyini %100 tutun. Pozisyonu düzeltin (Sniffing), harici laringeal manipülasyon (BURP) uygulayın. Alternatif bıçak (Macintosh/Miller) veya videolaringoskop (VL) kullanın.",
                    warningLevel = "moderate",
                    next = "q_second_attempt"
                ),
                "q_second_attempt" to AlgorithmNode(
                    type = "question",
                    textTr = "Alternatif teknikle (VL, Bougie, Stylus vb.) ikinci entübasyon denemesi başarılı oldu mu?",
                    options = listOf(
                        AlgorithmOption("Evet (Başarılı)", "Yes", "intubated_success"),
                        AlgorithmOption("Hayır (Yine Başarısız)", "No", "q_sga_try")
                    )
                ),
                "q_sga_try" to AlgorithmNode(
                    type = "question",
                    textTr = "İkinci deneme de başarısız. Supraglottik Havayolu Aygıtı (SGA/LMA) yerleştirilmesi kararı. SGA başarıyla yerleştirildi ve ventilasyon sağlandı mı?",
                    options = listOf(
                        AlgorithmOption("Evet (SGA ile ventilasyon OK)", "Yes", "action_sga_vent"),
                        AlgorithmOption("Hayır (SGA başarısız)", "No", "cico_action")
                    )
                ),
                "action_sga_vent" to AlgorithmNode(
                    type = "action",
                    textTr = "SGA ile ventilasyon sağlanıyor. Bu aşamada acil cerrahi değilse hastayı uyandırın, rejyonel anesteziye geçin veya SGA üzerinden fiberoptik yardımıyla entübasyon planlayın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "cico_action" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 CANNOT INTUBATE, CANNOT OXYGENATE (CICO) DURUMU! Doğrudan CICO algoritmasına geçin ve acil front-of-neck access hazırlığı yapın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "intubated_success" to AlgorithmNode(
                    type = "end",
                    textTr = "Entübasyon başarılı! Kapnografi dalga formunu görün, kafı şişirin, her iki akciğeri dinleyin ve tüpü sabitleyin.",
                    warningLevel = "none"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Algoritma tamamlandı. Yakın SpO2 ve hemodinamik takibe devam edin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "cico_crisis",
            titleTr = "Cannot Intubate Cannot Oxygenate (CICO)",
            titleEn = "Cannot Intubate Cannot Oxygenate (CICO)",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada entübasyon başarısız oldu, SGA yerleştirilemedi, maske ventilasyonu imkansız ve SpO₂ hızla düşüyor. CICO ilan edildi mi?",
                    options = listOf(
                        AlgorithmOption("Evet (Acil CICO)", "Yes", "action_cico_call"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_cico_call" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL CICO ALARMI! Kıdemli anestezi hekimi ve cerrah çağırın. Anestezi derinliğini koruyun, kas gevşetici düzeyini kontrol edin, çene itme (Jaw thrust) manevrasını uygulayın.",
                    warningLevel = "critical",
                    next = "q_airway_access"
                ),
                "q_airway_access" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastanın boyun anatomisi palpasyonla krikotiroid membran tespiti için uygun mu?",
                    options = listOf(
                        AlgorithmOption("Evet (Anatomi Belirgin)", "Yes", "action_needle_cric"),
                        AlgorithmOption("Hayır (Obez/Zor)", "No", "action_scalpel_bougie")
                    )
                ),
                "action_needle_cric" to AlgorithmNode(
                    type = "action",
                    textTr = "İğne Krikotirotomi (Needle Cricothyroidotomy) veya hazır perkütan krikotirotomi seti kullanın. Kateteri yerleştirip jet ventilatör veya düşük basınçlı O2 ile ventilasyon sağlayın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "action_scalpel_bougie" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 NEŞTER-BOUGIE-TÜP TEKNİĞİ: 1. Krikotiroid membrandan yatay insizyon yapın. 2. Parmakla veya klemple yolu açın. 3. Bougie'yi trakeye yönlendirin. 4. Bougie üzerinden 6.0 size kaflı tüpü kaydırıp yerleştirin, kafı şişirip ventilasyonu başlatın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Resüsitasyon adımları tamamlandı. Acil yoğun bakım transferi ve KBB/Cerrahi konsültasyonu planlayın.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "laryngospasm_crisis",
            titleTr = "Perioperatif Laringospazm",
            titleEn = "Perioperative Laryngospasm",
            category = "airway",
            urgencyLevel = "emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Ekstübasyon veya indüksiyon esnasında ani stridor, laringeal seslerin kaybolması, paradoksik göğüs hareketleri ve SpO₂ düşüşü var mı?",
                    options = listOf(
                        AlgorithmOption("Evet (Laringospazm)", "Yes", "action_initial_maneuver"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_initial_maneuver" to AlgorithmNode(
                    type = "action",
                    textTr = "1. Uyarımı hemen durdurun. 2. Çene itme (Jaw thrust) manevrası ile birlikte Larson Noktasına (kulak memesi arkası mastoid çıkıntı çukuru) çift taraflı derin parmak basıncı uygulayın. 3. 100% O₂ ile maskeden 10-15 cmH₂O PEEP/CPAP uygulayarak ventilasyon yapmayı deneyin.",
                    warningLevel = "critical",
                    next = "q_spasm_resolved"
                ),
                "q_spasm_resolved" to AlgorithmNode(
                    type = "question",
                    textTr = "Larson manevrası ve pozitif basınçlı maske ventilasyonu ile spazm çözüldü mn?",
                    options = listOf(
                        AlgorithmOption("Evet (Çözüldü)", "Yes", "end"),
                        AlgorithmOption("Hayır (Tam Obstrüksiyon/Ciddi Hipoksi)", "No", "action_medication")
                    )
                ),
                "action_medication" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 SİSTEMİK EYLEM: 1. Derin anestezi için Propofol bolus (0.5-1.0 mg/kg IV) uygulayın. 2. Eğer spazm çözülmezse ve SpO₂ < 85% ise kas gevşetici Süksinilkolin (0.5-1.0 mg/kg IV veya damar yolu yoksa 3-4 mg/kg IM) uygulayın. 3. Spazm çözülünce entübe edin veya toparlayınca uyandırın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Spazm çözüldü. Negatif basınçlı akciğer ödemi riskine karşı hastayı yakından takip edin ve SpO2 stabil olana kadar PACU'da tutun.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "intraoperative_hypoxemia",
            titleTr = "İntraoperatif Hipoksemi Algoritması",
            titleEn = "Intraoperative Hypoxemia Algorithm",
            category = "airway",
            urgencyLevel = "emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Anestezi sırasında hastada SpO₂ < 90% veya PaO₂ < 60 mmHg saptandı. İlk eylem nedir?",
                    options = listOf(
                        AlgorithmOption("%100 FiO₂ ver ve Manuel Ventilasyona geç", "100% O2 & Manual", "q_vent_resistance")
                    )
                ),
                "q_vent_resistance" to AlgorithmNode(
                    type = "question",
                    textTr = "Balon ile manuel ventilasyon yaparken havayolu direnci nasıl? Göğüs kalkıyor mu?",
                    options = listOf(
                        AlgorithmOption("Göğüs kalkıyor, direnç normal (Oksijen yetersizliği)", "Normal resistance", "check_fio2_circuit"),
                        AlgorithmOption("Yüksek direnç var, göğüs kalkmıyor (Obstrüksiyon/Spazm)", "High resistance", "check_obstruciton")
                    )
                ),
                "check_fio2_circuit" to AlgorithmNode(
                    type = "action",
                    textTr = "1. Ventilatör devresi ve O2 kaynağını denetleyin (O2 akışı açık mı?). \n2. Endotrakeal tüpün yerini kapnografi dalgasıyla kontrol edin (tüp kaymış veya tek akciğer entübasyonu yapılmış olabilir). \n3. Akciğerleri dinleyin, atelektazi şüphesi varsa alveoler rekrutman manevrası uygulayın ve PEEP artırın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "check_obstruciton" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 HAVAYOLU ENGELLERİ DEĞERLENDİRİLMESİ: \n1. Tüpü katlanma, sekresyon veya ısırma açısından kontrol edin (oral airway yerleştirin, kafı kontrol edin). \n2. Masif bronkospazm yönünden akciğerleri oskülte edin, gerekirse inhale bronkodilatör verin. \n3. Laringospazm şüphesi varsa derhal Larson manevrası yapın ve gerekirse Süksinilkolin uygulayın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Hipoksemi kontrol altına alındı. FiO₂ düzeyini hastanın fizyolojik ihtiyacına göre (%30-40) kademeli olarak düşürün. Atelektazi gelişimini önlemek için koruyucu ventilasyon yapın.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "perioperative_hypotension",
            titleTr = "Perioperatif Hipotansiyon",
            titleEn = "Perioperative Hypotension",
            category = "hemodynamics",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Ortalama Arter Basıncı (OAB / MAP) <65 mmHg veya hastanın bazal değerine göre >%20 düşüş var mı?",
                    textEn = "Is MAP <65 mmHg or >20% below baseline?",
                    options = listOf(
                        AlgorithmOption("Evet", "Yes", "q2"),
                        AlgorithmOption("Hayır", "No", "monitor")
                    )
                ),
                "q2" to AlgorithmNode(
                    type = "question",
                    textTr = "Hipotansiyon anestezi indüksiyonundan hemen sonra mı gelişti?",
                    textEn = "Did hypotension occur immediately after induction?",
                    options = listOf(
                        AlgorithmOption("Evet (İndüksiyon ilişkili)", "Yes", "induction_cause"),
                        AlgorithmOption("Hayır (İntraoperatif süreçte)", "No", "explore_causes")
                    )
                ),
                "induction_cause" to AlgorithmNode(
                    type = "action",
                    textTr = "Muhtemel sebep anestezik ajanların yol açtığı vazodilatasyon ve miyokard depresyonudur. Öncelikle anestezi derinliğini (MAC) azaltın, hızlıca 250-500 mL kristalloid yüklemesi yapın. Eşlik eden nabız durumuna göre Efedrin (taşikardi yoksa) veya Fenilefrin (taşikardi varsa) uygulayın.",
                    relatedDrugs = listOf("ephedrine", "phenylephrine", "norepinephrine"),
                    relatedCalculators = listOf("map", "maintenance_421"),
                    warningLevel = "moderate",
                    next = "q3"
                ),
                "explore_causes" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada aktif kanama, masif sıvı kaybı veya hipovolemi belirtileri var mı?",
                    options = listOf(
                        AlgorithmOption("Evet (Hipovolemik)", "Yes", "hypovolemic_tx"),
                        AlgorithmOption("Hayır (Kardiyojenik veya Dağılımsal şüphe)", "No", "cardiac_shock")
                    )
                ),
                "hypovolemic_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "Kanama kontrolünü cerrahi ekipten isteyin. Hızlı sıvı resüsitasyonuna başlayın. İzin verilebilir kan kaybını (ABL) hesaplayarak kan transfüzyon endikasyonunu değerlendirin. Gerekirse geçici hemodinamik stabilite sağlamak için düşük doz Noradrenalin infüzyonu bağlayın.",
                    relatedDrugs = listOf("norepinephrine"),
                    relatedCalculators = listOf("ebv_abl"),
                    warningLevel = "critical",
                    next = "q3"
                ),
                "cardiac_shock" to AlgorithmNode(
                    type = "action",
                    textTr = "Kardiyak disfonksiyon (infarktüs, kapak hastalığı), emboli veya sepsis yönünden hastayı değerlendirin. EKG ritmini ve ST segment değişikliklerini kontrol edin. Ciddi vakalarda inotropik destek (Dobutamin) ve acil kardiyoloji konsültasyonu düşünün.",
                    relatedDrugs = listOf("norepinephrine"),
                    warningLevel = "critical",
                    next = "end"
                ),
                "q3" to AlgorithmNode(
                    type = "question",
                    textTr = "Tedaviye rağmen OAB >65 mmHg düzeyine ulaştı mı?",
                    options = listOf(
                        AlgorithmOption("Evet (Stabil)", "Yes", "monitor"),
                        AlgorithmOption("Hayır (Dirençli Hipotansiyon)", "No", "refractory")
                    )
                ),
                "refractory" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL ESKALASYON: Dirençli hipotansiyon! Noradrenalin infüzyon hızını artırın. Anafilaksi belirtilerini (döküntü, hava yolu basınç artışı) kontrol edin. LAST (Lokal Anestezik Toksisitesi) şüphesi varsa Lipid infüzyonunu başlatın. Kıdemli anestezi uzmanı ve ek yardım çağırın.",
                    relatedDrugs = listOf("sugammadex"),
                    warningLevel = "critical",
                    next = "end"
                ),
                "monitor" to AlgorithmNode(
                    type = "end",
                    textTr = "Hasta stabil durumda. Yakın hemodinamik ve EKG monitörizasyonuna devam edin. Sıvı dengesini koruyun.",
                    warningLevel = "none"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Algoritma sonlandırıldı. Klinik bulguları takip edin ve lokal hastane protokollerine uygun hareket edin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "bradycardia_rescue",
            titleTr = "Klinik Bradikardi Algoritması",
            titleEn = "Clinical Bradycardia Algorithm",
            category = "hemodynamics",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Kalp hızı < 50/dakika. Hastada ciddi hipotansiyon, şok bulguları veya solunum arresti eşlik ediyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet (Sempatomik/Stabil Değil)", "Yes (Symptomatic)", "symptomatic_bradycardia"),
                        AlgorithmOption("Hayır (Stabil Bradikardi)", "No (Stable)", "monitor_stable")
                    )
                ),
                "symptomatic_bradycardia" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL HIZ HIZLANDIRMA PROTOKOLÜ: \n1. Atropin 1.0 mg IV bolus uygulayın. 3-5 dakikada bir tekrarlayabilirsiniz (Maksimum 3.0 mg). \n2. Atropin etkisiz kalırsa geçici pacemaker (Transkutan Pacing) hazırlayın. \n3. Eş zamanlı Adrenalin (2-10 mcg/dk) veya Dopamin (2-20 mcg/kg/dk) infüzyonuna başlayın.",
                    relatedDrugs = listOf("ephedrine"),
                    warningLevel = "critical",
                    next = "q_pacing_success"
                ),
                "q_pacing_success" to AlgorithmNode(
                    type = "question",
                    textTr = "İlaçlar ve pacing sonrası nabız/tansiyon düzeldi mi?",
                    options = listOf(
                        AlgorithmOption("Evet (Stabil)", "Yes", "end"),
                        AlgorithmOption("Hayır (Kardiyak Arrest şüphesi)", "No", "arrest_alert")
                    )
                ),
                "arrest_alert" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 KARDİYAK ARREST: Nabız alınamıyorsa derhal ACLS (İleri Yaşam Desteği) CPR protokolünü ve Mavi Kod durumunu başlatın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "monitor_stable" to AlgorithmNode(
                    type = "end",
                    textTr = "Hasta stabil durumda. Yakın EKG monitörizasyonuna devam edin. Bradikardiye sebep olan anestezik derinliğini (MAC) azaltın, vagal uyaranları (cerrahi çekme manevraları) sonlandırın.",
                    warningLevel = "none"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Bradikardi yönetimi sonlandırıldı. Klinik takibe devam ediliyor.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "tachycardia_rescue",
            titleTr = "Klinik Taşikardi Algoritması",
            titleEn = "Clinical Tachycardia Algorithm",
            category = "hemodynamics",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Kalp Hızı > 150/dakika. Hastada hemodinamik instabilite (hipotansiyon, şok, koroner iskemi) var mı?",
                    options = listOf(
                        AlgorithmOption("Evet (Stabil Değil)", "Yes (Unstable)", "action_synchronized_cardioversion"),
                        AlgorithmOption("Hayır (Stabil Taşikardi)", "No (Stable)", "q_qrs_width")
                    )
                ),
                "action_synchronized_cardioversion" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL SENKRONİZE KARDİYOVERSİYON: \n1. Hastaya anestezi/sedasyon verin (zaten genel anestezide değilse). \n2. Defibrilatörü SENKRONİZE moda alın. \n3. Dar düzenli ritim için 50-100 J, geniş ritim için 100-200 J ile kardiyoversiyon uygulayın. Gerekirse enerjiyi kademeli artırın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "q_qrs_width" to AlgorithmNode(
                    type = "question",
                    textTr = "EKG dalga formunda QRS genişliği nasıl?",
                    options = listOf(
                        AlgorithmOption("Dar QRS (<0.12 saniye)", "Narrow QRS", "narrow_tachycardia"),
                        AlgorithmOption("Geniş QRS (>=0.12 saniye)", "Wide QRS", "wide_tachycardia")
                    )
                ),
                "narrow_tachycardia" to AlgorithmNode(
                    type = "action",
                    textTr = "Dar QRS (SVT/Sinüs Taşikardisi vb.) Yönetimi: \n1. Sinüs taşikardisi ise altta yatan nedenleri (ağrı, yetersiz anestezi derinliği, hipovolemi) tedavi edin. \n2. SVT şüphesi varsa Vagal Manevralar (karotis masajı) uygulayın. \n3. Düzelmezse Adenozin 6 mg IV hızlı bolus uygulayın. Başarısız olursa 12 mg tekrarlayın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "wide_tachycardia" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 Geniş QRS (VT/Şüpheli Ritim) Yönetimi: \n1. Ritim düzenli ise Adenozin (6 mg IV) denenebilir. \n2. Amiodaron 150 mg IV infüzyon (10 dakikada) uygulayın. \n3. Elektrolit bozukluklarını (özellikle K ve Mg) kontrol edin, gerekirse Magnezyum Sülfat uygulayın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Ritim normale döndürüldü. EKG takibine devam ediliyor.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "anaphylaxis_rescue",
            titleTr = "Perioperatif Anafilaksi",
            titleEn = "Perioperative Anaphylaxis",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 6,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Maruziyet sonrası ani bronkospazm, yüksek pik havayolu basıncı, hipotansiyon ve ciltte döküntü başladı mı?",
                    options = listOf(
                        AlgorithmOption("Evet", "Yes", "action_stop_trigger"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_stop_trigger" to AlgorithmNode(
                    type = "action",
                    textTr = "1. Şüpheli tüm ajanları (antibiyotik, kas gevşetici, lateks) DERHAL sonlandırın. 2. Oksijeni %100 yapın. 3. Yardım çağırın. 4. Adrenalin (Epinefrin) ilk dozunu uygulayın: Grade 1-2 için 10-20 mcg IV bolus, Grade 3 (ciddi) için 50-100 mcg IV bolus yapın.",
                    relatedDrugs = listOf("ephedrine", "phenylephrine"),
                    warningLevel = "critical",
                    next = "q_hemodynamics"
                ),
                "q_hemodynamics" to AlgorithmNode(
                    type = "question",
                    textTr = "Adrenalin bolus sonrası hemodinamik stabilite sağlandı ve havayolu basınçları düzeldi mi?",
                    options = listOf(
                        AlgorithmOption("Evet (Stabil)", "Yes", "action_secondary_tx"),
                        AlgorithmOption("Hayır (Dirençli Hipotansiyon/Bronkospazm)", "No", "action_infusion_epinephrine")
                    )
                ),
                "action_infusion_epinephrine" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 DİRENÇLİ ANAFİLAKSİ: 1. Adrenalin IV dozunu tekrarlayın (100-500 mcg IV). 2. Hızla Adrenalin infüzyonu (0.05 - 0.2 mcg/kg/dk) başlayın. 3. Agresif sıvı yüklemesi yapın (2-4 L kristalloid). 4. Kardiyak arrest durumunda derhal CPR protokolünü başlatın.",
                    relatedDrugs = listOf("norepinephrine"),
                    warningLevel = "critical",
                    next = "end"
                ),
                "action_secondary_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "Destekleyici tedavilere geçin: 1. Antihistaminik (Klorfeniramin 10 mg IV). 2. Metilprednizolon (40-80 mg IV). 3. İnhale bronkodilatörler (Salbutamol). Yakın takiple 24 saat PACU/Yoğun Bakım izlemi planlayın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Algoritma tamamlandı. Alerji testleri için 24 saat sonra kan triptaz düzeylerini kontrol ettirin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "malignant_hyperthermia",
            titleTr = "Malign Hipertermi (MH) Kriz Kılavuzu",
            titleEn = "Malignant Hyperthermia (MH) Crisis Guide",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 10,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Tetikleyici ajan (uçucu anestezikler, süksinilkolin) sonrası açıklanamayan hızlı ETCO₂ artışı, taşikardi, rijidite (masseter spazmı) ve ani ateş yükselmesi var mı?",
                    options = listOf(
                        AlgorithmOption("Evet", "Yes", "action_mh_trigger"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_mh_trigger" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL EYLEM: MH Kriz Protokolü! 1. Tetikleyici ajanları (Sevo, Des, Süksinilkolin) DERHAL kapatın. 2. Ventilatör akışını >10 L/dk %100 O₂ yapın, aktif karbon filtreleri takın. 3. TIVA'ya (Propofol) geçip cerrahiyi en kısa sürede bitirin.",
                    warningLevel = "critical",
                    next = "action_dantrolene_admin"
                ),
                "action_dantrolene_admin" to AlgorithmNode(
                    type = "action",
                    textTr = "1. Dantrolen bolus (2.5 mg/kg IV) hemen uygulayın (Malign Hipertermi Dantrolen Hesaplayıcısını kullanın). 2. Hastayı soğutmaya başlayın: Buz torbaları, soğuk IV kristalloidler, soğuk mide lavajı. 3. Metabolik asidozu sodyum bikarbonat (1-2 mEq/kg) ile tedavi edin. 4. Hiperkalemi tedavisi uygulayın.",
                    relatedCalculators = listOf("dantrolene_rescue"),
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Hasta stabil olana kadar yoğun bakım şartlarında takip edin. Dantrolen idame dozlarına (1 mg/kg, her 6 saatte bir) devam edin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "last_toxicity",
            titleTr = "Lokal Anestezik Toksisitesi (LAST)",
            titleEn = "Local Anesthetic Systemic Toxicity (LAST)",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 8,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Lokal anestezik enjeksiyonu sonrasında ani nörolojik (nöbet, metalik tat, ajitasyon) veya kardiyak (aritmi, hipotansiyon) belirtiler başladı mı?",
                    options = listOf(
                        AlgorithmOption("Evet", "Yes", "last_action"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "last_action" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL EYLEM: LAST Protokolü! \n1. Enjeksiyonu DERHAL durdurun. \n2. Yardım çağırın ve %100 Oksijen ile hava yolunu güvenceye alın. \n3. %20 Lipid Emülsiyonu (Lipidrescue) hazırlatın ve hemen bolus uygulayın: 1.5 mL/kg IV (yaklaşık 100 mL bolus), ardından 0.25 mL/kg/dk infüzyon başlatın. \n4. Nöbet gelişirse Midazolam veya Propofol (düşük doz) ile kontrol altına alın.",
                    relatedDrugs = listOf("sugammadex"),
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Belirtiler yok. Yakın takibe devam edin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "massive_transfusion",
            titleTr = "Masif Transfüzyon Protokolü (MTP)",
            titleEn = "Massive Transfusion Protocol (MTP)",
            category = "fluids",
            urgencyLevel = "emergency",
            estimatedMinutes = 8,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Aktif kontrolsüz kanama nedeniyle 24 saatte >10 ünite veya 1 saatte >4 ünite eritrosit transfüzyonu ihtiyacı öngörülüyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet (MTP Başlat)", "Yes", "action_activate_mtp"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_activate_mtp" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 MASİF TRANSFÜZYON PROTOKOLÜ AKTİF! 1. Kan bankasını MTP koduyla arayıp paket isteyin. 2. Isıtıcılı hızlı infüzyon cihazlarını (Level 1) hazırlayın. 3. Hedef oran: 1 ES (Eritrosit) : 1 TDP (Taze Donmuş Plazma) : 1 TS (Trombosit) dengeli transfüzyon sağlayın.",
                    warningLevel = "critical",
                    next = "q_coagulation_state"
                ),
                "q_coagulation_state" to AlgorithmNode(
                    type = "question",
                    textTr = "Laboratuvar koagülasyon sonuçları veya ROTEM/TEG değerleri ulaştı mı?",
                    options = listOf(
                        AlgorithmOption("Evet (ROTEM/TEG kılavuzlu)", "Yes", "action_rotem_tx"),
                        AlgorithmOption("Hayır (Ampirik Kör Transfüzyon)", "No", "action_blind_tx")
                    )
                ),
                "action_rotem_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "ROTEM Kılavuzlu Hedefli Tedavi: \n1. FibTEM/A10 < 10 mm ise Fibrinojen konsantresi (2-4 g) verin. \n2. ExTEM/CT > 80 sn ise TDP (15 mL/kg) veya PCC uygulayın. \n3. Trombositopeni (Plt < 50,000) varsa Trombosit transfüzyonu yapın. \n4. İyonize Kalsiyumu >1.1 mmol/L tutmak için Kalsiyum Klorür uygulayın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "action_blind_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "Dengeli ampirik MTP paketlerine (1:1:1 oranında) devam edin. Her 4-6 ünitede bir iyonize kalsiyum kontrolü yapın ve 1 ampul Kalsiyum Klorür ekleyin. Asidoz ve hipotermiyi aktif olarak engelleyin.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Kanama kontrolü sağlanana, Hb >8 g/dL ve koagülasyon parametreleri normale dönene kadar hemodinamik takibe devam edin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "rotem_coagulation",
            titleTr = "ROTEM/TEG Temelli Koagülasyon Algoritması",
            titleEn = "ROTEM/TEG Guided Coagulation Algorithm",
            category = "hemodynamics",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 6,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Klinik olarak aktif kanayan hastada ROTEM (Rotasyonel Tromboelastometri) FibTEM A10 değeri kaç mm?",
                    options = listOf(
                        AlgorithmOption("FibTEM A10 < 10 mm (Ciddi Fibrinojen Eksikliği)", "FibTEM A10 < 10", "fibrinogen_tx"),
                        AlgorithmOption("FibTEM A10 >= 10 mm (Yeterli Fibrinojen)", "FibTEM A10 >= 10", "q_extem_ct")
                    )
                ),
                "fibrinogen_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 FİBRİNOJEN REPLASMANI: \n1. Fibrinojen konsantresi (2-4 g IV) verin veya Kriyopresipitat uygulayın (10 ünite). \n2. FibTEM A10 hedefini >12 mm düzeyinde tutun.",
                    warningLevel = "critical",
                    next = "q_extem_ct"
                ),
                "q_extem_ct" to AlgorithmNode(
                    type = "question",
                    textTr = "ExTEM CT (Pıhtılaşma Zamanı) değeri kaç saniye?",
                    options = listOf(
                        AlgorithmOption("ExTEM CT > 80 saniye (Pıhtılaşma faktör eksikliği)", "ExTEM CT > 80", "pcc_tdp_tx"),
                        AlgorithmOption("ExTEM CT <= 80 saniye (Faktörler normal)", "ExTEM CT <= 80", "q_extem_a10")
                    )
                ),
                "pcc_tdp_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 KOAGÜLASYON FAKTÖR REPLASMANI: \n1. Taze Donmuş Plazma (TDP) 15 mL/kg uygulayın veya Protrombin Kompleks Konsantresi (PCC) 20-30 IU/kg verin. \n2. Heparin etkisi şüphesinde Heptem CT kontrolü yapın, yüksekse Protamin Sülfat uygulayın.",
                    warningLevel = "critical",
                    next = "q_extem_a10"
                ),
                "q_extem_a10" to AlgorithmNode(
                    type = "question",
                    textTr = "ExTEM A10 (Pıhtı Mukavemeti) değeri kaç mm?",
                    options = listOf(
                        AlgorithmOption("ExTEM A10 < 40 mm (Trombositopeni/Trombosit Disfonksiyonu)", "ExTEM A10 < 40", "platelet_tx"),
                        AlgorithmOption("ExTEM A10 >= 40 mm (Güvenli Pıhtı Mukavemeti)", "ExTEM A10 >= 40", "end")
                    )
                ),
                "platelet_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "TROMBOSİT REPLASMANI: \n1. Trombosit süspansiyonu uygulayın (1 havuzlanmış veya 4-6 ünite random plt). \n2. Aktif antitrombosit ilaç (klopidogrel) alımı öyküsünde DDAVP (Desmopressin 0.3 mcg/kg) ekleyebilirsiniz.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Koagülasyon algoritmik kontrolü tamamlandı. ROTEM/TEG testini kanama sürerse 30 dakika sonra tekrarlayın.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "ponv_prevention",
            titleTr = "PONV (Postoperatif Bulantı-Kusma) Önleme",
            titleEn = "PONV (Postoperative Nausea-Vomiting) Prevention",
            category = "scores",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastanın Apfel Skoru kaç puan? \n(Risk Faktörleri: Kadın cinsiyet, sigara içmeme öyküsü, PONV/taşıt tutması öyküsü, postoperatif opioid kullanımı)",
                    options = listOf(
                        AlgorithmOption("0 - 1 Puan (Düşük Risk)", "0-1 pt (Low)", "low_ponv"),
                        AlgorithmOption("2 Puan (Orta Risk)", "2 pt (Medium)", "medium_ponv"),
                        AlgorithmOption("3 - 4 Puan (Yüksek Risk)", "3-4 pt (High)", "high_ponv")
                    )
                ),
                "low_ponv" to AlgorithmNode(
                    type = "end",
                    textTr = "Düşük PONV Riski: Rutin profilaksi şart değildir. Sadece cerrahi tipine (örn. laparoskopi, strabismus) göre gerekirse tek ajan uygulayabilirsiniz.",
                    warningLevel = "none"
                ),
                "medium_ponv" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ Orta PONV Riski: Tek veya çiftli ajan profilaksisi önerilir. İndüksiyondan hemen sonra Deksametazon 4-8 mg IV uygulayın veya cerrahi bitiminden 30 dakika önce Ondansetron 4 mg IV yapın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "high_ponv" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 YÜKSEK PONV RİSKİ: Çoklu multimodal önlem planlanmalıdır! \n1. İndüksiyonda Deksametazon 8 mg IV uygulayın. \n2. Cerrahi kapama esnasında Ondansetron 4 mg IV veya Metoklopramid 10 mg ekleyin. \n3. Gazlı anestezi yerine TIVA (Propofol indüksiyonu ve idamesi) tercih edin ve postoperatif opioid dozlarını minimize edin.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "PONV protokolü tamamlandı. PACU'da bulantı sürerse kurtarıcı doz olarak antagonist gruptan bir ilaç (örn. Metoklopramid) uygulayın.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "postop_pain_management",
            titleTr = "Postoperatif Ağrı Yönetim Algoritması",
            titleEn = "Postoperative Pain Management Algorithm",
            category = "scores",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Operasyon bitti, derlenme odasında (PACU) vizüel ağrı skalası (VAS) skoru kaç?",
                    options = listOf(
                        AlgorithmOption("VAS 1 - 3 (Hafif Ağrı)", "VAS 1-3", "mild_pain"),
                        AlgorithmOption("VAS 4 - 6 (Orta Şiddetli Ağrı)", "VAS 4-6", "moderate_pain"),
                        AlgorithmOption("VAS 7 - 10 (Ciddi Şiddetli Ağrı)", "VAS 7-10", "severe_pain")
                    )
                ),
                "mild_pain" to AlgorithmNode(
                    type = "action",
                    textTr = "Hafif Ağrı Tedavisi: \n1. Parasetamol (1000 mg IV, 6 saatte bir) uygulayın. \n2. Kontraendikasyon yoksa non-steroid antiinflamatuar (NSAID - örn. Tenoksikam 20 mg veya Ketorolak 30 mg) ekleyin.",
                    warningLevel = "none",
                    next = "end"
                ),
                "moderate_pain" to AlgorithmNode(
                    type = "action",
                    textTr = "Orta Şiddetli Ağrı Tedavisi: \n1. Parasetamol 1 g + NSAID standart rejimine başlayın. \n2. Ek olarak zayıf opioidler (Tramadol 50-100 mg IV) uygulayın. \n3. Mümkünse yara yerine lokal infiltrasyon yapın veya rejyonel blokları değerlendirin.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "severe_pain" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 CİDDİ AĞRI TEDAVİSİ (Multimodal Opioid Rejimi): \n1. Güçlü opioid titrasyonuna başlayın: Morfin 2-3 mg IV bolus (her 5-10 dakikada bir kontrol ederek) veya Fentanil 25-50 mcg IV titrasyonu uygulayın. \n2. Hasta Kontrollü Analjezi (PCA) pompası hazırlayın. \n3. Parasetamol ve NSAID'leri bazal ağrı kontrolü için arka planda düzenli devam ettirin. Yakın solunum takibi yapın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Ağrı düzeyi kontrol edildi. VAS skoru < 3 olana kadar derlenme odasından taburculuk planlamayın.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "pediatric_difficult_airway",
            titleTr = "Pediatrik Zor Havayolu Algoritması",
            titleEn = "Pediatric Difficult Airway Algorithm",
            category = "airway",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Pediatrik hastada indüksiyon sonrası entübasyon denemesi başarısız oldu. SpO₂ düzeyi nasıl?",
                    options = listOf(
                        AlgorithmOption("SpO₂ > 92% (Oksijenasyon Yeterli)", "SpO2 > 92%", "ped_mask_vent"),
                        AlgorithmOption("SpO₂ <= 92% (Hızlı Desatürasyon)", "SpO2 <= 92%", "ped_cico_alert")
                    )
                ),
                "ped_mask_vent" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ YETERLİ OKSİJENASYON: \n1. Pediatrik boyuta uygun videolaringoskop (VL) veya düz bleyd (Miller) kullanın. Deneme sayısını maksimum 2-3 ile sınırlayın (ödemi önlemek için). \n2. Başarısızlık halinde hemen pediatrik LMA/SGA yerleştirin.",
                    warningLevel = "moderate",
                    next = "q_ped_lma_success"
                ),
                "q_ped_lma_success" to AlgorithmNode(
                    type = "question",
                    textTr = "LMA ile ventilasyon sağlandı mı?",
                    options = listOf(
                        AlgorithmOption("Evet", "Yes", "ped_wake_up"),
                        AlgorithmOption("Hayır", "No", "ped_cico_alert")
                    )
                ),
                "ped_wake_up" to AlgorithmNode(
                    type = "action",
                    textTr = "Ventilasyon sağlanıyor. En güvenli yol hastayı uyandırmak ve işlemi ertelemek veya uyanık fiberoptik planlamaktır.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "ped_cico_alert" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 PEDİATRİK CICO DURUMU: Çocuklar çok hızlı desatüre olur! \n1. Derhal Mavi Kod ve KBB/Cerrahi ekipten yardım isteyin. \n2. Krikotiroid membran tespiti yapıp pediatrik iğne krikotirotomi veya cerrahi krikotirotomiye geçin. \n3. %100 O₂ ile yüksek frekansta nazik ventilasyon sağlayın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Pediatrik zor havayolu müdahale akışı sonlandırıldı.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "obstetric_difficult_airway",
            titleTr = "Obstetrik Zor Havayolu Algoritması",
            titleEn = "Obstetric Difficult Airway Algorithm",
            category = "airway",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Gebede sezaryen indüksiyonu yapıldı ancak birinci laringoskopi denemesinde entübasyon başarısız oldu. Öncelikli yaklaşım ne olmalıdır?",
                    options = listOf(
                        AlgorithmOption("Maske Ventilasyonu & Krikoid Bası", "Mask Vent & Cricoid", "q_ob_vent_ok")
                    )
                ),
                "q_ob_vent_ok" to AlgorithmNode(
                    type = "question",
                    textTr = "Krikoid bası altındayken gebe maske ile havalandırılabiliyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet (Havalandırılıyor)", "Yes", "ob_second_attempt"),
                        AlgorithmOption("Hayır (Maskelenemiyor)", "No", "ob_lma_rescue")
                    )
                ),
                "ob_second_attempt" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ İKİNCİ DENEME KURALLARI: \n1. Krikoid basıyı gevşetmeden pozisyonu optimize edin (rampa pozisyonu obstetrikte şarttır). \n2. Videolaringoskop (VL) kullanın. Deneyimli hekim tarafından tek bir deneme daha yapın (Maksimum 2 entübasyon girişimi). \n3. Başarısız olunursa doğrudan LMA/SGA yerleştirin.",
                    warningLevel = "moderate",
                    next = "ob_lma_rescue"
                ),
                "ob_lma_rescue" to AlgorithmNode(
                    type = "question",
                    textTr = "İkinci nesil LMA/SGA yerleştirildi. Havalandırma başarılı mı?",
                    options = listOf(
                        AlgorithmOption("Evet (SGA ile havalanıyor)", "Yes", "q_fetal_distress"),
                        AlgorithmOption("Hayır (Obstrüksiyon/CICO)", "No", "ob_cico_emergency")
                    )
                ),
                "q_fetal_distress" to AlgorithmNode(
                    type = "question",
                    textTr = "Gebede aktif fetal distres veya masif uterus kanaması var mı? (Cerrahi aciliyet)",
                    options = listOf(
                        AlgorithmOption("Evet (Fetal tehlike var, ameliyat sürmeli)", "Yes (Emergency)", "ob_sga_surgery"),
                        AlgorithmOption("Hayır (Elektif vaka)", "No (Elective)", "ob_wake_up")
                    )
                ),
                "ob_sga_surgery" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL SEZARYEN DEVAMI: LMA üzerinden cerrahiye devam edin. Mide içeriğinin aspire olmasını önlemek için LMA drenaj kanalından nazogastrik sonda yerleştirin. Ameliyatı en kısa sürede tamamlayın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "ob_wake_up" to AlgorithmNode(
                    type = "action",
                    textTr = "Fetal tehlike yoksa indüksiyonu durdurun, nöromüsküler blokajı Sugammadeks ile geri döndürün ve hastayı uyandırıp uyanık entübasyon veya rejyonel anesteziye geçin.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "ob_cico_emergency" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 OBSTETRİK CICO DURUMU: Gebe hastalar çok hızlı desatüre olur! Krikoid basıyı kaldırın, derhal krikotirotomi (Neşter-Bougie-Tüp) uygulayın. Sol uterus deplasmanını (hipotansiyonu önlemek için) sürdürün.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Obstetrik havayolu kriz protokolü tamamlandı.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "high_spinal",
            titleTr = "Yüksek / Total Spinal",
            titleEn = "High / Total Spinal",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 6,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Nöroaksiyel blok sonrasında ani ciddi hipotansiyon, bradikardi, solunum sıkıntısı, konuşmada güçlük ve üst ekstremitelerde duyu kaybı gelişti mi?",
                    options = listOf(
                        AlgorithmOption("Evet", "Yes", "action_high_spinal_alert"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_high_spinal_alert" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL EYLEM: Yüksek Spinal! 1. Yardım çağırın. 2. Havayolunu korumak için gerekirse derhal entübasyon hazırlığı yapın ve %100 O₂ verin. 3. Agresif sıvı yüklemesi yapın, Efedrin uygulayın. Nabız <50/dk ise Atropin (0.5 mg IV) uygulayın.",
                    warningLevel = "critical",
                    next = "q_respiratory_arrest"
                ),
                "q_respiratory_arrest" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada solunum arresti, bilinç kaybı veya derin hipoksi eşlik ediyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet (Total Spinal)", "Yes", "action_total_spinal_intubate"),
                        AlgorithmOption("Hayır (Kısmi Yüksek Spinal)", "No", "action_partial_support")
                    )
                ),
                "action_total_spinal_intubate" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 TOTAL SPİNAL RESÜSİTASYON: 1. Hastayı derhal entübe edin (hızlı ardışık indüksiyon). 2. Mekanik ventilatör desteğine başlayın. 3. Hemodinamiyi korumak için gerekirse sürekli Noradrenalin veya Adrenalin infüzyonu bağlayın. Duyu bloğu gerileyene kadar uyutun.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "action_partial_support" to AlgorithmNode(
                    type = "action",
                    textTr = "Maske ile Oksijen desteğine devam edin. Başı hafifçe yükseltin (lokal anestezik yayılımını sınırlamak için). Hemodinamiyi yakın takip ederek vazopressörler ile stabil tutun.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Lokal anestezi etkisi geçene kadar (2-4 saat) yakın hemodinamik ve solunum monitörizasyonuna devam edin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "pdph_management",
            titleTr = "PDPH (Post-Dural Puncture Headache) Algoritması",
            titleEn = "PDPH Management Algorithm",
            category = "scores",
            urgencyLevel = "urgent",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Spinal anestezi veya epidural girişimden 24-48 saat sonra ayağa kalkmakla şiddetlenen, yatınca hafifleyen frontal/oksipital baş ağrısı var mı?",
                    options = listOf(
                        AlgorithmOption("Evet (PDPH Uyumlu)", "Yes", "q_pdph_severity"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "q_pdph_severity" to AlgorithmNode(
                    type = "question",
                    textTr = "Ağrının şiddeti ve hastanın mobilizasyon durumu nasıl?",
                    options = listOf(
                        AlgorithmOption("Hafif - Orta Şiddetli (Hasta mobilize olabiliyor)", "Mild-Moderate", "conservative_tx"),
                        AlgorithmOption("Ciddi Şiddetli (Hasta yataktan kalkamıyor, bulantı/kusma var)", "Severe", "epidural_blood_patch")
                    )
                ),
                "conservative_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "Konservatif Destekleyici Tedavi: \n1. Hastayı yatak istirahatine alın, hidrasyonu artırın (3 L/gün sıvı). \n2. Kafein tedavisi başlayın (örn. 300-500 mg oral kafein veya koka kola). \n3. Parasetamol + NSAID ve teofilin gibi oral analjezikler reçete edin. 24 saat sonra hastayı tekrar değerlendirin.",
                    warningLevel = "none",
                    next = "end"
                ),
                "epidural_blood_patch" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 EPİDURAL KAN YAMASI (Epidural Blood Patch - Altın Standart): \n1. Konservatif tedaviye 24 saatte yanıt alınamazsa planlayın. \n2. Steril şartlarda hastanın kolundan 15-20 mL venöz kan çekin. \n3. Aynı anda dural ponksiyon yapılan mesafeden epidural aralığa girerek bu kanı yavaşça enjekte edin (baskı hissedene kadar). \n4. İşlem sonrası hastayı 2 saat yatar pozisyonda tutun.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "PDPH yönetimi sonlandırıldı. Pıhtı basısı riski yönünden hastanın nörolojik durumunu (bacaklarda his kaybı vb.) işlemden sonra 24 saat takip edin.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "epidural_hematoma_suspicion",
            titleTr = "Epidural Hematom Şüphesi",
            titleEn = "Epidural Hematoma Suspicion",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 5,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Nöroaksiyel blok (spinal/epidural) veya kateter çekilmesi sonrasında hastada ani başlayan sırt ağrısı, bacaklarda ilerleyici motor/duyu kaybı saptandı mı?",
                    options = listOf(
                        AlgorithmOption("Evet", "Yes", "action_urgent_mri"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "action_urgent_mri" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL NÖROLOJİK ALARM: Spinal kord basısı riski! \n1. Kateteri çekmeyin, cerrahi ve beyin cerrahisi ekiplerini derhal bilgilendirin. \n2. Acil lomber MRG (Manyetik Rezonans Görüntüleme) çekilmesini isteyin. Koagülasyon parametrelerini (INR, Plt) kontrol edin.",
                    warningLevel = "critical",
                    next = "q_mri_result"
                ),
                "q_mri_result" to AlgorithmNode(
                    type = "question",
                    textTr = "MRG sonucunda epidural aralıkta spinal korda bası yapan hematom saptandı mı?",
                    options = listOf(
                        AlgorithmOption("Evet (Hematom kanıtlandı)", "Yes", "action_decompression"),
                        AlgorithmOption("Hayır (Hematom yok)", "No", "alternative_diagnosis")
                    )
                ),
                "action_decompression" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 BEYİN CERRAHİSİ ACİL DEKOMPRESYON: \nKalıcı parapleji (felç) gelişimini önlemek için semptomların başlamasından itibaren en geç 8 saat içinde Acil Dekompressif Laminektomi uygulanmalıdır!",
                    warningLevel = "critical",
                    next = "end"
                ),
                "alternative_diagnosis" to AlgorithmNode(
                    type = "action",
                    textTr = "İskemik kord hasarı veya uzamış lokal anestezik blokajı yönünden değerlendirin. Hastanın nörolojik takibini 2 saatte bir sürdürün.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Epidural hematom kriz yönetimi sonlandırıldı.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "residual_neuromuscular_block",
            titleTr = "Rezidüel Nöromüsküler Blok",
            titleEn = "Residual Neuromuscular Block",
            category = "drugs",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Cerrahi bitti, hasta ekstübe edilmek isteniyor. Objektif nöromüsküler monitörde (TOF-Watch/TOFscan) ölçülen TOF oranı nedir?",
                    options = listOf(
                        AlgorithmOption("TOF Oranı < 0.9 (Rezidüel blok var)", "TOF < 0.9", "q_tof_count"),
                        AlgorithmOption("TOF Oranı >= 0.9 (Güvenli Derlenme)", "TOF >= 0.9", "safe_extubation")
                    )
                ),
                "q_tof_count" to AlgorithmNode(
                    type = "question",
                    textTr = "Cihazda ölçülen TOF yanıt sayısı (TOF Count) nedir?",
                    options = listOf(
                        AlgorithmOption("TOF Count = 0 (Derin/Yüksek Blok)", "TOF Count = 0", "deep_reversal"),
                        AlgorithmOption("TOF Count 1 - 3 (Orta Derece Blok)", "TOF Count 1-3", "medium_reversal"),
                        AlgorithmOption("TOF Count = 4 (Hafif Blok, TOF oranı < 0.9)", "TOF Count = 4", "mild_reversal")
                    )
                ),
                "deep_reversal" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 DERİN BLOK REVERSALİ: \n1. Blok Roküronyum veya Veküronyum ile yapıldıysa Sugammadeks 4 mg/kg IV uygulayın. \n2. Sugammadeks sonrası TOF oranının >0.9 olduğunu teyit etmeden hastayı ekstübe etmeyin. Neostigmin bu aşamada etkisizdir.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "medium_reversal" to AlgorithmNode(
                    type = "action",
                    textTr = "ORTA BLOK REVERSALİ: \n1. Roküronyum/Veküronyum için: Sugammadeks 2 mg/kg IV uygulayın. \n2. Sisatrakuryum/Alküronyum için: Neostigmin (0.04-0.05 mg/kg) + Atropin (0.02 mg/kg) kombinasyonunu uygulayın. \n3. TOF >0.9 olana kadar en az 10-15 dakika bekleyin.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "mild_reversal" to AlgorithmNode(
                    type = "action",
                    textTr = "HAFİF BLOK REVERSALİ: \n1. Sugammadeks 2 mg/kg veya Neostigmin (0.02-0.03 mg/kg) + Atropin uygulayın. \n2. TOF oranının 0.90 üzerine çıktığını kantitatif olarak görün.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "safe_extubation" to AlgorithmNode(
                    type = "end",
                    textTr = "Rezidüel blok yok. Hasta uyanık, havayolu refleksleri yerinde ve başını 5 saniye kaldırabiliyorsa güvenle ekstübe edilebilir.",
                    warningLevel = "none"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Nöromüsküler geri döndürme (reversal) süreci tamamlandı.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "sugammadex_neostigmine_reversal",
            titleTr = "Sugammadeks / Neostigmin Reversali",
            titleEn = "Sugammadex / Neostigmine Reversal Guide",
            category = "drugs",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Reversal için kullanılacak kas gevşetici grubu hangisidir?",
                    options = listOf(
                        AlgorithmOption("Aminosteroid (Roküronyum / Veküronyum)", "Aminosteroid", "q_sugammadex_dosing"),
                        AlgorithmOption("Benzilizokinolyum (Sisatrakuryum vb.)", "Benzylisoquinoline", "neostigmine_only")
                    )
                ),
                "q_sugammadex_dosing" to AlgorithmNode(
                    type = "question",
                    textTr = "Kas gevşemenin derinliği nedir? (TOF monitör bulgusu)",
                    options = listOf(
                        AlgorithmOption("Acil Reversal (Roküronyum 1.2 mg/kg bolus sonrası hemen)", "Immediate Reversal", "sugammadex_16"),
                        AlgorithmOption("Derin Blok (TOF count = 0, Post-tetanic count >= 1)", "Deep (PTC >= 1)", "sugammadex_4"),
                        AlgorithmOption("Yüzeysel Blok (TOF count >= 2)", "Moderate (TOF >= 2)", "sugammadex_2")
                    )
                ),
                "sugammadex_16" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ACİL REVERSAL DOZU: Sugammadeks 16 mg/kg IV yavaş bolus uygulayın. 3 dakika içinde tam derlenme sağlanacaktır.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "sugammadex_4" to AlgorithmNode(
                    type = "action",
                    textTr = "Derin Blok Reversali: Sugammadeks 4 mg/kg IV bolus uygulayın. TOF oranının >0.9 olmasını izleyin.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "sugammadex_2" to AlgorithmNode(
                    type = "action",
                    textTr = "Yüzeysel Blok Reversali: Sugammadeks 2 mg/kg IV bolus uygulayın. Derlenme süresi yaklaşık 2 dakikadır.",
                    warningLevel = "none",
                    next = "end"
                ),
                "neostigmine_only" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ NEOSTİGMİN REVERSAL PROTOKOLÜ: \n1. Sugammadeks sisatrakuryumu bağlamaz! Neostigmin tercih edilmelidir. \n2. TOF count en az 2, tercihen 4 olmadan asla Neostigmin uygulamayın (blok derinleşebilir). \n3. Neostigmin 0.04 mg/kg + Atropin 0.02 mg/kg IV karıştırarak yavaşça uygulayın. Muskarinik yan etkilere (bradikardi, sekresyon) karşı takip edin.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Reversal ilaç uygulaması tamamlandı.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "oliguria_management",
            titleTr = "İntraoperatif Oligüri Algoritması",
            titleEn = "Intraoperative Oliguria Algorithm",
            category = "fluids",
            urgencyLevel = "urgent",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "İntraoperatif idrar çıkışı < 0.5 mL/kg/saat (Oligüri). İlk yapılması gereken nedir?",
                    options = listOf(
                        AlgorithmOption("Kateter tıkanıklığını kontrol et ve Sıvı durumunu sorgula", "Check Foley & Fluids", "q_volume_status")
                    )
                ),
                "q_volume_status" to AlgorithmNode(
                    type = "question",
                    textTr = "Foley sondada katlanma/tıkanma yok. Hastanın hemodinamik parametreleri (OAB, Nabız basıncı varyasyonu) hipovolemiyi gösteriyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet (Hipovolemi var, hasta sıvıya yanıt verir)", "Yes (Hypovolemic)", "fluid_bolus_tx"),
                        AlgorithmOption("Hayır (Normovolemik / Hipervolemik)", "No (Normovolemic)", "check_hemodynamics")
                    )
                ),
                "fluid_bolus_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "Sıvı Yüklemesi: \n1. 250-500 mL kristalloid bolus uygulayın. \n2. İdrar çıkışını sonraki 30 dakikada tekrar değerlendirin. \n3. Yanıt yoksa hedefe yönelik sıvı tedavisi (GDFT) protokolüne göre stroke volume kontrolü yapın.",
                    warningLevel = "none",
                    next = "end"
                ),
                "check_hemodynamics" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ HEMODİNAMİK DÜZENLEME: \n1. Ortalama arter basıncını (OAB) renal perfüzyon için >70 mmHg düzeyinde tutun (Gerekirse vazopressör desteği ile). \n2. Ciddi böbrek yetmezliği şüphesinde diüretik (Furosemid 5-10 mg IV) sadece normovolemi sağlandıktan sonra düşünülebilir. Asla körlemesine yüksek doz diüretik yapmayın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Oligüri kontrolü tamamlandı. Postoperatif akut böbrek hasarı (AKI) takibi planlayın.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "sepsis_shock",
            titleTr = "Sepsis & Septik Şok Protokolü",
            titleEn = "Sepsis & Septic Shock Protocol",
            category = "critical",
            urgencyLevel = "emergency",
            estimatedMinutes = 8,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Enfeksiyon şüphesi olan hastada qSOFA skoru >= 2, ciddi hipotansiyon ve perfüzyon bozukluğu var mı?",
                    options = listOf(
                        AlgorithmOption("Evet (Sepsis / Şok Şüphesi)", "Yes", "sepsis_bundle_1h"),
                        AlgorithmOption("Hayır", "No", "end")
                    )
                ),
                "sepsis_bundle_1h" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 SURVIVING SEPSIS 1. SAAT PAKETİ: \n1. Kan laktat düzeyini hemen ölçün (Laktat > 2 mmol/L ise yakın takip). \n2. Antibiyotik vermeden önce en az iki farklı odaktan kan kültürü alın. \n3. Geniş spektrumlu IV antibiyotik tedavisini derhal başlatın. \n4. Hipotansiyon veya Laktat >= 4 mmol/L varlığında 30 mL/kg kristalloid sıvı yüklemesi başlatın.",
                    warningLevel = "critical",
                    next = "q_fluid_response"
                ),
                "q_fluid_response" to AlgorithmNode(
                    type = "question",
                    textTr = "30 mL/kg sıvı yüklemesine rağmen OAB >= 65 mmHg hedefine ulaşılamadı mı? (Septik Şok tablosu)",
                    options = listOf(
                        AlgorithmOption("Evet (Dirençli Şok)", "Yes", "vasopressor_initiation"),
                        AlgorithmOption("Hayır (Tansiyon normale döndü)", "No", "end")
                    )
                ),
                "vasopressor_initiation" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 SEPTİK ŞOK RESÜSİTASYONU: \n1. Birinci tercih vazopressör olan **Noradrenalin** infüzyonuna santral yoldan hemen başlayın (OAB >= 65 mmHg hedefleyin). \n2. Dirençli şok durumunda hidrokortizon (200 mg/gün IV) ekleyin. \n3. Sık aralıklarla arter kan gazı, laktat klirensi ve idrar çıkışı takibi yapın.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Sepsis acil müdahalesi tamamlandı. Hasta yoğun bakım ünitesine transfer edilmelidir.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "ards_ventilation",
            titleTr = "ARDS Koruyucu Ventilasyon Algoritması",
            titleEn = "ARDS Protective Ventilation Algorithm",
            category = "critical",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 6,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "ARDS (Akut Solunum Sıkıntısı Sendromu) olan hastada ventilatör ayarları planlanacak. İlk hedef nedir?",
                    options = listOf(
                        AlgorithmOption("Akciğer Koruyucu Ventilasyon (LTVT) Başlat", "LTVT Protocol", "ltvt_steps")
                    )
                ),
                "ltvt_steps" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 KORUYUCU VENTİLASYON BASAMAKLARI: \n1. Tidal Volümü hastanın **PBW (Predicted Body Weight)** kilosuna göre **6 mL/kg** olarak ayarlayın (ARDSNet standardı). \n2. Plato Basıncını (Pplat) mutlaka **< 30 cmH₂O** düzeyinde tutun. \n3. PEEP düzeyini PaO2/FiO2 oranına göre kademeli olarak artırarak atelektazileri engelleyin.",
                    warningLevel = "critical",
                    next = "q_driving_pressure"
                ),
                "q_driving_pressure" to AlgorithmNode(
                    type = "question",
                    textTr = "Ölçülen Sürüş Basıncı (Driving Pressure = Pplat - PEEP) kaç cmH₂O?",
                    options = listOf(
                        AlgorithmOption("Driving Pressure > 15 cmH₂O (Yüksek gerilme riski)", "DP > 15", "reduce_tidal_volume"),
                        AlgorithmOption("Driving Pressure <= 15 cmH₂O (Güvenli)", "DP <= 15", "check_oxygenation")
                    )
                ),
                "reduce_tidal_volume" to AlgorithmNode(
                    type = "action",
                    textTr = "Sürüş basıncını azaltmak için Tidal Volümü 5 veya 4 mL/kg düzeyine kadar düşürün. Oluşacak hafif hiperkapniye (Permisif Hiperkapni) arterial pH >= 7.20 olduğu sürece izin verin.",
                    warningLevel = "moderate",
                    next = "check_oxygenation"
                ),
                "check_oxygenation" to AlgorithmNode(
                    type = "question",
                    textTr = "Tüm ayarlara rağmen PaO₂/FiO₂ < 150 mmHg (Ağır ARDS tablosu) devam ediyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet (Ağır hipoksemi)", "Yes", "severe_ards_tx"),
                        AlgorithmOption("Hayır (Stabil oksijenasyon)", "No", "end")
                    )
                ),
                "severe_ards_tx" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 AĞIR ARDS YÖNETİMİ: \n1. Hastaya yüzüstü (Prone Pozisyonu) ventilasyon uygulayın (Günde en az 16 saat). \n2. Spontan solunum eforlarını baskılamak ve akciğer hasarını önlemek için sürekli kas gevşetici (Sisatrakuryum) infüzyonu bağlayın. \n3. Son çare olarak ECMO (Ekstrakorporal Membran Oksijenasyonu) endikasyonunu değerlendirin.",
                    warningLevel = "critical",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "ARDS ventilasyon kontrol döngüsü tamamlandı.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "hypertensive_delay_elective",
            titleTr = "Hipertansif Hastada Cerrahi Erteleme Kılavuzu",
            titleEn = "Hypertensive Elective Surgery Delay Guide",
            category = "scores",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 4,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Elektif cerrahi öncesi yapılan muayenede hastanın kan basıncı kaç saptandı?",
                    options = listOf(
                        AlgorithmOption("Sistolik < 180 mmHg ve Diyastolik < 110 mmHg", "BP < 180/110", "proceed_surgery"),
                        AlgorithmOption("Sistolik >= 180 mmHg veya Diyastolik >= 110 mmHg (Evre 3)", "BP >= 180/110", "q_end_organ")
                    )
                ),
                "proceed_surgery" to AlgorithmNode(
                    type = "end",
                    textTr = "Cerrahi Erteleme Gerekmez: Ameliyata devam edilebilir. Hastanın kronik antihipertansif ilaçlarını (Beta-blokerler, Kalsiyum kanal blokerleri) ameliyat sabahı az suyla almasını sağlayın. ACE inhibitörleri/ARB'leri vazopleji riskini önlemek için genellikle 24 saat önce kesilmelidir.",
                    warningLevel = "none"
                ),
                "q_end_organ" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada akut hedef organ hasarı bulgusu var mı? (Göğüs ağrısı, nefes darlığı, yeni başlayan nörolojik defisit, akut böbrek hasarı)",
                    options = listOf(
                        AlgorithmOption("Evet (Akut organ hasarı var / Hipertansif Acil)", "Yes", "hypertensive_emergency"),
                        AlgorithmOption("Hayır (Sadece yüksek tansiyon / Hipertansif İve)", "No", "q_surgery_type")
                    )
                ),
                "hypertensive_emergency" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 HİPERTANSİF ACİL DURUM: \n1. Ameliyatı derhal erteleyin. \n2. Hastayı yoğun bakım veya kardiyoloji servisine transfer edin. \n3. Kan basıncını IV infüzyonlarla (Perlinganit/Esmolol) kontrollü olarak düşürün (İlk saatte en fazla %20-25 oranında).",
                    warningLevel = "critical",
                    next = "end"
                ),
                "q_surgery_type" to AlgorithmNode(
                    type = "question",
                    textTr = "Planlanan cerrahinin türü nedir?",
                    options = listOf(
                        AlgorithmOption("Düşük/Orta riskli elektif cerrahi", "Low/Medium risk", "delay_and_control"),
                        AlgorithmOption("Acil / Kansere bağlı kritik cerrahi", "Cancer / Emergency", "proceed_with_monitoring")
                    )
                ),
                "delay_and_control" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ ELEKTİF ERTELEME: İntraoperatif serebral/miyokardiyal iskemi ve aritmi riskini azaltmak amacıyla elektif cerrahi ertelenmelidir. Hastayı kardiyolojiye yönlendirerek medikal tedavisinin optimize edilmesini (hedef < 140/90 mmHg) sağlayın.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "proceed_with_monitoring" to AlgorithmNode(
                    type = "action",
                    textTr = "⚠️ KONTROLLÜ DEVAM: Operasyon acil veya ertelenemez olduğundan devam edilmelidir. \n1. İntraoperatif yakın kan basıncı dalgalanmalarını önlemek için invaziv arteriyel monitörizasyon kurun. \n2. Anestezi derinliğini sabit tutun, tansiyon yükseldiğinde IV kısa etkili ajanlar (örn. Esmolol, Labetalol) ile müdahale edin.",
                    warningLevel = "moderate",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Hipertansif değerlendirme akışı tamamlandı.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        )
    ,

        Algorithm(
            algorithmId = "aspiration_regurgitation",
            titleTr = "Aspirasyon / Regürjitasyon Yönetimi",
            titleEn = "Aspiration / Regurgitation Management",
            category = "airway",
            urgencyLevel = "emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Aspirasyon/Regürjitasyon ne zaman gerçekleşti?",
                    options = listOf(
                        AlgorithmOption("İndüksiyon Sırasında (Aktif Kusma/Regürjitasyon)", "Induction", "induksiyon_aktif"),
                        AlgorithmOption("Entübasyon Sonrası (Pasif Regürjitasyon)", "Post-Intubation", "intubated_passive")
                    )
                ),
                "induksiyon_aktif" to AlgorithmNode(
                    type = "action",
                    textTr = "Başını hemen yana çevirin, ameliyat masasını Trendelenburg pozisyonuna getirin. Ağız içini ve yutağı kalın aspiratör ucu ile hızla aspire edin.",
                    next = "q_intube"
                ),
                "q_intube" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta entübe mi?",
                    options = listOf(
                        AlgorithmOption("Hayır, entübe değil", "No", "not_intubated"),
                        AlgorithmOption("Evet, entübe", "Yes", "post_intube_suction")
                    )
                ),
                "not_intubated" to AlgorithmNode(
                    type = "action",
                    textTr = "Hastayı entübe edin ve kafı hızla şişirin. Entübasyon öncesinde trakeal aspirasyon YAPMAYIN! (Mide içeriğini daha derine itebilir).",
                    next = "post_intube_suction"
                ),
                "post_intube_suction" to AlgorithmNode(
                    type = "action",
                    textTr = "Endotrakeal kaf şişirildikten sonra endotrakeal tüp içinden derin aspirasyon yapın. %100 O2 ile ventile edin. Bronkospazm/hipoksemi yönünden izleyin.",
                    next = "end"
                ),
                "intubated_passive" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 PASİF REAKSİYON: Kaf basıncını hemen kontrol edin ve gerekiyorsa şişirin. Tüp içinden derin aspirasyon yapın. pH ölçümü ve gerekirse bronkoskopi planlayın.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Aspirasyon acil yönetimi sağlandı. Hastayı postoperatif 24 saat boyunca akciğer grafisi, ateş ve oksijenasyon takibiyle bronkopnömoni yönünden izleyin.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "bronchospasm_rescue",
            titleTr = "Bronkospazm Algoritması",
            titleEn = "Bronchospasm Rescue Algorithm",
            category = "airway",
            urgencyLevel = "emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada bronkospazm bulguları (hışıltı solunum, ETCO2'de obstetrik 'shark-fin' eğrisi, yüksek peak havayolu basıncı) var mı?",
                    options = listOf(
                        AlgorithmOption("Evet, belirgin bronkospazm", "Yes", "bronchospasm_yes"),
                        AlgorithmOption("Hayır, sadece yüksek basınç var", "No", "high_pressure_only")
                    )
                ),
                "bronchospasm_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "Anestezi derinliğini artırın (Sevofluran/İnhaler konsantrasyonunu artırın veya Propofol bolus verin). %100 O2'ye geçin. Ventilatör modunu manuel veya basınç kontrollüye alın.",
                    next = "q_drugs"
                ),
                "q_drugs" to AlgorithmNode(
                    type = "question",
                    textTr = "Medikal tedaviye rağmen bronkospazm devam ediyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet, devam ediyor", "Yes", "bronchospasm_severe"),
                        AlgorithmOption("Hayır, geriledi", "No", "bronchospasm_resolved")
                    )
                ),
                "bronchospasm_severe" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ŞİDDETLİ REAKSİYON: ETT içinden MDI/Spacer ile 8-10 puf Salbutamol uygulayın. IV Magnezyum Sülfat (2 g, 10-20 dk içinde) veya IV Ketamin (0.2-0.5 mg/kg) bolus yapın. Ağır dirençli vakalarda IV Adrenalin (10-50 mcg yavaş bolus) düşünün.",
                    next = "end"
                ),
                "bronchospasm_resolved" to AlgorithmNode(
                    type = "action",
                    textTr = "Hastayı derin anestezi altında tutun. Tetikleyici faktörleri (cerrahi stimülasyon, yüzeysel anestezi) ortadan kaldırın. Yavaşça inhalerleri azaltın.",
                    next = "end"
                ),
                "high_pressure_only" to AlgorithmNode(
                    type = "action",
                    textTr = "Bronkospazm dışı nedenleri (tüp bükülmesi, sekresyon, tek akciğer ventilasyonu, obezite, pnömotoraks) değerlendirin.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Komplikasyon kontrolü sağlandı. Postoperatif dönemde hastayı bronkospazm nüksü açısından yakından takip edin.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "high_airway_pressure",
            titleTr = "Yüksek Havayolu Basıncı / Ventilatör Alarm Algoritması",
            titleEn = "High Airway Pressure / Ventilator Alarm",
            category = "airway",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Peak havayolu basıncı > 30 cmH2O mu?",
                    options = listOf(
                        AlgorithmOption("Evet, aniden yükseldi", "Yes", "pressure_high"),
                        AlgorithmOption("Hayır, sınırda veya normal", "No", "end")
                    )
                ),
                "pressure_high" to AlgorithmNode(
                    type = "action",
                    textTr = "Hastayı derhal ventilatörden ayırıp manuel balona (ambuya) geçin. Balonun uyumunu (akciğer kompliyansını) elinizle hissedin.",
                    next = "q_compliance"
                ),
                "q_compliance" to AlgorithmNode(
                    type = "question",
                    textTr = "Manuel ventilasyonda balon çok sert mi, direnç hissediliyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet, balon çok sert (Akciğer/Tüp problemi)", "Yes", "compliance_low"),
                        AlgorithmOption("Hayır, balon yumuşak (Devre/Ventilatör problemi)", "No", "compliance_normal")
                    )
                ),
                "compliance_low" to AlgorithmNode(
                    type = "action",
                    textTr = "Endotrakeal tüpten ince kateter geçirin. Kateter geçiyor mu?",
                    next = "q_catheter"
                ),
                "q_catheter" to AlgorithmNode(
                    type = "question",
                    textTr = "Kateter endotrakeal tüpten rahatça geçiyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet, geçiyor (Bronkospazm/Pnömotoraks/Ana bronş)", "Yes", "catheter_passed"),
                        AlgorithmOption("Hayır, takılıyor (Tüp tıkanıklığı/Isırma)", "No", "catheter_blocked")
                    )
                ),
                "catheter_passed" to AlgorithmNode(
                    type = "action",
                    textTr = "Bronkospazm, Tansiyon Pnömotoraks (asimetrik göğüs hareketi, hipoksemi, hipotansiyon) veya ana bronş entübasyonu yönünden akciğerleri dinleyin. Pnömotoraksta iğne dekompresyonu yapın.",
                    next = "end"
                ),
                "catheter_blocked" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 TÜP BLOKAJI: Hasta tüpü ısırıyorsa ısırma bloğu yerleştirin veya kas gevşetici yapın. Tüp bükülmüşse düzeltin. Tıkanıklık açılmıyorsa tüpü hızla değiştirin.",
                    next = "end"
                ),
                "compliance_normal" to AlgorithmNode(
                    type = "action",
                    textTr = "Sorun ventilatörde veya solunum devresindedir. Devre hortumlarını, APL valfini ve CO2 absorbanını kontrol edin.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Havayolu basınçları normal sınırlara döndü. Ventilatör limitlerini ve alarmlarını yeniden kalibre edin.",
                    warningLevel = "moderate"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "etco2_anomaly",
            titleTr = "ETCO₂ Ani Düşüş / Ani Yükseliş Algoritması",
            titleEn = "ETCO2 Drop / Rise Anomaly",
            category = "airway",
            urgencyLevel = "emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "ETCO2 anomalisi hangisi?",
                    options = listOf(
                        AlgorithmOption("Ani düşüş / Sıfırlanma (ETCO2 = 0)", "Drop", "etco2_drop"),
                        AlgorithmOption("Kademeli yükseliş / Hiperkapni", "Rise", "etco2_rise")
                    )
                ),
                "etco2_drop" to AlgorithmNode(
                    type = "action",
                    textTr = "Solunum devresi bağlantısını, tüpün yerini (ekstübasyon) ve nabzı (arrest) derhal kontrol edin!",
                    next = "q_pulse"
                ),
                "q_pulse" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada karotis/femoral nabız alınıyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet, nabız var (Tüp çıkması/Konnektör kopması)", "Yes", "pulse_yes"),
                        AlgorithmOption("Hayır, nabız alınamıyor (Kardiyak Arrest)", "No", "pulse_no")
                    )
                ),
                "pulse_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "Tüpün yerini kontrol edin (özofagus entübasyonu?), solunum devresi konnektörlerini sıkılaştırın, örnekleme hattının tıkalı olup olmadığına bakın.",
                    next = "end"
                ),
                "pulse_no" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 DERHAL MAVİ KOD VERİN! İntraoperatif Kardiyak Arrest Protokolünü başlatın.",
                    next = "end"
                ),
                "etco2_rise" to AlgorithmNode(
                    type = "action",
                    textTr = "Kademeli yükselişte: Hipoventilasyon, CO2 insüflasyonu (laparoskopi), absorban tükenmesi veya Malign Hipertermi (MH) yönünden değerlendirin.",
                    next = "q_mh"
                ),
                "q_mh" to AlgorithmNode(
                    type = "question",
                    textTr = "MH şüphesi var mı (taşikardi, rijidite, hızlı vücut sıcaklığı artışı, mikst asidoz)?",
                    options = listOf(
                        AlgorithmOption("Evet, MH şüphesi yüksek", "Yes", "mh_yes"),
                        AlgorithmOption("Hayır, mekanik hiperkapni", "No", "mh_no")
                    )
                ),
                "mh_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 Malign Hipertermi Kriz Protokolünü başlatın, tetikleyicileri (gazlar/süksinilkolin) kapatın, Dantrolen hazırlayın.",
                    next = "end"
                ),
                "mh_no" to AlgorithmNode(
                    type = "action",
                    textTr = "Dakika ventilasyonunu (frekans ve tidal volümü) artırın. Karbondioksit absorbanını (soda-lime) değiştirin.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "ETCO2 değerleri fizyolojik aralığa (35-45 mmHg) döndürüldü.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "intraop_cardiac_arrest",
            titleTr = "İntraoperatif Kardiyak Arrest Algoritması",
            titleEn = "Intraoperative Cardiac Arrest",
            category = "hemodynamics",
            urgencyLevel = "emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Kardiyak arrest (nabızsızlık, düz çizgi, ETCO2 ani düşüşü) doğrulandı mı?",
                    options = listOf(
                        AlgorithmOption("Evet, arrest doğrulandı", "Yes", "arrest_confirmed"),
                        AlgorithmOption("Hayır, şiddetli hipotansiyon var", "No", "severe_hypotension")
                    )
                ),
                "arrest_confirmed" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 MAVİ KOD VERİN! Cerrahiyi hemen durdurun, steril alanı koruyun. İnhaler anestezikleri kapatın, %100 O2 ile manuel ventilasyona geçin. Göğüs kompresyonlarına (CPR) başlayın.",
                    next = "q_rhythm"
                ),
                "q_rhythm" to AlgorithmNode(
                    type = "question",
                    textTr = "EKG ritmi nedir?",
                    options = listOf(
                        AlgorithmOption("Şoklanabilir Ritim (VF / Nabızsız VT)", "Shockable", "rhythm_shockable"),
                        AlgorithmOption("Şoklanamaz Ritim (Asistoli / PEA)", "Non-shockable", "rhythm_non_shockable")
                    )
                ),
                "rhythm_shockable" to AlgorithmNode(
                    type = "action",
                    textTr = "Derhal 200J (bifazik) şok uygulayın. CPR'a devam edin. 2. döngüden sonra IV Adrenalin 1 mg, 3. döngüden sonra IV Amiodaron 300 mg yapın.",
                    next = "q_rosc"
                ),
                "rhythm_non_shockable" to AlgorithmNode(
                    type = "action",
                    textTr = "CPR'a ara vermeden devam edin. Derhal IV Adrenalin 1 mg uygulayın (her 3-5 dakikada bir tekrarlayın). Geri döndürülebilir nedenleri (5T-5H) araştırın.",
                    next = "q_rosc"
                ),
                "q_rosc" to AlgorithmNode(
                    type = "question",
                    textTr = "Dolaşım geri döndü mü (ROSC)?",
                    options = listOf(
                        AlgorithmOption("Evet, ROSC sağlandı", "Yes", "rosc_yes"),
                        AlgorithmOption("Hayır, arrest devam ediyor", "No", "arrest_confirmed")
                    )
                ),
                "rosc_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "Post-arrest bakıma başlayın. Yakın hemodinamik takip, hedef sıcaklık yönetimi, arteriyel kan gazı takibi ve YBÜ nakli planlayın.",
                    next = "end"
                ),
                "severe_hypotension" to AlgorithmNode(
                    type = "action",
                    textTr = "Vazopressör bolusları (Adrenalin 10-50 mcg, Noradrenalin, Efdrin) verin ve agresif sıvı resüsitasyonuna başlayın.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Resüsitasyon yönetimi sonlandırıldı. Komplikasyonlar kaydedildi.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "hyperkalemia_emergency",
            titleTr = "Hiperkalemi Acil Tedavi Algoritması",
            titleEn = "Acute Hyperkalemia Treatment",
            category = "fluids",
            urgencyLevel = "emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Potasyum düzeyi > 6.0 mEq/L ve/veya EKG'de hiperkalemi bulguları (sivri T, PR uzaması, QRS genişlemesi) var mı?",
                    options = listOf(
                        AlgorithmOption("Evet, acil tedavi gerekir", "Yes", "hyperkalemia_confirmed"),
                        AlgorithmOption("Hayır, hafif hiperkalemi (<5.5 mEq/L)", "No", "mild_k")
                    )
                ),
                "hyperkalemia_confirmed" to AlgorithmNode(
                    type = "action",
                    textTr = "Miyokard membran stabilizasyonu için derhal IV Kalsiyum Glukonat (10% çözeltiden 10 mL, 5-10 dk içinde) veya IV Kalsiyum Klorür (3x daha güçlüdür, santral yoldan) uygulayın.",
                    next = "shift_k"
                ),
                "shift_k" to AlgorithmNode(
                    type = "action",
                    textTr = "Potasyumu hücre içine kaydırmak için: \n1. IV Glukoz + İnsülin (10 Ünite Regüler İnsülin + 50 mL 20% Dekstroz, 15-30 dk içinde). \n2. Nebülizatör ile 10-20 mg Salbutamol inhalasyonu.",
                    next = "q_acidosis"
                ),
                "q_acidosis" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastada belirgin metabolik asidoz (pH < 7.2) eşlik ediyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet, asidoz var", "Yes", "acidosis_yes"),
                        AlgorithmOption("Hayır, asidoz yok", "No", "elimination_k")
                    )
                ),
                "acidosis_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "IV Sodyum Bikarbonat (8.4% çözeltiden 50-100 mEq, yavaş IV) uygulayarak alkalizasyon ile potasyum düşüşünü destekleyin.",
                    next = "elimination_k"
                ),
                "elimination_k" to AlgorithmNode(
                    type = "action",
                    textTr = "Potasyum eliminasyonu için: Furosemid (20-40 mg IV), hemodiyaliz hazırlığı ve potasyum tutucu ilaçların kesilmesi adımlarını planlayın.",
                    next = "end"
                ),
                "mild_k" to AlgorithmNode(
                    type = "action",
                    textTr = "Potasyum içeren sıvıları (örn. İzotolon, Ringer Laktat) kesin. Takip kan gazı ve EKG kontrolü yapın.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Acil potasyum düşürücü tedavi uygulandı. Serum potasyumunu saatlik olarak takip edin.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "transfusion_reaction",
            titleTr = "Transfüzyon Reaksiyonu Algoritması",
            titleEn = "Transfusion Reaction Algorithm",
            category = "fluids",
            urgencyLevel = "emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Kan transfüzyonu sırasında reaksiyon bulguları (ani ateş artışı, hipotansiyon, ürtiker, bronkospazm, kırmızı/koyu idrar) ortaya çıktı mı?",
                    options = listOf(
                        AlgorithmOption("Evet, reaksiyon şüphesi var", "Yes", "reaction_yes"),
                        AlgorithmOption("Hayır, normal transfüzyon", "No", "end")
                    )
                ),
                "reaction_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 TRANSFÜZYONU DERHAL DURDURUN! Kan setini ve hortumlarını tamamen değiştirin. Damar yolunu serum fizyolojik ile açık tutun.",
                    next = "q_severity"
                ),
                "q_severity" to AlgorithmNode(
                    type = "question",
                    textTr = "Reaksiyonun klinik şiddeti nedir?",
                    options = listOf(
                        AlgorithmOption("Ağır Reaksiyon (Hipotansiyon, Anafilaksi, Hemoliz)", "Severe", "reaction_severe"),
                        AlgorithmOption("Hafif Reaksiyon (Sadece izole ateş veya ürtiker)", "Mild", "reaction_mild")
                    )
                ),
                "reaction_severe" to AlgorithmNode(
                    type = "action",
                    textTr = "Agresif sıvı resüsitasyonuna başlayın. IV Adrenalin (anafilakside 10-50 mcg bolus), IV Metilprednizolon (40-80 mg) ve IV Antihistaminik (Feniramin) uygulayın. İdrar çıkışını izleyin (Furosemid ile >1 mL/kg/saat hedefleyin).",
                    next = "send_labs"
                ),
                "reaction_mild" to AlgorithmNode(
                    type = "action",
                    textTr = "Kan transfüzyonunu kalıcı olarak kesin. Ateş için antipiretik (Parasetamol IV), kaşıntı için antihistaminik uygulayın. Durumu yakından izleyin.",
                    next = "end"
                ),
                "send_labs" to AlgorithmNode(
                    type = "action",
                    textTr = "Reaksiyonu doğrulamak için: Hastadan ve takılı kan torbasından kan örnekleri alarak kan merkezine gönderin. İdrarda serbest hemoglobin bakın. Coomb's testini tekrarlatın.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Transfüzyon reaksiyonu acil yönetimi tamamlandı. İlgili formları doldurun ve kan bankasını bilgilendirin.",
                    warningLevel = "critical"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "extubation_guidelines",
            titleTr = "Ekstübasyon Hazırlığı ve Zor Ekstübasyon Algoritması",
            titleEn = "Extubation Guidelines & Difficult Extubation",
            category = "airway",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta genel ekstübasyon kriterlerini (bilincin açık olması, komutları takip etmesi, yeterli ventilasyon (Tidal volüm >5-6 mL/kg), stabil hemodinami, rezidüel nöromüsküler bloğun geri çevrilmesi (TOF >%90)) karşılıyor mu?",
                    options = listOf(
                        AlgorithmOption("Evet, tüm kriterler uygun", "Yes", "criteria_met"),
                        AlgorithmOption("Hayır, kriterler yetersiz", "No", "criteria_not_met")
                    )
                ),
                "criteria_met" to AlgorithmNode(
                    type = "action",
                    textTr = "Kaf sızıntı testini (Cuff Leak Test) değerlendirin. Ağız içi ve yutağı aspire edin. Kafı söndürün ve hastayı inspirasyon pikinde nazikçe ekstübe edin.",
                    next = "post_extubation"
                ),
                "post_extubation" to AlgorithmNode(
                    type = "action",
                    textTr = "Hemen %100 O2 maskesi uygulayın. Hastanın solunum eforunu, ses tellerini (stridor varlığı) ve oksijen satürasyonunu takip edin.",
                    next = "end"
                ),
                "criteria_not_met" to AlgorithmNode(
                    type = "action",
                    textTr = "Ekstübasyon kriterleri tamamlanana kadar hastayı ventile etmeye devam edin. Nöromüsküler blok reversalini (Sugammadeks) ve sedasyon derinliğini gözden geçirin.",
                    next = "q_difficult_airway"
                ),
                "q_difficult_airway" to AlgorithmNode(
                    type = "question",
                    textTr = "Hasta başlangıçta zor havayolu olarak mı tanımlanmıştı?",
                    options = listOf(
                        AlgorithmOption("Evet, zor havayolu hastası", "Yes", "difficult_airway_yes"),
                        AlgorithmOption("Hayır, normal havayolu hastası", "No", "end")
                    )
                ),
                "difficult_airway_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 ZOR EKSTÜBASYON: Havayolu değişim kateteri (Airway Exchange Catheter) yerleştirmeyi düşünün veya uyanık ekstübasyon planlayın. Yeniden entübasyon ekipmanlarını hazır bulundurun.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Güvenli ekstübasyon süreci başarıyla yönetildi.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "postop_hypoxemia",
            titleTr = "Postoperatif Hipoksemi / PACU Solunum Depresyonu",
            titleEn = "Postoperative Hypoxemia in PACU",
            category = "airway",
            urgencyLevel = "urgent_emergency",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "PACU'da hastanın SpO2 < 90% ve/veya solunum sayısı < 8/dk mı?",
                    options = listOf(
                        AlgorithmOption("Evet, belirgin solunum depresyonu var", "Yes", "hypoxemia_yes"),
                        AlgorithmOption("Hayır, normal solunum", "No", "end")
                    )
                ),
                "hypoxemia_yes" to AlgorithmNode(
                    type = "action",
                    textTr = "Hemen yüksek akışlı oksijen desteği (%100 O2 maskesi) başlayın. Havayolu manevraları uygulayın (Jaw thrust, chin lift). Hastayı uyarın.",
                    next = "q_cause"
                ),
                "q_cause" to AlgorithmNode(
                    type = "question",
                    textTr = "Solunum depresyonunun birincil nedeni nedir?",
                    options = listOf(
                        AlgorithmOption("Artık Opioid Etkisi (Pupiller iğne ucu, yavaş solunum)", "Opioid", "cause_opioid"),
                        AlgorithmOption("Artık Nöromüsküler Blok (Gevşeklik, baş kaldıramama)", "NMB", "cause_nmb"),
                        AlgorithmOption("Havayolu Obstrüksiyonu (Stridor, apneik nöbet)", "Obstruction", "cause_obstruction")
                    )
                ),
                "cause_opioid" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 Opioid Reversali uygulayın: IV Nalokson (0.04 - 0.08 mg yavaş boluslar halinde, solunum düzelene kadar 2-3 dakikada bir tekrarlayın). Şiddetli solunum depresyonunu geri çevirin.",
                    next = "end"
                ),
                "cause_nmb" to AlgorithmNode(
                    type = "action",
                    textTr = "Rezidüel bloğu kontrol edin (TOF testi). Gerekliyse ek reversal dozu (IV Sugammadeks 2-4 mg/kg) uygulayın.",
                    next = "end"
                ),
                "cause_obstruction" to AlgorithmNode(
                    type = "action",
                    textTr = "Nazofaringeal veya orofaringeal airway yerleştirin. CPAP/BiPAP desteği düşünün. Laringospazm şüphesinde ilgili kriz algoritmasını uygulayın.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Oksijenasyon ve ventilasyon normal sınırlara getirildi. Hasta uyanık ve SpO2 >94% olana kadar PACU'da tutulmalıdır.",
                    warningLevel = "moderate"
                )
            ),
            requiresClinicalValidation = false
        ),
        Algorithm(
            algorithmId = "diabetic_glucose_management",
            titleTr = "Diyabetik Hastada Perioperatif Glukoz Yönetimi",
            titleEn = "Perioperative Glucose Management",
            category = "scores",
            urgencyLevel = "elective_urgent",
            estimatedMinutes = 3,
            startNode = "q1",
            nodes = mapOf(
                "q1" to AlgorithmNode(
                    type = "question",
                    textTr = "Hastanın ameliyat günü ölçülen kapiller kan şekeri düzeyi nedir?",
                    options = listOf(
                        AlgorithmOption("Hipoglisemi (< 70 mg/dL)", "Hypoglycemia", "glucose_low"),
                        AlgorithmOption("Normal / Kabul edilebilir (70 - 180 mg/dL)", "Normal", "glucose_normal"),
                        AlgorithmOption("Hiperglisemi (> 180 mg/dL)", "Hyperglycemia", "glucose_high")
                    )
                ),
                "glucose_low" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 HİPOGLİSEMİ TEDAVİSİ: Derhal IV %20 Dekstroz (50-100 mL) veya IV %10 Dekstroz (100-200 mL) bolus uygulayın. 15 dakika sonra kan şekerini tekrar ölçün.",
                    next = "end"
                ),
                "glucose_normal" to AlgorithmNode(
                    type = "action",
                    textTr = "Kan şekerini cerrahi boyunca 2-4 saatte bir takip edin. Olağandışı dalgalanmalar dışında ek tedavi gerekmez.",
                    next = "end"
                ),
                "glucose_high" to AlgorithmNode(
                    type = "action",
                    textTr = "Kan şekeri >180 mg/dL ise: İnsülin tedavisi planlanmalıdır.",
                    next = "q_high_level"
                ),
                "q_high_level" to AlgorithmNode(
                    type = "question",
                    textTr = "Hiperglisemi düzeyi nedir?",
                    options = listOf(
                        AlgorithmOption("180 - 250 mg/dL", "180-250", "high_moderate"),
                        AlgorithmOption("250 - 350 mg/dL", "250-350", "high_severe"),
                        AlgorithmOption("> 350 mg/dL (Kritik düzey)", "350+", "high_critical")
                    )
                ),
                "high_moderate" to AlgorithmNode(
                    type = "action",
                    textTr = "IV Regüler (Kısa etkili) İnsülin uygulayın: Yaklaşık 2-4 Ünite IV bolus yapın. 2 saat sonra kan şekerini kontrol edin.",
                    next = "end"
                ),
                "high_severe" to AlgorithmNode(
                    type = "action",
                    textTr = "IV Regüler İnsülin uygulayın: Yaklaşık 4-6 Ünite IV bolus yapın. Sıvı hidrasyonunu artırın. 1-2 saat sonra kan şekerini kontrol edin.",
                    next = "end"
                ),
                "high_critical" to AlgorithmNode(
                    type = "action",
                    textTr = "🚨 KRİTİK HİPERGLİSEMİ: IV İnsülin İnfüzyon Protokolü başlatın (0.05-0.1 Ünite/kg/saat regüler insülin). Potasyum düzeylerini ve kan gazını yakından izleyin. Ketoasidoz (DKA) riskini değerlendirin.",
                    next = "end"
                ),
                "end" to AlgorithmNode(
                    type = "end",
                    textTr = "Perioperatif kan şekeri hedef aralıkta (100-180 mg/dL) stabilize edildi.",
                    warningLevel = "none"
                )
            ),
            requiresClinicalValidation = false
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
}
