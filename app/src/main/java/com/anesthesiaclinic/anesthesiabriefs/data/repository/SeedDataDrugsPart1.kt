package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo

object SeedDataDrugsPart1 {
    val drugsList = listOf(
        Drug(
            drugId = "propofol",
            nameTr = "Propofol",
            nameEn = "Propofol",
            genericName = "Propofol",
            brandNames = listOf(
                "Diprivan",
                "Propofol Lipuro"
            ),
            category = "induction",
            atcCode = "N01AX10",
            drugClass = "Kısa etkili genel anestezik (GABA-A Agonisti)",
            mechanismTr = "GABA-A reseptör kompleksi üzerinde doğrudan agonist etki göstererek MSS depresyonu sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezi indüksiyonu (sağlıklı erişkin, ASA-PS 1-2): IV 1-2.5 mg/kg toplam doz.\n• İndüksiyon (hemodinamik bozukluk/hipovolemi veya halsiz/ASA-PS 3-4): IV 0.5-1.5 mg/kg.\n• Anestezi idamesi (ASA-PS 1-2): Sürekli IV infüzyon 50-200 mcg/kg/dk; klinik yanıta titre et. ASA-PS 3-4: 50-100 mcg/kg/dk.\n• Hızlı ardışık entübasyon (RSI, ameliyathane dışı): IV 1.5-2 mg/kg (aralık 1-3 mg/kg).\n• ICU'da mekanik ventile hasta sedasyonu: Sürekli IV infüzyon başlangıç 5 mcg/kg/dk; her 5-10 dakikada 5-10 mcg/kg/dk artır; idame 5-50 mcg/kg/dk (maks 60-80 mcg/kg/dk).\n• Monitörize anestezi bakımı (ASA-PS 1-2): Sürekli IV infüzyon başlangıç 25-75 mcg/kg/dk; ya da IV intermitan bolus 10-20 mg.\n• İşlemsel sedasyon (ameliyathane dışı): IV başlangıç 0.5-1 mg/kg, ardından her 1-3 dakikada 0.25-0.5 mg/kg.\n• Refrakter status epileptikus: IV yükleme 1-2 mg/kg, ardından her 3-5 dakikada 0.5-2 mg/kg (maks toplam 10 mg/kg); infüzyon 20 mcg/kg/dk başlangıç, idame 30-60 mcg/kg/dk.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Genel anestezi indüksiyonu (≥3 yaş çocuk, <17 yaş adölesan): IV 2.5-3.5 mg/kg, 20-30 saniyede; ASA-PS III-IV'te daha düşük doz.\n• Genel anestezi idamesi (≥2 ay): İntermitan IV bolus başlangıç 1-4 mg/kg; idame bolus 0.5-2 mg/kg. Sürekli IV infüzyon başlangıç 200-300 mcg/kg/dk (12-18 mg/kg/sa); 30 dk sonra 125-150 mcg/kg/dk (7.5-9 mg/kg/sa).\n• ICU sedasyonu: IV yükleme 0.5-1 mg/kg (maks 50 mg); sürekli infüzyon 16-66 mcg/kg/dk (1-4 mg/kg/sa). PRIS açısından yakın izlem.\n• İşlemsel sedasyon: IV başlangıç 1-2 mg/kg (<3 yaş'ta 2 mg/kg), ardından her 3-5 dakikada 0.5-1 mg/kg.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Bolus infüzyon (doza bağımlı) 9-51 saniye (ortalama 30 saniye)",
                peak = "belirtilmemiş",
                duration = "3-10 dakika (doz, hız ve süreye bağlı; uzun kullanımda dokularda birikip uzayabilir)",
                halfLife = "Bifazik: başlangıç 40 dakika; terminal 4-7 saat (10 günlük infüzyondan sonra 1-3 güne kadar)",
                elimination = "Hepatik; suda çözünür sülfat ve glukuronid konjugatlarına (~%50) (Eliminasyon yeri: İdrar (~%88 metabolit olarak); feçes (<%2))"
            ),
            contraindications = listOf(
                "Yumurta, soya fasulyesi veya propofol bileşenlerine bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Artmış intrakraniyal basınç/bozulmuş serebral dolaşım: dikkatli kullan; MAP'ı belirgin düşürerek serebral perfüzyonu azaltabilir.",
                "Enfeksiyon riski: viyaller mikroorganizma üremesini destekleyebilir; aseptik teknik şart.",
                "Solunum hastalığı ve epilepsi/nöbet öyküsünde dikkat.",
                "Opioidlerle birlikte: sedatif/anestezik etki artar, daha belirgin hipotansiyon; pediatride fentanil ile ciddi bradikardi.",
                "Pediatrik nörotoksisite uyarısı (<3 yaş ve 3. trimester).",
                "Bazı formlar benzil alkol içerir (yenidoğanda 'gasping sendromu' riski)."
            ),
            sideEffectsCommon = listOf(
                "Kardiyovasküler: hipotansiyon, bradikardi, aritmiler, düşük kardiyak debi",
                "Solunum: apne, bronkospazm, hava yolu obstrüksiyonu, hipoventilasyon",
                "Lokal: enjeksiyon yerinde yanma/ağrı, flebit",
                "Nörolojik: baş dönmesi, anormal rüyalar, deliryum",
                "Diğer: hiperlipidemi, propofol infüzyon sendromu (PRIS), kas rijiditesi/miyoklonus, anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Enjeksiyon ağrısı: Lidokain %1 ile azaltılabilir.",
                "Artmış intrakraniyal basınç/bozulmuş serebral dolaşım: dikkatli kullan; MAP'ı belirgin düşürerek serebral perfüzyonu azaltabilir.",
                "Enfeksiyon riski: viyaller mikroorganizma üremesini destekleyebilir; aseptik teknik şart.",
                "Solunum hastalığı ve epilepsi/nöbet öyküsünde dikkat.",
                "Opioidlerle birlikte: sedatif/anestezik etki artar, daha belirgin hipotansiyon; pediatride fentanil ile ciddi bradikardi.",
                "Pediatrik nörotoksisite uyarısı (<3 yaş ve 3. trimester).",
                "Bazı formlar benzil alkol içerir (yenidoğanda 'gasping sendromu' riski)."
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
            drugId = "ketamine",
            nameTr = "Ketamin",
            nameEn = "Ketamine",
            genericName = "Ketamine Hydrochloride",
            brandNames = listOf(
                "Ketalar",
                "Ketamine"
            ),
            category = "induction",
            atcCode = "N01AX03",
            drugClass = "Dissosiyatif anestezik (NMDA Reseptör Antagonisti)",
            mechanismTr = "NMDA reseptörlerini bloke ederek talamokortikal sistemi dissosiye eder, güçlü analjezi ve amnezi sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut/şiddetli ajitasyon (ek ajan, endikasyon dışı): IV başlangıç 1-2 mg/kg, 30-60 saniyede; gerekirse 5-10 dk sonra 0.5-1 mg/kg tekrar. IM başlangıç 4-6 mg/kg; gerekirse 10-25 dk sonra 2-3 mg/kg (maks önerilen 400 mg).\n• Subanestezik analjezi - akut ağrı: IV intermitan infüzyon 0.1-0.5 mg/kg (genelde 10-30 mg). IV sürekli infüzyon: 0.25-0.5 mg/kg bolus (maks 35 mg), ardından 0.05-0.25 mg/kg/sa; aralık 0.05-1 mg/kg/sa.\n• İntranazal (endikasyon dışı): 0.2-1 mg/kg.\n• Kronik inatçı ağrı: IV intermitan 0.25-0.6 mg/kg (maks 60 mg) 4-6 saatlik infüzyon; IV sürekli 0.05-0.15 mg/kg/sa. SC: 0.1-0.6 mg/kg. Oral başlangıç 0.5 mg/kg/gün, 3-4 bölünmüş dozda (maks 800 mg/gün).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı (düşük doz, sub-dissosiyatif; ≥3 yaş): İntranazal 1 mg/kg, bir kez tekrar edilebilir; aralık 0.5-1.5 mg/kg (maks 100 mg/doz).\n• Pre-anestezik sedasyon - İntranazal: ≥6 ay 3 mg/kg; <2 yaş 3-5 mg/kg; 2-7 yaş 3-6 mg/kg (yarısı her buruna), mask indüksiyondan önce.\n• Oral (≤8 yaş): 6-8 mg/kg, cerrahiden 20-30 dk önce.\nNot: ACEP <3 ay bebeklerde ketamin kullanımını mutlak kontrendikasyon kabul eder (hava yolu komplikasyon riski).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV anestezik <30 saniye; IM 3-4 dk (analjezi 10-15 dk); intranazal analjezi <10 dk; oral analjezi <30 dk",
                peak = "belirtilmemiş",
                duration = "IV anestezik 5-10 dk (toparlanma 1-2 saat); IM 12-25 dk (toparlanma 3-4 saat)",
                halfLife = "Alfa 10-15 dk; Beta 2.5 saat",
                elimination = "Yoğun hepatik metabolizma (N-demetilasyon; CYP2B6 ve CYP3A4) ile aktif metabolit norketamine (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Şiddetli kontrolsüz hipertansiyon, aktif iskemik kalp hastalığı",
                "Kafa içi basınç (ICP) artışının kritik olduğu durumlar (rölatif)"
            ),
            warnings = listOf(
                "30-60 saniyede yavaş IV verilmeli (solunum depresyonu/apne riski).",
                "Artmış intrakraniyal basınç: CNS kitlesi/anomali/hidrosefalide göreceli kontrendikasyon kabul edenler var.",
                "Artmış intraoküler basınç: açık göz yaralanmasında dikkat.",
                "Pediatrik nörotoksisite uyarısı."
            ),
            sideEffectsCommon = listOf(
                "Nörolojik: anesteziden uzamış çıkış (ajitasyon, konfüzyon, deliryum, halüsinasyon, canlı imgeler) (%12), psikiyatrik bozukluk, hipertoni (tonik-klonik hareketler)",
                "Kardiyovasküler: kan basıncı/kalp hızı artışı veya azalması, aritmi",
                "GİS: bulantı-kusma",
                "Oftalmik: diplopi, intraoküler basınç artışı, nistagmus",
                "Postmarketing: laringospazm, apne, solunum depresyonu, sistit/mesane disfonksiyonu, hepatobiliyer bozukluk, anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "30-60 saniyede yavaş IV verilmeli (solunum depresyonu/apne riski).",
                "CNS depresyonu: 24 saat araç/makine kullanımından kaçınılmalı.",
                "Artmış intrakraniyal basınç: CNS kitlesi/anomali/hidrosefalide göreceli kontrendikasyon kabul edenler var.",
                "Artmış intraoküler basınç: açık göz yaralanmasında dikkat.",
                "Tekrarlayan kullanımda hepatobiliyer disfonksiyon ve skleroza kolanjit.",
                "Porfiri ve tiroid bozukluğu: ACEP göreceli kontrendikasyon kabul eder (sempatomimetik etki artışı).",
                "Pediatrik nörotoksisite uyarısı."
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
            drugId = "etomidate",
            nameTr = "Etomidat",
            nameEn = "Etomidate",
            genericName = "Etomidate",
            brandNames = listOf(
                "Hypnomidate"
            ),
            category = "induction",
            atcCode = "N01AX07",
            drugClass = "Kısa etkili genel anestezik (GABA-A Agonisti)",
            mechanismTr = "GABA-A reseptörlerine bağlanarak klor kanallarını açık tutar, hemodinamik parametreleri etkilemeden hipnoz sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Genel anestezi indüksiyonu: IV başlangıç 0.3 mg/kg, 30-60 saniyede; olağan aralık 0.2-0.6 mg/kg.\n• İşlemsel sedasyon (endikasyon dışı): IV başlangıç 0.1-0.2 mg/kg, ardından her 3-5 dakikada 0.05 mg/kg.\n• Hızlı ardışık entübasyon (RSI, ameliyathane dışı): IV başlangıç 0.3 mg/kg; aralık 0.2-0.3 mg/kg (analjezik etkisi yoktur).\n• Subpotent anesteziklere takviye: kısa işlemlerde küçük artımlarla (genelde indüksiyon dozundan küçük).\n• Cushing sendromu (endikasyon dışı): IV yükleme 3-5 mg, ardından 0.02-0.05 mg/kg/sa sürekli infüzyon (genelde 1.5-4 mg/sa); serum kortizoluna titre.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Anestezi indüksiyonu: Bebekler IV 0.2-0.3 mg/kg tek doz; <10 yaş çocuk 0.2-0.3 mg/kg; ≥10 yaş ve adölesan 0.3 mg/kg (aralık 0.2-0.6 mg/kg).\n• İşlemsel sedasyon (≥6 ay bebek, çocuk, adölesan): IV başlangıç 0.2-0.3 mg/kg (aralık 0.1-0.4 mg/kg).\n• Hızlı ardışık entübasyon (RSI): IV/intraosseöz 0.2-0.4 mg/kg tek doz (maks 20 mg). Not: Septik şokta önerilmez (adrenokortikal supresyon-mortalite ilişkisi).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "30-60 saniye",
                peak = "1 dakika",
                duration = "Doza bağımlı: 2-3 dk (0.15 mg/kg); 3-5 dk (0.3 mg/kg); hızlı toparlanma redistribüsyon nedeniyle",
                halfLife = "Terminal 2.6-3.5 saat",
                elimination = "Hepatik ve plazma esterazları (Eliminasyon yeri: İdrar ~%75 (%80 metabolit; %2 değişmemiş ilaç))"
            ),
            contraindications = listOf(
                "Etomidata karşı bilinen aşırı duyarlılık",
                "Adrenal yetmezlik, ciddi Addison hastalığı"
            ),
            warnings = listOf(
                "Renal yetmezlikte toksisite riski yüksek; renal fonksiyonu izle.",
                "Pediatrik nörotoksisite uyarısı. Deneyimli personel tarafından uygulanmalı."
            ),
            sideEffectsCommon = listOf(
                "Nörolojik: miyoklonus (%33)",
                "Endokrin/metabolik: adrenal supresyon",
                "GİS: bulantı, kusma (anesteziden çıkışta), hıçkırık",
                "Lokal: enjeksiyon yerinde ağrı (%30-80)",
                "Kas-iskelet: geçici iskelet kası hareketleri",
                "Oftalmik: nistagmus",
                "Nadir/postmarketing: apne, bradikardi, hipotansiyon, laringospazm, masseter kas spazmı, kortizol sentezinde azalma."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Adrenal steroid yapımını baskılar (11-B-hidroksilaz inhibisyonu): tek indüksiyon dozu stres kaynaklı kortizol artışını 6-8 saat (yaşlı/halsiz hastada 24 saate kadar) bloke eder.",
                "ICU'da sürekli infüzyon mortaliteyi artırabilir; üretici sürekli infüzyonu önermez.",
                "Kalp yetmezliği: altta yatan miyokard disfonksiyonunu kötüleştirebilir.",
                "Renal yetmezlikte toksisite riski yüksek; renal fonksiyonu izle.",
                "Yaşlıda kardiyak depresyon (özellikle hipertansiyon).",
                "Pediatrik nörotoksisite uyarısı. Deneyimli personel tarafından uygulanmalı."
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
            drugId = "midazolam",
            nameTr = "Midazolam",
            nameEn = "Midazolam",
            genericName = "Midazolam Hydrochloride",
            brandNames = listOf(
                "Dormicum",
                "Versed"
            ),
            category = "induction",
            atcCode = "N05CD08",
            drugClass = "Kısa etkili Benzodiazepin (GABA-A Pozitif Allosterik Modülatör)",
            mechanismTr = "GABA-A reseptörlerindeki benzodiazepin bölgesine bağlanarak GABA'nın inhibitör etkisini güçlendirir; sedasyon, amnezi ve antikonvülzan etki sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Premedikasyon/preindüksiyon: IV 0.5-2 mg, 0.5-1 mg artımlarla klinik etkiye göre.\n• İndüksiyon (ek ajan): IV 0.5-2 mg, 0.5-1 mg artımlarla. (Tek/ko-indüksiyon: premedikeli 0.1-0.25 mg/kg, premedike olmayan 0.35 mg/kg'a kadar; nadiren kullanılır.)\n• Akut/şiddetli ajitasyon (endikasyon dışı): IV/IM başlangıç 2.5-5 mg; IV her 3-5 dakikada, IM her 5-10 dakikada tekrar; bazı hastalar ~20 mg.\n• ICU mekanik ventile sedasyon (alternatif): IV intermitan başlangıç 0.5-5 mg veya 0.01-0.05 mg/kg; sürekli infüzyon yükleme 0.5-5 mg, ardından 1-8 mg/sa veya 0.01-0.1 mg/kg/sa.\n• İşlemsel anksiyete (premedikasyon): IM 0.07-0.08 mg/kg; IV başlangıç 0.5-2 mg veya 0.01-0.02 mg/kg.\n• Palyatif/yaşam sonu sedasyon (endikasyon dışı): IV 1-5 mg; SC 2.5-10 mg; sürekli infüzyon 0.25-1 mg/sa başlangıç.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• İşlem/anestezi öncesi sedasyon-anksiyoliz-amnezi: IM 0.1-0.15 mg/kg (maks 10 mg). IV: ≥6 ay-<6 yaş 0.05-0.1 mg/kg (toplam 0.6 mg/kg'a kadar, maks 6 mg); ≥6 yaş 0.025-0.05 mg/kg (maks 10 mg); adölesan 1-2.5 mg (maks 10 mg).\n• İntranazal: 0.2 mg/kg tek doz, 15 dk'da tekrar; aralık 0.2-0.8 mg/kg (maks 10 mg).\n• Oral (≥6 ay, <16 yaş): 0.25-0.5 mg/kg tek doz (maks 20 mg).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV 1-5 dk; IM çocuk <5 dk, erişkin ~15 dk; oral 10-20 dk; intranazal sprey <10 dk",
                peak = "IV 3-5 dk; IM çocuk 15-30 dk, erişkin 30-60 dk; intranazal sprey 30 dk",
                duration = "Kısa etkili benzodiazepin; sedasyon: erişkin IM 20-120 dk, IV 7-75 dk",
                halfLife = "Erişkin ~3-6 saat (kaynakta erişkin tam değer kesilmiş; preterm yenidoğan medyan 6.3 saat)",
                elimination = "Yoğun hepatik, CYP3A4 ile; aktif metabolit 1-hidroksi-midazolam (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Akut dar açılı glokom",
                "Şiddetli KOAH veya solunum yetmezliği durumları"
            ),
            warnings = listOf(
                "Ciddi kardiyorespiratuvar olaylar (kalp durması, kalıcı nörolojik hasar, ölüm) bildirilmiştir; anormal hava yolu, siyanotik konjenital kalp hastalığı, sepsis veya ciddi pulmoner hastalıkta risk artar.",
                "Hipotansiyon (özellikle pediatride ve opioid alanlarda).",
                "İntranazal: suisidal düşünce riskinde artış.",
                "Akut hastalık, kalp yetmezliği, glokomda dikkat. Flumazenil hazır bulundurulmalı."
            ),
            sideEffectsCommon = listOf(
                "Solunum: apne (erişkin %15, çocuk %3), bradipne (≤%23), tidal hacim azalması",
                "GİS: kusma (≤%11), bulantı, hıçkırık",
                "Kardiyovasküler: hipotansiyon, bradikardi, taşikardi",
                "Nörolojik: emergence ajitasyon, sersemlik, deliryum, baş ağrısı",
                "Lokal: enjeksiyon yerinde ağrı",
                "İntranazal: burunda rahatsızlık, dizguzi",
                "Hipersensitivite: anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ciddi kardiyorespiratuvar olaylar (kalp durması, kalıcı nörolojik hasar, ölüm) bildirilmiştir; anormal hava yolu, siyanotik konjenital kalp hastalığı, sepsis veya ciddi pulmoner hastalıkta risk artar.",
                "Anterograd amnezi yapar.",
                "CNS depresyonu: araç/makine kullanımı için en az 1 gün beklenmeli.",
                "Hipotansiyon (özellikle pediatride ve opioid alanlarda).",
                "Paradoksal reaksiyonlar (özellikle pediatri, yaşlı, alkol/psikiyatrik öyküde).",
                "İntranazal: suisidal düşünce riskinde artış.",
                "Akut hastalık, kalp yetmezliği, glokomda dikkat. Flumazenil hazır bulundurulmalı."
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
            drugId = "dexmedetomidine",
            nameTr = "Deksmedetomidin",
            nameEn = "Dexmedetomidine",
            genericName = "Dexmedetomidine Hydrochloride",
            brandNames = listOf(
                "Precedex"
            ),
            category = "sedative",
            atcCode = "N05CM18",
            drugClass = "Selektif Alfa-2 Adrenerjik Reseptör Agonisti",
            mechanismTr = "Locus coeruleus'taki presinaptik alfa-2 reseptörlerini uyararak noradrenalin salınımını inhibe eder; uykuyu andıran (doğal uyku) sedasyon ve analjezi sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Genel anestezi idamesi (ek ajan, endikasyon dışı): Sürekli IV infüzyon 0.1-0.8 mcg/kg/sa; istenen etkiye titre.\n• İşlemsel sedasyon/monitörize anestezi bakımı (uyanık fiberoptik entübasyon dahil): IV yükleme 0.5-1 mcg/kg 10 dakikada, ardından 0.2-1 mcg/kg/sa sürekli infüzyon.\n• Kritik hasta sedasyonu: IV yükleme (opsiyonel) 1 mcg/kg 10 dakikada, ardından sürekli infüzyon 0.2-1.5 mcg/kg/sa; her 30 dakikada 0.2 mcg/kg/sa titre. (Monoterapi ile derin sedasyon sağlanamaz.)\n• Şizofreni/bipolar bozuklukta ajitasyon (alternatif): Sublingual film: hafif-orta ajitasyon başlangıç 120 mcg (maks 240 mcg/gün); şiddetli ajitasyon başlangıç 180 mcg (maks 360 mcg/gün).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• ICU sedasyonu: IV yükleme (opsiyonel) 0.5-1 mcg/kg 10 dakikada; sürekli infüzyon başlangıç 0.2-0.5 mcg/kg/sa, 0.1-0.3 mcg/kg/sa artırarak; aralık 0.2-2.5 mcg/kg/sa.\n• Postoperatif jonksiyonel ektopik taşikardi önlenmesi: yükleme 0.5-1 mcg/kg 15-20 dakikada; sürekli infüzyon 0.5-0.75 mcg/kg/sa, 48 saat.\n• Noninvaziv işlemler için sedasyon (nonintübe): İntranazal 2-3 mcg/kg tek doz, işlemden 30-60 dk önce; aralık 1-4 mcg/kg.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV yükleme 5-10 dk; intranazal erişkin 45-60 dk (çocuk 10-20 dk)",
                peak = "IV yükleme 15-30 dk; IV sürekli infüzyon 60 dk; intranazal 90-105 dk",
                duration = "Sürekli infüzyon sonrası (doza bağımlı) 60-240 dk",
                halfLife = "IM terminal 281±177 dk; IV preterm yenidoğan medyan 7.6 saat (erişkin değeri kaynakta kesilmiş)",
                elimination = "Hepatik; N-glukuronidasyon, N-metilasyon ve CYP2A6 (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "İleri derece kalp blokları (2. veya 3. derece AV blok)",
                "Ciddi kontrolsüz hipotansiyon veya bradikardi"
            ),
            warnings = listOf(
                "Dozlama hataları: idame dozu mcg/kg/saat olarak ifade edilir; dikkatli olunmalı.",
                "Kalp bloğu, bradikardi, ciddi ventriküler disfonksiyon, hipovolemi, kronik hipertansiyonda dikkat; miyokard disfonksiyonunu kötüleştirebilir.",
                "Sublingual film: hipotansiyon, ileri kalp bloğu, QT uzaması/torsades riski olanlarda kaçınılmalı.",
                "Diyabet, karaciğer yetmezliği, yaşlıda dikkat (KV olaylar belirgin olabilir).",
                "Yükleme dozları hemodinamik bozulma riski nedeniyle önerilmeyebilir. IV form deneyimli personelce, sürekli monitör altında uygulanmalı."
            ),
            sideEffectsCommon = listOf(
                "Kardiyovasküler (>%10): bradikardi (erişkin IV %5-42",
                "çocuk %57-71), hipertansiyon, hipotansiyon (erişkin IV %24-56), taşikardi. Yükleme dozlarında bradikardi/hipotansiyon ve paradoksal geçici hipertansiyon görülebilir. Uzun/yüksek doz kullanımda kesilmeyle çekilme sendromu (hipertansiyon, taşikardi, deliryum, ajitasyon)."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Dozlama hataları: idame dozu mcg/kg/saat olarak ifade edilir; dikkatli olunmalı.",
                "Yeterli/güvenilir amnezi sağlamaz; amnestik ajan eklenmesi gerekebilir.",
                "Kalp bloğu, bradikardi, ciddi ventriküler disfonksiyon, hipovolemi, kronik hipertansiyonda dikkat; miyokard disfonksiyonunu kötüleştirebilir.",
                "Sublingual film: hipotansiyon, ileri kalp bloğu, QT uzaması/torsades riski olanlarda kaçınılmalı.",
                "Diyabet, karaciğer yetmezliği, yaşlıda dikkat (KV olaylar belirgin olabilir).",
                "Yükleme dozları hemodinamik bozulma riski nedeniyle önerilmeyebilir. IV form deneyimli personelce, sürekli monitör altında uygulanmalı.",
                "Ani kesmekten kaçın; dozu kademeli azalt."
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
            drugId = "thiopental",
            nameTr = "Tiyopental",
            nameEn = "Thiopental",
            genericName = "Thiopental",
            brandNames = emptyList(),
            category = "induction",
            atcCode = null,
            drugClass = "Barbitürat grubu kısa etkili genel anestezik",
            mechanismTr = "GABA-A reseptörü üzerinden klor kanallarını açık tutarak MSS depresyonu sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezi indüksiyonu: IV 3-6 mg/kg; idame 25-100 mg gerektikçe.\n• Antikonvülzan: IV 75-250 mg/doz.\n• Artmış intrakraniyal basınç: IV 1.5-3.5 mg/kg/doz.\n(Not: Doz aralıkları reçeteleme rehberi değildir; yerel reçeteleme bilgisine başvurun.)", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("PDF'te belirtilmemiş", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "belirtilmemiş (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Endikasyonlar: Anestezi indüksiyonu ve anesteziye ek ajan; konvülsif durumların tedavisi; artmış intrakraniyal basınç tedavisi.",
                "(Konsis format - ayrıntılı uyarı/önlem bilgisi PDF'te yok.)"
            ),
            sideEffectsCommon = listOf(
                "PDF'te belirtilmemiş (konsis format)"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Farmakolojik kategori: Antikonvülzan/Barbitürat ve genel anestezik.",
                "Endikasyonlar: Anestezi indüksiyonu ve anesteziye ek ajan; konvülsif durumların tedavisi; artmış intrakraniyal basınç tedavisi.",
                "(Konsis format - ayrıntılı uyarı/önlem bilgisi PDF'te yok.)"
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
            drugId = "diazepam",
            nameTr = "Diazepam",
            nameEn = "Diazepam",
            genericName = "Diazepam",
            brandNames = listOf(
                "Diaѕtаt AcuDial [DSC];",
                "Diaѕtat Pediatric [DSC];",
                "ԁiаzеPΑM Intensol;",
                "Libеrvaոt [DSC];",
                "Valiսm;"
            ),
            category = "induction",
            atcCode = null,
            drugClass = "Uzun etkili Benzodiazepin (GABA-A Allosterik Modülatör)",
            mechanismTr = "GABA-A reseptörüne allosterik olarak bağlanarak GABA'nın inhibitör etkisini artırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• İşlemsel anksiyete (premedikasyon): IV 2-10 mg veya 0.03-0.1 mg/kg (maks tek doz 10 mg), işlemden 5-15 dk önce; gerekirse başlangıç dozun ~%50'si tekrar. Oral (endikasyon dışı): 2-10 mg, işlemden 30-60 dk önce.\n• Akut/şiddetli anksiyete: IM/IV/Oral 2-10 mg her 3-6 saatte, 40 mg/güne kadar.\n• Anksiyete bozuklukları: Oral başlangıç 2-5 mg günde 1-2 kez; kademeli artış 40 mg/güne kadar 2-4 bölünmüş dozda.\n• Alkol yoksunluğu: Semptom tetikli IV/Oral 5-10 mg gerektikçe; ya da sabit doz rejimi (Gün 1: 10 mg q6h → kademeli azalt).\n• İntoksikasyon (sempatomimetik): IV 2-10 mg her 3-10 dakikada (şiddetli ajitasyonda 20 mg'a kadar).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut nöbetler (bukkal film, 2-5 yaş): kiloya göre 5-15 mg; 4 saatte tekrar edilebilir, 24 saatte 2 dozu aşma.\n• Akut nöbetler (intranazal): yaş ve kiloya göre değişir (genelde 0.5 mg/kg temelli, 2-<6 yaş; 0.3 mg/kg, 6-<12 yaş; 0.2 mg/kg, ≥12 yaş); 5-20 mg arası kilo bantları.\n(Not: Anestezi-spesifik pediatrik doz PDF'te yer almamaktadır; veriler nöbet endikasyonu içindir.)", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Sedasyon (pediatrik) IV 4-5 dk; status epileptikus IV 1-3 dk, rektal 2-10 dk",
                peak = "belirtilmemiş",
                duration = "Uzun etkili benzodiazepin (yarılanma ömrü >40 saat); sedasyon pediatrik IV 60-120 dk",
                halfLife = "Erişkin parent ~60-72 saat; desmetildiazepam ~152-174 saat (IV parent 33-45 saat)",
                elimination = "Hepatik; CYP3A4 ve 2C19 ile N-desmetildiazepam ve temazepam (aktif) metabolitlerine (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık",
                "Akut dar açılı glokom",
                "Ciddi solunum yetmezliği"
            ),
            warnings = listOf(
                "Opioid alanlarda veya ciddi kronik hastalıkta (ör. solunum bozukluğu) dozu azalt/kaçın.",
                "Anterograd amnezi ve CNS depresyonu (araç/makine uyarısı).",
                "Solunum hastalığında (KOAH, uyku apnesi) dozu azalt/kaçın - ciddi solunum depresyonu.",
                "Hepatik/renal yetmezlikte dikkat.",
                "İntranazal: suisidal düşünce riski.",
                "Aktif metabolitlerin uzun yarılanma ömrü nedeniyle halsiz/yaşlıda birikim riski."
            ),
            sideEffectsCommon = listOf(
                "Nörolojik: sersemlik (%23), ataksi, baş ağrısı, konfüzyon, anterograd amnezi, paradoksal CNS stimülasyonu",
                "Kardiyovasküler: hipotansiyon, vazodilatasyon, bradikardi",
                "Solunum: hipoventilasyon, solunum depresyonu",
                "GİS: bulantı, kabızlık",
                "Lokal: lokalize flebit. İlaç bağımlılığı/çekilme."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Opioid alanlarda veya ciddi kronik hastalıkta (ör. solunum bozukluğu) dozu azalt/kaçın.",
                "Anterograd amnezi ve CNS depresyonu (araç/makine uyarısı).",
                "Paradoksal reaksiyonlar (özellikle pediatri, yaşlı, alkol/psikiyatrik öyküde).",
                "Depresyonda kaçın (akut/acil durumlar hariç).",
                "Solunum hastalığında (KOAH, uyku apnesi) dozu azalt/kaçın - ciddi solunum depresyonu.",
                "Hepatik/renal yetmezlikte dikkat.",
                "İntranazal: suisidal düşünce riski.",
                "Aktif metabolitlerin uzun yarılanma ömrü nedeniyle halsiz/yaşlıda birikim riski."
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
            drugId = "lorazepam",
            nameTr = "Lorazepam",
            nameEn = "Lorazepam",
            genericName = "Lorazepam",
            brandNames = listOf(
                "Αtivаn;",
                "ԼОRаzeрam Intensol;",
                "Լoreеv XR"
            ),
            category = "induction",
            atcCode = null,
            drugClass = "Orta etkili Benzodiazepin (GABA-A Allosterik Modülatör)",
            mechanismTr = "GABA-A reseptörüne allosterik bağlanarak inhibitör GABAerjik sinaps iletimini kolaylaştırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• İşlemsel anksiyete (premedikasyon): IV 1-4 mg veya 0.02-0.04 mg/kg (maks tek doz 4 mg), işlemden 5-20 dk önce; gerekirse ~%50 doz ≥5 dk sonra tekrar. Oral/Sublingual (endikasyon dışı): 0.5-2 mg, işlemden 30-90 dk önce.\n• Akut/şiddetli anksiyete-ajitasyon: IM/IV/Oral 0.5-2 mg her 4-6 saatte, 10 mg/güne kadar; ileri ajite hastalarda 4 mg'a kadar, IM/IV 10-30 dakikada tekrar.\n• Anksiyete bozuklukları: Oral başlangıç 0.5-1 mg günde 2-3 kez; 6 mg/güne kadar (bazı hastalarda 10 mg/gün).\n• Katatoni (endikasyon dışı): IV 1-2 mg; idame 1-2 mg günde 3 kez, 6-24 mg/güne kadar.\n• İleri kanser/palyatif: IM/IV/Oral 0.5-1 mg her 6-12 saatte.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut ajitasyon/anksiyete: <12 yaş Oral/IV/IM 0.05-0.1 mg/kg/doz (maks 2 mg) her 4-8 saatte; ≥12 yaş 0.05-0.1 mg/kg/doz günde 2-3 kez (maks 2 mg).\n• Mekanik ventile hasta sedasyonu: Oral/IV başlangıç 0.05-0.1 mg/kg/doz her 4 saatte (maks ilk doz 4 mg).\n• İşlemsel sedasyon: Oral 0.05 mg/kg (aralık 0.02-0.09 mg/kg).\n• Status epileptikus: IV 0.1 mg/kg yavaş; 5-10 dk'da tekrar (maks 4 mg). İntranazal 0.1 mg/kg (maks 5 mg) - midazolam tercih edilir.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Antiseizure IV <10 dk; sedasyon IV 15-20 dk, oral 20-30 dk; hipnoz IM 20-30 dk",
                peak = "belirtilmemiş (Time to peak: IM ≤3 saat, oral IR ~2 saat)",
                duration = "Kısa-orta etkili benzodiazepin; anestezi premedikasyonu IM/IV ~6-8 saat",
                halfLife = "Erişkin oral IR ~12 saat; IV ~14 saat; IM ~13-18 saat",
                elimination = "Hepatik; hızla lorazepam glukuronide (inaktif) konjuge (Eliminasyon yeri: İdrar (~%88, ağırlıklı inaktif metabolitler); feçes (~%7))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık",
                "Ağır solunum yetmezliği",
                "Uyku apnesi"
            ),
            warnings = listOf(
                "Opioid alanlarda veya ciddi kronik hastalıkta dozu azalt/kaçın.",
                "Hepatik yetmezlik/ensefalopatide dikkat (düşük doz); hepatik ensefalopatiyi kötüleştirebilir.",
                "Solunum hastalığında (KOAH, uyku apnesi) dozu azalt/kaçın.",
                "Yaşlı hastalarda ölüm riski artışı (özellikle demanslı yaşlılarda ilk 4 ay).",
                "Düşme riski yüksek hastalarda dikkat.",
                "Bazı formlar benzil alkol/polietilen glikol içerir; yenidoğanda dikkat."
            ),
            sideEffectsCommon = listOf(
                "Nörolojik: sersemlik, sedasyon, baş dönmesi (%7), konfüzyon, deliryum, halüsinasyon",
                "Solunum: apne (%1), hipoventilasyon, solunum yetmezliği, solunum depresyonu",
                "Kardiyovasküler: hipotansiyon",
                "Lokal: enjeksiyon yerinde ağrı (IM %1-17). Bağımlılık/çekilme, paradoksal reaksiyon, anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Opioid alanlarda veya ciddi kronik hastalıkta dozu azalt/kaçın.",
                "Depresyonda kaçın (akut/acil durumlar hariç).",
                "Hepatik yetmezlik/ensefalopatide dikkat (düşük doz); hepatik ensefalopatiyi kötüleştirebilir.",
                "Solunum hastalığında (KOAH, uyku apnesi) dozu azalt/kaçın.",
                "Opioidlerle: düşük başlangıç dozu ve titrasyon.",
                "Yaşlı hastalarda ölüm riski artışı (özellikle demanslı yaşlılarda ilk 4 ay).",
                "Düşme riski yüksek hastalarda dikkat.",
                "Bazı formlar benzil alkol/polietilen glikol içerir; yenidoğanda dikkat.",
                "Flumazenil uzun süreli kullananlarda çekilme yapabilir."
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
            drugId = "sevoflurane",
            nameTr = "Sevofluran",
            nameEn = "Sevoflurane",
            genericName = "Sevoflurane",
            brandNames = listOf(
                "Sevorane",
                "Sofrane"
            ),
            category = "inhalational",
            atcCode = "N01AB08",
            drugClass = "Halojenli Eter (Florlu İnhaler Anestezik)",
            mechanismTr = "MSS'de GABA-A ve glisin reseptörlerini potansiyalize ederken, NMDA ve nikotinik asetilkolin reseptörlerini bloke ederek anestezi, amnezi ve immobilizasyon sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezi: İnhalasyon; cerrahi anestezi düzeyleri genelde %0.5-3 konsantrasyonlarla (nitröz oksitli/oksitsiz) sağlanır; amnezi ve bilinç kaybının oluştuğu konsantrasyon %0.6.\n• MAC değerleri (oksijen içinde): 25 yaş %2.6; 40 yaş %2.1; 60 yaş %1.7; 80 yaş %1.4.\n• MAC değerleri (%65 N2O/%35 oksijen): 25 yaş %1.4; 40 yaş %1.1; 60 yaş %0.9; 80 yaş %0.7.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Anestezi: İnhalasyon; cerrahi düzeyler genelde %0.5-3 ile sağlanır.\n• MAC değerleri (oksijen içinde): 0-1 ay term yenidoğan %3.3; 1-<6 ay %3; 6 ay-<1 yaş %2.8; 1-<3 yaş %2.8; 3-12 yaş %2.5.\n• MAC (%65 N2O ile): 6 ay-<1 yaş %2; (%60 N2O ile) 1-<3 yaş %2.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "İndüksiyon süresi 2-3 dakika içinde",
                peak = "belirtilmemiş",
                duration = "Emergence süresi kesildiğindeki kan konsantrasyonuna bağlı; düşük kan/gaz solubilitesi (0.63) nedeniyle hızlı",
                halfLife = "belirtilmemiş",
                elimination = "Hepatik (~%5) CYP2E1 ile (Eliminasyon yeri: Soluk gazları (ekshalasyon))"
            ),
            contraindications = listOf(
                "Malign hipertermi öyküsü veya genetik yatkınlığı",
                "Sevoflurana karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Malign hipertermi tetikleyebilir (bazı vakalar fatal); süksinilkolin ile risk artar - dantrolen ile tedavi.",
                "Hepatik etki: postoperatif hepatit/hepatik disfonksiyon; önceki halojenli anestezik maruziyeti riski artırır.",
                "Pediatride perioperatif hiperkalemi (özellikle nöromusküler hastalık, ör. Duchenne); aritmi açısından izlem.",
                "Doza bağımlı hipotansiyon (diğer inhalan anesteziklerden daha hızlı olabilir).",
                "Serebral vazodilatasyon ile intrakraniyal basınç artışı.",
                "QT uzaması/torsades riski; QT uzatan ilaçlarla dikkat."
            ),
            sideEffectsCommon = listOf(
                "GİS: bulantı (%25), kusma (%18)",
                "Nörolojik: ajitasyon (%7-15), sersemlik, titreme",
                "Kardiyovasküler: hipotansiyon (%4-11), bradikardi, taşikardi",
                "Solunum: artmış öksürük (%5-11), laringospazm (%2-8), hava yolu obstrüksiyonu, apne",
                "Ciddi: malign hipertermi, QT uzaması/torsades, hepatotoksisite/hepatik yetmezlik, anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Malign hipertermi tetikleyebilir (bazı vakalar fatal); süksinilkolin ile risk artar - dantrolen ile tedavi.",
                "Emergence ajitasyonu/deliryum açısından izlem.",
                "Hepatik etki: postoperatif hepatit/hepatik disfonksiyon; önceki halojenli anestezik maruziyeti riski artırır.",
                "Pediatride perioperatif hiperkalemi (özellikle nöromusküler hastalık, ör. Duchenne); aritmi açısından izlem.",
                "Doza bağımlı hipotansiyon (diğer inhalan anesteziklerden daha hızlı olabilir).",
                "Serebral vazodilatasyon ile intrakraniyal basınç artışı.",
                "QT uzaması/torsades riski; QT uzatan ilaçlarla dikkat.",
                "Doza bağımlı solunum depresyonu."
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
            drugId = "desflurane",
            nameTr = "Desfluran",
            nameEn = "Desflurane",
            genericName = "Desflurane",
            brandNames = listOf(
                "Ѕսрranе"
            ),
            category = "inhalational",
            atcCode = null,
            drugClass = "Halojenli inhalasyon anestezik gaz",
            mechanismTr = "Merkezi sinir sistemindeki sinaptik iletimi modüle eder, MAC değeri %6'dır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezi indüksiyonu: İnhalasyon başlangıç %3, her 2-3 solukta %0.5-1 artımlarla (end-tidal %4-11); >%12 inspire konsantrasyon güvenli verilmiştir.\n• Anestezi idamesi: İnhalasyon %2.5-8.5 (nitröz oksitli/oksitsiz).\n• MAC-awake (amnezi/bilinç kaybı): %2.4.\n• MAC değerleri (%100 oksijen): 25 yaş %7.3; 45 yaş %6; 70 yaş %5.2.\n• MAC (%60 N2O/%40 oksijen): 25 yaş %4; 45 yaş %2.8; 70 yaş %1.7.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Anestezi idamesi: İnhalasyon ~%7-10 (nitröz oksitle daha düşük %4-7.5). MAC yaşla azalır; bireyselleştirilmeli. Desflurana özel buharlaştırıcı kullanılmalı.\n• MAC (%100 oksijen): 10 hafta %9.4; 9 ay %10; 2 yaş %9.1; 3 yaş %6.4; 4 yaş %8.6; 7 yaş %8.1; 25 yaş %7.3.\n• MAC (%60 N2O): 9 ay %7.5; 25 yaş %4.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "1-2 dakika",
                peak = "belirtilmemiş",
                duration = "Emergence süresi kesildiğindeki kan konsantrasyonuna bağlı; düşük kan/gaz solubilitesi (0.42) nedeniyle çok hızlı",
                halfLife = "belirtilmemiş",
                elimination = "Hepatik (%0.02) trifluoroasetata ve inorganik florüre (Eliminasyon yeri: Soluk gazları (ekshalasyon))"
            ),
            contraindications = listOf(
                "Malign hipertermi öyküsü veya genetik yatkınlık",
                "Halojenli ajanlara karşı aşırı duyarlılık"
            ),
            warnings = listOf(
                "Malign hipertermi tetikleyebilir (bazı vakalar fatal); süksinilkolin ile risk artar - dantrolen ile tedavi.",
                "Pediatride perioperatif hiperkalemi (özellikle Duchenne); aritmi açısından izlem.",
                "QT uzaması/torsades riski."
            ),
            sideEffectsCommon = listOf(
                "GİS: bulantı (%27), kusma (%16)",
                "Solunum: apne (indüksiyon erişkin %15), nefes tutma (indüksiyon %30), öksürük (indüksiyon %34), laringospazm (%3-10)",
                "Kardiyovasküler: bradikardi, taşikardi, hipertansiyon",
                "Ciddi: malign hipertermi, QT uzaması/torsades, hepatik yetmezlik/hepatit, hiperkalemi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Malign hipertermi tetikleyebilir (bazı vakalar fatal); süksinilkolin ile risk artar - dantrolen ile tedavi.",
                "Hepatik/renal kan akımında azalma; halojenli anesteziklere duyarlılaşmış hastalarda sensitivite hepatiti.",
                "Pediatride perioperatif hiperkalemi (özellikle Duchenne); aritmi açısından izlem.",
                "İntrakraniyal kitle lezyonlarında ≤0.8 MAC, barbitürat indüksiyonu ve hiperventilasyonla uygulanmalı.",
                "Kardiyovasküler hastalıkta tek indüksiyon ajanı olarak kullanma (kaynakta kesik).",
                "QT uzaması/torsades riski.",
                "Kurumuş CO2 absorbanı ile yüksek karbonmonoksit/karboksihemoglobin oluşabilir - taze absorban kullan."
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
            drugId = "isoflurane",
            nameTr = "İzofluran",
            nameEn = "Isoflurane",
            genericName = "Isoflurane",
            brandNames = listOf(
                "Forаne;",
                "Tеrrell"
            ),
            category = "inhalational",
            atcCode = null,
            drugClass = "Halojenli inhalasyon anestezik gaz",
            mechanismTr = "Merkezi sinir sistemindeki sinaptik iletimi modüle eder, MAC değeri %1.15'tir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezi: İnhalasyon.\n• İndüksiyon: %1.5-3 izofluran, oksijen veya oksijen-nitröz oksit karışımı ile.\n• İdame: Nitröz oksit ile %1-2.5; tek başına oksijenle ek %0.5-1.\n(Not: İndüksiyonda öksürük, nefes tutma, bronkospazm veya laringospazm olabilir; ultra kısa etkili barbitürat bu semptomları önleyebilir.)", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Anestezi: İnhalasyon; erişkin dozlamasına bakınız.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "Minimal hepatik (<%0.2), ağırlıklı CYP2E1 (Eliminasyon yeri: Soluk gazları (ekshalasyon))"
            ),
            contraindications = listOf(
                "Malign hipertermi öyküsü veya genetik duyarlılık",
                "Halojenli anesteziklere karşı duyarlılık"
            ),
            warnings = listOf(
                "Kardiyovasküler: doza bağımlı kan basıncı düşüşü (periferik vazodilatasyon); kardiyak debi korunur. Hipovolemik/hipotansif hastalarda ve koroner arter hastalığında dikkat (miyokard iskemisi riski). QT uzatabilir, nadiren torsades.",
                "İntrakraniyal basınç artışı - ICP düşürücü stratejilerle birlikte."
            ),
            sideEffectsCommon = listOf(
                "Nörolojik: ajitasyon (indüksiyon %52), titreme/üşüme (≤%14), deliryum (%6)",
                "Solunum: nefes tutma (indüksiyon %24), öksürük (indüksiyon %28), laringospazm (indüksiyon %8)",
                "GİS: bulantı (%15), kusma (%10)",
                "Kardiyovasküler: atriyal/ventriküler aritmiler, hipertansiyon, hipotansiyon",
                "Ciddi (postmarketing): malign hipertermi (uyarılarda), QT uzaması/torsades, hepatit/hepatik nekroz."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler: doza bağımlı kan basıncı düşüşü (periferik vazodilatasyon); kardiyak debi korunur. Hipovolemik/hipotansif hastalarda ve koroner arter hastalığında dikkat (miyokard iskemisi riski). QT uzatabilir, nadiren torsades.",
                "CNS depresyonu: en az 24 saat araç/makine kullanmayın.",
                "Hepatik etki: postoperatif hafif-ağır hepatik disfonksiyon (fatal nekroz/yetmezlik dahil); halojenli anesteziklere duyarlılaşmada sensitivite hepatiti.",
                "Pediatride perioperatif hiperkalemi (özellikle Duchenne).",
                "Hipersensitivite reaksiyonları (anafilaksi).",
                "İntrakraniyal basınç artışı - ICP düşürücü stratejilerle birlikte.",
                "Malign hipertermi tetikleyebilir - dantrolen ile tedavi."
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
            drugId = "nitrous_oxide",
            nameTr = "Azot Protoksit (Nitröz Oksit)",
            nameEn = "Nitrous Oxide",
            genericName = "Nitrous oxide",
            brandNames = emptyList(),
            category = "inhalational",
            atcCode = null,
            drugClass = "İnorganik inhalasyon anestezik gazı",
            mechanismTr = "NMDA reseptör antagonisti ve zayıf anestezik, güçlü analjeziktir, MAC değeri %104'tür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Cerrahi sedasyon ve analjezi (ek ajan): MAC ~%104-105 olduğundan tek başına tam anestezi için hiperbarik ortam gerekir; diğer anesteziklerin gereksinimini azaltır.\n• İşlemsel sedasyon: İnhalasyon %30-50 nitröz oksit + oksijen.\n• Genel anestezi: İnhalasyon %50-70 (maske veya endotrakeal tüp ile).\n• Dental sedasyon ve analjezi: İnhalasyon %30-50 nitröz oksit + oksijen.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Erişkin dozlamasına bakınız.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "İnhalasyon 2-5 dakika",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "Vücut metabolizması <%0.004 (minimal) (Eliminasyon yeri: Ağırlıklı soluk gazları; deri (minimal miktar))"
            ),
            contraindications = listOf(
                "Pnömotoraks, hava embolisi, bağırsak tıkanıklığı",
                "Yakın zamanda göz içi gaz enjeksiyonu öyküsü"
            ),
            warnings = listOf(
                "Kemik iliği supresyonu: uzun kullanımda; B12 eksikliği (pernisiyöz anemi) ve beslenme yetersizliği olanlarda risk artar.",
                "Nörolojik disfonksiyon: uzun kullanımda; B12 eksikliğinde risk artar."
            ),
            sideEffectsCommon = listOf(
                "Üreticinin prospektüsünde listelenmiş advers reaksiyon yoktur. Postmarketing: Kardiyovasküler: miyokard kontraktilitesinde depresyon, hipotansiyon",
                "GİS: bulantı-kusma (~%15)",
                "Nörolojik: kognitif disfonksiyon, dezoryantasyon, parestezi",
                "Otik: orta kulak basıncı artışı (geçici işitme kaybı, hemotimpanum, kulak zarı perforasyonu), tinnitus",
                "Solunum: atelektazi, hipoksi",
                "Endokrin: homosistinemi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Vücut boşluğu hacim genişlemesi: pnömotoraks, pnömosefalus, orta kulak cerrahisi veya bağırsak obstrüksiyonunda kullanmayın.",
                "Kemik iliği supresyonu: uzun kullanımda; B12 eksikliği (pernisiyöz anemi) ve beslenme yetersizliği olanlarda risk artar.",
                "Nörolojik disfonksiyon: uzun kullanımda; B12 eksikliğinde risk artar.",
                "Vitreoretinal cerrahi: intraoküler gaz varlığında intraoküler basıncı artırarak retina arter oklüzyonu/görme kaybına yol açabilir.",
                "Difüzyon hipoksisini önlemek için kesilmeden önce ve sonra birkaç dakika oksijen verilmeli.",
                "Bulantı-kusma (~%15); antiemetiklerle azaltılabilir.",
                "Bağımlılık/kötüye kullanım potansiyeli. Yüksek alarm ilacı (ISMP)."
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
            drugId = "fentanyl",
            nameTr = "Fentanil",
            nameEn = "Fentanyl",
            genericName = "Fentanyl Citrate",
            brandNames = listOf(
                "Fentanyl Citrate",
                "Talon"
            ),
            category = "opioid",
            atcCode = "N01AH01",
            drugClass = "Sentetik güçlü opioid analjezik (Mu-Reseptör Agonisti)",
            mechanismTr = "Mu-opioid reseptörlerine yüksek afiniteyle bağlanarak ağrı iletimini inhibe eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut postoperatif ağrı (PACU): IV 25-50 mcg her 5 dk (orta ağrı) veya 50-100 mcg her 2-5 dk (şiddetli ağrı), ağrı geçene kadar; IM 50-100 mcg her 1-2 saatte (yalnızca IV yoksa).\n• Akut nonoperatif şiddetli ağrı (yalnızca ICU/yakın izlem): IV/IM 25-50 mcg veya 0,35-0,5 mcg/kg her 30-60 dk; SUBQ (endikasyon dışı) 25-50 mcg her 1-2 saatte.\n• Hasta kontrollü analjezi (PCA): Olağan konsantrasyon 10 mcg/mL; talep dozu 5-20 mcg; kilitlenme aralığı 4-8 dk; opioid-naif hastada bazal infüzyon önerilmez.\n• Fentanil, ciddi böbrek/karaciğer disfonksiyonunda tercih edilen opioiddir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut şiddetli ağrı (opioid-naif): <50 kg IV 0,5-1 mcg/kg/doz, her 1-2 saatte (gerekirse her 30 dk); olağan maksimum 50 mcg/doz (kritik hastada 100 mcg/doz). ≥50 kg IV 25-50 mcg her 1-2 saatte.\n• Sürekli IV infüzyon: <50 kg 0,5-2,5 mcg/kg/saat; ≥50 kg 25-100 mcg/saat.\n• İntranazal (≥10 kg): 1,5-2 mcg/kg tek doz; maks 100 mcg/doz.\n• Anestezi (2-12 yaş): IV 2-10 mcg/kg/doz, ardından bazı durumlarda 2-5 mcg/kg/saat infüzyon.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV neredeyse anında (analjezik/solunum depresan etkinin tepesi birkaç dakikayı bulabilir), IM 7-8 dk, transmukozal 5-15 dk",
                peak = "belirtilmemiş",
                duration = "IV 0,5-1 saat, IM 1-2 saat, transdermal kaldırıldıktan sonra 72-96 saate kadar",
                halfLife = "belirtilmemiş",
                elimination = "yüksek lipofilik, kas ve yağa yeniden dağılır, 3 kompartmanlı dağılım (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Opioidlere karşı bilinen aşırı duyarlılık",
                "Akut veya şiddetli astım"
            ),
            warnings = listOf(
                "Ciddi hipotansiyona (ortostatik hipotansiyon, senkop) neden olabilir; hipovolemi, kardiyovasküler hastalık (akut MI) veya hipotansif etkiyi artıran ilaçlarda dikkat; dolaşım şokunda kullanmaktan kaçının.",
                "Bilinç bozukluğu/komada CO2 retansiyonu riskiyle kaçınılmalı; bradikardi, biliyer disfonksiyon, adrenal yetmezlikte dikkat.",
                "Aşırı doz riski yüksek hastalar için naloksan/nalmefen reçetelemeyi değerlendirin."
            ),
            sideEffectsCommon = listOf(
                "Bulantı, kusma, kabızlık, periferik ödem, sersemlik, uyuklama, baş ağrısı, halsizlik, bradikardi, hipotansiyon, solunum depresyonu, kaşıntı, konfüzyon"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ciddi hipotansiyona (ortostatik hipotansiyon, senkop) neden olabilir; hipovolemi, kardiyovasküler hastalık (akut MI) veya hipotansif etkiyi artıran ilaçlarda dikkat; dolaşım şokunda kullanmaktan kaçının.",
                "Serotonin sendromu: serotonerjik ajanlar (SSRI, SNRI, triptanlar, TCA, tramadol, MAOİ) ile yaşamı tehdit edebilir; şüphede kesilmeli.",
                "Opioid kaynaklı hiperaljezi ve özofageal disfonksiyon bildirilmiştir.",
                "Bilinç bozukluğu/komada CO2 retansiyonu riskiyle kaçınılmalı; bradikardi, biliyer disfonksiyon, adrenal yetmezlikte dikkat.",
                "Aşırı doz riski yüksek hastalar için naloksan/nalmefen reçetelemeyi değerlendirin."
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
            drugId = "remifentanil",
            nameTr = "Remifentanil",
            nameEn = "Remifentanil",
            genericName = "Remifentanil Hydrochloride",
            brandNames = listOf(
                "Ultiva"
            ),
            category = "opioid",
            atcCode = "N01AH06",
            drugClass = "Çok kısa etkili sentetik opioid (Mu-Reseptör Agonisti)",
            mechanismTr = "Mu-opioid reseptör agonistidir. Plazmada ve dokularda non-spesifik esterazlar tarafından çok hızlı yıkılır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Genel anestezi indüksiyon: sürekli infüzyon IV 0,5-1 mcg/kg/dk; <8 dk içinde entübasyon planlanıyorsa 30-60 sn içinde 1 mcg/kg başlangıç dozu.\n• İdame: aralıklı IV 1 mcg/kg her 2-5 dk; sürekli infüzyon nitröz oksit (%66) ile 0,4 mcg/kg/dk (0,1-2), izofluran ile 0,25 mcg/kg/dk (0,05-2), propofol ile 0,25 mcg/kg/dk (0,05-2).\n• Koroner baypas: 1 mcg/kg/dk.\n• Postoperatif (PACU): sürekli infüzyon IV 0,1 mcg/kg/dk (0,025-0,2); bolus önerilmez; >0,2 mcg/kg/dk solunum depresyonuyla ilişkili.\n• Monitörize anestezi bakımı analjezisi: tek IV 1 mcg/kg (30-60 sn), midazolam ile 0,5 mcg/kg.\n• Mekanik ventile ICU (endikasyon dışı): yükleme IV 1,5 mcg/kg; idame 0,008-0,25 mcg/kg/dk.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Postoperatif analjezi, mekanik ventile (3-16 yaş): sürekli IV infüzyon 0,1 mcg/kg/dk, etkiye titre.\n• Analjezi/sedasyon, mekanik ventile (≤2 ay): sürekli IV infüzyon başlangıç 0,15 mcg/kg/dk, etkiye titre.\n• Anestezi idamesi: 1-2 ay nitröz oksit (%70) ile 0,4 mcg/kg/dk (0,4-1); ≥3 ay halotan/sevofluran/izofluran ile 0,25 mcg/kg/dk (0,05-1,3), her 2-5 dk 1 mcg/kg ek bolus.\n• Obez hastalarda doz ideal vücut ağırlığına göre.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV 1-3 dk",
                peak = "3-5 dk",
                duration = "3-10 dk",
                halfLife = "erişkin terminal 10-20 dk, etkin 3-10 dk",
                elimination = "kan ve doku esterazlarıyla hızlı; plazma kolinesteraz veya karaciğer tarafından belirgin metabolize edilmez (Eliminasyon yeri: idrar)"
            ),
            contraindications = emptyList(),
            warnings = listOf(
                "Ölümcül solunum depresyonu olabilir; resüsitasyon/entübasyon ekipmanı, oksijen ve naloksan/nalmefen hazır bulundurun.",
                "Hipotansiyon, opioid kaynaklı hiperaljezi ve özofageal disfonksiyon görülebilir.",
                "Kafa travması/yüksek ICP'de aşırı dikkat; bradikardi, biliyer disfonksiyon, morbid obezitede dikkat."
            ),
            sideEffectsCommon = listOf(
                "Hipotansiyon, kaşıntı, bulantı, kusma, baş ağrısı, kas rijiditesi (göğüs duvarı rijiditesi), bradikardi, apne, solunum depresyonu, titreme"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ölümcül solunum depresyonu olabilir; resüsitasyon/entübasyon ekipmanı, oksijen ve naloksan/nalmefen hazır bulundurun.",
                "İntraoperatif farkındalık: propofol ≤75 mcg/kg/dk ile, <55 yaş hastalarda bildirilmiştir.",
                "Serotonin sendromu: serotonerjik ajanlar ve MAOİ ile yaşamı tehdit edebilir; şüphede kesilmeli.",
                "Hipotansiyon, opioid kaynaklı hiperaljezi ve özofageal disfonksiyon görülebilir.",
                "Kafa travması/yüksek ICP'de aşırı dikkat; bradikardi, biliyer disfonksiyon, morbid obezitede dikkat."
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
            drugId = "morphine",
            nameTr = "Morfin",
            nameEn = "Morphine",
            genericName = "Morphine Sulfate",
            brandNames = listOf(
                "Morphine",
                "Morfin Ampul"
            ),
            category = "opioid",
            atcCode = "N02AA01",
            drugClass = "Doğal opioid analjezik (Mu-Reseptör Agonisti)",
            mechanismTr = "Merkezi sinir sistemindeki mu-opioid reseptörlerini aktive ederek ağrı algısını değiştirir, spinal ve supraspinal analjezi sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut koroner sendrom, refrakter iskemik göğüs ağrısı: IV başlangıç 2-4 mg, ardından gerektikçe her 5-15 dk 2-8 mg.\n• Akut ağrı (opioid-naif) oral: hemen salımlı başlangıç 7,5-15 mg her 3-4 saatte; düşük solunum depresyonu riski olan hastanede 30 mg'a kadar her 4 saatte.\n• IV aralıklı: başlangıç 1-4 mg her 1-4 saatte; 10 mg'a kadar her 4 saatte; travma gibi şiddetli akut atakta başta her 5-15 dk verilebilir.\n• PCA: olağan konsantrasyon 1 mg/mL; talep dozu 0,5-2 mg; kilitlenme aralığı 6-10 dk.\n• IM (rutin önerilmez): 5-10 mg her 3-4 saatte.\n• Rektal: 10 mg her 4 saatte (30 mg'a kadar).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut şiddetli ağrı (opioid-naif): İnfant ≤6 ay oral 0,08-0,1 mg/kg/doz her 3-4 saatte; IV/SUBQ 0,025-0,05 mg/kg/doz her 2-4 saatte; sürekli IV infüzyon 0,008-0,02 mg/kg/saat.\n• >6 ay, çocuk, ergen <50 kg: oral 0,15-0,3 mg/kg/doz her 3-4 saatte (maks 10-20 mg/doz); ≥50 kg oral 10-20 mg her 3-4 saatte; IV/SUBQ <50 kg 0,05-0,1 mg/kg/doz.\n• <3 ay infantlar solunum depresyonuna daha duyarlı; ekstra dikkat ve sürekli izlem.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "oral (hemen salımlı) ~30 dk, IV 5-10 dk",
                peak = "belirtilmemiş",
                duration = "hemen salımlı 3-5 saat, uzatılmış salımlı 8-24 saat, epidural/intratekal 24 saate kadar, supozituvar 3-7 saat",
                halfLife = "term neonat 7,6 saat, erişkin (FK kesilmiş) belirtilmemiş",
                elimination = "hepatik, glukuronik asit konjugasyonu (M6G aktif analjezik, M3G inaktif) (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = emptyList(),
            warnings = listOf(
                "CNS depresyonu yapabilir; mental uyanıklık gerektiren işlerde dikkat; CO2 retansiyonu sedasyonu artırabilir.",
                "Ciddi hipotansiyon (ortostatik hipotansiyon, senkop) olabilir; hipovolemi, kardiyovasküler hastalık (akut MI), dolaşım şokunda kaçının.",
                "Fenantren hipersensitivitesi (kodein, hidrokodon, oksikodon vb.) olanlarda dikkat.",
                "Bilinç bozukluğu/komada CO2 retansiyonu riskiyle kaçının; kafa travması/yüksek ICP'de aşırı dikkat; biliyer disfonksiyon, adrenal yetmezlikte dikkat.",
                "Yüksek doz/risk faktörü olan hastalarda naloksan/nalmefen değerlendirin."
            ),
            sideEffectsCommon = listOf(
                "Kabızlık, bulantı, kusma, uyuklama, sersemlik, kaşıntı, anksiyete, konfüzyon, hipotansiyon, bradikardi, solunum depresyonu, terleme, miyoz"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "CNS depresyonu yapabilir; mental uyanıklık gerektiren işlerde dikkat; CO2 retansiyonu sedasyonu artırabilir.",
                "Ciddi hipotansiyon (ortostatik hipotansiyon, senkop) olabilir; hipovolemi, kardiyovasküler hastalık (akut MI), dolaşım şokunda kaçının.",
                "Fenantren hipersensitivitesi (kodein, hidrokodon, oksikodon vb.) olanlarda dikkat.",
                "Bilinç bozukluğu/komada CO2 retansiyonu riskiyle kaçının; kafa travması/yüksek ICP'de aşırı dikkat; biliyer disfonksiyon, adrenal yetmezlikte dikkat.",
                "Yüksek doz/risk faktörü olan hastalarda naloksan/nalmefen değerlendirin."
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
            drugId = "alfentanil",
            nameTr = "Alfentanil",
            nameEn = "Alfentanil",
            genericName = "Alfentanil (United States and Canada",
            brandNames = emptyList(),
            category = "opioid",
            atcCode = null,
            drugClass = "Kısa etkili sentetik opioid analjezik",
            mechanismTr = "Mu-opioid reseptör agonistidir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Analjezi/anestezi (IV, aktüel vücut ağırlığı; ideal ağırlığın %20 üzerindeyse yağsız vücut ağırlığı): İndüksiyon dozu 3 dakikada yavaş verilir.\n• Analjezi (yardımcı, ≤30 dk): indüksiyon 8-20 mcg/kg; idame 3-5 mcg/kg her 5-20 dk veya infüzyon 0,25-1 mcg/kg/dk; toplam 8-40 mcg/kg.\n• Analjezi/anestezi (30-60 dk): indüksiyon 20-50 mcg/kg (LMA için propofol ile ~10 mcg/kg); idame 5-15 mcg/kg her 5-20 dk; 75 mcg/kg'a kadar.\n• Anestezi indüksiyonu (>45 dk): 25-100 mcg/kg, 245 mcg/kg'a kadar.\n• Anestezi idamesi (>45 dk): 0,5-2 mcg/kg/dk; cerrahi bitiminden ≥30 dk önce kesilir.\n• Monitörize anestezi bakımı (MAC): 3-8 mcg/kg; idame 3-5 mcg/kg her 5-20 dk veya 0,25-1 mcg/kg/dk.\nNot: ABD'de >1 yıldır piyasada değil.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Anestezi <12 yaş: pre-indüksiyon/emergens ajitasyon önleme/tonsillektomi analjezisi IV 10-20 mcg/kg/doz; lomber ponksiyon/kemik iliği aspirasyonu (propofol ile) aralıklı IV 2-3 mcg/kg/doz.\n• ≥12 yaş ve ergen (yağsız vücut ağırlığı kuralı): ≤30 dk indüksiyon 8-20 mcg/kg; 30-60 dk 20-50 mcg/kg; sürekli infüzyon ≥45 dk indüksiyon 50-75 mcg/kg ardından 0,5-3 mcg/kg/dk (ortalama 1-1,5); anestezik indüksiyon ≥45 dk 130-245 mcg/kg.\n• Yalnızca pediatrik anestezi konusunda eğitimli klinisyenlerce kullanılmalı.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "hızlı, 5 dk içinde",
                peak = "belirtilmemiş",
                duration = "doza bağlı 30-60 dk",
                halfLife = "erişkin 90-111 dk, çocuk 40-63 dk",
                elimination = "hepatik (Eliminasyon yeri: idrar (metabolitlerin başlıca yolu; sadece %1 değişmeden atılır))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Ölümcül solunum depresyonu olabilir; resüsitasyon/entübasyon ekipmanı, oksijen ve naloksan/nalmefen hazır bulundurun.",
                "Hipotansiyon, opioid kaynaklı hiperaljezi, özofageal disfonksiyon görülebilir.",
                "Bradikardi (özellikle vagolitik olmayan kas gevşeticilerle/atropin kullanılmadığında belirgin), biliyer disfonksiyon, kafa travmasında dikkat."
            ),
            sideEffectsCommon = listOf(
                "Bulantı, kusma, bradikardi, göğüs duvarı rijiditesi, hipertansiyon, taşikardi, hipotansiyon, sersemlik, kas hareketleri, apne, solunum depresyonu, kaşıntı"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ölümcül solunum depresyonu olabilir; resüsitasyon/entübasyon ekipmanı, oksijen ve naloksan/nalmefen hazır bulundurun.",
                "Anaflaksi dahil hipersensitivite reaksiyonları olabilir.",
                "Serotonin sendromu: serotonerjik ajanlar ve MAOİ ile yaşamı tehdit edebilir; şüphede kesilmeli.",
                "Hipotansiyon, opioid kaynaklı hiperaljezi, özofageal disfonksiyon görülebilir.",
                "Bradikardi (özellikle vagolitik olmayan kas gevşeticilerle/atropin kullanılmadığında belirgin), biliyer disfonksiyon, kafa travmasında dikkat."
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
            drugId = "sufentanil",
            nameTr = "Sufentanil",
            nameEn = "Sufentanil",
            genericName = "Sufentanil",
            brandNames = listOf(
                "Dѕuviа"
            ),
            category = "opioid",
            atcCode = null,
            drugClass = "Çok güçlü sentetik opioid (Mu-Reseptör Agonisti)",
            mechanismTr = "Mu-opioid reseptör agonistidir (Fentanilden 5-10 kat güçlü).",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı (dilaltı tablet): başlangıç sublingual 30 mcg, gerektikçe en az 1 saat arayla; maks 360 mcg/gün (12 tablet); >72 saat kullanılmaz; yalnızca sertifikalı sağlık ortamında.\n• Cerrahi analjezi (dengeli anestezinin parçası, 1-2 saatlik cerrahi): IV toplam 1-2 mcg/kg (N2O/O2 ile); idame inkremental 10-25 mcg veya 5-20 mcg veya 0,1-0,25 mcg/kg; sürekli infüzyon maks 1 mcg/kg/saat (alternatif 0,3-0,9 veya 0,5-1,5 mcg/kg/saat).\n• Cerrahi analjezi (2-8 saatlik): toplam 2-8 mcg/kg; idame inkremental 10-50 mcg.\n• Cerrahi anestezi: toplam 8-30 mcg/kg yavaş; idame inkremental 0,5-10 mcg/kg; sürekli infüzyon toplam ≤30 mcg/kg.\n• Doğum analjezisi: epidural 10-15 mcg, bupivakain %0,125 ile; ≥1 saat arayla en fazla 3 doz.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Anestezi, kardiyak cerrahi (infant, çocuk, ergen): IV başlangıç 5-25 mcg/kg; idame 1-5 mcg/kg/doz (25-50 mcg/doza kadar) gerektikçe.\n• Epidural (≥3 ay infant ve çocuk): başlangıç bolus 0,2 mcg/kg, ardından ropivakain ile 0,1 mcg/kg/saat sürekli infüzyon.\n• Sedasyon, preoperatif/prosedürel (≥3 yaş): intranazal (parenteral preparat) 1-3 mcg/kg, diğer ajanlarla kombine.\n• İdeal ağırlığın %20 üzerinde yağsız vücut ağırlığı kullanılır; yalnızca deneyimli klinisyenlerce.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV 1-3 dk, epidural 10 dk, dilaltı tablet ~30 dk",
                peak = "belirtilmemiş",
                duration = "doza bağlı; epidural (bupivakain ile) 1,7 saat, dilaltı tablet ~3 saat",
                halfLife = "erişkin IV 164 dk, dilaltı tablet 2,5 saat, neonat 7,2 saat",
                elimination = "başlıca hepatik ve ince bağırsak (demetilasyon, dealkilasyon) (Eliminasyon yeri: idrar (%2 değişmeden, %80 metabolit; 24 saatte))"
            ),
            contraindications = listOf(
                "Opioidlere karşı bilinen aşırı duyarlılık",
                "Akut veya şiddetli solunum depresyonu"
            ),
            warnings = listOf(
                "Ölümcül solunum depresyonu olabilir; yüksek dozda postoperatif solunum depresyonu için nitelikli personel ve uygun olanaklar gerekli.",
                "CNS depresyonu yapabilir; mental uyanıklık gerektiren işlerde dikkat.",
                "Ciddi hipotansiyon (ortostatik hipotansiyon, senkop) olabilir; hipovolemi, kardiyovasküler hastalık (akut MI), dolaşım şokunda dikkat; dilaltı tablet dolaşım şokunda kaçınılmalı.",
                "Şiddetli bradikardi, opioid kaynaklı hiperaljezi, özofageal disfonksiyon, adrenal yetmezlikte dikkat."
            ),
            sideEffectsCommon = listOf(
                "Kaşıntı (epidural), bulantı, baş ağrısı, hipotansiyon, kusma, sersemlik, bradikardi, kas rijiditesi, miyoz, solunum depresyonu, apne"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ölümcül solunum depresyonu olabilir; yüksek dozda postoperatif solunum depresyonu için nitelikli personel ve uygun olanaklar gerekli.",
                "CNS depresyonu yapabilir; mental uyanıklık gerektiren işlerde dikkat.",
                "Ciddi hipotansiyon (ortostatik hipotansiyon, senkop) olabilir; hipovolemi, kardiyovasküler hastalık (akut MI), dolaşım şokunda dikkat; dilaltı tablet dolaşım şokunda kaçınılmalı.",
                "Serotonin sendromu: serotonerjik ajanlar ve MAOİ ile yaşamı tehdit edebilir; şüphede kesilmeli.",
                "Şiddetli bradikardi, opioid kaynaklı hiperaljezi, özofageal disfonksiyon, adrenal yetmezlikte dikkat."
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
            drugId = "tramadol",
            nameTr = "Tramadol",
            nameEn = "Tramadol",
            genericName = "Tramadol",
            brandNames = listOf(
                "СоոZip;",
                "Qdоlo [DSC];",
                "Ultram [DSC]"
            ),
            category = "opioid",
            atcCode = null,
            drugClass = "Zayıf Mu-Opioid Agonisti ve Monoamin Geri Alım İnhibitörü",
            mechanismTr = "Mu-opioid agonisti ve norepinefrin/serotonin geri alım inhibitörüdür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı (ör. postoperatif), hemen salımlı oral: başlangıç 25-50 mg her 4-6 saatte; gerektikçe 50-100 mg her 4-6 saatte; hızlı etki gerekiyorsa başlangıç 50-100 mg; maks 400 mg/gün.\n• Kronik ağrı (alternatif), opioid-naif hemen salımlı: günde <300 mg ile sınırla; örnek başlangıç 25-50 mg her 6 saatte; 50-100 mg her 4-6 saatte (maks 400 mg/gün).\n• Uzatılmış salımlı: başlangıç 100 mg günde 1 kez; 5 günde bir 100 mg artır (maks 300 mg/gün).\n• Tedaviye IR preparatla başlamak önerilir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut orta-şiddetli ağrı (tonsillektomi/adenoidektomi sonrası hariç), hemen salımlı: 12-16 yaş oral 1-2 mg/kg/doz her 4-6 saatte; maks 100 mg/doz; maks günlük 8 mg/kg/gün (≤400 mg/gün).\n• ≥17 yaş ergen: oral 50-100 mg her 4-6 saatte; maks 400 mg/gün; tolerans için 25 mg/gün başlanıp titre edilebilir.\n• <12 yaş tüm hastalarda ve tonsillektomi/adenoidektomi geçiren tüm pediyatrik hastalarda kontrendike.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "hemen salımlı 1 saat içinde",
                peak = "2-3 saat",
                duration = "hemen salımlı ~4-6 saat, uzatılmış salımlı ~24 saat",
                halfLife = "hemen salımlı 6,3 saat (aktif metabolit M1 7,4 saat)",
                elimination = "yoğun hepatik (CYP3A4, CYP2D6; aktif metabolit M1) (Eliminasyon yeri: idrar (~%30 değişmeden, %60 metabolit))"
            ),
            contraindications = listOf(
                "MAO inhibitörü kullanımı (son 14 gün içinde)",
                "Kontrolsüz epilepsi",
                "12 yaş altı çocuklar"
            ),
            warnings = listOf(
                "Ciddi hipotansiyon (ortostatik hipotansiyon, senkop) olabilir; hipovolemi, kardiyovasküler hastalık (akut MI), dolaşım şokunda kaçının.",
                "Bilinç bozukluğu/komada CO2 retansiyonu riskiyle kaçının; kafa travması/yüksek ICP'de aşırı dikkat.",
                "Mental sağlık sorunları olanlarda opioid kullanım bozukluğu/aşırı doz riski için dikkat; nöbet eşiğini düşürebilir.",
                "Yüksek risk faktörü olan hastalarda naloksan/nalmefen değerlendirin."
            ),
            sideEffectsCommon = listOf(
                "Kabızlık, bulantı, dispepsi, ağız kuruluğu, sersemlik, uyuklama, baş ağrısı, vertigo, kızarma, kaşıntı, terleme, ortostatik hipotansiyon"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ciddi hipotansiyon (ortostatik hipotansiyon, senkop) olabilir; hipovolemi, kardiyovasküler hastalık (akut MI), dolaşım şokunda kaçının.",
                "Diyabet: hipoglisemiye yol açabilir.",
                "Opioid kaynaklı hiperaljezi ve özofageal disfonksiyon bildirilmiştir.",
                "Bilinç bozukluğu/komada CO2 retansiyonu riskiyle kaçının; kafa travması/yüksek ICP'de aşırı dikkat.",
                "Mental sağlık sorunları olanlarda opioid kullanım bozukluğu/aşırı doz riski için dikkat; nöbet eşiğini düşürebilir.",
                "Yüksek risk faktörü olan hastalarda naloksan/nalmefen değerlendirin."
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
            drugId = "meperidine",
            nameTr = "Meperidin",
            nameEn = "Meperidine",
            genericName = "Meperidine (pethidine)",
            brandNames = listOf(
                "Dеmеrοl"
            ),
            category = "opioid",
            atcCode = null,
            drugClass = "Sentetik opioid analjezik",
            mechanismTr = "Mu-opioid reseptör agonistidir. Antikolinerjik etkileri vardır, titremeyi önler.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı (alternatif tedavi; nadir durumlar dışında ÖNERİLMEZ): IM (tercih edilen yol), IV 50-150 mg her 3-4 saatte; maks 600 mg/gün; süre ≤48 saatle sınırlı. Oral yol ve PCA önerilmez.\n• Obstetrik analjezi: IM, SUBQ 50-100 mg ağrı düzenli hale gelince; 1-3 saat arayla tekrarlanabilir.\n• Preoperatif: IM, SUBQ 50-100 mg, anestezi başlamadan 30-90 dk önce.\n• Postoperatif titreme (endikasyon dışı): IV 12,5-50 mg tek doz veya 0,2 mg/kg.\n• Amfoterisin B titremesi (endikasyon dışı): IV 25-50 mg tek doz.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı (>6 ay infant, çocuk, ergen; önerilmez ancak kaçınılamazsa ≤48 saat): IM/IV/SUBQ başlangıç 0,8-2 mg/kg/doz her 3-4 saatte; maks 50-75 mg/doz; oral 1,1-3 mg/kg/doz, maks 50-100 mg/doz.\n• Preoperatif sedasyon: IM/IV/SUBQ 0,5-2 mg/kg, anesteziden 30-90 dk önce; maks 2 mg/kg veya 100 mg/doz.\n• Orak hücre krizi (≥6 ay): <50 kg IV 0,75-1 mg/kg her 3-4 saatte; ≥50 kg IV 50-150 mg her 3 saatte (ilk tercih değil).\n• Postoperatif titreme: IV 1-2 mg/kg/doz tek doz; maks 50-75 mg/doz.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "oral/IM/SubQ 10-15 dk, IV ~5 dk",
                peak = "IV 5-7 dk, IM/SubQ ~1 saat, oral 2 saat",
                duration = "oral/IM/SubQ 2-4 saat, IV 2-3 saat",
                halfLife = "erişkin 2,5-4 saat (karaciğer hastalığı 7-11 saat); normeperidin erişkin 8-16 saat",
                elimination = "hepatik; meperidinik aside hidroliz veya normeperidine (aktif) N-demetilasyon (Eliminasyon yeri: idrar (metabolit olarak; ~%5 değişmeden))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Normeperidin (aktif metabolit, CNS uyarıcı) birikip anksiyete, tremor, nöbet tetikleyebilir; risk önceden CNS/renal disfonksiyon, uzun kullanım (>48 saat) ve kümülatif doz (>600 mg/24 saat) ile artar. Oral kullanılmamalı; naloksan nörotoksisiteyi tersine çevirmez, kötüleştirebilir.",
                "Ciddi hipotansiyon olabilir; dolaşım şokunda kaçının.",
                "Ölümcül solunum depresyonu olabilir; opioid kaynaklı hiperaljezi, özofageal disfonksiyon görülebilir."
            ),
            sideEffectsCommon = listOf(
                "Bulantı, kusma, kabızlık, ağız kuruluğu, terleme, kaşıntı, hipotansiyon, bradikardi, sersemlik, konfüzyon, deliryum, nöbet, tremor, solunum depresyonu, idrar retansiyonu"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Normeperidin (aktif metabolit, CNS uyarıcı) birikip anksiyete, tremor, nöbet tetikleyebilir; risk önceden CNS/renal disfonksiyon, uzun kullanım (>48 saat) ve kümülatif doz (>600 mg/24 saat) ile artar. Oral kullanılmamalı; naloksan nörotoksisiteyi tersine çevirmez, kötüleştirebilir.",
                "Kabızlık unstable anjina/post-MI hastalarında sorun olabilir; önleyici tedbir.",
                "Ciddi hipotansiyon olabilir; dolaşım şokunda kaçının.",
                "Serotonin sendromu: serotonerjik ajanlar, lityum, St. John's wort, MAOİ ile olabilir.",
                "Ölümcül solunum depresyonu olabilir; opioid kaynaklı hiperaljezi, özofageal disfonksiyon görülebilir."
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
            drugId = "paracetamol",
            nameTr = "Parasetamol",
            nameEn = "Acetaminophen (Paracetamol)",
            genericName = "Acetaminophen (paracetamol)",
            brandNames = listOf(
                "8 Hour Pain Reliever [OTC];",
                "8 HR Arthritis Pain Relief [OTC];",
                "Αϲеtаmiոорhеn 8 Hour [OTC];",
                "Αmiոοfeո [OTC];",
                "Aрrа [OTC];"
            ),
            category = "nonop_analgesic",
            atcCode = null,
            drugClass = "Anilin türevi analjezik ve antipiretik (Non-opioid)",
            mechanismTr = "Sentral prostaglandin sentezini inhibe eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Ağrı (hafif-orta) ve/veya ateş, oral: 325-650 mg her 4-6 saatte veya 1 g her 6 saatte; maks 4 g/gün (bazı uzmanlar normal karaciğer fonksiyonunda 3 g/gün önerir).\n• IV ≥50 kg: 650 mg her 4 saatte veya 1 g her 6 saatte; maks tek doz 1 g; maks 4 g/gün.\n• IV <50 kg: 12,5 mg/kg her 4 saatte veya 15 mg/kg her 6 saatte; maks tek doz 15 mg/kg (≤750 mg); maks 75 mg/kg/gün (≤3,75 g/gün).\n• Rektal: 325-650 mg her 4-6 saatte; maks 3,9 g/gün.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Ağrı (hafif-orta) veya ateş, oral kiloya göre (infant, çocuk, ergen): 10-15 mg/kg/doz her 4-6 saatte; 24 saatte en fazla 5 doz; maks 75 mg/kg/gün, 4.000 mg/gün'ü aşmaz.\n• Sabit dozlama (oral süspansiyon/çiğneme tableti, <12 yaş): kiloya/yaşa göre tablo (ör. 2-3 yaş 160 mg, 11 yaş 480-500 mg); her 4 saatte tekrarlanabilir, maks 5 doz/gün.\n• Hepatotoksisite riskini düşürmek için tüm kaynaklar günlük dozda hesaplanmalı.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "oral <1 saat, IV analjezi 5-10 dk / antipiretik 30 dk içinde",
                peak = "IV analjezik 1 saat",
                duration = "IV/oral analjezi 4-6 saat, IV antipiretik ≥6 saat",
                halfLife = "toksik dozda uzar; neonat GA 28-32 hafta 11 saat",
                elimination = "hepatik (sülfat/glukuronid konjugasyonu; CYP2E1 ile NAPQI'ye) (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Ciddi karaciğer yetmezliği veya aktif hepatit",
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Karaciğer yetmezliği/aktif karaciğer hastalığında dikkat; ciddi karaciğer yetmezliğinde IV form kontrendike.",
                "Ciddi hipovolemide (dehidratasyon/kan kaybı) IV formu dikkatle kullanın.",
                "Aspartam (fenilketonüri), benzil alkol (neonatlarda 'gasping sendromu'), polisorbat 80, propilen glikol içeren formlara dikkat."
            ),
            sideEffectsCommon = listOf(
                "Bulantı, kusma (özellikle IV), kabızlık, kaşıntı, hipertansiyon, hipotansiyon, baş ağrısı",
                "nadir: hepatotoksisite/akut karaciğer yetmezliği, anaflaksi, Stevens-Johnson sendromu, toksik epidermal nekroliz"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Yaşamı tehdit eden hepatotoksisite >4 g/gün dozlarda ilişkili; tüm kaynaklardan (reçete, OTC, kombinasyon) ve tüm yollardan günlük dozu erişkinde <4 g/gün ile sınırlayın.",
                "Karaciğer yetmezliği/aktif karaciğer hastalığında dikkat; ciddi karaciğer yetmezliğinde IV form kontrendike.",
                "Ciddi hipovolemide (dehidratasyon/kan kaybı) IV formu dikkatle kullanın.",
                "Aspartam (fenilketonüri), benzil alkol (neonatlarda 'gasping sendromu'), polisorbat 80, propilen glikol içeren formlara dikkat.",
                "Kendi kendine ilaçlamada belirtiler kötüleşir/ateş >3 gün sürerse hekime başvurulmalı."
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
