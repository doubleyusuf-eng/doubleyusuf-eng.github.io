import re

def check_file_braces(path):
    print(f"Checking: {path}")
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    # We find all Drug( blocks
    idx = 0
    drugs = []
    while True:
        idx = content.find('Drug(', idx)
        if idx == -1:
            break
        # Let's match balanced parentheses
        open_pos = idx + 4
        paren_count = 1
        sub_idx = open_pos + 1
        while paren_count > 0 and sub_idx < len(content):
            if content[sub_idx] == '(':
                paren_count += 1
            elif content[sub_idx] == ')':
                paren_count -= 1
            sub_idx += 1
            
        drug_block = content[idx:sub_idx]
        drugs.append((idx, sub_idx, drug_block))
        idx = sub_idx
        
    for i, (start, end, block) in enumerate(drugs):
        drug_id_match = re.search(r'drugId\s*=\s*\"([^\"]+)\"', block)
        drug_id = drug_id_match.group(1) if drug_id_match else f"Index {i}"
        
        # Check braces inside this block
        braces = []
        for char in block:
            if char in '({[':
                braces.append(char)
            elif char in ')}]':
                if not braces:
                    print(f"  Drug {drug_id}: Unmatched closing {char}")
                    continue
                last = braces.pop()
                if (char == ')' and last != '(') or (char == '}' and last != '{') or (char == ']' and last != '['):
                    print(f"  Drug {drug_id}: Mismatched {last} and {char}")
                    
        if braces:
            print(f"  Drug {drug_id}: Unclosed braces {braces}")
            
check_file_braces("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart3.kt")
check_file_braces("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart4.kt")
check_file_braces("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart5.kt")
