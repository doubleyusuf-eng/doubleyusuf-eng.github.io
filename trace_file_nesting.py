def trace_file_nesting(path):
    print(f"Tracing nesting for: {path}")
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    paren = 0
    brace = 0
    bracket = 0
    
    # We will trace characters and print when the level drops back to 0
    lines = content.split('\n')
    for i, line in enumerate(lines):
        line_num = i + 1
        for char in line:
            if char == '(':
                paren += 1
            elif char == ')':
                paren -= 1
            elif char == '{':
                brace += 1
            elif char == '}':
                brace -= 1
            elif char == '[':
                bracket += 1
            elif char == ']':
                bracket -= 1
                
        # If brace is 0 or paren is 0 inside the file body
        if brace == 0 and line_num > 7 and line_num < len(lines) - 2:
            print(f"  Line {line_num}: Object closed too early! brace={brace}, paren={paren}, bracket={bracket}")
            print(f"    Content: {line.strip()}")
            return
            
    print("  Nesting traced successfully.")

trace_file_nesting("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart3.kt")
trace_file_nesting("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart4.kt")
trace_file_nesting("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart5.kt")
