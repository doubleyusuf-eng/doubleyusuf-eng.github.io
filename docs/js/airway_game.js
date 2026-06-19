/* -------------------------------------------------------------
   Anesthesia Briefs - Difficult Airway Escape Game Logic
   Author: Antigravity AI Pair Programmer
   Features: Real-time Web Audio Synthesizer, Live Canvas EKG Wave, 
             ASA difficult airway algorithm simulation, Cookie fallback score saver.
------------------------------------------------------------- */

// Global Game Configuration & State
let gameLanguage = 'tr';
let gameState = 'menu'; // 'menu', 'playing', 'gameover', 'victory'
let currentCase = 1;
let currentNodeId = 'start';
let playerNick = 'Anestezist';
let playerCountry = 'TR';
let score = 0;
let spO2 = 99;
let heartRate = 75;
let etCO2 = 38;
let bloodPressure = '120/80';
let traumaLevel = 0; // 0 to 100%
let selectedTool = null; // Currently selected cart tool
let activeCartTab = 'laryngoscopes';

// Game Timing
let gameTimerInterval = null;
let elapsedSeconds = 0;
let spO2Target = 99;
let hrTarget = 75;

// Audio Context for Pulse Oximeter Tone & Alarms
let audioCtx = null;
let soundEnabled = true;
let nextBeepTime = 0;

// Canvas Visuals
let ekgCanvas = null;
let ekgCtx = null;
let etco2Canvas = null;
let etco2Ctx = null;
let animFrameId = null;
let ekgX = 0;
let etco2X = 0;

// Persistent Cookie Helpers for iOS/In-App WebView Redundancy
function saveLeaderboardToCookie(list) {
    try {
        const jsonStr = JSON.stringify(list);
        const b64 = btoa(unescape(encodeURIComponent(jsonStr)));
        const expiry = new Date();
        expiry.setFullYear(expiry.getFullYear() + 5); // 5 years persistence
        document.cookie = `ab_airway_leaderboard=${b64}; expires=${expiry.toUTCString()}; path=/; SameSite=Strict`;
    } catch (e) {
        console.error("Leaderboard cookie save failed", e);
    }
}

function loadLeaderboardFromCookie() {
    try {
        const decodedCookie = decodeURIComponent(document.cookie);
        const ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf("ab_airway_leaderboard=") === 0) {
                const b64 = c.substring("ab_airway_leaderboard=".length, c.length);
                const jsonStr = decodeURIComponent(escape(atob(b64)));
                return JSON.parse(jsonStr);
            }
        }
    } catch (e) {
        console.error("Leaderboard cookie load failed", e);
    }
    return null;
}

function saveValueToCookie(key, value) {
    try {
        const expiry = new Date();
        expiry.setFullYear(expiry.getFullYear() + 5);
        document.cookie = `${key}=${encodeURIComponent(value)}; expires=${expiry.toUTCString()}; path=/; SameSite=Strict`;
    } catch (e) {
        console.error("Value cookie save failed", e);
    }
}

function getValueFromCookie(key) {
    try {
        const decodedCookie = decodeURIComponent(document.cookie);
        const ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf(key + "=") === 0) {
                return decodeURIComponent(c.substring(key.length + 1, c.length));
            }
        }
    } catch (e) {
        console.error("Value cookie load failed", e);
    }
    return null;
}

// Leaderboard Database Logic
let leaderboard = [];

function initLeaderboard() {
    const localStored = localStorage.getItem('ab_airway_leaderboard');
    const cookieStored = loadLeaderboardFromCookie();
    
    if (localStored) {
        leaderboard = JSON.parse(localStored);
    }
    
    // Sync localstorage and cookie
    if ((!leaderboard || leaderboard.length === 0) && cookieStored && cookieStored.length > 0) {
        leaderboard = cookieStored;
        localStorage.setItem('ab_airway_leaderboard', JSON.stringify(leaderboard));
    } else if (leaderboard && leaderboard.length > 0 && (!cookieStored || cookieStored.length === 0)) {
        saveLeaderboardToCookie(leaderboard);
    } else if (leaderboard && leaderboard.length > 0 && cookieStored && cookieStored.length > 0) {
        // Merge list and sort, removing duplicates by name + score
        const merged = [...leaderboard, ...cookieStored];
        const unique = {};
        merged.forEach(item => {
            const key = `${item.name}-${item.score}`;
            unique[key] = item;
        });
        leaderboard = Object.values(unique)
            .sort((a, b) => b.score - a.score)
            .slice(0, 10);
        localStorage.setItem('ab_airway_leaderboard', JSON.stringify(leaderboard));
        saveLeaderboardToCookie(leaderboard);
    }
}

function saveScore(name, scoreValue) {
    initLeaderboard();
    const entry = {
        name: name,
        score: scoreValue,
        country: playerCountry,
        date: new Date().toLocaleDateString()
    };
    leaderboard.push(entry);
    leaderboard.sort((a, b) => b.score - a.score);
    leaderboard = leaderboard.slice(0, 10); // Keep top 10
    
    localStorage.setItem('ab_airway_leaderboard', JSON.stringify(leaderboard));
    saveLeaderboardToCookie(leaderboard);
}

// i18n Translations Dictionary
const gameTranslations = {
    'tr': {
        'game-title': 'Zor Havayolu<span>Kaçış Oyunu</span>',
        'overlay-title-menu': 'Zor Havayolu Kaçış Oyunu',
        'overlay-subtitle-menu': 'Klinik kararlarınızla hastanın hayatını kurtarın! Bu oyun, ASA Zor Havayolu Kılavuzu algoritmalarını simüle eder. Doğru kararlar puan kazandırır, hatalar ise hastayı hipoksiye sürükler.',
        'lbl-nick-prompt': 'Oyuncu Takma Adı (Nick):',
        'lbl-leaderboard-title': 'Liderlik Tablosu (En İyi 10)',
        'btn-start-text': 'Simülasyonu Başlat',
        'btn-mute': 'Sesi Kapat',
        'btn-unmute': 'Sesi Aç',
        'case-selection-title': 'Vaka Dosyası Seçin:',
        'case1-title': 'Vaka 1: Beklenen Zor Havayolu',
        'case1-desc': 'Retrognatisi ve Mallampati 4 skoru olan elektif cerrahi hastası. Riskleri öngörüp doğru stratejiyi uygulayın.',
        'case2-title': 'Vaka 2: Beklenmedik CVCI Krizi',
        'case2-desc': 'Genel anestezi sonrası maskeyle havalandırılamayan ve entübe edilemeyen (CVCI) hasta. Saniyeler önemli!',
        'case3-title': 'Vaka 3: Obstetrik RSI Acili',
        'case3-desc': 'Fetal distres nedeniyle acil sezaryene alınan tok karınlı gebe. Hızlı desatüre olan hassas bir havayolu.',
        'case4-title': 'Vaka 4: Pediatrik Akut Epiglottit',
        'case4-desc': '4 yaşında, stridorlu, salyalı ve tripod pozisyonda çocuk. Spontan solunumu korumak ve KBB ile koordinasyon kritik.',
        'case5-title': 'Vaka 5: Yüz / İnhalasyon Yanığı',
        'case5-desc': 'Yangından çıkarılan, yüz yanığı ve kurum saptanan hasta. Havayolu ödemi ilerlemeden erken karar şart.',
        'case6-title': 'Vaka 6: Ludwig Anjini',
        'case6-desc': 'Trismus, dil elevasyonu ve ağız tabanı ödemi olan derin boyun enfeksiyonu. Uyanık fiberoptik ve cerrahi yedek.',
        'hud-score': 'Skor',
        'hud-trauma': 'Havayolu Travması',
        'hud-spO2': 'SpO2 %',
        'hud-hr': 'Nabız bpm',
        'hud-bp': 'Kan Basıncı',
        'hud-etco2': 'EtCO2 mmHg',
        'cart-title-lbl': 'Zor Havayolu Arabası (Envanter)',
        'cart-tab-laryngo': 'Laringoskoplar',
        'cart-tab-sga': 'LMA / SGA',
        'cart-tab-adjunct': 'Ekipman / İlaç',
        'cart-tab-emergency': 'Acil eFONA',
        'status-selected': 'Seçili Cihaz:',
        'status-none': 'Yok (Elle müdahale)',
        'gameover-title': 'Hasta Kaybedildi! (Ex)',
        'gameover-subtitle': 'Hasta aşırı hipoksi nedeniyle kardiyak arreste girdi. Zor havayolu kılavuzuna uyarak adımları daha hızlı ve doğru atmalısınız.',
        'victory-title': 'Tebrikler! Vaka Başarıyla Yönetildi',
        'victory-subtitle': 'Hastanın havayolu güvenli hale getirildi, SpO2 stabilize edildi ve kılavuza tam uyum sağlandı.',
        'btn-play-again': 'Tekrar Oyna',
        'btn-main-menu': 'Ana Menüye Dön',
        'alarm-apnea': 'APNE / SOLUNUM YOK!',
        'alarm-hypoxia': 'KRİTİK HİPOKSİ ALARMI!',
        'alarm-stable': 'MONİTÖR STABİL'
    },
    'en': {
        'game-title': 'Difficult Airway<span>Escape Game</span>',
        'overlay-title-menu': 'Difficult Airway Escape Game',
        'overlay-subtitle-menu': 'Save the patient\'s life with your clinical decisions! This simulator tests your adherence to the ASA Difficult Airway Guidelines. Correct steps earn points, mistakes induce hypoxia.',
        'lbl-nick-prompt': 'Player Nickname:',
        'lbl-leaderboard-title': 'Leaderboard (Top 10)',
        'btn-start-text': 'Start Simulation',
        'btn-mute': 'Mute Sound',
        'btn-unmute': 'Unmute Sound',
        'case-selection-title': 'Select a Case File:',
        'case1-title': 'Case 1: Anticipated Difficult Airway',
        'case1-desc': 'Elective surgery patient with retrognathia and Mallampati IV score. Plan ahead and execute.',
        'case2-title': 'Case 2: Unexpected CVCI Crisis',
        'case2-desc': 'Post-induction scenario: Cannot Ventilate, Cannot Intubate (CVCI). Seconds count!',
        'case3-title': 'Case 3: Obstetric RSI Emergency',
        'case3-desc': 'Term pregnant patient with full stomach brought for emergency C-section due to fetal distress.',
        'case4-title': 'Case 4: Pediatric Acute Epiglottitis',
        'case4-desc': 'A 4-year-old with stridor, drooling and tripod positioning. Preserving spontaneous breathing and ENT coordination is critical.',
        'case5-title': 'Case 5: Face / Inhalation Burn',
        'case5-desc': 'A patient rescued from a fire with facial burns and soot. Early decision before edema progresses is essential.',
        'case6-title': "Case 6: Ludwig's Angina",
        'case6-desc': 'Deep neck infection with trismus, tongue elevation and floor-of-mouth edema. Awake fiberoptic with a surgical backup.',
        'hud-score': 'Score',
        'hud-trauma': 'Airway Trauma',
        'hud-spO2': 'SpO2 %',
        'hud-hr': 'Heart Rate bpm',
        'hud-bp': 'Blood Pressure',
        'hud-etco2': 'EtCO2 mmHg',
        'cart-title-lbl': 'Difficult Airway Cart (Inventory)',
        'cart-tab-laryngo': 'Laryngoscopes',
        'cart-tab-sga': 'LMA / SGA',
        'cart-tab-adjunct': 'Adjuncts / Meds',
        'cart-tab-emergency': 'eFONA / Rescue',
        'status-selected': 'Selected Device:',
        'status-none': 'None (Manual intervention)',
        'gameover-title': 'Patient Lost! (Cardiac Arrest)',
        'gameover-subtitle': 'The patient suffered cardiac arrest due to prolonged hypoxia. Follow the guidelines and act faster.',
        'victory-title': 'Success! Case Managed Safely',
        'victory-subtitle': 'Airway secured, SpO2 stabilized, and full guidelines compliance achieved.',
        'btn-play-again': 'Play Again',
        'btn-main-menu': 'Main Menu',
        'alarm-apnea': 'APNEA / NO VENTILATION!',
        'alarm-hypoxia': 'CRITICAL HYPOXIA ALARM!',
        'alarm-stable': 'MONITOR STABLE'
    }
};

// Airway Cart Items Database
const cartTools = [
    { id: 'mac_laryngo', icon: 'fa-solid fa-wrench', name: { tr: 'Macintosh Bıçak', en: 'Mac Laryngoscope' }, category: 'laryngoscopes' },
    { id: 'video_laryngo', icon: 'fa-solid fa-camera', name: { tr: 'Videolaringoskop', en: 'Videolaryngoscope' }, category: 'laryngoscopes' },
    { id: 'fiberoptic', icon: 'fa-solid fa-staff-snake', name: { tr: 'Fiberoptik Bronkoskop', en: 'Flexible Bronchoscope' }, category: 'laryngoscopes' },
    { id: 'lma_classic', icon: 'fa-solid fa-circle', name: { tr: 'LMA Klasik', en: 'LMA Classic' }, category: 'sga' },
    { id: 'lma_supreme', icon: 'fa-solid fa-shield-halved', name: { tr: 'LMA Supreme / I-Gel', en: 'LMA Supreme / I-Gel' }, category: 'sga' },
    { id: 'bougie', icon: 'fa-solid fa-compass-drafting', name: { tr: 'Kılavuz Buji', en: 'Airway Bougie' }, category: 'adjuncts' },
    { id: 'sugammadex', icon: 'fa-solid fa-capsules', name: { tr: 'Sugammadex (İlaç)', en: 'Sugammadex (Meds)' }, category: 'adjuncts' },
    { id: 'crico_kit', icon: 'fa-solid fa-kit-medical', name: { tr: 'Krikotiroidotomi Kiti', en: 'eFONA Cricothyroid Kit' }, category: 'emergency' }
];

