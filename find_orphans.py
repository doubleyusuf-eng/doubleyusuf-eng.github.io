import re

def find_orphan_fields(path):
    print(f"File: {path}")
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    # We find all Drug( blocks using paren balancing
    idx = 0
    blocks = []
    while True:
        idx = content.find('Drug(', idx)
        if idx == -1:
            break
        open_pos = idx + 4
        paren_count = 1
        sub_idx = open_pos + 1
        while paren_count > 0 and sub_idx < len(content):
            if content[sub_idx] == '(':
                paren_count += 1
            elif content[sub_idx] == ')':
                paren_count -= 1
            sub_idx += 1
        blocks.append((idx, sub_idx, content[idx:sub_idx]))
        idx = sub_idx
        
    # Let's check if the word "contraindications" occurs outside the parsed blocks
    # We can create a mask of all characters in content, set to True if inside a block
    in_block = [False] * len(content)
    for start, end, block in blocks:
        for j in range(start, end):
            in_block[j] = True
            
    # Search for "contraindications" in content
    for m in re.finditer(r'contraindications', content):
        start_pos = m.start()
        if not in_block[start_pos]:
            print(f"  Orphan 'contraindications' found at position {start_pos}!")
            # Print around it
            surround = content[max(0, start_pos-200):min(len(content), start_pos+200)]
            print(f"  Surrounding text:\n{surround}\n" + "-"*50)

find_orphan_fields("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart3.kt")
find_orphan_fields("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart4.kt")
find_orphan_fields("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart5.kt")
