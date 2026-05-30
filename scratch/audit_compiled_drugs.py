import json

def verify():
    with open("scratch/final_assistant_expanded.json", "r", encoding="utf-8") as f:
        db = json.load(f)
        
    drugs = [x for x in db if x["category"] == "drug_dosing"]
    print(f"Loaded {len(drugs)} drug dosing cards from compiled database.")
    
    tr_chars = "çğıöşüÇĞİÖŞÜ"
    leaky_chars = []
    leaky_placeholders = []
    
    for d in drugs:
        en_str = json.dumps(d["en"], ensure_ascii=False)
        
        # Check Turkish characters
        found_chars = [c for c in tr_chars if c in en_str]
        if found_chars:
            leaky_chars.append((d["id"], found_chars))
            
        # Check raw placeholders
        if "specified" in en_str.lower() or "defined" in en_str.lower():
            leaky_placeholders.append(d["id"])
            
    print("-" * 40)
    print(f"Total cards with Turkish characters in English block: {len(leaky_chars)}")
    for lid, chars in leaky_chars:
        print(f"  - {lid}: {chars}")
        
    print(f"Total cards with weak placeholders: {len(leaky_placeholders)}")
    for lid in leaky_placeholders:
        print(f"  - {lid}")
    print("-" * 40)
    
if __name__ == "__main__":
    verify()
