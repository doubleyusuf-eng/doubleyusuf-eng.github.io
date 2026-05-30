import os
import re
import json

ORIGINAL_SEED_FILE = "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugs.kt"
PARSED_JSON_FILE = "parsed_drugs.json"
OUTPUT_DIR = "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository"

DRUG_MAPPINGS = {
    # 1. Induction / Sedatives
    "thiopental": {
        "name_tr": "Tiyopental", "name_en": "Thiopental", "category": "induction",
        "class_tr": "Barbitürat grubu kısa etkili genel anestezik",
        "mechanism_tr": "GABA-A reseptörü üzerinden klor kanallarını açık tutarak MSS depresyonu sağlar.",
        "pearls": ["Tiyopental (Pentothal), kafa içi basıncını düşürür ve beyin koruyucu etkisi vardır.", "Enjeksiyon ağrısı yapabilir, intraarteriyel kaçışta şiddetli spazm ve nekroz riski vardır!"]
    },
    "diazepam": {
        "name_tr": "Diazepam", "name_en": "Diazepam", "category": "induction",
        "class_tr": "Uzun etkili Benzodiazepin",
        "mechanism_tr": "GABA-A reseptörüne allosterik olarak bağlanarak GABA'nın inhibitör etkisini artırır.",
        "pearls": ["Antikonvülzan ve kas gevşetici etkisi çok güçlüdür.", "Aktif metabolitleri nedeniyle etkisi çok uzundur, yaşlılarda birikir."]
    },
    "lorazepam": {
        "name_tr": "Lorazepam", "name_en": "Lorazepam", "category": "induction",
        "class_tr": "Orta etkili Benzodiazepin",
        "mechanism_tr": "GABA-A reseptörüne allosterik bağlanarak inhibitör GABAerjik sinaps iletimini kolaylaştırır.",
        "pearls": ["Premedikasyonda ve status epileptikusta sık kullanılır.", "Karaciğer yetmezliğinde konjugasyonla elendiği için diazepama göre daha güvenlidir."]
    },
    # 2. Inhalational
    "desflurane": {
        "name_tr": "Desfluran", "name_en": "Desflurane", "category": "inhalational",
        "class_tr": "Halojenli inhalasyon anestezik gaz",
        "mechanism_tr": "Merkezi sinir sistemindeki sinaptik iletimi modüle eder, MAC değeri %6'dır.",
        "pearls": ["Kan-gaz çözünürlüğü en düşük ajandır, uyanma son derece hızlıdır.", "Solunum yollarını irite eder, bronkospazm riski nedeniyle indüksiyonda kullanılmaz."]
    },
    "isoflurane": {
        "name_tr": "İzofluran", "name_en": "Isoflurane", "category": "inhalational",
        "class_tr": "Halojenli inhalasyon anestezik gaz",
        "mechanism_tr": "Merkezi sinir sistemindeki sinaptik iletimi modüle eder, MAC değeri %1.15'tir.",
        "pearls": ["Kardiyoprotektif özellikleri ve ucuz olması nedeniyle sık tercih edilir.", "Koroner çalma sendromu yapabilir (teorik)."]
    },
    "nitrous_oxide": {
        "name_tr": "Nitröz Oksit", "name_en": "Nitrous Oxide", "category": "inhalational",
        "class_tr": "İnhalasyon anestezi gazı (Gülme gazı)",
        "mechanism_tr": "NMDA reseptör antagonisti ve zayıf anestezik, güçlü analjeziktir, MAC değeri %104'tür.",
        "pearls": ["Güçlü analjezik etkisi vardır, ancak tek başına anestezi sağlayamaz.", "Kapalı boşluklardaki (pnömotoraks, kulak cerrahileri vb.) gaz hacmini artırır, bu durumlarda kontrendikedir!"]
    },
    # 3. Opioids
    "alfentanil": {
        "name_tr": "Alfentanil", "name_en": "Alfentanil", "category": "opioid",
        "class_tr": "Kısa etkili sentetik opioid analjezik",
        "mechanism_tr": "Mu-opioid reseptör agonistidir.",
        "pearls": ["Etki başlangıcı en hızlı olan opioiddir, günübirlik girişimsel işlemlerde tercih edilir.", "Göz içi basıncını düşürür, katarakt cerrahisinde etkilidir."]
    },
    "sufentanil": {
        "name_tr": "Sufentanil", "name_en": "Sufentanil", "category": "opioid",
        "class_tr": "Çok güçlü sentetik opioid analjezik",
        "mechanism_tr": "Mu-opioid reseptör agonistidir (Fentanilden 5-10 kat güçlü).",
        "pearls": ["Morfine göre 500-1000 kat daha güçlüdür, majör kalp ve beyin cerrahisinde hemodinamik stabilite sağlar.", "Dozlama mikrogram (mcg) cinsindendir."]
    },
    "tramadol": {
        "name_tr": "Tramadol", "name_en": "Tramadol", "category": "opioid",
        "class_tr": "Zayıf opioid analjezik ve SNRI",
        "mechanism_tr": "Mu-opioid agonisti ve norepinefrin/serotonin geri alım inhibitörüdür.",
        "pearls": ["Bulantı kusma yapma potansiyeli çok yüksektir.", "Serotonerjik etkisi nedeniyle MAO inhibitörleri ile kullanıldığında serotonin sendromu yapabilir."]
    },
    "meperidine": {
        "name_tr": "Petidin (Meperidin)", "name_en": "Meperidine (Pethidine)", "category": "opioid",
        "class_tr": "Sentetik opioid analjezik",
        "mechanism_tr": "Mu-opioid reseptör agonistidir. Antikolinerjik etkileri vardır, titremeyi önler.",
        "pearls": ["Postoperatif titreme tedavisinde (titreme önleyici olarak) altın standarttır.", "Aktif metaboliti normeperidin birikerek nöbete yol açabilir, böbrek yetmezliğinde kaçınılmalıdır."]
    },
    # 4. Non-Opioid Analgesics
    "acetaminophen_paracetamol": {
        "name_tr": "Parasetamol", "name_en": "Paracetamol (Acetaminophen)", "category": "nonop_analgesic",
        "class_tr": "Non-opioid analjezik ve antipiretik",
        "mechanism_tr": "Sentral prostaglandin sentezini inhibe eder.",
        "pearls": ["Karaciğer toksisitesini önlemek için günlük maksimum doz 4 gramı aşmamalıdır.", "IV infüzyon mutlaka 15 dakikada yavaşça yapılmalıdır."]
    },
    "metamizole": {
        "name_tr": "Metamizol (Novalgin)", "name_en": "Metamizole (Dipyrone)", "category": "nonop_analgesic",
        "class_tr": "Non-opioid analjezik ve antispazmodik",
        "mechanism_tr": "COX inhibitörüdür, düz kas gevşetici etkisi de bulunur.",
        "pearls": ["Hızlı enjeksiyon ciddi hipotansiyona yol açar, yavaş IV infüzyon yapılmalıdır.", "Kemik iliği supresyonu ve agranülositoz riski (nadir) nedeniyle yakından izlenmelidir."]
    },
    "dexketoprofen": {
        "name_tr": "Deksketoprofen", "name_en": "Dexketoprofen", "category": "nonop_analgesic",
        "class_tr": "NSAID (Nonsteroidal Anti-inflamatuar İlaç)",
        "mechanism_tr": "COX-1 ve COX-2 inhibitörüdür.",
        "pearls": ["Güçlü analjezik etkisi vardır, akut postoperatif ağrıda sık tercih edilir.", "Renal perfüzyonu bozabileceği için hidrasyonu kötü hastalarda dikkat edilmelidir."]
    },
    "diclofenac": {
        "name_tr": "Diklofenak", "name_en": "Diclofenac", "category": "nonop_analgesic",
        "class_tr": "NSAID",
        "mechanism_tr": "COX-1 ve COX-2 inhibitörüdür.",
        "pearls": ["Böbrek taşı (renal kolik) ve postoperatif ağrıda çok etkilidir.", "Akut böbrek yetmezliği ve GİS kanama riski yakından izlenmelidir."]
    },
    "ibuprofen": {
        "name_tr": "İbuprofen", "name_en": "Ibuprofen", "category": "nonop_analgesic",
        "class_tr": "NSAID",
        "mechanism_tr": "COX-1 ve COX-2 inhibitörüdür.",
        "pearls": ["Pediatrik ve yetişkin postoperatif ağrı yönetiminde güvenilir bir seçenektir.", "Yemeklerle veya bol suyla alınmalıdır."]
    },
    "ketorolac": {
        "name_tr": "Ketorolak", "name_en": "Ketorolac", "category": "nonop_analgesic",
        "class_tr": "NSAID",
        "mechanism_tr": "Güçlü COX-1 ve COX-2 inhibitörüdür.",
        "pearls": ["Analjezik gücü orta doz opioidlere eşdeğerdir.", "GİS kanama ve renal toksisite riski yüksek olduğu için ardışık kullanımı en fazla 5 günle sınırlandırılmalıdır."]
    },
    # 5. Muscle Relaxants
    "vecuronium": {
        "name_tr": "Veküronyum", "name_en": "Vecuronium", "category": "nmb",
        "class_tr": "Orta etkili depolarizan olmayan NMB",
        "mechanism_tr": "Kompetitif nikotinik asetilkolin reseptör antagonistidir.",
        "pearls": ["Sugammadeks veya neostigmin ile etkisi geri döndürülebilir.", "Aktif metaboliti böbrek yetmezliğinde uzamış bloğa yol açabilir."]
    },
    "atracurium": {
        "name_tr": "Atrakuryum", "name_en": "Atracurium", "category": "nmb",
        "class_tr": "Orta etkili depolarizan olmayan NMB",
        "mechanism_tr": "Kompetitif nikotinik asetilkolin reseptör antagonistidir. Hofmann eliminasyonu ile atılır.",
        "pearls": ["Sıcaklık ve pH'a bağlı spontaneous (Hofmann) eliminasyonla yıkılır.", "Metaboliti laudanosin çok yüksek dozlarda santral sinir sistemi stimülasyonu yapabilir."]
    },
    # 6. Reversal & Anticholinergics
    "flumazenil": {
        "name_tr": "Flumazenil", "name_en": "Flumazenil", "category": "reversal",
        "class_tr": "Benzodiazepin Antagonisti",
        "mechanism_tr": "GABA-A reseptörü benzodiazepin bağlanma bölgesinde kompetitif antagonisttir.",
        "pearls": ["Benzodiazepin doz aşımında veya sedasyonu sonlandırmada saniyeler içinde etki gösterir.", "Kronik benzodiazepin kullananlarda veya trisiklik antidepresan alanlarda nöbeti tetikleyebilir!"]
    },
    # 7. Vasopressors & Inotropes
    "dobutamine": {
        "name_tr": "Dobutamin", "name_en": "Dobutamine", "category": "vasopressor",
        "class_tr": "Pozitif İnotrop (Sempatomimetik)",
        "mechanism_tr": "Esas olarak Beta-1 adrenerjik reseptörleri uyararak kasılma gücünü artırır.",
        "pearls": ["Kardiyojenik şokta ve kalp yetmezliğinde kalp debisini artırmak için ilk tercihlerdendir.", "Taşikardi ve ventriküler aritmiyi tetikleyebilir."]
    },
    "dopamine": {
        "name_tr": "Dopamin", "name_en": "Dopamine", "category": "vasopressor",
        "class_tr": "İnotrop ve Vazopressör",
        "mechanism_tr": "Düşük dozda D1 (renal vazodilatasyon), orta dozda Beta-1 (inotrop), yüksek dozda Alfa-1 (vazokonstrüksiyon) uyarır.",
        "pearls": ["Sempatik uyarım sağlayarak hem nabzı hem tansiyonu yükseltir.", "Son kılavuzlarda septik şokta noradrenaline göre aritmojenik etkisi yüksek olduğu için ikinci plana düşmüştür."]
    },
    "vasopressin": {
        "name_tr": "Vazopressin (Antidiüretik Hormon)", "name_en": "Vasopressin", "category": "vasopressor",
        "class_tr": "Vazopressör Hormon",
        "mechanism_tr": "V1 reseptörlerini uyararak güçlü periferik vazokonstrüksiyon yapar.",
        "pearls": ["Asidoz durumlarında dahi vazokonstrüktör etkisini korur, catecholamine dirençli septik şokta tercih edilir.", "Kardiyak arrest resüsitasyonunda standart kılavuzlarda yer alır."]
    },
    "nitroprusside": {
        "name_tr": "Sodyum Nitroprussid", "name_en": "Sodium Nitroprusside", "category": "vasopressor",
        "class_tr": "Güçlü Hızlı Vazodilatör",
        "mechanism_tr": "Doğrudan nitrik oksit (NO) salarak arter ve venlerde vazodilatasyon sağlar.",
        "pearls": ["Aort diseksiyonu ve kontrollü hipotansiyon uygulamalarında çok etkilidir.", "Uzun süreli veya yüksek doz kullanımda siyanür toksisitesi riski vardır! Işıkta bozulur, infüzyon seti korunmalıdır."]
    },
    # 8. Antiarrhythmics
    "amiodarone": {
        "name_tr": "Amiodarone", "name_en": "Amiodarone", "category": "antiarrhythmic",
        "class_tr": "Sınıf III Antiarritmik",
        "mechanism_tr": "Esas olarak potasyum kanallarını bloke eder, ayrıca sodyum, kalsiyum ve alfa/beta reseptörleri inhibe eder.",
        "pearls": ["Akut dirençli VF/Nabızsız VT resüsitasyonunda ilk tercih antiaritmiktir.", "QT mesafesini uzatır, uzun süreli kullanımda tiroid disfonksiyonu ve pulmoner fibrozis yapabilir."]
    },
    "adenosine_systemic": {
        "name_tr": "Adenozin", "name_en": "Adenosine", "category": "antiarrhythmic",
        "class_tr": "Sınıf IV Sınıflanamayan Antiarritmik",
        "mechanism_tr": "AV düğümünde iletimi yavaşlatır, geçici olarak AV bloğu oluşturur (yarım ömrü <10 saniye).",
        "pearls": ["Paroksismal supraventriküler taşikardi (PSVT) sonlandırılmasında ilk tercihtir.", "Çok hızlı IV bolus verilmeli, arkasından SF gönderilmelidir. Geçici asistoli hissi yapabilir."]
    },
    "esmolol": {
        "name_tr": "Esmolol", "name_en": "Esmolol", "category": "antiarrhythmic",
        "class_tr": "Ultra kısa etkili Beta-1 Blokör",
        "mechanism_tr": "Kardiyoselektif kompetitif beta-1 reseptör antagonistidir. Plazma esterazları ile yıkılır.",
        "pearls": ["Yarım ömrü çok kısa (9 dk) olduğu için taşikardi ve hipertansiyon krizlerinde son derece güvenlidir.", "Hızlı boluslarda şiddetli hipotansiyona dikkat edilmelidir."]
    },
    "labetalol": {
        "name_tr": "Labetalol", "name_en": "Labetalol", "category": "antiarrhythmic",
        "class_tr": "Alfa-1 ve Non-selektif Beta Blokör",
        "mechanism_tr": "Hem alfa-1 hem beta reseptörlerini bloke ederek kan basıncını düşürür (Alfa/Beta oranı 1:7 IV).",
        "pearls": ["Gebelik hipertansiyonu (preeklampsi) tedavisinde en güvenli seçeneklerden biridir.", "Nabzı düşürür, astımlı hastalarda bronkospazm riski nedeniyle kaçınılmalıdır."]
    },
    "metoprolol": {
        "name_tr": "Metoprolol", "name_en": "Metoprolol", "category": "antiarrhythmic",
        "class_tr": "Kardiyoselektif Beta-1 Blokör",
        "mechanism_tr": "Kardiyoselektif beta-1 reseptör antagonistidir.",
        "pearls": ["Kardiyoselektif beta-1 blokördür, intraoperatif taşiaritmilerde nabız kontrolü için kullanılır.", "Bradikardi ve AV blok riski yakından izlenmelidir."]
    },
    "hydralazine": {
        "name_tr": "Hidralazin", "name_en": "Hydralazine", "category": "antiarrhythmic",
        "class_tr": "Direkt Arteriyel Vazodilatör",
        "mechanism_tr": "Arteriyol düz kaslarında kalsiyum hareketini bozarak vazodilatasyon yapar.",
        "pearls": ["Gebelik hipertansiyonunda sıklıkla güvenle kullanılır.", "Refleks taşikardi yapabileceği için koroner arter hastalarında dikkat edilmelidir."]
    },
    # 9. Local Anesthetics
    "levobupivacaine": {
        "name_tr": "Levobupivakain", "name_en": "Levobupivacaine", "category": "local_anesthetic",
        "class_tr": "Uzun etkili Lokal Anestezik (S-enantiyomer)",
        "mechanism_tr": "Sodyum kanallarını bloke eder, bupivakaine göre kardiyotoksisitesi daha düşüktür.",
        "pearls": ["Bupivakaine kıyasla motor blokaj gücü benzerdir ancak kardiyak güvenlik marjı daha geniştir.", "Rejyonel anestezi ve epidural analjezide sık kullanılır."]
    },
    "prilocaine": {
        "name_tr": "Prilokain", "name_en": "Prilocaine", "category": "local_anesthetic",
        "class_tr": "Orta etkili Lokal Anestezik",
        "mechanism_tr": "Sodyum kanallarını bloke eder, yüksek dozda methemoglobinemi riski vardır.",
        "pearls": ["Metaboliti o-toluidin methemoglobinemiye yol açabilir. Tedavisinde metilen mavisi kullanılır.", "Hızlı metabolize olduğu için sistemik toksisite riski düşüktür."]
    },
    "mepivacaine": {
        "name_tr": "Mepivakain", "name_en": "Mepivacaine", "category": "local_anesthetic",
        "class_tr": "Orta etkili Lokal Anestezik",
        "mechanism_tr": "Sodyum kanallarını bloke eder, vazodilatör etkisi düşüktür.",
        "pearls": ["Diş hekimliğinde ve periferik sinir bloklarında vazokonstrüktörsüz kullanımı yaygındır.", "Plasentayı kolay geçer, fetal asidozda iyon tuzağı yapabilir."]
    },
    "chloroprocaine": {
        "name_tr": "Kloroprokain", "name_en": "Chloroprocaine", "category": "local_anesthetic",
        "class_tr": "Kısa etkili Ester grubu Lokal Anestezik",
        "mechanism_tr": "Sodyum kanallarını bloke eder, plazma kolinesterazı ile çok hızlı yıkılır (yarım ömrü <1 dakika).",
        "pearls": ["Çok kısa etki süresi nedeniyle günübirlik epidural anestezi için idealdir.", "Kardiyotoksisite riski yok denecek kadar azdır."]
    },
    "articaine": {
        "name_tr": "Artikain", "name_en": "Articaine", "category": "local_anesthetic",
        "class_tr": "Hızlı etkili Amid-Ester hibrit Lokal Anestezik",
        "mechanism_tr": "Sodyum kanallarını bloke eder, diş hekimliği ve rejyonel anestezide sık kullanılır.",
        "pearls": ["Yapısındaki ek ester bağı sayesinde plazmada da yıkılır, yarı ömrü çok kısadır (20 dk).", "Kemik dokuya penetrasyonu mükemmeldir."]
    },
    # 10. Emergency / Antidotes
    "ipratropium_oral_inhalation": {
        "name_tr": "İpratropiyum Bromür", "name_en": "Ipratropium Bromide", "category": "crisis",
        "class_tr": "Antikolinerjik Bronkodilatör",
        "mechanism_tr": "Solunum yollarındaki M3 reseptörlerini bloke ederek bronkodilasyon sağlar.",
        "pearls": ["Akut astım krizlerinde salbutamol ile kombine edildiğinde (Duolin/Combivent) sinerjistik etki gösterir.", "Göz içi basıncını artırabilir, dar açılı glokomda dikkatli olunmalıdır."]
    },
    "calcium_chloride": {
        "name_tr": "Kalsiyum Klorür", "name_en": "Calcium Chloride", "category": "crisis",
        "class_tr": "Kalsiyum Tuzu (Elektrolit)",
        "mechanism_tr": "Miyokard kontraktilitesini artırır, hiperkalemi ve kalsiyum kanal blokörü toksisitesinde kardiyoprotektiftir.",
        "pearls": ["Kalsiyum glukonata kıyasla 3 kat daha fazla elementer kalsiyum içerir, acil durumlarda tercih edilir.", "Periferik damardan verilirse şiddetli flebit ve nekroz yapabilir, santral yoldan verilmesi önerilir."]
    },
    "insulin_regular": {
        "name_tr": "Regüler İnsülin (Kristalize)", "name_en": "Regular Insulin", "category": "crisis",
        "class_tr": "Hızlı/Kısa etkili İnsülin",
        "mechanism_tr": "Glikozun hücre içine girişini sağlar, hiperkalemide glukozla verilerek potasyumu hücre içine sokar.",
        "pearls": ["Hiperkalemi acil tedavisinde 10 IU regüler insülin + 50 mL %50 dekstroz IV olarak uygulanır.", "Kan şekeri takibi 30-60 dk arayla yapılmalıdır."]
    },
    "glucagon": {
        "name_tr": "Glukagon", "name_en": "Glucagon", "category": "crisis",
        "class_tr": "Pankreatik Hormon / Antidot",
        "mechanism_tr": "Karaciğerde glikojenolizi artırır, kalsiyum girişini artırarak Beta blokör toksisitesinde inotropik etki sağlar.",
        "pearls": ["Beta-blokör ve kalsiyum kanal blokörü doz aşımında ilk tercih antidottur.", "Bulantı kusma yapıcı etkisi çok yüksektir, solunum yolu korunmalıdır."]
    },
    # 11. Allergy & Anaphylaxis
    "hydrocortisone_systemic": {
        "name_tr": "Hidrokortizon", "name_en": "Hydrocortisone", "category": "allergy",
        "class_tr": "Glukokortikoid (Kortikosteroid)",
        "mechanism_tr": "İnflamasyonu baskılar, anafilaksi ve adrenal yetmezlik/kriz yönetiminde kullanılır.",
        "pearls": ["Anafilakside etkisi geç (4-6 saat sonra) başlar; bu nedenle acil resüsitasyonda ilk tedavi her zaman adrenalindir.", "Adrenal yetmezlikli hastalarda perioperatif stres dozu olarak verilmelidir."]
    },
    "methylprednisolone": {
        "name_tr": "Metilprednizolon", "name_en": "Methylprednisolone", "category": "allergy",
        "class_tr": "Glukokortikoid (Kortikosteroid)",
        "mechanism_tr": "Güçlü anti-inflamatuar etki gösterir.",
        "pearls": ["Anafilaksi, bronkospazm ve laringeal ödem profilaksisinde sıklıkla tercih edilir.", "Glukoz seviyesini yükseltebilir."]
    },
    "chlorpheniramine": {
        "name_tr": "Klorfeniramin", "name_en": "Chlorpheniramine", "category": "allergy",
        "class_tr": "H1 Reseptör Antagonisti (Antihistaminik)",
        "mechanism_tr": "Periferik H1 reseptörlerini bloke ederek alerjik reaksiyonları ve kaşıntıyı önler.",
        "pearls": ["Sedatif etkisi belirgindir.", "Anafilakside adrenalin ve steroidlerin yanında tamamlayıcı tedavi olarak uygulanır."]
    },
    # 12. Antiemetics
    "metoclopramide": {
        "name_tr": "Metoklopramid", "name_en": "Metoclopramide", "category": "antiemetic",
        "class_tr": "Dopamin Antagonisti (Prokinetik Antiemetik)",
        "mechanism_tr": "MSS'de D2 reseptörlerini bloke eder, mide boşalımını hızlandırır.",
        "pearls": ["Ekstrapiramidal yan etkiler (akut distoni) yapabilir. Genç kadınlarda ve çocuklarda risk daha yüksektir.", "Bağırsak obstrüksiyonu varlığında prokinetik etkisi nedeniyle kontrendikedir!"]
    },
    "granisetron": {
        "name_tr": "Granisetron", "name_en": "Granisetron", "category": "antiemetic",
        "class_tr": "5-HT3 Reseptör Antagonisti (Antiemetik)",
        "mechanism_tr": "Kemoreseptör tetikleme bölgesindeki 5-HT3 reseptörlerini selektif bloke eder.",
        "pearls": ["Bulantı kusmada ondansetron gibi oldukça etkilidir, etki süresi daha uzundur (24 saat).", "Baş ağrısı ve geçici kabızlık yapabilir, QT aralığını hafif uzatabilir."]
    },
    "droperidol": {
        "name_tr": "Droperidol", "name_en": "Droperidol", "category": "antiemetic",
        "class_tr": "Butirofenon grubu Antiemetik/Nöroleptik",
        "mechanism_tr": "MSS'de D2 reseptörlerini bloke ederek güçlü antiemetik etki sağlar.",
        "pearls": ["Çok düşük dozlarda (0.625 - 1.25 mg IV) dahi PONV profilaksisinde son derece etkilidir.", "QT mesafesini uzatabilir; EKG takibi önerilir, QT > 450 ms ise kaçınılmalıdır."]
    },
    "dimenhydrinate": {
        "name_tr": "Dimenhidrinat (Dramamine)", "name_en": "Dimenhydrinate", "category": "antiemetic",
        "class_tr": "H1 Antagonisti / Antikolinerjik (Antiemetik)",
        "mechanism_tr": "Vestibüler uyarımı baskılar, taşıt tutması ve postoperatif bulantıda etkilidir.",
        "pearls": ["Belirgin sedasyon ve ağız kuruluğu yapabilir.", "Hareketle tetiklenen (vestibüler kökenli) bulantılarda çok etkilidir."]
    },
    "scopolamine": {
        "name_tr": "Skopolamin (Hiyosin)", "name_en": "Scopolamine (Hyoscine)", "category": "antiemetic",
        "class_tr": "Antikolinerjik / Antiemetik",
        "mechanism_tr": "Merkezi ve periferik muskarinik reseptörleri bloke eder, sekresyonları azaltır.",
        "pearls": ["Transdermal yama formu PONV profilaksisinde ameliyattan önceki gece uygulanır.", "Yaşlılarda konfüzyon, halüsinasyon ve idrar retansiyonu yapabilir!"]
    },
    # 13. Bleeding & Coagulation
    "fibrinogen_concentrate_human": {
        "name_tr": "Fibrinojen Konsantresi", "name_en": "Fibrinogen Concentrate", "category": "coagulation",
        "class_tr": "Kan Ürünü Derivesi (Faktör I)",
        "mechanism_tr": "Fibrin pıhtı oluşumunu destekleyerek masif kanamalarda hemostaz sağlar.",
        "pearls": ["Kriyo-presipitata kıyasla daha az hacimde daha yüksek konsantrasyonda fibrinojen sağlar ve enfeksiyon riski düşüktür.", "Hedef fibrinojen seviyesi >150-200 mg/dL olmalıdır."]
    },
    "prothrombin_complex_concentrate_human": {
        "name_tr": "Protrombin Kompleks Konsantresi (PCC)", "name_en": "Prothrombin Complex Concentrate", "category": "coagulation",
        "class_tr": "4 Faktörlü Koagülasyon Konsantresi",
        "mechanism_tr": "Faktör II, VII, IX, X ve Protein C/S içerir. Warfarin etkisini hızla geri çevirir.",
        "pearls": ["Taze donmuş plazmaya (TDP) kıyasla hacim yüklenmesi yapmadan INR'yi dakikalar içinde düşürür.", "Kullanımında tromboemboli riski yakından izlenmelidir."]
    },
    "protamine_sulfate": {
        "name_tr": "Protamin Sülfat", "name_en": "Protamine Sulfate", "category": "coagulation",
        "class_tr": "Heparin Antagonisti (Antidot)",
        "mechanism_tr": "Pozitif yüklü protamin molekülü, negatif yüklü heparin ile iyonik olarak birleşerek heparini nötralize eder.",
        "pearls": ["Kardiyopulmoner bypass (açık kalp cerrahisi) sonrası heparini geri çevirmek için kullanılır.", "Hızlı IV enjeksiyon masif pulmoner hipertansiyon, bradikardi ve anafilaktoid reaksiyon yapabilir!"]
    },
    "heparin_unfractionated": {
        "name_tr": "Heparin (Fraksiyone Olmayan)", "name_en": "Heparin (Unfractionated)", "category": "coagulation",
        "class_tr": "Antikoagülan",
        "mechanism_tr": "Antitrombin III'ü aktive ederek Faktör IIa (trombin) ve Xa'yı inhibe eder.",
        "pearls": ["Kardiyovasküler cerrahide antikoagülasyon için temel ilaçtır. Etkisi ACT (Aktif Koagülasyon Zamanı) ile izlenir.", "Etkisi protamin sülfat ile tamamen geri çevrilebilir."]
    },
    "vitamin_k1_phytonadione_phytomenadione": {
        "name_tr": "K Vitamini (Fitonadion)", "name_en": "Vitamin K1 (Phytonadione)", "category": "coagulation",
        "class_tr": "Yağda Eriyen Vitamin / Koagülasyon Faktör Prekürsörü",
        "mechanism_tr": "Faktör II, VII, IX ve X'un karaciğerde sentezlenmesi için gereklidir.",
        "pearls": ["Warfarin aşırı dozunda veya INR yüksekliğinde etkiyi geri çevirmek için verilir.", "IV olarak çok yavaş (flebit ve anafilaksi riski) veya oral/SC uygulanmalıdır."]
    },
    # 14. Obstetric
    "oxytocin": {
        "name_tr": "Oksitosin", "name_en": "Oxytocin", "category": "obstetric",
        "class_tr": "Uterotonik Hormon",
        "mechanism_tr": "Uterus düz kaslarındaki oksitosin reseptörlerini uyararak ritmik kasılmalar sağlar (postpartum kanamayı önler).",
        "pearls": ["Sezaryen operasyonlarında plasenta ayrıldıktan hemen sonra başlanır.", "Hızlı bolus IV enjeksiyon masif hipotansiyon, refleks taşikardi ve koroner iskemi yapabilir! Yavaş infüzyon tercih edilmelidir."]
    },
    "methylergonovine": {
        "name_tr": "Metilergometrin (Metilergonovin)", "name_en": "Methylergonovine (Methylergometrine)", "category": "obstetric",
        "class_tr": "Ergot Alkaloidi Uterotonik",
        "mechanism_tr": "Uterus düz kasını doğrudan uyararak sürekli tonik kasılma sağlar.",
        "pearls": ["Oksitosine yanıt alınamayan postpartum atoni kanamalarında 2. tercih olarak uygulanır.", "Şiddetli vazokonstrüksiyon ve tansiyon yükselmesi yapabilir; preeklampsi ve hipertansiyon hastalarında kontrendikedir!"]
    },
    "carboprost": {
        "name_tr": "Karboprost", "name_en": "Carboprost Tromethamine", "category": "obstetric",
        "class_tr": "Prostaglandin F2-Alfa Analoğu Uterotonik",
        "mechanism_tr": "Uterus miyometriyumunu uyararak güçlü kasılmalar sağlar. Dirençli postpartum kanamada kullanılır.",
        "pearls": ["Şiddetli bronkospazm yapma riski nedeniyle astımlı hastalarda kesinlikle kontrendikedir!", "Gastrointestinal düz kasları uyararak şiddetli ishal ve bulantı yapabilir."]
    },
    "misoprostol": {
        "name_tr": "Misoprostol", "name_en": "Misoprostol", "category": "obstetric",
        "class_tr": "Prostaglandin E1 Analoğu",
        "mechanism_tr": "Miyometriyal kasılmaları uyarır, servikal olgunlaşmayı kolaylaştırır.",
        "pearls": ["Postpartum kanamada sıklıkla rektal veya sublingual yolla 800-1000 mcg dozunda uygulanır.", "Ateş yüksekliği ve titreme yan etkileri belirgindir."]
    },
    "nifedipine": {
        "name_tr": "Nifedipin", "name_en": "Nifedipine", "category": "obstetric",
        "class_tr": "Kalsiyum Kanal Blokörü (Tokolitik)",
        "mechanism_tr": "Düz kaslarda kalsiyum girişini engelleyerek uterus kasılmalarını gevşetir (tokolitik etki).",
        "pearls": ["Erken doğum tehdidinde (tokoliz amacıyla) ve preeklampsi tansiyon kontrolünde yaygın kullanılır.", "Magnezyum sülfat ile birlikte kullanıldığında sinerjistik hipotansiyon ve nöromüsküler blok derinleşmesi yapabilir."]
    },
    "terbutaline": {
        "name_tr": "Terbutalin", "name_en": "Terbutaline", "category": "obstetric",
        "class_tr": "Beta-2 Reseptör Agonisti (Tokolitik)",
        "mechanism_tr": "Miyometriyumdaki Beta-2 reseptörlerini uyararak intrasellüler cAMP'yi artırır ve uterus gevşemesini (tokoliz) sağlar.",
        "pearls": ["Akut uterin hipertonus veya fetal distress durumunda uterus gevşetmek için hızlıca SC veya IV verilebilir.", "Maternal taşikardi, çarpıntı, hiperglisemi ve hipokalemi yapabilir."]
    },
    # 15. Antibiotic Prophylaxis
    "cefazolin": {
        "name_tr": "Sefazolin", "name_en": "Cefazolin", "category": "antibiotic",
        "class_tr": "1. Kuşak Sefalosporin Antibiyotik",
        "mechanism_tr": "Bakteri hücre duvarı sentezini inhibe eder, cerrahi profilaksinin temel ilacıdır.",
        "pearls": ["Cerrahi kesiden 30-60 dakika önce verilmelidir.", "Cerrahi 4 saati aşarsa veya masif kanama (>1500 mL) varsa intraoperatif doz tekrarlanmalıdır."]
    },
    "cefuroxime": {
        "name_tr": "Sefuroksim", "name_en": "Cefuroxime", "category": "antibiotic",
        "class_tr": "2. Kuşak Sefalosporin Antibiyotik",
        "mechanism_tr": "Bakteri hücre duvarı sentezini inhibe eder.",
        "pearls": ["Gram negatif spektrumu sefazolinden biraz daha geniştir.", "Aşırı duyarlılık öyküsü kontrol edilmelidir."]
    }
}

