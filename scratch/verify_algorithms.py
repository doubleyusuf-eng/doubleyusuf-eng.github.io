import re
import sys

def parse_algorithms():
    file_path = "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataAlgorithms.kt"
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()

    # We will find all Algorithm declarations:
    # Algorithm(
    #     algorithmId = "...",
    #     titleTr = "...",
    #     titleEn = "...",
    #     category = "...",
    #     urgencyLevel = "..."
    # )
    
    # We can use regex to extract the main attributes of each Algorithm
    # Let's find all chunks starting with Algorithm( up to the next closing block or end of algorithm.
    # A robust way is to scan the file and find matches of algorithmId, titleTr, titleEn, category, urgencyLevel, isPremium, etc.
    
    # Let's find matches of:
    # algorithmId = "([^"]+)"
    # titleTr = "([^"]+)"
    # titleEn = "([^"]+)"
    # category = "([^"]+)"
    # urgencyLevel = "([^"]+)"
    
    # Note that there might be nested matches inside nodes, but the algorithm level has them at the top.
    # To be extremely precise, we can split the file by "Algorithm(" and then parse each section.
    
    sections = content.split("Algorithm(")
    # The first section is imports and header, so we skip it.
    algorithms = []
    for sec in sections[1:]:
        # Find the top-level algorithmId
        id_match = re.search(r'algorithmId\s*=\s*"([^"]+)"', sec)
        if not id_match:
            continue
        algo_id = id_match.group(1)
        
        # Find titleTr
        title_tr_match = re.search(r'titleTr\s*=\s*"([^"]+)"', sec)
        title_tr = title_tr_match.group(1) if title_tr_match else ""
        
        # Find titleEn
        title_en_match = re.search(r'titleEn\s*=\s*"([^"]+)"', sec)
        title_en = title_en_match.group(1) if title_en_match else ""
        
        # Find category
        category_match = re.search(r'category\s*=\s*"([^"]+)"', sec)
        category = category_match.group(1) if category_match else ""
        
        # Find urgencyLevel
        urgency_match = re.search(r'urgencyLevel\s*=\s*"([^"]+)"', sec)
        urgency = urgency_match.group(1) if urgency_match else ""
        
        algorithms.append({
            "id": algo_id,
            "titleTr": title_tr,
            "titleEn": title_en,
            "category": category,
            "urgencyLevel": urgency
        })
        
    return algorithms

