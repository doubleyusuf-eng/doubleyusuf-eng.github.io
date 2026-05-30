import json
import re

# Raw Turkish Spot Texts
tr_raw = """
**daily_pearl_151**
Rejyonal anestezide başarı kadar komplikasyonları erken fark etmek de güvenliğin parçasıdır.

**daily_pearl_152**
Blok öncesi antikoagülan kullanımı ilaç tipi, son doz zamanı, renal fonksiyon ve işlem riskiyle birlikte değerlendirilmelidir.

**daily_pearl_153**
Nöroaksiyel blok öncesi trombosit sayısı kadar kanama öyküsü ve trombosit trendi de önemlidir.

**daily_pearl_154**
Spinal anestezi hızlı, yoğun ve öngörülebilir blok sağlar ancak hipotansiyon riski belirgindir.

**daily_pearl_155**
Epidural anestezide titrasyon avantajı vardır ancak başlangıç spinal anesteziye göre daha yavaştır.

**daily_pearl_156**
Spinal hipotansiyon sempatik blok, venöz dönüş azalması ve göreceli hipovolemiyle ilişkilidir.

**daily_pearl_157**
Yüksek spinal blokta hipotansiyon, bradikardi, solunum güçlüğü ve bilinç değişikliği gelişebilir.

**daily_pearl_158**
Total spinal anestezide hızlı havayolu, ventilasyon ve dolaşım desteği sağlanmalıdır.

**daily_pearl_159**
Nöroaksiyel blok sonrası yeni motor defisit epidural hematom açısından acil değerlendirme gerektirir.

**daily_pearl_160**
Epidural hematomda nörolojik sonuç için erken tanı ve cerrahi dekompresyon kritik önemdedir.

**daily_pearl_161**
Postdural ponksiyon baş ağrısı tipik olarak ortostatik karakterlidir.

**daily_pearl_162**
Dirençli postdural ponksiyon baş ağrısında epidural kan yaması etkili bir tedavi seçeneğidir.

**daily_pearl_163**
Epidural test dozu intravasküler veya intratekal yerleşimi fark etmeye yardımcı olabilir ancak kusursuz değildir.

**daily_pearl_164**
Epidural kateterde aspirasyon negatif olsa bile intravasküler yerleşim tamamen dışlanamaz.

**daily_pearl_165**
Periferik sinir bloğunda ultrason rehberliği başarıyı artırabilir ancak sinir hasarı riskini tamamen ortadan kaldırmaz.

**daily_pearl_166**
Blok sırasında şiddetli ağrı veya yüksek enjeksiyon basıncı intranöral enjeksiyon uyarısı olabilir.

**daily_pearl_167**
İntranöral enjeksiyon şüphesinde enjeksiyon durdurulmalı ve iğne pozisyonu yeniden değerlendirilmelidir.

**daily_pearl_168**
Lokal anestezik toplam dozu tüm yapılan bloklar ve infiltrasyonlar birlikte düşünülerek hesaplanmalıdır.

**daily_pearl_169**
Adrenalin eklenmesi sistemik emilimi azaltabilir ancak uç arter bölgelerinde dikkatle değerlendirilmelidir.

**daily_pearl_170**
LAST erken bulguları tinnitus, metalik tat, ağız çevresi uyuşma, ajitasyon veya konfüzyon olabilir.

**daily_pearl_171**
LAST şüphesinde lokal anestezik uygulaması durdurulmalı, yardım çağrılmalı ve lipid emülsiyon hazırlanmalıdır.

**daily_pearl_172**
LAST tedavisinde havayolu kontrolü ve nöbetlerin hızlı yönetimi hipoksi ve asidozu önlemek için kritiktir.

**daily_pearl_173**
LAST sırasında kardiyak arrest gelişirse uzun süreli resüsitasyon gerekebilir.

**daily_pearl_174**
Bupivakain kardiyotoksisite açısından en dikkat edilmesi gereken lokal anesteziklerden biridir.

**daily_pearl_175**
Ropivakain daha güvenli kabul edilse de yüksek doz veya intravasküler enjeksiyonda toksisite oluşturabilir.

**daily_pearl_176**
Multimodal analjezi farklı mekanizmaları hedefleyerek opioid gereksinimini azaltır.

**daily_pearl_177**
Akut ağrı tedavisi yalnızca konfor değil, mobilizasyon ve solunum fonksiyonu için de önemlidir.

**daily_pearl_178**
Yetersiz analjezi taşikardi, hipertansiyon, hipoventilasyon ve deliryuma katkı sağlayabilir.

**daily_pearl_179**
Opioid tedavisinde sedasyon düzeyi solunum depresyonunun erken uyarısı olabilir.

**daily_pearl_180**
OSA, yaşlılık, benzodiazepin kullanımı ve renal yetmezlik opioid kaynaklı solunum depresyonu riskini artırır.

**daily_pearl_181**
PCA kullanımında hasta eğitimi, kilit süresi ve solunum takibi güvenliğin temelidir.

**daily_pearl_182**
PCA cihazını hasta dışında birinin kullanması ciddi aşırı doz riskine yol açabilir.

**daily_pearl_183**
Kronik opioid kullanan hastada tolerans nedeniyle postoperatif analjezi ihtiyacı artabilir.

**daily_pearl_184**
Opioid toleransı olan hastada ani opioid kesilmesi yoksunluk ve ciddi ağrıya neden olabilir.

**daily_pearl_185**
NSAİİ kullanımı böbrek fonksiyonu, kanama riski, mide koruması ve cerrahi tipe göre değerlendirilmelidir.

**daily_pearl_186**
Parasetamol opioid ihtiyacını azaltabilir ancak toplam günlük doz ve karaciğer fonksiyonu dikkate alınmalıdır.

**daily_pearl_187**
Gabapentinoidler sedasyon ve solunum depresyonu riskini artırabileceği için özellikle yaşlılarda dikkatli kullanılmalıdır.

**daily_pearl_188**
Ketamin düşük dozda opioid azaltıcı analjezik etki sağlayabilir.

**daily_pearl_189**
Lidokain infüzyonu bazı cerrahilerde opioid ihtiyacını azaltabilir ancak toksisite ve kontrendikasyonlar değerlendirilmelidir.

**daily_pearl_190**
PONV riskini değerlendirirken kadın cinsiyet, sigara içmeme, PONV/taşıt tutması öyküsü ve opioid kullanımı sorgulanmalıdır.

**daily_pearl_191**
Yüksek PONV riskinde farklı mekanizmalardan etki eden antiemetikler birlikte kullanılmalıdır.

**daily_pearl_192**
TIVA ve opioid azaltıcı stratejiler PONV riskini azaltabilir.

**daily_pearl_193**
Deksametazon PONV profilaksisinde etkilidir ancak diyabetik hastada hiperglisemi riski düşünülmelidir.

**daily_pearl_194**
Ondansetron QT uzaması riski olan hastalarda dikkatle kullanılmalıdır.

**daily_pearl_195**
PONV tedavisinde profilakside kullanılan aynı sınıftan ilacı kısa sürede tekrarlamak yerine farklı sınıf tercih edilebilir.

**daily_pearl_196**
PONV aspirasyon, yara ayrılması, dehidratasyon ve taburculuk gecikmesine neden olabilir.

**daily_pearl_197**
Rejyonal teknikler iyi seçildiğinde hem analjeziyi iyileştirir hem de opioid yan etkilerini azaltır.

**daily_pearl_198**
Blok sonrası taburculukta hasta düşme, uyuşuk ekstremite ve geç başlayan ağrı konusunda bilgilendirilmelidir.

**daily_pearl_199**
Tek doz periferik blok sonrası rebound ağrı için düzenli analjezi planı önceden yapılmalıdır.

**daily_pearl_200**
İyi ağrı yönetimi; etkili analjezi, düşük yan etki ve güvenli takip dengesidir.
"""