MISSING_DRUGS = [
    {
        "id": "mivacurium", "name_tr": "Mivakuryum", "name_en": "Mivacurium", "category": "nmb",
        "class_tr": "Kısa etkili depolarizan olmayan NMB",
        "mechanism_tr": "Nikotinik asetilkolin reseptörlerini kompetitif olarak bloke eder, plazma kolinesterazı ile hidroliz edilir.",
        "brand_names": ["Mivacron"],
        "adult_dose": "0.15 - 0.2 mg/kg IV yavaş bolus",
        "adult_notes": "Günübirlik cerrahiler için idealdir. Etki süresi yaklaşık 15-20 dakikadır.",
        "ped_dose": "0.2 mg/kg IV",
        "ped_notes": "Çocuklarda eliminasyon hızı daha yüksektir.",
        "renal": "Böbrek yetmezliğinde klirens hafif azalabilir, etki süresi uzayabilir.",
        "liver": "Karaciğer yetmezliğinde plazma kolinesteraz üretimi azalacağı için etki süresi belirgin uzar!",
        "obesity": "İdeal vücut ağırlığına (IBW) göre dozlanmalıdır.",
        "geriatri": "Yaşlılarda plazma kolinesteraz aktivitesi azaldığı için doz %25 düşürülmelidir.",
        "kinetics": "PharmacokineticsInfo(onset = \"2 - 3 dakika\", peak = \"3 - 5 dakika\", duration = \"15 - 20 dakika\", halfLife = \"2 - 5 dakika\", elimination = \"Plazma kolinesterazı (Psödokolinesteraz) ile hidroliz\")",
        "warnings": ["Hızlı enjeksiyonda histamin salınımına bağlı hipotansiyon ve bronkospazm yapabilir.", "Psödokolinesteraz eksikliği (atipik enzim) olanlarda uzamış apneye yol açar!"],
        "pearls": ["Kısa etki süresi nedeniyle günübirlik anestezi uygulamalarında entübasyon ve kısa süreli işlemler için mükemmeldir.", "Etkisi neostigmin ile geri döndürülebilir ancak psödokolinesteraz eksikliğinde ventilatör desteği şarttır."]
    },
    {
        "id": "pancuronium", "name_tr": "Pankuronyum", "name_en": "Pancuronium", "category": "nmb",
        "class_tr": "Uzun etkili depolarizan olmayan NMB",
        "mechanism_tr": "Nikotinik asetilkolin reseptörlerini kompetitif bloke eder, hafif vagolitik etkisi bulunur.",
        "brand_names": ["Pavulon"],
        "adult_dose": "0.08 - 0.1 mg/kg IV bolus",
        "adult_notes": "Uzun süreli cerrahi ve yoğun bakım ventilasyonlarında tercih edilir. Etkisi 60-100 dakika sürer.",
        "ped_dose": "0.1 mg/kg IV",
        "ped_notes": "Yenidoğanlarda hassasiyet artmıştır.",
        "renal": "Ciddi böbrek yetmezliğinde birikir, eliminasyon yarı ömrü belirgin uzar, kontrendikedir!",
        "liver": "Karaciğer yetmezliğinde eliminasyon yavaşlar.",
        "obesity": "İdeal vücut ağırlığına (IBW) göre dozlanmalıdır.",
        "geriatri": "Yaşlılarda eliminasyon yavaşladığı için doz azaltılmalı ve TOF ile izlenmelidir.",
        "kinetics": "PharmacokineticsInfo(onset = \"3 - 5 dakika\", peak = \"5 - 8 dakika\", duration = \"60 - 100 dakika\", halfLife = \"2 - 3 saat\", elimination = \"Böbreklerden değişmeden glomerular filtrasyonla atılır, kısmen karaciğer metabolizması\")",
        "warnings": ["Vagolitik etki: Kalp hızında ve kan basıncında belirgin artışa (taşikardi) yol açar.", "Böbrek yetmezliği olanlarda uzamış nöromüsküler blok riski çok yüksektir."],
        "pearls": ["Uzun etkili bir ajan olduğu için ameliyat sonu uyanmada residual (kalıntı) blokaj riski yüksektir, mutlaka neostigmin/atropin geri çevrilmesi ve TOF takibi yapılmalıdır.", "Kardiyovasküler vagolitik stimülasyon taşikardik hastalarda iskemiyi tetikleyebilir."]
    },
    {
        "id": "urapidil", "name_tr": "Urapidil", "name_en": "Urapidil", "category": "antiarrhythmic",
        "class_tr": "Alfa-1 Blokör ve 5-HT1A Agonisti (Antihistaminik dışı vazodilatör)",
        "mechanism_tr": "Periferik postsinaptik alfa-1 reseptörlerini bloke ederek sistemik direnci düşürür, merkezi 5-HT1A reseptörlerini uyararak refleks taşikardiyi önler.",
        "brand_names": ["Ebrantil", "Urapidil Ampul"],
        "adult_dose": "10 - 50 mg IV yavaş bolus",
        "adult_notes": "Kan basıncı durumuna göre titre edilir, ardından gerekirse 2-9 mg/saat infüzyon başlanır.",
        "ped_dose": "0.5 - 1.0 mg/kg IV",
        "ped_notes": "Pediatrik hipertansif krizlerde çok nadir ve dikkatli kullanılır.",
        "renal": "Renal yetmezlikte klirens hafif azalır, ancak belirgin doz modifikasyonu gerekmez.",
        "liver": "Karaciğer yetmezliğinde yarı ömrü uzayabilir, dozaj titre edilerek azaltılmalıdır.",
        "obesity": "Normal doz sınırları içinde kilo takibiyle verilir.",
        "geriatri": "Yaşlılarda hipotansiyon riski artmıştır, başlangıç dozları düşük tutulmalıdır.",
        "kinetics": "PharmacokineticsInfo(onset = \"3 - 5 dakika\", peak = \"5 - 10 dakika\", duration = \"4 - 6 saat\", halfLife = \"4 - 5 saat\", elimination = \"Karaciğerde metabolize edilir, inaktif metabolitleri idrarla atılır\")",
        "warnings": ["Hızlı ve yüksek dozlarda kontrolsüz ortostatik veya sistemik hipotansiyon riski.", "Gereksiz kombine vazodilatör kullanımı asistoliye kadar varan ciddi bradikardi yapabilir."],
        "pearls": ["Sempatolitik etkisi olmasına rağmen refleks taşikardi yapmaması en büyük avantajıdır. İntraoperatif cerrahi uyarılara bağlı akut hipertansif ataklarda (örn. entübasyon, ekstübasyon, diseksiyon) mükemmel etkilidir.", "Koroner arter hastalığı olanlarda güvenli vazodilatasyon sağlar."]
    },
    {
        "id": "dextrose", "name_tr": "Dekstroz (Glukoz)", "name_en": "Dextrose (Glucose)", "category": "crisis",
        "class_tr": "Karbonhidrat / Hipertonik Solüsyon",
        "mechanism_tr": "Hücrelere doğrudan glukoz sağlayarak hipoglisemiyi düzeltir, insülinle birlikte potasyumun hücre içine girişini kolaylaştırır.",
        "brand_names": ["Dekstroz %5", "Dekstroz %10", "Dekstroz %50"],
        "adult_dose": "10 - 25 g IV (%50 solüsyondan 20-50 mL veya %10 solüsyondan 100-250 mL yavaş IV)",
        "adult_notes": "Hipoglisemi acil tedavisinde verilir, ardından kan şekeri takibiyle idame sıvıya geçilir.",
        "ped_dose": "0.25 - 0.5 g/kg IV (%10'luk solüsyondan 2-5 mL/kg)",
        "ped_notes": "Çocuklarda kesinlikle hipertonik %50'lik dekstroz direkt verilmemelidir, damar iritasyonu ve rebound hipoglisemi riski yüksektir; %10'luk tercih edilir.",
        "renal": "Normal metabolizma, doz ayarlaması gerekmez.",
        "liver": "Normal metabolizma.",
        "obesity": "Dozaj standart hipoglisemi protokollerine göredir.",
        "geriatri": "Yaşlılarda sıvı yüklenmesi ve hiperglisemi riski izlenmelidir.",
        "kinetics": "PharmacokineticsInfo(onset = \"Hemen\", peak = \"Enjeksiyon sonu\", duration = \"Metabolik hıza bağlı\", halfLife = \"N/A\", elimination = \"Hücresel glikoliz ve glukoz metabolizması\")",
        "warnings": ["%10'dan daha konsantre solüsyonlar periferik damardan hızlı verilirse flebit ve lokal doku nekrozu yapabilir, yavaş veya santral hat tercih edilmelidir.", "Hızlı infüzyon ozmotik diüreze ve hiperglisemik komaya yol açabilir."],
        "pearls": ["Hiperkalemi krizlerinde (Potasyum > 6.0 mEq/L) sodyum bikarbonat ve kalsiyumun yanında, 10 IU regüler insülin + 50 mL %50 Dekstroz IV 20-30 dakikada verilerek potasyum hücre içine çekilir.", "Kan şekeri takibi şarttır."]
    },
    {
        "id": "ampicillin_sulbactam", "name_tr": "Ampisilin-Sulbaktam", "name_en": "Ampicillin-Sulbactam", "category": "antibiotic",
        "class_tr": "Aminopenisilin + Beta-Laktamaz İnhibitörü",
        "mechanism_tr": "Ampisilin bakteri hücre duvarı sentezini engeller, sulbaktam ise bakteriyel beta-laktamaz enzimlerini inhibe ederek ampisilini korur.",
        "brand_names": ["Duocid", "Ampisina", "Unasyn"],
        "adult_dose": "1.5 - 3.0 g IV yavaş infüzyon (cerrahi kesiden 30-60 dk önce)",
        "adult_notes": "Cerrahi süre 4 saati aşarsa veya >1500 mL masif kanama olursa intraoperatif doz tekrarlanmalıdır.",
        "ped_dose": "50 mg/kg IV (maksimum 3.0 g)",
        "ped_notes": "Yavaş IV infüzyon halinde uygulanmalıdır.",
        "renal": "eGFR <30 mL/dk olanlarda yarı ömrü uzar, profilaktik doz sonrası postoperatif idame doz aralığı uzatılmalıdır (12-24 saat).",
        "liver": "Karaciğer yetmezliğinde doz ayarlaması gerekmez.",
        "obesity": "Obezite hastalarında üst doz sınırından (3.0 g) verilmesi önerilir.",
        "geriatri": "Yaşlılarda renal fonksiyon takibine göre doz aralığı ayarlanır.",
        "kinetics": "PharmacokineticsInfo(onset = \"Hemen (IV)\", peak = \"Infüzyon sonu\", duration = \"4 - 6 saat\", halfLife = \"1 - 2 saat\", elimination = \"Böbreklerden glomerular filtrasyon ve tübüler sekresyonla atılır\")",
        "warnings": ["Penisilin grubu veya sefalosporin alerjisi (çapraz reaksiyon %5-10) varlığında anafilaksi riski yüksektir!", "Hızlı IV puşe uygulamalarından kaçınılmalıdır, nörotoksik etkiler ve nöbeti tetikleyebilir."],
        "pearls": ["Karın içi cerrahiler, jinekolojik ve baş-boyun cerrahilerinde mükemmel geniş spektrumlu koruma sağlar.", "Penisilin alerjisi olanlarda klindamisin veya vankomisin tercih edilmelidir."]
    },
    {
        "id": "vancomycin", "name_tr": "Vankomisin", "name_en": "Vancomycin", "category": "antibiotic",
        "class_tr": "Glikopeptid grubu antibiyotik",
        "mechanism_tr": "Bakteriyel hücre duvarı peptidoglikan sentezini D-alanyl-D-alanine bölgesine bağlanarak inhibe eder, hücre ölümünü sağlar.",
        "brand_names": ["Vancocin", "Vancomycin Ampul"],
        "adult_dose": "15 mg/kg IV yavaş infüzyon (cerrahi kesiden 60-120 dk önce başlanmalıdır)",
        "adult_notes": "Cerrahi profilakside yavaş verilmesi hayatidir. Cerrahi uzarsa 12 saatte bir tekrarlanır.",
        "ped_dose": "15 mg/kg IV yavaş infüzyon",
        "ped_notes": "Pediatrik hastalarda infüzyon süresi en az 60-90 dakika olmalıdır.",
        "renal": "Ciddi nefrotoksiktir! Böbrek yetmezliğinde birikir, eGFR takibi ve plazma düzey takibi (TDM) yapılmalıdır.",
        "liver": "Karaciğer yetmezliğinde doz ayarlaması gerekmez.",
        "obesity": "Gerçek vücut ağırlığına göre dozlanır, maksimum tekil doz 2.0 g'ı aşmamalıdır.",
        "geriatri": "Böbrek fonksiyonları zayıflamış yaşlılarda dozaj aralığı uzatılmalıdır.",
        "kinetics": "PharmacokineticsInfo(onset = \"Infüzyon sonu\", peak = \"1 - 2 saat\", duration = \"12 saat\", halfLife = \"6 - 8 saat\", elimination = \"Değişmeden böbreklerden glomerular filtrasyonla idrarla atılır\")",
        "warnings": ["Red Man (Kırmızı Adam) Sendromu: Hızlı IV infüzyon histamin salınımına yol açarak yüzde, boyunda masif kızarıklık, hipotansiyon ve bronkospazma neden olur. İnfüzyon hızı <10-15 mg/dk (en az 60 dk) olmalıdır.", "Ototoksik ve nefrotoksik etkileri diğer ilaçlarla (örn. gentamisin) kombine edildiğinde katlanır."],
        "pearls": ["MRSA (Metisiline Dirençli Staph. Aureus) taşıyıcılığı veya penisilin alerjisi olan protez/kardiyak cerrahilerinde profilakside ilk tercihtir.", "Kırmızı Adam Sendromu gelişirse infüzyon derhal durdurulmalı, antihistaminik verilmeli ve daha yavaş hızda tekrar başlanmalıdır."]
    },
    {
        "id": "clindamycin", "name_tr": "Klindamisin", "name_en": "Clindamycin", "category": "antibiotic",
        "class_tr": "Lincosamide grubu antibiyotik",
        "mechanism_tr": "Bakteriyel 50S ribozomal alt birimine geri dönüşümlü bağlanarak protein sentezini (peptid zincir uzamasını) inhibe eder.",
        "brand_names": ["Cleocin", "Clin Ampul", "Klindan"],
        "adult_dose": "600 - 900 mg IV yavaş infüzyon (cerrahi kesiden 30-60 dk önce)",
        "adult_notes": "Cerrahi 6 saati aşarsa intraoperatif doz tekrarlanmalıdır.",
        "ped_dose": "10 mg/kg IV yavaş infüzyon",
        "ped_notes": "10-20 dakikada yavaş verilmelidir.",
        "renal": "Ciddi böbrek yetmezliğinde doz ayarlaması gerekmez, karaciğer eliminasyonu baskındır.",
        "liver": "Ağır karaciğer yetmezliğinde klirens azalabilir, idame dozları azaltılmalıdır.",
        "obesity": "Standart dozlar uygulanır.",
        "geriatri": "Yaşlılarda psödomembranöz enterokolit riski yakından izlenmelidir.",
        "kinetics": "PharmacokineticsInfo(onset = \"Hemen (IV)\", peak = \"Infüzyon sonu\", duration = \"6 saat\", halfLife = \"2 - 3 saat\", elimination = \"Karaciğerde aktif metabolitlerine metabolize edilir, safra ve idrarla atılır\")",
        "warnings": ["Hızlı IV bolus (puşe) enjeksiyonlarda kardiyak toksisite: Şiddetli hipotansiyon, aritmi ve EKG değişiklikleri riski nedeniyle en az 10-20 dakikada infüze edilmelidir.", "Pseudomembranöz Enterokolit: C. difficile üremesine bağlı şiddetli kolit yapma riski diğer antibiyotiklere göre yüksektir."],
        "pearls": ["Penisilin ve sefalosporin alerjisi olan hastalarda batın içi ve baş-boyun cerrahilerinde mükemmel bir anaerobik profilaksi seçeneğidir.", "Nöromüsküler blokörlerin (kas gevşeticiler) etkisini potansiyalize edebilir."]
    },
    {
        "id": "gentamicin", "name_tr": "Gentamisin", "name_en": "Gentamicin", "category": "antibiotic",
        "class_tr": "Aminoglikozid grubu antibiyotik",
        "mechanism_tr": "Bakteriyel 30S ribozomal alt birimine bağlanarak mRNA kod okunmasını bozar, bakteri protein sentezini irreversibl inhibe eder.",
        "brand_names": ["Genta Ampul", "Gentamicin", "Gentasol"],
        "adult_dose": "5 mg/kg IV yavaş infüzyon (cerrahi kesiden 30-60 dk önce)",
        "adult_notes": "Tek doz profilaksi olarak verilir, postoperatif dönemde gerekirse böbrek fonksiyonuna göre idame planlanır.",
        "ped_dose": "2.5 - 5.0 mg/kg IV yavaş infüzyon",
        "ped_notes": "Pediatrik hastalarda da yavaş verilmelidir.",
        "renal": "Nefrotoksiktir! Böbrek yetmezliğinde birikir, glomerular filtrasyon zayıfsa yarı ömrü masif uzar. eGFR takibi ve plazma düzey takibi yapılmalıdır.",
        "liver": "Karaciğer yetmezliğinde doz ayarlaması gerekmez.",
        "obesity": "Obez hastalarında dozu hesaplarken Ayarlanmış Vücut Ağırlığı (AdjBW) kullanılmalıdır.",
        "geriatri": "Yaşlılarda böbrek rezervi azaldığı için doz titre edilmeli, böbrek fonksiyonu izlenmelidir.",
        "kinetics": "PharmacokineticsInfo(onset = \"Infüzyon sonu\", peak = \"30 - 60 dakika\", duration = \"8 - 12 saat\", halfLife = \"2 - 3 saat (renal yetmezlikte 24-48 saate uzayabilir)\", elimination = \"Değişmeden böbreklerden glomerular filtrasyonla idrarla atılır\")",
        "warnings": ["Ototoksisite (kalıcı koklear ve vestibüler hasar) ve Nefrotoksisite (akut tübüler nekroz) riski yüksektir.", "Nöromüsküler Blokajın Potansiyalizasyonu: Kas gevşeticilerin etkisini belirgin şekilde uzatır ve pre-sinaptik asetilkolin salınımını azaltarak geri çevrilmesini zorlaştırır."],
        "pearls": ["Kas gevşetici alan hastalarda gentamisin yapıldığında, ameliyat sonunda hastanın tam uyanması gecikebilir, TOF derinliği kontrol edilmeden hasta ekstübe edilmemelidir.", "Kalsiyum verilmesi gentamisine bağlı nöromüsküler bloğu hafifletebilir."]
    },
    {
        "id": "metronidazole", "name_tr": "Metronidazol", "name_en": "Metronidazole", "category": "antibiotic",
        "class_tr": "Nitroimidazol antibiyotik / Antiprotozoal",
        "mechanism_tr": "Bakteri hücresine girdikten sonra aktif ara metabolitlerine indirgenir, bu metabolitler bakteri DNA yapısını bozarak sentezini engeller.",
        "brand_names": ["Flagyl IV", "Nidazol IV", "Metrazol"],
        "adult_dose": "500 mg IV yavaş infüzyon (cerrahi kesiden 30-60 dk önce)",
        "adult_notes": "Genellikle sefazolin veya sefuroksim ile kombine edilerek kolorektal profilakside anaerob koruma amacıyla kullanılır.",
        "ped_dose": "15 mg/kg IV yavaş infüzyon",
        "ped_notes": "Pediatrik batın cerrahilerinde güvenle kullanılır.",
        "renal": "Ciddi böbrek yetmezliğinde (eGFR <10 mL/dk) metabolitleri birikebilir, dozaj %50 azaltılmalıdır.",
        "liver": "Ağır karaciğer yetmezliğinde klirensi %50 oranında düşer, doz azaltılması gerekir.",
        "obesity": "Standart dozlar yeterlidir.",
        "geriatri": "Yaşlılarda karaciğer klirensi azalacağı için yakın takip önerilir.",
        "kinetics": "PharmacokineticsInfo(onset = \"Infüzyon sonu\", peak = \"1 saat\", duration = \"8 saat\", halfLife = \"8 saat\", elimination = \"Karaciğerde yoğun şekilde metabolize edilir, idrarla atılır\")",
        "warnings": ["Alkol ile kullanıldığında Disülfiram Benzeri Reaksiyona (şiddetli hipotansiyon, kusma, taşikardi) yol açar. Postoperatif dönemde alkol kısıtlanmalıdır.", "Hızlı IV infüzyonda tromboflebit ve lokal damar iritasyonu riski vardır."],
        "pearls": ["Kolorektal ve jinekolojik cerrahilerde anaerobik patojenlere karşı profilaksinin vazgeçilmez parçasıdır.", "Periferik nöropati yapıcı etkisi uzun süreli tedavilerde görülebilir."]
    },
    {
        "id": "furosemide", "name_tr": "Furosemid", "name_en": "Furosemide", "category": "icu_metabolic",
        "class_tr": "Kıvrım (Loop) Diüretiği",
        "mechanism_tr": "Henle kulbunun çıkan kalın kolunda Na-K-2Cl kotransporterını inhibe ederek sodyum, potasyum, klor ve su atılımını masif olarak artırır.",
        "brand_names": ["Lasix Ampul", "Desal Ampul"],
        "adult_dose": "20 - 40 mg IV yavaş bolus (1-2 dakikada)",
        "adult_notes": "Sıvı yüklenmesi, akut akciğer ödemi veya kafa içi basınç artışında diürez sağlamak için verilir. Gerekirse infüzyon halinde titre edilir.",
        "ped_dose": "1 mg/kg IV yavaş bolus (maksimum 40 mg)",
        "ped_notes": "Pediatrik hastalarda sıvı ve elektrolit kaybı çok hızlı gelişir, yakından izlenmelidir.",
        "renal": "Böbrek yetmezliğinde tübüler sekresyon azalacağı için etkiye yanıt düşer; istenen diürezi sağlamak için daha yüksek dozlar (örn. 80-200 mg) gerekebilir.",
        "liver": "Ağır karaciğer yetmezliği ve sirozu olanlarda elektrolit dengesizliği hepatik komayı tetikleyebilir.",
        "obesity": "Standart diüretik protokolleri izlenir.",
        "geriatri": "Yaşlılarda dehidratasyon, akut böbrek hasarı ve ortostatik hipotansiyon riski son derece yüksektir.",
        "kinetics": "PharmacokineticsInfo(onset = \"5 - 15 dakika (IV)\", peak = \"30 dakika\", duration = \"2 - 3 saat\", halfLife = \"1.5 - 2 saat\", elimination = \"Böbreklerden glomerular filtrasyon ve tübüler sekresyonla değişmeden atılır\")",
        "warnings": ["Ototoksisite: Çok hızlı yüksek doz (>4 mg/dk hızda) enjekte edilirse geçici veya kalıcı işitme kaybı ve çınlamaya yol açabilir.", "Elektrolit Bozuklukları: Şiddetli hipokalemi (kalp aritmilerini tetikler), hiponatremi, hipomagnezemi ve dehidratasyon riski."],
        "pearls": ["Akut akciğer ödeminde IV enjeksiyon sonrası, diüretik etkisi henüz başlamadan önce dahi güçlü venodilatör etkisi sayesinde venöz dönüşü (preload) azaltarak hastayı saniyeler içinde rahatlatır.", "Hipokalemik hastalarda aritmi riski nedeniyle potasyum düzeyi yakın izlenmeli, gerekirse replasman yapılmalıdır."]
    },
    {
        "id": "mannitol", "name_tr": "Mannitol", "name_en": "Mannitol", "category": "icu_metabolic",
        "class_tr": "Ozmotik Diüretik",
        "mechanism_tr": "Glomerüllerden serbestçe filtre edilir ancak tübüllerden geri emilmez. Tübüler lümende ozmotik basınç yaratarak suyun geri emilimini engeller, beyin ödeminde ise hücre dışı sıvıyı intravasküler alana çekerek kranial basıncı düşürür.",
        "brand_names": ["Mannitol %20 Solüsyon"],
        "adult_dose": "0.25 - 1.0 g/kg IV yavaş infüzyon (15-30 dakikada)",
        "adult_notes": "Kafa içi basınç (ICP) veya göz içi basınç artışında ödemi çözmek amacıyla titre edilerek verilir.",
        "ped_dose": "0.25 - 0.5 g/kg IV yavaş infüzyon",
        "ped_notes": "Pediatrik hastalarda da yavaş verilmelidir.",
        "renal": "Akut böbrek yetmezliğinde (anüri durumunda) filtre edilemeyeceği için birikir ve akciğer ödemini tetikler, kesinlikle kontrendikedir!",
        "liver": "Doz ayarlaması gerekmez.",
        "obesity": "İdeal veya normal vücut ağırlığı takibine göre verilir.",
        "geriatri": "Yaşlılarda sıvı kaymaları sonucu akut kalp yetmezliği tetiklenebilir.",
        "kinetics": "PharmacokineticsInfo(onset = \"15 - 30 dakika (ICP düşüşü)\", peak = \"30 - 60 dakika\", duration = \"4 - 6 saat\", halfLife = \"100 dakika\", elimination = \"Böbreklerden glomerular filtrasyonla değişmeden atılır\")",
        "warnings": ["Akut Akciğer Ödemi ve Sıvı Yüklenmesi: IV infüzyon başlangıcında interstisyel sıvıyı hızla damar içine çektiği için geçici hipervolemi yaratarak kalp yetmezliği olanlarda akciğer ödemini tetikler.", "Ozmotik Nefroz ve Böbrek Hasarı: Yüksek dozlarda veya susuz kalmış hastalarda böbrek tübüllerine zarar verebilir."],
        "pearls": ["Kan-beyin bariyeri (BBB) sağlam olan dokularda beyin ödemini çözmede altın standarttır. Oda sıcaklığında kristalleşebilir; şişede kristal görülürse sıcak su banyosunda tamamen çözünmesi sağlanmalı, vücut sıcaklığına geldikten sonra mutlaka filtreli setle uygulanmalıdır.", "İnfaz sırasında idrar çıkışı ve serum ozmolaritesi (hedef <320 mOsm/L) izlenmelidir."]
    },
    {
        "id": "hypertonic_saline", "name_tr": "Hipertonik Salin", "name_en": "Hypertonic Saline (3% NaCl)", "category": "icu_metabolic",
        "class_tr": "Hipertonik Sodyum Klorür Solüsyonu",
        "mechanism_tr": "İntravasküler ozmolariteyi hızla yükselterek beyin hücrelerindeki ödem sıvısını ozmotik gradyanla damar içine çeker, kafa içi basıncı (ICP) düşürür ve serum sodyum düzeyini artırır.",
        "brand_names": ["%3 Sodyum Klorür (NaCl)", "%7.5 Sodyum Klorür"],
        "adult_dose": "150 - 250 mL IV yavaş infüzyon (10-20 dakikada, %3 NaCl)",
        "adult_notes": "Akut kafa içi basınç krizlerinde bolus infüzyon olarak verilir, semptomatik hiponatremi durumlarında ise sodyum açığı formülüne göre titre edilir.",
        "ped_dose": "3 - 5 mL/kg IV yavaş infüzyon (%3 NaCl)",
        "ped_notes": "Pediatrik kafa travması protokollerinde ICP kontrolü için sıklıkla tercih edilir.",
        "renal": "Normal böbrek fonksiyonlarında sodyum ve klor glomerular filtrasyonla elenir. Ağır renal yetmezlikte sodyum yüklenmesi yapabilir.",
        "liver": "Normal metabolizma.",
        "obesity": "Standart acil doz limitlerine göredir.",
        "geriatri": "Yaşlılarda sıvı kayması ve kalp yetmezliği riski yakından izlenmelidir.",
        "kinetics": "PharmacokineticsInfo(onset = \"Hemen\", peak = \"İnfüzyon sonu\", duration = \"Hücresel dengeye bağlı\", halfLife = \"N/A\", elimination = \"Böbreklerden idrarla sodyum ve klor atılımı\")",
        "warnings": ["Santral Pontin Miyelinolizis (Ozmotik Demiyelinizasyon Sendromu): Kronik hiponatremi varlığında sodyum düzeyi çok hızlı yükseltilirse beyin sapında demiyelinizasyona bağlı kalıcı felç gelişebilir! Sodyum artış hızı günde 8-10 mEq/L'yi aşmamalıdır.", "Damar İritasyonu: Yüksek ozmolarite nedeniyle periferik damardan verilirse ciddi flebit ve nekroz yapar. Mutlaka Santral Venöz Kateterden verilmelidir."],
        "pearls": ["Akut intrakranial basınç artışında, kalbi mannitol kadar zorlamadan ve diürez yapmadan ICP'yi çok hızlı düşürür; bu nedenle hemodinamisi sınırdaki kafa travmalı hastalarda ilk tercihlerdendir.", "İdrar çıkışı ve serum sodyumu sık aralıklarla takip edilmelidir."]
    },
    {
        "id": "albumin", "name_tr": "Albumin (İnsan)", "name_en": "Albumin (Human)", "category": "icu_metabolic",
        "class_tr": "Doğal Kolloid Solüsyonu (%5 veya %20)",
        "mechanism_tr": "Vücudun ana plazma proteinidir. İntravasküler onkotik basıncın %80'ini oluşturur, interstisyel alandaki sıvıyı damar yatağına çekerek plazma hacmini genişletir.",
        "brand_names": ["Human Albumin %5", "Human Albumin %20", "Zenalb"],
        "adult_dose": "12.5 - 25 g IV yavaş infüzyon (örn. %20'lik solüsyondan 50-100 mL veya %5'lik solüsyondan 250-500 mL)",
        "adult_notes": "Sıvı replasmanı, ciddi hipoalbüminemi, karaciğer sirozu (asit drenajı sonrası her 1 litre için 8g albümin) durumlarında verilir.",
        "ped_dose": "0.5 - 1.0 g/kg IV yavaş infüzyon",
        "ped_notes": "Pediatrik şok resüsitasyonunda veya yenidoğan ödemlerinde kullanılabilir.",
        "renal": "Glomerüler hasar yoksa idrarla elenmez. Böbrek yetmezliğinde hacim yüklenmesine dikkat edilmelidir.",
        "liver": "Normal sentez yeri karaciğerdir, yetmezliğinde eksikliği sık görülür.",
        "obesity": "Standart klinik protokoller izlenir.",
        "geriatri": "Yaşlılarda hızlı infüzyon akut akciğer ödemini ve kalp yetmezliğini tetikleyebilir.",
        "kinetics": "PharmacokineticsInfo(onset = \"Infüzyon sonu\", peak = \"15 - 30 dakika\", duration = \"Onkotik etkisi 12 - 24 saat sürer\", halfLife = \"15 - 20 gün\", elimination = \"Hücre içinde lizozomal enzimlerle aminoasitlere yıkılır\")",
        "warnings": ["Akut Akciğer Ödemi: Onkotik etkisi nedeniyle intravasküler hacmi çok hızlı genişletebileceği için kalp yetmezliği veya ciddi anemisi olanlarda akciğer ödemine yol açabilir.", "Pıhtılaşma Bozukluğu: Çok yüksek hacimlerde verilirse pıhtılaşma faktörlerini dilüe edebilir."],
        "pearls": ["%20'lik albümin hiperonkotik bir solüsyondur, verilen her 1 mL albümin damar yatağına interstisyumdan 3-4 mL sıvı çekerek plazmayı genişletir. Ciddi sepsis, yanık ve sirozlu asit hastalarında hacim restorasyonu için paha biçilemez bir doğal kolloiddir.", "Enfeksiyon bulaş riskini önlemek için pastörize edilmiştir."]
    }
]

