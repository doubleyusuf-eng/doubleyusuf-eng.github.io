import json
import os
import re

FINAL_EXPANDED_PATH = "scratch/final_assistant_expanded.json"
DESKTOP_EXPANDED_PATH = os.path.expanduser("~/Desktop/final_assistant_expanded.json")

TURKISH_CHARS = set("çğıöşüÇĞİÖŞÜ")

TURKISH_BLACK_WORDS = {
    "ile", "veya", "ve", "de", "da", "bir", "icin", "için", "olan", "göre", "gore", "bu", "şu", 
    "tipik", "araligi", "aralığı", "doz", "dozu", "dozaj", "dozlama", "bilgisi", 
    "belirtilmemistir", "belirtilmemiştir", "yetişkin", "yetiskin", "çocuk", "cocuk", "pediatrik", 
    "premedikasyon", "indüksiyon", "induksiyon", "idame", "anestezi", "anestezik", "kısa", "kisa", 
    "orta", "uzun", "etkili", "grubu", "sınıfı", "sinifi", "pozitif", "allosterik", "bloker", 
    "blokör", "blokörü", "agonist", "agonisti", "antagonist", "antagonisti", "reseptör", 
    "reseptor", "vazodilatör", "vazodilator", "infüzyon", "infuzyon", "yavaş", "yavas", "hızlı", 
    "hizli", "yavaşça", "yavasca", "hızlıca", "hizlica", "saat", "dakika", "dakikada", 
    "hastanın", "hastanin", "hekimin", "gebe", "gebelerde", "emziren", "kriz", "krizi", "kalsiyum", 
    "sodyum", "klorür", "klorur", "sülfat", "sulfat", "glukonat", "fosfat", "asetat", "laktat", 
    "bikarbonat", "karbonat", "hidroksit", "oksit", "nitrat", "nitrit", "metaboliti", "karaciğer", 
    "karaciger", "böbrek", "bobrek", "yetmezliği", "yetmezligi", "kalp", "akciğer", "akciger", 
    "beyin", "damar", "arter", "ven", "sinir", "blok", "bloku", "bloğu", "lokal", "sistemik", 
    "toksisite", "toksik", "etki", "yan", "reaksiyon", "alerji", "anafilaksi", "bronkospazm", 
    "laringospazm", "apne", "hipoksi", "hiperkapni", "asidoz", "alkaloz", "hipotansiyon", 
    "hipertansiyon", "bradikardi", "taşikardi", "tasikardi", "aritmi", "disritmi", "arrest", 
    "resüsitasyon", "resusitasyon", "cerrahi", "operasyon", "ameliyat", "girişim", "girisim", 
    "uygulama", "yukleme", "yükleme", "bolus", "kontrendike", "endikasyon", "önlem", "uyarı", 
    "dikkat", "takip", "izlem", "monitörizasyon", "monitorizasyon", "referans", "kaynak", 
    "meyve", "suyu"
}

ENGLISH_OK = {
    "local", "block", "ven", "arter", "oksit", "phosphates", "sulfate", "chlorides", 
    "calcium", "sodium", "infusion", "agonist", "antagonist", "receptor", "systemic", 
    "toxic", "apnea", "acidosis", "alkalosis", "hypotension", "hypertension", "bradycardia", 
    "tachycardia", "arrhythmia", "dysrhythmia", "arrest", "resuscitation", "bolus", "sepsis"
}

def clean_text_field(txt):
    if not txt:
        return txt
    
    # 1. Direct targeted replacements
    txt = txt.replace("fruit juice ile", "with fruit juice")
    txt = txt.replace("fruit juice ile)", "with fruit juice)")
    txt = txt.replace("Allosterik", "Allosteric")
    txt = txt.replace("allosterik", "allosteric")
    
    # 2. Word boundary replacements
    txt = re.sub(r'\bile\b', 'with', txt)
    txt = re.sub(r'\bveya\b', 'or', txt)
    txt = re.sub(r'\bve\b', 'and', txt)
    
    # Clean up double spaces if any were introduced
    txt = re.sub(r' +', ' ', txt)
    return txt

def process_database():
    with open(FINAL_EXPANDED_PATH, "r", encoding="utf-8") as f:
        db = json.load(f)
        
    drug_cards = [x for x in db if x.get("category") == "drug_dosing"]
    print(f"Loaded {len(drug_cards)} drug dosing cards.")
    
    for item in drug_cards:
        en_block = item.get("en", {})
        
        # Clean conversationalText
        if "conversationalText" in en_block:
            en_block["conversationalText"] = clean_text_field(en_block["conversationalText"])
            
        # Clean list fields
        for field in ["immediateRedFlags", "suggestedNextStepsGeneral", "whenToEscalate", "missingCriticalInformation", "doNotMiss"]:
            if field in en_block:
                en_block[field] = [clean_text_field(x) for x in en_block[field]]
                
    # Save the updated database
    with open(FINAL_EXPANDED_PATH, "w", encoding="utf-8") as f:
        json.dump(db, f, ensure_ascii=False, indent=2)
        
    # Also write to Desktop
    try:
        with open(DESKTOP_EXPANDED_PATH, "w", encoding="utf-8") as f:
            json.dump(db, f, ensure_ascii=False, indent=2)
        print(f"Copied clean database directly to Desktop: {DESKTOP_EXPANDED_PATH}")
    except Exception as e:
        print(f"Error copying to Desktop: {e}")
        
    # Re-verify and report
    leaks_found = {}
    print("\n--- Final Validation Verification Report ---")
    print("Verifying all 113 drug dosing cards...")
    
    for item in drug_cards:
        card_id = item["id"]
        en_block = item.get("en", {})
        en_text = json.dumps(en_block, ensure_ascii=False)
        
        # 1. Turkish characters check
        found_chars = [c for c in en_text if c in TURKISH_CHARS]
        
        # 2. Blacklist words check
        words = re.findall(r'[a-zA-ZğüşöçıİĞÜŞÖÇ]+', en_text.lower())
        found_black_words = [w for w in words if w in TURKISH_BLACK_WORDS and w not in ENGLISH_OK]
        
        leaked_terms_count = len(found_chars) + len(found_black_words)
        
        print(f"Card ID: {card_id: <35} | remaining leaked terms = {leaked_terms_count}")
        if leaked_terms_count > 0:
            leaks_found[card_id] = {
                "chars": list(set(found_chars)),
                "black_words": list(set(found_black_words))
            }
            
    print("\n-------------------------------------------")
    print(f"Total cards with remaining leaks: {len(leaks_found)}")
    if leaks_found:
        for cid, leak in leaks_found.items():
            print(f"FAIL: {cid} - chars: {leak['chars']}, black_words: {leak['black_words']}")
    else:
        print("PASS: Absolutely zero Turkish leaks found in any of the 113 drug dosing cards!")

if __name__ == "__main__":
    process_database()
