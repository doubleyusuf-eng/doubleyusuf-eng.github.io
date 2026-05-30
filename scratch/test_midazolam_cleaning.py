import json
import re

DRUG_CLASS_TR_TO_EN = {
    "Kısa etkili Benzodiazepin (GABA-A Pozitif Allosterik Modülatör)": "Short-acting Benzodiazepine (GABA-A Positive Allosteric Modulator)",
    "Kisa etkili Benzodiazepin (GABA-A Pozitif Allosterik Modülatör)": "Short-acting Benzodiazepine (GABA-A Positive Allosteric Modulator)",
}

def clean_english_leaks(text):
    if not text:
        return ""
    t = text
    t = t.replace("anestezik", "anesthetic").replace("Anestezik", "Anesthetic")
    t = t.replace("Dissosiyatif", "Dissociative").replace("dissosiyatif", "dissociative")
    t = t.replace("gevşetici", "blocker").replace("gevsetici", "blocker")
    t = t.replace("entubasyon", "intubation").replace("entübasyon", "intubation")
    t = t.replace("indüksiyon", "induction").replace("induksiyon", "induction")
    t = t.replace("veya", "or")
    t = t.replace("doz", "dose").replace("Doz", "Dose")
    t = t.replace("hastanın", "patient's").replace("hekimin", "physician's")
    t = t.replace("gebe", "pregnant").replace("Gebe", "Pregnant")
    t = t.replace("dozu", "dose").replace("Dozu", "Dose")
    t = t.replace("Antagonisti", "Antagonist").replace("antagonisti", "antagonist")
    t = t.replace("Agonisti", "Agonist").replace("agonisti", "agonist")
    t = t.replace("Reseptör", "Receptor").replace("reseptör", "receptor")
    t = t.replace("Blokörü", "Blocker").replace("blokörü", "blocker").replace("blokorü", "blocker")
    return t

def strip_turkish_characters_en(text):
    if not text:
        return ""
    char_map = {
        'ı': 'i', 'ş': 's', 'ğ': 'g', 'ö': 'o', 'ü': 'u', 'ç': 'c',
        'İ': 'I', 'Ş': 'S', 'Ğ': 'G', 'Ö': 'O', 'Ü': 'U', 'Ç': 'C'
    }
    for tr, en in char_map.items():
        text = text.replace(tr, en)
    return text

