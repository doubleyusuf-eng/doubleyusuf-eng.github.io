import os

res_dir = "app/src/main/res"
found = []
for root, dirs, files in os.walk(res_dir):
    for f in files:
        if "ic_launcher" in f:
            p = os.path.join(root, f)
            found.append(p)

print("Found launcher icon files:")
for path in found:
    print(f"  {path}")