def clean_html(text):
    text = re.sub(r'<[^>]+>', ' ', text)
    text = re.sub(r'\s+', ' ', text)
    return text.strip()

def escape_kotlin_str(val):
    if not val:
        return ""
    val = val.replace('\\', '\\\\').replace('"', '\\"').replace('\n', ' ')
    if len(val) > 400:
        val = val[:400] + "..."
    return val.strip()

def extract_dose_info(text):
    if not text:
        return "Dozaj bilgisi belirtilmemiştir.", ""
    text = escape_kotlin_str(text)
    idx = text.find('. ')
    if idx != -1 and idx < 150:
        dose = text[:idx+1]
        notes = text[idx+2:]
    else:
        if len(text) <= 120:
            dose = text
            notes = ""
        else:
            dose = text[:120] + "..."
            notes = text
    return dose, notes

def parse_existing_drugs():
    print("Reading SeedDataDrugsPart1.kt and SeedDataDrugsPart2.kt to preserve standard drugs...")
    drugs = []
    for path in [
        "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart1.kt",
        "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart2.kt"
    ]:
        if not os.path.exists(path):
            continue
        with open(path, 'r', encoding='utf-8') as f:
            text = f.read()
            
        # Parse blocks
        idx = 0
        while True:
            idx = text.find('Drug(', idx)
            if idx == -1:
                break
            paren_count = 1
            sub_idx = idx + 5
            while paren_count > 0 and sub_idx < len(text):
                if text[sub_idx] == '(':
                    paren_count += 1
                elif text[sub_idx] == ')':
                    paren_count -= 1
                sub_idx += 1
            drugs.append(text[idx:sub_idx])
            idx = sub_idx
            
    print(f"Found {len(drugs)} existing standard drugs.")
    return drugs

