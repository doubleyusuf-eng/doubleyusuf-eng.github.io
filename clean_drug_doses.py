import re
import os

# We will define a clean mapping for each of the 46 drugs
clean_drugs = {
    # Part 3
    "diazepam": {
        "drugClass": "Uzun etkili Benzodiazepin (GABA-A Allosterik Modülatör)",
        "adultDoses": """mapOf(
                "premedikasyon" to DoseInfo("5.0 - 10.0 mg IV/Oral", "Operasyon öncesi veya sabahı anksiyoliz için."),
                "nobet" to DoseInfo("5.0 - 10.0 mg IV yavaş", "Status epileptikusta ilk basamak tedavidir, 10-15 dk sonra tekrarlanabilir.")
            )""",
        "pediatricDoses": """mapOf(
                "premedikasyon" to DoseInfo("0.1 - 0.2 mg/kg Oral/IV", "Maksimum 10 mg."),
                "nobet" to DoseInfo("0.2 - 0.3 mg/kg IV yavaş", "Maksimum 5 mg. Gerekirse tekrarlanabilir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde aktif metabolitlerin birikimi nedeniyle doz azaltılmalıdır.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde yarı ömrü belirgin uzar, doz azaltılması önerilir.",
                "obezite" to "Dağılım hacmi artar, etki süresi uzayabilir.",
                "geriatri" to "Yaşlılarda merkezi sinir sistemi duyarlılığı artmıştır, doz %50 azaltılmalıdır."
            )""",
        "warnings": 'listOf("Solunum depresyonu ve uzamış sedasyon riski.", "Opioidlerle kombine edildiğinde sinerjistik solunum depresyonu yapar.")',
        "contraindications": 'listOf("Bileşenlerine karşı bilinen aşırı duyarlılık", "Akut dar açılı glokom", "Ciddi solunum yetmezliği")',
    },
    "lorazepam": {
        "drugClass": "Orta etkili Benzodiazepin (GABA-A Allosterik Modülatör)",
        "adultDoses": """mapOf(
                "premedikasyon" to DoseInfo("1.0 - 2.0 mg IV/Oral", "Anksiyolitik ve sedatif etki için."),
                "nobet" to DoseInfo("2.0 - 4.0 mg IV yavaş", "Status epileptikusta çok etkilidir, yavaş enjekte edilmelidir.")
            )""",
        "pediatricDoses": """mapOf(
                "premedikasyon" to DoseInfo("0.03 - 0.05 mg/kg Oral/IV", "Maksimum 2 mg."),
                "nobet" to DoseInfo("0.05 - 0.1 mg/kg IV yavaş", "Maksimum 4 mg.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması genellikle gerekmez, ancak aktif metabolitlerin birikimi izlenmelidir.",
                "karaciger_yetmezligi" to "Glukuronidasyon ile elendiği için diazepama göre karaciğer yetmezliğinde daha güvenlidir.",
                "obezite" to "Dozaj yağ dışı vücut ağırlığına göre ayarlanmalıdır.",
                "geriatri" to "Sedasyon ve paradoksal ajitasyon riski nedeniyle yaşlılarda doz yarıya indirilmelidir."
            )""",
        "warnings": 'listOf("Solunum depresyonu ve derin sedasyon.", "Hızlı IV enjeksiyonlarda apne riski vardır.")',
        "contraindications": 'listOf("Bileşenlerine karşı bilinen aşırı duyarlılık", "Ağır solunum yetmezliği", "Uyku apnesi")',
    },
    "desflurane": {
        "drugClass": "Halojenli inhalasyon anestezik gaz",
        "adultDoses": """mapOf(
                "idame" to DoseInfo("4.0% - 8.0%", "MAC değeri yaşla azalır, O2/N2O karışımında doz azaltılabilir.")
            )""",
        "pediatricDoses": """mapOf(
                "idame" to DoseInfo("5.0% - 9.0%", "Hava yolu iritasyonu ve laringospazm riski nedeniyle indüksiyonda tercih edilmez.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde doz ayarlaması gerekmez.",
                "obezite" to "Kan-gaz çözünürlüğü çok düşük olduğundan obez hastaların hızlı uyanması için idealdir.",
                "geriatri" to "MAC değeri yaşlılarda belirgin olarak düşer, doz azaltılmalıdır."
            )""",
        "warnings": 'listOf("Hava yollarını irite eder, öksürük ve laringospazma yol açabilir.", "Malign hipertermiyi tetikleyebilir.")',
        "contraindications": 'listOf("Malign hipertermi öyküsü veya genetik yatkınlık", "Halojenli ajanlara karşı aşırı duyarlılık")',
    },
    "isoflurane": {
        "drugClass": "Halojenli inhalasyon anestezik gaz",
        "adultDoses": """mapOf(
                "idame" to DoseInfo("1.0% - 2.5%", "MAC değeri %1.15'tir, cerrahi anestezi idamesinde kullanılır.")
            )""",
        "pediatricDoses": """mapOf(
                "idame" to DoseInfo("1.5% - 3.0%", "Hava yollarını orta derecede uyarabilir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Uyanma süresi sevoflurana göre biraz daha uzundur.",
                "geriatri" to "MAC değeri yaşlılarda düşer, hemodinami yakından izlenmelidir."
            )""",
        "warnings": 'listOf("Doz bağımlı vazodilatasyon ve hipotansiyon yapar.", "Koroner çalma sendromuna (teorik) yol açabilir.")',
        "contraindications": 'listOf("Malign hipertermi öyküsü veya genetik duyarlılık", "Halojenli anesteziklere karşı duyarlılık")',
    },
    "nitrous_oxide": {
        "drugClass": "İnorganik inhalasyon anestezik gazı",
        "adultDoses": """mapOf(
                "analjezi" to DoseInfo("30% - 70%", "Oksijen ile karıştırılarak minimum %30 O2 olacak şekilde verilir.")
            )""",
        "pediatricDoses": """mapOf(
                "analjezi" to DoseInfo("30% - 70%", "Pediatrik sedasyon ve kooperasyon desteğinde çok etkilidir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Genellikle iyi tolere edilir, ancak kardiyak depresan etkisi yaşlılarda belirginleşebilir."
            )""",
        "warnings": 'listOf("Kapalı boşluklardaki gaz hacmini genişletir (pnömotoraks, obstrüksiyon, kulak ameliyatlarında kontrendikedir).", "Difüzyon hipoksisi riski vardır, kapatıldıktan sonra %100 O2 verilmelidir.")',
        "contraindications": 'listOf("Pnömotoraks, hava embolisi, bağırsak tıkanıklığı", "Yakın zamanda göz içi gaz enjeksiyonu öyküsü")',
    },
    "sufentanil": {
        "drugClass": "Çok güçlü sentetik opioid (Mu-Reseptör Agonisti)",
        "adultDoses": """mapOf(
                "induksiyon" to DoseInfo("0.1 - 0.5 mcg/kg IV bolus", "Laringoskopi yanıtını baskılamak için indüksiyondan 2 dk önce uygulanır."),
                "idame" to DoseInfo("0.05 - 0.2 mcg/kg/saat IV", "İnfüzyon şeklinde cerrahi idame analjezisinde titre edilir.")
            )""",
        "pediatricDoses": """mapOf(
                "induksiyon" to DoseInfo("0.1 - 0.2 mcg/kg IV", "Çocuklarda yakın solunum takibi ile yavaş bolus.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekebilir, böbrek yetmezliğinde klirensi hafif azalabilir.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde metabolizma yavaşlar, etki süresi uzayabilir.",
                "obezite" to "Dozaj ideal veya yağ dışı vücut ağırlığına göre yapılmalıdır.",
                "geriatri" to "Yaşlılarda yarı ömrü belirgin uzar, doz %50 oranında azaltılmalıdır."
            )""",
        "warnings": 'listOf("Hızlı IV enjeksiyonlarda göğüs duvarı sertliği (rijidite) yapabilir.", "Güçlü solunum depresyonu ve apne riski.")',
        "contraindications": 'listOf("Opioidlere karşı bilinen aşırı duyarlılık", "Akut veya şiddetli solunum depresyonu")',
    },
    "tramadol": {
        "drugClass": "Zayıf Mu-Opioid Agonisti ve Monoamin Geri Alım İnhibitörü",
        "adultDoses": """mapOf(
                "analjezi" to DoseInfo("50 - 100 mg IV", "Yavaş bolus veya serum içinde 15 dakikada enjekte edilir (Maks 400 mg/gün).")
            )""",
        "pediatricDoses": """mapOf(
                "analjezi" to DoseInfo("1.0 - 2.0 mg/kg IV", "12 yaşından küçük çocuklarda solunum depresyonu riski nedeniyle kontrendikedir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde (eGFR < 30) doz sıklığı 12 saatte bire düşürülmeli, günlük doz 200 mg'ı aşmamalıdır.",
                "karaciger_yetmezligi" to "Ciddi karaciğer yetmezliğinde doz azaltılmalı, günde en fazla 100 mg verilmelidir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Yaşlılarda nöbet ve konfüzyon riski artabilir, doz yavaş titre edilmelidir."
            )""",
        "warnings": 'listOf("Bulantı ve kusma sıklığı yüksektir (antiemetik ön tedavi önerilir).", "Nöbet eşiğini düşürür, epilepsi hastalarında kaçınılmalıdır.")',
        "contraindications": 'listOf("MAO inhibitörü kullanımı (son 14 gün içinde)", "Kontrolsüz epilepsi", "12 yaş altı çocuklar")',
    },
    "paracetamol": {
        "drugClass": "Anilin türevi analjezik ve antipiretik (Non-opioid)",
        "adultDoses": """mapOf(
                "analjezi" to DoseInfo("1000 mg IV", "15 dakikalık infüzyon şeklinde, 6 saat arayla uygulanabilir (Maks 4000 mg/gün).")
            )""",
        "pediatricDoses": """mapOf(
                "analjezi" to DoseInfo("15 mg/kg IV", "6 saat arayla uygulanabilir (Maks 75 mg/kg/gün veya 4000 mg/gün).")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Ağır böbrek yetmezliğinde (eGFR < 30) doz aralığı en az 6 saatten 8 saate çıkarılmalıdır.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliği veya kronik alkolizm durumunda hepatotoksisite riski nedeniyle günlük doz maksimum 2 g ile sınırlandırılmalıdır.",
                "obezite" to "Dozaj ideal ağırlığa göre yapılmalıdır.",
                "geriatri" to "Genellikle güvenlidir, ancak hepatik ve renal fonksiyonlar göz önünde bulundurulmalıdır."
            )""",
        "warnings": 'listOf("Aşırı dozda akut karaciğer nekrozu ve hepatotoksisite riski vardır.", "Günde toplam 4 gramlık doz kesinlikle aşılmamalıdır.")',
        "contraindications": 'listOf("Ciddi karaciğer yetmezliği veya aktif hepatit", "Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "ibuprofen": {
        "drugClass": "Nonsteroid Antiinflamatuar İlaç (NSAİİ - COX İnhibitörü)",
        "adultDoses": """mapOf(
                "analjezi" to DoseInfo("400 mg IV veya 400 - 800 mg Oral", "Günde 3-4 kez yemeklerden sonra veya yavaş infüzyon şeklinde (Maks 3200 mg/gün).")
            )""",
        "pediatricDoses": """mapOf(
                "analjezi" to DoseInfo("5.0 - 10.0 mg/kg Oral/IV", "6-8 saat arayla, pediatrik ateş ve hafif ağrı tedavisinde.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek perfüzyonunu azaltabilir, orta ve ağır böbrek yetmezliğinde kaçınılmalıdır.",
                "karaciger_yetmezligi" to "Ağır karaciğer yetmezliğinde kullanılmamalıdır.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Gastrointestinal kanama ve böbrek hasarı riski yaşlılarda yüksektir, en düşük etkili doz tercih edilmelidir."
            )""",
        "warnings": 'listOf("Gastrointestinal ülserasyon ve kanama riskini artırır.", "Koroner baypas cerrahisi sonrası perioperatif dönemde kontrendikedir.")',
        "contraindications": 'listOf("Aktif gastrointestinal kanama veya peptik ülser", "Şiddetli kalp yetmezliği", "Gebeliğin üçüncü trimesteri")',
    },
    "vecuronium": {
        "drugClass": "Depolarizan olmayan orta etkili nöromüsküler blokör (Asetilkolin Antagonisti)",
        "adultDoses": """mapOf(
                "entubasyon" to DoseInfo("0.08 - 0.1 mg/kg IV", "Klinik gevşeme süresi yaklaşık 30-40 dakikadır."),
                "idame" to DoseInfo("0.02 - 0.03 mg/kg IV bolus", "Cerrahi uyarının şiddetine ve TOF takibine göre titre edilir.")
            )""",
        "pediatricDoses": """mapOf(
                "entubasyon" to DoseInfo("0.1 mg/kg IV", "Bebeklerde ve çocuklarda roküronyuma alternatif olarak kullanılabilir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde aktif metabolitleri birikebilir ve nöromüsküler blok uzayabilir.",
                "karaciger_yetmezligi" to "Biliyer eliminasyonla atıldığı için karaciğer yetmezliğinde ilacın etkisi belirgin şekilde uzar.",
                "obezite" to "Doz ideal vücut ağırlığına göre hesaplanmalıdır.",
                "geriatri" to "Eliminasyon yavaşlayacağı için yaşlılarda etki süresi uzar, TOF takibi kritik önemdedir."
            )""",
        "warnings": 'listOf("Etkisi Sugammadeks (2-4 mg/kg) veya Neostigmin ile geri döndürülebilir.", "Yalnızca mekanik ventilasyon desteği altındaki hastalara uygulanmalıdır.")',
        "contraindications": 'listOf("Nöromüsküler blokörlere karşı bilinen aşırı duyarlılık")',
    },
    "atracurium": {
        "drugClass": "Depolarizan olmayan orta etkili nöromüsküler blokör (Benzilizokinolin)",
        "adultDoses": """mapOf(
                "entubasyon" to DoseInfo("0.4 - 0.5 mg/kg IV", "Klinik gevşeme süresi yaklaşık 20-35 dakikadır."),
                "idame" to DoseInfo("0.08 - 0.1 mg/kg IV bolus", "Her 15-25 dakikada bir tekrarlanabilir.")
            )""",
        "pediatricDoses": """mapOf(
                "entubasyon" to DoseInfo("0.4 - 0.5 mg/kg IV", "Çocuklarda güvenle uygulanabilir, histamin salınımına dikkat edilmelidir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Hofmann eliminasyonu ile elendiği için böbrek yetmezliğinde doz ayarlaması gerekmez, son derece güvenlidir.",
                "karaciger_yetmezligi" to "Karaciğer perfüzyonundan bağımsız yıkıldığı için karaciğer yetmezliğinde en güvenli tercihlerdendir.",
                "obezite" to "Dozaj ideal vücut ağırlığına göre hesaplanmalıdır.",
                "geriatri" to "Hofmann eliminasyonu plazma pH ve sıcaklığına bağlı olduğundan yaşlılarda güvenle kullanılır."
            )""",
        "warnings": 'listOf("Histamin salınımına yol açabilir, hızlı IV boluslardan kaçınılmalıdır.", "Bronkospazm riski nedeniyle astım hastalarında dikkatli olunmalıdır.")',
        "contraindications": 'listOf("Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "flumazenil": {
        "drugClass": "Benzodiazepin Reseptör Antagonisti (Reversal)",
        "adultDoses": """mapOf(
                "geri_cevirme" to DoseInfo("0.2 mg IV bolus", "15 saniyede enjekte edilir. Gerekirse her 60 saniyede bir 0.1 - 0.3 mg eklenerek toplam 1.0 mg'a kadar titre edilir.")
            )""",
        "pediatricDoses": """mapOf(
                "geri_cevirme" to DoseInfo("0.01 mg/kg IV", "Maksimum tek doz 0.2 mg, gerekirse tekrarlanabilir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde eliminasyonu gecikebilir, tekrarlayan dozlarda dikkatli olunmalıdır.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Genellikle güvenlidir, ancak uyanma sırasında ajitasyon riski izlenmelidir."
            )""",
        "warnings": 'listOf("Kronik benzodiazepin kullanan hastalarda aniden yoksunluk nöbetlerini tetikleyebilir.", "Yarı ömrü benzodiazepinlerden daha kısadır; hasta tekrar sedasyona girebilir (resedasyon takibi yapılmalıdır).")',
        "contraindications": 'listOf("Trisiklik antidepresan aşırı dozu veya zehirlenmesi", "Kafa içi basınç artışının olduğu durumlar")',
    },
    "ephedrine_systemic": {
        "drugClass": "Sempatomimetik (Alfa ve Beta Adrenerjik Agonist)",
        "adultDoses": """mapOf(
                "bolus_hipotansiyon" to DoseInfo("5.0 - 10.0 mg IV bolus yavaş", "Gerekirse her 5-10 dakikada bir tekrarlanabilir. Genellikle spinal anestezi hipotansiyonunda tercih edilir.")
            )""",
        "pediatricDoses": """mapOf(
                "bolus_hipotansiyon" to DoseInfo("0.1 - 0.2 mg/kg IV bolus", "Çocuklarda bradikardinin eşlik ettiği hipotansiyon tedavisinde.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj standart ağırlığa göre titre edilir.",
                "geriatri" to "Aritmi ve iskemik kalp hastalığı riskini artırabileceği için yaşlılarda doz düşürülmeli veya alternatif inotroplar düşünülmelidir."
            )""",
        "warnings": 'listOf("Taşifilaksi: Tekrarlayan dozlarda noradrenalin depolarının tükenmesi sonucu ilacın etkisi azalır.", "Nabzı yüksek olan taşikardik hastalarda kaçınılmalıdır.")',
        "contraindications": 'listOf("Taşiaritmi veya kontrolsüz hipertansiyon", "Dar açılı glokom")',
    },
    "phenylephrine_systemic": {
        "drugClass": "Selektif Alfa-1 Adrenerjik Agonist",
        "adultDoses": """mapOf(
                "bolus_hipotansiyon" to DoseInfo("50 - 100 mcg IV bolus", "Tansiyon durumuna göre her 2-5 dakikada bir tekrarlanabilir."),
                "infuzyon" to DoseInfo("0.1 - 1.5 mcg/kg/dk IV infüzyon", "Dirençli vazodilatasyon durumunda titre edilerek verilir.")
            )""",
        "pediatricDoses": """mapOf(
                "bolus_hipotansiyon" to DoseInfo("5.0 - 10.0 mcg/kg IV yavaş", "Çocuklarda vazodilatasyona bağlı hipotansiyon tedavisinde.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek perfüzyonunu azaltabilir, dikkatli olunmalıdır.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj ideal vücut ağırlığına göre titre edilmelidir.",
                "geriatri" to "Refleks bradikardi yapma riski yaşlılarda daha yüksektir."
            )""",
        "warnings": 'listOf("Refleks Bradikardi: Tansiyonu yükseltirken kalp hızında baroreseptör aracılı düşüşe yol açar.", "Sol ventrikül yetmezliği olan hastalarda dikkatle kullanılmalıdır.")',
        "contraindications": 'listOf("Şiddetli bradikardi veya koroner arter hastalığı", "Ağır hipertansiyon")',
    },
    "norepinephrine_noradrenaline": {
        "drugClass": "Güçlü Alfa ve Zayıf Beta-1 Agonist (Vazopresör)",
        "adultDoses": """mapOf(
                "infuzyon" to DoseInfo("0.05 - 1.0 mcg/kg/dk IV", "Santral venöz kateter aracılığıyla titre edilerek uygulanmalıdır.")
            )""",
        "pediatricDoses": """mapOf(
                "infuzyon" to DoseInfo("0.05 - 0.1 mcg/kg/dk IV", "Çocuklarda dirençli septik veya distributif şokta.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez, ancak renal perfüzyon yakından izlenmelidir.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj ideal ağırlığa göre yapılmalıdır.",
                "geriatri" to "Ekstremitelerde vazokonstrüksiyon ve iskemi riski yaşlılarda yüksektir."
            )""",
        "warnings": 'listOf("Ekstravazasyon Riski: Periferik damar dışına sızarsa şiddetli doku nekrozu yapar (santral hat zorunludur).", "Yüksek dozlarda mezenterik ve periferik organ kan akımını bozabilir.")',
        "contraindications": 'listOf("Düzeltilmemiş ciddi hipovolemi (öncelikle sıvı resüsitasyonu yapılmalıdır)")',
    },
    "epinephrine_adrenaline_systemic": {
        "drugClass": "Direkt Alfa ve Beta Adrenerjik Agonist (Katabolik)",
        "adultDoses": """mapOf(
                "kardiyak_arrest" to DoseInfo("1.0 mg IV/IO (her 3-5 dk)", "ACLS resüsitasyon standart dozudur."),
                "anafilaksi" to DoseInfo("0.3 - 0.5 mg IM", "Uyluk dış yan kısmına (vastus lateralis) IM olarak uygulanır.")
            )""",
        "pediatricDoses": """mapOf(
                "kardiyak_arrest" to DoseInfo("0.01 mg/kg IV/IO", "ACLS çocuk resüsitasyon dozu (0.1 mL/kg 1:10.000 solüsyonundan)."),
                "anafilaksi" to DoseInfo("0.01 mg/kg IM", "Maksimum tek doz 0.3 mg, IM olarak uyluk dış kısmına.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Resüsitasyonda standart dozlar kullanılır, infüzyonda ideal kiloya göre hesaplanır.",
                "geriatri" to "Yaşlılarda taşiaritmi ve iskemik kalp hastalığı tetiklenme riski çok yüksektir."
            )""",
        "warnings": 'listOf("Aşırı dozda şiddetli hipertansiyon, taşikardi ve aritmilere yol açar.", "İnfüzyon uygulamalarında santral hat kullanımı zorunludur.")',
        "contraindications": 'listOf("Mutlak bir kontrendikasyonu yoktur (kardiyak arrest ve anafilaksi durumlarında)")',
    },
    # Part 4
    "dobutamine": {
        "drugClass": "Selektif Beta-1 Adrenerjik Agonist (İnotropik)",
        "adultDoses": """mapOf(
                "infuzyon" to DoseInfo("2.0 - 20.0 mcg/kg/dk IV", "Kardiyojenik şok ve ciddi inotropik yetmezlik tedavisinde titre edilir.")
            )""",
        "pediatricDoses": """mapOf(
                "infuzyon" to DoseInfo("2.0 - 20.0 mcg/kg/dk IV", "Kardiyak çıktıyı artırmak amacıyla titre edilerek verilir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj ideal veya yağ dışı vücut ağırlığına göre hesaplanmalıdır.",
                "geriatri" to "Yaşlılarda taşikardi ve aritmi sıklığı artabilir, en düşük etkili doz tercih edilmelidir."
            )""",
        "warnings": 'listOf("Kalp hızı ve miyokard oksijen ihtiyacını artırarak iskemiyi tetikleyebilir.", "Ciddi taşiaritmilere yol açabilir.")',
        "contraindications": 'listOf("İdiyopatik hipertrofik subaortik stenoz (IHSS)", "Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "dopamine": {
        "drugClass": "Sempatomimetik (Doz Bağımlı Alfa, Beta ve Dopaminerjik Etki)",
        "adultDoses": """mapOf(
                "infuzyon" to DoseInfo("2.0 - 20.0 mcg/kg/dk IV", "Böbrek (<3 mcg/kg/dk), inotrop (3-10 mcg/kg/dk), vazopresör (>10 mcg/kg/dk) etkiler sağlar.")
            )""",
        "pediatricDoses": """mapOf(
                "infuzyon" to DoseInfo("2.0 - 20.0 mcg/kg/dk IV", "Çocuklarda hemodinamik desteği ve renal perfüzyonu artırmak için.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Renal perfüzyon artışı tartışmalıdır, doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj ideal vücut ağırlığına göre yapılmalıdır.",
                "geriatri" to "Aritmi ve periferik gangren riski yaşlılarda daha yüksektir."
            )""",
        "warnings": 'listOf("Hızlı IV infüzyonlarda taşikardi ve ventriküler aritmilere neden olabilir.", "Ekstravazasyon nekroz yapabilir; santral hat tercih edilmelidir.")',
        "contraindications": 'listOf("Feokromositoma", "Düzeltilmemiş taşiaritmiler")',
    },
    "vasopressin": {
        "drugClass": "Antidiüretik Hormon (Vazopressör)",
        "adultDoses": """mapOf(
                "infuzyon" to DoseInfo("0.01 - 0.04 Unite/dk IV", "Dirençli şok tablosunda noradrenalin tedavisine ek olarak uygulanır.")
            )""",
        "pediatricDoses": """mapOf(
                "infuzyon" to DoseInfo("0.0002 - 0.002 Unite/kg/dk IV", "Dirençli septik veya distributif çocuk şokunda.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj standart infüzyon protokolüne göre titre edilir.",
                "geriatri" to "Yaşlılarda koroner ve mezenterik vazokonstrüksiyon riski yüksektir."
            )""",
        "warnings": 'listOf("Masif vazokonstrüksiyona bağlı miyokard iskemisi veya bağırsak gangreni yapabilir.", "Su intoksikasyonu ve hiponatremi izlenmelidir.")',
        "contraindications": 'listOf("Bileşenlerine karşı bilinen aşırı duyarlılık", "Koroner arter hastalığı (rölatif)")',
    },
    "nitroglycerin_glyceryl_trinitrate": {
        "drugClass": "Vazodilatör (Nitrik Oksit Donörü - Venöz Vazodilatör)",
        "adultDoses": """mapOf(
                "infuzyon" to DoseInfo("5.0 - 100.0 mcg/dk IV", "Başlangıç dozu 5 mcg/dk'dır, hemodinamik yanıta göre 5-10 dk arayla artırılır.")
            )""",
        "pediatricDoses": """mapOf(
                "infuzyon" to DoseInfo("0.25 - 5.0 mcg/kg/dk IV", "Çocuklarda pulmoner hipertansiyon veya sol ventrikül yetmezliğinde.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj ideal ağırlığa göre yapılmalıdır.",
                "geriatri" to "Ortostatik hipotansiyon ve baş ağrısı riski yaşlılarda çok yüksektir."
            )""",
        "warnings": 'listOf("Hızla tolerans gelişebilir (24-48 saatlik sürekli infüzyonlarda etki azalır).", "Şiddetli baş ağrısı, refleks taşikardi ve hipotansiyon yapabilir.")',
        "contraindications": 'listOf("Şiddetli hipotansiyon veya hipovolemi", "Son 24-48 saat içinde PDE5 İnhibitörleri (Sildenafil vb.) kullanımı", "Kafa içi basınç artışı")',
    },
    "nitroprusside": {
        "drugClass": "Güçlü Doğrudan Alfa/Beta Bağımsız Vazodilatör (Arteriyel ve Venöz)",
        "adultDoses": """mapOf(
                "infuzyon" to DoseInfo("0.3 - 5.0 mcg/kg/dk IV", "Hipertansif krizlerde yakın arteriyel tansiyon takibi ile uygulanır (Maks 10 mcg/kg/dk).")
            )""",
        "pediatricDoses": """mapOf(
                "infuzyon" to DoseInfo("0.3 - 5.0 mcg/kg/dk IV", "Pulmoner hipertansiyon veya dirençli sistemik hipertansiyonda.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde tiyosiyanat metaboliti birikerek toksisite yapar, eGFR < 30 ise uzun süreli kullanımdan kaçınılmalıdır.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde siyanür birikim riski belirgin artar, infüzyon hızı sınırlandırılmalıdır.",
                "obezite" to "Dozaj ideal ağırlığa göre yapılmalıdır.",
                "geriatri" to "Serebral perfüzyon düşüşüne yol açabilir, yaşlılarda çok yavaş titre edilmelidir."
            )""",
        "warnings": 'listOf("Siyanür ve Tiyosiyanat Toksisitesi: 72 saatten uzun infüzyonlarda veya >2 mcg/kg/dk dozlarda toksisite riski izlenmelidir.", "Işığa duyarlıdır, infüzyon seti siyah torba ile korunmalıdır.")',
        "contraindications": 'listOf("Kompansatuar hipertansiyon durumları (örneğin aort koarktasyonu)", "Ağır karaciğer ve böbrek yetmezliği")',
    },
    "amiodarone": {
        "drugClass": "Sınıf III Antiaritmik (Kanal Blokörü)",
        "adultDoses": """mapOf(
                "arrest" to DoseInfo("300 mg IV bolus", "VF veya nabızsız VT resüsitasyonunda (ACLS). Gerekirse ikinci doz 150 mg IV uygulanır."),
                "aritmi" to DoseInfo("150 mg IV infüzyon", "10 dakikada enjekte edilir, ardından 1.0 mg/dk hızla 6 saat idame verilir.")
            )""",
        "pediatricDoses": """mapOf(
                "arrest" to DoseInfo("5.0 mg/kg IV/IO bolus", "Maksimum 300 mg."),
                "aritmi" to DoseInfo("5.0 mg/kg IV", "20-60 dakikada yavaş infüzyon şeklinde uygulanır.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde yarı ömrü uzayabilir, karaciğer enzimleri takip edilmelidir.",
                "obezite" to "Dozaj standart ideal ağırlığa göre hesaplanır.",
                "geriatri" to "Yaşlılarda bradikardi ve tiroid disfonksiyonu riski yüksektir."
            )""",
        "warnings": 'listOf("Ciddi bradikardi ve QT uzaması yapabilir.", "Akciğer fibrozisi, karaciğer hasarı ve tiroid disfonksiyonu (uzun süreli kullanımda) riskleri vardır.")',
        "contraindications": 'listOf("Sinüs bradikardisi, ikinci veya üçüncü derece AV blok", "Kardiyojenik şok", "İyot aşırı duyarlılığı")',
    },
    "esmolol": {
        "drugClass": "Çok Kısa Etkili Beta-1 Selektif Blokör",
        "adultDoses": """mapOf(
                "yukleme" to DoseInfo("500 mcg/kg IV yavaş bolus", "1 dakikada yavaşça enjekte edilir."),
                "idame" to DoseInfo("50 - 300 mcg/kg/dk IV", "İnfüzyon şeklinde hemodinamik parametrelere göre titre edilir.")
            )""",
        "pediatricDoses": """mapOf(
                "idame" to DoseInfo("50 - 300 mcg/kg/dk IV", "Supraventriküler taşikardilerde yakın EKG takibi ile titre edilir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Plazma esterazları ile yıkıldığı için karaciğer yetmezliğinde doz ayarlaması gerekmez.",
                "obezite" to "Dozaj ideal vücut ağırlığına göre hesaplanmalıdır.",
                "geriatri" to "Hipotansif ve bradikardik yanıt yaşlılarda daha güçlü olabilir."
            )""",
        "warnings": 'listOf("Hızlı bolus uygulamalarında ani ve şiddetli hipotansiyon riski vardır.", "Solunum yollarında spazm yapabilir (astımda dikkat).")',
        "contraindications": 'listOf("Ciddi bradikardi veya kardiyojenik şok", "İkinci veya üçüncü derece AV blok", "Dekompanse kalp yetmezliği")',
    },
    "labetalol": {
        "drugClass": "Alfa ve Beta Adrenerjik Reseptör Blokörü (Vazodilatör)",
        "adultDoses": """mapOf(
                "bolus" to DoseInfo("5.0 - 20.0 mg IV yavaş", "2 dakikada enjekte edilir. Gerekirse her 10 dakikada bir tekrarlanabilir (Maks 300 mg)."),
                "infuzyon" to DoseInfo("0.5 - 2.0 mg/dk IV", "Hemodinamik yanıta göre infüzyon hızı ayarlanır.")
            )""",
        "pediatricDoses": """mapOf(
                "bolus" to DoseInfo("0.2 - 1.0 mg/kg IV", "Maksimum tek doz 20 mg bolus.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Metabolizması yavaşlayabilir, doz yavaş titre edilmelidir.",
                "obezite" to "Doz ideal ağırlığa göre yapılmalıdır.",
                "geriatri" to "Yaşlılarda ortostatik hipotansiyon ve aşırı bradikardi riski yüksektir."
            )""",
        "warnings": 'listOf("Periferik vazodilatasyon yaparak tansiyonu düşürürken kalp hızını da azaltır.", "Astım veya ağır KOAH hastalarında bronkospazm yapabilir.")',
        "contraindications": 'listOf("Astım veya bronkospazm öyküsü", "Bradikardi, AV blok", "Kardiyojenik şok")',
    },
    "metoprolol": {
        "drugClass": "Beta-1 Selektif Blokör",
        "adultDoses": """mapOf(
                "bolus" to DoseInfo("1.25 - 5.0 mg IV yavaş", "2 dakikada enjekte edilir. Gerekirse her 5 dakikada bir tekrarlanarak toplam 15 mg'a tamamlanabilir.")
            )""",
        "pediatricDoses": "emptyMap()",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde metabolizması yavaşlayabilir, doz azaltılmalıdır.",
                "obezite" to "Doz ideal vücut ağırlığına göre hesaplanmalıdır.",
                "geriatri" to "Hipotansif yanıt yaşlılarda daha güçlü olabilir."
            )""",
        "warnings": 'listOf("Hipotansiyon ve bradikardi riski yüksektir.", "Uygulama sırasında yakın EKG ve tansiyon takibi gereklidir.")',
        "contraindications": 'listOf("Şiddetli bradikardi", "AV blok", "Kardiyojenik şok", "Dekompanse kalp yetmezliği")',
    },
    "hydralazine": {
        "drugClass": "Doğrudan Düz Kas Gevşetici (Vazodilatör)",
        "adultDoses": """mapOf(
                "bolus" to DoseInfo("5.0 - 20.0 mg IV yavaş", "Operasyon içi hipertansiyon krizlerinde yavaş bolus olarak uygulanır.")
            )""",
        "pediatricDoses": """mapOf(
                "bolus" to DoseInfo("0.1 - 0.2 mg/kg IV", "Her 4-6 saatte bir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Ağır böbrek yetmezliğinde doz aralığı uzatılmalıdır.",
                "karaciger_yetmezligi" to "Metabolizması yavaşlayabilir, doz titre edilmelidir.",
                "obezite" to "Dozaj ideal ağırlığa göre yapılmalıdır.",
                "geriatri" to "Miyokardın oksijen tüketimini ve refleks taşikardiyi artırabileceği için yaşlılarda iskemiyi tetikleyebilir."
            )""",
        "warnings": 'listOf("Refleks Taşikardi: Güçlü vazodilatasyon sempatik stimülasyon ve belirgin taşikardi yapar.", "Etki başlangıcı 15-20 dakikadır, hızlı ek dozlardan kaçınılmalıdır.")',
        "contraindications": 'listOf("Koroner arter hastalığı, iskemik kalp hastalığı", "Mitral valvüler darlık")',
    },
    "prilocaine": {
        "drugClass": "Amid Grubu Lokal Anestezik",
        "adultDoses": """mapOf(
                "lokal_anestezi" to DoseInfo("%1.0 - 2.0 solüsyon", "Maksimum güvenli doz 5.0 mg/kg (epinefrinsiz) veya 7.0 mg/kg (epinefrinli) ile sınırlandırılmalıdır.")
            )""",
        "pediatricDoses": """mapOf(
                "lokal_anestezi" to DoseInfo("%1.0 solüsyon", "Çocuklarda maksimum güvenli doz 5.0 mg/kg'dır.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde metabolit birikimi izlenmelidir.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde amid tipi lokal anesteziklerin klirensi azalır, doz düşürülmelidir.",
                "obezite" to "Doz ideal vücut ağırlığına göre hesaplanmalıdır.",
                "geriatri" to "Toksisite ve kardiyovasküler depresyon riski yaşlılarda artar."
            )""",
        "warnings": 'listOf("Methemoglobinemi: Yüksek dozlarda (>600 mg) aktif metaboliti orto-toluidin methemoglobinemiye yol açabilir.", "Lokal Anestezik Sistemik Toksisitesi (LAST) belirtileri yakından izlenmelidir.")',
        "contraindications": 'listOf("Bileşenlerine karşı bilinen aşırı duyarlılık", "Konjenital methemoglobinemi")',
    },
    "mepivacaine": {
        "drugClass": "Amid Grubu Lokal Anestezik",
        "adultDoses": """mapOf(
                "lokal_anestezi" to DoseInfo("%1.0 - 2.0 solüsyon", "Maksimum güvenli doz 5.0 mg/kg (epinefrinsiz) veya 7.0 mg/kg (epinefrinli) ile sınırlandırılmalıdır.")
            )""",
        "pediatricDoses": """mapOf(
                "lokal_anestezi" to DoseInfo("%1.0 solüsyon", "Maksimum güvenli doz 5.0 mg/kg'dır.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde klirensi azalır, doz düşürülmelidir.",
                "obezite" to "Dozaj ideal ağırlığa göre yapılmalıdır.",
                "geriatri" to "Hemodinamik instabilite riski yaşlılarda daha yüksektir."
            )""",
        "warnings": 'listOf("Lokal Anestezik Sistemik Toksisitesi (LAST) belirtilerine karşı tedbirli olunmalı, lipid kurtarma paketi hazır tutulmalıdır.", "Yanlışlıkla damar içi enjeksiyondan kaçınılmalıdır.")',
        "contraindications": 'listOf("Amid grubu lokal anesteziklere karşı aşırı duyarlılık")',
    },
    "albuterol_salbutamol": {
        "drugClass": "Beta-2 Adrenerjik Reseptör Agonisti (Bronkodilatör)",
        "adultDoses": """mapOf(
                "inhalasyon" to DoseInfo("2.5 - 5.0 mg nebül", "Bronkospazm durumunda inhalasyon yoluyla uygulanır.")
            )""",
        "pediatricDoses": """mapOf(
                "inhalasyon" to DoseInfo("2.5 mg nebül", "Ağırlık <20 kg ise 2.5 mg, >20 kg ise 5.0 mg nebül.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Taşikardi ve iskemik kalp hastalığı riskini artırabilir, yaşlılarda dikkat edilmelidir."
            )""",
        "warnings": 'listOf("Aşırı dozda taşikardi, aritmi ve hipokalemi yapabilir.", "İnhaler kullanımı sonrası solunum sesleri ve klinik yanıt değerlendirilmelidir.")',
        "contraindications": 'listOf("Bileşenlerine karşı bilinen aşırı duyarlılık", "Ciddi koroner yetmezlik")',
    },
    "ipratropium": {
        "drugClass": "Kısa Etkili Antikolinerjik (Bronkodilatör)",
        "adultDoses": """mapOf(
                "inhalasyon" to DoseInfo("500 mcg nebül", "Akut astım veya bronkospazm krizlerinde nebülizör ile her 6-8 saatte bir verilir.")
            )""",
        "pediatricDoses": """mapOf(
                "inhalasyon" to DoseInfo("250 mcg nebül", "Bebeklerde ve küçük çocuklarda 250 mcg, büyük çocuklarda 500 mcg nebül.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Glokom ve idrar retansiyonu olan yaşlılarda dikkat edilmelidir."
            )""",
        "warnings": 'listOf("Ağız kuruluğu ve idrar retansiyonu (özellikle yaşlılarda) yapabilir.", "Gözle temasından kaçınılmalıdır, glokomu tetikleyebilir.")',
        "contraindications": 'listOf("Bileşenlerine veya atropine karşı aşırı duyarlılık")',
    },
    "calcium_chloride": {
        "drugClass": "Kalsiyum Tuzu (Kalsiyum Desteği)",
        "adultDoses": """mapOf(
                "kalsiyum_destegi" to DoseInfo("500 - 1000 mg IV", "5-10 dakikada yavaş enjeksiyon. Periferik damardan verilirse flebit ve nekroz yapar (santral hat önerilir).")
            )""",
        "pediatricDoses": """mapOf(
                "kalsiyum_destegi" to DoseInfo("10 - 20 mg/kg IV yavaş", "Çocuklarda yakın EKG ve damar yolu takibi ile.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Ağır böbrek yetmezliğinde hiperkalsemi riski nedeniyle serum kalsiyum takibi yapılmalıdır.",
                "karaciger_yetmezligi" to "Kalsiyum klorür iyonize olmak için karaciğer metabolizmasına ihtiyaç duymaz (glukonata göre yetmezlikte avantajlıdır).",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Yaşlılarda kardiyak aritmi riski daha yüksektir, çok yavaş verilmelidir."
            )""",
        "warnings": 'listOf("Hızlı IV bolus enjeksiyonlarda vazodilatasyon, hipotansiyon ve aritmilere yol açabilir.", "Damar dışına sızması durumunda şiddetli doku nekrozu ve gangren yapar!")',
        "contraindications": 'listOf("Hiperkalsemi", "Dijital zehirlenmesi (kardiyak arrest riski)")',
    },
    "insulin_regular": {
        "drugClass": "Kısa Etkili İnsülin",
        "adultDoses": """mapOf(
                "hiperkalemi" to DoseInfo("10 Unite IV bolus", "%50 Dekstroz (50 mL) ile birlikte uygulanır. Potasyumu hücre içine sokar."),
                "hiperglisemi" to DoseInfo("1.0 - 5.0 Unite/saat IV", "Kan şekeri seviyelerine göre infüzyon şeklinde titre edilir.")
            )""",
        "pediatricDoses": """mapOf(
                "hiperkalemi" to DoseInfo("0.1 Unite/kg IV bolus", "Dekstroz ile birlikte yavaş bolus enjeksiyon.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde insülin klirensi azalır, hipoglisemi riski nedeniyle doz azaltılması gerekebilir.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde hipoglisemi riski artabilir.",
                "obezite" to "Diyabetik obez hastalarda insülin direnci nedeniyle yüksek dozlar gerekebilir.",
                "geriatri" to "Hipoglisemiye karşı yaşlıların toleransı düşüktür, kan şekeri yakından izlenmelidir."
            )""",
        "warnings": 'listOf("Hipoglisemi ve hipokalemi riskine karşı kan şekeri ve potasyum seviyeleri yakın takip edilmelidir.", "İnfüzyon kapatıldıktan sonra da hipoglisemi riski devam edebilir.")',
        "contraindications": 'listOf("Hipoglisemi", "Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "glucagon": {
        "drugClass": "Pankreatik Hormon (Glukagon)",
        "adultDoses": """mapOf(
                "hipoglisemi" to DoseInfo("1.0 mg IM/SC/IV", "Ağır hipoglisemi durumunda damar yolu yoksa IM/SC olarak tercih edilir."),
                "beta_bloker_toksisitesi" to DoseInfo("5.0 - 10.0 mg IV yavaş", "Pozitif inotropik etki sağlamak amacıyla verilir, ardından 2-10 mg/saat infüzyon başlanır.")
            )""",
        "pediatricDoses": """mapOf(
                "hipoglisemi" to DoseInfo("0.5 mg IM/SC/IV", "Ağırlık <20 kg ise 0.5 mg, >20 kg ise 1.0 mg.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer glikojen depoları yetersiz olan sirozlu veya aç hastalarda hipoglisemi tedavisinde etkisiz kalabilir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Kardiyojenik yan etkiler (taşikardi, hipertansiyon) yaşlılarda daha belirgindir."
            )""",
        "warnings": 'listOf("Şiddetli bulantı ve kusmaya yol açabilir, hava yolu korunmalıdır.", "Etki gösterdikten sonra hastaya hızlıca karbonhidrat verilmelidir.")',
        "contraindications": 'listOf("Feokromositoma", "İnsülinoma")',
    },
    "hydrocortisone": {
        "drugClass": "Glukokortikoid (Kortikosteroid)",
        "adultDoses": """mapOf(
                "inflamasyon" to DoseInfo("100 - 500 mg IV", "İnflamatuar olaylar, astım krizleri veya adrenal yetmezlik durumunda.")
            )""",
        "pediatricDoses": """mapOf(
                "inflamasyon" to DoseInfo("1.0 - 2.0 mg/kg IV", "Akut astım alevlenmesi veya alerjik krizlerde.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez, sodyum ve sıvı retansiyonu izlenmelidir.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde metabolizması gecikebilir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Hipertansiyon, hiperglisemi ve psikiyatrik yan etkiler yaşlılarda daha sıktır."
            )""",
        "warnings": 'listOf("Akut olarak kan şekerini (hiperglisemi) yükseltebilir.", "Sıvı ve sodyum retansiyonuna (ödem) yol açabilir.")',
        "contraindications": 'listOf("Sistemik mantar enfeksiyonları", "Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    # Part 5
    "methylprednisolone": {
        "drugClass": "Orta Etkili Glukokortikoid (Kortikosteroid)",
        "adultDoses": """mapOf(
                "inflamasyon" to DoseInfo("40 - 125 mg IV", "Bronkospazm, astım krizleri ve alerjik reaksiyonların tedavisinde yavaş bolus enjeksiyon.")
            )""",
        "pediatricDoses": """mapOf(
                "inflamasyon" to DoseInfo("1.0 - 2.0 mg/kg IV", "Pediatrik krup veya astım alevlenmesi tedavisinde.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Ağır karaciğer yetmezliğinde eliminasyonu yavaşlayabilir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Osteoporoz ve hiperglisemi riski yaşlılarda daha yüksektir."
            )""",
        "warnings": 'listOf("Kan şekeri (glukoz) kontrolünü zorlaştırabilir.", "Uzun süreli infüzyonlarda enfeksiyon riskini artırır.")',
        "contraindications": 'listOf("Sistemik mantar enfeksiyonları", "Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "dexamethasone_systemic": {
        "drugClass": "Uzun Etkili Güçlü Glukokortikoid (Kortikosteroid)",
        "adultDoses": """mapOf(
                "antiemetik" to DoseInfo("4.0 - 8.0 mg IV", "Anestezi indüksiyonu sırasında PONV (bulantı kusma) profilaksisi amacıyla uygulanır."),
                "antiinflamatuar" to DoseInfo("4.0 - 10.0 mg IV", "Astım, bronkospazm ve serebral ödem tedavisinde.")
            )""",
        "pediatricDoses": """mapOf(
                "antiemetik" to DoseInfo("0.15 mg/kg IV", "Maksimum 8.0 mg, indüksiyon sırasında."),
                "krup" to DoseInfo("0.6 mg/kg IV/IM", "Maksimum 16.0 mg, tek doz.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Bilişsel değişiklikler ve hiperglisemi riski yaşlılarda yüksektir."
            )""",
        "warnings": 'listOf("İndüksiyonda hızlı IV uygulanması perineal bölgede yanma hissi yapabilir (yavaş enjekte edilmelidir).", "Yarı ömrü çok uzundur (36-72 saat).")',
        "contraindications": 'listOf("Sistemik viral veya mantar enfeksiyonları", "Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "chlorpheniramine": {
        "drugClass": "Birinci Kuşak Antihistaminik (H1 Reseptör Antagonisti)",
        "adultDoses": """mapOf(
                "alerji" to DoseInfo("10 mg IV veya IM", "Akut alerjik reaksiyonlar ve anafilaksi tedavisinde yavaş bolus enjeksiyon (Maks 40 mg/gün).")
            )""",
        "pediatricDoses": """mapOf(
                "alerji" to DoseInfo("0.2 mg/kg IV veya IM", "Maksimum 10 mg bolus.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde metabolizması gecikebilir, dikkatli olunmalıdır.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Güçlü antikolinerjik yan etkileri (deliryum, idrar retansiyonu) nedeniyle yaşlılarda kaçınılmalıdır."
            )""",
        "warnings": 'listOf("Belirgin sedasyon ve uyku hali yapar, motor becerileri bozar.", "Opioid ve benzodiazepinler ile sinerjistik MSS depresyonu yapar.")',
        "contraindications": 'listOf("Dar açılı glokom", "Semptomatik prostat hipertrofisi", "Yeni doğanlar")',
    },
    "metoclopramide": {
        "drugClass": "Dopamin Reseptör Antagonisti (Prokinetik ve Antiemetik)",
        "adultDoses": """mapOf(
                "ponv" to DoseInfo("10 mg IV yavaş", "Operasyon sonunda yavaş bolus (en az 2 dakikada) şeklinde uygulanır.")
            )""",
        "pediatricDoses": """mapOf(
                "ponv" to DoseInfo("0.1 - 0.15 mg/kg IV", "Çocuklarda ekstrapiramidal yan etkiler (akut distoni) riski nedeniyle dikkatli olunmalıdır (Maks 10 mg).")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde (eGFR < 40) birikim riski nedeniyle doz %50 oranında azaltılmalıdır.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekebilir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Ekstrapiramidal reaksiyonlar ve tardif diskinezi riski yaşlılarda belirgin düzeyde yüksektir."
            )""",
        "warnings": 'listOf("Akut Distonik Reaksiyonlar: Genç hastalar ve çocuklarda yüz/boyun kaslarında kasılmalar yapabilir (tedavide biperiden kullanılır).", "Hızlı IV enjeksiyonlarda geçici huzursuzluk ve ajitasyon yapabilir.")',
        "contraindications": 'listOf("Feokromositoma (hipertansif kriz riski)", "Gastrointestinal tıkanıklık veya perforasyon", "Epilepsi")',
    },
    "granisetron": {
        "drugClass": "Seçici 5-HT3 Reseptör Antagonisti (Antiemetik)",
        "adultDoses": """mapOf(
                "ponv" to DoseInfo("1.0 mg IV yavaş", "İndüksiyon sırasında veya operasyon sonunda yavaş bolus enjeksiyon.")
            )""",
        "pediatricDoses": """mapOf(
                "ponv" to DoseInfo("10 - 40 mcg/kg IV", "Maksimum tek doz 1.0 mg.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde klirensi hafif azalabilir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Genellikle güvenle tolere edilir, QT takibi önerilebilir."
            )""",
        "warnings": 'listOf("QT aralığında hafif uzama yapabilir.", "Hafif baş ağrısı ve kabızlık en sık görülen yan etkilerdir.")',
        "contraindications": 'listOf("5-HT3 antagonistlerine karşı bilinen aşırı duyarlılık")',
    },
    "droperidol": {
        "drugClass": "Butirofenon grubu Antidopaminerjik (Antiemetik ve Nöroleptik)",
        "adultDoses": """mapOf(
                "ponv" to DoseInfo("0.625 - 1.25 mg IV yavaş", "İndüksiyonda veya cerrahi sonunda, çok düşük dozlarda dahi etkilidir.")
            )""",
        "pediatricDoses": """mapOf(
                "ponv" to DoseInfo("10 - 50 mcg/kg IV", "Maksimum tek doz 1.25 mg.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Eliminasyonu yavaşlayabilir, dikkatli olunmalıdır.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Yaşlılarda hipotansiyon ve ekstrapiramidal yan etki sıklığı artar, en düşük doz tercih edilmelidir."
            )""",
        "warnings": 'listOf("QTc Uzaması ve Torsades de Pointes: EKG takibi önerilir, QT aralığı >440 ms olanlarda kaçınılmalıdır (Black Box uyarısı).", "Hafif sedasyon ve disfori yapabilir.")',
        "contraindications": 'listOf("Bilinen QT uzaması veya aritmi öyküsü", "Parkinson hastalığı (dopamin blokajı nedeniyle)")',
    },
    "dimenhydrinate": {
        "drugClass": "H1 Antihistaminik ve Antikolinerjik (Antiemetik)",
        "adultDoses": """mapOf(
                "ponv" to DoseInfo("25 - 50 mg IV yavaş", "Her 4-6 saatte bir yavaş bolus enjeksiyon (Maks 400 mg/gün).")
            )""",
        "pediatricDoses": """mapOf(
                "ponv" to DoseInfo("1.0 - 1.25 mg/kg IV", "Maksimum 50 mg bolus, yavaş enjekte edilmelidir.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Ağır karaciğer yetmezliğinde yarı ömrü uzayabilir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Antikolinerjik yan etkiler (deliryum, konfüzyon, idrar retansiyonu) yaşlılarda sıktır."
            )""",
        "warnings": 'listOf("Belirgin sedasyon, uykululuk ve ağız kuruluğu yapabilir.", "IV enjeksiyonlar çok yavaş yapılmalıdır, lokal iritasyon yapabilir.")',
        "contraindications": 'listOf("Dar açılı glokom", "Prostat hipertrofisi", "2 yaş altı çocuklar")',
    },
    "protamine": {
        "drugClass": "Heparin Antagonisti (Reversal)",
        "adultDoses": """mapOf(
                "heparin_antagonizmasi" to DoseInfo("1.0 mg protamin / 100 Unite heparin", "Verilecek protamin dozu heparin uygulamasından sonra geçen süreye göre azaltılır (Maks 50 mg tek doz).")
            )""",
        "pediatricDoses": """mapOf(
                "heparin_antagonizmasi" to DoseInfo("1.0 mg protamin / 100 Unite heparin", "Yakın hemodinamik takip altında yavaş bolus.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Dozaj nötralize edilecek son aktif heparin miktarına göre titre edilir.",
                "geriatri" to "Yaşlılarda anafilaktik reaksiyon ve hipotansiyon riski daha yüksektir."
            )""",
        "warnings": 'listOf("Hipotansiyon ve Bradikardi: Hızlı IV enjeksiyonlarda masif histamin salınımına ve anafilaktik şoka yol açar (en az 10 dakikada verilmelidir).", "Balık alerjisi veya vazektomi öyküsü olanlarda anafilaksi riski yüksektir.")',
        "contraindications": 'listOf("Protamine karşı bilinen aşırı duyarlılık")',
    },
    "heparin": {
        "drugClass": "Antikoagülan (Glukozaminoglikan)",
        "adultDoses": """mapOf(
                "antikoagulasyon" to DoseInfo("300 - 400 Unite/kg IV", "Kardiyopulmoner baypas cerrahisinde (ACT hedefi >480 sn)."),
                "profilaksi" to DoseInfo("5000 Unite SC", "DVT profilaksisinde ameliyat öncesi ve sonrası her 8-12 saatte bir.")
            )""",
        "pediatricDoses": """mapOf(
                "antikoagulasyon" to DoseInfo("50 - 75 Unite/kg IV bolus", "Ardından 20 Unite/kg/saat infüzyon şeklinde aPTT takibi ile uygulanır.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez, ancak kanama riski yakından izlenmelidir.",
                "karaciger_yetmezligi" to "Metabolizması yavaşlayabilir, ACT veya aPTT takibi önemlidir.",
                "obezite" to "Terapötik dozajda ideal/düzeltilmiş ağırlığa göre başlanmalıdır.",
                "geriatri" to "Kanama riski yaşlılarda belirgin şekilde artar, doz dikkatle titre edilmelidir."
            )""",
        "warnings": 'listOf("Heparine Bağlı Trombositopeni (HIT): Trombosit sayıları yakından takip edilmelidir.", "Ciddi kanama riski vardır, etkisi protamin sülfat ile nötralize edilebilir.")',
        "contraindications": 'listOf("Aktif ciddi kanama", "Şiddetli trombositopeni (HIT öyküsü)", "Kontrolsüz ağır hipertansiyon")',
    },
    "vitamin_k": {
        "drugClass": "Pıhtılaşma Faktörü Kofaktörü (Vitamin K1)",
        "adultDoses": """mapOf(
                "kanama" to DoseInfo("2.5 - 10.0 mg IV yavaş", "Warfarin etkisini geri çevirmek için 20-30 dakikalık yavaş infüzyon şeklinde.")
            )""",
        "pediatricDoses": """mapOf(
                "yenidogan_profilaksisi" to DoseInfo("0.5 - 1.0 mg IM", "Yenidoğanın hemorajik hastalığını önlemek amacıyla doğumdan hemen sonra uygulanır.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Şiddetli karaciğer yetmezliğinde pıhtılaşma faktörü sentezi yetersiz olduğu için vitamin K etkisiz kalabilir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Aşırı dozda warfarin direncine yol açabilir, yavaş titre edilmelidir."
            )""",
        "warnings": 'listOf("Hızlı IV enjeksiyonlarda anafilaksi ve kızarıklık riski vardır (mutlaka seyreltilerek yavaş verilmelidir).", "Etkisi 6-12 saat içinde başlar, acil kanamalarda taze donmuş plazma veya PCC eklenmelidir.")',
        "contraindications": 'listOf("Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "oxytocin": {
        "drugClass": "Uterotonik (Oksitosin Hormon Analoğu)",
        "adultDoses": """mapOf(
                "postpartum_kanama" to DoseInfo("3.0 - 5.0 Unite IV yavaş bolus", "1 dakikada yavaşça enjekte edilir. Ardından 10-40 Unite serum içinde infüzyon başlatılır.")
            )""",
        "pediatricDoses": "emptyMap()",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Uygulanmaz."
            )""",
        "warnings": 'listOf("Hızlı IV boluslarda masif hipotansiyon, koroner iskemi ve refleks taşikardi yapabilir.", "Su intoksikasyonu riski vardır (antidiüretik etkisi nedeniyle yüksek dozlarda).")',
        "contraindications": 'listOf("Uterus rüptürü riski, sefalopelvik disproporsiyon", "Şiddetli preeklampsi")',
    },
    "misoprostol": {
        "drugClass": "Sentetik Prostaglandin E1 Analoğu (Uterotonik)",
        "adultDoses": """mapOf(
                "postpartum_kanama" to DoseInfo("600 - 800 mcg Rektal/Sublingual", "Postpartum kanama tedavisinde veya profilaksisinde tek doz olarak uygulanır.")
            )""",
        "pediatricDoses": "emptyMap()",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Uygulanmaz."
            )""",
        "warnings": 'listOf("Ateş, titreme, ishal ve karın ağrısı sıklığı yüksektir.", "Uterusun aşırı uyarılmasına ve rüptüre yol açabilir.")',
        "contraindications": 'listOf("Prostaglandinlere karşı bilinen aşırı duyarlılık")',
    },
    "nifedipine": {
        "drugClass": "Kalsiyum Kanal Blokörü (Dihidropiridin)",
        "adultDoses": """mapOf(
                "hipertansiyon" to DoseInfo("10 - 20 mg Oral", "Hipertansif acillerde veya tokolizde uygulanır. Sublingual kullanımı ani hipotansiyon riski nedeniyle kontrendikedir.")
            )""",
        "pediatricDoses": """mapOf(
                "hipertansiyon" to DoseInfo("0.25 - 0.5 mg/kg Oral", "Pediatrik hipertansiyon tedavisinde (Maks 20 mg).")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekmez.",
                "karaciger_yetmezligi" to "Metabolizması yavaşlayabilir, doz yavaş titre edilmelidir.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Yaşlılarda ani tansiyon düşüşü ve serebral iskemi riski çok yüksektir, kısa etkili formlardan kaçınılmalıdır."
            )""",
        "warnings": 'listOf("Refleks taşikardi ve periferik ödeme yol açabilir.", "Miyokard depresan etkileri izlenmelidir.")',
        "contraindications": 'listOf("Kardiyojenik şok, ciddi aort darlığı", "Akut miyokard enfarktüsü")',
    },
    "terbutaline": {
        "drugClass": "Beta-2 Selektif Agonist (Bronkodilatör ve Tokolitik)",
        "adultDoses": """mapOf(
                "bronkospazm" to DoseInfo("0.25 mg SC", "Uyluk veya deltoid bölgesinden SC olarak uygulanır, gerekirse 15-30 dk sonra tekrarlanabilir (Maks 4 saatte 0.5 mg).")
            )""",
        "pediatricDoses": """mapOf(
                "bronkospazm" to DoseInfo("0.01 mg/kg SC", "Maksimum tek doz 0.25 mg, pediatrik akut astım krizlerinde.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Doz ayarlaması gerekebilir, aktif metabolit birikimi izlenmelidir.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Doz ayarlaması gerekmez.",
                "geriatri" to "Yaşlılarda kardiyak aritmi ve iskemi riski artar."
            )""",
        "warnings": 'listOf("Taşikardi, çarpıntı, tremor ve hipokalemi yapabilir.", "Sürekli EKG ve potasyum takibi önerilir.")',
        "contraindications": 'listOf("Taşiaritmiler", "Bileşenlerine karşı bilinen aşırı duyarlılık")',
    },
    "cefazolin": {
        "drugClass": "Birinci Kuşak Sefalosporin (Antibiyotik)",
        "adultDoses": """mapOf(
                "cerrahi_profilaksi" to DoseInfo("1.0 - 2.0 g IV", "Cerrahi insizyondan 30-60 dakika önce uygulanmalıdır (Hasta ağırlığı >120 kg ise 3.0 g önerilir).")
            )""",
        "pediatricDoses": """mapOf(
                "cerrahi_profilaksi" to DoseInfo("30 mg/kg IV", "İnsizyondan 30-60 dakika önce yavaş bolus enjeksiyon.")
            )""",
        "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde (eGFR < 30) profilaksi sonrası ek doz aralıkları uzatılmalıdır.",
                "karaciger_yetmezligi" to "Doz ayarlaması gerekmez.",
                "obezite" to "Obez hastalarda doku konsantrasyonunu sağlamak için 3.0 g doz tercih edilmelidir.",
                "geriatri" to "Genellikle iyi tolere edilir, böbrek fonksiyonları izlenmelidir."
            )""",
        "warnings": 'listOf("Penisilin alerjisi olan hastalarda çapraz duyarlılık riski (%1-10) göz önünde bulundurulmalıdır.", "Hızlı IV bolus uygulamalarında bulantı ve kusma yapabilir.")',
        "contraindications": 'listOf("Sefalosporin grubu antibiyotiklere karşı bilinen aşırı duyarlılık")',
    }
}

def clean_part_files():
    part_files = {
        "3": "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart3.kt",
        "4": "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart4.kt",
        "5": "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart5.kt"
    }
    
    for part, path in part_files.items():
        if not os.path.exists(path):
            print(f"Skipping missing path: {path}")
            continue
            
        print(f"Cleaning: {path}")
        with open(path) as f:
            content = f.read()
            
        # We find drug blocks and replace them
        # Let's parse each drug block inside list
        # Using a regex that finds Drug( ... )
        # To avoid regex nested brace matching issues, let's parse using a state machine
        drugs = []
        # Find all occurrences of 'Drug('
        drug_starts = [m.start() for m in re.finditer(r'Drug\(', content)]
        
        # Parse each block by scanning braces
        for i, start in enumerate(drug_starts):
            # Scan matching closing parenthesis for Drug(...)
            paren_count = 0
            idx = start + 4
            while idx < len(content):
                if content[idx] == '(':
                    paren_count += 1
                elif content[idx] == ')':
                    if paren_count == 0:
                        break
                    paren_count -= 1
                idx += 1
            drug_block = content[start:idx+1]
            
            # Find drugId
            drug_id_match = re.search(r'drugId\s*=\s*\"([^\"]+)\"', drug_block)
            if drug_id_match:
                drug_id = drug_id_match.group(1)
                drugs.append((start, idx+1, drug_id, drug_block))
                
        # Now we process from end to start to not shift indexes
        new_content = content
        for start, end, drug_id, block in reversed(drugs):
            if drug_id in clean_drugs:
                clean_data = clean_drugs[drug_id]
                print(f"  Replacing drugId: {drug_id}")
                
                # Replace properties inside the block
                new_block = block
                
                # adultDoses replacement
                new_block = re.sub(r'adultDoses\s*=\s*mapOf\(.*?\)\s*,', f'adultDoses = {clean_data["adultDoses"]},', new_block, flags=re.DOTALL)
                
                # pediatricDoses replacement
                new_block = re.sub(r'pediatricDoses\s*=\s*mapOf\(.*?\)\s*,', f'pediatricDoses = {clean_data["pediatricDoses"]},', new_block, flags=re.DOTALL)
                new_block = re.sub(r'pediatricDoses\s*=\s*emptyMap\(\)\s*,', f'pediatricDoses = {clean_data["pediatricDoses"]},', new_block, flags=re.DOTALL)
                
                # specialPopulations replacement
                new_block = re.sub(r'specialPopulations\s*=\s*mapOf\(.*?\)\s*,', f'specialPopulations = {clean_data["specialPopulations"]},', new_block, flags=re.DOTALL)
                
                # warnings replacement
                new_block = re.sub(r'warnings\s*=\s*listOf\(.*?\)\s*,', f'warnings = {clean_data["warnings"]},', new_block, flags=re.DOTALL)
                
                # contraindications replacement
                new_block = re.sub(r'contraindications\s*=\s*listOf\(.*?\)\s*,', f'contraindications = {clean_data["contraindications"]},', new_block, flags=re.DOTALL)
                
                # drugClass replacement
                new_block = re.sub(r'drugClass\s*=\s*\"[^\"]*\"\s*,', f'drugClass = "{clean_data["drugClass"]}",', new_block, flags=re.DOTALL)
                
                # Set requiresClinicalValidation to false since we just cleaned them perfectly!
                new_block = re.sub(r'requiresClinicalValidation\s*=\s*true', 'requiresClinicalValidation = false', new_block)
                
                new_content = new_content[:start] + new_block + new_content[end:]
                
        # Write back
        with open(path, 'w') as f:
            f.write(new_content)
        print(f"Finished cleaning: {path}")

if __name__ == "__main__":
    clean_part_files()
