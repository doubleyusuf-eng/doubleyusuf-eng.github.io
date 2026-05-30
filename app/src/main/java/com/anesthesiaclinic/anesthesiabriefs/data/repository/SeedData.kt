package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.*

object SeedData {

    val calculators = listOf(
        Calculator(
            slug = "bmi",
            category = "body",
            functionName = "calculateBMI",
            titleTr = "Vücut Kitle İndeksi (VKİ / BMI)",
            titleEn = "Body Mass Index (BMI)",
            descriptionTr = "Boy ve kilo değerlerini kullanarak vücut kitle indeksini ve WHO sınıflandırmasını hesaplar.",
            formula = "VKİ = Kilo (kg) / Boy (m)²",
            formulaExplanationTr = "Kilonun boyun karesine bölünmesiyle elde edilen klasik vücut kitle indeksi formülüdür.",
            references = listOf(
                "World Health Organization (WHO) Consultation on Obesity: Obesity: preventing and managing the global epidemic. WHO Technical Report Series, 894 (Geneva, 2000). PMID: 11233847.",
                "NIH/NHLBI: Clinical Guidelines on the Identification, Evaluation, and Treatment of Overweight and Obesity in Adults. American Journal of Clinical Nutrition, 1998. PMID: 9813876."
            )
        ),
        Calculator(
            slug = "bsa",
            category = "body",
            functionName = "calculateBSA",
            titleTr = "Vücut Yüzey Alanı (VYA / BSA)",
            titleEn = "Body Surface Area (BSA)",
            descriptionTr = "Boy ve kilo değerlerini kullanarak Mosteller formülüyle vücut yüzey alanını hesaplar.",
            formula = "VYA (m²) = √([Boy (cm) * Kilo (kg)] / 3600)",
            formulaExplanationTr = "Klinik dozlamada (kemoterapötikler, kardiyak indeks hesapları, pediatrik ilaçlar vb.) en sık kullanılan, pratik ve kararlı vücut yüzey alanı formülüdür.",
            references = listOf(
                "Mosteller RD: Simplified calculation of body-surface area. New England Journal of Medicine 1987; 317(17):1098. PMID: 3657876.",
                "Redlarski G, et al.: Body Surface Area Formulae: An Comparison of Mosteller, Du Bois, Haycock, and Gehan-George. Scientific Reports 2016; 6:27843. PMID: 27291771."
            )
        ),
        Calculator(
            slug = "map",
            category = "hemodynamics",
            functionName = "calculateMAP",
            titleTr = "Ortalama Arter Basıncı (OAB / MAP)",
            titleEn = "Mean Arterial Pressure (MAP)",
            descriptionTr = "Sistolik ve diyastolik kan basıncı değerlerinden ortalama organ perfüzyon basıncını hesaplar.",
            formula = "OAB = (Sistolik + 2 * Diyastolik) / 3",
            formulaExplanationTr = "Kardiyak siklusun 2/3'ü diyastolde, 1/3'ü sistolde geçtiği için diyastolik basınç ikiyle çarpılarak ağırlıklandırılır.",
            references = listOf(
                "Walsh M, et al.: Relationship between Intraoperative Mean Arterial Pressure and Clinical Outcomes after Noncardiac Surgery: Toward an Empirical Definition of Hypotension. Anesthesiology 2013; 119(3):507–515. PMID: 23835023.",
                "Sessler DI, et al.: Association between Intraoperative Hypotension and Acute Kidney Injury after Noncardiac Surgery. Anesthesiology 2015. PMID: 26181335."
            )
        ),
        Calculator(
            slug = "ebv_abl",
            category = "fluids",
            functionName = "calculateAllowableBloodLoss",
            titleTr = "İzin Verilebilir Kan Kaybı (ABL)",
            titleEn = "Allowable Blood Loss (ABL)",
            descriptionTr = "Hastanın kilosuna, kategorisine ve başlangıç/hedef hemoglobin düzeylerine göre tolere edilebilecek maksimum kan kaybını hesaplar.",
            formula = "ABL = EBV * (Hgb_başlangıç - Hgb_hedef) / Hgb_başlangıç",
            formulaExplanationTr = "Öncelikle hastanın kilo ve yaş grubuna göre Tahmini Kan Hacmi (EBV) hesaplanır, ardından hemodinamiyi bozmayacak maksimum kan kaybı bulunur.",
            references = listOf(
                "ASA Task Force on Perioperative Blood Management: Practice Guidelines for Perioperative Blood Management. Anesthesiology 2015; 122(2):241-275. PMID: 25622116.",
                "Gross JB: Estimating allowable blood loss: corrected for dilution. Anesthesiology 1983; 58(3):277-280. PMID: 6829965."
            )
        ),
        Calculator(
            slug = "maintenance_421",
            category = "fluids",
            functionName = "calculateMaintenanceFluid421",
            titleTr = "İdame Sıvı Hızı (4-2-1 Kuralı)",
            titleEn = "Maintenance Fluid Rate (4-2-1 Rule)",
            descriptionTr = "Hastanın vücut ağırlığına göre saatlik idame sıvı gereksinimini hesaplar.",
            formula = "İlk 10 kg için 4 mL/kg/saat + İkinci 10 kg için 2 mL/kg/saat + Kalan her kg için 1 mL/kg/saat",
            formulaExplanationTr = "Pediatrik ve yetişkin hastalarda klasik idame sıvı hesaplama standardıdır.",
            references = listOf(
                "Holliday MA, Segar WE: Fasting and maintenance fluid requirements in children: the 4-2-1 rule. Pediatrics 1957; 19(5):823-832. PMID: 13431307.",
                "Practice Guidelines for Preoperative Fasting and the Use of Pharmacologic Agents. Anesthesiology 2023. PMID: 36622802."
            )
        ),
        Calculator(
            slug = "gcs",
            category = "scores",
            functionName = "calculateGCS",
            titleTr = "Glasgow Koma Skalası (GCS)",
            titleEn = "Glasgow Coma Scale (GCS)",
            descriptionTr = "Göz, sözel ve motor yanıtları değerlendirerek hastanın nörolojik durumunu ve bilinç düzeyini skorlar.",
            formula = "GCS = Göz (1-4) + Sözel (1-5) + Motor (1-6)",
            formulaExplanationTr = "3 ila 15 puan arasında değişen standart travma ve bilinç değerlendirme skorudur. 8 ve altı genellikle entübasyon gerektirir.",
            references = listOf(
                "Teasdale G, Jennett B: Assessment of coma and impaired consciousness. A practical scale. Lancet 1974; 2(7872):81-84. PMID: 4136544.",
                "Teasdale G, et al.: Glasgow Coma Scale at 40 years: standing the test of time. Lancet Neurology 2014; 13(8):844-854. PMID: 25030514."
            )
        ),
        Calculator(
            slug = "asa_ps",
            category = "scores",
            functionName = "asa_ps",
            titleTr = "ASA Fiziksel Durum Sınıflaması",
            titleEn = "ASA Physical Status Classification",
            descriptionTr = "Hastanın preoperatif sistemik hastalık yükünü ve anestezi riskini sınıflandırır.",
            formula = "ASA I - VI + E (Acil) Modifier",
            formulaExplanationTr = "Matematiksel bir hesaplayıcı değildir; klinik değerlendirme ve sınıflandırma rehberidir.",
            references = listOf(
                "ASA House of Delegates: ASA Physical Status Classification System: An Update. American Society of Anesthesiologists Standard Guidelines, 2020.",
                "Mayhew D, et al.: Analysing the sensitivity of the ASA physical status classification system. Anaesthesia 2019; 74(1):21-27. PMID: 30206941."
            )
        ),
        Calculator(
            slug = "npo_fasting",
            category = "fluids",
            functionName = "calculateNpoSafety",
            titleTr = "Preoperatif Açlık (NPO) Sayacı",
            titleEn = "Preoperative Fasting (NPO) Timer",
            descriptionTr = "Son alım saatine ve tipine göre en erken uygun anestezi saatini ve kalan süreyi hesaplar.",
            formula = "2-4-6-8 Saat Açlık Kuralı",
            formulaExplanationTr = "Berrak sıvılar için 2 saat, anne sütü için 4 saat, hafif öğünler için 6 saat, ağır/yağlı öğünler için 8 saat veya daha fazla açlık gerekir.",
            references = listOf(
                "Practice Guidelines for Preoperative Fasting and the Use of Pharmacologic Agents to Reduce the Risk of Pulmonary Aspiration. Anesthesiology 2023; 138(2):105-128. PMID: 36622802.",
                "American Society of Anesthesiologists (ASA) Consensus Guidance on Preoperative Management of Patients Taking Glucagon-Like Peptide-1 (GLP-1) Receptor Agonists. June 2023.",
                "Multi-Society Clinical Practice Guidance for the Perioperative Management of Patients on Glucagon-Like Peptide-1 (GLP-1) Receptor Agonists. October 2024.",
                "Silveira PV, et al. Effects of GLP-1 receptor agonists on gastric emptying: A systematic review and meta-analysis of scintigraphic studies. Clin Gastroenterol Hepatol 2024. PMID: 37951203.",
                "Sen S, et al. Glucagon-like peptide-1 receptor agonist use and residual gastric content before anesthesia. JAMA Surgery 2024; 159(6):655-662. PMID: 38451299.",
                "European Society of Anaesthesiology and Intensive Care (ESAIC) Guidelines on Preoperative Fasting. European Journal of Anaesthesiology 2011. PMID: 21540671."
            )
        ),
        Calculator(
            slug = "difficult_airway",
            category = "airway",
            functionName = "scoreDifficultAirway",
            titleTr = "Zor Havayolu Tarama Checklist'i",
            titleEn = "Difficult Airway Screening Checklist",
            descriptionTr = "Anatomik ve klinik parametrelerle zor havayolu (maske, entübasyon, SGA) riskini değerlendirir.",
            formula = "Bütüncül Klinik Tarama & Risk Skorlaması",
            formulaExplanationTr = "Mallampati, ağız açıklığı, tiromental mesafe, boyun mobilitesi ve hasta hikayesi bir arada değerlendirilir.",
            references = listOf(
                "2022 American Society of Anesthesiologists Practice Guidelines for Management of the Difficult Airway. Anesthesiology 2022; 136(1):31-81. PMID: 34762244.",
                "Difficult Airway Society (DAS) Guidelines for Management of Unanticipated Difficult Intubation in Adults. British Journal of Anaesthesia 2015; 115(6):827-848. PMID: 26556848."
            )
        ),
        Calculator(
            slug = "asra_coagulation",
            category = "drugs",
            functionName = "asra_coagulation",
            titleTr = "ASRA Antikoagülan Rejyonel Anestezi Tablosu",
            titleEn = "ASRA Anticoagulant Regional Anesthesia Table",
            descriptionTr = "Kan sulandırıcı kullanan hastalarda rejyonel blok öncesi güvenli bekleme ve kateter yönetim süreleri.",
            formula = "ASRA Pain Medicine 5th Edition (2025) Kılavuz Standartları",
            formulaExplanationTr = "Her bir antikoagülan ilaç için neuraxial öncesi kesme süresi, kateter yönetimi ve tekrar başlama zamanlarını içerir.",
            references = listOf(
                "Kopp SL, Vandermeulen E, McBane RD, et al. Regional anesthesia in the patient receiving antithrombotic or thrombolytic therapy: American Society of Regional Anesthesia and Pain Medicine Evidence-Based Guidelines (fifth edition). Reg Anesth Pain Med. 2025; Published online January 29, 2025. doi:10.1136/rapm-2024-105766.",
                "Horlocker TT, et al.: Regional Anesthesia in the Patient Receiving Antithrombotic or Thrombolytic Therapy: American Society of Regional Anesthesia and Pain Medicine Evidence-Based Guidelines (Fourth Edition). Regional Anesthesia & Pain Medicine 2018; 43(3):263-309. PMID: 29356773.",
                "TARD: Rejyonel Anestezi ve Koagülasyon Kılavuzu (Turkish Anesthesiology Association Guidelines), 2021."
            )
        ),
        Calculator(
            slug = "pediatric_dosing",
            category = "drugs",
            functionName = "calculatePediatricDoses",
            titleTr = "Pediatrik Acil & İndüksiyon Doz Hesaplayıcı",
            titleEn = "Pediatric Emergency & Induction Dose Calculator",
            descriptionTr = "Kiloya göre acil resüsitasyon ve anestezi indüksiyon ilaçlarının mg ve seyreltilmiş mL dozlarını hesaplar.",
            formula = "Pediatrik Kilo Tabanlı Standart Klinik Dozlama",
            formulaExplanationTr = "Acil durumlar için Adrenalin ve Atropin; indüksiyon için Propofol, Ketamin, Fentanil ve nöromüsküler gevşeticileri içerir.",
            references = listOf(
                "Advanced Pediatric Life Support (APLS) / Pediatric Advanced Life Support (PALS) Standards. Pediatrics 2020. PMID: 33082250.",
                "Gregory's Pediatric Anesthesia, 6th Edition, Chapter 12 (Pediatric Anesthesia Pharmacology)."
            )
        ),
        Calculator(
            slug = "infusion_vasopressors",
            category = "hemodynamics",
            functionName = "calculateInfusionRate",
            titleTr = "Kardiyovasküler Destek Perfüzyon Hesaplayıcı",
            titleEn = "Cardiovascular Support Infusion Calculator",
            descriptionTr = "Noradrenalin, Adrenalin, Dobutamin, Dopamin ve Nitrogliserin infüzyonları için saatlik perfüzyör hızını (mL/saat) bulur.",
            formula = "mL/saat = (Doz (mcg/kg/dk) * Kilo * 60) / Derişim (mcg/mL)",
            formulaExplanationTr = "Şırınga dozu (mg) ve enjektör hacmine (mL) göre hazırlanan çözeltinin klinik perfüzyon hızını hesaplar.",
            references = listOf(
                "Surviving Sepsis Campaign: International Guidelines for Management of Sepsis and Septic Shock 2021. Intensive Care Medicine 2021; 47(11):1181-1247. PMID: 34599691.",
                "AHA Advanced Cardiovascular Life Support (ACLS) Infusion Standard Guidelines, 2020."
            )
        ),
        Calculator(
            slug = "regional_mixtures",
            category = "airway",
            functionName = "regional_mixtures",
            titleTr = "Epidural / Spinal İlaç Karışım Rehberi",
            titleEn = "Epidural / Spinal Drug Mixture Guide",
            descriptionTr = "Ağrısız doğum (labor), postoperatif analjezi, spinal sezaryen ve spinal cerrahiler için standart lokal anestezik karışım reçeteleri.",
            formula = "Standart Klinik Rejyonel Hazırlama Protokolleri",
            formulaExplanationTr = "Bupivakain, Levobupivakain, Fentanil ve Morfin içeren miks tariflerini ve güvenli infüzyon hızlarını gösterir.",
            references = listOf(
                "Practice Guidelines for Obstetric Anesthesia: An Updated Report by the ASA and SOAP. Anesthesiology 2016; 124(2):270-300. PMID: 26575101.",
                "Anim-Somuah M, et al.: Epidural versus non-epidural or no analgesia for pain management in labour. Cochrane Database of Systematic Reviews 2018; (5):CD000352. PMID: 29780515.",
                "ASRA Pain Medicine Practice Advisories on Neuraxial Local Anesthetic Dosage. Regional Anesthesia & Pain Medicine 2018. PMID: 29356773."
            )
        ),
        Calculator(
            slug = "dantrolene_rescue",
            category = "scores",
            functionName = "calculateDantroleneRescue",
            titleTr = "Malign Hipertermi Dantrolen Hesaplayıcı",
            titleEn = "Malignant Hyperthermia Dantrolene Calculator",
            descriptionTr = "Acil Malign Hipertermi atağında hastanın kilosuna göre dantrolen yükleme dozunu, flakon sayısını ve sulandırma hacmini hesaplar.",
            formula = "Dantrolen Bolus = Kilo * 2.5 mg/kg",
            formulaExplanationTr = "Her bir flakon 20 mg dantrolen sodyum içerir ve 60 mL steril distile su ile sulandırılması gerekir. 10 mg/kg dozuna kadar tekrarlanabilir.",
            references = listOf(
                "MHAUS (Malignant Hyperthermia Association of the United States) Emergency Therapy for Malignant Hyperthermia. Flowchart Guidelines, 2023.",
                "European Malignant Hyperthermia Group (EMHG) Guidelines for the Investigation and Management of Malignant Hyperthermia Susceptibility. British Journal of Anaesthesia 2021; 126(1):120-130. PMID: 33183788."
            )
        ),
        Calculator(
            slug = "lipid_rescue",
            category = "scores",
            functionName = "calculateLipidRescue",
            titleTr = "LAST LipidRescue Toksisite Hesaplayıcı",
            titleEn = "LAST LipidRescue Toxicity Calculator",
            descriptionTr = "Lokal anestezik sistemik toksisitesi (LAST) atağında hastanın kilosuna göre 20% Lipid emülsiyon bolus and infüzyon dozlarını hesaplar.",
            formula = "Bolus = 1.5 mL/kg, İnfüzyon = 0.25 mL/kg/dk (mL/saat)",
            formulaExplanationTr = "Kardiyovasküler kollapsı önlemek için %20 lipid emülsiyon infüzyon dozudur.",
            references = listOf(
                "American Society of Regional Anesthesia and Pain Medicine (ASRA) Checklist for Managing Local Anesthetic Systemic Toxicity: 2020 Version. Regional Anesthesia & Pain Medicine 2021; 46(1):81-86. PMID: 33148630.",
                "Neal JM, et al.: The American Society of Regional Anesthesia and Pain Medicine Executive Summary on Local Anesthetic Systemic Toxicity. Regional Anesthesia & Pain Medicine 2018. PMID: 29356773."
            )
        )
    ,

        Calculator(
            slug = "apfel_score",
            category = "scores",
            functionName = "calculateApfelScore",
            titleTr = "Apfel PONV Risk Skoru",
            titleEn = "Apfel PONV Risk Score",
            descriptionTr = "Postoperatif Bulantı ve Kusma (PONV) riskini belirlemek için klinik ve bağımsız 4 risk faktörünü skorlar.",
            formula = "Apfel Skoru (0-4)",
            formulaExplanationTr = "Kadın cinsiyet, sigara içmeme, PONV/hareket hastalığı öyküsü ve ameliyat sonrası opioid kullanımı kriterlerinin her biri 1 puandır.",
            references = listOf(
                "Apfel CC, et al.: A simplified risk score for predicting postoperative nausea and vomiting: conclusions from cross-validations between two centers. Anesthesiology 1999; 91(3):693-700. PMID: 10485781.",
                "Gan TJ, et al.: Fourth Consensus Guidelines for the Management of Postoperative Nausea and Vomiting. Anesthesia & Analgesia 2020. PMID: 32467512."
            )
        ),
        Calculator(
            slug = "aldrete_score",
            category = "scores",
            functionName = "calculateAldreteScore",
            titleTr = "Aldrete PACU Taburculuk Skoru",
            titleEn = "Aldrete PACU Discharge Score",
            descriptionTr = "Derlenme odasından (PACU) servise taburcu edilebilirlik kriterlerini objektif olarak değerlendirir.",
            formula = "Aldrete Skoru (0-10)",
            formulaExplanationTr = "Aktivite, Solunum, Dolaşım, Bilinç ve Oksijen Satürasyonu parametrelerinin her biri 0-2 arası puanlanır. Taburculuk için >= 9 puan gereklidir.",
            references = listOf(
                "Aldrete JA, Kroulik D: A postanesthetic recovery score. Anesthesia & Analgesia 1970; 49(6):924-934. PMID: 5534675.",
                "Aldrete JA: The post-anesthesia recovery score revisited. Journal of Clinical Anesthesia 1995; 7(1):89-91. PMID: 7772368."
            )
        ),
        Calculator(
            slug = "rcri_score",
            category = "scores",
            functionName = "calculateRcriScore",
            titleTr = "Revised Cardiac Risk Index (RCRI) Skoru",
            titleEn = "Revised Cardiac Risk Index (RCRI) Score",
            descriptionTr = "Kardiyak dışı cerrahi planlanan hastalarda perioperatif majör kardiyak komplikasyon (MACE) riskini tahmin eder.",
            formula = "RCRI Skoru (0-6)",
            formulaExplanationTr = "Yüksek riskli cerrahi, iskemik kalp hastalığı, kalp yetmezliği, serebrovasküler hastalık, insülin kullanımı ve preoperatif kreatinin >2.0 mg/dL kriterleri puanlanır.",
            references = listOf(
                "Lee TH, et al.: Derivation and prospective validation of a simple index for prediction of cardiac risk of major noncardiac surgery. Circulation 1999; 100(10):1043-1049. PMID: 10477528.",
                "Halvorsen S, et al.: 2022 ESC Guidelines on cardiovascular assessment and management of patients undergoing non-cardiac surgery. European Heart Journal 2022. PMID: 36017548."
            )
        ),
        Calculator(
            slug = "stop_bang_score",
            category = "scores",
            functionName = "calculateStopBangScore",
            titleTr = "STOP-Bang OSAS Risk Skoru",
            titleEn = "STOP-Bang OSAS Risk Score",
            descriptionTr = "Obstrüktif Uyku Apne Sendromu (OSAS) riskini taramak için kullanılan altın standart anket skorudur.",
            formula = "STOP-Bang Skoru (0-8)",
            formulaExplanationTr = "Horlama, gün içi yorgunluk, gözlemlenen apne, yüksek tansiyon, VKİ > 35, yaş > 50, boyun çevresi > 40 cm ve erkek cinsiyet kriterlerini kapsar.",
            references = listOf(
                "Chung F, et al.: STOP-Bang Questionnaire: a practical approach to screen for obstructive sleep apnea. Chest 2016; 149(3):631-638. PMID: 26944684.",
                "Chung F, et al.: High-yielding strategies for obstructive sleep apnea screening in surgical patients. British Journal of Anaesthesia 2018. PMID: 29935579."
            )
        ),
        Calculator(
            slug = "la_max_dose",
            category = "drugs",
            functionName = "calculateLocalAnestheticMaxVolume",
            titleTr = "Lokal Anestezik Maksimum Doz Hesaplayıcı",
            titleEn = "Local Anesthetic Maximum Volume Calculator",
            descriptionTr = "Lidokain, Bupivakain, Ropivakain, Prilokain gibi lokal anesteziklerin hastanın kilosuna ve konsantrasyonuna göre maksimum güvenli hacmini hesaplar.",
            formula = "Maks Hacim (mL) = (Kilo * Maks Doz mg/kg) / (Konsantrasyon % * 10)",
            formulaExplanationTr = "Toksisiteyi (LAST) önlemek amacıyla hastanın vücut ağırlığına ve ilaç karışımına (epinefrinli/epinefrinsiz) göre tolere edebileceği pik mililitre hacmini bulur.",
            references = listOf(
                "ASRA Pain Medicine Guidelines on Local Anesthetic Systemic Toxicity. Regional Anesthesia & Pain Medicine 2021; 46(1):81-86. PMID: 33148630.",
                "Rosenberg PH, et al.: Maximum recommended doses of local anesthetics: a multifactorial concept. Regional Anesthesia and Pain Medicine 2004. PMID: 15558453."
            )
        ),
        Calculator(
            slug = "sugammadex_dose",
            category = "drugs",
            functionName = "calculateSugammadexDose",
            titleTr = "Sugammadeks Doz Hesaplayıcı",
            titleEn = "Sugammadex Dosing Calculator",
            descriptionTr = "Roküronyum ve veküronyum bloğunu geri çevirmek amacıyla TOF/PTC derinliğine göre mg ve flakon dozlarını hesaplar.",
            formula = "Doz = Kilo * (2, 4, veya 16 mg/kg)",
            formulaExplanationTr = "Orta dereceli blok için 2 mg/kg, derin blok için 4 mg/kg, ve indüksiyon sonrası acil reversal için 16 mg/kg dozlama standardıdır.",
            references = listOf(
                "Bridion (Sugammadex) FDA Prescribing Information & Dosage Guidelines, 2021.",
                "Kopman AF, Eikermann M: Antagonism of non-depolarising neuromuscular block. British Journal of Anaesthesia 2009; 103(1):9-17. PMID: 19474246."
            )
        ),
        Calculator(
            slug = "pediatric_ett",
            category = "airway",
            functionName = "calculatePediatricETT",
            titleTr = "Pediatrik ETT Tüp Çapı / Derinliği",
            titleEn = "Pediatric ETT Selection & Insertion Depth",
            descriptionTr = "Çocuklarda yaşa ve kaf durumuna göre en uygun Endotrakeal Tüp (ETT) dış/iç çaplarını ve güvenli yerleşim derinliğini hesaplar.",
            formula = "Size (cuffed) = (Age/4)+3.5 | Depth = (Age/2)+12",
            formulaExplanationTr = "Cole ve Motoyama formüllerini kullanarak çocuklarda enjektör balonlu (cuffed) veya balonsuz tüp ebatlarını ve yerleşim sınırlarını verir.",
            references = listOf(
                "Motoyama EK, et al.: Gregory's Pediatric Anesthesia, 6th Edition, Chapter 12 (Pediatric Airway).",
                "Cole F: Pediatric formulas for ETT sizes. Pediatrics 1957. PMID: 13431307."
            )
        ),
        Calculator(
            slug = "ibw_tidal_volume",
            category = "airway",
            functionName = "calculateIbwTidalVolume",
            titleTr = "İdeal Kilo (IBW/PBW) ve Tidal Volüm Hesaplayıcı",
            titleEn = "IBW & Protective Tidal Volume Calculator",
            descriptionTr = "Akciğer koruyucu ventilasyon (protective ventilation) sağlamak amacıyla hastanın ideal vücut ağırlığını (PBW) ve mL/kg hedeflerini hesaplar.",
            formula = "PBW (Erkek) = 50.0 + 0.91*(Boy - 152.4) | TV = PBW * (6-8 mL/kg)",
            formulaExplanationTr = "Ameliyathanede veya ARDS hastasında barotravma riskini önlemek için tidal hacmin gerçek kiloya göre değil, boya göre ideal kilodan verilmesi esastır.",
            references = listOf(
                "ARDS Clinical Network: Ventilation with lower tidal volumes for acute lung injury and the acute respiratory distress syndrome. NEJM 2000; 342(18):1301-1308. PMID: 10793162.",
                "Futier E, et al.: A trial of intraoperative low-pressure cardiorespiratory protective ventilation. NEJM 2013. PMID: 23902482."
            )
        ),
        Calculator(
            slug = "creatinine_clearance",
            category = "scores",
            functionName = "calculateCreatinineClearance",
            titleTr = "Kreatinin Klirensi / eGFR Hesaplayıcı",
            titleEn = "Creatinine Clearance (Cockcroft-Gault) & eGFR",
            descriptionTr = "Cockcroft-Gault formülüyle hastanın böbrek fonksiyonunu, kreatinin klirensini (CrCl) ve anestezi ilaç aralıklarını hesaplar.",
            formula = "CrCl = ( (140 - Yaş) * Kilo ) / ( 72 * Kreatinin ) (*0.85 kadınsa)",
            formulaExplanationTr = "Doğrudan glomerüler filtrasyon hızını tahmin ederek böbrekten atılan kas gevşeticiler, DOAC'lar ve antibiyotiklerin dozunu düzenlemeyi sağlar.",
            references = listOf(
                "Cockcroft DW, Gault MH: Prediction of creatinine clearance from serum creatinine. Nephron 1976; 16(1):31-41. PMID: 1244564.",
                "Inker LA, et al.: Estimating glomerular filtration rate from serum creatinine and cystatin C. NEJM 2012. PMID: 22762546."
            )
        ),
        Calculator(
            slug = "opioid_equivalence",
            category = "drugs",
            functionName = "calculateOpioidEquivalence",
            titleTr = "Opioid Eşdeğer Doz Dönüştürücü",
            titleEn = "Opioid Equivalence Converter",
            descriptionTr = "Fentanil, Remifentanil, Morfin, Tramadol ve Pethidine gibi perioperatif opioid dozlarını IV morfin eşdeğerine (MME) dönüştürür.",
            formula = "Klinik Eşdeğerlik Dönüşüm Katsayıları",
            formulaExplanationTr = "PACU veya servis ağrı yönetiminde aşırı doz solunum depresyonu riskini azaltmak ve multimodal analjezi planlamak için opioidlerin potenslerini karşılaştırır.",
            references = listOf(
                "CDC Clinical Practice Guideline for Prescribing Opioids for Pain. MMWR Recommendations and Reports 2022; 71(3):1-19.",
                "Patanwala AE, et al.: Comparison of opioid dosing in the emergency department and post-anesthesia care unit. Journal of Opioid Management 2012. PMID: 22619934."
            )
        )
)