def main():
    existing_blocks = parse_existing_drugs()
    
    # Load parsed JSON
    print(f"Loading parsed JSON file: {PARSED_JSON_FILE}")
    with open(PARSED_JSON_FILE, 'r', encoding='utf-8') as f:
        parsed_data = json.load(f)
        
    all_drugs_code = []
    
    # Add existing drugs (preserving them exactly as is)
    all_drugs_code.extend(existing_blocks)
    
    # Track what IDs we have already added to avoid duplication (e.g. lipid_emulsion is already in existing)
    added_ids = set()
    for block in existing_blocks:
        match = re.search(r'drugId\s*=\s*\"([^\"]+)\"', block)
        if match:
            added_ids.add(match.group(1))
            
    print(f"Already seeded IDs from existing: {added_ids}")

    # Add missing clinical-grade drugs
    for item in MISSING_DRUGS:
        drug_id = item["id"]
        if drug_id in added_ids:
            print(f"Skipping missing drug {drug_id} (already present in existing).")
            continue
            
        brands_str = ", ".join([f'"{b}"' for b in item["brand_names"]])
        pearls_str = ", ".join([f'"{p}"' for p in item["pearls"]])
        warnings_str = ", ".join([f'"{w}"' for w in item["warnings"]])
        
        code = f'''Drug(
            drugId = "{drug_id}",
            nameTr = "{item["name_tr"]}",
            nameEn = "{item["name_en"]}",
            genericName = "{item["name_en"]}",
            brandNames = listOf({brands_str}),
            category = "{item["category"]}",
            drugClass = "{item["class_tr"]}",
            mechanismTr = "{item["mechanism_tr"]}",
            adultDoses = mapOf(
                "dosing" to DoseInfo(
                    dose = "{item["adult_dose"]}",
                    notes = "{item["adult_notes"]}"
                )
            ),
            pediatricDoses = mapOf(
                "dosing" to DoseInfo(
                    dose = "{item["ped_dose"]}",
                    notes = "{item["ped_notes"]}"
                )
            ),
            specialPopulations = mapOf(
                "renal_yetmezlik" to "{item["renal"]}",
                "karaciger_yetmezligi" to "{item["liver"]}",
                "obezite" to "{item["obesity"]}",
                "geriatri" to "{item["geriatri"]}"
            ),
            pharmacokinetics = {item["kinetics"]},
            contraindications = listOf("Bileşenlerine karşı bilinen aşırı duyarlılık"),
            warnings = listOf({warnings_str}),
            clinicalPearls = listOf({pearls_str}),
            requiresClinicalValidation = false
        )'''
        all_drugs_code.append(code)
        added_ids.add(drug_id)
        print(f"Added missing clinical drug: {drug_id}")

    # Add the remaining parsed drugs from UpToDate
    for key, data in parsed_data.items():
        # Match standard key or map to mapped key
        mapped_key = key
        if key == "adenosine_systemic":
            mapped_key = "adenosine"
        elif key == "acetaminophen_paracetamol":
            mapped_key = "paracetamol"
        elif key == "ipratropium_oral_inhalation":
            mapped_key = "ipratropium"
        elif key == "hydrocortisone_systemic":
            mapped_key = "hydrocortisone"
        elif key == "chlorpheniramine":
            mapped_key = "chlorpheniramine"
        elif key == "fibrinogen_concentrate_human":
            mapped_key = "fibrinogen_concentrate"
        elif key == "prothrombin_complex_concentrate_human":
            mapped_key = "prothrombin_complex_concentrate"
        elif key == "protamine_sulfate":
            mapped_key = "protamine"
        elif key == "heparin_unfractionated":
            mapped_key = "heparin"
        elif key == "vitamin_k1_phytonadione_phytomenadione":
            mapped_key = "vitamin_k"
        elif key == "methylergonovine":
            mapped_key = "methylergometrine"
        elif key == "insulin_regular":
            mapped_key = "insulin_regular"

        if mapped_key in added_ids:
            # Already added (e.g. standard ones in SeedDataDrugs like propofol, fentanyl, etc.)
            continue

        # Look up mappings for TR translation metadata
        mapping = DRUG_MAPPINGS.get(key, DRUG_MAPPINGS.get(mapped_key))
        if not mapping:
            print(f"Warning: No mapping dictionary entry for {key}. Using default fallback values.")
            mapping = {
                "name_tr": data["drug_name"].split(" (")[0],
                "name_en": data["drug_name"].split(" (")[0],
                "category": "icu_metabolic",
                "class_tr": data.get("category", "Tıbbi İlaç Monografı"),
                "mechanism_tr": "Klinik monograf bilgileri için kaynak dokümana başvurunuz.",
                "pearls": ["UpToDate klinik monografından alınmıştır."]
            }

        # Extract and clean doses
        sec = data["sections"]
        adult_text = sec.get("Dosing: Adult", "")
        adult_dose, adult_notes = extract_dose_info(adult_text)
        
        ped_text = sec.get("Dosing: Pediatric", "")
        if ped_text:
            ped_dose, ped_notes = extract_dose_info(ped_text)
            ped_block = f'''mapOf(
                "dosing" to DoseInfo(
                    dose = "{ped_dose}",
                    notes = "{ped_notes}"
                )
            )'''
        else:
            ped_block = "emptyMap()"

        # Special Populations
        renal_txt = escape_kotlin_str(sec.get("Dosing: Kidney Impairment: Adult", "Böbrek yetmezliğinde doz ayarlaması gerekebilir."))
        liver_txt = escape_kotlin_str(sec.get("Dosing: Liver Impairment: Adult", "Karaciğer yetmezliğinde doz ayarlaması gerekebilir."))
        obesity_txt = escape_kotlin_str(sec.get("Dosing: Obesity: Adult", "Obezite hastalarında dozaj ayarlaması için yeterli veri bulunmamaktadır."))
        geriatri_txt = escape_kotlin_str(sec.get("Dosing: Older Adult", "Özel bir doz ayarlaması belirtilmemiştir."))

        # Kinetics
        kin_txt = sec.get("Pharmacokinetics", "")
        if kin_txt:
            kin_txt_escaped = escape_kotlin_str(kin_txt)
            kin_block = f'''PharmacokineticsInfo(
                onset = "Veri bulunmuyor",
                peak = "Veri bulunmuyor",
                duration = "Veri bulunmuyor",
                halfLife = "Kinetik detaylar için monografa bakınız",
                elimination = "{kin_txt_escaped}"
            )'''
        else:
            kin_block = "null"

        # Warnings
        warnings_txt = sec.get("Warnings/Precautions", "")
        warnings_list = []
        if warnings_txt:
            parts = [p.strip() for p in warnings_txt.split('•') if p.strip()]
            if len(parts) > 1:
                warnings_list = [escape_kotlin_str(p)[:180] + "..." for p in parts[:3]]
            else:
                sentences = [s.strip() for s in warnings_txt.split('. ') if s.strip()]
                warnings_list = [escape_kotlin_str(s)[:180] + "..." for s in sentences[:3]]
        else:
            warnings_list = ["Klinik takip ve hastanın yakın izlemi önerilir."]

        brands_str = ", ".join([f'"{b}"' for b in data.get("brands", [])[:5]])
        warnings_str = ", ".join([f'"{w}"' for w in warnings_list])
        pearls_str = ", ".join([f'"{p}"' for p in mapping.get("pearls", [])])

        code = f'''Drug(
            drugId = "{mapped_key}",
            nameTr = "{mapping["name_tr"]}",
            nameEn = "{mapping["name_en"]}",
            genericName = "{escape_kotlin_str(data["drug_name"])}",
            brandNames = listOf({brands_str}),
            category = "{mapping["category"]}",
            drugClass = "{mapping["class_tr"]}",
            mechanismTr = "{mapping["mechanism_tr"]}",
            adultDoses = mapOf(
                "dosing" to DoseInfo(
                    dose = "{adult_dose}",
                    notes = "{adult_notes}"
                )
            ),
            pediatricDoses = {ped_block},
            specialPopulations = mapOf(
                "renal_yetmezlik" to "{renal_txt}",
                "karaciger_yetmezligi" to "{liver_txt}",
                "obezite" to "{obesity_txt}",
                "geriatri" to "{geriatri_txt}"
            ),
            pharmacokinetics = {kin_block},
            contraindications = listOf("Bileşenlerine karşı bilinen aşırı duyarlılık"),
            warnings = listOf({warnings_str}),
            clinicalPearls = listOf({pearls_str}),
            requiresClinicalValidation = true
        )'''
        all_drugs_code.append(code)
        added_ids.add(mapped_key)
        print(f"Added parsed drug: {mapped_key}")

    print(f"Total drugs generated: {len(all_drugs_code)}")

    # Split into 5 parts of roughly ~21 drugs each
    num_drugs = len(all_drugs_code)
    part_size = (num_drugs + 4) // 5  # Ceiling division
    print(f"Each part will contain up to {part_size} drugs.")

    for i in range(5):
        start = i * part_size
        end = min((i + 1) * part_size, num_drugs)
        part_drugs = all_drugs_code[start:end]
        part_name = f"SeedDataDrugsPart{i+1}"
        file_path = os.path.join(OUTPUT_DIR, f"{part_name}.kt")
        
        print(f"Writing {part_name} ({len(part_drugs)} drugs) to {file_path}")
        
        with open(file_path, "w", encoding="utf-8") as out:
            out.write(f'''package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo

object {part_name} {{
    val drugsList = listOf(
''')
            out.write(",\n".join(part_drugs))
            out.write('''
    )
}
''')

    # Regenerate main SeedDataDrugs.kt
    print(f"Regenerating main SeedDataDrugs.kt to concatenate all parts.")
    with open(ORIGINAL_SEED_FILE, "w", encoding="utf-8") as out:
        out.write('''package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug

object SeedDataDrugs {
    val drugsList = SeedDataDrugsPart1.drugsList +
                    SeedDataDrugsPart2.drugsList +
                    SeedDataDrugsPart3.drugsList +
                    SeedDataDrugsPart4.drugsList +
                    SeedDataDrugsPart5.drugsList
}
''')

    print("Generation complete!")

if __name__ == "__main__":
    main()
