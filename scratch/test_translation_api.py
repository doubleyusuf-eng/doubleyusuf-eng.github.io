import urllib.request
import urllib.parse
import json
import time

def translate_to_english(text):
    if not text:
        return ""
    url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=tr&tl=en&dt=t&q=" + urllib.parse.quote(text)
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        response = urllib.request.urlopen(req, timeout=10)
        data = json.loads(response.read().decode('utf-8'))
        translated = ''.join([part[0] for part in data[0] if part[0]])
        return translated
    except Exception as e:
        return f"Error: {e}"

print("Testing translation:")
res = translate_to_english("Bugün hava çok güzel ve ben kod yazıyorum.")
print(res)
