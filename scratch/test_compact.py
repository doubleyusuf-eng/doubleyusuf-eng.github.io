import json
import urllib.request
import time

api_key = "AIzaSyAeD84-0Gq8LGBVuqX621Cz7X_jRHQ6U2Y"
url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key={api_key}"

def test_single_compact():
    with open("scratch/pilot_cache.json", "r", encoding="utf-8") as f:
        old_cache = json.load(f)
        
    old_followups = old_cache["rsi"]
    
    prompt = f"""
You are an expert clinical editor and conversational designer.
Below is a list of 5 follow-up Q&A items for the base question 'rsi'.
Your task is to take these 5 items and rewrite them to be highly compact, safe, and professional in both Turkish (TR) and English (EN) simultaneously.

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

### Original Follow-up Items:
{json.dumps(old_followups, ensure_ascii=False, indent=2)}

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
    
    print("Sending request to Gemini...")
    try:
        with urllib.request.urlopen(req, timeout=30) as response:
            res = json.loads(response.read().decode("utf-8"))
            text = res["candidates"][0]["content"]["parts"][0]["text"].strip()
            print("Successfully received response:")
            print(text[:1000])
    except Exception as e:
        print("Failed with error:", e)

if __name__ == "__main__":
    test_single_compact()
