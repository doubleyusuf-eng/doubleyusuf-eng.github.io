package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDatabase
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.anesthesiaclinic.anesthesiabriefs.data.sync.FirebaseSyncService
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.ClinicalTeal
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.DeepNavy
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.SoftGold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardPrepSettingsScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", Context.MODE_PRIVATE) }

    var questionLang by remember { mutableStateOf(prefs.getString("board_prep_question_lang", "en") ?: "en") }
    var explanationLang by remember { mutableStateOf(prefs.getString("board_prep_explanation_lang", "both") ?: "both") }

    var isCheckingUpdates by remember { mutableStateOf(false) }
    var updateStatusMessage by remember { mutableStateOf<String?>(null) }
    var lastSyncTime by remember { mutableStateOf(prefs.getLong("board_prep_last_sync", 0L)) }

    var installedPacks by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
    var showResetDialog by remember { mutableStateOf(false) }
    var alsoDeleteProgress by remember { mutableStateOf(false) }

    // Load pack details from local metadata database
    val loadInstalledPacks = {
        scope.launch {
            val db = BoardPrepDatabase.getDatabase(context)
            val metadataList = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                db.boardPrepDao().getAllPackMetadata()
            }
            installedPacks = metadataList.map { meta ->
                mapOf(
                    "packId" to meta.packId.uppercase().replace("_", " "),
                    "version" to "v${meta.version}",
                    "sha256" to if (meta.checksumSha256.length > 10) meta.checksumSha256.take(8) + "..." else "N/A"
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        loadInstalledPacks()
    }

    val isTurkish = prefs.getString("settings_language", "en") == "tr"

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = {
                Text(
                    text = if (isTurkish) "Önbelleği Sıfırla" else "Reset Cache",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = if (isTurkish) {
                            "Soru önbelleğini sıfırlamak istediğinize emin misiniz? Bu işlem yerel soru dosyalarını temizler."
                        } else {
                            "Are you sure you want to reset the local question cache? This will clean up the local question files."
                        },
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 13.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clickable { alsoDeleteProgress = !alsoDeleteProgress }
                    ) {
                        Checkbox(
                            checked = alsoDeleteProgress,
                            onCheckedChange = { alsoDeleteProgress = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = ClinicalTeal,
                                uncheckedColor = Color.White.copy(alpha = 0.6f)
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isTurkish) {
                                "Öğrenme geçmişimi ve favorilerimi de sil"
                            } else {
                                "Also delete my learning history & bookmarks"
                            },
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showResetDialog = false
                        scope.launch {
                            BoardPrepRepository.resetLocalCache(context)
                            if (alsoDeleteProgress) {
                                BoardPrepRepository.clearAllProgress(context)
                            }
                            prefs.edit().putBoolean("board_prep_seeded_v13", false).apply()
                            updateStatusMessage = if (isTurkish) {
                                if (alsoDeleteProgress) "Tüm veriler ve soru önbelleği temizlendi!"
                                else "Soru önbelleği temizlendi! Sınav ilerlemeniz ve favorileriniz korundu."
                            } else {
                                if (alsoDeleteProgress) "All question cache and history have been fully reset!"
                                else "Local cache cleared successfully! Progress and bookmarks preserved."
                            }
                            loadInstalledPacks()
                        }
                    }
                ) {
                    Text(
                        text = if (isTurkish) "Sıfırla" else "Reset",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text(
                        text = if (isTurkish) "İptal" else "Cancel",
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            },
            containerColor = DeepNavy,
            shape = RoundedCornerShape(16.dp)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = if (isTurkish) "Board Hazırlık Ayarları" else "Board Prep Settings",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
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
                contentPadding = PaddingValues(bottom = 32.dp, top = 16.dp)
            ) {
                // Seeding & Sync segment
                item {
                    Text(
                        text = if (isTurkish) "Bulut Eşitleme" else "Cloud Synchronization",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = if (isTurkish) "Son Güncelleme Kontrolü" else "Last Update Check",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = if (lastSyncTime == 0L) {
                                            if (isTurkish) "Hiç eşitleme yapılmadı" else "Never synchronized"
                                        } else {
                                            val formatter = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault())
                                            formatter.format(java.util.Date(lastSyncTime))
                                        },
                                        color = Color.White.copy(alpha = 0.6f),
                                        fontSize = 12.sp
                                    )
                                }

                                if (isCheckingUpdates) {
                                    CircularProgressIndicator(color = SoftGold, modifier = Modifier.size(24.dp))
                                } else {
                                    Button(
                                        onClick = {
                                            isCheckingUpdates = true
                                            updateStatusMessage = null
                                            scope.launch {
                                                try {
                                                    val packs = listOf("edaic_part_i", "aba_basic", "aba_advanced", "viva_scenarios")
                                                    var updateFound = false
                                                    for (p in packs) {
                                                        val manifest = FirebaseSyncService.checkForUpdates(context, p)
                                                        if (manifest != null) {
                                                            FirebaseSyncService.syncPack(context, manifest)
                                                            updateFound = true
                                                        }
                                                    }
                                                    
                                                    val syncTime = System.currentTimeMillis()
                                                    prefs.edit().putLong("board_prep_last_sync", syncTime).apply()
                                                    lastSyncTime = syncTime
                                                    
                                                    updateStatusMessage = if (updateFound) {
                                                        if (isTurkish) "Yeni güncelleme başarıyla yüklendi!" else "Updates successfully downloaded!"
                                                    } else {
                                                        if (isTurkish) "Tüm soru paketleri güncel." else "All packs are up to date."
                                                    }
                                                    loadInstalledPacks()
                                                } catch (e: SecurityException) {
                                                    // Refinement 8 check: failed validation keeps previous local working files safely
                                                    updateStatusMessage = if (isTurkish) "Hata: SHA-256 doğrulanamadı!" else "Security Error: SHA-256 signature mismatch!"
                                                } catch (e: Exception) {
                                                    updateStatusMessage = if (isTurkish) "Güncelleme başarısız oldu!" else "Sync failed!"
                                                } finally {
                                                    isCheckingUpdates = false
                                                }
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = if (isTurkish) "Güncellemeleri Al" else "Check Now", fontSize = 12.sp)
                                    }
                                }
                            }

                            updateStatusMessage?.let { msg ->
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = msg,
                                    color = if (msg.contains("Hata") || msg.contains("failed") || msg.contains("Error")) Color.Red else ClinicalTeal,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                // Installed Packs Inventory
                item {
                    Text(
                        text = if (isTurkish) "Yüklü Soru Paketleri" else "Installed Packs Inventory",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (installedPacks.isEmpty()) {
                                Text(
                                    text = if (isTurkish) "Hiç paket yüklenmemiş." else "No packs loaded.",
                                    color = Color.White.copy(alpha = 0.5f),
                                    fontSize = 12.sp
                                )
                            } else {
                                installedPacks.forEach { pack ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = pack["packId"] ?: "", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                        Text(text = pack["version"] ?: "", color = SoftGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                    }
                                    HorizontalDivider(color = Color.White.copy(alpha = 0.1f), modifier = Modifier.padding(vertical = 4.dp))
                                }
                            }
                        }
                    }
                }

                // Bilingual Preference Section (Refinement 7)
                item {
                    Text(
                        text = if (isTurkish) "Bağımsız Dil Seçenekleri" else "Independent Language Settings",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // 1. Question Language Selection
                            Text(
                                text = if (isTurkish) "Soru Dili" else "Question Language",
                                color = SoftGold,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = questionLang == "en",
                                        onClick = {
                                            questionLang = "en"
                                            prefs.edit().putString("board_prep_question_lang", "en").apply()
                                        },
                                        colors = RadioButtonDefaults.colors(selectedColor = ClinicalTeal, unselectedColor = Color.White.copy(alpha = 0.6f))
                                    )
                                    Text(text = "English", color = Color.White, fontSize = 13.sp)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = questionLang == "tr",
                                        onClick = {
                                            questionLang = "tr"
                                            prefs.edit().putString("board_prep_question_lang", "tr").apply()
                                        },
                                        colors = RadioButtonDefaults.colors(selectedColor = ClinicalTeal, unselectedColor = Color.White.copy(alpha = 0.6f))
                                    )
                                    Text(text = "Türkçe", color = Color.White, fontSize = 13.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // 2. Explanation Language Selection
                            Text(
                                text = if (isTurkish) "Açıklama Dili" else "Explanation Language",
                                color = SoftGold,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = explanationLang == "en",
                                        onClick = {
                                            explanationLang = "en"
                                            prefs.edit().putString("board_prep_explanation_lang", "en").apply()
                                        },
                                        colors = RadioButtonDefaults.colors(selectedColor = ClinicalTeal, unselectedColor = Color.White.copy(alpha = 0.6f))
                                    )
                                    Text(text = if (isTurkish) "Sadece İngilizce Göster" else "Show English Only", color = Color.White, fontSize = 13.sp)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = explanationLang == "tr",
                                        onClick = {
                                            explanationLang = "tr"
                                            prefs.edit().putString("board_prep_explanation_lang", "tr").apply()
                                        },
                                        colors = RadioButtonDefaults.colors(selectedColor = ClinicalTeal, unselectedColor = Color.White.copy(alpha = 0.6f))
                                    )
                                    Text(text = if (isTurkish) "Sadece Türkçe Göster" else "Show Turkish Only", color = Color.White, fontSize = 13.sp)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = explanationLang == "both",
                                        onClick = {
                                            explanationLang = "both"
                                            prefs.edit().putString("board_prep_explanation_lang", "both").apply()
                                        },
                                        colors = RadioButtonDefaults.colors(selectedColor = ClinicalTeal, unselectedColor = Color.White.copy(alpha = 0.6f))
                                    )
                                    Text(text = if (isTurkish) "İki Dili de Göster" else "Show Both Languages", color = Color.White, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }

                // Maintenance & Cache Reset
                item {
                    Text(
                        text = if (isTurkish) "Sistem Bakımı" else "System Maintenance",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = if (isTurkish) {
                                    "Yerel önbellek sıfırlandığında soru kataloğu silinir ancak öğrenme geçmişiniz, işaretlediğiniz zorluklar ve favori sorularınız tamamen korunur."
                                } else {
                                    "Resetting the question cache clears offline data files but preserves all your historical attempts, difficult tags, and bookmarks intact."
                                },
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 12.sp
                            )
                            Button(
                                onClick = {
                                    alsoDeleteProgress = false
                                    showResetDialog = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f)),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = if (isTurkish) "Yerel Soru Önbelleğini Sıfırla" else "Reset Local Question Cache", color = Color.White)
                            }
                        }
                    }
                }

                // Safety Disclaimer Area
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.08f)),
                        border = CardDefaults.outlinedCardBorder(enabled = true).copy(
                            brush = Brush.horizontalGradient(listOf(Color.Red.copy(alpha = 0.3f), Color.Transparent))
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Warning, contentDescription = "Warning", tint = Color.Red, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isTurkish) "RESMİ BİLDİRİM VE SORUMLULUK REDDİ" else "OFFICIAL DISCLAIMER & SAFETY NOTICE",
                                    color = Color.Red,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isTurkish) {
                                    "Bu uygulamadaki sınav soruları anestezi hekimlerinin eğitimi için özel hazırlanmış özgün içeriklerdir. " +
                                    "Sorular resmi ABA, ESAIC veya EDAIC organizasyonlarına ait ticari veya lisanslı telifli sorular değildir. " +
                                    "Buradaki bilgiler klinik rehberlik değil, yalnızca akademik gelişim amaçlıdır. Hatalı ilaç dozajlarından veya klinik kararlardan geliştiriciler sorumlu tutulamaz."
                                } else {
                                    "All questions are original intellectual properties developed independently for anesthesiologist board prep. " +
                                    "They are not copies or endorsement products of ABA, ESAIC, or EDAIC organizations. " +
                                    "The medical guidelines herein are exclusively for academic educational preparation and do not constitute clinical guidance. Always consult licensed institutional policies."
                                },
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