// Clinical Scenario Decision Tree
const scenarioNodes = {
    // -------------------------------------------------------------
    // CASE 1: ANTICIPATED DIFFICULT AIRWAY
    // -------------------------------------------------------------
    'case1': {
        'start': {
            text: {
                tr: "Elektif tiroid cerrahisi planlanan 45 yaşında erkek hasta ameliyathaneye alındı. Fizik muayenede belirgin mikrognati (küçük çene), kısıtlı boyun hareketi ve Mallampati 4 havayolu yapısı tespit edildi. Anestezi indüksiyonu öncesi havayolu planınız nedir?",
                en: "A 45-year-old male scheduled for elective thyroid surgery is brought to the OR. Physical exam reveals retrognathia, limited neck mobility, and Mallampati IV airway. What is your pre-induction airway plan?"
            },
            choices: [
                {
                    text: { tr: "Hastayı uyutmadan (lokal anesteziyle) uyanık fiberoptik entübasyon (AFOI) hazırlığı yaparım.", en: "Prepare for Awake Fiberoptic Intubation (AFOI) under local anesthesia." },
                    nextNode: 'c1_awake_success',
                    score: 100,
                    trauma: 0,
                    spO2: 99,
                    hr: 75,
                    bp: '120/80',
                    etco2: 38
                },
                {
                    text: { tr: "Hızlı seri indüksiyon (RSI) yapıp doğrudan Macintosh bıçakla entübe etmeye çalışırım.", en: "Perform Rapid Sequence Induction (RSI) and attempt direct laryngoscopy." },
                    nextNode: 'c1_rsi_fail',
                    score: 0,
                    trauma: 15,
                    spO2: 90,
                    hr: 105,
                    bp: '145/90',
                    etco2: 0
                },
                {
                    text: { tr: "Sevofluran ile inhalasyon indüksiyonu yapıp spontan solunumu koruyarak ilerlerim.", en: "Inhalation induction with Sevoflurane, maintaining spontaneous breathing." },
                    nextNode: 'c1_inhalation_obstruction',
                    score: 50,
                    trauma: 0,
                    spO2: 95,
                    hr: 90,
                    bp: '130/80',
                    etco2: 25
                }
            ]
        },
        'c1_awake_success': {
            text: {
                tr: "Uyanık fiberoptik entübasyon stratejisi başarıyla uygulandı! Havayolu lokal anesteziyle baskılandı, fiberoptik skop yardımıyla vokal kordlar görüldü ve tüp nazikçe ilerletildi. Hasta entübe edildikten sonra genel anestezi indüksiyonuna geçildi. Tebrikler, kılavuza tam uyum sağladınız!",
                en: "Awake Fiberoptic Intubation executed successfully! The airway was topicalized, vocal cords were visualized with the bronchoscope, and the tube was advanced gently. General anesthesia was induced post-intubation. Excellent work adhering to the guidelines!"
            },
            choices: [],
            isVictory: true
        },
        'c1_rsi_fail': {
            text: {
                tr: "Propofol ve Roküronyum verilerek genel anestezi indüklendi. Maske ventilasyonu denediniz fakat hava kaçağı var, göğüs kalkmıyor. SpO2 düşmeye başladı (%90). Macintosh 3 laringoskopla doğrudan entübasyon denediniz ancak sadece epiglot ucu görünüyor (Cormack-Lehane Sınıf 4). Ne yapacaksınız?",
                en: "General anesthesia was induced with Propofol and Rocuronium. Face mask ventilation fails due to poor seal, no chest rise. SpO2 is dropping (90%). You attempt direct laryngoscopy with Mac 3 blade but only see the tip of the epiglottis (Cormack-Lehane Grade 4). What is next?"
            },
            choices: [
                {
                    text: { tr: "Videolaringoskop (VL) seçip havayolu bujisi (bougie) ile entübasyonu denerim.", en: "Select Videolaryngoscope (VL) and attempt intubation using a bougie." },
                    nextNode: 'c1_vl_success',
                    score: 80,
                    trauma: 10,
                    spO2: 98,
                    hr: 85,
                    bp: '125/80',
                    etco2: 38,
                    requiredTool: 'video_laryngo'
                },
                {
                    text: { tr: "Hemen LMA Supreme yerleştirerek ventilasyonu sağlamayı denerim.", en: "Immediately place LMA Supreme to restore ventilation." },
                    nextNode: 'c1_lma_success',
                    score: 60,
                    trauma: 5,
                    spO2: 97,
                    hr: 80,
                    bp: '118/75',
                    etco2: 36,
                    requiredTool: 'lma_supreme'
                },
                {
                    text: { tr: "Körlemesine entübasyon için tüp içine metal stile yerleştirip tekrar denerim.", en: "Insert metal stylet into the tube and attempt blind intubation again." },
                    nextNode: 'c1_blind_trauma',
                    score: -50,
                    trauma: 40,
                    spO2: 78,
                    hr: 120,
                    bp: '160/95',
                    etco2: 0
                }
            ]
        },
        'c1_inhalation_obstruction': {
            text: {
                tr: "İnhalasyon indüksiyonu sırasında anestezinin derinleşmesiyle hastanın havayolu tamamen tıkandı (obstrüksiyon). Göğüs hareketleri paradoks hal aldı, SpO2 %85'e geriledi. Maskeyle havalandırma çabalarınız yetersiz kalıyor. İlk hamleniz nedir?",
                en: "During inhalation induction, deep anesthesia caused complete airway obstruction. Chest movements became paradoxical, SpO2 is down to 85%. Face mask ventilation fails. What is your immediate rescue action?"
            },
            choices: [
                {
                    text: { tr: "Oral airway yerleştirip başa pozisyon vererek iki kişiyle maske ventilasyonu denerim.", en: "Insert oral airway, perform head-tilt/jaw-thrust, and attempt two-person ventilation." },
                    nextNode: 'c1_mask_optimized',
                    score: 80,
                    trauma: 0,
                    spO2: 97,
                    hr: 85,
                    bp: '125/80',
                    etco2: 36
                },
                {
                    text: { tr: "Hemen LMA Classic takarak körleme ventilasyonu denerim.", en: "Immediately insert LMA Classic and attempt rescue ventilation." },
                    nextNode: 'c1_lma_success',
                    score: 70,
                    trauma: 5,
                    spO2: 97,
                    hr: 80,
                    bp: '118/75',
                    etco2: 36,
                    requiredTool: 'lma_classic'
                }
            ]
        },
        'c1_mask_optimized': {
            text: {
                tr: "Optimizasyon başarılı! Çift el maske ventilasyonu ve oral airway sayesinde SpO2 %97'ye yükseldi. Ancak cerrahi için havayolunu emniyete almalısınız. Hangi cihazı seçeceksiniz?",
                en: "Optimization succeeded! Two-person ventilation and oral airway restored SpO2 to 97%. However, you must secure the airway for surgery. Which device will you select?"
            },
            choices: [
                {
                    text: { tr: "Videolaringoskop (VL) yardımıyla entübasyon denerim.", en: "Attempt intubation under Videolaryngoscopy (VL)." },
                    nextNode: 'c1_vl_success',
                    score: 80,
                    trauma: 5,
                    spO2: 99,
                    hr: 75,
                    bp: '120/80',
                    etco2: 38,
                    requiredTool: 'video_laryngo'
                },
                {
                    text: { tr: "Sugammadex vererek hastayı uyandırıp uyanık fiberoptik entübasyona geri dönerim.", en: "Administer Sugammadex to wake the patient and revert to awake fiberoptic." },
                    nextNode: 'c1_reverse_wake',
                    score: 100,
                    trauma: 0,
                    spO2: 99,
                    hr: 75,
                    bp: '120/80',
                    etco2: 35,
                    requiredTool: 'sugammadex'
                }
            ]
        },
        'c1_vl_success': {
            text: {
                tr: "Harika karar! Videolaringoskop sayesinde glottik açıklık (vokal kordlar) net şekilde görüntülendi. Buji üzerinden tüp başarıyla kaydırılarak entübasyon sağlandı. SpO2 %99, akciğer sesleri çift taraflı eşit. Vaka güvenle kontrol altında!",
                en: "Great call! Videolaryngoscopy provided a clear view of the glottic opening. The tube was slid over the bougie and secured. SpO2 is 99%, breath sounds are bilateral and equal. Case managed safely!"
            },
            choices: [],
            isVictory: true
        },
        'c1_lma_success': {
            text: {
                tr: "LMA Supreme yerleştirildi ve ventilasyon doğrulandı. ETCO2 trasesi normale döndü, SpO2 %97'de sabitlendi. Şimdi havayolunu kesinleştirmek için ne yapacaksınız?",
                en: "LMA Supreme placed successfully and ventilation confirmed. ETCO2 curve restored, SpO2 stabilized at 97%. How will you secure the definitive airway?"
            },
            choices: [
                {
                    text: { tr: "LMA içinden fiberoptik eşliğinde entübasyon yaparım.", en: "Perform fiberoptic intubation through the LMA." },
                    nextNode: 'c1_vl_success',
                    score: 90,
                    trauma: 0,
                    spO2: 99,
                    hr: 70,
                    bp: '115/75',
                    etco2: 38
                },
                {
                    text: { tr: "Hastayı uyandırıp uyanık fiberoptik entübasyona dönerim (Sugammadex ile).", en: "Wake the patient up using Sugammadex and return to awake fiberoptic." },
                    nextNode: 'c1_reverse_wake',
                    score: 100,
                    trauma: 0,
                    spO2: 99,
                    hr: 75,
                    bp: '120/80',
                    etco2: 35,
                    requiredTool: 'sugammadex'
                }
            ]
        },
        'c1_blind_trauma': {
            text: {
                tr: "Sert stileli tüple körlemesine entübasyon denemesi havayolu dokusunda şiddetli kanama ve ödeme yol açtı! SpO2 %78'e geriledi. Kanama nedeniyle artık maskeyle de havalandıramıyorsunuz (CVCI riski). Nabız 120 bpm, taşikardi var. Ne yapacaksınız?",
                en: "The blind intubation attempt with a rigid stylet caused severe mucosal bleeding and edema! SpO2 dropped to 78%. Bleeding makes face mask ventilation impossible (CVCI warning). HR is 120 bpm. What is your next move?"
            },
            choices: [
                {
                    text: { tr: "Sugammadex verip kas gevşeticiyi geri çevirerek uyanmasını beklerim.", en: "Give Sugammadex to reverse neuromuscular blockade and wait for return of ventilation." },
                    nextNode: 'c1_hypoxia_arrest',
                    score: -20,
                    trauma: 0,
                    spO2: 40,
                    hr: 135,
                    bp: '90/50',
                    etco2: 0,
                    requiredTool: 'sugammadex'
                },
                {
                    text: { tr: "Hemen krikotiroidotomi (eFONA) kitini hazırlayıp acil boyun cerrahisine geçerim.", en: "Immediately prepare the cricothyroidotomy (eFONA) kit for emergency neck access." },
                    nextNode: 'c1_crico_rescue',
                    score: 80,
                    trauma: 10,
                    spO2: 95,
                    hr: 90,
                    bp: '110/70',
                    etco2: 36,
                    requiredTool: 'crico_kit'
                }
            ]
        },
        'c1_reverse_wake': {
            text: {
                tr: "Sugammadex verilerek kas gevşetici etki saniyeler içinde geri çevrildi. Hasta güvenle uyandırıldı, kendi solunumu döndü. Ameliyat iptal edilerek uyanık fiberoptik entübasyon planı için hasta yoğun bakıma veya erteleme odasına sevk edildi. Kritik hatadan dönüldü ve hasta korundu!",
                en: "Sugammadex successfully reversed the neuromuscular block. The patient was woken up safely and spontaneous breathing returned. Surgery deferred, patient rescheduled for awake fiberoptic intubation. A critical crisis was averted successfully!"
            },
            choices: [],
            isVictory: true
        },
        'c1_crico_rescue': {
            text: {
                tr: "Scalpel-Bougie krikotiroidotomi kitiyle boyundan acil giriş sağlandı! Trakeaya buji üzerinden 6.0 numaralı kaf kılavuzlanarak yerleştirildi ve ventilasyon doğrulandı. SpO2 tekrar %95'e yükseldi. Hasta hayata döndürüldü!",
                en: "Emergency front-of-neck access (eFONA) performed using Scalpel-Bougie cricothyroidotomy kit! A 6.0 cuffed tube was guided over a bougie into the trachea. Ventilation confirmed, SpO2 recovered to 95%. Patient saved!"
            },
            choices: [],
            isVictory: true
        },
        'c1_hypoxia_arrest': {
            text: {
                tr: "Kritik Hata! Hasta tamamen tıkanmış durumdayken kas gevşeticinin geri dönmesini beklemek için çok geçti. Hasta hipoksik kardiyak arreste girdi. Acil eFONA gecikti.",
                en: "Critical Failure! Waiting for neuromuscular reversal in a completely obstructed CVCI situation was too slow. The patient suffered hypoxic cardiac arrest. Emergency eFONA was delayed."
            },
            choices: [],
            isGameOver: true
        }
    },
    
    // -------------------------------------------------------------
    // CASE 2: UNANTICIPATED CVCI (CANNOT VENTILATE, CANNOT INTUBATE)
    // -------------------------------------------------------------
    'case2': {
        'start': {
            text: {
                tr: "Normal havayolu muayenesi olan 25 yaşında bir hastaya genel anestezi indüksiyonu uygulandı. Kas gevşetici sonrası maskeyle havalandırma (ventilasyon) denediniz ancak hava yolu direnci çok yüksek, göğüs hareket etmiyor ve ETCO2 trasesi tamamen düz. SpO2 %95'e iniyor. İlk adımınız nedir?",
                en: "A 25-year-old patient with normal airway exam is induced for general anesthesia. Following muscle relaxant administration, mask ventilation fails due to high resistance, no chest rise, and flat ETCO2. SpO2 is dropping to 95%. What is your first step?"
            },
            choices: [
                {
                    text: { tr: "Maske ventilasyonunu optimize ederim (oral/nasal airway takarım, çift el maske tutuşuna geçerim).", en: "Optimize mask ventilation (insert oral/nasal airway, use two-handed mask grip)." },
                    nextNode: 'c2_mask_opt_try',
                    score: 100,
                    trauma: 0,
                    spO2: 97,
                    hr: 85,
                    bp: '130/80',
                    etco2: 30
                },
                {
                    text: { tr: "Macintosh laringoskopla doğrudan entübasyon denerim.", en: "Attempt direct laryngoscopy with Macintosh blade immediately." },
                    nextNode: 'c2_laryngo_fail',
                    score: -20,
                    trauma: 15,
                    spO2: 88,
                    hr: 100,
                    bp: '140/90',
                    etco2: 0,
                    requiredTool: 'mac_laryngo'
                },
                {
                    text: { tr: "Hemen bir Laryngeal Mask (LMA Classic) takarım.", en: "Insert Laryngeal Mask Airway (LMA Classic) immediately." },
                    nextNode: 'c2_lma_fail_node',
                    score: 50,
                    trauma: 5,
                    spO2: 85,
                    hr: 110,
                    bp: '150/95',
                    etco2: 0,
                    requiredTool: 'lma_classic'
                }
            ]
        },
        'c2_mask_opt_try': {
            text: {
                tr: "Optimizasyona rağmen (çift el maske tutuşu ve oral airway) ventilasyon sağlanamadı! Hastanın anatomisinde derin bir laringospazm veya havayolu tıkanıklığı var. SpO2 %89'a geriledi, nabız 105 bpm'e yükseldi. Hızlı aksiyon almalısınız:",
                en: "Despite optimizing (two-handed grip and oral airway), face mask ventilation remains impossible! The patient has a severe airway obstruction. SpO2 dropped to 89%, HR rose to 105 bpm. Act fast:"
            },
            choices: [
                {
                    text: { tr: "Videolaringoskop (VL) seçip glottik açıklığı görerek entübasyon denerim.", en: "Select Videolaryngoscope (VL) and attempt intubation under direct visualization." },
                    nextNode: 'c2_vl_first_attempt',
                    score: 90,
                    trauma: 10,
                    spO2: 90,
                    hr: 100,
                    bp: '135/85',
                    etco2: 38,
                    requiredTool: 'video_laryngo'
                },
                {
                    text: { tr: "Havayolunu kurtarmak için ikinci nesil LMA (LMA Supreme) yerleştiririm.", en: "Insert a second-generation SGA (LMA Supreme) to rescue the airway." },
                    nextNode: 'c2_lma_fail_node',
                    score: 80,
                    trauma: 5,
                    spO2: 80,
                    hr: 115,
                    bp: '155/95',
                    etco2: 0,
                    requiredTool: 'lma_supreme'
                }
            ]
        },
        'c2_laryngo_fail': {
            text: {
                tr: "Macintosh bıçakla yaptığınız laringoskopide vokal kordlar görülemedi (Grade 4 görünüm). SpO2 %84'e geriledi. Tekrar eden doğrudan laringoskopi denemesi havayolu ödemini artırır. Ne yapacaksınız?",
                en: "Direct laryngoscopy with Mac blade failed to show the vocal cords (Grade 4 view). SpO2 is now 84%. Repeated direct attempts will worsen edema. What will you do?"
            },
            choices: [
                {
                    text: { tr: "LMA Supreme yerleştirerek acil havalandırma (solunum) desteği sağlarım.", en: "Place LMA Supreme to establish rescue ventilation." },
                    nextNode: 'c2_lma_fail_node',
                    score: 80,
                    trauma: 5,
                    spO2: 80,
                    hr: 110,
                    bp: '150/90',
                    etco2: 0,
                    requiredTool: 'lma_supreme'
                },
                {
                    text: { tr: "Videolaringoskop (VL) ve Buji (Bougie) kombinasyonunu hazırlar ve denerim.", en: "Prepare and attempt Videolaryngoscopy (VL) with a Bougie." },
                    nextNode: 'c2_vl_with_bougie_success',
                    score: 90,
                    trauma: 10,
                    spO2: 98,
                    hr: 85,
                    bp: '125/80',
                    etco2: 38,
                    requiredTool: 'video_laryngo'
                }
            ]
        },
        'c2_lma_fail_node': {
            text: {
                tr: "Kritik Gelişme! Yerleştirdiğiniz LMA ventilasyon sağlamadı. Kaçak devam ediyor, göğüs hareket etmiyor ve SpO2 %72'ye kadar indi! Nabız 120 bpm (taşikardi). Hasta CVCI (Havalandırılamıyor, Entübe Edilemiyor) aşamasında. Ne yapacaksınız?",
                en: "Critical Event! The LMA failed to establish ventilation. Airway leak persists, no chest rise, and SpO2 plummeted to 72%! HR is 120 bpm. The patient is in a CVCI state. What is your choice?"
            },
            choices: [
                {
                    text: { tr: "Zaman kaybetmeden Krikotiroidotomi Kiti (eFONA) ile acil boyun cerrahisi uygularım.", en: "Immediately perform emergency front-of-neck access (eFONA) using cricothyroid kit." },
                    nextNode: 'c2_crico_rescue_success',
                    score: 100,
                    trauma: 15,
                    spO2: 95,
                    hr: 90,
                    bp: '110/70',
                    etco2: 38,
                    requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "Farklı bir laringoskop bıçağı takıp metal stileyle körlemesine entübasyon denerim.", en: "Mount a different laryngoscope blade and attempt blind intubation with a metal stylet." },
                    nextNode: 'c2_blind_arrest',
                    score: -100,
                    trauma: 50,
                    spO2: 40,
                    hr: 140,
                    bp: '80/40',
                    etco2: 0
                },
                {
                    text: { tr: "Sugammadex vererek gevşetici etkiyi kaldırıp hastayı uyanmaya bırakırım.", en: "Administer Sugammadex to reverse paralysis and wait for spontaneous recovery." },
                    nextNode: 'c2_suga_arrest',
                    score: -50,
                    trauma: 0,
                    spO2: 45,
                    hr: 135,
                    bp: '85/45',
                    etco2: 0,
                    requiredTool: 'sugammadex'
                }
            ]
        },
        'c2_vl_first_attempt': {
            text: {
                tr: "Videolaringoskop ile vokal kordlar kısmen görüntülendi (Grade 3a). Ancak tüp glottan geçmiyor. SpO2 %80'e geriledi. Havayolunda ödem oluşmaya başladı. Hamleniz?",
                en: "Videolaryngoscopy showed a partial view of vocal cords (Grade 3a). However, the tube won't pass. SpO2 dropped to 80%. Airway edema is developing. What is your action?"
            },
            choices: [
                {
                    text: { tr: "Havayolu bujisi (bougie) kullanarak tüpü buji üzerinden kaydırıp entübe ederim.", en: "Use an airway bougie to guide the tube into the trachea." },
                    nextNode: 'c2_vl_with_bougie_success',
                    score: 100,
                    trauma: 5,
                    spO2: 99,
                    hr: 75,
                    bp: '120/80',
                    etco2: 38,
                    requiredTool: 'bougie'
                },
                {
                    text: { tr: "Laryngoskopu geri çekip LMA Supreme yerleştirerek acil ventilasyon denerim.", en: "Withdraw laryngoscope and insert LMA Supreme to establish rescue ventilation." },
                    nextNode: 'c2_lma_fail_node',
                    score: 60,
                    trauma: 5,
                    spO2: 72,
                    hr: 120,
                    bp: '150/95',
                    etco2: 0,
                    requiredTool: 'lma_supreme'
                }
            ]
        },
        'c2_vl_with_bougie_success': {
            text: {
                tr: "Mükemmel klinik yaklaşım! Videolaringoskop ekranındaki Grade 3 görüntüde buji glottik açıklıktan başarıyla ilerletildi, ardından tüp buji üzerinden trakeaya kaydırıldı. Kaf şişirildi ve ventilasyon doğrulandı. SpO2 %99, akciğer sesleri simetrik. Vaka başarıyla yönetildi!",
                en: "Excellent clinical approach! The bougie was advanced through the glottis under videolaryngoscopic guidance, and the tube was tubed over it. Cuff inflated, ventilation confirmed. SpO2 is 99%, breath sounds symmetric. Case resolved successfully!"
            },
            choices: [],
            isVictory: true
        },
        'c2_crico_rescue_success': {
            text: {
                tr: "Acil boyun cerrahisi (eFONA) başarıyla tamamlandı! Krikotiroid membrandan girilen bistüri üzerinden buji kaydırıldı ve buji yardımıyla trakeal tüp yerleştirildi. Kaf şişirilip havalandırıldıktan sonra ETCO2 trasesi normale döndü ve SpO2 %95'e yükseldi. Hasta hayatta!",
                en: "Emergency front-of-neck access (eFONA) completed successfully! Scalpel-Bougie technique used, cuffed tube placed in the trachea. ETCO2 curve restored, SpO2 recovered to 95%. Patient saved!"
            },
            choices: [],
            isVictory: true
        },
        'c2_blind_arrest': {
            text: {
                tr: "Kritik Hata! Ağır hipoksi durumunda körleme entübasyon denemek havayolunu parçaladı ve şiddetli kanamaya sebep oldu. Hasta hipoksik asistoliye girdi ve ex kabul edildi.",
                en: "Critical Failure! Attempting blind intubation during profound hypoxia caused massive airway trauma and bleeding. The patient suffered hypoxic asystole."
            },
            choices: [],
            isGameOver: true
        },
        'c2_suga_arrest': {
            text: {
                tr: "Kritik Hata! CVCI (Havalandırılamıyor, Entübe Edilemiyor) durumunda, SpO2 %70'lerin altındayken Sugammadex'in etkisini göstermesini ve hastanın uyanmasını beklemek için yeterli süre yoktur. Beyin hipoksisi oluştu ve hasta kaybedildi.",
                en: "Critical Failure! In a CVCI situation with SpO2 <70%, there is not enough time to wait for Sugammadex to reverse paralysis and the patient to wake up. Hypoxic brain injury occurred."
            },
            choices: [],
            isGameOver: true
        }
    },
    
    // -------------------------------------------------------------
    // CASE 3: OBSTETRIC RSI CRISIS
    // -------------------------------------------------------------
    'case3': {
        'start': {
            text: {
                tr: "Fetal distres (bebek kalp atımı 80 bpm) sebebiyle acil sezaryen planlanan 38 haftalık tok karınlı gebe ameliyathaneye alındı. Gebe hastaların fonksiyonel rezidüel kapasiteleri düşük olduğundan hızla desatüre olurlar. İndüksiyon planınız nedir?",
                en: "A 38-week pregnant patient requires emergency C-section due to fetal distress (fetal HR 80 bpm). Full stomach status. Obstetric patients desaturate rapidly due to low functional residual capacity. What is your induction plan?"
            },
            choices: [
                {
                    text: { tr: "Hızlı Seri İndüksiyon (RSI) uygularım, indüksiyon öncesi 3 dakika %100 preoksijenasyon yapar ve krikoid bası (Sellick manevrası) uygulatırım.", en: "Perform Rapid Sequence Induction (RSI) with 3 mins preoxygenation and cricoid pressure." },
                    nextNode: 'c3_rsi_induced',
                    score: 100,
                    trauma: 0,
                    spO2: 99,
                    hr: 80,
                    bp: '130/80',
                    etco2: 38
                },
                {
                    text: { tr: "Aspirasyon riskini önlemek için lokal anesteziyle uyanık fiberoptik entübasyon denerim.", en: "To prevent aspiration risk, attempt awake fiberoptic intubation." },
                    nextNode: 'c3_fetal_bradycardia',
                    score: -30,
                    trauma: 0,
                    spO2: 95,
                    hr: 110,
                    bp: '150/95',
                    etco2: 35
                }
            ]
        },
        'c3_fetal_bradycardia': {
            text: {
                tr: "Uyanık fiberoptik entübasyon girişimi tok karınlı gebede ajitasyona ve sürenin uzamasına yol açtı. Fetal bradikardi daha da kötüleşti (kalp atımı 60 bpm). Acil C-section gerekiyor! Ne yapacaksınız?",
                en: "Awake fiberoptic intubation attempt caused maternal agitation and delayed the procedure. Fetal bradycardia worsened (fetal HR 60 bpm). Emergency C-section is critical! What will you do?"
            },
            choices: [
                {
                    text: { tr: "RSI indüksiyonuna geçerim, hızlıca Propofol + Süksinilkolin veririm.", en: "Switch to RSI induction with Propofol + Succinylcholine." },
                    nextNode: 'c3_rsi_induced',
                    score: 70,
                    trauma: 0,
                    spO2: 98,
                    hr: 95,
                    bp: '140/90',
                    etco2: 0
                }
            ]
        },
        'c3_rsi_induced': {
            text: {
                tr: "İndüksiyon sonrası Macintosh 3 bıçakla yapılan ilk entübasyon denemesinde tüpün özofagusa girdiği görüldü (ETCO2 düz trase, göğüs hareketleri yok). SpO2 %91'e iniyor. Ne yapacaksınız?",
                en: "Following induction, the first intubation attempt with Mac 3 blade resulted in esophageal intubation (flat ETCO2, no chest rise). SpO2 is dropping to 91%. What is your move?"
            },
            choices: [
                {
                    text: { tr: "Tüpü çekerim, krikoid basıyı hafifçe gevşetip maskeyle nazikçe ventilasyon sağlayarak oksijenlendiririm.", en: "Remove the tube, slightly ease cricoid pressure, and gently mask ventilate to preoxygenate." },
                    nextNode: 'c3_mask_ventilated',
                    score: 100,
                    trauma: 5,
                    spO2: 97,
                    hr: 90,
                    bp: '120/80',
                    etco2: 34
                },
                {
                    text: { tr: "Tüpü yerinde bırakıp doğrudan videolaringoskop (VL) ile ikinci entübasyon denemesine geçerim.", en: "Leave tube in place, switch to videolaryngoscope (VL), and attempt immediate re-intubation." },
                    nextNode: 'c3_obstetric_hypoxia',
                    score: -10,
                    trauma: 15,
                    spO2: 81,
                    hr: 115,
                    bp: '145/90',
                    etco2: 0,
                    requiredTool: 'video_laryngo'
                }
            ]
        },
        'c3_mask_ventilated': {
            text: {
                tr: "Özofageal tüp çekildi. Nazik maske ventilasyonu ile SpO2 tekrar %97'ye yükseltildi. İkinci entübasyon denemesinde başarısızlığı önlemek için hangi cihazı ve tekniği kullanacaksınız?",
                en: "Esophageal tube removed. Gentle mask ventilation restored SpO2 to 97%. To prevent failure in the second attempt, which device and technique will you use?"
            },
            choices: [
                {
                    text: { tr: "Videolaringoskop (VL) ve Buji (Bougie) kullanarak glottik açıklığı görerek entübasyon sağlarım.", en: "Use Videolaryngoscope (VL) and Bougie to intubate under direct visualization." },
                    nextNode: 'c3_obstetric_victory',
                    score: 100,
                    trauma: 5,
                    spO2: 99,
                    hr: 80,
                    bp: '120/80',
                    etco2: 38,
                    requiredTool: 'video_laryngo'
                },
                {
                    text: { tr: "LMA Supreme takarak havayolunu bu şekilde sürdürürüm.", en: "Place LMA Supreme and maintain ventilation through it." },
                    nextNode: 'c3_lma_maintenance',
                    score: 70,
                    trauma: 5,
                    spO2: 96,
                    hr: 85,
                    bp: '118/75',
                    etco2: 36,
                    requiredTool: 'lma_supreme'
                }
            ]
        },
        'c3_lma_maintenance': {
            text: {
                tr: "LMA Supreme takıldı, ventilasyon doğrulandı. Ancak hasta tok karınlı gebe olduğundan LMA ile devam etmek yüksek aspirasyon riski taşır. Ayrıca cerrahi sezaryendir. Ne yapacaksınız?",
                en: "LMA Supreme placed, ventilation confirmed. However, maintaining ventilation via LMA in a full-stomach pregnant patient carries a high risk of aspiration. What is your choice?"
            },
            choices: [
                {
                    text: { tr: "LMA içinden fiberoptik skop veya buji kılavuzluğunda entübasyon denerim.", en: "Attempt intubation through the LMA guided by a fiberoptic scope or bougie." },
                    nextNode: 'c3_obstetric_victory',
                    score: 95,
                    trauma: 5,
                    spO2: 99,
                    hr: 75,
                    bp: '120/80',
                    etco2: 38
                },
                {
                    text: { tr: "Operasyonu bu şekilde hızla tamamlatıp cerrahi bitiminde LMA'yı çekerim.", en: "Proceed with surgery under LMA and remove it immediately after delivery." },
                    nextNode: 'c3_aspiration_arrest',
                    score: -50,
                    trauma: 0,
                    spO2: 50,
                    hr: 130,
                    bp: '90/50',
                    etco2: 0
                }
            ]
        },
        'c3_obstetric_hypoxia': {
            text: {
                tr: "Ventilasyon olmadan yapılan ikinci entübasyon denemesi de başarısız oldu (Grade 4 görünüm). SpO2 %65'e düştü, hasta bradikardik (nabız 50 bpm). Ağır hipoksi ve kardiyak arrest riski mevcut! Acil eFONA kiti hazır mı?",
                en: "The second attempt without mask ventilation failed (Grade 4 view). SpO2 dropped to 65%, patient is bradycardic (HR 50 bpm). Risk of cardiac arrest is high! Is your eFONA kit ready?"
            },
            choices: [
                {
                    text: { tr: "Zaman kaybetmeden Krikotiroidotomi Kiti (eFONA) uygularım.", en: "Perform emergency front-of-neck access (eFONA) immediately." },
                    nextNode: 'c3_crico_rescue_success',
                    score: 100,
                    trauma: 15,
                    spO2: 95,
                    hr: 90,
                    bp: '110/70',
                    etco2: 38,
                    requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "LMA Supreme takarak havalandırma denerim.", en: "Place LMA Supreme and attempt rescue ventilation." },
                    nextNode: 'c3_hypoxia_arrest_obs',
                    score: -20,
                    trauma: 5,
                    spO2: 40,
                    hr: 45,
                    bp: '70/35',
                    etco2: 0,
                    requiredTool: 'lma_supreme'
                }
            ]
        },
        'c3_crico_rescue_success': {
            text: {
                tr: "eFONA kitiyle yapılan acil krikotiroidotomi sayesinde trakeaya hava akışı sağlandı! SpO2 %95'e çıktı, bebek sezaryenle sağlıklı doğurtuldu. Anne yoğun bakıma alındı. Kritik acil müdahale başarılı!",
                en: "Emergency cricothyroidotomy performed successfully! Airway secured, SpO2 recovered to 95%. Healthy baby delivered via C-section. Mother transferred to ICU. Rescue successful!"
            },
            choices: [],
            isVictory: true
        },
        'c3_obstetric_victory': {
            text: {
                tr: "Mükemmel yönetim! Videolaringoskop eşliğinde buji kılavuzluğunda tüp tek seferde trakeaya yerleştirildi. ETCO2 trasesi normal, akciğerler havalanıyor, aspirasyon önlendi. Bebek APGAR 9/10 ile doğdu, anne stabil. Tebrikler!",
                en: "Excellent management! The tube was placed smoothly in the trachea using VL and bougie. ETCO2 normal, lungs ventilated, aspiration prevented. Baby born with APGAR 9/10, mother stable. Congratulations!"
            },
            choices: [],
            isVictory: true
        },
        'c3_aspiration_arrest': {
            text: {
                tr: "Kritik Hata! Operasyon sırasında krikoid basınç ve korumalı entübasyon olmadan sezaryen cerrahisi sürdürülürken hasta yoğun mide içeriği aspire etti. Şiddetli laringospazm ve kimyasal pnömoni nedeniyle hasta ex oldu.",
                en: "Critical Failure! Proceeding with C-section under LMA in a full stomach patient led to massive gastric aspiration, chemical pneumonitis, and cardiac arrest."
            },
            choices: [],
            isGameOver: true
        },
        'c3_hypoxia_arrest_obs': {
            text: {
                tr: "Kritik Hata! Hastanın nabzı 40'lara inmişken acil cerrahi havayolu açmak yerine ağır hipokside LMA yerleştirmeye çalışmak fetal ve maternal asfiksiye (ölüme) sebep oldu.",
                en: "Critical Failure! Attempting LMA placement during severe bradycardia and hypoxia instead of emergency front-of-neck access led to fetal and maternal death."
            },
            choices: [],
            isGameOver: true
        }
    },

    // -------------------------------------------------------------
    // CASE 4: PEDIATRIC ACUTE EPIGLOTTITIS
    // -------------------------------------------------------------
    'case4': {
        'start': {
            text: {
                tr: "4 yaşında çocuk; 1 gündür yüksek ateş, salya akması, konuşamama ve inspiratuar stridor ile acile getirildi. Çocuk oturur pozisyonda, öne eğik ('tripod') ve huzursuz. Akut epiglottitten şüpheleniyorsunuz. İlk yaklaşımınız nedir?",
                en: "A 4-year-old presents with 1 day of high fever, drooling, inability to speak and inspiratory stridor. The child sits upright, leaning forward ('tripod') and agitated. You suspect acute epiglottitis. What is your initial approach?"
            },
            choices: [
                {
                    text: { tr: "Çocuğu sakin tutar, ebeveynden ayırmam; ameliyathaneye alıp KBB ekibi (rijit bronkoskopi) hazırken sevofluran ile spontan solunumu koruyarak inhalasyon indüksiyonu yaparım.", en: "Keep the child calm with the parent present; transfer to OR and perform inhalational induction with sevoflurane preserving spontaneous breathing, with ENT (rigid bronchoscopy) standing by." },
                    nextNode: 'c4_inhalation_induced', score: 100, trauma: 0, spO2: 96, hr: 130, bp: '100/60', etco2: 35
                },
                {
                    text: { tr: "Damar yolu açıp dil basacağıyla boğazı muayene ederek tanıyı doğrularım.", en: "Establish IV access and examine the throat with a tongue depressor to confirm the diagnosis." },
                    nextNode: 'c4_total_obstruction', score: -40, trauma: 20, spO2: 80, hr: 160, bp: '90/55', etco2: 0
                },
                {
                    text: { tr: "Supin yatırıp hızlı seri indüksiyon (RSI) ile kas gevşetici verip doğrudan entübe ederim.", en: "Lay supine, perform rapid sequence induction (RSI) with a muscle relaxant and intubate directly." },
                    nextNode: 'c4_laryngospasm', score: -20, trauma: 10, spO2: 78, hr: 165, bp: '85/50', etco2: 0
                }
            ]
        },
        'c4_inhalation_induced': {
            text: {
                tr: "İnhalasyon indüksiyonuyla çocuk yeterince derinleşti, spontan solunum korundu, SpO2 %96. KBB cerrahı rijit bronkoskopla yanı başınızda hazır. Laringoskopide şişmiş, 'kiraz kırmızısı' epiglot görüyorsunuz. Entübasyon için ne kullanırsınız?",
                en: "Inhalational induction achieved adequate depth with preserved spontaneous breathing, SpO2 96%. The ENT surgeon is ready with a rigid bronchoscope. On laryngoscopy you see a swollen 'cherry-red' epiglottis. What will you use to intubate?"
            },
            choices: [
                {
                    text: { tr: "Videolaringoskopla görüntüleyip normalden küçük çaplı, stileli bir tüple nazikçe entübe ederim.", en: "Visualize with a videolaryngoscope and intubate gently with a smaller, styletted tube." },
                    nextNode: 'c4_victory', score: 100, trauma: 5, spO2: 99, hr: 120, bp: '100/60', etco2: 38, requiredTool: 'video_laryngo'
                },
                {
                    text: { tr: "Fiberoptik bronkoskop eşliğinde tüpü vokal kordlardan nazikçe geçiririm.", en: "Pass the tube gently through the cords under flexible fiberoptic guidance." },
                    nextNode: 'c4_victory', score: 95, trauma: 0, spO2: 99, hr: 118, bp: '100/62', etco2: 38, requiredTool: 'fiberoptic'
                },
                {
                    text: { tr: "Görüş kısıtlı; bujiyle körlemesine epiglot altından geçmeyi zorlarım.", en: "View is limited; force a blind pass under the epiglottis with a bougie." },
                    nextNode: 'c4_trauma_bleed', score: -50, trauma: 45, spO2: 75, hr: 170, bp: '95/55', etco2: 0, requiredTool: 'bougie'
                }
            ]
        },
        'c4_total_obstruction': {
            text: {
                tr: "Dil basacağıyla muayene çocuğu ajite etti ve larinks tam tıkandı! Stridor sustu, göğüs hareketi yok, SpO2 %80'e düştü. Bu acil bir krizdir. Ne yaparsınız?",
                en: "Examination with the tongue depressor agitated the child and the larynx obstructed completely! Stridor ceased, no chest movement, SpO2 dropped to 80%. This is an emergency. What do you do?"
            },
            choices: [
                {
                    text: { tr: "%100 oksijen verip yanımdaki KBB cerrahından acil rijit bronkoskopi/trakeostomi ile havayolunu açmasını isterim.", en: "Give 100% oxygen and have the ENT surgeon urgently secure the airway with rigid bronchoscopy/tracheostomy." },
                    nextNode: 'c4_surgical_rescue', score: 70, trauma: 10, spO2: 94, hr: 120, bp: '100/65', etco2: 36
                },
                {
                    text: { tr: "Tüp ve stile alıp körlemesine entübasyon denerim.", en: "Grab a tube and stylet and attempt blind intubation." },
                    nextNode: 'c4_arrest', score: -60, trauma: 50, spO2: 0, hr: 0, bp: '0/0', etco2: 0
                }
            ]
        },
        'c4_laryngospasm': {
            text: {
                tr: "Kas gevşetici sonrası şiş epiglot nedeniyle ne maske ventilasyonu ne de entübasyon mümkün (pediatrik CVCI). SpO2 hızla %70'lere iniyor. Saniyeler kritik!",
                en: "After paralysis, the swollen epiglottis makes both mask ventilation and intubation impossible (pediatric CVCI). SpO2 is falling rapidly into the 70s. Seconds matter!"
            },
            choices: [
                {
                    text: { tr: "Yanımdaki KBB cerrahından derhal acil trakeostomi/rijit bronkoskopi ister, %100 O2 veririm.", en: "Have the ENT surgeon perform immediate tracheostomy/rigid bronchoscopy and give 100% O2." },
                    nextNode: 'c4_surgical_rescue', score: 60, trauma: 15, spO2: 93, hr: 125, bp: '95/60', etco2: 35
                },
                {
                    text: { tr: "Sugammadex verip kas gevşeticiyi geri çevirerek uyanmasını beklerim.", en: "Give Sugammadex to reverse paralysis and wait for spontaneous recovery." },
                    nextNode: 'c4_arrest', score: -40, trauma: 0, spO2: 0, hr: 0, bp: '0/0', etco2: 0, requiredTool: 'sugammadex'
                }
            ]
        },
        'c4_trauma_bleed': {
            text: {
                tr: "Buji zorlaması şiş epiglotta kanama ve tam obstrüksiyona yol açtı. SpO2 %75, çocuk bradikardiye giriyor. Tek kurtuluş cerrahi havayolu. Hamleniz?",
                en: "Forcing the bougie caused bleeding and complete obstruction of the swollen epiglottis. SpO2 75%, the child is becoming bradycardic. The only rescue is a surgical airway. Your move?"
            },
            choices: [
                {
                    text: { tr: "KBB cerrahından acil trakeostomi/rijit bronkoskopi ile havayolunu açmasını isterim.", en: "Have the ENT surgeon secure the airway by emergency tracheostomy/rigid bronchoscopy." },
                    nextNode: 'c4_surgical_rescue', score: 60, trauma: 10, spO2: 94, hr: 120, bp: '100/65', etco2: 36
                },
                {
                    text: { tr: "Görüşü düzeltmek için tekrar tekrar laringoskopi denerim.", en: "Repeatedly attempt laryngoscopy to improve the view." },
                    nextNode: 'c4_arrest', score: -50, trauma: 30, spO2: 0, hr: 0, bp: '0/0', etco2: 0
                }
            ]
        },
        'c4_victory': {
            text: {
                tr: "Mükemmel! Spontan solunum korunarak, deneyimli ekiple ve KBB güvencesinde havayolu travmasız emniyete alındı. SpO2 %99, ETCO2 trasesi normal. Çocuk güvende, yoğun bakıma alındı. Tebrikler!",
                en: "Excellent! With spontaneous breathing preserved, an experienced team and ENT backup, the airway was secured atraumatically. SpO2 99%, ETCO2 normal. The child is safe in the ICU. Congratulations!"
            },
            choices: [], isVictory: true
        },
        'c4_surgical_rescue': {
            text: {
                tr: "KBB cerrahı zamanında devreye girdi; cerrahi havayolu (trakeostomi) ile oksijenasyon sağlandı. SpO2 tekrar %94'e yükseldi ve kardiyak arrest önlendi. Kritik kriz başarıyla yönetildi!",
                en: "The ENT surgeon intervened in time; a surgical airway (tracheostomy) restored oxygenation. SpO2 recovered to 94% and cardiac arrest was averted. Critical crisis managed successfully!"
            },
            choices: [], isVictory: true
        },
        'c4_arrest': {
            text: {
                tr: "Kritik Hata! Şiş ve tıkalı bir pediatrik havayolunda zaman kaybedildi; çocuk hipoksik kardiyak arreste girdi. Epiglottitte havayolu spontan solunum korunarak ve cerrahi destek hazır iken yönetilmeliydi.",
                en: "Critical Failure! Time was lost in a swollen, obstructed pediatric airway; the child suffered hypoxic cardiac arrest. In epiglottitis the airway must be managed with spontaneous breathing preserved and surgical backup ready."
            },
            choices: [], isGameOver: true
        }
    },

    // -------------------------------------------------------------
    // CASE 5: FACE / INHALATION BURN INJURY
    // -------------------------------------------------------------
    'case5': {
        'start': {
            text: {
                tr: "Ev yangınından çıkarılan 35 yaşında erkek; yüzde yanıklar, burun kıllarında kurum, ses kısıklığı ve başlayan inspiratuar stridor mevcut. Bilinci açık, SpO2 %94. Havayolu ödemi hızla ilerleyebilir. Kararınız?",
                en: "A 35-year-old man rescued from a house fire has facial burns, soot in the nares, hoarse voice and early inspiratory stridor. Conscious, SpO2 94%. Airway edema may progress rapidly. Your decision?"
            },
            choices: [
                {
                    text: { tr: "Ödem ilerlemeden ERKEN entübasyon kararı alırım; deneyimli ekip, videolaringoskop ve büyük çaplı tüp hazırlar, eFONA setini açık tutarım.", en: "Decide on EARLY intubation before edema worsens; prepare an experienced team, videolaryngoscope and a large-bore tube, and keep the eFONA kit open." },
                    nextNode: 'c5_early_secure', score: 100, trauma: 0, spO2: 95, hr: 100, bp: '130/85', etco2: 35
                },
                {
                    text: { tr: "Stabil görünüyor; yoğun bakımda gözleme alıp entübasyonu erteler, gerekirse sonra müdahale ederim.", en: "He looks stable; observe in the ICU, defer intubation and intervene later if needed." },
                    nextNode: 'c5_delayed_edema', score: -50, trauma: 10, spO2: 84, hr: 120, bp: '140/90', etco2: 0
                },
                {
                    text: { tr: "Yüksek doz indüksiyon + tek deneme kör entübasyon ile hızlıca hallederim.", en: "Manage quickly with high-dose induction and a single blind intubation attempt." },
                    nextNode: 'c5_blind_fail', score: -30, trauma: 30, spO2: 80, hr: 125, bp: '135/85', etco2: 0
                }
            ]
        },
        'c5_early_secure': {
            text: {
                tr: "İndüksiyon sonrası laringoskopide supraglottik ödem ve kısmi görüş (Grade 3) var; tüpün geçişi zor. Hamleniz?",
                en: "After induction, laryngoscopy shows supraglottic edema and a partial view (Grade 3); the tube won't pass easily. Your move?"
            },
            choices: [
                {
                    text: { tr: "Videolaringoskop görüntüsünde bujiyi kordlardan geçirip tüpü buji üzerinden kaydırırım.", en: "Pass a bougie through the cords under videolaryngoscopy and rail the tube over it." },
                    nextNode: 'c5_victory', score: 100, trauma: 5, spO2: 99, hr: 90, bp: '125/80', etco2: 38, requiredTool: 'bougie'
                },
                {
                    text: { tr: "Spontan solunumu koruyarak fiberoptik bronkoskopla entübe ederim.", en: "Intubate with a flexible bronchoscope while preserving spontaneous breathing." },
                    nextNode: 'c5_victory', score: 95, trauma: 0, spO2: 99, hr: 88, bp: '125/80', etco2: 38, requiredTool: 'fiberoptic'
                },
                {
                    text: { tr: "Defalarca doğrudan laringoskopi denerim.", en: "Attempt direct laryngoscopy repeatedly." },
                    nextNode: 'c5_edema_cvci', score: -40, trauma: 45, spO2: 72, hr: 120, bp: '150/95', etco2: 0
                }
            ]
        },
        'c5_delayed_edema': {
            text: {
                tr: "Birkaç saat içinde havayolu ödemi ilerledi: tam stridor, ağız tabanı ve dilde şişlik. Entübasyon artık çok zor ve CVCI'ya yaklaşıyorsunuz. SpO2 %84. Ne yaparsınız?",
                en: "Within hours the airway edema progressed: full stridor, swelling of the floor of the mouth and tongue. Intubation is now very difficult and you are approaching CVCI. SpO2 84%. What do you do?"
            },
            choices: [
                {
                    text: { tr: "Geç kalındı; cerrahi havayolu (eFONA krikotiroidotomi) setini hazırlar ve uygularım.", en: "It's late; prepare and perform a surgical airway (eFONA cricothyroidotomy)." },
                    nextNode: 'c5_crico_rescue', score: 60, trauma: 15, spO2: 94, hr: 95, bp: '120/75', etco2: 36, requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "Hâlâ ısrarla oral entübasyon denemeye devam ederim.", en: "Persist with oral intubation attempts." },
                    nextNode: 'c5_arrest', score: -50, trauma: 35, spO2: 0, hr: 0, bp: '0/0', etco2: 0
                }
            ]
        },
        'c5_blind_fail': {
            text: {
                tr: "Kör entübasyon ödemli, frajil dokuda başarısız oldu ve kanamaya yol açtı. SpO2 %78, maske ventilasyonu da zorlaştı. Hamleniz?",
                en: "Blind intubation failed in the edematous, friable tissue and caused bleeding. SpO2 78%, mask ventilation is now difficult too. Your move?"
            },
            choices: [
                {
                    text: { tr: "Zaman kaybetmeden cerrahi havayolu (eFONA krikotiroidotomi) uygularım.", en: "Perform a surgical airway (eFONA cricothyroidotomy) without delay." },
                    nextNode: 'c5_crico_rescue', score: 80, trauma: 10, spO2: 95, hr: 95, bp: '120/75', etco2: 36, requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "Aynı yöntemle tekrar kör entübasyon denerim.", en: "Attempt blind intubation again with the same method." },
                    nextNode: 'c5_arrest', score: -60, trauma: 50, spO2: 0, hr: 0, bp: '0/0', etco2: 0
                }
            ]
        },
        'c5_edema_cvci': {
            text: {
                tr: "Tekrarlanan denemeler ödemi artırdı; artık ne entübe edebiliyor ne de havalandırabiliyorsunuz (CVCI). SpO2 %72. Tek seçenek?",
                en: "Repeated attempts worsened the edema; you can neither intubate nor ventilate (CVCI). SpO2 72%. The only option?"
            },
            choices: [
                {
                    text: { tr: "Acil cerrahi havayolu (eFONA krikotiroidotomi) uygularım.", en: "Perform emergency front-of-neck access (eFONA cricothyroidotomy)." },
                    nextNode: 'c5_crico_rescue', score: 80, trauma: 15, spO2: 94, hr: 95, bp: '115/70', etco2: 36, requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "Bir kez daha videolaringoskopiyle denerim.", en: "Try once more with videolaryngoscopy." },
                    nextNode: 'c5_arrest', score: -40, trauma: 25, spO2: 0, hr: 0, bp: '0/0', etco2: 0
                }
            ]
        },
        'c5_victory': {
            text: {
                tr: "Doğru karar! Ödem ilerlemeden havayolu erkenden ve travmasız emniyete alındı. SpO2 %99, ventilasyon doğrulandı. Erken entübasyon bu hastada hayat kurtardı. Tebrikler!",
                en: "Right call! The airway was secured early and atraumatically before edema progressed. SpO2 99%, ventilation confirmed. Early intubation saved this patient. Congratulations!"
            },
            choices: [], isVictory: true
        },
        'c5_crico_rescue': {
            text: {
                tr: "Krikotiroidotomi ile boyundan acil havayolu sağlandı; trakeaya kaflı tüp yerleştirildi ve ventilasyon doğrulandı. SpO2 %94'e yükseldi. Geç kalınsa da hasta kurtarıldı!",
                en: "Cricothyroidotomy established emergency front-of-neck access; a cuffed tube was placed and ventilation confirmed. SpO2 recovered to 94%. Late, but the patient was saved!"
            },
            choices: [], isVictory: true
        },
        'c5_arrest': {
            text: {
                tr: "Kritik Hata! İnhalasyon yaralanmasında havayolu ödemi öngörülmeli ve erken emniyete alınmalıydı. Gecikme ve tekrarlayan travmatik denemeler hipoksik arrestle sonuçlandı.",
                en: "Critical Failure! In inhalation injury, airway edema must be anticipated and the airway secured early. Delay and repeated traumatic attempts ended in hypoxic arrest."
            },
            choices: [], isGameOver: true
        }
    },

    // -------------------------------------------------------------
    // CASE 6: LUDWIG'S ANGINA (DEEP NECK INFECTION)
    // -------------------------------------------------------------
    'case6': {
        'start': {
            text: {
                tr: "32 yaşında kadın; diş enfeksiyonu sonrası ağız tabanında sert şişlik, dil elevasyonu, trismus (ağız açıklığı <2 cm), salya ve 'sıcak patates' sesi. Oturur pozisyonda rahat, yatınca boğuluyor. Ludwig anjini düşünüyorsunuz. Havayolu planınız?",
                en: "A 32-year-old woman after a dental infection has firm submandibular swelling, tongue elevation, trismus (mouth opening <2 cm), drooling and a 'hot potato' voice. Comfortable sitting, suffocates supine. You suspect Ludwig's angina. Your airway plan?"
            },
            choices: [
                {
                    text: { tr: "Spontan solunumu koruyarak, oturur pozisyonda topikal anesteziyle uyanık fiberoptik (nazal) entübasyon yaparım; cerrah trakeostomi için steril hazır beklesin.", en: "Awake fiberoptic (nasal) intubation in the sitting position under topical anesthesia, preserving spontaneous breathing, with a surgeon scrubbed and ready for tracheostomy." },
                    nextNode: 'c6_afoi_progress', score: 100, trauma: 0, spO2: 96, hr: 95, bp: '135/85', etco2: 35, requiredTool: 'fiberoptic'
                },
                {
                    text: { tr: "Standart RSI ile uyutup doğrudan laringoskopiyle entübe ederim.", en: "Induce with standard RSI and intubate by direct laryngoscopy." },
                    nextNode: 'c6_rsi_cvci', score: -50, trauma: 15, spO2: 78, hr: 130, bp: '150/95', etco2: 0
                },
                {
                    text: { tr: "Körlemesine LMA yerleştirip ventile etmeyi denerim.", en: "Insert an LMA blindly and attempt to ventilate." },
                    nextNode: 'c6_lma_fail6', score: -30, trauma: 10, spO2: 80, hr: 120, bp: '145/90', etco2: 0, requiredTool: 'lma_classic'
                }
            ]
        },
        'c6_afoi_progress': {
            text: {
                tr: "Nazal fiberoptik ilerlerken ödemli ama açık glottik açıklığı görüyorsunuz; hasta sakin ve spontan soluyor. Tüpü kordlardan geçirmeden önce ne yaparsınız?",
                en: "As the nasal fiberoptic advances you see an edematous but patent glottic opening; the patient is calm and breathing spontaneously. Before railroading the tube, what do you do?"
            },
            choices: [
                {
                    text: { tr: "Skopu kordlardan geçirip tüpü nazikçe kaydırır, yerleşimi kapnografi ile doğrularım.", en: "Pass the scope through the cords, gently rail the tube and confirm placement with capnography." },
                    nextNode: 'c6_victory', score: 100, trauma: 5, spO2: 99, hr: 88, bp: '125/80', etco2: 38
                },
                {
                    text: { tr: "Daha rahat çalışmak için derin sedasyon/kas gevşetici eklerim.", en: "Add deep sedation/muscle relaxant to work more comfortably." },
                    nextNode: 'c6_apnea_obstruct', score: -40, trauma: 5, spO2: 70, hr: 140, bp: '150/95', etco2: 0
                }
            ]
        },
        'c6_rsi_cvci': {
            text: {
                tr: "Kas gevşetici sonrası trismus nedeniyle ağız açılmadı; dil ve ağız tabanı ödemi maske ventilasyonunu da imkansız kıldı (CVCI). SpO2 %78 ve düşüyor. Hamleniz?",
                en: "After paralysis the trismus prevented mouth opening; tongue and floor-of-mouth edema made mask ventilation impossible too (CVCI). SpO2 78% and falling. Your move?"
            },
            choices: [
                {
                    text: { tr: "Zaman kaybetmeden cerrahi havayolu (eFONA krikotiroidotomi / acil trakeostomi) uygularım.", en: "Perform an emergency surgical airway (eFONA cricothyroidotomy / urgent tracheostomy) without delay." },
                    nextNode: 'c6_crico_rescue', score: 80, trauma: 15, spO2: 94, hr: 100, bp: '120/75', etco2: 36, requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "Sugammadex verip gevşeticiyi geri çevirerek uyanmasını beklerim.", en: "Give Sugammadex to reverse paralysis and wait for the patient to wake." },
                    nextNode: 'c6_arrest', score: -50, trauma: 0, spO2: 0, hr: 0, bp: '0/0', etco2: 0, requiredTool: 'sugammadex'
                }
            ]
        },
        'c6_lma_fail6': {
            text: {
                tr: "Ödemli ağız tabanı ve yükselmiş dil nedeniyle LMA doğru yerleşmedi; ventilasyon yok, SpO2 %72 (CVCI). Ne yaparsınız?",
                en: "Due to the edematous floor of mouth and elevated tongue the LMA seated poorly; no ventilation, SpO2 72% (CVCI). What do you do?"
            },
            choices: [
                {
                    text: { tr: "Acil cerrahi havayolu (eFONA krikotiroidotomi / trakeostomi) uygularım.", en: "Perform an emergency surgical airway (eFONA cricothyroidotomy / tracheostomy)." },
                    nextNode: 'c6_crico_rescue', score: 80, trauma: 15, spO2: 94, hr: 100, bp: '115/70', etco2: 36, requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "Stileli tüple körlemesine entübasyon denerim.", en: "Attempt blind intubation with a styletted tube." },
                    nextNode: 'c6_arrest', score: -60, trauma: 45, spO2: 0, hr: 0, bp: '0/0', etco2: 0
                }
            ]
        },
        'c6_apnea_obstruct': {
            text: {
                tr: "Sedasyon/gevşetici sonrası spontan solunum durdu ve ödemli havayolu tamamen tıkandı; ne ventilasyon ne de fiberoptik geçişi mümkün. SpO2 %70. Tek kurtuluş?",
                en: "After sedation/relaxant, spontaneous breathing stopped and the edematous airway obstructed completely; neither ventilation nor fiberoptic passage is possible. SpO2 70%. The only rescue?"
            },
            choices: [
                {
                    text: { tr: "Steril bekleyen cerrahdan acil trakeostomi / krikotiroidotomi ister, %100 O2 veririm.", en: "Have the scrubbed surgeon perform an emergency tracheostomy / cricothyroidotomy and give 100% O2." },
                    nextNode: 'c6_crico_rescue', score: 60, trauma: 15, spO2: 93, hr: 105, bp: '120/75', etco2: 35, requiredTool: 'crico_kit'
                },
                {
                    text: { tr: "Fiberoptiği tekrar tekrar geçirmeyi zorlarım.", en: "Force repeated fiberoptic passage attempts." },
                    nextNode: 'c6_arrest', score: -40, trauma: 20, spO2: 0, hr: 0, bp: '0/0', etco2: 0
                }
            ]
        },
        'c6_victory': {
            text: {
                tr: "Mükemmel! Spontan solunum korunarak uyanık fiberoptik entübasyon başarıyla tamamlandı, yerleşim kapnografiyle doğrulandı ve cerrahi yedek hazırdı. SpO2 %99. Ludwig anjini ders kitabına uygun yönetildi. Tebrikler!",
                en: "Excellent! Awake fiberoptic intubation was completed with spontaneous breathing preserved, placement confirmed by capnography, and a surgical backup ready. SpO2 99%. Textbook management of Ludwig's angina. Congratulations!"
            },
            choices: [], isVictory: true
        },
        'c6_crico_rescue': {
            text: {
                tr: "Steril bekleyen cerrah devreye girdi; boyundan cerrahi havayolu açıldı ve ventilasyon doğrulandı. SpO2 %94'e yükseldi, kardiyak arrest önlendi. Kriz kurtarma ile yönetildi!",
                en: "The scrubbed surgeon intervened; a surgical airway was established and ventilation confirmed. SpO2 recovered to 94%, cardiac arrest averted. Crisis managed by rescue!"
            },
            choices: [], isVictory: true
        },
        'c6_arrest': {
            text: {
                tr: "Kritik Hata! Trismuslu, ödemli Ludwig anjininde spontan solunumu kaybettiren veya travmatik girişimler felaketle sonuçlanır. Hasta hipoksik arreste girdi; doğru yol spontan solunumlu uyanık fiberoptik ve hazır cerrahi yedekti.",
                en: "Critical Failure! In Ludwig's angina with trismus and edema, losing spontaneous breathing or traumatic attempts is catastrophic. The patient arrested; the correct path was awake fiberoptic with spontaneous breathing and a ready surgical backup."
            },
            choices: [], isGameOver: true
        }
    }
};

