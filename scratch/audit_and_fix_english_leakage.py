import json
import re

def audit_and_fix_english_leakage():
    db_path = "app/src/main/assets/local_qa_database.json"
    with open(db_path, "r", encoding="utf-8") as f:
        db = json.load(f)
        
    replacements = {
        "Induksiyon": "Induction",
        "Indüksiyon": "Induction",
        "Kısa etkili genel anestezik": "Short-acting general anesthetic",
        "GABA-A Agonisti": "GABA-A agonist",
        "GABA-A Agonistı": "GABA-A agonist",
        "Yetişkin dozu belirtilmemiş": "Adult dose not specified",
        "Yetişkin Dozu:": "Adult Dosing:",
        "Pediatrik Dozu:": "Pediatric Dosing:",
        "Sınıfı:": "Class:",
        "Kas gevşetici": "Neuromuscular blocker",
        "depolarizan": "depolarizing",
        "depolarizan olmayan": "nondepolarizing",
        "Depolarizan olmayan": "Nondepolarizing",
        "Depolarizan": "Depolarizing",
    }
    
    modified_count = 0
    for item in db:
        if "en" in item and "conversationalText" in item["en"]:
            text = item["en"]["conversationalText"]
            original = text
            for tr_word, en_word in replacements.items():
                if tr_word in text:
                    text = text.replace(tr_word, en_word)
            
            if text != original:
                item["en"]["conversationalText"] = text
                modified_count += 1
                print(f"Fixed leakage in item '{item['id']}':")
                print(f"  Before: {repr(original[:120])}")
                print(f"  After:  {repr(text[:120])}")
                
    if modified_count > 0:
        with open(db_path, "w", encoding="utf-8") as f:
            json.dump(db, f, indent=2, ensure_ascii=False)
            
    print(f"Done. Fixed English text leakage in {modified_count} items.")

if __name__ == "__main__":
    audit_and_fix_english_leakage()
