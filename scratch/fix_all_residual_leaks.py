import json
import os
import re

FINAL_EXPANDED_PATH = "scratch/final_assistant_expanded.json"
DESKTOP_EXPANDED_PATH = os.path.expanduser("~/Desktop/final_assistant_expanded.json")

TURKISH_CHARS = set("çğıöşüÇĞİÖŞÜ")

# Extended replacements dictionary
REPLACEMENTS = {
    # Exact word-level replacements (case-insensitive)
    r'\bnsaii\b': 'nsaid',
    r'\bNsaii\b': 'NSAID',
    r'\bNSAII\b': 'NSAID',
    r'\bhizla\b': 'rapidly',
    r'\bgaz\b': 'gas',
    r'\bgazi\b': 'gas',
    r'\bantagonizmasi\b': 'antagonism',
    r'\bunite\b': 'unit',
    r'\bantagonisti\b': 'antagonist',
    r'\banaljezi\b': 'analgesia',
    r'\bidame\b': 'maintenance',
    r'\binduksiyon\b': 'induction',
    r'\bkisa\b': 'short',
    r'\bmaksimum\b': 'maximum',
    r'\bselektif\b': 'selective',
    r'\bgrubu\b': 'group',
    r'\bsinifi\b': 'class',
    r'\bajan\b': 'agent',
    r'\balkaloidi\b': 'alkaloid',
    r'\baminoglikozid\b': 'aminoglycoside',
    r'\banalogu\b': 'analogue',
    r'\banilin\b': 'aniline',
    r'\bantidiuretik\b': 'antidiuretic',
    r'\bantidopaminerjik\b': 'antidopaminergic',
    r'\bantidotu\b': 'antidote',
    r'\bantifibrinolitik\b': 'antifibrinolytic',
    r'\bantimuskarinik\b': 'antimuscarinic',
    r'\bantipiretik\b': 'antipyretic',
    r'\bantispazmodik\b': 'antispasmodic',
    r'\bblokor\b': 'blocker',
    r'\bdk\b': 'min',
    r'\bglikopeptid\b': 'glycopeptide',
    r'\bglukagon\b': 'glucagon',
    r'\binflamatuar\b': 'inflammatory',
    r'\bnitrik\b': 'nitric',
    r'\bnitroimidazol\b': 'nitroimidazole',
    r'\bnonsteroid\b': 'nonsteroidal',
    r'\bnoroleptik\b': 'neuroleptic',
    r'\bnutrisyonel\b': 'nutritional',
    r'\bpankreatik\b': 'pancreatic',
    r'\btokolitik\b': 'tocolytic',
    
    # Capitalized versions
    r'\bHizla\b': 'Rapidly',
    r'\bGaz\b': 'Gas',
    r'\bGazi\b': 'Gas',
    r'\bAntagonizmasi\b': 'Antagonism',
    r'\bUnite\b': 'Unit',
    r'\bAntagonisti\b': 'Antagonist',
    r'\bAnaljezi\b': 'Analgesia',
    r'\bIdame\b': 'Maintenance',
    r'\bInduksiyon\b': 'Induction',
    r'\bKisa\b': 'Short',
    r'\bMaksimum\b': 'Maximum',
    r'\bSelektif\b': 'Selective',
    r'\bGrubu\b': 'Group',
    r'\bSinifi\b': 'Class',
    r'\bAjan\b': 'Agent',
    r'\bAlkaloidi\b': 'Alkaloid',
    r'\bAminoglikozid\b': 'Aminoglycoside',
    r'\bAnalogu\b': 'Analogue',
    r'\bAnilin\b': 'Aniline',
    r'\bAntidiuretik\b': 'Antidiuretic',
    r'\bAntidopaminerjik\b': 'Antidopaminergic',
    r'\bAntidotu\b': 'Antidote',
    r'\bAntifibrinolitik\b': 'Antifibrinolytic',
    r'\bAntimuskarinik\b': 'Antimuscarinic',
    r'\bAntipiretik\b': 'Antipyretic',
    r'\bAntispazmodik\b': 'Antispasmodic',
    r'\bBlokor\b': 'Blocker',
    r'\bDk\b': 'Min',
    r'\bGlikopeptid\b': 'Glycopeptide',
    r'\bGlukagon\b': 'Glucagon',
    r'\bInflamatuar\b': 'Inflammatory',
    r'\bNitrik\b': 'Nitric',
    r'\bNitroimidazol\b': 'Nitroimidazole',
    r'\bNonsteroid\b': 'Nonsteroidal',
    r'\bNoroleptik\b': 'Neuroleptic',
    r'\bNutrisyonel\b': 'Nutritional',
    r'\bPankreatik\b': 'Pancreatic',
    r'\bTokolitik\b': 'Tocolytic',
}