// UI DOM Helper Functions
function getTranslation(key) {
    if (gameTranslations[gameLanguage] && gameTranslations[gameLanguage][key]) {
        return gameTranslations[gameLanguage][key];
    }
    return key;
}

function updateLocalization() {
    // Page translations
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (gameTranslations[gameLanguage][key]) {
            el.innerHTML = gameTranslations[gameLanguage][key];
        }
    });
    
    // Select input values
    const nickInput = document.getElementById('player-nick');
    if (nickInput && !nickInput.value) {
        nickInput.placeholder = gameLanguage === 'tr' ? 'Anestezist' : 'Anesthesiologist';
    }
    
    // Label translations
    const titleText = document.getElementById('game-title-text');
    if (titleText) titleText.innerHTML = getTranslation('game-title');
    
    // Overlay translation
    if (gameState === 'menu') {
        const overlayTitle = document.getElementById('overlay-title');
        if (overlayTitle) overlayTitle.innerText = getTranslation('overlay-title-menu');
        
        const overlaySub = document.getElementById('overlay-subtitle');
        if (overlaySub) overlaySub.innerText = getTranslation('overlay-subtitle-menu');
        
        const btnStart = document.getElementById('btn-start-text');
        if (btnStart) btnStart.innerText = getTranslation('btn-start-text');
        
        const caseTitle = document.getElementById('case-selection-title-lbl');
        if (caseTitle) caseTitle.innerText = getTranslation('case-selection-title');
        
        // Cases info
        const c1t = document.getElementById('case1-title-lbl');
        if (c1t) c1t.innerText = getTranslation('case1-title');
        const c1d = document.getElementById('case1-desc-lbl');
        if (c1d) c1d.innerText = getTranslation('case1-desc');
        
        const c2t = document.getElementById('case2-title-lbl');
        if (c2t) c2t.innerText = getTranslation('case2-title');
        const c2d = document.getElementById('case2-desc-lbl');
        if (c2d) c2d.innerText = getTranslation('case2-desc');
        
        const c3t = document.getElementById('case3-title-lbl');
        if (c3t) c3t.innerText = getTranslation('case3-title');
        const c3d = document.getElementById('case3-desc-lbl');
        if (c3d) c3d.innerText = getTranslation('case3-desc');

        const c4t = document.getElementById('case4-title-lbl');
        if (c4t) c4t.innerText = getTranslation('case4-title');
        const c4d = document.getElementById('case4-desc-lbl');
        if (c4d) c4d.innerText = getTranslation('case4-desc');

        const c5t = document.getElementById('case5-title-lbl');
        if (c5t) c5t.innerText = getTranslation('case5-title');
        const c5d = document.getElementById('case5-desc-lbl');
        if (c5d) c5d.innerText = getTranslation('case5-desc');

        const c6t = document.getElementById('case6-title-lbl');
        if (c6t) c6t.innerText = getTranslation('case6-title');
        const c6d = document.getElementById('case6-desc-lbl');
        if (c6d) c6d.innerText = getTranslation('case6-desc');
    }
    
    // HUD Labels
    const lblScore = document.getElementById('lbl-score-title');
    if (lblScore) lblScore.innerText = getTranslation('hud-score');
    
    const lblTrauma = document.getElementById('lbl-trauma-title');
    if (lblTrauma) lblTrauma.innerText = getTranslation('hud-trauma');
    
    const lblCart = document.getElementById('cart-title-txt');
    if (lblCart) lblCart.innerText = getTranslation('cart-title-lbl');
    
    // Cart Tabs
    const tabLaryngo = document.getElementById('tab-laryngo-txt');
    if (tabLaryngo) tabLaryngo.innerText = getTranslation('cart-tab-laryngo');
    const tabSga = document.getElementById('tab-sga-txt');
    if (tabSga) tabSga.innerText = getTranslation('cart-tab-sga');
    const tabAdjunct = document.getElementById('tab-adjunct-txt');
    if (tabAdjunct) tabAdjunct.innerText = getTranslation('cart-tab-adjunct');
    const tabEmergency = document.getElementById('tab-emergency-txt');
    if (tabEmergency) tabEmergency.innerText = getTranslation('cart-tab-emergency');
    
    updateSelectedToolUI();
    displayLeaderboard();
}

