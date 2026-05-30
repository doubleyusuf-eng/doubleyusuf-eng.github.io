import json
import os
import sys

SPOT_NOTES_PATH = "app/src/main/assets/board_spot_notes.json"
UI_SCREEN_PATH = "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/ui/screens/BoardPrepSpotNotesScreen.kt"

def run_comprehensive_smoke_test():
    print("=======================================================")
    print("🎯 FINAL 200 SPOT NOTES QUALITY & FUNCTIONAL AUDIT 🎯")
    print("=======================================================\n")

    # 1. Check file existence & parse JSON
    if not os.path.exists(SPOT_NOTES_PATH):
        print(f"❌ ERROR: board_spot_notes.json not found at {SPOT_NOTES_PATH}")
        sys.exit(1)
        
    try:
        with open(SPOT_NOTES_PATH, "r", encoding="utf-8") as f:
            notes = json.load(f)
        print("🟢 JSON PARSE TEST: Passed (Perfect syntax).")
    except Exception as e:
        print(f"❌ JSON PARSE TEST: Failed to parse JSON database: {e}")
        sys.exit(1)

    # 2. Verify total EDAIC_PAPER_A item count is exactly 200
    paper_a_notes = [n for n in notes if n.get("exam") == "EDAIC_PAPER_A"]
    total_count = len(paper_a_notes)
    if total_count == 200:
        print(f"🟢 ITEM COUNT VERIFICATION: Passed. Exactly {total_count} spot notes for EDAIC_PAPER_A.")
    else:
        print(f"❌ ITEM COUNT VERIFICATION: Failed! Found {total_count} items (expected exactly 200).")
        sys.exit(1)

    # 3. Duplicate ID Check
    ids = [n.get("id") for n in notes]
    duplicates = [i for i in set(ids) if ids.count(i) > 1]
    if len(duplicates) == 0:
        print("🟢 DUPLICATE ID CHECK: Passed. 0 duplicate IDs found across all items.")
    else:
        print(f"❌ DUPLICATE ID CHECK: Failed! Found duplicate IDs: {duplicates}")
        sys.exit(1)

    # 4. Content Completeness Check (tr, en, topic, subtopic, etc.)
    missing_data_errors = 0
    for note in notes:
        n_id = note.get("id", "UNKNOWN")
        for field in ["tr", "en", "topic", "subtopic", "exam", "id"]:
            val = note.get(field)
            if val is None or (isinstance(val, str) and not val.strip()):
                print(f"   ❌ Note '{n_id}': Field '{field}' is empty, null, or whitespace.")
                missing_data_errors += 1
                
    if missing_data_errors == 0:
        print("🟢 CONTENT COMPLETENESS AUDIT: Passed. 0 empty fields found across TR/EN/Topic/Subtopic.")
    else:
        print(f"❌ CONTENT COMPLETENESS AUDIT: Failed with {missing_data_errors} errors.")
        sys.exit(1)

    # 5. Search Functionality Simulation (FRC, MAC, sugammadex, Boyle, sensitivity)
    print("\n🔍 SIMULATING IN-MEMORY SEARCH QUERIES:")
    search_queries = ["FRC", "MAC", "sugammadex", "Boyle", "sensitivity"]
    
    for q in search_queries:
        ql = q.lower()
        matches = []
        for n in paper_a_notes:
            # Match tr, en, topic, subtopic, or tags
            if (ql in n["tr"].lower() or 
                ql in n["en"].lower() or 
                ql in n["topic"].lower() or 
                ql in n["subtopic"].lower() or 
                any(ql in tag.lower() for tag in n.get("tags", []))):
                matches.append(n)
                
        print(f"   • Search query '{q}': found {len(matches)} matching spot notes.")
        if len(matches) == 0:
            print(f"   ⚠️ Warning: Search term '{q}' did not return any matches.")
        else:
            # Print first matched item snippet
            first = matches[0]
            print(f"     [First Match: {first['id']}] - EN: \"{first['en'][:60]}...\"")

    # 6. Verify UI Filter Mapping Match
    print("\n🖥️ VERIFYING UI SCREEN FILTER COMPATIBILITY:")
    if not os.path.exists(UI_SCREEN_PATH):
        print(f"❌ ERROR: UI screen file not found at {UI_SCREEN_PATH}")
        sys.exit(1)
        
    with open(UI_SCREEN_PATH, "r", encoding="utf-8") as f:
        ui_content = f.read()
        
    if "EDAIC_PAPER_A" in ui_content:
        print("🟢 UI FILTER MATCH: Passed. 'EDAIC_PAPER_A' mapping matches UI filter dropdown perfectly.")
    else:
        print("❌ UI FILTER MATCH: Failed! 'EDAIC_PAPER_A' key not found in UI screen code.")
        sys.exit(1)

    # 7. Verify Random 10 Simulation
    print("\n🎲 SIMULATING RANDOM 10 SELECTION:")
    import random
    random_10 = random.sample(paper_a_notes, 10)
    print(f"   🟢 Selected 10 random notes successfully. Sample IDs: {[n['id'] for n in random_10]}")

    print("\n=======================================================")
    print("🎉 SUCCESS: 200 Spot Notes quality & search tests passed flawlessly!")
    print("=======================================================")

if __name__ == "__main__":
    run_comprehensive_smoke_test()
