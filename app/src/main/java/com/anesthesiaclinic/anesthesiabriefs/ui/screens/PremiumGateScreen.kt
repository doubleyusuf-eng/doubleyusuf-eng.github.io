package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepAccessManager
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.ClinicalTeal
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.DeepNavy
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.SoftGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumGateScreen(
    onDismiss: () -> Unit,
    onUnlockSuccess: () -> Unit = {},
    onStartFreePreview: () -> Unit = {}
) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", Context.MODE_PRIVATE) }
    val isTurkish = remember { prefs.getString("settings_language", "en") == "tr" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isTurkish) "Premium Üyeliğe Yükselt" else "Go Premium",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
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
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
            ) {
                // Header Crown / Icon
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .background(SoftGold.copy(alpha = 0.15f), RoundedCornerShape(36.dp))
                            .border(2.dp, SoftGold, RoundedCornerShape(36.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Premium Icon",
                            tint = SoftGold,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }

                // Title
                item {
                    Text(
                        text = if (isTurkish) "Board Hazırlık Premium Kilidini Aç" else "Unlock Board Prep Premium",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

                // Subtitle
                item {
                    Text(
                        text = if (isTurkish) {
                            "Eksiksiz EDAIC, ABA BASIC, ABA ADVANCED ve Viva-style anestezi board sınavı hazırlığına erişin."
                        } else {
                            "Access full EDAIC, ABA BASIC, ABA ADVANCED, and Viva-style anesthesia board preparation."
                        },
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                // Features Card
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
                        border = CardDefaults.outlinedCardBorder(enabled = true).copy(
                            brush = Brush.horizontalGradient(listOf(ClinicalTeal.copy(alpha = 0.3f), Color.Transparent))
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            val bullets = if (isTurkish) listOf(
                                "100+ sınav tarzı anestezi sorusu",
                                "EDAIC Paper A ve Paper B MTF pratikleri",
                                "ABA BASIC ve ADVANCED SBA soruları",
                                "EDAIC Viva sözlü sınav senaryoları",
                                "Detaylı açıklamalar ve sınav tuzakları",
                                "Sınav Modu ve detaylı ilerleme analitiği",
                                "Zayıf konu takibi ve hedeflenmiş çalışma",
                                "Favoriye eklenen soruları gözden geçirme",
                                "İsteğe bağlı bulut yedekleme ve senkronizasyon"
                            ) else listOf(
                                "100+ board-style anesthesia questions",
                                "EDAIC Paper A and Paper B MTF practice",
                                "ABA BASIC and ADVANCED SBA practice",
                                "EDAIC Viva oral exam scenarios",
                                "Detailed explanations and exam traps",
                                "Exam Mode and progress analytics",
                                "Weak topic tracking",
                                "Bookmarked question review",
                                "Optional cloud backup and sync"
                            )

                            bullets.forEach { bullet ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "Check",
                                        tint = ClinicalTeal,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = bullet,
                                        color = Color.White,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }

                // Primary Purchase Buttons
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            // TODO: Replace with Google Play Billing / validated entitlement before production release.
                            BoardPrepAccessManager.setPremiumUnlocked(context, true)
                            Toast.makeText(
                                context,
                                if (isTurkish) "Board Prep Premium simülasyonu başarıyla etkinleştirildi!" else "Board Prep Premium simulated unlock successful!",
                                Toast.LENGTH_LONG
                            ).show()
                            onUnlockSuccess()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = SoftGold, contentColor = DeepNavy),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = if (isTurkish) "Premium Sürümü Etkinleştir (Simüle)" else "Unlock Premium (Simulated)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }

                // Simulated Unlock Info text
                item {
                    Text(
                        text = if (isTurkish) "[Simülasyon Modu] Gerçek bir ödeme sistemi değildir. Test etmek için tıklayın."
                               else "[Simulation Mode] Not a real payment system. Tap to simulate unlock for testing.",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                // Auxiliary Actions Row
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                // TODO: Replace with Google Play Billing / validated entitlement before production release.
                                // Restore Purchase simulation
                                BoardPrepAccessManager.setPremiumUnlocked(context, true)
                                Toast.makeText(
                                    context,
                                    if (isTurkish) "Satın alma simülasyonu başarıyla geri yüklendi!" else "Purchase simulation restored successfully!",
                                    Toast.LENGTH_LONG
                                ).show()
                                onUnlockSuccess()
                                onDismiss()
                            },
                            border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
                                brush = Brush.horizontalGradient(listOf(ClinicalTeal.copy(alpha = 0.6f), ClinicalTeal.copy(alpha = 0.3f)))
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                        ) {
                            Text(
                                text = if (isTurkish) "Satın Almaları Geri Yükle" else "Restore Purchase",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = {
                                onStartFreePreview()
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.08f)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                        ) {
                            Text(
                                text = if (isTurkish) "Ücretsiz Önizlemeyi Başlat" else "Start Free Preview",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Maybe Later
                item {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = if (isTurkish) "Daha Sonra" else "Maybe Later",
                            color = Color.White.copy(alpha = 0.6f),
                            fontSize = 13.sp
                        )
                    }
                }

                // Safety Disclaimer (Requirement 15)
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.08f)),
                        border = CardDefaults.outlinedCardBorder(enabled = true).copy(
                            brush = Brush.horizontalGradient(listOf(Color.Red.copy(alpha = 0.3f), Color.Transparent))
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Warning, contentDescription = "Warning", tint = Color.Red, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isTurkish) "RESMİ BİLDİRİM VE SORUMLULUK REDDİ" else "OFFICIAL DISCLAIMER & SAFETY NOTICE",
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isTurkish) {
                                    "Tüm içerikler eğitim amaçlı orijinal sınav tarzı materyallerdir. Resmi ABA, ESAIC veya EDAIC kuruluşu tarafından onaylanmış veya bunlara ait değildir. Gerçek zamanlı klinik karar verme süreçleri için tasarlanmamıştır."
                                } else {
                                    "All content is original educational board-style material. It is not official ABA, ESAIC, Edaic, or board-endorsed content. It is not intended for real-time clinical decision-making."
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
