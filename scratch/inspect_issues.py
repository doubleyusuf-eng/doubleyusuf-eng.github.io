import json
from collections import Counter

def inspect():
    path = "scratch/final_assistant_expanded.json"
    with open(path, "r", encoding="utf-8") as f:
        db = json.load(f)
    
    print(f"Total items in expanded DB: {len(db)}")
    
    # 1. Check duplicate IDs
    ids = [x["id"] for x in db]
    counter = Counter(ids)
    duplicates = {k: v for k, v in counter.items() if v > 1}
    print(f"Duplicate IDs: {len(duplicates)}")
    for k, v in list(duplicates.items())[:10]:
        print(f"  {k}: {v}")
        
    # 2. Check question_en in base items (clinical_scenarios, drug_dosing)
    base_items = [x for x in db if x["category"] in ["clinical_scenarios", "drug_dosing"]]
    print(f"Total base items: {len(base_items)}")
    
    # Print some base items' question_tr and question_en to check for anomalies
    anomalous_q_en = []
    for item in base_items:
        q_en = item.get("question_en", "")
        q_tr = item.get("question_tr", "")
        # Look for things like Turkish words in question_en or empty/suspicious values
        # e.g., if it has common Turkish words
        turkish_words = ["nedir", "nelerdir", "nasil", "nasıl", "göre", "veya", "ile", "dozu", "anestezi", "hastada", "tedavisi"]
        if any(w in q_en.lower() for w in turkish_words) or q_en == q_tr or not q_en:
            anomalous_q_en.append((item["id"], q_tr, q_en))
            
    print(f"Potentially anomalous base item question_en (Total: {len(anomalous_q_en)}):")
    for item_id, q_tr, q_en in anomalous_q_en[:20]:
        print(f"  ID: {item_id}\n    TR: {q_tr}\n    EN: {q_en}")

    # 3. Check follow-up questions
    followup_items = [x for x in db if x["category"] == "clinical_followups"]
    print(f"Total follow-ups: {len(followup_items)}")
    
    # Let's see some of the follow-ups questions to see if they are too generic
    generic_counter = Counter()
    for item in followup_items:
        generic_counter[item.get("question_tr", "")] += 1
        
    print("Most common follow-up question_tr:")
    for k, v in generic_counter.most_common(10):
        print(f"  {k}: {v}")

if __name__ == "__main__":
    inspect()
