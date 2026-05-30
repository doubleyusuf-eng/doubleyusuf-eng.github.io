import json
import re

# Raw Turkish Spot Texts
tr_raw = """
**daily_pearl_101**
Propofol hipotansiyonu genellikle vazodilatasyon, venöz dönüş azalması ve miyokard depresyonu kombinasyonuyla oluşturur.

**daily_pearl_102**
Yaşlı, hipovolemik veya kardiyak rezervi düşük hastada propofol dozu yavaş ve filtre edilerek verilmelidir.

**daily_pearl_103**
Etomidat hemodinamik stabilite sağlar ancak adrenal supresyon riski nedeniyle kritik hastada dikkatle değerlendirilmelidir.

**daily_pearl_104**
Ketamin tek başına “her zaman tansiyonu artırır” şeklinde düşünülmemelidir; katekolamin depoları tükenmiş hastada hipotansiyon yapabilir.

**daily_pearl_105**
Ketamin bronkodilatör etkisi nedeniyle astım veya bronkospazm eğilimli hastada avantaj sağlayabilir.

**daily_pearl_106**
Midazolam yaşlı ve frail hastalarda uzamış sedasyon ve deliryum riskini artırabilir.

**daily_pearl_107**
Benzodiazepinler anksiyoliz ve amnezi sağlar ancak solunum depresyonu opioidlerle belirgin artar.

**daily_pearl_108**
Opioid verirken hedef yalnızca analjezi değil, solunum depresyonu ve hemodinamik etkileri dengelemektir.

**daily_pearl_109**
Remifentanil hızlı filtre edilebilir ancak infüzyon kesildikten sonra analjezik etkisi hızla kaybolur.

**daily_pearl_110**
Remifentanil sonrası postoperatif analjezi önceden planlanmazsa erken şiddetli ağrı gelişebilir.

**daily_pearl_111**
Fentanil lipofilik olduğu için hızlı başlangıçlıdır ancak tekrarlayan dozlarda birikim görülebilir.

**daily_pearl_112**
Morfin histamin salınımı, kaşıntı, bulantı ve daha uzun etki süresi nedeniyle dikkatli filtre edilmelidir.

**daily_pearl_113**
Nalokson opioid etkisini geri çevirir ama ağrı, sempatik aktivasyon ve tekrar solunum depresyonu riski yaratabilir.

**daily_pearl_114**
Naloksonun etki süresi bazı opioidlerden kısa olduğu için tekrar doz veya infüzyon gerekebilir.

**daily_pearl_115**
Süksinilkolin hızlı entübasyon için etkilidir ancak hiperkalemi, malign hipertermi ve bradikardi riskleri unutulmamalıdır.

**daily_pearl_116**
Yanık, denervasyon, immobilizasyon ve nöromüsküler hastalıkta süksinilkolin hiperkalemi açısından tehlikeli olabilir.

**daily_pearl_117**
Roküronyum yüksek dozda hızlı entübasyon koşulları sağlayabilir ancak etki süresi uzayabilir.

**daily_pearl_118**
Sugammadeks rokuronyum ve vekuronyumu geri döndürür; benzil izokinolinyum ajanlara etkili değildir.

**daily_pearl_119**
Sugammadeks sonrası klinik hareket yeterli gibi görünse bile kantitatif TOF değerlendirmesi güvenliği artırır.

**daily_pearl_120**
Neostigmin derin blokta etkili değildir; geri dönüş için yeterli spontan toparlanma olmalıdır.

**daily_pearl_121**
Neostigmin muskarinik yan etkiler nedeniyle antikolinerjik ajanla birlikte verilmelidir.

**daily_pearl_122**
Hipotermi, aminoglikozidler ve magnezyum nöromüsküler blok süresini uzatabilir.

**daily_pearl_123**
Rezidüel nöromüsküler blok postoperatif hipoksemi, aspirasyon ve havayolu obstrüksiyonu riskini artırır.

**daily_pearl_124**
Vazopressör seçimi hastanın kalp hızı, ritmi, kontraktilitesi ve vazodilatasyon derecesine göre yapılmalıdır.

**daily_pearl_125**
Fenilefrin saf vazokonstriksiyon sağlar ancak kardiyak debiyi düşürebilir.

**daily_pearl_126**
Efedrin taşikardi eğilimi olan hastada dikkatli kullanılmalıdır.

**daily_pearl_127**
Noradrenalin vazoplejik şokta filtre edilebilir ve güçlü bir ilk seçenek olabilir.

**daily_pearl_128**
Adrenalin anafilaksi ve kardiyak arrestte hayat kurtarıcıdır; gecikme mortaliteyi artırabilir.

**daily_pearl_129**
Dobutamin düşük kardiyak debi ve bozulmuş kontraktilitede vazopressörlerden daha uygun olabilir.

**daily_pearl_130**
Lokal anestezik dozu hesaplanırken hastanın kilosu, blok tipi, eklenen adrenalin ve toplam uygulanan doz birlikte düşünülmelidir.

**daily_pearl_131**
Lokal anestezik enjeksiyonunda aspirasyon ve fraksiyone uygulama LAST riskini azaltır.

**daily_pearl_132**
Ultrason rehberliği damar ponksiyonu riskini azaltabilir ancak sistemik toksisite riskini tamamen ortadan kaldırmaz.

**daily_pearl_133**
Bupivakain kardiyotoksisite açısından özellikle dikkat gerektirir.

**daily_pearl_134**
Ropivakain bupivakaine göre daha az kardiyotoksik kabul edilir ancak toksisite yine mümkündür.

**daily_pearl_135**
LAST erken dönemde tinnitus, metalik tat, ağız çevresi uyuşma veya ajitasyonla başlayabilir.

**daily_pearl_136**
LAST tedavisinde lokal anestezik uygulamasını durdurmak, yardım çağırmak, havayolu-dolaşımı desteklemek ve lipid emülsiyon vermek esastır.

**daily_pearl_137**
LAST sırasında aritmilerde standart kardiyak arrest algoritması modifiye edilmeli ve yüksek doz lokal anestetik/propofol dikkatle kullanılmalıdır.

**daily_pearl_138**
İnhalasyon ajanlarında düşük kan/gaz çözünürlüğü hızlı indüksiyon ve hızlı derlenme sağlar.

**daily_pearl_139**
Desfluran hızlı derlenme sağlar ancak havayolu irritasyonu ve sempatik uyarı yapabilir.

**daily_pearl_140**
Sevofluran düşük havayolu irritasyonu nedeniyle inhalasyon indüksiyonunda avantajlıdır.

**daily_pearl_141**
Azot protoksit kapalı hava boşluklarının hacmini veya basıncını artırabilir.

**daily_pearl_142**
Azot protoksit pnömotoraks, barsak obstrüksiyonu ve intrakraniyal hava gibi durumlarda dikkatle değerlendirilmelidir.

**daily_pearl_143**
Deksametazon PONV profilaksisinde yararlıdır ancak diyabetik hastada hiperglisemi riski düşünülmelidir.

**daily_pearl_144**
Ondansetron QT uzaması riski olan hastalarda dikkatli kullanılmalıdır.

**daily_pearl_145**
NSAİİ kullanımı böbrek fonksiyonu, kanama riski ve gastrointestinal yan etkiler açısından değerlendirilmelidir.

**daily_pearl_146**
Parasetamol opioid gereksinimini azaltabilir ancak karaciğer hastalığında toplam doz dikkatle hesaplanmalıdır.

**daily_pearl_147**
Antibiyotik profilaksisi doğru ilaç, doğru doz ve insizyondan önce uygun zamanlama ile verilmelidir.

**daily_pearl_148**
Yüksek riskli ilaçlarda konsantrasyon ve doz karışıklığı ciddi hasta güvenliği sorunudur.

**daily_pearl_149**
Benzer isimli veya benzer ambalajlı ilaçlar ameliyathanede ilaç hatasına yol açabilir.

**daily_pearl_150**
Her ilaç uygulamasında hasta, ilaç, doz, yol ve zaman doğrulaması güvenliğin temelidir.
"""

