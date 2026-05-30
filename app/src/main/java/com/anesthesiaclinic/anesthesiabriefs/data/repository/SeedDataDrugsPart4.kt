package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo

object SeedDataDrugsPart4 {
    val drugsList = listOf(
        Drug(
            drugId = "salbutamol",
            nameTr = "Albuterol (Salbutamol)",
            nameEn = "Albuterol (Salbutamol)",
            genericName = "Salbutamol Sulfate",
            brandNames = listOf(
                "Ventolin"
            ),
            category = "crisis",
            atcCode = "R03AC02",
            drugClass = "Selektif Kısa Etkili Beta-2 Adrenerjik Agonist (SABA)",
            mechanismTr = "Bronş düz kaslarındaki beta-2 adrenerjik reseptörleri seçici olarak uyararak adenilat siklazı aktive eder, cAMP artışı ile güçlü bronkodilasyon sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Astım akut atak (hafif-orta, ev): MDI/DPI (90 mcg/püskürtme): inhalasyon yoluyla 20 dk arayla 2-4 inhalasyon, 3 doz; iyi yanıtta aralık 3-4 saate uzatılır. Nebülizasyon: 2.5 mg, 20 dk arayla 3 doz.\n• Astım akut atak (orta-ağır): MDI/DPI: 20 dk arayla 4-10 inhalasyon, 3 doz, sonra azaltılır. Nebülizasyon: 2.5-5 mg 20 dk arayla 3 doz; kritik hastada 1 saatte sürekli nebülizasyon ile 10-15 mg.\n• IV sürekli infüzyon [Kanada ürünü]: başlangıç 5 mcg/dk; gerekirse 15-30 dk aralarla 10-20 mcg/dk'ya artırılır.\n• Aralıklı semptom giderme: MDI/DPI: gerektikçe 4-6 saatte bir 2 inhalasyon. Nebülizasyon: gerektikçe 4-6 saatte 2.5 mg.\n• İdame (alternatif/ek): Şurup/tablet oral: başlangıç 2-4 mg, günde 3-4 kez; maksimum 32 mg/gün.\n• Egzersiz kaynaklı bronkokonstriksiyon önleme: MDI/DPI: egzersizden 5-20 dk önce 2 inhalasyon.\n• Anafilaksiye bağlı bronkospazm (epinefrine ek): MDI/DPI: gerektikçe 2-3 inhalasyon; mekanik ventilasyonda 8 inhalasyona kadar. Nebülizasyon: 2.5-5 mg, gerektikçe tekrar.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Astım, akut semptom giderme: MDI/DPI (90 mcg/püskürtme), Bebek/Çocuk/Adölesan: gerektikçe 4-6 saatte 2 inhalasyon.\n• Nebülizasyon: Bebek ve Çocuk ≤4 yaş: gerektikçe 4-6 saatte 0.63-2.5 mg; >4 to <12 yaş: 4-8 saatte 1.25-5 mg; ≥12 yaş ve adölesan: 6-8 saatte 2.5 mg (aralık 1.25-5 mg).", null)),
            specialPopulations = mapOf(
                "koroner_hastalik" to "Beta-2 seçici olsa da yüksek dozlarda beta-1 uyarımı yaparak ciddi taşikardi ve miyokard iskemisi yapabilir."
            ),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Nebülizasyon ≤5 dk; oral inhalasyon (DPI) 5.7 dk, (MDI) 5.4-8.2 dk; oral ≤30 dk",
                peak = "serum nebülizasyon 30 dk, DPI 30 dk, MDI 25 dk",
                duration = "nebülizasyon 3-6 saat; DPI ~2 saat, MDI ~4-6 saat; oral 6-8 saat",
                halfLife = "oral inhalasyon 3.8-~5 saat; oral 5-6 saat",
                elimination = "hepatik (inaktif sülfata) (Eliminasyon yeri: idrar (%80-100 inhalasyon); feçes (<%20))"
            ),
            contraindications = listOf(
                "Salbutamole karşı bilinen aşırı duyarlılık",
                "Şiddetli taşiaritmiler"
            ),
            warnings = listOf(
                "Kardiyovasküler hastalıkta (aritmi, koroner yetmezlik, HT, KY) dikkatli kullan; miyokardiyal toksisite/disfonksiyon yapabilir.",
                "Diyabette dikkat; serum glukozunu artırabilir.",
                "Hipokalemide dikkat; serum potasyumunu düşürebilir.",
                "Hipertiroidi, glokom, nöbet bozukluğunda dikkat.",
                "Önerilen dozu aşma; aşırı kullanımla ölümler dahil ciddi advers olaylar bildirilmiştir."
            ),
            sideEffectsCommon = listOf(
                "Tremor (%5-38), sinirlilik, eksitasyon, taşikardi, çarpıntı, baş ağrısı, bronkospazm, hipertansiyon",
                "hipokalemi, serum glukozunda artış."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Kardiyovasküler hastalıkta (aritmi, koroner yetmezlik, HT, KY) dikkatli kullan; miyokardiyal toksisite/disfonksiyon yapabilir.",
                "Diyabette dikkat; serum glukozunu artırabilir.",
                "Hipokalemide dikkat; serum potasyumunu düşürebilir.",
                "Hipertiroidi, glokom, nöbet bozukluğunda dikkat.",
                "Önerilen dozu aşma; aşırı kullanımla ölümler dahil ciddi advers olaylar bildirilmiştir.",
                "Akut semptomlar geçmezse tıbbi yardım al; artan kullanım astımın kötüleştiğini gösterebilir."
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
            drugId = "ipratropium",
            nameTr = "İpratropium",
            nameEn = "Ipratropium",
            genericName = "Ipratropium (oral inhalation)",
            brandNames = listOf(
                "Αtrovent HFA"
            ),
            category = "crisis",
            atcCode = null,
            drugClass = "Kısa Etkili Antikolinerjik (Bronkodilatör)",
            mechanismTr = "Solunum yollarındaki M3 reseptörlerini bloke ederek bronkodilasyon sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Astım, akut atak orta-ağır (endikasyon dışı): Nebülizasyon: inhalasyon 20 dk arayla 0.5 mg, 3 doz, sonra 3 saate kadar saatte bir. MDI (17 mcg/püskürtme): spacer ile 20 dk arayla 4-8 inhalasyon, 3 doz, sonra 3 saate kadar saatte bir.\n• KOAH akut atak (ev/ofis): MDI: gerektikçe 4-6 saatte 2 inhalasyon; maksimum ilk 12 saatte 12 inhalasyon. Nebülizasyon: gerektikçe 6-8 saatte 0.5 mg; maksimum ilk 12 saatte 6 doz (3 mg). Acil/hastane: MDI: 1-2 saatte 2-4 inhalasyon (3 doza kadar), sonra gerektikçe 2-4 saatte. Nebülizasyon: saatte 0.5 mg (3 doza kadar), sonra 2-4 saatte.\n• Aralıklı semptom giderme: MDI: günde 4-6 kez 2 inhalasyon. Nebülizasyon: gerektikçe 4-8 saatte 0.5 mg.\n• İdame (alternatif): MDI: 6 saatte 2 inhalasyon. Nebülizasyon: 6-8 saatte 0.5 mg.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Astım akut atak (sınırlı veri): Çocuklar: Nebülizasyon 20 dk arayla 0.25-0.5 mg, 1 saatte (3 doz); MDI 20 dk arayla 4-8 inhalasyon, 3 doza kadar. Adölesan: Nebülizasyon 20 dk arayla 0.5 mg, 3 doz; MDI 4-8 inhalasyon.\n• Kronik akciğer durumlarına bağlı bronkospazm: ≥12 yaş ve adölesan: Nebülizasyon 0.5 mg günde 3-4 kez (6-8 saat arayla).\n• Bronkospazm, hışıltı (bebek): Nebülizasyon 4 saatte 0.125-0.25 mg.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "bronkodilatasyon 15 dk içinde",
                peak = "1-2 saat",
                duration = "MDI 2-4 saat; nebülizasyon 4-5 saat (bazılarında 7-8 saate kadar)",
                halfLife = "2 saat",
                elimination = "kısmen inaktif ester hidroliz ürünlerine metabolize (Eliminasyon yeri: idrar (%50))"
            ),
            contraindications = listOf(
                "Bileşenlerine veya atropine karşı aşırı duyarlılık"
            ),
            warnings = listOf(
                "Paradoksik (yaşamı tehdit edebilen) bronkospazm gelişirse kes ve alternatif tedaviye geç.",
                "Baş dönmesi/bulanık görme yapabilir; dikkat gerektiren işlerde uyar.",
                "Dar açılı glokomda dikkat; göz içi basıncını artırabilir.",
                "Prostat hiperplazisi/mesane boynu obstrüksiyonunda dikkat; idrar retansiyonu yapabilir.",
                "Akut bronkospazmda tek başına kurtarıcı tedavi olarak endike değil."
            ),
            sideEffectsCommon = listOf(
                "Baş ağrısı, baş dönmesi, ağız kuruluğu, bulantı, dispepsi, bronşit/KOAH alevlenmesi, üst solunum yolu enfeksiyonu",
                "nadir anafilaksi, paradoksik bronkospazm, glokom."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Paradoksik (yaşamı tehdit edebilen) bronkospazm gelişirse kes ve alternatif tedaviye geç.",
                "Baş dönmesi/bulanık görme yapabilir; dikkat gerektiren işlerde uyar.",
                "Anafilaksi dahil aşırı duyarlılık reaksiyonlarında derhal kes.",
                "Dar açılı glokomda dikkat; göz içi basıncını artırabilir.",
                "Prostat hiperplazisi/mesane boynu obstrüksiyonunda dikkat; idrar retansiyonu yapabilir.",
                "Akut bronkospazmda tek başına kurtarıcı tedavi olarak endike değil."
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
            drugId = "magnesium_sulfate",
            nameTr = "Magnezyum sülfat",
            nameEn = "Magnesium sulfate",
            genericName = "Magnesium Sulfate Heptahydrate",
            brandNames = listOf(
                "Magnezyum Sülfat %15"
            ),
            category = "crisis",
            atcCode = "B05XA05",
            drugClass = "Doğal Kalsiyum Antagonisti ve NMDA Blokörü",
            mechanismTr = "Voltaj bağımlı kalsiyum kanallarını bloke ederek nörotransmitter salınımını ve kas kasılmasını azaltır. Uterus düz kasını gevşetir, membran stabilitesini artırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("Not: 1 g magnezyum sülfat = 98.6 mg elemental Mg = 8.12 mEq elemental Mg.\n• Astım/KOAH ağır akut atak (ek ajan, endikasyon dışı): IV: 20 dk içinde tek doz 2 g.\n• Konstipasyon (alternatif): Oral: 8 ons (240 mL) suda eritilmiş ~10-20 g granül; 4 saatte tekrar; günde 2 dozu aşma.\n• Eklampsi/preeklampsi nöbet profilaksi/tedavi: IV: yükleme 4-6 g, 15-30 dk; sonra 1-2 g/saat sürekli infüzyon, doğum sonrası en az 24 saat. Nöbet olursa ek 2-4 g bolus ≥5 dk; infüzyon max 3 g/saat. Max 40 g/24 saat. IM (%50): yükleme 10 g (her kalçaya 5 g), sonra 4 saatte 5 g.\n• Fetal nöroprotektif (endikasyon dışı): IV: yükleme 4 g, 20-30 dk; sonra 1 g/saat, max 24 saat veya doğuma kadar.\n• Hipomagnezemi tedavisi: Hafif (Mg 1.5-1.7 mg/dL): IV 1-2 g, 1-2 saatte; IM (%50) 6 saatte 1 g, 4 doz. Orta (Mg 1-1.4): IV 2-4 g, 2-12 saatte.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Hipomagnezemi: Bebek/Çocuk/Adölesan: IV, Intraosseöz: 6 saatte 25-50 mg/kg/doz, 2-3 doz; max 2000 mg/doz.\n• Astım ağır akut atak (ek): IV: tek doz 50 mg/kg (aralık 25-75 mg/kg); max 2000 mg/doz.\n• Parenteral nütrisyon (elemental Mg): ≤50 kg: IV 0.3-0.5 mEq/kg/gün; >50 kg: 10-30 mEq/gün.\n• Torsade de pointes/VF: IV, Intraosseöz: 25 mg/kg (kesilmiş).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "antikonvülzan IM 1 saat, IV anında; laksatif oral 0.5-6 saat",
                peak = "belirtilmemiş",
                duration = "antikonvülzan IM 3-4 saat, IV 30 dk",
                halfLife = "belirtilmemiş",
                elimination = "metabolize olmaz (Eliminasyon yeri: idrar (Mg olarak); feçes (emilmeyen ilaç))"
            ),
            contraindications = listOf(
                "Myasthenia Gravis",
                "İleri derece kalp blokları",
                "Şiddetli renal yetmezlik (diyaliz gerektiren)"
            ),
            warnings = listOf(
                "Myastenia gravis/nöromusküler hastalıkta aşırı dikkatle kullan.",
                "Renal yetmezlikte dikkat; Mg birikip intoksikasyona yol açabilir.",
                "Obstetrikte dikkatli izlem; 5-7 günden uzun kullanım advers fetal olaylara yol açabilir."
            ),
            sideEffectsCommon = listOf(
                "Kızarma (IV, doza bağlı), hipotansiyon (IV, hıza bağlı), vazodilatasyon, hipermagnezemi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Myastenia gravis/nöromusküler hastalıkta aşırı dikkatle kullan.",
                "Renal yetmezlikte dikkat; Mg birikip intoksikasyona yol açabilir.",
                "Obstetrikte dikkatli izlem; 5-7 günden uzun kullanım advers fetal olaylara yol açabilir.",
                "Magnezyum toksisitesi fatal kardiyovasküler arrest ve/veya solunum paralizisine yol açabilir; kalsiyum glukonat hazır bulundurulmalı.",
                "Eşlik eden hipokalemi/hipokalsemi olabilir; potasyumu normalize etmek için hipomagnezemi düzeltilmeli."
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
            drugId = "calcium_gluconate",
            nameTr = "Kalsiyum glukonat",
            nameEn = "Calcium gluconate",
            genericName = "Calcium Gluconate / Calcium Chloride",
            brandNames = listOf(
                "Kalsiyum Sandoz",
                "Calcium Gluconate %10"
            ),
            category = "crisis",
            atcCode = "B05XA07",
            drugClass = "Elektrolit / Kalsiyum Replasmanı",
            mechanismTr = "Hücre dışı kalsiyum konsantrasyonunu artırarak miyokard uyarılabilirliğini, iletimini ve kontraktilitesini düzenler. Magnezyum ve potasyumun kardiyak toksik etkilerini antagonize eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("Not: 1 g kalsiyum glukonat = 93 mg elemental kalsiyum = 4.65 mEq; %10 solüsyon = 0.465 mEq (9.3 mg) elemental Ca/mL.\n• Beta-bloker toksisitesi (endikasyon dışı): IV: başlangıç 3-6 g (30-60 mL %10) veya 60 mg/kg, 5-10 dk; 10-20 dk arayla 3-4 ek doz veya 60-120 mg/kg/saat sürekli infüzyon.\n• Kalsiyum kanal bloker toksisitesi (endikasyon dışı): IV: başlangıç 3-6 g (30-60 mL %10) veya 60 mg/kg, 5-10 dk; tekrar veya 60-120 mg/kg/saat infüzyon. İyonize Ca'yı ÜLN'nin 1.5-2 katında tut.\n• CRRT sitrat antikoagülasyonunda Ca replasmanı (endikasyon dışı): sürekli infüzyon, sistemik iyonize Ca ~3.6-5.2 mg/dL hedefli.\n• Hidroflorik asit maruziyeti (endikasyon dışı): IV: 2 g hemen; gerekirse ek 1 g sonra 4 g 1 saatte. Topikal %2.5 jel; SUBQ %5-10 solüsyon 0.5 mL/cm² (asla kalsiyum klorür SUBQ kullanma).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Not: 1 g kalsiyum glukonat = 93 mg elemental kalsiyum = 4.65 mEq.\n• Kalsiyum kanal bloker toksisitesi: Bebek/Çocuk/Adölesan: IV 60 mg/kg/doz, 30-60 dk; max 3000-6000 mg/doz; hızlı uygulama (5-10 dk) ve 10-20 dk arayla 3-4 doz tekrar önerilir.\n• Hidroflorik asit yanığı (sınırlı veri): SUBQ %5-10 solüsyon 0.5 mL/cm² (asla kalsiyum klorür SUBQ kullanma).\n• Hiperkalemi (ek tedavi): elemental Ca olarak doz (kesilmiş).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "belirtilmemiş (Eliminasyon yeri: başlıca feçes (%75 emilmeyen Ca tuzları); idrar (%20))"
            ),
            contraindications = listOf(
                "Hiperkalsemi varlığı",
                "Dijital (Digoksin) toksisitesi"
            ),
            warnings = listOf(
                "Çok hızlı IV uygulamadan kaçın (erişkin max 200 mg/dk, pediatrik 100 mg/dk); hipotansiyon, bradikardi, aritmi, kardiyak arrest.",
                "Şiddetli hiperfosfatemide dikkat (Ca-fosfat çökelmesi).",
                "Şiddetli hipokalemide dikkat; ani serum Ca artışı yaşamı tehdit eden aritmi yapabilir.",
                "Tuz formuna dikkat; yanlış seçim ciddi doz hatasına yol açar."
            ),
            sideEffectsCommon = listOf(
                "Bradikardi, kardiyak aritmi, hipotansiyon, senkop, vazodilatasyon",
                "enjeksiyon yerinde inflamasyon ve doku nekrozu",
                "kutanöz kalsifikasyon."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ekstravazasyon: parenteral kalsiyum vezikandır; ekstravazasyondan kaçın (derin doku nekrozu); IV bölgeyi yakın izle.",
                "Çok hızlı IV uygulamadan kaçın (erişkin max 200 mg/dk, pediatrik 100 mg/dk); hipotansiyon, bradikardi, aritmi, kardiyak arrest.",
                "Şiddetli hiperfosfatemide dikkat (Ca-fosfat çökelmesi).",
                "Şiddetli hipokalemide dikkat; ani serum Ca artışı yaşamı tehdit eden aritmi yapabilir.",
                "Hipomagnezemiyi değerlendir/düzelt; hipokalsemi refraktere olabilir.",
                "Tuz formuna dikkat; yanlış seçim ciddi doz hatasına yol açar."
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
            drugId = "calcium_chloride",
            nameTr = "Kalsiyum klorür",
            nameEn = "Calcium chloride",
            genericName = "Calcium chloride",
            brandNames = emptyList(),
            category = "crisis",
            atcCode = null,
            drugClass = "Kalsiyum Tuzu (Kalsiyum Desteği)",
            mechanismTr = "Miyokard kontraktilitesini artırır, hiperkalemi ve kalsiyum kanal blokörü toksisitesinde kardiyoprotektiftir.",
            adultDoses = mapOf("dozaj" to DoseInfo("Not: 1 g kalsiyum klorür = 270 mg elemental kalsiyum; %10 solüsyon = 1.4 mEq (27 mg) elemental Ca/mL.\n• Beta-bloker toksisitesi (endikasyon dışı): IV: başlangıç %10 ile 1-2 g (10-20 mL) veya 20 mg/kg, 5-10 dk; 10-20 dk arayla 3-4 ek doz veya 20-40 mg/kg/saat infüzyon.\n• Kalsiyum kanal bloker toksisitesi (endikasyon dışı): IV: başlangıç 1-2 g (10-20 mL %10) veya 20 mg/kg, 5-10 dk; tekrar veya 20-40 mg/kg/saat infüzyon. İyonize Ca'yı ÜLN'nin 1.5-2 katında tut.\n• CRRT sitrat antikoagülasyonunda Ca replasmanı (endikasyon dışı): sürekli infüzyon, iyonize Ca ~3.6-5.2 mg/dL hedefli.\n• Hidroflorik asit maruziyeti (endikasyon dışı): IV: 4 g'a kadar bolus.\n• Hiperkalemi (ağır/acil, endikasyon dışı): IV, Intraosseöz: başlangıç 0.5-1 g, 2-5 dk; ECG değişiklikleri sürerse 5 dk sonra tekrar, sonra 30-60 dk arayla.\n• Akut hipokalsemi (alternatif): santral hat yoksa kalsiyum glukonat tercih edilir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Not: 1000 mg kalsiyum klorür = 270 mg elemental kalsiyum = 14 mEq.\n• Kalsiyum kanal bloker toksisitesi: Bebek/Çocuk/Adölesan: IV 10-20 mg/kg/doz, 5-10 dk; max 2000 mg/doz; etkiliyse 10-15 dk arayla tekrar veya 20-50 mg/kg/saat sürekli infüzyon.\n• Kardiyak arrest (hiperkalemi/hipokalsemi/hipermagnezemi/KKB toksisitesi, PALS): IV, Intraosseöz: 20 mg/kg/doz; max 2000 mg/doz; gerekirse 10 dk sonra tekrar.\n• Semptomatik hipokalsemi: IV 10-20 mg/kg/doz; max 1000 mg/doz; gerektikçe 4-6 saatte.\n• Düşük kardiyak output (ek): sürekli IV infüzyon 5-10 mg/kg/saat.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "belirtilmemiş (Eliminasyon yeri: başlıca feçes (%80 çözünmeyen Ca tuzları); idrar (%20))"
            ),
            contraindications = listOf(
                "Hiperkalsemi",
                "Dijital zehirlenmesi (kardiyak arrest riski)"
            ),
            warnings = listOf(
                "Asidozda dikkat; klorür tuzunun asidik etkisi asidozu artırabilir.",
                "Ceftriakson ile birlikte verme (Ca-ceftriakson çökeltisi; neonatlarda fatal akciğer/böbrek hasarı).",
                "Digoksinli hastalarda dikkat; hiperkalsemi aritmi tetikleyebilir.",
                "Şiddetli hiperfosfatemi/hipokalemide dikkat."
            ),
            sideEffectsCommon = listOf(
                "Kardiyak aritmi, hipotansiyon, periferik vazodilatasyon",
                "enjeksiyon yeri reaksiyonu, lokal yanma",
                "kutanöz kalsifikasyon",
                "ağızda metalik tat."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ekstravazasyon: vezikan; ekstravazasyon şiddetli nekroz ve doku kaybına yol açabilir; IV bölgeyi yakın izle.",
                "Asla SUBQ veya IM enjekte etme (doku nekrozu); klorür tuzu dokuya verilmemeli.",
                "Asidozda dikkat; klorür tuzunun asidik etkisi asidozu artırabilir.",
                "Ceftriakson ile birlikte verme (Ca-ceftriakson çökeltisi; neonatlarda fatal akciğer/böbrek hasarı).",
                "Digoksinli hastalarda dikkat; hiperkalsemi aritmi tetikleyebilir.",
                "Şiddetli hiperfosfatemi/hipokalemide dikkat."
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
            drugId = "sodium_bicarbonate",
            nameTr = "Sodyum bikarbonat",
            nameEn = "Sodium bicarbonate",
            genericName = "Sodium Bicarbonate",
            brandNames = listOf(
                "Sodyum Bikarbonat %8.4"
            ),
            category = "crisis",
            atcCode = "B05XA02",
            drugClass = "Sistemik Alkalizan ve Elektrolit Solüsyonu",
            mechanismTr = "Hücre dışı sıvıdaki bikarbonat iyonu konsantrasyonunu artırarak hidrojen iyonlarını nötralize eder ve kan pH'ını yükseltir.",
            adultDoses = mapOf("dozaj" to DoseInfo("Not: %8.4 IV ürün 1 mEq/mL sodyum ve 1 mEq/mL bikarbonat sağlar; 650 mg tablet ~7.7 mEq.\n• Antasit: Oral: 4 saatte gerektikçe 650 mg-2.6 g; max 15.6 g/gün; max dozu >2 hafta kullanma.\n• Sodyum kanal blokajına bağlı kardiyak ileti gecikmesi (endikasyon dışı): IV: başlangıç 50-100 mEq veya 1-2 mEq/kg, 1-2 dk; QRS daralana dek tekrar (kan pH 7.45-7.55). Sürekli infüzyon: %8.4 bikarbonat 150 mEq, 1 L D5W içinde ~150 mL/saat.\n• Hiperkalemi (ağır/acil, ek): kardiyak arrest ile: IV 50 mEq, 5 dk. Metabolik asidoz ile: IV 150 mEq, 1 L D5W içinde, 2-4 saatte.\n• Akut ağır metabolik asidoz: aralıklı %7.5 veya %8.4: IV başlangıç 89.2-100 mEq, 1-2 dk; 2 saatte yeniden değerlendir. Sürekli infüzyon (bikarbonat açığı formülü): doz (mEq) = 0.5 × kg × [hedef − ölçülen bikarbonat].", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Not: %4.2 IV ürün 0.5 mEq/mL, %8.4 IV ürün 1 mEq/mL sodyum ve bikarbonat sağlar.\n• Antasit: ≥6 yaş ve adölesan: Oral toz çözeltisi: 4 ons suda 1/2 çay kaşığı/doz; 2 saatte tekrar; max 6 doz/gün; max dozu >2 hafta kullanma.\n• Kardiyak arrest (PALS): Bebek/Çocuk/Adölesan: IV, Intraosseöz: 1 mEq/kg/doz; tekrar dozlar arteriyel kan gazına göre; <2 yaş için %4.2 tercih edilebilir. Rutin kullanım önerilmez.\n• KBH asidozu: serum bikarbonat <22 mEq/L ise: Oral, açık formülüne göre.\n• Hiperkalemi (ek, sınırlı veri).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "oral 15 dk; IV hızlı",
                peak = "belirtilmemiş",
                duration = "oral 1-3 saat; IV 8-10 dk",
                halfLife = "belirtilmemiş",
                elimination = "belirtilmemiş (Eliminasyon yeri: idrar (<%1))"
            ),
            contraindications = listOf(
                "Metabolik veya respiratuar alkaloz",
                "Hipokalsemi (alkalizasyon iyonize kalsiyumu daha da düşürür)",
                "Şiddetli hiponatremi"
            ),
            warnings = listOf(
                "Siroz, ödem, kalp yetmezliği, böbrek yetmezliğinde dikkat (sodyum retansiyonu)."
            ),
            sideEffectsCommon = listOf(
                "Hipernatremi, metabolik alkaloz."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Ekstravazasyon: ≥%8.4 konsantrasyonlarda vezikan; hipertonisite ve alkaliniteye bağlı doku nekrozu olabilir; ekstravazasyondan kaçın.",
                "Siroz, ödem, kalp yetmezliği, böbrek yetmezliğinde dikkat (sodyum retansiyonu).",
                "Peptik ülser tedavisinde kullanma.",
                "Neonat/bebek/<2 yaş çocukta hızlı uygulama hipernatremi, BOS basıncında azalma ve intrakraniyal kanamaya yol açmıştır.",
                "IV kullanımı belgelenmiş ağır metabolik asidoz ve ağır/acil hiperkalemi ile sınırlı tut.",
                "Toz/tablet uygulamadan önce suda tamamen çöz."
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
            drugId = "insulin_regular",
            nameTr = "İnsülin (regüler)",
            nameEn = "Insulin (regular)",
            genericName = "Insulin regular",
            brandNames = listOf(
                "HumuLIN R U-500 (CONCENTRATED) [DSC];",
                "HumuLIN R U-500 KwikPen;",
                "HumuLIN R [OTC];",
                "Мyхrеdlin;",
                "NovoLIN R FlexPen ReliOn [OTC];"
            ),
            category = "crisis",
            atcCode = null,
            drugClass = "Kısa Etkili İnsülin",
            mechanismTr = "Glikozun hücre içine girişini sağlar, hiperkalemide glukozla verilerek potasyumu hücre içine sokar.",
            adultDoses = mapOf("dozaj" to DoseInfo("Not: Regüler insülin U-100, konsantre U-500 ile değiştirilemez.\n• Kadavra organ kurtarma (hormonal resüsitasyon, endikasyon dışı): IV: 1 ünite/saat sürekli infüzyon (kan glukozu 120-180 mg/dL) veya 20 ünite bolus (dekstroz 25 g IV bolus sonrası).\n• Kalsiyum kanal bloker veya beta-bloker toksisitesi (endikasyon dışı): hipokalemiyi düzelt; kan glukozu <200 ise 50 mL %50 dekstroz ver. IV: 1 ünite/kg bolus (16 ünite/mL solüsyon), sonra 0.5-1 ünite/kg/saat sürekli infüzyon, klinik yanıta titre; daha yüksek dozlar (10 ünite/kg bolus, >10 ünite/kg/saat) kullanılmış.\n• Tip 1 DM: SUBQ (U-100): regüler insülin orta/uzun etkili insülinle birlikte. Başlangıç TGD ~0.4-0.5 ünite/kg/gün bölünmüş; idame 0.4-1 ünite/kg/gün. Bazal %40-50, prandiyal %50-60.\n• Tip 2 DM: regüler U-100; bazal insülin yeterli titre edilmesine rağmen hedeflere ulaşılamazsa.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Tip 1 DM: Bebek/Çocuk/Adölesan: SUBQ: başlangıç toplam günlük 0.4-0.5 ünite/kg/gün bölünmüş; olağan aralık 0.4-1 ünite/kg/gün; küçük çocuklarda 0.25 ünite/kg/gün; pubertede >1, bazen 2 ünite/kg/gün'e kadar. Kısmi remisyon: <0.5 ünite/kg/gün. Bazal ~%30-50; prandiyal kalan kısım. Regüler yerine hızlı etkili analog tercih edilir.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "U-100 IV ~10-21 dk, SUBQ ~30 dk; U-500 SUBQ <15 dk",
                peak = "U-100 IV infüzyon ~5 saat, SUBQ 1.5-3.5 saat; U-500 SUBQ 4-8 saat",
                duration = "U-100 IV 1.5 saat (durduktan sonra), SUBQ ~8 saat; U-500 SUBQ 13-24 saat",
                halfLife = "U-100 IV ~0.3-1 saat, SUBQ 1.5 saat; U-500 SUBQ 4.5 saat",
                elimination = "belirtilmemiş (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Hipoglisemi",
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Hipoglisemi en sık advers etki; insülin gücü/üretici/tip/uygulama değişikliğiyle hiper- veya hipoglisemi olabilir; ağır/uzamış hipoglisemi konvülziyon, bilinç kaybı, beyin hasarı veya ölüme yol açabilir.",
                "Ağır, yaşamı tehdit eden anafilaksi dahil alerjik reaksiyonlar olabilir; oluşursa kes.",
                "Renal/hepatik yetmezlikte hipoglisemi riski artar; etanolle dikkat."
            ),
            sideEffectsCommon = listOf(
                "Hipoglisemi, hipokalemi, kilo artışı",
                "periferik ödem",
                "enjeksiyon yerinde eritem/hipertrofi/lipoatrofi/amiloidoz, kaşıntı",
                "anafilaksi, aşırı duyarlılık."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Hipoglisemi en sık advers etki; insülin gücü/üretici/tip/uygulama değişikliğiyle hiper- veya hipoglisemi olabilir; ağır/uzamış hipoglisemi konvülziyon, bilinç kaybı, beyin hasarı veya ölüme yol açabilir.",
                "Hipokalemi: özellikle IV insülin potasyumu hücre içine kaydırır; serum potasyumunu sık izle ve gerektiğinde takviye et.",
                "Ağır, yaşamı tehdit eden anafilaksi dahil alerjik reaksiyonlar olabilir; oluşursa kes.",
                "Renal/hepatik yetmezlikte hipoglisemi riski artar; etanolle dikkat."
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
            drugId = "glucagon",
            nameTr = "Glukagon",
            nameEn = "Glucagon",
            genericName = "Glucagon",
            brandNames = listOf(
                "Βаԛѕimi One Pack;",
                "Baԛsimi Two Pack;",
                "GlսсаGеո Diagnostic [DSC];",
                "GlսϲаGen HypoKit [DSC];",
                "Gvоkе HypoPen 1-Pack;"
            ),
            category = "crisis",
            atcCode = null,
            drugClass = "Pankreatik Hormon (Glukagon)",
            mechanismTr = "Karaciğerde glikojenolizi artırır, kalsiyum girişini artırarak Beta blokör toksisitesinde inotropik etki sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anafilaksi (epinefrine refrakter, beta-bloker alanlarda, endikasyon dışı): IV: başlangıç 1-5 mg bolus; sonra 5-15 mcg/dk infüzyon, klinik yanıta titre.\n• Beta-bloker doz aşımı (endikasyon dışı): IV: 2-10 mg bolus; yanıt yoksa 10-15 dk sonra tekrar; yanıt varsa 2-5 mg/saat sürekli infüzyon (aralık 1-15 mg/saat).\n• Kalsiyum kanal bloker doz aşımı (endikasyon dışı): IV: 2-10 mg bolus; yanıt yoksa 2 dk arayla 2 kez tekrar; yanıt varsa 2-5 mg/saat infüzyon (aralık 1-15 mg/saat).\n• Tanısal yardım (radyolojik): Kolon gevşetme: IM 1-2 mg; IV 0.5-0.75 mg. Mide/duodenum/ince bağırsak: IM 1 mg; IV 0.2-0.5 mg.\n• Büyüme hormonu eksikliği testi (endikasyon dışı): IM: ≤90 kg 1 mg; >90 kg 1.5 mg.\n• Hipoglisemi: IM, IV, SUBQ: 1 mg; 15 dk sonra tekrar. İntranazal: 3 mg (tek aktüasyon) bir burun deliğine; 15 dk sonra yeni cihazla tekrar.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Ağır hipoglisemi: Bebek/Çocuk/Adölesan: ağırlık bazlı IM, IV, SUBQ: 0.02-0.03 mg/kg/doz; gerekirse 15 dk arayla, toplam 3 doza kadar; max 1 mg/doz. Ürüne özgü (Glukagon): <20 kg 0.5 mg, ≥20 kg 1 mg; GlukaGen: <25 kg 0.5 mg, ≥25 kg 1 mg. İntranazal (Baqsimi): Çocuk/Adölesan: 3 mg (1 aktüasyon) bir burun deliğine; 15 dk sonra yeni cihazla tekrar.\n• Hafif/yaklaşan hipoglisemi (mini-doz): Bebek/Çocuk ≤2 yaş: SUBQ (kesilmiş).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IM 10 dk; SUBQ ~10 dk; intranazal IM'den biraz gecikmeli",
                peak = "IV 5-20 dk; IM 30 dk (tanısal); SUBQ 30-45 dk",
                duration = "belirtilmemiş",
                halfLife = "belirtilmemiş",
                elimination = "belirtilmemiş (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Feokromositoma",
                "İnsülinoma"
            ),
            warnings = listOf(
                "Feokromositoma, insülinoma, glukagonomada kontrendike (katekolamin salınımı / paradoksik hipoglisemi)."
            ),
            sideEffectsCommon = listOf(
                "Bulantı (%26-30), kusma, baş ağrısı",
                "intranazalde üst solunum semptomları",
                "hipertansiyon, hipotansiyon, taşikardi, hiperglisemi",
                "aşırı duyarlılık (anafilaktik şok dahil)."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aşırı duyarlılık: cilt döküntüsü ve anafilaktik şok bildirilmiş; ağır reaksiyonda kes.",
                "Nekrolitik migratuar eritem sürekli infüzyonla nadir bildirilmiş.",
                "Feokromositoma, insülinoma, glukagonomada kontrendike (katekolamin salınımı / paradoksik hipoglisemi).",
                "Adrenal yetmezlik, kronik hipoglisemi, açlık/inanisyon: hepatik glikojen yetersiz olabilir, etkisiz kalabilir.",
                "İnsülin/sülfonilüre doz aşımında hipoglisemi öncelikle dekstroz ile tedavi edilmeli.",
                "Yanıt veren ağır hipoglisemide sekonder hipoglisemiyi önlemek için karbonhidrat ver."
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
            drugId = "hydrocortisone",
            nameTr = "Hidrokortizon",
            nameEn = "Hydrocortisone",
            genericName = "Hydrocortisone (systemic)",
            brandNames = listOf(
                "Alkindi Sprinkle;",
                "Сοrtef;",
                "Κhiոdivi;",
                "Ѕοlս-СОRTΕF"
            ),
            category = "allergy",
            atcCode = null,
            drugClass = "Glukokortikoid (Kortikosteroid)",
            mechanismTr = "İnflamasyonu baskılar, anafilaksi ve adrenal yetmezlik/kriz yönetiminde kullanılır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Adrenal kriz: IV: 100 mg bolus hemen, sonra 6 saatte 50 mg veya 24 saatte 200 mg sürekli IV infüzyon (ilk 24 saat). İyileşirse 12 saatte 50 mg'a azalt; stabilse oral idameye geç. Mineralokortikoid eksiğinde doz <40 mg/gün olunca fludrokortizon ekle.\n• Kronik adrenal yetmezlik idame: Oral: başlangıç 15-25 mg/gün, 2-3 bölünmüş doz; düşük etkili doz. 2 dozlu: 2/3 sabah, 1/3 öğleden sonra.\n• Stres dozu: Febril hastalık: oral idame dozunu ikiye katla (ateş 38-39°C) veya üçe katla (>39°C); 3 gün. Oral alamayan: IV/SUBQ/IM 100 mg erken, 6-12 saatte tekrar.\n• Cerrahi stres: Minör: ek 25 mg cerrahi günü. Orta: IV/IM 50-75 mg/gün. Major: IV 100 mg anesteziden hemen önce, sonra 6 saatte 50 mg veya 24 saatte 200 mg infüzyon.\n• Doğum/eylem: IV/IM 100 mg aktif eylem başında, sonra 6 saatte 50 mg veya 200 mg/24 saat infüzyon.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("PDF'te belirtilmemiş", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV 1 saat",
                peak = "IM 66±51 dk; oral 1.2±0.4 saat; SUBQ 91±34 dk",
                duration = "belirtilmemiş",
                halfLife = "IM 2.2 saat; IV 2 saat; oral 1.8 saat; SUBQ 4.7 saat",
                elimination = "hepatik (Eliminasyon yeri: idrar)"
            ),
            contraindications = listOf(
                "Sistemik mantar enfeksiyonları",
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "İmmünosüpresyon: enfeksiyon riskini artırır; suçiçeği/kızamık maruziyetinden kaçın; latent TB reaktivasyonu, Strongyloides hiperenfeksiyonu, hepatit B reaktivasyonu olabilir."
            ),
            sideEffectsCommon = listOf(
                "HPA aksı baskılanması, Cushing sendromu, hiperglisemi, sodyum/sıvı retansiyonu, hipokalemik alkaloz",
                "enfeksiyona yatkınlık",
                "peptik ülser/GI perforasyon",
                "psikiyatrik bozukluklar, miyopati",
                "bradikardi, hipertansiyon."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Adrenal baskılanma: HPA aksı baskılayabilir; ani kesilme adrenal krize yol açabilir; yavaş azaltarak kes.",
                "İmmünosüpresyon: enfeksiyon riskini artırır; suçiçeği/kızamık maruziyetinden kaçın; latent TB reaktivasyonu, Strongyloides hiperenfeksiyonu, hepatit B reaktivasyonu olabilir.",
                "Anafilaktoid reaksiyonlar nadiren görülmüş.",
                "Akut miyopati yüksek dozda bildirilmiş.",
                "Dermise enjeksiyon/sızıntıdan kaçın; deltoid kasa enjeksiyondan kaçın."
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
            drugId = "methylprednisolone",
            nameTr = "Metilprednizolon",
            nameEn = "Methylprednisolone",
            genericName = "Methylprednisolone",
            brandNames = listOf(
                "DЕΡО-Мedrol;",
                "Меdrоl;",
                "ЅОԼU-Μеdrοl"
            ),
            category = "allergy",
            atcCode = null,
            drugClass = "Orta Etkili Glukokortikoid (Kortikosteroid)",
            mechanismTr = "Güçlü anti-inflamatuar etki gösterir.",
            adultDoses = mapOf("dozaj" to DoseInfo("Güvenlik: Sadece süksinat formülasyonu (Solu-Medrol) IV verilebilir; asetat (Depo-Medrol) sadece IM/intraartiküler.\n• Olağan doz aralığı: IV (süksinat): 40-125 mg/gün tek veya bölünmüş; nadiren 1-2 mg/kg/gün'e kadar. Yüksek doz \"pulse\" (ağır romatizmal): 7-15 mg/kg/doz (veya 500 mg-1 g/doz) günde 1 kez, 3-5 gün. Oral: 16-64 mg/gün. IM (asetat/süksinat): tek doz 40-60 mg.\n• İntraartiküler (asetat): büyük eklem 20-80 mg; orta 10-40 mg; küçük 4-10 mg.\n• İntralezyonel (asetat): 20-60 mg.\n• ARDS (endikasyon dışı): Erken (≤72 saat): IV süksinat yükleme 1 mg/kg, 30 dk; gün 1-14: 1 mg/kg/gün; sonra azaltma. Geç (7. gün): yükleme 2 mg/kg; gün 1-14: 2 mg/kg/gün; sonra azaltma.\n• Adrenal kriz (alternatif): IV 40 mg/gün, 2 bölünmüş doz.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("Güvenlik: Sadece süksinat formülasyonu (Solu-Medrol) IV verilebilir; asetat (Depo-Medrol) sadece IM/intraartiküler. Spesifik pediatrik dozlar PDF'te belirtilmemiş.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV (süksinat) 1 saat içinde; intraartiküler (asetat) 1 hafta",
                peak = "oral 2.1±0.7 saat; IV süksinat 0.8 saat",
                duration = "intraartiküler (asetat) 1-5 hafta",
                halfLife = "adölesan IV 1.9 saat; erişkin oral 2.5 saat, IV süksinat 0.25 saat",
                elimination = "hepatik (Eliminasyon yeri: idrar (%1.3 oral, %9.2 IV süksinat değişmemiş))"
            ),
            contraindications = listOf(
                "Sistemik mantar enfeksiyonları",
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "KY/HT, MI sonrası dikkat (miyokard rüptürü ilişkisi); GI hastalıkta perforasyon riski.",
                "Myastenia graviste geçici kötüleşme; nöbet bozukluğunda dikkat.",
                "Sistemik sklerozda yüksek doz skleroderma renal kriz riskini artırabilir; mümkünse kaçın."
            ),
            sideEffectsCommon = listOf(
                "Adrenal baskılanma, Cushing sendromu, hiperglisemi, sodyum/sıvı retansiyonu, hipokalemik alkaloz",
                "bradikardi, aritmi, hipertansiyon, tromboembolizm",
                "peptik ülser/GI perforasyon",
                "cilt atrofisi",
                "anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Adrenal baskılanma: HPA aksını baskılayabilir, özellikle çocuklarda.",
                "Kafa travması: yüksek doz IV metilprednizolon ile mortalite artışı; kafa travmasında yüksek doz kortikosteroid kullanma.",
                "KY/HT, MI sonrası dikkat (miyokard rüptürü ilişkisi); GI hastalıkta perforasyon riski.",
                "Myastenia graviste geçici kötüleşme; nöbet bozukluğunda dikkat.",
                "Sistemik sklerozda yüksek doz skleroderma renal kriz riskini artırabilir; mümkünse kaçın.",
                "Septik artrit parenteral tedavi komplikasyonu olabilir."
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
            drugId = "dexamethasone",
            nameTr = "Deksametazon",
            nameEn = "Dexamethasone",
            genericName = "Dexamethasone Sodium Phosphate",
            brandNames = listOf(
                "Dekort",
                "Onadron"
            ),
            category = "antiemetic",
            atcCode = "H02AB02",
            drugClass = "Uzun Etkili Glukokortikoid (Kortikosteroid)",
            mechanismTr = "Prostaglandin sentezini inhibe eder, inflamasyonu ve kapiller geçirgenliği azaltır. CTZ'de serotonin ve endorfin yollarını modüle ederek antiemetik etki gösterir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Olağan doz aralığı: Oral, IV, IM: 4-20 mg/gün tek veya 2-4 bölünmüş doz; yüksek doz 0.4-0.8 mg/kg/gün (genellikle ≤40 mg/gün).\n• Akut dağ hastalığı/yüksek irtifa serebral ödem (endikasyon dışı): Önleme: Oral 6 saatte 2 mg veya 12 saatte 4 mg. Tedavi (AMS orta-ağır): Oral/IM/IV 6 saatte 4 mg. HACE: 8 mg tek doz, sonra 6 saatte 4 mg.\n• ARDS (endikasyon dışı): IV: gün 1-5 günde 20 mg, gün 6-10 günde 10 mg.\n• Adrenal kriz (alternatif): IV: günde 4-6 mg; ilk doz hemen.\n• Kronik adrenal yetmezlik (alternatif): Oral: başlangıç 0.5 mg günde 1; olağan aralık 0.25-0.75 mg/gün.\n• Stres dozu: parenteral gerekirse hidrokortizona geç.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Akut dağ hastalığı (orta)/HACE: Bebek/Çocuk/Adölesan: Oral/IM/IV 6 saatte 0.15 mg/kg/doz; max 4 mg/doz.\n• Hava yolu ödemi/ekstübasyon: Oral/IM/IV 0.5 mg/kg/doz (max 10 mg/doz), ekstübasyondan 6-12 saat önce, sonra 6 saatte 6 doz.\n• Anti-inflamatuar: Oral/IM/IV 0.02-0.3 mg/kg/gün veya 0.6-9 mg/m²/gün, 6-12 saatte bölünmüş.\n• Astım atağı: Oral/IM/IV 0.3-0.6 mg/kg günde 1, 1-2 gün; max 16 mg/doz.\n• Bakteriyel menenjit: IV 0.15 mg/kg/doz 6 saatte, ilk 2-4 gün; max 10 mg/doz; antibiyotik öncesi/birlikte.\n• Serebral ödem: yükleme 1-2 mg/kg/doz; idame 1-2 mg/kg/gün bölünmüş (kesilmiş).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV hızlı",
                peak = "oral 1-2 saat; IM ~30-120 dk; IV 5-10 dk",
                duration = "IV kısa",
                halfLife = "erişkin oral 4 saat, IV ~1-5 saat; çocuk 4 ay-16 yaş 4.34 saat",
                elimination = "hepatik (Eliminasyon yeri: idrar (~%10))"
            ),
            contraindications = listOf(
                "Sistemik mantar enfeksiyonları",
                "Deksametazona karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "KY/HT, MI sonrası dikkat (miyokard rüptürü); GI hastalıkta perforasyon riski (maskelenebilir).",
                "Myastenia graviste geçici kötüleşme; nöbet bozukluğu, sistemik sklerozda dikkat."
            ),
            sideEffectsCommon = listOf(
                "Sodyum/sıvı retansiyonu, hipokalemik alkaloz, hiperglisemi, adrenal baskılanma, Cushing sendromu",
                "bradikardi, aritmi, hipertansiyon",
                "pankreatit",
                "nöbet, psikiyatrik değişiklikler",
                "IV enjeksiyon sonrası perianal/perineal yanma-kaşıntı."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Adrenal baskılanma: HPA aksı baskılayabilir, özellikle çocuklarda.",
                "Deksametazon mineralokortikoid aktivite sağlamaz; kronik primer adrenal yetmezlik/krizde hidrokortizon tercih edilir.",
                "Kafa travması: yüksek doz IV ile mortalite artışı; kafa travmasında kullanma.",
                "KY/HT, MI sonrası dikkat (miyokard rüptürü); GI hastalıkta perforasyon riski (maskelenebilir).",
                "Feokromositoma krizi (fatal olabilir) bildirilmiş.",
                "Myastenia graviste geçici kötüleşme; nöbet bozukluğu, sistemik sklerozda dikkat."
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
            drugId = "chlorpheniramine",
            nameTr = "Klorfeniramin",
            nameEn = "Chlorpheniramine",
            genericName = "Chlorpheniramine",
            brandNames = listOf(
                "Allеr-Chlor [OTC];",
                "Allergy Relief [OTC];",
                "Allergy [OTC];",
                "Chlor-Trimeton Allergy [OTC];",
                "Сhlоrphеո [OTC];"
            ),
            category = "allergy",
            atcCode = null,
            drugClass = "Birinci Kuşak Antihistaminik (H1 Reseptör Antagonisti)",
            mechanismTr = "Periferik H1 reseptörlerini bloke ederek alerjik reaksiyonları ve kaşıntıyı önler.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Hareket hastalığı (endikasyon dışı): Hemen salınımlı: Oral: uyarandan 3 saat önce 4-12 mg. Not: Sedasyon güvensizse kullanma.\n• Üst solunum yolu durumları: Hemen salınımlı: Oral: 4-6 saatte 4 mg; 24 mg/24 saati aşma. Uzatılmış salınımlı: Oral: 12 saatte 12 mg; 24 mg/24 saati aşma.\n• Ürtiker (yeni başlangıç ve kronik spontan, alternatif, endikasyon dışı): Hemen salınımlı: Oral: 4-6 saatte 4 mg; 24 mg/24 saati aşma. Uzatılmış salınımlı: Oral: 12 saatte 12 mg; 24 mg/24 saati aşma.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Üst solunum yolu alerji semptomları (saman nezlesi): Hemen salınımlı oral şurup/tablet: 2-<6 yaş (sınırlı veri): 4-6 saatte 1 mg; max 6 mg/gün. 6-<12 yaş: 4-6 saatte 2 mg; max 12 mg/gün. ≥12 yaş ve adölesan: 4-6 saatte 4 mg; max 24 mg/gün. Uzatılmış salınımlı tablet: ≥12 yaş ve adölesan: 12 saatte 12 mg; max 24 mg/24 saat.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "çocuk/adölesan 6-16 yaş 2.5 saat; erişkin 2-3 saat",
                duration = "belirtilmemiş",
                halfLife = "çocuk/adölesan 13.1 saat; erişkin 14-24 saat",
                elimination = "hepatik (CYP450, CYP2D6 dahil; belirgin ilk geçiş etkisi) (Eliminasyon yeri: idrar)"
            ),
            contraindications = listOf(
                "Dar açılı glokom",
                "Semptomatik prostat hipertrofisi",
                "Yeni doğanlar"
            ),
            warnings = listOf(
                "CNS depresyonu yapabilir; fiziksel/zihinsel yetenekleri bozabilir; dikkat gerektiren işlerde (makine kullanımı, araç sürme) uyar."
            ),
            sideEffectsCommon = listOf(
                "Üretici etiketinde advers reaksiyon listelenmemiş. (Sınıf etkisi: sedasyon, antikolinerjik etkiler.)"
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "CNS depresyonu yapabilir; fiziksel/zihinsel yetenekleri bozabilir; dikkat gerektiren işlerde (makine kullanımı, araç sürme) uyar.",
                "Pediatride antihistaminikler küçük çocuklarda eksitasyon yapabilir; OTC kullanım <2 yaşta önerilmez.",
                "OTC kullanımda solunum problemi, glokom, prostat büyümesine bağlı idrar zorluğu veya sedatif/trankilizan kullanımında doktora danış."
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
            drugId = "ondansetron",
            nameTr = "Ondansetron",
            nameEn = "Ondansetron",
            genericName = "Ondansetron Hydrochloride",
            brandNames = listOf(
                "Zofran",
                "Zofer"
            ),
            category = "antiemetic",
            atcCode = "A04AA01",
            drugClass = "Selektif 5-HT3 (Serotonin) Reseptör Antagonisti",
            mechanismTr = "Kemoreseptör tetikleme zonunda (CTZ) ve periferik vagal sinir uçlarındaki 5-HT3 reseptörlerini bloke ederek bulantı ve kusma refleksini güçlü şekilde önler.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Kemoterapiye bağlı bulantı/kusma (önleme): IV: 8 mg veya 0.15 mg/kg tek doz (maksimum: 16 mg/doz), kemoterapiden önce. Oral: 8 mg günde 2 kez (2 doz) veya 24 mg tek doz. • Postoperatif bulantı/kusma. • Karsinoid sendroma bağlı ishal (off-label): Oral: 8 mg günde 3 kez; IV: 4-8 mg her 8 saatte. Güvenlik: 16 mg üzeri tek IV dozlar QT uzaması riski nedeniyle artık önerilmez.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Kemoterapiye bağlı bulantı/kusma: Bebek/Çocuk/Adölesan: IV, Oral: 0.15 mg/kg/doz (maks tek doz: 16 mg); kemoterapiden önce, sonra her 4-12 saatte, en fazla 3 doz; maks günlük doz: 0.45 mg/kg/gün veya 32 mg/gün. • Akut gastroenterit (sınırlı veri): IV: 0.15-0.3 mg/kg/doz tek doz (maks: 16 mg). • Postoperatif bulantı/kusma önleme.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "~30 dakika",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Erişkin 3-6 saat (oral pik ~2 saat)",
                elimination = "Yoğun hepatik (hidroksilasyon, glukuronid/sülfat konjugasyonu; CYP1A2, CYP2D6, CYP3A4 substratı) (Eliminasyon yeri: İdrar (%44-60 metabolit, ~%5 değişmemiş); feçes (~%25))"
            ),
            contraindications = listOf(
                "Ondansetrona karşı bilinen aşırı duyarlılık",
                "Apomorfin ile eş zamanlı kullanımı (şiddetli hipotansiyon riski nedeniyle)"
            ),
            warnings = listOf(
                "QT uzaması riski: Konjenital uzun-QT sendromunda kullanılmaz; 16 mg üzeri tek IV doz önerilmez. • Serotonin sendromu: Diğer serotonerjik ajanlarla (SSRI, SNRI, MAOİ, fentanil, tramadol, metilen mavisi) birlikte kullanımda, çoğu anestezi sonrası ortamda bildirilmiş; bazı vakalar ölümcül. • Bazı formlarda benzil alkol/sodyum benzoat (neonatlarda dikkat); ODT formları fenilalanin içerir."
            ),
            sideEffectsCommon = listOf(
                "Kabızlık (%9-11), baş ağrısı (%9-24), yorgunluk, baş dönmesi",
                "QT uzaması (doz bağımlı)",
                "nadir anafilaksi",
                "ekstrapiramidal reaksiyon",
                "serotonin sendromu."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "QT uzaması riski: Konjenital uzun-QT sendromunda kullanılmaz; 16 mg üzeri tek IV doz önerilmez. • Serotonin sendromu: Diğer serotonerjik ajanlarla (SSRI, SNRI, MAOİ, fentanil, tramadol, metilen mavisi) birlikte kullanımda, çoğu anestezi sonrası ortamda bildirilmiş; bazı vakalar ölümcül. • Bazı formlarda benzil alkol/sodyum benzoat (neonatlarda dikkat); ODT formları fenilalanin içerir."
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
            drugId = "metoclopramide",
            nameTr = "Metoklopramid",
            nameEn = "Metoclopramide",
            genericName = "Metoclopramide",
            brandNames = listOf(
                "Gimoti;",
                "Rеglan"
            ),
            category = "antiemetic",
            atcCode = null,
            drugClass = "Dopamin Reseptör Antagonisti (Prokinetik ve Antiemetik)",
            mechanismTr = "MSS'de D2 reseptörlerini bloke eder, mide boşalımını hızlandırır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Anestezide aspirasyon profilaksisi (off-label): IV: 10 mg, 1-2 dakikada, indüksiyondan ~30-60 dakika önce tek doz (nonpartikülat antasit ve/veya H2 antagonisti ile). • Gastroparezi: Oral (tercih), IM, IV, SC: 5 mg günde 2-3 kez, yemekten 30 dakika önce; gerekirse 10 mg'a artırılır; maks: 40 mg/gün. Nazal: 1 sprey (15 mg) tek burun deliğine günde 4 kez; maks: 60 mg/gün. • Malign inoperabl bağırsak tıkanıklığı: Oral, IV, SC: 10 mg her 4-6 saatte. Güvenlik: Tardif diskinezi riski nedeniyle ≥12 hafta sürekli kullanılmaz.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Gastroözofageal reflü (son seçenek): Bebek/Çocuk/Adölesan: Oral: 0.1-0.2 mg/kg/doz her 6-8 saatte (maks: 10 mg/doz). • Postoperatif bulantı/kusma önleme (sınırlı veri): Çocuk/Adölesan: IV: 0.1-0.25 mg/kg/doz tek doz, indüksiyon sonrası (maks: 10 mg/doz). • CINV (alternatif): Çocuk/Adölesan: IV: 0.5-2 mg/kg/doz; ekstrapiramidal semptom riski için difenhidramin/benztropin önerilir.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Oral 30-60 dk; IV 1-3 dk; IM 10-15 dk",
                peak = "Erişkin serum 1-2 saat",
                duration = "Terapötik 1-2 saat (yoldan bağımsız)",
                halfLife = "Erişkin 5-6 saat (nazal ~8 saat)",
                elimination = "Hepatik (oksidasyon, glukuronid/sülfat konjugasyonu; CYP2D6) (Eliminasyon yeri: İdrar (~%85); feçes)"
            ),
            contraindications = listOf(
                "Feokromositoma (hipertansif kriz riski)",
                "Gastrointestinal tıkanıklık veya perforasyon",
                "Epilepsi"
            ),
            warnings = listOf(
                "Tardif diskinezi: En kısa süre kullanılmalı, ≥12 hafta sürekli kullanılmaz; TD tanısı konursa hemen kesilir. • Hipertansiyon: Kan basıncını yükseltebilir; hipertansiyonda kaçınılır; tanı konmamış feokromositomada hipertansif kriz bildirilmiştir. • Parkinson hastalığında kaçınılır. • CYP2D6 zayıf metabolize edicilerde doz azaltımı. • Pediatrik (nazal/oral): tardif diskinezi ve neonatlarda methemoglobinemi riski nedeniyle önerilmez."
            ),
            sideEffectsCommon = listOf(
                "Uyku hali (%10-70, doz bağımlı), distonik reaksiyon (≤%25), yorgunluk, huzursuzluk",
                "nazal sprey: disgözi (%15)",
                "ekstrapiramidal reaksiyonlar, tardif diskinezi, nöroleptik malign sendrom, akatizi",
                "bradikardi/hipotansiyon (hızlı IV), hipertansif kriz."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Tardif diskinezi: En kısa süre kullanılmalı, ≥12 hafta sürekli kullanılmaz; TD tanısı konursa hemen kesilir. • Hipertansiyon: Kan basıncını yükseltebilir; hipertansiyonda kaçınılır; tanı konmamış feokromositomada hipertansif kriz bildirilmiştir. • Parkinson hastalığında kaçınılır. • CYP2D6 zayıf metabolize edicilerde doz azaltımı. • Pediatrik (nazal/oral): tardif diskinezi ve neonatlarda methemoglobinemi riski nedeniyle önerilmez."
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
            drugId = "granisetron",
            nameTr = "Granisetron",
            nameEn = "Granisetron",
            genericName = "Granisetron",
            brandNames = listOf(
                "Graոisol;",
                "Ѕаոсսѕο;",
                "Ѕuѕtоl"
            ),
            category = "antiemetic",
            atcCode = null,
            drugClass = "Seçici 5-HT3 Reseptör Antagonisti (Antiemetik)",
            mechanismTr = "Kemoreseptör tetikleme bölgesindeki 5-HT3 reseptörlerini selektif bloke eder.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Kemoterapiye bağlı bulantı/kusma (önleme): Oral: 2 mg günde 1 kez (kemoterapiden 1 saat önce) veya 1 mg günde 2 kez. IV: 10 mcg/kg (veya 1 mg) tek doz, kemoterapiden 30 dakika önce. SC (ER): 10 mg en az 30 dakika önce. Transdermal: 1 yama, kemoterapiden en az 24 saat önce. • Postoperatif bulantı/kusma önleme: IV: 0.35-3 mg (5-20 mcg/kg), anestezi indüksiyonu öncesi. • Postoperatif bulantı/kusma tedavi: IV: 0.1-1 mg tek doz.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Kemoterapiye bağlı bulantı/kusma (CINV): Bebek/Çocuk/Adölesan: IV: 40 mcg/kg günde tek doz, kemoterapiden önce. Oral: 40 mcg/kg/doz her 12 saatte (kemoterapi günlerinde). • Postoperatif bulantı/kusma önleme (sınırlı veri): Çocuk/Adölesan: IV: 40 mcg/kg tek doz (maks: 0.6 mg/doz); bu dozda QT uzaması gözlenmiştir, yakın izlem.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "IV 1-3 dakika",
                peak = "Transdermal sistemik maks ~48 saat",
                duration = "Oral/IV genellikle 24 saate kadar; SC (ER) 7 gün",
                halfLife = "Oral 6 saat; IV 5-9 saat; SC (ER) ~24 saat",
                elimination = "Hepatik (CYP1A1, CYP3A4) (Eliminasyon yeri: İdrar (%11-12 değişmemiş, %48-49 metabolit); feçes (%34-38))"
            ),
            contraindications = listOf(
                "5-HT3 antagonistlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "EKG etkileri: Doz bağımlı PR, QRS, QT/QTc uzaması; özellikle IV ile; QT uzatan diğer ajanlarla (Sınıf I-III antiaritmikler) birlikte torsade de pointes riski; QT uzaması/aritmi riski olanlarda dikkat. • Hipersensitivite/anafilaksi: Diğer 5-HT3 antagonistlerine duyarlı hastalarda çapraz reaktivite; SC ER ile maruziyet 5-7 gün sürebilir. • Serotonin sendromu. • Ciddi enjeksiyon yeri reaksiyonları (SC ER)."
            ),
            sideEffectsCommon = listOf(
                "Kabızlık (%3-22), bulantı (%20), baş ağrısı, yorgunluk, asteni",
                "QT uzaması (IV/oral/transdermal %1-3)",
                "enjeksiyon yeri reaksiyonları (SC ER ile ciddi olabilir)",
                "hipersensitivite/anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "EKG etkileri: Doz bağımlı PR, QRS, QT/QTc uzaması; özellikle IV ile; QT uzatan diğer ajanlarla (Sınıf I-III antiaritmikler) birlikte torsade de pointes riski; QT uzaması/aritmi riski olanlarda dikkat. • Hipersensitivite/anafilaksi: Diğer 5-HT3 antagonistlerine duyarlı hastalarda çapraz reaktivite; SC ER ile maruziyet 5-7 gün sürebilir. • Serotonin sendromu. • Ciddi enjeksiyon yeri reaksiyonları (SC ER)."
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
            drugId = "droperidol",
            nameTr = "Droperidol",
            nameEn = "Droperidol",
            genericName = "Droperidol",
            brandNames = emptyList(),
            category = "antiemetic",
            atcCode = null,
            drugClass = "Butirofenon grubu Antidopaminerjik (Antiemetik ve Nöroleptik)",
            mechanismTr = "MSS'de D2 reseptörlerini bloke ederek güçlü antiemetik etki sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Postoperatif bulantı/kusma (PONV): IV: 0.625-1.25 mg, işlem sonunda. Üretici etiketi: IM/IV maks başlangıç dozu 2.5 mg; gerekirse 1.25 mg ek doz dikkatle. • Akut ajitasyon (off-label): IM: Başlangıç 5-10 mg; IV: Başlangıç 2.5-10 mg, sedasyon sağlanana kadar her 5 dakikada tekrarlanabilir, maks 20 mg/epizod. • Kannabinoid hiperemezis sendromu (off-label): IV: 0.625-2.5 mg tek doz. Not: Bazal ve rutin vital bulgular ve EKG izlenir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Postoperatif bulantı/kusma; kurtarma tedavisi: Çocuk ≥2 yaş ve Adölesan: IM, IV: 0.01-0.075 mg/kg/doz (maks: 1.25 mg/doz); ek dozlar aşırı dikkatle, yarar riskten fazlaysa. • Akut ajitasyon (sınırlı veri): Çocuk ≥7 yaş ve Adölesan: IM, IV: 0.1-0.2 mg/kg/doz (maks: 10 mg/doz). Sabit dozlama: <34 kg: 0.625 mg; 34-57 kg: 1.25 mg; >57-68 kg: 1.875 mg; >68 kg: 2.5 mg.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "3-10 dakika",
                peak = "~30 dakika",
                duration = "2-4 saat (12 saate kadar uzayabilir)",
                halfLife = "Erişkin ~2 saat; Çocuk ~1.7 saat",
                elimination = "Hepatik (p-fluorofenilasetik asit, benzimidazolon, p-hidroksipiperidin) (Eliminasyon yeri: İdrar (%75, <%1 değişmemiş); feçes (%22))"
            ),
            contraindications = listOf(
                "Bilinen QT uzaması veya aritmi öyküsü",
                "Parkinson hastalığı (dopamin blokajı nedeniyle)"
            ),
            warnings = listOf(
                "Aritmi (kutulu uyarı): QT uzaması ve torsades de pointes; bradikardide (<50 bpm), kardiyak hastalıkta, MAO inhibitörü/Sınıf I-III antiaritmik veya QT uzatan ilaç kullananlarda, elektrolit bozukluğunda (hipokalemi/hipomagnezemi) aşırı dikkatle. EKG izlemi gerekir. • Ekstrapiramidal semptomlar, nöroleptik malign sendrom. • CNS depresyonu; ortostatik hipotansiyon; düşme riski; antikolinerjik etkiler."
            ),
            sideEffectsCommon = listOf(
                "QT uzaması, torsades de pointes, ventriküler aritmi/taşikardi, hipotansiyon",
                "ekstrapiramidal reaksiyon (akatizi, distoni), nöroleptik malign sendrom",
                "anafilaksi",
                "laringospazm, bronkospazm, solunum depresyonu",
                "sedasyon."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Aritmi (kutulu uyarı): QT uzaması ve torsades de pointes; bradikardide (<50 bpm), kardiyak hastalıkta, MAO inhibitörü/Sınıf I-III antiaritmik veya QT uzatan ilaç kullananlarda, elektrolit bozukluğunda (hipokalemi/hipomagnezemi) aşırı dikkatle. EKG izlemi gerekir. • Ekstrapiramidal semptomlar, nöroleptik malign sendrom. • CNS depresyonu; ortostatik hipotansiyon; düşme riski; antikolinerjik etkiler."
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
            drugId = "dimenhydrinate",
            nameTr = "Dimenhidrinat",
            nameEn = "Dimenhydrinate",
            genericName = "Dimenhydrinate",
            brandNames = listOf(
                "Dramamiոе [OTC];",
                "Drimiոаte [OTC];",
                "GoodSense Motion Sickness [OTC]"
            ),
            category = "antiemetic",
            atcCode = null,
            drugClass = "H1 Antihistaminik ve Antikolinerjik (Antiemetik)",
            mechanismTr = "Vestibüler uyarımı baskılar, taşıt tutması ve postoperatif bulantıda etkilidir.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Taşıt tutması/bulantı-kusma: Oral (hemen salınım): 50-100 mg her 4-6 saatte (maks: 400 mg/gün). IM, IV: 50 mg her 4 saatte; gerekirse 100 mg'a artırılabilir. • Gebelikle ilişkili şiddetli bulantı/kusma (off-label): Oral: 25-50 mg her 4-6 saatte (maks: 300 mg/gün; doksilaminle birlikte maks 200 mg/gün). IV: 50 mg 20 dakikada her 4-6 saatte. • Postoperatif bulantı/kusma (Kanada): IM, IV: 50 mg tedavi öncesi, sonra 50 mg işlem sonrası (maks: 400 mg/gün). • Akut vertigo: Oral/IM/IV: 50 mg her 4-6 saatte (maks süre: 3 gün).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Taşıt tutması/bulantı-kusma/vertigo profilaksisi: Çocuk ≥2 to <6 yaş: Oral: 12.5-25 mg her 6-8 saatte (maks: 75 mg/24 saat); ≥6 to <12 yaş: 25-50 mg her 6-8 saatte (maks: 150 mg/24 saat); ≥12 yaş ve Adölesan: 50-100 mg her 4-6 saatte (maks: 400 mg/24 saat). • Postoperatif bulantı/kusma (sınırlı veri): Bebek/Çocuk/Adölesan: IV: 0.5 mg/kg/doz (maks: 25 mg/doz).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Antiemetik IV anında; IM 20-30 dk; Oral 15-30 dk",
                peak = "belirtilmemiş",
                duration = "Oral 4-6 saat",
                halfLife = "Oral 5-8 saat",
                elimination = "Karaciğerde yoğun metabolizma (Eliminasyon yeri: Renal)"
            ),
            contraindications = listOf(
                "Dar açılı glokom",
                "Prostat hipertrofisi",
                "2 yaş altı çocuklar"
            ),
            warnings = listOf(
                "CNS depresyonu: Fiziksel/mental yetiyi bozabilir; yüksek dozda öfori, halüsinasyon, konfüzyon, geçici amnezi görülebilir. • Kardiyovasküler hastalıkta (aritmi, hipertansiyon, iskemik kalp hastalığı) dikkat. • Artmış göz içi basıncı/glokom, prostat hiperplazisi/idrar tıkanıklığı, piloroduodenal tıkanıklık, astım/alt solunum yolu hastalığında dikkat. • Ototoksik antibiyotiklerle birlikte: ototoksisite belirtilerini maskeleyebilir. • Yaşlı ve pediatrikte dikkat; <2 yaş OTC kullanılmaz."
            ),
            sideEffectsCommon = listOf(
                "Uyku hali",
                "taşikardi",
                "ağız kuruluğu, anoreksi, epigastrik rahatsızlık, bulantı",
                "bulanık görme",
                "baş ağrısı, uyarılma, huzursuzluk",
                "nadir anafilaksi",
                "nadir ciddi cilt reaksiyonları (Stevens-Johnson, toksik epidermal nekroliz)."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "CNS depresyonu: Fiziksel/mental yetiyi bozabilir; yüksek dozda öfori, halüsinasyon, konfüzyon, geçici amnezi görülebilir. • Kardiyovasküler hastalıkta (aritmi, hipertansiyon, iskemik kalp hastalığı) dikkat. • Artmış göz içi basıncı/glokom, prostat hiperplazisi/idrar tıkanıklığı, piloroduodenal tıkanıklık, astım/alt solunum yolu hastalığında dikkat. • Ototoksik antibiyotiklerle birlikte: ototoksisite belirtilerini maskeleyebilir. • Yaşlı ve pediatrikte dikkat; <2 yaş OTC kullanılmaz."
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
            drugId = "scopolamine",
            nameTr = "Skopolamin",
            nameEn = "Scopolamine",
            genericName = "Scopolamine (hyoscine)",
            brandNames = listOf(
                "Transderm Scop;",
                "Τrаոѕԁеrm-Ѕϲор [DSC]"
            ),
            category = "antiemetic",
            atcCode = null,
            drugClass = "Antikolinerjik / Antiemetik",
            mechanismTr = "Merkezi ve periferik muskarinik reseptörleri bloke eder, sekresyonları azaltır.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Postoperatif bulantı/kusma önleme: Skopolamin baz: Transdermal: 1 yama (1 mg/3 gün) kulak arkasına, anesteziden en az 1-2 saat önce veya bir gece önce uygulanır; işlemden 24 saat sonra çıkarılır. • Taşıt tutması önleme: Transdermal: 1 yama (1 mg/3 gün) kulak arkasına, etkiden 8 saat önce, 72 saate kadar. • GI/GU spazm (Kanada, butilbromür): Oral: 10-20 mg günde 3-5 kez (maks: 60 mg/gün); IM/IV/SC: 10-20 mg, gerekirse ≥30 dakika arayla tekrar (maks: 100 mg/gün). • Yaşam sonu sekresyonların azaltılması (off-label).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Postoperatif bulantı/kusma önleme (sınırlı veri): Çocuk <2 yaş: Transdermal: 1/4 yama ameliyattan birkaç saat önce; 2 to <6 yaş: 1/2 yama; 6 to <12 yaş: 1/2 to 1 yama; ≥12 yaş ve Adölesan: 1 yama. • Kronik salya akması (≥3 yaş): Transdermal: Başlangıç 1/4 yama her 3 günde 1 hafta; tolere edildikçe titre edilir (maks: 1 yama her 3 günde).", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "Skopolamin baz transdermal 6-8 saat; butilbromür injeksiyon ≤15 dk; hidrobromür ~15 dk",
                peak = "Transdermal 24 saat (hidrobromür IV ~5 dk, IM ~20 dk)",
                duration = "Transdermal 72 saat; hidrobromür injeksiyon ~4 saat",
                halfLife = "Transdermal 9.5 saat; butilbromür IV ~5 saat; hidrobromür ~1-3.5 saat",
                elimination = "Hepatik (Eliminasyon yeri: İdrar (transdermal <%10; butilbromür IV %42-61, feçes %28-37))"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Anafilaksi (şok dahil) parenteral uygulama sonrası bildirilmiştir; alerji/astım öyküsünde risk artar. • Paradoksik bradikardi: Düşük dozlar (0.1 mg) vagal etkiyle paradoksik bradikardi yapabilir. • Kardiyovasküler hastalık, GI/GU obstrüksiyon, glokom (açık açılı), hipertiroidi, ülseratif kolit (toksik megakolon), psikoz, nöbet bozukluğunda dikkat. • Yaşlı ve pediatrikte antikolinerjik/nörolojik/psikiyatrik yan etkilere duyarlılık."
            ),
            sideEffectsCommon = listOf(
                "Ağız kuruluğu (%29-67), uyku hali (%8-17), baş dönmesi (%12), ajitasyon, konfüzyon, midriyazis, görme bozukluğu",
                "faringit",
                "çekilmeyle ilişkili hipotansiyon, bulantı, terleme",
                "idrar retansiyonu",
                "cilt irritasyonu."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Anafilaksi (şok dahil) parenteral uygulama sonrası bildirilmiştir; alerji/astım öyküsünde risk artar. • Paradoksik bradikardi: Düşük dozlar (0.1 mg) vagal etkiyle paradoksik bradikardi yapabilir. • Kardiyovasküler hastalık, GI/GU obstrüksiyon, glokom (açık açılı), hipertiroidi, ülseratif kolit (toksik megakolon), psikoz, nöbet bozukluğunda dikkat. • Yaşlı ve pediatrikte antikolinerjik/nörolojik/psikiyatrik yan etkilere duyarlılık."
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
            drugId = "tranexamic_acid",
            nameTr = "Traneksamik asit",
            nameEn = "Tranexamic acid",
            genericName = "Tranexamic Acid",
            brandNames = listOf(
                "Transamine"
            ),
            category = "crisis",
            atcCode = "B02AA02",
            drugClass = "Antifibrinolitik (Lizin Analoğu)",
            mechanismTr = "Plazminojen üzerindeki lizin bağlama bölgelerini tersinir olarak bloke ederek plazminojene ve fibrine bağlanmasını engeller, fibrin yıkımını (fibrinolizis) durdurur.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Perioperatif kan kaybı ve transfüzyon önleme (kardiyak cerrahi, belirgin kan kaybı olan cerrahiler): IV: 1 g (veya 10-30 mg/kg) işlemden önce; uygulama hızı 100 mg/dakikayı geçmez (genellikle 10-30 dakikada). • Tromolitik tedaviyle ilişkili intrakraniyal kanama (off-label): IV: 1 g (veya 10-15 mg/kg) tek doz, hız ≤100 mg/dakika. • Akut anormal uterin kanama (off-label): IV: 10 mg/kg (maks 600 mg/doz) her 8 saatte, 5 güne kadar. Oral: 1.3 g günde 3 kez (650 mg tablet). Güvenlik: Yüksek toplam IV dozlar (≥50 mg/kg) nöbet riskini artırabilir.", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Ağır menstrüel kanama: Menarş sonrası kadın: Tablet (Lysteda): Oral: 1.300 mg günde 3 kez, menstrüasyon sırasında 5 güne kadar (maks: 3.900 mg/gün). • Diffüz alveolar kanama (intraktabl, çok sınırlı veri): ≤25 kg: İnhale: 250 mg her 6 saatte 3-4 doz; >25 kg ve Adölesan: İnhale: 500 mg her 6 saatte 3-4 doz. • Herediter anjioödem profilaksisi (sınırlı veri): Oral: 20-50 mg/kg/gün 2-3 bölünmüş dozda.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "Oral tek doz ortalama 2.5 saat",
                duration = "belirtilmemiş",
                halfLife = "IV ~2 saat; Oral ~11 saat",
                elimination = "Minimal metabolizma (%3 proteine bağlı, esas olarak plazminojene) (Eliminasyon yeri: İdrar (>%95 değişmemiş ilaç))"
            ),
            contraindications = listOf(
                "Aktif tromboembolik süreç veya geçirilmiş venöz/arteriyel tromboz öyküsü",
                "Subaraknoid kanama (serebral ödem ve enfarktüs riskini artırabilir)"
            ),
            warnings = listOf(
                "Dissemine intravasküler koagülasyon: Antifibrinolitik tedavi gerektiren DIC'te aşırı dikkatle, deneyimli sağlık personeli gözetiminde. • Subaraknoid kanamada dikkat: serebral ödem ve enfarkt olabilir. • Düzeltilmemiş kardiyovasküler/serebrovasküler hastalıkta tromboz komplikasyonları nedeniyle dikkat. • Yüksek IV dozlarda (≥50 mg/kg) nöbet riski. • Sadece IV kullanım; enjektörleri net etiketle, karışıklığı önle. • CNS etkileri (baş dönmesi)."
            ),
            sideEffectsCommon = listOf(
                "Baş ağrısı (%50), sırt ağrısı (%21), karın ağrısı (%20), nazal semptomlar (%25), kas-iskelet ağrısı",
                "anemi, yorgunluk",
                "tromboembolik olaylar (arteriyel/venöz tromboz, DVT, pulmoner emboli)",
                "hipotansiyon (hızlı IV)",
                "nöbet, miyoklonus",
                "görsel bozukluklar",
                "anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Dissemine intravasküler koagülasyon: Antifibrinolitik tedavi gerektiren DIC'te aşırı dikkatle, deneyimli sağlık personeli gözetiminde. • Subaraknoid kanamada dikkat: serebral ödem ve enfarkt olabilir. • Düzeltilmemiş kardiyovasküler/serebrovasküler hastalıkta tromboz komplikasyonları nedeniyle dikkat. • Yüksek IV dozlarda (≥50 mg/kg) nöbet riski. • Sadece IV kullanım; enjektörleri net etiketle, karışıklığı önle. • CNS etkileri (baş dönmesi)."
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
            drugId = "fibrinogen_concentrate",
            nameTr = "Fibrinojen konsantresi (insan)",
            nameEn = "Fibrinogen concentrate (human)",
            genericName = "Fibrinogen, concentrate from human plasma",
            brandNames = listOf(
                "Fеѕiltу;",
                "Fibrygа;",
                "RiаЅΤAP"
            ),
            category = "coagulation",
            atcCode = null,
            drugClass = "Kan Ürünü Derivesi (Faktör I)",
            mechanismTr = "Fibrin pıhtı oluşumunu destekleyerek masif kanamalarda hemostaz sağlar.",
            adultDoses = mapOf("dozaj" to DoseInfo("• Edinsel fibrinojen eksikliği (Fibryga): IV: 4 g tek doz; kanayan hastalarda plazma fibrinojen ≤200 mg/dL veya tromboelastometri FIBTEM A10 ≤10 mm olduğunda gerektikçe tekrarlanabilir. Doz plazma fibrinojen düzeyi/viskoelastik teste, kanama şiddetine, vücut ağırlığına göre bireyselleştirilir. • Konjenital fibrinojen eksikliği: IV: Hedef fibrinojen 100 mg/dL (minör kanama), 150 mg/dL (majör kanama). Bazal bilinmiyorsa: 70 mg/kg. Bazal biliniyorsa: Doz (mg/kg) = [Hedef − ölçülen düzey] / 1.8 (Fesilty, Fibryga) veya / 1.7 (RiaSTAP).", null)),
            pediatricDoses = mapOf("dozaj" to DoseInfo("• Edinsel fibrinojen eksikliği (akut kanama): Çocuk <12 yaş: Fibryga: IV: 70 mg/kg; gerektikçe tekrarlanabilir. Çocuk ≥12 yaş ve Adölesan: Fibryga: IV: 50 mg/kg. • Konjenital fibrinojen eksikliği: Bazal bilinmiyorsa: Fibryga, RiaSTAP: IV: 70 mg/kg. Bazal biliniyorsa: RiaSTAP: Doz = [Hedef − ölçülen] / 1.7; Fibryga <12 yaş: / 1.4; ≥12 yaş: / 1.8. Tekrar dozlar: minör kanamada <80 mg/dL, majör kanamada <130 mg/dL altına düşerse.", null)),
            specialPopulations = emptyMap(),
            pharmacokinetics = PharmacokineticsInfo(
                onset = "belirtilmemiş",
                peak = "belirtilmemiş",
                duration = "belirtilmemiş",
                halfLife = "Biyolojik fibrinojene benzer; ~100 saat (biyolojik); Fesilty 53.8 saat; Fibryga (erişkin/≥12 yaş) 75.9 saat; RiaSTAP (≥16 yaş) 82.3 saat",
                elimination = "belirtilmemiş (Eliminasyon yeri: belirtilmemiş)"
            ),
            contraindications = listOf(
                "Bileşenlerine karşı bilinen aşırı duyarlılık"
            ),
            warnings = listOf(
                "Hipersensitivite: Reaksiyonlar (ürtiker, göğüste sıkışma, hışıltı, hipotansiyon, anafilaksi) olabilir; oluşursa tedavi hemen kesilir. • Trombotik olaylar: Edinsel veya konjenital eksiklikte spontan tromboz olabilir; tromboz riski göz önünde bulundurulur. • İnsan plazma ürünü: Bulaşıcı ajan (virüs, teorik CJD) taşıma potansiyeli. • Disfibrinojenemi tedavisinde endike değildir. • Bazı formlarda polisorbat 80."
            ),
            sideEffectsCommon = listOf(
                "Atriyal fibrilasyon (%29), anemi (%16), deliryum (%15)",
                "tromboembolik komplikasyonlar (%9",
                "MI, arteriyel tromboz, inme, DVT, DIC, pulmoner emboli)",
                "akut böbrek hasarı (%8), karaciğer fonksiyon bozukluğu (%7)",
                "hipersensitivite/anafilaksi."
            ),
            sideEffectsSerious = emptyList(),
            clinicalPearls = listOf(
                "Hipersensitivite: Reaksiyonlar (ürtiker, göğüste sıkışma, hışıltı, hipotansiyon, anafilaksi) olabilir; oluşursa tedavi hemen kesilir. • Trombotik olaylar: Edinsel veya konjenital eksiklikte spontan tromboz olabilir; tromboz riski göz önünde bulundurulur. • İnsan plazma ürünü: Bulaşıcı ajan (virüs, teorik CJD) taşıma potansiyeli. • Disfibrinojenemi tedavisinde endike değildir. • Bazı formlarda polisorbat 80."
            ),
            storage = null,
            preparation = null,
            maxMgkgWithEpi = null,
            maxMgkgWithoutEpi = null,
            absoluteMaxMg = null,
            sources = emptyList(),
            isPremium = false,
            requiresClinicalValidation = true
        )
    )
}
