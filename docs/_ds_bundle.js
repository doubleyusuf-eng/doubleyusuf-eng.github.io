/* @ds-bundle: {"format":3,"namespace":"AnesthesiaBriefsDesignSystem_a9434e","components":[{"name":"Badge","sourcePath":"components/core/Badge.jsx"},{"name":"BottomNav","sourcePath":"components/core/BottomNav.jsx"},{"name":"Button","sourcePath":"components/core/Button.jsx"},{"name":"CategoryChip","sourcePath":"components/core/CategoryChip.jsx"},{"name":"ClinicalNote","sourcePath":"components/core/ClinicalNote.jsx"},{"name":"DoseRow","sourcePath":"components/core/DoseRow.jsx"},{"name":"DrugListItem","sourcePath":"components/core/DrugListItem.jsx"},{"name":"PremiumCard","sourcePath":"components/core/PremiumCard.jsx"},{"name":"SearchField","sourcePath":"components/core/SearchField.jsx"},{"name":"SectionHeader","sourcePath":"components/core/SectionHeader.jsx"}],"sourceHashes":{".webexport/game-build/js/airway_game.js":"282e050beafd",".webexport/game-build/js/app.js":"98c81febe01d",".webexport/game-build/js/game.js":"2136925a258b","components/core/Badge.jsx":"15acb26611a4","components/core/BottomNav.jsx":"f89069833fcf","components/core/Button.jsx":"f90ff10c943c","components/core/CategoryChip.jsx":"bc4524b02245","components/core/ClinicalNote.jsx":"6f6eccbbdcad","components/core/DoseRow.jsx":"dd1e3dbedeeb","components/core/DrugListItem.jsx":"bc9ea6e8ec13","components/core/PremiumCard.jsx":"5aa262aa2a98","components/core/SearchField.jsx":"e7fb926b5a5a","components/core/SectionHeader.jsx":"dd05bf74b2a7","game-build/js/airway_game.js":"282e050beafd","game-build/js/app.js":"98c81febe01d","game-build/js/game.js":"2136925a258b","ui_kits/mobile_app/AlgorithmFlowScreen.jsx":"5a88fdf0e914","ui_kits/mobile_app/AlgorithmsScreen.jsx":"599b3d8de3e7","ui_kits/mobile_app/CalculatorScreen.jsx":"cfe6c30e24dd","ui_kits/mobile_app/DrugsScreen.jsx":"aaef285d50f5","ui_kits/mobile_app/HomeScreen.jsx":"e3c45ed6c054","ui_kits/mobile_app/PhoneFrame.jsx":"6d82f6a07b66","ui_kits/mobile_app/RegionalScreen.jsx":"73d3908bd1e4"},"inlinedExternals":[],"unexposedExports":[]} */

(() => {

const __ds_ns = (window.AnesthesiaBriefsDesignSystem_a9434e = window.AnesthesiaBriefsDesignSystem_a9434e || {});

const __ds_scope = {};

(__ds_ns.__errors = __ds_ns.__errors || []);

// .webexport/game-build/js/airway_game.js
try { (() => {
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
    leaderboard = Object.values(unique).sort((a, b) => b.score - a.score).slice(0, 10);
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
const cartTools = [{
  id: 'mac_laryngo',
  icon: 'fa-solid fa-wrench',
  name: {
    tr: 'Macintosh Bıçak',
    en: 'Mac Laryngoscope'
  },
  category: 'laryngoscopes'
}, {
  id: 'video_laryngo',
  icon: 'fa-solid fa-camera',
  name: {
    tr: 'Videolaringoskop',
    en: 'Videolaryngoscope'
  },
  category: 'laryngoscopes'
}, {
  id: 'fiberoptic',
  icon: 'fa-solid fa-staff-snake',
  name: {
    tr: 'Fiberoptik Bronkoskop',
    en: 'Flexible Bronchoscope'
  },
  category: 'laryngoscopes'
}, {
  id: 'lma_classic',
  icon: 'fa-solid fa-circle',
  name: {
    tr: 'LMA Klasik',
    en: 'LMA Classic'
  },
  category: 'sga'
}, {
  id: 'lma_supreme',
  icon: 'fa-solid fa-shield-halved',
  name: {
    tr: 'LMA Supreme / I-Gel',
    en: 'LMA Supreme / I-Gel'
  },
  category: 'sga'
}, {
  id: 'bougie',
  icon: 'fa-solid fa-compass-drafting',
  name: {
    tr: 'Kılavuz Buji',
    en: 'Airway Bougie'
  },
  category: 'adjuncts'
}, {
  id: 'sugammadex',
  icon: 'fa-solid fa-capsules',
  name: {
    tr: 'Sugammadex (İlaç)',
    en: 'Sugammadex (Meds)'
  },
  category: 'adjuncts'
}, {
  id: 'crico_kit',
  icon: 'fa-solid fa-kit-medical',
  name: {
    tr: 'Krikotiroidotomi Kiti',
    en: 'eFONA Cricothyroid Kit'
  },
  category: 'emergency'
}];

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
      choices: [{
        text: {
          tr: "Hastayı uyutmadan (lokal anesteziyle) uyanık fiberoptik entübasyon (AFOI) hazırlığı yaparım.",
          en: "Prepare for Awake Fiberoptic Intubation (AFOI) under local anesthesia."
        },
        nextNode: 'c1_awake_success',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38
      }, {
        text: {
          tr: "Hızlı seri indüksiyon (RSI) yapıp doğrudan Macintosh bıçakla entübe etmeye çalışırım.",
          en: "Perform Rapid Sequence Induction (RSI) and attempt direct laryngoscopy."
        },
        nextNode: 'c1_rsi_fail',
        score: 0,
        trauma: 15,
        spO2: 90,
        hr: 105,
        bp: '145/90',
        etco2: 0
      }, {
        text: {
          tr: "Sevofluran ile inhalasyon indüksiyonu yapıp spontan solunumu koruyarak ilerlerim.",
          en: "Inhalation induction with Sevoflurane, maintaining spontaneous breathing."
        },
        nextNode: 'c1_inhalation_obstruction',
        score: 50,
        trauma: 0,
        spO2: 95,
        hr: 90,
        bp: '130/80',
        etco2: 25
      }]
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
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) seçip havayolu bujisi (bougie) ile entübasyonu denerim.",
          en: "Select Videolaryngoscope (VL) and attempt intubation using a bougie."
        },
        nextNode: 'c1_vl_success',
        score: 80,
        trauma: 10,
        spO2: 98,
        hr: 85,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Hemen LMA Supreme yerleştirerek ventilasyonu sağlamayı denerim.",
          en: "Immediately place LMA Supreme to restore ventilation."
        },
        nextNode: 'c1_lma_success',
        score: 60,
        trauma: 5,
        spO2: 97,
        hr: 80,
        bp: '118/75',
        etco2: 36,
        requiredTool: 'lma_supreme'
      }, {
        text: {
          tr: "Körlemesine entübasyon için tüp içine metal stile yerleştirip tekrar denerim.",
          en: "Insert metal stylet into the tube and attempt blind intubation again."
        },
        nextNode: 'c1_blind_trauma',
        score: -50,
        trauma: 40,
        spO2: 78,
        hr: 120,
        bp: '160/95',
        etco2: 0
      }]
    },
    'c1_inhalation_obstruction': {
      text: {
        tr: "İnhalasyon indüksiyonu sırasında anestezinin derinleşmesiyle hastanın havayolu tamamen tıkandı (obstrüksiyon). Göğüs hareketleri paradoks hal aldı, SpO2 %85'e geriledi. Maskeyle havalandırma çabalarınız yetersiz kalıyor. İlk hamleniz nedir?",
        en: "During inhalation induction, deep anesthesia caused complete airway obstruction. Chest movements became paradoxical, SpO2 is down to 85%. Face mask ventilation fails. What is your immediate rescue action?"
      },
      choices: [{
        text: {
          tr: "Oral airway yerleştirip başa pozisyon vererek iki kişiyle maske ventilasyonu denerim.",
          en: "Insert oral airway, perform head-tilt/jaw-thrust, and attempt two-person ventilation."
        },
        nextNode: 'c1_mask_optimized',
        score: 80,
        trauma: 0,
        spO2: 97,
        hr: 85,
        bp: '125/80',
        etco2: 36
      }, {
        text: {
          tr: "Hemen LMA Classic takarak körleme ventilasyonu denerim.",
          en: "Immediately insert LMA Classic and attempt rescue ventilation."
        },
        nextNode: 'c1_lma_success',
        score: 70,
        trauma: 5,
        spO2: 97,
        hr: 80,
        bp: '118/75',
        etco2: 36,
        requiredTool: 'lma_classic'
      }]
    },
    'c1_mask_optimized': {
      text: {
        tr: "Optimizasyon başarılı! Çift el maske ventilasyonu ve oral airway sayesinde SpO2 %97'ye yükseldi. Ancak cerrahi için havayolunu emniyete almalısınız. Hangi cihazı seçeceksiniz?",
        en: "Optimization succeeded! Two-person ventilation and oral airway restored SpO2 to 97%. However, you must secure the airway for surgery. Which device will you select?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) yardımıyla entübasyon denerim.",
          en: "Attempt intubation under Videolaryngoscopy (VL)."
        },
        nextNode: 'c1_vl_success',
        score: 80,
        trauma: 5,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Sugammadex vererek hastayı uyandırıp uyanık fiberoptik entübasyona geri dönerim.",
          en: "Administer Sugammadex to wake the patient and revert to awake fiberoptic."
        },
        nextNode: 'c1_reverse_wake',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 35,
        requiredTool: 'sugammadex'
      }]
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
      choices: [{
        text: {
          tr: "LMA içinden fiberoptik eşliğinde entübasyon yaparım.",
          en: "Perform fiberoptic intubation through the LMA."
        },
        nextNode: 'c1_vl_success',
        score: 90,
        trauma: 0,
        spO2: 99,
        hr: 70,
        bp: '115/75',
        etco2: 38
      }, {
        text: {
          tr: "Hastayı uyandırıp uyanık fiberoptik entübasyona dönerim (Sugammadex ile).",
          en: "Wake the patient up using Sugammadex and return to awake fiberoptic."
        },
        nextNode: 'c1_reverse_wake',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 35,
        requiredTool: 'sugammadex'
      }]
    },
    'c1_blind_trauma': {
      text: {
        tr: "Sert stileli tüple körlemesine entübasyon denemesi havayolu dokusunda şiddetli kanama ve ödeme yol açtı! SpO2 %78'e geriledi. Kanama nedeniyle artık maskeyle de havalandıramıyorsunuz (CVCI riski). Nabız 120 bpm, taşikardi var. Ne yapacaksınız?",
        en: "The blind intubation attempt with a rigid stylet caused severe mucosal bleeding and edema! SpO2 dropped to 78%. Bleeding makes face mask ventilation impossible (CVCI warning). HR is 120 bpm. What is your next move?"
      },
      choices: [{
        text: {
          tr: "Sugammadex verip kas gevşeticiyi geri çevirerek uyanmasını beklerim.",
          en: "Give Sugammadex to reverse neuromuscular blockade and wait for return of ventilation."
        },
        nextNode: 'c1_hypoxia_arrest',
        score: -20,
        trauma: 0,
        spO2: 40,
        hr: 135,
        bp: '90/50',
        etco2: 0,
        requiredTool: 'sugammadex'
      }, {
        text: {
          tr: "Hemen krikotiroidotomi (eFONA) kitini hazırlayıp acil boyun cerrahisine geçerim.",
          en: "Immediately prepare the cricothyroidotomy (eFONA) kit for emergency neck access."
        },
        nextNode: 'c1_crico_rescue',
        score: 80,
        trauma: 10,
        spO2: 95,
        hr: 90,
        bp: '110/70',
        etco2: 36,
        requiredTool: 'crico_kit'
      }]
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
      choices: [{
        text: {
          tr: "Maske ventilasyonunu optimize ederim (oral/nasal airway takarım, çift el maske tutuşuna geçerim).",
          en: "Optimize mask ventilation (insert oral/nasal airway, use two-handed mask grip)."
        },
        nextNode: 'c2_mask_opt_try',
        score: 100,
        trauma: 0,
        spO2: 97,
        hr: 85,
        bp: '130/80',
        etco2: 30
      }, {
        text: {
          tr: "Macintosh laringoskopla doğrudan entübasyon denerim.",
          en: "Attempt direct laryngoscopy with Macintosh blade immediately."
        },
        nextNode: 'c2_laryngo_fail',
        score: -20,
        trauma: 15,
        spO2: 88,
        hr: 100,
        bp: '140/90',
        etco2: 0,
        requiredTool: 'mac_laryngo'
      }, {
        text: {
          tr: "Hemen bir Laryngeal Mask (LMA Classic) takarım.",
          en: "Insert Laryngeal Mask Airway (LMA Classic) immediately."
        },
        nextNode: 'c2_lma_fail_node',
        score: 50,
        trauma: 5,
        spO2: 85,
        hr: 110,
        bp: '150/95',
        etco2: 0,
        requiredTool: 'lma_classic'
      }]
    },
    'c2_mask_opt_try': {
      text: {
        tr: "Optimizasyona rağmen (çift el maske tutuşu ve oral airway) ventilasyon sağlanamadı! Hastanın anatomisinde derin bir laringospazm veya havayolu tıkanıklığı var. SpO2 %89'a geriledi, nabız 105 bpm'e yükseldi. Hızlı aksiyon almalısınız:",
        en: "Despite optimizing (two-handed grip and oral airway), face mask ventilation remains impossible! The patient has a severe airway obstruction. SpO2 dropped to 89%, HR rose to 105 bpm. Act fast:"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) seçip glottik açıklığı görerek entübasyon denerim.",
          en: "Select Videolaryngoscope (VL) and attempt intubation under direct visualization."
        },
        nextNode: 'c2_vl_first_attempt',
        score: 90,
        trauma: 10,
        spO2: 90,
        hr: 100,
        bp: '135/85',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Havayolunu kurtarmak için ikinci nesil LMA (LMA Supreme) yerleştiririm.",
          en: "Insert a second-generation SGA (LMA Supreme) to rescue the airway."
        },
        nextNode: 'c2_lma_fail_node',
        score: 80,
        trauma: 5,
        spO2: 80,
        hr: 115,
        bp: '155/95',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }]
    },
    'c2_laryngo_fail': {
      text: {
        tr: "Macintosh bıçakla yaptığınız laringoskopide vokal kordlar görülemedi (Grade 4 görünüm). SpO2 %84'e geriledi. Tekrar eden doğrudan laringoskopi denemesi havayolu ödemini artırır. Ne yapacaksınız?",
        en: "Direct laryngoscopy with Mac blade failed to show the vocal cords (Grade 4 view). SpO2 is now 84%. Repeated direct attempts will worsen edema. What will you do?"
      },
      choices: [{
        text: {
          tr: "LMA Supreme yerleştirerek acil havalandırma (solunum) desteği sağlarım.",
          en: "Place LMA Supreme to establish rescue ventilation."
        },
        nextNode: 'c2_lma_fail_node',
        score: 80,
        trauma: 5,
        spO2: 80,
        hr: 110,
        bp: '150/90',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }, {
        text: {
          tr: "Videolaringoskop (VL) ve Buji (Bougie) kombinasyonunu hazırlar ve denerim.",
          en: "Prepare and attempt Videolaryngoscopy (VL) with a Bougie."
        },
        nextNode: 'c2_vl_with_bougie_success',
        score: 90,
        trauma: 10,
        spO2: 98,
        hr: 85,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }]
    },
    'c2_lma_fail_node': {
      text: {
        tr: "Kritik Gelişme! Yerleştirdiğiniz LMA ventilasyon sağlamadı. Kaçak devam ediyor, göğüs hareket etmiyor ve SpO2 %72'ye kadar indi! Nabız 120 bpm (taşikardi). Hasta CVCI (Havalandırılamıyor, Entübe Edilemiyor) aşamasında. Ne yapacaksınız?",
        en: "Critical Event! The LMA failed to establish ventilation. Airway leak persists, no chest rise, and SpO2 plummeted to 72%! HR is 120 bpm. The patient is in a CVCI state. What is your choice?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden Krikotiroidotomi Kiti (eFONA) ile acil boyun cerrahisi uygularım.",
          en: "Immediately perform emergency front-of-neck access (eFONA) using cricothyroid kit."
        },
        nextNode: 'c2_crico_rescue_success',
        score: 100,
        trauma: 15,
        spO2: 95,
        hr: 90,
        bp: '110/70',
        etco2: 38,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Farklı bir laringoskop bıçağı takıp metal stileyle körlemesine entübasyon denerim.",
          en: "Mount a different laryngoscope blade and attempt blind intubation with a metal stylet."
        },
        nextNode: 'c2_blind_arrest',
        score: -100,
        trauma: 50,
        spO2: 40,
        hr: 140,
        bp: '80/40',
        etco2: 0
      }, {
        text: {
          tr: "Sugammadex vererek gevşetici etkiyi kaldırıp hastayı uyanmaya bırakırım.",
          en: "Administer Sugammadex to reverse paralysis and wait for spontaneous recovery."
        },
        nextNode: 'c2_suga_arrest',
        score: -50,
        trauma: 0,
        spO2: 45,
        hr: 135,
        bp: '85/45',
        etco2: 0,
        requiredTool: 'sugammadex'
      }]
    },
    'c2_vl_first_attempt': {
      text: {
        tr: "Videolaringoskop ile vokal kordlar kısmen görüntülendi (Grade 3a). Ancak tüp glottan geçmiyor. SpO2 %80'e geriledi. Havayolunda ödem oluşmaya başladı. Hamleniz?",
        en: "Videolaryngoscopy showed a partial view of vocal cords (Grade 3a). However, the tube won't pass. SpO2 dropped to 80%. Airway edema is developing. What is your action?"
      },
      choices: [{
        text: {
          tr: "Havayolu bujisi (bougie) kullanarak tüpü buji üzerinden kaydırıp entübe ederim.",
          en: "Use an airway bougie to guide the tube into the trachea."
        },
        nextNode: 'c2_vl_with_bougie_success',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38,
        requiredTool: 'bougie'
      }, {
        text: {
          tr: "Laryngoskopu geri çekip LMA Supreme yerleştirerek acil ventilasyon denerim.",
          en: "Withdraw laryngoscope and insert LMA Supreme to establish rescue ventilation."
        },
        nextNode: 'c2_lma_fail_node',
        score: 60,
        trauma: 5,
        spO2: 72,
        hr: 120,
        bp: '150/95',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }]
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
      choices: [{
        text: {
          tr: "Hızlı Seri İndüksiyon (RSI) uygularım, indüksiyon öncesi 3 dakika %100 preoksijenasyon yapar ve krikoid bası (Sellick manevrası) uygulatırım.",
          en: "Perform Rapid Sequence Induction (RSI) with 3 mins preoxygenation and cricoid pressure."
        },
        nextNode: 'c3_rsi_induced',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 80,
        bp: '130/80',
        etco2: 38
      }, {
        text: {
          tr: "Aspirasyon riskini önlemek için lokal anesteziyle uyanık fiberoptik entübasyon denerim.",
          en: "To prevent aspiration risk, attempt awake fiberoptic intubation."
        },
        nextNode: 'c3_fetal_bradycardia',
        score: -30,
        trauma: 0,
        spO2: 95,
        hr: 110,
        bp: '150/95',
        etco2: 35
      }]
    },
    'c3_fetal_bradycardia': {
      text: {
        tr: "Uyanık fiberoptik entübasyon girişimi tok karınlı gebede ajitasyona ve sürenin uzamasına yol açtı. Fetal bradikardi daha da kötüleşti (kalp atımı 60 bpm). Acil C-section gerekiyor! Ne yapacaksınız?",
        en: "Awake fiberoptic intubation attempt caused maternal agitation and delayed the procedure. Fetal bradycardia worsened (fetal HR 60 bpm). Emergency C-section is critical! What will you do?"
      },
      choices: [{
        text: {
          tr: "RSI indüksiyonuna geçerim, hızlıca Propofol + Süksinilkolin veririm.",
          en: "Switch to RSI induction with Propofol + Succinylcholine."
        },
        nextNode: 'c3_rsi_induced',
        score: 70,
        trauma: 0,
        spO2: 98,
        hr: 95,
        bp: '140/90',
        etco2: 0
      }]
    },
    'c3_rsi_induced': {
      text: {
        tr: "İndüksiyon sonrası Macintosh 3 bıçakla yapılan ilk entübasyon denemesinde tüpün özofagusa girdiği görüldü (ETCO2 düz trase, göğüs hareketleri yok). SpO2 %91'e iniyor. Ne yapacaksınız?",
        en: "Following induction, the first intubation attempt with Mac 3 blade resulted in esophageal intubation (flat ETCO2, no chest rise). SpO2 is dropping to 91%. What is your move?"
      },
      choices: [{
        text: {
          tr: "Tüpü çekerim, krikoid basıyı hafifçe gevşetip maskeyle nazikçe ventilasyon sağlayarak oksijenlendiririm.",
          en: "Remove the tube, slightly ease cricoid pressure, and gently mask ventilate to preoxygenate."
        },
        nextNode: 'c3_mask_ventilated',
        score: 100,
        trauma: 5,
        spO2: 97,
        hr: 90,
        bp: '120/80',
        etco2: 34
      }, {
        text: {
          tr: "Tüpü yerinde bırakıp doğrudan videolaringoskop (VL) ile ikinci entübasyon denemesine geçerim.",
          en: "Leave tube in place, switch to videolaryngoscope (VL), and attempt immediate re-intubation."
        },
        nextNode: 'c3_obstetric_hypoxia',
        score: -10,
        trauma: 15,
        spO2: 81,
        hr: 115,
        bp: '145/90',
        etco2: 0,
        requiredTool: 'video_laryngo'
      }]
    },
    'c3_mask_ventilated': {
      text: {
        tr: "Özofageal tüp çekildi. Nazik maske ventilasyonu ile SpO2 tekrar %97'ye yükseltildi. İkinci entübasyon denemesinde başarısızlığı önlemek için hangi cihazı ve tekniği kullanacaksınız?",
        en: "Esophageal tube removed. Gentle mask ventilation restored SpO2 to 97%. To prevent failure in the second attempt, which device and technique will you use?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) ve Buji (Bougie) kullanarak glottik açıklığı görerek entübasyon sağlarım.",
          en: "Use Videolaryngoscope (VL) and Bougie to intubate under direct visualization."
        },
        nextNode: 'c3_obstetric_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 80,
        bp: '120/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "LMA Supreme takarak havayolunu bu şekilde sürdürürüm.",
          en: "Place LMA Supreme and maintain ventilation through it."
        },
        nextNode: 'c3_lma_maintenance',
        score: 70,
        trauma: 5,
        spO2: 96,
        hr: 85,
        bp: '118/75',
        etco2: 36,
        requiredTool: 'lma_supreme'
      }]
    },
    'c3_lma_maintenance': {
      text: {
        tr: "LMA Supreme takıldı, ventilasyon doğrulandı. Ancak hasta tok karınlı gebe olduğundan LMA ile devam etmek yüksek aspirasyon riski taşır. Ayrıca cerrahi sezaryendir. Ne yapacaksınız?",
        en: "LMA Supreme placed, ventilation confirmed. However, maintaining ventilation via LMA in a full-stomach pregnant patient carries a high risk of aspiration. What is your choice?"
      },
      choices: [{
        text: {
          tr: "LMA içinden fiberoptik skop veya buji kılavuzluğunda entübasyon denerim.",
          en: "Attempt intubation through the LMA guided by a fiberoptic scope or bougie."
        },
        nextNode: 'c3_obstetric_victory',
        score: 95,
        trauma: 5,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38
      }, {
        text: {
          tr: "Operasyonu bu şekilde hızla tamamlatıp cerrahi bitiminde LMA'yı çekerim.",
          en: "Proceed with surgery under LMA and remove it immediately after delivery."
        },
        nextNode: 'c3_aspiration_arrest',
        score: -50,
        trauma: 0,
        spO2: 50,
        hr: 130,
        bp: '90/50',
        etco2: 0
      }]
    },
    'c3_obstetric_hypoxia': {
      text: {
        tr: "Ventilasyon olmadan yapılan ikinci entübasyon denemesi de başarısız oldu (Grade 4 görünüm). SpO2 %65'e düştü, hasta bradikardik (nabız 50 bpm). Ağır hipoksi ve kardiyak arrest riski mevcut! Acil eFONA kiti hazır mı?",
        en: "The second attempt without mask ventilation failed (Grade 4 view). SpO2 dropped to 65%, patient is bradycardic (HR 50 bpm). Risk of cardiac arrest is high! Is your eFONA kit ready?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden Krikotiroidotomi Kiti (eFONA) uygularım.",
          en: "Perform emergency front-of-neck access (eFONA) immediately."
        },
        nextNode: 'c3_crico_rescue_success',
        score: 100,
        trauma: 15,
        spO2: 95,
        hr: 90,
        bp: '110/70',
        etco2: 38,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "LMA Supreme takarak havalandırma denerim.",
          en: "Place LMA Supreme and attempt rescue ventilation."
        },
        nextNode: 'c3_hypoxia_arrest_obs',
        score: -20,
        trauma: 5,
        spO2: 40,
        hr: 45,
        bp: '70/35',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }]
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
      choices: [{
        text: {
          tr: "Çocuğu sakin tutar, ebeveynden ayırmam; ameliyathaneye alıp KBB ekibi (rijit bronkoskopi) hazırken sevofluran ile spontan solunumu koruyarak inhalasyon indüksiyonu yaparım.",
          en: "Keep the child calm with the parent present; transfer to OR and perform inhalational induction with sevoflurane preserving spontaneous breathing, with ENT (rigid bronchoscopy) standing by."
        },
        nextNode: 'c4_inhalation_induced',
        score: 100,
        trauma: 0,
        spO2: 96,
        hr: 130,
        bp: '100/60',
        etco2: 35
      }, {
        text: {
          tr: "Damar yolu açıp dil basacağıyla boğazı muayene ederek tanıyı doğrularım.",
          en: "Establish IV access and examine the throat with a tongue depressor to confirm the diagnosis."
        },
        nextNode: 'c4_total_obstruction',
        score: -40,
        trauma: 20,
        spO2: 80,
        hr: 160,
        bp: '90/55',
        etco2: 0
      }, {
        text: {
          tr: "Supin yatırıp hızlı seri indüksiyon (RSI) ile kas gevşetici verip doğrudan entübe ederim.",
          en: "Lay supine, perform rapid sequence induction (RSI) with a muscle relaxant and intubate directly."
        },
        nextNode: 'c4_laryngospasm',
        score: -20,
        trauma: 10,
        spO2: 78,
        hr: 165,
        bp: '85/50',
        etco2: 0
      }]
    },
    'c4_inhalation_induced': {
      text: {
        tr: "İnhalasyon indüksiyonuyla çocuk yeterince derinleşti, spontan solunum korundu, SpO2 %96. KBB cerrahı rijit bronkoskopla yanı başınızda hazır. Laringoskopide şişmiş, 'kiraz kırmızısı' epiglot görüyorsunuz. Entübasyon için ne kullanırsınız?",
        en: "Inhalational induction achieved adequate depth with preserved spontaneous breathing, SpO2 96%. The ENT surgeon is ready with a rigid bronchoscope. On laryngoscopy you see a swollen 'cherry-red' epiglottis. What will you use to intubate?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskopla görüntüleyip normalden küçük çaplı, stileli bir tüple nazikçe entübe ederim.",
          en: "Visualize with a videolaryngoscope and intubate gently with a smaller, styletted tube."
        },
        nextNode: 'c4_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 120,
        bp: '100/60',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Fiberoptik bronkoskop eşliğinde tüpü vokal kordlardan nazikçe geçiririm.",
          en: "Pass the tube gently through the cords under flexible fiberoptic guidance."
        },
        nextNode: 'c4_victory',
        score: 95,
        trauma: 0,
        spO2: 99,
        hr: 118,
        bp: '100/62',
        etco2: 38,
        requiredTool: 'fiberoptic'
      }, {
        text: {
          tr: "Görüş kısıtlı; bujiyle körlemesine epiglot altından geçmeyi zorlarım.",
          en: "View is limited; force a blind pass under the epiglottis with a bougie."
        },
        nextNode: 'c4_trauma_bleed',
        score: -50,
        trauma: 45,
        spO2: 75,
        hr: 170,
        bp: '95/55',
        etco2: 0,
        requiredTool: 'bougie'
      }]
    },
    'c4_total_obstruction': {
      text: {
        tr: "Dil basacağıyla muayene çocuğu ajite etti ve larinks tam tıkandı! Stridor sustu, göğüs hareketi yok, SpO2 %80'e düştü. Bu acil bir krizdir. Ne yaparsınız?",
        en: "Examination with the tongue depressor agitated the child and the larynx obstructed completely! Stridor ceased, no chest movement, SpO2 dropped to 80%. This is an emergency. What do you do?"
      },
      choices: [{
        text: {
          tr: "%100 oksijen verip yanımdaki KBB cerrahından acil rijit bronkoskopi/trakeostomi ile havayolunu açmasını isterim.",
          en: "Give 100% oxygen and have the ENT surgeon urgently secure the airway with rigid bronchoscopy/tracheostomy."
        },
        nextNode: 'c4_surgical_rescue',
        score: 70,
        trauma: 10,
        spO2: 94,
        hr: 120,
        bp: '100/65',
        etco2: 36
      }, {
        text: {
          tr: "Tüp ve stile alıp körlemesine entübasyon denerim.",
          en: "Grab a tube and stylet and attempt blind intubation."
        },
        nextNode: 'c4_arrest',
        score: -60,
        trauma: 50,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c4_laryngospasm': {
      text: {
        tr: "Kas gevşetici sonrası şiş epiglot nedeniyle ne maske ventilasyonu ne de entübasyon mümkün (pediatrik CVCI). SpO2 hızla %70'lere iniyor. Saniyeler kritik!",
        en: "After paralysis, the swollen epiglottis makes both mask ventilation and intubation impossible (pediatric CVCI). SpO2 is falling rapidly into the 70s. Seconds matter!"
      },
      choices: [{
        text: {
          tr: "Yanımdaki KBB cerrahından derhal acil trakeostomi/rijit bronkoskopi ister, %100 O2 veririm.",
          en: "Have the ENT surgeon perform immediate tracheostomy/rigid bronchoscopy and give 100% O2."
        },
        nextNode: 'c4_surgical_rescue',
        score: 60,
        trauma: 15,
        spO2: 93,
        hr: 125,
        bp: '95/60',
        etco2: 35
      }, {
        text: {
          tr: "Sugammadex verip kas gevşeticiyi geri çevirerek uyanmasını beklerim.",
          en: "Give Sugammadex to reverse paralysis and wait for spontaneous recovery."
        },
        nextNode: 'c4_arrest',
        score: -40,
        trauma: 0,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0,
        requiredTool: 'sugammadex'
      }]
    },
    'c4_trauma_bleed': {
      text: {
        tr: "Buji zorlaması şiş epiglotta kanama ve tam obstrüksiyona yol açtı. SpO2 %75, çocuk bradikardiye giriyor. Tek kurtuluş cerrahi havayolu. Hamleniz?",
        en: "Forcing the bougie caused bleeding and complete obstruction of the swollen epiglottis. SpO2 75%, the child is becoming bradycardic. The only rescue is a surgical airway. Your move?"
      },
      choices: [{
        text: {
          tr: "KBB cerrahından acil trakeostomi/rijit bronkoskopi ile havayolunu açmasını isterim.",
          en: "Have the ENT surgeon secure the airway by emergency tracheostomy/rigid bronchoscopy."
        },
        nextNode: 'c4_surgical_rescue',
        score: 60,
        trauma: 10,
        spO2: 94,
        hr: 120,
        bp: '100/65',
        etco2: 36
      }, {
        text: {
          tr: "Görüşü düzeltmek için tekrar tekrar laringoskopi denerim.",
          en: "Repeatedly attempt laryngoscopy to improve the view."
        },
        nextNode: 'c4_arrest',
        score: -50,
        trauma: 30,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c4_victory': {
      text: {
        tr: "Mükemmel! Spontan solunum korunarak, deneyimli ekiple ve KBB güvencesinde havayolu travmasız emniyete alındı. SpO2 %99, ETCO2 trasesi normal. Çocuk güvende, yoğun bakıma alındı. Tebrikler!",
        en: "Excellent! With spontaneous breathing preserved, an experienced team and ENT backup, the airway was secured atraumatically. SpO2 99%, ETCO2 normal. The child is safe in the ICU. Congratulations!"
      },
      choices: [],
      isVictory: true
    },
    'c4_surgical_rescue': {
      text: {
        tr: "KBB cerrahı zamanında devreye girdi; cerrahi havayolu (trakeostomi) ile oksijenasyon sağlandı. SpO2 tekrar %94'e yükseldi ve kardiyak arrest önlendi. Kritik kriz başarıyla yönetildi!",
        en: "The ENT surgeon intervened in time; a surgical airway (tracheostomy) restored oxygenation. SpO2 recovered to 94% and cardiac arrest was averted. Critical crisis managed successfully!"
      },
      choices: [],
      isVictory: true
    },
    'c4_arrest': {
      text: {
        tr: "Kritik Hata! Şiş ve tıkalı bir pediatrik havayolunda zaman kaybedildi; çocuk hipoksik kardiyak arreste girdi. Epiglottitte havayolu spontan solunum korunarak ve cerrahi destek hazır iken yönetilmeliydi.",
        en: "Critical Failure! Time was lost in a swollen, obstructed pediatric airway; the child suffered hypoxic cardiac arrest. In epiglottitis the airway must be managed with spontaneous breathing preserved and surgical backup ready."
      },
      choices: [],
      isGameOver: true
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
      choices: [{
        text: {
          tr: "Ödem ilerlemeden ERKEN entübasyon kararı alırım; deneyimli ekip, videolaringoskop ve büyük çaplı tüp hazırlar, eFONA setini açık tutarım.",
          en: "Decide on EARLY intubation before edema worsens; prepare an experienced team, videolaryngoscope and a large-bore tube, and keep the eFONA kit open."
        },
        nextNode: 'c5_early_secure',
        score: 100,
        trauma: 0,
        spO2: 95,
        hr: 100,
        bp: '130/85',
        etco2: 35
      }, {
        text: {
          tr: "Stabil görünüyor; yoğun bakımda gözleme alıp entübasyonu erteler, gerekirse sonra müdahale ederim.",
          en: "He looks stable; observe in the ICU, defer intubation and intervene later if needed."
        },
        nextNode: 'c5_delayed_edema',
        score: -50,
        trauma: 10,
        spO2: 84,
        hr: 120,
        bp: '140/90',
        etco2: 0
      }, {
        text: {
          tr: "Yüksek doz indüksiyon + tek deneme kör entübasyon ile hızlıca hallederim.",
          en: "Manage quickly with high-dose induction and a single blind intubation attempt."
        },
        nextNode: 'c5_blind_fail',
        score: -30,
        trauma: 30,
        spO2: 80,
        hr: 125,
        bp: '135/85',
        etco2: 0
      }]
    },
    'c5_early_secure': {
      text: {
        tr: "İndüksiyon sonrası laringoskopide supraglottik ödem ve kısmi görüş (Grade 3) var; tüpün geçişi zor. Hamleniz?",
        en: "After induction, laryngoscopy shows supraglottic edema and a partial view (Grade 3); the tube won't pass easily. Your move?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop görüntüsünde bujiyi kordlardan geçirip tüpü buji üzerinden kaydırırım.",
          en: "Pass a bougie through the cords under videolaryngoscopy and rail the tube over it."
        },
        nextNode: 'c5_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 90,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'bougie'
      }, {
        text: {
          tr: "Spontan solunumu koruyarak fiberoptik bronkoskopla entübe ederim.",
          en: "Intubate with a flexible bronchoscope while preserving spontaneous breathing."
        },
        nextNode: 'c5_victory',
        score: 95,
        trauma: 0,
        spO2: 99,
        hr: 88,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'fiberoptic'
      }, {
        text: {
          tr: "Defalarca doğrudan laringoskopi denerim.",
          en: "Attempt direct laryngoscopy repeatedly."
        },
        nextNode: 'c5_edema_cvci',
        score: -40,
        trauma: 45,
        spO2: 72,
        hr: 120,
        bp: '150/95',
        etco2: 0
      }]
    },
    'c5_delayed_edema': {
      text: {
        tr: "Birkaç saat içinde havayolu ödemi ilerledi: tam stridor, ağız tabanı ve dilde şişlik. Entübasyon artık çok zor ve CVCI'ya yaklaşıyorsunuz. SpO2 %84. Ne yaparsınız?",
        en: "Within hours the airway edema progressed: full stridor, swelling of the floor of the mouth and tongue. Intubation is now very difficult and you are approaching CVCI. SpO2 84%. What do you do?"
      },
      choices: [{
        text: {
          tr: "Geç kalındı; cerrahi havayolu (eFONA krikotiroidotomi) setini hazırlar ve uygularım.",
          en: "It's late; prepare and perform a surgical airway (eFONA cricothyroidotomy)."
        },
        nextNode: 'c5_crico_rescue',
        score: 60,
        trauma: 15,
        spO2: 94,
        hr: 95,
        bp: '120/75',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Hâlâ ısrarla oral entübasyon denemeye devam ederim.",
          en: "Persist with oral intubation attempts."
        },
        nextNode: 'c5_arrest',
        score: -50,
        trauma: 35,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c5_blind_fail': {
      text: {
        tr: "Kör entübasyon ödemli, frajil dokuda başarısız oldu ve kanamaya yol açtı. SpO2 %78, maske ventilasyonu da zorlaştı. Hamleniz?",
        en: "Blind intubation failed in the edematous, friable tissue and caused bleeding. SpO2 78%, mask ventilation is now difficult too. Your move?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden cerrahi havayolu (eFONA krikotiroidotomi) uygularım.",
          en: "Perform a surgical airway (eFONA cricothyroidotomy) without delay."
        },
        nextNode: 'c5_crico_rescue',
        score: 80,
        trauma: 10,
        spO2: 95,
        hr: 95,
        bp: '120/75',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Aynı yöntemle tekrar kör entübasyon denerim.",
          en: "Attempt blind intubation again with the same method."
        },
        nextNode: 'c5_arrest',
        score: -60,
        trauma: 50,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c5_edema_cvci': {
      text: {
        tr: "Tekrarlanan denemeler ödemi artırdı; artık ne entübe edebiliyor ne de havalandırabiliyorsunuz (CVCI). SpO2 %72. Tek seçenek?",
        en: "Repeated attempts worsened the edema; you can neither intubate nor ventilate (CVCI). SpO2 72%. The only option?"
      },
      choices: [{
        text: {
          tr: "Acil cerrahi havayolu (eFONA krikotiroidotomi) uygularım.",
          en: "Perform emergency front-of-neck access (eFONA cricothyroidotomy)."
        },
        nextNode: 'c5_crico_rescue',
        score: 80,
        trauma: 15,
        spO2: 94,
        hr: 95,
        bp: '115/70',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Bir kez daha videolaringoskopiyle denerim.",
          en: "Try once more with videolaryngoscopy."
        },
        nextNode: 'c5_arrest',
        score: -40,
        trauma: 25,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c5_victory': {
      text: {
        tr: "Doğru karar! Ödem ilerlemeden havayolu erkenden ve travmasız emniyete alındı. SpO2 %99, ventilasyon doğrulandı. Erken entübasyon bu hastada hayat kurtardı. Tebrikler!",
        en: "Right call! The airway was secured early and atraumatically before edema progressed. SpO2 99%, ventilation confirmed. Early intubation saved this patient. Congratulations!"
      },
      choices: [],
      isVictory: true
    },
    'c5_crico_rescue': {
      text: {
        tr: "Krikotiroidotomi ile boyundan acil havayolu sağlandı; trakeaya kaflı tüp yerleştirildi ve ventilasyon doğrulandı. SpO2 %94'e yükseldi. Geç kalınsa da hasta kurtarıldı!",
        en: "Cricothyroidotomy established emergency front-of-neck access; a cuffed tube was placed and ventilation confirmed. SpO2 recovered to 94%. Late, but the patient was saved!"
      },
      choices: [],
      isVictory: true
    },
    'c5_arrest': {
      text: {
        tr: "Kritik Hata! İnhalasyon yaralanmasında havayolu ödemi öngörülmeli ve erken emniyete alınmalıydı. Gecikme ve tekrarlayan travmatik denemeler hipoksik arrestle sonuçlandı.",
        en: "Critical Failure! In inhalation injury, airway edema must be anticipated and the airway secured early. Delay and repeated traumatic attempts ended in hypoxic arrest."
      },
      choices: [],
      isGameOver: true
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
      choices: [{
        text: {
          tr: "Spontan solunumu koruyarak, oturur pozisyonda topikal anesteziyle uyanık fiberoptik (nazal) entübasyon yaparım; cerrah trakeostomi için steril hazır beklesin.",
          en: "Awake fiberoptic (nasal) intubation in the sitting position under topical anesthesia, preserving spontaneous breathing, with a surgeon scrubbed and ready for tracheostomy."
        },
        nextNode: 'c6_afoi_progress',
        score: 100,
        trauma: 0,
        spO2: 96,
        hr: 95,
        bp: '135/85',
        etco2: 35,
        requiredTool: 'fiberoptic'
      }, {
        text: {
          tr: "Standart RSI ile uyutup doğrudan laringoskopiyle entübe ederim.",
          en: "Induce with standard RSI and intubate by direct laryngoscopy."
        },
        nextNode: 'c6_rsi_cvci',
        score: -50,
        trauma: 15,
        spO2: 78,
        hr: 130,
        bp: '150/95',
        etco2: 0
      }, {
        text: {
          tr: "Körlemesine LMA yerleştirip ventile etmeyi denerim.",
          en: "Insert an LMA blindly and attempt to ventilate."
        },
        nextNode: 'c6_lma_fail6',
        score: -30,
        trauma: 10,
        spO2: 80,
        hr: 120,
        bp: '145/90',
        etco2: 0,
        requiredTool: 'lma_classic'
      }]
    },
    'c6_afoi_progress': {
      text: {
        tr: "Nazal fiberoptik ilerlerken ödemli ama açık glottik açıklığı görüyorsunuz; hasta sakin ve spontan soluyor. Tüpü kordlardan geçirmeden önce ne yaparsınız?",
        en: "As the nasal fiberoptic advances you see an edematous but patent glottic opening; the patient is calm and breathing spontaneously. Before railroading the tube, what do you do?"
      },
      choices: [{
        text: {
          tr: "Skopu kordlardan geçirip tüpü nazikçe kaydırır, yerleşimi kapnografi ile doğrularım.",
          en: "Pass the scope through the cords, gently rail the tube and confirm placement with capnography."
        },
        nextNode: 'c6_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 88,
        bp: '125/80',
        etco2: 38
      }, {
        text: {
          tr: "Daha rahat çalışmak için derin sedasyon/kas gevşetici eklerim.",
          en: "Add deep sedation/muscle relaxant to work more comfortably."
        },
        nextNode: 'c6_apnea_obstruct',
        score: -40,
        trauma: 5,
        spO2: 70,
        hr: 140,
        bp: '150/95',
        etco2: 0
      }]
    },
    'c6_rsi_cvci': {
      text: {
        tr: "Kas gevşetici sonrası trismus nedeniyle ağız açılmadı; dil ve ağız tabanı ödemi maske ventilasyonunu da imkansız kıldı (CVCI). SpO2 %78 ve düşüyor. Hamleniz?",
        en: "After paralysis the trismus prevented mouth opening; tongue and floor-of-mouth edema made mask ventilation impossible too (CVCI). SpO2 78% and falling. Your move?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden cerrahi havayolu (eFONA krikotiroidotomi / acil trakeostomi) uygularım.",
          en: "Perform an emergency surgical airway (eFONA cricothyroidotomy / urgent tracheostomy) without delay."
        },
        nextNode: 'c6_crico_rescue',
        score: 80,
        trauma: 15,
        spO2: 94,
        hr: 100,
        bp: '120/75',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Sugammadex verip gevşeticiyi geri çevirerek uyanmasını beklerim.",
          en: "Give Sugammadex to reverse paralysis and wait for the patient to wake."
        },
        nextNode: 'c6_arrest',
        score: -50,
        trauma: 0,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0,
        requiredTool: 'sugammadex'
      }]
    },
    'c6_lma_fail6': {
      text: {
        tr: "Ödemli ağız tabanı ve yükselmiş dil nedeniyle LMA doğru yerleşmedi; ventilasyon yok, SpO2 %72 (CVCI). Ne yaparsınız?",
        en: "Due to the edematous floor of mouth and elevated tongue the LMA seated poorly; no ventilation, SpO2 72% (CVCI). What do you do?"
      },
      choices: [{
        text: {
          tr: "Acil cerrahi havayolu (eFONA krikotiroidotomi / trakeostomi) uygularım.",
          en: "Perform an emergency surgical airway (eFONA cricothyroidotomy / tracheostomy)."
        },
        nextNode: 'c6_crico_rescue',
        score: 80,
        trauma: 15,
        spO2: 94,
        hr: 100,
        bp: '115/70',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Stileli tüple körlemesine entübasyon denerim.",
          en: "Attempt blind intubation with a styletted tube."
        },
        nextNode: 'c6_arrest',
        score: -60,
        trauma: 45,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c6_apnea_obstruct': {
      text: {
        tr: "Sedasyon/gevşetici sonrası spontan solunum durdu ve ödemli havayolu tamamen tıkandı; ne ventilasyon ne de fiberoptik geçişi mümkün. SpO2 %70. Tek kurtuluş?",
        en: "After sedation/relaxant, spontaneous breathing stopped and the edematous airway obstructed completely; neither ventilation nor fiberoptic passage is possible. SpO2 70%. The only rescue?"
      },
      choices: [{
        text: {
          tr: "Steril bekleyen cerrahdan acil trakeostomi / krikotiroidotomi ister, %100 O2 veririm.",
          en: "Have the scrubbed surgeon perform an emergency tracheostomy / cricothyroidotomy and give 100% O2."
        },
        nextNode: 'c6_crico_rescue',
        score: 60,
        trauma: 15,
        spO2: 93,
        hr: 105,
        bp: '120/75',
        etco2: 35,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Fiberoptiği tekrar tekrar geçirmeyi zorlarım.",
          en: "Force repeated fiberoptic passage attempts."
        },
        nextNode: 'c6_arrest',
        score: -40,
        trauma: 20,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c6_victory': {
      text: {
        tr: "Mükemmel! Spontan solunum korunarak uyanık fiberoptik entübasyon başarıyla tamamlandı, yerleşim kapnografiyle doğrulandı ve cerrahi yedek hazırdı. SpO2 %99. Ludwig anjini ders kitabına uygun yönetildi. Tebrikler!",
        en: "Excellent! Awake fiberoptic intubation was completed with spontaneous breathing preserved, placement confirmed by capnography, and a surgical backup ready. SpO2 99%. Textbook management of Ludwig's angina. Congratulations!"
      },
      choices: [],
      isVictory: true
    },
    'c6_crico_rescue': {
      text: {
        tr: "Steril bekleyen cerrah devreye girdi; boyundan cerrahi havayolu açıldı ve ventilasyon doğrulandı. SpO2 %94'e yükseldi, kardiyak arrest önlendi. Kriz kurtarma ile yönetildi!",
        en: "The scrubbed surgeon intervened; a surgical airway was established and ventilation confirmed. SpO2 recovered to 94%, cardiac arrest averted. Crisis managed by rescue!"
      },
      choices: [],
      isVictory: true
    },
    'c6_arrest': {
      text: {
        tr: "Kritik Hata! Trismuslu, ödemli Ludwig anjininde spontan solunumu kaybettiren veya travmatik girişimler felaketle sonuçlanır. Hasta hipoksik arreste girdi; doğru yol spontan solunumlu uyanık fiberoptik ve hazır cerrahi yedekti.",
        en: "Critical Failure! In Ludwig's angina with trismus and edema, losing spontaneous breathing or traumatic attempts is catastrophic. The patient arrested; the correct path was awake fiberoptic with spontaneous breathing and a ready surgical backup."
      },
      choices: [],
      isGameOver: true
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
    const beepIntervalMs = 60 / heartRate * 1000;
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
      const phase = ekgX * 1.5 % period;
      if (phase < period * 0.1) {
        // P-Wave
        y = 35 - 3 * Math.sin(phase / (period * 0.1) * Math.PI);
      } else if (phase >= period * 0.15 && phase < period * 0.2) {
        // Q-Wave
        y = 35 + 5 * ((phase - period * 0.15) / (period * 0.05));
      } else if (phase >= period * 0.2 && phase < period * 0.25) {
        // R-Spike
        const p = (phase - period * 0.2) / (period * 0.05);
        y = 40 - 35 * p;
      } else if (phase >= period * 0.25 && phase < period * 0.3) {
        // S-Drop
        const p = (phase - period * 0.25) / (period * 0.05);
        y = 5 + 40 * p;
      } else if (phase >= period * 0.3 && phase < period * 0.4) {
        // T-Wave
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
      const phase = etco2X * 0.8 % period;
      if (phase < period * 0.4) {
        // Expiration plateau
        y = 50 - etCO2 * 0.9 * Math.sin(phase / (period * 0.4) * Math.PI * 0.5 + Math.PI * 0.1);
      } else if (phase >= period * 0.4 && phase < period * 0.5) {
        // Inspiration washin
        const p = (phase - period * 0.4) / (period * 0.1);
        y = 50 - etCO2 * 0.9 * (1 - p);
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
    item.style.background = idx === 0 ? 'rgba(252, 211, 77, 0.12)' : idx % 2 === 0 ? 'rgba(255,255,255,0.02)' : 'transparent';
    if (idx === 0) item.style.border = '1px solid rgba(252, 211, 77, 0.2)';
    const flag = entry.country === 'TR' ? '🇹🇷' : entry.country === 'US' ? '🇺🇸' : entry.country === 'GB' ? '🇬🇧' : entry.country === 'DE' ? '🇩🇪' : entry.country === 'FR' ? '🇫🇷' : entry.country === 'IT' ? '🇮🇹' : entry.country === 'ES' ? '🇪🇸' : entry.country === 'CA' ? '🇨🇦' : entry.country === 'AU' ? '🇦🇺' : '🏳️';
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
})(); } catch (e) { __ds_ns.__errors.push({ path: ".webexport/game-build/js/airway_game.js", error: String((e && e.message) || e) }); }

// .webexport/game-build/js/app.js
try { (() => {
/* -------------------------------------------------------------
   Anesthesia Briefs Landing Page Javascript App
   Dual-language handling (i18n), Geo-IP Routing & Interactive UI
------------------------------------------------------------- */

// Comprehensive i18n Translations Dictionary
const translations = {
  'tr': {
    // Navigation
    'nav-features': 'Özellikler',
    'nav-board-prep': 'Board Hazırlık',
    'nav-preview': 'Ekranlar',
    'nav-instagram': 'Instagram',
    'nav-download-soon': 'Çok Yakında',
    'nav-game-play': '<i class="fa-solid fa-gamepad"></i> Laringoskop Fırlat!',
    'nav-airway-play': '<i class="fa-solid fa-brain"></i> Zor Havayolu',
    // Hero
    'hero-badge': 'Anestezi Profesyonelleri İçin',
    'hero-title': 'Klinik Kararlarınızda <br><span class="gradient-text">Güvence Altındasınız</span>',
    'hero-desc': 'Acil durum algoritmalarına, gelişmiş ilaç doz hesaplayıcılarına, tıbbi literatür özetlerine ve yapay zeka destekli klinik asistanınıza tek bir uygulamadan anında erişin.',
    'coming-soon': 'Çok Yakında',
    // Features
    'features-section-title': 'Uygulama Özellikleri',
    'features-section-subtitle': 'Klinik güvenliği ve doğruluğu en üst düzeye çıkarmak için tasarlanan 4 ana sütun.',
    'feature-1-title': 'Acil Algoritmalar',
    'feature-1-desc': 'Hayatı tehdit eden anestezi krizlerine hızlı erişim. Her saniyenin önemli olduğu durumlarda adım adım acil durum kılavuzları.',
    'feature-2-title': 'Klinik Hesaplayıcılar',
    'feature-2-desc': 'Yetişkin, pediatrik ve obstetrik hastalar için hassas doz hesaplamaları ve anestezi puanlama sistemleri.',
    'feature-3-title': 'AI Klinik Asistan',
    'feature-3-desc': 'Klinik sorularınızı anında yanıtlayan, uluslararası kılavuzlarla eğitilmiş gelişmiş yapay zeka asistanı.',
    'feature-4-title': 'Son Literatür',
    'feature-4-desc': 'Anestezi alanındaki en son klinik yayınların, güncel kılavuzların ve bilimsel makalelerin özetleri.',
    // Premium Hub (Board Prep)
    'premium-title': 'Board Prep: Sınav Hazırlık Merkezi',
    'premium-subtitle': 'EDAIC (Avrupa Anesteziyoloji Kurulu) ve ABA (Amerikan Anesteziyoloji Kurulu) sınavlarına hazırlıkta en büyük asistanınız. Bilgilerinizi tazeleyin, eksiklerinizi tamamlayın.',
    'board-questions-title': 'Board Soruları & Açıklamalı Çözümler',
    'board-questions-desc': 'EDAIC Part I, ABA BASIC ve EDAIC Viva sınav standartlarına tam uyumlu, geniş kapsamlı SBA ve MTF soru bankası. Her sorunun altında ayrıntılı, referanslı klinik çözümler.',
    'spot-notes-title': 'Spot Bilgiler & Hızlı Tekrar',
    'spot-notes-desc': 'Sınavlar için özenle derlenmiş, nokta atışı kilit bilgiler. Kapsamlı farmakolojik özet tablolar, havayolu yönetim tipleri, kritik fizyoloji noktaları ve mutlaka bilinmesi gereken 1000+ spot bilgi.',
    'exam-simulator-title': 'Gerçekçi Sınav Simülatörü',
    'exam-simulator-desc': 'Açıklamaların gizlendiği, katı bir zamanlayıcının çalıştığı ve sınav stresini önceden yönetebilmeniz için tasarlanmış gerçek kurul formatında özel sınav simülatörü.',
    'analytics-title': 'Konu Odaklı Gelişim Analizi',
    'analytics-desc': 'Hatalı çözdüğünüz soruları analiz ederek hangi fizyoloji veya farmakoloji başlığında zayıf olduğunuzu tespit eden ve nokta atışı gelişim gösteren dinamik grafik kartları.',
    // Demo Tabs
    'demo-title': 'Modern ve Hızlı Arayüzü Deneyimleyin',
    'demo-desc': 'Anesthesia Briefs, en stresli klinik anlarda bile ihtiyacınız olan bilgiye en fazla iki dokunuşla ulaşmanızı sağlayacak sezgisel bir arayüze sahiptir. Sade kart tasarımı ve kategorize edilmiş menüleriyle dikkatinizi dağıtmaz.',
    'demo-step1-title': 'Kişiselleştirilmiş Karşılama',
    'demo-step1-desc': 'Güne güvenli ve hazırlıklı başlamanız için her sabah güncellenen klinik ipuçları.',
    'demo-algorithms-title': 'Acil Algoritmalar',
    'demo-algorithms-desc': 'Kritik durumlara özel geliştirilmiş interaktif ve adım adım acil durum protokolleri.',
    'demo-step2-title': 'Klinik Hesaplayıcılar',
    'demo-step2-desc': 'Saniyeler içinde hassas dozajları bulan evrensel klinik hesap makinesi arayüzü.',
    'demo-drugs-title': 'İlaç Kılavuzu & Bilgileri',
    'demo-drugs-desc': 'Anestezide kullanılan tüm ilaçların endikasyonları, infüzyon dozajları ve kritik uyarıları.',
    'demo-board-title': 'Board Sınavı Hazırlık',
    'demo-board-desc': 'Sınav tiplerine, soru istatistiklerine ve eksiklerinize göre özelleştirilmiş Board Prep paneli.',
    'demo-spot-title': 'Spot Bilgiler & Özetler',
    'demo-spot-desc': 'Konulara göre sınıflandırılmış, hızlı tekrarlar yapabileceğiniz interaktif spot bilgi kartları.',
    'demo-step3-title': 'Yapay Zeka Klinik Asistanı',
    'demo-step3-desc': 'Gelişmiş klinik aramalar yapabileceğiniz ve tıbbi rehberleri sorgulayabileceğiniz yapay zeka.',
    // Instagram
    'insta-title': 'Instagram\'da Bizi Takip Edin',
    'insta-subtitle': 'Her gün paylaşılan yeni vaka sunumları, anestezi ipuçları ve eğitici içeriklerle bilginizi tazeleyin.',
    'insta-tag-1': 'Klinik Vaka',
    'insta-tag-2': 'İlaç Etkileşimleri',
    'insta-tag-3': 'Yapay Zeka',
    'insta-caption-1': 'Rapid Sequence Induction (RSI) sırasında krikoid basınç uygulaması ve güncel kanıtlar...',
    'insta-caption-2': 'Sevofluran ve Karbondioksit absorbam ısınması riskleri. Kritik reaksiyon analizleri...',
    'insta-caption-3': 'Tıbbi yapay zeka klinik asistanımızın son literatür güncellemeleri ve entegrasyonu...',
    'insta-btn-follow': 'Takip Et',
    // Download & Footer
    'download-title': 'Güvenli Kararlar Cebinizde',
    'download-desc': 'Anestezi uygulamalarında hızı, güvenliği ve bilimselliği artırmak için geliştirilen Anesthesia Briefs\'i hemen ücretsiz indirin.',
    'footer-moto': 'Klinik kararlarınızda güvence altındasınız.',
    'footer-links-title': 'Hızlı Linkler',
    'footer-legal-title': 'Yasal',
    'footer-privacy': 'Gizlilik Politikası',
    'footer-terms': 'Kullanım Şartları'
  },
  'en': {
    // Navigation
    'nav-features': 'Features',
    'nav-board-prep': 'Board Prep',
    'nav-preview': 'Screens',
    'nav-instagram': 'Instagram',
    'nav-download-soon': 'Coming Soon',
    'nav-game-play': '<i class="fa-solid fa-gamepad"></i> Laryngoscope Throw!',
    'nav-airway-play': '<i class="fa-solid fa-brain"></i> Difficult Airway',
    // Hero
    'hero-badge': 'For Anesthesia Professionals',
    'hero-title': 'You are safe in <br><span class="gradient-text">Your Clinical Decisions</span>',
    'hero-desc': 'Instantly access emergency algorithms, advanced drug dosage calculators, medical literature summaries, and your AI-powered clinical assistant from a single application.',
    'coming-soon': 'Coming Soon',
    // Features
    'features-section-title': 'Application Features',
    'features-section-subtitle': '4 core pillars designed to maximize clinical safety and decision accuracy.',
    'feature-1-title': 'Emergency Algorithms',
    'feature-1-desc': 'Rapid access to life-threatening anesthesia crises. Step-by-step emergency guidance when every single second counts.',
    'feature-2-title': 'Clinical Calculators',
    'feature-2-desc': 'Precise dosage calculations and anesthesia scoring systems for adult, pediatric, and obstetric patients.',
    'feature-3-title': 'AI Clinical Assistant',
    'feature-3-desc': 'Advanced medically-tuned AI assistant that answers clinical queries and verifies medical guidelines in real time.',
    'feature-4-title': 'Latest Literature',
    'feature-4-desc': 'Stay ahead with clean summaries of the latest clinical publications, guidelines, and anesthesia briefs.',
    // Premium Hub (Board Prep)
    'premium-title': 'Board Prep: Exam Prep Hub',
    'premium-subtitle': 'Your major assistant preparing for EDAIC (European Diploma) and ABA (American Board) exams. Refresh your knowledge, bridge your clinical gaps.',
    'board-questions-title': 'Board Questions & Detailed Explanations',
    'board-questions-desc': 'A comprehensive SBA & MTF question database fully compliant with EDAIC Part I, ABA BASIC, and EDAIC Viva standards. Detailed, referenced clinical breakdowns.',
    'spot-notes-title': 'Spot Notes & High-Yield Review',
    'spot-notes-desc': 'High-yield points compiled meticulously for exams. Detailed pharmacological summaries, airway classifications, key physiology tips, and 1000+ must-know facts.',
    'exam-simulator-title': 'Strict Exam Simulator',
    'exam-simulator-desc': 'Beat exam anxiety early with randomized board-style simulations featuring hidden answers, strict timers, and detailed performance tracking.',
    'analytics-title': 'Tag-Based Progress Analytics',
    'analytics-desc': 'Deep mistake tracking that analyzes incorrect answers to flag exactly which tag (e.g. airway, cardiac) requires revision, backed by dynamic progress charts.',
    // Demo Tabs
    'demo-title': 'Experience the Modern & Swift Interface',
    'demo-desc': 'Anesthesia Briefs features an intuitive interface designed to let you reach the information you need in maximum two taps, even during the most stressful clinical moments. Minimal card design avoids clutter.',
    'demo-step1-title': 'Personalized Welcome',
    'demo-step1-desc': 'Daily updated clinical tips to start your day prepared and secure in your decisions.',
    'demo-algorithms-title': 'Emergency Algorithms',
    'demo-algorithms-desc': 'Interactive and step-by-step emergency protocols designed specifically for critical situations.',
    'demo-step2-title': 'Clinical Calculators',
    'demo-step2-desc': 'Universal calculator UI designed to resolve exact drug weights and scores in seconds.',
    'demo-drugs-title': 'Drug Guide & Formulary',
    'demo-drugs-desc': 'Indications, infusion dosages, and critical warnings for all anesthesia drugs.',
    'demo-board-title': 'Board Exam Prep',
    'demo-board-desc': 'Customized Board Prep portal based on exam boards, questions statistics, and your weak areas.',
    'demo-spot-title': 'Spot Notes & Bulletproof Tips',
    'demo-spot-desc': 'Interactive quick facts categorized by tags, engineered for rapid review sessions before exams.',
    'demo-step3-title': 'AI Clinical Assistant',
    'demo-step3-desc': 'Advanced search bar and guide queries powered by our medically fine-tuned AI model.',
    // Instagram
    'insta-title': 'Follow Us on Instagram',
    'insta-subtitle': 'Refresh your knowledge with daily clinical cases, quick anesthesia tips, and educational visual guides.',
    'insta-tag-1': 'Clinical Case',
    'insta-tag-2': 'Drug Interactions',
    'insta-tag-3': 'Artificial Intelligence',
    'insta-caption-1': 'Cricoid pressure application during Rapid Sequence Induction (RSI) and modern evidence...',
    'insta-caption-2': 'Carbon dioxide absorbent heating risks with Sevoflurane. Critical chemical reaction analysis...',
    'insta-caption-3': 'Latest updates and clinical guidelines integration of our medical AI clinical assistant...',
    'insta-btn-follow': 'Follow',
    // Download & Footer
    'download-title': 'Safe Decisions in Your Pocket',
    'download-desc': 'Download Anesthesia Briefs for free now, engineered to increase speed, safety, and scientific precision in anesthesia.',
    'footer-moto': 'You are safe in your clinical decisions.',
    'footer-links-title': 'Quick Links',
    'footer-legal-title': 'Legal',
    'footer-privacy': 'Privacy Policy',
    'footer-terms': 'Terms of Service'
  }
};

// Global App State
let currentLang = 'tr';
let activeDemoTab = 'screen-home';

// Initialize Page Function
document.addEventListener("DOMContentLoaded", function () {
  initLanguage();
  initMobileMenu();
  initPreloader();
});

// Premium 3D Preloader Screen Handler
function initPreloader() {
  const preloader = document.getElementById('preloader');

  // Animate and fade out after 1.8 seconds
  setTimeout(() => {
    preloader.classList.add('preloader-fade-out');
  }, 1800);
}

// Detect User Language on First Visit (Localstorage > Geolocation IP > Browser Default)
function initLanguage() {
  const savedLang = localStorage.getItem('anesthesia_pref_lang');
  if (savedLang) {
    changeLanguage(savedLang);
  } else {
    // Run light, fast IP geolocation lookup
    fetch('https://ipapi.co/json/').then(response => response.json()).then(data => {
      const country = data.country_code;
      if (country === 'TR') {
        changeLanguage('tr');
      } else {
        changeLanguage('en');
      }
    }).catch(() => {
      // Fallback to browser language
      const userLang = navigator.language || navigator.userLanguage;
      if (userLang.startsWith('tr')) {
        changeLanguage('tr');
      } else {
        changeLanguage('en');
      }
    });
  }
}

// Function to Change Language smoothly with CSS Transition
function changeLanguage(lang) {
  if (!translations[lang]) return;
  currentLang = lang;
  localStorage.setItem('anesthesia_pref_lang', lang);

  // Add fade class to trigger smooth transition
  document.body.style.opacity = '0.98';
  setTimeout(() => {
    // Update all elements containing data-i18n attribute
    document.querySelectorAll('[data-i18n]').forEach(element => {
      const key = element.getAttribute('data-i18n');
      if (translations[lang][key]) {
        element.innerHTML = translations[lang][key];
      }
    });

    // Update document language attribute
    document.documentElement.lang = lang;

    // Toggle Active state on Language Selection buttons
    document.querySelectorAll('.lang-btn').forEach(btn => btn.classList.remove('active'));
    document.getElementById(`btn-${lang}`).classList.add('active');

    // Corrected Swapping of Hero screenshots (TR version shows TR screen, EN shows EN screen)
    const mainPhoneScreen = document.getElementById('screen-main');
    const secondaryPhoneScreen = document.getElementById('screen-secondary');
    const demoImg = document.getElementById('interactive-demo-img');
    if (lang === 'tr') {
      mainPhoneScreen.src = 'assets/screenshots/welcome_tr.jpg';
      secondaryPhoneScreen.src = 'assets/screenshots/welcome_en.jpg';
    } else {
      mainPhoneScreen.src = 'assets/screenshots/welcome_en.jpg';
      secondaryPhoneScreen.src = 'assets/screenshots/welcome_tr.jpg';
    }

    // Trigger showcase update to sync with language
    updateDemoScreenshot(activeDemoTab);
    document.body.style.opacity = '1';
  }, 150);
}

// Mobile Responsive Navigation Hamburger Drawer Menu
function initMobileMenu() {
  const mobileMenuBtn = document.getElementById('mobileMenuBtn');
  const navMenu = document.getElementById('navMenu');
  mobileMenuBtn.addEventListener('click', () => {
    navMenu.classList.toggle('mobile-active');
    const isOpen = navMenu.classList.contains('mobile-active');
    mobileMenuBtn.innerHTML = isOpen ? '<i class="fa-solid fa-xmark"></i>' : '<i class="fa-solid fa-bars"></i>';
  });

  // Close mobile menu on clicking any navigation link
  document.querySelectorAll('.nav-menu a').forEach(link => {
    link.addEventListener('click', () => {
      navMenu.classList.remove('mobile-active');
      mobileMenuBtn.innerHTML = '<i class="fa-solid fa-bars"></i>';
    });
  });
}

// Interactive Feature Demo Screenshot Switcher
function showDemoScreen(screenKey) {
  activeDemoTab = screenKey;

  // Update step list active status
  const steps = document.querySelectorAll('.step-item');
  steps.forEach(step => step.classList.remove('active'));

  // Add active class to corresponding clicked list item
  event.currentTarget.classList.add('active');
  updateDemoScreenshot(screenKey);
}

// Separate helper to update screenshots smoothly
function updateDemoScreenshot(screenKey) {
  const imgEl = document.getElementById('interactive-demo-img');
  if (!imgEl) return;
  imgEl.style.opacity = '0';
  setTimeout(() => {
    if (screenKey === 'screen-home') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/welcome_tr.jpg' : 'assets/screenshots/welcome_en.jpg';
    } else if (screenKey === 'screen-algorithms') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/algorithms_tr.jpg' : 'assets/screenshots/algorithms_en.jpg';
    } else if (screenKey === 'screen-calc') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/calc_tr.jpg' : 'assets/screenshots/calc_en.jpg';
    } else if (screenKey === 'screen-drugs') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/drugs_tr.jpg' : 'assets/screenshots/drugs_en.jpg';
    } else if (screenKey === 'screen-board') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/board_tr.jpg' : 'assets/screenshots/board_en.jpg';
    } else if (screenKey === 'screen-spot') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/spot_tr.jpg' : 'assets/screenshots/spot_en.jpg';
    } else if (screenKey === 'screen-ai') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/ai_tr.jpg' : 'assets/screenshots/ai_en.jpg';
    }
    imgEl.style.opacity = '1';
  }, 150);
}
})(); } catch (e) { __ds_ns.__errors.push({ path: ".webexport/game-build/js/app.js", error: String((e && e.message) || e) }); }

// .webexport/game-build/js/game.js
try { (() => {
/* -------------------------------------------------------------
   Anesthesia Briefs Laryngoscope Launcher Hybrid Game Engine
   Compatible with WebGL 3D (Three.js) and 2D Canvas fallback
   ------------------------------------------------------------- */

// Lightweight Vector Utility Classes for WebGL-independent calculations
class GameVector3 {
  constructor(x = 0, y = 0, z = 0) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  copy(v) {
    this.x = v.x;
    this.y = v.y;
    this.z = v.z;
    return this;
  }
  set(x, y, z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }
  add(v) {
    this.x += v.x;
    this.y += v.y;
    this.z += v.z;
    return this;
  }
  addScaledVector(v, s) {
    this.x += v.x * s;
    this.y += v.y * s;
    this.z += v.z * s;
    return this;
  }
  distanceTo(v) {
    return Math.hypot(this.x - v.x, this.y - v.y, this.z - v.z);
  }
  clone() {
    return new GameVector3(this.x, this.y, this.z);
  }
}
class GameVector2 {
  constructor(x = 0, y = 0) {
    this.x = x;
    this.y = y;
  }
  add(v) {
    this.x += v.x;
    this.y += v.y;
    return this;
  }
}

// Global Game Variables
let gameMode = '2d'; // '2d' or '3d'
let is3DAvailable = false;
let canvas2D = null,
  ctx2d = null;
let bgCanvas = null; // Offscreen canvas for static background caching
let scene = null,
  camera = null,
  renderer = null;
let gameState = 'menu'; // 'menu', 'playing', 'gameover', 'levelup'
let language = 'tr';
let audioEnabled = true;
let audioCtx = null;

// Game State Values
let score = 0;
let level = 1;
let lives = 5;
let windX = 0; // Wind drift along X-axis
let windZ = 0; // Wind drift along Z-axis
let highscore = 0;
let combo = 0;

// Leaderboard Management
let playerNick = "Anestezist";
let playerCountry = "TR";
let leaderboard = [];

// New key to force a clean local storage reset for the user (cache-bust previous mocks)
const LEADERBOARD_STORAGE_KEY = 'anesthesia_briefs_leaderboard_v1';

// Cookie Helpers for Redundancy Score Saving (Wipes out iOS/Instagram WebView daily purges)
function saveLeaderboardToCookie(list) {
  try {
    const data = JSON.stringify(list);
    const b64 = btoa(unescape(encodeURIComponent(data)));
    const expiry = new Date();
    expiry.setFullYear(expiry.getFullYear() + 5);
    document.cookie = `ab_leaderboard=${b64}; expires=${expiry.toUTCString()}; path=/; SameSite=Strict`;
  } catch (e) {
    console.error("Cookie save failed", e);
  }
}
function loadLeaderboardFromCookie() {
  try {
    const name = "ab_leaderboard=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        const b64 = c.substring(name.length, c.length);
        const data = decodeURIComponent(escape(atob(b64)));
        return JSON.parse(data);
      }
    }
  } catch (e) {
    console.error("Cookie load failed", e);
  }
  return null;
}
function saveValueToCookie(key, value) {
  try {
    const expiry = new Date();
    expiry.setFullYear(expiry.getFullYear() + 5);
    document.cookie = `${key}=${encodeURIComponent(value)}; expires=${expiry.toUTCString()}; path=/; SameSite=Strict`;
  } catch (e) {
    console.error("Cookie value save failed", e);
  }
}
function getValueFromCookie(key) {
  try {
    const name = key + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return decodeURIComponent(c.substring(name.length, c.length));
      }
    }
  } catch (e) {
    console.error("Cookie value load failed", e);
  }
  return null;
}
function initLeaderboard() {
  const stored = localStorage.getItem(LEADERBOARD_STORAGE_KEY);
  const cookieStored = loadLeaderboardFromCookie();
  if (stored) {
    try {
      leaderboard = JSON.parse(stored);
    } catch (e) {
      leaderboard = [];
    }
  }

  // Sync logic: if localStorage is empty but cookie has scores, restore!
  if ((!leaderboard || leaderboard.length === 0) && cookieStored && cookieStored.length > 0) {
    leaderboard = cookieStored;
    localStorage.setItem(LEADERBOARD_STORAGE_KEY, JSON.stringify(leaderboard));
  }
  // If cookie is empty but localStorage has scores, sync to cookie!
  else if (leaderboard && leaderboard.length > 0 && (!cookieStored || cookieStored.length === 0)) {
    saveLeaderboardToCookie(leaderboard);
  }
  // If both have scores, merge them and keep the unique top 10
  else if (leaderboard && leaderboard.length > 0 && cookieStored && cookieStored.length > 0) {
    const merged = [...leaderboard, ...cookieStored];
    const unique = {};
    merged.forEach(item => {
      const key = `${item.name}_${item.country}_${item.score}`;
      unique[key] = item;
    });
    leaderboard = Object.values(unique);
    leaderboard.sort((a, b) => b.score - a.score);
    leaderboard = leaderboard.slice(0, 10);
    localStorage.setItem(LEADERBOARD_STORAGE_KEY, JSON.stringify(leaderboard));
    saveLeaderboardToCookie(leaderboard);
  }
}
function saveLeaderboard() {
  leaderboard.sort((a, b) => b.score - a.score);
  leaderboard = leaderboard.slice(0, 10);
  localStorage.setItem(LEADERBOARD_STORAGE_KEY, JSON.stringify(leaderboard));
  saveLeaderboardToCookie(leaderboard);
}
function addScoreToLeaderboard(name, country, newScore) {
  if (!name || name.trim() === "") {
    name = language === 'tr' ? 'Anestezist' : 'Anesthesiologist';
  }
  if (!country) country = 'TR';
  leaderboard.push({
    name: name.trim().substring(0, 12),
    country: country,
    score: newScore
  });
  saveLeaderboard();
}
function displayLeaderboard() {
  const listContainer = document.getElementById('leaderboard-list');
  if (!listContainer) return;
  listContainer.innerHTML = '';
  initLeaderboard();
  if (leaderboard.length === 0) {
    const placeholder = document.createElement('div');
    placeholder.style.textAlign = 'center';
    placeholder.style.padding = '15px';
    placeholder.style.color = 'rgba(255, 255, 255, 0.4)';
    placeholder.style.fontSize = '12px';
    placeholder.style.fontStyle = 'italic';
    placeholder.innerText = language === 'tr' ? 'Henüz kaydedilmiş skor bulunmuyor. İlk skoru sen yap!' : 'No scores recorded yet. Be the first to set a high score!';
    listContainer.appendChild(placeholder);
    return;
  }
  const flags = {
    TR: '🇹🇷',
    US: '🇺🇸',
    GB: '🇬🇧',
    DE: '🇩🇪',
    FR: '🇫🇷',
    IT: '🇮🇹',
    ES: '🇪🇸',
    CA: '🇨🇦',
    AU: '🇦🇺',
    OTH: '🏳️'
  };
  leaderboard.forEach((entry, index) => {
    const item = document.createElement('div');
    item.style.display = 'flex';
    item.style.justifyContent = 'space-between';
    item.style.padding = '4px 8px';
    item.style.borderRadius = '4px';
    item.style.marginBottom = '2px';
    const isCurrentPlayer = entry.name === playerNick && entry.score === score && score > 0;
    if (isCurrentPlayer) {
      item.style.background = 'rgba(252, 211, 77, 0.2)';
      item.style.color = '#FCD34D';
      item.style.fontWeight = '800';
    } else {
      item.style.background = 'rgba(255,255,255,0.02)';
    }
    let rankStr = `${index + 1}.`;
    if (index === 0) rankStr = '🥇';else if (index === 1) rankStr = '🥈';else if (index === 2) rankStr = '🥉';
    const flag = flags[entry.country] || '🏳️';
    item.innerHTML = `<span>${rankStr} ${flag} ${entry.name}</span><span>${entry.score}</span>`;
    listContainer.appendChild(item);
  });
}

// Aiming parameters
let aimPitch = 40; // Vertical angle (degrees)
let aimYaw = 0; // Horizontal angle (degrees)
let throwPower = 60; // Launch force percentage

// 3D Objects References
let anesthesiologistGroup = null,
  surgeonGroup = null,
  tableMesh = null;
let drapeMesh = null,
  ivPoleMesh = null,
  monitorMesh = null;
let laryngoscopeMesh = null;
let trajectoryLine = null;
let particles3D = [];

// 2D Effects State
let particles2D = [];
let monitorFlashTimer = 0;
let surgeonXOffset = 0;
let surgeonZ = 0.3; // matches initial anesthesiologist hand offset

// Physics Configs
const GRAVITY = 0.18;
const FLOOR_Y = 0;

// Camera Tracking Parameters (3D only)
let cameraMode = 'aim'; // 'aim', 'flight', 'hit'
let cameraTargetPos = null;
let cameraTargetLook = null;
let currentLookAt = null;

// Motion / Velocity values for projectile
const project = {
  pos: new GameVector3(),
  vel: new GameVector3(),
  isFlying: false,
  radius: 0.15,
  trailParticles: []
};

// Target Motion Config
let targetDirection = 1;
let targetSpeed = 0.03;

// Localization Dictionary
const translations = {
  tr: {
    title: "Laringoskop<span>Fırlat!</span>",
    score: "Skor",
    level: "Seviye",
    lives: "Hak",
    wind: "Laminer Akış",
    windLeft: "Sol Esinti",
    windRight: "Sağ Esinti",
    windNone: "Sakin",
    nickPrompt: "Oyuncu Adı (Nick):",
    leaderboardTitle: "Liderlik Tablosu (En İyi 10)",
    defaultNick: "Anestezist",
    startTitle: "Laringoskop Fırlatma Savaşı",
    startSubtitle: "Cerraha laringoskop fırlatıp vaka hakkındaki komik isteklerine cevap ver! Sliders ile nişan al veya ekrandan geriye çekip fırlat.",
    playBtn: "Savaşı Başlat",
    gameOverTitle: "Ameliyat Bitti!",
    gameOverSubtitle: "Cerrah dikişleri tamamladı. Skorun:",
    restartBtn: "Yeniden Dene",
    levelUpTitle: "Tebrikler!",
    levelUpSubtitle: "Cerrah daha hızlı hareket etmeye ve laminer hava akışı sertleşmeye başlıyor!",
    nextLevelBtn: "Sonraki Seviye",
    instructions: ["Laringoskobu fırlatmak için 'Fırlat!' butonuna basın.", "Dikey Açı (Pitch) ve Yatay Yön (Yaw) ayarlarını kaydırıcılarla ayarlayın.", "Kafadan vuruşlar (Headshot) fazladan 250 puan kazandırır.", "Karşı rüzgara (laminer akış) dikkat edin, havada sapmaya yol açar.", "Hastabaşı monitörüne çarparsanız yansıyarak fırlayacaktır.", "Arka arkaya isabetler puan çarpanını (Combo) tetikler!"],
    surgeonHits: ["Aaa! Macintosh 4 mü o?!", "Miyorelaksan yapıldı mı anestezi?!", "Tablayı biraz kaldırın!", "Işık çok az, göremiyorum!", "Bu hasta kımıldıyor!", "Biz ameliyatı bitiriyoruz!", "Dikişlerim koptu!", "Kim fırlattı bunu?!"],
    surgeonMisses: ["Hedefin dikişlerimden de kötü!", "Anestezi uyuyor mu arkada?", "Iskaladın, anestezist bey!", "Eter ekranı beni korur!", "Uyanamadın galiba daha?", "Bari propofol fırlatsaydın!"]
  },
  en: {
    title: "Laryngoscope<span>Launcher</span>",
    score: "Score",
    level: "Level",
    lives: "Lives",
    wind: "Laminar Flow",
    windLeft: "West Wind",
    windRight: "East Wind",
    windNone: "Calm",
    nickPrompt: "Player Name (Nick):",
    leaderboardTitle: "Leaderboard (Top 10)",
    defaultNick: "Anesthesiologist",
    startTitle: "Laryngoscope Throw Battle",
    startSubtitle: "Launch the laryngoscope at the surgeon and react to their funny surgical comments! Aim with sliders or drag on the viewport.",
    playBtn: "Start Battle",
    gameOverTitle: "Surgery Over!",
    gameOverSubtitle: "The surgeon finished the suturing. Your score:",
    restartBtn: "Try Again",
    levelUpTitle: "Well Done!",
    levelUpSubtitle: "The surgeon is moving faster and laminar flow wind is picking up!",
    nextLevelBtn: "Next Level",
    instructions: ["Press 'Throw!' button to launch the laryngoscope.", "Adjust Pitch (Vertical) and Yaw (Horizontal) angles with sliders.", "Headshots give a bonus +250 points.", "Watch out for the laminar flow wind, it deflects the blade.", "Hitting the patient monitor causes a fast ricochet.", "Successive hits trigger high score combo multipliers!"],
    surgeonHits: ["Ouch! Is that a Macintosh 4 blade?!", "Was muscle relaxant given, anesthesia?!", "Lower the table please!", "Not enough light here!", "This patient is moving!", "We are closing now!", "My sutures are ruined!", "Who threw that?!"],
    surgeonMisses: ["Your aim is worse than my suturing!", "Is anesthesia sleeping back there?", "You missed, anesthesia!", "The ether screen protects me!", "Time to wake up, doctor!", "At least throw some propofol next time!"]
  }
};

// UI Overlay Speech Bubble timer
let activeBubbleDOM = null;
let bubbleTimer = 0;

// Procedural Audio Synthesizer via Web Audio API
function initAudio() {
  if (!audioCtx) {
    audioCtx = new (window.AudioContext || window.webkitAudioContext)();
  }
}
function playSynthesizedSound(type) {
  if (!audioEnabled) return;
  initAudio();
  if (!audioCtx || audioCtx.state === 'suspended') {
    audioCtx.resume();
  }
  try {
    const dest = audioCtx.destination;
    if (type === 'launch') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'triangle';
      osc.frequency.setValueAtTime(160, audioCtx.currentTime);
      osc.frequency.exponentialRampToValueAtTime(700, audioCtx.currentTime + 0.16);
      gain.gain.setValueAtTime(0.18, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.16);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.16);
    } else if (type === 'hit') {
      const osc1 = audioCtx.createOscillator();
      const osc2 = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc1.connect(gain);
      osc2.connect(gain);
      gain.connect(dest);
      osc1.type = 'sine';
      osc2.type = 'sawtooth';
      osc1.frequency.setValueAtTime(800, audioCtx.currentTime);
      osc1.frequency.linearRampToValueAtTime(160, audioCtx.currentTime + 0.24);
      osc2.frequency.setValueAtTime(1100, audioCtx.currentTime);
      osc2.frequency.linearRampToValueAtTime(120, audioCtx.currentTime + 0.18);
      gain.gain.setValueAtTime(0.25, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.25);
      osc1.start();
      osc2.start();
      osc1.stop(audioCtx.currentTime + 0.25);
      osc2.stop(audioCtx.currentTime + 0.25);
    } else if (type === 'headshot') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'square';
      osc.frequency.setValueAtTime(1300, audioCtx.currentTime);
      osc.frequency.exponentialRampToValueAtTime(60, audioCtx.currentTime + 0.45);
      gain.gain.setValueAtTime(0.35, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.45);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.45);
    } else if (type === 'miss') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'sine';
      osc.frequency.setValueAtTime(120, audioCtx.currentTime);
      osc.frequency.linearRampToValueAtTime(25, audioCtx.currentTime + 0.22);
      gain.gain.setValueAtTime(0.2, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.22);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.22);
    } else if (type === 'monitor') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'sine';
      osc.frequency.setValueAtTime(987.77, audioCtx.currentTime); // B5 tone
      gain.gain.setValueAtTime(0.2, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.15);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.15);
    }
  } catch (e) {
    console.error("Audio syntheziser failed:", e);
  }
}

// Projection Cabinet Mapping to convert 3D coordinates (x, y, z) into 2D Screen (px, py)
// Game coordinates:
// X: [-9.0, 9.0] -> Canvas X
// Y: [0.0, 6.0]  -> Canvas Y (inverted)
// Z: [-2.0, 2.0] -> Depth shift
function project3DTo2D(x, y, z) {
  const scaleX = 60;
  const scaleY = 91.6;

  // Core orthographic mapping
  const baseCanvasX = 640 + x * scaleX;
  const baseCanvasY = 600 - y * scaleY;

  // Add Cabinet depth projection (Z adds offset to both coordinates)
  return {
    x: baseCanvasX + z * 16,
    y: baseCanvasY - z * 8
  };
}

// Particle Bursts Generator
function spawnParticles(pos, count, type) {
  if (gameMode === '3d' && is3DAvailable) {
    spawnParticles3D(pos, count, type);
  } else {
    const screenPos = project3DTo2D(pos.x, pos.y, pos.z);
    for (let i = 0; i < count; i++) {
      let size = Math.random() * 5 + 3;
      let color = '#CBD5E1'; // standard sparks

      if (type === 'hit') {
        color = Math.random() > 0.4 ? '#FCD34D' : '#10B981';
      } else if (type === 'headshot') {
        color = Math.random() > 0.3 ? '#3B82F6' : '#93C5FD';
      } else if (type === 'miss') {
        color = '#8A8D8B';
      }
      let angle = Math.random() * Math.PI * 2;
      let speed = Math.random() * 4 + 1.5;
      particles2D.push({
        pos: new GameVector2(screenPos.x, screenPos.y),
        vel: new GameVector2(Math.cos(angle) * speed, Math.sin(angle) * speed - (Math.random() * 2 + 0.5)),
        color: color,
        size: size,
        alpha: 1.0,
        decay: Math.random() * 0.03 + 0.015
      });
    }
  }
}

// 3D Particle Spawner (Three.js fallback logic)
function spawnParticles3D(pos, count, type) {
  if (!scene || !THREE) return;
  for (let i = 0; i < count; i++) {
    let size = Math.random() * 0.08 + 0.04;
    let color = 0xCBD5E1;
    if (type === 'hit') {
      color = Math.random() > 0.4 ? 0xC2A267 : 0x10B981;
    } else if (type === 'headshot') {
      color = Math.random() > 0.3 ? 0x3B82F6 : 0x93C5FD;
    } else if (type === 'miss') {
      color = 0x8A8D8B;
    }
    const mat = new THREE.MeshBasicMaterial({
      color: color,
      transparent: true,
      opacity: 1.0
    });
    const geom = new THREE.BoxGeometry(size, size, size);
    const mesh = new THREE.Mesh(geom, mat);
    mesh.position.set(pos.x, pos.y, pos.z);
    let angle = Math.random() * Math.PI * 2;
    let speed = Math.random() * 0.15 + 0.05;
    const pObj = {
      mesh: mesh,
      vel: new THREE.Vector3(Math.cos(angle) * speed, Math.random() * 0.15 + 0.02, Math.sin(angle) * speed),
      alpha: 1.0,
      decay: Math.random() * 0.03 + 0.015
    };
    scene.add(mesh);
    particles3D.push(pObj);
  }
}

// Speech Bubble HTML Dialog boxes
function showHTMLSpeechBubble(text) {
  if (activeBubbleDOM) {
    activeBubbleDOM.remove();
  }
  const container = document.querySelector('.canvas-container');
  const bubble = document.createElement('div');
  bubble.className = 'surgeon-speech-bubble';
  bubble.innerText = text;
  bubble.style.position = 'absolute';
  bubble.style.right = '10%';
  bubble.style.top = '15%';
  bubble.style.background = 'white';
  bubble.style.color = '#0F172A';
  bubble.style.padding = '12px 20px';
  bubble.style.borderRadius = '12px';
  bubble.style.fontWeight = '700';
  bubble.style.fontFamily = 'Inter, sans-serif';
  bubble.style.fontSize = '14px';
  bubble.style.boxShadow = '0 10px 25px rgba(0,0,0,0.3)';
  bubble.style.border = '2px solid #C2A267';
  bubble.style.zIndex = '5';
  container.appendChild(bubble);
  activeBubbleDOM = bubble;
  bubbleTimer = 160;
}

// UI Localization Updates
function updateLocalization() {
  // Sync TR/EN button active classes
  const btnTr = document.getElementById('btn-tr');
  const btnEn = document.getElementById('btn-en');
  if (btnTr && btnEn) {
    if (language === 'tr') {
      btnTr.classList.add('active');
      btnEn.classList.remove('active');
    } else {
      btnEn.classList.add('active');
      btnTr.classList.remove('active');
    }
  }
  const textDict = translations[language];
  document.getElementById('game-title-text').innerHTML = textDict.title;
  document.getElementById('lbl-score').innerText = textDict.score + ": " + score;
  document.getElementById('lbl-level').innerText = textDict.level + ": " + level;
  document.getElementById('lbl-lives').innerText = textDict.lives + ": " + lives;
  document.getElementById('slider-pitch-label').innerText = language === 'tr' ? 'Dikey Açı' : 'Pitch Angle';
  document.getElementById('slider-yaw-label').innerText = language === 'tr' ? 'Yatay Yön' : 'Yaw Direction';
  document.getElementById('slider-power-label').innerText = language === 'tr' ? 'Fırlatma Gücü' : 'Throw Power';
  document.getElementById('btn-fire-text').innerText = language === 'tr' ? 'Fırlat!' : 'Throw!';

  // Nickname & Leaderboard translations
  const nickPromptEl = document.getElementById('lbl-nick-prompt');
  if (nickPromptEl) nickPromptEl.innerText = textDict.nickPrompt;
  const nickInputEl = document.getElementById('player-nick');
  if (nickInputEl) nickInputEl.placeholder = textDict.defaultNick;
  const leadTitleEl = document.getElementById('lbl-leaderboard-title');
  if (leadTitleEl) leadTitleEl.innerText = textDict.leaderboardTitle;
  if (gameState === 'menu') {
    document.getElementById('overlay-title').innerText = textDict.startTitle;
    document.getElementById('overlay-subtitle').innerText = textDict.startSubtitle;
    document.getElementById('btn-start-text').innerText = textDict.playBtn;
    const list = document.getElementById('instructions-list');
    list.innerHTML = '';
    textDict.instructions.forEach(ins => {
      const li = document.createElement('li');
      li.innerText = ins;
      list.appendChild(li);
    });
  } else if (gameState === 'gameover') {
    document.getElementById('overlay-title').innerText = textDict.gameOverTitle;
    document.getElementById('overlay-subtitle').innerText = textDict.gameOverSubtitle;
    document.getElementById('btn-start-text').innerText = textDict.restartBtn;
  } else if (gameState === 'levelup') {
    document.getElementById('overlay-title').innerText = textDict.levelUpTitle;
    document.getElementById('overlay-subtitle').innerText = textDict.levelUpSubtitle;
    document.getElementById('btn-start-text').innerText = textDict.nextLevelBtn;
  }
}

// Wind HUD display and layout updates
function updateLaminarWindHUD() {
  const textDict = translations[language];
  const windText = document.getElementById('lbl-wind');
  let directionStr = textDict.windNone;
  let arrow = "•";
  let speedColor = "#FFFFFF";
  const absWindX = Math.round(Math.abs(windX * 100));
  if (windX > 0) {
    directionStr = textDict.windRight;
    arrow = "→".repeat(Math.min(5, Math.ceil(windX * 100)));
    speedColor = "#10B981";
  } else if (windX < 0) {
    directionStr = textDict.windLeft;
    arrow = "←".repeat(Math.min(5, Math.ceil(Math.abs(windX) * 100)));
    speedColor = "#EF4444";
  }
  windText.innerHTML = `${textDict.wind}: <span style="color: ${speedColor}">${absWindX} ${directionStr} ${arrow}</span>`;
}
function setRandomWind() {
  windX = Math.random() * 0.08 - 0.04;
  windZ = Math.random() * 0.04 - 0.02;
  updateLaminarWindHUD();
}

// 3D Scene Initialization
function build3DScene() {
  if (typeof THREE === 'undefined') return;
  const container = document.querySelector('.canvas-container');
  const width = container.clientWidth;
  const height = container.clientHeight;
  renderer = new THREE.WebGLRenderer({
    canvas: document.getElementById('gameCanvas3D'),
    antialias: true
  });
  renderer.setSize(width, height);
  renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
  renderer.shadowMap.enabled = true;
  renderer.shadowMap.type = THREE.PCFSoftShadowMap;
  scene = new THREE.Scene();
  scene.background = new THREE.Color(0x0a0f1d);
  scene.fog = new THREE.FogExp2(0x0a0f1d, 0.015);
  camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 100);
  cameraTargetPos = new THREE.Vector3(-13, 4.5, 0);
  cameraTargetLook = new THREE.Vector3(10, 1.2, 0);
  currentLookAt = new THREE.Vector3().copy(cameraTargetLook);
  camera.position.copy(cameraTargetPos);
  camera.lookAt(currentLookAt);

  // Lights
  const ambient = new THREE.AmbientLight(0xffffff, 0.35);
  scene.add(ambient);
  const spotlight = new THREE.SpotLight(0xffffff, 2.5);
  spotlight.position.set(2, 8, 0);
  spotlight.angle = Math.PI / 4;
  spotlight.penumbra = 0.5;
  spotlight.castShadow = true;
  spotlight.shadow.mapSize.width = 1024;
  spotlight.shadow.mapSize.height = 1024;
  spotlight.shadow.camera.near = 1;
  spotlight.shadow.camera.far = 15;
  spotlight.shadow.bias = -0.001;
  scene.add(spotlight);
  const dirLight = new THREE.DirectionalLight(0x60a5fa, 0.4);
  dirLight.position.set(-8, 5, -5);
  scene.add(dirLight);

  // Grid Floor
  const floorGeo = new THREE.PlaneGeometry(35, 20);
  const floorMat = new THREE.MeshStandardMaterial({
    color: 0x111827,
    roughness: 0.8
  });
  const floor = new THREE.Mesh(floorGeo, floorMat);
  floor.rotation.x = -Math.PI / 2;
  floor.position.y = FLOOR_Y;
  floor.receiveShadow = true;
  scene.add(floor);
  const grid = new THREE.GridHelper(35, 35, 0x1e293b, 0x1e293b);
  grid.position.y = FLOOR_Y + 0.01;
  scene.add(grid);

  // Table
  const tableGroup = new THREE.Group();
  const baseGeom = new THREE.CylinderGeometry(0.4, 0.5, 1.2, 8);
  const metalMat = new THREE.MeshStandardMaterial({
    color: 0x64748b,
    metalness: 0.9,
    roughness: 0.1
  });
  const tableBase = new THREE.Mesh(baseGeom, metalMat);
  tableBase.position.y = 0.6;
  tableBase.castShadow = true;
  tableGroup.add(tableBase);
  const padGeom = new THREE.BoxGeometry(4.8, 0.25, 1.3);
  const padMat = new THREE.MeshStandardMaterial({
    color: 0x334155,
    roughness: 0.6
  });
  const bedPad = new THREE.Mesh(padGeom, padMat);
  bedPad.position.set(0, 1.2, 0);
  bedPad.castShadow = true;
  bedPad.receiveShadow = true;
  tableGroup.add(bedPad);
  tableMesh = tableGroup;
  tableMesh.position.set(3, 0, 0);
  scene.add(tableMesh);

  // Drape (Ether screen)
  const drapeGroup = new THREE.Group();
  const rodGeom = new THREE.CylinderGeometry(0.04, 0.04, 2.0, 8);
  const drapeRod = new THREE.Mesh(rodGeom, metalMat);
  drapeRod.position.set(0, 1.0, 0);
  drapeGroup.add(drapeRod);
  const topRodGeom = new THREE.CylinderGeometry(0.03, 0.03, 1.5, 8);
  const topRod = new THREE.Mesh(topRodGeom, metalMat);
  topRod.rotation.x = Math.PI / 2;
  topRod.position.set(0, 2.0, 0);
  drapeGroup.add(topRod);
  const clothGeom = new THREE.BoxGeometry(0.03, 1.4, 1.4);
  const clothMat = new THREE.MeshStandardMaterial({
    color: 0x3b82f6,
    transparent: true,
    opacity: 0.65,
    roughness: 0.9
  });
  const cloth = new THREE.Mesh(clothGeom, clothMat);
  cloth.position.set(0, 1.3, 0);
  drapeGroup.add(cloth);
  drapeMesh = drapeGroup;
  drapeMesh.position.set(0.5, 0, 0);
  scene.add(drapeMesh);

  // IV Pole
  const ivGroup = new THREE.Group();
  const mainPole = new THREE.Mesh(new THREE.CylinderGeometry(0.03, 0.03, 3.2, 8), metalMat);
  mainPole.position.y = 1.6;
  mainPole.castShadow = true;
  ivGroup.add(mainPole);
  const crossbar = new THREE.Mesh(new THREE.CylinderGeometry(0.015, 0.015, 0.6, 8), metalMat);
  crossbar.rotation.z = Math.PI / 2;
  crossbar.position.set(0, 3.1, 0);
  ivGroup.add(crossbar);
  const bagMat = new THREE.MeshStandardMaterial({
    color: 0xffffff,
    transparent: true,
    opacity: 0.45,
    roughness: 0.1
  });
  const bagL = new THREE.Mesh(new THREE.BoxGeometry(0.12, 0.25, 0.05), bagMat);
  bagL.position.set(-0.3, 2.85, 0);
  const bagR = bagL.clone();
  bagR.position.x = 0.3;
  ivGroup.add(bagL);
  ivGroup.add(bagR);
  ivPoleMesh = ivGroup;
  ivPoleMesh.position.set(1.5, 0, 1.4);
  scene.add(ivPoleMesh);

  // Patient Monitor
  const monGroup = new THREE.Group();
  const monitorCasing = new THREE.Mesh(new THREE.BoxGeometry(1.2, 0.8, 0.3), new THREE.MeshStandardMaterial({
    color: 0x334155,
    roughness: 0.7
  }));
  monitorCasing.castShadow = true;
  monGroup.add(monitorCasing);
  const lcdScreen = new THREE.Mesh(new THREE.BoxGeometry(1.05, 0.65, 0.04), new THREE.MeshStandardMaterial({
    color: 0x0f172a,
    emissive: 0x10b981,
    emissiveIntensity: 0.15
  }));
  lcdScreen.position.set(0, 0, 0.14);
  monGroup.add(lcdScreen);
  monitorMesh = monGroup;
  monitorMesh.position.set(-6.0, 2.7, -0.8);
  scene.add(monitorMesh);

  // Sitting Anesthesiologist & Stool
  anesthesiologistGroup = new THREE.Group();
  const stoolGroup = new THREE.Group();
  const stoolSeat = new THREE.Mesh(new THREE.CylinderGeometry(0.4, 0.4, 0.15, 12), new THREE.MeshStandardMaterial({
    color: 0x0f172a,
    roughness: 0.5
  }));
  stoolSeat.position.y = 0.8;
  stoolSeat.castShadow = true;
  stoolGroup.add(stoolSeat);
  const cylinderPost = new THREE.Mesh(new THREE.CylinderGeometry(0.06, 0.06, 0.8, 8), metalMat);
  cylinderPost.position.y = 0.4;
  stoolGroup.add(cylinderPost);
  const baseStar = new THREE.Mesh(new THREE.TorusGeometry(0.35, 0.05, 8, 12), metalMat);
  baseStar.rotation.x = Math.PI / 2;
  baseStar.position.y = 0.05;
  stoolGroup.add(baseStar);
  anesthesiologistGroup.add(stoolGroup);
  const playerGroup = new THREE.Group();
  const bodyMat = new THREE.MeshStandardMaterial({
    color: 0x065f46,
    roughness: 0.8
  });
  const scrubBody = new THREE.Mesh(new THREE.CylinderGeometry(0.35, 0.45, 1.0, 12), bodyMat);
  scrubBody.position.y = 1.3;
  scrubBody.castShadow = true;
  playerGroup.add(scrubBody);
  const legL = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.1, 0.6, 8), bodyMat);
  legL.rotation.x = Math.PI / 3;
  legL.position.set(-0.2, 0.95, 0.35);
  const legR = legL.clone();
  legR.position.x = 0.2;
  playerGroup.add(legL);
  playerGroup.add(legR);
  const skinMat = new THREE.MeshStandardMaterial({
    color: 0xfbcfe8,
    roughness: 0.8
  });
  const playerHead = new THREE.Mesh(new THREE.SphereGeometry(0.28, 16, 16), skinMat);
  playerHead.position.y = 2.0;
  playerHead.castShadow = true;
  playerGroup.add(playerHead);
  const surgicalCap = new THREE.Mesh(new THREE.SphereGeometry(0.29, 12, 12, 0, Math.PI * 2, 0, Math.PI / 2), new THREE.MeshStandardMaterial({
    color: 0x2563eb,
    roughness: 0.9
  }));
  surgicalCap.position.y = 2.05;
  playerGroup.add(surgicalCap);
  const mask = new THREE.Mesh(new THREE.BoxGeometry(0.34, 0.18, 0.16), new THREE.MeshStandardMaterial({
    color: 0x60a5fa,
    roughness: 0.9
  }));
  mask.position.set(0, 1.95, 0.2);
  playerGroup.add(mask);
  anesthesiologistGroup.add(playerGroup);
  anesthesiologistGroup.position.set(-7.5, 0, 0);
  scene.add(anesthesiologistGroup);

  // Anesthesia Machine (placed at x = -5.0, y = 0, z = -1.2) - Bizden tarafta
  const machineGroup = new THREE.Group();
  const cabinetBody = new THREE.Mesh(new THREE.BoxGeometry(0.9, 1.4, 0.7), new THREE.MeshStandardMaterial({
    color: 0x334155,
    roughness: 0.5
  }));
  cabinetBody.position.y = 0.7;
  cabinetBody.castShadow = true;
  cabinetBody.receiveShadow = true;
  machineGroup.add(cabinetBody);

  // Drawers handles (silver metal)
  const handleBarGeom = new THREE.BoxGeometry(0.5, 0.05, 0.05);
  const metalHandleMat = new THREE.MeshStandardMaterial({
    color: 0xe2e8f0,
    metalness: 0.8
  });
  for (let dy = 0.3; dy <= 0.9; dy += 0.3) {
    const handle = new THREE.Mesh(handleBarGeom, metalHandleMat);
    handle.position.set(0, dy, 0.36);
    machineGroup.add(handle);
  }

  // Vaporizers shelf and vaporizers
  const topShelf = new THREE.Mesh(new THREE.BoxGeometry(0.85, 0.05, 0.6), metalMat);
  topShelf.position.y = 1.425;
  machineGroup.add(topShelf);
  const sevoVap = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.12, 0.3, 8), new THREE.MeshStandardMaterial({
    color: 0xf59e0b,
    roughness: 0.3
  }));
  sevoVap.position.set(-0.2, 1.6, 0.15);
  machineGroup.add(sevoVap);
  const isoVap = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.12, 0.3, 8), new THREE.MeshStandardMaterial({
    color: 0x6d28d9,
    roughness: 0.3
  }));
  isoVap.position.set(0.2, 1.6, 0.15);
  machineGroup.add(isoVap);

  // Soda Lime Canister on side
  const limeAbsorber = new THREE.Mesh(new THREE.CylinderGeometry(0.15, 0.15, 0.4, 8), new THREE.MeshStandardMaterial({
    color: 0xf1f5f9,
    roughness: 0.6
  }));
  limeAbsorber.position.set(0.35, 0.9, 0.3);
  machineGroup.add(limeAbsorber);

  // Small ventilator screen on bracket
  const ventMount = new THREE.Mesh(new THREE.CylinderGeometry(0.02, 0.02, 0.5, 8), metalMat);
  ventMount.position.set(-0.3, 1.65, -0.1);
  machineGroup.add(ventMount);
  const ventCasing = new THREE.Mesh(new THREE.BoxGeometry(0.4, 0.3, 0.1), new THREE.MeshStandardMaterial({
    color: 0x334155
  }));
  ventCasing.position.set(-0.3, 1.9, -0.1);
  machineGroup.add(ventCasing);
  const ventScreen = new THREE.Mesh(new THREE.BoxGeometry(0.34, 0.24, 0.02), new THREE.MeshStandardMaterial({
    color: 0x020617,
    emissive: 0x0ea5e9,
    emissiveIntensity: 0.4
  }));
  ventScreen.position.set(-0.3, 1.9, -0.04);
  machineGroup.add(ventScreen);

  // Tubes
  const loopTube = new THREE.Mesh(new THREE.TorusGeometry(0.25, 0.03, 6, 12, Math.PI), new THREE.MeshStandardMaterial({
    color: 0x60a5fa,
    transparent: true,
    opacity: 0.8
  }));
  loopTube.rotation.y = Math.PI / 2;
  loopTube.position.set(0.35, 0.7, 0.35);
  machineGroup.add(loopTube);
  machineGroup.position.set(-5.0, 0, -1.2);
  scene.add(machineGroup);

  // Standing Surgeon (Target)
  surgeonGroup = new THREE.Group();
  const surgeonBody = new THREE.Mesh(new THREE.CylinderGeometry(0.35, 0.4, 1.4, 12), new THREE.MeshStandardMaterial({
    color: 0x1d4ed8,
    roughness: 0.8
  }));
  surgeonBody.position.y = 0.7;
  surgeonBody.castShadow = true;
  surgeonGroup.add(surgeonBody);
  const surgeonHead = new THREE.Mesh(new THREE.SphereGeometry(0.28, 16, 16), new THREE.MeshStandardMaterial({
    color: 0xffd2b2,
    roughness: 0.8
  }));
  surgeonHead.position.y = 1.6;
  surgeonHead.castShadow = true;
  surgeonGroup.add(surgeonHead);
  const surgeonCap = new THREE.Mesh(new THREE.SphereGeometry(0.29, 12, 12, 0, Math.PI * 2, 0, Math.PI / 2), new THREE.MeshStandardMaterial({
    color: 0x047857,
    roughness: 0.9
  }));
  surgeonCap.position.y = 1.65;
  surgeonGroup.add(surgeonCap);
  const surgeonMask = mask.clone();
  surgeonMask.position.set(0, 1.55, 0.2);
  surgeonGroup.add(surgeonMask);
  surgeonGroup.position.set(7.5, 0, surgeonZ);
  scene.add(surgeonGroup);

  // Trajectory Predictor Line
  const lineMat = new THREE.LineDashedMaterial({
    color: 0xfcd34d,
    dashSize: 0.25,
    gapSize: 0.15
  });
  trajectoryLine = new THREE.Line(new THREE.BufferGeometry(), lineMat);
  scene.add(trajectoryLine);
  updateTrajectoryLine();
}
function build3DLaryngoscope() {
  if (!scene || typeof THREE === 'undefined') return;
  if (laryngoscopeMesh) {
    scene.remove(laryngoscopeMesh);
  }
  const scopeGroup = new THREE.Group();
  const handleMat = new THREE.MeshStandardMaterial({
    color: 0xe2e8f0,
    metalness: 0.9,
    roughness: 0.2
  });
  const handle = new THREE.Mesh(new THREE.CylinderGeometry(0.06, 0.06, 0.45, 8), handleMat);
  handle.castShadow = true;
  scopeGroup.add(handle);
  const bladeShape = new THREE.Shape();
  bladeShape.moveTo(0, 0.04);
  bladeShape.quadraticCurveTo(0.2, 0.18, 0.45, 0.12);
  bladeShape.quadraticCurveTo(0.25, 0.05, 0, -0.04);
  bladeShape.closePath();
  const extrudeSettings = {
    depth: 0.05,
    bevelEnabled: true,
    bevelSegments: 2,
    steps: 1,
    bevelSize: 0.01,
    bevelThickness: 0.01
  };
  const blade = new THREE.Mesh(new THREE.ExtrudeGeometry(bladeShape, extrudeSettings), handleMat);
  blade.position.set(0, 0.18, -0.025);
  blade.castShadow = true;
  scopeGroup.add(blade);
  const fiber = new THREE.Mesh(new THREE.CylinderGeometry(0.008, 0.008, 0.35, 4), new THREE.MeshStandardMaterial({
    color: 0xffffff,
    emissive: 0xfbcb24,
    emissiveIntensity: 0.8
  }));
  fiber.rotation.z = -Math.PI / 4;
  fiber.position.set(0.18, 0.14, 0.04);
  scopeGroup.add(fiber);
  laryngoscopeMesh = scopeGroup;
  laryngoscopeMesh.castShadow = true;
  resetLaryngoscopePosition();
  scene.add(laryngoscopeMesh);
}
function resetLaryngoscopePosition() {
  if (laryngoscopeMesh) {
    laryngoscopeMesh.position.set(-7.0, 1.5, 0.3);
    laryngoscopeMesh.rotation.set(0, 0, 0);
    laryngoscopeMesh.scale.set(1.0, 1.0, 1.0);
  }
}

// Update Trajectory Predictor Line (3D only)
function updateTrajectoryLine() {
  if (project.isFlying || !trajectoryLine || typeof THREE === 'undefined') {
    if (trajectoryLine) trajectoryLine.visible = false;
    return;
  }
  trajectoryLine.visible = true;
  const pitchRad = aimPitch * (Math.PI / 180);
  const yawRad = aimYaw * (Math.PI / 180);
  const speed = throwPower * 0.0055;
  const vx = Math.cos(pitchRad) * Math.cos(yawRad) * speed;
  const vy = Math.sin(pitchRad) * speed;
  const vz = -Math.cos(pitchRad) * Math.sin(yawRad) * speed;
  const points = [];
  const tempPos = new GameVector3(-7.0, 1.5, 0.3);
  const tempVel = new GameVector3(vx, vy, vz);
  for (let i = 0; i < 60; i++) {
    points.push(new THREE.Vector3(tempPos.x, tempPos.y, tempPos.z));
    tempVel.y -= GRAVITY * 0.016;
    tempVel.x += windX * 0.01;
    tempVel.z += windZ * 0.01;
    tempPos.add(tempVel);
    if (tempPos.y <= FLOOR_Y) {
      points.push(new THREE.Vector3(tempPos.x, FLOOR_Y, tempPos.z));
      break;
    }
  }
  const geom = new THREE.BufferGeometry().setFromPoints(points);
  trajectoryLine.geometry = geom;
  trajectoryLine.computeLineDistances();
}
function drawStaticBackground(ctx) {
  if (!ctx) return;

  // 1. Clear background
  const grad = ctx.createRadialGradient(640, 200, 50, 640, 360, 600);
  grad.addColorStop(0, '#1E293B');
  grad.addColorStop(1, '#090F1E');
  ctx.fillStyle = grad;
  ctx.fillRect(0, 0, 1280, 720);

  // 2. Draw Floor tiles
  ctx.strokeStyle = 'rgba(255, 255, 255, 0.03)';
  ctx.lineWidth = 1;
  for (let i = 600; i <= 720; i += 20) {
    ctx.beginPath();
    ctx.moveTo(0, i);
    ctx.lineTo(1280, i);
    ctx.stroke();
  }

  // Draw depth lines on the floor
  for (let gx = -10; gx <= 10; gx += 2) {
    const p1 = project3DTo2D(gx, 0, -2.0);
    const p2 = project3DTo2D(gx, 0, 2.0);
    ctx.beginPath();
    ctx.moveTo(p1.x, p1.y);
    ctx.lineTo(p2.x, p2.y);
    ctx.stroke();
  }

  // 3. Patient Monitor Casing (Background depth z = -0.8)
  const monPos = project3DTo2D(-6.0, 2.7, -0.8);
  ctx.strokeStyle = '#475569';
  ctx.lineWidth = 6;
  ctx.beginPath();
  ctx.moveTo(monPos.x, monPos.y);
  ctx.lineTo(0, monPos.y); // Wall mount extending to left edge
  ctx.stroke();
  ctx.fillStyle = '#334155';
  ctx.fillRect(monPos.x - 50, monPos.y - 35, 100, 70);
  ctx.strokeStyle = '#64748b';
  ctx.lineWidth = 2;
  ctx.strokeRect(monPos.x - 50, monPos.y - 35, 100, 70);

  // 3.5. Anesthesia Machine (placed at x = -5.0, y = 0, z = -1.2)
  const amPos = project3DTo2D(-5.0, 0, -1.2);
  ctx.save();
  ctx.translate(amPos.x, amPos.y);

  // Main cabinet body
  ctx.fillStyle = '#334155';
  ctx.fillRect(-30, -95, 60, 95);
  ctx.strokeStyle = '#475569';
  ctx.lineWidth = 2;
  ctx.strokeRect(-30, -95, 60, 95);

  // Drawers and lines
  ctx.strokeStyle = '#1e293b';
  ctx.lineWidth = 1;
  for (let dy = -70; dy <= -20; dy += 20) {
    ctx.beginPath();
    ctx.moveTo(-30, dy);
    ctx.lineTo(30, dy);
    ctx.stroke();

    // Handle (silver/metal)
    ctx.fillStyle = '#94a3b8';
    ctx.fillRect(-14, dy + 8, 28, 4);
  }

  // Vaporizers shelf on top
  ctx.fillStyle = '#64748b';
  ctx.fillRect(-32, -98, 64, 4);

  // Yellow Vaporizer (Sevoflurane)
  ctx.fillStyle = '#f59e0b';
  ctx.fillRect(-20, -118, 12, 20);
  ctx.fillStyle = '#cbd5e1';
  ctx.fillRect(-17, -122, 6, 4);

  // Purple Vaporizer (Isoflurane)
  ctx.fillStyle = '#7c3aed';
  ctx.fillRect(-4, -118, 12, 20);
  ctx.fillStyle = '#cbd5e1';
  ctx.fillRect(-1, -122, 6, 4);

  // Soda Lime canister on the side
  ctx.fillStyle = '#f1f5f9';
  ctx.fillRect(22, -65, 16, 28);
  ctx.strokeStyle = '#94a3b8';
  ctx.lineWidth = 1;
  ctx.strokeRect(22, -65, 16, 28);
  ctx.fillStyle = '#f472b6';
  ctx.fillRect(24, -53, 12, 5);

  // Mount bracket for ventilator screen
  ctx.strokeStyle = '#64748b';
  ctx.lineWidth = 3;
  ctx.beginPath();
  ctx.moveTo(-20, -98);
  ctx.lineTo(-20, -130);
  ctx.stroke();

  // Ventilator Screen casing
  ctx.fillStyle = '#1e293b';
  ctx.fillRect(-32, -150, 24, 20);
  ctx.strokeStyle = '#475569';
  ctx.lineWidth = 1;
  ctx.strokeRect(-32, -150, 24, 20);

  // Ventilator LCD Screen (cyan)
  ctx.fillStyle = '#0284c7';
  ctx.fillRect(-30, -148, 20, 16);

  // Ventilator trace
  ctx.strokeStyle = '#ffffff';
  ctx.lineWidth = 1;
  ctx.beginPath();
  ctx.moveTo(-29, -140);
  ctx.lineTo(-26, -140);
  ctx.lineTo(-24, -145);
  ctx.lineTo(-22, -135);
  ctx.lineTo(-20, -140);
  ctx.lineTo(-11, -140);
  ctx.stroke();

  // Curved blue breathing tubes hanging
  ctx.strokeStyle = '#60a5fa';
  ctx.lineWidth = 3;
  ctx.beginPath();
  ctx.arc(15, -48, 14, 0, Math.PI, false);
  ctx.stroke();
  ctx.restore();

  // 4. Operating Table (Middle depth z = 0)
  const tBase = project3DTo2D(3.0, 0, 0);
  const tPad = project3DTo2D(3.0, 1.2, 0);
  ctx.fillStyle = '#475569'; // Pillar
  ctx.fillRect(tBase.x - 24, tPad.y, 48, tBase.y - tPad.y);
  ctx.fillStyle = '#1E293B'; // Pad
  ctx.fillRect(tPad.x - 160, tPad.y - 12, 320, 24);
  ctx.strokeStyle = '#334155';
  ctx.lineWidth = 1;
  ctx.strokeRect(tPad.x - 160, tPad.y - 12, 320, 24);

  // 5. Drape / Ether Screen (Middle depth z = 0)
  const dBase = project3DTo2D(0.5, 0, 0);
  const dTop = project3DTo2D(0.5, 2.0, 0);
  ctx.strokeStyle = '#64748b';
  ctx.lineWidth = 4;
  ctx.beginPath();
  ctx.moveTo(dBase.x, dBase.y);
  ctx.lineTo(dBase.x, dTop.y);
  ctx.stroke();
  ctx.beginPath();
  ctx.moveTo(dBase.x - 40, dTop.y);
  ctx.lineTo(dBase.x + 40, dTop.y);
  ctx.stroke();
  ctx.fillStyle = 'rgba(59, 130, 246, 0.65)'; // transparent drape blue
  ctx.fillRect(dBase.x - 36, dTop.y, 72, 120);

  // 6. Sitting Anesthesiologist stool, scrubs, face, cap, mask (without arm)
  const pBase = project3DTo2D(-7.5, 0, 0.3);

  // Rolling stool
  ctx.fillStyle = '#0f172a';
  ctx.fillRect(pBase.x - 22, pBase.y - 70, 44, 10); // seat
  ctx.fillStyle = '#475569';
  ctx.fillRect(pBase.x - 3, pBase.y - 60, 6, 56); // post
  ctx.fillStyle = '#334155';
  ctx.fillRect(pBase.x - 18, pBase.y - 4, 36, 4); // base legs

  // Green Scrubs body
  ctx.fillStyle = '#065f46';
  ctx.beginPath();
  ctx.arc(pBase.x, pBase.y - 100, 24, 0, Math.PI, true);
  ctx.fill();
  ctx.fillRect(pBase.x - 24, pBase.y - 100, 48, 36);

  // Knees/Legs
  ctx.strokeStyle = '#065f46';
  ctx.lineWidth = 10;
  ctx.lineCap = 'round';
  ctx.beginPath();
  ctx.moveTo(pBase.x - 10, pBase.y - 70);
  ctx.lineTo(pBase.x - 26, pBase.y - 45);
  ctx.lineTo(pBase.x - 26, pBase.y);
  ctx.moveTo(pBase.x + 10, pBase.y - 70);
  ctx.lineTo(pBase.x + 26, pBase.y - 45);
  ctx.lineTo(pBase.x + 26, pBase.y);
  ctx.stroke();

  // Face skin
  ctx.fillStyle = '#fbcfe8';
  ctx.beginPath();
  ctx.arc(pBase.x, pBase.y - 138, 18, 0, Math.PI * 2);
  ctx.fill();

  // Blue surgical cap
  ctx.fillStyle = '#2563eb';
  ctx.beginPath();
  ctx.arc(pBase.x, pBase.y - 143, 19, Math.PI, 0);
  ctx.fill();

  // Mask
  ctx.fillStyle = '#60a5fa';
  ctx.fillRect(pBase.x - 5, pBase.y - 138, 14, 10);

  // 8. IV Pole (Foreground layer, z = 1.4)
  const ivBase = project3DTo2D(1.5, 0, 1.4);
  const ivTop = project3DTo2D(1.5, 3.2, 1.4);
  ctx.strokeStyle = '#cbd5e1';
  ctx.lineWidth = 4;
  ctx.beginPath();
  ctx.moveTo(ivBase.x, ivBase.y);
  ctx.lineTo(ivBase.x, ivTop.y);
  ctx.stroke();
  ctx.beginPath();
  ctx.moveTo(ivBase.x - 18, ivTop.y + 10);
  ctx.lineTo(ivBase.x + 18, ivTop.y + 10);
  ctx.stroke();
  ctx.fillStyle = 'rgba(255,255,255,0.45)';
  ctx.fillRect(ivBase.x - 24, ivTop.y + 16, 10, 22);
  ctx.fillRect(ivBase.x + 14, ivTop.y + 16, 10, 22);
}

// 2D Draw Scene Function (Canvas drawing)
function draw2DScene() {
  if (!ctx2d) return;

  // 1. Draw cached static background
  if (!bgCanvas) {
    bgCanvas = document.createElement('canvas');
    bgCanvas.width = 1280;
    bgCanvas.height = 720;
    const bgCtx = bgCanvas.getContext('2d');
    drawStaticBackground(bgCtx);
  }
  ctx2d.drawImage(bgCanvas, 0, 0);

  // 2. Draw flashing/monitor state on top
  const monPos = project3DTo2D(-6.0, 2.7, -0.8);
  const isFlashing = monitorFlashTimer > 0;

  // Overwrite the LCD screen rectangle with current state
  ctx2d.fillStyle = isFlashing ? '#D97706' : '#0F172A';
  ctx2d.fillRect(monPos.x - 44, monPos.y - 29, 88, 58);

  // Heart trace
  ctx2d.strokeStyle = isFlashing ? '#FFFFFF' : '#10B981';
  ctx2d.lineWidth = 2;
  ctx2d.beginPath();
  ctx2d.moveTo(monPos.x - 40, monPos.y);
  ctx2d.lineTo(monPos.x - 20, monPos.y);
  ctx2d.lineTo(monPos.x - 15, monPos.y - 18);
  ctx2d.lineTo(monPos.x - 10, monPos.y + 18);
  ctx2d.lineTo(monPos.x - 5, monPos.y);
  ctx2d.lineTo(monPos.x + 15, monPos.y);
  ctx2d.lineTo(monPos.x + 20, monPos.y - 12);
  ctx2d.lineTo(monPos.x + 25, monPos.y + 12);
  ctx2d.lineTo(monPos.x + 30, monPos.y);
  ctx2d.lineTo(monPos.x + 40, monPos.y);
  ctx2d.stroke();

  // 3. Draw player arm & laryngoscope in hand
  const pBase = project3DTo2D(-7.5, 0, 0.3);
  if (!project.isFlying) {
    const handPos = project3DTo2D(-7.0, 1.5, 0.3);
    ctx2d.strokeStyle = '#065f46';
    ctx2d.lineWidth = 7;
    ctx2d.beginPath();
    ctx2d.moveTo(pBase.x + 12, pBase.y - 110);
    ctx2d.lineTo(handPos.x - 10, handPos.y + 10);
    ctx2d.lineTo(handPos.x, handPos.y);
    ctx2d.stroke();

    // Blade in hand
    ctx2d.save();
    ctx2d.translate(handPos.x, handPos.y);
    ctx2d.fillStyle = '#cbd5e1'; // metal handle
    ctx2d.fillRect(-2, -10, 4, 20);
    ctx2d.fillStyle = '#e2e8f0'; // Macintosh curve
    ctx2d.beginPath();
    ctx2d.moveTo(2, -10);
    ctx2d.quadraticCurveTo(12, -12, 16, -4);
    ctx2d.quadraticCurveTo(8, 0, 2, 0);
    ctx2d.closePath();
    ctx2d.fill();
    ctx2d.restore();
  } else {
    ctx2d.strokeStyle = '#065f46';
    ctx2d.lineWidth = 7;
    ctx2d.beginPath();
    ctx2d.moveTo(pBase.x + 12, pBase.y - 110);
    ctx2d.lineTo(pBase.x + 32, pBase.y - 95);
    ctx2d.stroke();
  }

  // 4. Standing Surgeon Target (x = 7.5, y = 0, z = surgeonZ)
  const sPos = project3DTo2D(7.5 + surgeonXOffset, 0, surgeonZ);
  const scale = 1.0 + surgeonZ * 0.12;
  ctx2d.save();
  ctx2d.translate(sPos.x, sPos.y);
  ctx2d.scale(scale, scale);

  // Gown
  ctx2d.fillStyle = '#1d4ed8';
  ctx2d.beginPath();
  ctx2d.arc(0, -110, 26, 0, Math.PI, true);
  ctx2d.fill();
  ctx2d.fillRect(-26, -110, 52, 110);

  // Face skin
  ctx2d.fillStyle = '#ffd2b2';
  ctx2d.beginPath();
  ctx2d.arc(0, -150, 18, 0, Math.PI * 2);
  ctx2d.fill();

  // Green cap
  ctx2d.fillStyle = '#047857';
  ctx2d.beginPath();
  ctx2d.arc(0, -155, 19, Math.PI, 0);
  ctx2d.fill();

  // Mask
  ctx2d.fillStyle = '#60a5fa';
  ctx2d.fillRect(-10, -150, 20, 10);

  // Arms holding retractors
  ctx2d.strokeStyle = '#1d4ed8';
  ctx2d.lineWidth = 9;
  ctx2d.lineCap = 'round';
  ctx2d.beginPath();
  ctx2d.moveTo(-18, -100);
  ctx2d.lineTo(-35, -80);
  ctx2d.moveTo(18, -100);
  ctx2d.lineTo(35, -80);
  ctx2d.stroke();
  ctx2d.restore();

  // 5. Dotted Aiming Trajectory (when playing and aiming)
  if (!project.isFlying && gameState === 'playing') {
    const pitchRad = aimPitch * (Math.PI / 180);
    const yawRad = aimYaw * (Math.PI / 180);
    const speed = throwPower * 0.0055;
    const vx = Math.cos(pitchRad) * Math.cos(yawRad) * speed;
    const vy = Math.sin(pitchRad) * speed;
    const vz = -Math.cos(pitchRad) * Math.sin(yawRad) * speed;
    const tempPos = new GameVector3(-7.0, 1.5, 0.3);
    const tempVel = new GameVector3(vx, vy, vz);
    ctx2d.strokeStyle = '#FCD34D';
    ctx2d.lineWidth = 3;
    ctx2d.setLineDash([4, 6]);
    ctx2d.beginPath();
    const startProj = project3DTo2D(tempPos.x, tempPos.y, tempPos.z);
    ctx2d.moveTo(startProj.x, startProj.y);
    for (let i = 0; i < 60; i++) {
      tempVel.y -= GRAVITY * 0.016;
      tempVel.x += windX * 0.01;
      tempVel.z += windZ * 0.01;
      tempPos.add(tempVel);
      const p = project3DTo2D(tempPos.x, tempPos.y, tempPos.z);
      ctx2d.lineTo(p.x, p.y);
      if (tempPos.y <= FLOOR_Y) break;
    }
    ctx2d.stroke();
    ctx2d.setLineDash([]);
  }

  // 6. Flying Laryngoscope
  if (project.isFlying) {
    const projPos = project3DTo2D(project.pos.x, project.pos.y, project.pos.z);
    ctx2d.save();
    ctx2d.translate(projPos.x, projPos.y);
    const rotation = Date.now() * 0.015;
    ctx2d.rotate(rotation);
    ctx2d.fillStyle = '#cbd5e1';
    ctx2d.fillRect(-3, -15, 6, 30);
    ctx2d.fillStyle = '#e2e8f0';
    ctx2d.beginPath();
    ctx2d.moveTo(3, -15);
    ctx2d.quadraticCurveTo(20, -18, 25, -4);
    ctx2d.quadraticCurveTo(12, 0, 3, 0);
    ctx2d.closePath();
    ctx2d.fill();
    ctx2d.fillStyle = '#fcd34d';
    ctx2d.beginPath();
    ctx2d.arc(12, -10, 2.5, 0, Math.PI * 2);
    ctx2d.fill();
    ctx2d.restore();
  }

  // 7. Flying Trail Particles
  for (let i = project.trailParticles.length - 1; i >= 0; i--) {
    const tp = project.trailParticles[i];
    const screenP = project3DTo2D(tp.pos.x, tp.pos.y, tp.pos.z);
    ctx2d.fillStyle = `rgba(255, 255, 255, ${tp.alpha})`;
    ctx2d.beginPath();
    ctx2d.arc(screenP.x, screenP.y, tp.size, 0, Math.PI * 2);
    ctx2d.fill();
  }

  // 8. Burst Sparkle Particles (Hits/Misses)
  for (let i = particles2D.length - 1; i >= 0; i--) {
    const p = particles2D[i];
    ctx2d.fillStyle = p.color;
    ctx2d.globalAlpha = p.alpha;
    ctx2d.beginPath();
    ctx2d.arc(p.pos.x, p.pos.y, p.size, 0, Math.PI * 2);
    ctx2d.fill();
  }
  ctx2d.globalAlpha = 1.0; // reset
}

// Trigger Throw Launcher
function fireFromSliders() {
  if (gameState !== 'playing' || project.isFlying) return;
  initAudio();
  const pitchRad = aimPitch * (Math.PI / 180);
  const yawRad = aimYaw * (Math.PI / 180);
  const speed = throwPower * 0.0055;
  project.pos.set(-7.0, 1.5, 0.3);
  project.vel.set(Math.cos(pitchRad) * Math.cos(yawRad) * speed, Math.sin(pitchRad) * speed, -Math.cos(pitchRad) * Math.sin(yawRad) * speed);
  project.isFlying = true;
  if (gameMode === '3d' && is3DAvailable) {
    trajectoryLine.visible = false;
    cameraMode = 'flight';
  }
  playSynthesizedSound('launch');
}

// Touch & Mouse Drag slingshot controls
let isDraggingViewport = false;
let startDragMouse = {
  x: 0,
  y: 0
};
let currentDragMouse = {
  x: 0,
  y: 0
};
function setupViewportAiming() {
  const container = document.querySelector('.canvas-container');
  container.addEventListener('mousedown', e => {
    if (gameState !== 'playing' || project.isFlying) return;
    initAudio();
    isDraggingViewport = true;
    startDragMouse.x = e.clientX;
    startDragMouse.y = e.clientY;
    currentDragMouse.x = e.clientX;
    currentDragMouse.y = e.clientY;
  });
  window.addEventListener('mousemove', e => {
    if (!isDraggingViewport) return;
    currentDragMouse.x = e.clientX;
    currentDragMouse.y = e.clientY;
    const dx = startDragMouse.x - currentDragMouse.x;
    const dy = currentDragMouse.y - startDragMouse.y;
    aimYaw = Math.min(45, Math.max(-45, Math.round(dx * 0.2)));
    aimPitch = Math.min(85, Math.max(5, Math.round(dy * 0.25 + 40)));
    throwPower = Math.min(100, Math.max(10, Math.round(Math.hypot(dx, dy) * 0.4)));
    document.getElementById('slider-pitch').value = aimPitch;
    document.getElementById('slider-yaw').value = aimYaw;
    document.getElementById('slider-power').value = throwPower;
    document.getElementById('val-pitch').innerText = aimPitch + "°";
    document.getElementById('val-yaw').innerText = (aimYaw >= 0 ? "+" : "") + aimYaw + "°";
    document.getElementById('val-power').innerText = throwPower + "%";
    if (gameMode === '3d' && is3DAvailable) {
      updateTrajectoryLine();
    }
  });
  window.addEventListener('mouseup', () => {
    if (!isDraggingViewport) return;
    isDraggingViewport = false;
    fireFromSliders();
  });

  // Mobile Touch
  container.addEventListener('touchstart', e => {
    if (gameState !== 'playing' || project.isFlying) return;
    initAudio();
    isDraggingViewport = true;
    startDragMouse.x = e.touches[0].clientX;
    startDragMouse.y = e.touches[0].clientY;
    currentDragMouse.x = e.touches[0].clientX;
    currentDragMouse.y = e.touches[0].clientY;
  }, {
    passive: true
  });
  window.addEventListener('touchmove', e => {
    if (!isDraggingViewport) return;
    currentDragMouse.x = e.touches[0].clientX;
    currentDragMouse.y = e.touches[0].clientY;
    const dx = startDragMouse.x - currentDragMouse.x;
    const dy = currentDragMouse.y - startDragMouse.y;
    aimYaw = Math.min(45, Math.max(-45, Math.round(dx * 0.2)));
    aimPitch = Math.min(85, Math.max(5, Math.round(dy * 0.25 + 40)));
    throwPower = Math.min(100, Math.max(10, Math.round(Math.hypot(dx, dy) * 0.4)));
    document.getElementById('slider-pitch').value = aimPitch;
    document.getElementById('slider-yaw').value = aimYaw;
    document.getElementById('slider-power').value = throwPower;
    document.getElementById('val-pitch').innerText = aimPitch + "°";
    document.getElementById('val-yaw').innerText = (aimYaw >= 0 ? "+" : "") + aimYaw + "°";
    document.getElementById('val-power').innerText = throwPower + "%";
    if (gameMode === '3d' && is3DAvailable) {
      updateTrajectoryLine();
    }
  }, {
    passive: true
  });
  window.addEventListener('touchend', () => {
    if (!isDraggingViewport) return;
    isDraggingViewport = false;
    fireFromSliders();
  });
}

// 3D & 2D Unified Collision Checking
function check3DCollisions() {
  const pos = project.pos;

  // 1. Floor collision
  if (pos.y <= FLOOR_Y + project.radius) {
    project.isFlying = false;
    spawnParticles(pos, 15, 'miss');
    playSynthesizedSound('miss');
    const quotes = translations[language].surgeonMisses;
    showHTMLSpeechBubble(quotes[Math.floor(Math.random() * quotes.length)]);
    loseLife();
    return;
  }

  // 2. Patient Monitor box hit (-6.0, 2.7, -0.8) size: (1.2, 0.8, 0.3)
  const monMin = new GameVector3(-6.0 - 0.6, 2.7 - 0.4, -0.8 - 0.15);
  const monMax = new GameVector3(-6.0 + 0.6, 2.7 + 0.4, -0.8 + 0.15);
  if (pos.x >= monMin.x - project.radius && pos.x <= monMax.x + project.radius && pos.y >= monMin.y - project.radius && pos.y <= monMax.y + project.radius && pos.z >= monMin.z - project.radius && pos.z <= monMax.z + project.radius) {
    // Bounce vector inversion
    project.vel.x = -project.vel.x * 0.95;
    project.vel.y = Math.abs(project.vel.y) * 0.9;
    project.vel.z = -project.vel.z * 0.9;
    pos.addScaledVector(project.vel, 1.2);

    // 3D screen lighting updates
    if (gameMode === '3d' && is3DAvailable && monitorMesh) {
      monitorMesh.children[1].material.emissive.setHex(0x10B981);
      monitorMesh.children[1].material.emissiveIntensity = 0.95;
      setTimeout(() => {
        if (monitorMesh) {
          monitorMesh.children[1].material.emissive.setHex(0x10B981);
          monitorMesh.children[1].material.emissiveIntensity = 0.15;
        }
      }, 400);
    }
    monitorFlashTimer = 25; // 25 frames flash on 2D Screen

    playSynthesizedSound('monitor');
    spawnParticles(pos, 10, 'hit');
    return;
  }

  // 3. IV Pole collision check (1.5, 1.6, 1.4)
  const distToPole2D = Math.hypot(pos.x - 1.5, pos.z - 1.4);
  if (distToPole2D <= 0.18 + project.radius && pos.y >= 0 && pos.y <= 3.2) {
    project.isFlying = false;
    spawnParticles(pos, 12, 'miss');
    playSynthesizedSound('miss');
    loseLife();
    return;
  }

  // 4. Drape Screen check
  if (pos.x >= 0.35 && pos.x <= 0.65 && pos.y >= 0 && pos.y <= 2.0 && Math.abs(pos.z) <= 0.8) {
    project.isFlying = false;
    spawnParticles(pos, 12, 'miss');
    playSynthesizedSound('miss');
    loseLife();
    return;
  }

  // 5. Surgeon target checks
  const targetX = 7.5;
  const targetY = 0;
  const targetZCoord = surgeonZ;
  if (gameMode === '2d') {
    // --- 2D Screen-space Collision Bounding Check ---
    const proj2D = project3DTo2D(pos.x, pos.y, pos.z);
    const head2D = project3DTo2D(targetX + surgeonXOffset, targetY + 1.6, targetZCoord);
    const scale = 1.0 + targetZCoord * 0.12;

    // 2D Headshot Check (screen distance radius 24px)
    const distHead = Math.hypot(proj2D.x - head2D.x, proj2D.y - head2D.y);
    if (distHead <= 24 * scale) {
      triggerHit(true);
      return;
    }

    // 2D Body Check
    // Body is drawn relative to base (sPos.y): width = 52px * scale, height = 110px * scale
    // horizontally centered at head2D.x
    // vertically from head2D.y + 32px to head2D.y + 146px
    const bodyWidth = 52 * scale;
    const bodyTop = head2D.y + 32 * scale;
    const bodyBottom = head2D.y + 146 * scale;
    if (proj2D.x >= head2D.x - bodyWidth / 2 && proj2D.x <= head2D.x + bodyWidth / 2 && proj2D.y >= bodyTop && proj2D.y <= bodyBottom) {
      triggerHit(false);
      return;
    }
  } else {
    // --- 3D Forgiving Bounding Check ---
    const headCenter = new GameVector3(targetX, targetY + 1.6, targetZCoord);
    const distToHead = pos.distanceTo(headCenter);
    if (distToHead <= 0.32 + project.radius) {
      triggerHit(true);
      return;
    }

    // Body check (Z axis is more forgiving)
    const distToBodyX = Math.abs(pos.x - targetX);
    const distToBodyZ = Math.abs(pos.z - targetZCoord);
    if (distToBodyX <= 0.45 + project.radius && distToBodyZ <= 0.8 + project.radius && pos.y >= targetY && pos.y <= targetY + 1.4) {
      triggerHit(false);
      return;
    }
  }
}
function triggerHit(isHeadshot) {
  project.isFlying = false;
  if (gameMode === '3d' && is3DAvailable) {
    cameraMode = 'hit';
  }
  spawnParticles(project.pos, isHeadshot ? 25 : 15, isHeadshot ? 'headshot' : 'hit');
  playSynthesizedSound(isHeadshot ? 'headshot' : 'hit');
  const quotes = translations[language].surgeonHits;
  showHTMLSpeechBubble(quotes[Math.floor(Math.random() * quotes.length)]);
  combo++;
  let scoreGain = isHeadshot ? 350 : 100;
  if (combo > 1) {
    scoreGain = scoreGain * combo;
    triggerFloatingComboLabel(scoreGain, combo);
  } else {
    triggerFloatingComboLabel(scoreGain, 0);
  }
  score += scoreGain;
  document.getElementById('lbl-score').innerText = translations[language].score + ": " + score;

  // Surgeon wiggles
  let shakeTimer = 0;
  const shakeInterval = setInterval(() => {
    const offset = Math.sin(shakeTimer) * 0.15;
    if (gameMode === '3d' && is3DAvailable && surgeonGroup) {
      surgeonGroup.position.x = 7.5 + offset;
    }
    surgeonXOffset = offset;
    shakeTimer += 0.8;
    if (shakeTimer > 10) {
      clearInterval(shakeInterval);
      if (gameMode === '3d' && is3DAvailable && surgeonGroup) {
        surgeonGroup.position.x = 7.5;
      }
      surgeonXOffset = 0;
    }
  }, 20);
  if (score >= level * 650) {
    setTimeout(triggerLevelUp, 1500);
  } else {
    setTimeout(resetRound, 1500);
  }
}
function triggerFloatingComboLabel(scoreGain, multiplier) {
  const container = document.querySelector('.canvas-container');
  const label = document.createElement('div');
  label.className = 'combo-floating-label';
  label.innerText = multiplier > 1 ? `COMBO x${multiplier}! +${scoreGain}` : `+${scoreGain}`;
  label.style.position = 'absolute';
  label.style.right = '12%';
  label.style.top = '40%';
  label.style.color = multiplier > 1 ? '#FCD34D' : '#10B981';
  label.style.fontWeight = '800';
  label.style.fontSize = '24px';
  label.style.fontFamily = 'Outfit, sans-serif';
  label.style.textShadow = '0 0 10px black';
  label.style.zIndex = '5';
  label.style.animation = 'floatUpFade 1.2s forwards ease-out';
  container.appendChild(label);
  setTimeout(() => {
    label.remove();
  }, 1200);
}

// Floating css animations inject
const styleSheet = document.createElement("style");
styleSheet.innerText = `
@keyframes floatUpFade {
    0% { transform: translateY(0); opacity: 1; }
    100% { transform: translateY(-40px); opacity: 0; }
}
.surgeon-speech-bubble::before {
    content: '';
    position: absolute;
    bottom: -10px;
    right: 30px;
    border-width: 10px 10px 0;
    border-style: solid;
    border-color: white transparent;
    display: block;
    width: 0;
}
`;
document.head.appendChild(styleSheet);
function loseLife() {
  lives--;
  combo = 0;
  document.getElementById('lbl-lives').innerText = translations[language].lives + ": " + lives;
  if (lives <= 0) {
    setTimeout(setGameOver, 1500);
  } else {
    setTimeout(resetRound, 1500);
  }
}
function resetRound() {
  project.isFlying = false;
  resetLaryngoscopePosition();
  setRandomWind();
  if (gameMode === '3d' && is3DAvailable) {
    updateTrajectoryLine();
    cameraMode = 'aim';
  }
  if (activeBubbleDOM) {
    activeBubbleDOM.remove();
    activeBubbleDOM = null;
  }
}

// Overlay triggers
function startNewGame() {
  score = 0;
  level = 1;
  lives = 5;
  targetSpeed = 0.03;
  combo = 0;

  // Read and save nickname
  const nickInput = document.getElementById('player-nick');
  if (nickInput && nickInput.value.trim() !== "") {
    playerNick = nickInput.value.trim().substring(0, 12);
    localStorage.setItem('laryngoscope_player_nick', playerNick);
  } else {
    playerNick = language === 'tr' ? 'Anestezist' : 'Anesthesiologist';
  }

  // Read and save country code
  const countrySelect = document.getElementById('player-country');
  if (countrySelect) {
    playerCountry = countrySelect.value || 'TR';
    localStorage.setItem('laryngoscope_player_country', playerCountry);
  } else {
    playerCountry = 'TR';
  }

  // Hide nick container and leaderboard when game starts
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) leadCont.classList.add('d-none');
  document.getElementById('gameOverlayScreen').classList.add('d-none');
  gameState = 'playing';
  resetRound();
  updateLocalization();
}
function nextLevel() {
  level++;
  lives = Math.min(lives + 1, 5);
  targetSpeed = 0.03 + level * 0.012;

  // Hide nick and leaderboard
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) leadCont.classList.add('d-none');
  document.getElementById('gameOverlayScreen').classList.add('d-none');
  gameState = 'playing';
  resetRound();
  updateLocalization();
}
function setGameOver() {
  gameState = 'gameover';
  if (score > highscore) {
    highscore = score;
    localStorage.setItem('laryngoscope_highscore', highscore);
  }

  // Add current score to leaderboard
  addScoreToLeaderboard(playerNick, playerCountry, score);
  document.getElementById('gameOverlayScreen').classList.remove('d-none');
  document.getElementById('stat-box-score').innerText = score;
  document.getElementById('stat-box-highscore').innerText = highscore;
  document.getElementById('stats-grid').classList.remove('d-none');

  // Hide nick input and show leaderboard
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) {
    leadCont.classList.remove('d-none');
    displayLeaderboard();
  }
  updateLocalization();
}
function triggerLevelUp() {
  gameState = 'levelup';
  document.getElementById('gameOverlayScreen').classList.remove('d-none');
  document.getElementById('stat-box-score').innerText = score;
  document.getElementById('stat-box-highscore').innerText = highscore;
  document.getElementById('stats-grid').classList.remove('d-none');

  // Hide nick input and leaderboard during level up transition
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) leadCont.classList.add('d-none');
  updateLocalization();
}
function syncSliderValues() {
  aimPitch = parseFloat(document.getElementById('slider-pitch').value);
  aimYaw = parseFloat(document.getElementById('slider-yaw').value);
  throwPower = parseFloat(document.getElementById('slider-power').value);
  if (gameMode === '3d' && is3DAvailable) {
    updateTrajectoryLine();
  }
}

// Game Mode Toggle Logic (2D/3D switches)
function toggleGameMode() {
  if (!is3DAvailable) return;
  initAudio();
  const canvas2DNode = document.getElementById('gameCanvas2D');
  const canvas3DNode = document.getElementById('gameCanvas3D');
  const toggleBtn = document.getElementById('btn-mode-toggle');
  if (gameMode === '2d') {
    gameMode = '3d';
    canvas2DNode.classList.add('d-none');
    canvas3DNode.classList.remove('d-none');
    toggleBtn.innerText = '2D';
    if (project.isFlying) {
      cameraMode = 'flight';
    } else {
      cameraMode = 'aim';
    }
    onWindowResize();
  } else {
    gameMode = '2d';
    canvas3DNode.classList.add('d-none');
    canvas2DNode.classList.remove('d-none');
    toggleBtn.innerText = '3D';
  }
}
function disable3DMode() {
  is3DAvailable = false;
  gameMode = '2d';
  const toggleBtn = document.getElementById('btn-mode-toggle');
  if (toggleBtn) {
    toggleBtn.style.opacity = '0.5';
    toggleBtn.style.cursor = 'not-allowed';
    toggleBtn.title = language === 'tr' ? '3D Modu Kullanılamıyor' : '3D Mode Unavailable';
    toggleBtn.innerText = '2D';
    toggleBtn.onclick = null;
  }
  const toast = document.getElementById('threejs-warning-toast');
  if (toast) {
    toast.classList.remove('d-none');
    toast.innerText = language === 'tr' ? 'WebGL/Three.js yüklenemedi veya desteklenmiyor. 2D moduna geçildi.' : 'WebGL/Three.js failed to load or is unsupported. Falling back to 2D mode.';
    setTimeout(() => {
      toast.style.opacity = '0';
      setTimeout(() => toast.classList.add('d-none'), 500);
    }, 4000);
  }
}

// 2D/3D Unified Animation loop
function animate() {
  requestAnimationFrame(animate);

  // Decrement flash timers
  if (monitorFlashTimer > 0) monitorFlashTimer--;

  // Dialog bubbles
  if (bubbleTimer > 0) {
    bubbleTimer--;
    if (bubbleTimer <= 0 && activeBubbleDOM) {
      activeBubbleDOM.remove();
      activeBubbleDOM = null;
    }
  }

  // Unified Physics Update
  if (gameState === 'playing') {
    // 1. Surgeon target lateral movement
    if (!project.isFlying) {
      surgeonZ += targetDirection * targetSpeed;
      if (surgeonGroup) {
        surgeonGroup.position.z = surgeonZ;
      }
      if (surgeonZ > 1.2) {
        targetDirection = -1;
      } else if (surgeonZ < -1.2) {
        targetDirection = 1;
      }
    }

    // 2. Projectile flight physics
    if (project.isFlying) {
      project.vel.y -= GRAVITY * 0.016;
      project.vel.x += windX * 0.01;
      project.vel.z += windZ * 0.01;
      project.pos.add(project.vel);
      if (laryngoscopeMesh) {
        laryngoscopeMesh.position.set(project.pos.x, project.pos.y, project.pos.z);
        laryngoscopeMesh.rotation.x += 0.15;
        laryngoscopeMesh.rotation.y += 0.08;
      }

      // Particles trails generator
      if (gameMode === '2d') {
        if (Math.random() > 0.4) {
          project.trailParticles.push({
            pos: project.pos.clone(),
            alpha: 0.5,
            size: Math.random() * 6 + 3
          });
        }
      } else {
        if (Math.random() > 0.4 && scene && THREE) {
          const trailGeo = new THREE.SphereGeometry(0.04, 4, 4);
          const trailMat = new THREE.MeshBasicMaterial({
            color: 0xffffff,
            transparent: true,
            opacity: 0.4
          });
          const trailMesh = new THREE.Mesh(trailGeo, trailMat);
          trailMesh.position.set(project.pos.x, project.pos.y, project.pos.z);
          scene.add(trailMesh);
          project.trailParticles.push({
            mesh: trailMesh,
            alpha: 0.4
          });
        }
      }
      check3DCollisions();
    }

    // Decay particle trails
    if (gameMode === '2d') {
      for (let i = project.trailParticles.length - 1; i >= 0; i--) {
        const tp = project.trailParticles[i];
        tp.alpha -= 0.02;
        if (tp.alpha <= 0) {
          project.trailParticles.splice(i, 1);
        }
      }
    } else {
      for (let i = project.trailParticles.length - 1; i >= 0; i--) {
        const tp = project.trailParticles[i];
        tp.alpha -= 0.015;
        if (tp.mesh && tp.mesh.material) {
          tp.mesh.material.opacity = tp.alpha;
          tp.mesh.scale.multiplyScalar(0.96);
        }
        if (tp.alpha <= 0) {
          if (scene && tp.mesh) scene.remove(tp.mesh);
          project.trailParticles.splice(i, 1);
        }
      }
    }
  }

  // Decay burst particles
  if (gameMode === '2d') {
    for (let i = particles2D.length - 1; i >= 0; i--) {
      const p = particles2D[i];
      p.pos.add(p.vel);
      p.vel.y += 0.15; // screen down pull gravity
      p.alpha -= p.decay;
      if (p.alpha <= 0) {
        particles2D.splice(i, 1);
      }
    }
  } else {
    for (let i = particles3D.length - 1; i >= 0; i--) {
      const p = particles3D[i];
      p.mesh.position.add(p.vel);
      p.vel.y -= 0.005;
      p.alpha -= p.decay;
      if (p.mesh && p.mesh.material) {
        p.mesh.material.opacity = p.alpha;
        p.mesh.scale.multiplyScalar(0.96);
      }
      if (p.alpha <= 0) {
        if (scene && p.mesh) scene.remove(p.mesh);
        particles3D.splice(i, 1);
      }
    }
  }

  // Draw/Render active views
  if (gameMode === '3d' && is3DAvailable && scene && camera && renderer) {
    if (cameraMode === 'aim') {
      cameraTargetPos.set(-13, 4.0, 0);
      cameraTargetLook.set(4, 1.2, 0);
    } else if (cameraMode === 'flight') {
      cameraTargetPos.set(project.pos.x - 3.5, project.pos.y + 1.8, project.pos.z);
      cameraTargetLook.set(project.pos.x, project.pos.y, project.pos.z);
    } else if (cameraMode === 'hit') {
      cameraTargetPos.set(4.5, 2.0, surgeonZ + 1.5);
      cameraTargetLook.set(7.5, 1.6, surgeonZ);
    }
    camera.position.lerp(cameraTargetPos, 0.08);
    currentLookAt.lerp(cameraTargetLook, 0.08);
    camera.lookAt(currentLookAt);
    renderer.render(scene, camera);
  } else {
    draw2DScene();
  }
}

// Viewport resize adjusts
function onWindowResize() {
  const container = document.querySelector('.canvas-container');
  const width = container.clientWidth;
  const height = container.clientHeight;
  if (gameMode === '3d' && is3DAvailable && camera && renderer) {
    camera.aspect = width / height;
    camera.updateProjectionMatrix();
    renderer.setSize(width, height);
  }
}

// Global UI Settings hooks
function changeGameLanguage(lang) {
  language = lang;
  localStorage.setItem('anesthesia_pref_lang', lang);
  saveValueToCookie('anesthesia_pref_lang', lang);
  updateLocalization();
  updateLaminarWindHUD();
}
function toggleAudio() {
  audioEnabled = !audioEnabled;
  const btn = document.getElementById('btn-audio');
  if (audioEnabled) {
    btn.innerHTML = '<i class="fa-solid fa-volume-high"></i>';
  } else {
    btn.innerHTML = '<i class="fa-solid fa-volume-xmark"></i>';
  }
}

// Game Startup Launcher
function initGame() {
  // 1. Initialize 2D Canvas context
  canvas2D = document.getElementById('gameCanvas2D');
  ctx2d = canvas2D.getContext('2d');
  canvas2D.width = 1280;
  canvas2D.height = 720;

  // Reset background canvas on restart/init to force redraw
  bgCanvas = null;

  // 2. Perform WebGL & CDN libraries availability checks
  const isThreeLoaded = typeof THREE !== 'undefined';
  let isWebGLSupported = false;
  if (isThreeLoaded) {
    try {
      const canvasTest = document.createElement('canvas');
      isWebGLSupported = !!(window.WebGLRenderingContext && (canvasTest.getContext('webgl') || canvasTest.getContext('experimental-webgl')));
    } catch (e) {
      isWebGLSupported = false;
    }
  }
  is3DAvailable = isThreeLoaded && isWebGLSupported;

  // Load language preference
  const savedLang = localStorage.getItem('anesthesia_pref_lang') || getValueFromCookie('anesthesia_pref_lang');
  if (savedLang) {
    language = savedLang;
    localStorage.setItem('anesthesia_pref_lang', savedLang);
    saveValueToCookie('anesthesia_pref_lang', savedLang);
  }

  // Load highscore with cookie fallback & sync
  const localHighscore = parseInt(localStorage.getItem('laryngoscope_highscore') || 0);
  const cookieHighscore = parseInt(getValueFromCookie('laryngoscope_highscore') || 0);
  highscore = Math.max(localHighscore, cookieHighscore);
  localStorage.setItem('laryngoscope_highscore', highscore);
  saveValueToCookie('laryngoscope_highscore', highscore);

  // Load nickname and country with cookie fallback & sync
  const localNick = localStorage.getItem('laryngoscope_player_nick');
  const cookieNick = getValueFromCookie('laryngoscope_player_nick');
  playerNick = localNick || cookieNick || (language === 'tr' ? 'Anestezist' : 'Anesthesiologist');
  localStorage.setItem('laryngoscope_player_nick', playerNick);
  saveValueToCookie('laryngoscope_player_nick', playerNick);
  const localCountry = localStorage.getItem('laryngoscope_player_country');
  const cookieCountry = getValueFromCookie('laryngoscope_player_country');
  playerCountry = localCountry || cookieCountry || 'TR';
  localStorage.setItem('laryngoscope_player_country', playerCountry);
  saveValueToCookie('laryngoscope_player_country', playerCountry);

  // 3. Setup mouse and mobile drag listener binds
  setupViewportAiming();

  // 4. Load 3D elements if available, but stay in 2D mode by default to prevent blackouts
  gameMode = '2d';
  const canvas2DNode = document.getElementById('gameCanvas2D');
  const canvas3DNode = document.getElementById('gameCanvas3D');
  canvas2DNode.classList.remove('d-none');
  canvas3DNode.classList.add('d-none');
  if (is3DAvailable) {
    try {
      build3DScene();
      build3DLaryngoscope();
      document.getElementById('btn-mode-toggle').innerText = '3D';
    } catch (e) {
      console.error("ThreeJS scene build failed:", e);
      disable3DMode();
    }
  } else {
    disable3DMode();
  }
  gameState = 'menu';
  const nickInput = document.getElementById('player-nick');
  if (nickInput) nickInput.value = playerNick;
  const countrySelect = document.getElementById('player-country');
  if (countrySelect) countrySelect.value = playerCountry;
  initLeaderboard();

  // Show nick input and leaderboard in the menu overlay
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.remove('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) {
    leadCont.classList.remove('d-none');
    displayLeaderboard();
  }
  updateLocalization();
  setRandomWind();
  window.addEventListener('resize', onWindowResize);

  // Run animation frames loop
  animate();
}

// Bind load trigger
window.addEventListener('load', () => {
  initGame();
});
})(); } catch (e) { __ds_ns.__errors.push({ path: ".webexport/game-build/js/game.js", error: String((e && e.message) || e) }); }

// components/core/Badge.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * Anesthesia Briefs — Badge
 * Small uppercase pill: PRO, EMERGENCY, PREMIUM, urgency tags.
 */
function Badge({
  children,
  variant = "gold",
  icon = null,
  style = {},
  ...rest
}) {
  const palettes = {
    gold: {
      fg: "var(--ab-gold)",
      bg: "rgba(194,162,103,0.18)",
      border: "rgba(194,162,103,0.4)"
    },
    emergency: {
      fg: "var(--ab-red)",
      bg: "rgba(166,26,26,0.10)",
      border: "rgba(166,26,26,0.30)"
    },
    teal: {
      fg: "var(--ab-teal)",
      bg: "rgba(74,124,140,0.12)",
      border: "rgba(74,124,140,0.30)"
    },
    navy: {
      fg: "var(--ab-navy)",
      bg: "rgba(27,54,93,0.08)",
      border: "rgba(27,54,93,0.20)"
    },
    solidGold: {
      fg: "var(--ab-navy)",
      bg: "var(--ab-gold)",
      border: "transparent"
    }
  };
  const p = palettes[variant] || palettes.gold;
  return /*#__PURE__*/React.createElement("span", _extends({
    style: {
      display: "inline-flex",
      alignItems: "center",
      gap: "5px",
      fontFamily: "var(--font-display)",
      fontSize: "10px",
      fontWeight: 800,
      letterSpacing: "0.5px",
      textTransform: "uppercase",
      color: p.fg,
      background: p.bg,
      border: `0.8px solid ${p.border}`,
      padding: "3px 9px",
      borderRadius: "var(--radius-sm)",
      ...style
    }
  }, rest), icon, children);
}
Object.assign(__ds_scope, { Badge });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/Badge.jsx", error: String((e && e.message) || e) }); }

// components/core/BottomNav.jsx
try { (() => {
/**
 * Anesthesia Briefs — BottomNav
 * 5-tab bottom navigation. Active tab gets a gold rounded pill behind a
 * navy filled icon; inactive tabs are muted outline icons + Inter labels.
 */
function BottomNav({
  items,
  active = 0,
  onSelect,
  style = {}
}) {
  return /*#__PURE__*/React.createElement("nav", {
    style: {
      display: "flex",
      background: "#fff",
      borderTop: "1px solid #E2E8F0",
      boxShadow: "var(--shadow-nav)",
      padding: "8px 4px 10px",
      ...style
    }
  }, items.map((it, i) => {
    const on = i === active;
    return /*#__PURE__*/React.createElement("button", {
      key: i,
      onClick: () => onSelect && onSelect(i),
      style: {
        flex: 1,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        gap: "4px",
        background: "none",
        border: "none",
        cursor: "pointer",
        padding: 0
      }
    }, /*#__PURE__*/React.createElement("span", {
      style: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        minWidth: on ? "48px" : "auto",
        padding: on ? "4px 12px" : "4px",
        borderRadius: "var(--radius-md)",
        background: on ? "rgba(194,162,103,0.2)" : "transparent",
        color: on ? "var(--ab-navy)" : "var(--ab-text-muted)",
        fontSize: "20px",
        transition: "background var(--dur-press) var(--ease-cubic)"
      }
    }, /*#__PURE__*/React.createElement("i", {
      className: `fa-solid ${it.icon}`
    })), /*#__PURE__*/React.createElement("span", {
      style: {
        fontFamily: on ? "var(--font-display)" : "var(--font-body)",
        fontSize: "11px",
        fontWeight: on ? 700 : 500,
        color: on ? "var(--ab-navy)" : "var(--ab-text-muted)"
      }
    }, it.label));
  }));
}
Object.assign(__ds_scope, { BottomNav });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/BottomNav.jsx", error: String((e && e.message) || e) }); }

// components/core/Button.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * Anesthesia Briefs — Button
 * Primary navy / gold / teal / danger / ghost actions.
 */
function Button({
  children,
  variant = "primary",
  size = "md",
  icon = null,
  iconRight = null,
  fullWidth = false,
  disabled = false,
  style = {},
  ...rest
}) {
  const palettes = {
    primary: {
      bg: "var(--ab-navy)",
      fg: "#fff",
      border: "transparent",
      shadow: "0 4px 12px rgba(27,54,93,0.15)"
    },
    gold: {
      bg: "var(--ab-gold)",
      fg: "var(--ab-navy)",
      border: "transparent",
      shadow: "0 4px 12px rgba(194,162,103,0.25)"
    },
    teal: {
      bg: "var(--ab-teal)",
      fg: "#fff",
      border: "transparent",
      shadow: "0 4px 12px rgba(74,124,140,0.2)"
    },
    danger: {
      bg: "var(--ab-red)",
      fg: "#fff",
      border: "transparent",
      shadow: "0 4px 12px rgba(166,26,26,0.2)"
    },
    ghost: {
      bg: "transparent",
      fg: "var(--ab-navy)",
      border: "var(--ab-border)",
      shadow: "none"
    }
  };
  const sizes = {
    sm: {
      padding: "8px 16px",
      fontSize: "13px",
      radius: "var(--radius-sm)",
      gap: "6px"
    },
    md: {
      padding: "12px 22px",
      fontSize: "15px",
      radius: "var(--radius-md)",
      gap: "8px"
    },
    lg: {
      padding: "15px 28px",
      fontSize: "16px",
      radius: "var(--radius-md)",
      gap: "10px"
    }
  };
  const p = palettes[variant] || palettes.primary;
  const s = sizes[size] || sizes.md;
  return /*#__PURE__*/React.createElement("button", _extends({
    disabled: disabled,
    style: {
      display: "inline-flex",
      alignItems: "center",
      justifyContent: "center",
      gap: s.gap,
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: s.fontSize,
      lineHeight: 1,
      padding: s.padding,
      width: fullWidth ? "100%" : "auto",
      color: p.fg,
      background: p.bg,
      border: `1.2px solid ${p.border}`,
      borderRadius: s.radius,
      boxShadow: disabled ? "none" : p.shadow,
      cursor: disabled ? "not-allowed" : "pointer",
      opacity: disabled ? 0.55 : 1,
      transition: "transform var(--dur-press) var(--ease-cubic), filter var(--dur-press) var(--ease-cubic)",
      ...style
    },
    onMouseDown: e => {
      if (!disabled) e.currentTarget.style.transform = "scale(0.98)";
    },
    onMouseUp: e => {
      e.currentTarget.style.transform = "scale(1)";
    },
    onMouseLeave: e => {
      e.currentTarget.style.transform = "scale(1)";
    }
  }, rest), icon, children, iconRight);
}
Object.assign(__ds_scope, { Button });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/Button.jsx", error: String((e && e.message) || e) }); }

// components/core/CategoryChip.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * Anesthesia Briefs — CategoryChip
 * Selectable rounded pill used for clinical category / urgency filters.
 * Navy fill when selected, linen when idle.
 */
function CategoryChip({
  children,
  selected = false,
  onClick,
  style = {},
  ...rest
}) {
  return /*#__PURE__*/React.createElement("button", _extends({
    onClick: onClick,
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: "14px",
      lineHeight: 1,
      padding: "11px 18px",
      whiteSpace: "nowrap",
      color: selected ? "#fff" : "var(--ab-navy)",
      background: selected ? "var(--ab-navy)" : "var(--surface-card)",
      border: "none",
      borderRadius: "var(--radius-md)",
      cursor: "pointer",
      boxShadow: selected ? "0 4px 10px rgba(27,54,93,0.18)" : "none",
      transition: "background var(--dur-press) var(--ease-cubic), color var(--dur-press) var(--ease-cubic)",
      ...style
    }
  }, rest), children);
}
Object.assign(__ds_scope, { CategoryChip });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/CategoryChip.jsx", error: String((e && e.message) || e) }); }

// components/core/ClinicalNote.jsx
try { (() => {
/**
 * Anesthesia Briefs — ClinicalNote
 * Bordered note box with a solid left accent strip. Teal = info, red = warning.
 * Mirrors ClinicalNoteBox from the Flutter theme.
 */
function ClinicalNote({
  children,
  tone = "info",
  icon = null,
  style = {}
}) {
  const isWarn = tone === "warning";
  const accent = isWarn ? "var(--ab-red)" : "var(--ab-teal)";
  const bg = isWarn ? "rgba(166,26,26,0.05)" : "rgba(74,124,140,0.05)";
  const defaultIcon = isWarn ? "fa-triangle-exclamation" : "fa-circle-info";
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      background: bg,
      border: `0.8px solid color-mix(in srgb, ${accent} 15%, transparent)`,
      borderRadius: "var(--radius-md)",
      overflow: "hidden",
      ...style
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: "4px",
      background: accent,
      flex: "none"
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: "8px",
      padding: "12px",
      alignItems: "flex-start"
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      color: accent,
      fontSize: "16px",
      lineHeight: 1.3,
      flex: "none"
    }
  }, icon || /*#__PURE__*/React.createElement("i", {
    className: `fa-solid ${defaultIcon}`
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-body)",
      fontSize: "12.5px",
      fontWeight: 500,
      lineHeight: 1.45,
      color: "var(--ab-navy)"
    }
  }, children)));
}
Object.assign(__ds_scope, { ClinicalNote });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/ClinicalNote.jsx", error: String((e && e.message) || e) }); }

// components/core/DoseRow.jsx
try { (() => {
/**
 * Anesthesia Briefs — DoseRow
 * Calculator result line: drug/label on the left, a teal primary value and a
 * navy secondary value (e.g. mL) stacked on the right, with prep note below.
 */
function DoseRow({
  label,
  value,
  secondary = null,
  note = null,
  divider = true,
  style = {}
}) {
  return /*#__PURE__*/React.createElement("div", {
    style: {
      paddingBottom: "14px",
      borderBottom: divider ? "1px solid rgba(27,54,93,0.10)" : "none",
      ...style
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      justifyContent: "space-between",
      alignItems: "flex-start",
      gap: "16px"
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: "15px",
      color: "var(--ab-navy)",
      flex: 1
    }
  }, label), /*#__PURE__*/React.createElement("div", {
    style: {
      textAlign: "right",
      flex: "none"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: "16px",
      color: "var(--ab-teal)"
    }
  }, value), secondary && /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: "15px",
      color: "var(--ab-navy)",
      marginTop: "2px"
    }
  }, secondary))), note && /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "6px 0 0",
      fontFamily: "var(--font-body)",
      fontSize: "12.5px",
      lineHeight: 1.5,
      color: "var(--ab-text-muted)"
    }
  }, note));
}
Object.assign(__ds_scope, { DoseRow });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/DoseRow.jsx", error: String((e && e.message) || e) }); }

// components/core/DrugListItem.jsx
try { (() => {
const CAT = {
  induction: {
    bg: "var(--cat-induction-bg)",
    border: "var(--cat-induction-border)",
    iconBg: "#DBEAFE",
    icon: "var(--cat-induction-accent)"
  },
  analgesic: {
    bg: "var(--cat-analgesic-bg)",
    border: "var(--cat-analgesic-border)",
    iconBg: "#FEF3C7",
    icon: "var(--cat-analgesic-accent)"
  },
  neuromuscular: {
    bg: "var(--cat-nmb-bg)",
    border: "var(--cat-nmb-border)",
    iconBg: "#CCFBF1",
    icon: "var(--cat-nmb-accent)"
  },
  reversal: {
    bg: "var(--cat-reversal-bg)",
    border: "var(--cat-reversal-border)",
    iconBg: "#D1FAE5",
    icon: "var(--cat-reversal-accent)"
  },
  cardiovascular: {
    bg: "var(--cat-cardio-bg)",
    border: "var(--cat-cardio-border)",
    iconBg: "#FEE2E2",
    icon: "var(--cat-cardio-accent)"
  },
  local: {
    bg: "var(--cat-local-bg)",
    border: "var(--cat-local-border)",
    iconBg: "#EDE9FE",
    icon: "var(--cat-local-accent)"
  },
  other: {
    bg: "var(--cat-other-bg)",
    border: "var(--cat-other-border)",
    iconBg: "#F1F5F9",
    icon: "var(--cat-other-accent)"
  }
};

/**
 * Anesthesia Briefs — DrugListItem
 * Category-tinted list row: avatar icon, name, generic/class subtitle,
 * optional premium star, chevron. `category` drives the color treatment.
 */
function DrugListItem({
  name,
  subtitle = "",
  category = "other",
  premium = false,
  onClick,
  style = {}
}) {
  const c = CAT[category] || CAT.other;
  return /*#__PURE__*/React.createElement("div", {
    onClick: onClick,
    style: {
      display: "flex",
      alignItems: "center",
      gap: "14px",
      background: c.bg,
      border: `1px solid ${c.border}`,
      borderRadius: "var(--radius-lg)",
      padding: "14px 16px",
      cursor: onClick ? "pointer" : "default",
      ...style
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: "44px",
      height: "44px",
      flex: "none",
      borderRadius: "50%",
      background: c.iconBg,
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      color: c.icon,
      fontSize: "18px"
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-prescription-bottle-medical"
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      minWidth: 0
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: "8px"
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: "16px",
      color: "var(--ab-navy)"
    }
  }, name), premium && /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-star",
    style: {
      color: "var(--ab-gold)",
      fontSize: "13px"
    }
  })), subtitle && /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-body)",
      fontSize: "13px",
      color: "var(--ab-text-muted)",
      marginTop: "2px"
    }
  }, subtitle)), /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-chevron-right",
    style: {
      color: "var(--ab-navy)",
      fontSize: "15px",
      flex: "none"
    }
  }));
}
Object.assign(__ds_scope, { DrugListItem });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/DrugListItem.jsx", error: String((e && e.message) || e) }); }

// components/core/PremiumCard.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * Anesthesia Briefs — PremiumCard
 * The signature surface: soft 3D elevation, 1.2px tinted outline,
 * subtle top-left→bottom-right sheen, optional left accent strip.
 * Press-scales to 0.98 when interactive (onClick supplied).
 */
function PremiumCard({
  children,
  accent = null,
  // accent color (border tint + strip + shadow)
  baseColor = "var(--surface-card)",
  accentStrip = false,
  // show a left colored strip
  radius = "var(--radius-xl)",
  padding = "16px",
  onClick = null,
  style = {},
  ...rest
}) {
  const [pressed, setPressed] = React.useState(false);
  const interactive = typeof onClick === "function";
  const sheen = `linear-gradient(135deg, ${baseColor} 0%, color-mix(in srgb, ${baseColor} 88%, #fff) 100%)`;
  const borderColor = accent ? `color-mix(in srgb, ${accent} 30%, transparent)` : "rgba(255,255,255,0.7)";
  return /*#__PURE__*/React.createElement("div", _extends({
    onClick: onClick,
    onMouseDown: () => interactive && setPressed(true),
    onMouseUp: () => setPressed(false),
    onMouseLeave: () => setPressed(false),
    style: {
      position: "relative",
      background: sheen,
      border: `1.2px solid ${borderColor}`,
      borderRadius: radius,
      boxShadow: pressed ? "var(--shadow-card-press)" : "var(--shadow-card)",
      transform: pressed ? "scale(0.98)" : "scale(1)",
      transition: "transform var(--dur-press) var(--ease-cubic), box-shadow var(--dur-press) var(--ease-cubic)",
      cursor: interactive ? "pointer" : "default",
      overflow: "hidden",
      ...style
    }
  }, rest), accentStrip && accent && /*#__PURE__*/React.createElement("div", {
    style: {
      position: "absolute",
      left: 0,
      top: 0,
      bottom: 0,
      width: "5.5px",
      background: accent
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      padding,
      paddingLeft: accentStrip && accent ? `calc(${padding} + 6px)` : padding
    }
  }, children));
}
Object.assign(__ds_scope, { PremiumCard });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/PremiumCard.jsx", error: String((e && e.message) || e) }); }

// components/core/SearchField.jsx
try { (() => {
/**
 * Anesthesia Briefs — SearchField
 * Pill search input. `onLight` for the cream body, default for the navy header.
 */
function SearchField({
  placeholder = "Search…",
  onLight = true,
  value,
  onChange,
  style = {}
}) {
  const bg = onLight ? "#fff" : "rgba(255,255,255,0.9)";
  const border = onLight ? "var(--surface-card)" : "rgba(27,54,93,0.12)";
  const radius = onLight ? "var(--radius-md)" : "var(--radius-pill)";
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: "12px",
      background: bg,
      border: `1.5px solid ${border}`,
      borderRadius: radius,
      padding: "13px 18px",
      boxShadow: onLight ? "none" : "0 4px 12px rgba(0,0,0,0.06)",
      ...style
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-magnifying-glass",
    style: {
      color: "var(--ab-teal)",
      fontSize: "16px"
    }
  }), /*#__PURE__*/React.createElement("input", {
    value: value,
    onChange: onChange,
    placeholder: placeholder,
    style: {
      flex: 1,
      border: "none",
      outline: "none",
      background: "transparent",
      fontFamily: "var(--font-body)",
      fontSize: "15px",
      color: "var(--ab-navy)"
    }
  }));
}
Object.assign(__ds_scope, { SearchField });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/SearchField.jsx", error: String((e && e.message) || e) }); }

// components/core/SectionHeader.jsx
try { (() => {
/**
 * Anesthesia Briefs — SectionHeader
 * Optional uppercase eyebrow chip, bold Outfit title, optional icon + subtitle.
 */
function SectionHeader({
  title,
  subtitle = null,
  eyebrow = null,
  eyebrowColor = "var(--ab-teal)",
  icon = null,
  style = {}
}) {
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      flexDirection: "column",
      gap: "2px",
      ...style
    }
  }, eyebrow && /*#__PURE__*/React.createElement("span", {
    style: {
      alignSelf: "flex-start",
      fontFamily: "var(--font-display)",
      fontSize: "10px",
      fontWeight: 800,
      letterSpacing: "0.5px",
      textTransform: "uppercase",
      color: eyebrowColor,
      background: `color-mix(in srgb, ${eyebrowColor} 12%, transparent)`,
      border: `0.8px solid color-mix(in srgb, ${eyebrowColor} 30%, transparent)`,
      borderRadius: "6px",
      padding: "2px 8px",
      marginBottom: "6px"
    }
  }, eyebrow), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: "8px"
    }
  }, icon && /*#__PURE__*/React.createElement("span", {
    style: {
      color: "var(--ab-teal)",
      fontSize: "20px"
    }
  }, icon), /*#__PURE__*/React.createElement("h2", {
    style: {
      margin: 0,
      fontFamily: "var(--font-display)",
      fontSize: "18px",
      fontWeight: 900,
      letterSpacing: "-0.2px",
      color: "var(--ab-navy)"
    }
  }, title)), subtitle && /*#__PURE__*/React.createElement("p", {
    style: {
      margin: 0,
      fontFamily: "var(--font-body)",
      fontSize: "12px",
      color: "var(--ab-text-muted)"
    }
  }, subtitle));
}
Object.assign(__ds_scope, { SectionHeader });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/core/SectionHeader.jsx", error: String((e && e.message) || e) }); }

// game-build/js/airway_game.js
try { (() => {
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
    leaderboard = Object.values(unique).sort((a, b) => b.score - a.score).slice(0, 10);
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
const cartTools = [{
  id: 'mac_laryngo',
  icon: 'fa-solid fa-wrench',
  name: {
    tr: 'Macintosh Bıçak',
    en: 'Mac Laryngoscope'
  },
  category: 'laryngoscopes'
}, {
  id: 'video_laryngo',
  icon: 'fa-solid fa-camera',
  name: {
    tr: 'Videolaringoskop',
    en: 'Videolaryngoscope'
  },
  category: 'laryngoscopes'
}, {
  id: 'fiberoptic',
  icon: 'fa-solid fa-staff-snake',
  name: {
    tr: 'Fiberoptik Bronkoskop',
    en: 'Flexible Bronchoscope'
  },
  category: 'laryngoscopes'
}, {
  id: 'lma_classic',
  icon: 'fa-solid fa-circle',
  name: {
    tr: 'LMA Klasik',
    en: 'LMA Classic'
  },
  category: 'sga'
}, {
  id: 'lma_supreme',
  icon: 'fa-solid fa-shield-halved',
  name: {
    tr: 'LMA Supreme / I-Gel',
    en: 'LMA Supreme / I-Gel'
  },
  category: 'sga'
}, {
  id: 'bougie',
  icon: 'fa-solid fa-compass-drafting',
  name: {
    tr: 'Kılavuz Buji',
    en: 'Airway Bougie'
  },
  category: 'adjuncts'
}, {
  id: 'sugammadex',
  icon: 'fa-solid fa-capsules',
  name: {
    tr: 'Sugammadex (İlaç)',
    en: 'Sugammadex (Meds)'
  },
  category: 'adjuncts'
}, {
  id: 'crico_kit',
  icon: 'fa-solid fa-kit-medical',
  name: {
    tr: 'Krikotiroidotomi Kiti',
    en: 'eFONA Cricothyroid Kit'
  },
  category: 'emergency'
}];

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
      choices: [{
        text: {
          tr: "Hastayı uyutmadan (lokal anesteziyle) uyanık fiberoptik entübasyon (AFOI) hazırlığı yaparım.",
          en: "Prepare for Awake Fiberoptic Intubation (AFOI) under local anesthesia."
        },
        nextNode: 'c1_awake_success',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38
      }, {
        text: {
          tr: "Hızlı seri indüksiyon (RSI) yapıp doğrudan Macintosh bıçakla entübe etmeye çalışırım.",
          en: "Perform Rapid Sequence Induction (RSI) and attempt direct laryngoscopy."
        },
        nextNode: 'c1_rsi_fail',
        score: 0,
        trauma: 15,
        spO2: 90,
        hr: 105,
        bp: '145/90',
        etco2: 0
      }, {
        text: {
          tr: "Sevofluran ile inhalasyon indüksiyonu yapıp spontan solunumu koruyarak ilerlerim.",
          en: "Inhalation induction with Sevoflurane, maintaining spontaneous breathing."
        },
        nextNode: 'c1_inhalation_obstruction',
        score: 50,
        trauma: 0,
        spO2: 95,
        hr: 90,
        bp: '130/80',
        etco2: 25
      }]
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
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) seçip havayolu bujisi (bougie) ile entübasyonu denerim.",
          en: "Select Videolaryngoscope (VL) and attempt intubation using a bougie."
        },
        nextNode: 'c1_vl_success',
        score: 80,
        trauma: 10,
        spO2: 98,
        hr: 85,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Hemen LMA Supreme yerleştirerek ventilasyonu sağlamayı denerim.",
          en: "Immediately place LMA Supreme to restore ventilation."
        },
        nextNode: 'c1_lma_success',
        score: 60,
        trauma: 5,
        spO2: 97,
        hr: 80,
        bp: '118/75',
        etco2: 36,
        requiredTool: 'lma_supreme'
      }, {
        text: {
          tr: "Körlemesine entübasyon için tüp içine metal stile yerleştirip tekrar denerim.",
          en: "Insert metal stylet into the tube and attempt blind intubation again."
        },
        nextNode: 'c1_blind_trauma',
        score: -50,
        trauma: 40,
        spO2: 78,
        hr: 120,
        bp: '160/95',
        etco2: 0
      }]
    },
    'c1_inhalation_obstruction': {
      text: {
        tr: "İnhalasyon indüksiyonu sırasında anestezinin derinleşmesiyle hastanın havayolu tamamen tıkandı (obstrüksiyon). Göğüs hareketleri paradoks hal aldı, SpO2 %85'e geriledi. Maskeyle havalandırma çabalarınız yetersiz kalıyor. İlk hamleniz nedir?",
        en: "During inhalation induction, deep anesthesia caused complete airway obstruction. Chest movements became paradoxical, SpO2 is down to 85%. Face mask ventilation fails. What is your immediate rescue action?"
      },
      choices: [{
        text: {
          tr: "Oral airway yerleştirip başa pozisyon vererek iki kişiyle maske ventilasyonu denerim.",
          en: "Insert oral airway, perform head-tilt/jaw-thrust, and attempt two-person ventilation."
        },
        nextNode: 'c1_mask_optimized',
        score: 80,
        trauma: 0,
        spO2: 97,
        hr: 85,
        bp: '125/80',
        etco2: 36
      }, {
        text: {
          tr: "Hemen LMA Classic takarak körleme ventilasyonu denerim.",
          en: "Immediately insert LMA Classic and attempt rescue ventilation."
        },
        nextNode: 'c1_lma_success',
        score: 70,
        trauma: 5,
        spO2: 97,
        hr: 80,
        bp: '118/75',
        etco2: 36,
        requiredTool: 'lma_classic'
      }]
    },
    'c1_mask_optimized': {
      text: {
        tr: "Optimizasyon başarılı! Çift el maske ventilasyonu ve oral airway sayesinde SpO2 %97'ye yükseldi. Ancak cerrahi için havayolunu emniyete almalısınız. Hangi cihazı seçeceksiniz?",
        en: "Optimization succeeded! Two-person ventilation and oral airway restored SpO2 to 97%. However, you must secure the airway for surgery. Which device will you select?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) yardımıyla entübasyon denerim.",
          en: "Attempt intubation under Videolaryngoscopy (VL)."
        },
        nextNode: 'c1_vl_success',
        score: 80,
        trauma: 5,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Sugammadex vererek hastayı uyandırıp uyanık fiberoptik entübasyona geri dönerim.",
          en: "Administer Sugammadex to wake the patient and revert to awake fiberoptic."
        },
        nextNode: 'c1_reverse_wake',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 35,
        requiredTool: 'sugammadex'
      }]
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
      choices: [{
        text: {
          tr: "LMA içinden fiberoptik eşliğinde entübasyon yaparım.",
          en: "Perform fiberoptic intubation through the LMA."
        },
        nextNode: 'c1_vl_success',
        score: 90,
        trauma: 0,
        spO2: 99,
        hr: 70,
        bp: '115/75',
        etco2: 38
      }, {
        text: {
          tr: "Hastayı uyandırıp uyanık fiberoptik entübasyona dönerim (Sugammadex ile).",
          en: "Wake the patient up using Sugammadex and return to awake fiberoptic."
        },
        nextNode: 'c1_reverse_wake',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 35,
        requiredTool: 'sugammadex'
      }]
    },
    'c1_blind_trauma': {
      text: {
        tr: "Sert stileli tüple körlemesine entübasyon denemesi havayolu dokusunda şiddetli kanama ve ödeme yol açtı! SpO2 %78'e geriledi. Kanama nedeniyle artık maskeyle de havalandıramıyorsunuz (CVCI riski). Nabız 120 bpm, taşikardi var. Ne yapacaksınız?",
        en: "The blind intubation attempt with a rigid stylet caused severe mucosal bleeding and edema! SpO2 dropped to 78%. Bleeding makes face mask ventilation impossible (CVCI warning). HR is 120 bpm. What is your next move?"
      },
      choices: [{
        text: {
          tr: "Sugammadex verip kas gevşeticiyi geri çevirerek uyanmasını beklerim.",
          en: "Give Sugammadex to reverse neuromuscular blockade and wait for return of ventilation."
        },
        nextNode: 'c1_hypoxia_arrest',
        score: -20,
        trauma: 0,
        spO2: 40,
        hr: 135,
        bp: '90/50',
        etco2: 0,
        requiredTool: 'sugammadex'
      }, {
        text: {
          tr: "Hemen krikotiroidotomi (eFONA) kitini hazırlayıp acil boyun cerrahisine geçerim.",
          en: "Immediately prepare the cricothyroidotomy (eFONA) kit for emergency neck access."
        },
        nextNode: 'c1_crico_rescue',
        score: 80,
        trauma: 10,
        spO2: 95,
        hr: 90,
        bp: '110/70',
        etco2: 36,
        requiredTool: 'crico_kit'
      }]
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
      choices: [{
        text: {
          tr: "Maske ventilasyonunu optimize ederim (oral/nasal airway takarım, çift el maske tutuşuna geçerim).",
          en: "Optimize mask ventilation (insert oral/nasal airway, use two-handed mask grip)."
        },
        nextNode: 'c2_mask_opt_try',
        score: 100,
        trauma: 0,
        spO2: 97,
        hr: 85,
        bp: '130/80',
        etco2: 30
      }, {
        text: {
          tr: "Macintosh laringoskopla doğrudan entübasyon denerim.",
          en: "Attempt direct laryngoscopy with Macintosh blade immediately."
        },
        nextNode: 'c2_laryngo_fail',
        score: -20,
        trauma: 15,
        spO2: 88,
        hr: 100,
        bp: '140/90',
        etco2: 0,
        requiredTool: 'mac_laryngo'
      }, {
        text: {
          tr: "Hemen bir Laryngeal Mask (LMA Classic) takarım.",
          en: "Insert Laryngeal Mask Airway (LMA Classic) immediately."
        },
        nextNode: 'c2_lma_fail_node',
        score: 50,
        trauma: 5,
        spO2: 85,
        hr: 110,
        bp: '150/95',
        etco2: 0,
        requiredTool: 'lma_classic'
      }]
    },
    'c2_mask_opt_try': {
      text: {
        tr: "Optimizasyona rağmen (çift el maske tutuşu ve oral airway) ventilasyon sağlanamadı! Hastanın anatomisinde derin bir laringospazm veya havayolu tıkanıklığı var. SpO2 %89'a geriledi, nabız 105 bpm'e yükseldi. Hızlı aksiyon almalısınız:",
        en: "Despite optimizing (two-handed grip and oral airway), face mask ventilation remains impossible! The patient has a severe airway obstruction. SpO2 dropped to 89%, HR rose to 105 bpm. Act fast:"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) seçip glottik açıklığı görerek entübasyon denerim.",
          en: "Select Videolaryngoscope (VL) and attempt intubation under direct visualization."
        },
        nextNode: 'c2_vl_first_attempt',
        score: 90,
        trauma: 10,
        spO2: 90,
        hr: 100,
        bp: '135/85',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Havayolunu kurtarmak için ikinci nesil LMA (LMA Supreme) yerleştiririm.",
          en: "Insert a second-generation SGA (LMA Supreme) to rescue the airway."
        },
        nextNode: 'c2_lma_fail_node',
        score: 80,
        trauma: 5,
        spO2: 80,
        hr: 115,
        bp: '155/95',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }]
    },
    'c2_laryngo_fail': {
      text: {
        tr: "Macintosh bıçakla yaptığınız laringoskopide vokal kordlar görülemedi (Grade 4 görünüm). SpO2 %84'e geriledi. Tekrar eden doğrudan laringoskopi denemesi havayolu ödemini artırır. Ne yapacaksınız?",
        en: "Direct laryngoscopy with Mac blade failed to show the vocal cords (Grade 4 view). SpO2 is now 84%. Repeated direct attempts will worsen edema. What will you do?"
      },
      choices: [{
        text: {
          tr: "LMA Supreme yerleştirerek acil havalandırma (solunum) desteği sağlarım.",
          en: "Place LMA Supreme to establish rescue ventilation."
        },
        nextNode: 'c2_lma_fail_node',
        score: 80,
        trauma: 5,
        spO2: 80,
        hr: 110,
        bp: '150/90',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }, {
        text: {
          tr: "Videolaringoskop (VL) ve Buji (Bougie) kombinasyonunu hazırlar ve denerim.",
          en: "Prepare and attempt Videolaryngoscopy (VL) with a Bougie."
        },
        nextNode: 'c2_vl_with_bougie_success',
        score: 90,
        trauma: 10,
        spO2: 98,
        hr: 85,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }]
    },
    'c2_lma_fail_node': {
      text: {
        tr: "Kritik Gelişme! Yerleştirdiğiniz LMA ventilasyon sağlamadı. Kaçak devam ediyor, göğüs hareket etmiyor ve SpO2 %72'ye kadar indi! Nabız 120 bpm (taşikardi). Hasta CVCI (Havalandırılamıyor, Entübe Edilemiyor) aşamasında. Ne yapacaksınız?",
        en: "Critical Event! The LMA failed to establish ventilation. Airway leak persists, no chest rise, and SpO2 plummeted to 72%! HR is 120 bpm. The patient is in a CVCI state. What is your choice?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden Krikotiroidotomi Kiti (eFONA) ile acil boyun cerrahisi uygularım.",
          en: "Immediately perform emergency front-of-neck access (eFONA) using cricothyroid kit."
        },
        nextNode: 'c2_crico_rescue_success',
        score: 100,
        trauma: 15,
        spO2: 95,
        hr: 90,
        bp: '110/70',
        etco2: 38,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Farklı bir laringoskop bıçağı takıp metal stileyle körlemesine entübasyon denerim.",
          en: "Mount a different laryngoscope blade and attempt blind intubation with a metal stylet."
        },
        nextNode: 'c2_blind_arrest',
        score: -100,
        trauma: 50,
        spO2: 40,
        hr: 140,
        bp: '80/40',
        etco2: 0
      }, {
        text: {
          tr: "Sugammadex vererek gevşetici etkiyi kaldırıp hastayı uyanmaya bırakırım.",
          en: "Administer Sugammadex to reverse paralysis and wait for spontaneous recovery."
        },
        nextNode: 'c2_suga_arrest',
        score: -50,
        trauma: 0,
        spO2: 45,
        hr: 135,
        bp: '85/45',
        etco2: 0,
        requiredTool: 'sugammadex'
      }]
    },
    'c2_vl_first_attempt': {
      text: {
        tr: "Videolaringoskop ile vokal kordlar kısmen görüntülendi (Grade 3a). Ancak tüp glottan geçmiyor. SpO2 %80'e geriledi. Havayolunda ödem oluşmaya başladı. Hamleniz?",
        en: "Videolaryngoscopy showed a partial view of vocal cords (Grade 3a). However, the tube won't pass. SpO2 dropped to 80%. Airway edema is developing. What is your action?"
      },
      choices: [{
        text: {
          tr: "Havayolu bujisi (bougie) kullanarak tüpü buji üzerinden kaydırıp entübe ederim.",
          en: "Use an airway bougie to guide the tube into the trachea."
        },
        nextNode: 'c2_vl_with_bougie_success',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38,
        requiredTool: 'bougie'
      }, {
        text: {
          tr: "Laryngoskopu geri çekip LMA Supreme yerleştirerek acil ventilasyon denerim.",
          en: "Withdraw laryngoscope and insert LMA Supreme to establish rescue ventilation."
        },
        nextNode: 'c2_lma_fail_node',
        score: 60,
        trauma: 5,
        spO2: 72,
        hr: 120,
        bp: '150/95',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }]
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
      choices: [{
        text: {
          tr: "Hızlı Seri İndüksiyon (RSI) uygularım, indüksiyon öncesi 3 dakika %100 preoksijenasyon yapar ve krikoid bası (Sellick manevrası) uygulatırım.",
          en: "Perform Rapid Sequence Induction (RSI) with 3 mins preoxygenation and cricoid pressure."
        },
        nextNode: 'c3_rsi_induced',
        score: 100,
        trauma: 0,
        spO2: 99,
        hr: 80,
        bp: '130/80',
        etco2: 38
      }, {
        text: {
          tr: "Aspirasyon riskini önlemek için lokal anesteziyle uyanık fiberoptik entübasyon denerim.",
          en: "To prevent aspiration risk, attempt awake fiberoptic intubation."
        },
        nextNode: 'c3_fetal_bradycardia',
        score: -30,
        trauma: 0,
        spO2: 95,
        hr: 110,
        bp: '150/95',
        etco2: 35
      }]
    },
    'c3_fetal_bradycardia': {
      text: {
        tr: "Uyanık fiberoptik entübasyon girişimi tok karınlı gebede ajitasyona ve sürenin uzamasına yol açtı. Fetal bradikardi daha da kötüleşti (kalp atımı 60 bpm). Acil C-section gerekiyor! Ne yapacaksınız?",
        en: "Awake fiberoptic intubation attempt caused maternal agitation and delayed the procedure. Fetal bradycardia worsened (fetal HR 60 bpm). Emergency C-section is critical! What will you do?"
      },
      choices: [{
        text: {
          tr: "RSI indüksiyonuna geçerim, hızlıca Propofol + Süksinilkolin veririm.",
          en: "Switch to RSI induction with Propofol + Succinylcholine."
        },
        nextNode: 'c3_rsi_induced',
        score: 70,
        trauma: 0,
        spO2: 98,
        hr: 95,
        bp: '140/90',
        etco2: 0
      }]
    },
    'c3_rsi_induced': {
      text: {
        tr: "İndüksiyon sonrası Macintosh 3 bıçakla yapılan ilk entübasyon denemesinde tüpün özofagusa girdiği görüldü (ETCO2 düz trase, göğüs hareketleri yok). SpO2 %91'e iniyor. Ne yapacaksınız?",
        en: "Following induction, the first intubation attempt with Mac 3 blade resulted in esophageal intubation (flat ETCO2, no chest rise). SpO2 is dropping to 91%. What is your move?"
      },
      choices: [{
        text: {
          tr: "Tüpü çekerim, krikoid basıyı hafifçe gevşetip maskeyle nazikçe ventilasyon sağlayarak oksijenlendiririm.",
          en: "Remove the tube, slightly ease cricoid pressure, and gently mask ventilate to preoxygenate."
        },
        nextNode: 'c3_mask_ventilated',
        score: 100,
        trauma: 5,
        spO2: 97,
        hr: 90,
        bp: '120/80',
        etco2: 34
      }, {
        text: {
          tr: "Tüpü yerinde bırakıp doğrudan videolaringoskop (VL) ile ikinci entübasyon denemesine geçerim.",
          en: "Leave tube in place, switch to videolaryngoscope (VL), and attempt immediate re-intubation."
        },
        nextNode: 'c3_obstetric_hypoxia',
        score: -10,
        trauma: 15,
        spO2: 81,
        hr: 115,
        bp: '145/90',
        etco2: 0,
        requiredTool: 'video_laryngo'
      }]
    },
    'c3_mask_ventilated': {
      text: {
        tr: "Özofageal tüp çekildi. Nazik maske ventilasyonu ile SpO2 tekrar %97'ye yükseltildi. İkinci entübasyon denemesinde başarısızlığı önlemek için hangi cihazı ve tekniği kullanacaksınız?",
        en: "Esophageal tube removed. Gentle mask ventilation restored SpO2 to 97%. To prevent failure in the second attempt, which device and technique will you use?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop (VL) ve Buji (Bougie) kullanarak glottik açıklığı görerek entübasyon sağlarım.",
          en: "Use Videolaryngoscope (VL) and Bougie to intubate under direct visualization."
        },
        nextNode: 'c3_obstetric_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 80,
        bp: '120/80',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "LMA Supreme takarak havayolunu bu şekilde sürdürürüm.",
          en: "Place LMA Supreme and maintain ventilation through it."
        },
        nextNode: 'c3_lma_maintenance',
        score: 70,
        trauma: 5,
        spO2: 96,
        hr: 85,
        bp: '118/75',
        etco2: 36,
        requiredTool: 'lma_supreme'
      }]
    },
    'c3_lma_maintenance': {
      text: {
        tr: "LMA Supreme takıldı, ventilasyon doğrulandı. Ancak hasta tok karınlı gebe olduğundan LMA ile devam etmek yüksek aspirasyon riski taşır. Ayrıca cerrahi sezaryendir. Ne yapacaksınız?",
        en: "LMA Supreme placed, ventilation confirmed. However, maintaining ventilation via LMA in a full-stomach pregnant patient carries a high risk of aspiration. What is your choice?"
      },
      choices: [{
        text: {
          tr: "LMA içinden fiberoptik skop veya buji kılavuzluğunda entübasyon denerim.",
          en: "Attempt intubation through the LMA guided by a fiberoptic scope or bougie."
        },
        nextNode: 'c3_obstetric_victory',
        score: 95,
        trauma: 5,
        spO2: 99,
        hr: 75,
        bp: '120/80',
        etco2: 38
      }, {
        text: {
          tr: "Operasyonu bu şekilde hızla tamamlatıp cerrahi bitiminde LMA'yı çekerim.",
          en: "Proceed with surgery under LMA and remove it immediately after delivery."
        },
        nextNode: 'c3_aspiration_arrest',
        score: -50,
        trauma: 0,
        spO2: 50,
        hr: 130,
        bp: '90/50',
        etco2: 0
      }]
    },
    'c3_obstetric_hypoxia': {
      text: {
        tr: "Ventilasyon olmadan yapılan ikinci entübasyon denemesi de başarısız oldu (Grade 4 görünüm). SpO2 %65'e düştü, hasta bradikardik (nabız 50 bpm). Ağır hipoksi ve kardiyak arrest riski mevcut! Acil eFONA kiti hazır mı?",
        en: "The second attempt without mask ventilation failed (Grade 4 view). SpO2 dropped to 65%, patient is bradycardic (HR 50 bpm). Risk of cardiac arrest is high! Is your eFONA kit ready?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden Krikotiroidotomi Kiti (eFONA) uygularım.",
          en: "Perform emergency front-of-neck access (eFONA) immediately."
        },
        nextNode: 'c3_crico_rescue_success',
        score: 100,
        trauma: 15,
        spO2: 95,
        hr: 90,
        bp: '110/70',
        etco2: 38,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "LMA Supreme takarak havalandırma denerim.",
          en: "Place LMA Supreme and attempt rescue ventilation."
        },
        nextNode: 'c3_hypoxia_arrest_obs',
        score: -20,
        trauma: 5,
        spO2: 40,
        hr: 45,
        bp: '70/35',
        etco2: 0,
        requiredTool: 'lma_supreme'
      }]
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
      choices: [{
        text: {
          tr: "Çocuğu sakin tutar, ebeveynden ayırmam; ameliyathaneye alıp KBB ekibi (rijit bronkoskopi) hazırken sevofluran ile spontan solunumu koruyarak inhalasyon indüksiyonu yaparım.",
          en: "Keep the child calm with the parent present; transfer to OR and perform inhalational induction with sevoflurane preserving spontaneous breathing, with ENT (rigid bronchoscopy) standing by."
        },
        nextNode: 'c4_inhalation_induced',
        score: 100,
        trauma: 0,
        spO2: 96,
        hr: 130,
        bp: '100/60',
        etco2: 35
      }, {
        text: {
          tr: "Damar yolu açıp dil basacağıyla boğazı muayene ederek tanıyı doğrularım.",
          en: "Establish IV access and examine the throat with a tongue depressor to confirm the diagnosis."
        },
        nextNode: 'c4_total_obstruction',
        score: -40,
        trauma: 20,
        spO2: 80,
        hr: 160,
        bp: '90/55',
        etco2: 0
      }, {
        text: {
          tr: "Supin yatırıp hızlı seri indüksiyon (RSI) ile kas gevşetici verip doğrudan entübe ederim.",
          en: "Lay supine, perform rapid sequence induction (RSI) with a muscle relaxant and intubate directly."
        },
        nextNode: 'c4_laryngospasm',
        score: -20,
        trauma: 10,
        spO2: 78,
        hr: 165,
        bp: '85/50',
        etco2: 0
      }]
    },
    'c4_inhalation_induced': {
      text: {
        tr: "İnhalasyon indüksiyonuyla çocuk yeterince derinleşti, spontan solunum korundu, SpO2 %96. KBB cerrahı rijit bronkoskopla yanı başınızda hazır. Laringoskopide şişmiş, 'kiraz kırmızısı' epiglot görüyorsunuz. Entübasyon için ne kullanırsınız?",
        en: "Inhalational induction achieved adequate depth with preserved spontaneous breathing, SpO2 96%. The ENT surgeon is ready with a rigid bronchoscope. On laryngoscopy you see a swollen 'cherry-red' epiglottis. What will you use to intubate?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskopla görüntüleyip normalden küçük çaplı, stileli bir tüple nazikçe entübe ederim.",
          en: "Visualize with a videolaryngoscope and intubate gently with a smaller, styletted tube."
        },
        nextNode: 'c4_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 120,
        bp: '100/60',
        etco2: 38,
        requiredTool: 'video_laryngo'
      }, {
        text: {
          tr: "Fiberoptik bronkoskop eşliğinde tüpü vokal kordlardan nazikçe geçiririm.",
          en: "Pass the tube gently through the cords under flexible fiberoptic guidance."
        },
        nextNode: 'c4_victory',
        score: 95,
        trauma: 0,
        spO2: 99,
        hr: 118,
        bp: '100/62',
        etco2: 38,
        requiredTool: 'fiberoptic'
      }, {
        text: {
          tr: "Görüş kısıtlı; bujiyle körlemesine epiglot altından geçmeyi zorlarım.",
          en: "View is limited; force a blind pass under the epiglottis with a bougie."
        },
        nextNode: 'c4_trauma_bleed',
        score: -50,
        trauma: 45,
        spO2: 75,
        hr: 170,
        bp: '95/55',
        etco2: 0,
        requiredTool: 'bougie'
      }]
    },
    'c4_total_obstruction': {
      text: {
        tr: "Dil basacağıyla muayene çocuğu ajite etti ve larinks tam tıkandı! Stridor sustu, göğüs hareketi yok, SpO2 %80'e düştü. Bu acil bir krizdir. Ne yaparsınız?",
        en: "Examination with the tongue depressor agitated the child and the larynx obstructed completely! Stridor ceased, no chest movement, SpO2 dropped to 80%. This is an emergency. What do you do?"
      },
      choices: [{
        text: {
          tr: "%100 oksijen verip yanımdaki KBB cerrahından acil rijit bronkoskopi/trakeostomi ile havayolunu açmasını isterim.",
          en: "Give 100% oxygen and have the ENT surgeon urgently secure the airway with rigid bronchoscopy/tracheostomy."
        },
        nextNode: 'c4_surgical_rescue',
        score: 70,
        trauma: 10,
        spO2: 94,
        hr: 120,
        bp: '100/65',
        etco2: 36
      }, {
        text: {
          tr: "Tüp ve stile alıp körlemesine entübasyon denerim.",
          en: "Grab a tube and stylet and attempt blind intubation."
        },
        nextNode: 'c4_arrest',
        score: -60,
        trauma: 50,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c4_laryngospasm': {
      text: {
        tr: "Kas gevşetici sonrası şiş epiglot nedeniyle ne maske ventilasyonu ne de entübasyon mümkün (pediatrik CVCI). SpO2 hızla %70'lere iniyor. Saniyeler kritik!",
        en: "After paralysis, the swollen epiglottis makes both mask ventilation and intubation impossible (pediatric CVCI). SpO2 is falling rapidly into the 70s. Seconds matter!"
      },
      choices: [{
        text: {
          tr: "Yanımdaki KBB cerrahından derhal acil trakeostomi/rijit bronkoskopi ister, %100 O2 veririm.",
          en: "Have the ENT surgeon perform immediate tracheostomy/rigid bronchoscopy and give 100% O2."
        },
        nextNode: 'c4_surgical_rescue',
        score: 60,
        trauma: 15,
        spO2: 93,
        hr: 125,
        bp: '95/60',
        etco2: 35
      }, {
        text: {
          tr: "Sugammadex verip kas gevşeticiyi geri çevirerek uyanmasını beklerim.",
          en: "Give Sugammadex to reverse paralysis and wait for spontaneous recovery."
        },
        nextNode: 'c4_arrest',
        score: -40,
        trauma: 0,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0,
        requiredTool: 'sugammadex'
      }]
    },
    'c4_trauma_bleed': {
      text: {
        tr: "Buji zorlaması şiş epiglotta kanama ve tam obstrüksiyona yol açtı. SpO2 %75, çocuk bradikardiye giriyor. Tek kurtuluş cerrahi havayolu. Hamleniz?",
        en: "Forcing the bougie caused bleeding and complete obstruction of the swollen epiglottis. SpO2 75%, the child is becoming bradycardic. The only rescue is a surgical airway. Your move?"
      },
      choices: [{
        text: {
          tr: "KBB cerrahından acil trakeostomi/rijit bronkoskopi ile havayolunu açmasını isterim.",
          en: "Have the ENT surgeon secure the airway by emergency tracheostomy/rigid bronchoscopy."
        },
        nextNode: 'c4_surgical_rescue',
        score: 60,
        trauma: 10,
        spO2: 94,
        hr: 120,
        bp: '100/65',
        etco2: 36
      }, {
        text: {
          tr: "Görüşü düzeltmek için tekrar tekrar laringoskopi denerim.",
          en: "Repeatedly attempt laryngoscopy to improve the view."
        },
        nextNode: 'c4_arrest',
        score: -50,
        trauma: 30,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c4_victory': {
      text: {
        tr: "Mükemmel! Spontan solunum korunarak, deneyimli ekiple ve KBB güvencesinde havayolu travmasız emniyete alındı. SpO2 %99, ETCO2 trasesi normal. Çocuk güvende, yoğun bakıma alındı. Tebrikler!",
        en: "Excellent! With spontaneous breathing preserved, an experienced team and ENT backup, the airway was secured atraumatically. SpO2 99%, ETCO2 normal. The child is safe in the ICU. Congratulations!"
      },
      choices: [],
      isVictory: true
    },
    'c4_surgical_rescue': {
      text: {
        tr: "KBB cerrahı zamanında devreye girdi; cerrahi havayolu (trakeostomi) ile oksijenasyon sağlandı. SpO2 tekrar %94'e yükseldi ve kardiyak arrest önlendi. Kritik kriz başarıyla yönetildi!",
        en: "The ENT surgeon intervened in time; a surgical airway (tracheostomy) restored oxygenation. SpO2 recovered to 94% and cardiac arrest was averted. Critical crisis managed successfully!"
      },
      choices: [],
      isVictory: true
    },
    'c4_arrest': {
      text: {
        tr: "Kritik Hata! Şiş ve tıkalı bir pediatrik havayolunda zaman kaybedildi; çocuk hipoksik kardiyak arreste girdi. Epiglottitte havayolu spontan solunum korunarak ve cerrahi destek hazır iken yönetilmeliydi.",
        en: "Critical Failure! Time was lost in a swollen, obstructed pediatric airway; the child suffered hypoxic cardiac arrest. In epiglottitis the airway must be managed with spontaneous breathing preserved and surgical backup ready."
      },
      choices: [],
      isGameOver: true
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
      choices: [{
        text: {
          tr: "Ödem ilerlemeden ERKEN entübasyon kararı alırım; deneyimli ekip, videolaringoskop ve büyük çaplı tüp hazırlar, eFONA setini açık tutarım.",
          en: "Decide on EARLY intubation before edema worsens; prepare an experienced team, videolaryngoscope and a large-bore tube, and keep the eFONA kit open."
        },
        nextNode: 'c5_early_secure',
        score: 100,
        trauma: 0,
        spO2: 95,
        hr: 100,
        bp: '130/85',
        etco2: 35
      }, {
        text: {
          tr: "Stabil görünüyor; yoğun bakımda gözleme alıp entübasyonu erteler, gerekirse sonra müdahale ederim.",
          en: "He looks stable; observe in the ICU, defer intubation and intervene later if needed."
        },
        nextNode: 'c5_delayed_edema',
        score: -50,
        trauma: 10,
        spO2: 84,
        hr: 120,
        bp: '140/90',
        etco2: 0
      }, {
        text: {
          tr: "Yüksek doz indüksiyon + tek deneme kör entübasyon ile hızlıca hallederim.",
          en: "Manage quickly with high-dose induction and a single blind intubation attempt."
        },
        nextNode: 'c5_blind_fail',
        score: -30,
        trauma: 30,
        spO2: 80,
        hr: 125,
        bp: '135/85',
        etco2: 0
      }]
    },
    'c5_early_secure': {
      text: {
        tr: "İndüksiyon sonrası laringoskopide supraglottik ödem ve kısmi görüş (Grade 3) var; tüpün geçişi zor. Hamleniz?",
        en: "After induction, laryngoscopy shows supraglottic edema and a partial view (Grade 3); the tube won't pass easily. Your move?"
      },
      choices: [{
        text: {
          tr: "Videolaringoskop görüntüsünde bujiyi kordlardan geçirip tüpü buji üzerinden kaydırırım.",
          en: "Pass a bougie through the cords under videolaryngoscopy and rail the tube over it."
        },
        nextNode: 'c5_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 90,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'bougie'
      }, {
        text: {
          tr: "Spontan solunumu koruyarak fiberoptik bronkoskopla entübe ederim.",
          en: "Intubate with a flexible bronchoscope while preserving spontaneous breathing."
        },
        nextNode: 'c5_victory',
        score: 95,
        trauma: 0,
        spO2: 99,
        hr: 88,
        bp: '125/80',
        etco2: 38,
        requiredTool: 'fiberoptic'
      }, {
        text: {
          tr: "Defalarca doğrudan laringoskopi denerim.",
          en: "Attempt direct laryngoscopy repeatedly."
        },
        nextNode: 'c5_edema_cvci',
        score: -40,
        trauma: 45,
        spO2: 72,
        hr: 120,
        bp: '150/95',
        etco2: 0
      }]
    },
    'c5_delayed_edema': {
      text: {
        tr: "Birkaç saat içinde havayolu ödemi ilerledi: tam stridor, ağız tabanı ve dilde şişlik. Entübasyon artık çok zor ve CVCI'ya yaklaşıyorsunuz. SpO2 %84. Ne yaparsınız?",
        en: "Within hours the airway edema progressed: full stridor, swelling of the floor of the mouth and tongue. Intubation is now very difficult and you are approaching CVCI. SpO2 84%. What do you do?"
      },
      choices: [{
        text: {
          tr: "Geç kalındı; cerrahi havayolu (eFONA krikotiroidotomi) setini hazırlar ve uygularım.",
          en: "It's late; prepare and perform a surgical airway (eFONA cricothyroidotomy)."
        },
        nextNode: 'c5_crico_rescue',
        score: 60,
        trauma: 15,
        spO2: 94,
        hr: 95,
        bp: '120/75',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Hâlâ ısrarla oral entübasyon denemeye devam ederim.",
          en: "Persist with oral intubation attempts."
        },
        nextNode: 'c5_arrest',
        score: -50,
        trauma: 35,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c5_blind_fail': {
      text: {
        tr: "Kör entübasyon ödemli, frajil dokuda başarısız oldu ve kanamaya yol açtı. SpO2 %78, maske ventilasyonu da zorlaştı. Hamleniz?",
        en: "Blind intubation failed in the edematous, friable tissue and caused bleeding. SpO2 78%, mask ventilation is now difficult too. Your move?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden cerrahi havayolu (eFONA krikotiroidotomi) uygularım.",
          en: "Perform a surgical airway (eFONA cricothyroidotomy) without delay."
        },
        nextNode: 'c5_crico_rescue',
        score: 80,
        trauma: 10,
        spO2: 95,
        hr: 95,
        bp: '120/75',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Aynı yöntemle tekrar kör entübasyon denerim.",
          en: "Attempt blind intubation again with the same method."
        },
        nextNode: 'c5_arrest',
        score: -60,
        trauma: 50,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c5_edema_cvci': {
      text: {
        tr: "Tekrarlanan denemeler ödemi artırdı; artık ne entübe edebiliyor ne de havalandırabiliyorsunuz (CVCI). SpO2 %72. Tek seçenek?",
        en: "Repeated attempts worsened the edema; you can neither intubate nor ventilate (CVCI). SpO2 72%. The only option?"
      },
      choices: [{
        text: {
          tr: "Acil cerrahi havayolu (eFONA krikotiroidotomi) uygularım.",
          en: "Perform emergency front-of-neck access (eFONA cricothyroidotomy)."
        },
        nextNode: 'c5_crico_rescue',
        score: 80,
        trauma: 15,
        spO2: 94,
        hr: 95,
        bp: '115/70',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Bir kez daha videolaringoskopiyle denerim.",
          en: "Try once more with videolaryngoscopy."
        },
        nextNode: 'c5_arrest',
        score: -40,
        trauma: 25,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c5_victory': {
      text: {
        tr: "Doğru karar! Ödem ilerlemeden havayolu erkenden ve travmasız emniyete alındı. SpO2 %99, ventilasyon doğrulandı. Erken entübasyon bu hastada hayat kurtardı. Tebrikler!",
        en: "Right call! The airway was secured early and atraumatically before edema progressed. SpO2 99%, ventilation confirmed. Early intubation saved this patient. Congratulations!"
      },
      choices: [],
      isVictory: true
    },
    'c5_crico_rescue': {
      text: {
        tr: "Krikotiroidotomi ile boyundan acil havayolu sağlandı; trakeaya kaflı tüp yerleştirildi ve ventilasyon doğrulandı. SpO2 %94'e yükseldi. Geç kalınsa da hasta kurtarıldı!",
        en: "Cricothyroidotomy established emergency front-of-neck access; a cuffed tube was placed and ventilation confirmed. SpO2 recovered to 94%. Late, but the patient was saved!"
      },
      choices: [],
      isVictory: true
    },
    'c5_arrest': {
      text: {
        tr: "Kritik Hata! İnhalasyon yaralanmasında havayolu ödemi öngörülmeli ve erken emniyete alınmalıydı. Gecikme ve tekrarlayan travmatik denemeler hipoksik arrestle sonuçlandı.",
        en: "Critical Failure! In inhalation injury, airway edema must be anticipated and the airway secured early. Delay and repeated traumatic attempts ended in hypoxic arrest."
      },
      choices: [],
      isGameOver: true
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
      choices: [{
        text: {
          tr: "Spontan solunumu koruyarak, oturur pozisyonda topikal anesteziyle uyanık fiberoptik (nazal) entübasyon yaparım; cerrah trakeostomi için steril hazır beklesin.",
          en: "Awake fiberoptic (nasal) intubation in the sitting position under topical anesthesia, preserving spontaneous breathing, with a surgeon scrubbed and ready for tracheostomy."
        },
        nextNode: 'c6_afoi_progress',
        score: 100,
        trauma: 0,
        spO2: 96,
        hr: 95,
        bp: '135/85',
        etco2: 35,
        requiredTool: 'fiberoptic'
      }, {
        text: {
          tr: "Standart RSI ile uyutup doğrudan laringoskopiyle entübe ederim.",
          en: "Induce with standard RSI and intubate by direct laryngoscopy."
        },
        nextNode: 'c6_rsi_cvci',
        score: -50,
        trauma: 15,
        spO2: 78,
        hr: 130,
        bp: '150/95',
        etco2: 0
      }, {
        text: {
          tr: "Körlemesine LMA yerleştirip ventile etmeyi denerim.",
          en: "Insert an LMA blindly and attempt to ventilate."
        },
        nextNode: 'c6_lma_fail6',
        score: -30,
        trauma: 10,
        spO2: 80,
        hr: 120,
        bp: '145/90',
        etco2: 0,
        requiredTool: 'lma_classic'
      }]
    },
    'c6_afoi_progress': {
      text: {
        tr: "Nazal fiberoptik ilerlerken ödemli ama açık glottik açıklığı görüyorsunuz; hasta sakin ve spontan soluyor. Tüpü kordlardan geçirmeden önce ne yaparsınız?",
        en: "As the nasal fiberoptic advances you see an edematous but patent glottic opening; the patient is calm and breathing spontaneously. Before railroading the tube, what do you do?"
      },
      choices: [{
        text: {
          tr: "Skopu kordlardan geçirip tüpü nazikçe kaydırır, yerleşimi kapnografi ile doğrularım.",
          en: "Pass the scope through the cords, gently rail the tube and confirm placement with capnography."
        },
        nextNode: 'c6_victory',
        score: 100,
        trauma: 5,
        spO2: 99,
        hr: 88,
        bp: '125/80',
        etco2: 38
      }, {
        text: {
          tr: "Daha rahat çalışmak için derin sedasyon/kas gevşetici eklerim.",
          en: "Add deep sedation/muscle relaxant to work more comfortably."
        },
        nextNode: 'c6_apnea_obstruct',
        score: -40,
        trauma: 5,
        spO2: 70,
        hr: 140,
        bp: '150/95',
        etco2: 0
      }]
    },
    'c6_rsi_cvci': {
      text: {
        tr: "Kas gevşetici sonrası trismus nedeniyle ağız açılmadı; dil ve ağız tabanı ödemi maske ventilasyonunu da imkansız kıldı (CVCI). SpO2 %78 ve düşüyor. Hamleniz?",
        en: "After paralysis the trismus prevented mouth opening; tongue and floor-of-mouth edema made mask ventilation impossible too (CVCI). SpO2 78% and falling. Your move?"
      },
      choices: [{
        text: {
          tr: "Zaman kaybetmeden cerrahi havayolu (eFONA krikotiroidotomi / acil trakeostomi) uygularım.",
          en: "Perform an emergency surgical airway (eFONA cricothyroidotomy / urgent tracheostomy) without delay."
        },
        nextNode: 'c6_crico_rescue',
        score: 80,
        trauma: 15,
        spO2: 94,
        hr: 100,
        bp: '120/75',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Sugammadex verip gevşeticiyi geri çevirerek uyanmasını beklerim.",
          en: "Give Sugammadex to reverse paralysis and wait for the patient to wake."
        },
        nextNode: 'c6_arrest',
        score: -50,
        trauma: 0,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0,
        requiredTool: 'sugammadex'
      }]
    },
    'c6_lma_fail6': {
      text: {
        tr: "Ödemli ağız tabanı ve yükselmiş dil nedeniyle LMA doğru yerleşmedi; ventilasyon yok, SpO2 %72 (CVCI). Ne yaparsınız?",
        en: "Due to the edematous floor of mouth and elevated tongue the LMA seated poorly; no ventilation, SpO2 72% (CVCI). What do you do?"
      },
      choices: [{
        text: {
          tr: "Acil cerrahi havayolu (eFONA krikotiroidotomi / trakeostomi) uygularım.",
          en: "Perform an emergency surgical airway (eFONA cricothyroidotomy / tracheostomy)."
        },
        nextNode: 'c6_crico_rescue',
        score: 80,
        trauma: 15,
        spO2: 94,
        hr: 100,
        bp: '115/70',
        etco2: 36,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Stileli tüple körlemesine entübasyon denerim.",
          en: "Attempt blind intubation with a styletted tube."
        },
        nextNode: 'c6_arrest',
        score: -60,
        trauma: 45,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c6_apnea_obstruct': {
      text: {
        tr: "Sedasyon/gevşetici sonrası spontan solunum durdu ve ödemli havayolu tamamen tıkandı; ne ventilasyon ne de fiberoptik geçişi mümkün. SpO2 %70. Tek kurtuluş?",
        en: "After sedation/relaxant, spontaneous breathing stopped and the edematous airway obstructed completely; neither ventilation nor fiberoptic passage is possible. SpO2 70%. The only rescue?"
      },
      choices: [{
        text: {
          tr: "Steril bekleyen cerrahdan acil trakeostomi / krikotiroidotomi ister, %100 O2 veririm.",
          en: "Have the scrubbed surgeon perform an emergency tracheostomy / cricothyroidotomy and give 100% O2."
        },
        nextNode: 'c6_crico_rescue',
        score: 60,
        trauma: 15,
        spO2: 93,
        hr: 105,
        bp: '120/75',
        etco2: 35,
        requiredTool: 'crico_kit'
      }, {
        text: {
          tr: "Fiberoptiği tekrar tekrar geçirmeyi zorlarım.",
          en: "Force repeated fiberoptic passage attempts."
        },
        nextNode: 'c6_arrest',
        score: -40,
        trauma: 20,
        spO2: 0,
        hr: 0,
        bp: '0/0',
        etco2: 0
      }]
    },
    'c6_victory': {
      text: {
        tr: "Mükemmel! Spontan solunum korunarak uyanık fiberoptik entübasyon başarıyla tamamlandı, yerleşim kapnografiyle doğrulandı ve cerrahi yedek hazırdı. SpO2 %99. Ludwig anjini ders kitabına uygun yönetildi. Tebrikler!",
        en: "Excellent! Awake fiberoptic intubation was completed with spontaneous breathing preserved, placement confirmed by capnography, and a surgical backup ready. SpO2 99%. Textbook management of Ludwig's angina. Congratulations!"
      },
      choices: [],
      isVictory: true
    },
    'c6_crico_rescue': {
      text: {
        tr: "Steril bekleyen cerrah devreye girdi; boyundan cerrahi havayolu açıldı ve ventilasyon doğrulandı. SpO2 %94'e yükseldi, kardiyak arrest önlendi. Kriz kurtarma ile yönetildi!",
        en: "The scrubbed surgeon intervened; a surgical airway was established and ventilation confirmed. SpO2 recovered to 94%, cardiac arrest averted. Crisis managed by rescue!"
      },
      choices: [],
      isVictory: true
    },
    'c6_arrest': {
      text: {
        tr: "Kritik Hata! Trismuslu, ödemli Ludwig anjininde spontan solunumu kaybettiren veya travmatik girişimler felaketle sonuçlanır. Hasta hipoksik arreste girdi; doğru yol spontan solunumlu uyanık fiberoptik ve hazır cerrahi yedekti.",
        en: "Critical Failure! In Ludwig's angina with trismus and edema, losing spontaneous breathing or traumatic attempts is catastrophic. The patient arrested; the correct path was awake fiberoptic with spontaneous breathing and a ready surgical backup."
      },
      choices: [],
      isGameOver: true
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
    const beepIntervalMs = 60 / heartRate * 1000;
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
      const phase = ekgX * 1.5 % period;
      if (phase < period * 0.1) {
        // P-Wave
        y = 35 - 3 * Math.sin(phase / (period * 0.1) * Math.PI);
      } else if (phase >= period * 0.15 && phase < period * 0.2) {
        // Q-Wave
        y = 35 + 5 * ((phase - period * 0.15) / (period * 0.05));
      } else if (phase >= period * 0.2 && phase < period * 0.25) {
        // R-Spike
        const p = (phase - period * 0.2) / (period * 0.05);
        y = 40 - 35 * p;
      } else if (phase >= period * 0.25 && phase < period * 0.3) {
        // S-Drop
        const p = (phase - period * 0.25) / (period * 0.05);
        y = 5 + 40 * p;
      } else if (phase >= period * 0.3 && phase < period * 0.4) {
        // T-Wave
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
      const phase = etco2X * 0.8 % period;
      if (phase < period * 0.4) {
        // Expiration plateau
        y = 50 - etCO2 * 0.9 * Math.sin(phase / (period * 0.4) * Math.PI * 0.5 + Math.PI * 0.1);
      } else if (phase >= period * 0.4 && phase < period * 0.5) {
        // Inspiration washin
        const p = (phase - period * 0.4) / (period * 0.1);
        y = 50 - etCO2 * 0.9 * (1 - p);
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
    item.style.background = idx === 0 ? 'rgba(252, 211, 77, 0.12)' : idx % 2 === 0 ? 'rgba(255,255,255,0.02)' : 'transparent';
    if (idx === 0) item.style.border = '1px solid rgba(252, 211, 77, 0.2)';
    const flag = entry.country === 'TR' ? '🇹🇷' : entry.country === 'US' ? '🇺🇸' : entry.country === 'GB' ? '🇬🇧' : entry.country === 'DE' ? '🇩🇪' : entry.country === 'FR' ? '🇫🇷' : entry.country === 'IT' ? '🇮🇹' : entry.country === 'ES' ? '🇪🇸' : entry.country === 'CA' ? '🇨🇦' : entry.country === 'AU' ? '🇦🇺' : '🏳️';
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
})(); } catch (e) { __ds_ns.__errors.push({ path: "game-build/js/airway_game.js", error: String((e && e.message) || e) }); }

// game-build/js/app.js
try { (() => {
/* -------------------------------------------------------------
   Anesthesia Briefs Landing Page Javascript App
   Dual-language handling (i18n), Geo-IP Routing & Interactive UI
------------------------------------------------------------- */

// Comprehensive i18n Translations Dictionary
const translations = {
  'tr': {
    // Navigation
    'nav-features': 'Özellikler',
    'nav-board-prep': 'Board Hazırlık',
    'nav-preview': 'Ekranlar',
    'nav-instagram': 'Instagram',
    'nav-download-soon': 'Çok Yakında',
    'nav-game-play': '<i class="fa-solid fa-gamepad"></i> Laringoskop Fırlat!',
    'nav-airway-play': '<i class="fa-solid fa-brain"></i> Zor Havayolu',
    // Hero
    'hero-badge': 'Anestezi Profesyonelleri İçin',
    'hero-title': 'Klinik Kararlarınızda <br><span class="gradient-text">Güvence Altındasınız</span>',
    'hero-desc': 'Acil durum algoritmalarına, gelişmiş ilaç doz hesaplayıcılarına, tıbbi literatür özetlerine ve yapay zeka destekli klinik asistanınıza tek bir uygulamadan anında erişin.',
    'coming-soon': 'Çok Yakında',
    // Features
    'features-section-title': 'Uygulama Özellikleri',
    'features-section-subtitle': 'Klinik güvenliği ve doğruluğu en üst düzeye çıkarmak için tasarlanan 4 ana sütun.',
    'feature-1-title': 'Acil Algoritmalar',
    'feature-1-desc': 'Hayatı tehdit eden anestezi krizlerine hızlı erişim. Her saniyenin önemli olduğu durumlarda adım adım acil durum kılavuzları.',
    'feature-2-title': 'Klinik Hesaplayıcılar',
    'feature-2-desc': 'Yetişkin, pediatrik ve obstetrik hastalar için hassas doz hesaplamaları ve anestezi puanlama sistemleri.',
    'feature-3-title': 'AI Klinik Asistan',
    'feature-3-desc': 'Klinik sorularınızı anında yanıtlayan, uluslararası kılavuzlarla eğitilmiş gelişmiş yapay zeka asistanı.',
    'feature-4-title': 'Son Literatür',
    'feature-4-desc': 'Anestezi alanındaki en son klinik yayınların, güncel kılavuzların ve bilimsel makalelerin özetleri.',
    // Premium Hub (Board Prep)
    'premium-title': 'Board Prep: Sınav Hazırlık Merkezi',
    'premium-subtitle': 'EDAIC (Avrupa Anesteziyoloji Kurulu) ve ABA (Amerikan Anesteziyoloji Kurulu) sınavlarına hazırlıkta en büyük asistanınız. Bilgilerinizi tazeleyin, eksiklerinizi tamamlayın.',
    'board-questions-title': 'Board Soruları & Açıklamalı Çözümler',
    'board-questions-desc': 'EDAIC Part I, ABA BASIC ve EDAIC Viva sınav standartlarına tam uyumlu, geniş kapsamlı SBA ve MTF soru bankası. Her sorunun altında ayrıntılı, referanslı klinik çözümler.',
    'spot-notes-title': 'Spot Bilgiler & Hızlı Tekrar',
    'spot-notes-desc': 'Sınavlar için özenle derlenmiş, nokta atışı kilit bilgiler. Kapsamlı farmakolojik özet tablolar, havayolu yönetim tipleri, kritik fizyoloji noktaları ve mutlaka bilinmesi gereken 1000+ spot bilgi.',
    'exam-simulator-title': 'Gerçekçi Sınav Simülatörü',
    'exam-simulator-desc': 'Açıklamaların gizlendiği, katı bir zamanlayıcının çalıştığı ve sınav stresini önceden yönetebilmeniz için tasarlanmış gerçek kurul formatında özel sınav simülatörü.',
    'analytics-title': 'Konu Odaklı Gelişim Analizi',
    'analytics-desc': 'Hatalı çözdüğünüz soruları analiz ederek hangi fizyoloji veya farmakoloji başlığında zayıf olduğunuzu tespit eden ve nokta atışı gelişim gösteren dinamik grafik kartları.',
    // Demo Tabs
    'demo-title': 'Modern ve Hızlı Arayüzü Deneyimleyin',
    'demo-desc': 'Anesthesia Briefs, en stresli klinik anlarda bile ihtiyacınız olan bilgiye en fazla iki dokunuşla ulaşmanızı sağlayacak sezgisel bir arayüze sahiptir. Sade kart tasarımı ve kategorize edilmiş menüleriyle dikkatinizi dağıtmaz.',
    'demo-step1-title': 'Kişiselleştirilmiş Karşılama',
    'demo-step1-desc': 'Güne güvenli ve hazırlıklı başlamanız için her sabah güncellenen klinik ipuçları.',
    'demo-algorithms-title': 'Acil Algoritmalar',
    'demo-algorithms-desc': 'Kritik durumlara özel geliştirilmiş interaktif ve adım adım acil durum protokolleri.',
    'demo-step2-title': 'Klinik Hesaplayıcılar',
    'demo-step2-desc': 'Saniyeler içinde hassas dozajları bulan evrensel klinik hesap makinesi arayüzü.',
    'demo-drugs-title': 'İlaç Kılavuzu & Bilgileri',
    'demo-drugs-desc': 'Anestezide kullanılan tüm ilaçların endikasyonları, infüzyon dozajları ve kritik uyarıları.',
    'demo-board-title': 'Board Sınavı Hazırlık',
    'demo-board-desc': 'Sınav tiplerine, soru istatistiklerine ve eksiklerinize göre özelleştirilmiş Board Prep paneli.',
    'demo-spot-title': 'Spot Bilgiler & Özetler',
    'demo-spot-desc': 'Konulara göre sınıflandırılmış, hızlı tekrarlar yapabileceğiniz interaktif spot bilgi kartları.',
    'demo-step3-title': 'Yapay Zeka Klinik Asistanı',
    'demo-step3-desc': 'Gelişmiş klinik aramalar yapabileceğiniz ve tıbbi rehberleri sorgulayabileceğiniz yapay zeka.',
    // Instagram
    'insta-title': 'Instagram\'da Bizi Takip Edin',
    'insta-subtitle': 'Her gün paylaşılan yeni vaka sunumları, anestezi ipuçları ve eğitici içeriklerle bilginizi tazeleyin.',
    'insta-tag-1': 'Klinik Vaka',
    'insta-tag-2': 'İlaç Etkileşimleri',
    'insta-tag-3': 'Yapay Zeka',
    'insta-caption-1': 'Rapid Sequence Induction (RSI) sırasında krikoid basınç uygulaması ve güncel kanıtlar...',
    'insta-caption-2': 'Sevofluran ve Karbondioksit absorbam ısınması riskleri. Kritik reaksiyon analizleri...',
    'insta-caption-3': 'Tıbbi yapay zeka klinik asistanımızın son literatür güncellemeleri ve entegrasyonu...',
    'insta-btn-follow': 'Takip Et',
    // Download & Footer
    'download-title': 'Güvenli Kararlar Cebinizde',
    'download-desc': 'Anestezi uygulamalarında hızı, güvenliği ve bilimselliği artırmak için geliştirilen Anesthesia Briefs\'i hemen ücretsiz indirin.',
    'footer-moto': 'Klinik kararlarınızda güvence altındasınız.',
    'footer-links-title': 'Hızlı Linkler',
    'footer-legal-title': 'Yasal',
    'footer-privacy': 'Gizlilik Politikası',
    'footer-terms': 'Kullanım Şartları'
  },
  'en': {
    // Navigation
    'nav-features': 'Features',
    'nav-board-prep': 'Board Prep',
    'nav-preview': 'Screens',
    'nav-instagram': 'Instagram',
    'nav-download-soon': 'Coming Soon',
    'nav-game-play': '<i class="fa-solid fa-gamepad"></i> Laryngoscope Throw!',
    'nav-airway-play': '<i class="fa-solid fa-brain"></i> Difficult Airway',
    // Hero
    'hero-badge': 'For Anesthesia Professionals',
    'hero-title': 'You are safe in <br><span class="gradient-text">Your Clinical Decisions</span>',
    'hero-desc': 'Instantly access emergency algorithms, advanced drug dosage calculators, medical literature summaries, and your AI-powered clinical assistant from a single application.',
    'coming-soon': 'Coming Soon',
    // Features
    'features-section-title': 'Application Features',
    'features-section-subtitle': '4 core pillars designed to maximize clinical safety and decision accuracy.',
    'feature-1-title': 'Emergency Algorithms',
    'feature-1-desc': 'Rapid access to life-threatening anesthesia crises. Step-by-step emergency guidance when every single second counts.',
    'feature-2-title': 'Clinical Calculators',
    'feature-2-desc': 'Precise dosage calculations and anesthesia scoring systems for adult, pediatric, and obstetric patients.',
    'feature-3-title': 'AI Clinical Assistant',
    'feature-3-desc': 'Advanced medically-tuned AI assistant that answers clinical queries and verifies medical guidelines in real time.',
    'feature-4-title': 'Latest Literature',
    'feature-4-desc': 'Stay ahead with clean summaries of the latest clinical publications, guidelines, and anesthesia briefs.',
    // Premium Hub (Board Prep)
    'premium-title': 'Board Prep: Exam Prep Hub',
    'premium-subtitle': 'Your major assistant preparing for EDAIC (European Diploma) and ABA (American Board) exams. Refresh your knowledge, bridge your clinical gaps.',
    'board-questions-title': 'Board Questions & Detailed Explanations',
    'board-questions-desc': 'A comprehensive SBA & MTF question database fully compliant with EDAIC Part I, ABA BASIC, and EDAIC Viva standards. Detailed, referenced clinical breakdowns.',
    'spot-notes-title': 'Spot Notes & High-Yield Review',
    'spot-notes-desc': 'High-yield points compiled meticulously for exams. Detailed pharmacological summaries, airway classifications, key physiology tips, and 1000+ must-know facts.',
    'exam-simulator-title': 'Strict Exam Simulator',
    'exam-simulator-desc': 'Beat exam anxiety early with randomized board-style simulations featuring hidden answers, strict timers, and detailed performance tracking.',
    'analytics-title': 'Tag-Based Progress Analytics',
    'analytics-desc': 'Deep mistake tracking that analyzes incorrect answers to flag exactly which tag (e.g. airway, cardiac) requires revision, backed by dynamic progress charts.',
    // Demo Tabs
    'demo-title': 'Experience the Modern & Swift Interface',
    'demo-desc': 'Anesthesia Briefs features an intuitive interface designed to let you reach the information you need in maximum two taps, even during the most stressful clinical moments. Minimal card design avoids clutter.',
    'demo-step1-title': 'Personalized Welcome',
    'demo-step1-desc': 'Daily updated clinical tips to start your day prepared and secure in your decisions.',
    'demo-algorithms-title': 'Emergency Algorithms',
    'demo-algorithms-desc': 'Interactive and step-by-step emergency protocols designed specifically for critical situations.',
    'demo-step2-title': 'Clinical Calculators',
    'demo-step2-desc': 'Universal calculator UI designed to resolve exact drug weights and scores in seconds.',
    'demo-drugs-title': 'Drug Guide & Formulary',
    'demo-drugs-desc': 'Indications, infusion dosages, and critical warnings for all anesthesia drugs.',
    'demo-board-title': 'Board Exam Prep',
    'demo-board-desc': 'Customized Board Prep portal based on exam boards, questions statistics, and your weak areas.',
    'demo-spot-title': 'Spot Notes & Bulletproof Tips',
    'demo-spot-desc': 'Interactive quick facts categorized by tags, engineered for rapid review sessions before exams.',
    'demo-step3-title': 'AI Clinical Assistant',
    'demo-step3-desc': 'Advanced search bar and guide queries powered by our medically fine-tuned AI model.',
    // Instagram
    'insta-title': 'Follow Us on Instagram',
    'insta-subtitle': 'Refresh your knowledge with daily clinical cases, quick anesthesia tips, and educational visual guides.',
    'insta-tag-1': 'Clinical Case',
    'insta-tag-2': 'Drug Interactions',
    'insta-tag-3': 'Artificial Intelligence',
    'insta-caption-1': 'Cricoid pressure application during Rapid Sequence Induction (RSI) and modern evidence...',
    'insta-caption-2': 'Carbon dioxide absorbent heating risks with Sevoflurane. Critical chemical reaction analysis...',
    'insta-caption-3': 'Latest updates and clinical guidelines integration of our medical AI clinical assistant...',
    'insta-btn-follow': 'Follow',
    // Download & Footer
    'download-title': 'Safe Decisions in Your Pocket',
    'download-desc': 'Download Anesthesia Briefs for free now, engineered to increase speed, safety, and scientific precision in anesthesia.',
    'footer-moto': 'You are safe in your clinical decisions.',
    'footer-links-title': 'Quick Links',
    'footer-legal-title': 'Legal',
    'footer-privacy': 'Privacy Policy',
    'footer-terms': 'Terms of Service'
  }
};

// Global App State
let currentLang = 'tr';
let activeDemoTab = 'screen-home';

// Initialize Page Function
document.addEventListener("DOMContentLoaded", function () {
  initLanguage();
  initMobileMenu();
  initPreloader();
});

// Premium 3D Preloader Screen Handler
function initPreloader() {
  const preloader = document.getElementById('preloader');

  // Animate and fade out after 1.8 seconds
  setTimeout(() => {
    preloader.classList.add('preloader-fade-out');
  }, 1800);
}

// Detect User Language on First Visit (Localstorage > Geolocation IP > Browser Default)
function initLanguage() {
  const savedLang = localStorage.getItem('anesthesia_pref_lang');
  if (savedLang) {
    changeLanguage(savedLang);
  } else {
    // Run light, fast IP geolocation lookup
    fetch('https://ipapi.co/json/').then(response => response.json()).then(data => {
      const country = data.country_code;
      if (country === 'TR') {
        changeLanguage('tr');
      } else {
        changeLanguage('en');
      }
    }).catch(() => {
      // Fallback to browser language
      const userLang = navigator.language || navigator.userLanguage;
      if (userLang.startsWith('tr')) {
        changeLanguage('tr');
      } else {
        changeLanguage('en');
      }
    });
  }
}

// Function to Change Language smoothly with CSS Transition
function changeLanguage(lang) {
  if (!translations[lang]) return;
  currentLang = lang;
  localStorage.setItem('anesthesia_pref_lang', lang);

  // Add fade class to trigger smooth transition
  document.body.style.opacity = '0.98';
  setTimeout(() => {
    // Update all elements containing data-i18n attribute
    document.querySelectorAll('[data-i18n]').forEach(element => {
      const key = element.getAttribute('data-i18n');
      if (translations[lang][key]) {
        element.innerHTML = translations[lang][key];
      }
    });

    // Update document language attribute
    document.documentElement.lang = lang;

    // Toggle Active state on Language Selection buttons
    document.querySelectorAll('.lang-btn').forEach(btn => btn.classList.remove('active'));
    document.getElementById(`btn-${lang}`).classList.add('active');

    // Corrected Swapping of Hero screenshots (TR version shows TR screen, EN shows EN screen)
    const mainPhoneScreen = document.getElementById('screen-main');
    const secondaryPhoneScreen = document.getElementById('screen-secondary');
    const demoImg = document.getElementById('interactive-demo-img');
    if (lang === 'tr') {
      mainPhoneScreen.src = 'assets/screenshots/welcome_tr.jpg';
      secondaryPhoneScreen.src = 'assets/screenshots/welcome_en.jpg';
    } else {
      mainPhoneScreen.src = 'assets/screenshots/welcome_en.jpg';
      secondaryPhoneScreen.src = 'assets/screenshots/welcome_tr.jpg';
    }

    // Trigger showcase update to sync with language
    updateDemoScreenshot(activeDemoTab);
    document.body.style.opacity = '1';
  }, 150);
}

// Mobile Responsive Navigation Hamburger Drawer Menu
function initMobileMenu() {
  const mobileMenuBtn = document.getElementById('mobileMenuBtn');
  const navMenu = document.getElementById('navMenu');
  mobileMenuBtn.addEventListener('click', () => {
    navMenu.classList.toggle('mobile-active');
    const isOpen = navMenu.classList.contains('mobile-active');
    mobileMenuBtn.innerHTML = isOpen ? '<i class="fa-solid fa-xmark"></i>' : '<i class="fa-solid fa-bars"></i>';
  });

  // Close mobile menu on clicking any navigation link
  document.querySelectorAll('.nav-menu a').forEach(link => {
    link.addEventListener('click', () => {
      navMenu.classList.remove('mobile-active');
      mobileMenuBtn.innerHTML = '<i class="fa-solid fa-bars"></i>';
    });
  });
}

// Interactive Feature Demo Screenshot Switcher
function showDemoScreen(screenKey) {
  activeDemoTab = screenKey;

  // Update step list active status
  const steps = document.querySelectorAll('.step-item');
  steps.forEach(step => step.classList.remove('active'));

  // Add active class to corresponding clicked list item
  event.currentTarget.classList.add('active');
  updateDemoScreenshot(screenKey);
}

// Separate helper to update screenshots smoothly
function updateDemoScreenshot(screenKey) {
  const imgEl = document.getElementById('interactive-demo-img');
  if (!imgEl) return;
  imgEl.style.opacity = '0';
  setTimeout(() => {
    if (screenKey === 'screen-home') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/welcome_tr.jpg' : 'assets/screenshots/welcome_en.jpg';
    } else if (screenKey === 'screen-algorithms') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/algorithms_tr.jpg' : 'assets/screenshots/algorithms_en.jpg';
    } else if (screenKey === 'screen-calc') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/calc_tr.jpg' : 'assets/screenshots/calc_en.jpg';
    } else if (screenKey === 'screen-drugs') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/drugs_tr.jpg' : 'assets/screenshots/drugs_en.jpg';
    } else if (screenKey === 'screen-board') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/board_tr.jpg' : 'assets/screenshots/board_en.jpg';
    } else if (screenKey === 'screen-spot') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/spot_tr.jpg' : 'assets/screenshots/spot_en.jpg';
    } else if (screenKey === 'screen-ai') {
      imgEl.src = currentLang === 'tr' ? 'assets/screenshots/ai_tr.jpg' : 'assets/screenshots/ai_en.jpg';
    }
    imgEl.style.opacity = '1';
  }, 150);
}
})(); } catch (e) { __ds_ns.__errors.push({ path: "game-build/js/app.js", error: String((e && e.message) || e) }); }

// game-build/js/game.js
try { (() => {
/* -------------------------------------------------------------
   Anesthesia Briefs Laryngoscope Launcher Hybrid Game Engine
   Compatible with WebGL 3D (Three.js) and 2D Canvas fallback
   ------------------------------------------------------------- */

// Lightweight Vector Utility Classes for WebGL-independent calculations
class GameVector3 {
  constructor(x = 0, y = 0, z = 0) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  copy(v) {
    this.x = v.x;
    this.y = v.y;
    this.z = v.z;
    return this;
  }
  set(x, y, z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }
  add(v) {
    this.x += v.x;
    this.y += v.y;
    this.z += v.z;
    return this;
  }
  addScaledVector(v, s) {
    this.x += v.x * s;
    this.y += v.y * s;
    this.z += v.z * s;
    return this;
  }
  distanceTo(v) {
    return Math.hypot(this.x - v.x, this.y - v.y, this.z - v.z);
  }
  clone() {
    return new GameVector3(this.x, this.y, this.z);
  }
}
class GameVector2 {
  constructor(x = 0, y = 0) {
    this.x = x;
    this.y = y;
  }
  add(v) {
    this.x += v.x;
    this.y += v.y;
    return this;
  }
}

// Global Game Variables
let gameMode = '2d'; // '2d' or '3d'
let is3DAvailable = false;
let canvas2D = null,
  ctx2d = null;
let bgCanvas = null; // Offscreen canvas for static background caching
let scene = null,
  camera = null,
  renderer = null;
let gameState = 'menu'; // 'menu', 'playing', 'gameover', 'levelup'
let language = 'tr';
let audioEnabled = true;
let audioCtx = null;

// Game State Values
let score = 0;
let level = 1;
let lives = 5;
let windX = 0; // Wind drift along X-axis
let windZ = 0; // Wind drift along Z-axis
let highscore = 0;
let combo = 0;

// Leaderboard Management
let playerNick = "Anestezist";
let playerCountry = "TR";
let leaderboard = [];

// New key to force a clean local storage reset for the user (cache-bust previous mocks)
const LEADERBOARD_STORAGE_KEY = 'anesthesia_briefs_leaderboard_v1';

// Cookie Helpers for Redundancy Score Saving (Wipes out iOS/Instagram WebView daily purges)
function saveLeaderboardToCookie(list) {
  try {
    const data = JSON.stringify(list);
    const b64 = btoa(unescape(encodeURIComponent(data)));
    const expiry = new Date();
    expiry.setFullYear(expiry.getFullYear() + 5);
    document.cookie = `ab_leaderboard=${b64}; expires=${expiry.toUTCString()}; path=/; SameSite=Strict`;
  } catch (e) {
    console.error("Cookie save failed", e);
  }
}
function loadLeaderboardFromCookie() {
  try {
    const name = "ab_leaderboard=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        const b64 = c.substring(name.length, c.length);
        const data = decodeURIComponent(escape(atob(b64)));
        return JSON.parse(data);
      }
    }
  } catch (e) {
    console.error("Cookie load failed", e);
  }
  return null;
}
function saveValueToCookie(key, value) {
  try {
    const expiry = new Date();
    expiry.setFullYear(expiry.getFullYear() + 5);
    document.cookie = `${key}=${encodeURIComponent(value)}; expires=${expiry.toUTCString()}; path=/; SameSite=Strict`;
  } catch (e) {
    console.error("Cookie value save failed", e);
  }
}
function getValueFromCookie(key) {
  try {
    const name = key + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return decodeURIComponent(c.substring(name.length, c.length));
      }
    }
  } catch (e) {
    console.error("Cookie value load failed", e);
  }
  return null;
}
function initLeaderboard() {
  const stored = localStorage.getItem(LEADERBOARD_STORAGE_KEY);
  const cookieStored = loadLeaderboardFromCookie();
  if (stored) {
    try {
      leaderboard = JSON.parse(stored);
    } catch (e) {
      leaderboard = [];
    }
  }

  // Sync logic: if localStorage is empty but cookie has scores, restore!
  if ((!leaderboard || leaderboard.length === 0) && cookieStored && cookieStored.length > 0) {
    leaderboard = cookieStored;
    localStorage.setItem(LEADERBOARD_STORAGE_KEY, JSON.stringify(leaderboard));
  }
  // If cookie is empty but localStorage has scores, sync to cookie!
  else if (leaderboard && leaderboard.length > 0 && (!cookieStored || cookieStored.length === 0)) {
    saveLeaderboardToCookie(leaderboard);
  }
  // If both have scores, merge them and keep the unique top 10
  else if (leaderboard && leaderboard.length > 0 && cookieStored && cookieStored.length > 0) {
    const merged = [...leaderboard, ...cookieStored];
    const unique = {};
    merged.forEach(item => {
      const key = `${item.name}_${item.country}_${item.score}`;
      unique[key] = item;
    });
    leaderboard = Object.values(unique);
    leaderboard.sort((a, b) => b.score - a.score);
    leaderboard = leaderboard.slice(0, 10);
    localStorage.setItem(LEADERBOARD_STORAGE_KEY, JSON.stringify(leaderboard));
    saveLeaderboardToCookie(leaderboard);
  }
}
function saveLeaderboard() {
  leaderboard.sort((a, b) => b.score - a.score);
  leaderboard = leaderboard.slice(0, 10);
  localStorage.setItem(LEADERBOARD_STORAGE_KEY, JSON.stringify(leaderboard));
  saveLeaderboardToCookie(leaderboard);
}
function addScoreToLeaderboard(name, country, newScore) {
  if (!name || name.trim() === "") {
    name = language === 'tr' ? 'Anestezist' : 'Anesthesiologist';
  }
  if (!country) country = 'TR';
  leaderboard.push({
    name: name.trim().substring(0, 12),
    country: country,
    score: newScore
  });
  saveLeaderboard();
}
function displayLeaderboard() {
  const listContainer = document.getElementById('leaderboard-list');
  if (!listContainer) return;
  listContainer.innerHTML = '';
  initLeaderboard();
  if (leaderboard.length === 0) {
    const placeholder = document.createElement('div');
    placeholder.style.textAlign = 'center';
    placeholder.style.padding = '15px';
    placeholder.style.color = 'rgba(255, 255, 255, 0.4)';
    placeholder.style.fontSize = '12px';
    placeholder.style.fontStyle = 'italic';
    placeholder.innerText = language === 'tr' ? 'Henüz kaydedilmiş skor bulunmuyor. İlk skoru sen yap!' : 'No scores recorded yet. Be the first to set a high score!';
    listContainer.appendChild(placeholder);
    return;
  }
  const flags = {
    TR: '🇹🇷',
    US: '🇺🇸',
    GB: '🇬🇧',
    DE: '🇩🇪',
    FR: '🇫🇷',
    IT: '🇮🇹',
    ES: '🇪🇸',
    CA: '🇨🇦',
    AU: '🇦🇺',
    OTH: '🏳️'
  };
  leaderboard.forEach((entry, index) => {
    const item = document.createElement('div');
    item.style.display = 'flex';
    item.style.justifyContent = 'space-between';
    item.style.padding = '4px 8px';
    item.style.borderRadius = '4px';
    item.style.marginBottom = '2px';
    const isCurrentPlayer = entry.name === playerNick && entry.score === score && score > 0;
    if (isCurrentPlayer) {
      item.style.background = 'rgba(252, 211, 77, 0.2)';
      item.style.color = '#FCD34D';
      item.style.fontWeight = '800';
    } else {
      item.style.background = 'rgba(255,255,255,0.02)';
    }
    let rankStr = `${index + 1}.`;
    if (index === 0) rankStr = '🥇';else if (index === 1) rankStr = '🥈';else if (index === 2) rankStr = '🥉';
    const flag = flags[entry.country] || '🏳️';
    item.innerHTML = `<span>${rankStr} ${flag} ${entry.name}</span><span>${entry.score}</span>`;
    listContainer.appendChild(item);
  });
}

// Aiming parameters
let aimPitch = 40; // Vertical angle (degrees)
let aimYaw = 0; // Horizontal angle (degrees)
let throwPower = 60; // Launch force percentage

// 3D Objects References
let anesthesiologistGroup = null,
  surgeonGroup = null,
  tableMesh = null;
let drapeMesh = null,
  ivPoleMesh = null,
  monitorMesh = null;
let laryngoscopeMesh = null;
let trajectoryLine = null;
let particles3D = [];

// 2D Effects State
let particles2D = [];
let monitorFlashTimer = 0;
let surgeonXOffset = 0;
let surgeonZ = 0.3; // matches initial anesthesiologist hand offset

// Physics Configs
const GRAVITY = 0.18;
const FLOOR_Y = 0;

// Camera Tracking Parameters (3D only)
let cameraMode = 'aim'; // 'aim', 'flight', 'hit'
let cameraTargetPos = null;
let cameraTargetLook = null;
let currentLookAt = null;

// Motion / Velocity values for projectile
const project = {
  pos: new GameVector3(),
  vel: new GameVector3(),
  isFlying: false,
  radius: 0.15,
  trailParticles: []
};

// Target Motion Config
let targetDirection = 1;
let targetSpeed = 0.03;

// Localization Dictionary
const translations = {
  tr: {
    title: "Laringoskop<span>Fırlat!</span>",
    score: "Skor",
    level: "Seviye",
    lives: "Hak",
    wind: "Laminer Akış",
    windLeft: "Sol Esinti",
    windRight: "Sağ Esinti",
    windNone: "Sakin",
    nickPrompt: "Oyuncu Adı (Nick):",
    leaderboardTitle: "Liderlik Tablosu (En İyi 10)",
    defaultNick: "Anestezist",
    startTitle: "Laringoskop Fırlatma Savaşı",
    startSubtitle: "Cerraha laringoskop fırlatıp vaka hakkındaki komik isteklerine cevap ver! Sliders ile nişan al veya ekrandan geriye çekip fırlat.",
    playBtn: "Savaşı Başlat",
    gameOverTitle: "Ameliyat Bitti!",
    gameOverSubtitle: "Cerrah dikişleri tamamladı. Skorun:",
    restartBtn: "Yeniden Dene",
    levelUpTitle: "Tebrikler!",
    levelUpSubtitle: "Cerrah daha hızlı hareket etmeye ve laminer hava akışı sertleşmeye başlıyor!",
    nextLevelBtn: "Sonraki Seviye",
    instructions: ["Laringoskobu fırlatmak için 'Fırlat!' butonuna basın.", "Dikey Açı (Pitch) ve Yatay Yön (Yaw) ayarlarını kaydırıcılarla ayarlayın.", "Kafadan vuruşlar (Headshot) fazladan 250 puan kazandırır.", "Karşı rüzgara (laminer akış) dikkat edin, havada sapmaya yol açar.", "Hastabaşı monitörüne çarparsanız yansıyarak fırlayacaktır.", "Arka arkaya isabetler puan çarpanını (Combo) tetikler!"],
    surgeonHits: ["Aaa! Macintosh 4 mü o?!", "Miyorelaksan yapıldı mı anestezi?!", "Tablayı biraz kaldırın!", "Işık çok az, göremiyorum!", "Bu hasta kımıldıyor!", "Biz ameliyatı bitiriyoruz!", "Dikişlerim koptu!", "Kim fırlattı bunu?!"],
    surgeonMisses: ["Hedefin dikişlerimden de kötü!", "Anestezi uyuyor mu arkada?", "Iskaladın, anestezist bey!", "Eter ekranı beni korur!", "Uyanamadın galiba daha?", "Bari propofol fırlatsaydın!"]
  },
  en: {
    title: "Laryngoscope<span>Launcher</span>",
    score: "Score",
    level: "Level",
    lives: "Lives",
    wind: "Laminar Flow",
    windLeft: "West Wind",
    windRight: "East Wind",
    windNone: "Calm",
    nickPrompt: "Player Name (Nick):",
    leaderboardTitle: "Leaderboard (Top 10)",
    defaultNick: "Anesthesiologist",
    startTitle: "Laryngoscope Throw Battle",
    startSubtitle: "Launch the laryngoscope at the surgeon and react to their funny surgical comments! Aim with sliders or drag on the viewport.",
    playBtn: "Start Battle",
    gameOverTitle: "Surgery Over!",
    gameOverSubtitle: "The surgeon finished the suturing. Your score:",
    restartBtn: "Try Again",
    levelUpTitle: "Well Done!",
    levelUpSubtitle: "The surgeon is moving faster and laminar flow wind is picking up!",
    nextLevelBtn: "Next Level",
    instructions: ["Press 'Throw!' button to launch the laryngoscope.", "Adjust Pitch (Vertical) and Yaw (Horizontal) angles with sliders.", "Headshots give a bonus +250 points.", "Watch out for the laminar flow wind, it deflects the blade.", "Hitting the patient monitor causes a fast ricochet.", "Successive hits trigger high score combo multipliers!"],
    surgeonHits: ["Ouch! Is that a Macintosh 4 blade?!", "Was muscle relaxant given, anesthesia?!", "Lower the table please!", "Not enough light here!", "This patient is moving!", "We are closing now!", "My sutures are ruined!", "Who threw that?!"],
    surgeonMisses: ["Your aim is worse than my suturing!", "Is anesthesia sleeping back there?", "You missed, anesthesia!", "The ether screen protects me!", "Time to wake up, doctor!", "At least throw some propofol next time!"]
  }
};

// UI Overlay Speech Bubble timer
let activeBubbleDOM = null;
let bubbleTimer = 0;

// Procedural Audio Synthesizer via Web Audio API
function initAudio() {
  if (!audioCtx) {
    audioCtx = new (window.AudioContext || window.webkitAudioContext)();
  }
}
function playSynthesizedSound(type) {
  if (!audioEnabled) return;
  initAudio();
  if (!audioCtx || audioCtx.state === 'suspended') {
    audioCtx.resume();
  }
  try {
    const dest = audioCtx.destination;
    if (type === 'launch') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'triangle';
      osc.frequency.setValueAtTime(160, audioCtx.currentTime);
      osc.frequency.exponentialRampToValueAtTime(700, audioCtx.currentTime + 0.16);
      gain.gain.setValueAtTime(0.18, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.16);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.16);
    } else if (type === 'hit') {
      const osc1 = audioCtx.createOscillator();
      const osc2 = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc1.connect(gain);
      osc2.connect(gain);
      gain.connect(dest);
      osc1.type = 'sine';
      osc2.type = 'sawtooth';
      osc1.frequency.setValueAtTime(800, audioCtx.currentTime);
      osc1.frequency.linearRampToValueAtTime(160, audioCtx.currentTime + 0.24);
      osc2.frequency.setValueAtTime(1100, audioCtx.currentTime);
      osc2.frequency.linearRampToValueAtTime(120, audioCtx.currentTime + 0.18);
      gain.gain.setValueAtTime(0.25, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.25);
      osc1.start();
      osc2.start();
      osc1.stop(audioCtx.currentTime + 0.25);
      osc2.stop(audioCtx.currentTime + 0.25);
    } else if (type === 'headshot') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'square';
      osc.frequency.setValueAtTime(1300, audioCtx.currentTime);
      osc.frequency.exponentialRampToValueAtTime(60, audioCtx.currentTime + 0.45);
      gain.gain.setValueAtTime(0.35, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.45);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.45);
    } else if (type === 'miss') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'sine';
      osc.frequency.setValueAtTime(120, audioCtx.currentTime);
      osc.frequency.linearRampToValueAtTime(25, audioCtx.currentTime + 0.22);
      gain.gain.setValueAtTime(0.2, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.22);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.22);
    } else if (type === 'monitor') {
      const osc = audioCtx.createOscillator();
      const gain = audioCtx.createGain();
      osc.connect(gain);
      gain.connect(dest);
      osc.type = 'sine';
      osc.frequency.setValueAtTime(987.77, audioCtx.currentTime); // B5 tone
      gain.gain.setValueAtTime(0.2, audioCtx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, audioCtx.currentTime + 0.15);
      osc.start();
      osc.stop(audioCtx.currentTime + 0.15);
    }
  } catch (e) {
    console.error("Audio syntheziser failed:", e);
  }
}

// Projection Cabinet Mapping to convert 3D coordinates (x, y, z) into 2D Screen (px, py)
// Game coordinates:
// X: [-9.0, 9.0] -> Canvas X
// Y: [0.0, 6.0]  -> Canvas Y (inverted)
// Z: [-2.0, 2.0] -> Depth shift
function project3DTo2D(x, y, z) {
  const scaleX = 60;
  const scaleY = 91.6;

  // Core orthographic mapping
  const baseCanvasX = 640 + x * scaleX;
  const baseCanvasY = 600 - y * scaleY;

  // Add Cabinet depth projection (Z adds offset to both coordinates)
  return {
    x: baseCanvasX + z * 16,
    y: baseCanvasY - z * 8
  };
}

// Particle Bursts Generator
function spawnParticles(pos, count, type) {
  if (gameMode === '3d' && is3DAvailable) {
    spawnParticles3D(pos, count, type);
  } else {
    const screenPos = project3DTo2D(pos.x, pos.y, pos.z);
    for (let i = 0; i < count; i++) {
      let size = Math.random() * 5 + 3;
      let color = '#CBD5E1'; // standard sparks

      if (type === 'hit') {
        color = Math.random() > 0.4 ? '#FCD34D' : '#10B981';
      } else if (type === 'headshot') {
        color = Math.random() > 0.3 ? '#3B82F6' : '#93C5FD';
      } else if (type === 'miss') {
        color = '#8A8D8B';
      }
      let angle = Math.random() * Math.PI * 2;
      let speed = Math.random() * 4 + 1.5;
      particles2D.push({
        pos: new GameVector2(screenPos.x, screenPos.y),
        vel: new GameVector2(Math.cos(angle) * speed, Math.sin(angle) * speed - (Math.random() * 2 + 0.5)),
        color: color,
        size: size,
        alpha: 1.0,
        decay: Math.random() * 0.03 + 0.015
      });
    }
  }
}

// 3D Particle Spawner (Three.js fallback logic)
function spawnParticles3D(pos, count, type) {
  if (!scene || !THREE) return;
  for (let i = 0; i < count; i++) {
    let size = Math.random() * 0.08 + 0.04;
    let color = 0xCBD5E1;
    if (type === 'hit') {
      color = Math.random() > 0.4 ? 0xC2A267 : 0x10B981;
    } else if (type === 'headshot') {
      color = Math.random() > 0.3 ? 0x3B82F6 : 0x93C5FD;
    } else if (type === 'miss') {
      color = 0x8A8D8B;
    }
    const mat = new THREE.MeshBasicMaterial({
      color: color,
      transparent: true,
      opacity: 1.0
    });
    const geom = new THREE.BoxGeometry(size, size, size);
    const mesh = new THREE.Mesh(geom, mat);
    mesh.position.set(pos.x, pos.y, pos.z);
    let angle = Math.random() * Math.PI * 2;
    let speed = Math.random() * 0.15 + 0.05;
    const pObj = {
      mesh: mesh,
      vel: new THREE.Vector3(Math.cos(angle) * speed, Math.random() * 0.15 + 0.02, Math.sin(angle) * speed),
      alpha: 1.0,
      decay: Math.random() * 0.03 + 0.015
    };
    scene.add(mesh);
    particles3D.push(pObj);
  }
}

// Speech Bubble HTML Dialog boxes
function showHTMLSpeechBubble(text) {
  if (activeBubbleDOM) {
    activeBubbleDOM.remove();
  }
  const container = document.querySelector('.canvas-container');
  const bubble = document.createElement('div');
  bubble.className = 'surgeon-speech-bubble';
  bubble.innerText = text;
  bubble.style.position = 'absolute';
  bubble.style.right = '10%';
  bubble.style.top = '15%';
  bubble.style.background = 'white';
  bubble.style.color = '#0F172A';
  bubble.style.padding = '12px 20px';
  bubble.style.borderRadius = '12px';
  bubble.style.fontWeight = '700';
  bubble.style.fontFamily = 'Inter, sans-serif';
  bubble.style.fontSize = '14px';
  bubble.style.boxShadow = '0 10px 25px rgba(0,0,0,0.3)';
  bubble.style.border = '2px solid #C2A267';
  bubble.style.zIndex = '5';
  container.appendChild(bubble);
  activeBubbleDOM = bubble;
  bubbleTimer = 160;
}

// UI Localization Updates
function updateLocalization() {
  // Sync TR/EN button active classes
  const btnTr = document.getElementById('btn-tr');
  const btnEn = document.getElementById('btn-en');
  if (btnTr && btnEn) {
    if (language === 'tr') {
      btnTr.classList.add('active');
      btnEn.classList.remove('active');
    } else {
      btnEn.classList.add('active');
      btnTr.classList.remove('active');
    }
  }
  const textDict = translations[language];
  document.getElementById('game-title-text').innerHTML = textDict.title;
  document.getElementById('lbl-score').innerText = textDict.score + ": " + score;
  document.getElementById('lbl-level').innerText = textDict.level + ": " + level;
  document.getElementById('lbl-lives').innerText = textDict.lives + ": " + lives;
  document.getElementById('slider-pitch-label').innerText = language === 'tr' ? 'Dikey Açı' : 'Pitch Angle';
  document.getElementById('slider-yaw-label').innerText = language === 'tr' ? 'Yatay Yön' : 'Yaw Direction';
  document.getElementById('slider-power-label').innerText = language === 'tr' ? 'Fırlatma Gücü' : 'Throw Power';
  document.getElementById('btn-fire-text').innerText = language === 'tr' ? 'Fırlat!' : 'Throw!';

  // Nickname & Leaderboard translations
  const nickPromptEl = document.getElementById('lbl-nick-prompt');
  if (nickPromptEl) nickPromptEl.innerText = textDict.nickPrompt;
  const nickInputEl = document.getElementById('player-nick');
  if (nickInputEl) nickInputEl.placeholder = textDict.defaultNick;
  const leadTitleEl = document.getElementById('lbl-leaderboard-title');
  if (leadTitleEl) leadTitleEl.innerText = textDict.leaderboardTitle;
  if (gameState === 'menu') {
    document.getElementById('overlay-title').innerText = textDict.startTitle;
    document.getElementById('overlay-subtitle').innerText = textDict.startSubtitle;
    document.getElementById('btn-start-text').innerText = textDict.playBtn;
    const list = document.getElementById('instructions-list');
    list.innerHTML = '';
    textDict.instructions.forEach(ins => {
      const li = document.createElement('li');
      li.innerText = ins;
      list.appendChild(li);
    });
  } else if (gameState === 'gameover') {
    document.getElementById('overlay-title').innerText = textDict.gameOverTitle;
    document.getElementById('overlay-subtitle').innerText = textDict.gameOverSubtitle;
    document.getElementById('btn-start-text').innerText = textDict.restartBtn;
  } else if (gameState === 'levelup') {
    document.getElementById('overlay-title').innerText = textDict.levelUpTitle;
    document.getElementById('overlay-subtitle').innerText = textDict.levelUpSubtitle;
    document.getElementById('btn-start-text').innerText = textDict.nextLevelBtn;
  }
}

// Wind HUD display and layout updates
function updateLaminarWindHUD() {
  const textDict = translations[language];
  const windText = document.getElementById('lbl-wind');
  let directionStr = textDict.windNone;
  let arrow = "•";
  let speedColor = "#FFFFFF";
  const absWindX = Math.round(Math.abs(windX * 100));
  if (windX > 0) {
    directionStr = textDict.windRight;
    arrow = "→".repeat(Math.min(5, Math.ceil(windX * 100)));
    speedColor = "#10B981";
  } else if (windX < 0) {
    directionStr = textDict.windLeft;
    arrow = "←".repeat(Math.min(5, Math.ceil(Math.abs(windX) * 100)));
    speedColor = "#EF4444";
  }
  windText.innerHTML = `${textDict.wind}: <span style="color: ${speedColor}">${absWindX} ${directionStr} ${arrow}</span>`;
}
function setRandomWind() {
  windX = Math.random() * 0.08 - 0.04;
  windZ = Math.random() * 0.04 - 0.02;
  updateLaminarWindHUD();
}

// 3D Scene Initialization
function build3DScene() {
  if (typeof THREE === 'undefined') return;
  const container = document.querySelector('.canvas-container');
  const width = container.clientWidth;
  const height = container.clientHeight;
  renderer = new THREE.WebGLRenderer({
    canvas: document.getElementById('gameCanvas3D'),
    antialias: true
  });
  renderer.setSize(width, height);
  renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
  renderer.shadowMap.enabled = true;
  renderer.shadowMap.type = THREE.PCFSoftShadowMap;
  scene = new THREE.Scene();
  scene.background = new THREE.Color(0x0a0f1d);
  scene.fog = new THREE.FogExp2(0x0a0f1d, 0.015);
  camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 100);
  cameraTargetPos = new THREE.Vector3(-13, 4.5, 0);
  cameraTargetLook = new THREE.Vector3(10, 1.2, 0);
  currentLookAt = new THREE.Vector3().copy(cameraTargetLook);
  camera.position.copy(cameraTargetPos);
  camera.lookAt(currentLookAt);

  // Lights
  const ambient = new THREE.AmbientLight(0xffffff, 0.35);
  scene.add(ambient);
  const spotlight = new THREE.SpotLight(0xffffff, 2.5);
  spotlight.position.set(2, 8, 0);
  spotlight.angle = Math.PI / 4;
  spotlight.penumbra = 0.5;
  spotlight.castShadow = true;
  spotlight.shadow.mapSize.width = 1024;
  spotlight.shadow.mapSize.height = 1024;
  spotlight.shadow.camera.near = 1;
  spotlight.shadow.camera.far = 15;
  spotlight.shadow.bias = -0.001;
  scene.add(spotlight);
  const dirLight = new THREE.DirectionalLight(0x60a5fa, 0.4);
  dirLight.position.set(-8, 5, -5);
  scene.add(dirLight);

  // Grid Floor
  const floorGeo = new THREE.PlaneGeometry(35, 20);
  const floorMat = new THREE.MeshStandardMaterial({
    color: 0x111827,
    roughness: 0.8
  });
  const floor = new THREE.Mesh(floorGeo, floorMat);
  floor.rotation.x = -Math.PI / 2;
  floor.position.y = FLOOR_Y;
  floor.receiveShadow = true;
  scene.add(floor);
  const grid = new THREE.GridHelper(35, 35, 0x1e293b, 0x1e293b);
  grid.position.y = FLOOR_Y + 0.01;
  scene.add(grid);

  // Table
  const tableGroup = new THREE.Group();
  const baseGeom = new THREE.CylinderGeometry(0.4, 0.5, 1.2, 8);
  const metalMat = new THREE.MeshStandardMaterial({
    color: 0x64748b,
    metalness: 0.9,
    roughness: 0.1
  });
  const tableBase = new THREE.Mesh(baseGeom, metalMat);
  tableBase.position.y = 0.6;
  tableBase.castShadow = true;
  tableGroup.add(tableBase);
  const padGeom = new THREE.BoxGeometry(4.8, 0.25, 1.3);
  const padMat = new THREE.MeshStandardMaterial({
    color: 0x334155,
    roughness: 0.6
  });
  const bedPad = new THREE.Mesh(padGeom, padMat);
  bedPad.position.set(0, 1.2, 0);
  bedPad.castShadow = true;
  bedPad.receiveShadow = true;
  tableGroup.add(bedPad);
  tableMesh = tableGroup;
  tableMesh.position.set(3, 0, 0);
  scene.add(tableMesh);

  // Drape (Ether screen)
  const drapeGroup = new THREE.Group();
  const rodGeom = new THREE.CylinderGeometry(0.04, 0.04, 2.0, 8);
  const drapeRod = new THREE.Mesh(rodGeom, metalMat);
  drapeRod.position.set(0, 1.0, 0);
  drapeGroup.add(drapeRod);
  const topRodGeom = new THREE.CylinderGeometry(0.03, 0.03, 1.5, 8);
  const topRod = new THREE.Mesh(topRodGeom, metalMat);
  topRod.rotation.x = Math.PI / 2;
  topRod.position.set(0, 2.0, 0);
  drapeGroup.add(topRod);
  const clothGeom = new THREE.BoxGeometry(0.03, 1.4, 1.4);
  const clothMat = new THREE.MeshStandardMaterial({
    color: 0x3b82f6,
    transparent: true,
    opacity: 0.65,
    roughness: 0.9
  });
  const cloth = new THREE.Mesh(clothGeom, clothMat);
  cloth.position.set(0, 1.3, 0);
  drapeGroup.add(cloth);
  drapeMesh = drapeGroup;
  drapeMesh.position.set(0.5, 0, 0);
  scene.add(drapeMesh);

  // IV Pole
  const ivGroup = new THREE.Group();
  const mainPole = new THREE.Mesh(new THREE.CylinderGeometry(0.03, 0.03, 3.2, 8), metalMat);
  mainPole.position.y = 1.6;
  mainPole.castShadow = true;
  ivGroup.add(mainPole);
  const crossbar = new THREE.Mesh(new THREE.CylinderGeometry(0.015, 0.015, 0.6, 8), metalMat);
  crossbar.rotation.z = Math.PI / 2;
  crossbar.position.set(0, 3.1, 0);
  ivGroup.add(crossbar);
  const bagMat = new THREE.MeshStandardMaterial({
    color: 0xffffff,
    transparent: true,
    opacity: 0.45,
    roughness: 0.1
  });
  const bagL = new THREE.Mesh(new THREE.BoxGeometry(0.12, 0.25, 0.05), bagMat);
  bagL.position.set(-0.3, 2.85, 0);
  const bagR = bagL.clone();
  bagR.position.x = 0.3;
  ivGroup.add(bagL);
  ivGroup.add(bagR);
  ivPoleMesh = ivGroup;
  ivPoleMesh.position.set(1.5, 0, 1.4);
  scene.add(ivPoleMesh);

  // Patient Monitor
  const monGroup = new THREE.Group();
  const monitorCasing = new THREE.Mesh(new THREE.BoxGeometry(1.2, 0.8, 0.3), new THREE.MeshStandardMaterial({
    color: 0x334155,
    roughness: 0.7
  }));
  monitorCasing.castShadow = true;
  monGroup.add(monitorCasing);
  const lcdScreen = new THREE.Mesh(new THREE.BoxGeometry(1.05, 0.65, 0.04), new THREE.MeshStandardMaterial({
    color: 0x0f172a,
    emissive: 0x10b981,
    emissiveIntensity: 0.15
  }));
  lcdScreen.position.set(0, 0, 0.14);
  monGroup.add(lcdScreen);
  monitorMesh = monGroup;
  monitorMesh.position.set(-6.0, 2.7, -0.8);
  scene.add(monitorMesh);

  // Sitting Anesthesiologist & Stool
  anesthesiologistGroup = new THREE.Group();
  const stoolGroup = new THREE.Group();
  const stoolSeat = new THREE.Mesh(new THREE.CylinderGeometry(0.4, 0.4, 0.15, 12), new THREE.MeshStandardMaterial({
    color: 0x0f172a,
    roughness: 0.5
  }));
  stoolSeat.position.y = 0.8;
  stoolSeat.castShadow = true;
  stoolGroup.add(stoolSeat);
  const cylinderPost = new THREE.Mesh(new THREE.CylinderGeometry(0.06, 0.06, 0.8, 8), metalMat);
  cylinderPost.position.y = 0.4;
  stoolGroup.add(cylinderPost);
  const baseStar = new THREE.Mesh(new THREE.TorusGeometry(0.35, 0.05, 8, 12), metalMat);
  baseStar.rotation.x = Math.PI / 2;
  baseStar.position.y = 0.05;
  stoolGroup.add(baseStar);
  anesthesiologistGroup.add(stoolGroup);
  const playerGroup = new THREE.Group();
  const bodyMat = new THREE.MeshStandardMaterial({
    color: 0x065f46,
    roughness: 0.8
  });
  const scrubBody = new THREE.Mesh(new THREE.CylinderGeometry(0.35, 0.45, 1.0, 12), bodyMat);
  scrubBody.position.y = 1.3;
  scrubBody.castShadow = true;
  playerGroup.add(scrubBody);
  const legL = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.1, 0.6, 8), bodyMat);
  legL.rotation.x = Math.PI / 3;
  legL.position.set(-0.2, 0.95, 0.35);
  const legR = legL.clone();
  legR.position.x = 0.2;
  playerGroup.add(legL);
  playerGroup.add(legR);
  const skinMat = new THREE.MeshStandardMaterial({
    color: 0xfbcfe8,
    roughness: 0.8
  });
  const playerHead = new THREE.Mesh(new THREE.SphereGeometry(0.28, 16, 16), skinMat);
  playerHead.position.y = 2.0;
  playerHead.castShadow = true;
  playerGroup.add(playerHead);
  const surgicalCap = new THREE.Mesh(new THREE.SphereGeometry(0.29, 12, 12, 0, Math.PI * 2, 0, Math.PI / 2), new THREE.MeshStandardMaterial({
    color: 0x2563eb,
    roughness: 0.9
  }));
  surgicalCap.position.y = 2.05;
  playerGroup.add(surgicalCap);
  const mask = new THREE.Mesh(new THREE.BoxGeometry(0.34, 0.18, 0.16), new THREE.MeshStandardMaterial({
    color: 0x60a5fa,
    roughness: 0.9
  }));
  mask.position.set(0, 1.95, 0.2);
  playerGroup.add(mask);
  anesthesiologistGroup.add(playerGroup);
  anesthesiologistGroup.position.set(-7.5, 0, 0);
  scene.add(anesthesiologistGroup);

  // Anesthesia Machine (placed at x = -5.0, y = 0, z = -1.2) - Bizden tarafta
  const machineGroup = new THREE.Group();
  const cabinetBody = new THREE.Mesh(new THREE.BoxGeometry(0.9, 1.4, 0.7), new THREE.MeshStandardMaterial({
    color: 0x334155,
    roughness: 0.5
  }));
  cabinetBody.position.y = 0.7;
  cabinetBody.castShadow = true;
  cabinetBody.receiveShadow = true;
  machineGroup.add(cabinetBody);

  // Drawers handles (silver metal)
  const handleBarGeom = new THREE.BoxGeometry(0.5, 0.05, 0.05);
  const metalHandleMat = new THREE.MeshStandardMaterial({
    color: 0xe2e8f0,
    metalness: 0.8
  });
  for (let dy = 0.3; dy <= 0.9; dy += 0.3) {
    const handle = new THREE.Mesh(handleBarGeom, metalHandleMat);
    handle.position.set(0, dy, 0.36);
    machineGroup.add(handle);
  }

  // Vaporizers shelf and vaporizers
  const topShelf = new THREE.Mesh(new THREE.BoxGeometry(0.85, 0.05, 0.6), metalMat);
  topShelf.position.y = 1.425;
  machineGroup.add(topShelf);
  const sevoVap = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.12, 0.3, 8), new THREE.MeshStandardMaterial({
    color: 0xf59e0b,
    roughness: 0.3
  }));
  sevoVap.position.set(-0.2, 1.6, 0.15);
  machineGroup.add(sevoVap);
  const isoVap = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.12, 0.3, 8), new THREE.MeshStandardMaterial({
    color: 0x6d28d9,
    roughness: 0.3
  }));
  isoVap.position.set(0.2, 1.6, 0.15);
  machineGroup.add(isoVap);

  // Soda Lime Canister on side
  const limeAbsorber = new THREE.Mesh(new THREE.CylinderGeometry(0.15, 0.15, 0.4, 8), new THREE.MeshStandardMaterial({
    color: 0xf1f5f9,
    roughness: 0.6
  }));
  limeAbsorber.position.set(0.35, 0.9, 0.3);
  machineGroup.add(limeAbsorber);

  // Small ventilator screen on bracket
  const ventMount = new THREE.Mesh(new THREE.CylinderGeometry(0.02, 0.02, 0.5, 8), metalMat);
  ventMount.position.set(-0.3, 1.65, -0.1);
  machineGroup.add(ventMount);
  const ventCasing = new THREE.Mesh(new THREE.BoxGeometry(0.4, 0.3, 0.1), new THREE.MeshStandardMaterial({
    color: 0x334155
  }));
  ventCasing.position.set(-0.3, 1.9, -0.1);
  machineGroup.add(ventCasing);
  const ventScreen = new THREE.Mesh(new THREE.BoxGeometry(0.34, 0.24, 0.02), new THREE.MeshStandardMaterial({
    color: 0x020617,
    emissive: 0x0ea5e9,
    emissiveIntensity: 0.4
  }));
  ventScreen.position.set(-0.3, 1.9, -0.04);
  machineGroup.add(ventScreen);

  // Tubes
  const loopTube = new THREE.Mesh(new THREE.TorusGeometry(0.25, 0.03, 6, 12, Math.PI), new THREE.MeshStandardMaterial({
    color: 0x60a5fa,
    transparent: true,
    opacity: 0.8
  }));
  loopTube.rotation.y = Math.PI / 2;
  loopTube.position.set(0.35, 0.7, 0.35);
  machineGroup.add(loopTube);
  machineGroup.position.set(-5.0, 0, -1.2);
  scene.add(machineGroup);

  // Standing Surgeon (Target)
  surgeonGroup = new THREE.Group();
  const surgeonBody = new THREE.Mesh(new THREE.CylinderGeometry(0.35, 0.4, 1.4, 12), new THREE.MeshStandardMaterial({
    color: 0x1d4ed8,
    roughness: 0.8
  }));
  surgeonBody.position.y = 0.7;
  surgeonBody.castShadow = true;
  surgeonGroup.add(surgeonBody);
  const surgeonHead = new THREE.Mesh(new THREE.SphereGeometry(0.28, 16, 16), new THREE.MeshStandardMaterial({
    color: 0xffd2b2,
    roughness: 0.8
  }));
  surgeonHead.position.y = 1.6;
  surgeonHead.castShadow = true;
  surgeonGroup.add(surgeonHead);
  const surgeonCap = new THREE.Mesh(new THREE.SphereGeometry(0.29, 12, 12, 0, Math.PI * 2, 0, Math.PI / 2), new THREE.MeshStandardMaterial({
    color: 0x047857,
    roughness: 0.9
  }));
  surgeonCap.position.y = 1.65;
  surgeonGroup.add(surgeonCap);
  const surgeonMask = mask.clone();
  surgeonMask.position.set(0, 1.55, 0.2);
  surgeonGroup.add(surgeonMask);
  surgeonGroup.position.set(7.5, 0, surgeonZ);
  scene.add(surgeonGroup);

  // Trajectory Predictor Line
  const lineMat = new THREE.LineDashedMaterial({
    color: 0xfcd34d,
    dashSize: 0.25,
    gapSize: 0.15
  });
  trajectoryLine = new THREE.Line(new THREE.BufferGeometry(), lineMat);
  scene.add(trajectoryLine);
  updateTrajectoryLine();
}
function build3DLaryngoscope() {
  if (!scene || typeof THREE === 'undefined') return;
  if (laryngoscopeMesh) {
    scene.remove(laryngoscopeMesh);
  }
  const scopeGroup = new THREE.Group();
  const handleMat = new THREE.MeshStandardMaterial({
    color: 0xe2e8f0,
    metalness: 0.9,
    roughness: 0.2
  });
  const handle = new THREE.Mesh(new THREE.CylinderGeometry(0.06, 0.06, 0.45, 8), handleMat);
  handle.castShadow = true;
  scopeGroup.add(handle);
  const bladeShape = new THREE.Shape();
  bladeShape.moveTo(0, 0.04);
  bladeShape.quadraticCurveTo(0.2, 0.18, 0.45, 0.12);
  bladeShape.quadraticCurveTo(0.25, 0.05, 0, -0.04);
  bladeShape.closePath();
  const extrudeSettings = {
    depth: 0.05,
    bevelEnabled: true,
    bevelSegments: 2,
    steps: 1,
    bevelSize: 0.01,
    bevelThickness: 0.01
  };
  const blade = new THREE.Mesh(new THREE.ExtrudeGeometry(bladeShape, extrudeSettings), handleMat);
  blade.position.set(0, 0.18, -0.025);
  blade.castShadow = true;
  scopeGroup.add(blade);
  const fiber = new THREE.Mesh(new THREE.CylinderGeometry(0.008, 0.008, 0.35, 4), new THREE.MeshStandardMaterial({
    color: 0xffffff,
    emissive: 0xfbcb24,
    emissiveIntensity: 0.8
  }));
  fiber.rotation.z = -Math.PI / 4;
  fiber.position.set(0.18, 0.14, 0.04);
  scopeGroup.add(fiber);
  laryngoscopeMesh = scopeGroup;
  laryngoscopeMesh.castShadow = true;
  resetLaryngoscopePosition();
  scene.add(laryngoscopeMesh);
}
function resetLaryngoscopePosition() {
  if (laryngoscopeMesh) {
    laryngoscopeMesh.position.set(-7.0, 1.5, 0.3);
    laryngoscopeMesh.rotation.set(0, 0, 0);
    laryngoscopeMesh.scale.set(1.0, 1.0, 1.0);
  }
}

// Update Trajectory Predictor Line (3D only)
function updateTrajectoryLine() {
  if (project.isFlying || !trajectoryLine || typeof THREE === 'undefined') {
    if (trajectoryLine) trajectoryLine.visible = false;
    return;
  }
  trajectoryLine.visible = true;
  const pitchRad = aimPitch * (Math.PI / 180);
  const yawRad = aimYaw * (Math.PI / 180);
  const speed = throwPower * 0.0055;
  const vx = Math.cos(pitchRad) * Math.cos(yawRad) * speed;
  const vy = Math.sin(pitchRad) * speed;
  const vz = -Math.cos(pitchRad) * Math.sin(yawRad) * speed;
  const points = [];
  const tempPos = new GameVector3(-7.0, 1.5, 0.3);
  const tempVel = new GameVector3(vx, vy, vz);
  for (let i = 0; i < 60; i++) {
    points.push(new THREE.Vector3(tempPos.x, tempPos.y, tempPos.z));
    tempVel.y -= GRAVITY * 0.016;
    tempVel.x += windX * 0.01;
    tempVel.z += windZ * 0.01;
    tempPos.add(tempVel);
    if (tempPos.y <= FLOOR_Y) {
      points.push(new THREE.Vector3(tempPos.x, FLOOR_Y, tempPos.z));
      break;
    }
  }
  const geom = new THREE.BufferGeometry().setFromPoints(points);
  trajectoryLine.geometry = geom;
  trajectoryLine.computeLineDistances();
}
function drawStaticBackground(ctx) {
  if (!ctx) return;

  // 1. Clear background
  const grad = ctx.createRadialGradient(640, 200, 50, 640, 360, 600);
  grad.addColorStop(0, '#1E293B');
  grad.addColorStop(1, '#090F1E');
  ctx.fillStyle = grad;
  ctx.fillRect(0, 0, 1280, 720);

  // 2. Draw Floor tiles
  ctx.strokeStyle = 'rgba(255, 255, 255, 0.03)';
  ctx.lineWidth = 1;
  for (let i = 600; i <= 720; i += 20) {
    ctx.beginPath();
    ctx.moveTo(0, i);
    ctx.lineTo(1280, i);
    ctx.stroke();
  }

  // Draw depth lines on the floor
  for (let gx = -10; gx <= 10; gx += 2) {
    const p1 = project3DTo2D(gx, 0, -2.0);
    const p2 = project3DTo2D(gx, 0, 2.0);
    ctx.beginPath();
    ctx.moveTo(p1.x, p1.y);
    ctx.lineTo(p2.x, p2.y);
    ctx.stroke();
  }

  // 3. Patient Monitor Casing (Background depth z = -0.8)
  const monPos = project3DTo2D(-6.0, 2.7, -0.8);
  ctx.strokeStyle = '#475569';
  ctx.lineWidth = 6;
  ctx.beginPath();
  ctx.moveTo(monPos.x, monPos.y);
  ctx.lineTo(0, monPos.y); // Wall mount extending to left edge
  ctx.stroke();
  ctx.fillStyle = '#334155';
  ctx.fillRect(monPos.x - 50, monPos.y - 35, 100, 70);
  ctx.strokeStyle = '#64748b';
  ctx.lineWidth = 2;
  ctx.strokeRect(monPos.x - 50, monPos.y - 35, 100, 70);

  // 3.5. Anesthesia Machine (placed at x = -5.0, y = 0, z = -1.2)
  const amPos = project3DTo2D(-5.0, 0, -1.2);
  ctx.save();
  ctx.translate(amPos.x, amPos.y);

  // Main cabinet body
  ctx.fillStyle = '#334155';
  ctx.fillRect(-30, -95, 60, 95);
  ctx.strokeStyle = '#475569';
  ctx.lineWidth = 2;
  ctx.strokeRect(-30, -95, 60, 95);

  // Drawers and lines
  ctx.strokeStyle = '#1e293b';
  ctx.lineWidth = 1;
  for (let dy = -70; dy <= -20; dy += 20) {
    ctx.beginPath();
    ctx.moveTo(-30, dy);
    ctx.lineTo(30, dy);
    ctx.stroke();

    // Handle (silver/metal)
    ctx.fillStyle = '#94a3b8';
    ctx.fillRect(-14, dy + 8, 28, 4);
  }

  // Vaporizers shelf on top
  ctx.fillStyle = '#64748b';
  ctx.fillRect(-32, -98, 64, 4);

  // Yellow Vaporizer (Sevoflurane)
  ctx.fillStyle = '#f59e0b';
  ctx.fillRect(-20, -118, 12, 20);
  ctx.fillStyle = '#cbd5e1';
  ctx.fillRect(-17, -122, 6, 4);

  // Purple Vaporizer (Isoflurane)
  ctx.fillStyle = '#7c3aed';
  ctx.fillRect(-4, -118, 12, 20);
  ctx.fillStyle = '#cbd5e1';
  ctx.fillRect(-1, -122, 6, 4);

  // Soda Lime canister on the side
  ctx.fillStyle = '#f1f5f9';
  ctx.fillRect(22, -65, 16, 28);
  ctx.strokeStyle = '#94a3b8';
  ctx.lineWidth = 1;
  ctx.strokeRect(22, -65, 16, 28);
  ctx.fillStyle = '#f472b6';
  ctx.fillRect(24, -53, 12, 5);

  // Mount bracket for ventilator screen
  ctx.strokeStyle = '#64748b';
  ctx.lineWidth = 3;
  ctx.beginPath();
  ctx.moveTo(-20, -98);
  ctx.lineTo(-20, -130);
  ctx.stroke();

  // Ventilator Screen casing
  ctx.fillStyle = '#1e293b';
  ctx.fillRect(-32, -150, 24, 20);
  ctx.strokeStyle = '#475569';
  ctx.lineWidth = 1;
  ctx.strokeRect(-32, -150, 24, 20);

  // Ventilator LCD Screen (cyan)
  ctx.fillStyle = '#0284c7';
  ctx.fillRect(-30, -148, 20, 16);

  // Ventilator trace
  ctx.strokeStyle = '#ffffff';
  ctx.lineWidth = 1;
  ctx.beginPath();
  ctx.moveTo(-29, -140);
  ctx.lineTo(-26, -140);
  ctx.lineTo(-24, -145);
  ctx.lineTo(-22, -135);
  ctx.lineTo(-20, -140);
  ctx.lineTo(-11, -140);
  ctx.stroke();

  // Curved blue breathing tubes hanging
  ctx.strokeStyle = '#60a5fa';
  ctx.lineWidth = 3;
  ctx.beginPath();
  ctx.arc(15, -48, 14, 0, Math.PI, false);
  ctx.stroke();
  ctx.restore();

  // 4. Operating Table (Middle depth z = 0)
  const tBase = project3DTo2D(3.0, 0, 0);
  const tPad = project3DTo2D(3.0, 1.2, 0);
  ctx.fillStyle = '#475569'; // Pillar
  ctx.fillRect(tBase.x - 24, tPad.y, 48, tBase.y - tPad.y);
  ctx.fillStyle = '#1E293B'; // Pad
  ctx.fillRect(tPad.x - 160, tPad.y - 12, 320, 24);
  ctx.strokeStyle = '#334155';
  ctx.lineWidth = 1;
  ctx.strokeRect(tPad.x - 160, tPad.y - 12, 320, 24);

  // 5. Drape / Ether Screen (Middle depth z = 0)
  const dBase = project3DTo2D(0.5, 0, 0);
  const dTop = project3DTo2D(0.5, 2.0, 0);
  ctx.strokeStyle = '#64748b';
  ctx.lineWidth = 4;
  ctx.beginPath();
  ctx.moveTo(dBase.x, dBase.y);
  ctx.lineTo(dBase.x, dTop.y);
  ctx.stroke();
  ctx.beginPath();
  ctx.moveTo(dBase.x - 40, dTop.y);
  ctx.lineTo(dBase.x + 40, dTop.y);
  ctx.stroke();
  ctx.fillStyle = 'rgba(59, 130, 246, 0.65)'; // transparent drape blue
  ctx.fillRect(dBase.x - 36, dTop.y, 72, 120);

  // 6. Sitting Anesthesiologist stool, scrubs, face, cap, mask (without arm)
  const pBase = project3DTo2D(-7.5, 0, 0.3);

  // Rolling stool
  ctx.fillStyle = '#0f172a';
  ctx.fillRect(pBase.x - 22, pBase.y - 70, 44, 10); // seat
  ctx.fillStyle = '#475569';
  ctx.fillRect(pBase.x - 3, pBase.y - 60, 6, 56); // post
  ctx.fillStyle = '#334155';
  ctx.fillRect(pBase.x - 18, pBase.y - 4, 36, 4); // base legs

  // Green Scrubs body
  ctx.fillStyle = '#065f46';
  ctx.beginPath();
  ctx.arc(pBase.x, pBase.y - 100, 24, 0, Math.PI, true);
  ctx.fill();
  ctx.fillRect(pBase.x - 24, pBase.y - 100, 48, 36);

  // Knees/Legs
  ctx.strokeStyle = '#065f46';
  ctx.lineWidth = 10;
  ctx.lineCap = 'round';
  ctx.beginPath();
  ctx.moveTo(pBase.x - 10, pBase.y - 70);
  ctx.lineTo(pBase.x - 26, pBase.y - 45);
  ctx.lineTo(pBase.x - 26, pBase.y);
  ctx.moveTo(pBase.x + 10, pBase.y - 70);
  ctx.lineTo(pBase.x + 26, pBase.y - 45);
  ctx.lineTo(pBase.x + 26, pBase.y);
  ctx.stroke();

  // Face skin
  ctx.fillStyle = '#fbcfe8';
  ctx.beginPath();
  ctx.arc(pBase.x, pBase.y - 138, 18, 0, Math.PI * 2);
  ctx.fill();

  // Blue surgical cap
  ctx.fillStyle = '#2563eb';
  ctx.beginPath();
  ctx.arc(pBase.x, pBase.y - 143, 19, Math.PI, 0);
  ctx.fill();

  // Mask
  ctx.fillStyle = '#60a5fa';
  ctx.fillRect(pBase.x - 5, pBase.y - 138, 14, 10);

  // 8. IV Pole (Foreground layer, z = 1.4)
  const ivBase = project3DTo2D(1.5, 0, 1.4);
  const ivTop = project3DTo2D(1.5, 3.2, 1.4);
  ctx.strokeStyle = '#cbd5e1';
  ctx.lineWidth = 4;
  ctx.beginPath();
  ctx.moveTo(ivBase.x, ivBase.y);
  ctx.lineTo(ivBase.x, ivTop.y);
  ctx.stroke();
  ctx.beginPath();
  ctx.moveTo(ivBase.x - 18, ivTop.y + 10);
  ctx.lineTo(ivBase.x + 18, ivTop.y + 10);
  ctx.stroke();
  ctx.fillStyle = 'rgba(255,255,255,0.45)';
  ctx.fillRect(ivBase.x - 24, ivTop.y + 16, 10, 22);
  ctx.fillRect(ivBase.x + 14, ivTop.y + 16, 10, 22);
}

// 2D Draw Scene Function (Canvas drawing)
function draw2DScene() {
  if (!ctx2d) return;

  // 1. Draw cached static background
  if (!bgCanvas) {
    bgCanvas = document.createElement('canvas');
    bgCanvas.width = 1280;
    bgCanvas.height = 720;
    const bgCtx = bgCanvas.getContext('2d');
    drawStaticBackground(bgCtx);
  }
  ctx2d.drawImage(bgCanvas, 0, 0);

  // 2. Draw flashing/monitor state on top
  const monPos = project3DTo2D(-6.0, 2.7, -0.8);
  const isFlashing = monitorFlashTimer > 0;

  // Overwrite the LCD screen rectangle with current state
  ctx2d.fillStyle = isFlashing ? '#D97706' : '#0F172A';
  ctx2d.fillRect(monPos.x - 44, monPos.y - 29, 88, 58);

  // Heart trace
  ctx2d.strokeStyle = isFlashing ? '#FFFFFF' : '#10B981';
  ctx2d.lineWidth = 2;
  ctx2d.beginPath();
  ctx2d.moveTo(monPos.x - 40, monPos.y);
  ctx2d.lineTo(monPos.x - 20, monPos.y);
  ctx2d.lineTo(monPos.x - 15, monPos.y - 18);
  ctx2d.lineTo(monPos.x - 10, monPos.y + 18);
  ctx2d.lineTo(monPos.x - 5, monPos.y);
  ctx2d.lineTo(monPos.x + 15, monPos.y);
  ctx2d.lineTo(monPos.x + 20, monPos.y - 12);
  ctx2d.lineTo(monPos.x + 25, monPos.y + 12);
  ctx2d.lineTo(monPos.x + 30, monPos.y);
  ctx2d.lineTo(monPos.x + 40, monPos.y);
  ctx2d.stroke();

  // 3. Draw player arm & laryngoscope in hand
  const pBase = project3DTo2D(-7.5, 0, 0.3);
  if (!project.isFlying) {
    const handPos = project3DTo2D(-7.0, 1.5, 0.3);
    ctx2d.strokeStyle = '#065f46';
    ctx2d.lineWidth = 7;
    ctx2d.beginPath();
    ctx2d.moveTo(pBase.x + 12, pBase.y - 110);
    ctx2d.lineTo(handPos.x - 10, handPos.y + 10);
    ctx2d.lineTo(handPos.x, handPos.y);
    ctx2d.stroke();

    // Blade in hand
    ctx2d.save();
    ctx2d.translate(handPos.x, handPos.y);
    ctx2d.fillStyle = '#cbd5e1'; // metal handle
    ctx2d.fillRect(-2, -10, 4, 20);
    ctx2d.fillStyle = '#e2e8f0'; // Macintosh curve
    ctx2d.beginPath();
    ctx2d.moveTo(2, -10);
    ctx2d.quadraticCurveTo(12, -12, 16, -4);
    ctx2d.quadraticCurveTo(8, 0, 2, 0);
    ctx2d.closePath();
    ctx2d.fill();
    ctx2d.restore();
  } else {
    ctx2d.strokeStyle = '#065f46';
    ctx2d.lineWidth = 7;
    ctx2d.beginPath();
    ctx2d.moveTo(pBase.x + 12, pBase.y - 110);
    ctx2d.lineTo(pBase.x + 32, pBase.y - 95);
    ctx2d.stroke();
  }

  // 4. Standing Surgeon Target (x = 7.5, y = 0, z = surgeonZ)
  const sPos = project3DTo2D(7.5 + surgeonXOffset, 0, surgeonZ);
  const scale = 1.0 + surgeonZ * 0.12;
  ctx2d.save();
  ctx2d.translate(sPos.x, sPos.y);
  ctx2d.scale(scale, scale);

  // Gown
  ctx2d.fillStyle = '#1d4ed8';
  ctx2d.beginPath();
  ctx2d.arc(0, -110, 26, 0, Math.PI, true);
  ctx2d.fill();
  ctx2d.fillRect(-26, -110, 52, 110);

  // Face skin
  ctx2d.fillStyle = '#ffd2b2';
  ctx2d.beginPath();
  ctx2d.arc(0, -150, 18, 0, Math.PI * 2);
  ctx2d.fill();

  // Green cap
  ctx2d.fillStyle = '#047857';
  ctx2d.beginPath();
  ctx2d.arc(0, -155, 19, Math.PI, 0);
  ctx2d.fill();

  // Mask
  ctx2d.fillStyle = '#60a5fa';
  ctx2d.fillRect(-10, -150, 20, 10);

  // Arms holding retractors
  ctx2d.strokeStyle = '#1d4ed8';
  ctx2d.lineWidth = 9;
  ctx2d.lineCap = 'round';
  ctx2d.beginPath();
  ctx2d.moveTo(-18, -100);
  ctx2d.lineTo(-35, -80);
  ctx2d.moveTo(18, -100);
  ctx2d.lineTo(35, -80);
  ctx2d.stroke();
  ctx2d.restore();

  // 5. Dotted Aiming Trajectory (when playing and aiming)
  if (!project.isFlying && gameState === 'playing') {
    const pitchRad = aimPitch * (Math.PI / 180);
    const yawRad = aimYaw * (Math.PI / 180);
    const speed = throwPower * 0.0055;
    const vx = Math.cos(pitchRad) * Math.cos(yawRad) * speed;
    const vy = Math.sin(pitchRad) * speed;
    const vz = -Math.cos(pitchRad) * Math.sin(yawRad) * speed;
    const tempPos = new GameVector3(-7.0, 1.5, 0.3);
    const tempVel = new GameVector3(vx, vy, vz);
    ctx2d.strokeStyle = '#FCD34D';
    ctx2d.lineWidth = 3;
    ctx2d.setLineDash([4, 6]);
    ctx2d.beginPath();
    const startProj = project3DTo2D(tempPos.x, tempPos.y, tempPos.z);
    ctx2d.moveTo(startProj.x, startProj.y);
    for (let i = 0; i < 60; i++) {
      tempVel.y -= GRAVITY * 0.016;
      tempVel.x += windX * 0.01;
      tempVel.z += windZ * 0.01;
      tempPos.add(tempVel);
      const p = project3DTo2D(tempPos.x, tempPos.y, tempPos.z);
      ctx2d.lineTo(p.x, p.y);
      if (tempPos.y <= FLOOR_Y) break;
    }
    ctx2d.stroke();
    ctx2d.setLineDash([]);
  }

  // 6. Flying Laryngoscope
  if (project.isFlying) {
    const projPos = project3DTo2D(project.pos.x, project.pos.y, project.pos.z);
    ctx2d.save();
    ctx2d.translate(projPos.x, projPos.y);
    const rotation = Date.now() * 0.015;
    ctx2d.rotate(rotation);
    ctx2d.fillStyle = '#cbd5e1';
    ctx2d.fillRect(-3, -15, 6, 30);
    ctx2d.fillStyle = '#e2e8f0';
    ctx2d.beginPath();
    ctx2d.moveTo(3, -15);
    ctx2d.quadraticCurveTo(20, -18, 25, -4);
    ctx2d.quadraticCurveTo(12, 0, 3, 0);
    ctx2d.closePath();
    ctx2d.fill();
    ctx2d.fillStyle = '#fcd34d';
    ctx2d.beginPath();
    ctx2d.arc(12, -10, 2.5, 0, Math.PI * 2);
    ctx2d.fill();
    ctx2d.restore();
  }

  // 7. Flying Trail Particles
  for (let i = project.trailParticles.length - 1; i >= 0; i--) {
    const tp = project.trailParticles[i];
    const screenP = project3DTo2D(tp.pos.x, tp.pos.y, tp.pos.z);
    ctx2d.fillStyle = `rgba(255, 255, 255, ${tp.alpha})`;
    ctx2d.beginPath();
    ctx2d.arc(screenP.x, screenP.y, tp.size, 0, Math.PI * 2);
    ctx2d.fill();
  }

  // 8. Burst Sparkle Particles (Hits/Misses)
  for (let i = particles2D.length - 1; i >= 0; i--) {
    const p = particles2D[i];
    ctx2d.fillStyle = p.color;
    ctx2d.globalAlpha = p.alpha;
    ctx2d.beginPath();
    ctx2d.arc(p.pos.x, p.pos.y, p.size, 0, Math.PI * 2);
    ctx2d.fill();
  }
  ctx2d.globalAlpha = 1.0; // reset
}

// Trigger Throw Launcher
function fireFromSliders() {
  if (gameState !== 'playing' || project.isFlying) return;
  initAudio();
  const pitchRad = aimPitch * (Math.PI / 180);
  const yawRad = aimYaw * (Math.PI / 180);
  const speed = throwPower * 0.0055;
  project.pos.set(-7.0, 1.5, 0.3);
  project.vel.set(Math.cos(pitchRad) * Math.cos(yawRad) * speed, Math.sin(pitchRad) * speed, -Math.cos(pitchRad) * Math.sin(yawRad) * speed);
  project.isFlying = true;
  if (gameMode === '3d' && is3DAvailable) {
    trajectoryLine.visible = false;
    cameraMode = 'flight';
  }
  playSynthesizedSound('launch');
}

// Touch & Mouse Drag slingshot controls
let isDraggingViewport = false;
let startDragMouse = {
  x: 0,
  y: 0
};
let currentDragMouse = {
  x: 0,
  y: 0
};
function setupViewportAiming() {
  const container = document.querySelector('.canvas-container');
  container.addEventListener('mousedown', e => {
    if (gameState !== 'playing' || project.isFlying) return;
    initAudio();
    isDraggingViewport = true;
    startDragMouse.x = e.clientX;
    startDragMouse.y = e.clientY;
    currentDragMouse.x = e.clientX;
    currentDragMouse.y = e.clientY;
  });
  window.addEventListener('mousemove', e => {
    if (!isDraggingViewport) return;
    currentDragMouse.x = e.clientX;
    currentDragMouse.y = e.clientY;
    const dx = startDragMouse.x - currentDragMouse.x;
    const dy = currentDragMouse.y - startDragMouse.y;
    aimYaw = Math.min(45, Math.max(-45, Math.round(dx * 0.2)));
    aimPitch = Math.min(85, Math.max(5, Math.round(dy * 0.25 + 40)));
    throwPower = Math.min(100, Math.max(10, Math.round(Math.hypot(dx, dy) * 0.4)));
    document.getElementById('slider-pitch').value = aimPitch;
    document.getElementById('slider-yaw').value = aimYaw;
    document.getElementById('slider-power').value = throwPower;
    document.getElementById('val-pitch').innerText = aimPitch + "°";
    document.getElementById('val-yaw').innerText = (aimYaw >= 0 ? "+" : "") + aimYaw + "°";
    document.getElementById('val-power').innerText = throwPower + "%";
    if (gameMode === '3d' && is3DAvailable) {
      updateTrajectoryLine();
    }
  });
  window.addEventListener('mouseup', () => {
    if (!isDraggingViewport) return;
    isDraggingViewport = false;
    fireFromSliders();
  });

  // Mobile Touch
  container.addEventListener('touchstart', e => {
    if (gameState !== 'playing' || project.isFlying) return;
    initAudio();
    isDraggingViewport = true;
    startDragMouse.x = e.touches[0].clientX;
    startDragMouse.y = e.touches[0].clientY;
    currentDragMouse.x = e.touches[0].clientX;
    currentDragMouse.y = e.touches[0].clientY;
  }, {
    passive: true
  });
  window.addEventListener('touchmove', e => {
    if (!isDraggingViewport) return;
    currentDragMouse.x = e.touches[0].clientX;
    currentDragMouse.y = e.touches[0].clientY;
    const dx = startDragMouse.x - currentDragMouse.x;
    const dy = currentDragMouse.y - startDragMouse.y;
    aimYaw = Math.min(45, Math.max(-45, Math.round(dx * 0.2)));
    aimPitch = Math.min(85, Math.max(5, Math.round(dy * 0.25 + 40)));
    throwPower = Math.min(100, Math.max(10, Math.round(Math.hypot(dx, dy) * 0.4)));
    document.getElementById('slider-pitch').value = aimPitch;
    document.getElementById('slider-yaw').value = aimYaw;
    document.getElementById('slider-power').value = throwPower;
    document.getElementById('val-pitch').innerText = aimPitch + "°";
    document.getElementById('val-yaw').innerText = (aimYaw >= 0 ? "+" : "") + aimYaw + "°";
    document.getElementById('val-power').innerText = throwPower + "%";
    if (gameMode === '3d' && is3DAvailable) {
      updateTrajectoryLine();
    }
  }, {
    passive: true
  });
  window.addEventListener('touchend', () => {
    if (!isDraggingViewport) return;
    isDraggingViewport = false;
    fireFromSliders();
  });
}

// 3D & 2D Unified Collision Checking
function check3DCollisions() {
  const pos = project.pos;

  // 1. Floor collision
  if (pos.y <= FLOOR_Y + project.radius) {
    project.isFlying = false;
    spawnParticles(pos, 15, 'miss');
    playSynthesizedSound('miss');
    const quotes = translations[language].surgeonMisses;
    showHTMLSpeechBubble(quotes[Math.floor(Math.random() * quotes.length)]);
    loseLife();
    return;
  }

  // 2. Patient Monitor box hit (-6.0, 2.7, -0.8) size: (1.2, 0.8, 0.3)
  const monMin = new GameVector3(-6.0 - 0.6, 2.7 - 0.4, -0.8 - 0.15);
  const monMax = new GameVector3(-6.0 + 0.6, 2.7 + 0.4, -0.8 + 0.15);
  if (pos.x >= monMin.x - project.radius && pos.x <= monMax.x + project.radius && pos.y >= monMin.y - project.radius && pos.y <= monMax.y + project.radius && pos.z >= monMin.z - project.radius && pos.z <= monMax.z + project.radius) {
    // Bounce vector inversion
    project.vel.x = -project.vel.x * 0.95;
    project.vel.y = Math.abs(project.vel.y) * 0.9;
    project.vel.z = -project.vel.z * 0.9;
    pos.addScaledVector(project.vel, 1.2);

    // 3D screen lighting updates
    if (gameMode === '3d' && is3DAvailable && monitorMesh) {
      monitorMesh.children[1].material.emissive.setHex(0x10B981);
      monitorMesh.children[1].material.emissiveIntensity = 0.95;
      setTimeout(() => {
        if (monitorMesh) {
          monitorMesh.children[1].material.emissive.setHex(0x10B981);
          monitorMesh.children[1].material.emissiveIntensity = 0.15;
        }
      }, 400);
    }
    monitorFlashTimer = 25; // 25 frames flash on 2D Screen

    playSynthesizedSound('monitor');
    spawnParticles(pos, 10, 'hit');
    return;
  }

  // 3. IV Pole collision check (1.5, 1.6, 1.4)
  const distToPole2D = Math.hypot(pos.x - 1.5, pos.z - 1.4);
  if (distToPole2D <= 0.18 + project.radius && pos.y >= 0 && pos.y <= 3.2) {
    project.isFlying = false;
    spawnParticles(pos, 12, 'miss');
    playSynthesizedSound('miss');
    loseLife();
    return;
  }

  // 4. Drape Screen check
  if (pos.x >= 0.35 && pos.x <= 0.65 && pos.y >= 0 && pos.y <= 2.0 && Math.abs(pos.z) <= 0.8) {
    project.isFlying = false;
    spawnParticles(pos, 12, 'miss');
    playSynthesizedSound('miss');
    loseLife();
    return;
  }

  // 5. Surgeon target checks
  const targetX = 7.5;
  const targetY = 0;
  const targetZCoord = surgeonZ;
  if (gameMode === '2d') {
    // --- 2D Screen-space Collision Bounding Check ---
    const proj2D = project3DTo2D(pos.x, pos.y, pos.z);
    const head2D = project3DTo2D(targetX + surgeonXOffset, targetY + 1.6, targetZCoord);
    const scale = 1.0 + targetZCoord * 0.12;

    // 2D Headshot Check (screen distance radius 24px)
    const distHead = Math.hypot(proj2D.x - head2D.x, proj2D.y - head2D.y);
    if (distHead <= 24 * scale) {
      triggerHit(true);
      return;
    }

    // 2D Body Check
    // Body is drawn relative to base (sPos.y): width = 52px * scale, height = 110px * scale
    // horizontally centered at head2D.x
    // vertically from head2D.y + 32px to head2D.y + 146px
    const bodyWidth = 52 * scale;
    const bodyTop = head2D.y + 32 * scale;
    const bodyBottom = head2D.y + 146 * scale;
    if (proj2D.x >= head2D.x - bodyWidth / 2 && proj2D.x <= head2D.x + bodyWidth / 2 && proj2D.y >= bodyTop && proj2D.y <= bodyBottom) {
      triggerHit(false);
      return;
    }
  } else {
    // --- 3D Forgiving Bounding Check ---
    const headCenter = new GameVector3(targetX, targetY + 1.6, targetZCoord);
    const distToHead = pos.distanceTo(headCenter);
    if (distToHead <= 0.32 + project.radius) {
      triggerHit(true);
      return;
    }

    // Body check (Z axis is more forgiving)
    const distToBodyX = Math.abs(pos.x - targetX);
    const distToBodyZ = Math.abs(pos.z - targetZCoord);
    if (distToBodyX <= 0.45 + project.radius && distToBodyZ <= 0.8 + project.radius && pos.y >= targetY && pos.y <= targetY + 1.4) {
      triggerHit(false);
      return;
    }
  }
}
function triggerHit(isHeadshot) {
  project.isFlying = false;
  if (gameMode === '3d' && is3DAvailable) {
    cameraMode = 'hit';
  }
  spawnParticles(project.pos, isHeadshot ? 25 : 15, isHeadshot ? 'headshot' : 'hit');
  playSynthesizedSound(isHeadshot ? 'headshot' : 'hit');
  const quotes = translations[language].surgeonHits;
  showHTMLSpeechBubble(quotes[Math.floor(Math.random() * quotes.length)]);
  combo++;
  let scoreGain = isHeadshot ? 350 : 100;
  if (combo > 1) {
    scoreGain = scoreGain * combo;
    triggerFloatingComboLabel(scoreGain, combo);
  } else {
    triggerFloatingComboLabel(scoreGain, 0);
  }
  score += scoreGain;
  document.getElementById('lbl-score').innerText = translations[language].score + ": " + score;

  // Surgeon wiggles
  let shakeTimer = 0;
  const shakeInterval = setInterval(() => {
    const offset = Math.sin(shakeTimer) * 0.15;
    if (gameMode === '3d' && is3DAvailable && surgeonGroup) {
      surgeonGroup.position.x = 7.5 + offset;
    }
    surgeonXOffset = offset;
    shakeTimer += 0.8;
    if (shakeTimer > 10) {
      clearInterval(shakeInterval);
      if (gameMode === '3d' && is3DAvailable && surgeonGroup) {
        surgeonGroup.position.x = 7.5;
      }
      surgeonXOffset = 0;
    }
  }, 20);
  if (score >= level * 650) {
    setTimeout(triggerLevelUp, 1500);
  } else {
    setTimeout(resetRound, 1500);
  }
}
function triggerFloatingComboLabel(scoreGain, multiplier) {
  const container = document.querySelector('.canvas-container');
  const label = document.createElement('div');
  label.className = 'combo-floating-label';
  label.innerText = multiplier > 1 ? `COMBO x${multiplier}! +${scoreGain}` : `+${scoreGain}`;
  label.style.position = 'absolute';
  label.style.right = '12%';
  label.style.top = '40%';
  label.style.color = multiplier > 1 ? '#FCD34D' : '#10B981';
  label.style.fontWeight = '800';
  label.style.fontSize = '24px';
  label.style.fontFamily = 'Outfit, sans-serif';
  label.style.textShadow = '0 0 10px black';
  label.style.zIndex = '5';
  label.style.animation = 'floatUpFade 1.2s forwards ease-out';
  container.appendChild(label);
  setTimeout(() => {
    label.remove();
  }, 1200);
}

// Floating css animations inject
const styleSheet = document.createElement("style");
styleSheet.innerText = `
@keyframes floatUpFade {
    0% { transform: translateY(0); opacity: 1; }
    100% { transform: translateY(-40px); opacity: 0; }
}
.surgeon-speech-bubble::before {
    content: '';
    position: absolute;
    bottom: -10px;
    right: 30px;
    border-width: 10px 10px 0;
    border-style: solid;
    border-color: white transparent;
    display: block;
    width: 0;
}
`;
document.head.appendChild(styleSheet);
function loseLife() {
  lives--;
  combo = 0;
  document.getElementById('lbl-lives').innerText = translations[language].lives + ": " + lives;
  if (lives <= 0) {
    setTimeout(setGameOver, 1500);
  } else {
    setTimeout(resetRound, 1500);
  }
}
function resetRound() {
  project.isFlying = false;
  resetLaryngoscopePosition();
  setRandomWind();
  if (gameMode === '3d' && is3DAvailable) {
    updateTrajectoryLine();
    cameraMode = 'aim';
  }
  if (activeBubbleDOM) {
    activeBubbleDOM.remove();
    activeBubbleDOM = null;
  }
}

// Overlay triggers
function startNewGame() {
  score = 0;
  level = 1;
  lives = 5;
  targetSpeed = 0.03;
  combo = 0;

  // Read and save nickname
  const nickInput = document.getElementById('player-nick');
  if (nickInput && nickInput.value.trim() !== "") {
    playerNick = nickInput.value.trim().substring(0, 12);
    localStorage.setItem('laryngoscope_player_nick', playerNick);
  } else {
    playerNick = language === 'tr' ? 'Anestezist' : 'Anesthesiologist';
  }

  // Read and save country code
  const countrySelect = document.getElementById('player-country');
  if (countrySelect) {
    playerCountry = countrySelect.value || 'TR';
    localStorage.setItem('laryngoscope_player_country', playerCountry);
  } else {
    playerCountry = 'TR';
  }

  // Hide nick container and leaderboard when game starts
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) leadCont.classList.add('d-none');
  document.getElementById('gameOverlayScreen').classList.add('d-none');
  gameState = 'playing';
  resetRound();
  updateLocalization();
}
function nextLevel() {
  level++;
  lives = Math.min(lives + 1, 5);
  targetSpeed = 0.03 + level * 0.012;

  // Hide nick and leaderboard
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) leadCont.classList.add('d-none');
  document.getElementById('gameOverlayScreen').classList.add('d-none');
  gameState = 'playing';
  resetRound();
  updateLocalization();
}
function setGameOver() {
  gameState = 'gameover';
  if (score > highscore) {
    highscore = score;
    localStorage.setItem('laryngoscope_highscore', highscore);
  }

  // Add current score to leaderboard
  addScoreToLeaderboard(playerNick, playerCountry, score);
  document.getElementById('gameOverlayScreen').classList.remove('d-none');
  document.getElementById('stat-box-score').innerText = score;
  document.getElementById('stat-box-highscore').innerText = highscore;
  document.getElementById('stats-grid').classList.remove('d-none');

  // Hide nick input and show leaderboard
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) {
    leadCont.classList.remove('d-none');
    displayLeaderboard();
  }
  updateLocalization();
}
function triggerLevelUp() {
  gameState = 'levelup';
  document.getElementById('gameOverlayScreen').classList.remove('d-none');
  document.getElementById('stat-box-score').innerText = score;
  document.getElementById('stat-box-highscore').innerText = highscore;
  document.getElementById('stats-grid').classList.remove('d-none');

  // Hide nick input and leaderboard during level up transition
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.add('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) leadCont.classList.add('d-none');
  updateLocalization();
}
function syncSliderValues() {
  aimPitch = parseFloat(document.getElementById('slider-pitch').value);
  aimYaw = parseFloat(document.getElementById('slider-yaw').value);
  throwPower = parseFloat(document.getElementById('slider-power').value);
  if (gameMode === '3d' && is3DAvailable) {
    updateTrajectoryLine();
  }
}

// Game Mode Toggle Logic (2D/3D switches)
function toggleGameMode() {
  if (!is3DAvailable) return;
  initAudio();
  const canvas2DNode = document.getElementById('gameCanvas2D');
  const canvas3DNode = document.getElementById('gameCanvas3D');
  const toggleBtn = document.getElementById('btn-mode-toggle');
  if (gameMode === '2d') {
    gameMode = '3d';
    canvas2DNode.classList.add('d-none');
    canvas3DNode.classList.remove('d-none');
    toggleBtn.innerText = '2D';
    if (project.isFlying) {
      cameraMode = 'flight';
    } else {
      cameraMode = 'aim';
    }
    onWindowResize();
  } else {
    gameMode = '2d';
    canvas3DNode.classList.add('d-none');
    canvas2DNode.classList.remove('d-none');
    toggleBtn.innerText = '3D';
  }
}
function disable3DMode() {
  is3DAvailable = false;
  gameMode = '2d';
  const toggleBtn = document.getElementById('btn-mode-toggle');
  if (toggleBtn) {
    toggleBtn.style.opacity = '0.5';
    toggleBtn.style.cursor = 'not-allowed';
    toggleBtn.title = language === 'tr' ? '3D Modu Kullanılamıyor' : '3D Mode Unavailable';
    toggleBtn.innerText = '2D';
    toggleBtn.onclick = null;
  }
  const toast = document.getElementById('threejs-warning-toast');
  if (toast) {
    toast.classList.remove('d-none');
    toast.innerText = language === 'tr' ? 'WebGL/Three.js yüklenemedi veya desteklenmiyor. 2D moduna geçildi.' : 'WebGL/Three.js failed to load or is unsupported. Falling back to 2D mode.';
    setTimeout(() => {
      toast.style.opacity = '0';
      setTimeout(() => toast.classList.add('d-none'), 500);
    }, 4000);
  }
}

// 2D/3D Unified Animation loop
function animate() {
  requestAnimationFrame(animate);

  // Decrement flash timers
  if (monitorFlashTimer > 0) monitorFlashTimer--;

  // Dialog bubbles
  if (bubbleTimer > 0) {
    bubbleTimer--;
    if (bubbleTimer <= 0 && activeBubbleDOM) {
      activeBubbleDOM.remove();
      activeBubbleDOM = null;
    }
  }

  // Unified Physics Update
  if (gameState === 'playing') {
    // 1. Surgeon target lateral movement
    if (!project.isFlying) {
      surgeonZ += targetDirection * targetSpeed;
      if (surgeonGroup) {
        surgeonGroup.position.z = surgeonZ;
      }
      if (surgeonZ > 1.2) {
        targetDirection = -1;
      } else if (surgeonZ < -1.2) {
        targetDirection = 1;
      }
    }

    // 2. Projectile flight physics
    if (project.isFlying) {
      project.vel.y -= GRAVITY * 0.016;
      project.vel.x += windX * 0.01;
      project.vel.z += windZ * 0.01;
      project.pos.add(project.vel);
      if (laryngoscopeMesh) {
        laryngoscopeMesh.position.set(project.pos.x, project.pos.y, project.pos.z);
        laryngoscopeMesh.rotation.x += 0.15;
        laryngoscopeMesh.rotation.y += 0.08;
      }

      // Particles trails generator
      if (gameMode === '2d') {
        if (Math.random() > 0.4) {
          project.trailParticles.push({
            pos: project.pos.clone(),
            alpha: 0.5,
            size: Math.random() * 6 + 3
          });
        }
      } else {
        if (Math.random() > 0.4 && scene && THREE) {
          const trailGeo = new THREE.SphereGeometry(0.04, 4, 4);
          const trailMat = new THREE.MeshBasicMaterial({
            color: 0xffffff,
            transparent: true,
            opacity: 0.4
          });
          const trailMesh = new THREE.Mesh(trailGeo, trailMat);
          trailMesh.position.set(project.pos.x, project.pos.y, project.pos.z);
          scene.add(trailMesh);
          project.trailParticles.push({
            mesh: trailMesh,
            alpha: 0.4
          });
        }
      }
      check3DCollisions();
    }

    // Decay particle trails
    if (gameMode === '2d') {
      for (let i = project.trailParticles.length - 1; i >= 0; i--) {
        const tp = project.trailParticles[i];
        tp.alpha -= 0.02;
        if (tp.alpha <= 0) {
          project.trailParticles.splice(i, 1);
        }
      }
    } else {
      for (let i = project.trailParticles.length - 1; i >= 0; i--) {
        const tp = project.trailParticles[i];
        tp.alpha -= 0.015;
        if (tp.mesh && tp.mesh.material) {
          tp.mesh.material.opacity = tp.alpha;
          tp.mesh.scale.multiplyScalar(0.96);
        }
        if (tp.alpha <= 0) {
          if (scene && tp.mesh) scene.remove(tp.mesh);
          project.trailParticles.splice(i, 1);
        }
      }
    }
  }

  // Decay burst particles
  if (gameMode === '2d') {
    for (let i = particles2D.length - 1; i >= 0; i--) {
      const p = particles2D[i];
      p.pos.add(p.vel);
      p.vel.y += 0.15; // screen down pull gravity
      p.alpha -= p.decay;
      if (p.alpha <= 0) {
        particles2D.splice(i, 1);
      }
    }
  } else {
    for (let i = particles3D.length - 1; i >= 0; i--) {
      const p = particles3D[i];
      p.mesh.position.add(p.vel);
      p.vel.y -= 0.005;
      p.alpha -= p.decay;
      if (p.mesh && p.mesh.material) {
        p.mesh.material.opacity = p.alpha;
        p.mesh.scale.multiplyScalar(0.96);
      }
      if (p.alpha <= 0) {
        if (scene && p.mesh) scene.remove(p.mesh);
        particles3D.splice(i, 1);
      }
    }
  }

  // Draw/Render active views
  if (gameMode === '3d' && is3DAvailable && scene && camera && renderer) {
    if (cameraMode === 'aim') {
      cameraTargetPos.set(-13, 4.0, 0);
      cameraTargetLook.set(4, 1.2, 0);
    } else if (cameraMode === 'flight') {
      cameraTargetPos.set(project.pos.x - 3.5, project.pos.y + 1.8, project.pos.z);
      cameraTargetLook.set(project.pos.x, project.pos.y, project.pos.z);
    } else if (cameraMode === 'hit') {
      cameraTargetPos.set(4.5, 2.0, surgeonZ + 1.5);
      cameraTargetLook.set(7.5, 1.6, surgeonZ);
    }
    camera.position.lerp(cameraTargetPos, 0.08);
    currentLookAt.lerp(cameraTargetLook, 0.08);
    camera.lookAt(currentLookAt);
    renderer.render(scene, camera);
  } else {
    draw2DScene();
  }
}

// Viewport resize adjusts
function onWindowResize() {
  const container = document.querySelector('.canvas-container');
  const width = container.clientWidth;
  const height = container.clientHeight;
  if (gameMode === '3d' && is3DAvailable && camera && renderer) {
    camera.aspect = width / height;
    camera.updateProjectionMatrix();
    renderer.setSize(width, height);
  }
}

// Global UI Settings hooks
function changeGameLanguage(lang) {
  language = lang;
  localStorage.setItem('anesthesia_pref_lang', lang);
  saveValueToCookie('anesthesia_pref_lang', lang);
  updateLocalization();
  updateLaminarWindHUD();
}
function toggleAudio() {
  audioEnabled = !audioEnabled;
  const btn = document.getElementById('btn-audio');
  if (audioEnabled) {
    btn.innerHTML = '<i class="fa-solid fa-volume-high"></i>';
  } else {
    btn.innerHTML = '<i class="fa-solid fa-volume-xmark"></i>';
  }
}

// Game Startup Launcher
function initGame() {
  // 1. Initialize 2D Canvas context
  canvas2D = document.getElementById('gameCanvas2D');
  ctx2d = canvas2D.getContext('2d');
  canvas2D.width = 1280;
  canvas2D.height = 720;

  // Reset background canvas on restart/init to force redraw
  bgCanvas = null;

  // 2. Perform WebGL & CDN libraries availability checks
  const isThreeLoaded = typeof THREE !== 'undefined';
  let isWebGLSupported = false;
  if (isThreeLoaded) {
    try {
      const canvasTest = document.createElement('canvas');
      isWebGLSupported = !!(window.WebGLRenderingContext && (canvasTest.getContext('webgl') || canvasTest.getContext('experimental-webgl')));
    } catch (e) {
      isWebGLSupported = false;
    }
  }
  is3DAvailable = isThreeLoaded && isWebGLSupported;

  // Load language preference
  const savedLang = localStorage.getItem('anesthesia_pref_lang') || getValueFromCookie('anesthesia_pref_lang');
  if (savedLang) {
    language = savedLang;
    localStorage.setItem('anesthesia_pref_lang', savedLang);
    saveValueToCookie('anesthesia_pref_lang', savedLang);
  }

  // Load highscore with cookie fallback & sync
  const localHighscore = parseInt(localStorage.getItem('laryngoscope_highscore') || 0);
  const cookieHighscore = parseInt(getValueFromCookie('laryngoscope_highscore') || 0);
  highscore = Math.max(localHighscore, cookieHighscore);
  localStorage.setItem('laryngoscope_highscore', highscore);
  saveValueToCookie('laryngoscope_highscore', highscore);

  // Load nickname and country with cookie fallback & sync
  const localNick = localStorage.getItem('laryngoscope_player_nick');
  const cookieNick = getValueFromCookie('laryngoscope_player_nick');
  playerNick = localNick || cookieNick || (language === 'tr' ? 'Anestezist' : 'Anesthesiologist');
  localStorage.setItem('laryngoscope_player_nick', playerNick);
  saveValueToCookie('laryngoscope_player_nick', playerNick);
  const localCountry = localStorage.getItem('laryngoscope_player_country');
  const cookieCountry = getValueFromCookie('laryngoscope_player_country');
  playerCountry = localCountry || cookieCountry || 'TR';
  localStorage.setItem('laryngoscope_player_country', playerCountry);
  saveValueToCookie('laryngoscope_player_country', playerCountry);

  // 3. Setup mouse and mobile drag listener binds
  setupViewportAiming();

  // 4. Load 3D elements if available, but stay in 2D mode by default to prevent blackouts
  gameMode = '2d';
  const canvas2DNode = document.getElementById('gameCanvas2D');
  const canvas3DNode = document.getElementById('gameCanvas3D');
  canvas2DNode.classList.remove('d-none');
  canvas3DNode.classList.add('d-none');
  if (is3DAvailable) {
    try {
      build3DScene();
      build3DLaryngoscope();
      document.getElementById('btn-mode-toggle').innerText = '3D';
    } catch (e) {
      console.error("ThreeJS scene build failed:", e);
      disable3DMode();
    }
  } else {
    disable3DMode();
  }
  gameState = 'menu';
  const nickInput = document.getElementById('player-nick');
  if (nickInput) nickInput.value = playerNick;
  const countrySelect = document.getElementById('player-country');
  if (countrySelect) countrySelect.value = playerCountry;
  initLeaderboard();

  // Show nick input and leaderboard in the menu overlay
  const nickInputCont = document.getElementById('nick-input-container');
  if (nickInputCont) nickInputCont.classList.remove('d-none');
  const leadCont = document.getElementById('leaderboard-container');
  if (leadCont) {
    leadCont.classList.remove('d-none');
    displayLeaderboard();
  }
  updateLocalization();
  setRandomWind();
  window.addEventListener('resize', onWindowResize);

  // Run animation frames loop
  animate();
}

// Bind load trigger
window.addEventListener('load', () => {
  initGame();
});
})(); } catch (e) { __ds_ns.__errors.push({ path: "game-build/js/game.js", error: String((e && e.message) || e) }); }

// ui_kits/mobile_app/AlgorithmFlowScreen.jsx
try { (() => {
// Anesthesia Briefs — Algorithm flow detail (CICO crisis steps).
const DS_FLOW = window.AnesthesiaBriefsDesignSystem_a9434e;
function AlgorithmFlowScreen({
  onBack
}) {
  const {
    Badge,
    ClinicalNote,
    PremiumCard
  } = DS_FLOW;
  const steps = [{
    n: 1,
    t: "Declare CICO",
    d: "Call for help. Announce 'Cannot intubate, cannot oxygenate'. Assign roles."
  }, {
    n: 2,
    t: "Optimise oxygenation",
    d: "100% O₂, two-handed mask, oral/nasal airways, full muscle relaxation."
  }, {
    n: 3,
    t: "Final attempt",
    d: "One best attempt at SGA / single look with optimal conditions."
  }, {
    n: 4,
    t: "Front-of-neck access",
    d: "Scalpel–bougie–tube cricothyroidotomy without delay."
  }];
  return /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      overflowY: "auto",
      background: "var(--ab-sand)",
      display: "flex",
      flexDirection: "column"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      background: "linear-gradient(135deg,#7A0F0F,#A61A1A)",
      padding: "54px 20px 20px",
      color: "#fff"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: 14
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-arrow-left",
    style: {
      fontSize: 18,
      cursor: "pointer"
    },
    onClick: onBack
  }), /*#__PURE__*/React.createElement(Badge, {
    variant: "solidGold",
    style: {
      background: "rgba(255,255,255,0.18)",
      color: "#fff",
      border: "0.8px solid rgba(255,255,255,0.4)"
    }
  }, "Emergency \xB7 4 min")), /*#__PURE__*/React.createElement("h1", {
    style: {
      margin: "14px 0 0",
      fontFamily: "var(--font-display)",
      fontWeight: 900,
      fontSize: 26,
      letterSpacing: "-0.5px"
    }
  }, "Cannot Intubate Cannot Oxygenate")), /*#__PURE__*/React.createElement("div", {
    style: {
      padding: "18px 20px 28px",
      display: "flex",
      flexDirection: "column",
      gap: 14
    }
  }, /*#__PURE__*/React.createElement(ClinicalNote, {
    tone: "warning"
  }, "Time-critical. Move to front-of-neck access early \u2014 do not persist with failed laryngoscopy."), steps.map(s => /*#__PURE__*/React.createElement(PremiumCard, {
    key: s.n,
    padding: "16px"
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: 14
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: 34,
      height: 34,
      flex: "none",
      borderRadius: "50%",
      background: "var(--ab-red)",
      color: "#fff",
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 15
    }
  }, s.n), /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 16,
      color: "var(--ab-navy)"
    }
  }, s.t), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "4px 0 0",
      fontFamily: "var(--font-body)",
      fontSize: 13,
      lineHeight: 1.45,
      color: "var(--ab-text-muted)"
    }
  }, s.d)))))));
}
window.AlgorithmFlowScreen = AlgorithmFlowScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/mobile_app/AlgorithmFlowScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/mobile_app/AlgorithmsScreen.jsx
try { (() => {
// Anesthesia Briefs — Decision Algorithms list screen.
const DS_ALGO = window.AnesthesiaBriefsDesignSystem_a9434e;
function AlgorithmsScreen({
  onOpenAlgo
}) {
  const {
    SearchField,
    CategoryChip,
    Badge
  } = DS_ALGO;
  const [cat, setCat] = React.useState(0);
  const [urg, setUrg] = React.useState(0);
  const algos = [{
    title: "Unexpected Difficult Intubation",
    cat: "Airway & Respiratory",
    time: "5 min"
  }, {
    title: "Cannot Intubate Cannot Oxygenate (CICO)",
    cat: "Critical Crises",
    time: "4 min"
  }, {
    title: "Perioperative Laryngospasm",
    cat: "Airway & Respiratory",
    time: "3 min"
  }, {
    title: "Intraoperative Hypoxemia",
    cat: "Airway & Respiratory",
    time: "4 min"
  }, {
    title: "Local Anesthetic Systemic Toxicity (LAST)",
    cat: "Critical Crises",
    time: "4 min"
  }];
  return /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      overflowY: "auto",
      background: "var(--ab-sand)",
      padding: "54px 20px 24px"
    }
  }, /*#__PURE__*/React.createElement("h1", {
    style: {
      margin: 0,
      fontFamily: "var(--font-display)",
      fontWeight: 900,
      fontSize: 28,
      letterSpacing: "-0.6px",
      color: "var(--ab-navy)"
    }
  }, "Decision Algorithms"), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "6px 0 18px",
      fontFamily: "var(--font-body)",
      fontSize: 15,
      color: "var(--ab-text-muted)"
    }
  }, "Critical scenarios and complication management trees."), /*#__PURE__*/React.createElement(SearchField, {
    placeholder: "Search algorithm or category\u2026"
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 13,
      color: "var(--ab-navy)",
      margin: "20px 0 10px"
    }
  }, "Clinical Categories"), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: 8,
      overflowX: "auto",
      paddingBottom: 4
    }
  }, ["All Categories", "Preop & Scoring", "Airway & Respiratory", "Critical Crises"].map((c, i) => /*#__PURE__*/React.createElement(CategoryChip, {
    key: i,
    selected: cat === i,
    onClick: () => setCat(i)
  }, c))), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 13,
      color: "var(--ab-navy)",
      margin: "16px 0 10px"
    }
  }, "Clinical Urgency Level"), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: 8,
      overflowX: "auto",
      paddingBottom: 4
    }
  }, ["All", "Elective", "Urgent", "Emergency"].map((c, i) => /*#__PURE__*/React.createElement(CategoryChip, {
    key: i,
    selected: urg === i,
    onClick: () => setUrg(i)
  }, c))), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      flexDirection: "column",
      gap: 14,
      marginTop: 18
    }
  }, algos.map((a, i) => /*#__PURE__*/React.createElement("div", {
    key: i,
    onClick: onOpenAlgo,
    style: {
      display: "flex",
      alignItems: "center",
      gap: 14,
      cursor: "pointer",
      background: "var(--cat-cardio-bg)",
      border: "1px solid var(--cat-cardio-border)",
      borderRadius: "var(--radius-lg)",
      padding: "16px 16px"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: 44,
      height: 44,
      flex: "none",
      borderRadius: 12,
      background: "#FEE2E2",
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      color: "var(--ab-red)",
      fontSize: 18
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-circle-xmark"
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      minWidth: 0
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 16,
      lineHeight: 1.2,
      color: "var(--ab-navy)"
    }
  }, a.title), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-body)",
      fontSize: 12,
      marginTop: 5,
      color: "var(--ab-teal)",
      fontWeight: 600
    }
  }, a.cat, " ", /*#__PURE__*/React.createElement("span", {
    style: {
      color: "var(--ab-text-muted)",
      fontWeight: 400
    }
  }, "\xB7 Time: ", a.time))), /*#__PURE__*/React.createElement(Badge, {
    variant: "emergency",
    icon: /*#__PURE__*/React.createElement("i", {
      className: "fa-solid fa-truck-medical"
    })
  }, "Emergency")))));
}
window.AlgorithmsScreen = AlgorithmsScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/mobile_app/AlgorithmsScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/mobile_app/CalculatorScreen.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
// Anesthesia Briefs — Pediatric Dose Calculator detail screen.
const DS_CALC = window.AnesthesiaBriefsDesignSystem_a9434e;
function CalculatorScreen() {
  const {
    PremiumCard,
    DoseRow
  } = DS_CALC;
  const [wt, setWt] = React.useState(15);
  const f = (n, d = 2) => n.toFixed(d);
  const doses = [{
    label: "Epinephrine (CPR / Resuscitation)",
    value: `${f(wt * 0.01, 3)} mg`,
    secondary: `${f(wt * 0.1)} mL`,
    note: "Dilute 1 mg ampoule with 10 mL saline (1:10,000). Give 0.1 mL/kg."
  }, {
    label: "Atropine (Bradycardia)",
    value: `${f(wt * 0.02)} mg`,
    secondary: `${f(wt * 0.2)} mL`,
    note: "Dilute 0.5 mg in 5 mL saline (0.1 mg/mL). Min 0.1 mg, max 0.5 mg."
  }, {
    label: "Propofol 1% (Induction)",
    value: `${f(wt * 2.5, 1)} mg`,
    secondary: `${f(wt * 0.25)} mL`,
    note: "10 mg/mL solution drawn directly at 2.5 mg/kg."
  }, {
    label: "Ketamine (Induction / Sedation)",
    value: `${f(wt * 2, 1)} mg`,
    secondary: `${f(wt * 0.2)} mL`,
    note: "Dilute 50 mg/mL ampoule in 5 mL saline (10 mg/mL). Draw 2 mg/kg."
  }];
  return /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      overflowY: "auto",
      background: "var(--ab-sand)",
      display: "flex",
      flexDirection: "column"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      background: "var(--gradient-header)",
      padding: "54px 20px 18px",
      color: "#fff",
      display: "flex",
      alignItems: "center",
      gap: 14
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-arrow-left",
    style: {
      fontSize: 18
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: 19,
      lineHeight: 1.15
    }
  }, "Pediatric Emergency & Induction Dose Calculator")), /*#__PURE__*/React.createElement("div", {
    style: {
      padding: "18px 20px 28px",
      display: "flex",
      flexDirection: "column",
      gap: 18
    }
  }, /*#__PURE__*/React.createElement("p", {
    style: {
      margin: 0,
      fontFamily: "var(--font-body)",
      fontSize: 15,
      lineHeight: 1.45,
      color: "var(--ab-text-muted)"
    }
  }, "Calculates the mg and diluted mL doses of emergency resuscitation and anesthesia induction drugs by weight."), /*#__PURE__*/React.createElement(PremiumCard, {
    padding: "18px"
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      justifyContent: "space-between",
      alignItems: "baseline"
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: 17,
      color: "var(--ab-navy)"
    }
  }, "Child Body Weight"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 22,
      color: "var(--ab-navy)"
    }
  }, f(wt, 1), " kg")), /*#__PURE__*/React.createElement("input", {
    type: "range",
    min: "3",
    max: "40",
    step: "0.5",
    value: wt,
    onChange: e => setWt(parseFloat(e.target.value)),
    style: {
      width: "100%",
      marginTop: 16,
      accentColor: "var(--ab-teal)"
    }
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 900,
      fontSize: 20,
      color: "var(--ab-navy)",
      letterSpacing: "-0.3px"
    }
  }, "Calculated Doses"), /*#__PURE__*/React.createElement(PremiumCard, {
    padding: "18px"
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      flexDirection: "column",
      gap: 14
    }
  }, doses.map((d, i) => /*#__PURE__*/React.createElement(DoseRow, _extends({
    key: i
  }, d, {
    divider: i < doses.length - 1
  })))))));
}
window.CalculatorScreen = CalculatorScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/mobile_app/CalculatorScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/mobile_app/DrugsScreen.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
// Anesthesia Briefs — Drug Database list screen.
const DS_DRUG = window.AnesthesiaBriefsDesignSystem_a9434e;
function DrugsScreen() {
  const {
    SearchField,
    DrugListItem,
    CategoryChip
  } = DS_DRUG;
  const [cat, setCat] = React.useState(0);
  const cats = ["All", "Induction", "Muscle Relaxants", "Opioids", "Local"];
  const drugs = [{
    name: "Propofol",
    subtitle: "Class: Short-acting general anesthetic (GABA-A Agonist)",
    category: "induction"
  }, {
    name: "Ketamine",
    subtitle: "Dissociative anesthetic (NMDA Receptor Antagonist)",
    category: "induction"
  }, {
    name: "Rocuronium",
    subtitle: "Aminosteroid neuromuscular blocker",
    category: "neuromuscular",
    premium: true
  }, {
    name: "Fentanyl",
    subtitle: "Synthetic opioid analgesic",
    category: "analgesic"
  }, {
    name: "Bupivacaine",
    subtitle: "Amide local anesthetic",
    category: "local"
  }, {
    name: "Sugammadex",
    subtitle: "Selective relaxant binding agent (reversal)",
    category: "reversal",
    premium: true
  }, {
    name: "Norepinephrine",
    subtitle: "Vasopressor · α/β adrenergic agonist",
    category: "cardiovascular"
  }];
  return /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      overflowY: "auto",
      background: "var(--ab-sand)",
      padding: "54px 20px 24px"
    }
  }, /*#__PURE__*/React.createElement("h1", {
    style: {
      margin: 0,
      fontFamily: "var(--font-display)",
      fontWeight: 900,
      fontSize: 28,
      letterSpacing: "-0.6px",
      color: "var(--ab-navy)"
    }
  }, "Drug Database"), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "6px 0 18px",
      fontFamily: "var(--font-body)",
      fontSize: 15,
      color: "var(--ab-text-muted)"
    }
  }, "Clinically validated, comprehensive anesthesia drug monographs."), /*#__PURE__*/React.createElement(SearchField, {
    placeholder: "Search drug name, generic, or class\u2026"
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: 8,
      overflowX: "auto",
      margin: "16px 0 18px",
      paddingBottom: 4
    }
  }, cats.map((c, i) => /*#__PURE__*/React.createElement(CategoryChip, {
    key: i,
    selected: cat === i,
    onClick: () => setCat(i)
  }, c))), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      flexDirection: "column",
      gap: 12
    }
  }, drugs.map((d, i) => /*#__PURE__*/React.createElement(DrugListItem, _extends({
    key: i
  }, d, {
    onClick: () => {}
  })))));
}
window.DrugsScreen = DrugsScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/mobile_app/DrugsScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/mobile_app/HomeScreen.jsx
try { (() => {
// Anesthesia Briefs — Home screen recreation.
const DS = window.AnesthesiaBriefsDesignSystem_a9434e;
const AB_HOME_I18N = {
  en: {
    welcome: "Welcome, Dr. \uD83D\uDC4B",
    tagline: "Your Safe Harbor in Clinical Decisions",
    search: "Search drug, formula, or algorithm\u2026",
    spotTitle: "Cricoid Pressure in RSI",
    spotBody: "During Rapid Sequence Induction, cricoid pressure should be ~10 N before loss of consciousness, increasing to 30 N after.",
    q1: "Emergency Algorithms",
    q1d: "Critical life-saving trees",
    q2: "Clinical Calculators",
    q2d: "Pediatric & adult dosing",
    q3: "Regional Anesthesia",
    q3d: "US-guided block atlas",
    q4: "AI Clinical Assistant",
    q4d: "Decision support chat",
    crisisTitle: "Emergency Crisis Algorithms",
    crisisBody: "Step-by-step guidance for MH, LAST, anaphylaxis & CICO.",
    crisis: ["CICO", "Anaphylaxis", "Malignant Hyperthermia", "Hyperkalemia"],
    boardTitle: "Anesthesia Board Prep",
    boardBody: "Bilingual (TR/EN) EDAIC & ABA question banks, viva scenarios and referenced explanations.",
    preopEyebrow: "Preop",
    preopTitle: "Preoperative Safety Shortcuts",
    preopSub: "Commonly used preop scores & guidelines",
    preopNote: "ASA-PS, NPO fasting timer, difficult-airway screen and the ASRA anticoagulant table \u2014 one tap away."
  },
  tr: {
    welcome: "Merhaba, Dr. \uD83D\uDC4B",
    tagline: "Klinik kararlar\u0131n\u0131zda g\u00fcvence alt\u0131ndas\u0131n\u0131z",
    search: "\u0130la\u00e7, form\u00fcl veya algoritma aray\u0131n\u2026",
    spotTitle: "RS\u0130'de Krikoid Bas\u0131n\u00e7",
    spotBody: "H\u0131zl\u0131 seri ind\u00fcksiyonda krikoid bas\u0131n\u00e7 bilin\u00e7 kayb\u0131ndan \u00f6nce ~10 N, sonras\u0131nda 30 N olmal\u0131d\u0131r.",
    q1: "Acil Algoritmalar",
    q1d: "Hayat kurtaran karar a\u011fa\u00e7lar\u0131",
    q2: "Klinik Hesaplay\u0131c\u0131lar",
    q2d: "Pediatrik & eri\u015fkin doz",
    q3: "Rejyonel Anestezi",
    q3d: "USG-rehberli blok atlas\u0131",
    q4: "AI Klinik Asistan",
    q4d: "Karar destek sohbeti",
    crisisTitle: "Acil Kriz Algoritmalar\u0131",
    crisisBody: "MH, LAST, anafilaksi ve CICO i\u00e7in ad\u0131m ad\u0131m rehberlik.",
    crisis: ["CICO", "Anafilaksi", "Malign Hipertermi", "Hiperkalemi"],
    boardTitle: "Anestezi Board Haz\u0131rl\u0131k",
    boardBody: "\u0130ki dilli (TR/EN) EDAIC & ABA soru bankalar\u0131, viva senaryolar\u0131 ve referansl\u0131 a\u00e7\u0131klamalar.",
    preopEyebrow: "Preop",
    preopTitle: "Preoperatif G\u00fcvenlik K\u0131sayollar\u0131",
    preopSub: "S\u0131k kullan\u0131lan preop skorlar\u0131 & k\u0131lavuzlar",
    preopNote: "ASA-PS, NPO a\u00e7l\u0131k zamanlay\u0131c\u0131s\u0131, zor hava yolu taramas\u0131 ve ASRA antikoag\u00fclan tablosu \u2014 tek dokunu\u015fla."
  }
};
function HomeScreen({
  onTab,
  onOpenAlgo,
  onOpenRegional
}) {
  const {
    PremiumCard,
    Badge,
    SearchField,
    SectionHeader,
    ClinicalNote,
    Button
  } = DS;
  const _lang = new URLSearchParams(window.location.search).get("lang") === "tr" ? "tr" : "en";
  const t = AB_HOME_I18N[_lang];
  const quick = [{
    title: t.q1,
    desc: t.q1d,
    icon: "fa-triangle-exclamation",
    color: "var(--ab-red)",
    tab: 2
  }, {
    title: t.q2,
    desc: t.q2d,
    icon: "fa-calculator",
    color: "var(--ab-teal)",
    tab: 1
  }, {
    title: t.q3,
    desc: t.q3d,
    icon: "fa-layer-group",
    color: "var(--ab-navy)",
    action: "regional"
  }, {
    title: t.q4,
    desc: t.q4d,
    icon: "fa-wand-magic-sparkles",
    color: "var(--ab-gold)",
    tab: null
  }];
  return /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      overflowY: "auto",
      background: "var(--ab-sand)"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      background: "var(--gradient-header)",
      borderBottomLeftRadius: 32,
      borderBottomRightRadius: 32,
      padding: "58px 20px 26px",
      color: "#fff",
      boxShadow: "var(--shadow-header)",
      position: "relative",
      overflow: "hidden"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: "absolute",
      right: -50,
      top: -50,
      width: 160,
      height: 160,
      borderRadius: "50%",
      background: "rgba(194,162,103,0.07)"
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      justifyContent: "space-between",
      alignItems: "center"
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-gear",
    style: {
      fontSize: 20
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 17,
      letterSpacing: "-0.4px"
    }
  }, "Anesthesia", /*#__PURE__*/React.createElement("em", {
    style: {
      color: "var(--ab-gold)",
      fontStyle: "italic",
      fontWeight: 400
    }
  }, "Briefs")), /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-circle-user",
    style: {
      fontSize: 22
    }
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: 10,
      marginTop: 22
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 900,
      fontSize: 25,
      letterSpacing: "-0.5px"
    }
  }, t.welcome), /*#__PURE__*/React.createElement(Badge, {
    variant: "gold"
  }, "PRO")), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "4px 0 20px",
      fontFamily: "var(--font-body)",
      fontSize: 13,
      color: "rgba(255,255,255,0.75)"
    }
  }, t.tagline), /*#__PURE__*/React.createElement(SearchField, {
    onLight: false,
    placeholder: t.search
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      padding: "20px 20px 28px",
      display: "flex",
      flexDirection: "column",
      gap: 22
    }
  }, /*#__PURE__*/React.createElement(PremiumCard, {
    accent: "var(--ab-gold)"
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "flex-start",
      gap: 10
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-lightbulb",
    style: {
      color: "var(--ab-gold)",
      fontSize: 20,
      marginTop: 2
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 15,
      color: "var(--ab-navy)"
    }
  }, t.spotTitle), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "6px 0 0",
      fontFamily: "var(--font-body)",
      fontSize: 13,
      lineHeight: 1.45,
      color: "var(--ab-navy)"
    }
  }, t.spotBody)), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: 8,
      color: "var(--ab-text-muted)"
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-rotate-right",
    style: {
      color: "var(--ab-teal)"
    }
  }), /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-xmark"
  })))), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "grid",
      gridTemplateColumns: "1fr 1fr",
      gap: 12
    }
  }, quick.map((q, i) => /*#__PURE__*/React.createElement(PremiumCard, {
    key: i,
    accent: q.color,
    accentStrip: true,
    baseColor: "var(--surface-card-bright)",
    padding: "16px",
    onClick: () => q.action === "regional" ? onOpenRegional() : q.tab != null && onTab(q.tab)
  }, /*#__PURE__*/React.createElement("i", {
    className: `fa-solid ${q.icon}`,
    style: {
      color: q.color,
      fontSize: 26
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 800,
      fontSize: 14,
      color: "var(--ab-navy)",
      marginTop: 12
    }
  }, q.title), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-body)",
      fontSize: 11,
      color: "var(--ab-text-muted)",
      marginTop: 4
    }
  }, q.desc)))), /*#__PURE__*/React.createElement(PremiumCard, {
    accent: "var(--ab-red)",
    accentStrip: true,
    baseColor: "#FFF0F0",
    padding: "16px",
    onClick: () => onTab(2)
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: 12
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-bolt",
    style: {
      color: "var(--ab-red)",
      fontSize: 30
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 900,
      fontSize: 14,
      color: "var(--ab-red)",
      letterSpacing: "0.3px"
    }
  }, t.crisisTitle), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "3px 0 0",
      fontFamily: "var(--font-body)",
      fontSize: 11,
      color: "var(--ab-text-slate)"
    }
  }, t.crisisBody)), /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-arrow-right",
    style: {
      color: "var(--ab-red)"
    }
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "grid",
      gridTemplateColumns: "1fr 1fr",
      gap: 10,
      marginTop: 14
    }
  }, t.crisis.map(label => /*#__PURE__*/React.createElement("div", {
    key: label,
    onClick: e => {
      e.stopPropagation();
      onOpenAlgo();
    },
    style: {
      height: 38,
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      background: "#fff",
      border: "1.2px solid rgba(166,26,26,0.25)",
      borderRadius: 10,
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: 12,
      color: "var(--ab-red)"
    }
  }, label)))), /*#__PURE__*/React.createElement(PremiumCard, {
    accent: "var(--ab-gold)",
    baseColor: "var(--ab-navy)",
    padding: "16px",
    onClick: () => {}
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: 14
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-graduation-cap",
    style: {
      color: "var(--ab-gold)",
      fontSize: 24
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: 16,
      color: "var(--ab-gold)"
    }
  }, t.boardTitle), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "5px 0 0",
      fontFamily: "var(--font-body)",
      fontSize: 12,
      lineHeight: 1.4,
      color: "rgba(255,255,255,0.85)"
    }
  }, t.boardBody)), /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-arrow-right",
    style: {
      color: "var(--ab-gold)"
    }
  }))), /*#__PURE__*/React.createElement(SectionHeader, {
    eyebrow: t.preopEyebrow,
    title: t.preopTitle,
    subtitle: t.preopSub
  }), /*#__PURE__*/React.createElement(ClinicalNote, {
    tone: "info"
  }, t.preopNote)));
}
window.HomeScreen = HomeScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/mobile_app/HomeScreen.jsx", error: String((e && e.message) || e) }); }

// ui_kits/mobile_app/PhoneFrame.jsx
try { (() => {
// Anesthesia Briefs — iPhone frame for the mobile UI kit.
// Black bezel, status bar, scrollable screen window. Children render inside.
function PhoneFrame({
  children,
  statusDark = false
}) {
  const statusColor = statusDark ? "#fff" : "var(--ab-navy)";
  return /*#__PURE__*/React.createElement("div", {
    style: {
      width: 390,
      height: 844,
      background: "#0B0B0C",
      borderRadius: 54,
      padding: 11,
      boxShadow: "0 40px 90px rgba(13,30,54,0.34), 0 8px 24px rgba(0,0,0,0.2)",
      position: "relative",
      flex: "none"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: "100%",
      height: "100%",
      background: "var(--ab-sand)",
      borderRadius: 44,
      overflow: "hidden",
      position: "relative",
      display: "flex",
      flexDirection: "column"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: "absolute",
      top: 0,
      left: 0,
      right: 0,
      height: 44,
      zIndex: 50,
      display: "flex",
      alignItems: "center",
      justifyContent: "space-between",
      padding: "0 28px",
      pointerEvents: "none"
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: 14,
      color: statusColor
    }
  }, "9:41"), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: 6,
      alignItems: "center",
      color: statusColor,
      fontSize: 13
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-signal"
  }), /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-wifi"
  }), /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-battery-full"
  }))), /*#__PURE__*/React.createElement("div", {
    style: {
      position: "absolute",
      top: 11,
      left: "50%",
      transform: "translateX(-50%)",
      width: 116,
      height: 32,
      background: "#0B0B0C",
      borderRadius: 20,
      zIndex: 60
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      minHeight: 0,
      display: "flex",
      flexDirection: "column"
    }
  }, children)));
}
window.PhoneFrame = PhoneFrame;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/mobile_app/PhoneFrame.jsx", error: String((e && e.message) || e) }); }

// ui_kits/mobile_app/RegionalScreen.jsx
try { (() => {
// Anesthesia Briefs — Regional Anesthesia atlas (hub + block detail).
// Hub lists nerve blocks by region; selecting one opens an atlas detail with
// Anatomy / Sonoanatomy tabs, probe-needle approach, and LA dosing.
const DS_REG = window.AnesthesiaBriefsDesignSystem_a9434e;
const REG_BLOCKS = [{
  id: "interscalene",
  name: "Interscalene",
  region: "upper",
  target: "C5–C6 roots (brachial plexus)",
  use: "Shoulder & proximal humerus",
  la: "15–20 mL · 0.5% bupivacaine",
  icon: "fa-hand",
  pearls: "Phrenic nerve palsy in ~100% — avoid in severe respiratory disease."
}, {
  id: "supraclavicular",
  name: "Supraclavicular",
  region: "upper",
  target: "Trunks/divisions, lateral to subclavian a.",
  use: "Elbow, forearm & hand",
  la: "20–25 mL · 0.5% bupivacaine",
  icon: "fa-hand",
  pearls: "“Corner pocket” target; keep needle in-plane, watch the pleura."
}, {
  id: "infraclavicular",
  name: "Infraclavicular",
  region: "upper",
  target: "Cords around axillary a., deep to pec. minor",
  use: "Elbow, forearm & hand",
  la: "20–30 mL · 0.5% bupivacaine",
  icon: "fa-hand",
  pearls: "Aim for a U-shaped spread posterior to the axillary artery."
}, {
  id: "femoral",
  name: "Femoral",
  region: "lower",
  target: "Femoral n., lateral to femoral a.",
  use: "Anterior thigh, knee, femur",
  la: "15–20 mL · 0.25–0.5% bupivacaine",
  icon: "fa-person-walking",
  pearls: "Hyperechoic n. under fascia iliaca, lateral to the artery."
}, {
  id: "adductor_canal",
  name: "Adductor Canal",
  region: "lower",
  target: "Saphenous n. in the canal",
  use: "Knee — motor-sparing analgesia",
  la: "10–15 mL · 0.25% bupivacaine",
  icon: "fa-person-walking",
  pearls: "Preserves quadriceps strength vs femoral — better for ambulation."
}, {
  id: "popliteal",
  name: "Popliteal Sciatic",
  region: "lower",
  target: "Sciatic n. above its bifurcation",
  use: "Foot & ankle",
  la: "20–30 mL · 0.5% bupivacaine",
  icon: "fa-person-walking",
  pearls: "Block above the bifurcation for both tibial & common peroneal."
}, {
  id: "esp",
  name: "Erector Spinae (ESP)",
  region: "trunk",
  target: "Fascial plane deep to erector spinae",
  use: "Thoracic & abdominal wall",
  la: "20–30 mL · 0.25% bupivacaine",
  icon: "fa-shield-heart",
  pearls: "Inject onto the transverse process; craniocaudal spread."
}, {
  id: "tap",
  name: "Transversus Abdominis (TAP)",
  region: "trunk",
  target: "Plane between IO & TA muscles",
  use: "Anterior abdominal wall",
  la: "15–20 mL per side",
  icon: "fa-shield-heart",
  pearls: "Somatic analgesia only — combine with multimodal for visceral pain."
}];
const REG_REGIONS = {
  all: "All Blocks",
  upper: "Upper Limb",
  lower: "Lower Limb",
  trunk: "Truncal"
};
const REG_REGIONS_TR = {
  all: "Tüm Bloklar",
  upper: "Üst Ekstremite",
  lower: "Alt Ekstremite",
  trunk: "Gövde"
};
function RegionalScreen({
  onBack
}) {
  const {
    SearchField,
    CategoryChip,
    ClinicalNote,
    PremiumCard,
    Badge
  } = DS_REG;
  const [region, setRegion] = React.useState("all");
  const [block, setBlock] = React.useState(null);
  const [tab, setTab] = React.useState("anatomy");

  // ---- Block detail view ----
  if (block) {
    const b = REG_BLOCKS.find(x => x.id === block);
    return /*#__PURE__*/React.createElement("div", {
      style: {
        flex: 1,
        overflowY: "auto",
        background: "var(--ab-sand)",
        display: "flex",
        flexDirection: "column"
      }
    }, /*#__PURE__*/React.createElement("div", {
      style: {
        background: "var(--gradient-header)",
        padding: "54px 20px 20px",
        color: "#fff",
        position: "relative",
        overflow: "hidden"
      }
    }, /*#__PURE__*/React.createElement("div", {
      style: {
        position: "absolute",
        right: -50,
        top: -40,
        width: 150,
        height: 150,
        borderRadius: "50%",
        background: "rgba(74,124,140,0.18)"
      }
    }), /*#__PURE__*/React.createElement("div", {
      style: {
        display: "flex",
        alignItems: "center",
        gap: 14,
        position: "relative"
      }
    }, /*#__PURE__*/React.createElement("i", {
      className: "fa-solid fa-arrow-left",
      style: {
        fontSize: 18,
        cursor: "pointer"
      },
      onClick: () => setBlock(null)
    }), /*#__PURE__*/React.createElement("span", {
      style: {
        fontFamily: "var(--font-display)",
        fontWeight: 700,
        fontSize: 13,
        letterSpacing: "0.5px",
        textTransform: "uppercase",
        color: "rgba(255,255,255,0.65)"
      }
    }, "Regional Atlas")), /*#__PURE__*/React.createElement("h1", {
      style: {
        margin: "12px 0 4px",
        fontFamily: "var(--font-display)",
        fontWeight: 900,
        fontSize: 25,
        letterSpacing: "-0.5px",
        position: "relative"
      }
    }, b.name, " Block"), /*#__PURE__*/React.createElement("p", {
      style: {
        margin: 0,
        fontFamily: "var(--font-body)",
        fontSize: 13,
        color: "rgba(255,255,255,0.78)",
        position: "relative"
      }
    }, b.use)), /*#__PURE__*/React.createElement("div", {
      style: {
        padding: "18px 20px 28px",
        display: "flex",
        flexDirection: "column",
        gap: 16
      }
    }, /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("div", {
      style: {
        display: "flex",
        gap: 8,
        marginBottom: 12
      }
    }, [["anatomy", "Anatomy"], ["sono", "Sonoanatomy"]].map(([k, lbl]) => /*#__PURE__*/React.createElement(CategoryChip, {
      key: k,
      selected: tab === k,
      onClick: () => setTab(k)
    }, lbl))), /*#__PURE__*/React.createElement("div", {
      style: {
        borderRadius: "var(--radius-lg)",
        overflow: "hidden",
        position: "relative",
        background: "#fff",
        border: "1px solid var(--border-subtle)",
        boxShadow: "var(--shadow-card)"
      }
    }, /*#__PURE__*/React.createElement("img", {
      src: `assets/atlas/${b.id}_${tab === "sono" ? "sono" : "anatomy"}.png`,
      alt: `${b.name} ${tab}`,
      style: {
        width: "100%",
        display: "block"
      }
    }))), /*#__PURE__*/React.createElement(PremiumCard, {
      padding: "16px"
    }, /*#__PURE__*/React.createElement("div", {
      style: {
        display: "flex",
        flexDirection: "column",
        gap: 12
      }
    }, /*#__PURE__*/React.createElement(Row, {
      icon: "fa-bullseye",
      color: "var(--ab-teal)",
      label: "Target",
      value: b.target
    }), /*#__PURE__*/React.createElement("div", {
      style: {
        height: 1,
        background: "rgba(27,54,93,0.08)"
      }
    }), /*#__PURE__*/React.createElement(Row, {
      icon: "fa-syringe",
      color: "var(--ab-navy)",
      label: "Local Anesthetic",
      value: b.la
    }))), /*#__PURE__*/React.createElement(ClinicalNote, {
      tone: "info"
    }, b.pearls), /*#__PURE__*/React.createElement(ClinicalNote, {
      tone: "warning"
    }, "Aspirate before and during injection. Stop immediately for paresthesia, high resistance, or any sign of LAST.")));
  }

  // ---- Hub view ----
  const shown = REG_BLOCKS.filter(b => region === "all" || b.region === region);
  const accentFor = r => r === "upper" ? "var(--ab-teal)" : r === "lower" ? "var(--ab-navy)" : "var(--ab-gold)";
  return /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      overflowY: "auto",
      background: "var(--ab-sand)",
      display: "flex",
      flexDirection: "column"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      background: "var(--gradient-header)",
      padding: "54px 20px 22px",
      color: "#fff",
      borderBottomLeftRadius: 32,
      borderBottomRightRadius: 32,
      position: "relative",
      overflow: "hidden"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: "absolute",
      right: -40,
      top: -50,
      width: 160,
      height: 160,
      borderRadius: "50%",
      background: "rgba(74,124,140,0.16)"
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: 14,
      position: "relative"
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-arrow-left",
    style: {
      fontSize: 18,
      cursor: "pointer"
    },
    onClick: onBack
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: "var(--font-display)",
      fontWeight: 700,
      fontSize: 13,
      letterSpacing: "0.5px",
      textTransform: "uppercase",
      color: "rgba(255,255,255,0.65)"
    }
  }, "Visual Atlas")), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "center",
      gap: 12,
      marginTop: 14,
      position: "relative"
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: 46,
      height: 46,
      borderRadius: 14,
      background: "rgba(255,255,255,0.12)",
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      fontSize: 22,
      color: "var(--ab-gold)"
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: "fa-solid fa-layer-group"
  })), /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("h1", {
    style: {
      margin: 0,
      fontFamily: "var(--font-display)",
      fontWeight: 900,
      fontSize: 24,
      letterSpacing: "-0.5px"
    }
  }, "Regional Anesthesia"), /*#__PURE__*/React.createElement("p", {
    style: {
      margin: "2px 0 0",
      fontFamily: "var(--font-body)",
      fontSize: 13,
      color: "rgba(255,255,255,0.78)"
    }
  }, "Ultrasound-guided nerve block atlas")))), /*#__PURE__*/React.createElement("div", {
    style: {
      padding: "18px 20px 28px"
    }
  }, /*#__PURE__*/React.createElement(SearchField, {
    placeholder: "Search a block or nerve\u2026"
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      gap: 8,
      overflowX: "auto",
      margin: "16px 0 18px",
      paddingBottom: 4
    }
  }, Object.keys(REG_REGIONS).map(r => /*#__PURE__*/React.createElement(CategoryChip, {
    key: r,
    selected: region === r,
    onClick: () => setRegion(r)
  }, REG_REGIONS[r]))), /*#__PURE__*/React.createElement("div", {
    style: {
      display: "grid",
      gridTemplateColumns: "1fr 1fr",
      gap: 12
    }
  }, shown.map(b => {
    const ac = accentFor(b.region);
    return /*#__PURE__*/React.createElement("div", {
      key: b.id,
      onClick: () => {
        setBlock(b.id);
        setTab("anatomy");
      },
      style: {
        cursor: "pointer",
        background: "var(--surface-card-bright)",
        border: "1.2px solid rgba(27,54,93,0.08)",
        borderRadius: "var(--radius-lg)",
        padding: 14,
        position: "relative",
        overflow: "hidden",
        boxShadow: "var(--shadow-card)"
      }
    }, /*#__PURE__*/React.createElement("div", {
      style: {
        position: "absolute",
        left: 0,
        top: 0,
        bottom: 0,
        width: 4,
        background: ac
      }
    }), /*#__PURE__*/React.createElement("div", {
      style: {
        width: 38,
        height: 38,
        borderRadius: 11,
        background: `color-mix(in srgb, ${ac} 14%, transparent)`,
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        color: ac,
        fontSize: 17,
        marginBottom: 10
      }
    }, /*#__PURE__*/React.createElement("i", {
      className: `fa-solid ${b.icon}`
    })), /*#__PURE__*/React.createElement("div", {
      style: {
        fontFamily: "var(--font-display)",
        fontWeight: 800,
        fontSize: 14,
        color: "var(--ab-navy)",
        lineHeight: 1.2
      }
    }, b.name), /*#__PURE__*/React.createElement("div", {
      style: {
        fontFamily: "var(--font-body)",
        fontSize: 11,
        color: "var(--ab-text-muted)",
        marginTop: 4
      }
    }, b.use));
  }))));
}
function Row({
  icon,
  color,
  label,
  value
}) {
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: "flex",
      alignItems: "flex-start",
      gap: 12
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      color,
      fontSize: 16,
      marginTop: 1,
      width: 18,
      textAlign: "center",
      flex: "none"
    }
  }, /*#__PURE__*/React.createElement("i", {
    className: `fa-solid ${icon}`
  })), /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-display)",
      fontSize: 11,
      fontWeight: 800,
      letterSpacing: "0.5px",
      textTransform: "uppercase",
      color: "var(--ab-text-muted)"
    }
  }, label), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: "var(--font-body)",
      fontSize: 14,
      color: "var(--ab-navy)",
      marginTop: 2
    }
  }, value)));
}
window.RegionalScreen = RegionalScreen;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/mobile_app/RegionalScreen.jsx", error: String((e && e.message) || e) }); }

__ds_ns.Badge = __ds_scope.Badge;

__ds_ns.BottomNav = __ds_scope.BottomNav;

__ds_ns.Button = __ds_scope.Button;

__ds_ns.CategoryChip = __ds_scope.CategoryChip;

__ds_ns.ClinicalNote = __ds_scope.ClinicalNote;

__ds_ns.DoseRow = __ds_scope.DoseRow;

__ds_ns.DrugListItem = __ds_scope.DrugListItem;

__ds_ns.PremiumCard = __ds_scope.PremiumCard;

__ds_ns.SearchField = __ds_scope.SearchField;

__ds_ns.SectionHeader = __ds_scope.SectionHeader;

})();