function changeGameLanguage(lang) {
    gameLanguage = lang;
    localStorage.setItem('anesthesia_pref_lang', lang);
    saveValueToCookie('anesthesia_pref_lang', lang);
    
    // Toggle active state in buttons
    document.querySelectorAll('.lang-btn').forEach(btn => btn.classList.remove('active'));
    document.getElementById(`btn-${lang}`).classList.add('active');
    
    updateLocalization();
    
    // If playing, re-render current state text
    if (gameState === 'playing') {
        renderCurrentNode();
    }
}

// Sound Synthesizer Engine (Web Audio API)
function initAudio() {
    if (audioCtx) return;
    try {
        const AudioContextClass = window.AudioContext || window.webkitAudioContext;
        audioCtx = new AudioContextClass();
    } catch (e) {
        console.error("Web Audio API not supported", e);
    }
}

function toggleAudio() {
    soundEnabled = !soundEnabled;
    const btn = document.getElementById('btn-audio');
    if (soundEnabled) {
        btn.innerHTML = '<i class="fa-solid fa-volume-high"></i>';
        if (audioCtx && audioCtx.state === 'suspended') {
            audioCtx.resume();
        }
    } else {
        btn.innerHTML = '<i class="fa-solid fa-volume-xmark"></i>';
    }
}

