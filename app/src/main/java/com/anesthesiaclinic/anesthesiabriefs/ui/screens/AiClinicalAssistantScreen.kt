package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.focus.onFocusChanged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import java.net.HttpURLConnection
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.jsonPrimitive
import com.anesthesiaclinic.anesthesiabriefs.data.model.*
import com.anesthesiaclinic.anesthesiabriefs.data.repository.AuthRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*
import com.anesthesiaclinic.anesthesiabriefs.utils.TranslationHelper
import kotlinx.coroutines.launch
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

// Premium Question Domain Definition
data class PremiumQuestion(
    val title: String,
    val prompt: String
)

val premiumCategories = mapOf(
    "Preop AI" to listOf(
        PremiumQuestion("ASA Sınıfı Değerlendir", "Bu hasta için ASA sınıfı nedir ve elektif cerrahiye uygun mudur?"),
        PremiumQuestion("Kardiyak Risk (RCRI)", "Bu hastanın kardiyak riski nedir? RCRI skorunu ve fonksiyonel kapasitesini nasıl değerlendiririm?"),
        PremiumQuestion("Günübirlik Cerrahi Uygunluğu", "Bu hasta günübirlik cerrahi için uygun mudur? OSAS ve ASA kısıtlamaları nelerdir?"),
        PremiumQuestion("Açlık / NPO Uygunluğu", "Bu hasta açlık (NPO) süreleri açısından ameliyata uygun mudur? 2-4-6-8 kuralına göre değerlendir."),
        PremiumQuestion("Cerrahi Erteleme Kriterleri", "Bu hastada cerrahiyi ertelemeyi gerektiren hipertansiyon veya diğer laboratuvar durumları nelerdir?")
    ),
    "Airway AI" to listOf(
        PremiumQuestion("Zor Havayolu Planı", "Bu hastada zor havayolu riski var mı? Adım adım uyanık fiberoptik veya GA havayolu planı oluştur."),
        PremiumQuestion("Obez / OSAS Havayolu", "Obez veya obstrüktif uyku apnesi (OSAS) olan hastada havayolu planı ve ekstübasyon hazırlığı nasıl olmalı?"),
        PremiumQuestion("Entübasyon Deneme Sınırı", "Zor havayolu laringoskopisinde kaç entübasyon denemesi yapılmalıdır? Tekrarlayan denemelerin riskleri nelerdir?"),
        PremiumQuestion("ARDS Ventilasyon Planı", "ARDS veya KOAH hastasında intraoperatif koruyucu ventilasyon (LTVT) parametreleri nasıl ayarlanmalıdır?"),
        PremiumQuestion("ETCO2 / Kapnogram Analizi", "Anestezi altında kapnografi (ETCO2) dalgasında curare cleft, bronkospazm veya ani düşüş görülürse ayırıcı tanı nedir?")
    ),
    "Emergency AI" to listOf(
        PremiumQuestion("Satürasyon Düşüyor (Hipoksemi)", "Satürasyon düşüyor, pik hava yolu basıncı yüksek! Hipoksemi algoritmasını adım adım başlat."),
        PremiumQuestion("Tansiyon Düştü (Hipotansiyon)", "Tansiyon düştü! İndüksiyon vazoplejisi, kanama veya hipovolemiye bağlı hipotansiyon yönetim algoritmasını başlat."),
        PremiumQuestion("Nabız Düştü (Bradikardi)", "Nabız düştü, hemodinami instabil! Bradikardi acil kurtarma ve atropin/pacing algoritmasını başlat."),
        PremiumQuestion("Nabız Yükseldi (Taşikardi)", "Nabız yükseldi! Stabil veya instabil taşikardi ve SVT/VT kurtarma algoritmasını başlat."),
        PremiumQuestion("Anafilaksi Şüphesi", "Ameliyathanede anafilaksi şüphesi var! Adrenalin (epinefrin) dozu, agresif sıvı tedavisi ve kriz protokolünü göster."),
        PremiumQuestion("Malign Hipertermi (MH)", "Malign hipertermi kriz şüphesi var! Volatil ajanları kesme, aktif soğutma ve Dantrolen flakon dozunu hesapla."),
        PremiumQuestion("LAST (Lokal Toksisite)", "Lokal anestezik sistemik toksisitesi (LAST) gelişti! Nöbet/aritmi yönetimi ve %20 Lipid Emülsiyon bolus/infüzyon dozunu hesapla."),
        PremiumQuestion("CICO (Havayolu Acil)", "Cannot Intubate Cannot Oxygenate (CICO) durumu ilan edildi! Acil neşter-bougie krikotirotomi algoritmasını göster."),
        PremiumQuestion("Kardiyak Arrest (CPR)", "Kardiyak arrest gelişti! Defibrilatör şoku, göğüs kompresyonu ve ACLS (Adrenalin/Amiodaron) resüsitasyon adımlarını başlat."),
        PremiumQuestion("Gebede/Çocukta Arrest", "Obstetrik (gebe) veya pediatrik (çocuk) hastada kardiyak arrest ve sol uterus deplasmanı / PALS resüsitasyon algoritmasını göster.")
    ),
    "Dose AI" to listOf(
        PremiumQuestion("İndüksiyon Ajan Seçimi", "Ciddi hipotansif, septik veya kardiyak hastada en güvenli indüksiyon ajanı (Propofol vs. Etomidat vs. Ketamin) nedir?"),
        PremiumQuestion("Kas Gevşetici & Reversal", "Rokuronyum ve Süksinilkolin dozu, kontrendikasyonları ve TOF monitör takibine göre reversal kılavuzu nedir?"),
        PremiumQuestion("Sugammadeks Doz Hesabı", "Sugammadeks ve Neostigmin dozu derin/yüzeysel nöromüsküler blokta kilograma göre nasıl hesaplanır?"),
        PremiumQuestion("Noradrenalin Hazırlama", "Noradrenalin ve Adrenalin acil perfüzyon infüzyon şırıngaları mg/mL olarak nasıl hazırlanır?"),
        PremiumQuestion("Pediatrik Doz Hesapları", "Pediatrik hastada kiloya göre indüksiyon ilaçları, CPR ilaçları, tüp çapı ve sıvı idame hızını hesapla.")
    ),
    "Regional AI" to listOf(
        PremiumQuestion("Blok Kriterleri ve Güvenlik", "Nöroaksiyel blok (spinal/epidural) öncesi hasta güvenliği taraması, trombosit sınırları ve kontrendikasyonları nelerdir?"),
        PremiumQuestion("Kan Sulandırıcı Durdurma", "ASRA 2025 kılavuzuna göre kan sulandırıcı (Warfarin, DOAC, Aspirin, LMWH) ilaçların kesilme ve başlanma süreleri nedir?"),
        PremiumQuestion("Maksimum Lokal Anestezik", "Lidokain ve Bupivakain için adrenalinli ve adrenalinsiz toksik sınırları ve maksimum mg dozlarını hesapla."),
        PremiumQuestion("Sezaryende Hipotansiyon", "Sezaryen operasyonunda spinal anestezi sonrası hipotansiyon önlenmesi, efedrin/fenilefrin seçimi nasıl olmalı?"),
        PremiumQuestion("Epidural Hematom Şüphesi", "Rejyonel anestezi sonrası bacaklarda motor kaybı var! Epidural hematom şüphesi, lomber MRG ve acil dekompresyon süreleri nedir?")
    ),
    "PACU AI" to listOf(
        PremiumQuestion("Aldrete & Taburculuk", "PACU (derlenme) taburculuk kriterleri nelerdir? Aldrete skoru nasıl hesaplanır?"),
        PremiumQuestion("PONV Risk & Profilaksi", "Postoperatif bulantı-kusma (PONV) risk sınıflaması (Apfel skoru) ve multimodal Ondansetron/Deksametazon profilaksisi nedir?"),
        PremiumQuestion("Multimodal Ağrı Planı", "Postoperatif ağrı yönetiminde Parasetamol, NSAID ve opioid içeren multimodal analjezi planı oluştur."),
        PremiumQuestion("PACU Solunum Depresyonu", "Derlenme odasında hastada solunum depresyonu veya rezidüel blok şüphesi var! Sugammadeks veya Nalokson kurtarma planı nedir?"),
        PremiumQuestion("Titreme / Deliryum Tedavisi", "Postoperatif titreme veya ajitasyon/deliryum vakasında farmakolojik tedavi yaklaşımları nelerdir?")
    ),
    "ICU AI" to listOf(
        PremiumQuestion("Sepsis 1. Saat Paketi", "Sepsis veya septik şok şüphesi olan hastada Surviving Sepsis 1-Hour Bundle ve Noradrenalin titrasyon planı nedir?"),
        PremiumQuestion("Şok Tipleri Ayırıcı Tanı", "Kardiyojenik, hipovolemik ve dağılımsal (septik/anafilaktik) şok tablolarında hemodinamik parametreler ve ayırıcı tanı nasıldır?"),
        PremiumQuestion("RASS Sedasyon Takibi", "Yoğun bakımde mekanik ventilasyon sedasyon derinliği (RASS skoru) ve deliryum (CAM-ICU) taraması nasıl yapılır?"),
        PremiumQuestion("Yoğun Bakım Ekstübasyon", "Yoğun bakım hastasında mekanik ventilatör weaning (ayırma) hazırlığı, cuff sızıntı testi ve ekstübasyon kriterleri nelerdir?"),
        PremiumQuestion("Oligüri Adım Adım Eylem", "Yoğun bakım veya intraoperatif idrar çıkışı azlığı (Oligüri) durumunda hipovolemi/renal perfüzyon değerlendirmesi ve eylem planı nedir?")
    ),
    "Obstetric & Pediatric AI" to listOf(
        PremiumQuestion("Preeklampsi Anestezi Yaklaşımı", "Preeklampside anestezi yaklaşımı, zor havayolu ve magnezyum sülfat duyarlılığı nedir?"),
        PremiumQuestion("Eklampsi Nöbeti Yönetimi", "Eklampsi nöbetinde magnezyum sülfat dozlama, toksisite ve kalsiyum glukonat kullanımı nasıldır?"),
        PremiumQuestion("Postpartum Hemoraji", "Postpartum hemorajide anestezi ekibi ne yapmalı? Uterotonikler, masif transfüzyon ve fibrinojen."),
        PremiumQuestion("Pediatrik Bradikardi", "Çocukta anestezi altında bradikardi nedenleri, hipoksi düzeltme ve atropin/epinefrin dozları."),
        PremiumQuestion("Çocukta ÜSYE Kararı", "Üst solunum yolu enfeksiyonu (ÜSYE) olan çocukta anestezi güvenliği ve ameliyat erteleme kriterleri."),
        PremiumQuestion("Pediatrik Laringospazm", "Çocukta laringospazm gelişirse CPAP, propofol, süksinilkolin ve atropin içeren eylem planı.")
    ),
    "Special Tech AI" to listOf(
        PremiumQuestion("Pacemaker / ICD Yönetimi", "Pacemaker veya ICD olan hastada anestezi öncesi hazırlık, koter planı ve mıknatıs kullanımı."),
        PremiumQuestion("Elektrokoter & Pil Etkileşimi", "Elektrokoter pil etkileşimini önlemek için bipolar koter ve grounding pad yerleşimi nasıldır?"),
        PremiumQuestion("Tek Akciğer Ventilasyonu", "Tek akciğer ventilasyonunda (OLV) hipoksemi geliştiğinde CPAP, PEEP ve iki akciğer ventilasyon adımları."),
        PremiumQuestion("Laparoskopide ETCO2 Artışı", "Laparoskopide yükselen ETCO2 durumunda CO2 absorbsiyonu, CO2 embolisi ve pnömotoraks ayırıcı tanısı."),
        PremiumQuestion("TURP Sendromu Şüphesi", "TURP sendromu belirtileri nelerdir? Dilüsyonel hiponatremi ve semptomatik tedavide %3 hipertonik salin."),
        PremiumQuestion("Cihaz / Oksijen Hatası", "Anestezi cihazı veya oksijen arızası durumunda acil ambu ile ventilasyon kurtarma planı.")
    ),
    "Special Patient AI" to listOf(
        PremiumQuestion("OSAS Anestezi Planı", "OSAS hastasında anestezi planı, zor havayolu, postop obstrüksiyon ve CPAP yönetimi."),
        PremiumQuestion("OSAS Günübirlik Cerrahi", "OSAS hastası günübirlik cerrahiye uygun mudur? Erteleme ve evde takip kriterleri."),
        PremiumQuestion("Obez Hastada İndüksiyon", "Obez hastada indüksiyon pozisyonu, preoksijenasyon, ilaç dozlamaları ve ventilatör hedefleri."),
        PremiumQuestion("Bariatrik Cerrahi", "Bariatrik cerrahi anestezi yönetimi, ventilasyon modları, rabdomiyoliz ve DVT profilaksisi."),
        PremiumQuestion("Günübirlik Taburculuk", "Günübirlik cerrahi taburculuk kriterleri, Aldrete/PADSS skorlaması ve POUR riski."),
        PremiumQuestion("Kronik Opioid Kullanımı", "Kronik opioid kullanan hastada postoperatif ağrı yönetimi, tolerans ve opioid PCA dozu."),
        PremiumQuestion("Alkol/Benzodiazepin Bağımlılığı", "Alkol veya benzodiazepin bağımlılığında perioperatif Wernicke ensefalopatisi ve yoksunluk yönetimi."),
        PremiumQuestion("Böbrek Yetmezliği", "Böbrek yetmezliğinde anestezi, hiperkalemi tedavisi, cisatrakuryum kullanımı ve fistül korunması."),
        PremiumQuestion("Hemodiyaliz Zamanı", "Hemodiyaliz hastasında ameliyat zamanlaması, diyaliz ilişkili heparin ve acil diyaliz K limitleri."),
        PremiumQuestion("Karaciğer Yetmezliği", "Karaciğer yetmezliği anestezi yönetimi, Child-Pugh/MELD skoru, asit ve koagülopati."),
        PremiumQuestion("Sirozda Rejyonal Blok", "Siroz hastasında spinal veya epidural güvenlik limitleri, trombosit sınırları ve epidural hematom.")
    ),
    "Endocrine & Neuro AI" to listOf(
        PremiumQuestion("Steroid Stres Dozu", "Kronik steroid kullanan hastada stres dozu replasmanı, hidrokortizon dozları ve Addison krizi."),
        PremiumQuestion("Adrenal Kriz", "Adrenal kriz belirtileri nelerdir? Hidrokortizon kurtarma tedavisi ve sıvı resüsitasyonu."),
        PremiumQuestion("Hipertiroidi / Tirotoksikoz", "Hipertiroidi perioperatif yönetimi, ötiroid erteleme ve sempatomimetik ilaç etkileşimleri."),
        PremiumQuestion("Tiroid Fırtınası", "Tiroid fırtınası belirtileri nelerdir? Aktif soğutma, esmolol ve PTU acil tedavisi."),
        PremiumQuestion("Hipotiroidi", "Hipotiroidi ameliyat erteleme kriterleri, anestezik hassasiyeti ve miksödem koması."),
        PremiumQuestion("Feokromositoma", "Feokromositoma perioperatif anestezi yönetimi, preop Roizen kriterleri ve tümör rezeksiyon şoku."),
        PremiumQuestion("Kafa İçi Basınç (KİBA)", "Kafa içi basıncı yüksek hastada serebral perfüzyon (CPP), normokapni ve mannitol tedavisi."),
        PremiumQuestion("Travmatik Beyin Hasarı", "Travmatik beyin hasarında ikincil hasarı önleme, MAP hedefleri ve MILS ile entübasyon.")
    ),
    "Trauma & Position AI" to listOf(
        PremiumQuestion("Prone Pozisyon Güvenliği", "Prone (yüzüstü) pozisyonda havayolu tespiti, göz basısı koruması ve yastıklama kuralları."),
        PremiumQuestion("Beach-Chair Pozisyonu", "Beach-chair pozisyonunda dikey yükseklik hidrostatik basınç farkı ve serebral perfüzyon."),
        PremiumQuestion("Litotomi Pozisyonu", "Litotomi pozisyonu sinir hasarları (peroneal/femoral), kompartman sendromu ve indirme hipotansiyonu."),
        PremiumQuestion("Yanık Sıvı / Parkland", "Yanık hastasında ilk 24 saat sıvı hesabı, Parkland formülü ve idrar hedefleri."),
        PremiumQuestion("Yanıkta Süksinilkolin", "Yanık hastasında süksinilkolin ilişkili hiperkalemi riski, 24 saat kuralı ve EKG değişiklikleri."),
        PremiumQuestion("Travma İndüksiyonu", "Travma hastasında hızlı indüksiyon (RSI), şokta indüksiyon dozları ve servikal omurga."),
        PremiumQuestion("Şok İndeksi", "Şok indeksi (HR/SBP) nasıl yorumlanır? Okült kanama taraması ve transfüzyon sınırları.")
    ),
    "Perioperative Meds AI" to listOf(
        PremiumQuestion("ACE / ARB Sabahı", "ACE inhibitörleri ve ARB'lerin ameliyat sabahı kesilmesi, vazoplejik şok ve vazopressin tedavisi."),
        PremiumQuestion("Beta Bloker Yönetimi", "Kronik beta bloker kullanan hastada perioperatif yönetim ve ani kesilme riskleri."),
        PremiumQuestion("Antidepresan Yönetimi", "SSRI, SNRI ve MAOI kullanan hastada serotonin sendromu, meperidin etkileşimi ve efedrin kontrendikasyonu."),
        PremiumQuestion("Bitkisel Ürün / Supplement", "Ginkgo, Ginseng ve Sarı Kantaron (St. John's) gibi bitkisel ürünlerin kanama/sedasyon riskleri.")
    ),
    "Neurology & Neonatal AI" to listOf(
        PremiumQuestion("Myastenia Gravis", "Myastenia gravis hastasında anestezi planı, kas gevşetici duyarlılığı, TOF takibi ve postop yoğun bakım."),
        PremiumQuestion("Myastenik vs Kolinerjik Kriz", "Myastenik kriz ile kolinerjik kriz ayırıcı tanısı, muskarinik bulgular ve acil solunum yönetimi."),
        PremiumQuestion("Musküler Distrofi", "Musküler distrofi (Duchenne/Becker) hastasında anestezik duyarlılığı, süksinilkolin hiperkalemi riski ve TIVA."),
        PremiumQuestion("Parkinson Hastalığı", "Parkinson hastasında perioperatif dopaminerjik tedavi, açlık yönetimi ve kaçınılması gereken antiemetikler."),
        PremiumQuestion("Epilepsi Hastası", "Epilepsi hastasında perioperatif antiepileptik ilaç yönetimi, nöbet eşiğini düşüren faktörler ve nöbet tedavisi."),
        PremiumQuestion("Demans ve Deliryum", "Demans hastasında postoperatif deliryum riskini azaltma yöntemleri ve kaçınılması gereken ilaçlar."),
        PremiumQuestion("Yenidoğan Anestezisi", "Yenidoğanda anestezi riskleri, hipotermi/hipoglisemi yönetimi, postop apne ve bradikardi.")
    ),
    "Airway & Tech AI" to listOf(
        PremiumQuestion("Zor Ekstübasyon Planı", "Zor havayolu hastasında güvenli ekstübasyon planı, airway exchange kateteri ve reentübasyon hazırlığı."),
        PremiumQuestion("Ekstübasyon Sonrası Stridor", "Ekstübasyon sonrası stridor yönetimi, adrenalin/steroid nebülizasyonu ve acil reentübasyon."),
        PremiumQuestion("Trakeostomi Acili", "Trakeostomili hastada tüp tıkanması veya yerinden çıkması durumunda acil havayolu yönetimi ve oksijenasyon."),
        PremiumQuestion("Larenjektomili Hasta", "Larenjektomili hastada acil oksijenasyon ve ventilasyonun stoma üzerinden yapılması kuralları."),
        PremiumQuestion("Hava Yolu Yangını", "Ameliyathanede endotrakeal tüp / hava yolu yangını geliştiğinde acil söndürme ve kurtarma adımları."),
        PremiumQuestion("MRI Anestezisi", "MRI ortamında anestezi yönetimi, ferromanyetik riskler ve acil hasta tahliye planı."),
        PremiumQuestion("EKT Anestezisi", "Elektrokonvülsif tedavide (EKT) anestezi yönetimi, süksinilkolin kullanımı ve otonomik yanıtlar.")
    ),
    "Special Surgery AI" to listOf(
        PremiumQuestion("Gebede Non-Obstetrik Cera.", "Gebede non-obstetrik cerrahide maternal/fetal güvenlik, sol uterin deplasman ve teratojenite."),
        PremiumQuestion("Okülokardiyak Refleks", "Göz cerrahisinde okülokardiyak refleks (okülokardiyak aritmi) geliştiğinde cerrahi ve medikal acil eylem."),
        PremiumQuestion("Tonsillektomi Kanaması", "Tonsillektomi sonrası kanamada anestezi yaklaşımı, tok kabul edilen hasta, RSI ve zor entübasyon."),
        PremiumQuestion("Robotik / Trendelenburg", "Robotik cerrahide derin Trendelenburg ve pnömoperitoneum solunumsal/hemodinamik etkileri ve yüz ödemi."),
        PremiumQuestion("TURBT / Obturator Refleks", "TURBT ameliyatında obturator sinir uyarısı, bacak adduksiyonu, mesane perforasyon riski ve önlemler."),
        PremiumQuestion("Mesane Perforasyonu", "Sistoskopi veya transüretral rezeksiyon (TUR) sırasında şüpheli mesane perforasyonu belirtileri ve anestezi yönetimi.")
    ),
    "Transplant & Immune AI" to listOf(
        PremiumQuestion("Karaciğer Transplantasyonu", "Karaciğer transplantasyonu anestezi yönetimi, reperfüzyon sendromu, major kanama ve ROTEM."),
        PremiumQuestion("Renal Transplantasyon", "Böbrek naklinde greft perfüzyonunu koruma hedefleri, hemodinami stabilizasyonu ve idrar takibi."),
        PremiumQuestion("İmmünsüprese Hasta", "İmmünsüprese hastada perioperatif enfeksiyon kontrolü, aseptik teknik ve steroid stres dozu."),
        PremiumQuestion("Kemoterapi Almış Hasta", "Kemoterapi sonrası organ toksisitesi (bleomisin akciğer toksisitesi, antrasiklinler ve EKO)."),
        PremiumQuestion("Radyoterapi Almış Hasta", "Baş-boyun radyoterapisi sonrası fibrozis, ağız açıklığı kısıtlılığı ve beklenen zor havayolu planı."),
        PremiumQuestion("Nakilli Hastada Cerrahi", "Organ nakli alıcısında non-transplant cerrahi yaklaşımı ve immünsüpresif ilaçların sürdürülmesi.")
    ),
    "Toxicology & Electrolyte AI" to listOf(
        PremiumQuestion("Beta Bloker Toksisitesi", "Beta bloker zehirlenmesinde glukagon infüzyonu, yüksek doz insülin (HDI) tedavisi ve pacing."),
        PremiumQuestion("Kalsiyum Kanal Bloker Tok.", "Kalsiyum kanal bloker zehirlenmesinde IV kalsiyum, norepinefrin, HDI ve lipid emülsiyon kurtarma."),
        PremiumQuestion("Lipid Emülsiyon Kurtarma", "Toksikolojide LAST dışı lipofilik ilaç zehirlenmelerinde %20 lipid emülsiyonu kurtarma tedavisi."),
        PremiumQuestion("MTP ve Hipokalsemi", "Masif transfüzyon sırasında sitrat toksisitesi, iyonize kalsiyum takibi ve kalsiyum klorür/glukonat dozu."),
        PremiumQuestion("Torsades de Pointes", "Polimorfik ventriküler taşikardi (torsades), QT aralığı uzaması ve IV Magnezyum Sülfat kurtarma tedavisi.")
    ),
    "Critical Care & Hematology AI" to listOf(
        PremiumQuestion("MH Güvenli Anestezi", "Malign hipertermi (MH) duyarlı hastada güvenli/güvensiz anestezikler, TIVA ve anestezi makinesi hazırlığı."),
        PremiumQuestion("Lateks Alerjisi Yönetimi", "Lateks alerjisi olan hastada lateks içermeyen ameliyathane hazırlığı, risk grupları ve anafilaksi."),
        PremiumQuestion("Orak Hücre Hastalığı", "Orak hücre hastasında sickling tetikleyicilerinden (soğuk, dehidratasyon, hipoksi) kaçınma ve ağrı kontrolü."),
        PremiumQuestion("Porfiri Güvenli Anestezi", "Porfiride atak tetikleyen anesteziklerden (barbitüratlar, etomidat) kaçınma ve güvenli ilaç seçimi."),
        PremiumQuestion("Methemoglobinemi Tedavi", "Methemoglobinemi tanısı (SpO2 %85 kilidi, ko-oksimetri), prilokain/benzokain ve Metilen Mavisi dozu."),
        PremiumQuestion("Şok Tipleri Ayırıcı Tanı", "Hipovolemik, distributif, kardiyojenik ve obstrüktif (PE, tamponad, pnömotoraks) şok hemodinamik ayırıcı tanısı."),
        PremiumQuestion("Septik Şok Vazopressör", "Septik şokta norepinefrin titrasyonu, MAP >65 mmHg hedefi, vazopressin eklenmesi ve laktat takibi."),
        PremiumQuestion("ECMO Hastasında Anestezi", "ECMO altındaki hastada anestezi, VV/VA-ECMO fizyolojisi, antikoagülasyon ve ultra-koruyucu ventilasyon."),
        PremiumQuestion("Pediatrik Acil Dozları", "Çocuk resüsitasyonunda (PALS) kiloya göre adrenalin/atropin dozları, sıvı bolusu ve tüp hesabı.")
    ),
    "Dental & ENT Special AI" to listOf(
        PremiumQuestion("Endokardit Profilaksisi", "Endokardit profilaksisi kimlere gerekir? En yüksek riskli kardiyak durumlar ve dental prosedürler."),
        PremiumQuestion("Antibiyotik Zamanlaması", "Cerrahi antibiyotik profilaksisi zamanlaması, tekrar doz kriterleri ve süre limitleri."),
        PremiumQuestion("Dental Sedasyon Kontrol", "Dental sedasyonda kısıtlı havayolu erişimi, aspirasyon önlemleri ve lokal anestezik takibi."),
        PremiumQuestion("ENT Cerrahi Havayolu", "Paylaşılan havayolunda entübasyon güvenliği, koter/lazer yangın önlemleri ve ekstübasyon.")
    ),
    "Cardiology & Neuro Special AI" to listOf(
        PremiumQuestion("Awake Craniotomy", "Uyanık kraniyotomide hasta kooperasyonu, scalp block ve sedasyon titrasyonu."),
        PremiumQuestion("Nöromonitörizasyon", "MEP/SSEP takibinde anestezik seçimi, kas gevşetici kısıtlamaları ve fizyolojik hedefler."),
        PremiumQuestion("EP Ablasyon Anestezisi", "Elektrofizyolojik ablasyonda aritmi indüksiyonu, sempatik tonus ve hemodinamik güvenlik."),
        PremiumQuestion("Kardiyoversiyon Sedasyon", "Elektriksel kardiyoversiyonda hızlı etkili sedasyon seçimi, solunum depresyonu ve hazırlık.")
    ),
    "Vascular Access & Neuraxial AI" to listOf(
        PremiumQuestion("Epidural Test Dozu", "Epidural kateterin intravasküler/intratekal yerleşimini belirleme ve test dozu standartları."),
        PremiumQuestion("Epidural Çalışmıyor", "Epidural kateter ağrı kontrol yetersizliği, patchy blok giderme ve migrasyon takibi."),
        PremiumQuestion("İntratekal Opioid İzlem", "İntratekal morfin sonrası geç solunum depresyonu takibi, kaşıntı/bulantı yönetimi ve nalokson."),
        PremiumQuestion("Zor Damar Yolu Yönetim", "Zor periferik IV erişimde ultrason rehberliği, intraosseöz acil hat ve inhalasyon indüksiyonu."),
        PremiumQuestion("CVC Kateterizasyon", "Santral venöz kateterizasyonda ultrason kullanımı, steril bariyer ve kılavuz tel doğrulama."),
        PremiumQuestion("CVC Arter Yaralanması", "Santral kateter takarken arter ponksiyonu veya kateter penetrasyonunda acil vasküler yönetim."),
        PremiumQuestion("CVC Pnömotoraks", "Santral kateter sonrası pnömotoraks şüphesi, akciğer USG ve acil iğne dekompresyonu."),
        PremiumQuestion("Arter Hattı Takibi", "Arter kanülasyonu komplikasyonları (iskemi, spazm, hematom) ve distal perfüzyon izlemi.")
    ),
    "NORA & Respiratory AI" to listOf(
        PremiumQuestion("Morbid Obez Ekstübasyon", "Morbid obez hastada uyanık ekstübasyon kriterleri, ramp pozisyonu ve TOF >=0.90 takibi."),
        PremiumQuestion("Postop Yoğun Bakım", "Postoperatif yoğun bakım kabul kriterleri, fizyolojik instabilite ve organ desteği kararları."),
        PremiumQuestion("Venöz Hava Embolisi", "Venöz hava embolisi belirtileri (ani ETCO2 düşüşü), Trendelenburg pozisyonu ve acil yönetim."),
        PremiumQuestion("HFNC Solunum Destek", "Perioperatif HFNC (yüksek akımlı oksijen) kullanımı, apneik oksijenasyon ve obez hasta takibi."),
        PremiumQuestion("NIV & CPAP Kullanımı", "Postoperatif NIV/CPAP endikasyonları, kontrendikasyonlar ve entübasyona geçiş kararları."),
        PremiumQuestion("NORA Güvenlik Protokolü", "Ameliyathane dışı anestezide (NORA/MRI) kısıtlı alan, transport ve acil havayolu hazırlığı."),
        PremiumQuestion("Sedasyon Kapnografi", "Sedasyon sırasında kapnografik monitörizasyon, hipoventilasyon tespiti ve SpO2 gecikmesi."),
        PremiumQuestion("Beklenmeyen Derinleşme", "Sedasyon derinleşmesi ve solunum depresyonunda acil eylem, çene itme, nalokson/flumazenil."),
        PremiumQuestion("Contrast Reaksiyonu", "Radyolojide kontrast madde reaksiyonları, bronkospazm/hipotansiyon ve acil adrenalin.")
    ),
    "Hematoloji & Kan Yönetimi AI" to listOf(
        PremiumQuestion("Koroner Stent Zamanlaması", "Koroner stent sonrası elektif cerrahi zamanlaması nasıl olmalı?"),
        PremiumQuestion("Dual Antiplatelet (DAPT) Yönetimi", "Dual antiplatelet tedavi (DAPT) alan hastada perioperatif süreç nasıl yönetilmeli?"),
        PremiumQuestion("Mekanik Kapak Köprüleme", "Mekanik kalp kapağı olan hastada antikoagülan köprüleme (bridging) nasıl olmalı?"),
        PremiumQuestion("DOAC Acil Cerrahi", "DOAC kullanan hastada acil cerrahide antikoagülan etkiyi geri döndürme (reversal) planı nedir?"),
        PremiumQuestion("Obstetrik Trombosit Sınırları", "Obstetrik trombositopenide nöroaksiyel anestezi (spinal/epidural) güvenlik sınırları nelerdir?"),
        PremiumQuestion("HELLP Sendromu Yaklaşımı", "HELLP sendromlu gebede anestezi planı ve laboratuvar takibi nasıl olmalı?"),
        PremiumQuestion("Amniyotik Sıvı Embolisi (AFE)", "Amniyotik sıvı embolisi (AFE) şüphesinde acil yönetim ve resüsitasyon adımları nedir?"),
        PremiumQuestion("Jehovah's Witness Kan Reddi", "Jehovah's Witness hastasında kan transfüzyonu reddi durumunda kan yönetimi nasıl planlanır?"),
        PremiumQuestion("Preoperatif Anemi Yönetimi", "Preoperatif anemi optimizasyonu, demir ve eritropoietin tedavisi nasıl uygulanır?"),
        PremiumQuestion("Trombositopeni Cerrahi Sınır", "Trombositopenisi olan hastada cerrahi ve rejyonal anestezi için güvenlik sınırları nelerdir?")
    ),
    "Alerji & Solunumsal Sedasyon AI" to listOf(
        PremiumQuestion("Lokal Anestezik Alerjisi", "Lokal anestezik alerjisi öyküsü olan hastada yaklaşım ve alternatifler nelerdir?"),
        PremiumQuestion("Antibiyotik Alerjisi & Sefazolin", "Penisilin/sefalosporin alerjisi olan hastada sefazolin profilaksisi güvenli midir?"),
        PremiumQuestion("Perioperatif Ürtiker & Döküntü", "Perioperatif ürtiker veya döküntü durumunda anafilaksi ayrımı ve acil eylem nedir?"),
        PremiumQuestion("Bronkoskopi Sedasyonu", "Fleksibl bronkoskopide sedasyon güvenliği, topikal lokal anestezik sınırları nelerdir?"),
        PremiumQuestion("Endoskopi Sedasyonu", "Gastrointestinal endoskopi sedasyonunda aspirasyon önlemleri ve havayolu güvenliği nasıl sağlanır?"),
        PremiumQuestion("ERCP Prone Sedasyon", "Prone (yüzüstü) pozisyonda ERCP sedasyonunda havayolu ve solunum güvenliği nasıl yönetilir?")
    ),
    "Yüksek Risk Havayolu & Vasküler AI" to listOf(
        PremiumQuestion("EBUS & Rijit Bronkoskopi", "EBUS veya rijit bronkoskopide paylaşılan havayolu ve ventilasyon yönetimi nasıl olmalı?"),
        PremiumQuestion("Trakeal Stentli Hasta", "Hava yolu stenti (trakeal stent) olan hastada anestezi ve entübasyon güvenliği nasıl sağlanır?"),
        PremiumQuestion("Mediastinal Kitle Kollapsı", "Mediastinal kitlesi olan hastada indüksiyon sonrası havayolu kollapsı riski nasıl yönetilir?"),
        PremiumQuestion("Antikoagüle Periferik Blok", "Antikoagüle hastada derin veya komprese edilemeyen periferik sinir blokları güvenli midir?")
    ),
    "Pediatrik & Neonatal Özel AI" to listOf(
        PremiumQuestion("Pediatrik Pilor Stenozu", "Hipertrofik pilor stenozu olan bebekte anestezi yönetimi, aspirasyon önlemleri ve resüsitasyon kriterleri nedir?"),
        PremiumQuestion("Neonatal Resüsitasyon 2025", "Yenidoğan resüsitasyonunda 2025 AHA/AAP algoritması adımları, ventilasyon ve düzeltici adımlar nelerdir?"),
        PremiumQuestion("Yenidoğan Hipoglisemi", "Yenidoğanda hipoglisemi sınırları, belirtileri ve enteral/parenteral glukoz infüzyon tedavisi nasıl yönetilir?"),
        PremiumQuestion("Yenidoğan Hipotermi", "Yenidoğanda intraoperatif hipotermi önlenmesi, aktif ısıtma teknikleri ve fizyolojik etkileri nelerdir?"),
        PremiumQuestion("Konjenital Diyafram Hernisi", "Konjenital diyafram hernisi (CDH) olan yenidoğanda havayolu yönetimi ve koruyucu ventilasyon hedefleri nelerdir?"),
        PremiumQuestion("Trakeoözofageal Fistül", "Pediatrik trakeoözofageal fistül (TEF) cerrahisinde indüksiyon, ventilasyon ve tüp pozisyonlama nasıl olmalı?"),
        PremiumQuestion("Down Sendromlu Hasta", "Down sendromlu hastada anestezi hazırlığı, atlantoaksiyel instabilite (AAI) ve subglottik stenoz önlemleri nelerdir?"),
        PremiumQuestion("Serebral Palsi Anestezisi", "Serebral palsi (SP) olan hastada kas gevşetici duyarlılığı, baklofen yoksunluk önleme ve pozisyonlama nasıldır?"),
        PremiumQuestion("Otizm Spektrum Anestezisi", "Otizm spektrum hastasında preoperatif hazırlık, duyusal uyaranları azaltma ve sakin premedikasyon planı nasıl olmalı?")
    ),
    "Ateş, Toksikoloji & Havayolu Acil AI" to listOf(
        PremiumQuestion("Malign Hipertermi Ayırıcı", "Malign hipertermi krizinde ayırıcı tanı basamakları, tiroid fırtınası ve NMS ayrımı nasıl yapılır?"),
        PremiumQuestion("Perioperatif Ateş", "Perioperatif dönemde gelişen ateşin (hipertermi) nedenleri, ayırıcı tanısı ve acil eylem planı nedir?"),
        PremiumQuestion("Duman İnhalasyonu", "Duman inhalasyonu hasarı olan hastada erken entübasyon kriterleri ve üst havayolu ödemi yönetimi nasıldır?"),
        PremiumQuestion("Karbonmonoksit Zehirlenmesi", "Karbonmonoksit (CO) zehirlenmesinde tanı (COHb), %100 O2 ve hiperbarik oksijen (HBO) endikasyonları nelerdir?"),
        PremiumQuestion("Siyanür Zehirlenmesi", "Siyanür zehirlenmesinde laktik asidoz takibi ve spesifik antidot (Hidroksokobalamin) dozu nedir?")
    ),
    "Rejyonal Güvenlik & Blok Komplikasyonları AI" to listOf(
        PremiumQuestion("Lokal Anestezik Maksimum", "Lokal anesteziklerin (lidokain, bupivakain) adrenalinli/adrenalsiz maksimum güvenli doz limitleri nelerdir?"),
        PremiumQuestion("Blok Sonrası Sinir Hasarı", "Rejyonal anestezi/blok sonrası gelişen sinir hasarı şüphesinde tanı, takip ve elektrofizyolojik inceleme süreci nedir?"),
        PremiumQuestion("Kompartman Sendromu", "Akut kompartman sendromu belirtileri, rejyonal anestezi maskeleme riski ve acil fasyotomi kriterleri nedir?"),
        PremiumQuestion("Sık Blok Komplikasyonları", "Sık uygulanan blokların (interkalaner, supraklavikular) komplikasyonları (Horner, LAST, pnömotoraks) nasıl yönetilir?"),
        PremiumQuestion("Kauda Ekuina Sendromu", "Spinal/epidural anestezi sonrası gelişen kauda ekuina sendromu belirtileri ve acil cerrahi dekompresyon zamanlaması nedir?")
    )
)

