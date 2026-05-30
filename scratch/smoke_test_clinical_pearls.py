import json
import os
import sys

def main():
    json_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/assets/daily_clinical_pearls.json"
    
    print("--------------------------------------------------")
    print("   DAILY CLINICAL PEARLS - PROGRAMMATIC SMOKE TEST")
    print("--------------------------------------------------")
    
    # 1. Check if file exists
    if not os.path.exists(json_path):
        print(f"FAIL: JSON file does not exist at {json_path}")
        sys.exit(1)
        
    # 2. Try parsing JSON
    try:
        with open(json_path, "r", encoding="utf-8") as f:
            pearls = json.load(f)
    except Exception as e:
        print(f"FAIL: Failed to parse JSON. Error: {e}")
        sys.exit(1)
        
    print("✓ JSON parse successful.")
    
    # 3. Check counts
    total_pearls = len(pearls)
    print(f"✓ Total clinical pearls found: {total_pearls}")
    
    if total_pearls != 300:
        print(f"FAIL: Expected exactly 300 clinical pearls, but found {total_pearls}.")
        sys.exit(1)
        
    # Allowed Categories
    allowed_categories = {
        "AIRWAY_RESPIRATORY",
        "HEMODYNAMIC_CARDIOVASCULAR",
        "DRUGS_PHARMACOLOGY",
        "REGIONAL_PAIN_PONV",
        "OBSTETRIC_PEDIATRIC_SPECIAL",
        "ICU_CRISIS_SAFETY_POSTOP"
    }
    
    category_counts = {cat: 0 for cat in allowed_categories}
    seen_ids = set()
    errors = []
    
    for idx, pearl in enumerate(pearls):
        p_id = pearl.get("id")
        
        # Check ID
        if not p_id:
            errors.append(f"Pearl at index {idx} has missing or empty 'id'.")
            continue
            
        if p_id in seen_ids:
            errors.append(f"Duplicate ID found: '{p_id}' at index {idx}.")
        seen_ids.add(p_id)
        
        # Check Category
        cat = pearl.get("category")
        if not cat:
            errors.append(f"Pearl '{p_id}' has missing or empty 'category'.")
        elif cat not in allowed_categories:
            errors.append(f"Pearl '{p_id}' has invalid category: '{cat}'. Allowed: {allowed_categories}")
        else:
            category_counts[cat] += 1
            
        # Check Subcategory
        sub = pearl.get("subcategory")
        if not sub or not str(sub).strip():
            errors.append(f"Pearl '{p_id}' has missing or empty 'subcategory'.")
            
        # Check TR Translation
        tr = pearl.get("tr")
        if not tr or not str(tr).strip():
            errors.append(f"Pearl '{p_id}' has missing or empty 'tr' translation.")
            
        # Check EN Translation
        en = pearl.get("en")
        if not en or not str(en).strip():
            errors.append(f"Pearl '{p_id}' has missing or empty 'en' translation.")
            
        # Check Clinical Use
        use = pearl.get("clinicalUse")
        if not use or not str(use).strip():
            errors.append(f"Pearl '{p_id}' has missing or empty 'clinicalUse'.")
            
        # Check Importance
        imp = pearl.get("importance")
        if not imp or not str(imp).strip():
            errors.append(f"Pearl '{p_id}' has missing or empty 'importance'.")
        elif imp.lower() not in ["high", "medium", "low"]:
            errors.append(f"Pearl '{p_id}' has invalid importance level: '{imp}'.")
            
        # Check Tags
        tags = pearl.get("tags")
        if tags is not None and not isinstance(tags, list):
            errors.append(f"Pearl '{p_id}' 'tags' should be an array of strings.")
            
    # Verify exact category distribution
    for cat, count in category_counts.items():
        print(f"  - Category '{cat}': {count} items")
        if cat == "AIRWAY_RESPIRATORY":
            if count != 50:
                errors.append(f"Category 'AIRWAY_RESPIRATORY' does not have exactly 50 items (found {count}).")
        elif cat == "HEMODYNAMIC_CARDIOVASCULAR":
            if count != 50:
                errors.append(f"Category 'HEMODYNAMIC_CARDIOVASCULAR' does not have exactly 50 items (found {count}).")
        elif cat == "DRUGS_PHARMACOLOGY":
            if count != 50:
                errors.append(f"Category 'DRUGS_PHARMACOLOGY' does not have exactly 50 items (found {count}).")
        elif cat == "REGIONAL_PAIN_PONV":
            if count != 50:
                errors.append(f"Category 'REGIONAL_PAIN_PONV' does not have exactly 50 items (found {count}).")
        elif cat == "OBSTETRIC_PEDIATRIC_SPECIAL":
            if count != 50:
                errors.append(f"Category 'OBSTETRIC_PEDIATRIC_SPECIAL' does not have exactly 50 items (found {count}).")
        else:
            if count != 50:
                errors.append(f"Category '{cat}' does not have exactly 50 items (found {count}).")
            
    if errors:
        print("\n--------------------------------------------------")
        print("FAIL: Quality audits encountered errors:")
        for err in errors:
            print(f"  - {err}")
        print("--------------------------------------------------")
        sys.exit(1)
        
    print("\n✓ 0 duplicate IDs found.")
    print("✓ 0 empty fields (id, category, subcategory, tr, en, clinicalUse, importance) found.")
    print("✓ AIRWAY_RESPIRATORY has exactly 50 items.")
    print("✓ HEMODYNAMIC_CARDIOVASCULAR has exactly 50 items.")
    print("✓ DRUGS_PHARMACOLOGY has exactly 50 items.")
    print("✓ REGIONAL_PAIN_PONV has exactly 50 items.")
    print("✓ OBSTETRIC_PEDIATRIC_SPECIAL has exactly 50 items.")
    print("✓ ICU_CRISIS_SAFETY_POSTOP has exactly 50 items.")
    print("--------------------------------------------------")
    print("STATUS: SMOKE TEST PASSED SUCCESSFULLY!")
    print("--------------------------------------------------")
    sys.exit(0)

if __name__ == "__main__":
    main()
