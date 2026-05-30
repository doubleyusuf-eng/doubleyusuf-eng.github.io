import json
import re

# Raw Turkish Spot Texts
tr_raw = """
**daily_pearl_051**
Hipotansiyonda ilk adım ölçümü doğrulamak, perfüzyonu değerlendirmek ve cerrahi alanı kontrol etmektir.

**daily_pearl_052**
Hipotansiyon tedavisinde hedef yalnızca MAP’i yükseltmek değil, organ perfüzyonunu düzeltmektir.

**daily_pearl_053**
Hipovolemide vazopressör geçici destek sağlayabilir ama esas tedavi volüm ve kanama kontrolüdür.

**daily_pearl_054**
Vazodilatasyona bağlı hipotansiyonda fenilefrin, efedrin veya noradrenalin kalp hızı ve kontraktiliteye göre seçilmelidir.

**daily_pearl_055**
Taşikardik ve vazodilate hastada yalnızca efedrin vermek kalp hızını daha da artırabilir.

**daily_pearl_056**
Bradikardik hipotansiyonda efedrin veya atropin fenilefrinden daha uygun olabilir.

**daily_pearl_057**
Fenilefrin SVR’yi artırır ancak bazı hastalarda refleks bradikardi ve kardiyak debi azalması yapabilir.

**daily_pearl_058**
Noradrenalin vazoplejik hipotansiyonda güçlü ve titre edilebilir bir seçenektir.

**daily_pearl_059**
Dobutamin düşük kardiyak debi ve bozulmuş kontraktilitede düşünülür.

**daily_pearl_060**
Kardiyojenik şokta sadece afterload artırmak kardiyak debiyi daha da bozabilir.

**daily_pearl_061**
Ani hipotansiyonda aritmi, kanama, anafilaksi, emboli, ilaç etkisi ve cerrahi olaylar hızlıca dışlanmalıdır.

**daily_pearl_062**
Ani hipertansiyonda ağrı, yetersiz anestezi, hiperkapni, mesane distansiyonu ve cerrahi stimülasyon düşünülmelidir.

**daily_pearl_063**
Taşikardi her zaman ağrı değildir; hipovolemi, hipoksi, hiperkapni, ateş ve ilaç etkileri de aranmalıdır.

**daily_pearl_064**
Bradikardi hipoksiyle ilişkiliyse yalnızca atropin değil, oksijenasyon ve ventilasyon da düzeltilmelidir.

**daily_pearl_065**
Koroner hastada taşikardi, miyokard oksijen tüketimini artırır ve diyastolik perfüzyon süresini azaltır.

**daily_pearl_066**
Koroner perfüzyon için diyastolik basınç ve yeterli hemoglobin önemlidir.

**daily_pearl_067**
ST değişikliği görüldüğünde önce elektrot, lead seçimi, hemodinami ve oksijen sunumu kontrol edilmelidir.

**daily_pearl_068**
Perioperatif miyokard iskemisinde taşikardi, hipotansiyon, hipertansiyon, hipoksemi ve anemi düzeltilmelidir.

**daily_pearl_069**
Ciddi aort stenozunda hipotansiyon ve taşikardi iyi tolere edilmez.

**daily_pearl_070**
Aort stenozunda sinüs ritmi, preload ve sistemik vasküler direnç korunmalıdır.

**daily_pearl_071**
Mitral stenozda taşikardi pulmoner konjesyonu kötüleştirebilir.

**daily_pearl_072**
Aort yetersizliğinde ağır bradikardi regürjitan volümü artırabilir.

**daily_pearl_073**
Mitral yetersizlikte aşırı afterload artışı regürjitasyonu artırabilir.

**daily_pearl_074**
Pulmoner hipertansiyonda hipoksi, hiperkapni, asidoz, ağrı ve hipotermi PVR’yi artırır.

**daily_pearl_075**
Pulmoner hipertansiyonda sağ ventrikül yetmezliği hızla kardiyovasküler kollapsa ilerleyebilir.

**daily_pearl_076**
Sağ ventrikül krizinde sistemik basınç, oksijenasyon ve pulmoner vasküler direnç aynı anda yönetilmelidir.

**daily_pearl_077**
PEEP oksijenasyonu iyileştirebilir ama aşırı PEEP sağ ventrikül afterloadunu artırabilir.

**daily_pearl_078**
Aritmide tedavi kararı ritimden çok hemodinamik stabiliteye göre verilmelidir.

**daily_pearl_079**
Hemodinamik olarak instabil taşiaritmide senkronize kardiyoversiyon geciktirilmemelidir.

**daily_pearl_080**
Yeni gelişen atriyal fibrilasyonda elektrolit, hipovolemi, hipoksi ve cerrahi stres araştırılmalıdır.

**daily_pearl_081**
Hiperkalemi geniş QRS veya ciddi EKG değişikliği yapıyorsa kalsiyum geciktirilmemelidir.

**daily_pearl_082**
Hipokalemi aritmi riskini artırabilir ve kas güçsüzlüğüne katkı sağlayabilir.

**daily_pearl_083**
Arteriyel hat basıncı klinikle uyumsuzsa transdüser seviyesi, sıfırlama ve dalga formu kontrol edilmelidir.

**daily_pearl_084**
Overdamped arteriyel dalga sistolik basıncı düşük, diyastolik basıncı yüksek gösterebilir.

**daily_pearl_085**
Underdamped arteriyel sistem sistolik basıncı olduğundan yüksek gösterebilir.

**daily_pearl_086**
Santral venöz basınç tek başına sıvı yanıtını güvenilir göstermez.

**daily_pearl_087**
Dinamik sıvı yanıtı göstergeleri kontrollü ventilasyon ve düzenli ritimde daha güvenilirdir.

**daily_pearl_088**
Spontan solunum, aritmi ve düşük tidal volüm PPV/SVV yorumunu zayıflatır.

**daily_pearl_089**
Sıvı yüklemesi kararı kan basıncı kadar perfüzyon, laktat, idrar çıkışı ve klinik bağlamla verilmelidir.

**daily_pearl_090**
İdrar çıkışı değerli bir perfüzyon göstergesidir ancak akut değişikliklerde tek başına yeterli değildir.

**daily_pearl_091**
Laktat yüksekliği hipoperfüzyonu gösterebilir ama adrenerjik stres ve ilaçlardan da etkilenebilir.

**daily_pearl_092**
Masif kanamada kalsiyum, sıcaklık, pH ve fibrinojen erken izlenmelidir.

**daily_pearl_093**
Hipotermi, koagülasyonu ve miyokard performansını olumsuz etkileyebilir.

**daily_pearl_094**
Pacemaker veya ICD’li hastada cihaz bağımlılığı ve elektromanyetik girişim planı önceden bilinmelidir.

**daily_pearl_095**
ICD şok fonksiyonu kapatılırsa eksternal defibrilatör hazır olmalıdır.

**daily_pearl_096**
Monopolar koter mümkünse cihazdan uzak ve kısa aralıklarla kullanılmalıdır.

**daily_pearl_097**
TEE instabil hastada preload, kontraktilite, kapak fonksiyonu ve sağ ventrikül değerlendirmesinde çok yararlıdır.

**daily_pearl_098**
Ani ETCO₂ düşüşüyle birlikte hipotansiyon pulmoner emboli veya hava embolisi açısından uyarıcıdır.

**daily_pearl_099**
Hemodinamik krizlerde cerrahla açık iletişim kanama, manipülasyon veya emboli gibi nedenleri hızla ortaya çıkarabilir.

**daily_pearl_100**
İyi hemodinamik yönetim tek bir sayıyı değil, oksijen sunumu ve organ perfüzyonunu hedefler.
"""

