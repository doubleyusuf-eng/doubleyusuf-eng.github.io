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
let canvas2D = null, ctx2d = null;
let scene = null, camera = null, renderer = null;
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

function initLeaderboard() {
    const stored = localStorage.getItem(LEADERBOARD_STORAGE_KEY);
    if (stored) {
        try {
            leaderboard = JSON.parse(stored);
        } catch (e) {
            leaderboard = [];
        }
    } else {
        leaderboard = [];
    }
}

function saveLeaderboard() {
    leaderboard.sort((a, b) => b.score - a.score);
    leaderboard = leaderboard.slice(0, 10);
    localStorage.setItem(LEADERBOARD_STORAGE_KEY, JSON.stringify(leaderboard));
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
        placeholder.innerText = language === 'tr' 
            ? 'Henüz kaydedilmiş skor bulunmuyor. İlk skoru sen yap!' 
            : 'No scores recorded yet. Be the first to set a high score!';
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
        
        const isCurrentPlayer = (entry.name === playerNick && entry.score === score && score > 0);
        if (isCurrentPlayer) {
            item.style.background = 'rgba(252, 211, 77, 0.2)';
            item.style.color = '#FCD34D';
            item.style.fontWeight = '800';
        } else {
            item.style.background = 'rgba(255,255,255,0.02)';
        }
        
        let rankStr = `${index + 1}.`;
        if (index === 0) rankStr = '🥇';
        else if (index === 1) rankStr = '🥈';
        else if (index === 2) rankStr = '🥉';
        
        const flag = flags[entry.country] || '🏳️';
        
        item.innerHTML = `<span>${rankStr} ${flag} ${entry.name}</span><span>${entry.score}</span>`;
        listContainer.appendChild(item);
    });
}

// Aiming parameters
let aimPitch = 40; // Vertical angle (degrees)
let aimYaw = 0;     // Horizontal angle (degrees)
let throwPower = 60; // Launch force percentage

