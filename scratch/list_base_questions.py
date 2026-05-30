import json

def list_all_base_questions():
    with open("app/src/main/assets/local_qa_database.json", "r", encoding="utf-8") as f:
        db = json.load(f)
        
    base_items = [x for x in db if x["category"] in ["clinical_scenarios", "drug_dosing"]]
    
    print(f"Total base items: {len(base_items)}")
    for item in base_items:
        print(f"ID: {item['id']} | TR: {item['question_tr']} | EN: {item['question_en']}")

if __name__ == "__main__":
    list_all_base_questions()
