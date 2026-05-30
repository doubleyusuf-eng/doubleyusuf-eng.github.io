import json
import re

def clean_english_leaks(text):
    if not text:
        return ""
    t = text
    t = t.replace("anestezik", "anesthetic").replace("Anestezik", "Anesthetic")
    t = t.replace("Dissosiyatif", "Dissociative").replace("dissosiyatif", "dissociative")
    t = t.replace("gevşetici", "blocker").replace("gevsetici", "blocker")
    t = t.replace("entubasyon", "intubation").replace("entübasyon", "intubation")
    t = t.replace("indüksiyon", "induction").replace("induksiyon", "induction")
    t = t.replace("veya", "or")
    # Clean other common leaks
    t = t.replace("doz", "dose").replace("Doz", "Dose")
    t = t.replace("hastanın", "patient's").replace("hekimin", "physician's")
    t = t.replace("gebe", "pregnant")
    return t

def clean_drug_conversational_text_tr(text):
    disclaimer = "[Klinik Karar Destek Modu] Bu doz bilgisi genel kılavuz amaçlı olup, mutlak bir talimat değildir; hastanın hemodinamik yanıtına göre bireysel olarak titre (titrate) edilmelidir."
    t = text
    t = re.sub(r'^\[Klinik Karar Destek Modu\].*?\n\n', '', t)
    
    t = t.replace("kesinlikle uygulayın", "hasta bazında titre ederek uygulayabilirsiniz")
    t = t.replace("mutlaka verin", "klinik yanıta göre titre edilmesi önerilir")
    t = t.replace("verilmelidir", "titre edilerek verilmesi düşünülmelidir")
    t = t.replace("uygulanmalıdır", "titre edilerek uygulanmalıdır")
    t = t.replace("dozu:", "tipik dozaj aralığı (typical range):")
    t = t.replace("Dozu:", "Tipik dozaj aralığı (typical range):")
    
    if disclaimer not in t:
        return f"{disclaimer}\n\n{t}"
    return t

def clean_drug_conversational_text_en(text):
    disclaimer = "[Clinical Decision Support Mode] This dosing information serves as a general typical range guideline and must be titrated carefully to patient-specific response."
    t = text
    t = re.sub(r'^\[Clinical Decision Support Mode\].*?\n\n', '', t)
    t = clean_english_leaks(t)
    
    t = t.replace("must be administered", "is typically titrated based on patient-specific response")
    t = t.replace("always administer", "titrate carefully based on response")
    t = t.replace("should be given", "may be considered for titration")
    t = t.replace("dosing:", "typical range:")
    t = t.replace("Dosing:", "Typical range:")
    t = t.replace("dose:", "typical range:")
    t = t.replace("Dose:", "Typical range:")
    
    if disclaimer not in t:
        return f"{disclaimer}\n\n{t}"
    return t

def test():
    with open("app/src/main/assets/local_qa_database.json", "r", encoding="utf-8") as f:
        db = json.load(f)
        
    drugs = [x for x in db if x["category"] == "drug_dosing"]
    print(f"Loaded {len(drugs)} drug dosing cards.")
    
    # Test cleaning on propofol
    prop = [x for x in drugs if x["id"] == "drug_dose_propofol"][0]
    print("Original TR:", prop["tr"]["conversationalText"])
    print("Original EN:", prop["en"]["conversationalText"])
    
    cleaned_tr = clean_drug_conversational_text_tr(prop["tr"]["conversationalText"])
    cleaned_en = clean_drug_conversational_text_en(prop["en"]["conversationalText"])
    
    print("-" * 40)
    print("Cleaned TR:", cleaned_tr)
    print("Cleaned EN:", cleaned_en)

if __name__ == "__main__":
    test()
