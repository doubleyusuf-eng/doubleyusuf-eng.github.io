from PIL import Image
im = Image.open("/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/build/intermediates/packaged_res/debug/packageDebugResources/drawable/logo_light.png")
width, height = im.size
print(f"Original Size: {width}x{height}")

def is_bg(pixel):
    r, g, b = pixel[0], pixel[1], pixel[2]
    return (240 <= r <= 255) and (240 <= g <= 255) and (240 <= b <= 255)

# Print first 20 rows with non-bg pixel count
for y in range(height):
    non_bg_count = sum(1 for x in range(width) if not is_bg(im.getpixel((x, y))))
    if non_bg_count > 0:
        print(f"Row {y}: {non_bg_count} non-bg pixels")
        if y > 50:
            break
