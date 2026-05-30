package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo

object SeedDataDrugsPart3 {
    val drugsList = listOf(
        Drug(
            drugId = "dobutamine",
            nameTr = "Dobutamin",
            nameEn = "Dobutamine",
            genericName = "Dobutamine",
            brandNames = emptyList(),
            category = "vasopressor",
            atcCode = null,
            drugClass = "Selektif Beta-1 Adrenerjik Agonist (İnotropik)",
            mechanismTr = "Esas olarak Beta-1 adrenerjik reseptörleri uyararak kasılma gücünü artırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut dekompanse kalp yetmezliği: İnfüzyon IV: Başlangıç 2-5 mcg/kg/dakika; olağan aralık 2-10 mcg/kg/dakika; maks doz: 20 mcg/kg/dakika\n• İnotropik destek (endikasyon dışı): İnfüzyon IV: Başlangıç 2-5 mcg/kg/dakika; olağan aralık 2-10 mcg/kg/dakika (0.5 mcg/kg/dk kadar düşük dozlar kullanılmıştır); maks 20 mcg/kg/dakika\n• Stres ekokardiyografi (tanısal): İnfüzyon IV: Başlangıç 5 mcg/kg/dakika; 3 dakika aralıkla 10, 20, 30, 40 mcg/kg/dakika'ya artır\n• Stres eko, viabilite: Başlangıç 2.5 mcg/kg/dakika; 5 dakika aralıkla 2.5 artışlarla maks 10 mcg/kg/dakika", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Hemodinamik destek (bebek, çocuk, ergen): Sürekli IV veya intraosseöz infüzyon: Başlangıç 0.5-1 mcg/kg/dakika; istenen yanıta dek kademeli titre et; olağan aralık 2-20 mcg/kg/dakika", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV: 1-10 dakika",
                peak = "10-20 dakika",
                duration = "belirtilmemiş",
                halfLife = "2 dakika",
                elimination = "Dokularda ve hepatik (konjugasyon ve metilasyon) inaktif metabolitlere (Eliminasyon yeri: İdrar (inaktif metabolit olarak))"
            ),
            contraindications = listOf(
                "İdiyopatik hipertrofik subaortik stenoz (IHSS)",
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Aritmiler: Ventriküler/supraventriküler aritmiler bildirilmiştir; dekompanse KY'de aritmiler için yakın izlem; AF/flutter'da ventrikül hızı kontrol altına alınmalı (ventriküler yanıt hızını artırabilir)",
                "KB etkisi: Genellikle artar ancak yüksek dozda vazodilatasyona bağlı hipotansiyon olabilir",
                "Kalp yetmezliği: NYHA III/IV'te uzun süreli kullanımda hospitalizasyon ve ölüm riski artar",
                "MAO inhibitörleri ile çok dikkatli; uzamış hipertansiyon olabilir"
            ),
            sideEffectsCommon = listOf(
                "Anjina, göğüs ağrısı, kalp hızında artış, sistolik KB artışı, palpitasyon, ventriküler erken vurular",
                "bulantı",
                "baş ağrısı",
                "dispne",
                "KB düşüşü, ventriküler taşikardi",
                "potasyum düşüşü",
                "lokal flebit",
                "torsade de pointes."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aritmiler: Ventriküler/supraventriküler aritmiler bildirilmiştir; dekompanse KY'de aritmiler için yakın izlem; AF/flutter'da ventrikül hızı kontrol altına alınmalı (ventriküler yanıt hızını artırabilir)",
                "KB etkisi: Genellikle artar ancak yüksek dozda vazodilatasyona bağlı hipotansiyon olabilir",
                "Kalp yetmezliği: NYHA III/IV'te uzun süreli kullanımda hospitalizasyon ve ölüm riski artar",
                "Aort stenozu gibi mekanik obstrüksiyonda etkisiz",
                "Elektrolit bozukluklarını (hipokalemi, hipomagnezemi) düzelt",
                "MAO inhibitörleri ile çok dikkatli; uzamış hipertansiyon olabilir"
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
            drugId = "dopamine",
            nameTr = "Dopamin",
            nameEn = "Dopamine",
            genericName = "Dopamine",
            brandNames = emptyList(),
            category = "vasopressor",
            atcCode = null,
            drugClass = "Sempatomimetik (Doz Bağımlı Alfa, Beta ve Dopaminerjik Etki)",
            mechanismTr = "Düşük dozda D1 (renal vazodilatasyon), orta dozda Beta-1 (inotrop), yüksek dozda Alfa-1 (vazokonstrüksiyon) uyarır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Doz-bağımlı etkiler: Düşük doz (<5 mcg/kg/dk) renal dopamin reseptörleri; orta doz (5-10 mcg/kg/dk) dopaminerjik+beta; yüksek doz (>10 mcg/kg/dk) alfa (vazokonstriksiyon)\n• Kardiyojenik şok (alternatif): İnfüzyon IV: olağan aralık 2-20 mcg/kg/dakika\n• Septik şok (alternatif): İnfüzyon IV: Başlangıç 2-5 mcg/kg/dakika; 20 mcg/kg/dakika'ya kadar\n• Post-kardiyak arrest şok: olağan aralık 5-20 mcg/kg/dakika\n• İnotropik destek: 5-15 mcg/kg/dakika (düşük uç tercih edilir)\n• AV blok/bradikardi (endikasyon dışı): Başlangıç 5 mcg/kg/dakika; 2 dakikada artır; maks 20 mcg/kg/dakika", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Hemodinamik destek (bebek, çocuk, ergen): Sürekli IV veya intraosseöz infüzyon: 2-20 mcg/kg/dakika; optimal yanıta dek 5-10 mcg/kg/dakika artışlarla kademeli titre et; düşük uç tercih edilir (inotropik etkiler düşük dozda, vazokonstriksiyon yüksek dozda baskın)", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Erişkin: 5 dakika içinde",
                peak = "belirtilmemiş",
                duration = "Erişkin: <10 dakika",
                halfLife = "~2 dakika",
                elimination = "Renal, hepatik, plazma; %75 MAO ile inaktif metabolitlere, %25 norepinefrine (aktif) (Eliminasyon yeri: İdrar (metabolit olarak))"
            ),
            contraindications = listOf(
                "Feokromositoma",
                "Düzeltilmemiş taşiaritmiler"
            ),
            warnings = listOf(
                "Aritmiler: Taşikardi ve ventriküler dahil taşiaritmi riskini artırabilir; kalp transplant adaylarında ani kardiyak ölüme karşı önlem al",
                "Kardiyovasküler hastalık, aktif miyokard iskemisi/yakın MI'de dikkatli kullan"
            ),
            sideEffectsCommon = listOf(
                "Anjina, atriyal fibrilasyon, bradikardi, kondüksiyon bozukluğu, ektopik vurular, hipertansiyon, hipotansiyon, palpitasyon, taşikardi, vazokonstriksiyon, ventriküler aritmi, geniş QRS",
                "periferik gangren",
                "bulantı, kusma",
                "azotemi",
                "baş ağrısı",
                "dispne."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aritmiler: Taşikardi ve ventriküler dahil taşiaritmi riskini artırabilir; kalp transplant adaylarında ani kardiyak ölüme karşı önlem al",
                "Ekstravazasyon: Vezikan; mümkünse büyük vene infüze et, bacak venlerinden kaçın",
                "Kardiyovasküler hastalık, aktif miyokard iskemisi/yakın MI'de dikkatli kullan",
                "Elektrolit bozukluklarını (hipokalemi, hipomagnezemi) düzelt",
                "Şokta dopamin alternatif vazopresörlere göre daha fazla advers olay; septik şokta norepinefrine kıyasla daha yüksek 28 günlük mortalite",
                "Sodyum metabisülfit içerebilir"
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
            drugId = "vasopressin",
            nameTr = "Vazopressin",
            nameEn = "Vasopressin",
            genericName = "Vasopressin",
            brandNames = listOf(
                "Vаѕоstrict"
            ),
            category = "vasopressor",
            atcCode = null,
            drugClass = "Antidiüretik Hormon (Vazopressör)",
            mechanismTr = "V1 reseptörlerini uyararak güçlü periferik vazokonstrüksiyon yapar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Septik şok (ek ajan): İnfüzyon IV: 0.03 ünite/dakika sabit doz (titre edilmez, norepinefrine eklenir); olağan aralık 0.01-0.04 ünite/dakika; >0.04 ünite/dakika yalnızca kurtarma tedavisi için\n• Post-kardiyotomi şok: İnfüzyon IV: Başlangıç 0.03 ünite/dakika; 10-15 dakika aralıkla 0.005 ünite/dakika artır (maks 0.1 ünite/dakika)\n• Organ donör yönetimi: IV: Başlangıç 0.01-0.04 ünite/dakika; bazıları 1 ünite bolus ardından 0.01-0.1 ünite/dakika\n• Arginin vazopressin eksikliği (akut, endikasyon dışı): İnfüzyon IV: dozlar değişken (kurumsal protokol); SUBQ: 5-10 ünite günde 2-3 kez", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Arginin vazopressin eksikliği (bebek, çocuk, ergen): Sürekli IV infüzyon: Başlangıç 0.5 milliünite/kg/saat; ~10 dakikada 0.5 milliünite/kg/saat artışlarla titre et\n• GI kanama (çocuk, ergen): Sürekli IV infüzyon: Başlangıç 2-5 milliünite/kg/dakika; maks 10 milliünite/kg/dakika\n• Nabızsız arrest/VF/VT: IV: 0.4 ünite/kg tek doz\n• IM, SUBQ (çocuk, ergen): 2.5-10 ünite günde 2-4 kez", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "Antidiüretik: 1-2 saat; Vazopresör IV: 15 dakika içinde",
                duration = "SUBQ antidiüretik 2-8 saat; IV vazopresör: infüzyon sonrası 20 dakika içinde",
                halfLife = "IV/SubQ: 10-20 dakika (görünür ≤10 dakika)",
                elimination = "Hepatik, renal (inaktif metabolitler) (Eliminasyon yeri: İdrar (SUBQ %5, IV ~%6 değişmemiş))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık",
                "Koroner arter hastalığı (rölatif)"
            ),
            warnings = listOf(
                "Su intoksikasyonu: Erken belirtiler uyku hali, baş ağrısı; koma ve nöbeti önlemek için tanınmalı",
                "Kardiyovasküler hastalık (ateroskleroz dahil), migren, renal hastalık ve vasküler hastalıkta dikkatli kullan"
            ),
            sideEffectsCommon = listOf(
                "Atriyal fibrilasyon, bradikardi, iskemik kalp hastalığı, distal ekstremite iskemisi, düşük kalp debisi, sağ kalp yetmezliği",
                "iskemik cilt lezyonu",
                "hiponatremi",
                "mezenterik iskemi",
                "trombositopeni, kanama",
                "bilirubin artışı",
                "renal yetersizlik."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Arginin vazopressin bozuklukları: Kesilmesinin ardından geri dönüşlü bozukluk (poliüri, dilüe idrar, hipernatremi) olabilir; elektrolit, sıvı ve idrar çıkışını izle",
                "Ekstravazasyon: Vezikan; şiddetli vazokonstriksiyon ve lokal doku nekrozu, gangren, iskemik kolite yol açabilir",
                "Su intoksikasyonu: Erken belirtiler uyku hali, baş ağrısı; koma ve nöbeti önlemek için tanınmalı",
                "Kardiyovasküler hastalık (ateroskleroz dahil), migren, renal hastalık ve vasküler hastalıkta dikkatli kullan"
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
            drugId = "nitroglycerin_glyceryl_trinitrate",
            nameTr = "Nitrogliserin",
            nameEn = "Nitroglycerin (Glyceryl Trinitrate)",
            genericName = "Nitroglycerin (glyceryl trinitrate)",
            brandNames = listOf(
                "GοNitrо [DSC];",
                "Νitrο-Βid;",
                "Νitrο-Dսr;",
                "Νitrо-Τime;",
                "Νitroliոguаl;"
            ),
            category = "icu_metabolic",
            atcCode = null,
            drugClass = "Vazodilatör (Nitrik Oksit Donörü - Venöz Vazodilatör)",
            mechanismTr = "Klinik monograf bilgileri için kaynak dokümana başvurunuz.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut dekompanse kalp yetmezliği (ek ajan): İnfüzyon IV: Başlangıç 5-10 mcg/dakika; 3-5 dakikada 5-10 mcg/dakika artışlarla 200 mcg/dakika'ya kadar titre et\n• Akut anjina: Dilaltı tablet: 0.3 veya 0.4 mg, 5 dakikada bir tekrar, 15 dakikada maks 3 tablet; refrakter anjinada tek doz 0.6 mg'a kadar\n• Akut anjina - sublingual toz: 0.4 mg/paket 1 paket; 5 dakikada bir, 15 dakikada maks 3 paket. Translingual sprey 0.4 mg: 1 sprey\n• Anjina (IV, diğer formlar etkisizse): Başlangıç 5-10 mcg/dakika; 5-10 dakikada 5 mcg/dakika artışlarla 20'ye, sonra 10-20 mcg/dakika artışlarla maks 400 mcg/dakika\n• Anjina önleme: Transdermal patch 0.2-0.4 mg/saat (maks 0.8 mg/saat)", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Kalp yetmezliği; kardiyojenik şok (bebek ve çocuk): Sürekli IV infüzyon: Başlangıç 0.25-0.5 mcg/kg/dakika; 15-20 dakikada 1 mcg/kg/dakika titre et; olağan aralık 1-5 mcg/kg/dakika; olağan maks 10 mcg/kg/dakika (20'ye kadar kullanılabilir)\n• Ergen: Sürekli IV infüzyon: Başlangıç 5-10 mcg/dakika; maks 200 mcg/dakika\n• Ekstravazasyon tedavisi (topikal): %2 pomad 4 mm/kg ince şerit", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Dilaltı tablet 1-3 dakika; uzatılmış salım ~60 dakika; topikal 15-30 dakika; transdermal ~30 dakika; IV: Hemen",
                peak = "Sublingual toz 7 dakika; dilaltı tablet 5 dakika; translingual sprey 4-15 dakika; transdermal 120 dakika; IV: Hemen",
                duration = "Dilaltı tablet en az 25 dakika; uzatılmış salım 4-8 saat; transdermal 10-12 saat; IV 3-5 dakika",
                halfLife = "~1-4 dakika",
                elimination = "Yoğun ilk geçiş; hepatik (di- ve mononitrat metabolitleri); RBC ve damar duvarlarında non-hepatik metabolizma (Eliminasyon yeri: İdrar (inaktif metabolit olarak))"
            ),
            contraindications = listOf(
                "Şiddetli hipotansiyon veya hipovolemi",
                "Son 24-48 saat içinde PDE5 İnhibitörleri (Sildenafil vb.) kullanımı",
                "Kafa içi basınç artışı"
            ),
            warnings = listOf(
                "Hipotansiyon/bradikardi: Küçük dozlarda bile şiddetli hipotansiyon ve şok olabilir; volüm deplesyonu, aort/mitral stenoz, inferior duvar MI'da dikkatli kullan",
                "Artmış intrakraniyal basınç: Nitrogliserin İKB'yi artırabilir; artmış İKB'de kontrendike"
            ),
            sideEffectsCommon = listOf(
                "Baş ağrısı (sık)",
                "hipotansiyon, senkop, ortostatik hipotansiyon",
                "baş dönmesi, parestezi",
                "asistoli, bradikardi, flushing, rebound hipertansiyon, taşikardi",
                "methemoglobinemi",
                "laktik asidoz",
                "bulantı, kusma",
                "dispne, geçici hipoksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Fosfodiesteraz-5 inhibitörü kullanımı: Avanafil sonrası ≥12 saat, sildenafil/vardenafil sonrası ≥24 saat, tadalafil sonrası ≥48 saat nitrat tedavisini geciktir",
                "Hipotansiyon/bradikardi: Küçük dozlarda bile şiddetli hipotansiyon ve şok olabilir; volüm deplesyonu, aort/mitral stenoz, inferior duvar MI'da dikkatli kullan",
                "Artmış intrakraniyal basınç: Nitrogliserin İKB'yi artırabilir; artmış İKB'de kontrendike",
                "Sol ventrikül çıkış yolu obstrüksiyonlu hipertrofik kardiyomiyopatide kaçın",
                "Tolerans gelişebilir; 24-48 saatte taşiflaksi; nitrat-serbest aralık önerilir"
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
            drugId = "nitroprusside",
            nameTr = "Nitroprussid",
            nameEn = "Nitroprusside",
            genericName = "Nitroprusside",
            brandNames = listOf(
                "Nipride RTU"
            ),
            category = "vasopressor",
            atcCode = null,
            drugClass = "Güçlü Doğrudan Alfa/Beta Bağımsız Vazodilatör (Arteriyel ve Venöz)",
            mechanismTr = "Doğrudan nitrik oksit (NO) salarak arter ve venlerde vazodilatasyon sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Hipertansif acil durum: İnfüzyon IV: Başlangıç 0.25-0.5 mcg/kg/dakika; 5 dakikada 0.5 mcg/kg/dakika titre et; toksisiteden kaçınmak için mümkünse ≤2 mcg/kg/dakika; maks 10 mcg/kg/dakika (yüksek dozlar yalnızca maks 10 dakika)\n• Akut aort sendromu/diseksiyon (ek ajan): Başlangıç 0.25-0.5 mcg/kg/dakika; 5 dakikada 0.5 artışlarla; maks 10 mcg/kg/dakika\n• Akut dekompanse kalp yetmezliği (ek ajan): Başlangıç 0.1-0.3 mcg/kg/dakika; 5-15 dakikada titre et; olağan aralık 1-3 mcg/kg/dakika; maks 5 mcg/kg/dakika (80 kg hastada)\n• Akut iskemik inme (endikasyon dışı): Başlangıç 0.25-0.5 mcg/kg/dakika; maks 10 mcg/kg/dakika (toksisiteyi önlemek için maks 2 düşünün)", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut hipertansiyon/hipertansif kriz (bebek, çocuk, ergen): Sürekli IV infüzyon: Başlangıç 0.3-0.5 mcg/kg/dakika; 5 dakikada titre et; olağan doz 3 mcg/kg/dakika; maks 10 mcg/kg/dakika\n• Kalp debisi idamesi/post-resüsitasyon: Sürekli IV infüzyon: Başlangıç 0.3-1 mcg/kg/dakika; maks 8 mcg/kg/dakika", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Hipotansif etki: <2 dakika",
                peak = "belirtilmemiş",
                duration = "Hipotansif etki: 1-10 dakika",
                halfLife = "Nitroprussid (dolaşım): ~2 dakika; Tiyosiyanat: ~3 gün (renal yetmezlikte 2-3 kat)",
                elimination = "Hemoglobinle birleşip siyanür oluşturur; siyanür rodanaz ile tiyosiyanata dönüşür (Eliminasyon yeri: İdrar (tiyosiyanat olarak))"
            ),
            contraindications = listOf(
                "Kompansatuar hipertansiyon durumları (örneğin aort koarktasyonu)",
                "Ağır karaciğer ve böbrek yetmezliği"
            ),
            warnings = listOf(
                "KUTULU UYARI - Siyanür toksisitesi: Kısa/düşük doz (<2 mcg/kg/dk) dışında büyük miktarda siyanür oluşur; maks dozu 10 dakikadan uzun kullanma; 10 mcg/kg/dk ile 10 dakikada KB kontrol edilemezse infüzyonu kes; siyanür toksisitesi belirtilerinde (metabolik asidoz, bradikardi, konfüzyon, konvülziyon) kes",
                "KUTULU UYARI - Hipotansiyon: Aşırı hipotansiyon hayati organ perfüzyonunu bozabilir; deneyimli personelce sürekli KB izlemi gerekir",
                "Artmış İKB: İKB'yi yükseltebilir; mevcut yüksek İKB'de aşırı dikkat"
            ),
            sideEffectsCommon = listOf(
                "Bradikardi, EKG değişiklikleri, flushing, palpitasyon, şiddetli hipotansiyon, substernal ağrı, taşikardi",
                "apprehensiyon, baş dönmesi, baş ağrısı, artmış İKB, huzursuzluk",
                "methemoglobinemi",
                "karın ağrısı, bulantı",
                "kas seğirmesi",
                "hipotiroidizm."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "KUTULU UYARI - Siyanür toksisitesi: Kısa/düşük doz (<2 mcg/kg/dk) dışında büyük miktarda siyanür oluşur; maks dozu 10 dakikadan uzun kullanma; 10 mcg/kg/dk ile 10 dakikada KB kontrol edilemezse infüzyonu kes; siyanür toksisitesi belirtilerinde (metabolik asidoz, bradikardi, konfüzyon, konvülziyon) kes",
                "KUTULU UYARI - Hipotansiyon: Aşırı hipotansiyon hayati organ perfüzyonunu bozabilir; deneyimli personelce sürekli KB izlemi gerekir",
                "Artmış İKB: İKB'yi yükseltebilir; mevcut yüksek İKB'de aşırı dikkat",
                "Tiyosiyanat toksisitesi: Renal yetmezlikte veya uzamış infüzyonda (>3 mcg/kg/dk, >72 saat)",
                "Methemoglobinemi: Doz-bağımlı; metilen mavisi ile tedavi"
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
            drugId = "amiodarone",
            nameTr = "Amiodaron",
            nameEn = "Amiodarone",
            genericName = "Amiodarone",
            brandNames = listOf(
                "Νехtеrοոe;",
                "Ρаϲеrоnе"
            ),
            category = "antiarrhythmic",
            atcCode = null,
            drugClass = "Sınıf III Antiaritmik (Kanal Blokörü)",
            mechanismTr = "Esas olarak potasyum kanallarını bloke eder, ayrıca sodyum, kalsiyum ve alfa/beta reseptörleri inhibe eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Atriyal fibrilasyon (farmakolojik kardiyoversiyon): IV: 150 mg 10-30 dakikada veya 300 mg 60 dakikada, sonra 1 mg/dakika 6 saat, sonra 0.5 mg/dakika 18 saat. Toplam yükleme dozu ~6-10 g\n• AF oral: 400-1.200 mg/gün 2-3 bölünmüş dozda, toplam yükleme ~6-10 g; idame 100-200 mg/gün\n• SVT (kardiyoversiyon): IV: 150 mg 10 dakikada, sonra 1 mg/dakika 6 saat, sonra 0.5 mg/dakika 18 saat\n• Ventriküler aritmi/elektriksel fırtına: IV: 150 mg 10 dakikada (gerekirse tekrar), sonra 1 mg/dakika 6 saat, sonra 0.5 mg/dakika 18 saat\n• Hız kontrolü (alternatif): IV: 150 veya 300 mg 1 saatte, sonra 10-50 mg/saat 24 saat", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Perfüze geniş kompleks taşikardi (bebek, çocuk, ergen): IV/Intraosseöz: Yükleme dozu 5 mg/kg (maks erişkin doz 300 mg/doz) 20-60 dakikada\n• VF/nabızsız VT (şok refrakter): IV/Intraosseöz: 5 mg/kg (maks 300 mg/doz) hızlı bolus\n• Taşiaritmi (oral): Yükleme 10 mg/kg/gün 2 bölünmüş dozda 5-14 gün; sonra 5-7 mg/kg/gün\n• Sürekli IV infüzyon: 5 mcg/kg/dakika; aralık 5-15 mcg/kg/dakika; maks 2.200 mg/gün", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Oral 2 gün-3 hafta; IV (elektrofizyolojik) saatler içinde; antiaritmik etki 2-3 gün-1-3 hafta",
                peak = "1 hafta-5 ay",
                duration = "Kesim sonrası değişken, 2 hafta-aylar (erişkin: birkaç ay)",
                halfLife = "Amiodaron tek doz 58 gün (15-142); IV tek doz 9-36 gün; N-desetilamiodaron tek doz 36 gün",
                elimination = "Hepatik CYP2C8 ve 3A4 ile aktif N-desetilamiodaron metabolitine (Eliminasyon yeri: Feçes; idrar (<%1 değişmemiş))"
            ),
            contraindications = listOf(
                "Sinüs bradikardisi, ikinci veya üçüncü derece AV blok",
                "Kardiyojenik şok",
                "İyot aşırı duyarlılığı"
            ),
            warnings = listOf(
                "Aritmiler: 2025 ACLS'de nabızsız VT/VF için tercih edilen antiaritmik; yaşamı tehdit etmeyen aritmide yalnızca diğerleri etkisizse kullan",
                "Kalp transplant öncesi kullanımı primer greft disfonksiyon riskini artırabilir"
            ),
            sideEffectsCommon = listOf(
                "Hipotansiyon, bradikardi, QT uzaması, torsade de pointes, kalp yetmezliği, AV blok, asistoli",
                "bulantı, kusma",
                "epitelyal keratopati (vorteks), korneal mikrodepozitler",
                "pulmoner toksisite (pnömonit, fibrozis)",
                "hipo-/hipertiroidizm",
                "anormal KCFT, hepatik nekroz",
                "cilt fotosensitivitesi, mavi-gri pigmentasyon."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ekstravazasyon: Vezikan olabilir; uygun iğne/kateter yerleşimini sağla",
                "Oküler etkiler: Düzenli oftalmik muayene; optik nöropati/nörit görme bozukluğu yapabilir, kalıcı körlük görülmüştür; korneal mikrodepozitler hastaların çoğunda",
                "Fotosensitivite: Aşırı güneş maruziyetinden kaçın; mavi-gri cilt renk değişimi",
                "Aritmiler: 2025 ACLS'de nabızsız VT/VF için tercih edilen antiaritmik; yaşamı tehdit etmeyen aritmide yalnızca diğerleri etkisizse kullan",
                "Elektrolit bozukluklarını (hipokalemi, hipomagnezemi, hipokalsemi) düzelt",
                "Kalp transplant öncesi kullanımı primer greft disfonksiyon riskini artırabilir"
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
            drugId = "adenosine",
            nameTr = "Adenozin",
            nameEn = "Adenosine",
            genericName = "Adenosine",
            brandNames = emptyList(),
            category = "antiarrhythmic",
            atcCode = null,
            drugClass = "Sınıf IV Sınıflanamayan Antiarritmik",
            mechanismTr = "AV düğümünde iletimi yavaşlatır, geçici olarak AV bloğu oluşturur (yarım ömrü <10 saniye).",
            adultDoses = mapOf("dozaj" to DoseInfo("• Paroksismal SVT: IV: Başlangıç 6 mg 1-2 saniyede periferik hattan, hemen ardından NS flush; 1-2 dakikada sonlanmaz/AV blok oluşmazsa 12 mg ikinci doz; gerekirse üçüncü doz 12 veya 18 mg\n• Santral hat uygulaması: Başlangıç 3 mg'a düşür, sonraki dozlar 6 mg, sonra 9 mg\n• Kalp transplant hastaları: Başlangıç 1 mg'a düşür; gerekirse 3 mg'a kadar artır\n• Farmakolojik stres testi: İnfüzyon IV (periferik hat): 140 mcg/kg/dakika 6 dakika; toplam doz 840 mcg/kg", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• SVT, hemodinamik olarak stabil değil (bebek, çocuk, ergen): Hızlı IV/Intraosseöz: Başlangıç 0.1 mg/kg (maks başlangıç 6 mg/doz); etkisizse 0.2 mg/kg'a artır (maks 12 mg/doz); her bolusu NS flush izler\n• SVT, stabil (<50 kg): Hızlı IV: Başlangıç 0.05-0.1 mg/kg; maks başlangıç 6 mg; etkisizse 0.05-0.1 mg/kg artışlarla maks tek doz 0.3 mg/kg veya 12 mg\n• ≥50 kg çocuk/ergen: Başlangıç 6 mg; etkisizse 12 mg\n• Kalp transplant hastaları: Başlangıç 0.025 mg/kg", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Hızlı",
                peak = "belirtilmemiş",
                duration = "Çok kısa",
                halfLife = "<10 saniye",
                elimination = "Vasküler endotel hücreleri ve eritrositlerce sistemik dolaşımdan uzaklaştırılır; hücre içi hızla metabolize (adenozin kinaz ve deaminaz) (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Kondüksiyon bozuklukları: AV nod iletimini azaltır, kalp bloğu yapabilir; yüksek dereceli AV blok, sinüs nod disfonksiyonu, semptomatik bradikardide kontrendike (fonksiyonel pacemaker yoksa)",
                "Hipotansiyon: İnfüzyonda daha belirgin; otonom disfonksiyon, karotis stenozunda dikkat",
                "Nöbet bildirilmiştir"
            ),
            sideEffectsCommon = listOf(
                "Aritmi (kardiyoversiyon sırasında %55",
                "AF, AV blok, palpitasyon, sinüs bradikardi/taşikardi), fasiyal flushing (%18)",
                "dispne",
                "göğüs basıncı",
                "bulantı",
                "baş dönmesi, baş ağrısı",
                "asistoli (uzamış), torsade de pointes, ventriküler fibrilasyon",
                "bronkospazm",
                "nöbet."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Atriyal fibrilasyon/flutter: PSVT hastalarında bildirilmiş; özellikle Wolff-Parkinson-White ile sorunlu olabilir",
                "Kardiyovasküler olaylar: Stres testinde kardiyak arrest, MI, SVO, sürekli VT görülmüştür; unstabil anjina/akut iskemide kaçın; resüsitasyon önlemleri hazır olmalı",
                "Kondüksiyon bozuklukları: AV nod iletimini azaltır, kalp bloğu yapabilir; yüksek dereceli AV blok, sinüs nod disfonksiyonu, semptomatik bradikardide kontrendike (fonksiyonel pacemaker yoksa)",
                "Hipotansiyon: İnfüzyonda daha belirgin; otonom disfonksiyon, karotis stenozunda dikkat",
                "Nöbet bildirilmiştir"
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
            drugId = "esmolol",
            nameTr = "Esmolol",
            nameEn = "Esmolol",
            genericName = "Esmolol",
            brandNames = listOf(
                "Βrеviblоc;",
                "Βrеviblоc in NaCl;",
                "Βrеvibloс Premixed;",
                "Βrevibloϲ Premixed DS"
            ),
            category = "antiarrhythmic",
            atcCode = null,
            drugClass = "Çok Kısa Etkili Beta-1 Selektif Blokör",
            mechanismTr = "Kardiyoselektif kompetitif beta-1 reseptör antagonistidir. Plazma esterazları ile yıkılır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Atriyal fibrilasyon/flutter, hız kontrolü: Yükleme (opsiyonel): IV: 500 mcg/kg 1 dakikada; İnfüzyon: Başlangıç 50 mcg/kg/dakika; yetersizse ≥4 dakikada 50 mcg/kg/dakika artışlarla maks 300 mcg/kg/dakika\n• İntra-/postoperatif taşikardi/hipertansiyon (immediate kontrol): IV: Bolus 1.000 mcg/kg 30 saniyede, ardından 150 mcg/kg/dakika infüzyon; maks 300 mcg/kg/dakika\n• İntra-/postoperatif (gradual): Bolus 500 mcg/kg 1 dakikada, ardından 50 mcg/kg/dakika ≥4 dakika; 50 artışlarla maks 300 mcg/kg/dakika\n• Akut aort sendromu (endikasyon dışı): IV: Yükleme 500 mcg/kg 1 dakikada, sonra 25-50 mcg/kg/dakika infüzyon; maks 300 mcg/kg/dakika\n• Hipertansif acil (endikasyon dışı): Yükleme 250-500 mcg/kg, infüzyon 25-50 mcg/kg/dakika; maks 300 mcg/kg/dakika", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut şiddetli hipertansiyon (yaşamı tehdit eden, bebek/çocuk/ergen): Sürekli IV infüzyon 100-500 mcg/kg/dakika; veya bolus 100-500 mcg/kg 1 dakikada, ardından 25-100 mcg/kg/dakika; maks 500 mcg/kg/dakika\n• Postoperatif hipertansiyon (konjenital kalp hastalığı): Bolus 100-500 mcg/kg 1 dakikada, infüzyon 100-500 mcg/kg/dakika; etkili aralık 125-1.000 mcg/kg/dakika\n• SVT: Bolus 100-500 mcg/kg, infüzyon 25-100 mcg/kg/dakika; idame 50-500 mcg/kg/dakika", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Beta-blokaj IV: 2-10 dakika (yükleme dozunda en hızlı)",
                peak = "belirtilmemiş",
                duration = "Hemodinamik etki 10-30 dakika (yüksek kümülatif dozda uzar)",
                halfLife = "Erişkin: Esmolol 9 dakika; asit metabolit 3.7 saat",
                elimination = "Kanda eritrosit esterazları ile (asit metabolit ve metanol oluşur) (Eliminasyon yeri: İdrar (~%73-88 asit metabolit olarak, <%2 değişmemiş))"
            ),
            contraindications = listOf(
                "Ciddi bradikardi veya kardiyojenik şok",
                "İkinci veya üçüncü derece AV blok",
                "Dekompanse kalp yetmezliği"
            ),
            warnings = listOf(
                "Hiperkalemi: Serum potasyum yükselmesi; risk faktörlü hastalarda potasyumu izle",
                "Hipotansiyon: Sık görülebilir; yakın KB izlemi; doz azaltma/kesme genellikle 30 dakikada düzeltir",
                "Kondüksiyon: Bradikardi, sinüs duraklaması, kalp bloğu, kardiyak arrest yapabilir; hasta sinüs sendromu, 2./3. derece AV blokta kontrendike (pacemaker yoksa)",
                "Dekompanse kalp yetmezliğinde kontrendike; bronkospastik hastalıkta dikkatle"
            ),
            sideEffectsCommon = listOf(
                "Hipotansiyon (asemptomatik %25, semptomatik %12)",
                "periferik iskemi",
                "bulantı, kusma",
                "infüzyon yeri reaksiyonu (tromboflebit, doku nekrozu)",
                "ajitasyon, konfüzyon, baş dönmesi, uyku hali",
                "bradikardi (şiddetli dahil), kalp bloğu, sinüs duraklaması",
                "hiperkalemi",
                "anjiyoödem."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Anafilaktik reaksiyonlar: Beta-bloker alan hastalarda anafilaksi tedavisi (epinefrin) etkisiz olabilir",
                "Ekstravazasyon: Vezikan; cilt nekrozu ve sloughing'e yol açabilir; küçük venlerden/kelebek kateterden infüzyondan kaçın",
                "Hiperkalemi: Serum potasyum yükselmesi; risk faktörlü hastalarda potasyumu izle",
                "Hipotansiyon: Sık görülebilir; yakın KB izlemi; doz azaltma/kesme genellikle 30 dakikada düzeltir",
                "Kondüksiyon: Bradikardi, sinüs duraklaması, kalp bloğu, kardiyak arrest yapabilir; hasta sinüs sendromu, 2./3. derece AV blokta kontrendike (pacemaker yoksa)",
                "Dekompanse kalp yetmezliğinde kontrendike; bronkospastik hastalıkta dikkatle",
                "Feokromositomada önce alfa-blokaj gerekir"
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
            drugId = "labetalol",
            nameTr = "Labetalol",
            nameEn = "Labetalol",
            genericName = "Labetalol",
            brandNames = emptyList(),
            category = "antiarrhythmic",
            atcCode = null,
            drugClass = "Alfa ve Beta Adrenerjik Reseptör Blokörü (Vazodilatör)",
            mechanismTr = "Hem alfa-1 hem beta reseptörlerini bloke ederek kan basıncını düşürür (Alfa/Beta oranı 1:7 IV).",
            adultDoses = mapOf("dozaj" to DoseInfo("• Hipertansif acil (aralıklı IV): Başlangıç 10-20 mg, 1-2 dk'da; ardından her 10 dk'da 20-80 mg, hedef KB'ye ulaşana dek. • Sürekli IV infüzyon: Yükleme 10-20 mg (2 dk'da, opsiyonel), ardından 0.5-2 mg/dk; bazı hastalarda 10 mg/dk'ya titrasyon. • Akut aort sendromu (IV): hedef KB ≤120 mmHg, kalp hızı 60-80/dk. • Üretici kümülatif IV dozu 300 mg'ı aşmamayı önerir. • Kronik/dirençli HT (oral): Başlangıç 100 mg günde 2 kez; her 2-3 günde artırılır; olağan 200-1.200 mg/gün, 2 bölünmüş dozda.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut şiddetli HT (aralıklı IV bolus): Çocuk ve adölesan 0.2-1 mg/kg/doz; maksimum 40 mg/doz. • Sürekli IV infüzyon: Bebek, çocuk, adölesan 0.25-3 mg/kg/saat; düşük dozdan başlanıp yavaş titre edilir. • Kronik HT (oral): Başlangıç 1-3 mg/kg/gün, 2 bölünmüş dozda; maksimum 10-12 mg/kg/gün, 1.200 mg/gün'e dek.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Oral 20 dk-2 saat, IV <5 dk",
                peak = "Oral 2-4 saat, IV 5-15 dk",
                duration = "Oral 8-12 saat, IV ortalama 16-18 saat",
                halfLife = "Oral 6-8 saat, IV ~5.5 saat",
                elimination = "Hepatik, esas olarak glukuronid konjugasyonu; yoğun ilk geçiş etkisi (Eliminasyon yeri: İdrar (%55-60 glukuronid konjugatı), feçes (%12-27 metabolit))"
            ),
            contraindications = listOf(
                "Astım veya bronkospazm öyküsü",
                "Bradikardi, AV blok",
                "Kardiyojenik şok"
            ),
            warnings = listOf(
                "Şiddetli hepatosellüler hasar bildirilmiştir (nadir); hepatik nekroz ve ölüm olabilir, hepatik fonksiyon izlenmeli. • Beta-bloker alanlarda anafilakside epinefrin etkisiz kalabilir. • Kompanse kalp yetmezliğinde çok dikkatli kullanılır. • Feokromositomada paradoksal hipertansif yanıt riski; önce yeterli alfa-1 blokajı sağlanmalı. • Hipertiroidi bulgularını maskeleyebilir, ani kesim tiroid fırtınası tetikleyebilir. • Myasthenia gravis, PVD/Raynaud'da dikkatli kullanım."
            ),
            sideEffectsCommon = listOf(
                "Ortostatik hipotansiyon (IV %58), bulantı, baş dönmesi, yorgunluk",
                "ödem, kızarma, hipotansiyon, ventriküler aritmi",
                "postmarketing: bradikardi, kalp bloğu, kalp yetmezliği, senkop, kolestatik sarılık, hepatik nekroz, anjioödem, anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Şiddetli hepatosellüler hasar bildirilmiştir (nadir); hepatik nekroz ve ölüm olabilir, hepatik fonksiyon izlenmeli. • Beta-bloker alanlarda anafilakside epinefrin etkisiz kalabilir. • Kompanse kalp yetmezliğinde çok dikkatli kullanılır. • Feokromositomada paradoksal hipertansif yanıt riski; önce yeterli alfa-1 blokajı sağlanmalı. • Hipertiroidi bulgularını maskeleyebilir, ani kesim tiroid fırtınası tetikleyebilir. • Myasthenia gravis, PVD/Raynaud'da dikkatli kullanım."
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
            drugId = "metoprolol",
            nameTr = "Metoprolol",
            nameEn = "Metoprolol",
            genericName = "Metoprolol",
            brandNames = listOf(
                "Kapspargo Sprinkle;",
                "Լοрrеѕѕοr;",
                "Toprol XL"
            ),
            category = "antiarrhythmic",
            atcCode = null,
            drugClass = "Beta-1 Selektif Blokör",
            mechanismTr = "Kardiyoselektif beta-1 reseptör antagonistidir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Atriyal fibrilasyon/flutter hız kontrolü (akut, IV): 2.5-5 mg, 2 dk'da; her 5 dk'da tekrarlanabilir; maksimum toplam doz 15 mg. • Kronik HT (oral, tartrat IR): Başlangıç 50 mg günde 2 kez; 200 mg/gün'e dek 2 bölünmüş dozda. • HT (süksinat ER): 25-100 mg günde 1 kez; 200 mg/gün'e dek. • Anjina (tartrat IR): 50 mg günde 2 kez; maksimum 400 mg/gün. • Kalp yetmezliği (süksinat ER): Başlangıç 12.5-25 mg günde 1 kez; hedef 200 mg günde 1 kez.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• HT (tartrat IR tablet): Başlangıç 0.5-1 mg/kg/doz (maks başlangıç 25 mg/doz) günde 2 kez; maksimum 6 mg/kg/gün, 200 mg/gün'ü aşmaz. • HT (süksinat ER, ≥6 yaş): 1 mg/kg/doz günde 1 kez (maks başlangıç 50 mg); maksimum 2 mg/kg/gün, 200 mg/gün'ü aşmaz. • Kalp yetmezliği (tartrat IR): Başlangıç 0.1-0.2 mg/kg/doz günde 2 kez; maksimum 3 mg/kg/gün, 200 mg/gün'ü aşmaz.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Oral IR <1 saat; IV ~20 dk (10 dk'da infüzyon)",
                peak = "Oral 1-2 saat",
                duration = "Oral IR değişken (doza bağlı); ER ~24 saat",
                halfLife = "Yenidoğan 5-10 saat; erişkin 3-4 saat (zayıf CYP2D6 metabolizörlerinde 7-9 saat)",
                elimination = "Yoğun hepatik CYP2D6; belirgin ilk geçiş etkisi (~%50) (Eliminasyon yeri: İdrar (%95))"
            ),
            contraindications = listOf(
                "Şiddetli bradikardi",
                "AV blok",
                "Kardiyojenik şok",
                "Dekompanse kalp yetmezliği"
            ),
            warnings = listOf(
                "Beta-bloker alanlarda anafilakside epinefrin etkisiz kalabilir. • Diabetes mellitusta hipoglisemiyi potansiyalize edebilir/maskeleyebilir. • HFrEF'te düşük dozdan başlanıp dikkatli titre edilir; sadece ER formu kalp yetmezliğinde endike. • Vazospastik anjinada alfa1 blokaj aktivitesi olmayan beta-blokerlerden kaçınılmalı. • Hipertiroidi bulgularını maskeleyebilir; ani kesim tiroid fırtınasını tetikleyebilir. • Myasthenia gravis, PVD/Raynaud'da dikkatli kullanım."
            ),
            sideEffectsCommon = listOf(
                "Bradikardi (sinüs duraklaması dahil %2-16), hipotansiyon (%1-27)",
                "birinci derece AV blok, kalp yetmezliği, palpitasyon, periferik ödem",
                "bronkospazm, dispne",
                "baş dönmesi, yorgunluk",
                "postmarketing: senkop, hepatit, erektil disfonksiyon."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Beta-bloker alanlarda anafilakside epinefrin etkisiz kalabilir. • Diabetes mellitusta hipoglisemiyi potansiyalize edebilir/maskeleyebilir. • HFrEF'te düşük dozdan başlanıp dikkatli titre edilir; sadece ER formu kalp yetmezliğinde endike. • Vazospastik anjinada alfa1 blokaj aktivitesi olmayan beta-blokerlerden kaçınılmalı. • Hipertiroidi bulgularını maskeleyebilir; ani kesim tiroid fırtınasını tetikleyebilir. • Myasthenia gravis, PVD/Raynaud'da dikkatli kullanım."
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
            drugId = "hydralazine",
            nameTr = "Hidralazin",
            nameEn = "Hydralazine",
            genericName = "Hydralazine",
            brandNames = emptyList(),
            category = "antiarrhythmic",
            atcCode = null,
            drugClass = "Doğrudan Düz Kas Gevşetici (Vazodilatör)",
            mechanismTr = "Arteriyol düz kaslarında kalsiyum hareketini bozarak vazodilatasyon yapar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Hipertansif acil (IM, IV): 10-20 mg her 4-6 saatte gerektikçe; gerekirse maksimum 40 mg/doz'a artırılabilir (bazı uzmanlar maks 20 mg/doz önerir); maksimum kümülatif doz 200 mg/24 saat. • Gebelik/postpartum şiddetli HT (IV): Başlangıç 5 veya 10 mg; her 20-40 dk'da 5-10 mg tekrar; toplam 20-30 mg sonrası başka ajana geçilir. • Perioperatif HT (IV): 5-20 mg her 4-6 saatte. • Kronik HT (oral): Başlangıç 10 mg günde 4 kez; olağan 100-200 mg/gün; maksimum 300 mg/gün.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut şiddetli HT (IM, IV): Başlangıç 0.1-0.2 mg/kg/doz her 4-6 saatte; olağan 0.2-0.6 mg/kg/doz; maksimum 20 mg/doz. • Akut şiddetli HT (oral): 0.25 mg/kg/doz her 6-8 saatte; maksimum 25 mg/doz. • Kronik HT (oral): Başlangıç 0.75 mg/kg/gün, 2-4 bölünmüş dozda; maksimum 7.5 mg/kg/gün, 200 mg/gün'ü aşmaz. • Kalp yetmezliği ardyük azaltma (IV): Bebek 0.1-0.5 mg/kg/doz her 6-8 saatte (maks 2 mg/kg/doz); çocuk/adölesan 0.15-0.2 mg/kg/doz her 4-6 saatte (maks 20 mg/doz).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV 10-80 dk",
                peak = "belirtilmemiş",
                duration = "IM, IV 12 saate kadar (asetilatör durumuna göre değişir)",
                halfLife = "3-7 saat",
                elimination = "Hepatik asetilasyon; yoğun ilk geçiş etkisi (oral) (Eliminasyon yeri: İdrar (metabolitler şeklinde))"
            ),
            contraindications = listOf(
                "Koroner arter hastalığı, iskemik kalp hastalığı",
                "Mitral valvüler darlık"
            ),
            warnings = listOf(
                "Koroner arter hastalığında kullanımı kontrendike; miyokardiyal stimülasyon anjina ve iskemik EKG değişikliklerine yol açabilir. • İskemik kökenli HFrEF'te eşzamanlı nitrat olmadan kaçınılmalı. • Mitral kapak romatizmal kalp hastalığında kontrendike. • İleri böbrek yetmezliğinde doz ayarı gerekir. • ≥200 mg/gün dozlarda lupus benzeri reaksiyon riski artar; genellikle bu dozlardan kaçınılır. • Refleks taşikardi nedeniyle beta-bloker ile kombinasyon düşünülmeli."
            ),
            sideEffectsCommon = listOf(
                "Refleks taşikardi, palpitasyon, kızarma, hipotansiyon, anjina, MI",
                "baş ağrısı, baş dönmesi",
                "lupus benzeri sendrom (≥200 mg, ≥3 ay ile yaygın)",
                "periferik nöropati",
                "agranülositoz, lökopeni",
                "hepatotoksisite, vaskülit."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Koroner arter hastalığında kullanımı kontrendike; miyokardiyal stimülasyon anjina ve iskemik EKG değişikliklerine yol açabilir. • İskemik kökenli HFrEF'te eşzamanlı nitrat olmadan kaçınılmalı. • Mitral kapak romatizmal kalp hastalığında kontrendike. • İleri böbrek yetmezliğinde doz ayarı gerekir. • ≥200 mg/gün dozlarda lupus benzeri reaksiyon riski artar; genellikle bu dozlardan kaçınılır. • Refleks taşikardi nedeniyle beta-bloker ile kombinasyon düşünülmeli."
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
            drugId = "lidocaine",
            nameTr = "Lidokain",
            nameEn = "Lidocaine",
            genericName = "Lidocaine Hydrochloride",
            brandNames = listOf(
                "Aritmal",
                "Jetokain"
            ),
            category = "local_anesthetic",
            atcCode = "N01BB02",
            drugClass = "Amid Grubu Lokal Anestezik ve Sınıf 1B Antihiperaktif",
            mechanismTr = "Voltaj kapılı sodyum kanallarını bloke ederek sinir liflerinde aksiyon potansiyeli iletimini engeller, duyusal ve motor blok sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Genel maksimum tek doz: 4.5 mg/kg/doz, 250-300 mg'ı aşmaz; LAST riski yüksek hastalarda azaltılır. • Lumbar epidural anestezi: %1.5 15-20 mL (toplam 225-300 mg); %2 10-15 mL (toplam 200-300 mg). • Torakal epidural: %1 20-30 mL (toplam 200-300 mg). • İnfiltrasyon (lokal): %0.5 veya %1, 1-60 mL (toplam 5-300 mg). • IV bölgesel anestezi (Bier blok): yalnızca %0.5 (epinefrinsiz) 10-60 mL (toplam 50-300 mg); maksimum 4 mg/kg/doz. • Brakiyal sinir bloğu: %1.5 15-20 mL (toplam 225-300 mg). • Kaudal: epinefrinle 1.5% test dozu önerilir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Kütanöz infiltrasyon (çocuk/adölesan): genellikle <%2 solüsyon; maksimum 5 mg/kg/doz, erişkin maksimumu 300 mg/doz'u aşmaz; 2 saat içinde tekrarlanmaz. • Şok refrakter VF/nabızsız VT (bebek, çocuk, adölesan): IV/IO yükleme 1 mg/kg/doz; sürekli infüzyon 20-50 mcg/kg/dk. Endotrakeal yükleme 2-3 mg/kg/doz. • İntraosseöz/infüzyon ağrısı: %1 veya %2 prezervatifsiz, 0.5 mg/kg (maks 40 mg/doz).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Sistemik tek IV bolus 45-90 saniye",
                peak = "belirtilmemiş",
                duration = "Tek IV bolus 10-20 dk",
                halfLife = "Bifazik; başlangıç ~8 dk, terminal erişkin 1.5-2 saat (>24 saat infüzyon, KY, KC hastalığında uzar)",
                elimination = "%90 hepatik CYP1A2 ve CYP3A4; aktif metabolitler (MEGX, GX) birikip CNS toksisitesi yapabilir (Eliminasyon yeri: İdrar (<%10 değişmemiş, ~%90 metabolit))"
            ),
            contraindications = listOf(
                "Amid grubu lokal anesteziklere bilinen aşırı duyarlılık",
                "Ciddi kalp blokları (özellikle 2. ve 3. derece AV bloklar)"
            ),
            warnings = listOf(
                "Metmoglobinemi bildirilmiştir; G6PD eksikliği, <6 ay bebeklerde risk artar. • İntravasküler enjeksiyondan kaçınılmalı; epidural/spinal için prezervatif içeren solüsyonlar kullanılmamalı. • IV uygulamada sürekli EKG izlemi gerekir; WPW sendromu ve ileri SA/AV/intraventriküler blokta kontrendike. • Şiddetli hepatik disfonksiyonda toksisite riski artar. • İntra-artiküler sürekli infüzyon kondroliz riski (onaylı değil). • Yenidoğanlarda benzil alkol içeren formlardan kaçınılmalı (gasping sendromu)."
            ),
            sideEffectsCommon = listOf(
                "Sistemik toksisiteye (LAST) bağlı: bradikardi, hipotansiyon, dolaşım şoku",
                "CNS: konfüzyon, baş dönmesi, nöbet, tremor, seğirme, bilinç kaybı",
                "bulanık görme, diplopi, kulak çınlaması",
                "respiratuar depresyon",
                "hipersensitivite/nonimmün anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Metmoglobinemi bildirilmiştir; G6PD eksikliği, <6 ay bebeklerde risk artar. • İntravasküler enjeksiyondan kaçınılmalı; epidural/spinal için prezervatif içeren solüsyonlar kullanılmamalı. • IV uygulamada sürekli EKG izlemi gerekir; WPW sendromu ve ileri SA/AV/intraventriküler blokta kontrendike. • Şiddetli hepatik disfonksiyonda toksisite riski artar. • İntra-artiküler sürekli infüzyon kondroliz riski (onaylı değil). • Yenidoğanlarda benzil alkol içeren formlardan kaçınılmalı (gasping sendromu)."
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
            drugId = "bupivacaine",
            nameTr = "Bupivakain",
            nameEn = "Bupivacaine",
            genericName = "Bupivacaine Hydrochloride",
            brandNames = listOf(
                "Marcaine",
                "Bupivacaine"
            ),
            category = "local_anesthetic",
            atcCode = "N01BB01",
            drugClass = "Uzun Etkili Amid Grubu Lokal Anestezik",
            mechanismTr = "Sodyum kanallarını güçlü ve uzun süreli bloke eder. Kalpteki sodyum kanallarından yavaş ayrıldığı için kardiyotoksik potansiyeli çok yüksektir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Lokal anestezi infiltrasyon: %0.25 lokal; maksimum 175 mg. • Kaudal blok (prezervatifsiz): %0.25 veya %0.5, 15-30 mL. • Epidural (kaudal hariç): %0.25 veya %0.5, 10-20 mL (3-5 mL artımlarla). • Yüksek kas gevşemesi gereken cerrahi (obstetrik hariç): %0.75, 10-20 mL. • Periferik sinir bloğu: %0.25 veya %0.5, 5 mL; maksimum 400 mg/gün. • Spinal (%0.75, %8.25 dekstrozda): alt ekstremite/perineal 1 mL, alt abdominal 1.6 mL, sezaryen 1-1.4 mL. • CSE sezaryen (spinal komponent): 8-12 mg + fentanil 15 mcg.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Kaudal blok (bebek/çocuk): ≤%0.25 solüsyon, 0.5-1.3 mL/kg (maks hacim 20 mL); plain solüsyonda 2 mg/kg, epinefrinle 3 mg/kg'ı aşmamalı. • Epidural (bebek): ≤%0.25, 0.7-0.75 mL/kg; maksimum 2.5 mg/kg. • Epidural (çocuk): %0.25, 0.3-0.6 mL/kg (maks hacim 20 mL); maksimum 2.5 mg/kg. • Adölesan: %0.25 veya %0.5, 10-20 mL (3-5 mL artımlarla). Bebeklerde kardiyotoksisite riskini azaltmak için ≤%0.25 konsantrasyon önerilir.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Epidural T6'ya 17 dk'ya kadar; spinal 1 dk içinde; infiltrasyon hızlı",
                peak = "belirtilmemiş",
                duration = "Epidural 2-7.7 saat; spinal 1.5-2.5 saat; infiltrasyon 2-8 saat; implant ~72 saat",
                halfLife = "Enjeksiyon yenidoğan 8.1 saat, erişkin 2.7 saat; implant 19 saat",
                elimination = "Hepatik; metabolit pipekoloksilidin (PPX) (Eliminasyon yeri: İdrar (~%6 değişmemiş))"
            ),
            contraindications = listOf(
                "Şiddetli sistemik enfeksiyon veya spinal girişim yerinde lokal enfeksiyon",
                "Ciddi koagülopati veya aktif antikoagülan kullanımı (neuraxial bloklar için)"
            ),
            warnings = listOf(
                "Bupivakain ürünleri nadir aritmi, kardiyak arrest ve ölümle ilişkilendirilmiştir. • Sistemik toksisiteye bağlı konvülsiyon ve kardiyak arrest (özellikle istemsiz intravasküler enjeksiyon) bildirilmiştir. • %0.75 konsantrasyon obstetrik anestezide ÖNERİLMEZ. • Metmoglobinemi riski. • Baş/boyun yakınında nadir solunum durması. • Kardiyovasküler hastalıkta dikkatli kullanım. • Her enjeksiyon öncesi aspirasyon yapılmalı (intravasküler enjeksiyon garantisi vermez)."
            ),
            sideEffectsCommon = listOf(
                "Sistemik toksisite (LAST): bradikardi, hipotansiyon, kalp bloğu, ventriküler aritmi, kardiyak arrest",
                "CNS: nöbet, koma, bilinç kaybı, paralizi, parapleji",
                "bulantı/kusma, baş dönmesi, parestezi, kulak çınlaması",
                "metmoglobinemi",
                "hipersensitivite."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Bupivakain ürünleri nadir aritmi, kardiyak arrest ve ölümle ilişkilendirilmiştir. • Sistemik toksisiteye bağlı konvülsiyon ve kardiyak arrest (özellikle istemsiz intravasküler enjeksiyon) bildirilmiştir. • %0.75 konsantrasyon obstetrik anestezide ÖNERİLMEZ. • Metmoglobinemi riski. • Baş/boyun yakınında nadir solunum durması. • Kardiyovasküler hastalıkta dikkatli kullanım. • Her enjeksiyon öncesi aspirasyon yapılmalı (intravasküler enjeksiyon garantisi vermez)."
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
            drugId = "ropivacaine",
            nameTr = "Ropivakain",
            nameEn = "Ropivacaine",
            genericName = "Ropivacaine Hydrochloride",
            brandNames = listOf(
                "Naropin"
            ),
            category = "local_anesthetic",
            atcCode = "N01BB09",
            drugClass = "Uzun Etkili Amid Grubu Lokal Anestezik (Saf S-Enantiyomer)",
            mechanismTr = "Voltaj kapılı sodyum kanallarını bloke ederek sinir iletimini engeller. Saf S-enantiyomer yapısı sayesinde bupivakaine göre kardiyotoksik potansiyeli belirgin olarak daha düşüktür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Lumbar epidural cerrahi blok: %0.5 15-30 mL; %0.75 15-25 mL; %1 15-20 mL. • Lumbar epidural sezaryen: %0.5 20-30 mL; %0.75 15-20 mL. • Torakal epidural blok: %0.5 veya %0.75, 5-15 mL. • Major sinir bloğu: %0.5 35-50 mL; %0.75 10-40 mL. • Doğum ağrısı (lumbar epidural): Başlangıç %0.2 10-20 mL; sürekli infüzyon 6-14 mL/saat %0.2. • Postoperatif ağrı periferik sinir bloğu: sürekli infüzyon 5-10 mL/saat %0.2. • İnfiltrasyon/minör blok: %0.2 1-100 mL veya %0.5 1-40 mL. Epidural öncesi epinefrinli test dozu (3-5 mL).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Kaudal blok (bebek/çocuk): %0.2 (2 mg/mL), 0.5-1 mL/kg; bazı uzmanlar maks hacim 25 mL önerir. • Epidural (lumbar, torakal): %0.2 (2 mg/mL), 0.7 mL/kg. • Sürekli epidural infüzyon (bebek <3 ay): bolus %0.2 0.5-1 mL/kg, ardından 0.2 mg/kg/saat. • Bebek ≥3 ay, çocuk, adölesan: bolus 0.5-1 mL/kg, ardından sürekli infüzyon 0.2-0.5 mg/kg/saat (sıklıkla 0.4 mg/kg/saat).", null)),
            specialPopulations = mapOf(
                "geriatri" to "LAST riski nedeniyle dozlar dikkatle titre edilmelidir."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Anestezi (yola bağlı) 3-15 dk",
                peak = "belirtilmemiş",
                duration = "3-15 saat (doz ve yola bağlı)",
                halfLife = "Erişkin epidural 5-7 saat; IV terminal 111±62 dk; çocuk epidural terminal 4.9 saat",
                elimination = "Hepatik, CYP1A2 ile metabolitlere (Eliminasyon yeri: İdrar (%86; %1 değişmemiş))"
            ),
            contraindications = listOf(
                "Amid grubu lokal anesteziklere karşı bilinen aşırı duyarlılık",
                "İntravenöz rejyonel anestezi (IVRA / Bier Bloğu) uygulamalarında kullanılması kontrendikedir."
            ),
            warnings = listOf(
                "CNS toksisite: her enjeksiyon sonrası bilinç durumu sürekli izlenmeli; huzursuzluk, kulak çınlaması, bulanık görme erken uyarı işaretleri. • Sistemik toksisiteye bağlı konvülsiyon ve kardiyak arrest (istemsiz intravasküler enjeksiyon). • Metmoglobinemi riski. • Kardiyovasküler hastalık, hipotansiyon, kalp bloğunda toksisite riski artar. • İntravasküler enjeksiyondan kaçınılmalı, aspirasyon yapılmalı. • İleri yaşta (>61 yaş) bradikardi/hipotansiyon daha sık."
            ),
            sideEffectsCommon = listOf(
                "Bradikardi (%6-20), hipotansiyon (%32-69), bulantı/kusma, sırt ağrısı",
                "CNS toksisite erken belirtileri: huzursuzluk, anksiyete, kulak çınlaması, bulanık görme, tremor",
                "nöbet",
                "kardiyak aritmi",
                "metmoglobinemi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "CNS toksisite: her enjeksiyon sonrası bilinç durumu sürekli izlenmeli; huzursuzluk, kulak çınlaması, bulanık görme erken uyarı işaretleri. • Sistemik toksisiteye bağlı konvülsiyon ve kardiyak arrest (istemsiz intravasküler enjeksiyon). • Metmoglobinemi riski. • Kardiyovasküler hastalık, hipotansiyon, kalp bloğunda toksisite riski artar. • İntravasküler enjeksiyondan kaçınılmalı, aspirasyon yapılmalı. • İleri yaşta (>61 yaş) bradikardi/hipotansiyon daha sık."
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
            drugId = "levobupivacaine",
            nameTr = "Levobupivakain",
            nameEn = "Levobupivacaine",
            genericName = "Levobupivacaine",
            brandNames = emptyList(),
            category = "local_anesthetic",
            atcCode = null,
            drugClass = "Uzun etkili Lokal Anestezik (S-enantiyomer)",
            mechanismTr = "Sodyum kanallarını bloke eder, bupivakaine göre kardiyotoksisitesi daha düşüktür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Cerrahi anestezi dental: %0.5-0.75, 5-10 mL. • Epidural sezaryen: %0.5, 15-30 mL; maksimum tek doz 150 mg. • Epidural cerrahi (sezaryen dışı): %0.5-0.75, 10-20 mL; maksimum tek doz 150 mg; uzun cerrahide maksimum kümülatif 400 mg/24 saat. • İntratekal: %0.5, 3 mL. • Lokal infiltrasyon: %0.25, 1-60 mL; maksimum 150 mg. • Periferik sinir: %0.25 1-40 mL veya %0.5 1-30 mL; maksimum 150 mg. • Doğum ağrısı epidural bolus: %0.25, 6-20 mL. • Doğum ağrısı epidural infüzyon: %0.0625 10-15 mL/saat veya %0.125 4-10 mL/saat (maks 12.5 mg/saat).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Çocuklar >6 ay - <12 yaş, cerrahi anestezi lokal infiltrasyon (ilioinguinal/iliohipogastrik blok): %0.25 solüsyon 0.5 mL/kg/taraf (maks 1.25 mg/kg/taraf) veya %0.5 solüsyon 0.25 mL/kg/taraf (maks 1.25 mg/kg/taraf).", null)),
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
            warnings = emptyList(),
            sideEffectsCommon = listOf(
                "PDF'te belirtilmemiş (concise format). Diğer uzun etkili lokal anesteziklere benzer sistemik toksisite (LAST) ve kardiyotoksisite beklenir."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Maksimum tek doz 150 mg aşılmamalı. • Maksimum kümülatif doz 400 mg/24 saat (postoperatif 570 mg/24 saat'e kadar iyi tolere edilmiştir). • Epidural infüzyonda maksimum 12.5 mg/saat (doğum) veya 18.75 mg/saat (peri/postoperatif)."
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
            drugId = "prilocaine",
            nameTr = "Prilokain",
            nameEn = "Prilocaine",
            genericName = "Prilocaine",
            brandNames = listOf(
                "4% Citanest Plain Dental"
            ),
            category = "local_anesthetic",
            atcCode = null,
            drugClass = "Amid Grubu Lokal Anestezik",
            mechanismTr = "Sodyum kanallarını bloke eder, yüksek dozda methemoglobinemi riski vardır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Dental anestezi (infiltrasyon veya kondüksiyon bloğu): Başlangıç 40-80 mg (1-2 mL) %4 solüsyon olarak. • AAPD 2009 kılavuzu 2 saatlik dönemde maksimum doz: <70 kg için 6 mg/kg (400 mg); ≥70 kg için 400 mg veya 5-6 kartuş. En düşük etkili doz ve dikkatli aspirasyon kullanılmalı.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Dental anestezi (infiltrasyon veya kondüksiyon bloğu): Çocuklar <10 yaş: tek diş, 2-3 dişlik maksiller infiltrasyon veya mandibüler blokla tüm kadran işlemleri için >40 mg (1 mL) %4 solüsyon nadiren gerekir. • Çocuklar ≥10 yaş ve adölesan: erişkin dozuna bakınız.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "İnfiltrasyon <2 dk; inferior alveolar sinir bloğu <3 dk",
                peak = "belirtilmemiş",
                duration = "İnfiltrasyon ~20 dk; inferior alveolar sinir bloğu ~2.5 saat",
                halfLife = "1.6 saat (hepatik/renal yetmezlikte uzar)",
                elimination = "Esas olarak hepatik, kısmen renal; amidazlarla hidrolizlenir (orto-toluidin oluşur) (Eliminasyon yeri: İdrar (<%5 değişmemiş))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık",
                "Konjenital methemoglobinemi"
            ),
            warnings = listOf(
                "Metmoglobinemi: G6PD eksikliği, konjenital/idiyopatik metmoglobinemi, <6 ay bebeklerde risk artar; konjenital veya idiyopatik metmoglobinemide kontrendike. • Malign hipertermiyi (MH) potansiyel olarak tetikleyebilir; standart protokol izlenmeli. • CNS toksisite erken uyarı işaretleri izlenmeli. • İntravasküler enjeksiyondan kaçınılmalı; en düşük etkili doz verilmeli. • Kardiyovasküler hastalık ve hepatik yetmezlikte dikkatli kullanım."
            ),
            sideEffectsCommon = listOf(
                "Metmoglobinemi (önemli risk)",
                "bradikardi, kardiyak arrest, hipotansiyon, dolaşım şoku",
                "CNS: konvülsiyon, baş dönmesi, oral parestezi (kalıcı olabilir), bilinç kaybı",
                "respiratuar arrest/depresyon",
                "anafilaktoid reaksiyon."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Metmoglobinemi: G6PD eksikliği, konjenital/idiyopatik metmoglobinemi, <6 ay bebeklerde risk artar; konjenital veya idiyopatik metmoglobinemide kontrendike. • Malign hipertermiyi (MH) potansiyel olarak tetikleyebilir; standart protokol izlenmeli. • CNS toksisite erken uyarı işaretleri izlenmeli. • İntravasküler enjeksiyondan kaçınılmalı; en düşük etkili doz verilmeli. • Kardiyovasküler hastalık ve hepatik yetmezlikte dikkatli kullanım."
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
            drugId = "mepivacaine",
            nameTr = "Mepivakain",
            nameEn = "Mepivacaine",
            genericName = "Mepivacaine",
            brandNames = listOf(
                "Ροlοсaiոe;",
                "Ροlοсаine Dental;",
                "Ροlοϲаiոe-МРF;",
                "Scandonest 3% Plain"
            ),
            category = "local_anesthetic",
            atcCode = null,
            drugClass = "Amid Grubu Lokal Anestezik",
            mechanismTr = "Sodyum kanallarını bloke eder, vazodilatör etkisi düşüktür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Maksimum günlük doz: 1.000 mg/24 saat. • Kaudal ve epidural blok (prezervatifsiz): %1 15-30 mL (maks 300 mg) veya %1.5 10-25 mL (maks 375 mg) veya %2 10-20 mL (maks 400 mg). • Servikal/brakiyal/interkostal/pudendal sinir bloğu: %1 5-40 mL (maks 400 mg) veya %2 5-20 mL (maks 400 mg). • İnfiltrasyon: %1 40 mL'e kadar (maks 400 mg); epinefrinle %1 50 mL'e kadar (maks 500 mg). • Major sinir bloğu: %1 veya %1.5, 30-50 mL (maks 350 mg; epinefrinle 500 mg). • Dental: tek bölge %3 51 mg; tüm ağız %3 270 mg (maks 400 mg). Epidural öncesi epinefrinli test dozu (45-50 mg).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Dental anestezi (%3 solüsyon, enjeksiyon): üretici maksimum 5-6 mg/kg; maksimum toplam 270 mg. AAPD alternatif: maksimum 4.4 mg/kg; maksimum toplam 300 mg. • Lokal/bölgesel anestezi (epidural, kaudal, periferik): tek/toplam doz 5-6 mg/kg (erişkin maks 400 mg); <3 yaş veya <14 kg hastalarda yalnızca <%2 konsantrasyon kullanılmalı (LAST riskini azaltmak için).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "3-20 dk; dental üst çene 30-120 saniye, alt çene 1-4 dk",
                peak = "belirtilmemiş",
                duration = "2-2.5 saat; dental üst çene 20 dk, alt çene 40 dk",
                halfLife = "Yenidoğan 8.7-9 saat; erişkin 1.9-3.2 saat",
                elimination = "Esas olarak hepatik (N-demetilasyon, hidroksilasyon, glukuronidasyon) (Eliminasyon yeri: İdrar (%90-95 metabolit))"
            ),
            contraindications = listOf(
                "Amid grubu lokal anesteziklere karşı aşırı duyarlılık"
            ),
            warnings = listOf(
                "CNS toksisite ve kardiyovasküler kollapsta ASRA Lokal Anestezik Toksisite Tedavi Protokolü uygulanmalı. • Ürün etiketi MH'yi tetikleyebileceğini önerse de kanıt ve uzman görüşü desteklemez, güvenli kabul edilir (MHAUS 2023). • Metmoglobinemi riski (G6PD eksikliği, <6 ay bebek). • İntra-artiküler sürekli infüzyon kondroliz riski (onaylı değil). • Kardiyovasküler hastalık (ritim bozukluğu, hipotansiyon, kalp bloğu) ve hepatik yetmezlikte dikkatli kullanım."
            ),
            sideEffectsCommon = listOf(
                "Sistemik toksisite (LAST): bradikardi, kardiyovasküler depresyon, kalp bloğu, hipotansiyon, ventriküler aritmi",
                "CNS: nöbet, bilinç kaybı, parestezi, tremor, kalıcı anestezi",
                "metmoglobinemi",
                "hipersensitivite/anafilaksi",
                "apne, respiratuar depresyon."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "CNS toksisite ve kardiyovasküler kollapsta ASRA Lokal Anestezik Toksisite Tedavi Protokolü uygulanmalı. • Ürün etiketi MH'yi tetikleyebileceğini önerse de kanıt ve uzman görüşü desteklemez, güvenli kabul edilir (MHAUS 2023). • Metmoglobinemi riski (G6PD eksikliği, <6 ay bebek). • İntra-artiküler sürekli infüzyon kondroliz riski (onaylı değil). • Kardiyovasküler hastalık (ritim bozukluğu, hipotansiyon, kalp bloğu) ve hepatik yetmezlikte dikkatli kullanım."
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
            drugId = "chloroprocaine",
            nameTr = "Kloroprokain",
            nameEn = "Chloroprocaine",
            genericName = "Chloroprocaine (systemic)",
            brandNames = listOf(
                "Сlοrοtеkаl [DSC];",
                "Νеѕасaine;",
                "Νеѕаϲаiոe-MРF"
            ),
            category = "local_anesthetic",
            atcCode = null,
            drugClass = "Kısa etkili Ester grubu Lokal Anestezik",
            mechanismTr = "Sodyum kanallarını bloke eder, plazma kolinesterazı ile çok hızlı yıkılır (yarım ömrü <1 dakika).",
            adultDoses = mapOf("dozaj" to DoseInfo("• Maksimum tek doz (epinefrinsiz): 11 mg/kg; maksimum toplam 800 mg. • Maksimum tek doz (epinefrin 1:200.000 ile): 14 mg/kg; maksimum toplam 1.000 mg. • Subaraknoid blok (spinal, Clorotekal): intratekal %1, 50 mg tek doz (T10 düzeyine etkili blok); diğer prezervatifsiz formlar off-label 20-60 mg. • Kaudal blok (prezervatifsiz): %2 veya %3, 15-25 mL; 40-60 dk aralıkla tekrarlanabilir. • Brakiyal pleksus: %2 30-40 mL (toplam 600-800 mg). • Lumbar epidural sezaryen (prezervatifsiz): %3 15-20 mL. • Pudendal: %2 her tarafa 10 mL (toplam 400 mg).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Lokal injektabl anestezi ve periferik sinir bloğu (çocuk >3 yaş ve adölesan): epinefrinsiz maksimum doz 11 mg/kg; infiltrasyon için %0.5-1, sinir bloğu için %1-1.5 konsantrasyonlar önerilir. Mümkün olan en düşük doz ve konsantrasyon kullanılmalı.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "6-12 dk",
                peak = "belirtilmemiş",
                duration = "60 dk'ya kadar",
                halfLife = "İn vitro plazma: yenidoğan 43±2 saniye; erişkin 21-25 saniye",
                elimination = "Plazma enzimleriyle hızlı hidroliz (2-kloro-4-aminobenzoik asit ve beta-dietilaminoetanol) (Eliminasyon yeri: İdrar (minimal değişmemiş; metabolitler))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Yüksek sistemik konsantrasyonlarda hipotansiyon ve bradikardi (özellikle istemsiz intravasküler enjeksiyonla); kümülatif doz sınırlandırılmalı, ultrason/direkt görüntüleme kullanılmalı. • CNS/kardiyovasküler kollapsta ASRA Lokal Anestezik Toksisite Protokolü uygulanmalı. • Metmoglobinemi riski (G6PD eksikliği, <6 ay bebek). • İntra-artiküler sürekli infüzyon kondroliz riski. • Şiddetli hipertansiyon, hipotansiyon, kalp bloğu, kardiyak dekompansasyonda çok dikkatli kullanım. • Şiddetli hepatik yetmezlikte dikkatli kullanım."
            ),
            sideEffectsCommon = listOf(
                "Hipotansiyon (%5), enjeksiyon yerinde ağrı",
                "postmarketing: bradikardi, kardiyak aritmi, taşikardi",
                "CNS: nöbet, kauda ekuina sendromu, araknoidit, parestezi, bilinç kaybı",
                "hipersensitivite/anafilaksi",
                "apne, respiratuar depresyon",
                "metmoglobinemi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Yüksek sistemik konsantrasyonlarda hipotansiyon ve bradikardi (özellikle istemsiz intravasküler enjeksiyonla); kümülatif doz sınırlandırılmalı, ultrason/direkt görüntüleme kullanılmalı. • CNS/kardiyovasküler kollapsta ASRA Lokal Anestezik Toksisite Protokolü uygulanmalı. • Metmoglobinemi riski (G6PD eksikliği, <6 ay bebek). • İntra-artiküler sürekli infüzyon kondroliz riski. • Şiddetli hipertansiyon, hipotansiyon, kalp bloğu, kardiyak dekompansasyonda çok dikkatli kullanım. • Şiddetli hepatik yetmezlikte dikkatli kullanım."
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
            drugId = "articaine",
            nameTr = "Artikain",
            nameEn = "Articaine",
            genericName = "Articaine and epinephrine",
            brandNames = listOf(
                "Αrtiсаԁeոt;",
                "Оrаblос;",
                "Ѕерtοϲainе with Epinephrine 1:100,000;",
                "Ѕерtοсaiոe with Epinephrine 1:200,000;",
                "Ζοrсаiոe"
            ),
            category = "local_anesthetic",
            atcCode = null,
            drugClass = "Hızlı etkili Amid-Ester hibrit Lokal Anestezik",
            mechanismTr = "Sodyum kanallarını bloke eder, diş hekimliği ve rejyonel anestezide sık kullanılır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Dental anestezi (submukozal infiltrasyon ve/veya sinir bloğu, artikain %4/epinefrin): • İnfiltrasyon: 0.5-2.5 mL; toplam artikain dozu 20-100 mg; maksimum 7 mg/kg (0.175 mL/kg). • Sinir bloğu: 0.5-3.4 mL; toplam artikain 20-136 mg; maksimum 7 mg/kg (0.175 mL/kg). • Oral cerrahi: 1-5.1 mL; toplam artikain 40-204 mg; maksimum 7 mg/kg (0.175 mL/kg). Rutin işlemlerde epinefrin 1:200.000 tercih edilir; daha belirgin hemostaz için 1:100.000 kullanılabilir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Çocuklar ≥4 yaş ve adölesan ≤16 yaş (submukozal infiltrasyon ve/veya sinir bloğu, artikain %4/epinefrin): basit işlemler 0.76-5.65 mg/kg; kompleks işlemler 0.37-7 mg/kg; maksimum artikain dozu 7 mg/kg (%4 solüsyonun 0.175 mL/kg'ı). • Adölesan ≥17 yaş: erişkin dozuna bakınız (infiltrasyon 0.5-2.5 mL; sinir bloğu 0.5-3.4 mL; oral cerrahi 1-5.1 mL; maks 7 mg/kg).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "1-9 dk",
                peak = "belirtilmemiş",
                duration = "Tam anestezi ~1 saat (infiltrasyon), ~2 saat (sinir bloğu)",
                halfLife = "Artikain/epinefrin 43.8-44.4 dk",
                elimination = "Hepatik, plazma karboksiesteraz ile artikainik aside (inaktif) (Eliminasyon yeri: İdrar (esas olarak metabolitler))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Sistemik toksisite: lokal anesteziklerin sistemik emilimi kardiyovasküler ve/veya CNS etkileri yapabilir; toksik düzeyler AV blok, ventriküler aritmi ve kardiyak arrest'e yol açar; her enjeksiyon sonrası vital bulgular ve bilinç durumu izlenmeli. • Metmoglobinemi riski (G6PD eksikliği, <6 ay bebek). • Epinefrin lokal iskemik hasar/nekroza neden olabilir; vasküler/hipertansif hastalıkta abartılı vazokonstriktör yanıt. • Kalp bloğu/kardiyovasküler hastalıkta dikkatli kullanım, doz azaltılmalı. • Şiddetli hepatik hastalıkta dikkatli kullanım."
            ),
            sideEffectsCommon = listOf(
                "Enjeksiyon yeri/işlem ağrısı (%6-13), baş ağrısı, parestezi, uyuşma",
                "palpitasyon, taşikardi",
                "metmoglobinemi",
                "sistemik toksisite (LAST): AV blok, ventriküler aritmi, kardiyak arrest, CNS depresyonu/stimülasyonu",
                "doku nekrozu, epinefrine bağlı lokal iskemi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Sistemik toksisite: lokal anesteziklerin sistemik emilimi kardiyovasküler ve/veya CNS etkileri yapabilir; toksik düzeyler AV blok, ventriküler aritmi ve kardiyak arrest'e yol açar; her enjeksiyon sonrası vital bulgular ve bilinç durumu izlenmeli. • Metmoglobinemi riski (G6PD eksikliği, <6 ay bebek). • Epinefrin lokal iskemik hasar/nekroza neden olabilir; vasküler/hipertansif hastalıkta abartılı vazokonstriktör yanıt. • Kalp bloğu/kardiyovasküler hastalıkta dikkatli kullanım, doz azaltılmalı. • Şiddetli hepatik hastalıkta dikkatli kullanım."
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
            drugId = "dantrolene",
            nameTr = "Dantrolen",
            nameEn = "Dantrolene",
            genericName = "Dantrolene Sodium",
            brandNames = listOf(
                "Dantrium",
                "Ryanodex"
            ),
            category = "crisis",
            atcCode = "M03CA01",
            drugClass = "Direkt Etkili İskelet Kası Gevşetici (Ryanodin Reseptör Antagonisti)",
            mechanismTr = "İskelet kası sarkoplazmik retikulumundaki ryanodin reseptörlerini (RyR1) bloke ederek kalsiyum salınımını engeller, kasılmayı ve hipermetabolik süreci durdurur.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Malign hipertermi (MH) krizi tedavisi (IV): Başlangıç 2.5 mg/kg; sürekli izlemle her 5 dk'da 2.5 mg/kg tekrar, semptomlar gerileyene ve tedavi hedeflerine ulaşana dek. Çoğu hasta ≤10 mg/kg kümülatif doza yanıt verir; persistan kontraktür/rijiditede >10 mg/kg gerekebilir. • MH nüks (tedavi sonrası): IV 1 mg/kg her 4-6 saatte veya 0.25 mg/kg/saat sürekli infüzyon, ≥24 saat. • MH preoperatif profilaksi (genellikle önerilmez): IV 2.5 mg/kg, anesteziden ~75 dk önce; veya oral 4-8 mg/kg/gün 3-4 bölünmüş dozda, cerrahiden 1-2 gün önce. • Kronik spastisite (oral): Başlangıç 25 mg günde 1 kez; titrasyonla maksimum 400 mg/gün.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• MH krizi tedavisi (bebek, çocuk, adölesan, IV): Başlangıç 2.5 mg/kg, ardından her 5 dk'da 1 veya 2.5 mg/kg, tedavi hedeflerine ulaşana dek; persistan kontraktür/rijiditede >10 mg/kg gerekebilir. Gerçek vücut ağırlığı kullanılır; maksimum toplam doz yok. • Kriz sonrası takip (IV): 1 mg/kg/doz her 4-6 saatte; 6 saat penceresi dışında 2-3 mg/kg/doz gerekebilir. • Kronik spastisite (oral, ≥5 yaş): <50 kg başlangıç 0.5 mg/kg/doz, titrasyonla maksimum 12 mg/kg/gün (400 mg/gün'e dek); ≥50 kg başlangıç 25 mg günde 1 kez, maksimum 400 mg/gün.", null)),
            specialPopulations = mapOf(
                "gebelik" to "Gebelik kategorisi C. Endike olduğunda hayat kurtarıcıdır, tereddüt edilmeden verilmelidir."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Yenidoğan ~20 saat; çocuk 2-7 yaş 10 saat; erişkin 4-11 saat",
                elimination = "Hepatik (major metabolitler 5-hidroksidantrolen ve asetilamino metaboliti) (Eliminasyon yeri: Feçes (%45-50); idrar (%25 değişmemiş ve metabolitler))"
            ),
            contraindications = listOf(
                "Akut acil Malign Hipertermi krizinde mutlak bir kontrendikasyonu yoktur."
            ),
            warnings = listOf(
                "MH krizinde mümkün olan en kısa sürede uygulanmalı; tüm tetikleyici ajanlar (volatil anestezikler, süksinilkolin) kesilmeli ve destekleyici bakım verilmeli. MHAUS 24 saat MH Hattı mevcut. • Oral form için hepatotoksisite potansiyeli; semptomatik hepatit (ölümcül olabilir) bildirilmiş; hepatik fonksiyon izlenmeli, 45 günde yarar yoksa kesilmeli. Aktif hepatik hastalıkta kontrendike. • IV dantrolenle kas güçsüzlüğü (kavrama, bacak, solunum kası, yutma); hasta yardımsız ambule etmemeli. • Ekstravazasyonda doku nekrozu (vesikan); uygun kateter yerleşimi sağlanmalı."
            ),
            sideEffectsCommon = listOf(
                "Kas güçsüzlüğü, halsizlik, baş dönmesi, uyuklama",
                "hepatotoksisite (hepatit, hepatik nekroz dahil), karaciğer enzim yükselmesi",
                "flebit/enjeksiyon yeri reaksiyonu (vesikan)",
                "dispne, respiratuar yetmezlik/kas güçsüzlüğü",
                "plevral efüzyon",
                "AV blok, taşikardi",
                "hiperkalemi",
                "anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "MH krizinde mümkün olan en kısa sürede uygulanmalı; tüm tetikleyici ajanlar (volatil anestezikler, süksinilkolin) kesilmeli ve destekleyici bakım verilmeli. MHAUS 24 saat MH Hattı mevcut. • Oral form için hepatotoksisite potansiyeli; semptomatik hepatit (ölümcül olabilir) bildirilmiş; hepatik fonksiyon izlenmeli, 45 günde yarar yoksa kesilmeli. Aktif hepatik hastalıkta kontrendike. • IV dantrolenle kas güçsüzlüğü (kavrama, bacak, solunum kası, yutma); hasta yardımsız ambule etmemeli. • Ekstravazasyonda doku nekrozu (vesikan); uygun kateter yerleşimi sağlanmalı."
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
