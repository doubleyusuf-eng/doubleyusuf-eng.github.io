import os
import sys
import json
import time
import urllib.request
import urllib.parse
import re

api_key = "AIzaSyAeD84-0Gq8LGBVuqX621Cz7X_jRHQ6U2Y"
url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key={api_key}"

STOP_WORDS = {
    "ve", "bir", "nedir", "nelerdir", "nasil", "nasıl", "veya", "icin", "için", "olan", "göre", "gore", "ile", "de", "da", "mi", "mı", "mu", "mü",
    "ne", "zaman", "the", "and", "what", "how", "is", "of", "for", "with", "or", "in", "to", "at", "on", "by", "an", "a"
}

def extract_keywords(tr_q, en_q):
    tokens = []
    for q in [tr_q, en_q]:
        words = re.findall(r'[a-zA-Z0-9ğüşöçıİĞÜŞÖÇ]+', q.lower())
        for w in words:
            if w not in STOP_WORDS and len(w) > 1:
                tokens.append(w)
    return sorted(list(set(tokens)))

def query_gemini_for_followups(item, is_medical=False):
    prompt_type = "Medical Anesthesiology and Critical Care" if is_medical else "General Productivity and Daily Life"
    
    safety_instructions = ""
    if is_medical:
        safety_instructions = """
### CRITICAL MEDICAL SAFETY INSTRUCTIONS (MANDATORY):
1. **Safety Headers**: Every answer in both Turkish and English MUST start with a clear, bold safety header:
   - Turkish: "[Klinik Karar Destek Modu] Bu yanıt genel bilgilendirme amaçlıdır ve doğrudan hekimin klinik kararı veya hastanın bireysel klinik değerlendirmesinin yerine geçmez."
   - English: "[Clinical Decision Support Mode] This information is for educational purposes only and does not substitute professional clinical evaluation or direct patient assessment."
2. **Highly Conservative Tone**: 
   - When discussing high-risk clinical topics (drug dosing, pediatric guidelines, pregnant patients, anticoagulant management, emergency airways, and critical care), write in a highly conservative, safety-first manner.
   - Include safe dosing ranges, highlight absolute contraindications, mandate double-checking with secondary sources, recommend senior consultant supervision, and detail step-by-step rescue protocols in case of emergencies.
   - Use warnings and precautions aggressively. Avoid overconfident claims.
"""
    else:
        safety_instructions = """
### GENERAL ASSISTANT SAFETY INSTRUCTIONS:
1. **Non-Clinical Header**: Every answer in both Turkish and English MUST start with a clean non-clinical warning:
   - Turkish: "[Genel Asistan Modu] Bu yanıt genel günlük yaşam asistanı paketi kapsamındadır."
   - English: "[General Assistant Mode] This response is part of the general daily life assistant pack."
2. **Helpful Tone**: Provide practical, actionable, structured advice suitable for general lifestyle assistance.
"""

    prompt = f"""
You are an expert conversational AI designed to build multi-turn conversational trees for an offline assistant app.
Below is a base Q&A node from the database. Think of exactly 5 highly context-relevant, logical, and natural follow-up questions that a user would ask after reading this response, along with their detailed, structured answers.
Generate these follow-up Q&As in both Turkish (TR) and English (EN) simultaneously.

{safety_instructions}

### CRITICAL FORMATTING RULE (MANDATORY):
- **NO NESTED DOUBLE QUOTES**: NEVER use double quotes (") inside any JSON string fields (like `question_tr`, `question_en`, `answer_tr`, or `answer_en`). If you need to quote something, use single quotes ('). Using double quotes inside string fields will break the JSON parser and fail the execution. Never write double quotes inside the text content.

### Base Item Details:
- Category: {item.get('category')}
- Question (TR): {item.get('question_tr')}
- Answer (TR): {item.get('tr', {}).get('conversationalText')}
- Question (EN): {item.get('question_en')}
- Answer (EN): {item.get('en', {}).get('conversationalText')}

### JSON Output Format Constraints:
Your response MUST be strictly a valid JSON array of exactly 5 follow-up Q&A objects. No extra markdown, no explanation, just the raw JSON array.
Follow this schema for each object in the array:
[
  {{
    "question_tr": "Highly relevant follow-up question 1 in Turkish (NEVER use double quotes)",
    "question_en": "Highly relevant follow-up question 1 in English (NEVER use double quotes)",
    "answer_tr": "Detailed, professional answer 1 in Turkish starting with header (NEVER use double quotes)",
    "answer_en": "Detailed, professional answer 1 in English starting with header (NEVER use double quotes)"
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

    for attempt in range(10):
        try:
            with urllib.request.urlopen(req, timeout=60) as response:
                res = json.loads(response.read().decode("utf-8"))
                text = res["candidates"][0]["content"]["parts"][0]["text"].strip()
                followups = json.loads(text)
                if isinstance(followups, list) and len(followups) == 5:
                    return followups
                else:
                    print(f"Warning: Gemini returned invalid followups list length on attempt {attempt+1}")
        except Exception as e:
            err_str = str(e)
            if "429" in err_str:
                sleep_time = 30.0
                print(f"Gemini API rate limited (429) for item '{item['id']}': {e}. Sleeping 30s before retry...")
            else:
                sleep_time = 5.0 * (attempt + 1)
                print(f"Gemini API attempt {attempt+1} failed for item '{item['id']}': {e}. Retrying in {sleep_time}s...")
            time.sleep(sleep_time)
    return None

def main():
    print("Starting safety-first pilot conversational tree generation (Resilient Caching Mode)...")
    
    # Load databases
    db_medical_path = "app/src/main/assets/local_qa_database.json"
    db_daily_path = "app/src/main/assets/local_assistant_daily_life_pack_1.json"
    cache_path = "scratch/pilot_cache.json"
    
    with open(db_medical_path, "r", encoding="utf-8") as f:
        db_medical = json.load(f)
        
    with open(db_daily_path, "r", encoding="utf-8") as f:
        db_daily = json.load(f)

    # Load cache if exists
    cache = {}
    if os.path.exists(cache_path):
        try:
            with open(cache_path, "r", encoding="utf-8") as cache_f:
                cache = json.load(cache_f)
            print(f"Loaded existing cache with {len(cache)} generated items.")
        except Exception as e:
            print(f"Warning: Error loading cache file: {e}. Starting fresh.")

    # Base item lists
    medical_ids = ["rsi", "difficult_airway", "npo", "spinal_hypotension", "drug_dose_propofol"]
    daily_ids = ["daily_life_1", "daily_life_11", "daily_life_51", "daily_life_81", "daily_life_141"]
    
    pilot_base_items = []
    
    # Extract target medical items
    for item_id in medical_ids:
        found = [x for x in db_medical if x["id"] == item_id]
        if found:
            pilot_base_items.append((found[0], True))
            
    # Extract target daily items
    for item_id in daily_ids:
        found = [x for x in db_daily if x["id"] == item_id]
        if found:
            pilot_base_items.append((found[0], False))

    print(f"Found {len(pilot_base_items)} base items for pilot production.")
    
    pilot_expanded_items = []
    pilot_followup_qa_items = []

    for idx, (base_item, is_medical) in enumerate(pilot_base_items):
        item_id = base_item["id"]
        print(f"[{idx+1}/10] Processing '{item_id}' (Medical: {is_medical})...")
        
        # Check cache
        followups = None
        if item_id in cache:
            print(f"  Found cached followups for '{item_id}'. Reusing cached Q&As.")
            followups = cache[item_id]
        else:
            print(f"  Querying Gemini API for '{item_id}'...")
            followups = query_gemini_for_followups(base_item, is_medical)
            if not followups:
                print(f"Warning: Skipping item '{item_id}' due to API failure/rate limit.")
                continue
            
            # Save to cache
            cache[item_id] = followups
            with open(cache_path, "w", encoding="utf-8") as cache_f:
                json.dump(cache, cache_f, ensure_ascii=False, indent=2)
            print(f"  Successfully cached results for '{item_id}'.")
            
        tr_followup_questions = []
        en_followup_questions = []
        
        for f_idx, f in enumerate(followups):
            f_num = f_idx + 1
            tr_q = f["question_tr"]
            en_q = f["question_en"]
            tr_a = f["answer_tr"]
            en_a = f["answer_en"]
            
            tr_followup_questions.append(tr_q)
            en_followup_questions.append(en_q)
            
            # Formulate LocalQaItem for the followup QA node
            followup_id = f"{item_id}_followup_{f_num}"
            keywords = extract_keywords(tr_q, en_q)
            
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
            
        # Update parent item's followUpQuestions lists
        base_item["tr"]["followUpQuestions"] = tr_followup_questions
        base_item["en"]["followUpQuestions"] = en_followup_questions
        
        pilot_expanded_items.append(base_item)
        time.sleep(1.0)  # Rate limiting sleep

    # Combine updated base items and their followups
    final_pilot_database = pilot_expanded_items + pilot_followup_qa_items
    
    out_path = "scratch/pilot_assistant_expanded.json"
    with open(out_path, "w", encoding="utf-8") as out_f:
        json.dump(final_pilot_database, out_f, ensure_ascii=False, indent=2)
        
    print(f"Successfully generated 10-item pilot database at {out_path} containing {len(final_pilot_database)} total QA items.")
    print("Pilot complete. Ready for manual safety inspection!")

if __name__ == "__main__":
    main()
