from PIL import Image
import os

img_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/res/drawable/logo_light.png"
im = Image.open(img_path)
width, height = im.size
print(f"Original size: {width}x{height}")

# Define a function to check if a pixel is background (off-white / gray)
def is_bg(pixel):
    # Handles both RGB and RGBA
    r, g, b = pixel[0], pixel[1], pixel[2]
    return (240 <= r <= 255) and (240 <= g <= 255) and (240 <= b <= 255)

# Count content pixels in each row and column
row_densities = [0] * height
col_densities = [0] * width

for y in range(height):
    for x in range(width):
        p = im.getpixel((x, y))
        if not is_bg(p):
            row_densities[y] += 1
            col_densities[x] += 1

# Find boundaries of content
min_y = next((i for i, d in enumerate(row_densities) if d > 0), 0)
max_y = next((height - 1 - i for i, d in enumerate(reversed(row_densities)) if d > 0), height - 1)

min_x = next((i for i, d in enumerate(col_densities) if d > 0), 0)
max_x = next((width - 1 - i for i, d in enumerate(reversed(col_densities)) if d > 0), width - 1)

print(f"Content boundaries: x={min_x}..{max_x}, y={min_y}..{max_y}")

content = im.crop((min_x, min_y, max_x + 1, max_y + 1))
cw, ch = content.size
print(f"Cropped content size: {cw}x{ch}")

# Create a beautiful new square image
# Let's add a small padding (e.g. 5% on each side) to make sure it looks elegant
# If size is too tight, it will look like it touches the screen edges, so 5% is a very professional padding.
size = max(cw, ch)
padding = int(size * 0.05) # 5% padding
new_size = size + 2 * padding

# The user wants it to be large, "resmi ikon içine sığdırmaya çalışma direk yazı kaybolmayacak şekilde olsun. Yani kesebilirsin istediğin gibi fotoğrafı."
# Let's create the background with the most common background color (246, 245, 244) or pure white (255, 255, 255)
# Using white (255, 255, 255) matches the rest of the launcher background.
new_im = Image.new("RGB", (new_size, new_size), (255, 255, 255))

# Paste content in the center
offset_x = (new_size - cw) // 2
offset_y = (new_size - ch) // 2
new_im.paste(content, (offset_x, offset_y))

# Let's resize it to 512x512
final_im = new_im.resize((512, 512), Image.Resampling.LANCZOS)
final_im.save(img_path)
print("Regenerated logo_light.png with optimal large padding successfully!")