// Generate simple beep tone with dynamic pitch based on SpO2
function playPulseBeep(frequency, duration) {
    if (!soundEnabled || !audioCtx) return;
    
    // Resume context if suspended (browser security block)
    if (audioCtx.state === 'suspended') {
        audioCtx.resume();
    }
    
    try {
        const osc = audioCtx.createOscillator();
        const gainNode = audioCtx.createGain();
        
        osc.connect(gainNode);
        gainNode.connect(audioCtx.destination);
        
        osc.type = 'sine';
        osc.frequency.setValueAtTime(frequency, audioCtx.currentTime);
        
        gainNode.gain.setValueAtTime(0.04, audioCtx.currentTime); // Low volume
        gainNode.gain.exponentialRampToValueAtTime(0.0001, audioCtx.currentTime + duration);
        
        osc.start(audioCtx.currentTime);
        osc.stop(audioCtx.currentTime + duration);
    } catch (e) {
        console.error("Audio playback error", e);
    }
}

// Alarm sound synthesis (flashing rapid warning tone)
function playAlarmBeep(frequency, duration) {
    if (!soundEnabled || !audioCtx) return;
    try {
        const osc = audioCtx.createOscillator();
        const gainNode = audioCtx.createGain();
        
        osc.connect(gainNode);
        gainNode.connect(audioCtx.destination);
        
        osc.type = 'sawtooth'; // piercing tone
        osc.frequency.setValueAtTime(frequency, audioCtx.currentTime);
        
        gainNode.gain.setValueAtTime(0.03, audioCtx.currentTime);
        gainNode.gain.exponentialRampToValueAtTime(0.0001, audioCtx.currentTime + duration);
        
        osc.start(audioCtx.currentTime);
        osc.stop(audioCtx.currentTime + duration);
    } catch (e) {
        console.error("Alarm audio playback error", e);
    }
}

