import urllib.request
import urllib.parse
import json

api_key = "AIzaSyAeD84-0Gq8LGBVuqX621Cz7X_jRHQ6U2Y"
url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key={api_key}"

def test_api():
    payload = {
        "contents": [{
            "parts": [{
                "text": "Say hello in 5 different languages as a JSON list. Keep it short."
            }]
        }],
        "generationConfig": {
            "responseMimeType": "application/json"
        }
    }
    
    data = json.dumps(payload).encode("utf-8")
    req = urllib.request.Request(url, data=data, headers={"Content-Type": "application/json"})
    try:
        with urllib.request.urlopen(req, timeout=10) as response:
            res = json.loads(response.read().decode("utf-8"))
            print("Success:")
            print(json.dumps(res, indent=2))
    except Exception as e:
        print("Failed:", e)

if __name__ == "__main__":
    test_api()
