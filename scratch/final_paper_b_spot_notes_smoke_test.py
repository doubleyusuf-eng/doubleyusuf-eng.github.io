import json
import os
import random
import sys

SPOT_NOTES_PATH = "app/src/main/assets/board_spot_notes.json"

def run_paper_b_smoke_test():
    print("=======================================================")
    print("🎯 FINAL EDAIC PAPER B SPOT NOTES QUALITY & AUDIT 🎯")
    print("=======================================================\n")

    # 1. Parse JSON File
    if not os.path.exists(SPOT_NOTES_PATH):
        print(f"❌ ERROR: Spot notes database file not found at path: {SPOT_NOTES_PATH}")
        sys.exit(1)
        
    try:
        with open(SPOT_NOTES_PATH, "r", encoding="utf-8") as f:
            all_notes = json.load(f)
        print("🟢 JSON PARSE TEST: Passed (Perfect syntax).")
    except Exception as e:
        print(f"❌ JSON PARSE TEST: Failed to parse JSON file! Details:\n   {e}")
        sys.exit(1)

    # Filter for EDAIC_PAPER_B
    paper_b_notes = [n for n in all_notes if n.get("exam") == "EDAIC_PAPER_B"]
    paper_a_notes = [n for n in all_notes if n.get("exam") == "EDAIC_PAPER_A"]
    
    print(f"📈 TOTAL ITEM COUNT: {len(all_notes)} spot notes in database.")
    print(f"   • EDAIC_PAPER_A count: {len(paper_a_notes)}")
    print(f"   • EDAIC_PAPER_B count: {len(paper_b_notes)}")

    # 2. Count Verification
    if len(paper_b_notes) == 200:
        print("🟢 ITEM COUNT VERIFICATION: Passed. Exactly 200 spot notes for EDAIC_PAPER_B.")
    else:
        print(f"❌ ITEM COUNT VERIFICATION: Failed! Expected 200, but found {len(paper_b_notes)} for EDAIC_PAPER_B.")
        sys.exit(1)

    if len(paper_a_notes) == 200:
        print("🟢 PAPER A INTEGRITY VERIFICATION: Passed. Exactly 200 spot notes for EDAIC_PAPER_A remain untouched.")
    else:
        print(f"❌ PAPER A INTEGRITY VERIFICATION: Failed! Expected 200, but found {len(paper_a_notes)} for EDAIC_PAPER_A.")
        sys.exit(1)

    # 3. Duplicate ID Check
    seen_ids = set()
    duplicate_ids = []
    for note in all_notes:
        note_id = note.get("id")
        if note_id in seen_ids:
            duplicate_ids.append(note_id)
        seen_ids.add(note_id)

    if duplicate_ids:
        print(f"❌ DUPLICATE ID CHECK: Failed! Found duplicates: {set(duplicate_ids)}")
        sys.exit(1)
    else:
        print("🟢 DUPLICATE ID CHECK: Passed. 0 duplicate IDs found across all 400 items.")

    # 4. Empty/Null Field Audit
    empty_content_errors = 0
    for idx, note in enumerate(all_notes):
        note_id = note.get("id", f"index_{idx}")
        for field in ["tr", "en", "topic", "subtopic", "exam", "id"]:
            val = note.get(field)
            if val is None or (isinstance(val, str) and not val.strip()):
                print(f"   ❌ Note '{note_id}': Field '{field}' is empty or null.")
                empty_content_errors += 1

    if empty_content_errors == 0:
        print("🟢 CONTENT COMPLETENESS AUDIT: Passed. 0 empty fields found across TR/EN/Topic/Subtopic.")
    else:
        print(f"❌ CONTENT COMPLETENESS AUDIT: Failed with {empty_content_errors} errors.")
        sys.exit(1)

    # 5. Search Simulation
    print("\n🔍 SIMULATING IN-MEMORY SEARCH QUERIES FOR PAPER B KEYWORDS:")
    keywords = ["CICO", "spinal hypotension", "LAST", "malignant hyperthermia", "OSA", "TRALI", "pneumoperitoneum"]
    for kw in keywords:
        matches = []
        for note in paper_b_notes:
            text = (note["tr"] + " " + note["en"] + " " + " ".join(note["tags"]) + " " + note["topic"] + " " + note["subtopic"]).lower()
            if kw.lower() in text:
                matches.append(note["id"])
        
        if matches:
            print(f"   • Search query '{kw}': found {len(matches)} matching spot notes.")
            print(f"     [First Match: {matches[0]}] - EN: \"{paper_b_notes[int(matches[0].split('_')[-1])-1]['en'][:60]}...\"")
        else:
            print(f"   ⚠️ Search query '{kw}': no direct matches in Paper B notes.")

    # 6. Random 10 Simulation
    print("\n🎲 SIMULATING RANDOM 10 SELECTION FOR PAPER B:")
    try:
        sampled = random.sample(paper_b_notes, 10)
        sample_ids = [n["id"] for n in sampled]
        print(f"   🟢 Selected 10 random notes successfully. Sample IDs: {sample_ids}")
    except Exception as e:
        print(f"   ❌ Random selection failed! Details: {e}")
        sys.exit(1)

    print("\n=======================================================")
    print("🎉 SUCCESS: EDAIC Paper B Spot Notes validation passed perfectly!")
    print("=======================================================")

if __name__ == "__main__":
    run_paper_b_smoke_test()
