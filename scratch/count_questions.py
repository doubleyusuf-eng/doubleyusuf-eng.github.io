import json
import os

assets_dir = "app/src/main/assets"
packs = ["edaic_part_i", "aba_basic", "aba_advanced", "viva_scenarios", "edaic_paper_a_pack_1", "edaic_paper_b_pack_1", "aba_basic_pack_1", "aba_advanced_pack_1", "edaic_viva_pack_1"]

for packId in packs:
    filename = {
        "edaic_part_i": "seed_board_prep_edaic_part_i.json",
        "aba_basic": "seed_board_prep_aba_basic.json",
        "aba_advanced": "seed_board_prep_aba_advanced.json",
        "viva_scenarios": "seed_board_prep_viva_scenario.json",
        "edaic_paper_a_pack_1": "seed_board_prep_edaic_paper_a_pack_1.json",
        "edaic_paper_b_pack_1": "seed_board_prep_edaic_paper_b_pack_1.json",
        "aba_basic_pack_1": "seed_board_prep_aba_basic_pack_1.json",
        "aba_advanced_pack_1": "seed_board_prep_aba_advanced_pack_1.json",
        "edaic_viva_pack_1": "seed_board_prep_edaic_viva_pack_1.json"
    }[packId]
    
    path = os.path.join(assets_dir, filename)
    with open(path, 'r') as f:
        data = json.load(f)
        
    print(f"File {filename}: type={type(data)}")
    if isinstance(data, dict):
        print(f"  keys: {list(data.keys())}")
        for k in data.keys():
            if isinstance(data[k], list):
                print(f"    key '{k}' is a list of size {len(data[k])}")
    elif isinstance(data, list):
        print(f"  list of size {len(data)}")
        if len(data) > 0:
            print(f"    first element type: {type(data[0])}")
