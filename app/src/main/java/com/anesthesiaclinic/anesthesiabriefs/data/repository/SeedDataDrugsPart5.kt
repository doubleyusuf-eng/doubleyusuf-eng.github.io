package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo

object SeedDataDrugsPart5 {
    val drugsList = listOf(
        Drug(
            drugId = "prothrombin_complex_concentrate",
            nameTr = "Protrombin kompleks konsantresi (insan)",
            nameEn = "Prothrombin complex concentrate (human)",
            genericName = "Prothrombin complex concentrate, 4-factor, unactivated, from human plasma",
            brandNames = listOf(
                "Βаlfахаr;",
                "Κсеntrа"
            ),
            category = "coagulation",
            atcCode = null,
            drugClass = "4 Faktörlü Koagülasyon Konsantresi",
            mechanismTr = "Faktör II, VII, IX, X ve Protein C/S içerir. Warfarin etkisini hızla geri çevirir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Vitamin K antagonisti geri çevirme (akut majör kanama veya acil cerrahi): IV (doz faktör IX aktivitesi birimiyle ifade edilir): INR 2 to <4: 25 unit/kg (maks 2.500 unit); INR 4-6: 35 unit/kg (maks 3.500 unit); INR >6: 50 unit/kg (maks 5.000 unit). Sabit doz: Başlangıç 1.000-2.000 unit (intrakraniyal kanamada 1.500-2.000 unit). • Direkt faktör Xa inhibitörüne bağlı yaşamı tehdit eden kanama (off-label, andeksanet alfa yoksa): IV: 2.000 unit veya 25-50 unit/kg. • Perioperatif koagülopati (off-label): Kardiyak cerrahi: IV: 15-25 unit/kg tek doz.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Octaplex [Kanada ürünü]: Adölesan ≥17 yaş: Erişkin dozuna bakınız. (Pediatrik doz ayrıca belirtilmemiş.)", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Hızlı; 30 dakika içinde belirgin INR düşüşü",
                peak = "belirtilmemiş",
                duration = "INR düzelmesi genellikle ≥24 saat",
                halfLife = "Faktör II 48-60 saat; Faktör VII 1.5-6 saat; Faktör IX 20-24 saat; Faktör X 24-48 saat; Protein C 1.5-6 saat; Protein S 24-48 saat",
                elimination = "belirtilmemiş (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Hipersensitivite reaksiyonları: Anjioödem, bronkospazm, hipotansiyon, ürtiker olabilir; ciddi reaksiyonda kesilir. • Hiperkoagülopati: Vitamin K antagonisti alanlarda altta yatan hiperkoagülabl durumları kötüleştirebilir; tromboembolik olaylar bildirilmiştir. • Heparin içerebilir. • İnsan plazma ürünü: bulaşıcı ajan taşıma potansiyeli. • Faktör VII içerir; faktör IX kompleksi (Bebulin, Profilnine) ile karıştırılmamalıdır."
            ),
            sideEffectsCommon = listOf(
                "Asteni (%12)",
                "atriyal fibrilasyon (%4), hipotansiyon (%7), taşikardi (%5), tromboz, DVT",
                "baş ağrısı (%7), inme (%1-2)",
                "anemi",
                "tromboembolik komplikasyonlar (MI, arteriyel tromboz, pulmoner emboli), DIC",
                "hipersensitivite/anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Hipersensitivite reaksiyonları: Anjioödem, bronkospazm, hipotansiyon, ürtiker olabilir; ciddi reaksiyonda kesilir. • Hiperkoagülopati: Vitamin K antagonisti alanlarda altta yatan hiperkoagülabl durumları kötüleştirebilir; tromboembolik olaylar bildirilmiştir. • Heparin içerebilir. • İnsan plazma ürünü: bulaşıcı ajan taşıma potansiyeli. • Faktör VII içerir; faktör IX kompleksi (Bebulin, Profilnine) ile karıştırılmamalıdır."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = true
        ),
        Drug(
            drugId = "protamine",
            nameTr = "Protamin sülfat",
            nameEn = "Protamine sulfate",
            genericName = "Protamine sulfate",
            brandNames = emptyList(),
            category = "coagulation",
            atcCode = null,
            drugClass = "Heparin Antagonisti (Reversal)",
            mechanismTr = "Pozitif yüklü protamin molekülü, negatif yüklü heparin ile iyonik olarak birleşerek heparini nötralize eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Heparin nötralizasyonu (IV heparin sonrası): IV: 1 mg protamin ~100 unit heparini nötralize eder; maks tek doz: 50 mg; yavaş IV enjeksiyon, 5 mg/dakikayı geçmez. Ek dozlar gerekirse 0.5 mg protamin / 100 unit heparin. Heparin yarılanma ömrü kısa olduğundan, geçen süreye göre doz ayarlanır. • Kardiyak cerrahi: CPB sonrası ACT yüksekse 25-100 mg tekrar protamin dozları (maks toplam: 3 mg/kg). • LMWH nötralizasyonu (off-label): Enoksaparin ≤8 saat içinde verildiyse: 1 mg protamin / 1 mg enoksaparin (maks: 50 mg).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Heparin (IV) nötralizasyonu (sınırlı veri): Bebek/Çocuk/Adölesan: IV (son heparin dozundan geçen süreye göre): <30 dk: 1 mg protamin / 100 unit heparin (maks: 50 mg/doz); 30-60 dk: 0.5-0.75 mg/100 unit; 60-120 dk: 0.375-0.5 mg/100 unit; >120 dk: 0.25-0.375 mg/100 unit. • LMWH nötralizasyonu: Enoksaparin ≤8 saat: 1 mg protamin / 1 mg enoksaparin; >8 saat: 0.5 mg/1 mg.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV heparin nötralizasyonu ~5 dakika",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "~7 dakika",
                elimination = "belirtilmemiş (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Protamine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "İnfüzyon reaksiyonları: Çok hızlı uygulama ciddi hipotansif ve anafilaktoid reaksiyonlara neden olabilir; herhangi 10 dakikalık sürede maks 50 mg. • Heparin geri tepmesi (rebound): Antikoagülasyon ve kanama ile heparin rebound olabilir; semptomlar genellikle protamin sonrası 8-9 saatte, 18 saate kadar geç çıkabilir. • Hipersensitivite reaksiyonları (uygun tedavi hazır bulundurulur). • Kardiyak cerrahi hastalarında yeterli dozda bile etkisiz olabilir."
            ),
            sideEffectsCommon = listOf(
                "Flushing, bulantı, kusma, lokal sıcaklık, dispne",
                "bradikardi, dolaşım şoku, hipotansiyon (ciddi olabilir), sağ kalp yetmezliği, ventriküler fibrilasyon",
                "immün trombositopeni",
                "hipersensitivite/anafilaksi",
                "nonkardiyojenik pulmoner ödem, pulmoner hipertansiyon (akut)."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "İnfüzyon reaksiyonları: Çok hızlı uygulama ciddi hipotansif ve anafilaktoid reaksiyonlara neden olabilir; herhangi 10 dakikalık sürede maks 50 mg. • Heparin geri tepmesi (rebound): Antikoagülasyon ve kanama ile heparin rebound olabilir; semptomlar genellikle protamin sonrası 8-9 saatte, 18 saate kadar geç çıkabilir. • Hipersensitivite reaksiyonları (uygun tedavi hazır bulundurulur). • Kardiyak cerrahi hastalarında yeterli dozda bile etkisiz olabilir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "heparin",
            nameTr = "Heparin (fraksiyone olmayan)",
            nameEn = "Heparin (unfractionated)",
            genericName = "Heparin (unfractionated)",
            brandNames = emptyList(),
            category = "coagulation",
            atcCode = null,
            drugClass = "Antikoagülan (Glukozaminoglikan)",
            mechanismTr = "Antitrombin III'ü aktive ederek Faktör IIa (trombin) ve Xa'yı inhibe eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Atriyal fibrilasyon (köprüleme): IV: Başlangıç bolus 60-80 unit/kg (maks: 5.000 unit), ardından sürekli infüzyon 12-18 unit/kg/saat (maks: 1.000 unit/saat); antikoagülasyon hedefine göre ayarlanır. • ECMO (devre antikoagülasyonu): IV: Bolus 50-100 unit/kg veya 2.500-5.000 unit kanülasyonda, ardından sürekli infüzyon 8-12 unit/kg/saat (maks: 1.600 unit/saat); anti-Xa 0.3-0.5 unit/mL veya aPTT 50-80 saniyeye titre edilir. • Antibiyotik lock (off-label): 100-5.000 unit/mL. Güvenlik: Birçok konsantrasyon mevcut (1-20.000 unit/mL); doğru konsantrasyon dikkatle seçilir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• ECMO (venoarteriyel/kardiyak): Bebek/Çocuk/Adölesan: IV: 100 unit/kg kanülasyon öncesi, ardından ACT 180-220 saniyede tutacak sürekli IV infüzyon (alternatif hedefler: PTT 1.5-2.5× kontrol veya anti-Xa 0.3-0.7 unit/mL). • Sistemik-pulmoner şant veya yüksek riskli santral venöz kateterlerde tromboprofilaksi: Sürekli IV infüzyon: 10-15 unit/kg/saat. • Santral hat flush/açıklık (sınırlı veri): kuruma göre protokol. • Parenteral nutrisyon katkı maddesi: 1 unit/mL.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Antikoagülasyon IV anında; SC ~20-30 dakika",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Ortalama 1.5 saat (1-2 saat aralık); doz bağımlı (25 unit/kg: 30 dk; 100 unit/kg: 60 dk; 400 unit/kg: 150 dk)",
                elimination = "Kompleks; retiküloendotelyal sistem (karaciğer, dalak) depolimerizasyon/desülfasyon (Eliminasyon yeri: İdrar (küçük miktarda değişmemiş; terapötik dozda nonrenal mekanizma baskın))"
            ),
            contraindications = listOf(
                "Aktif ciddi kanama",
                "Şiddetli trombositopeni (HIT öyküsü)",
                "Kontrolsüz ağır hipertansiyon"
            ),
            warnings = listOf(
                "Fatal ilaç hataları: Birçok konsantrasyon mevcut (1-20.000 unit/mL); her şırınga/flakon dikkatle incelenmeli; özellikle pediatrik hastalarda heparin aşırı dozuna bağlı fatal kanamalar olmuştur. • Heparin direnci: >35.000 unit/24 saat gerekirse antitrombin eksikliği vb. düşünülür. • Hipersensitivite reaksiyonları (anafilaksi dahil); bazı ürünler hayvan kaynaklı (domuz) ve hayvan alerjisinde kontrendike olabilir. • Bazı formlarda benzil alkol (neonatlarda kontrendike) ve sülfit."
            ),
            sideEffectsCommon = listOf(
                "Hemoraji (adrenal, over, retroperitoneal)",
                "heparine bağlı trombositopeni (HIT), HIT'te tromboz (MI, inme, DVT, pulmoner emboli, cilt nekrozu)",
                "hiperkalemi, aldosteron sentezi baskılanması",
                "karaciğer enzim yükselmesi",
                "SC enjeksiyon yeri reaksiyonları",
                "anafilaksi",
                "uzun kullanımda osteoporoz."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Fatal ilaç hataları: Birçok konsantrasyon mevcut (1-20.000 unit/mL); her şırınga/flakon dikkatle incelenmeli; özellikle pediatrik hastalarda heparin aşırı dozuna bağlı fatal kanamalar olmuştur. • Heparin direnci: >35.000 unit/24 saat gerekirse antitrombin eksikliği vb. düşünülür. • Hipersensitivite reaksiyonları (anafilaksi dahil); bazı ürünler hayvan kaynaklı (domuz) ve hayvan alerjisinde kontrendike olabilir. • Bazı formlarda benzil alkol (neonatlarda kontrendike) ve sülfit."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "vitamin_k",
            nameTr = "Vitamin K1 (Fitomenadion)",
            nameEn = "Vitamin K1 (Phytonadione, Phytomenadione)",
            genericName = "Vitamin K1 (phytonadione, phytomenadione)",
            brandNames = listOf(
                "Меphytοո"
            ),
            category = "coagulation",
            atcCode = null,
            drugClass = "Pıhtılaşma Faktörü Kofaktörü (Vitamin K1)",
            mechanismTr = "Faktör II, VII, IX ve X'un karaciğerde sentezlenmesi için gereklidir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Warfarin/kumarin antikoagülasyonunun geri çevrilmesi: Doz ve yol kanama şiddeti ve/veya INR'ye bağlıdır. Acil geri çevirme: IV önerilir; yaşamı tehdit eden kanamada pıhtılaşma faktörleri (4-faktör PCC veya TDP) ile kombine. Acil olmayan geri çevirme: Oral önerilir (INR ~24-48 saatte düşer).\n• Olağan doz aralığı – Oral: Başlangıç 2.5-10 mg (INR'ye göre), tek doz; INR'yi 12-48 saat sonra ölç.\n• IV: Başlangıç 2.5-10 mg (INR ve kanama şiddetine göre), 10-20 dakikada tek doz (maksimum infüzyon hızı: 1 mg/dakika); INR'yi 6-12 saat sonra ölç.\n• Majör/yaşamı tehdit eden kanama: IV 10 mg, 10-20 dakikada (maks 1 mg/dakika), 4-faktör PCC ile birlikte.\n• Güvenlik: Anafilaksi dahil ciddi infüzyon reaksiyonu riski nedeniyle maksimum IV uygulama hızı 1 mg/dakika. Not: Yüksek dozlar (>10-15 mg) ≥1 hafta warfarin direncine yol açabilir. SUBQ önerilmez (öngörülemeyen emilim); IM kaçınılır (hematom riski).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Parenteral beslenme idame ihtiyacı: Bebekler: IV 10 mcg/kg/gün; Çocuk ve adölesanlar: IV 200 mcg/gün (parenteral beslenme çözeltisine katkı).\n• Vitamin K antagonistlerinin geri çevrilmesi (ağırlığa göre, tercih edilen): Bebek, çocuk, adölesan: IV 0.03 mg/kg/doz (aşırı uzamış INR için, genellikle INR >8).\n• Sabit dozlama: Kanama yok, hızlı geri çevirme, ileri oral antikoagülan gerekecek: SUBQ/IV 0.5-2 mg; ileri antikoagülan gerekmeyecek: SUBQ/IV 2-5 mg. Önemli kanama (yaşamı tehdit etmeyen): SUBQ/IV 0.5-2 mg (TDP ile). Yaşamı tehdit eden: SUBQ/IV 5 mg (PCC ile).\n• Kolestaz: Oral 2.4-15 mg/gün. IM yolu hematom riski nedeniyle kaçınılır (K eksikliği kanaması profilaksisi/tedavisi hariç).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Artmış pıhtılaşma faktörleri: Oral 6-10 saat; IV 1-2 saat",
                peak = "INR normale döner: Oral 24-48 saat; IV 12-14 saat",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "Hızlı hepatik metabolizma (Eliminasyon yeri: İdrar ve feçeste metabolit olarak)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Aşırı duyarlılık/anafilaktoid reaksiyonlar: Anafilaksi, göğüs ağrısı, siyanoz, dispne, şok, kardiyorespiratuar arrest ve ölüm bildirilmiştir; özellikle polietoksillenmiş hint yağı içeren formülasyonların hızlı yüksek IV dozlarında. Uygun dozlama, dilüsyon ve yavaş uygulama riski azaltır.",
                "Yenidoğanlar: Özellikle prematürelerde dikkatli kullanılmalı; yüksek dozlarla ağır hemolitik anemi, sarılık, hiperbilirubinemi bildirilmiştir."
            ),
            sideEffectsCommon = listOf(
                "Göğüs ağrısı, yüz kızarması, hipotansiyon, taşikardi, baş dönmesi, terleme, döküntü, ürtiker, kaşıntı",
                "hiperbilirubinemi",
                "aşırı duyarlılık/anafilaktoid reaksiyon, anafilaksi",
                "enjeksiyon bölgesi reaksiyonu",
                "siyanoz, dispne."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aşırı duyarlılık/anafilaktoid reaksiyonlar: Anafilaksi, göğüs ağrısı, siyanoz, dispne, şok, kardiyorespiratuar arrest ve ölüm bildirilmiştir; özellikle polietoksillenmiş hint yağı içeren formülasyonların hızlı yüksek IV dozlarında. Uygun dozlama, dilüsyon ve yavaş uygulama riski azaltır.",
                "Dermatolojik toksisite: Parenteral uygulamadan sonra gecikmiş tip aşırı duyarlılık, ekzematöz reaksiyon, skleroderma benzeri lekeler; reaksiyonda tedaviyi kes.",
                "Antikoagülan kaynaklı koagülopati: VKA alan hastalarda warfarin direncine yol açmadan INR'yi güvenli aralığa düşürecek doz uygulanmalı; yüksek dozlar (>10-15 mg) ≥1 hafta warfarin direnci yapabilir.",
                "Yenidoğanlar: Özellikle prematürelerde dikkatli kullanılmalı; yüksek dozlarla ağır hemolitik anemi, sarılık, hiperbilirubinemi bildirilmiştir.",
                "Karaciğer hastalığı/herediter hipoprotrombinemi: Etkisizdir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "oxytocin",
            nameTr = "Oksitosin",
            nameEn = "Oxytocin",
            genericName = "Oxytocin",
            brandNames = listOf(
                "Рitοϲin"
            ),
            category = "obstetric",
            atcCode = null,
            drugClass = "Uterotonik (Oksitosin Hormon Analoğu)",
            mechanismTr = "Uterus düz kaslarındaki oksitosin reseptörlerini uyararak ritmik kasılmalar sağlar (postpartum kanamayı önler).",
            adultDoses = mapOf("dozaj" to DoseInfo("• Doğum indüksiyonu/güçlendirmesi: Sürekli fetal ve uterin monitörizasyon gerekir. Düşük doz rejimi (endikasyon dışı): IV başlangıç 0.5-2 milliünite/dakika; her 15-40 dakikada 1-2 milliünite/dakika artır. Yüksek doz rejimi: IV başlangıç 4-6 milliünite/dakika; her 15-40 dakikada 3-6 milliünite/dakika artır. Maksimum: net belirlenmemiş; birçok protokol 40 milliünite/dakika önerir (önceki sezaryende ≤20 milliünite/dakika tercih edilebilir).\n• Medikal abortus, ikinci trimester (alternatif, endikasyon dışı): IV başlangıç 3 saatte 20-100 ünite, ardından diürez için 1 saat dur; her 3 saatte hızı artır (örn. 50 ünite/3 saat); maksimum hız 300 ünite/3 saat.\n• Postpartum uterin kanama – Önleme: IV 30 dakikada 10 ünite, sonra hızı 7.5 ünite/saate düşür. Alternatif: yavaş IV bolus 1-3 ünite, ardından idame infüzyon en fazla 7.5 ünite/saat; uterin ton korunursa 1-2.5 ünite/saate düşür. Genellikle 2-4 saat (yüksek riskte 8 saate kadar).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("PDF'te belirtilmemiş", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Uterin kontraksiyonlar: IM 3-5 dakika; IV ~1 dakika",
                peak = "belirtilmemiş",
                duration = "IM 2-3 saat; IV 1 saat",
                halfLife = "1-6 dakika (geç gebelik ve laktasyonda azalır)",
                elimination = "belirtilmemiş (Eliminasyon yeri: İdrar (az miktar değişmeden))"
            ),
            contraindications = listOf(
                "Uterus rüptürü riski, sefalopelvik disproporsiyon",
                "Şiddetli preeklampsi"
            ),
            warnings = listOf(
                "Antidiüretik etki: İntrinsik antidiüretik etki (su intoksikasyonu) yapabilir; özellikle yüksek dozlarda (40-50 milliünite/dakika) 24 saat boyunca yavaş infüzyon ve ek sıvıyla konvülziyon, koma ve ölüm görülebilir.",
                "Kardiyovasküler etkiler: Aritmi, hipotansiyon, miyokardiyal iskemi, periferik vazodilatasyon, taşikardi bildirilmiştir; kardiyovasküler hastalık veya hemodinamik instabilitede aşırı dikkatle kullanın.",
                "Maternal ölümler: Hipertansif epizodlar, subaraknoid kanama veya uterus rüptürü kaynaklı maternal/fetal ölümler oksitosik ilaçlarla görülmüştür."
            ),
            sideEffectsCommon = listOf(
                "Kardiyak aritmi, hipertansif kriz, hipotansiyon, subaraknoid kanama, taşikardi, ventriküler prematür kontraksiyonlar",
                "su intoksikasyonu (yavaş infüzyonla ağır su zehirlenmesi, nöbet, koma)",
                "bulantı, kusma",
                "postpartum kanama, uterus rüptürü",
                "pelvik hematom",
                "anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Antidiüretik etki: İntrinsik antidiüretik etki (su intoksikasyonu) yapabilir; özellikle yüksek dozlarda (40-50 milliünite/dakika) 24 saat boyunca yavaş infüzyon ve ek sıvıyla konvülziyon, koma ve ölüm görülebilir.",
                "Kardiyovasküler etkiler: Aritmi, hipotansiyon, miyokardiyal iskemi, periferik vazodilatasyon, taşikardi bildirilmiştir; kardiyovasküler hastalık veya hemodinamik instabilitede aşırı dikkatle kullanın.",
                "Maternal ölümler: Hipertansif epizodlar, subaraknoid kanama veya uterus rüptürü kaynaklı maternal/fetal ölümler oksitosik ilaçlarla görülmüştür.",
                "Uterin etkiler: Yüksek doz veya aşırı duyarlılık uterin hipertonisite, spazm, tetanik kontraksiyon veya rüptüre yol açabilir.",
                "Uygun kullanım: Fetal distres, hidramnios, parsiyel plasenta previa, prematürite, sınırda sefalopelvik disproporsiyon veya uterus rüptürüne yatkınlık durumlarında indüksiyon genellikle önerilmez.",
                "Eğitimli personel ve hata önleme için tek standardize konsantrasyon önerilir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "methylergometrine",
            nameTr = "Metilergonovin",
            nameEn = "Methylergonovine",
            genericName = "Methylergonovine (methylergometrine)",
            brandNames = listOf(
                "Меthergine"
            ),
            category = "obstetric",
            atcCode = null,
            drugClass = "Ergot Alkaloidi Uterotonik",
            mechanismTr = "Uterus düz kasını doğrudan uyararak sürekli tonik kasılma sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Postpartum kanama: Oral 0.2 mg puerperiumda günde 3-4 kez (maksimum süre: 1 hafta).\n• IM, IV: 0.2 mg ön omuz doğumundan sonra, plasenta doğumundan sonra veya puerperiumda; gerektikçe her 2-4 saatte tekrarlanabilir. Not: IV uygulama yalnızca yaşamı tehdit eden durumlarda düşünülmelidir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("PDF'te belirtilmemiş", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Oksitosik etki: Oral 5-10 dakika; IM 2-5 dakika; IV anında",
                peak = "Serum doruk zamanı: Oral 1.12±0.82 saat; IM 0.41±0.21 saat",
                duration = "belirtilmemiş",
                halfLife = "IM 3.39 saat (aralık 1.5-12.7 saat)",
                elimination = "Hepatik metabolizma (Eliminasyon yeri: İdrar ve feçes)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Koroner arter hastalığı: KAH veya KAH risk faktörü (diyabet, yüksek kolesterol, obezite, sigara) olanlarda metilergonovin kaynaklı vazospazm sonrası miyokardiyal iskemi/infarktüs gelişebilir.",
                "Doğumun ikinci evresinde dikkatle kullanın.",
                "Sepsis ve obliteratif vasküler hastalıkta dikkatle kullanın.",
                "IV uygulama: Ani hipertansif ve serebrovasküler olay riski nedeniyle rutin IV uygulamaya uygun değildir; yalnızca yaşamı tehdit eden durumlarda düşünülmelidir, en az 60 saniyede yavaş ve kan basıncı izlenerek verilmeli; intraarteriyel/periarteriyel enjeksiyondan kaçınılmalı."
            ),
            sideEffectsCommon = listOf(
                "Hipertansiyon, hipotansiyon, palpitasyon, taşikardi, tromboflebit, vazokonstriksiyon, vazospazm",
                "terleme, döküntü",
                "su intoksikasyonu",
                "karın ağrısı, ishal, bulantı, kusma",
                "baş dönmesi, baş ağrısı, halüsinasyon, nöbet",
                "dispne. Pazarlama sonrası: akut MI, anjina, AV blok, koroner arter vazospazmı, ventriküler fibrilasyon/taşikardi, anafilaksi, serebrovasküler olay."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Koroner arter hastalığı: KAH veya KAH risk faktörü (diyabet, yüksek kolesterol, obezite, sigara) olanlarda metilergonovin kaynaklı vazospazm sonrası miyokardiyal iskemi/infarktüs gelişebilir.",
                "Doğumun ikinci evresinde dikkatle kullanın.",
                "Sepsis ve obliteratif vasküler hastalıkta dikkatle kullanın.",
                "IV uygulama: Ani hipertansif ve serebrovasküler olay riski nedeniyle rutin IV uygulamaya uygun değildir; yalnızca yaşamı tehdit eden durumlarda düşünülmelidir, en az 60 saniyede yavaş ve kan basıncı izlenerek verilmeli; intraarteriyel/periarteriyel enjeksiyondan kaçınılmalı.",
                "Medikasyon hataları: Yenidoğanlara yanlışlıkla uygulama bildirilmiştir.",
                "Belirgin ilaç etkileşimleri mevcut (doz/sıklık ayarı veya kaçınma gerektirir)."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = true
        ),
        Drug(
            drugId = "carboprost",
            nameTr = "Karboprost",
            nameEn = "Carboprost",
            genericName = "Carboprost tromethamine",
            brandNames = listOf(
                "Ηеmаbate"
            ),
            category = "obstetric",
            atcCode = null,
            drugClass = "Prostaglandin F2-Alfa Analoğu Uterotonik",
            mechanismTr = "Uterus miyometriyumunu uyararak güçlü kasılmalar sağlar. Dirençli postpartum kanamada kullanılır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Dirençli postpartum uterin kanama: IM başlangıç 250 mcg; gerekirse 15-90 dakika aralıklarla tekrarlanabilir; maksimum toplam doz 2 mg (8 doz).\n• Gebelik sonlandırma: IM 250 mcg, sonra uterin yanıta göre 1.5-3.5 saat aralıklarla 250 mcg; birkaç 250 mcg dozundan sonra yanıt yetersizse 500 mcg verilebilir; toplam 12 mg'ı aşma veya >2 gün sürekli uygulama yapma. Not: 100 mcg test dozu düşünülebilir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("PDF'te belirtilmemiş", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "Serum doruk zamanı: IM 30 dakika",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "belirtilmemiş (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "[ABD Kutulu Uyarı] Güçlü oksitosik ajan; yalnızca önerilen dozlamaya kesin uyumla kullanılmalı. Karboprost yalnızca acil yoğun bakım ve akut cerrahi olanağı bulunan hastanelerde, tıbbi eğitimli personel tarafından kullanılmalıdır.",
                "Ateş: Hipotalamik termoregülasyon etkisine bağlı geçici ateş olabilir; ilaç ateşini postabortus endometritten ayırt etmek için dikkat.",
                "Astım: Astım öyküsünde dikkatle kullanın; geçici bronkokonstriksiyon yapabilir.",
                "Skarlı uterus, kardiyovasküler hastalık, diyabet, nöbet öyküsü olanlarda dikkatle kullanın."
            ),
            sideEffectsCommon = listOf(
                "Düz kas kontraktilitesi artışına bağlı etkiler en sık (genellikle geçici): ishal (~%67), kusma (~%67), bulantı (~%33)",
                "ateş, titreme, üşüme",
                "göğüs ağrısı/sıkışma, hipertansiyon, palpitasyon, taşikardi, senkop",
                "bronkospazm, dispne, öksürük",
                "baş ağrısı, baş dönmesi",
                "uterin perforasyon/rüptür",
                "enjeksiyon bölgesi ağrısı. Nadir: aşırı duyarlılık (anafilaktik şok, anjiyoödem)."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "[ABD Kutulu Uyarı] Güçlü oksitosik ajan; yalnızca önerilen dozlamaya kesin uyumla kullanılmalı. Karboprost yalnızca acil yoğun bakım ve akut cerrahi olanağı bulunan hastanelerde, tıbbi eğitimli personel tarafından kullanılmalıdır.",
                "Ateş: Hipotalamik termoregülasyon etkisine bağlı geçici ateş olabilir; ilaç ateşini postabortus endometritten ayırt etmek için dikkat.",
                "Gastrointestinal etkiler: GI yan etkileri azaltmak için ön tedavi/eş zamanlı antiemetik ve antidiyareik ajan önerilir.",
                "Hipertansiyon: Tedaviyle artmış kan basıncı görülebilir (genellikle orta derece).",
                "Astım: Astım öyküsünde dikkatle kullanın; geçici bronkokonstriksiyon yapabilir.",
                "Skarlı uterus, kardiyovasküler hastalık, diyabet, nöbet öyküsü olanlarda dikkatle kullanın."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = true
        ),
        Drug(
            drugId = "misoprostol",
            nameTr = "Misoprostol",
            nameEn = "Misoprostol",
            genericName = "Misoprostol",
            brandNames = listOf(
                "Суtoteс"
            ),
            category = "obstetric",
            atcCode = null,
            drugClass = "Sentetik Prostaglandin E1 Analoğu (Uterotonik)",
            mechanismTr = "Miyometriyal kasılmaları uyarır, servikal olgunlaşmayı kolaylaştırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Doğum indüksiyonu için servikal olgunlaşma (endikasyon dışı): İntravajinal 25 mcg (100 mcg tabletin 1/4'ü); 3-6 saat aralıklarla tekrarlanabilir. Daha yüksek doz (50 mcg her 6 saat) bazı durumlarda kullanılabilir (artmış yan etki riski). Oral: 25 mcg; 2 saat aralıklarla tekrarlanabilir. Son misoprostol dozundan sonra 4 saatten erken oksitosin başlatma.\n• Medikal abortus (mifepriston ile kombinasyon tercih edilir): Birinci trimester: Bukkal/sublingual/intravajinal 800 mcg, mifepristondan 24-48 saat sonra; ≥9-<12 hafta için 3-6 saat sonra 800 mcg tekrar. İkinci trimester: 400 mcg, mifepristondan 24-48 saat sonra; ekspülsiyona kadar her 3 saatte tekrarla. Monoterapi: Birinci trimester 800 mcg her 3 saat; ikinci trimester 400 mcg her 3 saat (ekspülsiyona kadar).\n• NSAİİ kaynaklı gastrik ülser önleme: Oral 200 mcg günde 4 kez; tolere edilemezse 100 mcg günde 4 kez.\n• Postpartum uterin kanama (endikasyon dışı) – Önleme: Oksitosin ile kombinasyon: bukkal/sublingual 200-400 mcg tek doz. Monoterapi: oral 400-600 mcg tek doz; sublingual 600 mcg tek doz; rektal 400-800 mcg tek doz.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("PDF'te belirtilmemiş", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Gastrik asit sekresyonu inhibisyonu: 30 dakika",
                peak = "Serum doruk zamanı: Misoprostol asit: açlıkta 12±3 dakika",
                duration = "Gastrik asit sekresyonu inhibisyonu: 3 saat",
                halfLife = "Misoprostol asit: 20-40 dakika",
                elimination = "Hepatik; misoprostol aside hızlı de-esterifikasyon (aktif) (Eliminasyon yeri: İdrar (%80))"
            ),
            contraindications = listOf(
                "Prostaglandinlere karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Abortifasiyent: Hasta abortifasiyent özelliği konusunda uyarılmalı ve misoprostolü başkalarına vermemesi söylenmelidir.",
                "Servikal olgunlaşmada: Önceki sezaryen veya majör uterus cerrahisi olanlarda uterus rüptürü riski nedeniyle kullanmaktan kaçının; önceden düzenli ağrılı kontraksiyonu olanlarda aşırı uterin aktivite riski nedeniyle dikkat. Uygulamayı izleyen en az 30 dakika ve düzenli aktivite sürdükçe fetal kalp hızı ve uterin aktiviteyi sürekli izleyin.",
                "Kardiyovasküler hastalıkta dikkatle kullanın.",
                "Gastrik ülser kullanımı: Yalnızca NSAİİ alan ve gastrik ülser komplikasyonu açısından yüksek riskli hastalarda; NSAİİ tedavisi süresince alınmalıdır."
            ),
            sideEffectsCommon = listOf(
                ">%10: karın ağrısı, ishal (ağır ishal dahil). %1-10: konstipasyon, dispepsi, gaz, bulantı, kusma, baş ağrısı. <%1: akut MI, aritmi, göğüs ağrısı, hipertansiyon, hipotansiyon",
                "anafilaksi",
                "serebrovasküler olay. Sıklık tanımsız: abortus, prematür doğum. Pazarlama sonrası: uterus rüptürü, anormal trombosit agregasyonu."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Abortifasiyent: Hasta abortifasiyent özelliği konusunda uyarılmalı ve misoprostolü başkalarına vermemesi söylenmelidir.",
                "Servikal olgunlaşmada: Önceki sezaryen veya majör uterus cerrahisi olanlarda uterus rüptürü riski nedeniyle kullanmaktan kaçının; önceden düzenli ağrılı kontraksiyonu olanlarda aşırı uterin aktivite riski nedeniyle dikkat. Uygulamayı izleyen en az 30 dakika ve düzenli aktivite sürdükçe fetal kalp hızı ve uterin aktiviteyi sürekli izleyin.",
                "Kardiyovasküler hastalıkta dikkatle kullanın.",
                "Gastrik ülser kullanımı: Yalnızca NSAİİ alan ve gastrik ülser komplikasyonu açısından yüksek riskli hastalarda; NSAİİ tedavisi süresince alınmalıdır."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "nifedipine",
            nameTr = "Nifedipin",
            nameEn = "Nifedipine",
            genericName = "Nifedipine",
            brandNames = listOf(
                "Ρrοсаrԁia XL"
            ),
            category = "obstetric",
            atcCode = null,
            drugClass = "Kalsiyum Kanal Blokörü (Dihidropiridin)",
            mechanismTr = "Düz kaslarda kalsiyum girişini engelleyerek uterus kasılmalarını gevşetir (tokolitik etki).",
            adultDoses = mapOf("dozaj" to DoseInfo("• Güvenlik: IR sublingual nifedipin hiçbir endikasyon için önerilmez.\n• Anal fissür (endikasyon dışı): Peri-anal %0.2-0.3 merhem/jel, fissür çevresine günde 2-4 kez, 4 hafta.\n• Angina (kronik stabil/vazospastik): Oral uzatılmış salım (ER): Başlangıç 30 veya 60 mg günde 1 kez; 1-2 haftada etkili doza titre et. >90 mg/gün nadiren gerekir; maksimum 120 mg/gün. IR (oral/sublingual) önerilmez.\n• Yüksek irtifa pulmoner ödemi (ek tedavi, endikasyon dışı): ER 30 mg her 12 saat (çıkıştan 24 saat önce başla, maksimal irtifaya ulaştıktan sonra 4-5 gün sürdür). Tedavi: ER 30 mg her 12 saat.\n• Kronik hipertansiyon: Oral ER başlangıç 30 veya 60 mg günde 1 kez; aylık değerlendir, 90 mg günde 1 kez'e titre et (120 mg/gün'e kadar bildirilmiş). IR (oral/sublingual) akut KB düşürme için kullanılmamalı.\n• Gebelik/postpartum hipertansif acil (endikasyon dışı): Oral ER: Başlangıç 30 mg; hedef KB sağlanmazsa 1-2 saat sonra 30 mg tekrarla.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Ağır hipertansiyon (sınırlı veri): Çocuk ve adölesan: IR oral 0.04-0.25 mg/kg/doz; maksimum tek doz 10 mg/doz; gerekirse her 4-6 saatte tekrar; maksimum günlük doz 1-2 mg/kg/gün. (Pediatrik kılavuzlar akut ağır HT için nifedipini önermez.)\n• Kronik hipertansiyon (sınırlı veri): Çocuk ve adölesan: ER oral başlangıç 0.2-0.5 mg/kg/gün günde 1 kez veya 12 saatte bir bölünmüş; başlangıç erişkin dozu 30-60 mg/gün'ü aşma; maksimum 3 mg/kg/gün, 120 mg/gün'e kadar.\n• Yüksek irtifa pulmoner ödemi (sınırlı veri): IR oral 0.5 mg/kg/doz her 8 saat (maks 20 mg/doz); ER (tercih edilen) 1.5 mg/kg/gün.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IR ~20 dakika",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Sağlıklı erişkin 2-5 saat; siroz 7 saat; yaşlı 7 saat (ER tablet)",
                elimination = "Hepatik, CYP3A4 ile inaktif metabolitlere (Eliminasyon yeri: İdrar (%60-80 inaktif metabolit); feçes)"
            ),
            contraindications = listOf(
                "Kardiyojenik şok, ciddi aort darlığı",
                "Akut miyokard enfarktüsü"
            ),
            warnings = listOf(
                "Hipotansiyon/senkop: IR nifedipin akut KB düşürme için kullanılmamalıdır.",
                "Aort stenozu: Ağır aort stenozunda aşırı dikkatle kullanın; koroner perfüzyonu azaltıp miyokardiyal iskemiye yol açabilir.",
                "Hipertrofik kardiyomiyopati (çıkış yolu obstrüksiyonu): Afterload azalması semptomları kötüleştirebileceğinden dikkatle kullanın.",
                "Cerrahi: Majör cerrahi öncesi dikkatle kullanın; kardiyopulmoner bypass, kanama veya vazodilatör anestezi ile ağır hipotansiyon olabilir; mümkünse cerrahiden >36 saat önce kesmeyi düşünün."
            ),
            sideEffectsCommon = listOf(
                ">%10: yüz kızarması (IR %25), periferik ödem (%4-30), kalp yetmezliği, mide yanması, bulantı, baş dönmesi (IR %27), baş ağrısı (%16-23), asteni. %1-10: akut MI (%4), kalp yetmezliği (%2), palpitasyon, geçici hipotansiyon (%5), pulmoner ödem (%2), öksürük, dispne. <%1: aritmi, senkop, taşikardi, gingival hiperplazi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Hipotansiyon/senkop: IR nifedipin akut KB düşürme için kullanılmamalıdır.",
                "Aort stenozu: Ağır aort stenozunda aşırı dikkatle kullanın; koroner perfüzyonu azaltıp miyokardiyal iskemiye yol açabilir.",
                "Kalp yetmezliği: ACC/AHA kalp yetmezliği kılavuzları KKB'lerin kullanımından kaçınmayı önerir.",
                "Hipertrofik kardiyomiyopati (çıkış yolu obstrüksiyonu): Afterload azalması semptomları kötüleştirebileceğinden dikkatle kullanın.",
                "GI darlık/striktür: ER formlar nondeformable matris içerir; GI darlığı olanlarda bezoar/obstrüksiyon görülebilir.",
                "Cerrahi: Majör cerrahi öncesi dikkatle kullanın; kardiyopulmoner bypass, kanama veya vazodilatör anestezi ile ağır hipotansiyon olabilir; mümkünse cerrahiden >36 saat önce kesmeyi düşünün.",
                "Ani kesilme KAH'lı hastalarda rebound anjinaya neden olabilir.",
                "Belirgin ilaç etkileşimleri (CYP3A4) mevcuttur."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "terbutaline",
            nameTr = "Terbutalin",
            nameEn = "Terbutaline",
            genericName = "Terbutaline",
            brandNames = emptyList(),
            category = "obstetric",
            atcCode = null,
            drugClass = "Beta-2 Selektif Agonist (Bronkodilatör ve Tokolitik)",
            mechanismTr = "Miyometriyumdaki Beta-2 reseptörlerini uyararak intrasellüler cAMP'yi artırır ve uterus gevşemesini (tokoliz) sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Astım, dirençli veya ağır alevlenme (alternatif): SUBQ başlangıç 0.25 mg tek doz; ağır semptom devam ederse 20 dakikada bir, toplam 3 doza kadar tekrarlanabilir. Parenteral epinefrin ile kombine kullanma.\n• Astım, aralıklı semptom giderme (alternatif): Kuru toz inhaler (0.5 mg/aktüasyon, Kanada): oral inhalasyon, gerektikçe her 4 saatte 1 inhalasyon; etkisizse 5 dakika sonra tekrar (maks 6 inhalasyon/gün). Oral: 5 mg günde 3 kez (~6 saat aralık); yan etki olursa 2.5 mg günde 3 kez'e düşür; maksimum 15 mg/gün.\n• Ekstravazasyon yönetimi (endikasyon dışı): Büyük: SUBQ 1 mg (1 mg/10 mL NS) bölgeye infiltre; 15 dakika sonra tekrar. Küçük/distal: SUBQ 0.5 mg (1 mg/1 mL NS); 15 dakika sonra tekrar.\n• Tokoliz (endikasyon dışı, yalnızca akut kısa süreli ≤72 saat): IV 2.5-5 mcg/dakika; her 20-30 dakikada 2.5-5 mcg/dakika artır (maks 25 mcg/dakika). SUBQ 0.25 mg her 20 dakika-3 saat; nabız >120/dakika ise bekle.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Astım, akut alevlenme – Acil servis (sınırlı veri): Çocuk: SUBQ 0.01 mg/kg/doz her 20 dakikada 3 doz; her 2-6 saatte tekrar; maksimum 0.4 mg/doz. Adölesan: SUBQ 0.25 mg/doz; 20 dakikada bir, 3 doza kadar.\n• Yoğun bakım: Çocuk ve adölesan: sürekli infüzyon, IV bolus 4-10 mcg/kg, ardından 0.1-0.4 mcg/kg/dakika sürekli infüzyon; her 30 dakikada 0.1-0.2 mcg/kg/dakika titre et; olağan maksimum 2-3 mcg/kg/dakika.\n• Akut semptom giderme – Oral: ≥12 ve <15 yaş: 2.5 mg günde 3 kez (maks 7.5 mg/gün). ≥15 yaş: 5 mg günde 3 kez (maks 15 mg/gün). Oral inhalasyon (Kanada): ≥6 yaş: 1 inhalasyon (0.5 mg) gerektikçe.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Oral 30-45 dakika; SUBQ 6-15 dakika; inhalasyon 5 dakika (maksimum etki 15-60 dakika)",
                peak = "Serum doruk zamanı: SUBQ 0.5 saat",
                duration = "Oral 4-8 saat; oral inhalasyon 3-6 saat; SUBQ 1.5-4 saat",
                halfLife = "5.7 saat (aralık 2.9-14 saat)",
                elimination = "Hepatik, inaktif sülfat konjugatlarına (Eliminasyon yeri: İdrar (~%60 değişmeden); feçes)"
            ),
            contraindications = listOf(
                "Taşiaritmiler",
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Paradoksik bronkospazm: Yaşamı tehdit edebilen paradoksik bronkospazm nadiren görülebilir; oluşursa hemen kesip alternatif tedaviye geçin.",
                "Aşırı duyarlılık: Acil aşırı duyarlılık reaksiyonları (ürtiker, anjiyoödem, döküntü, bronkospazm) bildirilmiştir.",
                "Ciddi etkiler/ölümler: Önerilen doz/sıklığı aşmayın; aşırı kullanımla ölüm dahil ciddi advers olaylar görülmüştür.",
                "Kardiyovasküler hastalık: Aritmi, koroner yetmezlik, hipertansiyon, kalp yetmezliğinde dikkatle kullanın; beta-agonistler KB/kalp hızı artışı ve EKG değişiklikleri (QTc uzaması) yapabilir.",
                "Diyabet, hipertiroidizm, hipokalemi, glokom, nöbet bozukluğunda dikkatle kullanın."
            ),
            sideEffectsCommon = listOf(
                ">%10: sinirlilik, huzursuzluk, tremor",
                "serum potasyum düşüşü, serum glukoz artışı. %1-10: hipertansiyon, taşikardi, baş dönmesi, baş ağrısı, uykusuzluk, terleme, bulantı, kusma, kas krampları. <%1/pazarlama sonrası: aritmi, göğüs ağrısı, hipokalemi/hipotansiyon (preterm doğum), laktik asidoz, paradoksik bronkospazm, pulmoner ödem."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Paradoksik bronkospazm: Yaşamı tehdit edebilen paradoksik bronkospazm nadiren görülebilir; oluşursa hemen kesip alternatif tedaviye geçin.",
                "Aşırı duyarlılık: Acil aşırı duyarlılık reaksiyonları (ürtiker, anjiyoödem, döküntü, bronkospazm) bildirilmiştir.",
                "Ciddi etkiler/ölümler: Önerilen doz/sıklığı aşmayın; aşırı kullanımla ölüm dahil ciddi advers olaylar görülmüştür.",
                "Kardiyovasküler hastalık: Aritmi, koroner yetmezlik, hipertansiyon, kalp yetmezliğinde dikkatle kullanın; beta-agonistler KB/kalp hızı artışı ve EKG değişiklikleri (QTc uzaması) yapabilir.",
                "Diyabet, hipertiroidizm, hipokalemi, glokom, nöbet bozukluğunda dikkatle kullanın.",
                "Anti-inflamatuar ajan olmadan kronik tedavi bileşeni olarak kullanmayın.",
                "Belirgin ilaç etkileşimleri mevcuttur."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "cefazolin",
            nameTr = "Sefazolin",
            nameEn = "Cefazolin",
            genericName = "Cefazolin",
            brandNames = emptyList(),
            category = "antibiotic",
            atcCode = null,
            drugClass = "Birinci Kuşak Sefalosporin (Antibiyotik)",
            mechanismTr = "Bakteri hücre duvarı sentezini inhibe eder, cerrahi profilaksinin temel ilacıdır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Dozlama: Geleneksel aralıklı infüzyon (30-60 dakika); toplam günlük doz 24 saatte sürekli infüzyonla da verilebilir.\n• Kan dolaşımı enfeksiyonu (metisilin duyarlı stafilokok veya duyarlı Enterobacteriaceae): IV 2 g her 8 saat.\n• Endokardit profilaksisi (dental/invaziv solunum işlemi, alternatif, endikasyon dışı): IM/IV 1 g tek doz, işlemden 30-60 dakika önce (verilmediyse işlemden 2 saat sonrasına kadar verilebilir).\n• Endokardit tedavisi (alternatif): Doğal kapak: IV 2 g her 8 saat, 6 hafta. Protez kapak: IV 2 g her 8 saat ≥6 hafta (rifampin ve gentamisin ile kombine).\n• İntraabdominal enfeksiyon (hafif-orta): Akut kolesistit: IV 1-2 g her 8 saat. Diğer (perfore apandiks, divertikülit; endikasyon dışı): IV 1-2 g her 8 saat, metronidazol ile.\n• Osteomiyelit ve/veya diskit: IV 2 g her 8 saat (genellikle 6 hafta).\n• Antibiyotik kilit tekniği: İntrakateter, son konsantrasyon 5-10 mg/mL (heparinle kombine edilebilir).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Genel dozlama: Bebek, çocuk, adölesan: IM/IV 25-100 mg/kg/gün, 8 saatte bir bölünmüş; olağan maksimum 2,000 mg/doz. Ciddi enfeksiyonlar için 150 mg/kg/gün'e kadar, 6-8 saatte bir; 12 g/gün'ü aşma.\n• Endokardit profilaksisi (invaziv dental işlem öncesi, alternatif): IM/IV 50 mg/kg tek doz, işlemden 30-60 dakika önce; maksimum 1,000 mg/doz.\n• Endokardit tedavisi: Çocuk ve adölesan: IV 100 mg/kg/gün, 8 saatte bir bölünmüş; maksimum 12 g/gün; en az 4 hafta.\n• Akut osteoartiküler enfeksiyon (bakteriyel artrit, osteomiyelit): IV 100-150 mg/kg/gün, 6-8 saatte bir bölünmüş; maksimum 12 g/gün.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "Serum doruk zamanı: IM 0.5-2 saat; IV 5 dakika içinde",
                duration = "belirtilmemiş",
                halfLife = "Erişkin IV 1.8 saat; IM ~2 saat (renal yetmezlikte uzar)",
                elimination = "belirtilmemiş (Eliminasyon yeri: İdrar (%70-80 değişmeden))"
            ),
            contraindications = listOf(
                "Sefalosporin grubu antibiyotiklere karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Aşırı duyarlılık/penisilin çapraz reaksiyon: Beta-laktam ilaçlarla ciddi anafilaktik reaksiyonlar bildirilmiştir; tedaviden önce önceki penisilin, sefalosporin veya diğer alerjileri dikkatle araştırın; beta-laktam antibiyotikler arasında çapraz duyarlılık kurulduğundan penisilin/beta-laktam alerjisi olanlarda dikkatli kullanın.",
                "Gastrointestinal hastalık (özellikle kolit) öyküsünde dikkatle kullanın.",
                "Nöbet bozukluğu: Özellikle renal yetmezlikte yüksek düzeyler nöbet riskini artırabilir; dikkatle kullanın."
            ),
            sideEffectsCommon = listOf(
                "Hipotansiyon, senkop",
                "kaşıntı, döküntü",
                "ishal, karın krampı, bulantı, kusma, oral kandidiyazis",
                "vajinit, vulvovajinal kandidiyazis",
                "eozinofili, lökopeni, nötropeni, trombositopeni",
                "hepatit, transaminaz artışı",
                "enjeksiyon bölgesi reaksiyonu/flebit. Pazarlama sonrası: Stevens-Johnson sendromu, toksik epidermal nekroliz, anafilaksi, anjiyoödem, C. difficile ishali, hemolitik anemi, akut interstisyel nefrit."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aşırı duyarlılık/penisilin çapraz reaksiyon: Beta-laktam ilaçlarla ciddi anafilaktik reaksiyonlar bildirilmiştir; tedaviden önce önceki penisilin, sefalosporin veya diğer alerjileri dikkatle araştırın; beta-laktam antibiyotikler arasında çapraz duyarlılık kurulduğundan penisilin/beta-laktam alerjisi olanlarda dikkatli kullanın.",
                "Yükselmiş INR: Özellikle beslenme yetersizliği, uzun tedavi, karaciğer/böbrek hastalığı olanlarda INR artışı görülebilir.",
                "Süperenfeksiyon: Uzun kullanım mantar veya bakteriyel süperenfeksiyona (C. difficile dahil) yol açabilir.",
                "Gastrointestinal hastalık (özellikle kolit) öyküsünde dikkatle kullanın.",
                "Nöbet bozukluğu: Özellikle renal yetmezlikte yüksek düzeyler nöbet riskini artırabilir; dikkatle kullanın."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "cefuroxime",
            nameTr = "Sefuroksim",
            nameEn = "Cefuroxime",
            genericName = "Cefuroxime",
            brandNames = emptyList(),
            category = "antibiotic",
            atcCode = null,
            drugClass = "İkinci Kuşak Sefalosporin (Antibiyotik)",
            mechanismTr = "Bakteri hücre duvarı sentezini inhibe eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Dozlama: IM FDA onaylı yoldur ve IV ile aynı dozda verilebilir.\n• Isırık yarası enfeksiyonu (profilaksi/tedavi, alternatif, endikasyon dışı): Oral 500 mg günde 2 kez, anaerob ajanla kombine.\n• KOAH akut alevlenme: Oral 500 mg günde 2 kez, 5-7 gün.\n• İntraabdominal enfeksiyon (hafif-orta, endikasyon dışı): Akut kolesistit: IV 1.5 g her 8 saat. Diğer (apandisit, divertikülit, abse): IV 1.5 g her 8 saat, metronidazol ile.\n• Lyme hastalığı: Eritema migrans: Oral 500 mg günde 2 kez, 14 gün. Kardit: Oral 500 mg günde 2 kez, 14-21 gün.\n• Akut otitis media (alternatif): Oral 500 mg günde 2 kez, 5-7 gün (hafif-orta) veya 10 gün (ağır).\n• Toplum kökenli pnömoni (komorbiditeli ayaktan): Oral 500 mg günde 2 kez, en az 5 gün.\n• Grup A streptokokal farenjit (alternatif): Oral 250 mg günde 2 kez, 10 gün.\n• Cerrahi profilaksi (kardiyak, baş-boyun cerrahisi, alternatif): IV 1.5 g cerrahi insizyondan 60 dakika önce; uzun işlem veya aşırı kan kaybında 4 saatte intraoperatif tekrarlanabilir; postoperatif toplam süre ≤24 saat.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Genel dozlama: Bebek, çocuk, adölesan: IV/IM 100-150 mg/kg/gün, 8 saatte bir bölünmüş; maksimum 6,000 mg/gün. Oral: 20-30 mg/kg/gün, 12 saatte bir bölünmüş; maksimum 1,000 mg/gün.\n• Akut bakteriyel kronik bronşit alevlenmesi: Adölesan: Oral 250-500 mg her 12 saat, 10 gün.\n• İntraabdominal enfeksiyon (alternatif, sınırlı veri): IV 150-200 mg/kg/gün, 6-8 saatte bir bölünmüş; maksimum 1,500 mg/doz.\n• Lyme hastalığı: Oral 30 mg/kg/gün, 12 saatte bir bölünmüş; maksimum 500 mg/doz.\n• Akut osteoartiküler enfeksiyon: IV/IM 150-200 mg/kg/gün, 6-8 saatte bir bölünmüş; maksimum 6,000 mg/gün.\n• Akut otitis media (alternatif): Oral 30 mg/kg/gün, 12 saatte bir bölünmüş; maksimum 500 mg/doz.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "Serum doruk zamanı: IM ~15-60 dakika; IV 2-3 dakika; Oral: çocuk ~3-4 saat, erişkin ~2-3 saat",
                duration = "belirtilmemiş",
                halfLife = "Erişkin ~1-2 saat (renal yetmezlikte uzar); çocuk/adölesan 1.4-1.9 saat",
                elimination = "Sefuroksim aksetil (oral) intestinal mukoza ve kanda sefuroksime hidrolize olur (Eliminasyon yeri: İdrar (%66-100 değişmeden))"
            ),
            contraindications = listOf(
                "Sefalosporin grubu antibiyotiklere karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Aşırı duyarlılık/penisilin çapraz reaksiyon: Beta-laktam ilaçlarla ciddi, bazen ölümcül anafilaktik reaksiyonlar bildirilmiştir; tedaviden önce önceki penisilin, sefalosporin veya diğer alerjileri dikkatle araştırın; beta-laktamlar arasında çapraz duyarlılık kurulduğundan penisilin/beta-laktam alerjisinde dikkatli kullanın.",
                "Gastrointestinal hastalık (kolit öyküsü) ve renal yetmezlikte dikkatle kullanın.",
                "Nöbet bozukluğu: Sefalosporinler özellikle doz ayarı yapılmayan renal yetmezlikte nöbete yol açabilir; nöbet olursa kesin."
            ),
            sideEffectsCommon = listOf(
                ">%10: ishal (%4-11). %1-10: bulantı ve kusma (%3-7), vajinit, hematokrit/hemoglobin düşüşü, eozinofili, transaminaz artışı, Jarisch-Herxheimer reaksiyonu (%6), lokal tromboflebit. <%1: göğüs ağrısı, döküntü, ürtiker, baş dönmesi, nötropeni, pozitif direkt Coombs. Pazarlama sonrası: Stevens-Johnson sendromu, toksik epidermal nekroliz, anafilaksi, anjiyoödem, C. difficile koliti, hemolitik anemi, hepatit, nöbet."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aşırı duyarlılık/penisilin çapraz reaksiyon: Beta-laktam ilaçlarla ciddi, bazen ölümcül anafilaktik reaksiyonlar bildirilmiştir; tedaviden önce önceki penisilin, sefalosporin veya diğer alerjileri dikkatle araştırın; beta-laktamlar arasında çapraz duyarlılık kurulduğundan penisilin/beta-laktam alerjisinde dikkatli kullanın.",
                "Yükselmiş INR: Özellikle beslenme yetersizliği, uzun tedavi, karaciğer/böbrek hastalığında INR artışı görülebilir.",
                "Süperenfeksiyon: Uzun kullanım mantar/bakteriyel süperenfeksiyona (C. difficile ishali, psödomembranöz kolit dahil; >2 ay sonra dahi) yol açabilir.",
                "Gastrointestinal hastalık (kolit öyküsü) ve renal yetmezlikte dikkatle kullanın.",
                "Nöbet bozukluğu: Sefalosporinler özellikle doz ayarı yapılmayan renal yetmezlikte nöbete yol açabilir; nöbet olursa kesin.",
                "Tabletler güçlü acı tat nedeniyle ezilmemeli/çiğnenmemelidir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "ampicillin_sulbactam",
            nameTr = "Ampisilin-Sulbaktam",
            nameEn = "Ampicillin-Sulbactam",
            genericName = "Ampicillin-Sulbactam",
            brandNames = listOf(
                "Duocid",
                "Ampisina",
                "Unasyn"
            ),
            category = "antibiotic",
            atcCode = null,
            drugClass = "Aminopenisilin + Beta-Laktamaz İnhibitörü",
            mechanismTr = "Ampisilin bakteri hücre duvarı sentezini engeller, sulbaktam ise bakteriyel beta-laktamaz enzimlerini inhibe ederek ampisilini korur.",
            adultDoses = mapOf("dozaj" to DoseInfo("Dozlar total ampisilin/sulbaktam gramı olarak ifade edilir (2:1 oranlı kombinasyon).\n• Olağan doz aralığı: IM, IV: her 6 saatte bir 1.5-3 g (maksimum: 12 g/gün)\n• Kan dolaşımı enfeksiyonu / odontojenik / diyabetik ayak (orta-şiddetli): IV: her 6 saatte 3 g\n• Endokardit (Enterokok): IV: her 6 saatte 3 g, gentamisin ile kombine, 6 hafta\n• Pelvik inflamatuar hastalık: IV: her 6 saatte 3 g, doksisiklin ile kombine\n• Çoklu ilaca dirençli A. baumannii (alternatif): IV: 8 saatte bir 9 g (4 saatte infüzyon) veya 27 g/gün sürekli infüzyon", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Dozlar ampisilin bileşeni mg cinsindendir.\n• Genel doz (Bebek/Çocuk/Adölesan): IV, IM: 100-200 mg ampisilin/kg/gün, 6 saatte bir bölünmüş; olağan maksimum: 2.000 mg ampisilin/doz\n• Endokardit: IV: 200-300 mg ampisilin/kg/gün, 4-6 saatte bir bölünmüş; maks 2.000 mg/doz\n• Menenjit: IV: 400 mg ampisilin/kg/gün, 4-6 saatte bir bölünmüş\n• Komplike intraabdominal enfeksiyon: IV: 200 mg ampisilin/kg/gün, 6 saatte bir", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Sulbaktam erişkin (normal böbrek): 1-1.3 saat; çocuk ≤12 yaş: ~0.81 saat",
                elimination = "belirtilmemiş (Eliminasyon yeri: İdrar (~%75-85 değişmemiş ilaç, 8 saat içinde))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Anafilaktoid/aşırı duyarlılık: penisilin tedavisinde ciddi, bazen ölümcül reaksiyonlar; beta-laktam alerjisi öyküsünde dikkat. Sefalosporinlerle çapraz reaksiyon olabilir; reaksiyon halinde kes",
                "Hepatik disfonksiyon: hepatit ve kolestatik sarılık bildirilmiş (ölümcül olabilir); karaciğer fonksiyonunu izle"
            ),
            sideEffectsCommon = listOf(
                "Enjeksiyon yerinde ağrı (IM %16, IV %3), flebit/tromboflebit, diyare, döküntü. Nadir: bulantı, kusma, kandidiyazis, baş ağrısı, karaciğer enzim artışı. Pazarlama sonrası: C. difficile ishali, ağır deri reaksiyonları (SJS, TEN, eritema multiforme), kolestatik hepatit/sarılık, anafilaksi/anjiyoödem, hemolitik anemi, agranülositoz."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Anafilaktoid/aşırı duyarlılık: penisilin tedavisinde ciddi, bazen ölümcül reaksiyonlar; beta-laktam alerjisi öyküsünde dikkat. Sefalosporinlerle çapraz reaksiyon olabilir; reaksiyon halinde kes",
                "Hepatik disfonksiyon: hepatit ve kolestatik sarılık bildirilmiş (ölümcül olabilir); karaciğer fonksiyonunu izle",
                "Döküntü: ampisilin döküntüsünü aşırı duyarlılıktan ayırt et",
                "Süperenfeksiyon: uzun kullanımda C. difficile ishali ve psödomembranöz kolit",
                "Enfeksiyöz mononükleozda kullanılması önerilmez (döküntü)"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "clindamycin",
            nameTr = "Klindamisin",
            nameEn = "Clindamycin",
            genericName = "Clindamycin",
            brandNames = listOf(
                "Cleocin",
                "Clin Ampul",
                "Klindan"
            ),
            category = "antibiotic",
            atcCode = null,
            drugClass = "Lincosamide grubu antibiyotik",
            mechanismTr = "Bakteriyel 50S ribozomal alt birimine geri dönüşümlü bağlanarak protein sentezini (peptid zincir uzamasını) inhibe eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Olağan doz: Oral: 600 mg-1.8 g/gün, 2-4 bölünmüş dozda\n• IM, IV: 600 mg-2.7 g/gün, 2-4 bölünmüş dozda; hayati tehlike taşıyan enfeksiyonlarda 4.8 g/gün'e kadar IV; IM maksimum 600 mg/doz\n• Diyabetik ayak (hafif-orta): Oral: 300 mg günde 3-4 kez veya 450 mg günde 3 kez\n• Isırık yarası: Oral: 300-450 mg günde 3 kez; IV: her 6-8 saatte 600 mg\n• Bakteriyel vajinozis (alternatif): Oral: 300 mg günde 2 kez, 7 gün\n• Mastit (laktasyonel): Oral: 300 mg günde 4 kez veya 450 mg günde 3 kez", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Doz toplam vücut ağırlığına göre (≥2 yaş).\n• Genel doz (Bebek/Çocuk/Adölesan): IM, IV: 20-40 mg/kg/gün, 6-8 saatte bir bölünmüş; maksimum 2.700 mg/gün\n• Oral: 10-25 mg/kg/gün, 8 saatte bir bölünmüş; bazı enfeksiyonlarda 30-40 mg/kg/gün; maksimum 1.800 mg/gün\n• Antraks postmaruziyet profilaksisi: Oral: 10 mg/kg/doz 8 saatte bir; maks 600 mg/doz\n• Antraks sistemik/menenjit (alternatif): IV: 13.3 mg/kg/doz 8 saatte bir; maks 900 mg/doz", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Erişkin 3 saat; yaşlı (oral) ~4 saat",
                elimination = "Karaciğer; başlıca CYP3A4 ile (klindamisin sülfoksit ana metaboliti) (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Renal toksisite: akut böbrek hasarı bildirilmiş; klindamisine bağlı şüpheleniliyorsa kes",
                "GİS hastalığı (özellikle kolit) öyküsünde dikkatli kullan; yaşlılarda ishal toleransı düşük, bağırsak sıklığını izle",
                "Kapsül: bol su ile, en az 30 dk dik pozisyonda kal (özofajit riski)"
            ),
            sideEffectsCommon = listOf(
                "Karın ağrısı, bulantı, kusma, ürtiker, veziküllü dermatit. Pazarlama sonrası: C. difficile ishali/koliti, ishal, hipotansiyon (hızlı IV), tromboflebit, ağır deri reaksiyonları (SJS, TEN), kolestatik hepatit, anafilaksi/anjiyoödem, agranülositoz, nötropeni, trombositopeni, akut böbrek hasarı, IM enjeksiyon yerinde abse/ağrı."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Renal toksisite: akut böbrek hasarı bildirilmiş; klindamisine bağlı şüpheleniliyorsa kes",
                "Süperenfeksiyon: özellikle maya aşırı çoğalması olabilir",
                "GİS hastalığı (özellikle kolit) öyküsünde dikkatli kullan; yaşlılarda ishal toleransı düşük, bağırsak sıklığını izle",
                "Menenjit tedavisinde uygun değildir (yetersiz CSF penetrasyonu)",
                "Yenidoğanlarda benzil alkol içeren formlardan kaçın ('gasping sendromu')",
                "IV: bolus olarak seyreltmeden verme; 10-60 dakikada infüze et",
                "Kapsül: bol su ile, en az 30 dk dik pozisyonda kal (özofajit riski)"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "furosemide",
            nameTr = "Furosemid",
            nameEn = "Furosemide",
            genericName = "Furosemide",
            brandNames = listOf(
                "Lasix Ampul",
                "Desal Ampul"
            ),
            category = "icu_metabolic",
            atcCode = null,
            drugClass = "Kıvrım (Loop) Diüretiği",
            mechanismTr = "Henle kulbunun çıkan kalın kolunda Na-K-2Cl kotransporterını inhibe ederek sodyum, potasyum, klor ve su atılımını masif olarak artırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("Doz eşdeğerliği: Furosemid 40 mg = bumetanid 1 mg = torsemid 10-20 mg.\n• Ödem veya volüm yükü (loop diüretik kullanmayanlar): Oral, IV: başlangıç 20-40 mg tek doz, sonra titre et (oral biyoyararlanım ~IV dozun %50'si)\n• Refrakter ödem / akut dekompansasyon (oral diüretik alanlar): IV bolus: günlük oral idame dozun 1-2.5 katı; maksimum etkin tek doz 80-200 mg; maksimum günlük 600 mg/gün\n• Sürekli infüzyon: IV: başlangıç 5 mg/saat; yanıt yetersizse 10 mg/saat'e, gerekirse 40 mg/saat'e kadar\n• Siroza bağlı asit: Oral: başlangıç 40 mg günde 1 kez; maksimum 160 mg/gün (genellikle spironolakton ile)", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Oral ve parenteral dozlar değiştirilebilir değildir (oral genelde daha yüksek).\n• Ödem (akut aralıklı), Oral: başlangıç 2 mg/kg tek doz; etkisizse 6-8 saatte 1-2 mg/kg/doz artış; maks 6 mg/kg/doz\n• Kronik idame, Oral: 0.5-2 mg/kg/doz her 6-24 saatte; maks 6 mg/kg/gün (erişkin maks 600 mg/gün'ü aşmadan)\n• Aralıklı IV, IM: 0.5-2 mg/kg/doz her 6-12 saatte; maks 6 mg/kg/doz (erişkin maks 200 mg/doz'u aşmadan)\n• Sürekli IV infüzyon (Bebek/Çocuk): bolus 1-2 mg/kg, ardından 0.05-0.4 mg/kg/saat", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Diürez: IV 5 dakika; Oral/SL 30-60 dakika (akut pulmoner ödemde semptomatik düzelme 15-20 dakika)",
                peak = "Oral/SL 1-2 saat; IV erişkin 0.5 saat; SUBQ 4-5 saat",
                duration = "Oral/SL 6-8 saat; IV erişkin ~2 saat",
                halfLife = "Erişkin normal böbrek 0.5-2 saat; son dönem böbrek hastalığı 9 saat",
                elimination = "Minimal hepatik (Eliminasyon yeri: İdrar (oral %50, IV %80) 24 saat içinde; feçes)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Sülfonamid ('sulfa') alerjisi: çapraz reaksiyon potansiyeli (özellikle ağır SJS/TEN öyküsünde kaçın)",
                "Sirozda elektrolit/asit-baz dengesizliğinden kaçın (hepatik ensefalopati riski); diyabette glukoz kontrolüne dikkat",
                "Önemli ilaç etkileşimleri mevcut (gentamisin gibi ototoksik ilaçlarla birlikte risk artar)"
            ),
            sideEffectsCommon = listOf(
                "Elektrolit bozuklukları (hipokalemi, hipomagnezemi, hipokalsemi, hipokloremik alkaloz, hipovolemi), hiperglisemi, hiperürisemi, ortostatik hipotansiyon, baş dönmesi, baş ağrısı, bulanık görme, kulak çınlaması/sağırlık, akut böbrek hasarı. Ağır: anafilaksi, SJS/TEN, agranülositoz, aplastik/hemolitik anemi, pankreatit, hepatik ensefalopati."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Hiperürisemi: asemptomatik hiperürisemi, nadiren gut alevlenmesi",
                "Sülfonamid ('sulfa') alerjisi: çapraz reaksiyon potansiyeli (özellikle ağır SJS/TEN öyküsünde kaçın)",
                "Tiroid etkisi: >80 mg dozlar geçici tiroid hormon değişikliğine yol açabilir",
                "Sirozda elektrolit/asit-baz dengesizliğinden kaçın (hepatik ensefalopati riski); diyabette glukoz kontrolüne dikkat",
                "Bariatrik cerrahi sonrası erken dönemde diüretiklerden kaçın",
                "Önemli ilaç etkileşimleri mevcut (gentamisin gibi ototoksik ilaçlarla birlikte risk artar)"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "gentamicin",
            nameTr = "Gentamisin",
            nameEn = "Gentamicin",
            genericName = "Gentamicin",
            brandNames = listOf("Genta Ampul", "Gentamicin", "Gentasol"),
            category = "antibiotic",
            atcCode = null,
            drugClass = "Aminoglikozid grubu antibiyotik",
            mechanismTr = "Bakteriyel 30S ribozomal alt birimine bağlanarak mRNA kod okunmasını bozar, bakteri protein sentezini irreversibl inhibe eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("Doz vücut ağırlığına göre hesaplanır ve terapötik ilaç düzey izlemiyle yönlendirilir (P. aeruginosa için önerilmez).\n• Gram-negatif, geleneksel doz: IV, IM: 3-5 mg/kg/gün, 8 saatte bir bölünmüş (hedef pik 7-10 mcg/mL, dip <2 mcg/mL, ideal <1 mcg/mL); bazı uzmanlar 2.5-3 mg/kg yükleme önerir\n• Yüksek doz uzun aralıklı (günde tek doz): IV: 5-7 mg/kg günde 1 kez (hedef pik ~15-20 mcg/mL, dip ≤0.5 mcg/mL)\n• Gram-pozitif sinerji (non-CNS): IV, IM: 3 mg/kg/gün, 1-3 bölünmüş dozda (hedef pik 3-4 mcg/mL, dip <1 mcg/mL)\n• Kan dolaşımı enfeksiyonu: IV: 5-7 mg/kg günde 1 kez, kombinasyon rejiminin parçası olarak", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Serum düzey izlemi önerilir; obezitede ayarlanmış vücut ağırlığı kullanılır.\n• Geleneksel doz (Bebek/Çocuk/Adölesan): IM, IV: 2-2.5 mg/kg/doz her 8 saatte\n• Uzun aralıklı, kiloya göre: IV: 5-7.5 mg/kg/doz her 24 saatte\n• Yaşa göre: 3 ay-<2 yaş: 9.5 mg/kg/doz; 2-<8 yaş: 8.5 mg/kg/doz; ≥8 yaş: 7 mg/kg/doz (24 saatte bir)\n• Bruselloz: IV, IM: 5 mg/kg/doz günde 1 kez, kombinasyon rejimi", null)),
            specialPopulations = mapOf(
                "renal_yetmezlik" to "Nefrotoksiktir! Böbrek yetmezliğinde birikir, glomerular filtrasyon zayıfsa yarı ömrü masif uzar. eGFR takibi ve plazma düzey takibi yapılmalıdır.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde doz ayarlaması gerekmez.",
                "obezite" to "Obez hastalarında dozu hesaplarken Ayarlanmış Vücut Ağırlığı (AdjBW) kullanılmalıdır.",
                "geriatri" to "Yaşlılarda böbrek rezervi azaldığı için doz filtre edilmeli, böbrek fonksiyonu izlenmelidir."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "Serum pik zamanı: IM 30-90 dakika; IV 30-dakikalık infüzyon sonrası 30 dakika",
                duration = "belirtilmemiş",
                halfLife = "Erişkin ~2 saat (böbrek yetmezliğinde ortalama 41 saat)",
                elimination = "Belirgin metabolizma yok (Eliminasyon yeri: İdrar (≥%70 değişmemiş ilaç))"
            ),
            contraindications = listOf("Bileşenlerine karşı bilinen aşırı duyarlılık"),
            warnings = listOf(
                "[Kutu Uyarı] Nefrotoksisite: önceden böbrek bozukluğu, nefrotoksik ilaçlar, ileri yaş, dehidratasyon risk faktörleri; bulgularda kes (genellikle geri dönüşlü)",
                "[Kutu Uyarı] Nörotoksisite/ototoksisite: doz ve süreyle orantılı; çınlama/vertigo vestibüler hasar işareti olabilir; bulgularda kes",
                "[Kutu Uyarı] Diğer nörotoksik/nefrotoksik ilaçlardan (sisplatin, polimiksin B, kolistin, vankomisin, diğer aminoglikozidler) kaçın",
                "[Kutu Uyarı] Güçlü diüretiklerle (etakrinik asit, furosemid) birlikte kullanmaktan kaçın",
                "[Kutu Uyarı] Gebelikte fetal zarar verebilir; MT-RNR1 varyant taşıyıcılarında ototoksisite riski artar"
            ),
            sideEffectsCommon = listOf(
                "Nefrotoksisite (kreatinin/BUN artışı, renal tübüler nekroz, böbrek yetmezliği), ototoksisite (işitme kaybı, kulak çınlaması, vertigo/ataksi), nöromusküler blokaj, elektrolit bozuklukları (hipokalsemi, hipokalemi, hipomagnezemi), periferik nöropati, döküntü, C. difficile ishali, anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "[Kutu Uyarı] Nefrotoksisite: önceden böbrek bozukluğu, nefrotoksik ilaçlar, ileri yaş, dehidratasyon risk faktörleri; bulgularda kes (genellikle geri dönüşlü)",
                "[Kutu Uyarı] Nörotoksisite/ototoksisite: doz ve süreyle orantılı; çınlama/vertigo vestibüler hasar işareti olabilir; bulgularda kes",
                "Nöromusküler blokaj ve solunum felci: özellikle anestezi/nöromusküler bloker sonrası",
                "[Kutu Uyarı] Diğer nörotoksik/nefrotoksik ilaçlardan (sisplatin, polimiksin B, kolistin, vankomisin, diğer aminoglikozidler) kaçın",
                "[Kutu Uyarı] Güçlü diüretiklerle (etakrinik asit, furosemid) birlikte kullanmaktan kaçın",
                "[Kutu Uyarı] Gebelikte fetal zarar verebilir; MT-RNR1 varyant taşıyıcılarında ototoksisite riski artar",
                "Terapötik düzey izlemi gereklidir"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "mannitol",
            nameTr = "Mannitol",
            nameEn = "Mannitol",
            genericName = "Mannitol",
            brandNames = listOf("Mannitol %20 Solüsyon"),
            category = "icu_metabolic",
            atcCode = null,
            drugClass = "Ozmotik Diüretik",
            mechanismTr = "Glomerüllerden serbestçe filtre edilir ancak tübüllerden geri emilmez. Tübüler lümende ozmotik basınç yaratarak suyun geri emilimini engeller, beyin ödeminde ise hücre dışı sıvıyı intravasküler alana çekerek kranial basıncı düşürür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• İntrakraniyal basınç azaltma (serebral ödem, travma, kanama, iskemik inme): IV (%20 solüsyon): 0.5-2 g/kg tek doz; yanıta göre her 4-6 saatte 0.25-1 g/kg/doz tekrarlanabilir\n• İntraoküler basınç azaltma (cerrahi öncesi): IV: 1.5-2 g/kg, 30-60 dakikada, ameliyattan 1-1.5 saat önce\n• Travmatik hifema: IV: 1.5 g/kg, 45 dakikada günde 2 kez (IOP >35 mm Hg için)\n• Böbrek transplantasyonu intraoperatif volüm optimizasyonu: IV: revaskülarizasyonda 12.5-25 g (50 g'a kadar çalışılmış); bazı uzmanlar 1 g/kg, maks 75 g\n• Sigaterra balık zehirlenmesi: IV (%20): 1 g/kg, 30-45 dakikada", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Ürünler: %10 (0.1 g/mL), %20 (0.2 g/mL), %25 (0.25 g/mL).\n• İntrakraniyal basınç azaltma (Bebek/Çocuk/Adölesan): IV: 0.25-1 g/kg/doz, 20-30 dakikada; serum osmolalitesini <320 mOsm/kg tutmak için tekrarla\n• İntraoküler basınç azaltma: IV: 1.5-2 g/kg/doz, ≥30 dakikada (ameliyat öncesi 60-90 dakika önce)\n• Travmatik hifema: IV: 1.5 g/kg/doz, 45 dakikada günde 2 kez (IOP >35 mm Hg)", null)),
            specialPopulations = mapOf(
                "renal_yetmezlik" to "Akut böbrek yetmezliğinde (anüri durumunda) filtre edilemeyeceği için birikir ve akciğer ödemini tetikler, kesinlikle kontrendikedir!",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "İdeal veya normal vücut ağırlığı takibine göre verilir.",
                "geriatri" to "Yaşlılarda sıvı kaymaları sonucu akut kalp yetmezliği tetiklenebilir."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Diürez 1-3 saat; intrakraniyal basınç azalması ~15-30 dakika",
                peak = "belirtilmemiş",
                duration = "İntrakraniyal basınç azalması 1.5-6 saat",
                halfLife = "0.5-2.5 saat (akut böbrek hasarı/son dönem böbrek yetmezliğinde ~36 saat)",
                elimination = "Minimal hepatik (glikojene) (Eliminasyon yeri: İdrar (~%80 değişmemiş ilaç))"
            ),
            contraindications = listOf("Bileşenlerine karşı bilinen aşırı duyarlılık"),
            warnings = listOf(
                "Hipersensitivite: ciddi reaksiyonlar (anafilaksi, ölümcül olabilir); reaksiyonda hemen kes",
                "Nefrotoksisite: özellikle yüksek dozda; serum osmolalitesi/osmolar açığı izle, akut böbrek hasarında kes",
                "Serebral ödem: uzun süre dolaşımda kalırsa beyinde birikip rebound ICP artışına yol açabilir; aralıklı bolus tercih edilir",
                "Ağır pulmoner ödem/kalp yetmezliğinde büyük hacimlerden kaçın"
            ),
            sideEffectsCommon = listOf(
                "Elektrolit/sıvı bozuklukları (hipervolemi, hipovolemi, hiper/hiponatremi, hiper/hipokalemi, metabolik asidoz/alkaloz, dehidratasyon), kalp yetmezliği, hipotansiyon, taşikardi, pulmoner ödem/konjesyon, akut böbrek hasarı, osmotik nefroz, baş ağrısı, konfüzyon, koma, nöbet, anafilaksi, infüzyon yeri reaksiyonu, cilt nekrozu."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ekstravazasyon: vezikan (>%5 konsantrasyon); kompartman sendromuna yol açabilir; büyük santral venden uygulanması önerilir",
                "Sıvı/elektrolit dengesizliği: hipervolemi ve elektrolit bozukluklarına yol açabilir; kardiyak/pulmoner konjesyonu izle",
                "Hipersensitivite: ciddi reaksiyonlar (anafilaksi, ölümcül olabilir); reaksiyonda hemen kes",
                "Nefrotoksisite: özellikle yüksek dozda; serum osmolalitesi/osmolar açığı izle, akut böbrek hasarında kes",
                "Kan ile aynı anda verilmemeli (psödoaglütinasyon/hemoliz)",
                "Serebral ödem: uzun süre dolaşımda kalırsa beyinde birikip rebound ICP artışına yol açabilir; aralıklı bolus tercih edilir",
                "Ağır pulmoner ödem/kalp yetmezliğinde büyük hacimlerden kaçın"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "metronidazole",
            nameTr = "Metronidazol",
            nameEn = "Metronidazole",
            genericName = "Metronidazole",
            brandNames = listOf("Flagyl IV", "Nidazol IV", "Metrazol"),
            category = "antibiotic",
            atcCode = null,
            drugClass = "Nitroimidazol antibiyotik / Antiprotozoal",
            mechanismTr = "Bakteri hücresine girdikten sonra aktif ara metabolitlerine indirgenir, bu metabolitler bakteri DNA yapısını bozarak sentezini engeller.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Amebiyaz (intestinal/ekstraintestinal): Oral: her 8 saatte 500-750 mg, 7-10 gün (ardından intraluminal ajan)\n• Bakteriyel vajinozis: Oral: 500 mg günde 2 kez, 7 gün\n• Isırık yarası: Oral, IV: her 8 saatte 500 mg, kombinasyon rejimi\n• Diyabetik ayak (orta-şiddetli): Oral, IV: her 8-12 saatte 500 mg, kombinasyon rejimi\n• C. difficile enfeksiyonu (nonsevere, alternatif): Oral: 500 mg günde 3 kez, 10-14 gün; fulminan: IV: her 8 saatte 500 mg (oral/rektal vankomisin ile), 10 gün\n• Crohn hastalığı (cerrahi sonrası): Oral: 20 mg/kg/gün veya 1-2 g/gün, 3 ay", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Genel doz (Bebek/Çocuk/Adölesan): Oral: 15-50 mg/kg/gün, 8 saatte bir bölünmüş; maks 2.250 mg/gün; IV: 22.5-40 mg/kg/gün, 6-8 saatte bir bölünmüş; maks 4.000 mg/gün\n• Amebiyaz: Oral, IV: 35-50 mg/kg/gün, 8 saatte bir bölünmüş, 7-10 gün; maks 750 mg/doz\n• Bakteriyel vajinozis (<45 kg): Oral: 15-25 mg/kg/gün, 8 saatte bir; ≥45 kg: 500 mg her 12 saatte\n• C. difficile (Çocuk/Adölesan, nonsevere): Oral: 7.5 mg/kg/doz her 6-8 saatte, 10 gün; maks 500 mg/doz", null)),
            specialPopulations = mapOf(
                "renal_yetmezlik" to "Ciddi böbrek yetmezliğinde (eGFR <10 mL/dk) metabolitleri birikebilir, dozaj %50 azaltılmalıdır.",
                "karaciger_yetmezligi" to "Ağır karaciğer yetmezliğinde klirensi %50 oranında düşer, doz azaltılması gerekir.",
                "obezite" to "Standart dozlar yeterlidir.",
                "geriatri" to "Yaşlılarda karaciğer klirensi azalacağı için yakın takip önerilir."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "Serum pik zamanı: Oral kapsül/tablet 1-2 saat; oral süspansiyon 0.25-6 saat",
                duration = "belirtilmemiş",
                halfLife = "Erişkin ~8 saat; çocuk/adölesan 6-10 saat",
                elimination = "Hepatik (%30-60), aktif hidroksi metaboliti dahil (Eliminasyon yeri: İdrar (değişmemiş ilaç ve metabolitler %60-80); feçes (%6-15))"
            ),
            contraindications = listOf("Bileşenlerine karşı bilinen aşırı duyarlılık"),
            warnings = listOf(
                "Hepatik bozuklukta dikkatli kullan (birikim potansiyeli); ağır hepatik bozuklukta doz ayarı",
                "Renal bozuklukta dikkat (birikim)",
                "Nöbet bozukluğu öyküsünde dikkatli kullan",
                "Enjeksiyon: yüksek sodyum içeriği nedeniyle kalp yetmezliği, ödem veya sodyum tutucu durumlarda dikkat (parenteral solüsyon gram başına 28 mEq sodyum içerir)"
            ),
            sideEffectsCommon = listOf(
                "Baş ağrısı (%18), vajinit (%15), bulantı (%10-12), metalik tat (%9), karın ağrısı/ishal (%4), baş dönmesi. Frekansı belirtilmeyen: kusma, glossit, kıllı dil, ürtiker, döküntü. Pazarlama sonrası: QT uzaması, SJS/TEN, pankreatit, lökopeni/nötropeni/trombositopeni, hepatotoksisite (özellikle Cockayne sendromu), anafilaksi, DRESS, idrar renk değişimi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Süperenfeksiyon: uzun kullanımda C. difficile ishali ve psödomembranöz kolit",
                "Hepatik bozuklukta dikkatli kullan (birikim potansiyeli); ağır hepatik bozuklukta doz ayarı",
                "Renal bozuklukta dikkat (birikim)",
                "Nöbet bozukluğu öyküsünde dikkatli kullan",
                "Enjeksiyon: yüksek sodyum içeriği nedeniyle kalp yetmezliği, ödem veya sodyum tutucu durumlarda dikkat (parenteral solüsyon gram başına 28 mEq sodyum içerir)",
                "Nazogastrik aspirasyonda serum düzeyi azalabilir"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "pheniramine",
            nameTr = "Feniramin Maleat",
            nameEn = "Pheniramine Maleate",
            genericName = "Chlorpheniramine",
            brandNames = listOf("Allеr-Chlor [OTC]", "Allergy Relief [OTC]", "Allergy [OTC]", "Chlor-Trimeton Allergy [OTC]", "Сhlоrphеո [OTC]"),
            category = "allergy",
            atcCode = null,
            drugClass = "Birinci Kuşak Antihistaminik (H1 Reseptör Antagonisti)",
            mechanismTr = "Periferik H1 reseptörlerini bloke ederek alerjik reaksiyonları ve kaşıntıyı önler.",
            adultDoses = mapOf("dozaj" to DoseInfo("Endikasyonlar: alerjik durumlar (saman nezlesi, ilaç döküntüleri, anjiyonörotik ödem, serum hastalığı, alerjik konjonktivit), artmış sekresyonlu solunum yolu durumları (vazomotor/akut rinit), kaşıntılı deri durumları, hareket hastalığı ve Menière hastalığına bağlı bulantı/kusma/vertigo önleme ve tedavisi.\n• Tablet (Erişkin ve >10 yaş çocuk): Başlangıç Avil 25'ten 1 tablet günde 2-3 kez; gerekirse 2 tablet Avil 25 veya 1 tablet Avil 50'ye günde 2-3 kez artırılabilir\n• Enjeksiyon (Erişkin ve ≥12 yaş): 1-2 mL günde 2 kez, IM veya yavaş IV (1 mL/dakika); akut semptomlar geçene kadar 12 saatlik aralıklarla tekrarlanabilir\n• Seyahat hastalığında doz yolculuktan en az 30 dakika önce alınmalı", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Tablet (5-10 yaş): Avil 25'ten 1 tablet günde 2 keze kadar; 5 yaş altı çocuklarda tablet önerilmez\n• Enjeksiyon (≤12 yaş bebek ve çocuk): yalnızca IM verilmelidir", null)),
            specialPopulations = mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde metabolizması gecikebilir, dikkatli olunmalıdır.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Güçlü antikolinerjik yan etkileri (deliryum, idrar retansiyonu) nedeniyle yaşlılarda kaçınılmalıdır."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "Karaciğerde metabolize edilir (ağır hepatik hastalıkta doz ayarı düşünülmeli) (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf("Dar açılı glokom", "Semptomatik prostat hipertrofisi", "Yeni doğanlar"),
            warnings = listOf(
                "Sersemlik yapabilir; araç/makine kullananlarda doz ve uygulama zamanına dikkat",
                "Alkol ve diğer SSS depresanları ile birlikte alınmamalı; toksik dozda halüsinojen olabilir, kötüye kullanım potansiyeli",
                "Antikolinerjik etki: prostat hipertrofisi, dar açılı glokom, astım veya ağır kardiyovasküler hastalıkta dikkat ve yakın izlem"
            ),
            sideEffectsCommon = listOf(
                "Genellikle iyi tolere edilir",
                "en sık yan etki sedasyondur (genellikle birkaç günde kaybolur). SSS: yorgunluk, baş dönmesi, kulak çınlaması, konsantrasyon güçlüğü, koordinasyon bozukluğu, sinirlilik, uykusuzluk, tremor",
                "aşırı dozda ajitasyon/konvülziyon (özellikle çocuklarda), dezoryantasyon, halüsinasyon. GİS: bulantı, kusma, ishal, kolik, ağız kuruluğu, kabızlık. Üriner retansiyon, çarpıntı, baş ağrısı, bulanık görme, artmış göz içi basınç, kas zayıflığı, nadir kan diskrazileri (agranülositoz, hemolitik anemi), aşırı duyarlılık."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kontrendikasyonlar: feniramine aşırı duyarlılık, semptomatik prostat hipertrofisi, MAO-inhibitörü tedavisi alanlar, yenidoğan ve prematüre bebekler",
                "Sersemlik yapabilir; araç/makine kullananlarda doz ve uygulama zamanına dikkat",
                "Alkol ve diğer SSS depresanları ile birlikte alınmamalı; toksik dozda halüsinojen olabilir, kötüye kullanım potansiyeli",
                "Antikolinerjik etki: prostat hipertrofisi, dar açılı glokom, astım veya ağır kardiyovasküler hastalıkta dikkat ve yakın izlem",
                "Anti-emetik etkisi diğer durumların belirtilerini maskeleyebilir",
                "İlaç etkileşimleri: MAO-inhibitörleri antikolinerjik etkiyi uzatır/şiddetlendirir; alkol/SSS depresanları SSS etkilerini artırır; atropin antikolinerjik aktiviteyi artırır",
                "Boş mideye alınmamalı"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "vancomycin",
            nameTr = "Vankomisin",
            nameEn = "Vancomycin",
            genericName = "Vancomycin",
            brandNames = listOf("Vancocin", "Vancomycin Ampul"),
            category = "antibiotic",
            atcCode = null,
            drugClass = "Glikopeptid grubu antibiyotik",
            mechanismTr = "Bakteriyel hücre duvarı peptidoglikan sentezini D-alanyl-D-alanine bölgesine bağlanarak inhibe eder, hücre ölümünü sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("Başlangıç IV dozu gerçek vücut ağırlığına göre, sonraki dozlar terapötik izlemle ayarlanır (ciddi MRSA enfeksiyonlarında AUC izlemi tercih edilir).\n• Oral: 125-500 mg günde 4 kez (C. difficile için; sistemik enfeksiyonda etkisizdir)\n• IV aralıklı infüzyon: 15-20 mg/kg/doz (en yakın 250 mg'a yuvarlanmış) her 8-12 saatte; ciddi MRSA enfeksiyonlarında AUC/MIC 400-600 hedefine göre ayarla\n• Yükleme dozu (ciddi hasta, MRSA): 20-35 mg/kg (gerçek vücut ağırlığı; maks 3 g/doz)\n• Sürekli infüzyon: yükleme 15-20 mg/kg, ardından idame 30-40 mg/kg/gün (60 mg/kg/gün'e kadar), hedef düzey 20-25 mg/L\n• Kan dolaşımı enfeksiyonu: IV: 15-20 mg/kg/doz her 8-12 saatte", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Doz serum konsantrasyon izlemiyle ayarlanmalıdır.\n• Genel doz (Bebek/Çocuk/Adölesan): IV: başlangıç 45-60 mg/kg/gün, 6-8 saatte bir bölünmüş; ciddi MRSA için AUC24 hedefi 400 mg•saat/L\n• Antraks (kutanöz veya sistemik/menenjit, alternatif): IV: 20 mg/kg/doz her 8 saatte; AUC24 400 mg•saat/L hedefine ayarla (veya AUC izlemi yoksa dip 15-20 mg/L)", null)),
            specialPopulations = mapOf(
                "renal_yetmezlik" to "Ciddi nefrotoksiktir! Böbrek yetmezliğinde birikir, eGFR takibi ve plazma düzey takibi (TDM) yapılmalıdır.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde doz ayarlaması gerekmez.",
                "obezite" to "Gerçek vücut ağırlığına göre dozlanır, maksimum tekil doz 2.0 g'ı aşmamalıdır.",
                "geriatri" to "Böbrek fonksiyonları zayıflamış yaşlılarda dozaj aralığı uzatılmalıdır."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Erişkin 4-6 saat (böbrek yetmezliğinde belirgin uzar; son dönem böbrek hastalığı 7.5 gün)",
                elimination = "Belirgin metabolizma yok (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf("Bileşenlerine karşı bilinen aşırı duyarlılık"),
            warnings = listOf(
                "Toksisite riski (akut böbrek hasarı) dip konsantrasyonla artar, özellikle 15-20 mg/L üzerinde; AUC arttıkça (günlük AUC 650-1.300 mg•saat/L üzeri) risk artar",
                "Renal bozuklukta veya diğer nefrotoksik ilaçlarla dikkat; doz ayarı ve yakın izlem gerekir"
            ),
            sideEffectsCommon = listOf(
                "Hipokalemi (%13), bulantı (%17), karın ağrısı (%15), nefrotoksisite (%5",
                "kreatinin artışı, böbrek yetmezliği), ateş, baş ağrısı, ishal, periferik ödem. Frekansı belirtilmeyen: hipotansiyon, şok, enjeksiyon yeri flebiti, anemi, lökopeni. Pazarlama sonrası: kızarma (vankomisin infüzyon reaksiyonu), C. difficile ishali/koliti, ağır deri reaksiyonları (SJS, TEN), anafilaksi, DRESS, agranülositoz, trombositopeni, nötropeni."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Toksisite riski (akut böbrek hasarı) dip konsantrasyonla artar, özellikle 15-20 mg/L üzerinde; AUC arttıkça (günlük AUC 650-1.300 mg•saat/L üzeri) risk artar",
                "Ekstravazasyon ve tromboflebit: IV vankomisin irritandır; doğru kateter yerleşimini sağla, ekstravazasyondan kaçın",
                "Süperenfeksiyon: uzun kullanımda mantar/bakteri süperenfeksiyonu",
                "İnflamatuar bağırsak hastalığı: oral vankomisinde klinik anlamlı serum düzeyleri olabilir",
                "Renal bozuklukta veya diğer nefrotoksik ilaçlarla dikkat; doz ayarı ve yakın izlem gerekir",
                "Oral vankomisin yalnızca CDI/enterokolit için; sistemik enfeksiyonda etkisiz. Parenteral form enterokolitte etkisizdir",
                "İntraoküler (off-label): hemorajik tıkayıcı retinal vaskülit (kalıcı görme kaybı) bildirilmiş",
                "Terapötik düzey/serum konsantrasyon izlemi gereklidir"
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "lipid_emulsion",
            nameTr = "%20 Lipid Emülsiyonu",
            nameEn = "20% Lipid Emulsion",
            genericName = "Intravenous Lipid Emulsion (ILE)",
            brandNames = listOf(
                "Intralipid 20%",
                "Lipofundin"
            ),
            category = "crisis",
            atcCode = "B05XX",
            drugClass = "LAST Resüsitasyon antidotu ve Nutrisyonel Lipid Solüsyonu",
            mechanismTr = "1. 'Lipid Sink' (Lipid Lavabosu) etkisiyle kandaki lipofilik lokal anestezikleri içine çekerek kalpten uzaklaştırır. 2. Miyokard hücrelerine doğrudan serbest yağ asidi sağlayarak ATP üretimini destekler.",
            adultDoses = emptyMap(),
            pediatricDoses = mapOf("last_rescue" to DoseInfo("1.5 mL/kg IV bolus, ardından 0.25 mL/kg/dk infüzyon", "Çocuklarda lokal anestezi toksisitesi (LAST) durumunda standart hayat kurtarıcı dozdur.")),
            specialPopulations = mapOf(
                "mh_kriz" to "Malign hipertermide kullanımı yoktur, karıştırılmamalıdır."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Hemen",
                peak = "Enjeksiyon sonu",
                duration = "Dolaşımdan klirensi hızlıdır",
                halfLife = "30 - 45 dakika",
                elimination = "Retiküloendotelyal sistem ve kas/adipoz doku tarafından lipoprotein lipaz ile yıkım"
            ),
            contraindications = listOf(
                "Lokal anestezik sistemik toksisitesi durumunda mutlak kontrendikasyonu yoktur.",
                "Ağır yumurta/soya alerjisi rölatif kontrendikedir."
            ),
            warnings = listOf(
                "LAST dışı durumlarda aşırı dozda uygulanırsa hipertrigliseridemi, pankreatit ve pulmoner perfüzyon bozukluğu yapabilir.",
                "Maksimum toplam güvenli günlük doz 12 mL/kg'ı aşmamalıdır."
            ),
            sideEffectsCommon = listOf(
                "Hipertrigliseridemi",
                "Bulantı",
                "Hafif ateş artışı"
            ),
            sideEffectsSerious = listOf(
                "Pankreatit",
                "Lipid yüklenme sendromu",
                "Akut Akciğer Hasarı"
            ),
            clinicalPearls = listOf(
                "Lokal anesteziye bağlı gelişen dirençli kardiak arrest resüsitasyonunda, klasik ACLS protokolünden farklı olarak Adrenalin dozları <1 mcg/kg (örn. 10-100 mcg) olarak düşük tutulmalı, vazopressin verilmemelidir. Çünkü yüksek adrenalin lipid sink etkisini zayıflatabilir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "mivacurium",
            nameTr = "Mivakuryum",
            nameEn = "Mivacurium",
            genericName = "Mivacurium",
            brandNames = listOf(
                "Mivacron"
            ),
            category = "nmb",
            atcCode = null,
            drugClass = "Kısa etkili depolarizan olmayan NMB",
            mechanismTr = "Nikotinik asetilkolin reseptörlerini kompetitif olarak bloke eder, plazma kolinesterazı ile hidroliz edilir.",
            adultDoses = mapOf("dosing" to DoseInfo("0.15 - 0.2 mg/kg IV yavaş bolus", "Günübirlik cerrahiler için idealdir. Etki süresi yaklaşık 15-20 dakikadır.")),
            pediatricDoses = mapOf("dosing" to DoseInfo("0.2 mg/kg IV", "Çocuklarda eliminasyon hızı daha yüksektir.")),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "2 - 3 dakika",
                peak = "3 - 5 dakika",
                duration = "15 - 20 dakika",
                halfLife = "2 - 5 dakika",
                elimination = "Plazma kolinesterazı (Psödokolinesteraz) ile hidroliz"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Hızlı enjeksiyonda histamin salınımına bağlı hipotansiyon ve bronkospazm yapabilir.",
                "Psödokolinesteraz eksikliği (atipik enzim) olanlarda uzamış apneye yol açar!"
            ),
            sideEffectsCommon = emptyList(),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kısa etki süresi nedeniyle günübirlik anestezi uygulamalarında entübasyon ve kısa süreli işlemler için mükemmeldir.",
                "Etkisi neostigmin ile geri döndürülebilir ancak psödokolinesteraz eksikliğinde ventilatör desteği şarttır."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "pancuronium",
            nameTr = "Pankuronyum",
            nameEn = "Pancuronium",
            genericName = "Pancuronium",
            brandNames = listOf(
                "Pavulon"
            ),
            category = "nmb",
            atcCode = null,
            drugClass = "Uzun etkili depolarizan olmayan NMB",
            mechanismTr = "Nikotinik asetilkolin reseptörlerini kompetitif bloke eder, hafif vagolitik etkisi bulunur.",
            adultDoses = mapOf("dosing" to DoseInfo("0.08 - 0.1 mg/kg IV bolus", "Uzun süreli cerrahi ve yoğun bakım ventilasyonlarında tercih edilir. Etkisi 60-100 dakika sürer.")),
            pediatricDoses = mapOf("dosing" to DoseInfo("0.1 mg/kg IV", "Yenidoğanlarda hassasiyet artmıştır.")),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "3 - 5 dakika",
                peak = "5 - 8 dakika",
                duration = "60 - 100 dakika",
                halfLife = "2 - 3 saat",
                elimination = "Böbreklerden değişmeden glomerular filtrasyonla atılır, kısmen karaciğer metabolizması"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Vagolitik etki: Kalp hızında ve kan basıncında belirgin artışa (taşikardi) yol açar.",
                "Böbrek yetmezliği olanlarda uzamış nöromüsküler blok riski çok yüksektir."
            ),
            sideEffectsCommon = emptyList(),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Uzun etkili bir ajan olduğu için ameliyat sonu uyanmada residual (kalıntı) blokaj riski yüksektir, mutlaka neostigmin/atropin geri çevrilmesi ve TOF takibi yapılmalıdır.",
                "Kardiyovasküler vagolitik stimülasyon taşikardik hastalarda iskemiyi tetikleyebilir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "urapidil",
            nameTr = "Urapidil",
            nameEn = "Urapidil",
            genericName = "Urapidil",
            brandNames = listOf(
                "Ebrantil",
                "Urapidil Ampul"
            ),
            category = "antiarrhythmic",
            atcCode = null,
            drugClass = "Alfa-1 Blokör ve 5-HT1A Agonisti (Antihistaminik dışı vazodilatör)",
            mechanismTr = "Periferik postsinaptik alfa-1 reseptörlerini bloke ederek sistemik direnci düşürür, merkezi 5-HT1A reseptörlerini uyararak refleks taşikardiyi önler.",
            adultDoses = mapOf("dosing" to DoseInfo("10 - 50 mg IV yavaş bolus", "Kan basıncı durumuna göre titre edilir, ardından gerekirse 2-9 mg/saat infüzyon başlanır.")),
            pediatricDoses = mapOf("dosing" to DoseInfo("0.5 - 1.0 mg/kg IV", "Pediatrik hipertansif krizlerde çok nadir ve dikkatli kullanılır.")),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "3 - 5 dakika",
                peak = "5 - 10 dakika",
                duration = "4 - 6 saat",
                halfLife = "4 - 5 saat",
                elimination = "Karaciğerde metabolize edilir, inaktif metabolitleri idrarla atılır"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Hızlı ve yüksek dozlarda kontrolsüz ortostatik veya sistemik hipotansiyon riski.",
                "Gereksiz kombine vazodilatör kullanımı asistoliye kadar varan ciddi bradikardi yapabilir."
            ),
            sideEffectsCommon = emptyList(),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Sempatolitik etkisi olmasına rağmen refleks taşikardi yapmaması en büyük avantajıdır. İntraoperatif cerrahi uyarılara bağlı akut hipertansif ataklarda (örn. entübasyon, ekstübasyon, diseksiyon) mükemmel etkilidir.",
                "Koroner arter hastalığı olanlarda güvenli vazodilatasyon sağlar."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "dextrose",
            nameTr = "Dekstroz (Glukoz)",
            nameEn = "Dextrose (Glucose)",
            genericName = "Dextrose (Glucose)",
            brandNames = listOf(
                "Dekstroz %5",
                "Dekstroz %10",
                "Dekstroz %50"
            ),
            category = "crisis",
            atcCode = null,
            drugClass = "Karbonhidrat / Hipertonik Solüsyon",
            mechanismTr = "Hücrelere doğrudan glukoz sağlayarak hipoglisemiyi düzeltir, insülinle birlikte potasyumun hücre içine girişini kolaylaştırır.",
            adultDoses = mapOf("dosing" to DoseInfo("10 - 25 g IV (%50 solüsyondan 20-50 mL veya %10 solüsyondan 100-250 mL yavaş IV)", "Hipoglisemi acil tedavisinde verilir, ardından kan şekeri takibiyle idame sıvıya geçilir.")),
            pediatricDoses = mapOf("dosing" to DoseInfo("0.25 - 0.5 g/kg IV (%10'luk solüsyondan 2-5 mL/kg)", "Çocuklarda kesinlikle hipertonik %50'lik dekstroz direkt verilmemelidir, damar iritasyonu ve rebound hipoglisemi riski yüksektir; %10'luk tercih edilir.")),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Hemen",
                peak = "Enjeksiyon sonu",
                duration = "Metabolik hıza bağlı",
                halfLife = "N/A",
                elimination = "Hücresel glikoliz ve glukoz metabolizması"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "%10'dan daha konsantre solüsyonlar periferik damardan hızlı verilirse flebit ve lokal doku nekrozu yapabilir, yavaş veya santral hat tercih edilmelidir.",
                "Hızlı infüzyon ozmotik diüreze ve hiperglisemik komaya yol açabilir."
            ),
            sideEffectsCommon = emptyList(),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Hiperkalemi krizlerinde (Potasyum > 6.0 mEq/L) sodyum bikarbonat ve kalsiyumun yanında, 10 IU regüler insülin + 50 mL %50 Dekstroz IV 20-30 dakikada verilerek potasyum hücre içine çekilir.",
                "Kan şekeri takibi şarttır."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "hypertonic_saline",
            nameTr = "Hipertonik Salin",
            nameEn = "Hypertonic Saline (3% NaCl)",
            genericName = "Hypertonic Saline (3% NaCl)",
            brandNames = listOf(
                "%3 Sodyum Klorür (NaCl)",
                "%7.5 Sodyum Klorür"
            ),
            category = "icu_metabolic",
            atcCode = null,
            drugClass = "Hipertonik Sodyum Klorür Solüsyonu",
            mechanismTr = "İntravasküler ozmolariteyi hızla yükselterek beyin hücrelerindeki ödem sıvısını ozmotik gradyanla damar içine çeker, kafa içi basıncı (ICP) düşürür ve serum sodyum düzeyini artırır.",
            adultDoses = mapOf("dosing" to DoseInfo("150 - 250 mL IV yavaş infüzyon (10-20 dakikada, %3 NaCl)", "Akut kafa içi basınç krizlerinde bolus infüzyon olarak verilir, semptomatik hiponatremi durumlarında ise sodyum açığı formülüne göre titre edilir.")),
            pediatricDoses = mapOf("dosing" to DoseInfo("3 - 5 mL/kg IV yavaş infüzyon (%3 NaCl)", "Pediatrik kafa travması protokollerinde ICP kontrolü için sıklıkla tercih edilir.")),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Hemen",
                peak = "İnfüzyon sonu",
                duration = "Hücresel dengeye bağlı",
                halfLife = "N/A",
                elimination = "Böbreklerden idrarla sodyum ve klor atılımı"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Santral Pontin Miyelinolizis (Ozmotik Demiyelinizasyon Sendromu): Kronik hiponatremi varlığında sodyum düzeyi çok hızlı yükseltilirse beyin sapında demiyelinizasyona bağlı kalıcı felç gelişebilir! Sodyum artış hızı günde 8-10 mEq/L'yi aşmamalıdır.",
                "Damar İritasyonu: Yüksek ozmolarite nedeniyle periferik damardan verilirse ciddi flebit ve nekroz yapar. Mutlaka Santral Venöz Kateterden verilmelidir."
            ),
            sideEffectsCommon = emptyList(),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Akut intrakranial basınç artışında, kalbi mannitol kadar zorlamadan ve diürez yapmadan ICP'yi çok hızlı düşürür; bu nedenle hemodinamisi sınırdaki kafa travmalı hastalarda ilk tercihlerdendir.",
                "İdrar çıkışı ve serum sodyumu sık aralıklarla takip edilmelidir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        ),
        Drug(
            drugId = "albumin",
            nameTr = "Albumin (İnsan)",
            nameEn = "Albumin (Human)",
            genericName = "Albumin (Human)",
            brandNames = listOf(
                "Human Albumin %5",
                "Human Albumin %20",
                "Zenalb"
            ),
            category = "icu_metabolic",
            atcCode = null,
            drugClass = "Doğal Kolloid Solüsyonu (%5 veya %20)",
            mechanismTr = "Vücudun ana plazma proteinidir. İntravasküler onkotik basıncın %80'ini oluşturur, interstisyel alandaki sıvıyı damar yatağına çekerek plazma hacmini genişletir.",
            adultDoses = mapOf("dosing" to DoseInfo("12.5 - 25 g IV yavaş infüzyon (örn. %20'lik solüsyondan 50-100 mL veya %5'lik solüsyondan 250-500 mL)", "Sıvı replasmanı, ciddi hipoalbüminemi, karaciğer sirozu (asit drenajı sonrası her 1 litre için 8g albümin) durumlarında verilir.")),
            pediatricDoses = mapOf("dosing" to DoseInfo("0.5 - 1.0 g/kg IV yavaş infüzyon", "Pediatrik şok resüsitasyonunda veya yenidoğan ödemlerinde kullanılabilir.")),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Infüzyon sonu",
                peak = "15 - 30 dakika",
                duration = "Onkotik etkisi 12 - 24 saat sürer",
                halfLife = "15 - 20 gün",
                elimination = "Hücre içinde lizozomal enzimlerle aminoasitlere yıkılır"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Akut Akciğer Ödemi: Onkotik etkisi nedeniyle intravasküler hacmi çok hızlı genişletebileceği için kalp yetmezliği veya ciddi anemisi olanlarda akciğer ödemine yol açabilir.",
                "Pıhtılaşma Bozukluğu: Çok yüksek hacimlerde verilirse pıhtılaşma faktörlerini dilüe edebilir."
            ),
            sideEffectsCommon = emptyList(),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "%20'lik albümin hiperonkotik bir solüsyondur, verilen her 1 mL albümin damar yatağına interstisyumdan 3-4 mL sıvı çekerek plazmayı genişletir. Ciddi sepsis, yanık ve sirozlu asit hastalarında hacim restorasyonu için paha biçilemez bir doğal kolloiddir.",
                "Enfeksiyon bulaş riskini önlemek için pastörize edilmiştir."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = false
        )
    )
}
