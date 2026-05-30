from PIL import Image
import os

# Bounds found: x in [81, 965], y in [336, 628]
x_min, x_max = 81, 965
y_min, y_max = 336, 628

def generate():
    im = Image.open("Uygulama ana ekran logosu.png")
    
    # 1. Crop to bounding box
    cropped = im.crop((x_min, y_min, x_max + 1, y_max + 1))
    
    # 2. Add padding to avoid edge clipping (e.g. 16px horizontal, 8px vertical)
    padding_x = 16
    padding_y = 8
    width = cropped.width + 2 * padding_x
    height = cropped.height + 2 * padding_y
    
    # Generate logo_in_app.png (for light backgrounds - transparent with dark gray pixels)
    logo_light = Image.new("RGBA", (width, height), (0, 0, 0, 0))
    
    # Generate logo_in_app_navy.png (for dark/navy backgrounds - transparent with white pixels)
    logo_dark = Image.new("RGBA", (width, height), (0, 0, 0, 0))
    
    for y in range(cropped.height):
        for x in range(cropped.width):
            p = cropped.getpixel((x, y))
            r, g, b = p[0], p[1], p[2]
            
            # calculate brightness/intensity
            # Original background is white/light (254, 254, 254), content is dark (0-10)
            avg = (r + g + b) // 3
            
            # We want to map the dark content to a clean alpha channel
            # Content should have high alpha, background should have 0 alpha
            if avg > 240:
                alpha = 0
            elif avg < 120:
                alpha = 255
            else:
                # Interpolate alpha smoothly for anti-aliasing
                alpha = int(255 * (240 - avg) / 120)
                alpha = max(0, min(255, alpha))
                
            if alpha > 0:
                # For light mode (logo_in_app): keep original color, set alpha
                logo_light.putpixel((x + padding_x, y + padding_y), (r, g, b, alpha))
                
                # For dark mode (logo_in_app_navy): make color white, set alpha
                logo_dark.putpixel((x + padding_x, y + padding_y), (255, 255, 255, alpha))
                
    # Save the output images
    drawable_dir = "app/src/main/res/drawable"
    os.makedirs(drawable_dir, exist_ok=True)
    
    light_path = os.path.join(drawable_dir, "logo_in_app.png")
    dark_path = os.path.join(drawable_dir, "logo_in_app_navy.png")
    
    logo_light.save(light_path, "PNG")
    logo_dark.save(dark_path, "PNG")
    
    print(f"Generated logo_in_app: {light_path}")
    print(f"Generated logo_in_app_navy: {dark_path}")

if __name__ == "__main__":
    generate()
