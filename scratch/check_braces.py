import re

def check_braces(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    lines = content.split('\n')
    open_braces = 0
    stack = []
    
    for i, line in enumerate(lines):
        line_num = i + 1
        # strip comments
        line_clean = re.sub(r'//.*', '', line)
        line_clean = re.sub(r'".*?"', '""', line_clean)
        for char in line_clean:
            if char == '{':
                open_braces += 1
                stack.append((line_num, line.strip()))
            elif char == '}':
                open_braces -= 1
                if stack:
                    stack.pop()
                else:
                    print(f"Extra closing brace at line {line_num}: {line}")
        if line_num >= 1580:
            print(f"Line {line_num} ({open_braces}): {line.strip()}")
            
    print(f"\nFinal open braces: {open_braces}")

check_braces('/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/ui/screens/BoardPrepQuizScreen.kt')
