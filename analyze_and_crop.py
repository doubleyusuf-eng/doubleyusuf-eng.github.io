from PIL import Image
import os

img_path = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/res/drawable/logo_light.png"
im = Image.open(img_path)
width, height = im.size
print(f"Original size: {width}x{height}")

# Convert to grayscale to analyze density
gray = im.convert("L")

# Let's count non-white pixels in each row and column
# We consider any pixel with value < 250 as "content"
threshold = 250
row_densities = [0] * height
col_densities = [0] * width

for y in range(height):
    for x in range(width):
        if gray.getpixel((x, y)) < threshold:
            row_densities[y] += 1
            col_densities[x] += 1

# Find boundaries where density is non-zero
# We can allow a small noise threshold (e.g., at least 2 pixels of content)
noise_thresh = 2

min_y = next((i for i, d in enumerate(row_densities) if d > noise_thresh), 0)
max_y = next((height - 1 - i for i, d in enumerate(reversed(row_densities)) if d > noise_thresh), height - 1)

min_x = next((i for i, d in enumerate(col_densities) if d > noise_thresh), 0)
max_x = next((width - 1 - i for i, d in enumerate(reversed(col_densities)) if d > noise_thresh), width - 1)

print(f"Detected content boundaries: x={min_x}..{max_x}, y={min_y}..{max_y}")

# Crop the content
content = im.crop((min_x, min_y, max_x + 1, max_y + 1))
cw, ch = content.size
print(f"Content cropped size: {cw}x{ch}")

# Now, we want to create a new square icon.
# The user wants the content to be MUCH LARGER, not squeezed inside a small circle with massive margins.
# "direk yazı kaybolmayacak şekilde olsun. Yani kesebilirsin istediğin gibi fotoğrafı"
# Let's pad it with a small margin (e.g., 5-8% margin) and make it square.
size = max(cw, ch)
# Let's create a new white square image
new_im = Image.new("RGB", (size, size), (255, 255, 255))
# Paste content in the center
offset_x = (size - cw) // 2
offset_y = (size - ch) // 2
new_im.paste(content, (offset_x, offset_y))

# Let's resize to standard launcher icon size (e.g. 512x512) for the logo_light.png
final_im = new_im.resize((512, 512), Image.Resampling.LANCZOS)
final_im.save(img_path)
print("Successfully regenerated logo_light.png with optimal large padding!")
