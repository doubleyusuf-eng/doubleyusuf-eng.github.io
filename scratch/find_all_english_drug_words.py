import json
import re

FINAL_EXPANDED_PATH = "scratch/final_assistant_expanded.json"

with open(FINAL_EXPANDED_PATH, "r", encoding="utf-8") as f:
    db = json.load(f)

drug_cards = [x for x in db if x.get("category") == "drug_dosing"]

unique_words = set()
for item in drug_cards:
    # Gather question_en
    if "question_en" in item:
        unique_words.update(re.findall(r'[a-zA-ZğüşöçıİĞÜŞÖÇ]+', item["question_en"]))
        
    en_block = item.get("en", {})
    for key, value in en_block.items():
        if isinstance(value, str):
            unique_words.update(re.findall(r'[a-zA-ZğüşöçıİĞÜŞÖÇ]+', value))
        elif isinstance(value, list):
            for element in value:
                if isinstance(element, str):
                    unique_words.update(re.findall(r'[a-zA-ZğüşöçıİĞÜŞÖÇ]+', element))
                elif isinstance(element, dict):
                    for subval in element.values():
                        if isinstance(subval, str):
                            unique_words.update(re.findall(r'[a-zA-ZğüşöçıİĞÜŞÖÇ]+', subval))

print(f"Total unique words in English drug fields: {len(unique_words)}")
# Let's write them all to a temporary file
sorted_words = sorted(list(unique_words), key=lambda x: x.lower())
with open("scratch/all_english_drug_words.txt", "w", encoding="utf-8") as f:
    for w in sorted_words:
        f.write(w + "\n")
print("Saved sorted words to scratch/all_english_drug_words.txt")
