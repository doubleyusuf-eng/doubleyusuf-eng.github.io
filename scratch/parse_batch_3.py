import json
import os

existing_path = "app/src/main/assets/board_spot_notes.json"

# Load existing
if os.path.exists(existing_path):
    with open(existing_path, "r", encoding="utf-8") as f:
        spot_notes = json.load(f)
    print(f"Loaded {len(spot_notes)} existing spot notes.")
else:
    spot_notes = []
    print("No existing spot notes found. Starting fresh.")

new_spots = [
    # 101
    {
        "id": "edaic_a_spot_101",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Sugammadeks, rokuronyum ve vekuronyumu enkapsüle ederek etkilerini geri döndürür.",
        "en": "Sugammadex reverses rocuronium and vecuronium by encapsulating them.",
        "importance": "high",
        "tags": ["sugammadex", "rocuronium", "vecuronium"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 102
    {
        "id": "edaic_a_spot_102",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Sugammadeks benzil izokinolinyum grubu kas gevşeticileri geri döndürmez.",
        "en": "Sugammadex does not reverse benzylisoquinolinium neuromuscular blockers.",
        "importance": "high",
        "tags": ["sugammadex", "benzylisoquinolinium"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 103
    {
        "id": "edaic_a_spot_103",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Neostigmin asetilkolinesterazı inhibe ederek nöromüsküler kavşakta asetilkolini artırır.",
        "en": "Neostigmine inhibits acetylcholinesterase and increases acetylcholine at the neuromuscular junction.",
        "importance": "high",
        "tags": ["neostigmine", "acetylcholinesterase"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 104
    {
        "id": "edaic_a_spot_104",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Neostigmin muskarinik yan etkileri azaltmak için antikolinerjik ajanla birlikte verilir.",
        "en": "Neostigmine is given with an anticholinergic agent to reduce muscarinic adverse effects.",
        "importance": "high",
        "tags": ["neostigmine", "anticholinergic", "atropine", "glycopyrrolate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 105
    {
        "id": "edaic_a_spot_105",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Derin nöromüsküler blokta neostigmin yetersiz olabilir; spontan geri dönüş bulgusu gerekir.",
        "en": "Neostigmine may be inadequate in deep neuromuscular block; evidence of spontaneous recovery is required.",
        "importance": "high",
        "tags": ["neostigmine", "deep_block", "reversal"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 106
    {
        "id": "edaic_a_spot_106",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Hipotermi nöromüsküler blok süresini uzatabilir.",
        "en": "Hypothermia may prolong the duration of neuromuscular blockade.",
        "importance": "high",
        "tags": ["hypothermia", "neuromuscular_blockade"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 107
    {
        "id": "edaic_a_spot_107",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Neuromuscular Blockers",
        "tr": "Aminoglikozidler, magnezyum ve lityum nöromüsküler bloğu artırabilir.",
        "en": "Aminoglycosides, magnesium, and lithium may potentiate neuromuscular blockade.",
        "importance": "high",
        "tags": ["neuromuscular_blockade", "potentiation", "magnesium", "aminoglycosides"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 108
    {
        "id": "edaic_a_spot_108",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Lokal anestezikler voltaj kapılı sodyum kanallarını bloke ederek aksiyon potansiyeli iletimini engeller.",
        "en": "Local anesthetics block voltage-gated sodium channels and prevent action potential propagation.",
        "importance": "high",
        "tags": ["local_anesthetics", "sodium_channels"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 109
    {
        "id": "edaic_a_spot_109",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Lokal anesteziğin noniyonize formu sinir membranından daha kolay geçer.",
        "en": "The non-ionized form of a local anesthetic crosses the nerve membrane more easily.",
        "importance": "high",
        "tags": ["local_anesthetics", "non_ionized_form", "membrane_crossing"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 110
    {
        "id": "edaic_a_spot_110",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Asidik dokuda lokal anesteziklerin iyonize fraksiyonu artar ve etki başlangıcı gecikebilir.",
        "en": "In acidic tissue, the ionized fraction of local anesthetics increases and onset may be delayed.",
        "importance": "high",
        "tags": ["local_anesthetics", "acidic_tissue", "ionization"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 111
    {
        "id": "edaic_a_spot_111",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Bupivakain kardiyotoksisite riski açısından lidokainden daha tehlikelidir.",
        "en": "Bupivacaine is more dangerous than lidocaine in terms of cardiotoxicity risk.",
        "importance": "high",
        "tags": ["bupivacaine", "cardiotoxicity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 112
    {
        "id": "edaic_a_spot_112",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Ropivakain bupivakaine göre daha az kardiyotoksik kabul edilir.",
        "en": "Ropivacaine is considered less cardiotoxic than bupivacaine.",
        "importance": "high",
        "tags": ["ropivacaine", "cardiotoxicity"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 113
    {
        "id": "edaic_a_spot_113",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Adrenalin eklenmesi lokal anesteziğin sistemik emilimini azaltabilir ve etki süresini uzatabilir.",
        "en": "Adding epinephrine may reduce systemic absorption of a local anesthetic and prolong its duration.",
        "importance": "high",
        "tags": ["local_anesthetics", "epinephrine", "absorption"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 114
    {
        "id": "edaic_a_spot_114",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Lokal anestezik sistemik toksisitesinde erken bulgular ağız çevresinde uyuşma, tinnitus ve metalik tat olabilir.",
        "en": "Early signs of local anesthetic systemic toxicity may include circumoral numbness, tinnitus, and metallic taste.",
        "importance": "high",
        "tags": ["last", "toxicity", "early_signs"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 115
    {
        "id": "edaic_a_spot_115",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Lokal anestezik toksisitesi nöbet, kardiyak aritmi ve kardiyovasküler kollapsa ilerleyebilir.",
        "en": "Local anesthetic toxicity may progress to seizures, cardiac arrhythmias, and cardiovascular collapse.",
        "importance": "high",
        "tags": ["last", "toxicity", "cardiovascular_collapse", "seizures"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 116
    {
        "id": "edaic_a_spot_116",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Lipid emülsiyon tedavisi ciddi lokal anestezik sistemik toksisitesinde kullanılır.",
        "en": "Lipid emulsion therapy is used in severe local anesthetic systemic toxicity.",
        "importance": "high",
        "tags": ["lipid_emulsion", "intralipid", "last_treatment"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 117
    {
        "id": "edaic_a_spot_117",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Ester lokal anestezikler plazma kolinesterazları ile metabolize edilir.",
        "en": "Ester local anesthetics are metabolized by plasma cholinesterases.",
        "importance": "high",
        "tags": ["ester_local_anesthetics", "plasma_cholinesterases", "metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 118
    {
        "id": "edaic_a_spot_118",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Amid lokal anestezikler esas olarak karaciğerde metabolize edilir.",
        "en": "Amide local anesthetics are metabolized mainly in the liver.",
        "importance": "high",
        "tags": ["amide_local_anesthetics", "hepatic_metabolism", "metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 119
    {
        "id": "edaic_a_spot_119",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "pKa değeri fizyolojik pH’a yaklaştıkça lokal anesteziğin etki başlangıcı genellikle hızlanır.",
        "en": "As the pKa approaches physiological pH, the onset of local anesthetic action is usually faster.",
        "importance": "high",
        "tags": ["local_anesthetics", "pka", "onset_time"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 120
    {
        "id": "edaic_a_spot_120",
        "exam": "EDAIC_PAPER_A",
        "topic": "Pharmacology Basics",
        "subtopic": "Local Anesthetics",
        "tr": "Protein bağlanması yüksek olan lokal anesteziklerin etki süresi genellikle daha uzundur.",
        "en": "Local anesthetics with higher protein binding generally have a longer duration of action.",
        "importance": "high",
        "tags": ["local_anesthetics", "protein_binding", "duration_of_action"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 121
    {
        "id": "edaic_a_spot_121",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Receptors",
        "tr": "Alfa-1 reseptör aktivasyonu vazokonstriksiyon yapar.",
        "en": "Alpha-1 receptor activation causes vasoconstriction.",
        "importance": "high",
        "tags": ["alpha_1_receptor", "vasoconstriction"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 122
    {
        "id": "edaic_a_spot_122",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Receptors",
        "tr": "Beta-1 reseptör aktivasyonu kalp hızı, kontraktilite ve iletimi artırır.",
        "en": "Beta-1 receptor activation increases heart rate, contractility, and conduction.",
        "importance": "high",
        "tags": ["beta_1_receptor", "inotropy", "chronotropy"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 123
    {
        "id": "edaic_a_spot_123",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Receptors",
        "tr": "Beta-2 reseptör aktivasyonu bronkodilatasyon ve vazodilatasyon yapar.",
        "en": "Beta-2 receptor activation causes bronchodilation and vasodilation.",
        "importance": "high",
        "tags": ["beta_2_receptor", "bronchodilation", "vasodilation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 124
    {
        "id": "edaic_a_spot_124",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Sympathomimetics",
        "tr": "Fenilefrin primer olarak alfa-1 agonisttir ve sistemik vasküler direnci artırır.",
        "en": "Phenylephrine is primarily an alpha-1 agonist and increases systemic vascular resistance.",
        "importance": "high",
        "tags": ["phenylephrine", "alpha_1_agonist", "vasopressor"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 125
    {
        "id": "edaic_a_spot_125",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Sympathomimetics",
        "tr": "Efedrin indirekt ve direkt sempatomimetik etkiler gösterir; taşifilaksi gelişebilir.",
        "en": "Ephedrine has indirect and direct sympathomimetic effects; tachyphylaxis may occur.",
        "importance": "high",
        "tags": ["ephedrine", "tachyphylaxis", "vasopressor"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 126
    {
        "id": "edaic_a_spot_126",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Sympathomimetics",
        "tr": "Noradrenalin güçlü alfa etkisiyle vazokonstriksiyon yapar ve beta-1 etkisi de vardır.",
        "en": "Norepinephrine causes vasoconstriction through strong alpha effects and also has beta-1 activity.",
        "importance": "high",
        "tags": ["norepinephrine", "alpha_agonist", "vasopressor"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 127
    {
        "id": "edaic_a_spot_127",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Sympathomimetics",
        "tr": "Adrenalin düşük dozda beta etkiler, yüksek dozda alfa etkilerle daha baskın hale gelir.",
        "en": "Epinephrine has more prominent beta effects at low doses and more prominent alpha effects at higher doses.",
        "importance": "high",
        "tags": ["epinephrine", "adrenaline", "inodilator"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 128
    {
        "id": "edaic_a_spot_128",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Sympathomimetics",
        "tr": "Dobutamin baskın beta-1 agonist etkisiyle kontraktiliteyi artırır.",
        "en": "Dobutamine increases contractility mainly through beta-1 agonist activity.",
        "importance": "high",
        "tags": ["dobutamine", "beta_1_agonist", "inotrope"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 129
    {
        "id": "edaic_a_spot_129",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Sympathomimetics",
        "tr": "Dopaminin etkileri doza bağlı olarak dopaminerjik, beta ve alfa reseptörlere kayar.",
        "en": "The effects of dopamine shift from dopaminergic to beta and alpha receptor effects depending on dose.",
        "importance": "high",
        "tags": ["dopamine", "dopaminergic_receptors", "inopressor"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 130
    {
        "id": "edaic_a_spot_130",
        "exam": "EDAIC_PAPER_A",
        "topic": "Autonomic Nervous System",
        "subtopic": "Anticholinergics",
        "tr": "Atropin muskarinik reseptör antagonisti olarak vagal tonusu azaltır ve kalp hızını artırır.",
        "en": "Atropine is a muscarinic receptor antagonist that reduces vagal tone and increases heart rate.",
        "importance": "high",
        "tags": ["atropine", "anticholinergic", "bradycardia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 131
    {
        "id": "edaic_a_spot_131",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Depth of Anesthesia",
        "tr": "Glaskow benzeri sedasyon skorları klinikte faydalı olsa da anestezi derinliğini doğrudan ölçmez.",
        "en": "Sedation scores are clinically useful but do not directly measure depth of anesthesia.",
        "importance": "high",
        "tags": ["depth_of_anesthesia", "sedation_scores"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 132
    {
        "id": "edaic_a_spot_132",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Depth of Anesthesia",
        "tr": "BIS, EEG sinyallerinden türetilmiş hipnotik derinlik monitörüdür.",
        "en": "BIS is a hypnotic depth monitor derived from EEG signals.",
        "importance": "high",
        "tags": ["bis", "eeg", "depth_of_anesthesia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 133
    {
        "id": "edaic_a_spot_133",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Depth of Anesthesia",
        "tr": "Kas gevşetici varlığında hareket olmaması yeterli anestezi derinliği anlamına gelmez.",
        "en": "Absence of movement in the presence of neuromuscular blockade does not indicate adequate anesthetic depth.",
        "importance": "high",
        "tags": ["depth_of_anesthesia", "neuromuscular_blockade", "awareness"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 134
    {
        "id": "edaic_a_spot_134",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Neuromuscular Monitoring",
        "tr": "TOF oranının 0.9’un altında olması rezidüel nöromüsküler blok açısından anlamlıdır.",
        "en": "A TOF ratio below 0.9 is significant for residual neuromuscular blockade.",
        "importance": "high",
        "tags": ["tof", "residual_block", "extubation_criteria"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 135
    {
        "id": "edaic_a_spot_135",
        "exam": "EDAIC_PAPER_A",
        "topic": "Monitoring",
        "subtopic": "Neuromuscular Monitoring",
        "tr": "Post-tetanik sayım derin nöromüsküler blok derecesini değerlendirmede kullanılabilir.",
        "en": "Post-tetanic count can be used to assess the degree of deep neuromuscular block.",
        "importance": "high",
        "tags": ["post_tetanic_count", "deep_block", "monitoring"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 136
    {
        "id": "edaic_a_spot_136",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Physiology",
        "tr": "Renal kan akımı kardiyak debinin yaklaşık dörtte birini alır.",
        "en": "Renal blood flow receives approximately one-quarter of cardiac output.",
        "importance": "high",
        "tags": ["renal_blood_flow", "cardiac_output"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 137
    {
        "id": "edaic_a_spot_137",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Function",
        "tr": "Glomerüler filtrasyon hızı renal fonksiyonun temel göstergelerinden biridir.",
        "en": "Glomerular filtration rate is one of the main indicators of renal function.",
        "importance": "high",
        "tags": ["gfr", "renal_function"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 138
    {
        "id": "edaic_a_spot_138",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Renal Function",
        "tr": "Kreatinin kas kitlesinden etkilenir ve akut böbrek hasarında geç yükselebilir.",
        "en": "Creatinine is affected by muscle mass and may rise late in acute kidney injury.",
        "importance": "high",
        "tags": ["creatinine", "muscle_mass", "acute_kidney_injury"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 139
    {
        "id": "edaic_a_spot_139",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Acute Kidney Injury",
        "tr": "Hipovolemi renal perfüzyonu azaltarak prerenal akut böbrek hasarına yol açabilir.",
        "en": "Hypovolemia may reduce renal perfusion and cause prerenal acute kidney injury.",
        "importance": "high",
        "tags": ["prerenal_aki", "renal_perfusion", "hypovolemia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 140
    {
        "id": "edaic_a_spot_140",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Endocrine",
        "tr": "ADH su geri emilimini artırarak idrarı konsantre eder.",
        "en": "ADH increases water reabsorption and concentrates urine.",
        "importance": "high",
        "tags": ["adh", "vasopressin", "urine_concentration"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 141
    {
        "id": "edaic_a_spot_141",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Endocrine",
        "tr": "Aldosteron sodyum geri emilimini ve potasyum atılımını artırır.",
        "en": "Aldosterone increases sodium reabsorption and potassium excretion.",
        "importance": "high",
        "tags": ["aldosterone", "sodium_reabsorption", "potassium_excretion"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 142
    {
        "id": "edaic_a_spot_142",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Acid Base",
        "tr": "Metabolik asidozda primer problem bikarbonat azalmasıdır.",
        "en": "In metabolic acidosis, the primary problem is reduced bicarbonate.",
        "importance": "high",
        "tags": ["metabolic_acidosis", "bicarbonate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 143
    {
        "id": "edaic_a_spot_143",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Acid Base",
        "tr": "Respiratuvar asidozda primer problem PaCO₂ artışıdır.",
        "en": "In respiratory acidosis, the primary problem is increased PaCO₂.",
        "importance": "high",
        "tags": ["respiratory_acidosis", "paco2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 144
    {
        "id": "edaic_a_spot_144",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Acid Base",
        "tr": "Metabolik alkaloz genellikle hidrojen iyonu kaybı veya bikarbonat artışı ile ilişkilidir.",
        "en": "Metabolic alkalosis is usually related to hydrogen ion loss or increased bicarbonate.",
        "importance": "high",
        "tags": ["metabolic_alkalosis", "bicarbonate"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 145
    {
        "id": "edaic_a_spot_145",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Acid Base",
        "tr": "Respiratuvar alkalozda primer problem PaCO₂ azalmasıdır.",
        "en": "In respiratory alkalosis, the primary problem is decreased PaCO₂.",
        "importance": "high",
        "tags": ["respiratory_alkalosis", "paco2"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 146
    {
        "id": "edaic_a_spot_146",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Acid Base",
        "tr": "Laktik asidoz doku hipoperfüzyonu ve anaerobik metabolizma ile ilişkili olabilir.",
        "en": "Lactic acidosis may be associated with tissue hypoperfusion and anaerobic metabolism.",
        "importance": "high",
        "tags": ["lactic_acidosis", "hypoperfusion", "anaerobic_metabolism"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 147
    {
        "id": "edaic_a_spot_147",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Electrolytes",
        "tr": "Hiperkalemi EKG’de sivri T dalgaları, PR uzaması ve QRS genişlemesine neden olabilir.",
        "en": "Hyperkalemia may cause peaked T waves, PR prolongation, and QRS widening on ECG.",
        "importance": "high",
        "tags": ["hyperkalemia", "peaked_t_waves", "cardiac_arrhythmia"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 148
    {
        "id": "edaic_a_spot_148",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Electrolytes",
        "tr": "Hipokalsemi QT aralığını uzatabilir.",
        "en": "Hypocalcemia may prolong the QT interval.",
        "importance": "high",
        "tags": ["hypocalcemia", "qt_prolongation"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 149
    {
        "id": "edaic_a_spot_149",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Electrolytes",
        "tr": "Hipermagnezemi derin tendon reflekslerinde azalma ve solunum depresyonuna yol açabilir.",
        "en": "Hypermagnesemia may cause reduced deep tendon reflexes and respiratory depression.",
        "importance": "high",
        "tags": ["hypermagnesemia", "deep_tendon_reflexes", "respiratory_depression"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    },
    # 150
    {
        "id": "edaic_a_spot_150",
        "exam": "EDAIC_PAPER_A",
        "topic": "Renal & Metabolic",
        "subtopic": "Endocrine",
        "tr": "Hipoglisemi nörolojik hasar yapabilir ve anestezi altında klinik bulguları maskelenebilir.",
        "en": "Hypoglycemia can cause neurological injury and its clinical signs may be masked under anesthesia.",
        "importance": "high",
        "tags": ["hypoglycemia", "neurological_injury", "masking"],
        "sourceType": "board_review",
        "lastReviewed": "2026-05-30"
    }
]

combined = spot_notes + new_spots

# Save updated list
with open(existing_path, "w", encoding="utf-8") as f:
    json.dump(combined, f, ensure_ascii=False, indent=2)

print(f"Successfully combined and wrote {len(combined)} spot notes to {existing_path}")