// 3D Objects References
let anesthesiologistGroup = null, surgeonGroup = null, tableMesh = null;
let drapeMesh = null, ivPoleMesh = null, monitorMesh = null;
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
        instructions: [
            "Laringoskobu fırlatmak için 'Fırlat!' butonuna basın.",
            "Dikey Açı (Pitch) ve Yatay Yön (Yaw) ayarlarını kaydırıcılarla ayarlayın.",
            "Kafadan vuruşlar (Headshot) fazladan 250 puan kazandırır.",
            "Karşı rüzgara (laminer akış) dikkat edin, havada sapmaya yol açar.",
            "Hastabaşı monitörüne çarparsanız yansıyarak fırlayacaktır.",
            "Arka arkaya isabetler puan çarpanını (Combo) tetikler!"
        ],
        surgeonHits: [
            "Aaa! Macintosh 4 mü o?!",
            "Miyorelaksan yapıldı mı anestezi?!",
            "Tablayı biraz kaldırın!",
            "Işık çok az, göremiyorum!",
            "Bu hasta kımıldıyor!",
            "Biz ameliyatı bitiriyoruz!",
            "Dikişlerim koptu!",
            "Kim fırlattı bunu?!"
        ],
        surgeonMisses: [
            "Hedefin dikişlerimden de kötü!",
            "Anestezi uyuyor mu arkada?",
            "Iskaladın, anestezist bey!",
            "Eter ekranı beni korur!",
            "Uyanamadın galiba daha?",
            "Bari propofol fırlatsaydın!"
        ]
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
        instructions: [
            "Press 'Throw!' button to launch the laryngoscope.",
            "Adjust Pitch (Vertical) and Yaw (Horizontal) angles with sliders.",
            "Headshots give a bonus +250 points.",
            "Watch out for the laminar flow wind, it deflects the blade.",
            "Hitting the patient monitor causes a fast ricochet.",
            "Successive hits trigger high score combo multipliers!"
        ],
        surgeonHits: [
            "Ouch! Is that a Macintosh 4 blade?!",
            "Was muscle relaxant given, anesthesia?!",
            "Lower the table please!",
            "Not enough light here!",
            "This patient is moving!",
            "We are closing now!",
            "My sutures are ruined!",
            "Who threw that?!"
        ],
        surgeonMisses: [
            "Your aim is worse than my suturing!",
            "Is anesthesia sleeping back there?",
            "You missed, anesthesia!",
            "The ether screen protects me!",
            "Time to wake up, doctor!",
            "At least throw some propofol next time!"
        ]
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
        } 
        else if (type === 'hit') {
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
        }
        else if (type === 'headshot') {
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
        }
        else if (type === 'miss') {
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
        }
        else if (type === 'monitor') {
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
        
        const mat = new THREE.MeshBasicMaterial({ color: color, transparent: true, opacity: 1.0 });
        const geom = new THREE.BoxGeometry(size, size, size);
        const mesh = new THREE.Mesh(geom, mat);
        
        mesh.position.set(pos.x, pos.y, pos.z);
        
        let angle = Math.random() * Math.PI * 2;
        let speed = Math.random() * 0.15 + 0.05;
        
        const pObj = {
            mesh: mesh,
            vel: new THREE.Vector3(
                Math.cos(angle) * speed,
                Math.random() * 0.15 + 0.02,
                Math.sin(angle) * speed
            ),
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
    } 
    else if (gameState === 'gameover') {
        document.getElementById('overlay-title').innerText = textDict.gameOverTitle;
        document.getElementById('overlay-subtitle').innerText = textDict.gameOverSubtitle;
        document.getElementById('btn-start-text').innerText = textDict.restartBtn;
    }
    else if (gameState === 'levelup') {
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
    windX = (Math.random() * 0.08 - 0.04);
    windZ = (Math.random() * 0.04 - 0.02);
    updateLaminarWindHUD();
}

// 3D Scene Initialization
function build3DScene() {
    if (typeof THREE === 'undefined') return;
    
    const container = document.querySelector('.canvas-container');
    const width = container.clientWidth;
    const height = container.clientHeight;
    
    renderer = new THREE.WebGLRenderer({ canvas: document.getElementById('gameCanvas3D'), antialias: true });
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
    const floorMat = new THREE.MeshStandardMaterial({ color: 0x111827, roughness: 0.8 });
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
    const metalMat = new THREE.MeshStandardMaterial({ color: 0x64748b, metalness: 0.9, roughness: 0.1 });
    const tableBase = new THREE.Mesh(baseGeom, metalMat);
    tableBase.position.y = 0.6;
    tableBase.castShadow = true;
    tableGroup.add(tableBase);
    
    const padGeom = new THREE.BoxGeometry(4.8, 0.25, 1.3);
    const padMat = new THREE.MeshStandardMaterial({ color: 0x334155, roughness: 0.6 });
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
    const clothMat = new THREE.MeshStandardMaterial({ color: 0x3b82f6, transparent: true, opacity: 0.65, roughness: 0.9 });
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
    
    const bagMat = new THREE.MeshStandardMaterial({ color: 0xffffff, transparent: true, opacity: 0.45, roughness: 0.1 });
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
    const monitorCasing = new THREE.Mesh(new THREE.BoxGeometry(1.2, 0.8, 0.3), new THREE.MeshStandardMaterial({ color: 0x334155, roughness: 0.7 }));
    monitorCasing.castShadow = true;
    monGroup.add(monitorCasing);
    
    const lcdScreen = new THREE.Mesh(new THREE.BoxGeometry(1.05, 0.65, 0.04), new THREE.MeshStandardMaterial({ color: 0x0f172a, emissive: 0x10b981, emissiveIntensity: 0.15 }));
    lcdScreen.position.set(0, 0, 0.14);
    monGroup.add(lcdScreen);
    
    monitorMesh = monGroup;
    monitorMesh.position.set(-6.0, 2.7, -0.8);
    scene.add(monitorMesh);
    
    // Sitting Anesthesiologist & Stool
    anesthesiologistGroup = new THREE.Group();
    const stoolGroup = new THREE.Group();
    const stoolSeat = new THREE.Mesh(new THREE.CylinderGeometry(0.4, 0.4, 0.15, 12), new THREE.MeshStandardMaterial({ color: 0x0f172a, roughness: 0.5 }));
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
    const bodyMat = new THREE.MeshStandardMaterial({ color: 0x065f46, roughness: 0.8 });
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
    
    const skinMat = new THREE.MeshStandardMaterial({ color: 0xfbcfe8, roughness: 0.8 });
    const playerHead = new THREE.Mesh(new THREE.SphereGeometry(0.28, 16, 16), skinMat);
    playerHead.position.y = 2.0;
    playerHead.castShadow = true;
    playerGroup.add(playerHead);
    
    const surgicalCap = new THREE.Mesh(new THREE.SphereGeometry(0.29, 12, 12, 0, Math.PI*2, 0, Math.PI/2), new THREE.MeshStandardMaterial({ color: 0x2563eb, roughness: 0.9 }));
    surgicalCap.position.y = 2.05;
    playerGroup.add(surgicalCap);
    
    const mask = new THREE.Mesh(new THREE.BoxGeometry(0.34, 0.18, 0.16), new THREE.MeshStandardMaterial({ color: 0x60a5fa, roughness: 0.9 }));
    mask.position.set(0, 1.95, 0.2);
    playerGroup.add(mask);
    
    anesthesiologistGroup.add(playerGroup);
    anesthesiologistGroup.position.set(-7.5, 0, 0);
    scene.add(anesthesiologistGroup);
    
    // Anesthesia Machine (placed at x = -5.0, y = 0, z = -1.2) - Bizden tarafta
    const machineGroup = new THREE.Group();
    const cabinetBody = new THREE.Mesh(new THREE.BoxGeometry(0.9, 1.4, 0.7), new THREE.MeshStandardMaterial({ color: 0x334155, roughness: 0.5 }));
    cabinetBody.position.y = 0.7;
    cabinetBody.castShadow = true;
    cabinetBody.receiveShadow = true;
    machineGroup.add(cabinetBody);
    
    // Drawers handles (silver metal)
    const handleBarGeom = new THREE.BoxGeometry(0.5, 0.05, 0.05);
    const metalHandleMat = new THREE.MeshStandardMaterial({ color: 0xe2e8f0, metalness: 0.8 });
    for (let dy = 0.3; dy <= 0.9; dy += 0.3) {
        const handle = new THREE.Mesh(handleBarGeom, metalHandleMat);
        handle.position.set(0, dy, 0.36);
        machineGroup.add(handle);
    }
    
    // Vaporizers shelf and vaporizers
    const topShelf = new THREE.Mesh(new THREE.BoxGeometry(0.85, 0.05, 0.6), metalMat);
    topShelf.position.y = 1.425;
    machineGroup.add(topShelf);
    
    const sevoVap = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.12, 0.3, 8), new THREE.MeshStandardMaterial({ color: 0xf59e0b, roughness: 0.3 }));
    sevoVap.position.set(-0.2, 1.6, 0.15);
    machineGroup.add(sevoVap);
    
    const isoVap = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.12, 0.3, 8), new THREE.MeshStandardMaterial({ color: 0x6d28d9, roughness: 0.3 }));
    isoVap.position.set(0.2, 1.6, 0.15);
    machineGroup.add(isoVap);
    
    // Soda Lime Canister on side
    const limeAbsorber = new THREE.Mesh(new THREE.CylinderGeometry(0.15, 0.15, 0.4, 8), new THREE.MeshStandardMaterial({ color: 0xf1f5f9, roughness: 0.6 }));
    limeAbsorber.position.set(0.35, 0.9, 0.3);
    machineGroup.add(limeAbsorber);
    
    // Small ventilator screen on bracket
    const ventMount = new THREE.Mesh(new THREE.CylinderGeometry(0.02, 0.02, 0.5, 8), metalMat);
    ventMount.position.set(-0.3, 1.65, -0.1);
    machineGroup.add(ventMount);
    
    const ventCasing = new THREE.Mesh(new THREE.BoxGeometry(0.4, 0.3, 0.1), new THREE.MeshStandardMaterial({ color: 0x334155 }));
    ventCasing.position.set(-0.3, 1.9, -0.1);
    machineGroup.add(ventCasing);
    
    const ventScreen = new THREE.Mesh(new THREE.BoxGeometry(0.34, 0.24, 0.02), new THREE.MeshStandardMaterial({ color: 0x020617, emissive: 0x0ea5e9, emissiveIntensity: 0.4 }));
    ventScreen.position.set(-0.3, 1.9, -0.04);
    machineGroup.add(ventScreen);
    
    // Tubes
    const loopTube = new THREE.Mesh(new THREE.TorusGeometry(0.25, 0.03, 6, 12, Math.PI), new THREE.MeshStandardMaterial({ color: 0x60a5fa, transparent: true, opacity: 0.8 }));
    loopTube.rotation.y = Math.PI / 2;
    loopTube.position.set(0.35, 0.7, 0.35);
    machineGroup.add(loopTube);
    
    machineGroup.position.set(-5.0, 0, -1.2);
    scene.add(machineGroup);
    
    // Standing Surgeon (Target)
    surgeonGroup = new THREE.Group();
    const surgeonBody = new THREE.Mesh(new THREE.CylinderGeometry(0.35, 0.4, 1.4, 12), new THREE.MeshStandardMaterial({ color: 0x1d4ed8, roughness: 0.8 }));
    surgeonBody.position.y = 0.7;
    surgeonBody.castShadow = true;
    surgeonGroup.add(surgeonBody);
    
    const surgeonHead = new THREE.Mesh(new THREE.SphereGeometry(0.28, 16, 16), new THREE.MeshStandardMaterial({ color: 0xffd2b2, roughness: 0.8 }));
    surgeonHead.position.y = 1.6;
    surgeonHead.castShadow = true;
    surgeonGroup.add(surgeonHead);
    
    const surgeonCap = new THREE.Mesh(new THREE.SphereGeometry(0.29, 12, 12, 0, Math.PI*2, 0, Math.PI/2), new THREE.MeshStandardMaterial({ color: 0x047857, roughness: 0.9 }));
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
    const handleMat = new THREE.MeshStandardMaterial({ color: 0xe2e8f0, metalness: 0.9, roughness: 0.2 });
    
    const handle = new THREE.Mesh(new THREE.CylinderGeometry(0.06, 0.06, 0.45, 8), handleMat);
    handle.castShadow = true;
    scopeGroup.add(handle);
    
    const bladeShape = new THREE.Shape();
    bladeShape.moveTo(0, 0.04);
    bladeShape.quadraticCurveTo(0.2, 0.18, 0.45, 0.12);
    bladeShape.quadraticCurveTo(0.25, 0.05, 0, -0.04);
    bladeShape.closePath();
    
    const extrudeSettings = { depth: 0.05, bevelEnabled: true, bevelSegments: 2, steps: 1, bevelSize: 0.01, bevelThickness: 0.01 };
    const blade = new THREE.Mesh(new THREE.ExtrudeGeometry(bladeShape, extrudeSettings), handleMat);
    blade.position.set(0, 0.18, -0.025);
    blade.castShadow = true;
    scopeGroup.add(blade);
    
    const fiber = new THREE.Mesh(new THREE.CylinderGeometry(0.008, 0.008, 0.35, 4), new THREE.MeshStandardMaterial({ color: 0xffffff, emissive: 0xfbcb24, emissiveIntensity: 0.8 }));
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
        
        tempVel.y -= (GRAVITY * 0.016);
        tempVel.x += (windX * 0.01);
        tempVel.z += (windZ * 0.01);
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