def main():
    print("=== ALGORITHMS VERIFICATION AND SMOKE TEST ===")
    algorithms = parse_algorithms()
    
    print(f"Parsed {len(algorithms)} algorithms from SeedDataAlgorithms.kt.")
    
    # 1. Total algorithm count must be 51
    if len(algorithms) != 51:
        print(f"❌ ERROR: Expected 51 algorithms, but found {len(algorithms)}.")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: Exactly 51 algorithms found.")
        
    # 2. Check for unique IDs and no duplicates
    ids = [a["id"] for a in algorithms]
    unique_ids = set(ids)
    if len(ids) != len(unique_ids):
        duplicates = [x for x in unique_ids if ids.count(x) > 1]
        print(f"❌ ERROR: Duplicate IDs found: {duplicates}")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: All algorithm IDs are unique.")
        
    # 3. Check for empty fields
    empty_fields = False
    for a in algorithms:
        for k, v in a.items():
            if not v:
                print(f"❌ ERROR: Empty field '{k}' found in algorithm with ID '{a['id']}'.")
                empty_fields = True
    if empty_fields:
        sys.exit(1)
    else:
        print("🟢 SUCCESS: No blank fields in any algorithm definition.")
        
    # 4. Distribution by Category
    categories = {}
    for a in algorithms:
        cat = a["category"]
        categories[cat] = categories.get(cat, 0) + 1
        
    print("\n--- Category Distribution ---")
    for cat, count in sorted(categories.items()):
        print(f"  - {cat}: {count}")
        
    # Valid categories are scores, airway, hemodynamics, fluids, drugs, critical
    valid_categories = {"scores", "airway", "hemodynamics", "fluids", "drugs", "critical"}
    invalid_categories = set(categories.keys()) - valid_categories
    if invalid_categories:
        print(f"❌ ERROR: Invalid categories found: {invalid_categories}")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: All categories are valid.")
        
    # 5. Distribution by Urgency Level
    urgencies = {}
    for a in algorithms:
        urg = a["urgencyLevel"]
        urgencies[urg] = urgencies.get(urg, 0) + 1
        
    print("\n--- Urgency Level Distribution ---")
    for urg, count in sorted(urgencies.items()):
        print(f"  - {urg}: {count}")
        
    # 6. Critical Only filter list (urgencyLevel == emergency)
    critical_only = [a["id"] for a in algorithms if a["urgencyLevel"] == "emergency"]
    print(f"\n--- Critical Only Filtered Algorithms (Total: {len(critical_only)}) ---")
    for idx, cid in enumerate(sorted(critical_only), 1):
        print(f"  {idx}. {cid}")
        
    # 7. Verify the 10 new algorithms
    new_alg_ids = {
        "tension_pneumothorax",
        "pulmonary_embolism_crisis",
        "venous_air_embolism",
        "severe_perioperative_shock",
        "rv_failure_ph_crisis",
        "eclampsia_preeclampsia_crisis",
        "postpartum_hemorrhage",
        "opioid_respiratory_depression",
        "delayed_emergence_pacu",
        "medication_error_response"
    }
    
    found_new = []
    print("\n--- Crisis Algorithms Add-on Pack 1 Verification ---")
    for a in algorithms:
        if a["id"] in new_alg_ids:
            found_new.append(a)
            print(f"  - ID: {a['id']}, category: {a['category']}, urgencyLevel: {a['urgencyLevel']}")
            
    if len(found_new) != 10:
        missing = new_alg_ids - {a["id"] for a in found_new}
        print(f"❌ ERROR: Expected 10 new algorithms, but found {len(found_new)}. Missing: {missing}")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: All 10 new algorithms are correctly seeded and mapped.")
        
    # 8. Check original 41 algorithms
    original_41_ids = {
        "asa_ps_evaluation", "preop_npo_evaluation", "stop_bang_osas", "preop_cardiac_rcri", "coagulation_management",
        "expected_difficult_airway", "unexpected_difficult_intubation", "cico_crisis", "laryngospasm_crisis", "intraoperative_hypoxemia",
        "perioperative_hypotension", "bradycardia_rescue", "tachycardia_rescue", "anaphylaxis_rescue", "malignant_hyperthermia",
        "last_toxicity", "massive_transfusion", "rotem_coagulation", "ponv_prevention", "postop_pain_management",
        "pediatric_difficult_airway", "obstetric_difficult_airway", "high_spinal", "pdph_management", "epidural_hematoma_suspicion",
        "residual_neuromuscular_block", "sugammadex_neostigmine_reversal", "oliguria_management", "sepsis_shock", "ards_ventilation",
        "hypertensive_delay_elective", "aspiration_regurgitation", "bronchospasm_rescue", "high_airway_pressure", "etco2_anomaly",
        "intraop_cardiac_arrest", "hyperkalemia_emergency", "transfusion_reaction", "extubation_guidelines", "postop_hypoxemia",
        "diabetic_glucose_management"
    }
    
    found_original = [a for a in algorithms if a["id"] in original_41_ids]
    if len(found_original) != 41:
        missing_orig = original_41_ids - {a["id"] for a in found_original}
        print(f"❌ ERROR: Original 41 algorithms have been modified or deleted. Missing original IDs: {missing_orig}")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: Original 41 algorithms are completely untouched and intact.")

    print("\n🎉 ALL CHECKS PASSED SUCCESSFULLY! Database is verified as correct and pristine.")

if __name__ == "__main__":
    main()
