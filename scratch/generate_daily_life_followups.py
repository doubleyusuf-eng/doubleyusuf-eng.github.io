import os
import sys
import json
import time
import re
import urllib.request
import urllib.parse

def translate_to_english(text):
    if not text:
        return ""
    # Sleep to respect rate limits
    time.sleep(0.08)
    url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=tr&tl=en&dt=t&q=" + urllib.parse.quote(text)
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    for attempt in range(5):
        try:
            response = urllib.request.urlopen(req, timeout=10)
            data = json.loads(response.read().decode('utf-8'))
            translated = ''.join([part[0] for part in data[0] if part[0]])
            return translated
        except Exception as e:
            print(f"Translation attempt {attempt+1} failed for text '{text[:20]}...': {e}")
            time.sleep(1.0)
    return text  # fallback to original

STOP_WORDS = {
    "ve", "bir", "nedir", "nelerdir", "nasil", "nasıl", "veya", "icin", "için", "olan", "göre", "gore", "ile", "de", "da", "mi", "mı", "mu", "mü",
    "ne", "zaman", "the", "and", "what", "how", "is", "of", "for", "with", "or", "in", "to", "at", "on", "by", "an", "a"
}

def extract_keywords(tr_q, en_q):
    tokens = []
    for q in [tr_q, en_q]:
        words = re.findall(r'[a-zA-Z0-9ğüşöçıİĞÜŞÖÇ]+', q.lower())
        for w in words:
            if w not in STOP_WORDS and len(w) > 1:
                tokens.append(w)
    return sorted(list(set(tokens)))

