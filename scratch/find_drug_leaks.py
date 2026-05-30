import json
import re

def scan_drug_leaks():
    with open("app/src/main/assets/local_qa_database.json", "r", encoding="utf-8") as f:
        db = json.load(f)
        
    drugs = [x for x in db if x["category"] == "drug_dosing"]
    print(f"Total drug dosing cards: {len(drugs)}")
    
    turkish_char_regex = re.compile(r'[çğıöşüÇĞİÖŞÜ]')
    
    leaky_drugs = []
    for d in drugs:
        en_block = json.dumps(d["en"], ensure_ascii=False)
        
        leaks = []
        # Check Turkish characters in English block
        # (excluding standard Turkish names or properties if any, but since the entire 'en' object is English, it should have 0 Turkish characters!)
        found_chars = turkish_char_regex.findall(en_block)
        if found_chars:
            leaks.append(f"Turkish characters found: {set(found_chars)}")
            
        # Check common Turkish words
        tr_words = ["reseptör", "antagonisti", "dozu", "doz", "anestezi", "veya", "hastada", "ve", "ile", "göre", "anestezik", "gevşetici", "bloke edici", "agonist", "antagonist"]
        # Wait, "agonist" and "antagonist" are English too! But "antagonisti", "agonisti", "reseptör" are Turkish.
        tr_words_strict = ["reseptör", "antagonisti", "agonisti", "dozu", "gevşetici", "anestezik", "veya", "hastada"]
        for w in tr_words_strict:
            if w in en_block.lower():
                leaks.append(f"Strict Turkish word found: '{w}'")
                
        # Check placeholders like "not specified" or "not defined"
        if "not specified" in en_block.lower() or "not defined" in en_block.lower():
            leaks.append("Weak placeholder 'not specified' or 'not defined' found")
            
        if leaks:
            leaky_drugs.append({
                "id": d["id"],
                "leaks": leaks,
                "class_en": d["en"].get("conversationalText", "")
            })
            
    print(f"Found {len(leaky_drugs)} leaky/placeholder drug cards:")
    for ld in leaky_drugs:
        print(f"ID: {ld['id']}")
        for l in ld["leaks"]:
            print(f"  - {l}")
        # Print class/conversationalText snippet
        lines = ld["class_en"].split("\n")
        class_line = [x for x in lines if "class:" in x.lower() or "sınıfı:" in x.lower()]
        if class_line:
            print(f"  Class Line: {class_line[0]}")
        print("-" * 40)

if __name__ == "__main__":
    scan_drug_leaks()
