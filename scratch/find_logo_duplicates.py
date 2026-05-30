import os

res_dir = "app/src/main/res"
found = []
for root, dirs, files in os.walk(res_dir):
    for f in files:
        if "logo_in_app" in f:
            p = os.path.join(root, f)
            found.append(p)

print("Found logo files:")
for path in found:
    print(f"  {path}")
