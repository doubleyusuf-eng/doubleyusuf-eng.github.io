import re
import os
import sys

def main():
    print("=== SMOKE TEST: HOME SCREEN CARD & CRITICAL ALGORITHMS ===")
    
    # 1. Check for SeedDataAlgorithms.kt
    seed_path = "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataAlgorithms.kt"
    if not os.path.exists(seed_path):
        print(f"❌ ERROR: {seed_path} not found.")
        sys.exit(1)
        
    with open(seed_path, "r", encoding="utf-8") as f:
        seed_content = f.read()
        
    sections = seed_content.split("Algorithm(")
    algorithms = []
    for sec in sections[1:]:
        id_match = re.search(r'algorithmId\s*=\s*"([^"]+)"', sec)
        if not id_match:
            continue
        algo_id = id_match.group(1)
        
        urgency_match = re.search(r'urgencyLevel\s*=\s*"([^"]+)"', sec)
        urgency = urgency_match.group(1) if urgency_match else ""
        
        algorithms.append({
            "id": algo_id,
            "urgencyLevel": urgency
        })
        
    total_count = len(algorithms)
    print(f"✓ Parsed SeedDataAlgorithms.kt. Total algorithms: {total_count}")
    if total_count != 51:
        print(f"❌ ERROR: Expected 51 algorithms, but found {total_count}.")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: Total algorithm count is exactly 51.")
        
    critical_only_count = len([a for a in algorithms if a["urgencyLevel"] == "emergency"])
    print(f"✓ Total 'Critical Only' (urgencyLevel = 'emergency') algorithms: {critical_only_count}")
    if critical_only_count != 25:
        print(f"❌ ERROR: Expected 25 Critical Only algorithms, but found {critical_only_count}.")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: Critical Only algorithm count is exactly 25.")
        
    # 2. Check for HomeScreen.kt Card Presence and Click action
    home_path = "app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/ui/screens/HomeScreen.kt"
    if not os.path.exists(home_path):
        print(f"❌ ERROR: {home_path} not found.")
        sys.exit(1)
        
    with open(home_path, "r", encoding="utf-8") as f:
        home_content = f.read()
        
    # Search for "Critical Algorithms" and "Kritik Algoritmalar"
    if "Critical Algorithms" not in home_content or "Kritik Algoritmalar" not in home_content:
        print("❌ ERROR: Home Screen Card title ('Critical Algorithms' / 'Kritik Algoritmalar') is missing!")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: Card titles are present in HomeScreen.kt.")
        
    # Search for "Rapid access to life-threatening anesthesia crises"
    if "Rapid access to life-threatening anesthesia crises" not in home_content or "Hayatı tehdit eden anestezi krizlerine hızlı erişim" not in home_content:
        print("❌ ERROR: Home Screen Card descriptions are missing!")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: Card descriptions are present in HomeScreen.kt.")
        
    # Search for the click action: onNavigateToAlgorithms("emergency")
    click_pattern = r'onNavigateToAlgorithms\("emergency"\)'
    if not re.search(click_pattern, home_content):
        print("❌ ERROR: Click action targeting 'emergency' filter ('onNavigateToAlgorithms(\"emergency\")') not found in HomeScreen.kt!")
        sys.exit(1)
    else:
        print("🟢 SUCCESS: Click action is correctly mapped to onNavigateToAlgorithms(\"emergency\").")
        
    # 3. Check for AnesthesiaBriefs.apk presence at root workspace
    apk_path = "AnesthesiaBriefs.apk"
    if not os.path.exists(apk_path):
        print(f"❌ ERROR: {apk_path} not found at root workspace.")
        sys.exit(1)
    else:
        print(f"🟢 SUCCESS: Verified compiled AnesthesiaBriefs.apk is present at root workspace. Size: {os.path.getsize(apk_path)} bytes.")
        
    print("\n🎉 ALL SMOKE TEST CHECKS COMPLETED AND PASSED SUCCESSFULLY!")

if __name__ == "__main__":
    main()
