package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug
import com.anesthesiaclinic.anesthesiabriefs.data.repository.FirestoreRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*
import com.anesthesiaclinic.anesthesiabriefs.utils.TranslationHelper
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugDetailScreen(
    drugId: String,
    currentLanguage: String,
    onBackClick: () -> Unit
) {
    val drug = remember(drugId) { FirestoreRepository.getDrugById(drugId) } ?: return
    val isTurkish = currentLanguage == "tr"
    fun t(text: String): String = TranslationHelper.translate(text, currentLanguage)
    fun t(tr: String, en: String): String = if (isTurkish) tr else en


    var isFavorite by remember { mutableStateOf(FirestoreRepository.isFavorite(drug.drugId)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(t(drug.nameTr, drug.nameEn), style = MaterialTheme.typography.titleLarge, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = t("Geri", "Back"), tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        FirestoreRepository.toggleFavorite(drug.drugId)
                        isFavorite = !isFavorite
                    }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = t("Favori", "Favorite"),
                            tint = if (isFavorite) SoftGold else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WarmSand)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Header Badge Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ClinicalTeal.copy(alpha = 0.15f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = t(drug.category).uppercase(),
                        color = ClinicalTeal,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = drug.atcCode ?: t("ATC Kod Yok", "No ATC Code"),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondaryLight
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = drug.genericName,
                style = MaterialTheme.typography.headlineMedium,
                color = DeepNavy,
                fontWeight = FontWeight.Bold
            )
            if (drug.brandNames.isNotEmpty()) {
                Text(
                    text = "${t("Piyasa İsimleri", "Brand Names")}: ${drug.brandNames.joinToString(", ")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondaryLight,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = t(drug.drugClass ?: ""),
                style = MaterialTheme.typography.bodyLarge,
                color = ClinicalTeal,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contraindications / Warnings Box (Red Box if any)
            if (drug.warnings.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFDF2F2))
                        .border(1.dp, CriticalRed.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Text(
                            text = t("⚠️ KRİTİK UYARILAR & PRIS RİSKLERİ", "⚠️ CRITICAL WARNINGS & PRIS RISKS"),
                            fontWeight = FontWeight.Bold,
                            color = CriticalRed,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        drug.warnings.forEach { warn ->
                            Text(
                                text = "• ${t(warn)}",
                                color = TextPrimaryLight,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Dosing Info Cards
            Text(
                text = t("Klinik Dozlama Rehberi", "Clinical Dosing Guide"),
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // Adult Dosing Card
            if (drug.adultDoses.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = DeepNavy)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(t("Yetişkin Dozları", "Adult Doses"), style = MaterialTheme.typography.titleLarge, color = DeepNavy, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        drug.adultDoses.forEach { (type, info) ->
                            Text(text = TranslationHelper.translateDoseKey(type, currentLanguage), fontWeight = FontWeight.Bold, color = ClinicalTeal, fontSize = 13.sp)
                            AnnotatedDoseText(text = t(info.dose), modifier = Modifier.padding(vertical = 4.dp))
                            if (info.notes != null) {
                                Text(text = t(info.notes), color = TextSecondaryLight, fontSize = 12.sp, modifier = Modifier.padding(bottom = 8.dp))
                            } else {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }

            // Pediatric Dosing Card
            if (drug.pediatricDoses.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ChildCare, contentDescription = null, tint = DeepNavy)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(t("Pediatrik Dozlar", "Pediatric Doses"), style = MaterialTheme.typography.titleLarge, color = DeepNavy, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        drug.pediatricDoses.forEach { (type, info) ->
                            Text(text = TranslationHelper.translateDoseKey(type, currentLanguage), fontWeight = FontWeight.Bold, color = ClinicalTeal, fontSize = 13.sp)
                            AnnotatedDoseText(text = t(info.dose), modifier = Modifier.padding(vertical = 4.dp))
                            if (info.notes != null) {
                                Text(text = t(info.notes), color = TextSecondaryLight, fontSize = 12.sp, modifier = Modifier.padding(bottom = 8.dp))
                            } else {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Pharmacokinetics Card
            drug.pharmacokinetics?.let { pk ->
                Text(
                    text = t("Farmakokinetik Profili", "Pharmacokinetics Profile"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        PkRow(t("Etki Başlangıcı (Onset)", "Onset of Action"), t(pk.onset ?: "-"))
                        PkRow(t("Zirve Etki (Peak)", "Peak Effect"), t(pk.peak ?: "-"))
                        PkRow(t("Etki Süresi (Duration)", "Duration of Action"), t(pk.duration ?: "-"))
                        PkRow(t("Yarı Ömür (Half Life)", "Half-Life"), t(pk.halfLife ?: "-"))
                        PkRow(t("Eliminasyon", "Elimination"), t(pk.elimination ?: "-"))
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Clinical Pearls
            if (drug.clinicalPearls.isNotEmpty()) {
                Text(
                    text = t("Klinik İpuçları (Pearls)", "Clinical Pearls"),
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Linen)
                        .border(1.dp, BorderSand, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        drug.clinicalPearls.forEach { pearl ->
                            Row {
                                Text("💡 ", fontSize = 16.sp)
                                Text(
                                    text = t(pearl),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = TextPrimaryLight
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PkRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge, color = TextSecondaryLight)
        Text(value, style = MaterialTheme.typography.bodyLarge, color = DeepNavy, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AnnotatedDoseText(text: String, modifier: Modifier = Modifier) {
    val lines = text.replace("\\n", "\n").split("\n")
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        lines.forEach { line ->
            if (line.trim().isEmpty()) return@forEach
            
            val trimmed = line.trim()
            if (trimmed.startsWith("•") && trimmed.contains(":")) {
                val bulletIndex = trimmed.indexOf("•")
                val colonIndex = trimmed.indexOf(":")
                val header = trimmed.substring(bulletIndex + 1, colonIndex).trim()
                val body = trimmed.substring(colonIndex).trim()
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "• ",
                        color = ClinicalTeal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = DeepNavy)) {
                                append(header)
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = TextPrimaryLight)) {
                                append(body)
                            }
                        },
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            } else {
                Text(
                    text = trimmed,
                    color = TextPrimaryLight,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