# Raw English Spot Texts
en_raw = """
**daily_pearl_101**
Propofol usually causes hypotension through a combination of vasodilation, reduced venous return, and myocardial depression.

**daily_pearl_102**
In elderly, hypovolemic, or low-cardiac-reserve patients, propofol should be given slowly and titrated.

**daily_pearl_103**
Etomidate provides hemodynamic stability but should be carefully considered in critically ill patients because of adrenal suppression risk.

**daily_pearl_104**
Ketamine should not be assumed to “always increase blood pressure”; it may cause hypotension in catecholamine-depleted patients.

**daily_pearl_105**
Ketamine may be advantageous in asthma or bronchospasm-prone patients because of its bronchodilatory effect.

**daily_pearl_106**
Midazolam may increase the risk of prolonged sedation and delirium in elderly and frail patients.

**daily_pearl_107**
Benzodiazepines provide anxiolysis and amnesia, but respiratory depression increases markedly when combined with opioids.

**daily_pearl_108**
When giving opioids, the goal is not only analgesia but balancing respiratory depression and hemodynamic effects.

**daily_pearl_109**
Remifentanil is rapidly titratable, but its analgesic effect disappears quickly after stopping the infusion.

**daily_pearl_110**
If postoperative analgesia is not planned before stopping remifentanil, early severe pain may occur.

**daily_pearl_111**
Fentanyl is lipophilic and fast acting, but accumulation may occur with repeated dosing.

**daily_pearl_112**
Morphine should be titrated carefully because of histamine release, pruritus, nausea, and longer duration.

**daily_pearl_113**
Naloxone reverses opioid effects but may cause pain, sympathetic activation, and recurrent respiratory depression.

**daily_pearl_114**
Naloxone duration may be shorter than that of some opioids, so repeat dosing or infusion may be required.

**daily_pearl_115**
Succinylcholine is effective for rapid intubation, but hyperkalemia, malignant hyperthermia, and bradycardia risks must be remembered.

**daily_pearl_116**
In burns, denervation, immobilization, and neuromuscular disease, succinylcholine may be dangerous because of hyperkalemia.

**daily_pearl_117**
High-dose rocuronium may provide rapid intubating conditions, but duration may be prolonged.

**daily_pearl_118**
Sugammadex reverses rocuronium and vecuronium; it is not effective for benzylisoquinolinium agents.

**daily_pearl_119**
Even if clinical movement appears adequate after sugammadex, quantitative TOF assessment improves safety.

**daily_pearl_120**
Neostigmine is ineffective in deep block; adequate spontaneous recovery must be present.

**daily_pearl_121**
Neostigmine should be given with an anticholinergic agent because of muscarinic adverse effects.

**daily_pearl_122**
Hypothermia, aminoglycosides, and magnesium may prolong neuromuscular blockade.

**daily_pearl_123**
Residual neuromuscular block increases the risk of postoperative hypoxemia, aspiration, and airway obstruction.

**daily_pearl_124**
Vasopressor choice should be based on heart rate, rhythm, contractility, and degree of vasodilation.

**daily_pearl_125**
Phenylephrine provides pure vasoconstriction but may reduce cardiac output.

**daily_pearl_126**
Ephedrine should be used cautiously in patients prone to tachycardia.

**daily_pearl_127**
Norepinephrine is a titratable and powerful first option in vasoplegic shock.

**daily_pearl_128**
Epinephrine is life-saving in anaphylaxis and cardiac arrest; delay may increase mortality.

**daily_pearl_129**
Dobutamine may be more appropriate than vasopressors in low cardiac output and impaired contractility.

**daily_pearl_130**
When calculating local anesthetic dose, consider weight, block type, added epinephrine, and total administered dose.

**daily_pearl_131**
Aspiration and incremental injection reduce the risk of LAST during local anesthetic administration.

**daily_pearl_132**
Ultrasound guidance may reduce vascular puncture but does not eliminate systemic toxicity risk.

**daily_pearl_133**
Bupivacaine requires particular caution because of cardiotoxicity.

**daily_pearl_134**
Ropivacaine is considered less cardiotoxic than bupivacaine, but toxicity is still possible.

**daily_pearl_135**
LAST may begin with tinnitus, metallic taste, circumoral numbness, or agitation.

**daily_pearl_136**
LAST treatment is based on stopping local anesthetic administration, calling for help, supporting airway and circulation, and giving lipid emulsion.

**daily_pearl_137**
During LAST-related arrhythmias, standard cardiac arrest algorithms should be modified, and high-dose local anesthetic or propofol should be used cautiously.

**daily_pearl_138**
Low blood/gas solubility of inhaled agents provides faster induction and recovery.

**daily_pearl_139**
Desflurane provides rapid recovery but may cause airway irritation and sympathetic stimulation.

**daily_pearl_140**
Sevoflurane is advantageous for inhalational induction because of low airway irritation.

**daily_pearl_141**
Nitrous oxide can increase the volume or pressure of closed air spaces.

**daily_pearl_142**
Nitrous oxide should be carefully considered in pneumothorax, bowel obstruction, and intracranial air.

**daily_pearl_143**
Dexamethasone is useful for PONV prophylaxis, but hyperglycemia risk should be considered in diabetic patients.

**daily_pearl_144**
Ondansetron should be used cautiously in patients at risk of QT prolongation.

**daily_pearl_145**
NSAID use should be assessed for renal function, bleeding risk, and gastrointestinal adverse effects.

**daily_pearl_146**
Paracetamol may reduce opioid requirement, but total dose should be carefully calculated in liver disease.

**daily_pearl_147**
Antibiotic prophylaxis should use the correct drug, correct dose, and appropriate timing before incision.

**daily_pearl_148**
Concentration and dose confusion with high-risk drugs is a major patient safety issue.

**daily_pearl_149**
Look-alike or sound-alike medications may cause medication errors in the operating room.

**daily_pearl_150**
For every drug administration, confirming patient, drug, dose, route, and time is fundamental to safety.
"""

