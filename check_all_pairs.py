content = open('/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/ui/screens/CalculatorDetailScreen.kt').read()
stack = []
idx = 0
length = len(content)
in_string = False
in_raw_string = False
in_line_comment = False
in_block_comment = False

pairs = {
    '}': '{',
    ')': '(',
    ']': '['
}

line_num = 1
while idx < length:
    char = content[idx]
    if char == '\n':
        line_num += 1
        
    if in_line_comment:
        if char == '\n':
            in_line_comment = False
        idx += 1
        continue
    if in_block_comment:
        if content[idx:idx+2] == '*/':
            in_block_comment = False
            idx += 2
        else:
            idx += 1
        continue
    if in_raw_string:
        if content[idx:idx+3] == '\"\"\"':
            in_raw_string = False
            idx += 3
        else:
            if content[idx:idx+2] == '${':
                stack.append(('${', idx, line_num))
                idx += 2
            elif char == '}':
                if len(stack) > 0 and stack[-1][0] == '${':
                    stack.pop()
                idx += 1
            else:
                idx += 1
        continue
    if in_string:
        if char == '\"' and (idx == 0 or content[idx-1] != '\\'):
            in_string = False
            idx += 1
        else:
            if content[idx:idx+2] == '${':
                stack.append(('${', idx, line_num))
                idx += 2
            elif char == '}':
                if len(stack) > 0 and stack[-1][0] == '${':
                    stack.pop()
                idx += 1
            else:
                idx += 1
        continue
        
    if content[idx:idx+2] == '//':
        in_line_comment = True
        idx += 2
        continue
    if content[idx:idx+2] == '/*':
        in_block_comment = True
        idx += 2
        continue
    if content[idx:idx+3] == '\"\"\"':
        in_raw_string = True
        idx += 3
        continue
    if char == '\"':
        in_string = True
        idx += 1
        continue
        
    if char in ['{', '(', '[']:
        stack.append((char, idx, line_num))
    elif char in ['}', ')', ']']:
        expected = pairs[char]
        if len(stack) == 0:
            print(f'Unmatched close char structurally: {char} at line {line_num}!')
        else:
            top_char, top_idx, top_line = stack.pop()
            if top_char != expected:
                print(f'Mismatched: {char} at line {line_num} expected {pairs[top_char]} from line {top_line}!')
                
    idx += 1

print('Remaining items on stack:', len(stack))
for item in stack:
    snippet = content[item[1]:item[1]+40].replace('\n', ' ')
    print(f'Unmatched open {item[0]} at line {item[2]}: {snippet}')
