import json
import os

existing_path = "app/src/main/assets/board_spot_notes.json"

# Load existing notes
if os.path.exists(existing_path):
    with open(existing_path, "r", encoding="utf-8") as f:
        spot_notes = json.load(f)
    print(f"Loaded {len(spot_notes)} existing spot notes.")
else:
    spot_notes = []
    print("No existing spot notes found. Starting fresh.")

new_spots = [
    # 051
    {
        "id": "aba_basic_spot_051",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "MAC, hastaların %50’sinde cerrahi uyarana hareket yanıtını önleyen alveoler anestezik konsantrasyondur.",
        "en": "MAC is the alveolar anesthetic concentration that prevents movement to surgical stimulus in 50% of patients.",
        "importance": "high",
        "tags": ["mac", "inhalational_anesthetics", "definition"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 052
    {
        "id": "aba_basic_spot_052",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "MAC yaşla azalır; yaşlı hastalarda volatil anestezik gereksinimi daha düşüktür.",
        "en": "MAC decreases with age; elderly patients require less volatile anesthetic.",
        "importance": "high",
        "tags": ["mac", "elderly_patients", "volatile_requirement"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 053
    {
        "id": "aba_basic_spot_053",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Hipotermi, gebelik, opioidler ve benzodiazepinler MAC’i azaltır.",
        "en": "Hypothermia, pregnancy, opioids, and benzodiazepines decrease MAC.",
        "importance": "high",
        "tags": ["mac_factors", "hypothermia", "pregnancy", "opioids", "benzodiazepines"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 054
    {
        "id": "aba_basic_spot_054",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Düşük kan/gaz çözünürlüğü olan volatil ajanların indüksiyon ve derlenmesi daha hızlıdır.",
        "en": "Volatile agents with low blood/gas solubility have faster induction and recovery.",
        "importance": "high",
        "tags": ["solubility", "blood_gas_coefficient", "induction_speed", "recovery_speed"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 055
    {
        "id": "aba_basic_spot_055",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Desfluran düşük kan/gaz çözünürlüğü nedeniyle hızlı derlenme sağlar.",
        "en": "Desflurane provides rapid recovery because of its low blood/gas solubility.",
        "importance": "high",
        "tags": ["desflurane", "solubility", "rapid_recovery"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 056
    {
        "id": "aba_basic_spot_056",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Sevofluran düşük havayolu irritasyonu nedeniyle inhalasyon indüksiyonunda sık kullanılır.",
        "en": "Sevoflurane is commonly used for inhalational induction because it causes low airway irritation.",
        "importance": "high",
        "tags": ["sevoflurane", "inhalational_induction", "airway_irritation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 057
    {
        "id": "aba_basic_spot_057",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "İzofluran keskin kokulu olabilir ve inhalasyon indüksiyonu için sevoflurana göre daha az uygundur.",
        "en": "Isoflurane may be pungent and is less suitable for inhalational induction than sevoflurane.",
        "importance": "high",
        "tags": ["isoflurane", "pungency", "inhalational_induction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 058
    {
        "id": "aba_basic_spot_058",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Azot protoksit analjezik özellik taşır ancak tek başına cerrahi anestezi için yeterli değildir.",
        "en": "Nitrous oxide has analgesic properties but is not sufficient alone for surgical anesthesia.",
        "importance": "high",
        "tags": ["nitrous_oxide", "analgesia", "surgical_anesthesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 059
    {
        "id": "aba_basic_spot_059",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Azot protoksit kapalı hava boşluklarına difüze olarak hacim veya basıncı artırabilir.",
        "en": "Nitrous oxide can diffuse into closed air spaces and increase their volume or pressure.",
        "importance": "high",
        "tags": ["nitrous_oxide", "closed_air_spaces", "diffusion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 060
    {
        "id": "aba_basic_spot_060",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Volatil anestezikler doza bağlı solunum depresyonu yapabilir.",
        "en": "Volatile anesthetics can cause dose-dependent respiratory depression.",
        "importance": "high",
        "tags": ["volatile_anesthetics", "respiratory_depression", "dose_dependent"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 061
    {
        "id": "aba_basic_spot_061",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Inhalational Anesthetics",
        "tr": "Volatil ajanlar sistemik vasküler direnci azaltarak hipotansiyona katkı sağlayabilir.",
        "en": "Volatile agents may contribute to hypotension by reducing systemic vascular resistance.",
        "importance": "high",
        "tags": ["volatile_anesthetics", "systemic_vascular_resistance", "hypotension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 062
    {
        "id": "aba_basic_spot_062",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Propofol GABA-A reseptör aktivitesini artırarak hipnoz oluşturur.",
        "en": "Propofol produces hypnosis by enhancing GABA-A receptor activity.",
        "importance": "high",
        "tags": ["propofol", "gaba_a", "hypnosis"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 063
    {
        "id": "aba_basic_spot_063",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Propofol vazodilatasyon ve miyokard depresyonu ile hipotansiyona neden olabilir.",
        "en": "Propofol may cause hypotension through vasodilation and myocardial depression.",
        "importance": "high",
        "tags": ["propofol", "hypotension", "vasodilation", "myocardial_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 064
    {
        "id": "aba_basic_spot_064",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Propofol antiemetik özellik gösterir ve PONV riskini azaltabilir.",
        "en": "Propofol has antiemetic properties and may reduce PONV risk.",
        "importance": "high",
        "tags": ["propofol", "antiemetic", "ponv_risk"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 065
    {
        "id": "aba_basic_spot_065",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Etomidat hemodinamik stabilite sağlar ancak adrenal steroid sentezini baskılayabilir.",
        "en": "Etomidate provides hemodynamic stability but may suppress adrenal steroid synthesis.",
        "importance": "high",
        "tags": ["etomidate", "hemodynamic_stability", "adrenal_suppression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 066
    {
        "id": "aba_basic_spot_066",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Ketamin NMDA reseptör antagonisti olup analjezi ve dissosiyatif anestezi sağlar.",
        "en": "Ketamine is an NMDA receptor antagonist that provides analgesia and dissociative anesthesia.",
        "importance": "high",
        "tags": ["ketamine", "nmda_antagonist", "dissociative_anesthesia", "analgesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 067
    {
        "id": "aba_basic_spot_067",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Ketamin sempatik aktivasyonu artırarak kalp hızı ve kan basıncını yükseltebilir.",
        "en": "Ketamine may increase heart rate and blood pressure through sympathetic activation.",
        "importance": "high",
        "tags": ["ketamine", "sympathetic_activation", "heart_rate", "hypertension"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 068
    {
        "id": "aba_basic_spot_068",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Intravenous Anesthetics",
        "tr": "Ketamin bronkodilatör özellikleri nedeniyle reaktif havayolu hastalarında yararlı olabilir.",
        "en": "Ketamine may be useful in reactive airway disease because of its bronchodilatory properties.",
        "importance": "high",
        "tags": ["ketamine", "bronchodilation", "reactive_airway"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 069
    {
        "id": "aba_basic_spot_069",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Sedatives",
        "tr": "Benzodiazepinler anksiyoliz, amnezi, sedasyon ve antikonvülzan etki sağlar.",
        "en": "Benzodiazepines provide anxiolysis, amnesia, sedation, and anticonvulsant effects.",
        "importance": "high",
        "tags": ["benzodiazepines", "sedation", "anxiolysis", "amnesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 070
    {
        "id": "aba_basic_spot_070",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Sedatives",
        "tr": "Midazolam fizyolojik pH’da lipofilik hale gelerek hızlı santral sinir sistemi geçişi sağlar.",
        "en": "Midazolam becomes lipophilic at physiological pH, allowing rapid central nervous system penetration.",
        "importance": "high",
        "tags": ["midazolam", "lipophilicity", "cns_penetration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 071
    {
        "id": "aba_basic_spot_071",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Opioids",
        "tr": "Opioidler analjezilerini esas olarak mü opioid reseptörleri üzerinden oluşturur.",
        "en": "Opioids produce analgesia mainly through mu opioid receptors.",
        "importance": "high",
        "tags": ["opioids", "mu_receptors", "analgesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 072
    {
        "id": "aba_basic_spot_072",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Opioids",
        "tr": "Opioidler solunum depresyonu, bulantı, kusma, kaşıntı ve konstipasyon yapabilir.",
        "en": "Opioids may cause respiratory depression, nausea, vomiting, pruritus, and constipation.",
        "importance": "high",
        "tags": ["opioid_side_effects", "respiratory_depression", "nausea", "constipation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 073
    {
        "id": "aba_basic_spot_073",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Opioids",
        "tr": "Fentanil morfinden daha lipofiliktir ve daha hızlı etki başlangıcına sahiptir.",
        "en": "Fentanyl is more lipophilic than morphine and has a faster onset of action.",
        "importance": "high",
        "tags": ["fentanyl", "morphine", "lipophilicity", "onset_speed"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 074
    {
        "id": "aba_basic_spot_074",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Opioids",
        "tr": "Remifentanil esterazlarla metabolize edilir ve bağlam duyarlı yarılanma süresi kısadır.",
        "en": "Remifentanil is metabolized by esterases and has a short context-sensitive half-time.",
        "importance": "high",
        "tags": ["remifentanil", "esterases", "context_sensitive_half_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 075
    {
        "id": "aba_basic_spot_075",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Opioids",
        "tr": "Morfin histamin salınımına bağlı hipotansiyon ve kaşıntıya neden olabilir.",
        "en": "Morphine may cause hypotension and pruritus due to histamine release.",
        "importance": "high",
        "tags": ["morphine", "histamine_release", "hypotension", "pruritus"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 076
    {
        "id": "aba_basic_spot_076",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Opioids",
        "tr": "Nalokson opioid antagonisti olup opioid kaynaklı solunum depresyonunu geri çevirebilir.",
        "en": "Naloxone is an opioid antagonist that can reverse opioid-induced respiratory depression.",
        "importance": "high",
        "tags": ["naloxone", "opioid_antagonist", "reversal"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 077
    {
        "id": "aba_basic_spot_077",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Opioids",
        "tr": "Naloksonun etki süresi bazı opioidlerden kısa olabilir; tekrar doz gerekebilir.",
        "en": "Naloxone duration may be shorter than that of some opioids; repeated dosing may be required.",
        "importance": "high",
        "tags": ["naloxone", "duration_of_action", "repeated_dosing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 078
    {
        "id": "aba_basic_spot_078",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Junction",
        "tr": "Asetilkolin nöromüsküler kavşakta nikotinik reseptörleri aktive ederek kas kontraksiyonu başlatır.",
        "en": "Acetylcholine activates nicotinic receptors at the neuromuscular junction to initiate muscle contraction.",
        "importance": "high",
        "tags": ["acetylcholine", "nicotinic_receptors", "neuromuscular_junction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 079
    {
        "id": "aba_basic_spot_079",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Nondepolarizan kas gevşeticiler nikotinik asetilkolin reseptörlerinde kompetitif antagonisttir.",
        "en": "Nondepolarizing neuromuscular blockers are competitive antagonists at nicotinic acetylcholine receptors.",
        "importance": "high",
        "tags": ["nondepolarizing_nmbs", "competitive_antagonist", "nicotinic_receptors"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 080
    {
        "id": "aba_basic_spot_080",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Süksinilkolin depolarizan nöromüsküler blok oluşturur ve hızlı başlangıçlıdır.",
        "en": "Succinylcholine produces depolarizing neuromuscular blockade and has a rapid onset.",
        "importance": "high",
        "tags": ["succinylcholine", "depolarizing_nmb", "rapid_onset"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 081
    {
        "id": "aba_basic_spot_081",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Süksinilkolin hiperkalemi, bradikardi, miyalji ve malign hipertermi tetiklenmesiyle ilişkilidir.",
        "en": "Succinylcholine is associated with hyperkalemia, bradycardia, myalgia, and triggering malignant hyperthermia.",
        "importance": "high",
        "tags": ["succinylcholine_complications", "hyperkalemia", "malignant_hyperthermia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 082
    {
        "id": "aba_basic_spot_082",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Yanık, denervasyon ve nöromüsküler hastalıkta süksinilkoline bağlı hiperkalemi riski artar.",
        "en": "The risk of succinylcholine-induced hyperkalemia increases in burns, denervation, and neuromuscular disease.",
        "importance": "high",
        "tags": ["succinylcholine_contraindications", "hyperkalemia_risk", "burns", "denervation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 083
    {
        "id": "aba_basic_spot_083",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Roküronyum hızlı başlangıçlı nondepolarizan kas gevşeticidir.",
        "en": "Rocuronium is a rapid-onset nondepolarizing neuromuscular blocker.",
        "importance": "high",
        "tags": ["rocuronium", "rapid_onset_nmb"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 084
    {
        "id": "aba_basic_spot_084",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Reversal Agents",
        "tr": "Sugammadeks rokuronyum ve vekuronyumu enkapsüle ederek nöromüsküler bloğu geri döndürür.",
        "en": "Sugammadex reverses neuromuscular blockade by encapsulating rocuronium and vecuronium.",
        "importance": "high",
        "tags": ["sugammadex", "encapsulation", "rocuronium_reversal"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 085
    {
        "id": "aba_basic_spot_085",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Reversal Agents",
        "tr": "Neostigmin asetilkolinesterazı inhibe ederek asetilkolin miktarını artırır.",
        "en": "Neostigmine increases acetylcholine by inhibiting acetylcholinesterase.",
        "importance": "high",
        "tags": ["neostigmin", "acetylcholinesterase_inhibition", "acetylcholine_increase"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 086
    {
        "id": "aba_basic_spot_086",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Reversal Agents",
        "tr": "Neostigmin muskarinik yan etkileri azaltmak için glikopirolat veya atropin ile birlikte verilir.",
        "en": "Neostigmine is given with glycopyrrolate or atropine to reduce muscarinic adverse effects.",
        "importance": "high",
        "tags": ["neostigmin", "muscarinic_side_effects", "glycopyrrolate", "atropine"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 087
    {
        "id": "aba_basic_spot_087",
        "exam": "ABA_BASIC",
        "topic": "Neuromuscular Monitoring",
        "subtopic": "Residual Block",
        "tr": "TOF oranı 0.9’un altında ise rezidüel nöromüsküler blok riski devam eder.",
        "en": "If the TOF ratio is below 0.9, the risk of residual neuromuscular blockade remains.",
        "importance": "high",
        "tags": ["tof_ratio", "residual_block", "neuromuscular_monitoring"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 088
    {
        "id": "aba_basic_spot_088",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Hipotermi nöromüsküler blok süresini uzatabilir.",
        "en": "Hypothermia may prolong the duration of neuromuscular blockade.",
        "importance": "high",
        "tags": ["hypothermia_effect", "neuromuscular_blockade_duration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 089
    {
        "id": "aba_basic_spot_089",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Aminoglikozidler ve magnezyum nöromüsküler bloğu potansiyalize edebilir.",
        "en": "Aminoglycosides and magnesium may potentiate neuromuscular blockade.",
        "importance": "high",
        "tags": ["drug_interactions", "potentiation", "aminoglycosides", "magnesium"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 090
    {
        "id": "aba_basic_spot_090",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Lokal anestezikler voltaj kapılı sodyum kanallarını bloke eder.",
        "en": "Local anesthetics block voltage-gated sodium channels.",
        "importance": "high",
        "tags": ["local_anesthetics", "voltage_gated_sodium_channels", "mechanism_of_action"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 091
    {
        "id": "aba_basic_spot_091",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Lokal anesteziklerin noniyonize formu sinir membranından daha kolay geçer.",
        "en": "The non-ionized form of local anesthetics crosses the nerve membrane more easily.",
        "importance": "high",
        "tags": ["non_ionized_form", "membrane_crossing", "ph_effects"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 092
    {
        "id": "aba_basic_spot_092",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Asidik dokuda lokal anesteziklerin etki başlangıcı gecikebilir.",
        "en": "In acidic tissue, the onset of local anesthetic action may be delayed.",
        "importance": "high",
        "tags": ["acidic_tissue", "delayed_onset", "infection"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 093
    {
        "id": "aba_basic_spot_093",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Amid lokal anestezikler esas olarak karaciğerde metabolize edilir.",
        "en": "Amide local anesthetics are metabolized mainly in the liver.",
        "importance": "high",
        "tags": ["amides", "hepatic_metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 094
    {
        "id": "aba_basic_spot_094",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Ester lokal anestezikler plazma kolinesterazları ile metabolize edilir.",
        "en": "Ester local anesthetics are metabolized by plasma cholinesterases.",
        "importance": "high",
        "tags": ["esters", "plasma_cholinesterases", "metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 095
    {
        "id": "aba_basic_spot_095",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Bupivakain kardiyotoksisite açısından lidokainden daha risklidir.",
        "en": "Bupivacaine carries a higher cardiotoxicity risk than lidocaine.",
        "importance": "high",
        "tags": ["bupivacaine", "cardiotoxicity", "lidocaine"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 096
    {
        "id": "aba_basic_spot_096",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Ropivakain bupivakaine göre daha az kardiyotoksik kabul edilir.",
        "en": "Ropivacaine is considered less cardiotoxic than bupivacaine.",
        "importance": "high",
        "tags": ["ropivacaine", "cardiotoxicity_reduction", "bupivacaine"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 097
    {
        "id": "aba_basic_spot_097",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "Local Anesthetics",
        "tr": "Adrenalin lokal anestezik emilimini azaltabilir ve etki süresini uzatabilir.",
        "en": "Epinephrine may reduce local anesthetic absorption and prolong duration of action.",
        "importance": "high",
        "tags": ["epinephrine", "absorption_reduction", "prolonged_duration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 098
    {
        "id": "aba_basic_spot_098",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "LAST Prevention",
        "tr": "LAST erken dönemde ağız çevresinde uyuşma, tinnitus ve metalik tat ile başlayabilir.",
        "en": "LAST may begin with circumoral numbness, tinnitus, and metallic taste.",
        "importance": "high",
        "tags": ["last_signs", "early_toxicity", "metallic_taste", "tinnitus"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 099
    {
        "id": "aba_basic_spot_099",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "LAST Prevention",
        "tr": "LAST nöbet, aritmi ve kardiyovasküler kollapsa ilerleyebilir.",
        "en": "LAST may progress to seizures, arrhythmias, and cardiovascular collapse.",
        "importance": "high",
        "tags": ["last_progression", "seizures", "cardiovascular_collapse"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 100
    {
        "id": "aba_basic_spot_100",
        "exam": "ABA_BASIC",
        "topic": "Pharmacology",
        "subtopic": "LAST Prevention",
        "tr": "Ciddi lokal anestezik sistemik toksisitesinde lipid emülsiyon tedavisi uygulanır.",
        "en": "Lipid emulsion therapy is used for severe local anesthetic systemic toxicity.",
        "importance": "high",
        "tags": ["lipid_emulsion_therapy", "last_treatment", "intralipid"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

combined = spot_notes + new_spots

# Double-check duplicates or incorrect formats in-memory first
seen_ids = set()
duplicates = []
for item in combined:
    note_id = item["id"]
    if note_id in seen_ids:
        duplicates.append(note_id)
    seen_ids.add(note_id)

if duplicates:
    print(f"ERROR: Duplicate IDs found before saving: {duplicates}")
    exit(1)

# Save updated list
with open(existing_path, "w", encoding="utf-8") as f:
    json.dump(combined, f, ensure_ascii=False, indent=2)

print(f"Successfully combined and wrote {len(combined)} spot notes to {existing_path}")
