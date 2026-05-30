import json
import re

# Raw Turkish Spot Texts
tr_raw = """
**daily_pearl_001**
Preoksijenasyon yalnızca FiO₂ vermek değil, apne sırasında oksijen rezervini artırma işlemidir.

**daily_pearl_002**
İyi preoksijenasyon için maske kaçağı önlenmeli, hasta kooperasyonu sağlanmalı ve yeterli süre verilmelidir.

**daily_pearl_003**
Obez hastada ramped pozisyon hem preoksijenasyonu hem de laringoskopik görüşü iyileştirebilir.

**daily_pearl_004**
Gebede ve obez hastada güvenli apne süresi kısa olduğu için ilk deneme en iyi deneme olmalıdır.

**daily_pearl_005**
Pediatrik hastada hipoksemi hızlı gelişir; havayolu girişimi öncesi ekipman ve ilaçlar hazır olmalıdır.

**daily_pearl_006**
Zor havayolunda amaç “tüp geçirmek” değil, oksijenasyonu sürdürmektir.

**daily_pearl_007**
Başarısız entübasyonda aynı yöntemi tekrarlamak yerine erken strateji değiştirmek daha güvenlidir.

**daily_pearl_008**
Yardım çağırmak zor havayolu yönetiminde gecikmiş değil, erken bir basamak olmalıdır.

**daily_pearl_009**
Videolaringoskop iyi görüntü sağlayabilir ama tüp ilerletme hâlâ zor olabilir.

**daily_pearl_010**
Supraglottik havayolu başarısız entübasyonda oksijenasyonu kurtarmak için güçlü bir seçenektir.

**daily_pearl_011**
CICO durumunda zaman kaybetmeden acil ön boyun erişimi düşünülmelidir.

**daily_pearl_012**
Endotrakeal tüp yerleşimini doğrulamada sürekli dalga formlu kapnografi en güvenilir yöntemdir.

**daily_pearl_013**
Özofageal entübasyonda kalıcı ve düzenli ETCO₂ dalga formu beklenmez.

**daily_pearl_014**
Ani ETCO₂ kaybı tüp çıkması, devre ayrılması, kardiyak arrest veya masif emboli belirtisi olabilir.

**daily_pearl_015**
Hipoksemi geliştiğinde ilk adım FiO₂’yi 1.0 yapmak ve oksijenasyonu hızla kurtarmaktır.

**daily_pearl_016**
İntraoperatif hipoksemide tüp pozisyonu, devre, ventilasyon, bronkospazm, aspirasyon ve pnömotoraks sistematik kontrol edilmelidir.

**daily_pearl_017**
Tek taraflı solunum sesi ana bronş entübasyonu veya pnömotoraks açısından uyarıcıdır.

**daily_pearl_018**
Yüksek peak basınçta manuel ventilasyona geçmek sorunun direnç mi kompliyans mı olduğunu anlamayı kolaylaştırır.

**daily_pearl_019**
Peak basınç yüksek, plateau normalse havayolu direnci artışı düşünülmelidir.

**daily_pearl_020**
Peak ve plateau basınç birlikte yüksekse akciğer veya toraks kompliyansı azalmış olabilir.

**daily_pearl_021**
Bronkospazmda shark-fin kapnografi, uzamış ekspirasyon ve yüksek peak basınç beklenir.

**daily_pearl_022**
Şiddetli bronkospazmda wheezing kaybolabilir; sessiz akciğer kritik obstrüksiyon göstergesidir.

**daily_pearl_023**
Bronkospazm yönetiminde derin anestezi, inhale beta-agonist ve tetikleyicinin kaldırılması temel yaklaşımdır.

**daily_pearl_024**
Laringospazmda ilk yaklaşım uyarıyı kesmek, %100 oksijen, CPAP ve jaw thrust uygulamaktır.

**daily_pearl_025**
Tam laringospazmda ventilasyon sağlanamıyorsa süksinilkolin geciktirilmemelidir.

**daily_pearl_026**
Laringospazm sonrası negatif basınçlı pulmoner ödem gelişebileceği unutulmamalıdır.

**daily_pearl_027**
Aspirasyon şüphesinde öncelik oksijenasyon, havayolunun temizlenmesi ve bronkospazm tedavisidir.

**daily_pearl_028**
Aspirasyondan sonra antibiyotik her zaman rutin değildir; klinik enfeksiyon bulgularına göre karar verilir.

**daily_pearl_029**
Atelektazi genel anestezi sonrası hipokseminin sık ve erken nedenlerinden biridir.

**daily_pearl_030**
PEEP atelektaziyi azaltabilir ancak venöz dönüşü ve kardiyak debiyi azaltabileceği için dikkatle filtre edilmelidir.

**daily_pearl_031**
Auto-PEEP’te solunum frekansını azaltmak ve ekspirasyon süresini uzatmak çoğu zaman faydalıdır.

**daily_pearl_032**
KOAH hastasında yüksek solunum frekansı dinamik hiperinflasyonu kötüleştirebilir.

**daily_pearl_033**
Astımlı hastada entübasyon öncesi yeterli anestezi derinliği bronkospazm riskini azaltır.

**daily_pearl_034**
Devrede inspirasyon fazında CO₂ görülmesi rebreathing veya CO₂ absorbent problemi düşündürür.

**daily_pearl_035**
ETCO₂ ile PaCO₂ farkının artması ölü boşluk artışı veya perfüzyon bozukluğunu gösterebilir.

**daily_pearl_036**
Pulse oksimetre normal görünse bile ventilasyon bozukluğu kapnografiden anlaşılabilir.

**daily_pearl_037**
Karbonmonoksit zehirlenmesinde pulse oksimetre yanıltıcı şekilde normal veya yüksek gösterebilir.

**daily_pearl_038**
Methemoglobinemide pulse oksimetre değeri yaklaşık %85 civarında takılabilir.

**daily_pearl_039**
Düşük perfüzyon, hareket ve oje pulse oksimetre güvenilirliğini azaltabilir.

**daily_pearl_040**
Ekstübasyon da entübasyon kadar planlanmalıdır; özellikle zor havayolu hastasında geri dönüş planı hazır olmalıdır.

**daily_pearl_041**
Zor entübasyon öyküsü olan hastada ekstübasyon öncesi re-entübasyon stratejisi belirlenmelidir.

**daily_pearl_042**
Havayolu ödemi şüphesinde cuff leak değerlendirmesi ve gecikmiş ekstübasyon düşünülebilir.

**daily_pearl_043**
OSA hastasında opioid ve sedatifler postoperatif havayolu obstrüksiyonu riskini artırır.

**daily_pearl_044**
Obez hastada ekstübasyon tam uyanıklıkta ve baş yükseltilmiş pozisyonda daha güvenli olabilir.

**daily_pearl_045**
Rezidüel nöromüsküler blok postoperatif hipoksemi ve havayolu obstrüksiyonunun önlenebilir nedenidir.

**daily_pearl_046**
TOF oranı 0.9’un altında ise güvenli derlenme için rezidüel blok hâlâ klinik olarak önemlidir.

**daily_pearl_047**
Postoperatif hipoksemide atelektazi, opioid etkisi, rezidüel blok, aspirasyon ve pulmoner emboli düşünelmelidir.

**daily_pearl_048**
Transport sırasında oksijen kaynağı, monitörizasyon, ambu ve acil havayolu ekipmanı hazır olmalıdır.

**daily_pearl_049**
Havayolu krizi sırasında kapalı döngü iletişim görevlerin doğru anlaşılmasını sağlar.

**daily_pearl_050**
Havayolu yönetiminde en güvenli plan, başarısızlık ihtimalini baştan kabul eden plandır.
"""

