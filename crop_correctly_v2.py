from PIL import Image
import os

# Paths
source_img = "Uygulama ana ekran logosu.png"
res_dir = "app/src/main/res"

def crop_and_generate():
    print(f"Opening source image: {source_img}")
    im = Image.open(source_img)
    print(f"Source image size: {im.size}, mode: {im.mode}")
    
    # 1. Crop components based on researched bounds
    # "Anesthesia": x in [81, 577], y in [352, 549] (width 496, height 197)
    anesthesia = im.crop((81, 352, 577, 549))
    
    # "Briefs": x in [586, 785], y in [352, 549] (width 199, height 197)
    briefs = im.crop((586, 352, 785, 549))
    
    # ECG Heart Symbol: x in [789, 1041], y in [336, 628] (width 252, height 292)
    symbol = im.crop((789, 336, 1041, 629))
    
    print("Successfully cropped all elements.")
    
    # 2. Create the beautiful stacked square image
    canvas_size = 512
    # Create pure white background
    canvas = Image.new("RGB", (canvas_size, canvas_size), (255, 255, 255))
    
    # Scale factors: We want "Anesthesia" to span about 460 pixels wide
    scale_factor = 460.0 / 496.0
    
    # Resize components
    anesthesia_w = 460
    anesthesia_h = int(197 * scale_factor)
    anesthesia_resized = anesthesia.resize((anesthesia_w, anesthesia_h), Image.Resampling.LANCZOS)
    
    briefs_w = int(199 * scale_factor)
    briefs_h = int(197 * scale_factor)
    briefs_resized = briefs.resize((briefs_w, briefs_h), Image.Resampling.LANCZOS)
    
    symbol_w = int(252 * scale_factor)
    symbol_h = int(292 * scale_factor)
    symbol_resized = symbol.resize((symbol_w, symbol_h), Image.Resampling.LANCZOS)
    
    # Calculate positions to center them perfectly
    # Row 1: "Anesthesia"
    r1_x = (canvas_size - anesthesia_w) // 2
    r1_y = 45 # Perfect top margin
    canvas.paste(anesthesia_resized, (r1_x, r1_y))
    
    # Row 2: "Briefs" and Symbol next to each other
    spacing = 16
    r2_total_w = briefs_w + spacing + symbol_w
    r2_x_start = (canvas_size - r2_total_w) // 2
    
    # We want them vertically aligned centered on the same axis
    r2_y_center = 360 # Perfect middle-bottom vertical alignment
    r2_briefs_y = r2_y_center - briefs_h // 2
    r2_symbol_y = r2_y_center - symbol_h // 2
    
    # Paste on canvas
    canvas.paste(briefs_resized, (r2_x_start, r2_briefs_y))
    canvas.paste(symbol_resized, (r2_x_start + briefs_w + spacing, r2_symbol_y))
    
    print("Stacked canvas completed successfully.")
    
    # 3. Save the new drawable/logo_light.png
    logo_light_path = os.path.join(res_dir, "drawable/logo_light.png")
    os.makedirs(os.path.dirname(logo_light_path), exist_ok=True)
    canvas.save(logo_light_path, "PNG")
    print(f"Saved: {logo_light_path}")
    
    # 4. Generate all scaled mipmap versions for launcher icon & round launcher
    mipmap_configs = {
        "mipmap-mdpi": 48,
        "mipmap-hdpi": 72,
        "mipmap-xhdpi": 96,
        "mipmap-xxhdpi": 144,
        "mipmap-xxxhdpi": 192
    }
    
    for folder, size in mipmap_configs.items():
        folder_path = os.path.join(res_dir, folder)
        os.makedirs(folder_path, exist_ok=True)
        
        # Resize to specified density
        resized_icon = canvas.resize((size, size), Image.Resampling.LANCZOS)
        
        # Save standard ic_launcher
        icon_path = os.path.join(folder_path, "ic_launcher.png")
        resized_icon.save(icon_path, "PNG")
        
        # Save round ic_launcher_round
        round_path = os.path.join(folder_path, "ic_launcher_round.png")
        resized_icon.save(round_path, "PNG")
        
        print(f"Generated {size}x{size} icons for: {folder}")

if __name__ == "__main__":
    crop_and_generate()
