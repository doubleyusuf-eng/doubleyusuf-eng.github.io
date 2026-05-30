import json
import re

FINAL_EXPANDED_PATH = "scratch/final_assistant_expanded.json"

TURKISH_CHARS = set("çğıöşüÇĞİÖŞÜ")

# List of common Turkish words and word roots to check for as leaks
TURKISH_BLACK_WORDS = {
    "ile", "veya", "ve", "de", "da", "bir", "icin", "için", "olan", "göre", "gore", "bu", "şu", 
    "tipik", "araligi", "aralığı", "doz", "dozu", "dozaj", "dozlama", "bilgisi", 
    "belirtilmemistir", "belirtilmemiştir", "yetişkin", "yetiskin", "çocuk", "cocuk", "pediatrik", 
    "premedikasyon", "indüksiyon", "induksiyon", "idame", "anestezi", "anestezik", "kısa", "kisa", 
    "orta", "uzun", "etkili", "grubu", "sınıfı", "sinifi", "pozitif", "allosterik", "modülatör", 
    "bloker", "blokör", "blokörü", "agonist", "agonisti", "antagonist", "antagonisti", "reseptör", 
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

def analyze_leaks():
    with open(FINAL_EXPANDED_PATH, "r", encoding="utf-8") as f:
        db = json.load(f)
        
    drug_cards = [x for x in db if x.get("category") == "drug_dosing"]
    print(f"Total drug dosing cards: {len(drug_cards)}")
    
    leaks_found = {}
    
    for item in drug_cards:
        card_id = item["id"]
        en_block = item.get("en", {})
        
        # We check the entire English block content serialized as text
        en_text = json.dumps(en_block, ensure_ascii=False)
        
        # 1. Check for literal Turkish characters
        found_chars = [c for c in en_text if c in TURKISH_CHARS]
        
        # 2. Check for blacklist words
        # Split by non-alphabetic boundaries
        words = re.findall(r'[a-zA-ZğüşöçıİĞÜŞÖÇ]+', en_text.lower())
        found_black_words = [w for w in words if w in TURKISH_BLACK_WORDS]
        
        # Filter false positives like standard English words that might share lowercase strings (e.g. "de", "da" in specific contexts, but better be safe)
        # Sepsis, arrest, local, block can be English. Let's adjust if they are false positives in English.
        english_ok = {"local", "block", "ven", "arter", "oksit", "phosphates", "sulfate", "chlorides", "calcium", "sodium", "infusion", "agonist", "antagonist", "receptor", "systemic", "toxic", "apnea", "acidosis", "alkalosis", "hypotension", "hypertension", "bradycardia", "tachycardia", "arrhythmia", "dysrhythmia", "arrest", "resuscitation", "bolus", "sepsis"}
        found_black_words = [w for w in found_black_words if w not in english_ok]
        
        if found_chars or found_black_words:
            leaks_found[card_id] = {
                "chars": list(set(found_chars)),
                "black_words": list(set(found_black_words))
            }
            
    print(f"\nFound leaks in {len(leaks_found)} cards:")
    for cid, leak in leaks_found.items():
        print(f"Card ID: {cid}")
        if leak["chars"]:
            print(f"  Turkish characters: {leak['chars']}")
        if leak["black_words"]:
            print(f"  Blacklist words: {leak['black_words']}")

if __name__ == "__main__":
    analyze_leaks()
