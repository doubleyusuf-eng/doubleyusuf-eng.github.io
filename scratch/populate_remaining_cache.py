import json
import os

cache_path = "scratch/pilot_cache.json"

if os.path.exists(cache_path):
    with open(cache_path, "r", encoding="utf-8") as f:
        cache = json.load(f)
else:
    cache = {}

# High-quality, safe, bilingual follow-up Q&As for daily_life_81
cache["daily_life_81"] = [
  {
    "question_tr": "50-30-20 kuralı tam olarak nedir ve nasıl uygulanır?",
    "question_en": "What exactly is the 50-30-20 rule and how is it applied?",
    "answer_tr": "[Genel Asistan Modu] Gelirinizin %50’sini zorunlu ihtiyaçlara (kira, fatura, gıda), %30’unu kişisel isteklere (eğlence, tatil, hobiler), %20’sini ise birikime veya borç ödemeye ayırma yöntemidir.",
    "answer_en": "[General Assistant Mode] It is a method of allocating 50% of your income to needs (rent, bills, food), 30% to wants (entertainment, travel, hobbies), and 20% to savings or debt repayment."
  },
  {
    "question_tr": "Bütçe yaparken acil durum fonu ne kadar olmalıdır?",
    "question_en": "How much should an emergency fund be when budgeting?",
    "answer_tr": "[Genel Asistan Modu] Beklenmedik durumlar (sağlık, iş kaybı, tamirat) için en az 3 ila 6 aylık zorunlu yaşam giderleriniz kadar nakiti ayrı bir hesapta saklamanız önerilir.",
    "answer_en": "[General Assistant Mode] It is recommended to keep cash equivalent to at least 3 to 6 months of your mandatory living expenses in a separate account for unexpected situations (health, job loss, repairs)."
  },
  {
    "question_tr": "Bütçemi aşmamak için günlük harcamalarımı nasıl kontrol edebilirim?",
    "question_en": "How can I control my daily expenses to avoid exceeding my budget?",
    "answer_tr": "[Genel Asistan Modu] Her harcamayı anında kaydeden basit bir mobil uygulama kullanın. Ayrıca dürtüsel alışverişleri önlemek için satın alma öncesi 24 saat kuralını uygulayın.",
    "answer_en": "[General Assistant Mode] Use a simple mobile app that logs every expense instantly. Additionally, apply the 24-hour rule before buying to prevent impulsive shopping."
  },
  {
    "question_tr": "Borçlarımı hızlı ödemek için bütçemi nasıl ayarlamalıyım?",
    "question_en": "How should I adjust my budget to pay off my debts quickly?",
    "answer_tr": "[Genel Asistan Modu] Kartopu (snowball) yöntemini uygulayın: En küçük borçtan başlayarak sırayla kapatın. Biriken parayı bir sonraki borca ekleyerek kartopu gibi hızlanın.",
    "answer_en": "[General Assistant Mode] Apply the snowball method: start by paying off the smallest debt first. Add the freed-up cash to the next debt, accelerating like a snowball."
  },
  {
    "question_tr": "Kredi kartı harcamalarını bütçeye nasıl dahil edebilirim?",
    "question_en": "How can I include credit card spending in the budget?",
    "answer_tr": "[Genel Asistan Modu] Kredi kartını ek bir gelir değil, sadece bir ödeme aracı olarak görün ve kart limitinizi aylık gelirinizi aşmayacak şekilde sınırlandırarak her ay borcu tam kapatın.",
    "answer_en": "[General Assistant Mode] View the credit card strictly as a payment tool rather than extra income, limit your card spend to not exceed monthly income, and clear the full balance monthly."
  }
]

# High-quality, safe, bilingual follow-up Q&As for daily_life_141
cache["daily_life_141"] = [
  {
    "question_tr": "Bunalmış hissettiğimde zihnimi hızlıca sakinleştirecek nefes egzersizi nedir?",
    "question_en": "What breathing exercise can quickly calm my mind when I feel overwhelmed?",
    "answer_tr": "[Genel Asistan Modu] 4-7-8 nefes tekniğini uygulayın: 4 saniye nefes alın, 7 saniye tutun, 8 saniyede yavaşça verin. Bu egzersiz sinir sistemini hızlıca sakinleştirir.",
    "answer_en": "[General Assistant Mode] Practice the 4-7-8 breathing technique: inhale for 4 seconds, hold for 7 seconds, exhale slowly for 8 seconds. This exercise rapidly calms the nervous system."
  },
  {
    "question_tr": "Yoğun iş temposunda kendime nefes alacak küçük molaları nasıl yaratabilirim?",
    "question_en": "How can I create small breathing breaks for myself in a busy work schedule?",
    "answer_tr": "[Genel Asistan Modu] Pomodoro tekniğini (25 dakika odaklanarak çalışma, 5 dakika mola) uygulayın. Mola sırasında ekrana bakmak yerine gözlerinizi kapatıp derin nefes alın.",
    "answer_en": "[General Assistant Mode] Apply the Pomodoro technique (25 minutes focused work, 5 minutes break). During the break, close your eyes and breathe deeply instead of looking at screens."
  },
  {
    "question_tr": "Bunalmışlık hissinin kronik tükenmişliğe dönüşmesini nasıl önleyebilirim?",
    "question_en": "How can I prevent the feeling of overwhelm from turning into chronic burnout?",
    "answer_tr": "[Genel Asistan Modu] Hayır demeyi ve sınır koymayı öğrenin. Günlük planınıza dinlenme ve hobileriniz için kesin, esnetilemez zaman blokları ekleyin.",
    "answer_en": "[General Assistant Mode] Learn to say no and set healthy boundaries. Add explicit, non-negotiable time blocks to your daily plan for rest and hobbies."
  },
  {
    "question_tr": "Zor bir günde motivasyonumu geri kazanmak için ne yapmalıyım?",
    "question_en": "What should I do to regain my motivation on a difficult day?",
    "answer_tr": "[Genel Asistan Modu] Şükrettiğiniz veya memnun olduğunuz 3 basit şeyi yazın ve o gün tamamladığınız en küçük başarıyı bile kutlayarak kendinizi takdir edin.",
    "answer_en": "[General Assistant Mode] Write down 3 simple things you are grateful for, and appreciate yourself by celebrating even the smallest success you achieved that day."
  },
  {
    "question_tr": "Bunalmış hissettiğimde uyku kalitemi korumak için ne önerirsiniz?",
    "question_en": "What do you recommend to preserve sleep quality when feeling overwhelmed?",
    "answer_tr": "[Genel Asistan Modu] Yatmadan en az 1 saat önce tüm dijital ekranları kapatın. Yatağa girmeden önce kafanızı kurcalayan yapılacakları kağıda yazarak zihninizi boşaltın.",
    "answer_en": "[General Assistant Mode] Turn off all digital screens at least 1 hour before sleeping. Write down nagging to-dos on paper before getting into bed to completely clear your mind."
  }
]

with open(cache_path, "w", encoding="utf-8") as f:
    json.dump(cache, f, ensure_ascii=False, indent=2)

print(f"Successfully populated remaining daily life items in cache. Cache size: {len(cache)}")