// Vitals Monitor Scheduler
function tickVitals() {
    if (gameState !== 'playing') return;
    
    // Smooth interpolation towards targets
    if (spO2 !== spO2Target) {
        const diff = spO2Target - spO2;
        spO2 += Math.sign(diff) * Math.min(Math.abs(diff), 1);
    }
    
    if (heartRate !== hrTarget) {
        const diff = hrTarget - heartRate;
        heartRate += Math.sign(diff) * Math.min(Math.abs(diff), 2);
    }
    
    // Map SpO2 to ETCO2 and BP states
    if (spO2 === 0) {
        bloodPressure = '0/0';
        etCO2 = 0;
    }
    
    // Update DOM Numbers
    const elSpO2 = document.getElementById('num-spO2');
    if (elSpO2) {
        elSpO2.innerText = spO2;
        // Color flashing on hypoxia
        if (spO2 < 70) {
            elSpO2.parentElement.className = 'vital-box red';
        } else if (spO2 < 90) {
            elSpO2.parentElement.className = 'vital-box yellow';
        } else {
            elSpO2.parentElement.className = 'vital-box cyan';
        }
    }
    
    const elHR = document.getElementById('num-hr');
    if (elHR) {
        elHR.innerText = heartRate;
        if (spO2 < 70) {
            elHR.parentElement.className = 'vital-box red';
        } else {
            elHR.parentElement.className = 'vital-box green';
        }
    }
    
    const elETCO2 = document.getElementById('num-etco2');
    if (elETCO2) {
        elETCO2.innerText = etCO2;
        elETCO2.parentElement.className = etCO2 > 0 ? 'vital-box yellow' : 'vital-box yellow';
    }
    
    const elBP = document.getElementById('num-bp');
    if (elBP) elBP.innerText = bloodPressure;
    
    // Trauma Progress
    const elTrauma = document.getElementById('val-trauma');
    if (elTrauma) elTrauma.innerText = traumaLevel + '%';
    const elTraumaFill = document.getElementById('trauma-progress-fill');
    if (elTraumaFill) elTraumaFill.style.width = traumaLevel + '%';
    
    // Schedule beep based on heart rate
    const now = Date.now();
    if (now >= nextBeepTime && heartRate > 0) {
        // Map SpO2 (30-100) to pitch frequency (200-900 Hz)
        const baseFreq = 200 + (Math.max(30, spO2) - 30) * 10;
        playPulseBeep(baseFreq, 0.15);
        
        // Hypoxia double-alarm beep
        if (spO2 < 80) {
            setTimeout(() => {
                playAlarmBeep(baseFreq + 100, 0.08);
            }, 180);
        }
        
        const beepIntervalMs = (60 / heartRate) * 1000;
        nextBeepTime = now + beepIntervalMs;
    }
    
    // Monitor screen flashing warning
    const monitorBox = document.getElementById('monitor-box');
    const alarmBanner = document.getElementById('monitor-alarm-banner');
    if (monitorBox) {
        if (spO2 < 80) {
            monitorBox.classList.add('alarm-active');
            if (alarmBanner) {
                alarmBanner.innerText = getTranslation('alarm-hypoxia');
                alarmBanner.style.color = 'var(--vital-red)';
            }
        } else if (etCO2 === 0 && gameState === 'playing' && spO2Target < 99) {
            monitorBox.classList.add('alarm-active');
            if (alarmBanner) {
                alarmBanner.innerText = getTranslation('alarm-apnea');
                alarmBanner.style.color = 'var(--vital-yellow)';
            }
        } else {
            monitorBox.classList.remove('alarm-active');
            if (alarmBanner) {
                alarmBanner.innerText = getTranslation('alarm-stable');
                alarmBanner.style.color = '#9ca3af';
            }
        }
    }
    
    // Game Over condition: SpO2 hits 0
    if (spO2 <= 0) {
        triggerGameOver();
    }
}