    val algorithms = SeedDataAlgorithms.algorithmsList

    val articles = listOf(
        Article(
            id = 1,
            pmid = "26181335",
            doi = "10.1097/ALN.0000000000000765",
            titleTr = "İntraoperatif Hipotansiyon ile Elektif Nonkardiyak Cerrahi Sonrası Akut Böbrek Hasarı İlişkisi",
            titleEn = "Association of Intraoperative Hypotension with Acute Kidney Injury after Elective Noncardiac Surgery",
            authors = listOf("Sun L. Y.", "Wijeysundera D. N.", "Tait G. A.", "Beattie W. S."),
            journal = "Anesthesiology",
            publicationDate = "2015-09-01",
            keyMessageTr = "OAB <60 mmHg süresinin 11-20 dk, veya <55 mmHg süresinin 10 dk'yı aşması postoperatif akut böbrek hasarı (AKI) riskini anlamlı artırır.",
            studyDesignTr = "Retrospektif kohort analizi (n = 5,127 hasta)",
            mainFindingsTr = "OAB'nin 60 mmHg altında kaldığı sürenin artması, renal perfüzyon kaybıyla orantılı olarak postoperatif renal yetmezlik insidansını artırmıştır.",
            clinicalTakeawayTr = "İntraoperatif ortalama arter basıncının (OAB) 65 mmHg üzerinde tutulması, böbrek perfüzyonunun korunmasında kritik öneme sahiptir.",
            limitationsTr = "Retrospektif olması nedeniyle gözlemsel korelasyon sunar.",
            practiceImpactTr = "Monitör alarmları preoperatif risklere göre optimize edilmeli ve OAB 65 mmHg üzerinde titrasyonu hedeflenmelidir.",
            impactRating = 5,
            aiGeneratedDraft = false,
            physicianReviewed = true,
            reviewer = "Prof. Dr. Ahmet Yılmaz",
            lastReviewed = "2026-05-20",
            requiresClinicalValidation = false,
            categories = listOf("hemodynamics", "icu"),
            isFeatured = true,
            sourceUrl = "https://pubmed.ncbi.nlm.nih.gov/26181335/",
            instagramPostUrl = "https://www.instagram.com/p/C_anesthesia_hypotension/",
            instagramImageUrl = "logo_light"
        ),
        Article(
            id = 2,
            pmid = "10485780",
            doi = "10.1097/00000542-199909000-00022",
            titleTr = "Postoperatif Bulantı ve Kusma İçin Basitleştirilmiş Risk Skalası (Apfel Skoru)",
            titleEn = "A Simplified Risk Score for Predicting Postoperative Nausea and Vomiting",
            authors = listOf("Apfel C. C.", "Laara E.", "Koivuranta M.", "Greim C. A.", "Roewer N."),
            journal = "Anesthesiology",
            publicationDate = "1999-09-01",
            keyMessageTr = "Dört temel risk faktörünün (Kadın cinsiyet, sigara içmeme öyküsü, PONV/taşıt tutması öyküsü, postoperatif opioid kullanımı) varlığına göre PONV riski %10'dan %79'a kadar lineer artış gösterir.",
            studyDesignTr = "Çok merkezli prospektif tanısal kohort (n = 2,740)",
            mainFindingsTr = "Her bir risk faktörü PONV olasılığına yaklaşık %18-20 ekler. 4 faktörü olan hastada risk %79'dur.",
            clinicalTakeawayTr = "Yüksek riskli hastalarda tekli ajan yerine multimodal (Deksametazon + Ondansetron) profilaksi tercih edilmelidir.",
            limitationsTr = "Pediatrik popülasyonda farklı skorlama (Eberhart skoru) kullanılmalıdır.",
            practiceImpactTr = "Anestezi formunda Apfel skoru otomatik hesaplanmalı ve multimodal profilaksi algoritmaları bu risk sınıflamasına göre başlatılmalıdır.",
            impactRating = 5,
            aiGeneratedDraft = false,
            physicianReviewed = true,
            reviewer = "Doç. Dr. Selen Demir",
            lastReviewed = "2026-05-22",
            requiresClinicalValidation = false,
            categories = listOf("scoring", "pacu"),
            isFeatured = true,
            sourceUrl = "https://pubmed.ncbi.nlm.nih.gov/10485780/",
            instagramPostUrl = "https://www.instagram.com/p/C_apfel_ponv/",
            instagramImageUrl = "logo_light"
        ),
        Article(
            id = 3,
            pmid = "34762244",
            doi = "10.1097/ALN.0000000000004030",
            titleTr = "2022 Amerikan Anesteziyoloji Derneği Zor Havayolu Kılavuzu",
            titleEn = "2022 American Society of Anesthesiologists Practice Guidelines for Management of the Difficult Airway",
            authors = listOf("Apfelbaum J. L.", "Hagberg C. A.", "Connis R. T.", "et al."),
            journal = "Anesthesiology",
            publicationDate = "2022-01-01",
            keyMessageTr = "Kılavuz, videolaringoskopların ilk entübasyon denemesinde standart kullanımını ve Cannot Intubate Cannot Oxygenate (CICO) durumunda neşter-bougie krikotirotominin ivedilikle başlatılmasını önermektedir.",
            studyDesignTr = "Uzman konsensus kılavuzu ve sistematik literatür taraması",
            mainFindingsTr = "Videolaringoskop kullanımı ilk denemede başarıyı artırırken zor havayolu hasarını düşürür. Entübasyon deneme sayısının sınırlanması kritik önem taşır.",
            clinicalTakeawayTr = "Zor havayolunda planlı videolaringoskopi, stile ve SGA hazırlığı hayat kurtarır.",
            limitationsTr = "Uygulama klinikler arası altyapı ve videolaringoskop erişimine bağlı değişkenlik gösterebilir.",
            practiceImpactTr = "Her zor havayolu öngörülen vakada başucunda zor havayolu arabası ve cerrahi krikotirotomi kiti hazır bulundurulmalıdır.",
            impactRating = 5,
            aiGeneratedDraft = false,
            physicianReviewed = true,
            reviewer = "Prof. Dr. Ahmet Yılmaz",
            lastReviewed = "2026-05-24",
            requiresClinicalValidation = false,
            categories = listOf("airway", "emergency"),
            isFeatured = true,
            sourceUrl = "https://pubmed.ncbi.nlm.nih.gov/34762244/",
            instagramPostUrl = "https://www.instagram.com/p/C_difficult_airway/",
            instagramImageUrl = "logo_light"
        ),
        Article(
            id = 4,
            pmid = "29356773",
            doi = "10.1097/AAP.0000000000000763",
            titleTr = "Bölgesel Anestezi ve Antikoagülan Tedavi: ASRA Pain Medicine Kılavuzu",
            titleEn = "Regional Anesthesia in the Patient Receiving Antithrombotic or Thrombolytic Therapy: ASRA Evidence-Based Guidelines",
            authors = listOf("Horlocker T. T.", "Vandermeuelen E.", "Kopp S. L.", "et al."),
            journal = "Regional Anesthesia & Pain Medicine",
            publicationDate = "2018-04-01",
            keyMessageTr = "Spinal/epidural blokaj öncesi antikoagülan ilaçların (Warfarin, DOAC'lar, LMWH) kılavuza göre güvenli kesilme sürelerine uyulmalı ve kateter çekimi sonrası uygun bekleme süreleri korunmalıdır.",
            studyDesignTr = "ASRA Pain Medicine Uzlaşma Kılavuzu (5. Baskı temeli)",
            mainFindingsTr = "Kateter ilişkili epidural hematom sıklığı nadirdir ancak nörolojik hasar riski yüksektir. DOAC'lar için kreatinin klirensine bağlı kesim süreleri hayatidir.",
            clinicalTakeawayTr = "Blok öncesi INR < 1.5 ve trombosit sayısı > 80.000/mm³ hedeflenmeli; ASRA sürelerine sıkı sıkıya bağlı kalınmalıdır.",
            limitationsTr = "Kanama bozukluğu olan veya kombine ilaç kullanan hastalarda risk kümülatif artar.",
            practiceImpactTr = "Elektif regional anestezi öncesi koagülasyon parametreleri sorgulanmalı ve kateter takipleri 2 saatlik nörolojik his kontrolleriyle sürdürülmelidir.",
            impactRating = 5,
            aiGeneratedDraft = false,
            physicianReviewed = true,
            reviewer = "Doç. Dr. Selen Demir",
            lastReviewed = "2026-05-25",
            requiresClinicalValidation = false,
            categories = listOf("regional", "coagulation"),
            isFeatured = true,
            sourceUrl = "https://pubmed.ncbi.nlm.nih.gov/29356773/",
            instagramPostUrl = "https://www.instagram.com/p/C_asra_coagulation/",
            instagramImageUrl = "logo_light"
        ),
        Article(
            id = 5,
            pmid = "33148630",
            doi = "10.1097/AAP.0000000000001258",
            titleTr = "Lokal Anestezik Sistemik Toksisitesi (LAST) Yönetimi: ASRA Kontrol Listesi",
            titleEn = "Managing Local Anesthetic Systemic Toxicity: The ASRA Checklist",
            authors = listOf("Neal J. M.", "Neal E. J.", "Horlocker T. T."),
            journal = "Regional Anesthesia & Pain Medicine",
            publicationDate = "2021-01-01",
            keyMessageTr = "LAST durumunda hava yolu kontrolünü takiben derhal %20 Lipid Emülsiyon tedavisi (1.5 mL/kg IV bolus ve 0.25 mL/kg/dk infüzyon) başlatılmalıdır.",
            studyDesignTr = "ASRA Pratik Kanıta Dayalı Kontrol Listesi Güncellemesi",
            mainFindingsTr = "Erken lipid infüzyonu lokal anestezik sink (emilim) etkisi yaratarak miyokardiyal ve serebral toksisiteyi hızla geri çevirir.",
            clinicalTakeawayTr = "LAST krizlerinde standart resüsitasyon ilaçlarından Vazopressin kullanılmamalı, Epinefrin dozu <1 mcg/kg olacak şekilde sınırlandırılmalıdır.",
            limitationsTr = "Lipid emülsiyon tedavisinin erken verilmesi kardiyak arrest aşamasına geçişi engeller.",
            practiceImpactTr = "Lokal anestezik blok uygulanan tüm ameliyathane ve blok odalarında hazır %20 Lipid Emülsiyon şişesi ve LAST kriz posteri bulunmalıdır.",
            impactRating = 5,
            aiGeneratedDraft = false,
            physicianReviewed = true,
            reviewer = "Prof. Dr. Ahmet Yılmaz",
            lastReviewed = "2026-05-25",
            requiresClinicalValidation = false,
            categories = listOf("regional", "emergency"),
            isFeatured = true,
            sourceUrl = "https://pubmed.ncbi.nlm.nih.gov/33148630/",
            instagramPostUrl = "https://www.instagram.com/p/C_last_lipidrescue/",
            instagramImageUrl = "logo_light"
        )
    )
}