# 5 follow-up templates for each of the 19 daily life categories
CATEGORIES_TEMPLATES = {
    "daily_planning": [
        {
            "q": "Bu planlamayı yaparken beklenmedik bölünmeleri nasıl yönetebilirim?",
            "a": "Beklenmedik bölünmeler için planınızda mutlaka 30-45 dakikalık boş tampon zamanlar bırakın. Acil durum çıktığında kalan saatleri yeniden planlayıp önemsiz işleri erteleyebilirsiniz."
        },
        {
            "q": "Planı uygularken odaklanmamı kaybetmemek için hangi yöntemi tavsiye edersiniz?",
            "a": "Pomodoro tekniğini (25 dk çalışma, 5 dk mola) kullanabilirsiniz. Ayrıca planladığınız işe başlamadan önce tüm bildirimleri kapatmak ve masanızı sadeleştirmek odaklanmayı kolaylaştırır."
        },
        {
            "q": "Bu planlama alışkanlığını uzun vadede kalıcı hale getirmek için ne yapmalıyım?",
            "a": "Her gün aynı saatte sadece 5 dakikanızı planlamaya ayırın. Süreci basitleştirmek için karmaşık araçlar yerine sade bir not defteriyle başlayın."
        },
        {
            "q": "Planlanan işler gün sonunda yetişmediğinde nasıl bir yol izlemeliyim?",
            "a": "Kendinizi suçlamayın. Yetişmeyen işleri analiz edin: Süreyi mi yanlış hesapladınız, yoksa dikkat dağıtıcılar mı araya girdi? Bu işleri ertesi günün planına en başa yerleştirin."
        },
        {
            "q": "Dijital planlama araçları mı yoksa klasik kağıt-kalem mi daha etkilidir?",
            "a": "Kağıt-kalem somut bir tamamlama hissi verir ve dikkat dağıtmaz. Dijital araçlar (Trello, Notion, Google Takvim) ise hatırlatıcılar ve esneklik açısından avantajlıdır."
        }
    ],
    "productivity_and_focus": [
        {
            "q": "Odaklanma süremi zamanla nasıl artırabilirim?",
            "a": "Odaklanma sürelerini kademeli olarak artırın (örn. 25 dakikadan 30-35 dakikaya). Zihinsel kaslarınızı geliştirmek için her gün düzenli pratik yapın."
        },
        {
            "q": "Çalışırken dikkatimi dağıtan en büyük içsel etkenleri nasıl yenerim?",
            "a": "Erteleme direncini kırmak için 5 dakika kuralını uygulayın. Sadece 5 dakika çalışıp bırakacağınızı kendinize söyleyin, çoğunlukla devamı gelecektir."
        },
        {
            "q": "Gürültülü ortamlarda odaklanmayı kolaylaştıracak önerileriniz nelerdir?",
            "a": "Gürültü engelleyici kulaklıklar kullanabilir, odaklanma odaklı klasik müzik, lo-fi veya beyaz/pembe gürültü sesleri dinleyebilirsiniz."
        },
        {
            "q": "Zihinsel yorgunluk hissettiğimde nasıl verimli mola verebilirim?",
            "a": "Mola sırasında ekrandan tamamen uzaklaşın, gözlerinizi dinlendirin, esneme hareketleri yapın ve büyük bir bardak su için."
        },
        {
            "q": "Odaklanmayı artıran en etkili fiziksel çalışma ortamı nasıl olmalıdır?",
            "a": "Masanızda sadece o anki işe ait malzemeleri bırakın. Odanın ışık ve hava kalitesinin iyi olmasına dikkat edin."
        }
    ],
    "reminder_and_organization": [
        {
            "q": "Hatırlatıcılarımı gözden kaçırmamak için en iyi dijital uygulama hangisidir?",
            "a": "Google Keep, Todoist veya telefonunuzun varsayılan hatırlatıcı uygulaması oldukça etkilidir. Basit arayüzlü olanları tercih edin."
        },
        {
            "q": "Çok fazla hatırlatıcı kurmak bildirim yorgunluğuna yol açar mı? Nasıl dengelerim?",
            "a": "Her şeye hatırlatıcı kurmayın. Sadece günün en kritik 3-4 işi için bildirim kurun, kalanları normal yapılacaklar listesinden takip edin."
        },
        {
            "q": "Haftalık organizasyon yaparken zaman yönetimini nasıl optimize edebilirim?",
            "a": "Benzer görevleri gruplandırarak (batching) aynı gün veya saatte tamamlayın. E-postaları toplu yanıtlamak buna güzel bir örnektir."
        },
        {
            "q": "Kağıt notlarımı dijital ortama aktarırken nasıl bir düzen kurmalıyım?",
            "a": "Notlarınızı tarayıp bulut depolamada (Google Drive/iCloud) kategorize edilmiş klasörlerde saklayın. Dosya adlarına tarih eklemeyi unutmayın."
        },
        {
            "q": "Planladığım organizasyonu ailem veya iş arkadaşlarımla nasıl paylaşabilirim?",
            "a": "Paylaşımlı takvimler (Google Takvim) veya ortak pano uygulamaları (Trello, Notion) kullanarak herkesin güncel kalmasını sağlayabilirsiniz."
        }
    ],
    "home_and_order": [
        {
            "q": "Ev düzenini korumak için günlük kaç dakikalık bir rutin yeterlidir?",
            "a": "Günde sadece 15 dakikalık hızlı bir toparlama rutini evinizin daima düzenli kalmasını sağlar. Akşam yatmadan önce bunu yapabilirsiniz."
        },
        {
            "q": "Evdeki fazla ve gereksiz eşyalardan kurtulmak için en kolay yöntem nedir?",
            "a": "Son 6 ayda hiç kullanmadığınız veya size mutluluk vermeyen eşyaları ayırın, bağışlayın veya geri dönüşüme gönderin."
        },
        {
            "q": "Küçük çocuklu veya evcil hayvanlı evlerde düzeni nasıl sürdürebiliriz?",
            "a": "Oyuncaklar ve evcil hayvan eşyaları için kapalı, alt raflarda bulunan ve kolay ulaşılabilir kutular/sepetler kullanın."
        },
        {
            "q": "Temizlik yaparken motivasyonu yüksek tutmak için ne önerirsiniz?",
            "a": "Hareketli bir müzik veya merak ettiğiniz bir podcast dinleyerek temizliği eğlenceli ve öğretici bir aktiviteye dönüştürün."
        },
        {
            "q": "Mevsim geçişlerinde gardırop ve ev düzenini nasıl planlamalıyız?",
            "a": "Kullanılmayan mevsimlik kıyafetleri vakumlu torbalarda saklayarak alandan tasarruf edin. Eşyaları kaldırmadan önce yıkayıp kurulayın."
        }
    ],
    "food_and_shopping": [
        {
            "q": "Haftalık yemek hazırlığı (meal prep) yaparken nelere dikkat etmeliyim?",
            "a": "Haftalık menü belirleyin. Malzemeleri önceden yıkayıp doğrayarak cam saklama kaplarında buzdolabında muhafaza edin."
        },
        {
            "q": "Sağlıklı alışveriş yaparken etiket okuma alışkanlığını nasıl kazanabilirim?",
            "a": "Ürünlerin şeker, doymuş yağ, sodyum ve katkı maddesi oranlarına bakın. İçindekiler listesi ne kadar kısaysa ürün o kadar doğaldır."
        },
        {
            "q": "Alışveriş bütçesini aşmamak için en etkili taktik nedir?",
            "a": "Alışverişe kesinlikle tok karnına çıkın, bir liste hazırlayın ve listeniz dışındaki cazip reyonlara uğramayın."
        },
        {
            "q": "Evde yemek yapmaya vakit bulamadığım yoğun günlerde ne pişirebilirim?",
            "a": "Pratik makarnalar, haşlanmış baklagilli salatalar, ton balıklı sandviç veya yumurtalı tarifler harika kurtarıcılardır."
        },
        {
            "q": "Mutfakta sıfır atık düzenine geçmek için ilk adım ne olmalıdır?",
            "a": "Gıda atıklarını kompost yapabilir, kalan sebze saplarını buzlukta biriktirip sebze çorbası suyu için dondurabilirsiniz."
        }
    ],
    "personal_care": [
        {
            "q": "Kaliteli bir uyku için akşam yemeğinden sonra ne yapmalıyım?",
            "a": "Kafein alımını kesin, bitki çayları için, loş ışık kullanın ve yatmadan 1 saat önce tüm ekranları kapatın."
        },
        {
            "q": "Sabahları uyanır uyanmaz enerjik hissetmek için ne yapabilirim?",
            "a": "Uyanır uyanmaz büyük bir bardak su için, pencereyi açarak temiz hava alın ve 5 dakikalık esneme hareketleri yapın."
        },
        {
            "q": "Basit ve sürdürülebilir bir kişisel bakım rutini nasıl oluşturulur?",
            "a": "Cildinizi temizleyin, nemlendirin, güneş koruyucu sürün ve gün içinde bol su tüketerek rutini tamamlayın."
        },
        {
            "q": "Yoğun stres altında kendimi sakinleştirmek için ne yapabilirim?",
            "a": "4-7-8 nefes tekniğini (4 sn al, 7 sn tut, 8 sn ver) uygulayarak sinir sisteminizi hızlıca sakinleştirin."
        },
        {
            "q": "Kişisel bakımda zihinsel sağlığı desteklemek için ne önerirsiniz?",
            "a": "Her gün 10-15 dakika telefonunuzu sessize alıp hiçbir şey yapmadan sadece kendi düşüncelerinizle baş başa kalın."
        }
    ],
    "communication_and_social": [
        {
            "q": "Sosyal ilişkilerde sınır koyarken suçluluk hissetmemek için ne yapmalıyım?",
            "a": "Sınır koymanın kendinize duyduğunuz saygının bir gereği olduğunu ve sağlıklı ilişkilerin temelini oluşturduğunu kendinize hatırlatın."
        },
        {
            "q": "Zor ve gergin konuşmalarda sakinliğimi nasıl koruyabilirim?",
            "a": "Karşı taraf konuşurken sözünü kesmeden aktif şekilde dinleyin ve cevap vermeden önce derin bir nefes alıp 3 saniye bekleyin."
        },
        {
            "q": "Yeni insanlarla tanışırken ilk izlenimi nasıl güçlendirebilirim?",
            "a": "Göz teması kurun, samimi bir gülümsemeyle selamlayın, ismini unutmamak için konuşma içinde tekrarlayın ve aktif dinleyici olun."
        },
        {
            "q": "Uzaktaki arkadaşlarla ilişkileri canlı tutmak için ne önerirsiniz?",
            "a": "Aylık düzenli görüntülü görüşme günleri belirleyin ve gün içindeki komik anları veya küçük hatır sorma mesajlarını paylaşın."
        },
        {
            "q": "Sosyal medyada daha sağlıklı bir iletişim dili nasıl kurulur?",
            "a": "Bir yorum veya mesaj yazmadan önce 'Bu yazdığım karşı tarafa faydalı mı, nazik mi ve gerekli mi?' diye kendinize sorun."
        }
    ],
    "travel_and_outings": [
        {
            "q": "Seyahat hazırlıklarını son güne bırakmamak için nasıl bir plan yapmalıyım?",
            "a": "Yolculuktan 3 gün önce temel bir check-list hazırlayın ve valizinizi son ana sıkıştırmadan yavaş yavaş doldurun."
        },
        {
            "q": "Yolculuk sırasında oluşabilecek mide bulantısı veya stresi nasıl önlerim?",
            "a": "Nane limon çayı veya zencefil tüketin. Yolculuk boyunca derin ve düzenli nefes egzersizleri yapın ve yola odaklanın."
        },
        {
            "q": "Gideceğim yerdeki yerel lezzetleri ve gizli kalmış mekanları nasıl keşfedebilirim?",
            "a": "Turistik popüler rehberler yerine yerel blogları okuyun ve oradaki esnafa, otel çalışanlarına favori yerlerini sorun."
        },
        {
            "q": "Seyahat valizinde alandan tasarruf etmek için en iyi katlama yöntemi hangisidir?",
            "a": "Kıyafetleri katlamak yerine sıkı rulo (ranger roll) yaparak yerleştirmek alandan büyük tasarruf sağlar ve kırışmayı önler."
        },
        {
            "q": "Yabancı bir ülkede dil engeliyle karşılaştığımda ne yapmalıyım?",
            "a": "Çevrimdışı dil paketlerini (Google Çeviri) indirin, temel selamlaşma/teşekkür kelimelerini öğrenin ve beden dilini samimi kullanın."
        }
    ],
    "finance_and_budget": [
        {
            "q": "Bütçe takibini kolaylaştıran en popüler mobil uygulama hangisidir?",
            "a": "Gelir-gider takibi için 1Money, Spendee veya Money Manager uygulamalarını kullanabilir ya da basit bir Excel şablonu tutabilirsiniz."
        },
        {
            "q": "Dürtüsel alışveriş (impulsive buying) isteğini nasıl kontrol altına alabilirim?",
            "a": "Beğendiğiniz bir ürünü satın almadan önce kendinize 24 saat veya 3 gün düşünme süresi verin, dürtünün azaldığını göreceksiniz."
        },
        {
            "q": "Küçük tasarrufların (örneğin dışarıda kahve içmemek) bütçeye etkisi nedir?",
            "a": "Küçük tasarruflar birikerek uzun vadede ciddi bir acil durum fonu oluşturmanızı veya finansal hedeflerinize daha hızlı ulaşmanızı sağlar."
        },
        {
            "q": "Kredi kartı borçlarını yönetirken en sık yapılan hata nedir?",
            "a": "Sadece asgari tutarı ödemek borcun faizle kartopu gibi büyümesine yol açar; her ay borcun tamamını kapatmaya çalışın."
        },
        {
            "q": "Finansal hedefler belirlerken gerçekçi olmak için nelere dikkat etmeliyiz?",
            "a": "Hedeflerinizi net rakamlar ve sürelerle tanımlayın (örn. '6 ayda acil durum fonu için 15.000 TL biriktirmek') ve gelirinize göre ayarlayın."
        }
    ],
    "technology_and_digital": [
        {
            "q": "Dijital detoks (digital detox) yapmaya nereden başlamalıyım?",
            "a": "Yatak odasına telefon sokmayarak başlayın. Akşam 9'dan sonra bildirimleri kapatın ve bunun yerine kitap okumayı deneyin."
        },
        {
            "q": "Bilgisayar ve telefondaki dosyaları düzenli tutmak için haftalık ne yapmalıyım?",
            "a": "Her cuma günü masaüstündeki geçici dosyaları silin veya ilgili klasörlere taşıyın. İndirilenler klasörünü tamamen boşaltın."
        },
        {
            "q": "İnternet güvenliği için iki faktörlü doğrulamayı (2FA) açmak neden önemlidir?",
            "a": "Şifreniz çalınsa bile telefonunuza gelen onay kodu olmadan hesabınıza yabancı kişilerin erişmesini tamamen engeller."
        },
        {
            "q": "Sosyal medya bağımlılığından kurtulmak için en etkili teknik nedir?",
            "a": "Sosyal medya uygulamalarını ana ekrandan kaldırın veya erişimi zorlaştırmak için her gün ekran süresi limiti koyun."
        },
        {
            "q": "Bulut depolama alanımı (Google Drive/iCloud) nasıl verimli kullanabilirim?",
            "a": "Büyük boyutlu eski videoları temizleyin ve dosyalarınızı 'Yıl_Ay_Konu' formatında isimlendirerek tek bir klasör yapısında toplayın."
        }
    ],
    "education_and_learning": [
        {
            "q": "Yeni bir yabancı dil öğrenirken günlük ne kadarlık çalışma yeterlidir?",
            "a": "Haftada bir gün 2 saat çalışmak yerine, her gün düzenli 15-20 dakika pratik yapmak kalıcı öğrenme için çok daha etkilidir."
        },
        {
            "q": "Öğrendiğim bilgilerin kalıcı hafızaya aktarılması için en iyi yöntem hangisidir?",
            "a": "Feynman tekniğini uygulayarak konuyu sanki 10 yaşındaki bir çocuğa anlatıyormuş gibi basit kelimelerle açıklamayı deneyin."
        },
        {
            "q": "Ders çalışırken odaklanmayı artıran müzik türleri nelerdir?",
            "a": "Sözsüz klasik müzik, lo-fi ritimler, doğa sesleri veya alfa dalgası müzikleri zihni sakinleştirerek odaklanmayı destekler."
        },
        {
            "q": "Online eğitimlerden (Coursera, Udemy vb.) maksimum verim almak için ne yapmalıyım?",
            "a": "Eğitimi sadece izlemekle kalmayın, aktif notlar alın, her bölümden sonra küçük uygulamalar yapın ve kendinizi test edin."
        },
        {
            "q": "Öğrenme sürecinde erteleme hastalığını (procrastination) nasıl aşabilirim?",
            "a": "Çalışma hedeflerinizi çok küçük parçalara bölün (örn. 'Bugün sadece 1 sayfa okuyacağım' veya '1 kelime öğreneceğim')."
        }
    ],
    "work_and_career": [
        {
            "q": "İş yerinde tükenmişlik (burnout) hissettiğimde ilk ne yapmalıyım?",
            "a": "Yöneticinizle sınırlarınızı konuşun, kısa bir izin kullanın ve mesai saatleri dışında iş e-postalarına bakmayı tamamen bırakın."
        },
        {
            "q": "Evden çalışırken (remote work) iş-özel yaşam dengesini nasıl kurabilirim?",
            "a": "Çalışma saatlerinizin başlangıç ve bitişini netleştirin. Mesai bitince iş bilgisayarını kapatıp ayrı bir odaya kaldırın."
        },
        {
            "q": "İş yerindeki çatışmaları profesyonelce çözmek için nasıl bir iletişim dili kullanmalıyım?",
            "a": "Suçlayıcı 'sen' dili yerine, durum odaklı ve kendi hislerinizi belirten yapıcı 'ben' dilini ('Böyle olduğunda çalışmakta zorlanıyorum') tercih edin."
        },
        {
            "q": "Kariyerimde yükselmek için kendimi nasıl sürekli güncel tutabilirim?",
            "a": "Sektörünüzdeki güncel makaleleri okuyun, profesyonel topluluklara katılın ve yeni beceriler için sertifika programlarını takip edin."
        },
        {
            "q": "Verimli bir iş günü için sabah ilk çalışma saatini nasıl değerlendirmeliyiz?",
            "a": "E-postaları kontrol etmeden önce, günün en zor ve en önemli işini (kurbağayı yemek) tamamlayın. E-postalar dikkatinizi dağıtabilir."
        }
    ],
    "family_and_relationships": [
        {
            "q": "Aile içinde sağlıklı sınırları korurken kırıcı olmamayı nasıl başarabiliriz?",
            "a": "Sınırlarınızı sevgiyle, sakin bir ses tonuyla ve gerekçelerini net şekilde açıklayarak ifade edin. 'Şu an dinlenmeye ihtiyacım var' diyebilirsiniz."
        },
        {
            "q": "Eşler arasında ortak finansal kararlar alırken nelere dikkat edilmelidir?",
            "a": "Gelir ve giderleri şeffafça paylaşın. Ortak hedefler koyun ve her iki tarafa da kişisel harcama limitleri belirleyerek özgürlük tanıyın."
        },
        {
            "q": "Çocukların ekran süresini (tablet/telefon) sınırlandırmak için ne yapabiliriz?",
            "a": "Birlikte kutu oyunları oynayın, açık hava aktiviteleri planlayın, sınırları net koyun ve en önemlisi siz de onların yanında ekran sürenizi azaltın."
        },
        {
            "q": "Aile içindeki geçmiş kırgınlıkları çözmek için nasıl bir adım atabiliriz?",
            "a": "Geçmişi suçlamak yerine bugünkü hislerinizi paylaşın. 'Geçmişte olanlar beni üzdü ama gelecekte daha iyi iletişim kurmak istiyorum' diyebilirsiniz."
        },
        {
            "q": "Yaşlı aile üyeleriyle iletişimi güçlendirmek için ne önerirsiniz?",
            "a": "Onların hayat tecrübelerini ve hikayelerini sabırla dinleyin, teknolojik konularda destek olun ve gün içinde düzenli arayıp seslerini duyun."
        }
    ],
    "healthy_living": [
        {
            "q": "Daha sağlıklı bir yaşam için günlük su tüketimi ne kadar olmalıdır?",
            "a": "Genel kural olarak günde en az 2-2.5 litre su içmeye özen gösterin. İdrar renginizin açık sarı olması yeterli su aldığınızı gösterir."
        },
        {
            "q": "Evde ekipmansız yapabileceğim en etkili günlük egzersiz hangisidir?",
            "a": "Vücut ağırlığıyla yapılan squat, lunge, şınav, plank ve 5 dakikalık esneme hareketleri genel zindelik için harika bir başlangıçtır."
        },
        {
            "q": "Gün içinde göz sağlığımı korumak için ekran karşısında ne yapmalıyım?",
            "a": "20-20-20 kuralını uygulayın: Her 20 dakikada bir, 20 saniye boyunca, 20 fit (6 metre) uzağa bakarak göz kaslarınızı dinlendirin."
        },
        {
            "q": "Sağlıklı beslenmeye başlarken en sık yapılan hata nedir?",
            "a": "Sürdürülemez şok diyetler uygulamaktır. Bunun yerine porsiyonları kademeli küçültün, işlenmiş gıdaları ve şekeri yavaş yavaş hayatınızdan çıkarın."
        },
        {
            "q": "Duruş bozukluğunu (kamburluk) önlemek için masa başında ne yapabiliriz?",
            "a": "Dik oturun, omuzlarınızı geriye alın, ekran yüksekliğini göz hizasına getirin ve saat başı kalkıp kısa omuz ve boyun esneme egzersizleri yapın."
        }
    ],
    "mindset_and_motivation": [
        {
            "q": "Negatif düşünce sarmalından (overthinking) hızlıca nasıl çıkabilirim?",
            "a": "Düşüncelerinizi bir kağıda yazın. Sonra kendinize 'Bu durum şu an benim kontrolümde mi?' diye sorun ve sadece kontrol edebileceğiniz adıma odaklanın."
        },
        {
            "q": "Başarısızlık korkusunu yenmek için zihniyetimi nasıl değiştirebilirim?",
            "a": "Başarısızlığı bir son değil, öğrenme sürecinin en doğal ve en değerli geri bildirimi olarak görün. Hatalar sizi hedefinize yaklaştırır."
        },
        {
            "q": "İçsel motivasyonumu kaybetmeden uzun vadeli hedeflerime nasıl sadık kalırım?",
            "a": "Büyük hedefinize ulaştığınızda elde edeceğiniz kazançları yazıp görebileceğiniz yere asın. Yolculuktaki küçük başarıları da ödüllendirin."
        },
        {
            "q": "Kendimi başkalarıyla kıyaslama alışkanlığından nasıl kurtulabilirim?",
            "a": "Sadece kendi dünkü halinizle bugünkü halinizi kıyaslayın. Herkesin şartları, yetenekleri ve yolculuğu tamamen farklıdır."
        },
        {
            "q": "Güne yüksek motivasyonla başlamak için sabah ilk ne yapmalıyım?",
            "a": "Güne şükrettiğiniz veya memnun olduğunuz 3 basit şeyi zihninizden geçirerek başlayın. Pozitif zihin güne harika yön verir."
        }
    ],
    "hobbies_and_leisure": [
        {
            "q": "Hobi edinmek için bütçe ayırmam şart mı? Ücretsiz hobiler nelerdir?",
            "a": "Şart değildir. Doğa yürüyüşü, çizim, günlük yazma, yabancı dil öğrenme, evde meditasyon veya origami gibi harika ücretsiz hobiler vardır."
        },
        {
            "q": "Zamanım çok kısıtlı, hobime nasıl vakit ayırabilirim?",
            "a": "Haftalık planınızda hobiniz için cuma akşamı veya pazar sabahı gibi sabit 30 dakikalık bloklar ayırın ve bu zamana sadık kalın."
        },
        {
            "q": "Yeni başladığım bir hobide hemen pes etmemek için ne yapmalıyım?",
            "a": "Günde sadece 10 dakika pratik yapın. Başlangıçta mükemmeliyeti değil, düzenli devam etmeyi hedefleyin. Alışkanlık kazandıkça süreç kolaylaşır."
        },
        {
            "q": "Hobimi bir gelir kaynağına (side hustle) dönüştürmek mantıklı mı?",
            "a": "Eğer hobinizden keyif almayı sürdürebilecekseniz mantıklıdır. Ancak gelir baskısı hobiyi strese dönüştürüyorsa sadece hobi olarak bırakmak daha iyidir."
        },
        {
            "q": "Yalnız vakit geçirmeyi sevmeyen biri için sosyal hobiler nelerdir?",
            "a": "Grup doğa yürüyüşleri, amatör tiyatro toplulukları, dans kursları, kitap kulüpleri veya ortak spor aktiviteleri harika sosyal hobilerdir."
        }
    ],
    "decision_making": [
        {
            "q": "Önemli kararlar alırken duygusal davranmamak için ne yapmalıyım?",
            "a": "Karar vermeden önce en az bir gece uyuyun ve durumu tamamen rasyonel artı-eksi tablosuna dökün. Acele karar vermekten kaçının."
        },
        {
            "q": "Grup içinde ortak karar alırken uzlaşmayı nasıl kolaylaştırabiliriz?",
            "a": "Herkesin önceliklerini tarafsızca dinleyin. Ortak hedefleri vurgulayın ve her iki tarafın da kabul edebileceği esnek orta noktalara odaklanın."
        },
        {
            "q": "Karar yorgunluğunu (decision fatigue) azaltmak için günlük ne yapabiliriz?",
            "a": "Kıyafet seçimi, haftalık yemek planı gibi basit kararları önceden sabitleyerek zihinsel enerjinizi önemli kararlara saklayın."
        },
        {
            "q": "Aldığım kararın yanlış olduğunu fark ettiğimde ne yapmalıyım?",
            "a": "Durumu kabul edin, pişmanlığa takılmak yerine ders çıkarın ve rotanızı hızlıca güncelleyin. Kararlar dinamiktir."
        },
        {
            "q": "Hızlı karar vermem gereken kriz anlarında nasıl sakin kalırım?",
            "a": "Durumu 3 saniye durup dışarıdan izleyin, en kötü senaryoyu düşünün ve en güvenli, en basit ilk adımı seçerek eyleme geçin."
        }
    ],
    "daily_practical_tips": [
        {
            "q": "Evden çıkarken anahtar/cüzdan unutmamak için en pratik çözüm nedir?",
            "a": "Giriş kapısının hemen yanına şık bir anahtarlık kutusu koyun ve bu eşyaları eve girer girmez oraya bırakmayı kural edinin."
        },
        {
            "q": "Haftalık çamaşır ve bulaşık yükünü azaltmak için nasıl bir düzen kurmalıyız?",
            "a": "Bulaşıkları tezgahta bekletmeden hemen durulayıp makineye yerleştirin. Çamaşırları renklerine göre ayrı sepetlerde biriktirin."
        },
        {
            "q": "Evde basit ilk yardım çantasında mutlaka bulunması gerekenler nelerdir?",
            "a": "Sargı bezi, yara bandı, antiseptik solüsyon, ağrı kesici, cımbız, steril eldiven ve yanık kremi mutlaka bulunmalıdır."
        },
        {
            "q": "Telefon şarjımın gün ortasında bitmesini önlemek için ne yapmalıyım?",
            "a": "Kullanmadığınız konum ve Bluetooth servislerini kapatın, ekran parlaklığını otomatik yapın ve arka planda çalışan uygulamaları sınırlayın."
        },
        {
            "q": "Günlük kıyafet kombinlerimi hızlıca seçmek için pazar gününden ne yapabilirim?",
            "a": "Haftalık 5 günlük kombinleri pazar akşamından askılara hazırlayarak sabah hazırlanırken harcayacağınız süreyi sıfırlayın."
        }
    ],
    "general_assistant": [
        {
            "q": "Bana verdiğiniz önerileri nasıl daha verimli uygulayabilirim?",
            "a": "Önerilerden sadece bir tanesini seçin ve 3 gün boyunca düzenlice uygulayın. Küçük adımların birikim gücüne güvenin."
        },
        {
            "q": "Asistan olarak bana başka hangi konularda pratik destek sağlayabilirsiniz?",
            "a": "Günlük planlama, verimlilik, ev düzeni, beslenme, seyahat, dijital yaşam ve çalışma pratiklerinde destek sunabilirim."
        },
        {
            "q": "Kişisel hedeflerimi gerçekleştirmemde bana nasıl yardımcı olabilirsiniz?",
            "a": "Hedeflerinizi küçük uygulanabilir adımlara bölebilir, günlük planlar, kontrol listeleri ve motivasyon adımları hazırlayabilirim."
        },
        {
            "q": "Zaman yönetimi konusunda bana en temel tavsiyeniz nedir?",
            "a": "Günün en önemli ve en zor işini sabah ilk sırada yapın (kurbağayı yiyin) ve çoklu iş (multitask) yapmaktan kesinlikle kaçının."
        },
        {
            "q": "Benim için günlük bir odaklanma cümlesi veya felsefesi önerir misiniz?",
            "a": "Bugün sadece elinizdeki işi en iyi şekilde yapmaya odaklanın. Gelecek, bugünkü küçük ve düzenli adımlarınızla şekillenir."
        }
    ]
}

