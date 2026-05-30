import os
import re
import json

HTML_DIR = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/Yeni_ilaclar_anesthesia"

def clean_html(text):
    text = re.sub(r'<[^>]+>', ' ', text)
    text = re.sub(r'\s+', ' ', text)
    return text.strip()

def parse_file(filename):
    filepath = os.path.join(HTML_DIR, filename)
    with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
        html = f.read()

    # Extract Title
    title_match = re.search(r'<title>([^<]+)</title>', html)
    title = title_match.group(1) if title_match else filename
    drug_name = title.split(':')[0].strip()

    # Find drug Brand Names
    brand_match = re.search(r'Brand Names:[^<]*US.*?(<ul>.*?</ul>)', html, re.DOTALL)
    brands = []
    if brand_match:
        brands = [clean_html(x) for x in re.findall(r'<li>(.*?)</li>', brand_match.group(1))]

    # Find category
    cat_match = re.search(r'Pharmacologic Category.*?(<ul>.*?</ul>)', html, re.DOTALL)
    category = ""
    if cat_match:
        category = clean_html(cat_match.group(1))

    # Extract key sections using headings
    sections = {}
    headings = [
        ("Dosing: Adult", r'<span class="drugH1">Dosing: Adult</span>(.*?)<div class="[^"]*drugH1Div'),
        ("Dosing: Kidney Impairment: Adult", r'<span class="drugH1">Dosing: Kidney Impairment: Adult</span>(.*?)<div class="[^"]*drugH1Div'),
        ("Dosing: Liver Impairment: Adult", r'<span class="drugH1">Dosing: Liver Impairment: Adult</span>(.*?)<div class="[^"]*drugH1Div'),
        ("Dosing: Obesity: Adult", r'<span class="drugH1">Dosing: Obesity: Adult</span>(.*?)<div class="[^"]*drugH1Div'),
        ("Dosing: Older Adult", r'<span class="drugH1">Dosing: Older Adult</span>(.*?)<div class="[^"]*drugH1Div'),
        ("Dosing: Pediatric", r'<span class="drugH1">Dosing: Pediatric</span>(.*?)<div class="[^"]*drugH1Div'),
        ("Warnings/Precautions", r'<span class="drugH1">Warnings/Precautions</span>(.*?)<div class="[^"]*drugH1Div'),
        ("Pharmacokinetics", r'<span class="drugH1">Pharmacokinetics</span>(.*?)<div class="[^"]*drugH1Div')
    ]

    for sec_name, pattern in headings:
        match = re.search(pattern, html, re.DOTALL)
        if match:
            sections[sec_name] = clean_html(match.group(1))
        else:
            # Try matching until end of text if it's the last section
            fallback_pattern = pattern.split('(.*?)')[0] + r'(.*?)$'
            fallback_match = re.search(fallback_pattern, html, re.DOTALL)
            if fallback_match:
                sections[sec_name] = clean_html(fallback_match.group(1))

    return {
        "drug_name": drug_name,
        "brands": brands,
        "category": category,
        "sections": sections
    }

def main():
    files = [f for f in os.listdir(HTML_DIR) if f.endswith('.html')]
    print(f"Found {len(files)} HTML files.")
    results = {}
    for filename in sorted(files):
        try:
            drug_id = re.sub(r'^\d+_', '', filename).replace('.html', '').replace('-', '_')
            results[drug_id] = parse_file(filename)
            print(f"Parsed: {drug_id}")
        except Exception as e:
            print(f"Error parsing {filename}: {e}")

    with open("/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/parsed_drugs.json", "w", encoding="utf-8") as f:
        json.dump(results, f, ensure_ascii=False, indent=2)
    print("Parsing completed.")

if __name__ == "__main__":
    main()
