package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo

object SeedDataDrugsPart2 {
    val drugsList = listOf(
        Drug(
            drugId = "metamizole",
            nameTr = "Metamizol",
            nameEn = "Metamizole",
            genericName = "Dipyrone (metamizole)",
            brandNames = emptyList(),
            category = "nonop_analgesic",
            atcCode = null,
            drugClass = "Non-opioid analjezik ve antispazmodik",
            mechanismTr = "COX inhibitörüdür, düz kas gevşetici etkisi de bulunur.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Ağrı veya ateş tedavisi:\n• Oral (≥15 yaş veya >53 kg ve erişkin): 500 mg-1 g günde 4 keze kadar; maks 4 g/gün.\n• IM, IV: 1-2,5 g günde 4 keze kadar; maks 5 g/gün.\n• Rektal (supozituvar): 300 mg günde 4 keze kadar; maks 1,2 g/gün.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Çocuklar ≥3 ay - ergen <15 yaş: IM, IV, oral, rektal; doz kiloya göre büyük değişkenlik gösterir (ürün etiketine bakınız).", null)),
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
                "Nonopioid analjezik; ağrı veya ateş tedavisinde kullanılır (PDF özet formatında detaylı uyarı bulunmuyor)."
            ),
            sideEffectsCommon = listOf(
                "PDF'te belirtilmemiş"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Nonopioid analjezik; ağrı veya ateş tedavisinde kullanılır (PDF özet formatında detaylı uyarı bulunmuyor)."
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
            drugId = "dexketoprofen",
            nameTr = "Deksketoprofen",
            nameEn = "Dexketoprofen",
            genericName = "Dexketoprofen",
            brandNames = emptyList(),
            category = "nonop_analgesic",
            atcCode = null,
            drugClass = "NSAID (Nonsteroidal Anti-inflamatuar İlaç)",
            mechanismTr = "COX-1 ve COX-2 inhibitörüdür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Hafif-orta ağrı tedavisi:\n• Oral (erişkin): 12,5 mg her 4-6 saatte veya 25 mg her 8 saatte; maks 75 mg/gün.", null)),
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
                "Nonsteroid antiinflamatuvar (NSAİİ) analjezik; hafif-orta ağrı tedavisinde kullanılır (PDF özet formatında detaylı uyarı bulunmuyor)."
            ),
            sideEffectsCommon = listOf(
                "PDF'te belirtilmemiş"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Nonsteroid antiinflamatuvar (NSAİİ) analjezik; hafif-orta ağrı tedavisinde kullanılır (PDF özet formatında detaylı uyarı bulunmuyor)."
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
            drugId = "diclofenac",
            nameTr = "Diklofenak",
            nameEn = "Diclofenac",
            genericName = "Diclofenac (systemic)",
            brandNames = listOf(
                "Cаmbiа;",
                "Catаflam [DSC];",
                "Լоfеnа;",
                "Ζiрsоr;",
                "Ζοrvοlеx [DSC]"
            ),
            category = "nonop_analgesic",
            atcCode = null,
            drugClass = "NSAID",
            mechanismTr = "COX-1 ve COX-2 inhibitörüdür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı, oral (potasyum/sodyum tuzu): 100-150 mg/gün bölünmüş dozlarda; başlangıç 100 mg yükleme dozu verilebilir; maks (1. günden sonra) 150 mg/gün. Diklofenak asit: 18 mg veya 35 mg günde 3 kez; maks 105 mg/gün.\n• Ankilozan spondilit / osteoartrit / romatoid artrit: 100-150 mg/gün bölünmüş (romatoid artritte gerekirse 200 mg/gün'e kadar).\n• Dismenore: 150 mg/gün bölünmüş; başlangıç 75-100 mg yükleme; menses başında.\n• Gut akut atak (endikasyon dışı): 100-150 mg/gün bölünmüş, atak başlangıcından 24-48 saat içinde.\n• Migren akut tedavi: oral toz/IR tablet 50-100 mg tek doz.\n• Maks günlük doz tuzlar için 150-200 mg (Health Canada ≤100 mg/gün), diklofenak asit için 105 mg.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı (hafif-orta), ≥12 yaş ve ergen: hemen salımlı kapsül (Zipsor) oral 25 mg günde 4 kez.\n• Jüvenil idiyopatik artrit: hemen salımlı tablet oral 2-3 mg/kg/gün bölünmüş, günde 2-3 kez; maks 150 mg/gün. Supozituvar (12,5/25 mg) rektal 1-3 mg/kg/gün.\n• Migren (≥12 yaş): oral toz çözelti (Cambia) 50 mg (1 paket) tek doz, atak başında.\n• Postoperatif ağrı: rektal supozituvar başlangıç 1-2 mg/kg/doz, ardından 1 mg/kg/doz günde 3 kez; maks tek doz 50 mg; maks 3 mg/kg/gün; ≤4 gün.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "~2 saat (sıvı dolu kapsül ~1 saat)",
                elimination = "hepatik; ilk geçiş metabolizması, birkaç metabolit oluşur (Eliminasyon yeri: idrar (~%65), safra (~%35))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Kardiyovasküler trombotik olay (MI, inme) riski; bilinen KV hastalık/risk faktörü ve yüksek dozda artar; kalp yetmezliğinde kaçının; en düşük etkili doz en kısa süre.",
                "GI olaylar: aktif GI kanamada kaçının; ülser öyküsü, antikoagülan/aspirin/kortikosteroid eş kullanımında dikkat; risk altında PPİ koadministrasyonu.",
                "Anafilaktoid reaksiyonlar; 'aspirin triadı' (astım, aspirin intoleransı, rinit) olanlarda bronkospazm/astım/rinit/ürtiker varsa kontrendike."
            ),
            sideEffectsCommon = listOf(
                "Ödem, bulantı, kusma, kabızlık, ishal, dispepsi, karın ağrısı, baş ağrısı, sersemlik, hipertansiyon, karaciğer enzimlerinde artış, GI kanama/ülser, kaşıntı",
                "ciddi: anaflaksi, Stevens-Johnson sendromu, hepatik yetmezlik, böbrek yetmezliği"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler trombotik olay (MI, inme) riski; bilinen KV hastalık/risk faktörü ve yüksek dozda artar; kalp yetmezliğinde kaçının; en düşük etkili doz en kısa süre.",
                "GI olaylar: aktif GI kanamada kaçının; ülser öyküsü, antikoagülan/aspirin/kortikosteroid eş kullanımında dikkat; risk altında PPİ koadministrasyonu.",
                "Anafilaktoid reaksiyonlar; 'aspirin triadı' (astım, aspirin intoleransı, rinit) olanlarda bronkospazm/astım/rinit/ürtiker varsa kontrendike.",
                "DRESS (eozinofili ve sistemik semptomlu ilaç reaksiyonu) bildirilmiştir.",
                "CNS etkileri (sersemlik, bulanık görme); uzun tedavide periyodik görme değerlendirmesi."
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
            drugId = "ibuprofen",
            nameTr = "İbuprofen",
            nameEn = "Ibuprofen",
            genericName = "Ibuprofen",
            brandNames = listOf(
                "Αdԁаpriո [OTC];",
                "Aԁvil Junior Strength [OTC];",
                "Αԁvil Liqui-Gels minis [OTC];",
                "Advil Migraine [OTC];",
                "Αԁvil [OTC];"
            ),
            category = "nonop_analgesic",
            atcCode = null,
            drugClass = "Nonsteroid Antiinflamatuar İlaç (NSAİİ - COX İnhibitörü)",
            mechanismTr = "COX-1 ve COX-2 inhibitörüdür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Ağrı, IV/oral: 200-400 mg her 4-6 saatte veya 600-800 mg her 6-8 saatte; maks 3,2 g/gün (kronik kullanımda bazı uzmanlar 2,4 g/gün önerir). OTC oral: 200 mg her 4-6 saatte, gerekirse 400 mg'a; maks 1,2 g/gün.\n• Antiinflamatuvar (romatizmal artrit): oral 400-800 mg her 6-8 saatte; maks 3,2 g/gün.\n• Ateş (alternatif): IV/oral 200-400 mg her 4-6 saatte; gerekirse 600-800 mg her 6 saatte; maks 3,2 g/gün.\n• Dismenore: oral başlangıç 400 mg her 4 saatte veya 600-800 mg her 6-8 saatte; maks 3,2 g/gün.\n• Migren akut: oral 400-600 mg tek doz.\n• Gut akut atak (endikasyon dışı): 800 mg her 8 saatte.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Ağrı IV (Caldolor): ≥3-<6 ay 10 mg/kg/doz tek doz (maks 100 mg); ≥6 ay-<12 yaş 10 mg/kg/doz (maks 400 mg) her 4-6 saatte, maks 40 mg/kg/gün veya 2.400 mg/gün; ≥12 yaş 400 mg her 4-6 saatte; ≥18 yaş 400-800 mg her 6 saatte (maks 3.200 mg/gün).\n• Oral kiloya göre (infant <6 ay sınırlı veri): 4-10 mg/kg/doz (maks 600 mg) her 6-8 saatte; maks 40 mg/kg/gün veya 2.400 mg/gün.\n• Sabit oral dozlama (≥6 ay-≤11 yaş): kilo/yaş tablosuna göre (ör. 2-3 yaş 100 mg, 11 yaş 300 mg); her 6-8 saatte, maks 4 doz/gün.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "oral analjezik 30-60 dk içinde",
                peak = "antipiretik maksimum etki 2-4 saat",
                duration = "oral antipiretik 6-8 saat",
                halfLife = "erişkin IV 2,22-2,44 saat",
                elimination = "hepatik oksidasyon (R/S izomer rasemik karışımı) (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Aktif gastrointestinal kanama veya peptik ülser",
                "Şiddetli kalp yetmezliği",
                "Gebeliğin üçüncü trimesteri"
            ),
            warnings = listOf(
                "Kardiyovasküler trombotik olay riski; var olan KV hastalık/GI hastalık/böbrek bozukluğu/karaciğer hastalığında kaçının veya dikkat; en düşük etkili doz en kısa süre.",
                "GI kanama riski olanlarda PPİ koadministrasyonu düşünün.",
                "Hiperkalemi riski (≥65 yaş, diyabet, renal hastalık, ACE inhibitörü eş kullanımı); potasyum izlemi.",
                "Astım: aspirin duyarlı astımda kontrendike, ciddi/ölümcül bronkospazm olabilir.",
                "Aseptik menenjit (SLE/karışık bağ doku hastalığında) riski artabilir; oftalmik etkiler için görme değerlendirmesi.",
                "Bariatrik cerrahi sonrası kronik oral nonselektif NSAİİ'den kaçının."
            ),
            sideEffectsCommon = listOf(
                "Hemoglobinde azalma, ödem, döküntü, kaşıntı, karın ağrısı/kramp, dispepsi, mide ekşimesi, bulantı, kabızlık, ishal, sersemlik, baş ağrısı, tinnitus",
                "IV: hipokalemi, anemi, nötropeni",
                "ciddi: GI ülser/kanama, bronkospazm"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler trombotik olay riski; var olan KV hastalık/GI hastalık/böbrek bozukluğu/karaciğer hastalığında kaçının veya dikkat; en düşük etkili doz en kısa süre.",
                "GI kanama riski olanlarda PPİ koadministrasyonu düşünün.",
                "Hiperkalemi riski (≥65 yaş, diyabet, renal hastalık, ACE inhibitörü eş kullanımı); potasyum izlemi.",
                "Astım: aspirin duyarlı astımda kontrendike, ciddi/ölümcül bronkospazm olabilir.",
                "Aseptik menenjit (SLE/karışık bağ doku hastalığında) riski artabilir; oftalmik etkiler için görme değerlendirmesi.",
                "Bariatrik cerrahi sonrası kronik oral nonselektif NSAİİ'den kaçının."
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
            drugId = "ketorolac",
            nameTr = "Ketorolak",
            nameEn = "Ketorolac",
            genericName = "Ketorolac (systemic)",
            brandNames = emptyList(),
            category = "nonop_analgesic",
            atcCode = null,
            drugClass = "NSAID",
            mechanismTr = "Güçlü COX-1 ve COX-2 inhibitörüdür.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Akut ağrı (≥50 kg ve <65 yaş): IV: tek doz 30 mg veya gerektikçe her 6 saatte bir 15-30 mg; maksimum 120 mg/gün; maksimum süre 5 gün. IM: tek doz 30-60 mg veya her 6 saatte bir 15-30 mg (ya da her 4-6 saatte 10-30 mg); maksimum 120 mg/gün. Oral (yalnızca IM/IV tedavinin devamı olarak): 20 mg ardından her 4-6 saatte 10 mg; maksimum 40 mg/gün\n• Akut ağrı (<50 kg veya ≥65 yaş): IV: tek doz 15 mg veya her 6 saatte 15 mg; maksimum 60 mg/gün. IM: tek doz 30 mg veya her 6 saatte 15 mg; maksimum 60 mg/gün. Oral: her 4-6 saatte 10 mg; maksimum 40 mg/gün\n• Migren (off-label, ≥50 kg ve <65 yaş): IV: tek doz 30 mg; IM: tek doz 60 mg\n• Migren (<50 kg veya ≥65 yaş): IV: tek doz 15 mg; IM: tek doz 30 mg", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Bebek/Çocuk <2 yaş (sınırlı veri): IV: 0,5 mg/kg/doz her 6-8 saatte; maksimum 15 mg/doz; 48-72 saati aşmamalı (0,25 mg/kg/doz da önerilmiştir)\n• Çocuk ≥2 yaş ve Adölesan ≤16 yaş: IM/IV: 0,5 mg/kg/doz her 6-8 saatte; maksimum 30 mg/doz; 5 günü aşmamalı. Oral: 1 mg/kg/doz her 4-6 saatte; maksimum 10 mg/doz; maksimum 40 mg/gün (yalnızca IV/IM devamı olarak)\n• Adölesan ≥17 yaş <50 kg: IM: 30 mg veya her 6 saatte 15 mg; IV: 15 mg veya her 6 saatte 15 mg; maksimum 60 mg/gün; Oral: 10 mg ardından her 4-6 saatte 10 mg; maksimum 40 mg/gün\n• Adölesan ≥17 yaş ≥50 kg: IM: 60 mg veya her 6 saatte 30 mg; IV: 30 mg veya her 6 saatte 30 mg; maksimum 120 mg/gün; Oral: 20 mg ardından her 4-6 saatte 10 mg; maksimum 40 mg/gün", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Analjezik ~30 dakika",
                peak = "Analjezik ~2-3 saat",
                duration = "Analjezik 4-6 saat",
                halfLife = "Erişkin ortalama ~5 saat (aralık 2-9 saat); yaşlılarda %30-50 uzar",
                elimination = "Hepatik; hidroksilasyon ve glukuronid konjugasyonu (Eliminasyon yeri: İdrar (%92, ~%60 değişmemiş); feçes ~%6)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "GI kanama riski (özellikle ≥60 yaş, antikoagülan/ikili antiplatelet); risk altındakilerde PPI düşünülmeli",
                "Kardiyovasküler riski (tromboz) artırabilir, özellikle yerleşik KV hastalıkta",
                "Hiperkalemi riski; potasyumu yakından izle",
                "Hepatik yetmezlikte kullanma (GI kanama ve böbrek yetmezliği riski)",
                "Aseptik menenjit riski (SLE/karışık bağ dokusu hastalığı)"
            ),
            sideEffectsCommon = listOf(
                "Karın ağrısı, dispepsi, bulantı, baş ağrısı",
                "karaciğer enzim artışı",
                "GI kanama/ülser/perforasyon, ödem, hipertansiyon",
                "uzamış kanama zamanı, anemi",
                "enjeksiyon yeri ağrısı",
                "baş dönmesi, uyuklama. Postmarketing: MI, ciddi cilt reaksiyonları (SJS/TEN)"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "GI kanama riski (özellikle ≥60 yaş, antikoagülan/ikili antiplatelet); risk altındakilerde PPI düşünülmeli",
                "Kardiyovasküler riski (tromboz) artırabilir, özellikle yerleşik KV hastalıkta",
                "En düşük etkili dozu, en kısa sürede kullan; 4-6 saatlik aralığı kısaltma",
                "Hiperkalemi riski; potasyumu yakından izle",
                "Hepatik yetmezlikte kullanma (GI kanama ve böbrek yetmezliği riski)",
                "Aseptik menenjit riski (SLE/karışık bağ dokusu hastalığı)",
                "Cerrahi/dental işlemlerden önce en az 4-6 yarılanma ömrü kadar kes"
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
            drugId = "succinylcholine",
            nameTr = "Süksinilkolin",
            nameEn = "Succinylcholine",
            genericName = "Succinylcholine Chloride / Suxamethonium",
            brandNames = listOf(
                "Lysthenon",
                "Anectine"
            ),
            category = "nmb",
            atcCode = "M03AB01",
            drugClass = "Depolarizan nöromüsküler blokör (Asetilkolin Agonisti)",
            mechanismTr = "Nikotinik asetilkolin reseptörlerine bağlanarak uç plakta sürekli depolarizasyon yaratır, kasları refrakter dönemde bırakarak gevşeme sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Endotrakeal entübasyon: IV: 0,6 mg/kg (aralık: 0,3-1,1 mg/kg)\n• Hızlı seri entübasyon (off-label): IV: 1-1,5 mg/kg\n• Uzun cerrahi işlemler (aralıklı): Başlangıç IV: 0,3-1,1 mg/kg; gerektikçe uygun aralıklarla 0,04-0,07 mg/kg\n• IM: 3-4 mg/kg'a kadar; maksimum toplam doz 150 mg\n• Elektrokonvülzif terapi (ECT, off-label): IV: 0,5-1,5 mg/kg (klinik pratikte ≥0,75 mg/kg daha yaygın)\nNot: Atropin ön medikasyonu bradikardiyi azaltabilir", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Acil entübasyon (örn. RSI) IM: Bebek <6 ay: 4-5 mg/kg; Bebek ≥6 ay ve Çocuk: 4 mg/kg (maks 150 mg/doz); Adölesan: 3-4 mg/kg (maks 150 mg/doz)\n• IV: Bebek ≤6 ay: 2-3 mg/kg/doz; Bebek >6 ay: 1-2 mg/kg/doz (3 mg/kg'a kadar önerilmiş); Çocuk: 1-2 mg/kg/doz; Adölesan: 1-1,5 mg/kg/doz\nNot: Bradikardi/asistoli riskini azaltmak için IV öncesi atropin önerilir; obezde total vücut ağırlığı kullanılır. Cerrahi/uzun süreli paralitik (sürekli infüzyon) önerilmez", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV erişkin flask paralizi <60 saniye (Neonat/Bebek ~30 sn); IM erişkin 2-3 dk",
                peak = "belirtilmemiş",
                duration = "IV ~4-10 dakika; IM 10-30 dakika; hipotermi uzatabilir",
                halfLife = "belirtilmemiş",
                elimination = "Plazma psödokolinesteraz ile hızla inaktif metabolitlere hidrolize olur (Eliminasyon yeri: İdrar (~%10 değişmemiş))"
            ),
            contraindications = listOf(
                "Malign Hipertermi öyküsü veya genetik yatkınlığı",
                "Hiperkalemi riski yüksek durumlar: Ciddi yanıklar (>24 saat geçmiş), ezilme travmaları (crush), spinal kord hasarı, uzamış immobilizasyon",
                "Atipik plazma kolinesteraz enzimi varlığı",
                "Penetran göz yaralanmaları (göz içi basıncını artırır)"
            ),
            warnings = listOf(
                "Malign hipertermi tetikleyebilir (bazı vakalar ölümcül); volatil ajanlarla ve genetik yatkınlıkta risk artar; şüphede tetikleyiciyi kes, dantrolen uygula",
                "Ciddi anafilaktik reaksiyonlar bildirilmiş; epinefrin hazır bulundurulmalı; diğer NMBA'lara önceki anafilakside dikkat",
                "Bradikardi (2. doz/çocuklarda artar); antikolinerjik (atropin) ön medikasyonla azaltılabilir",
                "Yanık (≥%20 TVYA), nöromüsküler hastalık, denervasyonda hiperkalemi riski"
            ),
            sideEffectsCommon = listOf(
                "Bradikardi (2. dozda ve çocuklarda daha sık), asistoli, aritmi, hipertansiyon/hipotansiyon",
                "hiperkalemi",
                "malign hipertermi",
                "fasikülasyon, kas ağrısı, rabdomiyoliz",
                "göz içi basıncı artışı",
                "apne, solunum depresyonu",
                "anafilaksi/aşırı duyarlılık",
                "sialore"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Malign hipertermi tetikleyebilir (bazı vakalar ölümcül); volatil ajanlarla ve genetik yatkınlıkta risk artar; şüphede tetikleyiciyi kes, dantrolen uygula",
                "Ciddi anafilaktik reaksiyonlar bildirilmiş; epinefrin hazır bulundurulmalı; diğer NMBA'lara önceki anafilakside dikkat",
                "Bradikardi (2. doz/çocuklarda artar); antikolinerjik (atropin) ön medikasyonla azaltılabilir",
                "Göz içi ve kafa içi ve mide içi basıncını artırabilir; dar açılı glokom/penetran göz yaralanmasında kaçın",
                "Yanık (≥%20 TVYA), nöromüsküler hastalık, denervasyonda hiperkalemi riski"
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
            drugId = "rocuronium",
            nameTr = "Rokuronyum",
            nameEn = "Rocuronium",
            genericName = "Rocuronium Bromide",
            brandNames = listOf(
                "Esmeron",
                "Curon"
            ),
            category = "nmb",
            atcCode = "M03AC09",
            drugClass = "Depolarizan olmayan nöromüsküler blokör (Asetilkolin Antagonisti)",
            mechanismTr = "Nikotinik asetilkolin reseptörleri için asetilkolin ile yarışarak kompetitif antagonist olarak bağlanır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Endotrakeal entübasyon: IV: 0,6-1 mg/kg tek doz; olağan aralık 0,45-1,2 mg/kg\n• Hızlı seri entübasyon: IV: 1-1,2 mg/kg tek doz (bazı uzmanlar 1,5 mg/kg)\n• Genel anestezide idame (aralıklı): IV: 0,1-0,2 mg/kg; gerektikçe tekrar (TOF izlemiyle)\n• Genel anestezide idame (sürekli infüzyon): IV: 5-12 mcg/kg/dakika başlangıç; olağan aralık 5-16 mcg/kg/dakika\n• YBÜ mekanik ventilasyon (sürekli infüzyon): IV: 0,6-1 mg/kg yükleme, ardından 3-8 mcg/kg/dakika; aralık 3-16 mcg/kg/dakika\n• YBÜ aralıklı: IV: 0,6-1 mg/kg (veya 50 mg) yükleme, ardından her 30-60 dakikada 0,3-1 mg/kg (veya 25 mg)", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Cerrahi anesteziye yardımcı başlangıç: IV: Bebek, Çocuk, Adölesan: 0,45-0,6 mg/kg\n• İdame aralıklı: IV: 0,075-0,15 mg/kg; gerektikçe tekrar\n• İdame sürekli infüzyon: IV: 7-12 mcg/kg/dakika (0,42-0,72 mg/kg/saat)\n• IM (sınırlı veri): Bebek ≥3 ay: 1 mg/kg tek doz; Çocuk 1-<6 yaş: 1,8 mg/kg tek doz\n• Hızlı seri entübasyon (sınırlı veri): IV: olağan 1 mg/kg; aralık 0,6-1,2 mg/kg\n• YBÜ paralizi aralıklı IV: 0,6 mg/kg; sürekli infüzyon: 5-17 mcg/kg/dakika (0,3-1 mg/kg/saat)", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Erişkin 45 saniye-3 dakikada iyi entübasyon koşulları; maksimum blokaj 4 dakika içinde (Bebek/Çocuk 30 sn-1 dk)",
                peak = "belirtilmemiş",
                duration = "Erişkin ~20-120 dakika (doz bağımlı); hipotermi uzatabilir",
                halfLife = "Beta eliminasyon: Erişkin 1,4-2,4 saat",
                elimination = "Minimal hepatik; 17-desasetilrokuronyum (parent ilacın %5-10 aktivitesi) (Eliminasyon yeri: Feçes (%50-75); idrar (%33'e kadar))"
            ),
            contraindications = emptyList(),
            warnings = listOf(
                "Diğer NMBA'lara önceki anafilakside kontrendike (çapraz duyarlılık)",
                "Uzamış paralizi olabilir (özellikle uzun kullanımda); ekstübasyondan önce yeterli iyileşme sağlanmalı; kortikosteroid kullanımı dikkate alınmalı"
            ),
            sideEffectsCommon = listOf(
                "Periferik vasküler dirençte artış, taşikardi (≤%5",
                "çocuklarda daha sık), hipertansiyon, geçici hipotansiyon",
                "anafilaksi. <%1: anafilaktoid reaksiyon, aritmi, bulantı, kaşıntı, döküntü, kusma"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Anafilaktoid/aşırı duyarlılık reaksiyonları bildirilmiş; epinefrin hazır bulundurulmalı",
                "Diğer NMBA'lara önceki anafilakside kontrendike (çapraz duyarlılık)",
                "Uzamış paralizi olabilir (özellikle uzun kullanımda); ekstübasyondan önce yeterli iyileşme sağlanmalı; kortikosteroid kullanımı dikkate alınmalı",
                "Yanık (≥%20 TVYA) ve immobilize hastalarda direnç gelişebilir",
                "Kardiyovasküler hastalıkta etki başlangıcı gecikebilir ve süre uzayabilir",
                "Hepatik yetmezlikte klinik süre uzayabilir"
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
            drugId = "cisatracurium",
            nameTr = "Sisatraküryum",
            nameEn = "Cisatracurium",
            genericName = "Cisatracurium Besylate",
            brandNames = listOf(
                "Nimbex"
            ),
            category = "nmb",
            atcCode = "M03AC11",
            drugClass = "Depolarizan olmayan nöromüsküler blokör (Benzilizokinolin grubu)",
            mechanismTr = "Motor uç plaktaki asetilkolin reseptörlerini kompetitif olarak bloke eder. Hofmann eliminasyonu ile vücuttan atılır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Endotrakeal entübasyon: IV: 0,15-0,2 mg/kg tek doz\n• Hızlı seri entübasyon (alternatif ajan, off-label): IV: 0,4 mg/kg tek doz\n• Genel anestezide idame (aralıklı): IV: 0,01-0,03 mg/kg; gerektikçe tekrar (TOF izlemiyle)\n• Genel anestezide idame (sürekli infüzyon): IV: 1-3 mcg/kg/dakika başlangıç; titre et\n• YBÜ mekanik ventilasyon (sürekli infüzyon): IV: 0,1-0,2 mg/kg yükleme, ardından 1-3 mcg/kg/dakika; olağan aralık 0,5-10 mcg/kg/dakika", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Aralıklı dozlama: Bebek ve Çocuk <2 yaş: IV: 0,15 mg/kg/doz (5-10 saniyede); Çocuk ≥2 yaş ve Adölesan: IV: 0,1-0,15 mg/kg/doz (5-10 saniyede)\n• Sürekli IV infüzyon: Bebek, Çocuk, Adölesan: 1-4 mcg/kg/dakika (0,06-0,24 mg/kg/saat)\nNot: Obezde ideal vücut ağırlığı kullanılmalı", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV 2-3 dakika (doz bağımlı)",
                peak = "3-5 dakika",
                duration = "Doz bağımlı, 0,1 mg/kg tek dozdan sonra 35-45 dakika; hipotermi uzatabilir",
                halfLife = "22-29 dakika",
                elimination = "Kan dolaşımında hızlı non-enzimatik degradasyon (Hofmann eliminasyonu) ile laudanozin ve inaktif metabolitlere (Eliminasyon yeri: İdrar (%95; <%10 değişmemiş); feçes (%4))"
            ),
            contraindications = emptyList(),
            warnings = listOf(
                "Ciddi anafilaktik reaksiyonlar bildirilmiş (bazıları ölümcül); epinefrin hazır bulundurulmalı",
                "Bradikardi diğer NMBA'lara göre daha sık olabilir (kalp hızına anlamlı etkisi yoktur)",
                "Rezidüel paralizi bildirilmiş; nöromüsküler hastalıkta (myastenia gravis) risk artar; reversal ajanları düşünülebilir; yeterli iyileşmeden sonra ekstübe et",
                "Bazı formlarda benzil alkol; neonatlarda dikkat"
            ),
            sideEffectsCommon = listOf(
                "Bronkospazm (≤%1)",
                "bradikardi, kızarma, hipotansiyon, döküntü. Postmarketing: histamin salınımı, ciddi aşırı duyarlılık (anafilaksi dahil), nöromüsküler bloktan gecikmiş iyileşme, miyopati, malign hipertermi"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ciddi anafilaktik reaksiyonlar bildirilmiş (bazıları ölümcül); epinefrin hazır bulundurulmalı",
                "Bradikardi diğer NMBA'lara göre daha sık olabilir (kalp hızına anlamlı etkisi yoktur)",
                "Rezidüel paralizi bildirilmiş; nöromüsküler hastalıkta (myastenia gravis) risk artar; reversal ajanları düşünülebilir; yeterli iyileşmeden sonra ekstübe et",
                "Terapötik hipotermi Hofmann eliminasyonunu yavaşlatıp paralizi süresini uzatabilir",
                "Yanık (≥%20 TVYA) ve immobilize hastalarda direnç",
                "Bazı formlarda benzil alkol; neonatlarda dikkat"
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
            drugId = "vecuronium",
            nameTr = "Veküronyum",
            nameEn = "Vecuronium",
            genericName = "Vecuronium",
            brandNames = emptyList(),
            category = "nmb",
            atcCode = null,
            drugClass = "Depolarizan olmayan orta etkili nöromüsküler blokör (Asetilkolin Antagonisti)",
            mechanismTr = "Kompetitif nikotinik asetilkolin reseptör antagonistidir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Endotrakeal entübasyon: IV: 0,1-0,2 mg/kg tek doz\n• Hızlı seri entübasyon (alternatif ajan, off-label): IV: 0,1-0,2 mg/kg tek doz\n• Genel anestezide idame (aralıklı): IV: 0,01-0,015 mg/kg; gerektikçe tekrar (TOF izlemiyle)\n• Genel anestezide idame (sürekli infüzyon): IV: 0,8-1,7 mcg/kg/dakika başlangıç; titre et\n• YBÜ mekanik ventilasyon (aralıklı): IV: 0,1-0,2 mg/kg; tekrar; titreme tedavisinde 8-12 mg kullanılabilir\n• YBÜ (sürekli infüzyon): IV: 0,08-0,1 mg/kg yükleme, ardından 0,8-1,7 mcg/kg/dakika; olağan aralık 0,8-1,2 mcg/kg/dakika", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Bebek: IV: 0,08-0,1 mg/kg/doz, gerektikçe tekrar (süksinilkolinle entübasyon yapıldıysa 0,04-0,06 mg/kg'a azaltılabilir); sürekli infüzyon: 0,8-1,7 mcg/kg/dakika (0,05-0,1 mg/kg/saat)\n• Çocuk ve Adölesan: IV: 0,08-0,1 mg/kg/doz, gerektikçe tekrar; sürekli infüzyon: 0,8-2,5 mcg/kg/dakika (0,05-0,15 mg/kg/saat). 1-10 yaş daha yüksek başlangıç dozu gerekebilir\n• Hızlı seri entübasyon (sınırlı veri): Bebek, Çocuk, Adölesan: IV/Intraosseöz: 0,1-0,3 mg/kg", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "İyi entübasyon koşulları 2-4 dakika içinde; maksimum nöromüsküler blokaj 3-5 dakika içinde",
                peak = "belirtilmemiş",
                duration = "Dengeli anestezide (%25 iyileşmeye kadar) 25-40 dakika; %95 iyileşme ~45-65 dakika; hipotermi uzatabilir",
                halfLife = "Sağlıklı erişkin 65-75 dakika (Bebek 65 dk; Çocuk 41 dk)",
                elimination = "Aktif metabolit 3-desasetil veküronyum (parent ilacın 1/2 aktivitesi) (Eliminasyon yeri: Primer feçes (%35-50); idrar (%15-50 değişmemiş ilaç ve metabolitler))"
            ),
            contraindications = listOf(
                "Nöromüsküler blokörlere karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Ciddi anafilaktik reaksiyonlar bildirilmiş (bazıları ölümcül); epinefrin hazır bulundurulmalı; diğer NMBA'lara önceki anafilakside dikkat",
                "Uzamış paralizi olabilir (özellikle uzun kullanımda); kortikosteroid kullanımı dikkate alınmalı",
                "Sulandırıcı (bakteriyostatik su) benzil alkol içerir; neonatlarda dikkat"
            ),
            sideEffectsCommon = listOf(
                "<%1, postmarketing: bradikardi, dolaşım şoku, ödem, kızarma, aşırı duyarlılık reaksiyonu (eritem, hipotansiyon, taşikardi, ürtiker dahil), kaşıntı, döküntü"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ciddi anafilaktik reaksiyonlar bildirilmiş (bazıları ölümcül); epinefrin hazır bulundurulmalı; diğer NMBA'lara önceki anafilakside dikkat",
                "Uzamış paralizi olabilir (özellikle uzun kullanımda); kortikosteroid kullanımı dikkate alınmalı",
                "Yanık (≥%20 TVYA) ve immobilize hastalarda direnç",
                "Hepatik yetmezlikte klinik süre uzayabilir; anefrik hastalarda da uzayabilir",
                "Yaşlılarda doz azaltımı düşünülebilir",
                "Sulandırıcı (bakteriyostatik su) benzil alkol içerir; neonatlarda dikkat"
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
            drugId = "atracurium",
            nameTr = "Atraküryum",
            nameEn = "Atracurium",
            genericName = "Atracurium",
            brandNames = emptyList(),
            category = "nmb",
            atcCode = null,
            drugClass = "Depolarizan olmayan orta etkili nöromüsküler blokör (Benzilizokinolin)",
            mechanismTr = "Kompetitif nikotinik asetilkolin reseptör antagonistidir. Hofmann eliminasyonu ile atılır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Endotrakeal entübasyon: IV: 0,5-0,6 mg/kg tek doz\n• Genel anestezide idame (aralıklı): IV: her ~15-25 dakika aralıkla 0,08-0,1 mg/kg (TOF izlemiyle)\n• Genel anestezide idame (sürekli infüzyon): IV: 4-12 mcg/kg/dakika başlangıç; olağan aralık 2-15 mcg/kg/dakika\n• YBÜ mekanik ventilasyon (sürekli infüzyon): IV: 0,4-0,6 mg/kg yükleme, ardından 4-12 mcg/kg/dakika; olağan aralık 2-20 mcg/kg/dakika\n• YBÜ aralıklı: IV: 0,4-0,6 mg/kg, ardından her ~15-25 dakika 0,08-0,1 mg/kg", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Cerrahi anesteziye yardımcı bolus: Bebek ve Çocuk <2 yaş: IV: başlangıç 0,3-0,4 mg/kg; Çocuk ≥2 yaş ve Adölesan: IV: başlangıç 0,4-0,5 mg/kg, ardından 0,08-0,1 mg/kg/doz (20-45 dk sonra), her 15-25 dakikada tekrar (KV hastalık/histamin riskinde 0,3-0,4 mg/kg'a azalt)\n• Cerrahi sürekli infüzyon: Bebek/Çocuk <2 yaş: 6-14 mcg/kg/dakika; Çocuk ≥2 yaş ve Adölesan: 9-10 mcg/kg/dakika başlangıç, idame 5-9 mcg/kg/dakika\n• YBÜ paralizi: bolus IV: 0,3-0,6 mg/kg; sürekli infüzyon: 5-12 mcg/kg/dakika (aralık 5-40 mcg/kg/dakika)", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "2-3 dakika (doz bağımlı)",
                peak = "3-5 dakika",
                duration = "0,4-0,5 mg/kg dozdan sonra iyileşme 20-35 dakikada başlar; %95 iyileşme 60-70 dakika; hipotermi uzatabilir",
                halfLife = "Erişkin bifazik: başlangıç (dağılım) 2 dakika; terminal 20 dakika",
                elimination = "Ester hidrolizi ve Hofmann eliminasyonu (böbrek/karaciğer/enzimden bağımsız); laudanozin CNS uyaranı (Eliminasyon yeri: İdrar (<%5))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Ciddi anafilaktik reaksiyonlar bildirilmiş (bazıları ölümcül); epinefrin hazır bulundurulmalı; diğer NMBA'lara önceki anafilakside dikkat",
                "Bradikardi diğer NMBA'lara göre daha sık olabilir (kalp hızına anlamlı etkisi yoktur)",
                "Bazı formlarda benzil alkol; neonatlarda dikkat"
            ),
            sideEffectsCommon = listOf(
                "Kızarma",
                "<%1: bradikardi, bronkospazm, dispne, eritem, aşırı duyarlılık reaksiyonu, hipotansiyon, artmış bronşiyal sekresyon, enjeksiyon yeri reaksiyonu, laringospazm, kaşıntı, nöbet, taşikardi, ürtiker, hışıltı (genellikle histamin salınımına bağlı, hafif/nadir)"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ciddi anafilaktik reaksiyonlar bildirilmiş (bazıları ölümcül); epinefrin hazır bulundurulmalı; diğer NMBA'lara önceki anafilakside dikkat",
                "Bradikardi diğer NMBA'lara göre daha sık olabilir (kalp hızına anlamlı etkisi yoktur)",
                "Uzamış paralizi olabilir (özellikle uzun kullanımda); ekstübasyondan önce yeterli iyileşme",
                "Yanık (≥%20 TVYA) ve immobilize hastalarda direnç",
                "Bazı formlarda benzil alkol; neonatlarda dikkat"
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
            drugId = "sugammadex",
            nameTr = "Sugammadeks",
            nameEn = "Sugammadex",
            genericName = "Sugammadex Sodium",
            brandNames = listOf(
                "Bridion"
            ),
            category = "reversal",
            atcCode = "V03AB35",
            drugClass = "Selektif Relaksant Bağlayıcı Ajan (Modifiye Gamma-Siklodekstrin)",
            mechanismTr = "Roküronyum ve veküronyum moleküllerini 1:1 oranında enkapsüle (kapsül içine alma) ederek nöromüsküler kavşaktan uzaklaştırır ve bloğu hızla sonlandırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Rokuronyum/veküronyum blokajının rutin reversalı (derin blok: en az 1-2 post-tetanik sayım, TOF'ta 2. seğirme öncesi): IV: 4 mg/kg tek doz\n• Orta blok (TOF'ta 2. seğirme görüldükten sonra): IV: 2 mg/kg tek doz\n• Rokuronyum blokajının acil reversalı: IV: 16 mg/kg tek doz (1,2 mg/kg rokuronyumdan ~3 dakika sonra)\nNot: Dozlama gerçek vücut ağırlığına göre. Acil reversaldan sonra rokuronyum/veküronyum yeniden vermek için 24 saat bekle", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Bebek, Çocuk, Adölesan rutin reversal: Derin blok (1-2 post-tetanik sayım, TOF'a sıfır yanıt): IV bolus: 4 mg/kg tek doz\n• Orta blok (TOF'ta 2. seğirme görüldükten sonra): IV bolus: 2 mg/kg tek doz\nNot: Dozlama gerçek vücut ağırlığına göre", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "<3 dakika",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Erişkin etkin ~2 saat (renal yetmezlikte uzar: hafif 4 saat, orta 6 saat, ağır 19 saat); <17 yaş ~1-2 saat",
                elimination = "Metabolize edilmez (Eliminasyon yeri: İdrar (%95 değişmemiş ilaç))"
            ),
            contraindications = listOf(
                "Sugammadeks bileşenlerine karşı bilinen aşırı duyarlılık (anafilaksi riski)"
            ),
            warnings = listOf(
                "Belirgin bradikardi ve kardiyak arrestle giden bradikardi bildirilmiş (genellikle uygulamadan dakikalar sonra); hemodinamik izle, anlamlı bradikardide atropin uygula",
                "Ciddi aşırı duyarlılık (anafilaksi/anafilaktik şok); önceden maruz kalmamış hastalarda da olabilir",
                "Hemostaz bozukluğu olanlarda dikkat (aPTT/PT geçici uzaması)"
            ),
            sideEffectsCommon = listOf(
                "Hipotansiyon (%5-13), bulantı (erişkin %26), kusma, ağrı (%48-59)",
                "bradikardi (%5-10), QT uzaması, hipertansiyon, taşikardi",
                "aşırı duyarlılık (%7-9",
                "anafilaksi <%1)",
                "baş ağrısı, baş dönmesi",
                "öksürük. Postmarketing: anafilaktik şok, laringospazm, bronkospazm, atriyal fibrilasyon"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Belirgin bradikardi ve kardiyak arrestle giden bradikardi bildirilmiş (genellikle uygulamadan dakikalar sonra); hemodinamik izle, anlamlı bradikardide atropin uygula",
                "Ciddi aşırı duyarlılık (anafilaksi/anafilaktik şok); önceden maruz kalmamış hastalarda da olabilir",
                "Nöromüsküler blokajın nüksü (rekürarizasyon) olmuş; genellikle suboptimal dozlamayla; reversaldan sonra solunum izlemini sürdür, ekstübasyon sonrası ventilatör desteği erişilebilir olsun",
                "Hemostaz bozukluğu olanlarda dikkat (aPTT/PT geçici uzaması)",
                "Yalnızca rokuronyum veya veküronyum için kullan; non-steroidal NMBA'larda kullanma",
                "YBÜ kullanımı değerlendirilmemiştir"
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
            drugId = "neostigmine",
            nameTr = "Neostigmin",
            nameEn = "Neostigmine",
            genericName = "Neostigmine Methylsulfate",
            brandNames = listOf(
                "Neostigmin",
                "Prostigmin"
            ),
            category = "reversal",
            atcCode = "N07AA01",
            drugClass = "Reversibl Asetilkolinesteraz İnhibitörü",
            mechanismTr = "Asetilkolini yıkan asetilkolinesteraz enzimini inhibe ederek nöromüsküler kavşakta asetilkolin miktarını artırır ve non-depolarizan blokörlerin etkisini yarışmalı olarak geri çevirir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Nondepolarizan nöromüsküler blokajın cerrahi sonrası reversalı: IV: 0,02-0,07 mg/kg; 10-20 dakikada %90 TOF seğirme oranı sağlar; maksimum toplam doz 0,07 mg/kg veya 5 mg (hangisi azsa). TOF sayımına göre: TOF 2-3: 0,07 mg/kg; TOF 4 (fade ile): 0,04-0,05 mg/kg; TOF 4 (fade yok): 0,02 mg/kg\n• Myastenia gravis (Kanada): Oral: başlangıç 15 mg günde 3 kez; olağan 150 mg/gün; aralık 15-375 mg/gün\n• Akut kolonik psödo-obstrüksiyon (Ogilvie, off-label): IV bolus: 3-5 dakikada 2 mg; aralıklı infüzyon: 60 dakikada 2,5 mg; sürekli infüzyon: saatte 0,4 mg\nNot: Antikolinerjik ajan (atropin/glikopirolat) IV olarak önce veya birlikte verilmeli", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Nondepolarizan nöromüsküler blokajın cerrahi sonrası reversalı: Bebek, Çocuk, Adölesan: IV: 0,03-0,07 mg/kg; 10-20 dakikada %90 TOF seğirme oranı; maksimum toplam doz 0,07 mg/kg veya 5 mg (hangisi azsa). 0,03 mg/kg kısa yarı ömürlü ajanlar için (örn. rokuronyum); 0,07 mg/kg uzun yarı ömürlü ajanlar için (örn. veküronyum)\n• Juvenil myastenia gravis tanısı (sınırlı veri): Bebek/Çocuk <2 yaş: IM: 0,04 mg/kg tek doz; sonuç şüpheliyse 4 saatte tekrar\nNot: Antikolinerjik ajan önce veya birlikte verilmeli", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Peristaltik aktivite: Oral 2-4 saat; Parenteral 10-30 dakika",
                peak = "belirtilmemiş",
                duration = "IM 2,5-4 saat",
                halfLife = "IM erişkin 51-90 dakika; IV aralık 24-113 dakika; oral erişkin 42-60 dakika",
                elimination = "Hepatik (Eliminasyon yeri: İdrar (%50 değişmemiş, kalanı metabolitler))"
            ),
            contraindications = listOf(
                "Mekanik intestinal veya üriner sistem obstrüksiyonu",
                "Peritonit varlığı"
            ),
            warnings = listOf(
                "Kardiyovasküler etkiler: Bradikardi, hipotansiyon, disritmiler özellikle IV ile olabilir; nondepolarizan blokaj reversalı için IV verildiğinde atropin/glikopirolat birlikte veya önce verilmeli",
                "Kolinerjik kriz: Aşırı doz aşırı kas güçsüzlüğü ve potansiyel ölümcül solunum paralizi ile sonuçlanabilir; miyastenik krizden ayırt edilmeli",
                "Aşırı duyarlılık (anafilaksi, anjiyoödem, bronkospazm); atropin ve epinefrin hazır bulundurulmalı",
                "Belirli nöromüsküler hastalıklarda (myastenia gravis, müsküler distrofi) kaçın veya dikkatle kullan; sugammadeks tercih edilebilir"
            ),
            sideEffectsCommon = listOf(
                "Bradikardi, AV blok, aritmi, hipotansiyon, senkop, kızarma",
                "diaforez, kaşıntı, döküntü",
                "flatülans, artmış peristaltizm, bulantı, sialore, kusma",
                "baş dönmesi, uyuklama, nöbet, postanestezik titreme",
                "miyozis",
                "apne, bronkospazm, artmış bronşiyal sekresyon, solunum depresyonu",
                "anafilaksi"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler etkiler: Bradikardi, hipotansiyon, disritmiler özellikle IV ile olabilir; nondepolarizan blokaj reversalı için IV verildiğinde atropin/glikopirolat birlikte veya önce verilmeli",
                "Kolinerjik kriz: Aşırı doz aşırı kas güçsüzlüğü ve potansiyel ölümcül solunum paralizi ile sonuçlanabilir; miyastenik krizden ayırt edilmeli",
                "Aşırı duyarlılık (anafilaksi, anjiyoödem, bronkospazm); atropin ve epinefrin hazır bulundurulmalı",
                "Nöromüsküler etkiler: Blokaj minimalken büyük IV dozlar nöromüsküler disfonksiyona yol açabilir; iyileşme tama yakınsa dozu azalt",
                "Belirli nöromüsküler hastalıklarda (myastenia gravis, müsküler distrofi) kaçın veya dikkatle kullan; sugammadeks tercih edilebilir"
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
            drugId = "atropine",
            nameTr = "Atropin",
            nameEn = "Atropine",
            genericName = "Atropine Sulfate",
            brandNames = listOf(
                "Atropin Sülfat"
            ),
            category = "anticholinergic",
            atcCode = "A03BA01",
            drugClass = "Antimuskarinik (Antikolinerjik)",
            mechanismTr = "Parasempatik sinir uçlarındaki postgangliyonik muskarinik reseptörleri yarışmalı olarak bloke eder, vagal tonusu baskılayarak kalp hızını ve sekresyonları azaltır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Semptomatik/hemodinamik instabil bradikardi: IV, IM, Intraosseöz: semptomlar düzelene veya kalp hızı stabilize olana dek her 3-5 dakikada 1 mg; maksimum toplam doz 3 mg. Endotrakeal (en az tercih edilen): her 3-5 dakikada 2-2,5 mg\n• Nöromüsküler blokaj reversalı sırasında bradikardi: IV: edrofonyumla 5-7 mcg/kg veya neostigminle 15-20 mcg/kg\n• Salivasyon ve sekresyon inhibisyonu (preanestezi): IM, IV, SUBQ: işlemden 30-60 dk önce 0,5-1 mg; gerektikçe her 4-6 saatte tekrar; maksimum 3 mg\n• Organofosfat/karbamat/sinir ajanı zehirlenmesi: IV/IM/Intraosseöz: hafif 1-2 mg, orta-ağır 2-5 mg başlangıç bolusu; her 3-5 dakikada önceki dozu ikiye katla; idamede total bolusun %10-20'si saatte sürekli infüzyon\nNot: IV dozlar ≤0,25 mg paradoks bradikardiyle ilişkilendirilmiştir", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Bradikardi: Bebek, Çocuk, Adölesan: IV/Intraosseöz: 0,02 mg/kg/doz; minimum 0,1 mg/doz, maksimum 0,5 mg/doz; 5 dakikada bir kez tekrar. Endotrakeal: 0,04-0,06 mg/kg/doz\n• Salivasyon/sekresyon inhibisyonu (preop): Bebek/Çocuk <12 yaş: IM, IV, SUBQ: 0,02 mg/kg/doz (maks 0,5 mg/doz); Çocuk ≥12 yaş ve Adölesan: 0,02 mg/kg/doz (maks 1 mg/doz); 30-60 dk önce, her 4-6 saatte tekrar\n• Acil entübasyon premedikasyon: IV/Intraosseöz: 0,02 mg/kg/doz (aralık 0,01-0,02; maks 0,5 mg/doz)\n• Organofosfat/karbamat/sinir ajanı zehirlenmesi: pulmoner duruma titre et (ağır vakada önerilen dozun ≥2 katı gerekebilir)", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Kalp hızı artışı: IV anında (maksimum 0,7-4 dk), IM 15-30 dk; Salivasyon inhibisyonu: IM 30 dk içinde",
                peak = "IV kalp hızı 0,7-4 dakika; IM salivasyon 30-60 dakika",
                duration = "Salivasyon inhibisyonu: IM ≤4 saat",
                halfLife = "Erişkin 3 ± 0,9 saat; Çocuk <2 yaş 6,9 ± 3 saat; Yaşlı 65-75 yaş 10 ± 7,3 saat",
                elimination = "Hepatik enzimatik hidroliz (Eliminasyon yeri: İdrar (%13-50 değişmemiş ilaç ve metabolitler))"
            ),
            contraindications = listOf(
                "Dar açılı glokom",
                "Mekanik prostat obstrüksiyonu (üriner retansiyon riski)",
                "Taşiaritmiler"
            ),
            warnings = listOf(
                "Kardiyovasküler hastalıkta dikkat (miyokard iskemisi, kalp yetmezliği, taşiaritmi); KB artışı ve taşikardi iskemiye/MI'ye yol açabilir",
                "Dar açılı glokomda dikkat (akut glokom)"
            ),
            sideEffectsCommon = listOf(
                "Asistoli, atriyal aritmi, bradikardi, taşikardi, çarpıntı, çeşitli aritmiler, EKG değişiklikleri, kan basıncı değişiklikleri",
                "ajitasyon, konfüzyon, deliryum, baş dönmesi, halüsinasyon, nöbet",
                "anhidroz, kuru/sıcak cilt",
                "ağız kuruluğu, konstipasyon, paralitik ileus",
                "idrar retansiyonu",
                "bulanık görme. Doz ile ilişkili"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aşırı duyarlılık/anafilaktik reaksiyonlar olabilir",
                "Hipertermi: Terlemeyi inhibe edip ısı kaynaklı yaralanmaya yol açabilir",
                "Tip II ikinci derece veya üçüncü derece AV blokta etkin tedavi için güvenme; kalp transplant alıcılarında etkisiz olabilir veya paradoks blok yapabilir",
                "Kardiyovasküler hastalıkta dikkat (miyokard iskemisi, kalp yetmezliği, taşiaritmi); KB artışı ve taşikardi iskemiye/MI'ye yol açabilir",
                "Dar açılı glokomda dikkat (akut glokom)",
                "Hepatik/renal yetmezlikte etki uzayabilir",
                "Pediatride antikolinerjik etkilere daha duyarlı olabilirler"
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
            drugId = "glycopyrrolate",
            nameTr = "Glikopirolat",
            nameEn = "Glycopyrrolate",
            genericName = "Glycopyrronium Bromide",
            brandNames = listOf(
                "Robinul"
            ),
            category = "anticholinergic",
            atcCode = "A03AB02",
            drugClass = "Kuaterner Amonyum Antimuskarinik",
            mechanismTr = "Muskarinik reseptörleri seçici olarak bloke ederek tükürük, bronşiyal ve gastrik sekresyonları azaltır, kalp hızını dengeler.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Sekresyon azaltımı (preoperatif): IM: anesteziden 30-60 dk önce veya preanestezik opioid/sedatif ile birlikte 4 mcg/kg\n• Bradikardi, vagal refleks reversalı (intraoperatif): IV: tek doz 0,1 mg; gerektikçe 2-3 dakika aralıkla tekrar\n• Kolinerjik ajanların muskarinik etkilerinin reversalı: IV: her 1 mg neostigmin veya 5 mg piridostigmin için 0,2 mg\n• Yaşam sonu sekresyon azaltımı (off-label): IV, SUBQ: gerektikçe her 4-8 saatte 0,1-0,2 mg (≤4 doz/gün) veya 0,1-0,2 mg ardından 24 saatte 0,6-1,2 mg sürekli infüzyon\n• Primer fokal hiperhidroz (off-label): Oral: günde 1-2 kez 1-2 mg", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Sekresyon azaltımı (preoperatif): Bebek/Çocuk ≤2 yaş: IM: işlemden 30-60 dk önce tek doz 4-9 mcg/kg; Çocuk >2 yaş ve Adölesan: IM: tek doz 4 mcg/kg\n• Bradikardi, vagal refleks reversalı (intraoperatif): Bebek, Çocuk, Adölesan: IV: 4 mcg/kg/doz (maks 100 mcg/doz) 2-3 dakika aralıkla\n• Kolinerjik ajanların muskarinik etkilerinin reversalı: IV: her 1 mg neostigmin veya 5 mg piridostigmin için 0,2 mg\n• Hızlı seri entübasyon ön tedavi: IV/Intraosseöz: 0,005-0,01 mg/kg; maksimum 0,2 mg/doz\n• Kronik salya akması: Çocuk ≥3 yaş ve Adölesan ≤16 yaş: Oral solüsyon: başlangıç 20 mcg/kg/doz günde 3 kez", null)),
            specialPopulations = mapOf(
                "geriatri" to "Kan-beyin bariyerini geçmediği için yaşlılarda atropine kıyasla konfüzyon/deliryum riski son derece düşüktür."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IM 15-30 dakika; IV 1 dakika içinde",
                peak = "IM ~30-45 dakika içinde",
                duration = "Vagal etki 2-3 saat; salivasyon inhibisyonu 7 saate kadar; parenteral 7 saat",
                halfLife = "IM erişkin 0,55-1,25 saat; IV 0,83 ± 0,27 saat; oral solüsyon 3 saat",
                elimination = "belirtilmemiş (Eliminasyon yeri: İdrar (değişmemiş ilaç, IM >%80, IV %85); safra (<%5))"
            ),
            contraindications = listOf(
                "Dar açılı glokom",
                "Şiddetli ülseratif kolit, toksik megakolon"
            ),
            warnings = listOf(
                "Kardiyovasküler hastalıkta dikkat (koroner arter hastalığı, aritmi, kalp yetmezliği, hipertansiyon, taşikardi); uygulama öncesi taşikardiyi değerlendir",
                "Hepatik/renal yetmezlikte doz azaltımı düşünülmeli; renal yetmezlikte eliminasyon ciddi bozulur",
                "Prostat hiperplazisi/mesane boynu obstrüksiyonunda dikkat (idrar retansiyonu)",
                "Ağır ülseratif kolitte kontrendike; ileus/toksik megakolon riski",
                "Yaşlılarda antikolinerjik etki, konfüzyon, halüsinasyon riski",
                "Bazı formlarda benzil alkol/propilen glikol; neonatlarda dikkat"
            ),
            sideEffectsCommon = listOf(
                "Kızarma (%30)",
                "konstipasyon (%35), kusma (%40), ağız kuruluğu (%40)",
                "idrar retansiyonu (%15)",
                "baş ağrısı (%15) (oral kullanımda çocuk/adölesanlarda bildirilen insidanslar)"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler hastalıkta dikkat (koroner arter hastalığı, aritmi, kalp yetmezliği, hipertansiyon, taşikardi); uygulama öncesi taşikardiyi değerlendir",
                "Hepatik/renal yetmezlikte doz azaltımı düşünülmeli; renal yetmezlikte eliminasyon ciddi bozulur",
                "Prostat hiperplazisi/mesane boynu obstrüksiyonunda dikkat (idrar retansiyonu)",
                "Ağır ülseratif kolitte kontrendike; ileus/toksik megakolon riski",
                "Yaşlılarda antikolinerjik etki, konfüzyon, halüsinasyon riski",
                "Pediatride paradoksal hiperexitabilite olabilir; bebek/Down sendromu/spastik paralizide artmış yanıt",
                "Bazı formlarda benzil alkol/propilen glikol; neonatlarda dikkat"
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
            drugId = "naloxone",
            nameTr = "Nalokson",
            nameEn = "Naloxone",
            genericName = "Naloxone Hydrochloride",
            brandNames = listOf(
                "Nalokson",
                "Narcan"
            ),
            category = "crisis",
            atcCode = "V03AB15",
            drugClass = "Saf Opioid Reseptör Antagonisti",
            mechanismTr = "Mu, kappa ve delta opioid reseptörlerine yüksek afinite ile bağlanarak agonist etki göstermeden opioidlerin tüm etkilerini yarışmalı olarak bloke eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Opioid reversalı, yaşamı tehdit eden aşırı doz: IV (tercih edilen): başlangıç 0,04-2 mg; gerektikçe her 2-3 dakikada artan dozlarla tekrar; ≥1 doz gerekmesi sık. 10 mg kümülatif doza yanıt yoksa başka nedenleri düşün\n• Sürekli infüzyon (off-label): IV: başlangıç etkin bolusun 2/3'ü saatte (olağan doz 0,25-6,25 mg/saat)\n• IM, SUBQ (IV yoksa): Nalokson (0,4 mg/mL): başlangıç 0,04-2 mg; her 2-3 dakikada artan dozlarla tekrar\n• Klonidin toksisitesi (off-label): IV: başlangıç 0,4-2 mg; gerektikçe tekrar (14 mg'a kadar bildirilmiş)", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Opioid intoksikasyon/aşırı doz (tam reversal): IV (tercih edilen)/Intraosseöz: Bebek/Çocuk <5 yaş veya ≤20 kg: 0,1 mg/kg/doz; gerektikçe her 2-3 dakikada tekrar; Çocuk ≥5 yaş veya >20 kg ve Adölesan: 2 mg/doz; yanıt yoksa her 2-3 dakikada tekrar\n• IM, SUBQ: çözelti: Bebek, Çocuk, Adölesan: 0,1 mg/kg/doz (maks 2 mg/doz); her 2-3 dakikada tekrar. Prefilled 5 mg (Zimhi): 5 mg tek doz\n• Endotrakeal: IV dozun 2-3 katı", null)),
            specialPopulations = mapOf(
                "opioid_bagimlisi" to "Fiziksel bağımlılığı olan hastalarda aniden yüksek doz verilmesi şiddetli akut yoksunluk sendromunu (ajitasyon, hipertansiyon, taşikardi, kusma) tetikler."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV ~2 dakika; IM, SUBQ 2-5 dakika; intranazal ~3-17 dakika",
                peak = "belirtilmemiş",
                duration = "~30-120 dakika (yola bağlı); IV süresi IM'den kısa; çoğu opioidden kısa olduğu için tekrar dozlar gerekir",
                halfLife = "Erişkin IM/IV/SUBQ 0,5-1,5 saat; intranazal ~1,1-2 saat; neonat ortalama 3,1 saat",
                elimination = "Primer hepatik glukuronidasyon (Eliminasyon yeri: İdrar (metabolitler olarak))"
            ),
            contraindications = listOf(
                "Naloksona karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Akut opioid çekilmesi: Opioid bağımlılarında akut çekilmeyi tetikleyebilir (ağrı, taşikardi, hipertansiyon, ateş, bulantı, ajitasyon); neonatlarda yaşamı tehdit edebilir. Postoperatif gibi durumlarda dozu dikkatle titre et, hastayı tam uyandırma",
                "Opioid aşırı doz semptomlarının nüksü (re-narkotizasyon): Opioid uzun etkiliyse solunum ve/veya CNS depresyonu nüksedebilir; nüks riski geçene kadar sürekli izle",
                "Kombatiflik: Resüsitasyonda ajite/saldırgan olabilirler (fentanil kullananlarda risk artar)",
                "Kardiyovasküler hastalıkta dikkat; ani reversalla pulmoner ödem ve KV instabilite (VF dahil) bildirilmiş",
                "Nöbet öyküsünde dikkat; meperidin nöbetlerinde kaçın"
            ),
            sideEffectsCommon = listOf(
                "Kızarma, hipertansiyon, hipotansiyon, taşikardi, ventriküler fibrilasyon/taşikardi",
                "diaforez",
                "bulantı, kusma, abdominal kramp",
                "ajitasyon, konfüzyon, baş ağrısı, nöbet, titreme, çekilme sendromu",
                "pulmoner ödem",
                "dispne. Postmarketing: bradikardi, agresif davranış, anksiyete"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Akut opioid çekilmesi: Opioid bağımlılarında akut çekilmeyi tetikleyebilir (ağrı, taşikardi, hipertansiyon, ateş, bulantı, ajitasyon); neonatlarda yaşamı tehdit edebilir. Postoperatif gibi durumlarda dozu dikkatle titre et, hastayı tam uyandırma",
                "Opioid aşırı doz semptomlarının nüksü (re-narkotizasyon): Opioid uzun etkiliyse solunum ve/veya CNS depresyonu nüksedebilir; nüks riski geçene kadar sürekli izle",
                "Kombatiflik: Resüsitasyonda ajite/saldırgan olabilirler (fentanil kullananlarda risk artar)",
                "Kardiyovasküler hastalıkta dikkat; ani reversalla pulmoner ödem ve KV instabilite (VF dahil) bildirilmiş",
                "Nöbet öyküsünde dikkat; meperidin nöbetlerinde kaçın"
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
            drugId = "flumazenil",
            nameTr = "Flumazenil",
            nameEn = "Flumazenil",
            genericName = "Flumazenil",
            brandNames = emptyList(),
            category = "reversal",
            atcCode = null,
            drugClass = "Benzodiazepin Reseptör Antagonisti (Reversal)",
            mechanismTr = "GABA-A reseptörü benzodiazepin bağlanma bölgesinde kompetitif antagonisttir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Benzodiazepin reversalı (prosedürel sedasyon veya genel anestezi): IV: başlangıç 2 dakikada 0,2 mg; istenen bilinç düzeyine 1 dakika sonra ulaşılmazsa 0,2 mg 1 dakika aralıklarla 4 kez tekrarlanabilir; olağan kümülatif aralık 0,6-1 mg; maksimum kümülatif doz 1 mg\n• Benzodiazepin aşırı dozu: IV: başlangıç 2 dakikada 0,2 mg; ardından 3 dakikada 0,3 mg, sonra 5 dakikada 0,5 mg ve 1 dakika aralıklarla tekrar; olağan kümülatif aralık 1-3 mg; maksimum 3 mg (nadiren 5 mg'a kadar)\n• Resedasyon: IV: 0,2-1 mg dozlar (0,1 mg/dakika hızında) 20 dakika aralıklarla; maksimum kümülatif resedasyon dozu 1 saatte 3 mg", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Benzodiazepin reversalı (prosedürel sedasyon veya genel anestezi): Bebek, Çocuk, Adölesan: IV: başlangıç 0,01 mg/kg (maks 0,2 mg/doz); 15 saniyede verilir; gerekirse 45 saniye sonra ve sonra her dakika tekrar; maksimum kümülatif doz 0,05 mg/kg veya 1 mg (hangisi azsa); olağan total 0,08-1 mg\n• Benzodiazepin intoksikasyon/aşırı doz (sınırlı veri): IV: başlangıç 0,01 mg/kg (maks 0,2 mg/doz); gerekirse her dakika tekrar; maksimum kümülatif 1 mg. Sürekli IV infüzyon: 0,005-0,01 mg/kg/saat", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "1-2 dakika; 3 dakika içinde %80 yanıt",
                peak = "6-10 dakika",
                duration = "Resedasyon ~1 saat sonra olabilir (aralık 19-50 dakika); reversal etkisi benzodiazepinden önce geçebilir",
                halfLife = "Erişkin alfa 4-11 dakika; terminal 40-80 dakika; Çocuk terminal 20-75 dakika",
                elimination = "Hepatik; hepatik kan akımına bağımlı (Eliminasyon yeri: Feçes; idrar (<%1 değişmemiş ilaç))"
            ),
            contraindications = listOf(
                "Trisiklik antidepresan aşırı dozu veya zehirlenmesi",
                "Kafa içi basınç artışının olduğu durumlar"
            ),
            warnings = listOf(
                "[US Boxed Warning]: Benzodiazepin reversalı nöbete yol açabilir; uzun süreli sedasyon alanlarda veya trisiklik antidepresan aşırı dozunu izleyen durumlarda daha sık; doz bireyselleştirilmeli, nöbet yönetimine hazır olunmalı",
                "Resedasyon: Büyük tek doz/kümülatif benzodiazepin ve NMBA + çoklu anestezikle daha sık; resedasyon riski geçene kadar izle",
                "Benzodiazepin aşırı dozunda rutin kullanım tartışmalı; prokonvülzan/proaritmik ko-ingestion, fiziksel bağımlılık veya nöbet bozukluğu olanlarda risk",
                "Kafa yaralanmasında dikkat (konvülziyon tetikleyebilir); ilaç/alkol bağımlılarında dikkat"
            ),
            sideEffectsCommon = listOf(
                "Kusma (%11)",
                "kızarma, çarpıntı, vazodilatasyon",
                "diaforez",
                "bulantı, ağız kuruluğu",
                "ajitasyon, anksiyete, ataksi (%10), baş dönmesi (%10), insomni, sinirlilik, tremor, vertigo (%10)",
                "bulanık görme",
                "dispne, hiperventilasyon",
                "enjeksiyon yeri reaksiyonu. <%1: bradikardi, nöbet, titreme"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "[US Boxed Warning]: Benzodiazepin reversalı nöbete yol açabilir; uzun süreli sedasyon alanlarda veya trisiklik antidepresan aşırı dozunu izleyen durumlarda daha sık; doz bireyselleştirilmeli, nöbet yönetimine hazır olunmalı",
                "Resedasyon: Büyük tek doz/kümülatif benzodiazepin ve NMBA + çoklu anestezikle daha sık; resedasyon riski geçene kadar izle",
                "Solunum depresyonunu reverse etmek için güvenme; oksijenasyon değerlendirmesinin yerini tutmaz",
                "Benzodiazepin aşırı dozunda rutin kullanım tartışmalı; prokonvülzan/proaritmik ko-ingestion, fiziksel bağımlılık veya nöbet bozukluğu olanlarda risk",
                "Kafa yaralanmasında dikkat (konvülziyon tetikleyebilir); ilaç/alkol bağımlılarında dikkat"
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
            drugId = "ephedrine",
            nameTr = "Efedrin",
            nameEn = "Ephedrine",
            genericName = "Ephedrine Sulfate",
            brandNames = listOf(
                "Ephedrine",
                "Efedrin Ampul"
            ),
            category = "vasopressor",
            atcCode = "C01CA24",
            drugClass = "Sempatomimetik (Alfa ve Beta Adrenerjik Agonist)",
            mechanismTr = "Hem alfa hem beta reseptörleri uyarır. Esas olarak endojen noradrenalin salınımını tetikleyerek (indirekt etki) sempatik aktiviteyi artırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezi kaynaklı hipotansiyon (efedrin sülfat): IV: Başlangıç 5-10 mg; KB'yi korumak için gerektikçe tekrarla (maks kümülatif doz: 50 mg)\n• Anestezi kaynaklı hipotansiyon (efedrin hidroklorür): IV: Başlangıç 4.7-9.4 mg; gerektikçe tekrarla (maks kümülatif doz: 47 mg)\n• Postoperatif bulantı-kusma (önleme, endikasyon dışı): IM: cerrahi sonunda 0.5 mg/kg\n• Astım (OTC, oral): 12.5-25 mg her 4 saatte bir gerektikçe; maks 150 mg/24 saat", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Anestezi kaynaklı hipotansiyon (bebek, çocuk, ergen): Yavaş IV puşe: 0.1-0.3 mg/kg/doz; en düşük etkili dozu kullan; olağan erişkin doz aralığı 5-10 mg/doz; KB'yi korumak için gerektikçe tekrarla; maks toplam doz: 50 mg\n• Astım (≥12 yaş, OTC oral): 12.5-25 mg her 4 saatte bir; maks günlük doz 150 mg/24 saat", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IM: 10-20 dakika içinde",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "idrar pH'ına bağlı; pH 5: ~3 saat, pH 6.3: ~6 saat",
                elimination = "Minimal hepatik (p-hidroksiefedrin, p-hidroksinorefedrin, norefedrin) (Eliminasyon yeri: İdrar (esas olarak değişmemiş; idrar pH'ına bağlı))"
            ),
            contraindications = listOf(
                "Şiddetli koroner arter hastalığı, taşiaritmi varlığı",
                "Dar açılı glokom"
            ),
            warnings = listOf(
                "Kardiyovasküler etkiler: Profilaktik kullanımda hipertansiyona yol açabilir (yalnızca hipotansiyon tedavisinde endike)",
                "Renal yetmezlikte dikkatli kullan; eliminasyon yarı ömrü uzayabilir",
                "Yaşlılarda dikkatli kullan"
            ),
            sideEffectsCommon = listOf(
                "Bradikardi, reaktif hipertansiyon, düzensiz nabız, palpitasyon, taşikardi, ventriküler ektopi",
                "bulantı, kusma",
                "baş dönmesi, huzursuzluk",
                "taşiflaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler etkiler: Profilaktik kullanımda hipertansiyona yol açabilir (yalnızca hipotansiyon tedavisinde endike)",
                "Renal yetmezlikte dikkatli kullan; eliminasyon yarı ömrü uzayabilir",
                "Yaşlılarda dikkatli kullan",
                "Taşiflaksi/tolerans: Tekrarlı, uzamış veya aşırı kullanımda gelişebilir; tedaviye geçici ara verilmesi etkinliği geri kazandırır"
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
            drugId = "phenylephrine",
            nameTr = "Fenilefrin",
            nameEn = "Phenylephrine",
            genericName = "Phenylephrine Hydrochloride",
            brandNames = listOf(
                "Phenylephrine",
                "Neo-Synephrine"
            ),
            category = "vasopressor",
            atcCode = "C01CA06",
            drugClass = "Selektif Alfa-1 Adrenerjik Agonist",
            mechanismTr = "Saf alfa-1 reseptör agonistidir. Periferik vazokonstrüksiyon yaparak sistemik vasküler direnci ve kan basıncını yükseltir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezi sırasında hipotansiyon - IV bolus: Başlangıç 40-100 mcg/doz; gerektikçe her 1-2 dakikada tekrarla; maks toplam doz: 200 mcg\n• Anestezi sırasında hipotansiyon - İnfüzyon: IV: Başlangıç 10-35 mcg/dakika; maks doz: 200 mcg/dakika\n• Septik/vazodilatör şok (kiloya göre): İnfüzyon IV: Başlangıç 0.5-2 mcg/kg/dakika; olağan aralık 0.25-5 mcg/kg/dakika\n• Septik şok (kiloya göre değil, ~80 kg): İnfüzyon IV: Başlangıç 40-160 mcg/dakika; olağan aralık 20-400 mcg/dakika\n• Kardiyojenik şok: İnfüzyon IV: 0.1-10 mcg/kg/dakika", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Hipotansiyon / düşük kalp debisi (bebek, çocuk, ergen): IM, SUBQ: 100 mcg/kg/doz her 1-2 saatte bir; maks 5.000 mcg/doz\n• IV bolus: 5-20 mcg/kg/doz her 10-15 dakikada; başlangıç doz 500 mcg/doz'u aşmamalı\n• İnfüzyon IV: Başlangıç 0.1-0.5 mcg/kg/dakika; şokta 2 mcg/kg/dakika'ya kadar; tet spazmlarında 5 mcg/kg/dakika'ya kadar", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "KB artışı/vazokonstriksiyon: IM, SubQ 10-15 dakika; IV: Hemen",
                peak = "Oral 0.75-2 saat",
                duration = "IM 1-2 saat; IV ~15-20 dakika; SubQ 50 dakika",
                halfLife = "Alfa faz ~5 dakika; terminal faz 2-3 saat",
                elimination = "Hepatik (oksidatif deaminasyon, sülfasyon, glukuronidasyon); inaktif metabolitler (Eliminasyon yeri: İdrar (çoğunlukla inaktif metabolit olarak))"
            ),
            contraindications = emptyList(),
            warnings = listOf(
                "Kardiyovasküler: Şiddetli bradikardi (baroreflex) ve kalp debisinde azalmaya yol açabilir; ciddi KAH'ta anjina tetikleyebilir; kalp yetmezliği veya kardiyojenik şokta kaçın/aşırı dikkatle kullan; hipertansiyonda kaçın (şiddetli HT'de kontrendike)",
                "MAO inhibitörleri ile çok dikkatli kullan; hipertansiyon oluşabilir"
            ),
            sideEffectsCommon = listOf(
                "AV blok, bradikardi (refleks), aritmi, hipertansiyon/hipertansif kriz, iskemi, düşük kalp debisi",
                "baş ağrısı, serebrovasküler olay, parestezi, tremor",
                "lokal solukluk",
                "pulmoner ödem",
                "sülfit duyarlılığı/anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler: Şiddetli bradikardi (baroreflex) ve kalp debisinde azalmaya yol açabilir; ciddi KAH'ta anjina tetikleyebilir; kalp yetmezliği veya kardiyojenik şokta kaçın/aşırı dikkatle kullan; hipertansiyonda kaçın (şiddetli HT'de kontrendike)",
                "Ekstravazasyon: Vezikan; uygun iğne/kateter yerleşimini sağla, ekstravazasyondan kaçın",
                "Asidoz etkinliği azaltır; kullanım sırasında asidozu düzelt",
                "Otonom disfonksiyonda abartılı KB yanıtı görülebilir",
                "MAO inhibitörleri ile çok dikkatli kullan; hipertansiyon oluşabilir"
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
            drugId = "norepinephrine",
            nameTr = "Norepinefrin (Noradrenalin)",
            nameEn = "Norepinephrine (Noradrenaline)",
            genericName = "Norepinephrine Bitartrate",
            brandNames = listOf(
                "Levophed",
                "Noralin"
            ),
            category = "vasopressor",
            atcCode = "C01CA03",
            drugClass = "Güçlü Alfa ve Zayıf Beta-1 Agonist",
            mechanismTr = "Güçlü alfa-1 uyarısıyla periferik vazokonstrüksiyon sağlarken, hafif beta-1 uyarısıyla miyokard kontraktilitesini ve kalp debisini destekler.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Hipotansiyon/şok (kiloya göre): İnfüzyon IV: Başlangıç 0.05-0.15 mcg/kg/dakika; olağan aralık 0.025-1 mcg/kg/dakika; refrakter şokta nadiren 1-~3 mcg/kg/dakika'ya kadar\n• Hipotansiyon/şok (kiloya göre değil): IV: Başlangıç 5-15 mcg/dakika; olağan aralık 2-80 mcg/dakika; refrakter şokta 80-250 mcg/dakika'ya kadar\n• Kardiyojenik şok (kiloya göre): IV: Başlangıç 0.05 mcg/kg/dakika; olağan aralık 0.05-0.4 mcg/kg/dakika\n• Septik şok (kiloya göre): IV: Başlangıç 0.05-0.15 mcg/kg/dakika; olağan aralık 0.025-1 mcg/kg/dakika", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Hipotansiyon/şok (sıvıya dirençli; bebek, çocuk, ergen): Sürekli IV veya intraosseöz infüzyon: Başlangıç 0.05-0.1 mcg/kg/dakika; istenen etkiye titre et; olağan maks doz: 2 mcg/kg/dakika", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Çok hızlı etkili",
                peak = "Kararlı durum: 5 dakika",
                duration = "Vazopresör: 1-2 dakika",
                halfLife = "Ortalama ~2.4 dakika",
                elimination = "COMT ve MAO yoluyla (Eliminasyon yeri: İdrar (inaktif metabolit olarak; az miktarda değişmemiş ilaç))"
            ),
            contraindications = listOf(
                "Ciddi hipovolemi varlığı (öncelikle sıvı açığı kapatılmalıdır)"
            ),
            warnings = listOf(
                "Profound hipoksi/hiperkarbide ventriküler taşikardi/fibrilasyon yapabilir; aşırı dikkatle kullan",
                "Sodyum metabisülfit içerebilir; astım/sülfit alerjisinde dikkat",
                "Ani kesme: İnfüzyonu kademeli azalt, eş zamanlı IV sıvı ver; ani kesilmede şiddetli hipotansiyon olur"
            ),
            sideEffectsCommon = listOf(
                "Bradikardi, aritmi, stres kardiyomiyopatisi, periferik vasküler yetersizlik",
                "anksiyete, geçici baş ağrısı",
                "dispne",
                "periferik gangren ve iskemi (dijital)."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ekstravazasyon: Vezikan; mümkünse büyük vene infüze et, bacak venlerinden kaçın; ekstravazasyon olursa seyreltilmiş fentolamin (erişkinde 10 mL salinde 5-10 mg) ile infiltre et",
                "Hipovolemiyi tedavi öncesi düzelt; aksi halde şiddetli vazokonstriksiyon, renal perfüzyon azalması",
                "Profound hipoksi/hiperkarbide ventriküler taşikardi/fibrilasyon yapabilir; aşırı dikkatle kullan",
                "Sodyum metabisülfit içerebilir; astım/sülfit alerjisinde dikkat",
                "Ani kesme: İnfüzyonu kademeli azalt, eş zamanlı IV sıvı ver; ani kesilmede şiddetli hipotansiyon olur"
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
            drugId = "adrenaline",
            nameTr = "Epinefrin (Adrenalin)",
            nameEn = "Epinephrine (Adrenaline)",
            genericName = "Epinephrine Hydrochloride",
            brandNames = listOf(
                "Adrenalin Biofarma",
                "Epinephrine"
            ),
            category = "vasopressor",
            atcCode = "C01CA24",
            drugClass = "Sempatomimetik (Direkt Alfa ve Beta Adrenerjik Agonist)",
            mechanismTr = "Alfa-1 (vazokonstrüksiyon), Beta-1 (pozitif inotrop/kronotrop) ve Beta-2 (bronkodilasyon) reseptörlerini güçlü şekilde uyarır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anafilaksi: IM: 0.3 veya 0.5 mg (>50 kg hastada 0.5 mg) 1 mg/mL solüsyonla; gerektikçe ~5-15 dakikada tekrarla\n• Anafilaksi (refrakter, infüzyon, endikasyon dışı): IV: Başlangıç 0.1-0.2 mcg/kg/dakika; olağan aralık 0.01-0.2 mcg/kg/dakika\n• Yavaş IV bolus (endikasyon dışı): 0.05-0.1 mg (0.1 mg/mL solüsyon, 10 mL NS'de seyreltilmiş) 1-10 dakikada\n• Otoenjektör (öz-tedavi): IM/SUBQ: 0.3 mg; gerektikçe ~5-15 dakikada tekrarla\n• Nazal sprey: 1 sprey (2 mg) bir burun deliğine; yetersizse 5 dakika sonra aynı deliğe ikinci doz\n• Akut şiddetli astım (endikasyon dışı): IM/SUBQ: 0.3-0.5 mg (1 mg/mL); gerekirse 20 dakikada bir, toplam 3 doz", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Asistoli/nabızsız arrest (bebek, çocuk, ergen): IV/Intraosseöz: 0.01 mg/kg (0.1 mL/kg 0.1 mg/mL solüsyon); maks 1 mg/doz; her 3-5 dakikada tekrarla. Endotrakeal: 0.1 mg/kg (0.1 mL/kg 1 mg/mL); maks 2.5 mg/doz\n• Bradikardi: IV/Intraosseöz: 0.01 mg/kg (maks 1 mg/doz); her 3-5 dakikada tekrarla\n• Kalp debisi artışı/post-resüsitasyon: Sürekli IV/intraosseöz infüzyon: 0.05-1 mcg/kg/dakika (<0.3 beta, >0.3 alfa etkileri)", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Bronkodilatasyon: SUBQ ~5-10 dakika",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "IV <5 dakika",
                elimination = "Adrenerjik nörona alınır, MAO ve COMT ile metabolize; dolaşımdaki ilaç hepatik (Eliminasyon yeri: İdrar (inaktif metabolitler, metanefrin; az miktar değişmemiş))"
            ),
            contraindications = listOf(
                "Mutlak kontrendikasyonu yoktur (kardiyak arrest ve şiddetli anafilaksi durumunda)"
            ),
            warnings = listOf(
                "Kardiyak etkiler: Anjina pektorisi tetikleyebilir veya aritmi indükleyebilir; kardiyak hastalıkta dikkatli kullan",
                "Periferik konstriksiyon ve kardiyak uyarıya bağlı pulmoner ödem; renal damar konstriksiyonu ile idrar çıkışında azalma olabilir",
                "Kardiyovasküler hastalık, diyabet, feokromositoma, tiroid hastalığında dikkatli kullan"
            ),
            sideEffectsCommon = listOf(
                "Akut MI, anjina, aritmi, stres kardiyomiyopatisi, hipertansiyon, ventriküler fibrilasyon",
                "hiperglisemi, hipokalemi, laktik asidoz",
                "anksiyete, serebral hemoraji, tremor",
                "ekstremite iskemisi",
                "pulmoner ödem",
                "enjeksiyon yerinde doku nekrozu. Nazal sprey: baş ağrısı, nazal konjesyon, parestezi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyak etkiler: Anjina pektorisi tetikleyebilir veya aritmi indükleyebilir; kardiyak hastalıkta dikkatli kullan",
                "Ekstravazasyon: IV uygulamada vezikan; ekstravazasyondan kaçın",
                "Periferik konstriksiyon ve kardiyak uyarıya bağlı pulmoner ödem; renal damar konstriksiyonu ile idrar çıkışında azalma olabilir",
                "Kardiyovasküler hastalık, diyabet, feokromositoma, tiroid hastalığında dikkatli kullan",
                "Vazopresör vermeden önce kan hacmi açığını düzelt",
                "Kazara dijital/el/ayak enjeksiyonu lokal iskemiye yol açabilir; acil tıbbi yardım gerekir"
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