# Raw English Spot Texts
en_raw = """
**daily_pearl_151**
In regional anesthesia, early recognition of complications is as important as block success.

**daily_pearl_152**
Before regional anesthesia, anticoagulant use should be assessed with drug type, last dose timing, renal function, and procedural risk.

**daily_pearl_153**
Before neuraxial block, bleeding history and platelet trend are as important as the platelet count.

**daily_pearl_154**
Spinal anesthesia provides rapid, dense, and predictable block, but the risk of hypotension is significant.

**daily_pearl_155**
Epidural anesthesia offers titration, but its onset is slower than spinal anesthesia.

**daily_pearl_156**
Spinal hypotension is related to sympathetic block, reduced venous return, and relative hypovolemia.

**daily_pearl_157**
High spinal block may cause hypotension, bradycardia, respiratory difficulty, and altered consciousness.

**daily_pearl_158**
In total spinal anesthesia, rapid airway, ventilation, and circulatory support must be provided.

**daily_pearl_159**
New motor deficit after neuraxial block requires urgent evaluation for epidural hematoma.

**daily_pearl_160**
In epidural hematoma, early diagnosis and surgical decompression are critical for neurological outcome.

**daily_pearl_161**
Postdural puncture headache typically has an orthostatic character.

**daily_pearl_162**
Epidural blood patch is an effective treatment option for persistent postdural puncture headache.

**daily_pearl_163**
An epidural test dose may help detect intravascular or intrathecal placement, but it is not perfect.

**daily_pearl_164**
Negative aspiration from an epidural catheter does not completely exclude intravascular placement.

**daily_pearl_165**
Ultrasound guidance may improve peripheral nerve block success but does not eliminate nerve injury risk.

**daily_pearl_166**
Severe pain or high injection pressure during block may warn of intraneural injection.

**daily_pearl_167**
If intraneural injection is suspected, injection should be stopped and needle position reassessed.

**daily_pearl_168**
The total local anesthetic dose should be calculated considering all blocks and infiltrations together.

**daily_pearl_169**
Adding epinephrine may reduce systemic absorption, but it should be carefully considered in end-artery areas.

**daily_pearl_170**
Early signs of LAST may include tinnitus, metallic taste, circumoral numbness, agitation, or confusion.

**daily_pearl_171**
If LAST is suspected, stop local anesthetic administration, call for help, and prepare lipid emulsion.

**daily_pearl_172**
In LAST treatment, airway control and rapid seizure management are critical to prevent hypoxia and acidosis.

**daily_pearl_173**
If cardiac arrest occurs during LAST, prolonged resuscitation may be required.

**daily_pearl_174**
Bupivacaine is one of the local anesthetics requiring the most caution for cardiotoxicity.

**daily_pearl_175**
Although ropivacaine is considered safer, toxicity can still occur with high dose or intravascular injection.

**daily_pearl_176**
Multimodal analgesia reduces opioid requirements by targeting different mechanisms.

**daily_pearl_177**
Acute pain treatment is important not only for comfort but also for mobilization and respiratory function.

**daily_pearl_178**
Inadequate analgesia may contribute to tachycardia, hypertension, hypoventilation, and delirium.

**daily_pearl_179**
During opioid therapy, sedation level may be an early warning sign of respiratory depression.

**daily_pearl_180**
OSA, advanced age, benzodiazepine use, and renal failure increase the risk of opioid-induced respiratory depression.

**daily_pearl_181**
During PCA use, patient education, lockout interval, and respiratory monitoring are fundamental for safety.

**daily_pearl_182**
Use of a PCA device by anyone other than the patient may cause serious overdose risk.

**daily_pearl_183**
Patients on chronic opioids may require more postoperative analgesia because of tolerance.

**daily_pearl_184**
Abrupt opioid discontinuation in opioid-tolerant patients may cause withdrawal and severe pain.

**daily_pearl_185**
NSAID use should be assessed according to renal function, bleeding risk, gastric protection, and surgical type.

**daily_pearl_186**
Paracetamol may reduce opioid requirement, but total daily dose and liver function must be considered.

**daily_pearl_187**
Gabapentinoids may increase sedation and respiratory depression risk, especially in elderly patients.

**daily_pearl_188**
Low-dose ketamine may provide opioid-sparing analgesia.

**daily_pearl_189**
Lidocaine infusion may reduce opioid requirement in some surgeries, but toxicity and contraindications must be assessed.

**daily_pearl_190**
When assessing PONV risk, ask about female sex, nonsmoking status, history of PONV or motion sickness, and opioid use.

**daily_pearl_191**
In high PONV risk, antiemetics with different mechanisms should be combined.

**daily_pearl_192**
TIVA and opioid-sparing strategies may reduce PONV risk.

**daily_pearl_193**
Dexamethasone is effective for PONV prophylaxis, but hyperglycemia risk should be considered in diabetic patients.

**daily_pearl_194**
Ondansetron should be used carefully in patients at risk of QT prolongation.

**daily_pearl_195**
For PONV treatment, choosing a different drug class may be preferable to repeating the same prophylactic class too soon.

**daily_pearl_196**
PONV may cause aspiration, wound dehiscence, dehydration, and delayed discharge.

**daily_pearl_197**
When well selected, regional techniques improve analgesia and reduce opioid adverse effects.

**daily_pearl_198**
At discharge after a block, patients should be informed about falls, numb limbs, and delayed rebound pain.

**daily_pearl_199**
A regular analgesic plan should be made in advance for rebound pain after a single-shot peripheral block.

**daily_pearl_200**
Good pain management balances effective analgesia, low adverse effects, and safe follow-up.
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

# Map subcategory, tags and clinicalUse for the 50 pearls (151-200)
def get_metadata(num):
    n = int(num)
    if n <= 153:
        return "Regional Anesthesia Safety", "Coagulation check", ["asra", "coagulation", "regional", "anticoagulants"]
    elif n <= 158:
        return "Neuraxial Blocks", "Epidural/Spinal technique", ["spinal", "epidural", "hypotension", "total_spinal"]
    elif n <= 164:
        return "Neuraxial Complications", "Neuraxial safety", ["hematoma", "headache", "test_dose", "postdural_puncture"]
    elif n <= 169:
        return "Peripheral Nerve Blocks", "USG block technique", ["peripheral", "block", "ultrasound", "intraneural"]
    elif n <= 175:
        return "Local Anesthetic Toxicity (LAST)", "Toxicity management", ["last", "lipidrescue", "bupivacaine", "ropivacaine"]
    elif n <= 178:
        return "Multimodal Analgesia", "Pain therapy options", ["multimodal", "analgesia", "acute_pain"]
    elif n <= 184:
        return "Opioid Safety & PCA", "Patient-Controlled Analgesia", ["pca", "opioid", "respiratory_depression", "tolerance"]
    elif n <= 189:
        return "Non-opioid Analgesia", "Non-opioid therapy", ["nsaid", "paracetamol", "gabapentinoids", "ketamine", "lidocaine"]
    elif n <= 196:
        return "PONV Management", "PONV prevention", ["ponv", "prophylaxis", "ondansetron", "dexamethasone", "tiva"]
    else:
        return "Post-block Care", "Discharge instructions", ["rebound_pain", "discharge", "patient_safety"]

# High Yield Numbers for Batch 4 (151-200)
def get_importance(num):
    n = int(num)
    high_yield_nums = {151, 152, 153, 154, 157, 158, 159, 160, 162, 165, 166, 168, 170, 171, 172, 174, 176, 177, 179, 180, 181, 182, 185, 190, 191, 192, 193, 196, 199, 200}
    return "high" if n in high_yield_nums else "medium"

pearls_list = []
for i in range(151, 201):
    num_str = f"{i:03d}"
    pearl_id = f"daily_pearl_{num_str}"
    
    sub, use, tags = get_metadata(i)
    imp = get_importance(i)
    
    pearls_list.append({
        "id": pearl_id,
        "category": "REGIONAL_PAIN_PONV",
        "subcategory": sub,
        "tr": tr_spots[pearl_id],
        "en": en_spots[pearl_id],
        "clinicalUse": use,
        "importance": imp,
        "tags": tags,
        "sourceType": "clinical_review",
        "lastReviewed": "2026-05-30"
    })

# Load previous pearls from assets
current_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/assets/daily_clinical_pearls.json"
with open(current_path, "r", encoding="utf-8") as f:
    current_pearls = json.load(f)

# Keep first 150 items (AIRWAY, HEMO, DRUGS)
batch1_2_3_pearls = [p for p in current_pearls if (p["id"].startswith("daily_pearl_0") or p["id"].startswith("daily_pearl_10") or p["id"].startswith("daily_pearl_11") or p["id"].startswith("daily_pearl_12") or p["id"].startswith("daily_pearl_13") or p["id"].startswith("daily_pearl_14") or p["id"].startswith("daily_pearl_150")) or (int(p["id"].split("_")[-1]) <= 150)]

# Add remaining shifted dummy items for other categories (daily_pearl_201 to daily_pearl_206)
shifted_dummies = [
  {
    "id": "daily_pearl_201",
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
    "id": "daily_pearl_202",
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
    "id": "daily_pearl_203",
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
    "id": "daily_pearl_204",
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
    "id": "daily_pearl_205",
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
    "id": "daily_pearl_206",
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
combined_all = batch1_2_3_pearls + pearls_list + shifted_dummies

# Write JSON
with open(current_path, "w", encoding="utf-8") as out:
    json.dump(combined_all, out, indent=2, ensure_ascii=False)

print(f"SUCCESS: Integrated Batch 4! Wrote {len(combined_all)} clinical pearls to {current_path} successfully!")
