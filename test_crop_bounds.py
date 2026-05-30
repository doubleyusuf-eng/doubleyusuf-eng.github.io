from PIL import Image

im = Image.open("/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/build/intermediates/packaged_res/debug/packageDebugResources/drawable/logo_light.png")
width, height = im.size
print(f"Original size: {width}x{height}")

def is_bg(pixel):
    r, g, b = pixel[0], pixel[1], pixel[2]
    return (240 <= r <= 255) and (240 <= g <= 255) and (240 <= b <= 255)

# Count content pixels per row and column
row_densities = [0] * height
col_densities = [0] * width

for y in range(height):
    for x in range(width):
        p = im.getpixel((x, y))
        if not is_bg(p):
            row_densities[y] += 1
            col_densities[x] += 1

# Let's filter out row/column noise by using a threshold (say, at least 45 pixels of content)
threshold = 45

min_y = next((i for i, d in enumerate(row_densities) if d >= threshold), 0)
max_y = next((height - 1 - i for i, d in enumerate(reversed(row_densities)) if d >= threshold), height - 1)

min_x = next((i for i, d in enumerate(col_densities) if d >= threshold), 0)
max_x = next((width - 1 - i for i, d in enumerate(reversed(col_densities)) if d >= threshold), width - 1)

print(f"Filtered Content Boundaries: x={min_x}..{max_x}, y={min_y}..{max_y}")
print(f"Cropped Content Dimensions: {max_x - min_x + 1}x{max_y - min_y + 1}")