def deep_clean_ascii_turkish_leaks_en(text):
    if not text:
        return ""
    t = text
    
    t = t.replace("Premedikasyon:", "Premedication:")
    t = t.replace("premedikasyon:", "premedication:")
    t = t.replace("Premedikasyon", "Premedication")
    t = t.replace("premedikasyon", "premedication")
    t = t.replace("meyve suyu ile", "with fruit juice")
    t = t.replace("meyve suyu", "fruit juice")
    t = t.replace(" veya ", " or ")
    t = t.replace("yavas", "slow").replace("Yavas", "Slow")
    t = t.replace("hizla", "rapidly").replace("Hizla", "Rapidly")
    t = t.replace("dozaj", "dosage").replace("Dozaj", "Dosage")
    t = t.replace("dozu", "dose").replace("Dozu", "Dose")
    t = t.replace("Maksimum", "Maximum").replace("maksimum", "maximum")
    
    replacements = {
        "Kisa": "Short", "kisa": "short",
        "Selektif": "Selective", "selektif": "selective",
        "Lokal": "Local", "lokal": "local",
        "Anestezik": "Anesthetic", "anestezik": "anesthetic",
        "Reseptor": "Receptor", "reseptor": "receptor",
        "Antagonisti": "Antagonist", "antagonisti": "antagonist",
        "Agonisti": "Agonist", "agonisti": "agonist",
        "Blokoru": "Blocker", "blokoru": "blocker",
        "Blokor": "Blocker", "blokor": "blocker",
        "Kalsiyum": "Calcium", "kalsiyum": "calcium",
        "Tuzu": "Salt", "tuzu": "salt",
        "Destegi": "Support", "destegi": "support",
        "Sinif": "Class", "sinif": "class",
        "Urunu": "Product", "urunu": "product",
        "Derivesi": "Derivative", "derivesi": "derivative",
        "Kan": "Blood", "kan": "blood",
        "Faktor": "Factor", "faktor": "factor",
        "Koagulasyon": "Coagulation", "koagulasyon": "coagulasyon",
        "Konsantresi": "Concentrate", "konsantresi": "concentrate",
        "Pihtilasma": "Coagulation", "pihtilasma": "coagulation",
        "Faktoru": "Factor", "faktoru": "factor",
        "Kofaktoru": "Cofactor", "kofaktoru": "cofactor",
        "Uterotonik": "Uterotonic", "uterotonik": "uterotonik",
        "Analogu": "Analog", "analogu": "analog",
        "Hormon": "Hormone", "hormon": "hormone",
        "Karbonhidrat": "Carbohydrate", "karbonhidrat": "karbonhidrat",
        "Solusyonu": "Solution", "solusyonu": "solution",
        "Ozmotik": "Osmotic", "ozmotik": "osmotic",
        "Diuretigi": "Diuretic", "diuretigi": "diuretic",
        "Kivrim": "Loop", "kivrim": "loop",
        "Sentetik": "Synthetic", "sentetik": "synthetic",
        "Guclu": "Strong", "guclu": "strong",
        "Pozitif": "Positive", "pozitif": "positive",
        "Allosterik": "Allosteric", "allosterik": "allosterik",
        "Modulator": "Modulator", "modulator": "modulator",
        "Reversal": "Reversal", "reversal": "reversal",
        "Antidotu": "Antidote", "antidotu": "antidote",
        "Nutrisyonel": "Nutritional", "nutrisyonel": "nutrisyonel",
        "Lipid": "Lipid", "lipid": "lipid",
        "Reversibl": "Reversible", "reversibl": "reversible",
        "Asetilkolinesteraz": "Acetylcholinesterase",
        "Inhibitoru": "Inhibitor", "inhibitoru": "inhibitor",
        "Siklodekstrin": "Cyclodextrin", "siklodekstrin": "siklodekstrin",
        "Sempatomimetik": "Sympathomimetic", "sempatomimetik": "sympathomimetic",
        "Katabolik": "Catabolic", "katabolik": "catabolic",
        "Vazopresor": "Vasopressor", "vazopresor": "vasopressor",
        "Inotropik": "Inotropic", "inotropik": "inotropic",
        "Dihidropiridin": "Dihydropyridine", "dihidropiridin": "dihidropiridin",
        "Sefalosporin": "Cephalosporin", "sefalosporin": "cephalosporin",
        "Antibiyotik": "Antibiotic", "antibiyotik": "antibiotic",
        "Antiprotozoal": "Antiprotozoal", "antiprotozoal": "antiprotozoal",
        "Antipiretik": "Antipyretic", "antipiretik": "antipyretic",
        "Antispazmodik": "Antispasmodic", "antispazmodik": "antispasmodic",
        "Aminopenisilin": "Aminopenicillin", "aminopenisilin": "aminopenicillin",
        "Beta-Laktamaz": "Beta-Lactamase", "beta-laktamaz": "beta-lactamase",
        "Glikopeptid": "Glycopeptide", "glikopeptid": "glycopeptide",
        "Lincosamide": "Lincosamide", "lincosamide": "lincosamide",
        "Nitroimidazol": "Nitroimidazole", "nitroimidazol": "nitroimidazole",
        "Ester": "Ester", "ester": "ester",
        "Amid": "Amide", "amid": "amide",
        "Amid-Ester": "Amide-Ester", "amid-ester": "amide-ester",
        "Depolarizing": "Depolarizing", "depolarizing": "depolarizing",
        "Nondepolarizing": "Non-depolarizing", "nondepolarizing": "non-depolarizing",
        "Benzilizokinolin": "Benzylisoquinoline", "benzilizokinolin": "benzylisoquinoline",
        "Antihistaminik": "Antihistaminic", "antihistaminik": "antihistaminic",
        "Antiarritmik": "Antiarrhythmic", "antiarritmik": "antiarrhythmic",
        "Antiaritmik": "Antiarrhythmic", "antiaritmik": "antiarrhythmic",
        "Adrenerjik": "Adrenergic", "adrenerjik": "adrenergic",
        "Vazodilator": "Vasodilator", "vazodilator": "vasodilator",
        "Alkalizan": "Alkalinizing", "alkalizan": "alkalizan",
        "Inorganik": "Inorganic", "inorganik": "inorganic",
        "Inhaler": "Inhalational", "inhaler": "inhalational",
        "Inhalasyon": "Inhalational", "inhalasyon": "inhalational",
        "Gaz": "Gas", "gaz": "gas",
        "Gazi": "Gas", "gazi": "gas",
        "Benzodiazepin": "Benzodiazepine", "benzodiazepin": "benzodiazepine",
        "etkili": "acting", "Etkili": "Acting",
        "grubu": "group", "Grubu": "Group",
        "seçici": "selective", "secici": "selective",
        "Seçici": "Selective", "Secici": "Selective",
        "ve": "and"
    }
    
    for tr, en in replacements.items():
        t = re.sub(r'\b' + tr + r'\b', en, t)
        
    return t

def test():
    with open("app/src/main/assets/local_qa_database.json", "r", encoding="utf-8") as f:
        db = json.load(f)
        
    mid = [x for x in db if x["id"] == "drug_dose_midazolam"][0]
    print("Original Midazolam TR:", mid["tr"]["conversationalText"])
    print("Original Midazolam EN:", mid["en"]["conversationalText"])
    
    cleaned_en = mid["en"]["conversationalText"]
    cleaned_en = clean_english_leaks(cleaned_en)
    
    # Apply Class mappings
    for tr, en in DRUG_CLASS_TR_TO_EN.items():
        cleaned_en = cleaned_en.replace(tr, en)
        
    cleaned_en = deep_clean_ascii_turkish_leaks_en(cleaned_en)
    cleaned_en = strip_turkish_characters_en(cleaned_en)
    
    print("-" * 40)
    print("Cleaned Midazolam EN:\n", cleaned_en)

if __name__ == "__main__":
    test()