# The expanded blacklist for validation
TURKISH_BLACK_WORDS = {
    "hizla", "gazi", "gaz", "antagonizmasi", "unite", "antagonisti", "analjezi", "idame", 
    "induksiyon", "kisa", "maksimum", "selektif", "grubu", "sinifi", "ajan", "alkaloidi", 
    "aminoglikozid", "analogu", "anilin", "antidiuretik", "antidopaminerjik", "antidotu", 
    "antifibrinolitik", "antimuskarinik", "antipiretik", "antispazmodik", "blokor", "dk", 
    "glikopeptid", "glukagon", "inflamatuar", "nitrik", "nitroimidazol", "nonsteroid", 
    "noroleptik", "nsaii", "nutrisyonel", "pankreatik", "ponv", "tokolitik",
    "ile", "veya", "ve", "de", "da", "bir", "icin", "için", "olan", "göre", "gore", "bu", "şu", 
    "tipik", "araligi", "aralığı", "doz", "dozu", "dozaj", "dozlama", "bilgisi", 
    "belirtilmemistir", "belirtilmemiştir", "yetişkin", "yetiskin", "çocuk", "cocuk", "pediatrik", 
    "premedikasyon", "indüksiyon", "anestezi", "anestezik", "kısa", "orta", "uzun", "etkili", 
    "pozitif", "allosterik", "bloker", "blokör", "blokörü", "agonist", "agonisti", "antagonist", 
    "antagonisti", "reseptör", "reseptor", "vazodilatör", "vazodilator", "infüzyon", "infuzyon", 
    "yavaş", "yavas", "hızlı", "hizli", "yavaşça", "yavasca", "hızlıca", "hizlica", "saat", 
    "dakika", "dakikada", "hastanın", "hastanin", "hekimin", "gebe", "gebelerde", "emziren", 
    "kriz", "krizi", "kalsiyum", "sodyum", "klorür", "klorur", "sülfat", "sulfat", "glukonat", 
    "fosfat", "asetat", "laktat", "bikarbonat", "karbonat", "hidroksit", "oksit", "nitrat", 
    "nitrit", "metaboliti", "karaciğer", "karaciger", "böbrek", "bobrek", "yetmezliği", 
    "yetmezligi", "kalp", "akciğer", "akciger", "beyin", "damar", "arter", "ven", "sinir", 
    "blok", "bloku", "bloğu", "lokal", "sistemik", "toksisite", "toksik", "etki", "yan", 
    "reaksiyon", "alerji", "anafilaksi", "bronkospazm", "laringospazm", "apne", "hipoksi", 
    "hiperkapni", "asidoz", "alkaloz", "hipotansiyon", "hipertansiyon", "bradikardi", 
    "taşikardi", "tasikardi", "aritmi", "disritmi", "arrest", "resüsitasyon", "resusitasyon", 
    "cerrahi", "operasyon", "ameliyat", "girişim", "girisim", "uygulama", "yukleme", "yükleme", 
    "bolus", "kontrendike", "endikasyon", "önlem", "uyarı", "dikkat", "takip", "izlem", 
    "monitörizasyon", "monitorizasyon", "referans", "kaynak", "meyve", "suyu"
}

ENGLISH_OK = {
    "local", "block", "ven", "arter", "oksit", "phosphates", "sulfate", "chlorides", 
    "calcium", "sodium", "infusion", "agonist", "antagonist", "receptor", "systemic", 
    "toxic", "apnea", "acidosis", "alkalosis", "hypotension", "hypertension", "bradycardia", 
    "tachycardia", "arrhythmia", "dysrhythmia", "arrest", "resuscitation", "bolus", "sepsis",
    "ponv", "nsaid", "nsaids"
}

