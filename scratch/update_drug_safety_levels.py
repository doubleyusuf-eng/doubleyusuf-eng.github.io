import json

def update_drug_safety_levels():
    db_path = "app/src/main/assets/local_qa_database.json"
    with open(db_path, "r", encoding="utf-8") as f:
        db = json.load(f)
    
    modified_count = 0
    for item in db:
        if item.get("category") == "drug_dosing":
            item["safetyLevel"] = "urgent"
            if "tr" in item:
                item["tr"]["safetyLevel"] = "urgent"
            if "en" in item:
                item["en"]["safetyLevel"] = "urgent"
            modified_count += 1
            
    with open(db_path, "w", encoding="utf-8") as f:
        json.dump(db, f, indent=2, ensure_ascii=False)
        
    print(f"Successfully updated safety levels for {modified_count} drug dosing items to 'urgent'.")

if __name__ == "__main__":
    update_drug_safety_levels()
