import os
import re
import sys
sys.path.append('.')
import clean_drug_doses

clean_drugs = clean_drug_doses.clean_drugs

# Add cefuroxime to clean_drugs
clean_drugs["cefuroxime"] = {
    "drugClass": "İkinci Kuşak Sefalosporin (Antibiyotik)",
    "adultDoses": """mapOf(
                "cerrahi_profilaksi" to DoseInfo("1.5 g IV", "Cerrahi insizyondan 30-60 dakika önce yavaş infüzyon şeklinde uygulanır.")
            )""",
    "pediatricDoses": """mapOf(
                "cerrahi_profilaksi" to DoseInfo("30 mg/kg IV", "İnsizyondan 30-60 dakika önce yavaş bolus enjeksiyon.")
            )""",
    "specialPopulations": """mapOf(
                "renal_yetmezlik" to "Böbrek yetmezliğinde (eGFR < 30) doz sıklığı azaltılmalıdır.",
                "karaciger_yetmezligi" to "Karaciğer yetmezliğinde doz ayarlaması gerekmez.",
                "obezite" to "Obezite hastalarında dozaj ayarlaması gerekmez.",
                "geriatri" to "Yaşlılarda böbrek fonksiyonları izlenmelidir."
            )""",
    "warnings": 'listOf("Penisilin alerjisi olan hastalarda çapraz duyarlılık riski (%1-10) göz önünde bulundurulmalıdır.", "Hızlı IV bolus uygulamalarında bulantı ve kusma yapabilir.")',
    "contraindications": 'listOf("Sefalosporin grubu antibiyotiklere karşı bilinen aşırı duyarlılık")',
}

def extract_field_value(block, field_name):
    pattern = rf'{field_name}\s*=\s*'
    match = re.search(pattern, block)
    if not match:
        return None
    start_pos = match.end()
    
    val_text = block[start_pos:].strip()
    if val_text.startswith('"'):
        end_idx = 1
        while end_idx < len(val_text):
            if val_text[end_idx] == '"' and val_text[end_idx-1] != '\\':
                break
            end_idx += 1
        return val_text[:end_idx+1]
    
    if val_text.startswith('listOf(') or val_text.startswith('mapOf('):
        open_idx = val_text.find('(')
        paren_count = 1
        sub_idx = open_idx + 1
        while paren_count > 0 and sub_idx < len(val_text):
            if val_text[sub_idx] == '(':
                paren_count += 1
            elif val_text[sub_idx] == ')':
                paren_count -= 1
            sub_idx += 1
        return val_text[:sub_idx]
        
    for keyword in ['emptyMap()', 'emptyList()', 'null', 'true', 'false']:
        if val_text.startswith(keyword):
            return keyword
            
    comma_idx = val_text.find(',')
    if comma_idx != -1:
        return val_text[:comma_idx].strip()
    return val_text.strip()

def parse_drug_blocks(content):
    blocks = []
    idx = 0
    while True:
        idx = content.find('Drug(', idx)
        if idx == -1:
            break
            
        open_pos = idx + 4
        paren_count = 1
        sub_idx = open_pos + 1
        
        in_string = False
        in_escape = False
        in_single_comment = False
        in_multi_comment = False
        
        while paren_count > 0 and sub_idx < len(content):
            char = content[sub_idx]
            
            if in_single_comment:
                if char == '\n':
                    in_single_comment = False
            elif in_multi_comment:
                if char == '/' and content[sub_idx-1] == '*':
                    in_multi_comment = False
            elif in_string:
                if in_escape:
                    in_escape = False
                elif char == '\\':
                    in_escape = True
                elif char == '"':
                    in_string = False
            else:
                if char == '/' and sub_idx + 1 < len(content) and content[sub_idx+1] == '/':
                    in_single_comment = True
                elif char == '/' and sub_idx + 1 < len(content) and content[sub_idx+1] == '*':
                    in_multi_comment = True
                elif char == '"':
                    in_string = True
                elif char == '(':
                    paren_count += 1
                elif char == ')':
                    paren_count -= 1
                    
            sub_idx += 1
            
        blocks.append(content[idx:sub_idx])
        idx = sub_idx
    return blocks

def clean_file(path, part_name):
    if not os.path.exists(path):
        print(f"File not found: {path}")
        return
        
    print(f"Processing & Reconstructing: {path}")
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    raw_blocks = parse_drug_blocks(content)
    
    new_blocks = []
    for block in raw_blocks:
        # Extract drugId
        drug_id_match = re.search(r'drugId\s*=\s*\"([^\"]+)\"', block)
        if not drug_id_match:
            continue
        drug_id = drug_id_match.group(1)
        
        if drug_id in clean_drugs:
            print(f"  Cleaning drugId: {drug_id}")
            clean_data = clean_drugs[drug_id]
            
            # Extract other clean fields
            nameTr = extract_field_value(block, "nameTr").strip('"')
            nameEn = extract_field_value(block, "nameEn").strip('"')
            genericName = extract_field_value(block, "genericName").strip('"')
            
            brandNames = extract_field_value(block, "brandNames")
            if not brandNames or brandNames == "None":
                brandNames = "listOf()"
                
            category = extract_field_value(block, "category").strip('"')
            mechanismTr = extract_field_value(block, "mechanismTr").strip('"')
            
            clinicalPearls = extract_field_value(block, "clinicalPearls")
            if not clinicalPearls or clinicalPearls == "None":
                clinicalPearls = "listOf()"
            
            # Reconstruct clean block
            new_block = f'''        Drug(
            drugId = "{drug_id}",
            nameTr = "{nameTr}",
            nameEn = "{nameEn}",
            genericName = "{genericName}",
            brandNames = {brandNames},
            category = "{category}",
            drugClass = "{clean_data['drugClass']}",
            mechanismTr = "{mechanismTr}",
            adultDoses = {clean_data['adultDoses']},
            pediatricDoses = {clean_data['pediatricDoses']},
            specialPopulations = {clean_data['specialPopulations']},
            pharmacokinetics = null,
            contraindications = {clean_data['contraindications']},
            warnings = {clean_data['warnings']},
            clinicalPearls = {clinicalPearls},
            requiresClinicalValidation = false
        )'''
            new_blocks.append(new_block)
        else:
            print(f"  Keeping standard drugId: {drug_id}")
            # Ensure proper indent for existing block
            lines = [("        " + line.strip() if line.strip() else "") for line in block.split('\n')]
            new_blocks.append("\n".join(lines))
            
    # Reconstruct the entire Kotlin file structure
    new_content = f'''package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo

object {part_name} {{
    val drugsList = listOf(
''' + ",\n".join(new_blocks) + '''
    )
}
'''
            
    with open(path, 'w', encoding='utf-8') as f:
        f.write(new_content)
    print(f"Finished rebuilding: {path}")

def main():
    part_files = {
        "SeedDataDrugsPart3": "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart3.kt",
        "SeedDataDrugsPart4": "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart4.kt",
        "SeedDataDrugsPart5": "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart5.kt"
    }
    for part_name, path in part_files.items():
        clean_file(path, part_name)

if __name__ == '__main__':
    main()
