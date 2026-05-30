from PIL import Image

im = Image.open("Uygulama ana ekran logosu.png")
# Check a few dark pixels in the symbol area
# Symbol is at x in [789, 965]
count = 0
for y in range(336, 628, 5):
    for x in range(789, 965, 5):
        p = im.getpixel((x, y))
        r, g, b = p[0], p[1], p[2]
        if r < 200 or g < 200 or b < 200:
            count += 1
            if count < 10:
                print(f"Dark pixel at ({x},{y}): R={r}, G={g}, B={b}")

print(f"Total dark pixels analyzed in symbol area: {count}")