// Canvas Live Monitor Waveform Rendering Loop
function initCanvasDrawing() {
    ekgCanvas = document.getElementById('ekgCanvas');
    etco2Canvas = document.getElementById('etco2Canvas');
    
    if (ekgCanvas) {
        ekgCtx = ekgCanvas.getContext('2d');
        ekgCanvas.width = ekgCanvas.parentElement.clientWidth;
        ekgCanvas.height = 70;
    }
    
    if (etco2Canvas) {
        etco2Ctx = etco2Canvas.getContext('2d');
        etco2Canvas.width = etco2Canvas.parentElement.clientWidth;
        etco2Canvas.height = 70;
    }
    
    ekgX = 0;
    etco2X = 0;
    
    if (animFrameId) cancelAnimationFrame(animFrameId);
    renderWaves();
}

function renderWaves() {
    if (gameState !== 'playing') return;
    
    const width = ekgCanvas ? ekgCanvas.width : 0;
    
    if (ekgCtx && ekgCanvas) {
        // Clear sweep slice ahead of path to prevent trace overlapping
        ekgCtx.fillStyle = 'rgba(3, 7, 18, 0.1)';
        ekgCtx.fillRect(ekgX, 0, 15, 70);
        
        ekgCtx.strokeStyle = spO2 < 70 ? 'var(--vital-red)' : 'var(--vital-green)';
        ekgCtx.lineWidth = 2;
        ekgCtx.beginPath();
        
        let y = 35;
        if (heartRate > 0) {
            // Synthesize standard EKG waveform (P-QRS-T) based on current heart rate frequency
            const period = 60 / heartRate * 60; // frames per beat (approx at 60fps)
            const phase = (ekgX * 1.5) % period;
            
            if (phase < period * 0.1) { // P-Wave
                y = 35 - 3 * Math.sin((phase / (period * 0.1)) * Math.PI);
            } else if (phase >= period * 0.15 && phase < period * 0.2) { // Q-Wave
                y = 35 + 5 * ((phase - period * 0.15) / (period * 0.05));
            } else if (phase >= period * 0.2 && phase < period * 0.25) { // R-Spike
                const p = (phase - period * 0.2) / (period * 0.05);
                y = 40 - 35 * p;
            } else if (phase >= period * 0.25 && phase < period * 0.3) { // S-Drop
                const p = (phase - period * 0.25) / (period * 0.05);
                y = 5 + 40 * p;
            } else if (phase >= period * 0.3 && phase < period * 0.4) { // T-Wave
                const p = (phase - period * 0.3) / (period * 0.1);
                y = 35 - 8 * Math.sin(p * Math.PI);
            }
        }
        
        ekgCtx.moveTo(ekgX, y);
        ekgX += 1.5;
        if (ekgX >= width) ekgX = 0;
        
        // Draw little dot at cursor head
        ekgCtx.lineTo(ekgX, y);
        ekgCtx.stroke();
    }
    
    if (etco2Ctx && etco2Canvas) {
        etco2Ctx.fillStyle = 'rgba(3, 7, 18, 0.1)';
        etco2Ctx.fillRect(etco2X, 0, 15, 70);
        
        etco2Ctx.strokeStyle = 'var(--vital-yellow)';
        etco2Ctx.lineWidth = 2;
        etco2Ctx.beginPath();
        
        let y = 50; // flatline default
        if (etCO2 > 0 && heartRate > 0) {
            // Synthesize square alveolar ventilatory ETCO2 curve
            const period = 60 / 12 * 60; // resp rate fixed at 12/min for simplicity
            const phase = (etco2X * 0.8) % period;
            
            if (phase < period * 0.4) { // Expiration plateau
                y = 50 - (etCO2 * 0.9) * Math.sin((phase / (period * 0.4)) * Math.PI * 0.5 + Math.PI*0.1);
            } else if (phase >= period * 0.4 && phase < period * 0.5) { // Inspiration washin
                const p = (phase - period * 0.4) / (period * 0.1);
                y = 50 - (etCO2 * 0.9) * (1 - p);
            }
        }
        
        etco2Ctx.moveTo(etco2X, y);
        etco2X += 1.2;
        if (etco2X >= width) etco2X = 0;
        
        etco2Ctx.lineTo(etco2X, y);
        etco2Ctx.stroke();
    }
    
    animFrameId = requestAnimationFrame(renderWaves);
}

// Interactive Airway Cart Dock Tabs & Tool selection
function switchCartTab(tabId) {
    activeCartTab = tabId;
    
    // Update Tab headers
    document.querySelectorAll('.cart-tab-btn').forEach(btn => btn.classList.remove('active'));
    const activeBtn = document.querySelector(`.cart-tab-btn[onclick="switchCartTab('${tabId}')"]`);
    if (activeBtn) activeBtn.classList.add('active');
    
    // Render tools inside drawer
    renderToolsDrawer();
}

function renderToolsDrawer() {
    const container = document.getElementById('cart-drawer');
    if (!container) return;
    
    container.innerHTML = '';
    
    const categoryTools = cartTools.filter(t => t.category === activeCartTab);
    categoryTools.forEach(tool => {
        const card = document.createElement('div');
        card.className = `cart-tool-card ${selectedTool === tool.id ? 'selected' : ''}`;
        card.setAttribute('onclick', `selectCartTool('${tool.id}')`);
        
        const nameText = tool.name[gameLanguage] || tool.name.en;
        
        card.innerHTML = `
            <i class="${tool.icon} tool-icon"></i>
            <span class="tool-name">${nameText}</span>
        `;
        
        container.appendChild(card);
    });
}

function selectCartTool(toolId) {
    if (selectedTool === toolId) {
        selectedTool = null; // deselect
    } else {
        selectedTool = toolId;
    }
    
    renderToolsDrawer();
    updateSelectedToolUI();
}

function updateSelectedToolUI() {
    const elStatus = document.getElementById('selected-tool-status');
    if (!elStatus) return;
    
    if (selectedTool) {
        const toolObj = cartTools.find(t => t.id === selectedTool);
        const nameText = toolObj.name[gameLanguage] || toolObj.name.en;
        elStatus.innerHTML = `${getTranslation('status-selected')} <span style="color: var(--vital-green); font-weight:700;">${nameText}</span>`;
    } else {
        elStatus.innerHTML = `${getTranslation('status-selected')} <span style="color: #64748b;">${getTranslation('status-none')}</span>`;
    }
}

// Text Console Logger (Story flow)
function addLogEntry(text, type = 'normal') {
    const consoleBox = document.getElementById('console-logs');
    if (!consoleBox) return;
    
    const entry = document.createElement('div');
    entry.className = `log-entry ${type}`;
    entry.innerText = text;
    
    consoleBox.appendChild(entry);
    
    // Scroll to bottom smoothly
    consoleBox.scrollTop = consoleBox.scrollHeight;
}

// Game Core Logic & State Machine
function selectCase(caseNum) {
    currentCase = caseNum;
    document.querySelectorAll('.case-card').forEach(c => c.classList.remove('active'));
    document.getElementById(`case-${caseNum}-card`).classList.add('active');
}

function startSimulation() {
    initAudio();
    
    // Player nickname registration
    const inputNick = document.getElementById('player-nick');
    if (inputNick && inputNick.value.trim()) {
        playerNick = inputNick.value.trim().substring(0, 12);
        localStorage.setItem('laryngoscope_player_nick', playerNick);
        saveValueToCookie('laryngoscope_player_nick', playerNick);
    } else {
        playerNick = gameLanguage === 'tr' ? 'Anestezist' : 'Anesthesiologist';
    }
    
    const inputCountry = document.getElementById('player-country');
    if (inputCountry) {
        playerCountry = inputCountry.value;
        localStorage.setItem('laryngoscope_player_country', playerCountry);
        saveValueToCookie('laryngoscope_player_country', playerCountry);
    }
    
    // Set Vitals parameters
    score = 0;
    spO2 = 99;
    spO2Target = 99;
    heartRate = 75;
    hrTarget = 75;
    etCO2 = 38;
    bloodPressure = '120/80';
    traumaLevel = 0;
    selectedTool = null;
    elapsedSeconds = 0;
    
    // Hide Overlay
    const overlay = document.getElementById('gameOverlayScreen');
    if (overlay) overlay.classList.add('d-none');
    
    gameState = 'playing';
    currentNodeId = 'start';
    
    // Reset DOM elements
    const logs = document.getElementById('console-logs');
    if (logs) logs.innerHTML = '';
    
    // Initialize monitors & audio loop
    nextBeepTime = Date.now();
    initCanvasDrawing();
    
    // Start tick intervals
    if (gameTimerInterval) clearInterval(gameTimerInterval);
    gameTimerInterval = setInterval(() => {
        elapsedSeconds++;
        tickVitals();
    }, 1000);
    
    // Initialize Cart Tab
    switchCartTab('laryngoscopes');
    
    // Launch First node
    renderCurrentNode();
}

