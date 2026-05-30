def find_balance(path):
    print(f"File: {path}")
    with open(path, 'r') as f:
        lines = f.readlines()
        
    paren = 0
    brace = 0
    bracket = 0
    
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
                
        if paren < 0 or brace < 0 or bracket < 0:
            print(f"  Line {line_num}: NEGATIVE BALANCE! paren={paren}, brace={brace}, bracket={bracket}")
            print(f"    Line content: {line.strip()}")
            return
            
    print(f"  Final balance: paren={paren}, brace={brace}, bracket={bracket}")

find_balance("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart3.kt")
find_balance("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart4.kt")
find_balance("app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataDrugsPart5.kt")