// 2D Draw Scene Function (Canvas drawing)
function draw2DScene() {
    if (!ctx2d) return;
    
    // 1. Clear background
    const grad = ctx2d.createRadialGradient(640, 200, 50, 640, 360, 600);
    grad.addColorStop(0, '#1E293B');
    grad.addColorStop(1, '#090F1E');
    ctx2d.fillStyle = grad;
    ctx2d.fillRect(0, 0, 1280, 720);
    
    // 2. Draw Floor tiles
    ctx2d.strokeStyle = 'rgba(255, 255, 255, 0.03)';
    ctx2d.lineWidth = 1;
    for (let i = 600; i <= 720; i += 20) {
        ctx2d.beginPath();
        ctx2d.moveTo(0, i);
        ctx2d.lineTo(1280, i);
        ctx2d.stroke();
    }
    
    // Draw depth lines on the floor
    for (let gx = -10; gx <= 10; gx += 2) {
        const p1 = project3DTo2D(gx, 0, -2.0);
        const p2 = project3DTo2D(gx, 0, 2.0);
        ctx2d.beginPath();
        ctx2d.moveTo(p1.x, p1.y);
        ctx2d.lineTo(p2.x, p2.y);
        ctx2d.stroke();
    }
    
    // 3. Patient Monitor (Background depth z = -0.8) - Bizden tarafta
    const monPos = project3DTo2D(-6.0, 2.7, -0.8);
    ctx2d.strokeStyle = '#475569';
    ctx2d.lineWidth = 6;
    ctx2d.beginPath();
    ctx2d.moveTo(monPos.x, monPos.y);
    ctx2d.lineTo(0, monPos.y); // Wall mount extending to left edge
    ctx2d.stroke();
    
    ctx2d.fillStyle = '#334155';
    ctx2d.fillRect(monPos.x - 50, monPos.y - 35, 100, 70);
    ctx2d.strokeStyle = '#64748b';
    ctx2d.lineWidth = 2;
    ctx2d.strokeRect(monPos.x - 50, monPos.y - 35, 100, 70);
    
    const isFlashing = monitorFlashTimer > 0;
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
    
    // 3.5. Anesthesia Machine (placed at x = -5.0, y = 0, z = -1.2) - Bizden tarafta
    const amPos = project3DTo2D(-5.0, 0, -1.2);
    ctx2d.save();
    ctx2d.translate(amPos.x, amPos.y);
    
    // Main cabinet body (dark slate blue)
    ctx2d.fillStyle = '#334155';
    ctx2d.fillRect(-30, -95, 60, 95);
    ctx2d.strokeStyle = '#475569';
    ctx2d.lineWidth = 2;
    ctx2d.strokeRect(-30, -95, 60, 95);
    
    // Drawers and lines
    ctx2d.strokeStyle = '#1e293b';
    ctx2d.lineWidth = 1;
    for (let dy = -70; dy <= -20; dy += 20) {
        ctx2d.beginPath();
        ctx2d.moveTo(-30, dy);
        ctx2d.lineTo(30, dy);
        ctx2d.stroke();
        
        // Handle (silver/metal)
        ctx2d.fillStyle = '#94a3b8';
        ctx2d.fillRect(-14, dy + 8, 28, 4);
    }
    
    // Vaporizers shelf on top
    ctx2d.fillStyle = '#64748b';
    ctx2d.fillRect(-32, -98, 64, 4);
    
    // Yellow Vaporizer (Sevoflurane)
    ctx2d.fillStyle = '#f59e0b';
    ctx2d.fillRect(-20, -118, 12, 20);
    ctx2d.fillStyle = '#cbd5e1'; // metal cap
    ctx2d.fillRect(-17, -122, 6, 4);
    
    // Purple Vaporizer (Isoflurane)
    ctx2d.fillStyle = '#7c3aed';
    ctx2d.fillRect(-4, -118, 12, 20);
    ctx2d.fillStyle = '#cbd5e1'; // metal cap
    ctx2d.fillRect(-1, -122, 6, 4);
    
    // Soda Lime canister on the side (white/clear cylinder)
    ctx2d.fillStyle = '#f1f5f9';
    ctx2d.fillRect(22, -65, 16, 28);
    ctx2d.strokeStyle = '#94a3b8';
    ctx2d.lineWidth = 1;
    ctx2d.strokeRect(22, -65, 16, 28);
    // Draw pink/purple lime indicator lines
    ctx2d.fillStyle = '#f472b6';
    ctx2d.fillRect(24, -53, 12, 5);
    
    // Mount bracket for ventilator screen
    ctx2d.strokeStyle = '#64748b';
    ctx2d.lineWidth = 3;
    ctx2d.beginPath();
    ctx2d.moveTo(-20, -98);
    ctx2d.lineTo(-20, -130);
    ctx2d.stroke();
    
    // Ventilator Screen casing
    ctx2d.fillStyle = '#1e293b';
    ctx2d.fillRect(-32, -150, 24, 20);
    ctx2d.strokeStyle = '#475569';
    ctx2d.lineWidth = 1;
    ctx2d.strokeRect(-32, -150, 24, 20);
    
    // Ventilator LCD Screen (cyan)
    ctx2d.fillStyle = '#0284c7';
    ctx2d.fillRect(-30, -148, 20, 16);
    
    // Ventilator trace
    ctx2d.strokeStyle = '#ffffff';
    ctx2d.lineWidth = 1;
    ctx2d.beginPath();
    ctx2d.moveTo(-29, -140);
    ctx2d.lineTo(-26, -140);
    ctx2d.lineTo(-24, -145);
    ctx2d.lineTo(-22, -135);
    ctx2d.lineTo(-20, -140);
    ctx2d.lineTo(-11, -140);
    ctx2d.stroke();
    
    // Curved blue breathing tubes hanging
    ctx2d.strokeStyle = '#60a5fa';
    ctx2d.lineWidth = 3;
    ctx2d.beginPath();
    ctx2d.arc(15, -48, 14, 0, Math.PI, false);
    ctx2d.stroke();
    
    ctx2d.restore();
    
    // 4. Operating Table (Middle depth z = 0)
    const tBase = project3DTo2D(3.0, 0, 0);
    const tPad = project3DTo2D(3.0, 1.2, 0);
    
    ctx2d.fillStyle = '#475569'; // Pillar
    ctx2d.fillRect(tBase.x - 24, tPad.y, 48, tBase.y - tPad.y);
    
    ctx2d.fillStyle = '#1E293B'; // Pad
    ctx2d.fillRect(tPad.x - 160, tPad.y - 12, 320, 24);
    ctx2d.strokeStyle = '#334155';
    ctx2d.lineWidth = 1;
    ctx2d.strokeRect(tPad.x - 160, tPad.y - 12, 320, 24);
    
    // 5. Drape / Ether Screen (Middle depth z = 0)
    const dBase = project3DTo2D(0.5, 0, 0);
    const dTop = project3DTo2D(0.5, 2.0, 0);
    
    ctx2d.strokeStyle = '#64748b';
    ctx2d.lineWidth = 4;
    ctx2d.beginPath();
    ctx2d.moveTo(dBase.x, dBase.y);
    ctx2d.lineTo(dBase.x, dTop.y);
    ctx2d.stroke();
    
    ctx2d.beginPath();
    ctx2d.moveTo(dBase.x - 40, dTop.y);
    ctx2d.lineTo(dBase.x + 40, dTop.y);
    ctx2d.stroke();
    
    ctx2d.fillStyle = 'rgba(59, 130, 246, 0.65)'; // transparent drape blue
    ctx2d.fillRect(dBase.x - 36, dTop.y, 72, 120);
    
    // 6. Sitting Anesthesiologist (x = -7.5, y = 0, z = 0.3)
    const pBase = project3DTo2D(-7.5, 0, 0.3);
    
    // Rolling stool
    ctx2d.fillStyle = '#0f172a';
    ctx2d.fillRect(pBase.x - 22, pBase.y - 70, 44, 10); // seat
    ctx2d.fillStyle = '#475569';
    ctx2d.fillRect(pBase.x - 3, pBase.y - 60, 6, 56); // post
    ctx2d.fillStyle = '#334155';
    ctx2d.fillRect(pBase.x - 18, pBase.y - 4, 36, 4); // base legs
    
    // Green Scrubs body
    ctx2d.fillStyle = '#065f46';
    ctx2d.beginPath();
    ctx2d.arc(pBase.x, pBase.y - 100, 24, 0, Math.PI, true);
    ctx2d.fill();
    ctx2d.fillRect(pBase.x - 24, pBase.y - 100, 48, 36);
    
    // Knees/Legs
    ctx2d.strokeStyle = '#065f46';
    ctx2d.lineWidth = 10;
    ctx2d.lineCap = 'round';
    ctx2d.beginPath();
    ctx2d.moveTo(pBase.x - 10, pBase.y - 70);
    ctx2d.lineTo(pBase.x - 26, pBase.y - 45);
    ctx2d.lineTo(pBase.x - 26, pBase.y);
    ctx2d.moveTo(pBase.x + 10, pBase.y - 70);
    ctx2d.lineTo(pBase.x + 26, pBase.y - 45);
    ctx2d.lineTo(pBase.x + 26, pBase.y);
    ctx2d.stroke();
    
    // Face skin
    ctx2d.fillStyle = '#fbcfe8';
    ctx2d.beginPath();
    ctx2d.arc(pBase.x, pBase.y - 138, 18, 0, Math.PI*2);
    ctx2d.fill();
    
    // Blue surgical cap
    ctx2d.fillStyle = '#2563eb';
    ctx2d.beginPath();
    ctx2d.arc(pBase.x, pBase.y - 143, 19, Math.PI, 0);
    ctx2d.fill();
    
    // Mask
    ctx2d.fillStyle = '#60a5fa';
    ctx2d.fillRect(pBase.x - 5, pBase.y - 138, 14, 10);
    
    // Arm & Laryngoscope in hand (when not flying)
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
    
    // 7. Standing Surgeon Target (x = 7.5, y = 0, z = surgeonZ)
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
    ctx2d.arc(0, -150, 18, 0, Math.PI*2);
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
    
    // 8. IV Pole (Foreground layer, z = 1.4)
    const ivBase = project3DTo2D(1.5, 0, 1.4);
    const ivTop = project3DTo2D(1.5, 3.2, 1.4);
    
    ctx2d.strokeStyle = '#cbd5e1';
    ctx2d.lineWidth = 4;
    ctx2d.beginPath();
    ctx2d.moveTo(ivBase.x, ivBase.y);
    ctx2d.lineTo(ivBase.x, ivTop.y);
    ctx2d.stroke();
    
    ctx2d.beginPath();
    ctx2d.moveTo(ivBase.x - 18, ivTop.y + 10);
    ctx2d.lineTo(ivBase.x + 18, ivTop.y + 10);
    ctx2d.stroke();
    
    ctx2d.fillStyle = 'rgba(255,255,255,0.45)';
    ctx2d.fillRect(ivBase.x - 24, ivTop.y + 16, 10, 22);
    ctx2d.fillRect(ivBase.x + 14, ivTop.y + 16, 10, 22);
    
    // 9. Dotted Aiming Trajectory (when playing and aiming)
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
            tempVel.y -= (GRAVITY * 0.016);
            tempVel.x += (windX * 0.01);
            tempVel.z += (windZ * 0.01);
            tempPos.add(tempVel);
            
            const p = project3DTo2D(tempPos.x, tempPos.y, tempPos.z);
            ctx2d.lineTo(p.x, p.y);
            
            if (tempPos.y <= FLOOR_Y) break;
        }
        ctx2d.stroke();
        ctx2d.setLineDash([]);
    }
    
    // 10. Flying Laryngoscope
    if (project.isFlying) {
        const projPos = project3DTo2D(project.pos.x, project.pos.y, project.pos.z);
        
        ctx2d.save();
        ctx2d.translate(projPos.x, projPos.y);
        
        const rotation = (Date.now() * 0.015);
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
        ctx2d.arc(12, -10, 2.5, 0, Math.PI*2);
        ctx2d.fill();
        
        ctx2d.restore();
    }
    
    // 11. Flight Smoke Trail Particles
    for (let i = project.trailParticles.length - 1; i >= 0; i--) {
        const tp = project.trailParticles[i];
        const screenP = project3DTo2D(tp.pos.x, tp.pos.y, tp.pos.z);
        ctx2d.fillStyle = `rgba(255, 255, 255, ${tp.alpha})`;
        ctx2d.beginPath();
        ctx2d.arc(screenP.x, screenP.y, tp.size, 0, Math.PI * 2);
        ctx2d.fill();
    }
    
    // 12. Burst Sparkle Particles (Hits/Misses)
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
    project.vel.set(
        Math.cos(pitchRad) * Math.cos(yawRad) * speed,
        Math.sin(pitchRad) * speed,
        -Math.cos(pitchRad) * Math.sin(yawRad) * speed
    );
    
    project.isFlying = true;
    
    if (gameMode === '3d' && is3DAvailable) {
        trajectoryLine.visible = false;
        cameraMode = 'flight';
    }
    
    playSynthesizedSound('launch');
}

