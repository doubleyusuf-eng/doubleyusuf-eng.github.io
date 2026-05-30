import json

def modify_base_qa():
    db_path = "app/src/main/assets/local_qa_database.json"
    with open(db_path, "r", encoding="utf-8") as f:
        db = json.load(f)
    
    modified_count = 0
    
    for item in db:
        if item["id"] == "rsi":
            item["question_en"] = "Rapid Sequence Induction (RSI)"
            
            # TR updates
            item["tr"]["suggestedNextStepsGeneral"] = [
                "İndüksiyon öncesi aspiratörün çalışır durumda olduğunu kontrol edin.",
                "Klinik duruma göre uygun indüksiyon ajanı (örn. Propofol veya Etomidat) ve kas gevşetici (tipik olarak Süksinilkolin 1-1.5 mg/kg veya Roküronyum 0.6-1.2 mg/kg IV) dozlarını hasta bazında ayarlayarak uygulayın.",
                "Tüp kafı şişirilip doğrulanana kadar Sellick manevrasını (krikoid bası) sürdürün; ancak krikoid bası laringeal görüşü veya ventilasyonu bozarsa basıyı gevşetmeyi veya yönünü değiştirmeyi değerlendirin."
            ]
            
            # EN updates
            item["en"]["suggestedNextStepsGeneral"] = [
                "Ensure suction is on and functioning before induction.",
                "Select and adjust appropriate induction agents (e.g., Propofol or Etomidate) and neuromuscular blockers (typically Succinylcholine 1-1.5 mg/kg or Rocuronium 0.6-1.2 mg/kg IV) based on patient-specific clinical requirements.",
                "Maintain cricoid pressure (Sellick maneuver) until the endotracheal tube cuff is inflated and verified; however, if cricoid pressure impairs laryngeal visualization or ventilation, consider reducing or releasing the pressure."
            ]
            modified_count += 1
            print("Modified base 'rsi'")
            
        elif item["id"] == "npo":
            item["question_en"] = "Preoperative Fasting Guidelines (NPO)"
            modified_count += 1
            print("Modified base 'npo'")
            
        elif item["id"] == "drug_dose_propofol":
            item["safetyLevel"] = "urgent"
            
            # TR updates
            item["tr"]["safetyLevel"] = "urgent"
            item["tr"]["conversationalText"] = "💊 Propofol Dozlama ve Klinik Bilgisi:\n\n**Yetişkin Dozu:**\n• İndüksiyon: 1.5 - 2.5 mg/kg IV (Yaşlı veya ASA III-IV hastalarda 1.0 - 1.5 mg/kg IV'ye düşürülmelidir)\n• İdame (TIVA): 100 - 200 mcg/kg/dk IV (6 - 12 mg/kg/sa) veya TCI (hedef kontrollü infüzyon) ile 3 - 6 mcg/ml plazma konsantrasyonu\n\n**Pediatrik Dozu:**\n• İndüksiyon (3 yaş üstü): 2.5 - 4.0 mg/kg IV (Yavaş enjeksiyonla titre edilerek)\n\n**Sınıfı:** Kısa etkili genel anestezik (GABA-A Agonisti)"
            
            # EN updates
            item["en"]["safetyLevel"] = "urgent"
            item["en"]["conversationalText"] = "💊 Propofol Dosing and Clinical Information:\n\n**Adult Dosing:**\n• Induction: 1.5 - 2.5 mg/kg IV (Reduce to 1.0 - 1.5 mg/kg IV in elderly or ASA III-IV patients)\n• Maintenance (TIVA): 100 - 200 mcg/kg/min IV (6 - 12 mg/kg/h) or TCI (Target Controlled Infusion) at 3 - 6 mcg/ml plasma concentration\n\n**Pediatric Dosing:**\n• Induction (>3 years): 2.5 - 4.0 mg/kg IV (Titrated slowly)\n\n**Class:** Short-acting general anesthetic (GABA-A Agonist)"
            
            modified_count += 1
            print("Modified base 'drug_dose_propofol'")
            
    with open(db_path, "w", encoding="utf-8") as f:
        json.dump(db, f, indent=2, ensure_ascii=False)
        
    print(f"Successfully updated base QA database. Modified {modified_count} items.")

if __name__ == "__main__":
    modify_base_qa()
