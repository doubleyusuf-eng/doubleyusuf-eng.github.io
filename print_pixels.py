from PIL import Image
im = Image.open("/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/res/drawable/logo_light.png")
width, height = im.size
print(f"Size: {width}x{height}")
# Check corners
print(f"Top-left: {im.getpixel((0, 0))}")
print(f"Top-right: {im.getpixel((width-1, 0))}")
print(f"Bottom-left: {im.getpixel((0, height-1))}")
print(f"Bottom-right: {im.getpixel((width-1, height-1))}")

# Check first row
first_row = [im.getpixel((x, 0)) for x in range(width)]
unique_first_row = set(first_row)
print(f"Unique colors in first row: {list(unique_first_row)[:10]}... total unique = {len(unique_first_row)}")

# Let's count colors
from collections import Counter
colors = Counter([im.getpixel((x, y)) for y in range(height) for x in range(width)])
print("Top 10 most common colors:")
for color, count in colors.most_common(10):
    print(f"  {color}: {count} ({count / (width*height) * 100:.2f}%)")