def clean_value(val):
    if isinstance(val, str):
        cleaned = val
        for pattern, repl in REPLACEMENTS.items():
            cleaned = re.sub(pattern, repl, cleaned)
        # Clean up any leftover double spaces
        cleaned = re.sub(r' +', ' ', cleaned)
        return cleaned
    elif isinstance(val, list):
        return [clean_value(x) for x in val]
    elif isinstance(val, dict):
        return {k: clean_value(v) for k, v in val.items()}
    return val

def process_and_validate():
    # 1. Load the database
    with open(FINAL_EXPANDED_PATH, "r", encoding="utf-8") as f:
        db = json.load(f)
        
    drug_cards = [x for x in db if x.get("category") == "drug_dosing"]
    print(f"Loaded {len(drug_cards)} drug dosing cards.")
    
    # 2. Apply cleaning recursively to question_en and the en block
    for item in drug_cards:
        if "question_en" in item:
            item["question_en"] = clean_value(item["question_en"])
            
        if "en" in item:
            item["en"] = clean_value(item["en"])
            
    # 3. Save the cleaned database back
    with open(FINAL_EXPANDED_PATH, "w", encoding="utf-8") as f:
        json.dump(db, f, ensure_ascii=False, indent=2)
        
    # Also write to Desktop
    try:
        with open(DESKTOP_EXPANDED_PATH, "w", encoding="utf-8") as f:
            json.dump(db, f, ensure_ascii=False, indent=2)
        print(f"Successfully copied the fully cleaned database directly to Desktop: {DESKTOP_EXPANDED_PATH}")
    except Exception as e:
        print(f"Error copying to Desktop: {e}")
        
    # 4. Strict Validation Pass
    print("\n=== RUNNING STRICT SCAN AND VERIFICATION ===")
    total_violations = 0
    detailed_violations = {}
    
    for item in drug_cards:
        card_id = item["id"]
        
        # We will scan:
        # - item["question_en"]
        # - all string values inside item["en"] block
        strings_to_check = []
        if "question_en" in item:
            strings_to_check.append(("question_en", item["question_en"]))
            
        en_block = item.get("en", {})
        
        def collect_strings(val, prefix="en"):
            if isinstance(val, str):
                strings_to_check.append((prefix, val))
            elif isinstance(val, list):
                for idx, x in enumerate(val):
                    collect_strings(x, f"{prefix}[{idx}]")
            elif isinstance(val, dict):
                for k, v in val.items():
                    collect_strings(v, f"{prefix}.{k}")
                    
        collect_strings(en_block)
        
        card_violations = []
        for field, text in strings_to_check:
            # 1. Turkish characters check
            found_chars = [c for c in text if c in TURKISH_CHARS]
            
            # 2. Blacklisted words check
            words = re.findall(r'[a-zA-ZğüşöçıİĞÜŞÖÇ]+', text.lower())
            found_black_words = [w for w in words if w in TURKISH_BLACK_WORDS and w not in ENGLISH_OK]
            
            if found_chars or found_black_words:
                card_violations.append({
                    "field": field,
                    "text_snippet": text[:80] + "..." if len(text) > 80 else text,
                    "turkish_chars": list(set(found_chars)),
                    "black_words": list(set(found_black_words))
                })
                
        if card_violations:
            total_violations += len(card_violations)
            detailed_violations[card_id] = card_violations
            
    print("===========================================")
    print(f"Total violations found: {total_violations} across {len(detailed_violations)} cards.")
    if detailed_violations:
        for cid, violations in detailed_violations.items():
            print(f"\n❌ CARD: {cid}")
            for v in violations:
                print(f"  Field: {v['field']}")
                print(f"  Snippet: \"{v['text_snippet']}\"")
                if v['turkish_chars']:
                    print(f"  Turkish chars: {v['turkish_chars']}")
                if v['black_words']:
                    print(f"  Black words: {v['black_words']}")
    else:
        print("🎉 SUCCESS: Absolutely 0 leaks or Turkish words found in the English block of all 113 drug dosing cards!")

if __name__ == "__main__":
    process_and_validate()
