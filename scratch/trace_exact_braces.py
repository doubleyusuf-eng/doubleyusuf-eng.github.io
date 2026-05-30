def trace_range(path):
    with open(path, 'r') as f:
        content = f.read()
        
    stack = []
    lines = content.split('\n')
    
    line_num = 1
    in_string = False
    escape = False
    in_line_comment = False
    in_block_comment = False
    
    i = 0
    while i < len(content):
        char = content[i]
        
        if char == '\n':
            line_num += 1
            in_line_comment = False
            i += 1
            continue
            
        if in_line_comment:
            i += 1
            continue
            
        if in_block_comment:
            if char == '*' and i + 1 < len(content) and content[i+1] == '/':
                in_block_comment = False
                i += 2
            else:
                i += 1
            continue
            
        if in_string:
            if escape:
                escape = False
            elif char == '\\':
                escape = True
            elif char == '"':
                in_string = False
            i += 1
            continue
            
        # check comments/strings
        if char == '/' and i + 1 < len(content) and content[i+1] == '/':
            in_line_comment = True
            i += 2
            continue
        if char == '/' and i + 1 < len(content) and content[i+1] == '*':
            in_block_comment = True
            i += 2
            continue
        if char == '"':
            in_string = True
            i += 1
            continue
            
        if char == '{':
            stack.append((line_num, lines[line_num - 1].strip()))
        elif char == '}':
            if stack:
                pop_line, pop_content = stack.pop()
                if 1470 <= line_num <= 1500 or 1690 <= line_num <= 1715:
                    print(f"Closed '{pop_content}' (line {pop_line}) at line {line_num}")
            else:
                print(f"Extra closing brace at line {line_num}: {lines[line_num - 1].strip()}")
                
        i += 1

trace_range("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/ui/screens/BoardPrepQuizScreen.kt")
