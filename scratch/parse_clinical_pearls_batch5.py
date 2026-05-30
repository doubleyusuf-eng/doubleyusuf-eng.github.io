import json
import re

# Raw Turkish Spot Texts
tr_raw = """
**daily_pearl_201**
Gebede FRC azalır ve oksijen tüketimi artar; bu nedenle desatürasyon beklenenden hızlı gelişebilir.

**daily_pearl_202**
Gebede preoksijenasyon indüksiyonun opsiyonel değil, kritik bir güvenlik basamağıdır.

**daily_pearl_203**
Sezaryende sol uterin yer değiştirme aortokaval basıyı azaltmaya yardımcı olur.

**daily_pearl_204**
Spinal anestezi sonrası obstetrik hipotansiyon uteroplasental perfüzyonu azaltabileceği için hızlı tedavi edilmelidir.

**daily_pearl_205**
Obstetrik spinal hipotansiyonda fenilefrin sık tercih edilir; bradikardi varsa ajan seçimi yeniden değerlendirilmelidir.

**daily_pearl_206**
Gebede zor havayolu riski ödem, kilo alımı ve hızlı desatürasyon nedeniyle daha ciddidir.

**daily_pearl_207**
Gebede aspirasyon riski yüksekse indüksiyon öncesi kurtarma havayolu planı net olmalıdır.

**daily_pearl_208**
Preeklampside havayolu ödemi ve abartılı laringoskopi yanıtı beklenmelidir.

**daily_pearl_209**
Preeklamptik hastada magnezyum sülfat nöromüsküler blok etkisini uzatabilir.

**daily_pearl_210**
HELLP sendromunda nöroaksiyel karar yalnızca tek trombosit değerine değil, trend ve klinik tabloya göre verilmelidir.

**daily_pearl_211**
Postpartum kanamada uterin atoni en sık nedenlerden biridir ve uterotonikler erken düşünülmelidir.

**daily_pearl_212**
Oksitosin hızlı bolus verildiğinde hipotansiyon ve taşikardi yapabilir.

**daily_pearl_213**
Plasenta akreta şüphesinde masif kanama hazırlığı ve multidisipliner planlama gerekir.

**daily_pearl_214**
Amniyotik sıvı embolisi ani hipoksemi, hipotansiyon ve koagülopati ile akla gelmelidir.

**daily_pearl_215**
Pediatrik hastalarda ilaç dozları kilogram başına hesaplanmalı ve maksimum dozlar kontrol edilmelidir.

**daily_pearl_216**
Çocuklarda düşük FRC ve yüksek oksijen tüketimi hızlı desatürasyona neden olur.

**daily_pearl_217**
Pediatrik bradikardi çoğu zaman hipoksinin ciddi bir bulgusudur.

**daily_pearl_218**
Çocukta laringospazm hızlı desatürasyon ve bradikardiye ilerleyebilir.

**daily_pearl_219**
Pediatrik havayolu girişiminden önce uygun boy maske, tüp, laringoskop ve ilaçlar hazır olmalıdır.

**daily_pearl_220**
Üst solunum yolu enfeksiyonu olan çocukta bronkospazm, laringospazm ve desatürasyon riski artar.

**daily_pearl_221**
Prematüre bebeklerde postoperatif apne riski nedeniyle izlem planı önceden yapılmalıdır.

**daily_pearl_222**
Neonatal resüsitasyonda kalp hızının düzelmesi etkili ventilasyonun en önemli göstergesidir.

**daily_pearl_223**
Çocuklarda hipotansiyon geç bulgu olabilir; perfüzyon, kapiller dolum ve kalp hızı erken uyarı sağlar.

**daily_pearl_224**
Pediatrik sıvı tedavisinde hipotonik solüsyonlar hiponatremi riskini artırabilir.

**daily_pearl_225**
Yaşlı hastalarda farmakodinamik duyarlılık artar; ilaçlar düşük dozla ve titre edilerek verilmelidir.

**daily_pearl_226**
Frailty, yaşlı hastada komplikasyon ve fonksiyon kaybı riskini yaşın kendisinden daha iyi gösterebilir.

**daily_pearl_227**
Yaşlı hastada deliryum riskini azaltmak için benzodiazepinlerden kaçınmak ve ağrıyı iyi kontrol etmek önemlidir.

**daily_pearl_228**
Yaşlı hastada hipotermi daha kolay gelişebilir ve derlenmeyi uzatabilir.

**daily_pearl_229**
Obez hastada ramped pozisyon preoksijenasyon, maske ventilasyonu ve laringoskopiyi kolaylaştırabilir.

**daily_pearl_230**
Obezite postoperatif hipoventilasyon, atelektazi ve OSA ile ilişkili riskleri artırır.

**daily_pearl_231**
OSA hastasında opioid azaltıcı analjezi ve postoperatif solunum monitörizasyonu planlanmalıdır.

**daily_pearl_232**
OSA hastasında sedatif premedikasyon dikkatle değerlendirilmelidir.

**daily_pearl_233**
Renal yetmezlikte potasyum, volüm durumu ve ilaç eliminasyonu preoperatif olarak kontrol edilmelidir.

**daily_pearl_234**
Diyaliz hastasında son diyaliz zamanı ve damar yolu erişimi anestezi planını etkiler.

**daily_pearl_235**
Üremi trombosit fonksiyon bozukluğu yaparak kanama riskini artırabilir.

**daily_pearl_236**
Karaciğer yetmezliğinde koagülopati, hipoglisemi ve ilaç metabolizmasında gecikme beklenmelidir.

**daily_pearl_237**
Sirozda düşük albümin protein bağlı ilaçların serbest fraksiyonunu artırabilir.

**daily_pearl_238**
Asit ve portal hipertansiyon solunum mekaniği ve aspirasyon riskini etkileyebilir.

**daily_pearl_239**
Diyabetik hastada perioperatif hipoglisemi anestezi altında maskelenebilir.

**daily_pearl_240**
Perioperatif hiperglisemi enfeksiyon ve yara iyileşme sorunları ile ilişkilidir.

**daily_pearl_241**
DKA şüphesinde sıvı, insülin ve potasyum yönetimi birlikte planlanmalıdır.

**daily_pearl_242**
Uzun süre steroid kullanan hastada adrenal supresyon ve stres doz steroid ihtiyacı değerlendirilmelidir.

**daily_pearl_243**
Adrenal yetmezlik perioperatif refrakter hipotansiyonla kendini gösterebilir.

**daily_pearl_244**
Hipertiroidi taşikardi, hipertansiyon ve artmış oksijen tüketimi ile perioperatif riski artırır.

**daily_pearl_245**
Tiroid fırtınası hipertermi, taşikardi, hipertansiyon ve bilinç değişikliği ile acil tanınmalıdır.

**daily_pearl_246**
Hipotiroidi ilaçlara duyarlılığı, hipotermi riskini ve ventilasyon depresyonunu artırabilir.

**daily_pearl_247**
Feokromositoma cerrahisinde preoperatif alfa blokaj ve volüm optimizasyonu kritik önemdedir.

**daily_pearl_248**
Feokromositoma çıkarıldıktan sonra vazodilatasyon ve katekolamin azalmasına bağlı hipotansiyon gelişebilir.

**daily_pearl_249**
Karsinoid sendromda tümör manipülasyonu flushing, bronkospazm ve ciddi hipotansiyon tetikleyebilir.

**daily_pearl_250**
Özel popülasyonlarda güvenli anestezi, standart protokolü değil hastaya göre uyarlanmış planı gerektirir.
"""