def parse_spots(text):
    spots = {}
    pattern = r"\*\*daily_pearl_(\d+)\*\*\s*\n([^\n]+(?:\n[^\n]+)*)"
    matches = re.findall(pattern, text)
    for m in matches:
        num = m[0]
        val = m[1].strip()
        spots[f"daily_pearl_{num}"] = val
    return spots

tr_spots = parse_spots(tr_raw)
en_spots = parse_spots(en_raw)

# Map subcategory, tags and clinicalUse for the 50 pearls (101-150)
def get_metadata(num):
    n = int(num)
    if n <= 107:
        return "Induction Agents", "Anesthetic induction", ["propofol", "etomidate", "ketamine", "midazolam", "elderly"]
    elif n <= 114:
        return "Opioids", "Opioid safety", ["opioid", "remifentanil", "fentanyl", "morphine", "naloxone"]
    elif n <= 117:
        return "Neuromuscular Blockers", "Relaxant dosing", ["succinylcholine", "rocuronium", "hyperkalemia", "induction"]
    elif n <= 123:
        return "Neuromuscular Reversals", "Reversal safety", ["sugammadex", "neostigmine", "tof", "residual_block"]
    elif n <= 129:
        return "Vasoactive Dosing", "Vasoactive drug selection", ["norepinephrine", "epinephrine", "phenylephrine", "dobutamine", "anaphylaxis"]
    elif n <= 137:
        return "Local Anesthetic Toxicity (LAST)", "Toxicity management", ["last", "lipidrescue", "bupivacaine", "ropivacaine", "seizure"]
    elif n <= 142:
        return "Inhalational Agents", "Inhalation selection", ["sevoflurane", "desflurane", "nitrous_oxide", "solubility"]
    elif n <= 147:
        return "Adjuvant Pharmacology", "Adjuvant therapy", ["dexamethasone", "ondansetron", "nsaid", "paracetamol", "antibiotic"]
    else:
        return "Medication Safety", "Drug safety", ["medication_error", "verification", "safety", "look_alike"]

