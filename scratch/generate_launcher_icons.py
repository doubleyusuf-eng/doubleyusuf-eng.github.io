from PIL import Image, ImageDraw
import os

# Bounds for circular brand mark: x in [789, 965], y in [336, 628]
x_min, x_max = 789, 965
y_min, y_max = 336, 628

def generate_launcher():
    im = Image.open("Uygulama ana ekran logosu.png")
    
    # 1. Crop to brand mark region
    brand_mark = im.crop((x_min, y_min, x_max + 1, y_max + 1))
    
    # 2. Get exact content bounds of brand mark (ignore light background)
    w, h = brand_mark.size
    min_bx, min_by = w, h
    max_bx, max_by = 0, 0
    for y in range(h):
        for x in range(w):
            p = brand_mark.getpixel((x, y))
            if sum(p[:3]) < 720: # not white
                if x < min_bx: min_bx = x
                if y < min_by: min_by = y
                if x > max_bx: max_bx = x
                if y > max_by: max_by = y
                
    content_w = max_bx - min_bx + 1
    content_h = max_by - min_by + 1
    brand_cropped = brand_mark.crop((min_bx, min_by, max_bx + 1, max_by + 1))
    
    print(f"Brand mark content size: {content_w}x{content_h}")
    
    # 3. Create high-res master square icon (512x512)
    # We want the content to be scaled so its largest dimension is 64% of 512 (which is 328 pixels)
    target_dim = 328
    scale = target_dim / max(content_w, content_h)
    new_w = int(content_w * scale)
    new_h = int(content_h * scale)
    
    resized_brand = brand_cropped.resize((new_w, new_h), Image.Resampling.LANCZOS)
    
    # Standard square icon canvas (solid white background)
    icon_square = Image.new("RGBA", (512, 512), (255, 255, 255, 255))
    offset_x = (512 - new_w) // 2
    offset_y = (512 - new_h) // 2
    
    # Apply transparency to brand content so it fits cleanly
    for y in range(resized_brand.height):
        for x in range(resized_brand.width):
            p = resized_brand.getpixel((x, y))
            r, g, b = p[0], p[1], p[2]
            avg = (r + g + b) // 3
            if avg < 240:
                # Content pixel - paste onto square canvas
                icon_square.putpixel((x + offset_x, y + offset_y), (r, g, b, 255))
                
    # Round icon canvas (transparent background with a white circle and brand mark inside)
    icon_round = Image.new("RGBA", (512, 512), (0, 0, 0, 0))
    draw = ImageDraw.Draw(icon_round)
    # Draw white background circle (66% size = 338 pixels diameter)
    circle_margin = (512 - 450) // 2
    draw.ellipse([circle_margin, circle_margin, 512 - circle_margin, 512 - circle_margin], fill=(255, 255, 255, 255))
    
    # Paste brand content onto round canvas
    for y in range(resized_brand.height):
        for x in range(resized_brand.width):
            p = resized_brand.getpixel((x, y))
            r, g, b = p[0], p[1], p[2]
            avg = (r + g + b) // 3
            if avg < 240:
                icon_round.putpixel((x + offset_x, y + offset_y), (r, g, b, 255))
                
    # 4. Save to all density mipmap folders
    mipmap_configs = {
        "mipmap-mdpi": 48,
        "mipmap-hdpi": 72,
        "mipmap-xhdpi": 96,
        "mipmap-xxhdpi": 144,
        "mipmap-xxxhdpi": 192
    }
    
    res_dir = "app/src/main/res"
    for folder, size in mipmap_configs.items():
        folder_path = os.path.join(res_dir, folder)
        os.makedirs(folder_path, exist_ok=True)
        
        # Standard icon
        sq_resized = icon_square.resize((size, size), Image.Resampling.LANCZOS)
        sq_resized.save(os.path.join(folder_path, "ic_launcher.png"), "PNG")
        
        # Round icon
        rd_resized = icon_round.resize((size, size), Image.Resampling.LANCZOS)
        rd_resized.save(os.path.join(folder_path, "ic_launcher_round.png"), "PNG")
        
        print(f"Generated {size}x{size} launcher icons for {folder}")

if __name__ == "__main__":
    generate_launcher()