// Touch & Mouse Drag slingshot controls
let isDraggingViewport = false;
let startDragMouse = { x: 0, y: 0 };
let currentDragMouse = { x: 0, y: 0 };

function setupViewportAiming() {
    const container = document.querySelector('.canvas-container');
    
    container.addEventListener('mousedown', (e) => {
        if (gameState !== 'playing' || project.isFlying) return;
        initAudio();
        
        isDraggingViewport = true;
        startDragMouse.x = e.clientX;
        startDragMouse.y = e.clientY;
        currentDragMouse.x = e.clientX;
        currentDragMouse.y = e.clientY;
    });
    
    window.addEventListener('mousemove', (e) => {
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
    container.addEventListener('touchstart', (e) => {
        if (gameState !== 'playing' || project.isFlying) return;
        initAudio();
        isDraggingViewport = true;
        startDragMouse.x = e.touches[0].clientX;
        startDragMouse.y = e.touches[0].clientY;
        currentDragMouse.x = e.touches[0].clientX;
        currentDragMouse.y = e.touches[0].clientY;
    }, {passive: true});
    
    window.addEventListener('touchmove', (e) => {
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
    }, {passive: true});
    
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
    
    if (pos.x >= monMin.x - project.radius && pos.x <= monMax.x + project.radius &&
        pos.y >= monMin.y - project.radius && pos.y <= monMax.y + project.radius &&
        pos.z >= monMin.z - project.radius && pos.z <= monMax.z + project.radius) {
        
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
        
        if (proj2D.x >= head2D.x - bodyWidth/2 && proj2D.x <= head2D.x + bodyWidth/2 &&
            proj2D.y >= bodyTop && proj2D.y <= bodyBottom) {
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
        if (distToBodyX <= 0.45 + project.radius &&
            distToBodyZ <= 0.8 + project.radius &&
            pos.y >= targetY && pos.y <= targetY + 1.4) {
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
        toast.innerText = language === 'tr' 
            ? 'WebGL/Three.js yüklenemedi veya desteklenmiyor. 2D moduna geçildi.' 
            : 'WebGL/Three.js failed to load or is unsupported. Falling back to 2D mode.';
        
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
                    const trailMat = new THREE.MeshBasicMaterial({ color: 0xffffff, transparent: true, opacity: 0.4 });
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
        } 
        else if (cameraMode === 'flight') {
            cameraTargetPos.set(project.pos.x - 3.5, project.pos.y + 1.8, project.pos.z);
            cameraTargetLook.set(project.pos.x, project.pos.y, project.pos.z);
        } 
        else if (cameraMode === 'hit') {
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
    
    // 2. Perform WebGL & CDN libraries availability checks
    const isThreeLoaded = (typeof THREE !== 'undefined');
    let isWebGLSupported = false;
    if (isThreeLoaded) {
        try {
            const canvasTest = document.createElement('canvas');
            isWebGLSupported = !!(window.WebGLRenderingContext && 
                (canvasTest.getContext('webgl') || canvasTest.getContext('experimental-webgl')));
        } catch (e) {
            isWebGLSupported = false;
        }
    }
    
    is3DAvailable = isThreeLoaded && isWebGLSupported;
    
    // Load highscore
    highscore = parseInt(localStorage.getItem('laryngoscope_highscore') || 0);
    
    // Load language preference
    const savedLang = localStorage.getItem('anesthesia_pref_lang');
    if (savedLang) language = savedLang;
    
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
    
    // Load last used nickname and country, then initialize leaderboard
    playerNick = localStorage.getItem('laryngoscope_player_nick') || (language === 'tr' ? 'Anestezist' : 'Anesthesiologist');
    playerCountry = localStorage.getItem('laryngoscope_player_country') || 'TR';
    
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
