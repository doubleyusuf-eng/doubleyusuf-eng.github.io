import sqlite3
import os

db_path = "app/build/intermediates/assets/debug/board_prep.db"
# Wait, let's search for actual SQLite database files on the device/filesystem.
# The app database on an Android device is stored in app's data folder, which we can't access directly unless we are in the emulator or we find the seeded DB in the assets folder.
# Let's search for any .json or .db files in the app assets.
assets_dir = "app/src/main/assets"
if os.path.exists(assets_dir):
    print("Assets:")
    for f in os.listdir(assets_dir):
        if f.endswith(".json") or f.endswith(".db"):
            print(f"  {f} (size: {os.path.getsize(os.path.join(assets_dir, f))})")
else:
    print("Assets dir not found")