def main():
    pack_path = "app/src/main/assets/local_assistant_daily_life_pack_1.json"
    if not os.path.exists(pack_path):
        print(f"Error: {pack_path} not found.")
        sys.exit(1)
        
    with open(pack_path, 'r', encoding='utf-8') as f:
        qa_items = json.load(f)
        
    print(f"Loaded {len(qa_items)} items from daily life pack.")
    
    new_followup_items = []
    
    for idx, item in enumerate(qa_items):
        num = idx + 1
        cat_key = item["category"]
        if cat_key not in CATEGORIES_TEMPLATES:
            # Fallback to general_assistant if category key not found in templates
            cat_key = "general_assistant"
            
        templates = CATEGORIES_TEMPLATES[cat_key]
        
        # Populate follow-up questions list in base item (TR & EN)
        tr_followups = []
        en_followups = []
        
        print(f"[{num}/200] Generating follow-ups for '{item['question_tr'][:30]}...' in category '{cat_key}'")
        
        for f_idx, temp in enumerate(templates):
            f_num = f_idx + 1
            tr_q = temp["q"]
            tr_a = temp["a"]
            
            # Translate follow-up Q&A to English
            en_q = translate_to_english(tr_q)
            en_a = translate_to_english(tr_a)
            
            tr_followups.append(tr_q)
            en_followups.append(en_q)
            
            # Create a new local QA item for this follow-up node
            followup_id = f"daily_life_{num}_followup_{f_num}"
            keywords = extract_keywords(tr_q, en_q)
            
            followup_item = {
                "id": followup_id,
                "category": cat_key,
                "keywords": keywords,
                "question_tr": tr_q,
                "question_en": en_q,
                "safetyLevel": "routine",
                "tr": {
                    "safetyLevel": "routine",
                    "patientIdentifierDetected": False,
                    "userAppearsToBeClinician": False,
                    "notASubstituteWarning": "Bu yanıt genel günlük yaşam asistanı paketi kapsamındadır.",
                    "conversationalText": tr_a,
                    "immediateRedFlags": [],
                    "missingCriticalInformation": [],
                    "likelyClinicalCategoriesToConsider": [],
                    "suggestedNextStepsGeneral": [],
                    "relatedAlgorithms": [],
                    "relatedCalculators": [],
                    "relatedDrugCards": [],
                    "doNotMiss": [],
                    "whenToEscalate": [],
                    "referencesToShow": [],
                    "followUpQuestions": []
                },
                "en": {
                    "safetyLevel": "routine",
                    "patientIdentifierDetected": False,
                    "userAppearsToBeClinician": False,
                    "notASubstituteWarning": "This response is part of the general daily life assistant pack.",
                    "conversationalText": en_a,
                    "immediateRedFlags": [],
                    "missingCriticalInformation": [],
                    "likelyClinicalCategoriesToConsider": [],
                    "suggestedNextStepsGeneral": [],
                    "relatedAlgorithms": [],
                    "relatedCalculators": [],
                    "relatedDrugCards": [],
                    "doNotMiss": [],
                    "whenToEscalate": [],
                    "referencesToShow": [],
                    "followUpQuestions": []
                }
            }
            new_followup_items.append(followup_item)
            
        # Update parent item's followUpQuestions lists
        item["tr"]["followUpQuestions"] = tr_followups
        item["en"]["followUpQuestions"] = en_followups
        
    # Combine original modified items and new followups
    all_items = qa_items + new_followup_items
    
    with open(pack_path, 'w', encoding='utf-8') as out_f:
        json.dump(all_items, out_f, ensure_ascii=False, indent=2)
        
    print(f"Successfully added {len(new_followup_items)} new follow-up QA items to {pack_path}.")
    print(f"Total database count: {len(all_items)} items.")

if __name__ == '__main__':
    main()
