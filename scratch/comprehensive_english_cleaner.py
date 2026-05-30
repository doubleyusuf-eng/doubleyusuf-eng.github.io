import json

def comprehensive_english_cleaner():
    db_path = "app/src/main/assets/local_qa_database.json"
    with open(db_path, "r", encoding="utf-8") as f:
        db = json.load(f)
        
    replacements = {
        "Entubasyon": "Intubation",
        "entubasyon": "intubation",
        "Entübasyon": "Intubation",
        "entübasyon": "intubation",
        "nöromüsküler blokör": "neuromuscular blocker",
        "Nöromüsküler blokör": "Neuromuscular blocker",
        "nöromüsküler bloker": "neuromuscular blocker",
        "Nöromüsküler bloker": "Neuromuscular blocker",
        "Asetilkolin Agonisti": "Acetylcholine Agonist",
        "Asetilkolin Antagonisti": "Acetylcholine Antagonist",
        "yavaş enjeksiyon": "slow injection",
        "yavaş bolus": "slow bolus",
        "Yavaş bolus": "Slow bolus",
        "yavaş": "slow",
        "Yavaş": "Slow",
        "dozu": "dose",
        "Dozu": "Dose",
        "İdame": "Maintenance",
        "idame": "maintenance",
        "İndüksiyon": "Induction",
        "indüksiyon": "induction",
        "Kısa etkili genel anestezik": "Short-acting general anesthetic",
        "Genel Anestezik": "General Anesthetic",
        "genel anestezik": "general anesthetic",
        "Uyanık entübasyon": "Awake intubation",
        "Zor havayolu": "Difficult airway",
        "zor havayolu": "difficult airway",
        "Açlık kuralları": "Fasting rules",
        "açlık kuralları": "fasting rules",
        "Hipotansiyon": "Hypotension",
        "hipotansiyon": "hypotension",
        "Gebelik": "Pregnancy",
        "gebelik": "pregnancy",
        "Acil": "Emergency",
        "acil": "emergency",
        "Aspirasyon": "Aspiration",
        "aspirasyon": "aspiration",
        "Krikoid bası": "Cricoid pressure",
        "krikoid bası": "cricoid pressure",
        "Kapnografi": "Capnography",
        "kapnografi": "capnography",
        "Preoksijenasyon": "Preoxygenation",
        "preoksijenasyon": "preoxygenation",
    }
    
    modified_count = 0
    for item in db:
        if "en" in item:
            item_changed = False
            
            # Check question_en
            q_en = item.get("question_en", "")
            orig_q_en = q_en
            for tr_word, en_word in replacements.items():
                if tr_word in q_en:
                    q_en = q_en.replace(tr_word, en_word)
            if q_en != orig_q_en:
                item["question_en"] = q_en
                item_changed = True
                
            # Check conversationalText
            if "conversationalText" in item["en"]:
                text = item["en"]["conversationalText"]
                orig_text = text
                for tr_word, en_word in replacements.items():
                    if tr_word in text:
                        text = text.replace(tr_word, en_word)
                if text != orig_text:
                    item["en"]["conversationalText"] = text
                    item_changed = True
            
            # Check other list fields in en
            for field in ["immediateRedFlags", "missingCriticalInformation", "likelyClinicalCategoriesToConsider", "suggestedNextStepsGeneral", "doNotMiss", "whenToEscalate"]:
                if field in item["en"]:
                    list_val = item["en"][field]
                    new_list_val = []
                    for val in list_val:
                        if isinstance(val, str):
                            orig_val = val
                            for tr_word, en_word in replacements.items():
                                if tr_word in val:
                                    val = val.replace(tr_word, en_word)
                            new_list_val.append(val)
                            if val != orig_val:
                                item_changed = True
                        else:
                            new_list_val.append(val)
                    item["en"][field] = new_list_val
            
            if item_changed:
                modified_count += 1
                
    if modified_count > 0:
        with open(db_path, "w", encoding="utf-8") as f:
            json.dump(db, f, indent=2, ensure_ascii=False)
            
    print(f"Successfully cleaned comprehensive English leakage in {modified_count} items.")

if __name__ == "__main__":
    comprehensive_english_cleaner()
