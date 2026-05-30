import os
import json
import time
import urllib.request
import urllib.parse
import re

api_key = "AIzaSyAeD84-0Gq8LGBVuqX621Cz7X_jRHQ6U2Y"
url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key={api_key}"

def compact_followups(base_id, followups, is_medical):
    prompt_type = "Medical Anesthesiology and Critical Care" if is_medical else "General Productivity and Daily Life"
    
    safety_instructions = ""
    if is_medical:
        safety_instructions = """
### CRITICAL CLINICAL SAFETY & COMPACTING INSTRUCTIONS (MANDATORY):
1. **Safety Headers**: Every answer in Turkish and English MUST start with this exact header:
   - Turkish: "[Klinik Karar Destek Modu] Bu yanıt genel bilgilendirme amaçlıdır ve doğrudan hekimin klinik kararı veya hastanın bireysel klinik değerlendirmesinin yerine geçmez."
   - English: "[Clinical Decision Support Mode] This information is for educational purposes only and does not substitute professional clinical evaluation or direct patient assessment."

2. **Strict Compact 4-Part Structure (Keep it highly concise for mobile screens)**:
   Make sure each answer is extremely short, structured, and easy to read. Use exactly these four sections:
   - **Klinik Özet / Clinical Summary**: 1-2 sentence maximum summary.
   - **Kritik Uyarılar / Critical Warnings**: Short bullet points highlighting high-risk considerations, safety ranges, absolute contraindications, and pediatric/pregnancy cautions. Keep them very brief!
   - **Pratik Yaklaşım / Practical Approach**: Short bullet points of clinical steps. Use cautious, non-absolute, patient-centric phrasing (e.g. 'consider titration', 'adjust based on patient response', 'maintain cricoid pressure unless view or ventilation is compromised').
   - **Eskalasyon / Escalation**: 1-2 bullets on what to do if things fail (e.g., 'escalate to difficult airway algorithm', 'call for senior assistance').

3. **Highly Conservative & Cautious Phrasing**:
   - Avoid direct command verbs or overly absolute phrasing like 'use X mg/kg' or 'do not release under any circumstances'. 
   - Instead, use phrases like 'consider clinical situation', 'typical range of X to Y', 'cricoid pressure should be maintained unless it impairs laryngeal view or ventilation, in which case release or adjustment is warranted', 'double check with local guidelines', 'senior supervision recommended'.

4. **Zero Turkish Leakage in English**:
   - Strictly verify that English questions and answers have NO Turkish words or suffixes (e.g., use 'induction' NOT 'indüksiyon' or 'induksiyon', 'intubation' NOT 'entubasyon', 'anesthetic' NOT 'anestezik', 'neuromuscular blocker' NOT 'gevşetici', etc.).
"""
    else:
        safety_instructions = """
### GENERAL ASSISTANT SAFETY & FORMATTING INSTRUCTIONS:
1. **Non-Clinical Header**: Every answer in Turkish and English MUST start with this exact header:
   - Turkish: "[Genel Asistan Modu] Bu yanıt genel günlük yaşam asistanı paketi kapsamındadır."
   - English: "[General Assistant Mode] This response is part of the general daily life assistant pack."
2. **Helpful Tone**: Provide practical, actionable, structured, and concise advice suitable for general lifestyle assistance. Keep it very compact for mobile screens.
"""

    prompt = f"""
You are an expert clinical editor and conversational designer.
Below is a list of 5 follow-up Q&A items for the base question '{base_id}'.
Your task is to take these 5 items and rewrite them to be highly compact, safe, and professional in both Turkish (TR) and English (EN) simultaneously, following the safety instructions.

{safety_instructions}

### CRITICAL FORMATTING RULE (MANDATORY):
- **NO NESTED DOUBLE QUOTES**: NEVER use double quotes (") inside any JSON string fields. If you need to quote something, use single quotes ('). Using double quotes inside string fields will break the JSON parser.

### Original Follow-up Items:
{json.dumps(followups, ensure_ascii=False, indent=2)}

### JSON Output Format Constraints:
Your response MUST be strictly a valid JSON array of exactly 5 follow-up Q&A objects. No extra markdown, no explanation, just the raw JSON array.
Follow this schema for each object in the array:
[
  {{
    "question_tr": "Compact TR question (NEVER use double quotes)",
    "question_en": "Compact EN question (NEVER use double quotes)",
    "answer_tr": "Compact structured TR answer (NEVER use double quotes)",
    "answer_en": "Compact structured EN answer (NEVER use double quotes)"
  }},
  ... (exactly 5 items)
]
"""

    payload = {
        "contents": [{
            "parts": [{
                "text": prompt
            }]
        }],
        "generationConfig": {
            "responseMimeType": "application/json"
        }
    }

    data = json.dumps(payload).encode("utf-8")
    req = urllib.request.Request(url, data=data, headers={"Content-Type": "application/json"})

    for attempt in range(12):
        try:
            with urllib.request.urlopen(req, timeout=60) as response:
                res = json.loads(response.read().decode("utf-8"))
                text = res["candidates"][0]["content"]["parts"][0]["text"].strip()
                result = json.loads(text)
                if isinstance(result, list) and len(result) == 5:
                    return result
                else:
                    print(f"Warning: Invalid list length on attempt {attempt+1}")
        except Exception as e:
            err_str = str(e)
            if "429" in err_str:
                sleep_time = 35.0
                print(f"Gemini API rate limited (429) for '{base_id}': {e}. Sleeping 35s before retry...")
            else:
                sleep_time = 6.0 * (attempt + 1)
                print(f"Attempt {attempt+1} failed for '{base_id}': {e}. Retrying in {sleep_time}s...")
            time.sleep(sleep_time)
    return None