function renderCurrentNode() {
    const nodes = scenarioNodes[`case${currentCase}`];
    if (!nodes || !nodes[currentNodeId]) return;
    
    const node = nodes[currentNodeId];
    
    // Display story text in log
    const localizedText = node.text[gameLanguage] || node.text.en;
    addLogEntry(localizedText, 'normal');
    
    // Setup Choices pane
    const choicesBox = document.getElementById('choices-pane');
    if (!choicesBox) return;
    choicesBox.innerHTML = '';
    
    // If leaf node (victory / gameover)
    if (node.isVictory) {
        addLogEntry(gameLanguage === 'tr' ? "Vaka başarıyla güvene alındı!" : "Case secured successfully!", 'success');
        setTimeout(() => {
            triggerVictory();
        }, 3000);
        return;
    }
    
    if (node.isGameOver) {
        addLogEntry(gameLanguage === 'tr' ? "Kritik organ hasarı gelişti, hasta kaybedildi!" : "Critical organ damage developed, patient lost!", 'alert');
        setTimeout(() => {
            triggerGameOver();
        }, 3000);
        return;
    }
    
    // Render decision buttons
    node.choices.forEach((choice, idx) => {
        const btn = document.createElement('button');
        btn.className = 'choice-btn';
        
        let toolIndicator = '';
        if (choice.requiredTool) {
            const toolObj = cartTools.find(t => t.id === choice.requiredTool);
            const toolName = toolObj.name[gameLanguage] || toolObj.name.en;
            toolIndicator = `<span style="font-size: 10px; padding: 2px 5px; background: rgba(234, 179, 8, 0.15); color: var(--vital-yellow); border-radius:4px; margin-left: auto;">[${toolName}]</span>`;
        }
        
        btn.innerHTML = `
            <span class="choice-icon">${idx + 1}</span>
            <span>${choice.text[gameLanguage] || choice.text.en}</span>
            ${toolIndicator}
        `;
        
        btn.addEventListener('click', () => {
            handleChoiceSelection(choice);
        });
        
        choicesBox.appendChild(btn);
    });
}

function handleChoiceSelection(choice) {
    // Check if tool is required
    if (choice.requiredTool && selectedTool !== choice.requiredTool) {
        // Warning log in console
        const toolObj = cartTools.find(t => t.id === choice.requiredTool);
        const toolName = toolObj.name[gameLanguage] || toolObj.name.en;
        addLogEntry(gameLanguage === 'tr' ? `[UYARI] Bu hamle için entübasyon arabasından "${toolName}" seçmeniz gerekiyor!` : `[WARNING] You must select "${toolName}" from the airway cart first!`, 'system');
        
        // Play error warning audio frequency
        playAlarmBeep(150, 0.25);
        return;
    }
    
    // Apply Vitals changes
    if (choice.score) score += choice.score;
    if (choice.trauma) traumaLevel = Math.min(100, traumaLevel + choice.trauma);
    
    if (choice.spO2 !== undefined) spO2Target = choice.spO2;
    if (choice.hr !== undefined) hrTarget = choice.hr;
    if (choice.bp !== undefined) bloodPressure = choice.bp;
    if (choice.etco2 !== undefined) etCO2 = choice.etco2;
    
    // Move to next node
    currentNodeId = choice.nextNode;
    
    // Clear selected tool upon action consumption
    selectedTool = null;
    updateSelectedToolUI();
    renderToolsDrawer();
    
    renderCurrentNode();
}

function triggerGameOver() {
    gameState = 'gameover';
    if (gameTimerInterval) clearInterval(gameTimerInterval);
    if (animFrameId) cancelAnimationFrame(animFrameId);
    
    // Play flatline tone for 2 seconds
    if (soundEnabled && audioCtx) {
        try {
            const osc = audioCtx.createOscillator();
            const gain = audioCtx.createGain();
            osc.connect(gain);
            gain.connect(audioCtx.destination);
            osc.frequency.setValueAtTime(120, audioCtx.currentTime);
            gain.gain.setValueAtTime(0.04, audioCtx.currentTime);
            osc.start();
            osc.stop(audioCtx.currentTime + 2.0);
        } catch (e) {}
    }
    
    // Update Overlay DOM
    const overlay = document.getElementById('gameOverlayScreen');
    if (overlay) overlay.classList.remove('d-none');
    
    const title = document.getElementById('overlay-title');
    if (title) title.innerText = getTranslation('gameover-title');
    
    const sub = document.getElementById('overlay-subtitle');
    if (sub) sub.innerText = getTranslation('gameover-subtitle');
    
    // Hide inputs, show stats
    document.getElementById('nick-input-container').classList.add('d-none');
    document.getElementById('case-selection-area').classList.add('d-none');
    document.getElementById('stats-grid').classList.remove('d-none');
    
    document.getElementById('stat-box-score').innerText = score;
    
    // Save highscore
    saveScore(playerNick, score);
    
    const localHigh = parseInt(localStorage.getItem('ab_airway_highscore') || 0);
    const cookieHigh = parseInt(getValueFromCookie('ab_airway_highscore') || 0);
    const highscore = Math.max(localHigh, cookieHigh, score);
    localStorage.setItem('ab_airway_highscore', highscore);
    saveValueToCookie('ab_airway_highscore', highscore);
    
    document.getElementById('stat-box-highscore').innerText = highscore;
    
    // Action button
    const btnAction = document.getElementById('btn-overlay-action');
    if (btnAction) {
        btnAction.setAttribute('onclick', 'startSimulation()');
        btnAction.innerHTML = `<i class="fa-solid fa-rotate-right"></i> <span>${getTranslation('btn-play-again')}</span>`;
    }
    
    updateLocalization();
}

function triggerVictory() {
    gameState = 'victory';
    if (gameTimerInterval) clearInterval(gameTimerInterval);
    if (animFrameId) cancelAnimationFrame(animFrameId);
    
    // Update Overlay DOM
    const overlay = document.getElementById('gameOverlayScreen');
    if (overlay) overlay.classList.remove('d-none');
    
    const title = document.getElementById('overlay-title');
    if (title) title.innerText = getTranslation('victory-title');
    
    const sub = document.getElementById('overlay-subtitle');
    if (sub) sub.innerText = getTranslation('victory-subtitle');
    
    // Hide inputs, show stats
    document.getElementById('nick-input-container').classList.add('d-none');
    document.getElementById('case-selection-area').classList.add('d-none');
    document.getElementById('stats-grid').classList.remove('d-none');
    
    // Calculate final score bonuses
    const timeBonus = Math.max(0, 300 - elapsedSeconds);
    const traumaPenalty = traumaLevel * 2;
    const finalScore = Math.max(50, score + timeBonus - traumaPenalty);
    
    document.getElementById('stat-box-score').innerText = finalScore;
    
    // Save highscore
    saveScore(playerNick, finalScore);
    
    const localHigh = parseInt(localStorage.getItem('ab_airway_highscore') || 0);
    const cookieHigh = parseInt(getValueFromCookie('ab_airway_highscore') || 0);
    const highscore = Math.max(localHigh, cookieHigh, finalScore);
    localStorage.setItem('ab_airway_highscore', highscore);
    saveValueToCookie('ab_airway_highscore', highscore);
    
    document.getElementById('stat-box-highscore').innerText = highscore;
    
    // Action button
    const btnAction = document.getElementById('btn-overlay-action');
    if (btnAction) {
        btnAction.setAttribute('onclick', 'startSimulation()');
        btnAction.innerHTML = `<i class="fa-solid fa-rotate-right"></i> <span>${getTranslation('btn-play-again')}</span>`;
    }
    
    updateLocalization();
}

function displayLeaderboard() {
    const container = document.getElementById('leaderboard-list');
    if (!container) return;
    
    container.innerHTML = '';
    
    initLeaderboard();
    
    if (!leaderboard || leaderboard.length === 0) {
        container.innerHTML = `<div style="text-align:center; color:#64748b; padding:10px;">${gameLanguage === 'tr' ? 'Henüz kaydedilmiş skor yok' : 'No recorded scores yet'}</div>`;
        return;
    }
    
    leaderboard.forEach((entry, idx) => {
        const item = document.createElement('div');
        item.style.display = 'flex';
        item.style.justifyContent = 'space-between';
        item.style.padding = '4px 6px';
        item.style.borderRadius = '4px';
        
        // Zebra striping
        item.style.background = idx === 0 ? 'rgba(252, 211, 77, 0.12)' : (idx % 2 === 0 ? 'rgba(255,255,255,0.02)' : 'transparent');
        if (idx === 0) item.style.border = '1px solid rgba(252, 211, 77, 0.2)';
        
        const flag = entry.country === 'TR' ? '🇹🇷' : 
                     entry.country === 'US' ? '🇺🇸' : 
                     entry.country === 'GB' ? '🇬🇧' : 
                     entry.country === 'DE' ? '🇩🇪' : 
                     entry.country === 'FR' ? '🇫🇷' : 
                     entry.country === 'IT' ? '🇮🇹' : 
                     entry.country === 'ES' ? '🇪🇸' : 
                     entry.country === 'CA' ? '🇨🇦' : 
                     entry.country === 'AU' ? '🇦🇺' : '🏳️';
                     
        item.innerHTML = `
            <div style="display:flex; gap:6px; align-items:center;">
                <span style="color:${idx === 0 ? '#fbbf24' : '#94a3b8'}; font-weight:700;">#${idx + 1}</span>
                <span>${flag}</span>
                <span style="font-weight:600; color:${idx === 0 ? '#ffffff' : '#e2e8f0'};">${entry.name}</span>
            </div>
            <div style="font-weight:700; color:${idx === 0 ? '#fbbf24' : 'var(--accent-gold)'};">${entry.score} pts</div>
        `;
        container.appendChild(item);
    });
}

function returnToMenu() {
    gameState = 'menu';
    if (gameTimerInterval) clearInterval(gameTimerInterval);
    if (animFrameId) cancelAnimationFrame(animFrameId);
    
    const overlay = document.getElementById('gameOverlayScreen');
    if (overlay) overlay.classList.remove('d-none');
    
    document.getElementById('nick-input-container').classList.remove('d-none');
    document.getElementById('case-selection-area').classList.remove('d-none');
    document.getElementById('stats-grid').classList.add('d-none');
    
    // Action button
    const btnAction = document.getElementById('btn-overlay-action');
    if (btnAction) {
        btnAction.setAttribute('onclick', 'startSimulation()');
        btnAction.innerHTML = `<i class="fa-solid fa-play"></i> <span id="btn-start-text">${getTranslation('btn-start-text')}</span>`;
    }
    
    updateLocalization();
}

// Re-size live monitor canvases on viewport/orientation change (mobile robustness)
function resizeWaveCanvases() {
    if (gameState !== 'playing') return;
    if (ekgCanvas && ekgCanvas.parentElement) {
        ekgCanvas.width = ekgCanvas.parentElement.clientWidth;
        ekgCanvas.height = 70;
    }
    if (etco2Canvas && etco2Canvas.parentElement) {
        etco2Canvas.width = etco2Canvas.parentElement.clientWidth;
        etco2Canvas.height = 70;
    }
    ekgX = 0;
    etco2X = 0;
}

let _resizeWaveTimer = null;
window.addEventListener('resize', () => {
    clearTimeout(_resizeWaveTimer);
    _resizeWaveTimer = setTimeout(resizeWaveCanvases, 200);
});
window.addEventListener('orientationchange', () => {
    setTimeout(resizeWaveCanvases, 350);
});

// Window load triggers
window.addEventListener('load', () => {
    // Try to load saved language and nickname
    const savedLang = localStorage.getItem('anesthesia_pref_lang') || getValueFromCookie('anesthesia_pref_lang');
    if (savedLang) {
        gameLanguage = savedLang;
    } else {
        const userLang = navigator.language || navigator.userLanguage;
        gameLanguage = userLang.startsWith('tr') ? 'tr' : 'en';
    }
    
    const savedNick = localStorage.getItem('laryngoscope_player_nick') || getValueFromCookie('laryngoscope_player_nick');
    if (savedNick) {
        playerNick = savedNick;
        const inputNick = document.getElementById('player-nick');
        if (inputNick) inputNick.value = playerNick;
    }
    
    const savedCountry = localStorage.getItem('laryngoscope_player_country') || getValueFromCookie('laryngoscope_player_country');
    if (savedCountry) {
        playerCountry = savedCountry;
        const inputCountry = document.getElementById('player-country');
        if (inputCountry) inputCountry.value = playerCountry;
    }
    
    // Set language toggle buttons active state
    document.querySelectorAll('.lang-btn').forEach(btn => btn.classList.remove('active'));
    const activeBtn = document.getElementById(`btn-${gameLanguage}`);
    if (activeBtn) activeBtn.classList.add('active');
    
    updateLocalization();
    initLeaderboard();
    displayLeaderboard();
});
