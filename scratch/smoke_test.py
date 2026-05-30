import json
import os
import sys

DATABASE_PATH = "app/src/main/assets/local_qa_database.json"

REQUIRED_IDS = [
    "drug_dose_midazolam",
    "drug_dose_dantrolene",
    "drug_dose_nitrous_oxide",
    "rsi",
    "cico",
    "npo",
    "greeting_hello"
]

BLACK_WORDS = [
    "hizla", "gazi", "antagonizmasi", "unite", "antagonisti", "analjezi", 
    "idame", "induksiyon", "kisa", "maksimum", "selektif"
]

TURKISH_CHARS = set("çğıöşüÇĞİÖŞÜ")

def run_smoke_test():
    print("=== STARTING CLINICAL DATABASE SMOKE TEST ===")
    
    # 1. Parse JSON File
    if not os.path.exists(DATABASE_PATH):
        print(f"❌ Error: Database file not found at {DATABASE_PATH}")
        sys.exit(1)
        
    try:
        with open(DATABASE_PATH, "r", encoding="utf-8") as f:
            db = json.load(f)
        print(f"🟢 JSON parsed successfully. Total items loaded: {len(db)}")
    except Exception as e:
        print(f"❌ JSON parsing failed: {e}")
        sys.exit(1)
        
    # 2. Check for required IDs
    print("\n--- Verifying Target IDs ---")
    missing_ids = []
    found_items = {}
    for target_id in REQUIRED_IDS:
        match = [x for x in db if x.get("id") == target_id]
        if not match:
            # Fallback check for substring matching
            substring_matches = [x.get("id") for x in db if target_id in x.get("id", "").lower()]
            print(f"❌ Target ID '{target_id}' not found! Substring matches in DB: {substring_matches}")
            missing_ids.append(target_id)
        else:
            print(f"🟢 Target ID '{target_id}' verified in database.")
            found_items[target_id] = match[0]
            
    if missing_ids:
        print(f"❌ Smoke test failed: Missing required target IDs: {missing_ids}")
        sys.exit(1)
        
    # 3. Check for Turkish character leaks in the English blocks of the verified targets
    print("\n--- Scanning Verified Targets for Leakage (Turkish Characters & Blacklist) ---")
    total_violations = 0
    
    for item_id, item in found_items.items():
        print(f"\nScanning item: {item_id}")
        en_block = item.get("en", {})
        question_en = item.get("question_en", "")
        
        # We will scan both question_en and all values inside en block
        texts_to_check = [("question_en", question_en)]
        
        def collect_strings(val, prefix="en"):
            if isinstance(val, str):
                texts_to_check.append((prefix, val))
            elif isinstance(val, list):
                for idx, x in enumerate(val):
                    collect_strings(x, f"{prefix}[{idx}]")
            elif isinstance(val, dict):
                for k, v in val.items():
                    collect_strings(v, f"{prefix}.{k}")
                    
        collect_strings(en_block)
        
        violations_count = 0
        for field, text in texts_to_check:
            # Check Turkish characters
            found_chars = [c for c in text if c in TURKISH_CHARS]
            
            # Check blacklist words
            lower_text = text.lower()
            found_words = [w for w in BLACK_WORDS if w in lower_text]
            
            if found_chars or found_words:
                print(f"  ❌ Violation in field '{field}':")
                print(f"     Snippet: \"{text[:100]}...\"")
                if found_chars:
                    print(f"     Turkish chars found: {list(set(found_chars))}")
                if found_words:
                    print(f"     Blacklisted words found: {found_words}")
                violations_count += 1
                total_violations += 1
                
        if violations_count == 0:
            print("  🟢 100% Clean! No leaks found.")
            
    if total_violations > 0:
        print(f"\n❌ Smoke test failed: Found {total_violations} leakage violations across target items.")
        sys.exit(1)
        
    print("\n=======================================================")
    print("🎉 SUCCESS: Smoke test passed cleanly! All targets are verified and leak-free.")
    print("=======================================================")

if __name__ == "__main__":
    run_smoke_test()
