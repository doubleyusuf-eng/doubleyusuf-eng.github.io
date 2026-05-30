package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.model.DailyClinicalPearl
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.anesthesiaclinic.anesthesiabriefs.data.repository.FirestoreRepository
import com.anesthesiaclinic.anesthesiabriefs.data.repository.SeedDataSpotTips
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.encodeToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userName: String,
    currentLanguage: String,
    onNavigateToCalculators: () -> Unit,
    onNavigateToAlgorithms: (String?) -> Unit,
    onNavigateToDrugs: () -> Unit,
    onNavigateToLiterature: () -> Unit,
    onNavigateToAiAssistant: () -> Unit,
    onSelectDrug: (String) -> Unit,
    onSelectCalculator: (String) -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToBoardPrep: () -> Unit,
    onNavigateToDailyClinicalPearls: () -> Unit
) {
    val isTurkish = currentLanguage == "tr"
    fun t(tr: String, en: String): String = if (isTurkish) tr else en

    var searchQuery by remember { mutableStateOf("") }
    val searchResults = remember(searchQuery) { FirestoreRepository.searchAll(searchQuery) }

    // Dynamic Pearl Banner settings
    var showPearlBanner by remember { mutableStateOf(true) }
    var activeTipTitle by remember { mutableStateOf("") }
    var activeTipContent by remember { mutableStateOf("") }
    var isRefreshingTip by remember { mutableStateOf(false) }

    val context = androidx.compose.ui.platform.LocalContext.current
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", android.content.Context.MODE_PRIVATE) }
    val coroutineScope = rememberCoroutineScope()

    // Load random tip on launch
    LaunchedEffect(currentLanguage) {
        val (title, content) = SeedDataSpotTips.getRandomTip(isTurkish)
        activeTipTitle = title
        activeTipContent = content
    }

    var dailyPearlOfDay by remember { mutableStateOf<DailyClinicalPearl?>(null) }
    LaunchedEffect(Unit) {
        try {
            val pearls = BoardPrepRepository.getDailyClinicalPearls(context)
            if (pearls.isNotEmpty()) {
                dailyPearlOfDay = pearls.random()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun refreshSpotTip() {
        coroutineScope.launch {
            isRefreshingTip = true
            val apiKey = "AIzaSyAeD84-0Gq8LGBVuqX621Cz7X_jRHQ6U2Y"
            var fetchedFromGemini = false
            
            if (apiKey.isNotEmpty()) {
                val geminiTip = withContext(Dispatchers.IO) {
                    try {
                        val url = java.net.URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$apiKey")
                        val conn = url.openConnection() as java.net.HttpURLConnection
                        conn.requestMethod = "POST"
                        conn.setRequestProperty("Content-Type", "application/json")
                        conn.doOutput = true
                        
                        val prompt = "Anesteziyoloji, yoğun bakım veya acil tıp hakkında pratik, bilimsel açıdan doğru ve akılda kalıcı en fazla 2 cümlelik klinik bir spot bilgi (clinical pearl/tip) yaz. Lütfen yanıtını sadece ham metin olarak ve ${if (isTurkish) "Türkçe" else "İngilizce"} ver. Başlık ekleme, sadece spot bilgiyi ver."
                        val body = """{"contents": [{"parts": [{"text": ${kotlinx.serialization.json.Json.encodeToString(prompt)}}]}]}"""
                        
                        conn.outputStream.use { it.write(body.toByteArray(charset("utf-8"))) }
                        
                        if (conn.responseCode == 200) {
                            val resp = conn.inputStream.bufferedReader().use { it.readText() }
                            val root = kotlinx.serialization.json.Json.parseToJsonElement(resp) as kotlinx.serialization.json.JsonObject
                            val candidates = root["candidates"] as? kotlinx.serialization.json.JsonArray
                            val first = candidates?.firstOrNull() as? kotlinx.serialization.json.JsonObject
                            val responseContent = first?.get("content") as? kotlinx.serialization.json.JsonObject
                            val parts = responseContent?.get("parts") as? kotlinx.serialization.json.JsonArray
                            val text = (parts?.firstOrNull() as? kotlinx.serialization.json.JsonObject)?.get("text")?.jsonPrimitive?.content ?: ""
                            text.trim()
                        } else {
                            null
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }
                
                if (!geminiTip.isNullOrBlank()) {
                    activeTipTitle = t("🤖 Canlı Yapay Zeka Klinik İncisi", "🤖 Live AI Clinical Pearl")
                    activeTipContent = geminiTip
                    fetchedFromGemini = true
                }
            }
            
            if (!fetchedFromGemini) {
                // Fallback to random pre-seeded offline spot tip
                val (title, content) = SeedDataSpotTips.getRandomTip(isTurkish)
                activeTipTitle = title
                activeTipContent = content
            }
            isRefreshingTip = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Upper Greeting Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DeepNavy, DeepNavy.copy(alpha = 0.95f))
                    )
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                    
                    AppLogo(
                        modifier = Modifier.weight(1f),
                        textSizeSp = 20,
                        iconSizeDp = 26,
                        isDarkTheme = true,
                        isNavy = true
                    )
                    
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = t("Merhaba, Dr. $userName 👋", "Hello, Dr. $userName 👋"),
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = t("Klinik kararlarınızda güvence altındasınız.", "You are safe in your clinical decisions."),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondaryDark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Modern Search Field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(t("İlaç, formül veya algoritma arayın...", "Search drug, formula, or algorithm..."), color = TextSecondaryDark) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondaryDark) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null, tint = TextSecondaryDark)
                            }
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.1f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                        focusedBorderColor = SoftGold,
                        unfocusedBorderColor = BorderSand.copy(alpha = 0.3f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        }

        // Dynamic Clinical Spot Tip Card (Göz Alıcı Spot Bilgiler Bitişik)
        AnimatedVisibility(
            visible = showPearlBanner && activeTipContent.isNotEmpty(),
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, SoftGold.copy(alpha = 0.8f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = SoftGold,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = activeTipTitle,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = activeTipContent,
                            color = TextPrimaryLight,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(4.dp))
                    
                    // Refresh button
                    if (isRefreshingTip) {
                        CircularProgressIndicator(
                            color = ClinicalTeal,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(16.dp).align(Alignment.CenterVertically)
                        )
                    } else {
                        IconButton(
                            onClick = { refreshSpotTip() },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Yenile",
                                tint = ClinicalTeal,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(4.dp))

                    IconButton(
                        onClick = { showPearlBanner = false },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Kapat",
                            tint = TextSecondaryLight,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        // Animated Search Results
        AnimatedVisibility(
            visible = searchQuery.isNotEmpty(),
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Linen)
                    .border(1.dp, BorderSand, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = t(
                        "Arama Sonuçları (${searchResults.calculators.size + searchResults.drugs.size + searchResults.algorithms.size} eşleşme)",
                        "Search Results (${searchResults.calculators.size + searchResults.drugs.size + searchResults.algorithms.size} matches)"
                    ),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                if (searchResults.calculators.isEmpty() && searchResults.drugs.isEmpty() && searchResults.algorithms.isEmpty()) {
                    Text(t("Sonuç bulunamadı. Lütfen kelimeyi kontrol edin.", "No results found. Please check spelling."), style = MaterialTheme.typography.bodyLarge, color = TextSecondaryLight)
                }

                // Calculators list
                searchResults.calculators.forEach { calc ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectCalculator(calc.slug) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Calculate, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (isTurkish) calc.titleTr else calc.titleEn, style = MaterialTheme.typography.bodyLarge, color = TextPrimaryLight, fontWeight = FontWeight.SemiBold)
                    }
                }

                // Drugs list
                searchResults.drugs.forEach { drug ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectDrug(drug.drugId) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Medication, contentDescription = null, tint = SoftGold, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (isTurkish) drug.nameTr else drug.genericName, style = MaterialTheme.typography.bodyLarge, color = TextPrimaryLight, fontWeight = FontWeight.SemiBold)
                    }
                }

                // Algorithms list
                searchResults.algorithms.forEach { algo ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToAlgorithms(null) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.AccountTree, contentDescription = null, tint = CriticalRed, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (isTurkish) algo.titleTr else algo.titleEn, style = MaterialTheme.typography.bodyLarge, color = TextPrimaryLight, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Access Grid
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = t("Hızlı Erişim", "Quick Access"),
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                QuickCard(
                    title = t("Acil\nAlgoritmalar", "Emergency\nAlgorithms"),
                    icon = Icons.Default.Warning,
                    color = CriticalRed,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigateToAlgorithms("emergency") }
                )
                QuickCard(
                    title = t("Klinik\nHesaplayıcılar", "Clinical\nCalculators"),
                    icon = Icons.Default.Calculate,
                    color = ClinicalTeal,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToCalculators
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                QuickCard(
                    title = t("AI Klinik\nAsistan", "AI Clinical\nAssistant"),
                    icon = Icons.Default.AutoAwesome,
                    color = SoftGold,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToAiAssistant
                )
                QuickCard(
                    title = t("Son\nLiteratürler", "Latest\nLiterature"),
                    icon = Icons.Default.MenuBook,
                    color = DeepNavy,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToLiterature
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Premium Critical Algorithms Entry Card
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CriticalRed),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToAlgorithms("emergency") },
                border = BorderStroke(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(listOf(Color.White.copy(alpha = 0.4f), Color.Transparent))
                )
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(CriticalRed, Color(0xFF8B0000))
                            )
                        )
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = t("Kritik Algoritmalar", "Critical Algorithms"),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = t(
                                "Hayatı tehdit eden anestezi krizlerine hızlı erişim",
                                "Rapid access to life-threatening anesthesia crises"
                            ),
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Open Critical Algorithms",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Premium Board Prep Entry Card
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DeepNavy),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToBoardPrep() },
                border = BorderStroke(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(listOf(SoftGold.copy(alpha = 0.5f), Color.Transparent))
                )
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(DeepNavy, DeepNavy.copy(alpha = 0.8f))
                            )
                        )
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = SoftGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = t("Anestezi Kurul Hazırlığı", "Anesthesia Board Prep"),
                                color = SoftGold,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = t(
                                "EDAIC ve ABA BASIC/ADVANCED sınavları için iki dilde (TR/EN) kapsamlı soru bankası, sözlü sınav senaryoları ve detaylı klinik açıklamalar.",
                                "Comprehensive bilingual (TR/EN) question banks, oral exam viva scenarios, and high-fidelity explanations for EDAIC & ABA exams."
                            ),
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Open Board Prep",
                        tint = SoftGold,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Preoperative & Safety Tools Quick Shortcuts Row
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = t("Preoperatif Değerlendirme & Güvenlik", "Preoperative Assessment & Safety"),
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, BorderSand),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    PreopShortcutItem(
                        title = t("ASA Fiziksel Durum Sınıflaması (ASA PS)", "ASA Physical Status Classification (ASA PS)"),
                        subtitle = t("Hasta risk kategorileri ve klinik vaka kohortları", "Patient risk categories and clinical case cohorts"),
                        icon = Icons.Default.Assignment,
                        color = ClinicalTeal,
                        onClick = { onSelectCalculator("asa_ps") }
                    )
                    HorizontalDivider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                    PreopShortcutItem(
                        title = t("Preoperatif Açlık (NPO) Sayacı", "Preoperative Fasting (NPO) Timer"),
                        subtitle = t("2-4-6-8 Saat açlık takibi ve aspirasyon risk uyarıları", "2-4-6-8 Hour fasting tracking and aspiration risk alerts"),
                        icon = Icons.Default.Timer,
                        color = AlertAmber,
                        onClick = { onSelectCalculator("npo_fasting") }
                    )
                    HorizontalDivider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                    PreopShortcutItem(
                        title = t("Zor Havayolu Tarama Checklist'i", "Difficult Airway Screening Checklist"),
                        subtitle = t("Mallampati, TMD, ağız açıklığı ve ekipman hazırlığı", "Mallampati, TMD, mouth opening and equipment preparation"),
                        icon = Icons.Default.Warning,
                        color = CriticalRed,
                        onClick = { onSelectCalculator("difficult_airway") }
                    )
                    HorizontalDivider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                    PreopShortcutItem(
                        title = t("ASRA Antikoagülan Rejyonel Anestezi Tablosu", "ASRA Anticoagulant Regional Anesthesia Table"),
                        subtitle = t("Blok öncesi kesme, kateter çekme ve başlama süreleri", "Pre-block holding, catheter removal, and restart times"),
                        icon = Icons.Default.Medication,
                        color = DeepNavy,
                        onClick = { onSelectCalculator("asra_coagulation") }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Pediatric & Crisis Emergency Management Quick Shortcuts Row
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = t("Pediatrik & Acil Kriz Yönetimi", "Pediatric & Emergency Crisis Management"),
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, BorderSand),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    PreopShortcutItem(
                        title = t("Pediatrik Acil & İndüksiyon Dozları", "Pediatric Emergency & Induction Doses"),
                        subtitle = t("Çocuğun kilosuna göre CPR ve indüksiyon ilaç rehberi", "CPR and induction drug guide based on child's weight"),
                        icon = Icons.Default.ChildCare,
                        color = ClinicalTeal,
                        onClick = { onSelectCalculator("pediatric_dosing") }
                    )
                    HorizontalDivider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                    PreopShortcutItem(
                        title = t("Malign Hipertermi Acil Kriz Kılavuzu", "Malignant Hyperthermia Emergency Crisis Guide"),
                        subtitle = t("Dantrolen dozu, flakon sayısı ve kriz protokol adımları", "Dantrolene dose, vial count, and crisis protocol steps"),
                        icon = Icons.Default.Dangerous,
                        color = CriticalRed,
                        onClick = { onSelectCalculator("dantrolene_rescue") }
                    )
                    HorizontalDivider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                    PreopShortcutItem(
                        title = t("LAST LipidRescue Toksisite Düzeyleri", "LAST LipidRescue Toxicity Levels"),
                        subtitle = t("Lokal anestezik sistemik reaksiyonlarında %20 lipid infüzyon dozu", "20% lipid infusion dosage in local anesthetic systemic reactions"),
                        icon = Icons.Default.LocalHospital,
                        color = CriticalRed,
                        onClick = { onSelectCalculator("lipid_rescue") }
                    )
                    HorizontalDivider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                    PreopShortcutItem(
                        title = t("Vazopressör Perfüzyon & İnfüzyon Hızları", "Vasopressor Perfusion & Infusion Rates"),
                        subtitle = t("Noradrenalin, Adrenalin, Dobutamin ve Dopamin hesapları", "Norepinephrine, Epinephrine, Dobutamine and Dopamine calculations"),
                        icon = Icons.Default.Timer,
                        color = AlertAmber,
                        onClick = { onSelectCalculator("infusion_vasopressors") }
                    )
                    HorizontalDivider(color = BorderSand.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 10.dp))
                    PreopShortcutItem(
                        title = t("Rejyonel Karışım & Epidural Dozaj Reçeteleri", "Regional Mixture & Epidural Dosing Recipes"),
                        subtitle = t("Ağrısız doğum ve postoperatif epidural ilaç miks tarifleri", "Painless labor and postoperative epidural drug mix recipes"),
                        icon = Icons.Default.Layers,
                        color = DeepNavy,
                        onClick = { onSelectCalculator("regional_mixtures") }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Daily Clinical Pearl Card (Dynamic & Interactive!)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Linen)
                .border(1.dp, BorderSand, RoundedCornerShape(16.dp))
                .clickable { onNavigateToDailyClinicalPearls() }
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = SoftGold,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = t("Günün Klinik Bilgisi", "Clinical Pearl of the Day"),
                            style = MaterialTheme.typography.titleLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Navigate to Pearls Hub",
                        tint = DeepNavy.copy(alpha = 0.6f),
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                dailyPearlOfDay?.let { pearl ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = pearl.subcategory.uppercase(),
                        color = ClinicalTeal,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = dailyPearlOfDay?.let { if (isTurkish) it.tr else it.en } ?: t(
                        "Lokal Anestezik Sistemik Toksisitesi (LAST) şüphesinde propofol hemodinamiyi daha da bozabileceği için nöbet kontrolünde öncelikle benzodiazepinler tercih edilmelidir. Oksijenasyon ve Lipid infüzyonuna derhal başlanmalıdır.",
                        "In case of suspected Local Anesthetic Systemic Toxicity (LAST), benzodiazepines should be preferred for seizure control as propofol can further impair hemodynamics. Oxygenation and lipid infusion must start immediately."
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimaryLight,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Featured Drugs / Carousel Section
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            Text(
                text = t("Popüler İlaç Referansları", "Popular Drug References"),
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(FirestoreRepository.drugsList.value) { drug ->
                    FeaturedDrugCard(
                        drug = drug,
                        currentLanguage = currentLanguage,
                        onClick = { onSelectDrug(drug.drugId) }
                    )
                }
            }
        }
    }
}

@Composable
fun QuickCard(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Linen),
        border = BorderStroke(1.dp, BorderSand),
        modifier = modifier
            .height(130.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color.copy(alpha = 0.15f))
                    .padding(8.dp)
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun FeaturedDrugCard(
    drug: Drug,
    currentLanguage: String,
    onClick: () -> Unit
) {
    val isTurkish = currentLanguage == "tr"
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderSand),
        modifier = Modifier
            .width(200.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(ClinicalTeal.copy(alpha = 0.1f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = drug.category.uppercase(),
                    color = ClinicalTeal,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (isTurkish) drug.nameTr else drug.genericName,
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = drug.genericName,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondaryLight,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isTurkish) "Monografı Gör" else "View Monograph",
                    color = SoftGold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = SoftGold, modifier = Modifier.size(14.dp))
            }
        }
    }
}

@Composable
fun PreopShortcutItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color.copy(alpha = 0.15f))
                .padding(8.dp)
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, color = DeepNavy, fontSize = 13.sp)
            Text(subtitle, fontSize = 11.sp, color = TextSecondaryLight)
        }
        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = DeepNavy, modifier = Modifier.size(16.dp))
    }
}