# High Yield Numbers for Batch 3 (101-150)
def get_importance(num):
    n = int(num)
    high_yield_nums = {101, 102, 103, 104, 109, 110, 113, 115, 116, 118, 119, 120, 123, 127, 128, 130, 131, 133, 135, 136, 138, 140, 141, 144, 148, 149, 150}
    return "high" if n in high_yield_nums else "medium"

pearls_list = []
for i in range(101, 151):
    num_str = f"{i:03d}"
    pearl_id = f"daily_pearl_{num_str}"
    
    sub, use, tags = get_metadata(i)
    imp = get_importance(i)
    
    pearls_list.append({
        "id": pearl_id,
        "category": "DRUGS_PHARMACOLOGY",
        "subcategory": sub,
        "tr": tr_spots[pearl_id],
        "en": en_spots[pearl_id],
        "clinicalUse": use,
        "importance": imp,
        "tags": tags,
        "sourceType": "clinical_review",
        "lastReviewed": "2026-05-30"
    })

# Load previous pearls from current assets
current_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/assets/daily_clinical_pearls.json"
with open(current_path, "r", encoding="utf-8") as f:
    current_pearls = json.load(f)

# Keep first 100 items (AIRWAY_RESPIRATORY and HEMODYNAMIC_CARDIOVASCULAR)
batch1_2_pearls = [p for p in current_pearls if p["id"].startswith("daily_pearl_0") or (p["id"].startswith("daily_pearl_100")) or (int(p["id"].split("_")[-1]) <= 100)]

