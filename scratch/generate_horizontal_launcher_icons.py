from PIL import Image, ImageDraw
import os

# Content bounds: x in [81, 965], y in [336, 628]
x_min, x_max = 81, 965
y_min, y_max = 336, 628

def generate():
    im = Image.open("Uygulama ana ekran logosu.png")
    
    # 1. Crop the entire horizontal brand mark tightly
    brand_cropped = im.crop((x_min, y_min, x_max + 1, y_max + 1)).convert("RGBA")
    
    # Make near-white/beige background transparent to blend seamlessly with pure white background
    datas = brand_cropped.getdata()
    newData = []
    for item in datas:
        # If the pixel is near the light beige/grey background (R > 240 and G > 240 and B > 240)
        if item[0] > 240 and item[1] > 240 and item[2] > 240:
            newData.append((255, 255, 255, 0))
        else:
            newData.append(item)
    brand_cropped.putdata(newData)
    
    content_w = brand_cropped.width
    content_h = brand_cropped.height
    print(f"Cropped brand mark content size: {content_w}x{content_h}")
    
    # 2. Generate standard square ic_launcher.png (512x512)
    # The content should be scaled to a width of 440px (about 86% of canvas) to be nicely zoomed in
    target_w_sq = 440
    scale_sq = target_w_sq / content_w
    target_h_sq = int(content_h * scale_sq)
    
    brand_sq = brand_cropped.resize((target_w_sq, target_h_sq), Image.Resampling.LANCZOS)
    
    icon_square = Image.new("RGB", (512, 512), (255, 255, 255))
    offset_x_sq = (512 - target_w_sq) // 2
    offset_y_sq = (512 - target_h_sq) // 2
    icon_square.paste(brand_sq, (offset_x_sq, offset_y_sq), mask=brand_sq)
    
    # 3. Generate circular ic_launcher_round.png (512x512)
    # The content should be scaled to a width of 390px so it fits completely inside the circle safe zone
    target_w_rd = 390
    scale_rd = target_w_rd / content_w
    target_h_rd = int(content_h * scale_rd)
    
    brand_rd = brand_cropped.resize((target_w_rd, target_h_rd), Image.Resampling.LANCZOS)
    
    icon_round = Image.new("RGBA", (512, 512), (0, 0, 0, 0))
    draw = ImageDraw.Draw(icon_round)
    # Draw white circle in center (diameter 480)
    circle_margin = (512 - 480) // 2
    draw.ellipse([circle_margin, circle_margin, 512 - circle_margin, 512 - circle_margin], fill=(255, 255, 255, 255))
    
    # Center the brand mark inside the round white circle
    offset_x_rd = (512 - target_w_rd) // 2
    offset_y_rd = (512 - target_h_rd) // 2
    
    # Convert brand_rd to RGBA
    brand_rd_rgba = brand_rd.convert("RGBA")
    
    # Paste using the brand_rd_rgba itself as mask for perfect transparent blending on the white circle
    icon_round.paste(brand_rd_rgba, (offset_x_rd, offset_y_rd), mask=brand_rd_rgba)
    
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
        
        print(f"Generated horizontal launcher icons ({size}x{size}) for {folder}")

if __name__ == "__main__":
    generate()
