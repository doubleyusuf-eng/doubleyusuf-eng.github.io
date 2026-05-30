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
document.addEventListener("DOMContentLoaded", function() {
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
        fetch('https://ipapi.co/json/')
            .then(response => response.json())
            .then(data => {
                const country = data.country_code;
                if (country === 'TR') {
                    changeLanguage('tr');
                } else {
                    changeLanguage('en');
                }
            })
            .catch(() => {
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
            mainPhoneScreen.src = 'assets/screenshots/Kişiselleştirilmiş Karşılama.jpg';
            secondaryPhoneScreen.src = 'assets/screenshots/Personalized welcome.jpg';
        } else {
            mainPhoneScreen.src = 'assets/screenshots/Personalized welcome.jpg';
            secondaryPhoneScreen.src = 'assets/screenshots/Kişiselleştirilmiş Karşılama.jpg';
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
            imgEl.src = currentLang === 'tr' ? 'assets/screenshots/Kişiselleştirilmiş Karşılama.jpg' : 'assets/screenshots/Personalized welcome.jpg';
        } else if (screenKey === 'screen-algorithms') {
            imgEl.src = currentLang === 'tr' ? 'assets/screenshots/Algoritmalar.jpg' : 'assets/screenshots/Algorithms.jpg';
        } else if (screenKey === 'screen-calc') {
            imgEl.src = currentLang === 'tr' ? 'assets/screenshots/Hesaplayıcı.jpg' : 'assets/screenshots/Calculator.jpg';
        } else if (screenKey === 'screen-drugs') {
            imgEl.src = currentLang === 'tr' ? 'assets/screenshots/İlaçlar.jpg' : 'assets/screenshots/Drugs.jpg';
        } else if (screenKey === 'screen-board') {
            imgEl.src = currentLang === 'tr' ? 'assets/screenshots/Board hazirlik.jpg' : 'assets/screenshots/Board prep.jpg';
        } else if (screenKey === 'screen-spot') {
            imgEl.src = currentLang === 'tr' ? 'assets/screenshots/Spot bilgiler.jpg' : 'assets/screenshots/Spot notes.jpg';
        } else if (screenKey === 'screen-ai') {
            imgEl.src = currentLang === 'tr' ? 'assets/screenshots/Yapay zeka.jpg' : 'assets/screenshots/Artificial intelligence.jpg';
        }
        imgEl.style.opacity = '1';
    }, 150);
}