def main():
    print("Starting programmatic pilot compactifier and cleaner...")
    
    # Load base databases
    db_medical_path = "app/src/main/assets/local_qa_database.json"
    db_daily_path = "app/src/main/assets/local_assistant_daily_life_pack_1.json"
    cache_in_path = "scratch/pilot_cache.json"
    cache_out_path = "scratch/pilot_cache_v2.json"
    
    with open(db_medical_path, "r", encoding="utf-8") as f:
        db_medical = json.load(f)
        
    with open(db_daily_path, "r", encoding="utf-8") as f:
        db_daily = json.load(f)
        
    with open(cache_in_path, "r", encoding="utf-8") as f:
        old_cache = json.load(f)
        
    new_cache = {}
    if os.path.exists(cache_out_path):
        try:
            with open(cache_out_path, "r", encoding="utf-8") as f:
                new_cache = json.load(f)
            print(f"Loaded existing v2 cache with {len(new_cache)} items.")
        except:
            pass

    # Base item lists
    medical_ids = ["rsi", "difficult_airway", "npo", "spinal_hypotension", "drug_dose_propofol"]
    daily_ids = ["daily_life_1", "daily_life_11", "daily_life_51", "daily_life_81", "daily_life_141"]
    
    all_targets = []
    for item_id in medical_ids:
        found = [x for x in db_medical if x["id"] == item_id]
        if found:
            all_targets.append((found[0], True))
    for item_id in daily_ids:
        found = [x for x in db_daily if x["id"] == item_id]
        if found:
            all_targets.append((found[0], False))
            
    print(f"Targets configured: {len(all_targets)}")
    
    for idx, (base_item, is_medical) in enumerate(all_targets):
        item_id = base_item["id"]
        if item_id in new_cache:
            print(f"[{idx+1}/10] '{item_id}' already compacted in v2 cache.")
            continue
            
        print(f"[{idx+1}/10] Compacting '{item_id}' (Medical: {is_medical})...")
        old_followups = old_cache[item_id]
        
        compacted = compact_followups(item_id, old_followups, is_medical)
        if compacted:
            new_cache[item_id] = compacted
            with open(cache_out_path, "w", encoding="utf-8") as f:
                json.dump(new_cache, f, ensure_ascii=False, indent=2)
            print(f"  Successfully compacted and cached '{item_id}'.")
        else:
            print(f"  Failed to compact '{item_id}'.")
            
        # Strict rate limit defense sleep
        time.sleep(8.0)

    # Now formulate the final pilot_assistant_expanded.json!
    print("Compiling final pilot expanded database...")
    pilot_expanded_items = []
    pilot_followup_qa_items = []
    
    # Helper to clean text
    def clean_text(text):
        return text.replace('"', "'")
        
    for base_item, is_medical in all_targets:
        item_id = base_item["id"]
        followups = new_cache[item_id]
        
        tr_followup_questions = []
        en_followup_questions = []
        
        for f_idx, f in enumerate(followups):
            f_num = f_idx + 1
            tr_q = clean_text(f["question_tr"])
            en_q = clean_text(f["question_en"])
            tr_a = clean_text(f["answer_tr"])
            en_a = clean_text(f["answer_en"])
            
            tr_followup_questions.append(tr_q)
            en_followup_questions.append(en_q)
            
            followup_id = f"{item_id}_followup_{f_num}"
            
            # extract keywords
            tokens = []
            for q in [tr_q, en_q]:
                words = re.findall(r'[a-zA-Z0-9ğüşöçıİĞÜŞÖÇ]+', q.lower())
                for w in words:
                    if len(w) > 1:
                        tokens.append(w)
            keywords = sorted(list(set(tokens)))
            
            followup_qa = {
                "id": followup_id,
                "category": "clinical_followups" if is_medical else base_item["category"],
                "keywords": keywords,
                "question_tr": tr_q,
                "question_en": en_q,
                "safetyLevel": base_item.get("safetyLevel", "routine"),
                "tr": {
                    "safetyLevel": base_item.get("safetyLevel", "routine"),
                    "patientIdentifierDetected": False,
                    "userAppearsToBeClinician": not is_medical,
                    "notASubstituteWarning": base_item["tr"].get("notASubstituteWarning", ""),
                    "conversationalText": tr_a,
                    "immediateRedFlags": [],
                    "missingCriticalInformation": [],
                    "likelyClinicalCategoriesToConsider": [],
                    "suggestedNextStepsGeneral": [],
                    "relatedAlgorithms": [],
                    "relatedCalculators": [],
                    "relatedDrugCards": [],
                    "doNotMiss": [],
                    "whenToEscalate": [],
                    "referencesToShow": [],
                    "followUpQuestions": []
                },
                "en": {
                    "safetyLevel": base_item.get("safetyLevel", "routine"),
                    "patientIdentifierDetected": False,
                    "userAppearsToBeClinician": not is_medical,
                    "notASubstituteWarning": base_item["en"].get("notASubstituteWarning", ""),
                    "conversationalText": en_a,
                    "immediateRedFlags": [],
                    "missingCriticalInformation": [],
                    "likelyClinicalCategoriesToConsider": [],
                    "suggestedNextStepsGeneral": [],
                    "relatedAlgorithms": [],
                    "relatedCalculators": [],
                    "relatedDrugCards": [],
                    "doNotMiss": [],
                    "whenToEscalate": [],
                    "referencesToShow": [],
                    "followUpQuestions": []
                }
            }
            pilot_followup_qa_items.append(followup_qa)
            
        base_item["tr"]["followUpQuestions"] = tr_followup_questions
        base_item["en"]["followUpQuestions"] = en_followup_questions
        pilot_expanded_items.append(base_item)
        
    final_pilot_database = pilot_expanded_items + pilot_followup_qa_items
    out_path = "scratch/pilot_assistant_expanded.json"
    with open(out_path, "w", encoding="utf-8") as out_f:
        json.dump(final_pilot_database, out_f, ensure_ascii=False, indent=2)
        
    # Also copy to desktop!
    os.system("cp scratch/pilot_assistant_expanded.json ~/Desktop/pilot_assistant_expanded.json")
    print(f"Successfully generated new pilot expanded database at {out_path} containing {len(final_pilot_database)} total QA items and copied to desktop.")

if __name__ == "__main__":
    main()
