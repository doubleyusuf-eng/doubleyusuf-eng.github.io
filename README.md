# Anesthesia Briefs &mdash; Official Website & Mobile App

Welcome to the official repository for **Anesthesia Briefs** (Anestezi Özetleri), the premium educational resource and calculator hub for anesthesia professionals, residents, and students.

This repository houses both the landing page hosted at [www.anesthesiabriefs.com](https://www.anesthesiabriefs.com) and the mobile application codebase.

---

## 🌐 Landing Page Overview (`/docs`)

The official landing page is a responsive, highly performant, single-page application built using vanilla technologies to ensure instant loading times and clean performance.

### Key Features
*   **Dual-Language Experience (TR/EN):** Fully translated landing page with automated location-based language detection and seamless manual language toggles.
*   **3D Interactive Laryngoscope:** A gorgeous, custom-rendered 3D SVG-based Macintosh laryngoscope blade loader that matches the official logo, built with gradient steel finishes and micro-animations.
*   **Interactive Showcase:** A pixel-perfect device mockup slider showcasing real Android application screenshots in their native aspects.
*   **Premium Feature Highlight:** Prominent visual sections highlighting **Board Quiz (Board Soruları)** and **Spot Notes (Spot Bilgiler)** designed for exam preparation.
*   **Social Feed Integration:** A custom-crafted Instagram feed preview showing real posts, updates, and community engagement.

---

## 📂 Project Structure

```bash
├── docs/                      # Web Landing Page (Hosted via GitHub Pages)
│   ├── index.html             # Main multilingual entry point
│   ├── gallery.html           # Internal mockup asset checker
│   ├── css/
│   │   └── style.css          # Elite CSS tokens, knurling textures & layout
│   ├── js/
│   │   └── app.js             # Geolocation router, toggle handlers & dynamic translations
│   └── assets/
│       └── screenshots/       # Application screenshots (TR & EN versions)
└── app/                       # Android Mobile Application Source Code
```

---

## 🚀 How to Run Locally

### 1. Web Page Preview
To run the landing page locally and preview changes in real-time, execute a simple server inside the project root:
```bash
python3 -m http.server 8200
```
Then, open [http://localhost:8200/docs/](http://localhost:8200/docs/) in your browser.

---

## ⚙️ Deploying to GitHub Pages

The website is set up to deploy automatically from the `/docs` folder using GitHub Pages.

1. Go to your repository settings on GitHub.
2. Select **Pages** from the left-hand sidebar.
3. Under **Build and deployment**, select **Deploy from a branch**.
4. Choose the `main` branch and select the `/docs` folder as the source.
5. Click **Save**.

### Custom Domain Setup
To point your custom domain `www.anesthesiabriefs.com` to this page:
1. In the GitHub Pages settings, add `www.anesthesiabriefs.com` as the custom domain.
2. Ensure your DNS provider (e.g., GoDaddy, Squarespace, Cloudflare) has a **CNAME** record pointing `www.anesthesiabriefs.com` to `doubleyusuf-eng.github.io`.