# Raw English Spot Texts
en_raw = """
**daily_pearl_051**
In hypotension, the first step is to confirm the measurement, assess perfusion, and check the surgical field.

**daily_pearl_052**
The goal of treating hypotension is not only to raise MAP but to restore organ perfusion.

**daily_pearl_053**
In hypovolemia, vasopressors may provide temporary support, but definitive treatment is volume replacement and bleeding control.

**daily_pearl_054**
In vasodilatory hypotension, phenylephrine, ephedrine, or norepinephrine should be selected according to heart rate and contractility.

**daily_pearl_055**
In a tachycardic vasodilated patient, ephedrine alone may further increase heart rate.

**daily_pearl_056**
In bradycardic hypotension, ephedrine or atropine may be more appropriate than phenylephrine.

**daily_pearl_057**
Phenylephrine increases SVR but may cause reflex bradycardia and reduced cardiac output in some patients.

**daily_pearl_058**
Norepinephrine is a strong and titratable option for vasoplegic hypotension.

**daily_pearl_059**
Dobutamine should be considered in low cardiac output and impaired contractility.

**daily_pearl_060**
In cardiogenic shock, simply increasing afterload may further impair cardiac output.

**daily_pearl_061**
In sudden hypotension, arrhythmia, bleeding, anaphylaxis, embolism, drug effect, and surgical events should be rapidly excluded.

**daily_pearl_062**
In sudden hypertension, consider pain, inadequate anesthesia, hypercapnia, bladder distension, and surgical stimulation.

**daily_pearl_063**
Tachycardia is not always pain; hypovolemia, hypoxia, hypercapnia, fever, and drug effects should also be sought.

**daily_pearl_064**
If bradycardia is related to hypoxia, oxygenation and ventilation must be corrected, not only atropine given.

**daily_pearl_065**
In coronary disease, tachycardia increases myocardial oxygen demand and reduces diastolic perfusion time.

**daily_pearl_066**
Diastolic pressure and adequate hemoglobin are important for coronary perfusion.

**daily_pearl_067**
When ST changes occur, first check electrodes, lead selection, hemodynamics, and oxygen delivery.

**daily_pearl_068**
In perioperative myocardial ischemia, tachycardia, hypotension, hypertension, hypoxemia, and anemia should be corrected.

**daily_pearl_069**
In severe aortic stenosis, hypotension and tachycardia are poorly tolerated.

**daily_pearl_070**
In aortic stenosis, sinus rhythm, preload, and systemic vascular resistance should be maintained.

**daily_pearl_071**
In mitral stenosis, tachycardia may worsen pulmonary congestion.

**daily_pearl_072**
In aortic regurgitation, severe bradycardia may increase regurgitant volume.

**daily_pearl_073**
In mitral regurgitation, excessive afterload may increase regurgitation.

**daily_pearl_074**
In pulmonary hypertension, hypoxia, hypercapnia, acidosis, pain, and hypothermia increase PVR.

**daily_pearl_075**
In pulmonary hypertension, right ventricular failure may rapidly progress to cardiovascular collapse.

**daily_pearl_076**
In right ventricular crisis, systemic pressure, oxygenation, and pulmonary vascular resistance must be managed simultaneously.

**daily_pearl_077**
PEEP may improve oxygenation, but excessive PEEP can increase right ventricular afterload.

**daily_pearl_078**
In arrhythmia, treatment decisions should be based more on hemodynamic stability than rhythm name alone.

**daily_pearl_079**
In hemodynamically unstable tachyarrhythmia, synchronized cardioversion should not be delayed.

**daily_pearl_080**
In new-onset atrial fibrillation, investigate electrolytes, hypovolemia, hypoxia, and surgical stress.

**daily_pearl_081**
If hyperkalemia causes wide QRS or serious ECG changes, calcium should not be delayed.

**daily_pearl_082**
Hypokalemia may increase arrhythmia risk and contribute to muscle weakness.

**daily_pearl_083**
If arterial pressure is inconsistent with the clinical picture, check transducer level, zeroing, and waveform.

**daily_pearl_084**
An overdamped arterial waveform may underestimate systolic pressure and overestimate diastolic pressure.

**daily_pearl_085**
An underdamped arterial system may overestimate systolic pressure.

**daily_pearl_086**
Central venous pressure alone does not reliably indicate fluid responsiveness.

**daily_pearl_087**
Dynamic fluid responsiveness indices are more reliable during controlled ventilation and regular rhythm.

**daily_pearl_088**
Spontaneous breathing, arrhythmia, and low tidal volume weaken PPV/SVV interpretation.

**daily_pearl_089**
Fluid administration should be decided using perfusion, lactate, urine output, and clinical context, not blood pressure alone.

**daily_pearl_090**
Urine output is a useful perfusion marker but is not sufficient alone during acute changes.

**daily_pearl_091**
Elevated lactate may indicate hypoperfusion, but it can also be influenced by adrenergic stress and drugs.

**daily_pearl_092**
In massive bleeding, calcium, temperature, pH, and fibrinogen should be monitored early.

**daily_pearl_093**
Hypothermia may impair coagulation and myocardial performance.

**daily_pearl_094**
In patients with pacemaker or ICD, device dependency and the electromagnetic interference plan should be known in advance.

**daily_pearl_095**
If ICD shock therapy is disabled, an external defibrillator should be immediately available.

**daily_pearl_096**
Monopolar cautery should be used away from the device and in short bursts when possible.

**daily_pearl_097**
TEE is very useful in unstable patients for assessing preload, contractility, valve function, and the right ventricle.

**daily_pearl_098**
Sudden ETCO₂ decrease with hypotension is a warning sign for pulmonary embolism or air embolism.

**daily_pearl_099**
Clear communication with the surgeon during hemodynamic crises may rapidly reveal causes such as bleeding, manipulation, or embolism.

**daily_pearl_100**
Good hemodynamic management targets oxygen delivery and organ perfusion, not a single number.
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

# Map subcategory, tags and clinicalUse for the 50 pearls (051-100)
def get_metadata(num):
    n = int(num)
    if n <= 53:
        return "Hypotension Assessment", "Hypotension diagnosis", ["hypotension", "perfusion", "hypovolemia"]
    elif n <= 60:
        return "Vasopressors & Inotropes", "Vasoactive drug selection", ["norepinephrine", "ephedrine", "phenylephrine", "dobutamine", "shock"]
    elif n <= 64:
        return "Hemodynamic Crisis", "Emergency rescue", ["bradycardia", "tachycardia", "hypoxia", "atropine"]
    elif n <= 68:
        return "Coronary Perfusion", "Coronary ischemia prevention", ["coronary", "diastolic", "ischemia", "anemia"]
    elif n <= 73:
        return "Valvular Heart Disease", "Valvular management", ["aortic_stenosis", "mitral_stenosis", "aortic_regurgitation", "mitral_regurgitation"]
    elif n <= 77:
        return "Pulmonary Hypertension", "PVR management", ["pulmonary_hypertension", "pvr", "right_ventricle", "peep"]
    elif n <= 82:
        return "Arrhythmias", "Rhythm management", ["arrhythmia", "cardioversion", "hyperkalemia", "hypokalemia"]
    elif n <= 85:
        return "Arterial Line Monitoring", "Invasive arterial tracking", ["arterial_line", "overdamped", "underdamped"]
    elif n <= 89:
        return "Fluid Responsiveness", "Fluid optimization", ["fluid_responsiveness", "cvp", "ppv", "svv"]
    elif n <= 93:
        return "Perfusion & Coagulation", "Microcirculation safety", ["lactate", "urine_output", "bleeding", "hypothermia"]
    elif n <= 96:
        return "Cardiac Devices (CIED)", "Pacemaker / ICD safety", ["pacemaker", "icd", "cautery", "electromagnetic"]
    else:
        return "Monitoring & Communication", "Clinical communication", ["tee", "embolism", "cooperation", "perfusion"]

# High Yield Numbers for Batch 2 (051-100)
def get_importance(num):
    n = int(num)
    high_yield_nums = {51, 52, 53, 54, 58, 60, 61, 65, 69, 70, 74, 75, 78, 79, 81, 83, 84, 87, 89, 92, 94, 95, 98, 100}
    return "high" if n in high_yield_nums else "medium"

pearls_list = []
for i in range(51, 101):
    num_str = f"{i:03d}"
    pearl_id = f"daily_pearl_{num_str}"
    
    sub, use, tags = get_metadata(i)
    imp = get_importance(i)
    
    pearls_list.append({
        "id": pearl_id,
        "category": "HEMODYNAMIC_CARDIOVASCULAR",
        "subcategory": sub,
        "tr": tr_spots[pearl_id],
        "en": en_spots[pearl_id],
        "clinicalUse": use,
        "importance": imp,
        "tags": tags,
        "sourceType": "clinical_review",
        "lastReviewed": "2026-05-30"
    })

# Load Batch 1 pearls from current assets
current_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/assets/daily_clinical_pearls.json"
with open(current_path, "r", encoding="utf-8") as f:
    current_pearls = json.load(f)

# Keep only the first 50 items (AIRWAY_RESPIRATORY)
batch1_pearls = [p for p in current_pearls if p["id"].startswith("daily_pearl_0") and int(p["id"].split("_")[-1]) <= 50]

# Add remaining shifted dummy items for other categories (daily_pearl_101 to daily_pearl_112)
shifted_dummies = [
  {
    "id": "daily_pearl_101",
    "category": "DRUGS_PHARMACOLOGY",
    "subcategory": "Sugammadex",
    "tr": "Sugammadex rokuronyum bloğunu hızla geri çevirir; ancak şiddetli anafilaksi riski nedeniyle uygulamadan sonra hasta izlenmelidir.",
    "en": "Sugammadex rapidly reverses rocuronium block; however, patients should be monitored post-administration due to rare severe anaphylaxis risk.",
    "clinicalUse": "Neuromuscular reversal",
    "importance": "high",
    "tags": ["sugammadex", "rocuronium", "anaphylaxis"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_102",
    "category": "DRUGS_PHARMACOLOGY",
    "subcategory": "Propofol Pain",
    "tr": "Propofol enjeksiyon ağrısı, büyük venlerin kullanımı veya öncesinde lidokain verilmesiyle azaltılabilir.",
    "en": "Propofol injection pain can be minimized by using larger veins or pre-administering lidocaine.",
    "clinicalUse": "Induction comfort",
    "importance": "medium",
    "tags": ["propofol", "lidocaine", "injection_pain"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_103",
    "category": "DRUGS_PHARMACOLOGY",
    "subcategory": "Remifentanil Hyperalgesia",
    "tr": "Yüksek doz remifentanil infüzyonu postoperatif hiperaljeziye yol açabilir; geçişte multimodal analjezi planlanmalıdır.",
    "en": "High-dose remifentanil infusion can lead to postoperative hyperalgesia; multimodal analgesia should be planned for transition.",
    "clinicalUse": "Opioid management",
    "importance": "high",
    "tags": ["remifentanil", "hyperalgesia", "analgesia"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_104",
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
    "id": "daily_pearl_105",
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
    "id": "daily_pearl_106",
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
    "id": "daily_pearl_107",
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
    "id": "daily_pearl_108",
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
    "id": "daily_pearl_109",
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
    "id": "daily_pearl_110",
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
    "id": "daily_pearl_111",
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
    "id": "daily_pearl_112",
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

# Combined lists
combined_all = batch1_pearls + pearls_list + shifted_dummies

# Write JSON
with open(current_path, "w", encoding="utf-8") as out:
    json.dump(combined_all, out, indent=2, ensure_ascii=False)

print(f"SUCCESS: Integrated Batch 2! Wrote {len(combined_all)} clinical pearls to {current_path} successfully!")
