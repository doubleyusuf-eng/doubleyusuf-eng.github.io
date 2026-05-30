import json
import re

turkish_letters = re.compile(r'[çğıöşüÇĞİÖŞÜ]')

def scan():
    with open("app/src/main/assets/local_qa_database.json", "r", encoding="utf-8") as f:
        db = json.load(f)
        
    base_items = [x for x in db if x["category"] in ["clinical_scenarios", "drug_dosing"]]
    
    suspicious = []
    for item in base_items:
        q_en = item.get("question_en", "")
        q_tr = item.get("question_tr", "")
        item_id = item["id"]
        is_drug = item["category"] == "drug_dosing"
        
        # Criteria for suspicious EN question:
        # 1. Contains Turkish letters
        # 2. Contains common Turkish words (case-insensitive)
        # 3. Equals question_tr and is not a drug name (and has no spaces, or is a clinical term that is clearly Turkish)
        # 4. Is very short or seems wrong
        
        reasons = []
        if turkish_letters.search(q_en):
            reasons.append("Turkish letters")
            
        tr_words = ["nedir", "nelerdir", "nasil", "nasıl", "göre", "veya", "ile", "dozu", "anestezi", "hastada", "tedavisi", "oranı", "değerleri", "yönetimi", "basıncı", "embolisi", "darlığı", "yetmezliği", "krizi", "doz", "akciğer", "pnömotoraks", "üst", "solunum", "yolu", "enfeksiyonu", "çocuk", "bebek", "burun", "akıntısı"]
        if any(w in q_en.lower() for w in tr_words):
            reasons.append("Turkish words detected")
            
        if q_en == q_tr and not is_drug:
            # Check if it looks like Turkish or is identical without being a standard medical term
            reasons.append("EN equals TR (non-drug)")
            
        # Specific known low-quality English titles
        low_quality_terms = ["as anestezi", "ms anestezi", "kolinerjik kriz", "hellp anestezi", "ebus anestezi", "ventile edemiyorum", "lokal anestezik"]
        if any(t in q_en.lower() for t in low_quality_terms):
            reasons.append("Known low quality term")
            
        if reasons:
            suspicious.append({
                "id": item_id,
                "category": item["category"],
                "tr": q_tr,
                "en": q_en,
                "reasons": reasons
            })
            
    print(f"Found {len(suspicious)} suspicious base items:")
    for s in suspicious:
        print(f"ID: {s['id']} | Category: {s['category']}")
        print(f"  TR: {s['tr']}")
        print(f"  EN: {s['en']} (Reasons: {', '.join(s['reasons'])})")
        print("-" * 40)

if __name__ == "__main__":
    scan()
