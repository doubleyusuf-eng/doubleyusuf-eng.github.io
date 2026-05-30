package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepAccessManager
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.ClinicalTeal
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.DeepNavy
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.SoftGold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
fun BoardPrepHomeScreen(
    onNavigateToQuiz: (
        boardType: String,
        quizMode: String,
        packId: String?,
        category: String?,
        difficulty: String?,
        bookmarkedOnly: Boolean,
        unansweredOnly: Boolean,
        incorrectOnly: Boolean
    ) -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToSpotNotes: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", android.content.Context.MODE_PRIVATE) }
    
    var stats by remember { mutableStateOf<Map<String, Any>>(emptyMap()) }
    var showDisclaimerAlert by remember { mutableStateOf(false) }
    
    // Custom Session & Pack Browser panel state
    var showPackBrowser by remember { mutableStateOf(false) }
    var showPremiumGate by remember { mutableStateOf(false) }
    var richPacks by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var categoriesList by remember { mutableStateOf<List<String>>(emptyList()) }
    
    // Selected custom filters
    var customBoardType by remember { mutableStateOf("ALL") }
    var customPackId by remember { mutableStateOf("ALL") }
    var customCategory by remember { mutableStateOf("ALL") }
    var customDifficulty by remember { mutableStateOf("ALL") }
    var customBookmarkedOnly by remember { mutableStateOf(false) }
    var customUnansweredOnly by remember { mutableStateOf(false) }
    var customIncorrectOnly by remember { mutableStateOf(false) }
    var customMode by remember { mutableStateOf("STUDY") }

    val isPremium = remember { mutableStateOf(BoardPrepAccessManager.isPremiumUser(context)) }

    // Fetch rich stats on startup
    val refreshStats = {
        scope.launch {
            stats = BoardPrepRepository.getStats(context)
            richPacks = BoardPrepRepository.getInstalledPacksRich(context)
            categoriesList = BoardPrepRepository.getAllCategories(context)
            isPremium.value = BoardPrepAccessManager.isPremiumUser(context)
        }
    }

    LaunchedEffect(Unit) {
        refreshStats()
        val accepted = prefs.getBoolean("board_prep_disclaimer_accepted", false)
        if (!accepted) {
            showDisclaimerAlert = true
        }
    }

    val isTurkish = prefs.getString("settings_language", "en") == "tr"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = if (isTurkish) "Sınav Hazırlığı" else "Board Prep Hub",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    // Custom session button
                    IconButton(onClick = { 
                        if (isPremium.value) {
                            showPackBrowser = true 
                        } else {
                            showPremiumGate = true
                        }
                    }) {
                        Icon(Icons.Default.Tune, contentDescription = "Pack Browser & Filters", tint = SoftGold)
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepNavy
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DeepNavy, DeepNavy.copy(alpha = 0.85f))
                    )
                )
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 32.dp, top = 8.dp)
            ) {
                // Dashboard Header Banner
                item {
                    val totalQ = stats["totalQuestions"] as? Int ?: 0
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (isTurkish) "Toplam Soru Havuzu" else "Total Question Pool",
                                    color = Color.White.copy(alpha = 0.6f),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                if (!isPremium.value) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Box(
                                        modifier = Modifier
                                            .background(SoftGold.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = if (isTurkish) "Önizleme Aktif" else "Preview Active",
                                            color = SoftGold,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(4.dp))
                            
                            if (isPremium.value) {
                                Text(
                                    text = "$totalQ",
                                    color = ClinicalTeal,
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "12",
                                        color = SoftGold,
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = " / $totalQ",
                                        color = Color.White.copy(alpha = 0.4f),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(bottom = 6.dp)
                                    )
                                }
                            }

                            Text(
                                text = if (isTurkish) {
                                    if (isPremium.value) "Tüm premium soru paketleri ve sınav simülatörü kilidi açık."
                                    else "Ücretsiz deneme sürümü aktif. 12 adet board düzeyinde örnek soruya erişebilirsiniz."
                                } else {
                                    if (isPremium.value) "All premium question packs and exam simulator unlocked."
                                    else "Free Preview. Access exactly 12 board-style sample questions."
                                },
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { 
                                        if (isPremium.value) {
                                            showPackBrowser = true 
                                        } else {
                                            showPremiumGate = true
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Default.Tune, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (isTurkish) "Paketler & Filtreler" else "Browse Packs & Filters",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                if (!isPremium.value) {
                                    Button(
                                        onClick = { showPremiumGate = true },
                                        colors = ButtonDefaults.buttonColors(containerColor = SoftGold, contentColor = DeepNavy),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = if (isTurkish) "Premium'a Geç" else "Go Premium",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Progress Metrics Row
                item {
                    val mtfAcc = stats["mtfStatementAccuracy"] as? Int ?: 0
                    val sbaAcc = stats["sbaAccuracy"] as? Int ?: 0
                    val vivaConf = stats["vivaConfidence"] as? Int ?: 0

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "MTF Acc", color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp)
                                Text(text = "$mtfAcc%", color = ClinicalTeal, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text(text = if (isTurkish) "Şık Doğruluğu" else "Statement Level", color = Color.White.copy(alpha = 0.4f), fontSize = 8.sp)
                            }
                        }
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "SBA Acc", color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp)
                                Text(text = "$sbaAcc%", color = SoftGold, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text(text = if (isTurkish) "Tekli Seçmeli" else "Single Best", color = Color.White.copy(alpha = 0.4f), fontSize = 8.sp)
                            }
                        }
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Viva Conf", color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp)
                                Text(text = "$vivaConf%", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text(text = if (isTurkish) "Sözlü Özgüven" else "Self Confidence", color = Color.White.copy(alpha = 0.4f), fontSize = 8.sp)
                            }
                        }
                    }
                }

                // Dashboard Cards Segment
                item {
                    Text(
                        text = if (isTurkish) "Hazırlık Modülleri" else "Exam Categories",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // 1. EDAIC Part I card
                item {
                    val count = stats["edaicCount"] as? Int ?: 0
                    val solved = stats["edaicSolved"] as? Int ?: 0
                    BoardDashboardCard(
                        title = "EDAIC Part I MTF",
                        questionCount = count,
                        solvedCount = solved,
                        icon = Icons.Default.FactCheck,
                        accentColor = ClinicalTeal,
                        isTurkish = isTurkish,
                        isPremium = isPremium.value,
                        showPreviewBadge = !isPremium.value, // Shows preview badge
                        previewCount = 5,
                        onNavigate = { mode ->
                            if (mode == "EXAM" && !isPremium.value) {
                                showPremiumGate = true
                            } else {
                                onNavigateToQuiz("EDAIC_PART_I", mode, null, null, null, false, false, false)
                            }
                        }
                    )
                }

                // 2. ABA BASIC card
                item {
                    val count = stats["abaBasicCount"] as? Int ?: 0
                    val solved = stats["abaBasicSolved"] as? Int ?: 0
                    BoardDashboardCard(
                        title = "ABA BASIC",
                        questionCount = count,
                        solvedCount = solved,
                        icon = Icons.Default.MenuBook,
                        accentColor = SoftGold,
                        isTurkish = isTurkish,
                        isPremium = isPremium.value,
                        showPreviewBadge = !isPremium.value, // Shows preview badge
                        previewCount = 5,
                        onNavigate = { mode ->
                            if (mode == "EXAM" && !isPremium.value) {
                                showPremiumGate = true
                            } else {
                                onNavigateToQuiz("ABA_BASIC", mode, null, null, null, false, false, false)
                            }
                        }
                    )
                }

                // 3. ABA ADVANCED card (Premium Only)
                item {
                    val count = stats["abaAdvancedCount"] as? Int ?: 0
                    val solved = stats["abaAdvancedSolved"] as? Int ?: 0
                    BoardDashboardCard(
                        title = "ABA ADVANCED",
                        questionCount = count,
                        solvedCount = solved,
                        icon = Icons.Default.Settings,
                        accentColor = Color.Magenta,
                        isTurkish = isTurkish,
                        isPremium = isPremium.value,
                        showPreviewBadge = false,
                        isLocked = !isPremium.value,
                        onNavigate = { mode ->
                            if (!isPremium.value) {
                                showPremiumGate = true
                            } else {
                                onNavigateToQuiz("ABA_ADVANCED", mode, null, null, null, false, false, false)
                            }
                        }
                    )
                }

                // 4. EDAIC Viva card
                item {
                    val count = stats["edaicVivaCount"] as? Int ?: 0
                    val solved = stats["edaicVivaSolved"] as? Int ?: 0
                    BoardDashboardCard(
                        title = "EDAIC Viva / Oral Exam",
                        questionCount = count,
                        solvedCount = solved,
                        icon = Icons.Default.RecordVoiceOver,
                        accentColor = Color.White,
                        isTurkish = isTurkish,
                        isPremium = isPremium.value,
                        showPreviewBadge = !isPremium.value,
                        previewCount = 2,
                        onNavigate = { mode ->
                            if (mode == "EXAM" && !isPremium.value) {
                                showPremiumGate = true
                            } else {
                                onNavigateToQuiz("EDAIC_VIVA", mode, null, null, null, false, false, false)
                            }
                        }
                    )
                }

                // 5. Bookmarked Questions card
                item {
                    val count = stats["totalBookmarks"] as? Int ?: 0
                    val solved = stats["bookmarkedSolved"] as? Int ?: 0
                    BoardDashboardCard(
                        title = if (isTurkish) "Favori Sorular" else "Bookmarked Questions",
                        questionCount = count,
                        solvedCount = solved,
                        icon = Icons.Default.Bookmark,
                        accentColor = SoftGold,
                        isTurkish = isTurkish,
                        isPremium = isPremium.value,
                        showPreviewBadge = false,
                        onNavigate = { mode ->
                            onNavigateToQuiz("BOOKMARKS", mode, null, null, null, false, false, false)
                        }
                    )
                }

                // 6. Weak Topics card
                val weakTopics = stats["weakTopics"] as? List<*> ?: emptyList<Any>()
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
                        border = BorderStroke(1.dp, if (!isPremium.value) Color.Red.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                if (!isPremium.value) {
                                    showPremiumGate = true
                                }
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Dangerous, contentDescription = null, tint = Color.Red, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (isTurkish) "Zayıf Konular" else "Weak Topics",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    if (!isPremium.value) {
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Icon(Icons.Default.Lock, contentDescription = "Locked", tint = SoftGold, modifier = Modifier.size(16.dp))
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.Red.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "${weakTopics.size} " + (if (isTurkish) "Konu" else "Topics"),
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(10.dp))
                            
                            if (weakTopics.isEmpty()) {
                                Text(
                                    text = if (isTurkish) "Mükemmel! Zayıf konunuz bulunamadı." else "No weak areas found yet! Keep it up.",
                                    color = Color.White.copy(alpha = 0.5f),
                                    fontSize = 12.sp
                                )
                            } else {
                                Text(
                                    text = if (isTurkish) {
                                        if (isPremium.value) "Düşük başarı ve sık yapılan hatalara göre otomatik tespit edilen alanlar:"
                                        else "Sık yaptığınız hatalara göre zayıf konularınız burada listelenir. (Premium)"
                                    } else {
                                        if (isPremium.value) "Based on low accuracies & errors, focus on these tags:"
                                        else "Your personal weak areas are tracked here. (Premium Only)"
                                    },
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                if (isPremium.value) {
                                    FlowRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        weakTopics.take(8).forEach { topic ->
                                            Box(
                                                modifier = Modifier
                                                    .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(6.dp))
                                                    .border(1.dp, Color.Red.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                                                    .clickable {
                                                        onNavigateToQuiz("ALL", "STUDY", null, null, null, false, false, false)
                                                    }
                                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                            ) {
                                                Text(text = topic.toString(), color = Color.White, fontSize = 10.sp)
                                            }
                                        }
                                    }
                                } else {
                                    // Blur-like effect placeholder for free users
                                    FlowRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        listOf("physiology", "pharmacology", "airway", "regional").forEach { topic ->
                                            Box(
                                                modifier = Modifier
                                                    .background(Color.White.copy(alpha = 0.03f), RoundedCornerShape(6.dp))
                                                    .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(6.dp))
                                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                            ) {
                                                Text(text = topic, color = Color.White.copy(alpha = 0.2f), fontSize = 10.sp)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Spot Notes / Spot Bilgiler Card
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = ClinicalTeal.copy(alpha = 0.08f)),
                        border = BorderStroke(1.dp, ClinicalTeal.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onNavigateToSpotNotes() }
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LibraryBooks,
                                contentDescription = null,
                                tint = SoftGold,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isTurkish) "Spot Bilgiler / Hızlı Tekrar" else "Spot Notes / High-Yield Review",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (isTurkish) {
                                        "Sınavlara özel hazırlanmış en az 1000 spot bilgi. Özet tablolar ve kilit hatırlatmalar."
                                    } else {
                                        "Over 1000 custom high-yield notes compiled for EDAIC & ABA. Bulletproof quick-review."
                                    },
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Go",
                                tint = Color.White.copy(alpha = 0.6f),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                // 7. Exam Mode card
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f)),
                        border = BorderStroke(1.dp, if (!isPremium.value) SoftGold.copy(alpha = 0.1f) else SoftGold.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Speed, contentDescription = null, tint = SoftGold, modifier = Modifier.size(22.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isTurkish) "Sınav Simülatörü" else "Strict Exam Simulator",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                if (!isPremium.value) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(Icons.Default.Lock, contentDescription = "Locked", tint = SoftGold, modifier = Modifier.size(16.dp))
                                }
                            }
                            Text(
                                text = if (isTurkish) {
                                    "Açıklamaların gizlendiği, rastgele seçilen sorularla gerçek kurul sınav simülasyonunu anında başlatın. (Premium)"
                                } else {
                                    "Launch a strict, randomized board simulation with hidden answers, tracking scoring metrics dynamically. (Premium Only)"
                                },
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 11.sp,
                                modifier = Modifier.padding(top = 6.dp, bottom = 12.dp)
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = {
                                        if (!isPremium.value) {
                                            showPremiumGate = true
                                        } else {
                                            onNavigateToQuiz("ALL", "EXAM", null, null, null, false, false, false)
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = if (isPremium.value) SoftGold else Color.White.copy(alpha = 0.1f)),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = if (isTurkish) "Genel Sınavı Başlat" else "Start General Exam",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isPremium.value) DeepNavy else Color.White.copy(alpha = 0.5f)
                                    )
                                }
                                OutlinedButton(
                                    onClick = {
                                        if (!isPremium.value) {
                                            showPremiumGate = true
                                        } else {
                                            customMode = "EXAM"
                                            showPackBrowser = true
                                        }
                                    },
                                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f)),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = if (isTurkish) "Özel Sınav Hazırla" else "Customize Exam",
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }

                // 8. Progress Analytics Card (Locked if Free)
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
                        border = BorderStroke(1.dp, if (!isPremium.value) Color.White.copy(alpha = 0.05f) else ClinicalTeal.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                if (!isPremium.value) {
                                    showPremiumGate = true
                                }
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Analytics, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(22.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isTurkish) "Detaylı Gelişim Analitiği" else "Detailed Progress Analytics",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                if (!isPremium.value) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(Icons.Default.Lock, contentDescription = "Locked", tint = SoftGold, modifier = Modifier.size(16.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            if (isPremium.value) {
                                val totalBookmarks = stats["totalBookmarks"] as? Int ?: 0
                                val correctAttempts = stats["correctAttempts"] as? Int ?: 0
                                val totalAttempts = stats["totalAttempts"] as? Int ?: 0
                                val strictAcc = if (totalAttempts > 0) (correctAttempts * 100 / totalAttempts) else 0

                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                        Text(text = if (isTurkish) "Toplam Çözüm Denemesi:" else "Total Attempts:", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                                        Text(text = "$totalAttempts", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                        Text(text = if (isTurkish) "Net Doğru Soru Oranı:" else "Strict Questions Accuracy:", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                                        Text(text = "$strictAcc%", color = SoftGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                        Text(text = if (isTurkish) "Favoriye Eklenen Soru:" else "Bookmarked Questions:", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                                        Text(text = "$totalBookmarks", color = ClinicalTeal, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }
                            } else {
                                Text(
                                    text = if (isTurkish) {
                                        "İnce taneli soru-yanıt analizi, şık düzeyinde doğruluk raporları ve sözlü sınav grafiklerine erişin. (Premium)"
                                    } else {
                                        "Access deep statement-level correctness breakdowns, simulated metrics tracking, and oral exam self-confidence charts. (Premium Only)"
                                    },
                                    color = Color.White.copy(alpha = 0.6f),
                                    fontSize = 11.sp,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }

                // Local device progress backing notification (Requirement 3)
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = ClinicalTeal.copy(alpha = 0.08f)),
                        border = BorderStroke(1.dp, ClinicalTeal.copy(alpha = 0.2f)),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CloudQueue, contentDescription = "Cloud", tint = ClinicalTeal, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = if (isTurkish) {
                                    "İlerlemeniz bu cihaza kaydedilir. Yedeklemek ve diğer cihazlarla senkronize etmek için giriş yapın."
                                } else {
                                    "Your progress is saved on this device. Sign in to back up and sync across devices."
                                },
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                // Safety Disclaimer Card (Requirement 15)
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.05f)),
                        border = BorderStroke(1.dp, Color.Red.copy(alpha = 0.15f)),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Info, contentDescription = null, tint = Color.Red, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isTurkish) "RESMİ BİLDİRİM" else "OFFICIAL DISCLAIMER",
                                    color = Color.Red,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isTurkish) {
                                    "Tüm içerikler anestezi board sınav hazırlığı için özgün geliştirilmiştir. Resmi ABA, ESAIC, EDAIC organizasyonlarından bağımsızdır. Klinik karar destek sistemi değildir."
                                } else {
                                    "All content is original educational board-style material. It is not official ABA, ESAIC, EDAIC, or board-endorsed content. It is not intended for real-time clinical decision-making."
                                },
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 10.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }
            }

            // Premium Gate Overlay Sheet / Dialog
            if (showPremiumGate) {
                Dialog(
                    onDismissRequest = { showPremiumGate = false },
                    properties = DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    PremiumGateScreen(
                        onDismiss = { showPremiumGate = false },
                        onUnlockSuccess = { refreshStats() },
                        onStartFreePreview = {
                            onNavigateToQuiz("ALL", "STUDY", null, null, null, false, false, false)
                        }
                    )
                }
            }

            // Copyright Disclaimer Modal on first launch
            if (showDisclaimerAlert) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { 
                        Text(
                            text = if (isTurkish) "Telif Hakları ve Sorumluluk Reddi" else "Copyright & Safety Disclaimer",
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        ) 
                    },
                    text = {
                        Text(
                            text = if (isTurkish) {
                                "Bu bölüm sadece eğitim amaçlı özgün anestezi sınav hazırlık sorularını içermektedir. " +
                                "Buradaki sorular hiçbir şekilde ABA, ESAIC, EDAIC veya başka bir resmi kurum, ders kitabı veya ticari soru bankasının sorularını kopyalamaz, çoğaltmaz veya temsil etmez. " +
                                "Telif hakkı güvenliğini tam sağlamak adına tüm içerikler sıfırdan özgün olarak yazılmıştır. Herhangi bir resmi kurumla doğrudan veya dolaylı ortaklığı yoktur."
                            } else {
                                "This section contains original anesthesia board prep questions written solely for educational purposes. " +
                                "These questions do NOT copy, reproduce, or represent official questions from the ABA, ESAIC, EDAIC, or any official textbook/commercial bank. " +
                                "All content is newly authored to protect copyright safety and ensure original learning. No official endorsement is claimed."
                            },
                            fontSize = 13.sp
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                prefs.edit().putBoolean("board_prep_disclaimer_accepted", true).apply()
                                showDisclaimerAlert = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = DeepNavy)
                        ) {
                            Text(text = if (isTurkish) "Okudum, Kabul Ediyorum" else "I Accept")
                        }
                    }
                )
            }

            // Pack Browser & Advanced Filters Modal Panel
            if (showPackBrowser) {
                Dialog(
                    onDismissRequest = { showPackBrowser = false },
                    properties = DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DeepNavy)
                            .padding(16.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Header
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isTurkish) "Paket Tarayıcı ve Filtreler" else "Pack Browser & Filters",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(onClick = { showPackBrowser = false }) {
                                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                                }
                            }
                            
                            // Segment selector
                            var activeSubTab by remember { mutableStateOf(0) } // 0: Packs, 1: Custom Filter
                             TabRow(
                                selectedTabIndex = activeSubTab,
                                containerColor = Color.Transparent,
                                contentColor = SoftGold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                Tab(
                                    selected = activeSubTab == 0,
                                    onClick = { activeSubTab = 0 },
                                    selectedContentColor = SoftGold,
                                    unselectedContentColor = Color.White.copy(alpha = 0.4f),
                                    text = {
                                        Text(
                                            text = if (isTurkish) "Paketler" else "Installed Packs",
                                            fontWeight = if (activeSubTab == 0) FontWeight.ExtraBold else FontWeight.Medium,
                                            fontSize = 13.sp
                                        )
                                    }
                                )
                                Tab(
                                    selected = activeSubTab == 1,
                                    onClick = { activeSubTab = 1 },
                                    selectedContentColor = SoftGold,
                                    unselectedContentColor = Color.White.copy(alpha = 0.4f),
                                    text = {
                                        Text(
                                            text = if (isTurkish) "Özel Filtre" else "Custom Filters",
                                            fontWeight = if (activeSubTab == 1) FontWeight.ExtraBold else FontWeight.Medium,
                                            fontSize = 13.sp
                                        )
                                    }
                                )
                            }

                            Box(modifier = Modifier.weight(1f)) {
                                if (activeSubTab == 0) {
                                    // Pack Browser Segment
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        items(richPacks) { pack ->
                                            val packId = pack["packId"]?.toString() ?: ""
                                            val title = if (isTurkish) pack["titleTr"]?.toString() ?: "" else pack["titleEn"]?.toString() ?: ""
                                            val bType = pack["boardType"]?.toString() ?: ""
                                            val ver = pack["version"]?.toString() ?: ""
                                            val qCount = pack["questionCount"] as? Int ?: 0
                                            val sCount = pack["solvedCount"] as? Int ?: 0
                                            val fmt = pack["format"]?.toString() ?: ""
                                            val sha = pack["sha256"]?.toString() ?: ""
                                            
                                            Card(
                                                shape = RoundedCornerShape(12.dp),
                                                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
                                                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Column(modifier = Modifier.padding(14.dp)) {
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceBetween,
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = title,
                                                            color = Color.White,
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            maxLines = 1,
                                                            overflow = TextOverflow.Ellipsis,
                                                            modifier = Modifier.weight(1f)
                                                        )
                                                        Text(text = ver, color = SoftGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                    
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                    ) {
                                                        Text(text = "Board: $bType", color = Color.White.copy(alpha = 0.5f), fontSize = 10.sp)
                                                        Text(text = "|", color = Color.White.copy(alpha = 0.2f), fontSize = 10.sp)
                                                        Text(text = "Format: $fmt", color = Color.White.copy(alpha = 0.5f), fontSize = 10.sp)
                                                        Text(text = "|", color = Color.White.copy(alpha = 0.2f), fontSize = 10.sp)
                                                        Text(text = "SHA: $sha", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                                                    }
                                                    
                                                    Spacer(modifier = Modifier.height(10.dp))
                                                    
                                                    // Progress bar
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        LinearProgressIndicator(
                                                            progress = if (qCount > 0) sCount.toFloat() / qCount else 0f,
                                                            color = ClinicalTeal,
                                                            trackColor = Color.White.copy(alpha = 0.1f),
                                                            modifier = Modifier.weight(1f).height(6.dp).background(Color.Transparent, RoundedCornerShape(4.dp))
                                                        )
                                                        Spacer(modifier = Modifier.width(8.dp))
                                                        Text(
                                                            text = "$sCount / $qCount solved",
                                                            color = Color.White.copy(alpha = 0.6f),
                                                            fontSize = 10.sp
                                                        )
                                                    }
                                                    
                                                    Spacer(modifier = Modifier.height(12.dp))
                                                    
                                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                                        Button(
                                                            onClick = {
                                                                showPackBrowser = false
                                                                onNavigateToQuiz(bType, "STUDY", packId, null, null, false, false, false)
                                                            },
                                                            colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                                                            shape = RoundedCornerShape(6.dp),
                                                            modifier = Modifier.weight(1f)
                                                        ) {
                                                            Text(text = if (isTurkish) "Çalışma Modu" else "Study", fontSize = 11.sp)
                                                        }
                                                        Button(
                                                            onClick = {
                                                                showPackBrowser = false
                                                                onNavigateToQuiz(bType, "EXAM", packId, null, null, false, false, false)
                                                            },
                                                            colors = ButtonDefaults.buttonColors(containerColor = SoftGold),
                                                            shape = RoundedCornerShape(6.dp),
                                                            modifier = Modifier.weight(1f)
                                                        ) {
                                                            Text(text = if (isTurkish) "Sınav Modu" else "Exam", fontSize = 11.sp, color = DeepNavy)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    // Custom Filters Segment
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.spacedBy(14.dp)
                                    ) {
                                        // 1. Board Type Filter
                                        item {
                                            Text(text = if (isTurkish) "Board Türü" else "Board Exam Type", color = SoftGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                                                listOf("ALL", "EDAIC_PART_I", "ABA_BASIC", "ABA_ADVANCED", "EDAIC_VIVA").forEach { bt ->
                                                    Box(
                                                        modifier = Modifier
                                                            .background(if (customBoardType == bt) ClinicalTeal else Color.White.copy(alpha = 0.05f), RoundedCornerShape(6.dp))
                                                            .clickable { customBoardType = bt }
                                                            .padding(horizontal = 8.dp, vertical = 6.dp)
                                                    ) {
                                                        Text(text = bt.replace("_", " "), color = Color.White, fontSize = 10.sp)
                                                    }
                                                }
                                            }
                                        }
                                        
                                        // 2. Category Filter
                                        item {
                                            Text(text = if (isTurkish) "Tıbbi Kategori" else "Clinical Category", color = SoftGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            FlowRow(
                                                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                                verticalArrangement = Arrangement.spacedBy(6.dp)
                                            ) {
                                                (listOf("ALL") + categoriesList).forEach { cat ->
                                                    Box(
                                                        modifier = Modifier
                                                            .background(if (customCategory == cat) ClinicalTeal else Color.White.copy(alpha = 0.05f), RoundedCornerShape(6.dp))
                                                            .clickable { customCategory = cat }
                                                            .padding(horizontal = 8.dp, vertical = 6.dp)
                                                    ) {
                                                        Text(text = cat, color = Color.White, fontSize = 10.sp)
                                                    }
                                                }
                                            }
                                        }
                                        
                                        // 3. Difficulty Filter
                                        item {
                                            Text(text = if (isTurkish) "Zorluk Derecesi" else "Difficulty Level", color = SoftGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                                                listOf("ALL", "easy", "medium", "hard").forEach { diff ->
                                                    Box(
                                                        modifier = Modifier
                                                            .background(if (customDifficulty.lowercase() == diff.lowercase()) ClinicalTeal else Color.White.copy(alpha = 0.05f), RoundedCornerShape(6.dp))
                                                            .clickable { customDifficulty = diff }
                                                            .padding(horizontal = 10.dp, vertical = 6.dp)
                                                    ) {
                                                        Text(text = diff.uppercase(), color = Color.White, fontSize = 10.sp)
                                                    }
                                                }
                                            }
                                        }
                                        
                                        // 4. Checkbox toggles for bookmarked / unanswered / incorrect
                                        item {
                                            Text(text = if (isTurkish) "Soru Kriterleri" else "Question Criteria Filters", color = SoftGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            Column(modifier = Modifier.padding(top = 4.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Checkbox(
                                                        checked = customBookmarkedOnly,
                                                        onCheckedChange = { customBookmarkedOnly = it },
                                                        colors = CheckboxDefaults.colors(checkedColor = ClinicalTeal)
                                                    )
                                                    Text(text = if (isTurkish) "Sadece Favoriler" else "Bookmarked Only", color = Color.White, fontSize = 12.sp)
                                                }
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Checkbox(
                                                        checked = customUnansweredOnly,
                                                        onCheckedChange = { customUnansweredOnly = it },
                                                        colors = CheckboxDefaults.colors(checkedColor = ClinicalTeal)
                                                    )
                                                    Text(text = if (isTurkish) "Sadece Çözülmemişler" else "Unanswered Only", color = Color.White, fontSize = 12.sp)
                                                }
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Checkbox(
                                                        checked = customIncorrectOnly,
                                                        onCheckedChange = { customIncorrectOnly = it },
                                                        colors = CheckboxDefaults.colors(checkedColor = ClinicalTeal)
                                                    )
                                                    Text(text = if (isTurkish) "Sadece Hatalı Çözülmüşler" else "Incorrect/Failed Only", color = Color.White, fontSize = 12.sp)
                                                }
                                            }
                                        }
                                        
                                        // 5. Quiz session mode
                                        item {
                                            Text(text = if (isTurkish) "Oynanış Modu" else "Gameplay Mode Selection", color = SoftGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                                                Button(
                                                    onClick = { customMode = "STUDY" },
                                                    colors = ButtonDefaults.buttonColors(containerColor = if (customMode == "STUDY") ClinicalTeal else Color.White.copy(alpha = 0.05f)),
                                                    shape = RoundedCornerShape(6.dp)
                                                ) {
                                                    Text(text = if (isTurkish) "Çalışma Modu" else "Study Mode", fontSize = 11.sp)
                                                }
                                                Button(
                                                    onClick = { customMode = "EXAM" },
                                                    colors = ButtonDefaults.buttonColors(containerColor = if (customMode == "EXAM") SoftGold else Color.White.copy(alpha = 0.05f)),
                                                    shape = RoundedCornerShape(6.dp)
                                                ) {
                                                    Text(text = if (isTurkish) "Sınav Modu" else "Exam Mode", fontSize = 11.sp, color = if (customMode == "EXAM") DeepNavy else Color.White)
                                                }
                                            }
                                        }
                                        
                                        // Launch session button
                                        item {
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Button(
                                                onClick = {
                                                    showPackBrowser = false
                                                    onNavigateToQuiz(
                                                        customBoardType,
                                                        customMode,
                                                        if (customPackId == "ALL") null else customPackId,
                                                        if (customCategory == "ALL") null else customCategory,
                                                        if (customDifficulty == "ALL") null else customDifficulty,
                                                        customBookmarkedOnly,
                                                        customUnansweredOnly,
                                                        customIncorrectOnly
                                                    )
                                                },
                                                colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier.fillMaxWidth().height(48.dp)
                                            ) {
                                                Icon(Icons.Default.PlayArrow, contentDescription = null)
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    text = if (isTurkish) "Özel Filtreli Oturumu Başlat" else "Launch Filtered Quiz Session",
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold
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
    }
}

@Composable
fun BoardDashboardCard(
    title: String,
    questionCount: Int,
    solvedCount: Int,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    isTurkish: Boolean,
    isPremium: Boolean,
    showPreviewBadge: Boolean = false,
    previewCount: Int = 0,
    isLocked: Boolean = false,
    onNavigate: (String) -> Unit // mode: "STUDY" or "EXAM"
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
        border = BorderStroke(1.dp, if (isLocked) Color.White.copy(alpha = 0.05f) else Color.White.copy(alpha = 0.1f)),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    
                    if (isLocked) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Locked",
                            tint = SoftGold,
                            modifier = Modifier.size(16.dp)
                        )
                    } else if (showPreviewBadge) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .background(ClinicalTeal.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = if (isTurkish) "Önizleme" else "Preview",
                                color = ClinicalTeal,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                
                Text(
                    text = if (isLocked) {
                        if (isTurkish) "Kilitli" else "Locked"
                    } else {
                        val countLabel = if (showPreviewBadge) "$previewCount" else "$questionCount"
                        "$solvedCount / $countLabel " + (if (isTurkish) "Çözüldü" else "Solved")
                    },
                    color = if (isLocked) Color.White.copy(alpha = 0.4f) else SoftGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Progress Bar
            Spacer(modifier = Modifier.height(10.dp))
            val progress = if (isLocked) 0f else {
                val maxQuestions = if (showPreviewBadge) previewCount else questionCount
                if (maxQuestions > 0) solvedCount.toFloat() / maxQuestions else 0f
            }
            LinearProgressIndicator(
                progress = progress,
                color = accentColor,
                trackColor = Color.White.copy(alpha = 0.06f),
                modifier = Modifier.fillMaxWidth().height(5.dp).background(Color.Transparent, RoundedCornerShape(4.dp))
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { onNavigate("STUDY") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isLocked) Color.White.copy(alpha = 0.06f) else ClinicalTeal,
                        contentColor = if (isLocked) Color.White.copy(alpha = 0.3f) else Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = if (isTurkish) "Çalışma" else "Study", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { onNavigate("EXAM") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isLocked) Color.White.copy(alpha = 0.06f) else SoftGold,
                        contentColor = if (isLocked) Color.White.copy(alpha = 0.3f) else DeepNavy
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = if (isTurkish) "Sınav" else "Exam", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}



