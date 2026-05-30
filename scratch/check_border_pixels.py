from PIL import Image

im = Image.open("Uygulama ana ekran logosu.png")
width, height = im.size

# check edges
non_white_border_pixels = []
for x in range(width):
    p1 = im.getpixel((x, 0))
    p2 = im.getpixel((x, height - 1))
    if sum(p1) < 720:
        non_white_border_pixels.append((x, 0, p1))
    if sum(p2) < 720:
        non_white_border_pixels.append((x, height - 1, p2))

for y in range(height):
    p1 = im.getpixel((0, y))
    p2 = im.getpixel((width - 1, y))
    if sum(p1) < 720:
        non_white_border_pixels.append((0, y, p1))
    if sum(p2) < 720:
        non_white_border_pixels.append((width - 1, y, p2))

print(f"Total non-white border pixels found: {len(non_white_border_pixels)}")
if len(non_white_border_pixels) > 0:
    print("Some border pixels:")
    for px in non_white_border_pixels[:10]:
        print(f"  Pos ({px[0]},{px[1]}): {px[2]}")
