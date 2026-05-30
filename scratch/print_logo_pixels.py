from PIL import Image

im = Image.open("Uygulama ana ekran logosu.png")
print("Corners:")
print("  Top-left (0,0):", im.getpixel((0, 0)))
print("  Top-right (1040,0):", im.getpixel((1040, 0)))
print("  Bottom-left (0,981):", im.getpixel((0, 981)))
print("  Bottom-right (1040,981):", im.getpixel((1040, 981)))

# Let's count how many pixels have what brightness
from collections import Counter
brightness = []
for y in range(0, im.height, 10):
    for x in range(0, im.width, 10):
        p = im.getpixel((x, y))
        brightness.append(sum(p)//3)

print("Sample brightness distribution (0-255):")
c = Counter(brightness)
for val in sorted(c.keys())[:10]:
    print(f"  Brightness {val}: {c[val]} pixels")
for val in sorted(c.keys(), reverse=True)[:10]:
    print(f"  Brightness {val}: {c[val]} pixels")
