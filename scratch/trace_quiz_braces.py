def find_balance(path):
    print(f"File: {path}")
    with open(path, 'r') as f:
        lines = f.readlines()
        
    brace = 0
    stack = []
    
    for i, line in enumerate(lines):
        line_num = i + 1
        stripped = line.strip()
        # ignore comments
        if stripped.startswith("//"):
            continue
        
        for idx, char in enumerate(line):
            if char == '{':
                brace += 1
                stack.append((line_num, stripped))
            elif char == '}':
                brace -= 1
                if stack:
                    stack.pop()
                else:
                    print(f"Extra closing brace at line {line_num}: {stripped}")
                    
        if brace < 0:
            print(f"Line {line_num} caused negative brace balance!")
            return
            
    print(f"Final brace balance: {brace}")
    if brace > 0:
        print("Unclosed braces:")
        for lnum, lcontent in stack:
            print(f"  Line {lnum}: {lcontent}")

find_balance("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/ui/screens/BoardPrepQuizScreen.kt")
