from PIL import Image
import os

res_dir = "/Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/res"

for root, dirs, files in os.walk(res_dir):
    for f in files:
        if f.endswith(".png") and ("logo_" in f or "ic_launcher" in f):
            p = os.path.join(root, f)
            im = Image.open(p)
            print(f"File: {f} ({p.replace(res_dir, '')})")
            print(f"  Size: {im.size}, Mode: {im.mode}")
            if im.mode in ('RGBA', 'LA') or (im.mode == 'P' and 'transparency' in im.info):
                bbox = im.getbbox()
                if bbox:
                    print(f"  Bounding box (non-transparent): {bbox}")
                else:
                    print("  Fully transparent!")
            else:
                # Solid background - let's check bounding box of non-white or non-navy pixels
                # Let's inspect the upper-left pixel color to guess background
                bg_color = im.getpixel((0, 0))
                print(f"  Solid background guess color: {bg_color}")
                # Find bounding box of pixels different from bg_color
                # We can do this by creating a mask
                diff_pixels = []
                width, height = im.size
                # simple sample check to find content boundaries
                min_x, min_y, max_x, max_y = width, height, 0, 0
                for y in range(height):
                    for x in range(width):
                        c = im.getpixel((x, y))
                        # check if it is significantly different from bg_color
                        if im.mode == 'RGB':
                            diff = sum(abs(c[i] - bg_color[i]) for i in range(3))
                        elif isinstance(c, int):
                            diff = abs(c - bg_color)
                        else:
                            diff = sum(abs(c[i] - bg_color[i]) for i in range(len(c)))
                        if diff > 15: # threshold
                            if x < min_x: min_x = x
                            if y < min_y: min_y = y
                            if x > max_x: max_x = x
                            if y > max_y: max_y = y
                if min_x <= max_x and min_y <= max_y:
                    print(f"  Content bounding box (non-bg): {(min_x, min_y, max_x, max_y)}")
                else:
                    print("  No content found (solid color)!")