val premiumCategoriesEn = mapOf(
    "Preop AI" to listOf(
        PremiumQuestion("Evaluate ASA Class", "What is the ASA class for this patient and are they suitable for elective surgery?"),
        PremiumQuestion("Cardiac Risk (RCRI)", "What is the cardiac risk for this patient? How do I evaluate the RCRI score and functional capacity?"),
        PremiumQuestion("Day Surgery Suitability", "Is this patient suitable for day surgery? What are the OSAS and ASA limitations?"),
        PremiumQuestion("Fasting / NPO Status", "Is this patient suitable for surgery in terms of fasting (NPO) times? Evaluate according to the 2-4-6-8 rule."),
        PremiumQuestion("Delay Criteria", "What are the hypertension or other laboratory criteria requiring surgical delay in this patient?")
    ),
    "Airway AI" to listOf(
        PremiumQuestion("Difficult Airway Plan", "Is there a difficult airway risk in this patient? Create a step-by-step awake fiberoptic or GA airway plan."),
        PremiumQuestion("Obese / OSAS Airway", "How should airway management and extubation prep be handled in an obese or OSAS patient?"),
        PremiumQuestion("Intubation Trials Limit", "How many intubation attempts should be made in a difficult airway? What are the risks of repeated attempts?"),
        PremiumQuestion("ARDS Ventilation Plan", "How should intraoperative protective ventilation (LTVT) parameters be set in an ARDS or COPD patient?"),
        PremiumQuestion("ETCO2 / Capnogram Analysis", "What is the differential diagnosis if a curare cleft, bronchospasm, or sudden drop is seen in the capnography wave under anesthesia?")
    ),
    "Emergency AI" to listOf(
        PremiumQuestion("Desaturation (Hypoxemia)", "Saturation is falling, peak airway pressure is high! Start the step-by-step hypoxemia algorithm."),
        PremiumQuestion("Hypotension Management", "Blood pressure dropped! Start the management algorithm for hypotension due to induction vasoplegia, bleeding, or hypovolemia."),
        PremiumQuestion("Bradycardia Management", "Heart rate dropped, hemodynamics unstable! Start the emergency bradycardia rescue and atropine/pacing algorithm."),
        PremiumQuestion("Tachycardia Management", "Heart rate is rising! Start the stable or unstable tachycardia and SVT/VT rescue algorithm."),
        PremiumQuestion("Anaphylaxis Suspicion", "Anaphylaxis suspected in the OR! Show adrenaline dose, aggressive fluid therapy, and crisis protocol."),
        PremiumQuestion("Malignant Hyperthermia (MH)", "Malignant hyperthermia crisis suspected! Stop volatile agents, initiate active cooling, and calculate Dantrolene vial dose."),
        PremiumQuestion("LAST (Local Toxicity)", "Local anesthetic systemic toxicity (LAST) developed! Manage seizure/arrhythmia and calculate 20% Lipid Emulsion bolus/infusion dose."),
        PremiumQuestion("CICO (Airway Emergency)", "Cannot Intubate Cannot Oxygenate (CICO) declared! Show the emergency scalpel-bougie cricothyrotomy algorithm."),
        PremiumQuestion("Cardiac Arrest (CPR)", "Cardiac arrest occurred! Start emergency defibrillating shock, chest compressions, and ACLS (Adrenaline/Amiodarone) resuscitation steps."),
        PremiumQuestion("Pediatric/OB Arrest", "Show the cardiac arrest and left uterine displacement / PALS resuscitation algorithm in an obstetric (pregnant) or pediatric (child) patient.")
    ),
    "Dose AI" to listOf(
        PremiumQuestion("Induction Agent Selection", "What is the safest induction agent (Propofol vs. Etomidate vs. Ketamine) in a severely hypotensive, septic, or cardiac patient?"),
        PremiumQuestion("Muscle Relaxant & Reversal", "What is the dosage, contraindications, and reversal guide for Rocuronium and Succinylcholine based on TOF monitoring?"),
        PremiumQuestion("Sugammadex Dose Calculation", "How are Sugammadex and Neostigmine doses calculated based on body weight in deep/shallow neuromuscular block?"),
        PremiumQuestion("Norepinephrine Syringe", "How are emergency norepinephrine and epinephrine infusion syringes prepared in mg/mL?"),
        PremiumQuestion("Pediatric Dose Calculations", "Calculate pediatric induction drugs, CPR drugs, ETT tube size, and fluid maintenance rate by weight.")
    ),
    "Regional AI" to listOf(
        PremiumQuestion("Block Criteria & Safety", "What are the patient safety screening, platelet thresholds, and contraindications before a neuraxial block (spinal/epidural)?"),
        PremiumQuestion("Anticoagulant Discontinuation", "What are the discontinuation and restart times for anticoagulant (Warfarin, DOAC, Aspirin, LMWH) drugs according to the ASRA 2025 guidelines?"),
        PremiumQuestion("Max Local Anesthetic", "Calculate toxic thresholds and maximum mg doses for lidocaine and bupivacaine with and without adrenaline."),
        PremiumQuestion("Cesarean Hypotension", "How should post-spinal hypotension prevention, ephedrine/phenylephrine selection be managed in cesarean section?"),
        PremiumQuestion("Epidural Hematom Suspicion", "Motor loss in legs after regional anesthesia! What are the epidural hematoma suspicion, lumbar MRI, and emergency decompression times?")
    ),
    "PACU AI" to listOf(
        PremiumQuestion("Aldrete & Discharge", "What are the PACU discharge criteria? How is the Aldrete score calculated?"),
        PremiumQuestion("PONV Risk & Prophylaxis", "What are the postoperative nausea and vomiting (PONV) risk classification (Apfel score) and multimodal Ondansetron/Dexamethasone prophylaxis?"),
        PremiumQuestion("Multimodal Pain Plan", "Create a multimodal analgesia plan containing Paracetamol, NSAIDs, and opioids for postoperative pain management."),
        PremiumQuestion("PACU Respiratory Depression", "Respiratory depression or residual block suspected in the recovery room! What is the Sugammadex or Naloxone rescue plan?"),
        PremiumQuestion("Shivering / Delirium Treatment", "What are the pharmacological treatment approaches in postoperative shivering or agitation/delirium?")
    ),
    "ICU AI" to listOf(
        PremiumQuestion("Sepsis 1-Hour Bundle", "What are the Surviving Sepsis 1-Hour Bundle and Norepinephrine titration plans for a patient with suspected sepsis or septic shock?"),
        PremiumQuestion("Shock Types Diagnosis", "What are the hemodynamic parameters and differential diagnosis in cardiogenic, hypovolemic, and distributive (septic/anaphylactic) shock?"),
        PremiumQuestion("RASS Sedation Monitoring", "How are mechanical ventilation sedation depth (RASS score) and delirium (CAM-ICU) screening performed in the ICU?"),
        PremiumQuestion("ICU Extubation Criteria", "What are the mechanical ventilator weaning prep, cuff leak test, and extubation criteria for ICU patients?"),
        PremiumQuestion("Oliguria Step-by-Step Action", "What is the hypovolemia/renal perfusion evaluation and action plan in case of low urine output (Oliguria) in the ICU or intraoperatively?")
    ),
    "Obstetric & Pediatric AI" to listOf(
        PremiumQuestion("Preeclampsia Management", "How should anesthesia be managed in preeclampsia and HELLP syndrome?"),
        PremiumQuestion("Eclamptic Seizure Control", "How is an eclamptic seizure managed with magnesium sulfate and how is toxicity treated?"),
        PremiumQuestion("Postpartum Hemorrhage", "What is the anesthetic management, uterotonics use, and MTP protocol for postpartum hemorrhage?"),
        PremiumQuestion("Pediatric Bradycardia", "Anesthetized pediatric bradycardia occurred! Show hypoxia correction and atropine/epinephrine doses."),
        PremiumQuestion("Pediatric URI Decision", "What are the decision criteria and safety guidelines for anesthesia in a child with upper respiratory tract infection?"),
        PremiumQuestion("Pediatric Laryngospasm", "What is the emergency CPAP, propofol, and succinylcholine/atropine plan for pediatric laryngospasm?")
    ),
    "Special Tech AI" to listOf(
        PremiumQuestion("Pacemaker / ICD Prep", "What are the preoperative settings and prep steps for patients with a pacemaker or ICD?"),
        PremiumQuestion("Cautery & CIED Interaction", "How does electrocautery affect a pacemaker/ICD? How do we minimize electromagnetic interference?"),
        PremiumQuestion("One-Lung Ventilation", "What are the CPAP/PEEP rescue steps for hypoxemia during one-lung ventilation (OLV)?"),
        PremiumQuestion("Laparoscopy Hypercapnia", "How is rising ETCO2 during laparoscopy managed? Explain CO2 embolism and pneumothorax differentials."),
        PremiumQuestion("TURP Syndrome & Na", "What are the signs of TURP syndrome? How is severe symptomatic hyponatremia corrected with hypertonic saline?"),
        PremiumQuestion("Machine / Oxygen Failure", "What is the emergency manual bag ventilation plan in case of an anesthesia machine or oxygen supply failure?")
    ),
    "Special Patient AI" to listOf(
        PremiumQuestion("OSA Anesthesia Plan", "How should anesthesia be planned in OSA patients? Explain difficult airway, postop obstruction, and CPAP management."),
        PremiumQuestion("OSA Ambulatory Surgery", "Is an OSA patient suitable for ambulatory surgery? What are the postponement and home discharge criteria?"),
        PremiumQuestion("Obese Patient Induction", "Explain induction positioning, preoxygenation, drug dosing, and protective ventilation targets in obese patients."),
        PremiumQuestion("Bariatric Anesthesia", "Explain bariatric anesthesia management, ventilation modes, rhabdomyolysis, and DVT prophylaxis."),
        PremiumQuestion("Ambulatory Discharge", "What are the ambulatory surgery discharge criteria? Explain Aldrete/PADSS and POUR risk."),
        PremiumQuestion("Chronic Opioid Use", "Explain postoperative pain management, tolerance, and opioid PCA dosing in chronic opioid users."),
        PremiumQuestion("Alcohol/Benzo Dependence", "Explain perioperative Wernicke encephalopathy prevention and withdrawal management in alcohol/benzodiazepine dependence."),
        PremiumQuestion("Renal Failure Anesthesia", "Explain anesthesia in renal failure, hyperkalemia treatment, cisatracurium dosing, and AV fistula protection."),
        PremiumQuestion("Dialysis Timing & Heparin", "Explain timing of surgery in dialysis patients, dialysis-induced heparinization, and emergency dialysis K limits."),
        PremiumQuestion("Liver Failure Anesthesia", "Explain liver failure anesthetic management, Child-Pugh/MELD score, ascites, and coagulopathy."),
        PremiumQuestion("Cirrhosis Regional Safety", "What are the safety limits for spinal or epidural anesthesia in liver cirrhosis? Explain platelet/INR limits and epidural hematoma.")
    ),
    "Endocrine & Neuro AI" to listOf(
        PremiumQuestion("Steroid Stress Dose", "Explain stress-dose steroid replacement in chronic steroid users, hydrocortisone doses, and Addisonian crisis."),
        PremiumQuestion("Adrenal Crisis", "What are the signs of adrenal crisis? Explain IV hydrocortisone rescue and saline fluid resuscitation."),
        PremiumQuestion("Hyperthyroidism Prep", "Explain perioperative management of hyperthyroidism, euthyroid delay criteria, and sympathomimetic interactions."),
        PremiumQuestion("Thyroid Storm", "What are the signs of thyroid storm? Explain active cooling, esmolol, and PTU emergency treatment."),
        PremiumQuestion("Hypothyroidism", "What are the surgery postponement criteria in hypothyroidism? Explain anesthetic sensitivity and myxedema coma."),
        PremiumQuestion("Pheochromocytoma", "Explain anesthetic management in pheochromocytoma, Roizen criteria, and post-resection shock."),
        PremiumQuestion("Elevated ICP (Raised ICP)", "Explain cerebral perfusion pressure (CPP) maintenance, normocapnia, and mannitol therapy in elevated ICP."),
        PremiumQuestion("Traumatic Brain Injury", "Explain secondary injury prevention, MAP targets, and intubation under Manual In-line Stabilization (MILS) in TBI.")
    ),
    "Trauma & Position AI" to listOf(
        PremiumQuestion("Prone Position Safety", "Explain airway securing, eye pressure prevention, and padding rules in prone positioning."),
        PremiumQuestion("Beach-Chair Position", "Explain vertical heart-to-brain hydrostatic pressure gradient and cerebral perfusion in beach-chair position."),
        PremiumQuestion("Lithotomy Position", "Explain lithotomy position nerve injuries (peroneal/femoral), compartment syndrome, and drop hypotension."),
        PremiumQuestion("Burn Fluid / Parkland", "Explain initial 24h fluid calculation using the Parkland formula and target urine output in burn injury."),
        PremiumQuestion("Burn & Succinylcholine", "Explain succinylcholine-induced hyperkalemia risk in burn patients, the 24-hour safe rule, and EKG changes."),
        PremiumQuestion("Trauma Induction", "Explain rapid sequence induction (RSI), drug dose modifications in hemorrhagic shock, and cervical spine protection."),
        PremiumQuestion("Shock Index (HR/SBP)", "How is Shock Index interpreted? Explain occult hemorrhage screening and blood transfusion thresholds.")
    ),
    "Perioperative Meds AI" to listOf(
        PremiumQuestion("ACE / ARB Morning", "Explain morning-of-surgery withholding of ACE inhibitors and ARBs, vasoplegic shock, and vasopressin rescue."),
        PremiumQuestion("Beta Blocker Management", "Explain perioperative continuation of chronic beta-blockers and the risks of acute withdrawal."),
        PremiumQuestion("Antidepressant Management", "Explain serotonin syndrome risk in SSRIs/SNRIs, meperidine interaction, and ephedrine contraindications in MAOIs."),
        PremiumQuestion("Herbal Supplement Safety", "Explain bleeding and sedation risks of herbal supplements like Ginkgo, Garlic, Ginseng, and St. John's Wort.")
    ),
    "Neurology & Neonatal AI" to listOf(
        PremiumQuestion("Myasthenia Gravis", "Explain myasthenia gravis anesthetic plan, muscle relaxant sensitivity, TOF monitoring, and postop ICU care."),
        PremiumQuestion("Myasthenic vs Cholinergic", "Explain differentiation between myasthenic and cholinergic crisis, muscarinic signs, and emergency airway care."),
        PremiumQuestion("Muscular Dystrophy", "Explain anesthetic sensitivity, succinylcholine hyperkalemia risk, and TIVA in muscular dystrophy (Duchenne/Becker)."),
        PremiumQuestion("Parkinson's Disease", "Explain perioperative dopaminergic therapy, fasting management, and contraindicated antiemetics in Parkinson's."),
        PremiumQuestion("Epilepsy Patient", "Explain perioperative antiepileptic drug management, factors lowering seizure threshold, and seizure treatment."),
        PremiumQuestion("Dementia & Delirium", "Explain methods to reduce postoperative delirium risk and drugs to avoid in dementia patients."),
        PremiumQuestion("Neonatal Anesthesia", "Explain neonatal anesthesia risks, hypothermia/hypoglycemia management, postoperative apnea, and bradycardia.")
    ),
    "Airway & Tech AI" to listOf(
        PremiumQuestion("Difficult Extubation Plan", "Explain safe extubation plan, airway exchange catheter use, and reintubation readiness in difficult airway."),
        PremiumQuestion("Post-Extubation Stridor", "Explain post-extubation stridor management, epinephrine/steroid nebulization, and urgent reintubation."),
        PremiumQuestion("Tracheostomy Emergency", "Explain emergency airway management and oxygenation in case of tracheostomy tube blockage or displacement."),
        PremiumQuestion("Laryngectomy Patient", "Explain emergency oxygenation and ventilation rules through the neck stoma in laryngectomy patients."),
        PremiumQuestion("Airway Fire Response", "Explain emergency extinguishing and rescue steps in case of an endotracheal tube / airway fire in the OR."),
        PremiumQuestion("MRI Anesthesia Safety", "Explain anesthetic management in the MRI suite, ferromagnetic hazards, and emergency patient extraction."),
        PremiumQuestion("ECT Anesthesia", "Explain ECT anesthetic management, succinylcholine dosing, and hemodynamic/autonomic responses.")
    ),
    "Special Surgery AI" to listOf(
        PremiumQuestion("Non-Obstetric Pregnancy", "Explain maternal/fetal safety, left uterine displacement, and teratogenicity during non-obstetric surgery in pregnancy."),
        PremiumQuestion("Oculocardiac Reflex", "Explain emergency surgical and medical intervention in case of oculocardiac reflex (bradycardia/asystole) in eye surgery."),
        PremiumQuestion("Tonsillectomy Hemorrhage", "Explain anesthetic management, full-stomach emergency prep, RSI, and difficult airway rescue in bleeding post-tonsillectomy."),
        PremiumQuestion("Robotic steep Trendelenburg", "Explain robotic steep Trendelenburg and pneumoperitoneum respiratory/hemodynamic consequences and facial edema."),
        PremiumQuestion("TURBT Obturator Reflex", "Explain obturator nerve stimulation, sudden thigh adduction, bladder perforation risk, and prevention during TURBT."),
        PremiumQuestion("Bladder Perforation", "Explain signs of suspected bladder perforation and anesthetic management during cystoscopy or transurethral resection (TUR).")
    ),
    "Transplant & Immune AI" to listOf(
        PremiumQuestion("Liver Transplantation", "Explain liver transplantation anesthetic management, post-reperfusion syndrome, major bleeding, and ROTEM."),
        PremiumQuestion("Renal Transplantation", "Explain renal transplantation graft perfusion preservation, hemodynamic stability, and urine output monitoring."),
        PremiumQuestion("Immunosuppressed Patient", "Explain perioperative infection control, aseptic technique, and stress-dose steroid replacement."),
        PremiumQuestion("Post-Chemotherapy Patient", "Explain post-chemotherapy organ toxicities (bleomycin pulmonary toxicity, anthracycline cardiomyopathy, and echo)."),
        PremiumQuestion("Post-Radiotherapy Airway", "Explain head and neck radiation-induced fibrosis, microstomia, and anticipated difficult airway planning."),
        PremiumQuestion("Recipient Non-Transplant", "Explain perioperative management of transplant recipients undergoing non-transplant surgery and immunosuppression maintenance.")
    ),
    "Toxicology & Electrolyte AI" to listOf(
        PremiumQuestion("Beta Blocker Toxicity", "Explain beta-blocker overdose management, IV glucagon infusion, high-dose insulin (HDI) therapy, and pacing."),
        PremiumQuestion("Calcium Channel Blocker Tok.", "Explain calcium channel blocker overdose, IV calcium, norepinephrine, HDI, and lipid emulsion rescue."),
        PremiumQuestion("Lipid Emulsion Rescue", "Explain emergency intravenous lipid emulsion (ILE) rescue in non-local anesthetic lipophilic overdoses."),
        PremiumQuestion("MTP & Hypocalcemia", "Explain citrate toxicity during massive transfusion, ionized calcium monitoring, and calcium chloride/gluconate replacement."),
        PremiumQuestion("Torsades de Pointes", "Explain polymorphic ventricular tachycardia (torsades), QT interval prolongation, and emergency magnesium sulfate rescue.")
    ),
    "Critical Care & Hematology AI" to listOf(
        PremiumQuestion("MH Safe Anesthesia", "Explain anesthetic safe list, TIVA, and anesthesia machine preparation in malignant hyperthermia susceptible patients."),
        PremiumQuestion("Lateks Allergy Management", "Explain latex-free operating room environment, patient screening, and anaphylaxis rescue protocol."),
        PremiumQuestion("Sickle Cell Disease", "Explain prevention of sickling triggers (hypothermia, dehydration, hypoxemia) and pain crisis management in sickle cell."),
        PremiumQuestion("Porphyria Safe Anesthesia", "Explain porphyrogenic anesthetic triggers avoidance (thiopental, etomidate) and safe drug selection."),
        PremiumQuestion("Methemoglobinemia Treatment", "Explain methemoglobinemia diagnosis (SpO2 85% lock, co-oximetry), prilocaine/benzocaine triggers, and Methylene Blue dose."),
        PremiumQuestion("Shock Types Diagnosis", "Explain hemodynamic differential diagnosis of hypovolemic, distributive, cardiogenic, and obstructive shock."),
        PremiumQuestion("Septik Shock Vasopressor", "Explain norepinephrine titration, MAP >65 mmHg target, vasopressin addition, and serial lactate tracking."),
        PremiumQuestion("ECMO Patient Anesthesia", "Explain anesthesia in patients under ECMO, VV/VA-ECMO physiology, anticoagulation, and ultra-protective ventilation."),
        PremiumQuestion("Pediatric Emergency Dose", "Explain pediatric advanced life support (PALS) weight-based dosing for epinephrine, atropine, fluid bolus, and tube sizing.")
    ),
    "Dental & ENT Special AI" to listOf(
        PremiumQuestion("Endocarditis Prophylaxis", "Determine if endocarditis prophylaxis is indicated; identify highest-risk cardiac lesions and dental procedures."),
        PremiumQuestion("Antibiotic Timing", "Explain optimal timing of surgical antibiotic prophylaxis, intraoperative redosing triggers, and duration limits."),
        PremiumQuestion("Dental Sedation Control", "Manage limited airway access, aspiration precautions, and local anesthetic limits during dental sedation."),
        PremiumQuestion("ENT Shared Airway", "Coordinate safe shared-airway ventilation, electrocautery/laser fire prevention, and smooth extubation.")
    ),
    "Cardiology & Neuro Special AI" to listOf(
        PremiumQuestion("Awake Craniotomy", "Manage patient cooperation, perform effective regional scalp blocks, and titrate conscious sedation."),
        PremiumQuestion("Neuromonitoring", "Select anesthetics, manage neuromuscular blocker restrictions, and maintain physiology during MEP/SSEP."),
        PremiumQuestion("EP Ablation Anesthesia", "Manage sedation depth, preserve sympathetic tone, and monitor hemodynamics during electrophysiology ablation."),
        PremiumQuestion("Cardioversion Sedation", "Provide safe rapid-onset deep sedation, manage respiratory depression, and prepare for shock rescue.")
    ),
    "Vascular Access & Neuraxial AI" to listOf(
        PremiumQuestion("Epidural Test Dose", "Perform and interpret an epidural test dose to detect intravascular or intrathecal catheter migration."),
        PremiumQuestion("Epidural Not Working", "Troubleshoot a failed epidural catheter, address patchy/unilateral blocks, and evaluate migration."),
        PremiumQuestion("Intrathecal Opioid Monitor", "Monitor delayed respiratory depression after intrathecal morphine, manage pruritus, and titrate naloxone."),
        PremiumQuestion("Difficult IV Access", "Manage difficult peripheral access using ultrasound guidance, emergency intraosseous line, or inhalation induction."),
        PremiumQuestion("CVC Catheterization", "Apply ultrasound guidance, maintain maximum sterile barrier, and verify guidewire placement for central lines."),
        PremiumQuestion("CVC Arterial Puncture", "Manage accidental carotid or subclavian artery dilation/cannulation during central line attempts."),
        PremiumQuestion("CVC Pneumothorax", "Diagnose post-CVC pneumothorax with ultrasound/chest X-ray, and perform emergency needle decompression."),
        PremiumQuestion("Arterial Line Safety", "Prevent and treat arterial cannulation complications including thrombosis, distal ischemia, and vasospasm.")
    ),
    "NORA & Respiratory AI" to listOf(
        PremiumQuestion("Morbid Obese Extubation", "Plan safe awake extubation, use ramped positioning, and target quantitative TOF ratio >=0.90 in obese patients."),
        PremiumQuestion("Postop ICU Admission", "Assess post-op intensive care unit admission criteria based on physiologic instability and organ support needs."),
        PremiumQuestion("Venous Air Embolism", "Identify signs of venous air embolism (precipitous ETCO2 drop), apply left lateral/Trendelenburg, and manage shock."),
        PremiumQuestion("HFNC Respiratory Support", "Utilize high-flow nasal cannula for preoxygenation, apneic oxygenation, and high-risk extubation."),
        PremiumQuestion("NIV & CPAP Use", "Select postoperative NIV/CPAP parameters, evaluate contraindications, and manage failed non-invasive support."),
        PremiumQuestion("NORA Safety Protocol", "Implement safety checklists in remote anesthetizing locations (MRI/Angio), transport, and emergency airways."),
        PremiumQuestion("Sedation Capnography", "Use capnography during moderate-to-deep sedation to detect hypoventilation before pulse oximetry lag."),
        PremiumQuestion("Sedation Overdeepening", "Manage accidental deep sedation/apnea with stimulation, airway positioning, and flumazenil/naloxone titration."),
        PremiumQuestion("Contrast Media Reaction", "Recognize radiological contrast hypersensitivity reactions, manage bronchospasm, and titrate epinephrine.")
    ),
    "Hematology & Blood Management AI" to listOf(
        PremiumQuestion("Coronary Stent Timing", "How should elective noncardiac surgery timing be managed after a recent coronary stent?"),
        PremiumQuestion("Dual Antiplatelet (DAPT) Management", "How should perioperative dual antiplatelet therapy (DAPT) be managed?"),
        PremiumQuestion("Mechanical Valve Bridging", "How should anticoagulation bridging be managed in a patient with a mechanical heart valve?"),
        PremiumQuestion("DOAC Emergency Surgery", "What is the reversal plan for direct oral anticoagulants (DOAC) in emergency surgery?"),
        PremiumQuestion("Obstetric Platelet Limits", "What are the neuraxial anesthesia safety limits in obstetric thrombocytopenia?"),
        PremiumQuestion("HELLP Syndrome Approach", "What is the anesthetic management and laboratory monitoring plan for a patient with HELLP syndrome?"),
        PremiumQuestion("Amniotic Fluid Embolism (AFE)", "What are the emergency management and resuscitation steps for suspected amniotic fluid embolism (AFE)?"),
        PremiumQuestion("Jehovah's Witness Blood Refusal", "How is blood management planned for a Jehovah's Witness patient who refuses transfusion?"),
        PremiumQuestion("Preoperative Anemia Management", "How should preoperative anemia optimization, iron, and erythropoietin therapy be managed?"),
        PremiumQuestion("Thrombocytopenia Surgical Limit", "What are the safety limits for surgery and regional anesthesia in patients with thrombocytopenia?")
    ),
    "Allergy & Respiratory Sedation AI" to listOf(
        PremiumQuestion("Local Anesthetic Allergy", "What is the management and alternatives for a patient with a history of local anesthetic allergy?"),
        PremiumQuestion("Antibiotic Allergy & Cefazolin", "Is cefazolin prophylaxis safe in a patient with a penicillin or cephalosporin allergy?"),
        PremiumQuestion("Perioperative Urticaria & Rash", "What is the differentiation from anaphylaxis and emergency action for perioperative urticaria or rash?"),
        PremiumQuestion("Bronchoscopy Sedation", "What are the sedation safety guidelines and topical local anesthetic limits in flexible bronchoscopy?"),
        PremiumQuestion("Endoscopy Sedation", "How are aspiration precautions and airway safety managed during gastrointestinal endoscopy sedation?"),
        PremiumQuestion("ERCP Prone Sedation", "How are airway and respiratory safety managed during ERCP sedation in the prone position?")
    ),
    "High-Risk Airway & Vascular AI" to listOf(
        PremiumQuestion("EBUS & Rigid Bronscoscopy", "How should shared airway and ventilation be managed during EBUS or rigid bronchoscopy?"),
        PremiumQuestion("Tracheal Stent Patient", "How is anesthesia and intubation safety managed in a patient with a tracheal stent?"),
        PremiumQuestion("Mediastinal Mass Collapse", "How is the risk of post-induction airway collapse managed in a patient with a mediastinal mass?"),
        PremiumQuestion("Anticoagulated Peripheral Block", "Are deep or non-compressible peripheral nerve blocks safe in an anticoagulated patient?")
    ),
    "Pediatric & Neonatal Special AI" to listOf(
        PremiumQuestion("Pediatric Pyloric Stenosis", "What is the anesthetic management, aspiration precautions, and resuscitation criteria for a baby with hypertrophic pyloric stenosis?"),
        PremiumQuestion("Neonatal Resuscitation 2025", "What are the 2025 AHA/AAP algorithm steps, ventilation corrective steps, and post-resuscitation care in neonatal resuscitation?"),
        PremiumQuestion("Neonatal Hypoglycemia", "What are the hypoglycemia thresholds, clinical signs, and enteral/parenteral glucose infusion protocols in neonates?"),
        PremiumQuestion("Neonatal Hypothermia", "How is intraoperative hypothermia prevented in neonates? What are the active warming techniques and physiological effects?"),
        PremiumQuestion("Congenital Diaphragmatic Hernia", "What are the airway management and lung-protective ventilation goals for a neonate with congenital diaphragmatic hernia (CDH)?"),
        PremiumQuestion("Tracheoesophageal Fistula", "What is the anesthetic management, induction, ventilation, and tube positioning for pediatric tracheoesophageal fistula (TEF) surgery?"),
        PremiumQuestion("Down Syndrome Anesthesia", "What are the preoperative preparations, atlantoaxial instability (AAI), and subglottic stenosis safety rules for patients with Down syndrome?"),
        PremiumQuestion("Cerebral Palsy Anesthesia", "What is the muscle relaxant sensitivity, baclofen withdrawal prevention, and delicate positioning in cerebral palsy (CP)?"),
        PremiumQuestion("Autism Spectrum Anesthesia", "How should preoperative prep, minimizing sensory overload, and calm premedication be planned in a patient with autism spectrum disorder (ASD)?")
    ),
    "Fever, Toxicology & Airway Emergency AI" to listOf(
        PremiumQuestion("Malignant Hyperthermia Diff", "How is the differential diagnosis of malignant hyperthermia managed, separating it from thyroid storm, pheo, and NMS?"),
        PremiumQuestion("Perioperative Fever", "What are the intraoperative and postoperative causes, differential diagnosis, and emergency management of perioperative fever?"),
        PremiumQuestion("Smoke Inhalation", "What are the early prophylactic intubation indications and upper airway edema management steps in smoke inhalation injury?"),
        PremiumQuestion("Carbon Monoxide Poisoning", "What is the diagnosis (COHb), standard oxygen treatment, and hyperbaric oxygen (HBO) indications in carbon monoxide poisoning?"),
        PremiumQuestion("Cyanide Poisoning", "What is the lactic acidosis threshold, monitoring, and specific antidote (Hydroxocobalamin) dosing in cyanide poisoning?")
    ),
    "Regional Safety & Block Complications AI" to listOf(
        PremiumQuestion("LA Max Dose Details", "What are the weight-based and absolute maximum safe dose limits of local anesthetics (lidocaine, bupivacaine) with and without epinephrine?"),
        PremiumQuestion("Regional Block Nerve Injury", "What are the diagnostic, follow-up, and electrophysiological evaluation steps for suspected nerve injury after regional anesthesia?"),
        PremiumQuestion("Compartment Syndrome", "What are the signs of compartment syndrome, the risk of masking by regional block, and urgent fasciotomy criteria?"),
        PremiumQuestion("Frequent Block Complications", "How are common complications (Horner's syndrome, LAST, pneumothorax) managed after interscalene or supraclavicular blocks?"),
        PremiumQuestion("Cauda Equina Syndrome", "What are the signs of cauda equina syndrome after neuraxial blockade and the timing for urgent surgical decompression?")
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiClinicalAssistantScreen(
    currentLanguage: String,
    onBackClick: () -> Unit,
    onSelectDrug: (String) -> Unit,
    onSelectCalculator: (String) -> Unit,
    onSelectAlgorithm: (String) -> Unit,
    initialText: String = ""
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val isTurkish = currentLanguage == "tr"
    fun t(tr: String, en: String): String = if (isTurkish) tr else en

    fun isInputEnglish(text: String): Boolean {
        val lowercaseText = text.lowercase()
        val turkishUniqueChars = setOf('ğ', 'ş', 'ç', 'ı', 'ö', 'ü', 'Ğ', 'Ş', 'Ç', 'İ', 'Ö', 'Ü')
        if (turkishUniqueChars.any { it in text }) return false

        val words = lowercaseText.split(Regex("[^a-zA-ZğüşöçıİĞÜŞÖÇ]+")).filter { it.isNotEmpty() }.toSet()
        
        val turkishWords = setOf(
            "ve", "bir", "nedir", "nelerdir", "nasil", "nasıl", "dozu", "hasta", "orani", "oranı", 
            "basinci", "basıncı", "veya", "icin", "için", "olan", "göre", "gore", "ile", "de", "da",
            "mi", "mı", "mu", "mü", "ne", "zaman", "nasıl", "nasil", "tedavi", "tedavisi", "ilac", "ilaç", 
            "doz", "kullanilir", "kullanılır", "kullanimi", "kullanımı", "risk", "riski", "belirtiler",
            "belirtileri"
        )
        
        val englishWords = setOf(
            "the", "and", "what", "how", "is", "dose", "patient", "pressure", "rate", "of", "for", 
            "with", "or", "in", "to", "at", "on", "by", "an", "a", "limit", "dosing", "treatment", 
            "drug", "use", "usage", "risk", "symptoms", "when", "guideline", "guidelines", "anesthesia"
        )
        
        val trCount = words.count { it in turkishWords }
        val enCount = words.count { it in englishWords }
        
        if (trCount > enCount) return false
        if (enCount > trCount) return true
        
        return currentLanguage == "en"
    }

    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", android.content.Context.MODE_PRIVATE) }
    var isOnlineMode by remember { mutableStateOf(false) } // Default to offline (Local)
    var hasAcceptedOnlineDisclaimer by remember { mutableStateOf(prefs.getBoolean("online_ai_disclaimer_accepted_v1", false)) }
    var showConsentModal by remember { mutableStateOf(false) }
    var showQuickPrompts by remember { mutableStateOf(true) }

    fun acceptOnlineDisclaimer() {
        prefs.edit().apply {
            putBoolean("online_ai_disclaimer_accepted_v1", true)
            putString("online_ai_disclaimer_accepted_at", java.text.DateFormat.getDateTimeInstance().format(java.util.Date()))
            apply()
        }
        hasAcceptedOnlineDisclaimer = true
        isOnlineMode = true
    }

    var userText by remember { mutableStateOf(initialText) }
    
    LaunchedEffect(initialText) {
        if (initialText.isNotEmpty()) {
            userText = initialText
        }
    }

    val messages = remember { mutableStateListOf<Message>() }
    var isGenerating by remember { mutableStateOf(false) }

    // Active premium category
    var activeCategory by remember { mutableStateOf("Preop AI") }

    // Gemini API settings
    val apiKey = com.anesthesiaclinic.anesthesiabriefs.utils.ApiKeyConfig.GEMINI_API_KEY
    var lastApiErrorDetail by remember { mutableStateOf<String?>(null) }

    suspend fun callGeminiApi(history: List<Message>, key: String, isQueryEnglish: Boolean): AiStructuredResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val urlConnection = URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$key")
                    .openConnection() as HttpURLConnection
                urlConnection.requestMethod = "POST"
                urlConnection.setRequestProperty("Content-Type", "application/json")
                urlConnection.doOutput = true
 
                val systemInstruction = if (!isQueryEnglish) {
                    "Sen deneyimli bir anestezi uzmanı yapay zeka asistanısın. Kullanıcının sorduğu anestezi, yoğun bakım veya acil tıp ile ilgili klinik sorulara son derece bilimsel, güncel kılavuzlara (ASA, ERC, ASRA vb.) dayalı ve Türkçe cevaplar ver. Yanıtını kesinlikle belirtilen JSON formatında oluşturmalısın. safetyLevel alanı sadece 'routine', 'urgent' veya 'emergency' değerlerini almalıdır. notASubstituteWarning alanına 'Bu analiz bir anestezi uzmanının klinik kararının yerini alamaz.' gibi bir Türkçe uyarı yaz. referencesToShow alanında en az iki adet güncel makale referansı (PMID'si ile birlikte) ver. Eğer kullanıcı basit bir soru soruyorsa (örn. dozlama, hızlı bilgi, ilaç soruları) veya bir önceki vakayı tartışmaya devam ediyorsa, yanıtını 'conversationalText' alanına yaz ve diğer tüm klinik dizileri boş array ([]) bırak. Eğer yeni ve karmaşık bir klinik vaka analizi soruluyorsa veya kullanıcı yeni bir vaka sunuyorsa, o zaman bilgileri ilgili kategorilere (immediateRedFlags, missingCriticalInformation, vb.) dağıt ve 'conversationalText' alanını null veya boş bırak."
                } else {
                    "You are an experienced anesthesiologist AI assistant. Provide highly scientific, guideline-based (ASA, ERC, ASRA, etc.) responses in English to clinical queries related to anesthesia, intensive care, or emergency medicine. You MUST format your response exactly in the specified JSON format. The safetyLevel field must only take 'routine', 'urgent', or 'emergency'. The notASubstituteWarning field must contain an English warning such as 'This analysis cannot replace the clinical judgment of an anesthesiologist.' In referencesToShow, provide at least two recent article references (with PMIDs). If the user is asking a simple question (e.g. dosing, quick info, drug queries) or continuing the discussion of a previous case, write your response in the 'conversationalText' field and leave all other clinical arrays empty ([]). If a new and complex clinical case analysis is being asked or a new case is presented, distribute the information to the appropriate categories (immediateRedFlags, missingCriticalInformation, etc.) and leave 'conversationalText' null or empty."
                }

                val contentsList = mutableListOf<String>()
                for (msg in history) {
                    if (msg.id == 0) continue // Skip welcome message
                    val roleStr = if (msg.role == "user") "user" else "model"
                    val partText = if (msg.role == "user") {
                        msg.content
                    } else {
                        if (msg.structuredResponse != null) {
                            kotlinx.serialization.json.Json.encodeToString(msg.structuredResponse)
                        } else {
                            msg.content
                        }
                    }
                    contentsList.add("""
                        {
                          "role": "$roleStr",
                          "parts": [
                            { "text": ${kotlinx.serialization.json.Json.encodeToString(partText)} }
                          ]
                        }
                    """.trimIndent())
                }

                val contentsJson = contentsList.joinToString(separator = ",\n")

                val requestBody = """
                    {
                      "contents": [
                        $contentsJson
                      ],
                      "systemInstruction": {
                        "parts": [
                          { "text": ${kotlinx.serialization.json.Json.encodeToString(systemInstruction)} }
                        ]
                      },
                      "generationConfig": {
                        "responseMimeType": "application/json",
                        "responseSchema": {
                          "type": "OBJECT",
                          "properties": {
                            "safetyLevel": { "type": "STRING", "enum": ["routine", "urgent", "emergency"] },
                            "notASubstituteWarning": { "type": "STRING" },
                            "conversationalText": { "type": "STRING" },
                            "immediateRedFlags": { "type": "ARRAY", "items": { "type": "STRING" } },
                            "missingCriticalInformation": { "type": "ARRAY", "items": { "type": "STRING" } },
                            "likelyClinicalCategoriesToConsider": { "type": "ARRAY", "items": { "type": "STRING" } },
                            "suggestedNextStepsGeneral": { "type": "ARRAY", "items": { "type": "STRING" } },
                            "doNotMiss": { "type": "ARRAY", "items": { "type": "STRING" } },
                            "whenToEscalate": { "type": "ARRAY", "items": { "type": "STRING" } },
                            "referencesToShow": { "type": "ARRAY", "items": { "type": "STRING" } }
                          },
                          "required": ["safetyLevel", "notASubstituteWarning", "conversationalText", "immediateRedFlags", "missingCriticalInformation", "likelyClinicalCategoriesToConsider", "suggestedNextStepsGeneral", "doNotMiss", "whenToEscalate", "referencesToShow"]
                        }
                      }
                    }
                """.trimIndent()

                urlConnection.outputStream.use { os ->
                    val input = requestBody.toByteArray(charset("utf-8"))
                    os.write(input, 0, input.size)
                }

                val responseCode = urlConnection.responseCode
                if (responseCode == 200) {
                    val responseString = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    val rootJson = kotlinx.serialization.json.Json.parseToJsonElement(responseString) as kotlinx.serialization.json.JsonObject
                    val candidates = rootJson["candidates"] as? kotlinx.serialization.json.JsonArray
                    val firstCandidate = candidates?.firstOrNull() as? kotlinx.serialization.json.JsonObject
                    val content = firstCandidate?.get("content") as? kotlinx.serialization.json.JsonObject
                    val parts = content?.get("parts") as? kotlinx.serialization.json.JsonArray
                    val firstPart = parts?.firstOrNull() as? kotlinx.serialization.json.JsonObject
                    val text = firstPart?.get("text")?.jsonPrimitive?.content ?: ""
                    var rawJson = text.trim()
                    
                    if (rawJson.startsWith("```")) {
                        rawJson = rawJson.removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
                    }
                    
                    val parsed = kotlinx.serialization.json.Json { 
                        ignoreUnknownKeys = true 
                        coerceInputValues = true
                    }.decodeFromString<AiStructuredResponse>(rawJson)
                    
                    withContext(Dispatchers.Main) {
                        lastApiErrorDetail = null
                    }
                    parsed
                } else {
                    val errorString = urlConnection.errorStream?.bufferedReader()?.use { it.readText() } ?: ""
                    withContext(Dispatchers.Main) {
                        lastApiErrorDetail = "HTTP $responseCode: $errorString"
                    }
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMsg = e.localizedMessage ?: e.javaClass.simpleName
                withContext(Dispatchers.Main) {
                    lastApiErrorDetail = errorMsg
                }
                null
            }
        }
    }

    // Pre-populate chat history with greeting
    if (messages.isEmpty()) {
        val greetingText = if (isTurkish) {
            "Merhaba! Ben Anesthesia Briefs AI Klinik Asistanıyım. Klinik vaka akıl yürütmenize, kırmızı bayrakları belirlemeye veya ilgili kılavuzlara ulaşmanıza yardımcı olabilirim. Lütfen tartışmak istediğiniz vakayı veya soruyu aşağıya yazın."
        } else {
            "Hello! I am the Anesthesia Briefs AI Clinical Assistant. I can help you with clinical case reasoning, identifying red flags, or accessing relevant guidelines. Please type the case details or your question below."
        }
        messages.add(
            Message(
                id = 0,
                role = "assistant",
                content = greetingText
            )
        )
    }

    // Client-side Anonymization Pipeline
    fun anonymizeText(text: String): Pair<String, Boolean> {
        var cleanText = text
        var found = false

        // 1. T.C. Kimlik No (11-digit regex)
        val tcRegex = Regex("\\b\\d{11}\\b")
        if (tcRegex.containsMatchIn(cleanText)) {
            cleanText = cleanText.replace(tcRegex, "[MASKED_KIMLIK]")
            found = true
        }

        // 2. Telefon Numaraları
        val phoneRegex = Regex("(05|5)\\d{2}\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}")
        if (phoneRegex.containsMatchIn(cleanText)) {
            cleanText = cleanText.replace(phoneRegex, "[MASKED_TELEFON]")
            found = true
        }

        // 3. Hospital/File Number Heuristic (e.g. Protokol 123456)
        val fileRegex = Regex("(?i)(protokol|dosya|hasta)\\s?(no|numarası)?\\s?\\b\\d{4,9}\\b")
        if (fileRegex.containsMatchIn(cleanText)) {
            cleanText = cleanText.replace(fileRegex, "[MASKED_DOSYA_NO]")
            found = true
        }

        return Pair(cleanText, found)
    }

    // Local deterministic clinical reasoning engine for robust offline-ready assistance
    fun generateDeterministicAiResponse(userCleanInput: String): AiStructuredResponse {
        val q = userCleanInput.lowercase()

        // 1. Context-aware query enrichment for short follow-up questions
        var enrichedInput = userCleanInput
        val qClean = userCleanInput.lowercase().trim()
        val words = qClean.split(Regex("\\s+")).filter { it.isNotEmpty() }
        
        val followUpKeywordsTr = listOf("neden", "niçin", "nasıl", "alternatif", "kontrendikasyon", "komplikasyon", "başka", "kimlere")
        val followUpKeywordsEn = listOf("why", "how", "alternative", "contraindication", "complication", "other", "who")
        
        if (words.size <= 5 && (followUpKeywordsTr.any { qClean.contains(it) } || followUpKeywordsEn.any { qClean.contains(it) })) {
            val lastUserMsg = messages.dropLast(1).lastOrNull { it.role == "user" }?.content
            if (!lastUserMsg.isNullOrBlank()) {
                enrichedInput = "$lastUserMsg $userCleanInput"
                println("LocalSearchEngine: Enriched query '$userCleanInput' to '$enrichedInput'")
            }
        }

        // 2. Try to find the best fuzzy/semantic match from our local 500+ Q&A database first
        val isQueryEnglish = isInputEnglish(enrichedInput)
        val match = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch(enrichedInput, isQueryEnglish)
        if (match != null) {
            return if (isQueryEnglish) match.en else match.tr
        }

        // 2. Try to fetch premium predefined high-fidelity offline response from hardcoded triggers
        val premiumResponse = com.anesthesiaclinic.anesthesiabriefs.data.repository.SeedDataAiResponses.getStructuredResponse(userCleanInput, isQueryEnglish)
        if (premiumResponse != null) {
            return premiumResponse
        }

        // 3. Fallback: check if the query is non-clinical/general assistant question to prevent clinical card leakage
        val clinicalKeywords = listOf(
            "sepsis", "shock", "şok", "hemodinami", "aritmi", "arrest", "cpr", "resüsitasyon", "kalp", "havayolu", "entübe", "entübasyon",
            "anestezi", "anestetik", "propofol", "süksinilkolin", "rokuronyum", "doz", "mg", "kg", "hasta", "klinik", "tedavi",
            "kontrendike", "endikasyon", "kılavuz", "blok", "spinal", "epidural", "laringospazm", "bronkospazm", "toksisite", "last",
            "npo", "açlık", "aspirasyon", "akciğer", "oksijen", "spo2", "hipoksi", "hiperkalemi", "kalsiyum", "infüzyon", "atc"
        )
        val isClinical = clinicalKeywords.any { q.contains(it) }

        if (!isClinical) {
            return AiStructuredResponse(
                safetyLevel = "routine",
                notASubstituteWarning = if (isQueryEnglish) "General Assistant Mode" else "Genel Asistan Modu",
                conversationalText = if (isQueryEnglish) {
                    "I couldn't find a direct answer to this daily life question in my local database. As a general assistant, you can ask me about daily planning, productivity, home organization, fitness, food, or general workflow tips!"
                } else {
                    "Bu soruya yerel veritabanında tam bir cevap bulamadım. Ancak genel asistan olarak bana günlük planlama, verimlilik, ev düzeni, beslenme, kişisel bakım ve çalışma pratikleri hakkında başka sorular sorabilirsiniz!"
                },
                immediateRedFlags = emptyList(),
                missingCriticalInformation = emptyList(),
                likelyClinicalCategoriesToConsider = emptyList(),
                suggestedNextStepsGeneral = emptyList(),
                relatedAlgorithms = emptyList(),
                relatedCalculators = emptyList(),
                relatedDrugCards = emptyList(),
                doNotMiss = emptyList(),
                whenToEscalate = emptyList(),
                referencesToShow = emptyList(),
                followUpQuestions = emptyList()
            )
        }

        // Emergency detection keyword matching matching both Turkish and English acute states
        val emergencyKeywords = listOf(
            "arrest", "cpr", "kalp durdu", "mavi kod", "code blue",
            "cico", "entübe edemiyorum", "ventile edemiyorum", "cannot intubate", "cannot oxygenate",
            "satürasyon düşüyor", "hipoksemi", "spo2 düşük", "desaturation", "hypoxemia", "low spo2",
            "anafilaksi", "alerji", "bronkospazm", "laringospazm", "anaphylaxis", "allergy", "bronchospasm", "laryngospasm",
            "malign hipertermi", "etco2 yükseliyor", "hiperkapni", "malignant hyperthermia", "rising etco2", "hypercapnia",
            "last", "lokal anestezik toksisite", "local anesthetic systemic toxicity",
            "masif kanama", "mtp", "transfüzyon", "massive bleeding", "massive transfusion",
            "hiperkalemi", "asistoli", "vf", "vt", "hyperkalemia", "asystole", "ventricular fibrillation", "ventricular tachycardia",
            "yüksek spinal", "total spinal", "high spinal",
            "aspirasyon", "regürjitasyon", "aspiration", "regurgitation",
            "pulmoner emboli", "hava embolisi", "co2 embolisi", "pulmonary embolism", "air embolism",
            "tansiyon pnömotoraks", "tension pneumothorax",
            "epidural hematom", "motor defisit", "epidural hematoma", "motor deficit",
            "opioid solunum depresyonu", "nalokson", "naloxone", "respiratory depression"
        )
        val safety = if (emergencyKeywords.any { q.contains(it) } || q.contains("zor") || q.contains("kurtarma") || q.contains("spazm")) "emergency" else "routine"
        
        return when {
            // 1. CARDIAC ARREST / ACLS
            q.contains("arrest") || q.contains("kalp") || q.contains("durdu") || q.contains("cpr") || q.contains("mavi") || q.contains("resüsitasyon") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "🚨 HASTANIN KALBİ DURDU! DERHAL KARDİYAK ARREST / ACLS RESÜSİTASYON PROTOKOLÜNÜ BAŞLATIN!",
                    immediateRedFlags = listOf(
                        "Nabız alınamıyor, solunum durmuş, EKG'de Asistoli/VF/Nabızsız VT/NEA ritmi mevcut.",
                        "Kritik beyin ve organ hipoksisi saniyeler içinde başlar."
                    ),
                    missingCriticalInformation = listOf(
                        "Mevcut EKG ritmi (Şoklanabilir: VF/Nabızsız VT vs. Şoklanamaz: Asistoli/NEA)",
                        "Hastanın tahmini kilosu (Pediatrik veya yetişkin resüsitasyon dozlamaları için)",
                        "Damar yolu (IV) veya intraosseöz (IO) erişim durumu"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Kardiyak Arrest (VF/VT/NEA/Asistoli)",
                        "Geri döndürülebilir nedenler (5H 5T: Hipovolemi, Hipoksi, Asidoz, Hipo/Hiperkalemi, Hipotermi; Tension Pnömotoraks, Tamponad, Toksinler, Tromboz)"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Mavi Kod (Resüsitasyon Ekibi) durumunu ilan edin ve defibrilatörü derhal getirtin.",
                        "Kesintisiz, yüksek kaliteli göğüs kompresyonlarına başlayın (100-120/dk hızda, 5-6 cm derinlikte).",
                        "Şoklanabilir Ritim (VF/Nabızsız VT) ise: Hemen 1 şok (200 J bifazik) verin ve derhal CPR'a 2 dakika devam edin.",
                        "Şoklanamaz Ritim (Asistoli/NEA) ise: Derhal 1 mg Adrenalin IV/IO uygulayın, her 3-5 dakikada bir tekrarlayın.",
                        "İleri havayolunu (Endotrakeal tüp veya SGA) yerleştirin, dakikada 10 nefes verin (kompresyonlara ara vermeden)."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("bradycardia_rescue", "Bradikardi Algoritması"),
                        AiSuggestionItem("tachycardia_rescue", "Taşikardi Algoritması"),
                        AiSuggestionItem("cico_crisis", "CICO Acil Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("pediatric_dosing", "Pediatrik Doz Hesaplayıcı"),
                        AiSuggestionItem("dantrolene_rescue", "Dantrolen Hesaplayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("ephedrine", "Efedrin Monografı"),
                        AiSuggestionItem("norepinephrine", "Noradrenalin Monografı")
                    ),
                    doNotMiss = listOf("CPR sırasında göğüs geri yayılımına izin vermek", "Her 2 dakikada bir ritim/nabız kontrolünü 10 saniyeden kısa tutmak"),
                    whenToEscalate = listOf("Spontan dolaşımın dönmesi (ROSC) sonrası post-arrest yoğun bakım yönetimine geçiş"),
                    referencesToShow = listOf("AHA Guidelines for CPR and ECC. Circulation 2020. PMID: 33081529.", "ERC Guidelines for Resuscitation 2021. Resuscitation 2021. PMID: 33773824.")
                )
            }
            // 2. DIFFICULT AIRWAY & CICO
            q.contains("zor") || q.contains("entübe") || q.contains("entübasyon") || q.contains("havayolu") || q.contains("hava yolu") || q.contains("cico") || q.contains("maske") || q.contains("ekstübasyon") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "🚨 ZOR HAVAYOLU / CICO ALARMI! Solunum yolu açıklığı ve ventilasyon acil tehlikede.",
                    immediateRedFlags = listOf(
                        "SpO₂ hızla düşüyor, manuel maske ventilasyonu başarısız (Cannot Oxygenate).",
                        "Vokal kordlar görüntülenemiyor (Cormack-Lehane Grade 3-4)."
                    ),
                    missingCriticalInformation = listOf(
                        "Maske ventilasyonu şu an yeterli mi?",
                        "Hastanın pediatrik, obstetrik (gebe) veya obezite durumu?",
                        "Zor havayolu arabası ve videolaringoskop başucunda hazır mı?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Beklenmeyen zor laringoskopi/entübasyon",
                        "Zor maske ventilasyonu",
                        "Cannot Intubate Cannot Oxygenate (CICO) krizi"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Kıdemli hekim desteği isteyin ve zor havayolu arabasını derhal getirttirin.",
                        "Çift kişi maske ventilasyonunu (jaw thrust + oral/nasal airway) %100 O₂ ile optimize edin.",
                        "Girişim sayısını sınırlayın (maksimum 2 veya 3 deneme) - laringeal ödemi engellemek için önemlidir.",
                        "Videolaringoskop (VL) ve kılavuz (bougie veya stile) kullanın.",
                        "Eğer entübasyon ve oksijenasyon tamamen başarısızsa (CICO), derhal Neşter-Bougie ile krikotirotomi açın."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("expected_difficult_airway", "Zor Havayolu Algoritması"),
                        AiSuggestionItem("unexpected_difficult_intubation", "Beklenmeyen Zor Entübasyon"),
                        AiSuggestionItem("cico_crisis", "CICO Kriz Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("difficult_airway", "Zor Havayolu Checklist'i")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı"),
                        AiSuggestionItem("rocuronium", "Roküronyum Monografı")
                    ),
                    doNotMiss = listOf("Başarısız her entübasyon denemesi sonrası mutlaka maske ile oksijenasyon denemek", "Krikoid basıyı (Sellick) gerekirse gevşetmek"),
                    whenToEscalate = listOf("Cerrahi havayolu ihtiyacı doğduğunda KBB veya Genel Cerrahi desteğini acil çağırmak"),
                    referencesToShow = listOf("2022 American Society of Anesthesiologists Practice Guidelines for Management of the Difficult Airway. Anesthesiology 2022; 136(1):31-81. PMID: 34762244.", "Difficult Airway Society (DAS) Guidelines. British Journal of Anaesthesia 2015. PMID: 26556848.")
                )
            }
            // 3. NPO & FASTING / ASPIRATION
            q.contains("açlık") || q.contains("npo") || q.contains("yemek") || q.contains("sıvı") || q.contains("anne sütü") || q.contains("bebek") || q.contains("aspirasyon") -> {
                val warning = if (q.contains("aspirasyon") || q.contains("acil")) "critical" else "moderate"
                AiStructuredResponse(
                    safetyLevel = "urgent",
                    notASubstituteWarning = "🍽️ PREOPERATİF AÇLIK (NPO) VE ASPİRASYON YÖNETİMİ. Elektif açlık süreleri güvenliği korumak içindir.",
                    immediateRedFlags = listOf(
                        "Tok hastada elektif cerrahi aspirasyon pnömonisi riski nedeniyle ertelenmelidir.",
                        "Acil cerrahide NPO süresi beklenemez, Hızlı Ardışık İndüksiyon (RSI) uygulanmalıdır."
                    ),
                    missingCriticalInformation = listOf(
                        "En son alınan besinin türü (Berrak sıvı, anne sütü, hafif öğün, yağlı yemek)?",
                        "Son alım saati ile planlanan cerrahi saati?",
                        "Mide boşalmasını geciktiren durum var mı (Gebelik, kontrolsüz DM, barsak obstrüksiyonu)?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Mide boşalması gecikmiş / yüksek aspirasyon riskli hasta",
                        "2-4-6-8 Saat Açlık Kılavuzu uygunluğu",
                        "Acil cerrahi ve RSI endikasyonu"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Berrak sıvılar (su, çay, posasız meyve suyu) için 2 saat açlık yeterlidir.",
                        "Anne sütü için 4 saat, formül mama ve hafif öğünler için 6 saat açlık gereklidir.",
                        "Katı ve yağlı ağır yemekler için minimum 8 saat açlık süresi uygulanmalıdır.",
                        "Tok hastada acil cerrahide aspirasyon profilaksisi (H2 reseptör blokörü + Sodyum sitrat) uygulayın.",
                        "RSI (Hızlı Ardışık İndüksiyon) planlayın, krikoid bası uygulayın ve spontan solunumu hızla baskılayın."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("preop_npo_evaluation", "Preoperatif NPO Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("npo_fasting", "NPO Açlık Sayacı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı"),
                        AiSuggestionItem("propofol", "Propofol Monografı")
                    ),
                    doNotMiss = listOf("Gastroparezisi olan diyabetiklerde açlık sürelerinin uzatılabileceği uyarısı", "Berrak sıvıların su bardağı sınırını aşmaması"),
                    whenToEscalate = listOf("Ciddi masif aspirasyon durumunda derhal endotrakeal aspirasyon ve koruyucu ARDS ventilasyonuna geçiş"),
                    referencesToShow = listOf("Practice Guidelines for Preoperative Fasting and the Use of Pharmacologic Agents. Anesthesiology 2023; 138(2):105-128. PMID: 36622802.", "ESAIC NPO Guidelines. European Journal of Anaesthesiology 2011. PMID: 21540671.")
                )
            }
            // 4. PREOP CARDIAC & RISK / RCRI / ASA
            q.contains("asa") || q.contains("risk") || q.contains("kardiyak") || q.contains("rcri") || q.contains("ertele") || q.contains("günübirlik") || q.contains("tetkik") || q.contains("konsültasyon") -> {
                AiStructuredResponse(
                    safetyLevel = "routine",
                    notASubstituteWarning = "📋 PREOPERATİF RİSK VE OPTİMİZASYON DEĞERLENDİRMESİ. Hastanın ameliyat öncesi bazal risklerini belirler.",
                    immediateRedFlags = listOf(
                        "Son 3 ayda geçirilmiş MI, inme veya kontrolsüz dekompanse kalp yetmezliği elektif cerrahiyi erteletir.",
                        "Evre 3 Hipertansiyon (Tansiyon >= 180/110 mmHg) hedef organ hasarı yoksa erteleme gerektirmez ama kontrol altına alınmalıdır."
                    ),
                    missingCriticalInformation = listOf(
                        "Hastanın fonksiyonel kapasitesi (METS düzeyi >= 4 yeterli midir?)",
                        "RCRI risk faktörleri sayısı (1. Yüksek riskli cerrahi, 2. İskemik kalp, 3. KKY, 4. SVO, 5. İnsülin DM, 6. Kreatinin >2.0)"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Revised Cardiac Risk Index (RCRI) Sınıflandırması",
                        "ASA Fiziksel Durum Sınıflaması (ASA I - VI)",
                        "Preoperatif Laboratuvar ve EKG/Eko tetkik gereklilikleri"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "RCRI Skoru >= 2 ise ameliyat öncesi EKG ve transtorasik ekokardiyografi (TTE) ile sol ventrikül fonksiyon değerlendirmesi önerilir.",
                        "Günübirlik cerrahide: ASA I-II hastalar ile stabil ve kontrol altında ASA III hastalar günübirlik cerrahiye alınabilir.",
                        "Hastanın fonksiyonel kapasitesi METS < 4 ise veya dekompanse ise kardiyoloji konsültasyonu şarttır.",
                        "Hipertansif acil (akut hedef organ hasarlı yüksek tansiyon) durumunda elektif cerrahi ertelenmeli, tansiyon IV ajanlarla kontrollü düşürülmelidir."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("preop_cardiac_rcri", "Preoperatif RCRI Algoritması"),
                        AiSuggestionItem("asa_ps_evaluation", "ASA Sınıflama Algoritması"),
                        AiSuggestionItem("hypertensive_delay_elective", "Hipertansif Erteleme Kılavuzu")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("asa_ps", "ASA PS Hesaplama Yardımcısı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("norepinephrine", "Noradrenalin Monografı")
                    ),
                    doNotMiss = listOf("Yaşlı hastalarda kırılganlık (frailty) değerlendirmesi", "Beta blokerlerin sabah az suyla aldırılması"),
                    whenToEscalate = listOf("Kritik iskemik kardiyak hadiselerde elektif cerrahi iptali ve acil yoğun bakım veya anjiyo planı"),
                    referencesToShow = listOf("Halvorsen S, et al.: 2022 ESC/ESAIC Guidelines on cardiovascular assessment and management in non-cardiac surgery. European Heart Journal 2022. PMID: 36017553.", "ASA Physical Status Classification Guidelines. American Society of Anesthesiologists Standards 2020.")
                )
            }
            // 5. ANTICOAGULANTS & NEURAXIAL
            q.contains("antikoagülan") || q.contains("kan sulandırıcı") || q.contains("kateter") || q.contains("warfarin") || q.contains("aspirin") || q.contains("klopidogrel") || q.contains("asra") || q.contains("spinal") || q.contains("epidural") || q.contains("hematom") || q.contains("heparin") || q.contains("apixaban") || q.contains("rivaroxaban") -> {
                AiStructuredResponse(
                    safetyLevel = "urgent",
                    notASubstituteWarning = "🩸 ANTİKOAGÜLAN YÖNETİMİ VE NÖROAKSİYEL BLOK GÜVENLİĞİ. İlerleyici epidural hematom felce yol açabilir.",
                    immediateRedFlags = listOf(
                        "Spinal/epidural blok veya kateter çekimi sonrası ani gelişen sırt ağrısı ve bacaklarda his kaybı acil epidural hematom şüphesidir!",
                        "INR > 1.5 veya Trombosit < 80.000/mm³ durumunda nöroaksiyel blokaj kontrendikedir."
                    ),
                    missingCriticalInformation = listOf(
                        "Hastanın en son aldığı antikoagülan ilacın adı ve son alım saati?",
                        "Böbrek fonksiyonu (Kreatinin Klirensi - DOAC'ların kesilme süresi için hayati önemdedir)"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "ASRA Pain Medicine 5th Edition (2025) Kılavuz Standartları",
                        "Köprüleme (Bridging) Tedavisi ihtiyacı (Warfarin kullanan mekanik kapaklı hastalar vb.)",
                        "Epidural hematom durumunda acil dekompressif laminektomi zamanlaması (<8 saat)"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Warfarin (Coumadin): Girişimden 5 gün önce kesilmeli, INR < 1.5 olduğunda spinal anestezi yapılmalıdır.",
                        "Klopidogrel (Plavix): Girişimden 5-7 gün önce kesilmelidir. Ticagrelor (Brilinta): 5 gün önce kesilmelidir.",
                        "DOAC'lar (Apiksaban/Rivaroksaban): Elektif girişimlerden ve yüksek riskli bloklardan en az 72 saat önce durdurulmalıdır.",
                        "Profilaktik LMWH (Clexane vb.): Son dozdan en az 12 saat sonra kateter çekilebilir veya girişim yapılabilir.",
                        "Kateter çekildikten sonra ilk doz kan sulandırıcı için en az 4 saat beklenmelidir."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("coagulation_management", "Antikoagülan Yönetim Algoritması"),
                        AiSuggestionItem("epidural_hematoma_suspicion", "Epidural Hematom Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("asra_coagulation", "ASRA Antikoagülan Tablosu")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("morphine", "Morfin Monografı")
                    ),
                    doNotMiss = listOf("Kateter çekimi esnasında da en az girişim kadar dikkatli bekleme süresine uyulmalıdır", "Hastanın nörolojik durumunun 2 saatte bir takibi"),
                    whenToEscalate = listOf("Epidural hematom kanıtlandığında 8 saat içinde ACİL Beyin Cerrahisi Dekompresyonu"),
                    referencesToShow = listOf("Horlocker TT, et al.: Regional Anesthesia in the Patient Receiving Antithrombotic or Thrombolytic Therapy: ASRA Pain Medicine Evidence-Based Guidelines. Regional Anesthesia & Pain Medicine 2018. PMID: 29356773.", "TARD Antikoagülan Kılavuzu (Turkish Anesthesiology Association Guidelines), 2021.")
                )
            }
            // 6. MALIGNANT HYPERTHERMIA
            q.contains("malign") || q.contains("hipertermi") || q.contains("dantrolen") || q.contains("mh") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "🚨 ACİL MALİGN HİPERTERMİ KRİZİ! Tetikleyici ajanlara bağlı hipermetabolik reaksiyon.",
                    immediateRedFlags = listOf(
                        "Açıklanamayan hızlı ETCO₂ yükselmesi ve taşikardi.",
                        "Genelleşmiş kas rijiditesi (özellikle masseter spazmı) ve ani hipertermi."
                    ),
                    missingCriticalInformation = listOf(
                        "Hastanın tam kilosu (Dantrolen yükleme ve idame dozları için kritik önemdedir)",
                        "Uygulanan güncel tetikleyici anestezi ajanı (Sevofluran, Desfluran, Süksinilkolin)"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Malign Hipertermi (MH) Kriz Tablosu",
                        "Nöroleptik Malign Sendrom",
                        "Tiroid Fırtınası"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Tüm tetikleyici uçucu anestezikleri (Sevo/Des) kapatın, TIVA'ya (Propofol) geçin, Süksinilkolini durdurun.",
                        "Hava akışını >10 L/dk %100 O₂ yapın, devrelere hemen aktif karbon filtreleri yerleştirin.",
                        "Dantrolen 2.5 mg/kg IV bolus hemen uygulayın (20 mg flakonlar 60 mL distile su ile sulandırılır).",
                        "Hastayı aktif olarak soğutmaya başlayın (buzlu IV kristalloidler, buz torbaları, soğuk mide lavajı).",
                        "Kan gazı kontrolü ile metabolik asidozu Sodyum Bikarbonat, hiperkalemiyi insülin-glukoz ile tedavi edin."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("malignant_hyperthermia", "Malign Hipertermi Kriz Kılavuzu")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("dantrolene_rescue", "Dantrolen Hesaplayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı")
                    ),
                    doNotMiss = listOf("Kalsiyum kanal blokerlerini (verapamil vb.) dantrolen ile birlikte asla kullanmamak (hiperkalemi/asistoli riski)", "Dantrolen dozunu kriz çözülene kadar 10 mg/kg'a kadar tekrarlayabilmek"),
                    whenToEscalate = listOf("Yoğun bakım ünitesine transfer ve 24-48 saatlik yakın MH nüks izlemi"),
                    referencesToShow = listOf("EMHG guidelines for the investigation and management of malignant hyperthermia susceptibility. British Journal of Anaesthesia 2010. PMID: 20705639.", "Malignant Hyperthermia Crisis Flowchart. Malignant Hyperthermia Association of the United States (MHAUS), 2023.")
                )
            }
            // 7. LAST / LOCAL ANESTHETIC TOXICITY
            q.contains("last") || q.contains("toksisite") || q.contains("lokal") || q.contains("lipid") || q.contains("maksimum") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "Lokal Anestezik Sistemik Toksisitesi (LAST) hayati önem taşır. Derhal LAST protokolünü başlatın.",
                    immediateRedFlags = listOf(
                        "Nöbet veya ani nörolojik ajitasyon varlığı.",
                        "Genişlemiş QRS, bradikardi veya kardiyak arrest şüphesi."
                    ),
                    missingCriticalInformation = listOf(
                        "Uygulanan lokal anestezik türü ve toplam mg dozu.",
                        "Kilosu (Lipidrescue hesabı için)",
                        "Enjeksiyonun intravenöz kaçış şüphesi"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Lokal Anestezik Sistemik Toksisitesi (LAST)",
                        "Anafilaktik reaksiyon",
                        "Maksimum lokal anestezik doz sınırları"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Lokal anestezik enjeksiyonunu DERHAL durdurun.",
                        "Yardım çağırın, %100 Oksijen bağlayın.",
                        "%20 Lipid Emülsiyonu hazırlatın: 1.5 mL/kg IV bolus uygulayın, ardından 0.25 mL/kg/dk infüzyon başlatın.",
                        "Maksimum Güvenli Dozlar: Lidokain = 4 mg/kg (adrenalinli 7 mg/kg), Bupivakain = 2 mg/kg (adrenalinli 2.5 mg/kg)."
                    ),
                    relatedAlgorithms = listOf(AiSuggestionItem("last_toxicity", "Lokal Anestezik Toksisitesi Algoritması")),
                    relatedCalculators = listOf(
                        AiSuggestionItem("lipid_rescue", "LAST LipidRescue Hesaplayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("sugammadex", "Sugammadeks Monografı")
                    ),
                    doNotMiss = listOf("Solunum yolu kontrolü ve hipoksi engelleme", "Benzodiazepinler ile nöbet baskılama"),
                    whenToEscalate = listOf("Asistoli veya dirençli ventriküler fibrilasyon durumunda standart CPR + Lipid Rescue devam etme"),
                    referencesToShow = listOf("American Society of Regional Anesthesia and Pain Medicine (ASRA) Checklist for Managing Local Anesthetic Systemic Toxicity. Regional Anesthesia & Pain Medicine 2021. PMID: 33148630.", "TARD Güvenli Lokal Anestezi Bildirgesi, 2022.")
                )
            }
            // 8. BRADYCARDIA / TACHYCARDIA
            q.contains("bradikardi") || q.contains("taşikardi") || q.contains("nabız") || q.contains("aritmi") || q.contains("esmolol") || q.contains("amiodaron") -> {
                val warning = "critical"
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "💓 PERİOPERATİF RİTİM VE hemodinami Bozukluğu. Hızlı karar verme organ perfüzyonunu korur.",
                    immediateRedFlags = listOf(
                        "Bradikardi (Nabız <50/dk) ciddi hipotansiyon ve şok tablosuyla eşlik ediyorsa acil müdahale gerekir.",
                        "Taşikardi (Nabız >150/dk) hemodinamik instabiliteye neden olduysa defibrilatör ile senkronize kardiyoversiyon uygulanmalıdır."
                    ),
                    missingCriticalInformation = listOf(
                        "Mevcut tam kalp hızı ve ortalama arter basıncı?",
                        "EKG ritminde QRS genişliği (Dar QRS <0.12 sn vs Geniş QRS >=0.12 sn)?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Sempatomimetik yetersizlik / Yüksek spinal anestezi vagal tonusu",
                        "Dar QRS Supraventriküler Taşikardi (SVT) veya Sinüs Taşikardisi",
                        "Geniş QRS Ventriküler Taşikardi (VT) veya şüpheli aritmiler"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Bradikardi stabil değilse: Atropin 1.0 mg IV bolus uygulayın (her 3-5 dk, maks 3.0 mg). Yetmezse pacing hazırlayın veya Adrenalin/Dopamin infüzyonuna başlayın.",
                        "Laparoskopi esnasında bradikardi gelişirse: Pnömoperitoneumu derhal sonlandırın ve cerrahın çekmesini durdurun.",
                        "Dar QRS stabil SVT: Vagal manevralar uygulayın, yetmezse Adenozin 6 mg IV hızlı bolus yapın.",
                        "Dar QRS Sinüs Taşikardisi: Nedenini (ağrı, yetersiz anestezi, hipovolemi) bularak tedavi edin. Gerekirse Esmolol uygulayın."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("bradycardia_rescue", "Bradikardi Algoritması"),
                        AiSuggestionItem("tachycardia_rescue", "Taşikardi Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("infusion_vasopressors", "Vazopressör İnfüzyon Hesaplayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("ephedrine", "Efedrin Monografı")
                    ),
                    doNotMiss = listOf("Çocuklarda bradikardi genellikle hipoksemi belirtisidir, öncelikle ventilasyonu kontrol edin", "Senkronize kardiyoversiyonda defibrilatörün 'SYNC' butonunu aktif etmek"),
                    whenToEscalate = listOf("Kardiyak arrest (asistoli/nabızsız VT) durumunda derhal mavi kod ACLS protokolü"),
                    referencesToShow = listOf("AHA Guidelines for Cardiopulmonary Resuscitation and Emergency Cardiovascular Care. Circulation 2020. PMID: 33081529.", "AHA/ERC Cardiac Arrest and Arrhythmia Standard Guidelines.")
                )
            }
            // 9. HYPOXEMIA / HYPOTENSION / TUBE / CAPNOGRAPHY / PEAK PRESSURE
            q.contains("satürasyon") || q.contains("hipoksemi") || q.contains("oksijen") || q.contains("hipotansiyon") || q.contains("tansiyon düştü") || q.contains("etco2") || q.contains("hava yolu basıncı") || q.contains("peak") || q.contains("plateau") || q.contains("tüp") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "🚨 İNTRAOPERATİF SOLUNUM VE DOLANIM ACİLLERİ! Hızla hipoksi ve organ perfüzyon kaybı gelişir.",
                    immediateRedFlags = listOf(
                        "SpO₂ < 90% veya PaO₂ < 60 mmHg ciddi doku hipoksisi gösterir, hemen %100 FiO₂ ile manuel ventilasyona geçin.",
                        "Tansiyonun indüksiyon sonrası OAB <65 mmHg altına düşmesi böbrek hasarı riski oluşturur, erkenden Noradrenalin başlayın."
                    ),
                    missingCriticalInformation = listOf(
                        "Manuel ventilasyon yaparken havayolu direnci ve göğüs kalkışı nasıl?",
                        "ETCO₂ dalga formu sıfır mı (özofageal entübasyon) yoksa atelektazi/bronkospazm şüphesi mi var?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "İntraoperatif Hipoksemi Algoritması (atelektazi, bronkospazm, devre kaçakları)",
                        "İntraoperatif Hipotansiyon Algoritması (vazodilatasyon, kanama, kardiyak depresyon)",
                        "Peak ve Plateau basınç yükselmesi (Peak yüksek Plateau normal ise havayolu direnci artışı/bronkospazm; ikisi de yüksekse atelektazi/pnömotoraks)"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Hipoksemi: %100 O₂ verip kapnografı denetleyin. Endobronşiyal tek akciğer entübasyonu şüphesinde tüpü geri çekin. PEEP artırıp recruitment manevrası yapın.",
                        "Hipotansiyon: Derinliği azaltın, kristalloid bolus yapın. Nabız yüksekse Fenilefrin/Noradrenalin, nabız düşükse Efedrin titre edin.",
                        "Peak Basınç Yüksekliği: Tüpü katlanma, ısırma ve sekresyon yönünden kontrol edin, oral airway yerleştirip aspire edin. Bronkospazm varsa inhale Salbutamol verin.",
                        "ETCO₂ Sıfır: Tüpün yerini (akciğer sesleri ve kapnografiyle) teyit edin, özofagusta ise hemen ekstübe edip entübasyonu tekrarlayın."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("intraoperative_hypoxemia", "İntraoperatif Hipoksemi Algoritması"),
                        AiSuggestionItem("perioperative_hypotension", "İntraoperatif Hipotansiyon Algoritması"),
                        AiSuggestionItem("cico_crisis", "CICO Acil Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("map", "MAP Hesaplayıcı"),
                        AiSuggestionItem("difficult_airway", "Zor Havayolu Taraması")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("propofol", "Propofol Monografı")
                    ),
                    doNotMiss = listOf("Peak ve Plateau basınç farkının havayolu direncini yansıttığı klinik bilgisi", "İntraoperatif anafilaksinin dirençli hipotansiyon yapabileceği"),
                    whenToEscalate = listOf("Ciddi hava yolu tıkanıklığı veya dirençli hipoksemi durumlarında acil cerrahi havayolu planı"),
                    referencesToShow = listOf("ASA Standards for Basic Anesthetic Monitoring. American Society of Anesthesiologists, 2020.", "AAGBI Safety Guideline: High Airway Pressure during Mechanical Ventilation, 2016.")
                )
            }
            // 10. DRUG DOSING & REVERSALS / SUGAMMADEX / NEOSTIGMINE / NEUROMUSCULAR
            q.contains("doz") || q.contains("ilaçlar") || q.contains("sugammadeks") || q.contains("neostigmin") || q.contains("gevşetici") || q.contains("reversal") || q.contains("rokuronyum") || q.contains("süksinilkolin") -> {
                AiStructuredResponse(
                    safetyLevel = "routine",
                    notASubstituteWarning = "💊 NÖROMÜSKÜLER BLOKAJ VE GERİ DÖNDÜRME (REVERSAL) REHBERİ. Rezidüel blok postoperatif apnelere yol açar.",
                    immediateRedFlags = listOf(
                        "Ekstübasyon öncesi kantitatif TOF oranı < 0.90 ise rezidüel blok mevcuttur, hasta kesinlikle ekstübe edilmemelidir!",
                        "Sugammadeks, benzilizokinolyum grubu (Sisatrakuryum) kas gevşeticileri bağlamaz, bu grupta Neostigmin kullanılmalıdır."
                    ),
                    missingCriticalInformation = listOf(
                        "TOF monitöründeki TOF Count düzeyi (TOF count = 0 derin blok, TOF count >=2 yüzeysel blok)?",
                        "Kullanılan kas gevşeticinin türü (Roküronyum mu, Sisatrakuryum mu)?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Rezidüel Nöromüsküler Blokaj Yönetimi",
                        "Sugammadeks / Neostigmin Reversal Algoritması",
                        "TOF-Watch ve TOFscan Kantitatif Monitörizasyon Yorumlama"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Sugammadeks Dozu (Roküronyum/Veküronyum): TOF count 1-2 (yüzeysel) ise 2 mg/kg IV; TOF count 0 (derin, PTC >=1) ise 4 mg/kg IV bolus uygulayın.",
                        "Sugammadeks Acil Kurtarma Dozu: 1.2 mg/kg rokuronyum bolusundan hemen sonra acil geri döndürme için 16 mg/kg IV uygulayın.",
                        "Neostigmin Dozu: TOF count en az 2, tercihen 4 olduğunda Neostigmin (0.04-0.05 mg/kg) + Atropin (0.02 mg/kg) IV olarak uygulayın.",
                        "Süksinilkolin: RSI için 1.0-1.5 mg/kg IV verilir. Hiperkalemi, MH öyküsü ve psödokolinesteraz yetmezliğinde kontrendikedir."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("residual_neuromuscular_block", "Rezidüel Blok Algoritması"),
                        AiSuggestionItem("sugammadex_neostigmine_reversal", "Sugammadeks Reversal Kılavuzu")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("pediatric_dosing", "Pediatrik Doz Hesaplayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("sugammadex", "Sugammadeks Monografı"),
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı"),
                        AiSuggestionItem("rocuronium", "Roküronyum Monografı"),
                        AiSuggestionItem("cisatracurium", "Sisatrakuryum Monografı")
                    ),
                    doNotMiss = listOf("Neostigminin derin blokajda (TOF count = 0) etkisiz olduğu ve kas gevşemeyi artırabileceği uyarısı", "Muskarinik yan etkileri önlemek için Neostigmin ile Atropinin aynı enjektörde karıştırılması"),
                    whenToEscalate = listOf("Sugammadeks sonrası alerjik reaksiyon veya uzamış solunum yetmezliğinde mekanik ventilasyon desteğine devam"),
                    referencesToShow = listOf("Practice Guidelines for the Management of Neuromuscular Blockade. Anesthesiology 2023; 138(1):13-41. PMID: 36520073.")
                )
            }
            // 11. PACU / ALDRETE / PONV / APFEL / AĞRI / SHIVERING / DELIRYUM
            q.contains("pacu") || q.contains("derlenme") || q.contains("aldrete") || q.contains("ponv") || q.contains("apfel") || q.contains("bulantı") || q.contains("kusma") || q.contains("ağrı") || q.contains("titreme") || q.contains("deliryum") || q.contains("analjezi") -> {
                AiStructuredResponse(
                    safetyLevel = "routine",
                    notASubstituteWarning = "🏥 POSTOPERATİF DERLENME ODASI (PACU) VE AĞRI/BULANTI YÖNETİMİ. Güvenli taburculuk hedeflenir.",
                    immediateRedFlags = listOf(
                        "PACU'da solunum sayısı <10/dk veya SpO2 <90% ise aşırı opioid/rezidüel blok etkisi düşünülmelidir, derhal Nalokson veya Sugammadeks verin.",
                        "VAS skoru > 7 olan şiddetli ağrılı hastaları multimodal opioid titrasyonu yapmadan klinikten çıkarmayın."
                    ),
                    missingCriticalInformation = listOf(
                        "Hastanın Apfel skoru risk faktörleri sayısı (Kadın cinsiyet, sigara içmeme, PONV öyküsü, opioid kullanımı)?",
                        "Güncel Aldrete Skoru puanı (Aktivite, Solunum, Dolaşım, Bilinç, SpO2 - taburculuk için >= 9 olmalıdır)?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Postoperatif Bulantı-Kusma (PONV) Profilaksi ve Önleme",
                        "Multimodal Postoperatif Ağrı Yönetim Protokolleri",
                        "Aldrete Derlenme Skorlama Sistemi"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "PONV Profilaksisi: Yüksek riskli (Apfel 3-4) hastalarda indüksiyonda Deksametazon 4-8 mg IV + cerrahi bitiminde Ondansetron 4 mg IV uygulayın.",
                        "Ağrı Yönetimi: Hafif ağrı (VAS 1-3) için Parasetamol 1 g + NSAID; Orta ağrı (VAS 4-6) için zayıf opioidler (Tramadol); Şiddetli ağrı için Morfin titrasyonu.",
                        "PACU Titremesi: Isıtıcı battaniye kullanın, farmakolojik olarak Meperidin (Petidin) 10-25 mg IV bolus uygulayın.",
                        "Postoperatif Deliryum: Altta yatan nedenleri (hipoksi, mesane distansiyonu, ağrı) dışlayın, ajitasyon için düşük doz Dexmedetomidine planlayın."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("ponv_prevention", "PONV Önleme Algoritması"),
                        AiSuggestionItem("postop_pain_management", "Postoperatif Ağrı Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("gcs", "GCS Skorlayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("morphine", "Morfin Monografı")
                    ),
                    doNotMiss = listOf("Ondansetronun QT aralığını uzatabileceği uyarısı", "Opioid solunum depresyonunda Naloksonun 0.04 mg adımlarla yavaş titre edilmesi"),
                    whenToEscalate = listOf("Ciddi hava yolu tıkanıklığı veya solunum arrestinde PACU acil havayolu entübasyonu"),
                    referencesToShow = listOf("SAMBA Consensus Guidelines for the Management of Postoperative Nausea and Vomiting. Anesthesia & Analgesia 2020; 131(2):411-448. PMID: 32428612.", "ASA Postanesthetic Care Guidelines. Anesthesiology 2013.")
                )
            }
            // 12. ICU / SEPSIS / SHOCK / OLIGURIA / ACUTE KIDNEY INJURY / ARDS
            q.contains("yoğun bakım") || q.contains("sepsis") || q.contains("şok") || q.contains("oligüri") || q.contains("idrar") || q.contains("kreatinin") || q.contains("ards") || q.contains("pbw") || q.contains("weaning") || q.contains("septik") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "🏥 YOĞUN BAKIM VE KRİTİK HASTA YÖNETİMİ. Erken hemodinamik destek hayatta kalma oranını artırır.",
                    immediateRedFlags = listOf(
                        "Septik şokta 1 saat içinde geniş spektrumlu antibiyotik verilmemesi mortaliteyi doğrusal olarak artırır.",
                        "ARDS ventilasyonunda plato basıncının (Pplat) >30 cmH₂O veya sürüş basıncının >15 cmH₂O olması akciğer hasarı (VILI) riskidir."
                    ),
                    missingCriticalInformation = listOf(
                        "qSOFA skoru kriterleri mevcut mu (Solunum hızı >=22, mental durum değişikliği, SBP <=100)?",
                        "İntraoperatif idrar çıkışı miktarı (idrar çıkışı < 0.5 mL/kg/saat ise oligüri vardır)?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Surviving Sepsis Campaign 1. Saat Tedavi Paketi",
                        "ARDS Akciğer Koruyucu Ventilasyon (LTVT) Protokolü",
                        "İntraoperatif Oligüri ve Renal Perfüzyon Yönetimi"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Sepsis 1. Saat: Kan kültürleri alın, geniş spektrumlu antibiyotik başlayın, laktat ölçün. Tansiyon düşükse 30 mL/kg kristalloid sıvı yükleyin.",
                        "Septik Şok: Sıvıya dirençli şokta birinci tercih vazopressör olan Noradrenalin infüzyonuna santral yoldan hemen başlayın (Hedef OAB >=65 mmHg).",
                        "ARDS Ventilasyon: Tidal volümü PBW kilosuna göre 6 mL/kg ayarlayın. Pplat <30 cmH₂O ve PEEP titrasyonu ile atelektazileri engelleyin.",
                        "Oligüri: Sondada tıkanma/katlanma olmadığını doğrulayın. Hipovolemi varsa 250-500 mL kristalloid bolus yapın. Renal perfüzyon için OAB >70 mmHg tutun."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("sepsis_shock", "Sepsis & Şok Algoritması"),
                        AiSuggestionItem("ards_ventilation", "ARDS Koruyucu Ventilasyon"),
                        AiSuggestionItem("oliguria_management", "Oligüri Yönetim Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("maintenance_421", "Saatlik İdame Hesaplayıcı"),
                        AiSuggestionItem("map", "MAP Ortalama Arter Basıncı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("norepinephrine", "Noradrenalin Monografı")
                    ),
                    doNotMiss = listOf("Septik şokta diüretiklerin (furosemid) kontrendike olduğu, perfüzyonu bozabileceği uyarısı", "ARDSprone pozisyon ventilasyonu süresinin günlük en az 16 saat önerilmesi"),
                    whenToEscalate = listOf("Refrakter septik şok veya ağır ARDS hipoksemisinde ECMO (ekstrakorporal membran oksijenasyonu) endikasyonu"),
                    referencesToShow = listOf("Surviving Sepsis Campaign: International Guidelines for Management of Sepsis and Septic Shock 2021. Intensive Care Medicine 2021. PMID: 34599691.", "ARDSNet Protocols: Lower Tidal Volumes Ventilation. N Engl J Med 2000. PMID: 10793162.")
                )
            }
            // 13. OBSTETRICS / PREECLAMPSIA / PREGNANCY / SEZARYEN
            q.contains("gebe") || q.contains("sezaryen") || q.contains("preeklampsi") || q.contains("eklampsi") || q.contains("hellp") || q.contains("obstetrik") || q.contains("uterotonik") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "🤰 OBSTETRİK ANESTEZİ VE KRİTİK YÖNETİM. Hem anne hem de fetüsün fizyolojisi korunmalıdır.",
                    immediateRedFlags = listOf(
                        "Gebelikte solunum rezervleri çok azdır, desatürasyon çok hızlı gerçekleşir. İndüksiyonda preoksijenasyon kritik önem taşır.",
                        "Preeklampside trombosit sayısı < 75.000/mm³ ise veya hızla düşüyorsa spinal/epidural anestezi kontrendikedir!"
                    ),
                    missingCriticalInformation = listOf(
                        "Gebede sezaryen öncesi trombosit sayısı ve koagülasyon parametreleri?",
                        "Spinal blok sonrası gelişen hipotansiyonda nabız durumu (Efedrin vs Fenilefrin seçimi için)?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Obstetrik Zor Havayolu ve CICO Yönetim Protokolü",
                        "Spinal Anestezi Sonrası Hipotansiyon ve Sol Uterus Deplasmanı",
                        "Preeklampsi/Eklampsi Nöbeti ve Magnezyum Sülfat Tedavisi"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Gebede Zor Havayolu: Rampa pozisyonu uygulayın, krikoid bası altında Hızlı Ardışık İndüksiyon (RSI) yapın, deneme sayısını 2 ile sınırlayın.",
                        "Sezaryen Spinal Hipotansiyon: Aortokaval basıyı önlemek için masayı sola 15 derece eğin (sol uterus deplasmanı). Fenilefrin infüzyon/boluslarını birinci tercih yapın.",
                        "Preeklampsi/Eklampsi: Nöbet kontrolü için Magnezyum Sülfat 4-6 g IV bolus (15-20 dk), ardından 1-2 g/saat idame infüzyon uygulayın.",
                        "Postpartum Hemoraji: Oksitosin (yavaş IV), Metilergonovin (IM) veya Karboprost (IM) uygulayın. Oksitosin hızlı verilirse vazodilatasyon ve hipotansiyon yapar."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("obstetric_difficult_airway", "Obstetrik Zor Havayolu"),
                        AiSuggestionItem("high_spinal", "Yüksek/Total Spinal Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("difficult_airway", "Zor Havayolu Taraması")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı"),
                        AiSuggestionItem("phenylephrine", "Fenilefrin Monografı")
                    ),
                    doNotMiss = listOf("Magnezyum sülfat alan gebelerde kas gevşeticilerin (rokuronyum vb.) etkisinin uzayabileceği uyarısı", "Eklampsi nöbetinde fenitoin yerine MgSO4 verilmesi"),
                    whenToEscalate = listOf("Gebede kardiyak arrest durumunda 4-5 dakika içinde ROSC sağlanamazsa acil Perimortem Sezaryen (PMCD) kararı"),
                    referencesToShow = listOf("Practice Guidelines for Obstetric Anesthesia: An Updated Report by the ASA and SOAP. Anesthesiology 2016; 124(2):270-300. PMID: 26575101.", "OAA/DAS Obstetric Airway Guidelines. Anaesthesia 2015. PMID: 26477969.")
                )
            }
            // 14. PEDIATRICS / CHILD
            q.contains("çocuk") || q.contains("pediatrik") || q.contains("bebek") || q.contains("yenidoğan") || q.contains("pals") -> {
                AiStructuredResponse(
                    safetyLevel = "urgent",
                    notASubstituteWarning = "👶 PEDİATRİK ANESTEZİ VE HASSAS DOZLAMA. Çocukların fizyolojisi ve ilaç toleransları yetişkinlerden farklıdır.",
                    immediateRedFlags = listOf(
                        "Çocuklarda kalp hızı kalp debisinin temel belirleyicisidir. Bradikardi derhal tedavi edilmesi gereken kritik bir acildir!",
                        "Pediatrik havayolu dar ve huni şeklindedir. Subglottik ödem riskini önlemek için tüp çapı seçimi hassas yapılmalıdır."
                    ),
                    missingCriticalInformation = listOf(
                        "Çocuğun tam vücut ağırlığı (kilo) - tüm ilaç ve tüp çapı hesaplamalarının tek temelidir.",
                        "Çocuğun yaşı (tüp çapı formülü için gereklidir)?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Pediatrik Zor Havayolu Yönetimi Algoritması",
                        "Kiloya Göre Pediatrik Acil ve İndüksiyon Doz Hesaplamaları",
                        "Pediatrik Sıvı İdame ve 4-2-1 Kuralı Uygulanması"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Pediatrik Tüp Çapı: Kaflı tüp için = (Yaş / 4) + 3.5 mm; Kafsız tüp için = (Yaş / 4) + 4.0 mm formülü uygulanır.",
                        "Pediatrik Dozlar: Adrenalin (Resüsitasyon) = 10 mcg/kg (1:10000 seyreltilmiş solüsyondan 0.1 mL/kg). Atropin = 0.02 mg/kg IV.",
                        "Laringospazm: %100 O₂ ile CPAP uygulayın, Larson noktasına bası yapın. Çözülmezse Propofol bolus veya Süksinilkolin (0.5-1.0 mg/kg IV) verin.",
                        "Sıvı Gereksinimi: 4-2-1 kuralına göre saatlik idame hızını hesaplayın. Hipovolemi bolusu 10-20 mL/kg kristalloiddir."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("pediatric_difficult_airway", "Pediatrik Zor Havayolu"),
                        AiSuggestionItem("laryngospasm_crisis", "Laringospazm Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("pediatric_dosing", "Pediatrik Doz Hesaplayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("atropine", "Atropin Monografı"),
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı")
                    ),
                    doNotMiss = listOf("Pediatrik hastalarda ikinci doz Süksinilkolin yapılmadan önce mutlaka Atropin ön tedavisi verilmesi gerektiği", "Çocuklarda preoperatif açlık sürelerinin 2-4-6 saat olarak uygulanması"),
                    whenToEscalate = listOf("Pediatrik CICO durumunda KBB acil yardımı ile acil iğne/cerrahi krikotirotomi"),
                    referencesToShow = listOf("2020 AHA Guidelines for CPR and ECC: Part 4: Pediatric Basic and Advanced Life Support. Circulation 2020. PMID: 33081532.", "APAGBI Pediatric Anesthesia Guidelines, 2022.")
                )
            }
            // 15. BRONCHOSPASM
            q.contains("bronkospazm") || q.contains("hışıltı") || q.contains("wheezing") || q.contains("astım") -> {
                AiStructuredResponse(
                    safetyLevel = "emergency",
                    notASubstituteWarning = "🚨 İNTRAOPERATİF BRONKOSPAZM KRİZİ! Havayolu direnci masif artmıştır.",
                    immediateRedFlags = listOf(
                        "Pik havayolu basınçları > 40 cmH₂O üzerine çıkabilir, soluk verme eğrisi (kapnografi) 'köpekbalığı yüzgeci' şeklini alır.",
                        "Ciddi desatürasyon ve ventilasyon imkansızlığı kalp krizine yol açabilir."
                    ),
                    missingCriticalInformation = listOf(
                        "Hastanın bilinen astım veya KOAH öyküsü var mı?",
                        "Derin anestezi sağlandı mı yoksa yetersiz derinlik/uyarı kaynaklı laringeal refleks tetiklenmesi mi?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "İntraoperatif Bronkospazm Algoritması",
                        "Anafilaktik reaksiyonun solunumsal bulgusu",
                        "Endotrakeal tüp tıkanıklığı / kaf kayması ayırıcı tanısı"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Anesteziyi uçucu ajanlar veya Propofol bolusları ile derinleştirin (uçucu ajanlar güçlü bronkodilatördür).",
                        "Tüp içinden Salbutamol inhaler (4-8 puf) uygulayın.",
                        "Damar yolundan Magnezyum Sülfat (2 g IV, 20 dakikada) yavaşça infüze edin.",
                        "Dirençli vakalarda bronkodilatör etkisi nedeniyle Ketamin bolusu (0.5-1.0 mg/kg) veya Adrenalin IV bolus (10-20 mcg) düşünün.",
                        "Ventilatörde soluk verme süresini uzatın (I:E oranını 1:3 veya 1:4 yapın) hava hapsini (auto-PEEP) önlemek için."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("intraoperative_hypoxemia", "Hipoksemi Algoritması"),
                        AiSuggestionItem("anaphylaxis_rescue", "Anafilaksi Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("difficult_airway", "Zor Havayolu Taraması")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("ketamine", "Ketamin Monografı"),
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı")
                    ),
                    doNotMiss = listOf("Bronkospazm sırasında körlemesine yüksek PEEP vermekten kaçınmak (auto-PEEP ve tansiyon düşüşü riski)", "Tüpün aspire edilerek sekresyon tıkanıklığının ekarte edilmesi"),
                    whenToEscalate = listOf("Refrakter yaşamı tehdit eden bronkospazmda yoğun bakım transferi ve sürekli inhaler infüzyonları"),
                    referencesToShow = listOf("Global Strategy for Asthma Management and Prevention. Global Initiative for Asthma (GINA) Report 2023.", "TARD Astım Yönetim Kılavuzu, 2020.")
                )
            }
            // 16. SHIVERING / POSTOP SHIVERING
            q.contains("shivering") || q.contains("titreme") || q.contains("titriyor") || q.contains("postop titreme") -> {
                AiStructuredResponse(
                    safetyLevel = "routine",
                    notASubstituteWarning = "❄️ POSTOPERATİF TİTREME YÖNETİMİ. Titreme oksijen tüketimini %400'e kadar artırabilir.",
                    immediateRedFlags = listOf(
                        "Koroner arter hastalarında titremenin yarattığı masif oksijen tüketim artışı miyokard iskemisini (ST çökmesi) tetikleyebilir.",
                        "Titremeye eşlik eden ciddi hipotermi (vücut sıcaklığı <35°C) koagülopatiyi ve yara yeri enfeksiyonlarını artırır."
                    ),
                    missingCriticalInformation = listOf(
                        "Hastanın güncel vücut sıcaklığı (çekirdek ısı)?",
                        "Hastada bilinen koroner arter hastalığı veya iskemi öyküsü var mı?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Anestezik ajanların termoregülasyon baskılaması ve uyanma titremesi",
                        "İntraoperatif Hipoterminin Postoperatif Yansımaları"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Hastayı aktif sıcak hava üfleyen battaniyelerle (Bair Hugger) dışarıdan ısıtın.",
                        "Farmakolojik Tedavi (Altın Standart): **Meperidin (Petidin)** 10-25 mg IV bolus uygulayın. Titremeyi dakikalar içinde durdurur.",
                        "Alternatif İlaçlar: Klonidin 75-150 mcg IV veya Tramadol 50 mg IV verilebilir."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("postop_pain_management", "Ağrı Yönetim Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("gcs", "GCS Skorlayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("morphine", "Morfin Monografı")
                    ),
                    doNotMiss = listOf("Meperidinin MAO inhibitörleri kullanan hastalarda kontrendike olduğu uyarısı", "Isıtılmamış soğuk IV sıvıların titremeyi artırabileceği"),
                    whenToEscalate = listOf("Ciddi hipotermi ve dirençli titreme durumunda hastanın derleme odasında daha uzun süre izlenmesi ve ısıtılması"),
                    referencesToShow = listOf("Practice Advisory for the Prevention, Detection, and Management of Perioperative Hypothermia. Anesthesiology 2016; 125(6):1093-1111. PMID: 27869680.")
                )
            }
            // 17. ELECTROLYTES / HYPERKALEMIA / HYPOGLYCEMIA
            q.contains("potasyum") || q.contains("hiperkalemi") || q.contains("hipoglisemi") || q.contains("elektrolit") || q.contains("kalsiyum") || q.contains("şeker") -> {
                AiStructuredResponse(
                    safetyLevel = "urgent",
                    notASubstituteWarning = "🧪 METABOLİK VE ELEKTROLİT KRİZLERİ YÖNETİMİ. Akut bozukluklar ölümcül aritmilere yol açar.",
                    immediateRedFlags = listOf(
                        "Plazma Potasyumu (K+) > 6.0 mEq/L acil hiperkalemidir; EKG'de sivri T dalgaları, QRS genişlemesi ve asistoli riski taşır!",
                        "Glukoz < 50 mg/dL acil hipoglisemidir; anestezi altında bilinç kapalı olduğu için belirti vermez ve kalıcı nörolojik hasar yapabilir."
                    ),
                    missingCriticalInformation = listOf(
                        "Hiperkalemide EKG bulguları mevcut mu (sivri T dalgaları, PR uzaması)?",
                        "Hipoglisemik hastanın anestezi öncesi insülin alım öyküsü ve mevcut kan şekeri değeri?"
                    ),
                    likelyClinicalCategoriesToConsider = listOf(
                        "Akut Hiperkalemi Acil Tedavi Protokolü",
                        "İntraoperatif Hipoglisemi ve Kan Şekeri Kontrolü",
                        "Masif Transfüzyon Kaynaklı Hipokalsemi (Kalsiyum replasmanı)"
                    ),
                    suggestedNextStepsGeneral = listOf(
                        "Hiperkalemi Acil Tedavisi: \n1. Miyokardı korumak için 1 ampul Kalsiyum Glukonat veya Kalsiyum Klorür IV olarak yavaşça uygulayın.\n2. Potasyumu hücre içine sokmak için: 10 Ünite Regüler İnsülin + 50 mL %50 Dekstroz IV infüzyon (30 dk) yapın.\n3. Sodyum Bikarbonat 1 mEq/kg IV ve inhale Salbutamol uygulayın.",
                        "Hipoglisemi Tedavisi: 100 mL %10 Dekstroz veya 20-40 mL %20 Dekstroz IV bolus uygulayın. Kan şekerini 15 dakikada bir kontrol edin."
                    ),
                    relatedAlgorithms = listOf(
                        AiSuggestionItem("sepsis_shock", "Sepsis & Şok Algoritması"),
                        AiSuggestionItem("rotem_coagulation", "ROTEM Koagülasyon Algoritması")
                    ),
                    relatedCalculators = listOf(
                        AiSuggestionItem("maintenance_421", "Saatlik İdame Hesaplayıcı")
                    ),
                    relatedDrugCards = listOf(
                        AiSuggestionItem("succinylcholine", "Süksinilkolin Monografı - Hiperkalemide KONTRENDİKEDİR!")
                    ),
                    doNotMiss = listOf("Hiperkalemili hastalarda Süksinilkolin kullanımının KESİNLİKLE kontrendike olduğu", "Kalsiyum klorürün periferik damardan kaçarsa doku nekrozu riski"),
                    whenToEscalate = listOf("Dirençli asidoz ve hiperkalemide acil diyaliz veya hemodiyaliz ihtiyacı"),
                    referencesToShow = listOf("ASA Standards for Patient Care, 2020.", "Endocrine Society Clinical Practice Guidelines on Adrenal Insufficiency and Diabetes Management. J Clin Endocrinol Metab 2016.")
                )
            }
            else -> {
                // Default fallback response
                AiStructuredResponse(
                    safetyLevel = safety,
                    notASubstituteWarning = "Bu yanıt klinik kararın yerine geçmez. Klinik değerlendirmenizi temel alın.",
                    immediateRedFlags = listOf("Klinik bulguların stabilizasyonu önceliklidir."),
                    missingCriticalInformation = listOf("Hasta yaşı, kilosu ve bilinen ek hastalıklar"),
                    likelyClinicalCategoriesToConsider = listOf("Anestezi yönetimi, havayolu kontrolü ve analjezi titrasyonu"),
                    suggestedNextStepsGeneral = listOf(
                        "Vakayı hemodinami ve solunum yönünden monitörize edin.",
                        "İlaç kılavuzlarından uygun dozları doğrulayın."
                    ),
                    relatedAlgorithms = emptyList(),
                    relatedCalculators = listOf(AiSuggestionItem("bmi", "Kilo / BMI Hesaplayıcı")),
                    relatedDrugCards = listOf(AiSuggestionItem("propofol", "Propofol Monografı")),
                    doNotMiss = listOf("Alerji hikayesi", "Zor havayolu öngörücüleri"),
                    whenToEscalate = listOf("Hemodinamik instabilite geliştiğinde"),
                    referencesToShow = listOf("ASA Standards of Basic Anesthetic Monitoring. American Society of Anesthesiologists Standards, 2020.")
                )
            }
        }
    }

    fun translateResponseToEnglish(resp: AiStructuredResponse): AiStructuredResponse {
        return resp.copy(
            notASubstituteWarning = TranslationHelper.translate(resp.notASubstituteWarning, "en"),
            conversationalText = resp.conversationalText?.let { TranslationHelper.translate(it, "en") },
            immediateRedFlags = resp.immediateRedFlags.map { TranslationHelper.translate(it, "en") },
            missingCriticalInformation = resp.missingCriticalInformation.map { TranslationHelper.translate(it, "en") },
            likelyClinicalCategoriesToConsider = resp.likelyClinicalCategoriesToConsider.map { TranslationHelper.translate(it, "en") },
            suggestedNextStepsGeneral = resp.suggestedNextStepsGeneral.map { TranslationHelper.translate(it, "en") },
            doNotMiss = resp.doNotMiss.map { TranslationHelper.translate(it, "en") },
            whenToEscalate = resp.whenToEscalate.map { TranslationHelper.translate(it, "en") },
            referencesToShow = resp.referencesToShow.map { TranslationHelper.translate(it, "en") },
            relatedAlgorithms = resp.relatedAlgorithms.map { it.copy(titleTr = TranslationHelper.translate(it.titleTr, "en")) },
            relatedCalculators = resp.relatedCalculators.map { it.copy(titleTr = TranslationHelper.translate(it.titleTr, "en")) },
            relatedDrugCards = resp.relatedDrugCards.map { it.copy(titleTr = TranslationHelper.translate(it.titleTr, "en")) }
        )
    }

    fun sendMessage(customPrompt: String? = null) {
        val originalText = customPrompt ?: userText
        if (originalText.isBlank()) return

        val isUserQueryEnglish = isInputEnglish(originalText)

        userText = ""
        isGenerating = true

        // 1. Client-Side Anonymization Check
        val (anonymizedInput, foundPatientData) = anonymizeText(originalText)

        // 2. Add user message
        messages.add(
            Message(
                id = messages.size,
                role = "user",
                content = anonymizedInput
            )
        )

        coroutineScope.launch {
            // Scroll to bottom
            listState.animateScrollToItem(messages.size - 1)

            var response: AiStructuredResponse? = null
            var apiError = false
            var isPremiumResponse = false

            if (isOnlineMode && apiKey.isNotEmpty()) {
                response = callGeminiApi(messages, apiKey, isUserQueryEnglish)
                if (response == null) {
                    apiError = true
                }
            }

            if (response == null) {
                // Simulate Network Delay
                kotlinx.coroutines.delay(1000)
                val premium = com.anesthesiaclinic.anesthesiabriefs.data.repository.SeedDataAiResponses.getStructuredResponse(anonymizedInput, isUserQueryEnglish)
                if (premium != null) {
                    response = premium
                    isPremiumResponse = true
                } else {
                    response = generateDeterministicAiResponse(anonymizedInput)
                }
            }

            if (isUserQueryEnglish && response != null && !isPremiumResponse) {
                response = translateResponseToEnglish(response)
            }

            val updatedResponse = response!!.copy(patientIdentifierDetected = foundPatientData)

            val assistantContent = when {
                foundPatientData -> {
                    if (!isUserQueryEnglish) {
                        "⚠️ UYARI: Hastayı tanımlayıcı bilgi (T.C. Kimlik / Tel No / Dosya No) tespit edildi ve maskelendi. Güvenlik gereği hasta bilgisi girmeyiniz.\n\nVaka analiziniz aşağıda yapılandırılmıştır:"
                    } else {
                        "⚠️ WARNING: Patient identifying information (ID / Phone / File No) was detected and masked. Please do not enter patient details for security.\n\nYour case analysis is structured below:"
                    }
                }
                !isOnlineMode -> {
                    if (!isUserQueryEnglish) {
                        "ℹ️ Çevrimdışı Mod — Yerel veritabanı yanıtı:"
                    } else {
                        "ℹ️ Offline Mode — Local database response:"
                    }
                }
                apiError -> {
                    if (!isUserQueryEnglish) {
                        "⚠️ Bağlantı yok — çevrimdışı moda geçildi. Yerel çevrimdışı motor yanıtı gösteriliyor:"
                    } else {
                        "⚠️ No connection — switched to offline mode. Showing local offline engine response:"
                    }
                }
                apiKey.isNotEmpty() -> {
                    if (!isUserQueryEnglish) {
                        "🤖 Canlı Gemini AI tarafından gerçek zamanlı analiz edildi:\n\nVaka analiziniz klinik standartlara göre analiz edilmiştir:"
                    } else {
                        "🤖 Analyzed in real-time by Live Gemini AI:\n\nYour case analysis has been structured according to clinical standards:"
                    }
                }
                else -> {
                    if (!isUserQueryEnglish) {
                        "Vakanız klinik standartlara göre analiz edilmiştir:"
                    } else {
                        "Your case has been analyzed according to clinical standards:"
                    }
                }
            }

            messages.add(
                Message(
                    id = messages.size,
                    role = "assistant",
                    content = assistantContent,
                    structuredResponse = updatedResponse
                )
            )

            isGenerating = false
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isTurkish) "Ask AI: Anestezi Karar Destek" else "Ask AI: Anesthesia Decision Support", style = MaterialTheme.typography.titleLarge, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = if (isTurkish) "Geri" else "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
            )
        }
    ) { paddingValues ->
        // Legally Binding Disclaimer Consent Dialog Modal
        if (showConsentModal) {
            Dialog(
                onDismissRequest = { showConsentModal = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize().systemBarsPadding(),
                    color = WarmSand
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = t(
                                "⚠️ ÇEVRİMİÇİ YAPAY ZEKA MODU — KULLANIM KOŞULLARI VE SORUMLULUK REDDİ",
                                "⚠️ ONLINE AI MODE — TERMS OF USE AND DISCLAIMER"
                            ),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .background(Color.White, RoundedCornerShape(12.dp))
                                .border(BorderStroke(1.dp, BorderSand), RoundedCornerShape(12.dp))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = t("Bilgilendirme Amaçlıdır:", "For Informational Purposes Only:"),
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy,
                                fontSize = 13.sp
                            )
                            Text(
                                text = t(
                                    "Bu yapay zeka asistanı yalnızca genel bilgilendirme ve eğitim amaçlıdır. Sağladığı yanıtlar tıbbi tavsiye, tanı veya tedavi niteliği taşımaz ve hekimin bağımsız klinik değerlendirmesinin, muayenesinin veya profesyonel yargısının yerine geçmez.",
                                    "This AI assistant is intended solely for general informational and educational purposes. Its responses do not constitute medical advice, diagnosis, or treatment, and do not replace the independent clinical assessment, examination, or professional judgment of a physician."
                                ),
                                color = TextPrimaryLight,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            Text(
                                text = t("Doğruluk Garantisi Yoktur:", "No Guarantee of Accuracy:"),
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy,
                                fontSize = 13.sp
                            )
                            Text(
                                text = t(
                                    "Yanıtlar yapay zeka tarafından otomatik olarak üretilmektedir ve hatalı, eksik veya güncel olmayan bilgi içerebilir. Tüm dozaj, ilaç ve klinik bilgileri kullanan sağlık profesyoneli, güncel kılavuzlar ve onaylı kaynaklar üzerinden bağımsız olarak doğrulamakla yükümlüdür.",
                                    "Responses are automatically generated by artificial intelligence and may contain inaccurate, incomplete, or outdated information. The healthcare professional using this application is responsible for independently verifying all dosing, drug, and clinical information against current guidelines and approved sources."
                                ),
                                color = TextPrimaryLight,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            Text(
                                text = t("Hasta Verisi Girmeyiniz:", "Do Not Enter Patient Data:"),
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy,
                                fontSize = 13.sp
                            )
                            Text(
                                text = t(
                                    "Bu alana hasta kimlik bilgisi (ad-soyad, T.C. kimlik numarası, dosya/protokol numarası, doğum tarihi veya kişiyi tanımlayabilecek diğer bilgiler) girmeyiniz. Sorularınızı kimliksiz ve genel klinik formatta iletiniz. Girilen veriler işlenmek üzere yurt dışındaki sunuculara aktarılabilir.",
                                    "Do not enter patient identifiers (name, national ID, file/record number, date of birth, or any other identifying information) in this field. Submit your questions in a de-identified, general clinical format. Entered data may be transferred to servers located abroad for processing."
                                ),
                                color = TextPrimaryLight,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            Text(
                                text = t("Nihai Sorumluluk Kullanıcıdadır:", "Final Responsibility Rests with the User:"),
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy,
                                fontSize = 13.sp
                            )
                            Text(
                                text = t(
                                    "Klinik karar ve uygulamadan doğacak tüm sorumluluk, ilgili sağlık profesyoneline aittir. Geliştirici, bu asistanın kullanımından doğabilecek doğrudan veya dolaylı hiçbir zarardan sorumlu tutulamaz.\n\nDevam ederek bu koşulları okuduğunuzu ve kabul ettiğinizi beyan edersiniz.",
                                    "All responsibility arising from clinical decisions and their application rests with the relevant healthcare professional. The developer cannot be held liable for any direct or indirect harm arising from the use of this assistant.\n\nBy continuing, you confirm that you have read and accepted these terms."
                                ),
                                color = TextPrimaryLight,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { showConsentModal = false },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepNavy),
                                border = BorderStroke(1.dp, BorderSand)
                            ) {
                                Text(t("Vazgeç", "Cancel"), fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = {
                                    acceptOnlineDisclaimer()
                                    showConsentModal = false
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1.5f)
                                    .height(48.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal)
                            ) {
                                Text(t("Okudum ve kabul ediyorum", "I have read and accept"), fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WarmSand)
                .padding(paddingValues)
        ) {
            // Static Safety Disclaimer Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFFDE7))
                    .border(1.dp, AlertAmber.copy(alpha = 0.3f))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = AlertAmber, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isTurkish) {
                            "⚠️ Yapay Zeka kararları yalnızca klinik eğitim ve hızlı danışma amaçlıdır. Klinik kararlarda nihai sorumluluk hekime aittir. Hasta kimlik verisi girmeyiniz."
                        } else {
                            "⚠️ AI decisions are for clinical education and quick reference only. Ultimate responsibility for clinical decisions lies with the clinician. Do not enter patient identifying data."
                        },
                        fontSize = 10.sp,
                        color = TextPrimaryLight,
                        lineHeight = 13.sp
                    )
                }
            }

            // Mode Selector Toggle Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .border(BorderStroke(1.dp, BorderSand), RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (!isOnlineMode) DeepNavy else Color.Transparent)
                        .clickable { isOnlineMode = false }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = t("Çevrimdışı (Yerel)", "Offline (Local)"),
                        color = if (!isOnlineMode) Color.White else TextPrimaryLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isOnlineMode) DeepNavy else Color.Transparent)
                        .clickable {
                            if (!hasAcceptedOnlineDisclaimer) {
                                showConsentModal = true
                            } else {
                                isOnlineMode = true
                            }
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = t("Çevrimiçi (AI)", "Online (AI)"),
                        color = if (isOnlineMode) Color.White else TextPrimaryLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            // Chat Messages List
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages) { msg ->
                    if (msg.role == "user") {
                        UserMessageBubble(text = msg.content)
                    } else {
                        AssistantMessageBubble(
                            currentLanguage = currentLanguage,
                            text = msg.content,
                            structured = msg.structuredResponse,
                            onSelectDrug = onSelectDrug,
                            onSelectCalculator = onSelectCalculator,
                            onSelectAlgorithm = onSelectAlgorithm,
                            onFollowUpClick = { sendMessage(it) }
                        )
                    }
                }
                if (isGenerating) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(8.dp), contentAlignment = Alignment.CenterStart) {
                            Text(
                                text = if (isTurkish) "Asistan klinik verileri analiz ediyor, lütfen bekleyin..." else "Assistant is analyzing clinical data, please wait...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = ClinicalTeal
                            )
                        }
                    }
                }
            }

            // 🔗 Ask AI: Anesthesia Decision Support Panel (collapsible premium Prompts)
            if (showQuickPrompts) {
                Surface(
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp,
                    color = WarmSand,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (isTurkish) "🩺 Ask AI: Karar Destek Hızlı Promtları (Premium)" else "🩺 Ask AI: Decision Support Quick Prompts (Premium)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )
                            IconButton(
                                onClick = { showQuickPrompts = false },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = TextSecondaryLight,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }

                        val categoriesToUse = if (isTurkish) premiumCategories else premiumCategoriesEn

                    // Category Selector Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        categoriesToUse.keys.forEach { cat ->
                            val isSel = activeCategory == cat
                            FilterChip(
                                selected = isSel,
                                onClick = { activeCategory = cat },
                                label = { Text(cat, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = ClinicalTeal,
                                    selectedLabelColor = Color.White,
                                    containerColor = Linen,
                                    labelColor = TextPrimaryLight
                                )
                            )
                        }
                    }

                    // Questions shelf for active category
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val questions = categoriesToUse[activeCategory] ?: emptyList()
                        questions.forEach { qObj ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Linen)
                                    .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
                                    .clickable { sendMessage(qObj.prompt) }
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                                    .widthIn(max = 240.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.HelpOutline, 
                                        contentDescription = null, 
                                        tint = ClinicalTeal, 
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = qObj.title, 
                                        fontSize = 10.sp, 
                                        color = DeepNavy, 
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
            }

            // Input Send Bar
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = Linen,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .imePadding()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = userText,
                            onValueChange = { if (it.length <= 2000) userText = it },
                            placeholder = { Text(if (isTurkish) "Vaka detaylarını veya sorunuzu yazın..." else "Type case details or your question...", color = TextSecondaryLight, fontSize = 13.sp) },
                            maxLines = 4,
                            modifier = Modifier
                                .weight(1f)
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) {
                                        coroutineScope.launch {
                                            if (messages.isNotEmpty()) {
                                                listState.animateScrollToItem(messages.size - 1)
                                            }
                                        }
                                    }
                                },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedTextColor = TextPrimaryLight,
                                unfocusedTextColor = TextPrimaryLight,
                                focusedBorderColor = ClinicalTeal,
                                unfocusedBorderColor = BorderSand
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { sendMessage() },
                            enabled = userText.isNotBlank() && !isGenerating,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (userText.isNotBlank()) DeepNavy else BorderSand)
                        ) {
                            Icon(Icons.Default.Send, contentDescription = if (isTurkish) "Gönder" else "Send", tint = Color.White)
                        }
                    }

                    if (!isOnlineMode) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Linen)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = t(
                                    "ℹ️ Çevrimdışı Mod — Yanıtlar kılavuz tabanlı (ASA, ERC, ASRA) yerel veritabanından üretilir. İnternet gerektirmez.",
                                    "ℹ️ Offline Mode — Responses are generated from a guideline-based (ASA, ERC, ASRA) local database. No internet required."
                                ),
                                color = TextSecondaryLight,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFFEF3C7))
                                .border(BorderStroke(0.5.dp, Color(0xFFF59E0B)))
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = t(
                                    "⚠️ AI üretimi yanıt — Hasta kimlik bilgisi girmeyin. Önerileri klinik kararınızla doğrulayın.",
                                    "⚠️ AI-generated response — Do not enter patient identifiers. Verify suggestions with your clinical judgment."
                                ),
                                color = Color(0xFF78350F),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun UserMessageBubble(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp))
                .background(DeepNavy)
                .padding(12.dp)
        ) {
            Text(text, color = Color.White, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun AssistantMessageBubble(
    currentLanguage: String,
    text: String,
    structured: AiStructuredResponse?,
    onSelectDrug: (String) -> Unit,
    onSelectCalculator: (String) -> Unit,
    onSelectAlgorithm: (String) -> Unit,
    onFollowUpClick: (String) -> Unit
) {
    val isMsgEnglish = if (structured != null) {
        val warning = structured.notASubstituteWarning.lowercase()
        warning.contains("this") || warning.contains("judgment") || warning.contains("replace") || warning.contains("not a substitute")
    } else {
        val content = text.lowercase()
        if (content.contains("offline mode") || content.contains("real-time") || content.contains("warning") || content.contains("switched to offline")) {
            true
        } else if (content.contains("çevrimdışı mod") || content.contains("canlı gemini") || content.contains("uyari") || content.contains("bağlantı yok")) {
            false
        } else {
            currentLanguage == "en"
        }
    }
    val isTurkish = !isMsgEnglish
    val showConversational = structured != null && !structured.conversationalText.isNullOrBlank()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp))
                .background(Linen)
                .border(1.dp, BorderSand, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = SoftGold, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isTurkish) "AI Asistan" else "AI Assistant",
                    style = MaterialTheme.typography.labelLarge,
                    color = ClinicalTeal,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            if (showConversational) {
                // If patient info mask warning was triggered, display it as a subtle warning note first
                if (text.contains("⚠️ UYARI") || text.contains("⚠️ WARNING")) {
                    val warningPart = text.substringBefore("\n\n")
                    if (warningPart.startsWith("⚠️ UYARI") || warningPart.startsWith("⚠️ WARNING")) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = CriticalRed.copy(alpha = 0.05f)),
                            border = BorderStroke(1.dp, CriticalRed.copy(alpha = 0.2f)),
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Text(
                                text = warningPart,
                                fontSize = 11.sp,
                                color = CriticalRed,
                                modifier = Modifier.padding(8.dp),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                
                // Conversational plain-text response
                Text(
                    text = structured!!.conversationalText!!,
                    color = TextPrimaryLight,
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text(text, color = TextPrimaryLight, style = MaterialTheme.typography.bodyMedium)

                // Dynamic Structured Response Sections
                if (structured != null) {
                    Spacer(modifier = Modifier.height(12.dp))

                    // Safety Level Indicator Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                when (structured.safetyLevel) {
                                    "emergency" -> CriticalRed
                                    "urgent" -> AlertAmber
                                    else -> SafeGreen
                                }
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (isTurkish) "GÜVENLİK DÜZEYİ: ${structured.safetyLevel.uppercase()}" else "SAFETY LEVEL: ${structured.safetyLevel.uppercase()}",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 🚨 Red Flags
                    if (structured.immediateRedFlags.isNotEmpty()) {
                        ExpandableClinicianSection(if (isTurkish) "🚩 KIRMIZI BAYRAKLAR (KRİTİK RİSKLER)" else "🚩 RED FLAGS (CRITICAL RISKS)", CriticalRed) {
                            structured.immediateRedFlags.forEach { flag ->
                                Text("• $flag", color = CriticalRed, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // 📋 Missing Information
                    if (structured.missingCriticalInformation.isNotEmpty()) {
                        ExpandableClinicianSection(if (isTurkish) "📋 EKSİK KLİNİK BİLGİLER" else "📋 MISSING CLINICAL INFORMATION", DeepNavy) {
                            structured.missingCriticalInformation.forEach { info ->
                                Text("• $info", color = TextPrimaryLight, fontSize = 12.sp, modifier = Modifier.padding(vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // 📊 Suggested Actions
                    if (structured.suggestedNextStepsGeneral.isNotEmpty()) {
                        ExpandableClinicianSection(if (isTurkish) "📊 KLİNİK ÖNERİ AKIŞI" else "📊 CLINICAL ACTION FLOW", ClinicalTeal) {
                            structured.suggestedNextStepsGeneral.forEachIndexed { idx, step ->
                                Text("${idx + 1}. $step", color = TextPrimaryLight, fontSize = 12.sp, modifier = Modifier.padding(vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // ⛔ Do Not Miss
                    if (structured.doNotMiss.isNotEmpty()) {
                        ExpandableClinicianSection(if (isTurkish) "⛔ GÖZDEN KAÇIRMAMA UYARISI" else "⛔ CRITICAL DO-NOT-MISS WARNING", AlertAmber) {
                            structured.doNotMiss.forEach { item ->
                                Text("• $item", color = AlertAmber, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // ⚠️ Escalation
                    if (structured.whenToEscalate.isNotEmpty()) {
                        ExpandableClinicianSection(if (isTurkish) "⚠️ SEVK / ESKALASYON KRİTERLERİ" else "⚠️ ESCALATION / REFERRAL CRITERIA", CriticalRed) {
                            structured.whenToEscalate.forEach { item ->
                                Text("• $item", color = CriticalRed, fontSize = 12.sp, modifier = Modifier.padding(vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // 📚 References
                    if (structured.referencesToShow.isNotEmpty()) {
                        ExpandableClinicianSection(if (isTurkish) "📚 AKADEMİK REFERANSLAR" else "📚 ACADEMIC REFERENCES", DeepNavy) {
                            structured.referencesToShow.forEach { ref ->
                                Text("• $ref", color = ClinicalTeal, fontSize = 11.sp, modifier = Modifier.padding(vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // 🔗 Related Modules
                    if (structured.relatedAlgorithms.isNotEmpty() || structured.relatedCalculators.isNotEmpty() || structured.relatedDrugCards.isNotEmpty()) {
                        Text(
                            text = if (isTurkish) "🔗 İlişkili Modüller (Doğrudan Geçiş):" else "🔗 Related Modules (Direct Navigation):",
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            structured.relatedAlgorithms.forEach { algo ->
                                val title = if (isTurkish) algo.titleTr else TranslationHelper.translate(algo.titleTr, "en")
                                ModuleButton(title, CriticalRed) { onSelectAlgorithm(algo.id) }
                            }
                            structured.relatedCalculators.forEach { calc ->
                                val title = if (isTurkish) calc.titleTr else TranslationHelper.translate(calc.titleTr, "en")
                                ModuleButton(title, ClinicalTeal) { onSelectCalculator(calc.id) }
                            }
                            structured.relatedDrugCards.forEach { drug ->
                                val title = if (isTurkish) drug.titleTr else TranslationHelper.translate(drug.titleTr, "en")
                                ModuleButton(title, SoftGold) { onSelectDrug(drug.id) }
                            }
                        }
                    }

                    if (structured.followUpQuestions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (isTurkish) "💡 Önerilen Takip Soruları:" else "💡 Suggested Follow-up Questions:",
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            structured.followUpQuestions.forEach { q ->
                                Card(
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    border = BorderStroke(1.dp, BorderSand),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onFollowUpClick(q) }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.QuestionAnswer,
                                            contentDescription = null,
                                            tint = ClinicalTeal,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = q,
                                            color = DeepNavy,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandableClinicianSection(
    title: String,
    headerColor: Color,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 10.sp, color = headerColor)
            Icon(
                if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = headerColor,
                modifier = Modifier.size(14.dp)
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun ModuleButton(
    title: String,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color.copy(alpha = 0.15f), contentColor = color),
        border = BorderStroke(1.dp, color),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
        modifier = Modifier.height(30.dp)
    ) {
        Text(title, fontSize = 9.sp, fontWeight = FontWeight.Bold)
    }
}
