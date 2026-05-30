import os
import re
import sys
import json
import time
import urllib.request
import urllib.parse

def translate_to_english(text):
    if not text:
        return ""
    # Sleep to respect rate limits
    time.sleep(0.2)
    url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=tr&tl=en&dt=t&q=" + urllib.parse.quote(text)
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    for attempt in range(5):
        try:
            response = urllib.request.urlopen(req, timeout=10)
            data = json.loads(response.read().decode('utf-8'))
            translated = ''.join([part[0] for part in data[0] if part[0]])
            return translated
        except Exception as e:
            print(f"Translation attempt {attempt+1} failed for text '{text[:20]}...': {e}")
            time.sleep(1.0)
    return text # fallback to original

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

def main():
    raw_path = "scratch/raw_daily_life_qa.txt"
    out_path = "app/src/main/assets/local_assistant_daily_life_pack_1.json"
    
    if not os.path.exists(raw_path):
        print(f"Error: {raw_path} not found.")
        sys.exit(1)
        
    with open(raw_path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    # Split content by category sections
    sections = re.split(r'###\s+(.*)', content)
    
    qa_items = []
    
    # Simple state machine to parse QA items
    current_category = "general"
    category_map = {
        "Gün Planlama": "daily_planning",
        "Verimlilik ve Odaklanma": "productivity_and_focus",
        "Hatırlatıcı ve Organizasyon": "reminder_and_organization",
        "Ev ve Düzen": "home_and_order",
        "Yemek ve Alışveriş": "food_and_shopping",
        "Kişisel Bakım": "personal_care",
        "İletişim ve Sosyal Yaşam": "communication_and_social",
        "Seyahat ve Dışarı Çıkma": "travel_and_outings",
        "Finans ve Bütçe": "finance_and_budget",
        "Teknoloji ve Dijital Yaşam": "technology_and_digital",
        "Eğitim ve Öğrenme": "education_and_learning",
        "İş ve Profesyonel Yaşam": "work_and_career",
        "Aile ve Günlük İlişkiler": "family_and_relationships",
        "Sağlıklı Yaşam Genel Bilgi": "healthy_living",
        "Duygu Durumu ve Motivasyon": "mindset_and_motivation",
        "Hobi ve Boş Zaman": "hobbies_and_leisure",
        "Karar Verme ve Problem Çözme": "decision_making",
        "Günlük Pratik Yardım": "daily_practical_tips",
        "Yerel Asistan Kullanımına Uygun Genel Sorular": "general_assistant"
    }
    
    # Regular expressions for Q and A
    # E.g.:
    # 1. **Soru:** Bugün işlerimi nasıl planlamalıyım?
    #    **Cevap:** Önce en önemli 3 işi belirleyin...
    qa_blocks = re.findall(r'(\d+)\.\s+\*\*Soru:\*\*\s*(.*?)\n\s*\*\*Cevap:\*\*\s*(.*?)(?=\n\d+\.|\n---|###|$)', content, re.DOTALL)
    
    print(f"Found {len(qa_blocks)} Q&A blocks to process.")
    
    for idx, (num_str, tr_q, tr_a) in enumerate(qa_blocks):
        num = int(num_str)
        tr_q = tr_q.strip()
        tr_a = tr_a.strip()
        
        # Determine category based on question number index
        # 1-10: Gün Planlama
        # 11-20: Verimlilik ve Odaklanma
        # etc.
        cat_key = "general_assistant"
        if 1 <= num <= 10: cat_key = "daily_planning"
        elif 11 <= num <= 20: cat_key = "productivity_and_focus"
        elif 21 <= num <= 30: cat_key = "reminder_and_organization"
        elif 31 <= num <= 40: cat_key = "home_and_order"
        elif 41 <= num <= 50: cat_key = "food_and_shopping"
        elif 51 <= num <= 60: cat_key = "personal_care"
        elif 61 <= num <= 70: cat_key = "communication_and_social"
        elif 71 <= num <= 80: cat_key = "travel_and_outings"
        elif 81 <= num <= 90: cat_key = "finance_and_budget"
        elif 91 <= num <= 100: cat_key = "technology_and_digital"
        elif 101 <= num <= 110: cat_key = "education_and_learning"
        elif 111 <= num <= 120: cat_key = "work_and_career"
        elif 121 <= num <= 130: cat_key = "family_and_relationships"
        elif 131 <= num <= 140: cat_key = "healthy_living"
        elif 141 <= num <= 150: cat_key = "mindset_and_motivation"
        elif 151 <= num <= 160: cat_key = "hobbies_and_leisure"
        elif 161 <= num <= 170: cat_key = "decision_making"
        elif 171 <= num <= 180: cat_key = "daily_practical_tips"
        elif 181 <= num <= 200: cat_key = "general_assistant"
        
        # Translate
        print(f"[{num}/200] Translating Q: {tr_q[:40]}...")
        en_q = translate_to_english(tr_q)
        print(f"[{num}/200] Translating A: {tr_a[:40]}...")
        en_a = translate_to_english(tr_a)
        
        keywords = extract_keywords(tr_q, en_q)
        
        item = {
            "id": f"daily_life_{num}",
            "category": cat_key,
            "keywords": keywords,
            "question_tr": tr_q,
            "question_en": en_q,
            "safetyLevel": "routine",
            "tr": {
                "safetyLevel": "routine",
                "patientIdentifierDetected": False,
                "userAppearsToBeClinician": False,
                "notASubstituteWarning": "Bu yanıt genel günlük yaşam asistanı paketi kapsamındadır.",
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
                "safetyLevel": "routine",
                "patientIdentifierDetected": False,
                "userAppearsToBeClinician": False,
                "notASubstituteWarning": "This response is part of the general daily life assistant pack.",
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
        qa_items.append(item)
        
    with open(out_path, 'w', encoding='utf-8') as out_f:
        json.dump(qa_items, out_f, ensure_ascii=False, indent=2)
        
    print(f"Successfully generated daily life pack JSON at {out_path} with {len(qa_items)} items.")

if __name__ == '__main__':
    main()
