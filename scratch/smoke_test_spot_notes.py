import json
import os
import sys

SPOT_NOTES_PATH = "app/src/main/assets/board_spot_notes.json"

REQUIRED_KEYS = [
    "id", "exam", "topic", "subtopic", "tr", "en", "importance", "tags", "sourceType", "lastReviewed"
]

def run_spot_notes_smoke_test():
    print("=======================================================")
    print("🔬 RUNNING BOARD SPOT NOTES INTEGRATION & QUALITY AUDIT 🔬")
    print("=======================================================\n")

    # 1. Parse JSON File
    if not os.path.exists(SPOT_NOTES_PATH):
        print(f"❌ ERROR: Spot notes database file not found at path: {SPOT_NOTES_PATH}")
        sys.exit(1)
        
    try:
        with open(SPOT_NOTES_PATH, "r", encoding="utf-8") as f:
            notes = json.load(f)
        print(f"🟢 JSON PARSE TEST: Passed. Loaded {len(notes)} spot notes from assets.")
    except Exception as e:
        print(f"❌ JSON PARSE TEST: Failed to parse JSON file! Details:\n   {e}")
        sys.exit(1)

    print(f"\n📈 TOTAL ITEM COUNT: {len(notes)}")

    # 2. Exam Distribution
    print("\n📊 EXAM DISTRIBUTION BREAKDOWN:")
    exam_counts = {}
    for idx, note in enumerate(notes):
        exam = note.get("exam", "UNKNOWN")
        exam_counts[exam] = exam_counts.get(exam, 0) + 1
        
    for exam, count in sorted(exam_counts.items()):
        print(f"   • {exam}: {count} spot notes")

    # 3. Duplicate ID Check
    print("\n🔍 ID UNIQUENESS AUDIT:")
    ids = []
    duplicate_ids = []
    seen_ids = set()
    for note in notes:
        note_id = note.get("id")
        if note_id:
            if note_id in seen_ids:
                duplicate_ids.append(note_id)
            seen_ids.add(note_id)
            ids.append(note_id)
            
    if duplicate_ids:
        print(f"❌ DUPLICATE ID CHECK: Failed! Found {len(duplicate_ids)} duplicate IDs:")
        for dup in set(duplicate_ids):
            print(f"   • Duplicate ID: '{dup}'")
        sys.exit(1)
    else:
        print("🟢 DUPLICATE ID CHECK: Passed! 0 duplicate IDs found. All records have unique identifiers.")

    # 4. Empty/Null Field Audit (TR/EN Content, Topic/Subtopic)
    print("\n📝 CONTENT COMPLETENESS AUDIT:")
    missing_fields_errors = 0
    empty_content_errors = 0
    invalid_tags_errors = 0
    
    for idx, note in enumerate(notes):
        note_id = note.get("id", f"index_{idx}")
        
        # Check all required keys exist
        missing_keys = [k for k in REQUIRED_KEYS if k not in note]
        if missing_keys:
            print(f"   ❌ Note '{note_id}': Missing required keys {missing_keys}")
            missing_fields_errors += len(missing_keys)
            
        # Verify non-empty and non-whitespace values for critical fields
        for field in ["tr", "en", "topic", "subtopic", "exam", "id"]:
            val = note.get(field)
            if val is None or (isinstance(val, str) and not val.strip()):
                print(f"   ❌ Note '{note_id}': Field '{field}' is empty or null.")
                empty_content_errors += 1
                
        # Verify tags structure
        tags = note.get("tags")
        if tags is not None:
            if not isinstance(tags, list):
                print(f"   ❌ Note '{note_id}': 'tags' is of type {type(tags).__name__} (expected list).")
                invalid_tags_errors += 1
            else:
                for t_idx, t in enumerate(tags):
                    if not isinstance(t, str) or not t.strip():
                        print(f"   ❌ Note '{note_id}': Tag at index {t_idx} is empty or invalid.")
                        invalid_tags_errors += 1
                        
    # Print results of completeness audit
    if missing_fields_errors == 0 and empty_content_errors == 0 and invalid_tags_errors == 0:
        print("🟢 CONTENT COMPLETENESS CHECK: Passed! All notes are complete, typed correctly, and contain valid text.")
    else:
        print(f"❌ CONTENT COMPLETENESS CHECK: Failed with errors:")
        if missing_fields_errors > 0:
            print(f"   • Missing fields errors: {missing_fields_errors}")
        if empty_content_errors > 0:
            print(f"   • Empty/null TR/EN/Topic/Subtopic content errors: {empty_content_errors}")
        if invalid_tags_errors > 0:
            print(f"   • Invalid tags errors: {invalid_tags_errors}")
        sys.exit(1)

    print("\n=======================================================")
    print("🎉 SUCCESS: All spot notes tests passed perfectly!")
    print("=======================================================")

if __name__ == "__main__":
    run_spot_notes_smoke_test()
