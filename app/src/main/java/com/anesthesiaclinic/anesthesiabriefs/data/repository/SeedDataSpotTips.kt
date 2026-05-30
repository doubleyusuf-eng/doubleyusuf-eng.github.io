package com.anesthesiaclinic.anesthesiabriefs.data.repository

import kotlin.random.Random

data class SpotTipItem(
    val id: Int,
    val titleTr: String,
    val contentTr: String,
    val titleEn: String,
    val contentEn: String
)

object SeedDataSpotTips {

    val tips = listOf(
        SpotTipItem(
            id = 1,
            titleTr = "Sugammadeks ve Oral Kontraseptifler",
            contentTr = "Sugammadeks kullanımı sonrasında oral kontraseptiflerin etkisi 7 gün boyunca azalabilir. Hastalara bu süreçte ek bariyer korunma yöntemleri kullanmaları mutlaka önerilmelidir.",
            titleEn = "Sugammadex & Oral Contraceptives",
            contentEn = "Sugammadex can decrease the efficacy of oral contraceptives for 7 days. Advise female patients of childbearing potential to use an additional barrier method during this time."
        ),
        SpotTipItem(
            id = 2,
            titleTr = "Pediatrik Bradikardide İlk Adım",
            contentTr = "Pediatrik hastalarda perioperatif bradikardi aksi kanıtlanana kadar hipoksi belirtisidir. İlk müdahale atropin değil, %100 oksijenle etkin ventilasyon olmalıdır.",
            titleEn = "First Step in Pediatric Bradycardia",
            contentEn = "Pediatric perioperative bradycardia is a sign of hypoxia until proven otherwise. The first response must be effective ventilation with 100% oxygen, not atropine."
        ),
        SpotTipItem(
            id = 3,
            titleTr = "Magnezyum Sülfat Potansiyalizasyonu",
            contentTr = "Magnezyum sülfat kullanımı, hem depolarizan (Süksinilkolin) hem de non-depolarizan kas gevşeticilerin nöromüsküler blok süresini ve derinliğini belirgin derecede artırır.",
            titleEn = "Magnesium Sulfate Potentiation",
            contentEn = "Magnesium sulfate administration significantly potentiates and prolongs neuromuscular blockade induced by both depolarizing and non-depolarizing muscle relaxants."
        ),
        SpotTipItem(
            id = 4,
            titleTr = "Lokal Anestezik Toksisitesinde Kalsiyum Kanalları",
            contentTr = "LAST (Lokal Toksisite) resüsitasyonu esnasında kalsiyum kanal blokerleri (Verapamil vb.) kesinlikle kontrendikedir; dirençli bradikardi ve asistoliye yol açabilirler.",
            titleEn = "LAST & Calcium Channel Blockers",
            contentEn = "Calcium channel blockers are strictly contraindicated during Local Anesthetic Systemic Toxicity (LAST) rescue, as they may lead to refractory bradycardia or cardiac arrest."
        ),
        SpotTipItem(
            id = 5,
            titleTr = "Hızlı Ardışık İndüksiyonda Basınç",
            contentTr = "Hızlı ardışık indüksiyonda (RSI) krikoid bası (Sellick manevrası), hasta bilincini kaybetmeden önce 10 Newton, bilinç kaybı sonrasında ise 30 Newton olmalıdır.",
            titleEn = "Cricoid Pressure in RSI",
            contentEn = "During Rapid Sequence Induction (RSI), cricoid pressure (Sellick maneuver) should be 10 Newtons before loss of consciousness, increasing to 30 Newtons after."
        ),
        SpotTipItem(
            id = 6,
            titleTr = "Laparoskopide ETCO2 Ani Yükselişi",
            contentTr = "Laparoskopik cerrahide insüflasyon esnasında ETCO2'nin ani yükselmesi, peritoneal CO2 emiliminin yanı sıra subkütan amfizem veya kapnoperitonyum şüphesi uyandırmalıdır.",
            titleEn = "Sudden ETCO2 Rise in Laparoscopy",
            contentEn = "A sudden rise in ETCO2 during laparoscopy should raise suspicion for subcutaneous emphysema or capnoperitoneum, alongside physiological peritoneal absorption."
        ),
        SpotTipItem(
            id = 7,
            titleTr = "Spinal Anestezi Sonrası Geçici İşitme Kaybı",
            contentTr = "Dural ponksiyon sonrası dural sızıntı nedeniyle azalan BOS basıncı, nadiren endolenfatik hidrops benzeri geçici bilateral düşük frekanslı işitme kaybına yol açabilir.",
            titleEn = "Transient Hearing Loss Post Spinal",
            contentEn = "Decreased CSF pressure from dural leakage after lumbar puncture can rarely cause transient, bilateral low-frequency hearing loss resembling endolymphatic hydrops."
        ),
        SpotTipItem(
            id = 8,
            titleTr = "Preoperatif Açlıkta Sakız Çiğneme",
            contentTr = "Elektif cerrahi öncesi hastanın sakız çiğnemesi, operasyonun ertelenmesini gerektirmez; ancak gastrik sıvı hacmini ve asiditesini hafifçe artırabileceği akılda tutulmalıdır.",
            titleEn = "Chewing Gum and NPO Status",
            contentEn = "Chewing gum prior to elective surgery does not necessitate case delay, but keep in mind it may slightly increase gastric fluid volume and acidity."
        ),
        SpotTipItem(
            id = 9,
            titleTr = "Triküspid Regürjitasyonunda PEEP Yönetimi",
            contentTr = "Ciddi triküspid yetmezliği olan hastalarda yüksek PEEP uygulaması, sağ ventrikül ard-yükünü artırarak kardiyak debiyi ciddi ölçüde düşürebilir.",
            titleEn = "PEEP in Tricuspid Regurgitation",
            contentEn = "In patients with severe tricuspid regurgitation, high PEEP can increase right ventricular afterload and significantly decrease cardiac output."
        ),
        SpotTipItem(
            id = 10,
            titleTr = "Dantrolen Sulandırma Güçlüğü",
            contentTr = "Malign hipertermi kurtarma ilacı Dantrolen (20 mg flakon), yalnızca distile su (60 mL) ile sulandırılmalıdır. Serum fizyolojik veya dekstroz çökmesine yol açar.",
            titleEn = "Dantrolene Reconstitution Pearl",
            contentEn = "Dantrolene (20 mg vial) must be reconstituted only with 60 mL of sterile preservative-free water. Saline or dextrose solutions will cause precipitation."
        ),
        SpotTipItem(
            id = 11,
            titleTr = "Klorheksidin ile Spinal Öncesi Cilt Temizliği",
            contentTr = "Nöroaksiyel blok öncesi cilt temizliğinde klorheksidin kullanıldığında, nörotoksisite ve kimyasal menenjiti önlemek için solüsyonun tamamen kuruması beklenmelidir.",
            titleEn = "Chlorhexidine in Neuraxial Blocks",
            contentEn = "When using chlorhexidine for skin antisepsis before neuraxial block, ensure the solution is completely dry to prevent neurotoxicity and chemical meningitis."
        ),
        SpotTipItem(
            id = 12,
            titleTr = "Nitroz Oksit ve Pnömotoraks",
            contentTr = "Azot protoksit (N2O), kan/gaz dağılım katsayısının yüksekliği nedeniyle kapalı hava boşluklarına hızla geçerek pnömotoraks veya gaz embolisi hacmini katlayabilir.",
            titleEn = "Nitrous Oxide and Pneumothorax",
            contentEn = "Nitrous oxide (N2O) quickly diffuses into closed air cavities due to its blood/gas partition coefficient, rapidly expanding a pneumothorax or air embolus."
        ),
        SpotTipItem(
            id = 13,
            titleTr = "Kronik Opioid Alan Hastada Hiperaljezi",
            contentTr = "Kronik opioid kullanan hastalarda intraoperatif yüksek doz Remifentanil infüzyonu, postoperatif dönemde akut hiperaljeziye (ağrı hassasiyetine) yol açabilir.",
            titleEn = "Remifentanil-Induced Hyperalgesia",
            contentEn = "High intraoperative doses of Remifentanil infusion in chronic opioid users can cause acute postoperative hyperalgesia (increased pain sensitivity)."
        ),
        SpotTipItem(
            id = 14,
            titleTr = "Karbondioksit Absorbanı Isınması",
            contentTr = "Karbondioksit absorbanının (Sodalime) aşırı ısınması ve ani kuruması, özellikle Sevofluran veya Desfluran ile reaksiyona girerek karbonmonoksit üretimine neden olabilir.",
            titleEn = "Sodalime & Carbon Monoxide",
            contentEn = "Overheating and desiccation of carbon dioxide absorbent (Sodalime) can cause toxic reactions with Sevoflurane or Desflurane, producing carbon monoxide."
        ),
        SpotTipItem(
            id = 15,
            titleTr = "Kardiyoversiyonda Senkronizasyon",
            contentTr = "Stabil olmayan taşikardilerde senkronize kardiyoversiyon uygulanırken cihazın 'SYNC' modu mutlaka aktif olmalıdır; aksi halde R-on-T fenomeni ile VF tetiklenebilir.",
            titleEn = "Synchronization in Cardioversion",
            contentEn = "During synchronized cardioversion for unstable tachycardias, the 'SYNC' mode must be active to avoid shock delivery during the T-wave, causing VF."
        )
    )

    fun getRandomTip(isTurkish: Boolean): Pair<String, String> {
        val index = Random.nextInt(tips.size)
        val tip = tips[index]
        return if (isTurkish) {
            Pair(tip.titleTr, tip.contentTr)
        } else {
            Pair(tip.titleEn, tip.contentEn)
        }
    }
}
