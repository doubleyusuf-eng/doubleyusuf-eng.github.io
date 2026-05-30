from PIL import Image

im = Image.open("Uygulama ana ekran logosu.png")
width, height = im.size
min_x, min_y = width, height
max_x, max_y = 0, 0

for y in range(height):
    for x in range(width):
        p = im.getpixel((x, y))
        if sum(p[:3]) < 450: # threshold for dark content
            if x < min_x: min_x = x
            if y < min_y: min_y = y
            if x > max_x: max_x = x
            if y > max_y: max_y = y

print(f"Content bounding box: x in [{min_x}, {max_x}], y in [{min_y}, {max_y}]")
print(f"Width: {max_x - min_x}, Height: {max_y - min_y}")
