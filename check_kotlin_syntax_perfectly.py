import re

def strip_strings_and_comments(code):
    # Strip multi-line comments /* ... */
    code = re.sub(r'/\*.*?\*/', '', code, flags=re.DOTALL)
    # Strip single-line comments // ...
    code = re.sub(r'//.*?\n', '\n', code)
    
    # Strip string literals (taking care of escaped quotes)
    # Match double quoted string
    pattern = r'"([^"\\]|\\.)*"'
    code = re.sub(pattern, '""', code)
    return code

def find_balance(path):
    print(f"Tracing balanced syntax (ignoring strings/comments) for: {path}")
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    clean_code = strip_strings_and_comments(content)
    
    paren = 0
    brace = 0
    bracket = 0
    
    lines = clean_code.split('\n')
    for i, line in enumerate(lines):
        line_num = i + 1
        for char in line:
            if char == '(':
                paren += 1
            elif char == ')':
                paren -= 1
                if paren < 0:
                    print(f"  Line {line_num}: Extra closing parenthesis ')'! paren={paren}")
                    print(f"    Line: {line.strip()}")
                    return False
            elif char == '{':
                brace += 1
            elif char == '}':
                brace -= 1
                if brace < 0:
                    print(f"  Line {line_num}: Extra closing brace '}}'! brace={brace}")
                    print(f"    Line: {line.strip()}")
                    return False
            elif char == '[':
                bracket += 1
            elif char == ']':
                bracket -= 1
                if bracket < 0:
                    print(f"  Line {line_num}: Extra closing bracket ']'! bracket={bracket}")
                    print(f"    Line: {line.strip()}")
                    return False
                    
        # Check if early close of val list
        if brace == 0 and line_num > 7 and line_num < len(lines) - 2:
            print(f"  Line {line_num}: Object closed too early! brace={brace}, paren={paren}")
            print(f"    Line: {line.strip()}")
            return False
            
    if paren != 0 or brace != 0 or bracket != 0:
        print(f"  Unbalanced at end: paren={paren}, brace={brace}, bracket={bracket}")
        return False
        
    print("  Perfect balance!")
    return True

find_balance("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart3.kt")
find_balance("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart4.kt")
find_balance("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart5.kt")