# Raw English Spot Texts
en_raw = """
**daily_pearl_001**
Preoxygenation is not just giving FiO₂; it increases oxygen reserve during apnea.

**daily_pearl_002**
Effective preoxygenation requires preventing mask leak, ensuring patient cooperation, and allowing enough time.

**daily_pearl_003**
In obese patients, the ramped position may improve both preoxygenation and laryngoscopic view.

**daily_pearl_004**
In pregnant and obese patients, safe apnea time is short, so the first attempt should be the best attempt.

**daily_pearl_005**
In pediatric patients, hypoxemia develops rapidly; airway equipment and drugs should be ready before intervention.

**daily_pearl_006**
In difficult airway, the goal is not “passing the tube” but maintaining oxygenation.

**daily_pearl_007**
After failed intubation, changing strategy early is safer than repeating the same approach.

**daily_pearl_008**
Calling for help should be an early, not delayed, step in difficult airway management.

**daily_pearl_009**
Videolaryngoscopy may provide a good view, but tube delivery can still be difficult.

**daily_pearl_010**
A supraglottic airway is a strong option to rescue oxygenation after failed intubation.

**daily_pearl_011**
In CICO, emergency front-of-neck access should be considered without delay.

**daily_pearl_012**
Continuous waveform capnography is the most reliable method to confirm endotracheal tube placement.

**daily_pearl_013**
Persistent and regular ETCO₂ waveform is not expected with esophageal intubation.

**daily_pearl_014**
Sudden loss of ETCO₂ may indicate tube dislodgement, circuit disconnection, cardiac arrest, or massive embolism.

**daily_pearl_015**
When hypoxemia develops, the first step is to set FiO₂ to 1.0 and rapidly rescue oxygenation.

**daily_pearl_016**
In intraoperative hypoxemia, tube position, circuit, ventilation, bronchospasm, aspiration, and pneumothorax should be checked systematically.

**daily_pearl_017**
Unilateral breath sounds should raise concern for mainstem intubation or pneumothorax.

**daily_pearl_018**
In high peak pressure, switching to manual ventilation helps determine whether the problem is resistance or compliance.

**daily_pearl_019**
High peak pressure with normal plateau pressure suggests increased airway resistance.

**daily_pearl_020**
High peak and plateau pressures together may indicate reduced lung or chest wall compliance.

**daily_pearl_021**
Bronchospasm is associated with shark-fin capnography, prolonged expiration, and high peak pressure.

**daily_pearl_022**
In severe bronchospasm, wheezing may disappear; a silent chest indicates critical obstruction.

**daily_pearl_023**
Bronchospasm management is based on deepening anesthesia, inhaled beta-agonist, and removing the trigger.

**daily_pearl_024**
Initial laryngospasm management includes stopping stimulation, 100% oxygen, CPAP, and jaw thrust.

**daily_pearl_025**
If ventilation is impossible in complete laryngospasm, succinylcholine should not be delayed.

**daily_pearl_026**
Negative-pressure pulmonary edema may occur after laryngospasm.

**daily_pearl_027**
In suspected aspiration, priorities are oxygenation, airway clearance, and bronchospasm treatment.

**daily_pearl_028**
Antibiotics after aspiration are not always routine; the decision depends on clinical signs of infection.

**daily_pearl_029**
Atelectasis is a common early cause of hypoxemia after general anesthesia.

**daily_pearl_030**
PEEP may reduce atelectasis, but it should be titrated carefully because it can reduce venous return and cardiac output.

**daily_pearl_031**
In auto-PEEP, reducing respiratory rate and prolonging expiratory time are often helpful.

**daily_pearl_032**
In COPD, high respiratory rate may worsen dynamic hyperinflation.

**daily_pearl_033**
In asthmatic patients, adequate anesthetic depth before intubation reduces bronchospasm risk.

**daily_pearl_034**
CO₂ during the inspiratory phase suggests rebreathing or a CO₂ absorbent problem.

**daily_pearl_035**
An increased ETCO₂–PaCO₂ gradient may indicate increased dead space or impaired perfusion.

**daily_pearl_036**
Even when pulse oximetry appears normal, capnography may reveal ventilatory failure.

**daily_pearl_037**
In carbon monoxide poisoning, pulse oximetry may be misleadingly normal or high.

**daily_pearl_038**
In methemoglobinemia, pulse oximetry may remain around approximately 85%.

**daily_pearl_039**
Low perfusion, motion, and nail polish may reduce pulse oximeter reliability.

**daily_pearl_040**
Extubation should be planned as carefully as intubation, especially in difficult airway patients.

**daily_pearl_041**
In patients with previous difficult intubation, a re-intubation strategy should be defined before extubation.

**daily_pearl_042**
When airway edema is suspected, cuff leak assessment and delayed extubation may be considered.

**daily_pearl_043**
In OSA patients, opioids and sedatives increase the risk of postoperative airway obstruction.

**daily_pearl_044**
In obese patients, extubation may be safer when fully awake and head-up.

**daily_pearl_045**
Residual neuromuscular blockade is a preventable cause of postoperative hypoxemia and airway obstruction.

**daily_pearl_046**
If the TOF ratio is below 0.9, residual block remains clinically important for safe recovery.

**daily_pearl_047**
In postoperative hypoxemia, consider atelectasis, opioid effect, residual block, aspiration, and pulmonary embolism.

**daily_pearl_048**
During transport, oxygen supply, monitoring, bag-mask device, and emergency airway equipment should be ready.

**daily_pearl_049**
During an airway crisis, closed-loop communication ensures tasks are correctly understood.

**daily_pearl_050**
The safest airway plan is one that accepts the possibility of failure from the beginning.
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

# Map subcategory, tags and clinicalUse for the 50 pearls
def get_metadata(num):
    n = int(num)
    if n <= 4:
        return "Preoxygenation", "Preoxygenation efficacy", ["preoxygenation", "airway", "induction"]
    elif n == 5:
        return "Pediatric Airway", "Pediatric preparation", ["pediatric", "airway", "preparation"]
    elif n <= 11:
        return "Airway Rescue", "Difficult airway rescue", ["rescue", "difficult_airway", "cico", "emergency"]
    elif n <= 14:
        return "Capnography", "Airway confirmation", ["capnography", "monitoring", "etco2"]
    elif n <= 17:
        return "Hypoxemia", "Hypoxemia rescue", ["hypoxemia", "oxygenation", "emergency"]
    elif n <= 20:
        return "Ventilation Pressures", "Ventilation optimization", ["compliance", "resistance", "pressures"]
    elif n <= 23:
        return "Bronchospasm", "Bronchospasm rescue", ["bronchospasm", "wheezing", "emergency"]
    elif n <= 26:
        return "Laryngospasm", "Laryngospasm rescue", ["laryngospasm", "pediatric", "larson", "pulmonary_edema"]
    elif n <= 28:
        return "Aspiration", "Aspiration management", ["aspiration", "pneumonitis", "infection"]
    elif n <= 30:
        return "Atelectasis", "Atelectasis recruitment", ["atelectasis", "peep", "recruitment"]
    elif n <= 33:
        return "Ventilation Resistance", "Ventilation planning", ["copd", "astma", "auto_peep", "dynamic_hyperinflation"]
    elif n <= 36:
        return "Capnography Monitoring", "Monitoring accuracy", ["capnography", "etco2", "dead_space"]
    elif n <= 39:
        return "Pulse Oximetry", "Monitoring limits", ["pulse_oximetry", "co_poisoning", "methemoglobinemia", "perfusion"]
    elif n <= 44:
        return "Extubation", "Extubation planning", ["extubation", "difficult_airway", "osa", "obesity", "cuff_leak"]
    elif n <= 47:
        return "Postoperative Recovery", "Postop recovery", ["postoperative", "residual_block", "tof", "hypoxemia"]
    elif n == 48:
        return "Transport", "Patient transport", ["transport", "safety", "monitoring"]
    else:
        return "Crisis Management", "Crisis communication", ["crisis", "safety", "communication"]

# Allowed Importance
def get_importance(num):
    # Highlight critical life-threatening topics as high yield
    high_yield_nums = {1, 2, 3, 4, 6, 7, 8, 11, 12, 14, 15, 16, 21, 22, 24, 25, 27, 30, 40, 41, 42, 45, 46, 49, 50}
    return "high" if int(num) in high_yield_nums else "medium"

pearls_list = []
for i in range(1, 51):
    num_str = f"{i:03d}"
    pearl_id = f"daily_pearl_{num_str}"
    
    sub, use, tags = get_metadata(i)
    imp = get_importance(i)
    
    pearls_list.append({
        "id": pearl_id,
        "category": "AIRWAY_RESPIRATORY",
        "subcategory": sub,
        "tr": tr_spots[pearl_id],
        "en": en_spots[pearl_id],
        "clinicalUse": use,
        "importance": imp,
        "tags": tags,
        "sourceType": "clinical_review",
        "lastReviewed": "2026-05-30"
    })

# Add existing shifted dummy items for other categories (daily_pearl_051 to daily_pearl_065)
shifted_dummies = [
  {
    "id": "daily_pearl_051",
    "category": "HEMODYNAMIC_CARDIOVASCULAR",
    "subcategory": "Arterial Waveform",
    "tr": "Arteriyel dalga formundaki solunumsal varyasyon (dicrotic notch değişkenliği) sıvı yanıtının güçlü bir dinamik göstergesidir.",
    "en": "Respiratory variation in arterial waveform (dicrotic notch variability) is a strong dynamic indicator of fluid responsiveness.",
    "clinicalUse": "Fluid management",
    "importance": "high",
    "tags": ["arterial_line", "fluid_responsiveness", "monitoring"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_052",
    "category": "HEMODYNAMIC_CARDIOVASCULAR",
    "subcategory": "Vasopressors in Sepsis",
    "tr": "Sepsise bağlı hipotansiyonda erken noradrenalin başlanması, aşırı sıvı yüklenmesini önler ve organ perfüzyonunu korur.",
    "en": "Early initiation of norepinephrine in sepsis-induced hypotension prevents fluid overload and preserves organ perfusion.",
    "clinicalUse": "Hemodynamic support",
    "importance": "high",
    "tags": ["norepinephrine", "sepsis", "shock"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_053",
    "category": "HEMODYNAMIC_CARDIOVASCULAR",
    "subcategory": "Diastolic Pressure",
    "tr": "Diyastolik kan basıncı, sol ventrikül koroner perfüzyonunun ana belirleyicisidir; aşırı hipotansiyon miyokard iskemisini tetikler.",
    "en": "Diastolic blood pressure is the primary determinant of left ventricular coronary perfusion; severe hypotension triggers myocardial ischemia.",
    "clinicalUse": "Coronary perfusion",
    "importance": "medium",
    "tags": ["diastolic", "coronary", "ischemia"],
    "sourceType": "clinical_review",
    "lastReviewed": "2026-05-30"
  },
  {
    "id": "daily_pearl_054",
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
    "id": "daily_pearl_055",
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
    "id": "daily_pearl_056",
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
    "id": "daily_pearl_057",
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
    "id": "daily_pearl_058",
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
    "id": "daily_pearl_059",
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
    "id": "daily_pearl_060",
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
    "id": "daily_pearl_061",
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
    "id": "daily_pearl_062",
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
    "id": "daily_pearl_063",
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
    "id": "daily_pearl_064",
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
    "id": "daily_pearl_065",
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
combined_pearls = pearls_list + shifted_dummies

output_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/assets/daily_clinical_pearls.json"
with open(output_path, "w", encoding="utf-8") as out:
    json.dump(combined_pearls, out, indent=2, ensure_ascii=False)

print(f"SUCCESS: Wrote {len(combined_pearls)} clinical pearls to {output_path} successfully!")