# Raw English Spot Texts
en_raw = """
**daily_pearl_201**
In pregnancy, FRC decreases and oxygen consumption increases; desaturation may occur faster than expected.

**daily_pearl_202**
In pregnancy, preoxygenation is not optional; it is a critical safety step before induction.

**daily_pearl_203**
Left uterine displacement during cesarean delivery helps reduce aortocaval compression.

**daily_pearl_204**
Obstetric hypotension after spinal anesthesia should be treated rapidly because it may reduce uteroplacental perfusion.

**daily_pearl_205**
Phenylephrine is often preferred for obstetric spinal hypotension; if bradycardia is present, agent choice should be reassessed.

**daily_pearl_206**
Difficult airway risk in pregnancy is more serious because of edema, weight gain, and rapid desaturation.

**daily_pearl_207**
If aspiration risk is high in pregnancy, the rescue airway plan should be clear before induction.

**daily_pearl_208**
In preeclampsia, airway edema and exaggerated laryngoscopic response should be anticipated.

**daily_pearl_209**
Magnesium sulfate in preeclamptic patients may prolong neuromuscular blockade.

**daily_pearl_210**
In HELLP syndrome, neuraxial decisions should be based not only on a single platelet value but also on trend and clinical picture.

**daily_pearl_211**
Uterine atony is one of the most common causes of postpartum hemorrhage, and uterotonics should be considered early.

**daily_pearl_212**
Rapid bolus oxytocin may cause hypotension and tachycardia.

**daily_pearl_213**
When placenta accreta is suspected, massive bleeding preparation and multidisciplinary planning are required.

**daily_pearl_214**
Amniotic fluid embolism should be considered with sudden hypoxemia, hypotension, and coagulopathy.

**daily_pearl_215**
In pediatric patients, drug doses should be calculated per kilogram and maximum doses checked.

**daily_pearl_216**
In children, low FRC and high oxygen consumption cause rapid desaturation.

**daily_pearl_217**
Pediatric bradycardia is often a serious sign of hypoxia.

**daily_pearl_218**
Laryngospasm in children may rapidly progress to desaturation and bradycardia.

**daily_pearl_219**
Before pediatric airway intervention, appropriate-size mask, tube, laryngoscope, and drugs should be ready.

**daily_pearl_220**
Children with upper respiratory infection have increased risk of bronchospasm, laryngospasm, and desaturation.

**daily_pearl_221**
In premature infants, postoperative apnea risk requires a monitoring plan in advance.

**daily_pearl_222**
In neonatal resuscitation, improvement in heart rate is the most important indicator of effective ventilation.

**daily_pearl_223**
Hypotension can be a late sign in children; perfusion, capillary refill, and heart rate provide earlier warning.

**daily_pearl_224**
Hypotonic solutions in pediatric fluid therapy may increase the risk of hyonatremi.

**daily_pearl_225**
Elderly patients have increased pharmacodynamic sensitivity; drugs should be started low and titrated.

**daily_pearl_226**
Frailty may predict complications and functional decline better than age alone in elderly patients.

**daily_pearl_227**
Avoiding benzodiazepines and controlling pain well are important to reduce delirium risk in elderly patients.

**daily_pearl_228**
Hypothermia may develop more easily in elderly patients and may prolong recovery.

**daily_pearl_229**
In obese patients, ramped positioning may improve preoxygenation, mask ventilation, and laryngoscopy.

**daily_pearl_230**
Obesity increases risks related to postoperative hypoventilation, atelectasis, and OSA.

**daily_pearl_231**
In OSA patients, opioid-sparing analgesia and postoperative respiratory monitoring should be planned.

**daily_pearl_232**
Sedative premedication should be carefully considered in patients with OSA.

**daily_pearl_233**
In renal failure, potassium, volume status, and drug elimination should be checked preoperatively.

**daily_pearl_234**
In dialysis patients, timing of last dialysis and vascular access affect the anesthetic plan.

**daily_pearl_235**
Uremia may cause platelet dysfunction and increase bleeding risk.

**daily_pearl_236**
In liver failure, coagulopathy, hypoglycemia, and delayed drug metabolism should be expected.

**daily_pearl_237**
In cirrhosis, low albumin may increase the free fraction of protein-bound drugs.

**daily_pearl_238**
Ascites and portal hypertension may affect respiratory mechanics and aspiration risk.

**daily_pearl_239**
In diabetic patients, perioperative hypoglycemia may be masked under anesthesia.

**daily_pearl_240**
Perioperative hyperglycemia is associated with infection and impaired wound healing.

**daily_pearl_241**
If DKA is suspected, fluid, insulin, and potassium management should be planned together.

**daily_pearl_242**
In patients on long-term steroids, adrenal suppression and stress-dose steroid need should be assessed.

**daily_pearl_243**
Adrenal insufficiency may present as perioperative refractory hypotension.

**daily_pearl_244**
Hyperthyroidism increases perioperative risk through tachycardia, hypertension, and increased oxygen consumption.

**daily_pearl_245**
Thyroid storm must be recognized urgently with hyperthermia, tachycardia, hypertension, and altered mental status.

**daily_pearl_246**
Hypothyroidism may increase drug sensitivity, hypothermia risk, and ventilatory depression.

**daily_pearl_247**
In pheochromocytoma surgery, preoperative alpha blockade and volume optimization are critical.

**daily_pearl_248**
After pheochromocytoma removal, hypotension may occur due to vasodilation and reduced catecholamines.

**daily_pearl_249**
In carcinoid syndrome, tumor manipulation may trigger flushing, bronchospasm, and severe hypotension.

**daily_pearl_250**
Safe anesthesia in special populations requires an individualized plan rather than a standard protocol.
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

# Map subcategory, tags and clinicalUse for the 50 pearls (201-250)
def get_metadata(num):
    n = int(num)
    if n <= 207:
        return "Obstetric Anesthesia", "Physiology & Airway safety", ["pregnancy", "frc", "preoxygenation", "LUD", "spinal_hypotension"]
    elif n <= 210:
        return "Preeclampsia & HELLP", "High-risk obstetrics", ["preeclampsia", "magnesium_sulfate", "hellp", "platelets"]
    elif n <= 214:
        return "Obstetric Emergencies", "Bleeding & Embolism rescue", ["atony", "oxytocin", "placenta_accreta", "amniotic_embolism"]
    elif n <= 224:
        return "Pediatric Anesthesia", "Pediatric safety", ["pediatric", "frc", "bradycardia", "laryngospasm", "resuscitation"]
    elif n <= 228:
        return "Geriatric Anesthesia", "Geriatric optimization", ["geriatric", "frailty", "delirium", "hypothermia"]
    elif n <= 232:
        return "Obesity & OSA", "Airway & Postop planning", ["obesity", "osa", "ramped", "opioid_sparing"]
    elif n <= 235:
        return "Renal Insufficiency", "Renal considerations", ["renal", "dialysis", "potassium", "uremic_bleeding"]
    elif n <= 238:
        return "Hepatic Insufficiency", "Hepatic considerations", ["hepatic", "liver_failure", "cirrhosis", "ascites"]
    elif n <= 243:
        return "Diabetes & Adrenal Special Populations", "Metabolic optimization", ["diabetes", "hyperglycemia", "steroids", "adrenal_insufficiency"]
    elif n <= 246:
        return "Thyroid Special Populations", "Endocrine safety", ["hyperthyroidism", "thyroid_storm", "hypothyroidism"]
    elif n <= 249:
        return "Endocrine Crises", "Endocrine emergency", ["pheochromocytoma", "carcinoid_syndrome", "alpha_blockade"]
    else:
        return "Special Populations Care", "Individualized planning", ["special_populations", "safety", "individualized"]

# High Yield Numbers for Batch 5 (201-250)
def get_importance(num):
    n = int(num)
    high_yield_nums = {201, 202, 203, 204, 206, 208, 209, 210, 211, 214, 215, 216, 217, 218, 222, 225, 226, 227, 229, 231, 233, 235, 236, 239, 242, 243, 245, 247, 248, 250}
    return "high" if n in high_yield_nums else "medium"

pearls_list = []
for i in range(201, 251):
    num_str = f"{i:03d}"
    pearl_id = f"daily_pearl_{num_str}"
    
    sub, use, tags = get_metadata(i)
    imp = get_importance(i)
    
    pearls_list.append({
        "id": pearl_id,
        "category": "OBSTETRIC_PEDIATRIC_SPECIAL",
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

# Keep first 200 items (AIRWAY, HEMO, DRUGS, REGIONAL)
batch1_2_3_4_pearls = [p for p in current_pearls if (p["id"].startswith("daily_pearl_0") or p["id"].startswith("daily_pearl_10") or p["id"].startswith("daily_pearl_11") or p["id"].startswith("daily_pearl_12") or p["id"].startswith("daily_pearl_13") or p["id"].startswith("daily_pearl_14") or p["id"].startswith("daily_pearl_15") or p["id"].startswith("daily_pearl_16") or p["id"].startswith("daily_pearl_17") or p["id"].startswith("daily_pearl_18") or p["id"].startswith("daily_pearl_19") or p["id"].startswith("daily_pearl_200")) or (int(p["id"].split("_")[-1]) <= 200)]

# Add remaining shifted dummy items for final category (daily_pearl_251 to daily_pearl_253)
shifted_dummies = [
  {
    "id": "daily_pearl_251",
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
    "id": "daily_pearl_252",
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
    "id": "daily_pearl_253",
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
combined_all = batch1_2_3_4_pearls + pearls_list + shifted_dummies

# Write JSON
with open(current_path, "w", encoding="utf-8") as out:
    json.dump(combined_all, out, indent=2, ensure_ascii=False)

print(f"SUCCESS: Integrated Batch 5! Wrote {len(combined_all)} clinical pearls to {current_path} successfully!")