# Add remaining shifted dummy items for other categories (daily_pearl_151 to daily_pearl_159)
shifted_dummies = [
  {
    "id": "daily_pearl_151",
    "category": "REGIONAL_PAIN_PONV",
    "subcategory": "LAST Seizure",
    "tr": "Lokal anestezik sistemik toksisitesi (LAST) nöbetlerinde propofol yerine hemodinamiyi koruyan benzodiazepini tercih edin.",
    "en": "In local anesthetic systemic toxicity (LAST) seizures, prefer benzodiazepines over propofol to maintain hemodynamic stability.",
    "clinicalUse": "Toxicity management",
    "importance": "high",
    "tags": ["last", "toxicity", "seizure", "midazolam"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_152",
    "category": "REGIONAL_PAIN_PONV",
    "subcategory": "Spinal Hypotension",
    "tr": "Spinal anestezi sonrası hipotansiyonda efedrin yerine refleks taşikardi yapmayan fenilefrin veya noradrenalin tercih edilebilir.",
    "en": "In post-spinal anesthesia hypotension, phenylephrine or norepinephrine may be preferred over ephedrine to avoid reflex tachycardia.",
    "clinicalUse": "Spinal rescue",
    "importance": "medium",
    "tags": ["spinal", "hypotension", "phenylephrine"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_153",
    "category": "REGIONAL_PAIN_PONV",
    "subcategory": "PONV Risk",
    "tr": "PONV riski yüksek hastalarda uçucu anesteziklerden kaçınmak (TIVA) ve multimodal profilaksi uygulamak altın standarttır.",
    "en": "In patients with high PONV risk, avoiding volatile anesthetics (TIVA) and using multimodal prophylaxis is the gold standard.",
    "clinicalUse": "PONV prevention",
    "importance": "high",
    "tags": ["ponv", "tiva", "prophylaxis"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_154",
    "category": "OBSTETRIC_PEDIATRIC_SPECIAL",
    "subcategory": "Aortocaval Compression",
    "tr": "Gebe hastalarda supin pozisyonda aortokaval basıyı önlemek için mutlaka sol uterin deplasman uygulanmalıdır.",
    "en": "Left uterine displacement must be applied in pregnant patients to prevent aortocaval compression in the supine position.",
    "clinicalUse": "Obstetric positioning",
    "importance": "high",
    "tags": ["pregnancy", "aortocaval", "positioning"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_155",
    "category": "OBSTETRIC_PEDIATRIC_SPECIAL",
    "subcategory": "Pediatric Laryngospasm",
    "tr": "Pediatrik laringospazmda ilk adım pozitif basınçlı oksijenasyon ve Larson manevrasıdır (çene arkası basınç).",
    "en": "In pediatric laryngospasm, the first step is positive pressure oxygenation and the Larson maneuver (jaw-thrust pressure).",
    "clinicalUse": "Pediatric emergency",
    "importance": "high",
    "tags": ["laryngospasm", "pediatric", "larson"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_156",
    "category": "OBSTETRIC_PEDIATRIC_SPECIAL",
    "subcategory": "Geriatric Dosing",
    "tr": "Geriatrik hastalarda anestezik ilaç gereksinimi belirgin şekilde azalır; indüksiyon dozları %30-50 düşürülmelidir.",
    "en": "Anesthetic drug requirements are significantly reduced in geriatric patients; induction doses should be decreased by 30-50%.",
    "clinicalUse": "Geriatric titration",
    "importance": "medium",
    "tags": ["geriatric", "dosing", "pharmacokinetics"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_157",
    "category": "ICU_CRISIS_SAFETY_POSTOP",
    "subcategory": "CICO Emergency",
    "tr": "Can't Intubate Can't Oxygenate (CICO) durumunda zaman kaybetmeden cerrahi veya perkütan acil havayoluna geçilmelidir.",
    "en": "In a Can't Intubate Can't Oxygenate (CICO) scenario, proceed immediately to surgical or percutaneous emergency airway without delay.",
    "clinicalUse": "Airway crisis",
    "importance": "high",
    "tags": ["cico", "emergency", "airway", "front_of_neck"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_158",
    "category": "ICU_CRISIS_SAFETY_POSTOP",
    "subcategory": "Malignant Hyperthermia",
    "tr": "Malign hipertermi şüphesinde tetikleyici ajanlar hemen kapatılmalı, devre değiştirilmeli ve Dantrolen başlanmalıdır.",
    "en": "In suspected malignant hyperthermia, trigger agents must be stopped immediately, circuit changed, and Dantrolene initiated.",
    "clinicalUse": "Crisis management",
    "importance": "high",
    "tags": ["malignant_hyperthermia", "dantrolene", "crisis"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_159",
    "category": "ICU_CRISIS_SAFETY_POSTOP",
    "subcategory": "Time Out",
    "tr": "Time-out (güvenli cerrahi checklist) sadece bir prosedür değil, yanlış taraf cerrahisini önleyen kritik bir güvenlik bariyeridir.",
    "en": "Time-out (safe surgery checklist) is not just a procedure; it is a critical safety barrier that prevents wrong-site surgery.",
    "clinicalUse": "Patient safety",
    "importance": "medium",
    "tags": ["timeout", "safety", "checklist"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  }
]

# Total combined list
combined_all = batch1_2_pearls + pearls_list + shifted_dummies

# Write JSON
with open(current_path, "w", encoding="utf-8") as out:
    json.dump(combined_all, out, indent=2, ensure_ascii=False)

print(f"SUCCESS: Integrated Batch 3! Wrote {len(combined_all)} clinical pearls to {current_path} successfully!")
