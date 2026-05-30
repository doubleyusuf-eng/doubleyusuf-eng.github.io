package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentLanguage: String,
    isDarkTheme: Boolean,
    unitSystem: String,
    onLanguageChange: (String) -> Unit,
    onThemeChange: (Boolean) -> Unit,
    onUnitSystemChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val isTurkish = currentLanguage == "tr"
    fun t(tr: String, en: String): String = if (isTurkish) tr else en

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(t("Ayarlar", "Settings"), style = MaterialTheme.typography.titleLarge, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Theme Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, BorderSand),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.WbSunny, contentDescription = null, tint = SoftGold, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = t("Görünüm Teması", "Appearance Theme"),
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (!isDarkTheme) DeepNavy else Color.White)
                                .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
                                .clickable { onThemeChange(false) }
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = t("Açık Tema", "Light Theme"),
                                color = if (!isDarkTheme) Color.White else TextPrimaryLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isDarkTheme) DeepNavy else Color.White)
                                .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
                                .clickable { onThemeChange(true) }
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = t("Koyu Tema", "Dark Theme"),
                                color = if (isDarkTheme) Color.White else TextPrimaryLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Language Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, BorderSand),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Translate, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = t("Uygulama Dili", "Application Language"),
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (currentLanguage == "en") DeepNavy else Color.White)
                                .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
                                .clickable { onLanguageChange("en") }
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = "English (Default)",
                                color = if (currentLanguage == "en") Color.White else TextPrimaryLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (currentLanguage == "tr") DeepNavy else Color.White)
                                .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
                                .clickable { onLanguageChange("tr") }
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = "Türkçe",
                                color = if (currentLanguage == "tr") Color.White else TextPrimaryLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Unit System Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, BorderSand),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Straighten, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = t("Birim Sistemi", "Unit System"),
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (unitSystem == "metric") DeepNavy else Color.White)
                                .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
                                .clickable { onUnitSystemChange("metric") }
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = t("Metrik (kg, cm)", "Metric (kg, cm)"),
                                color = if (unitSystem == "metric") Color.White else TextPrimaryLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (unitSystem == "imperial") DeepNavy else Color.White)
                                .border(1.dp, BorderSand, RoundedCornerShape(8.dp))
                                .clickable { onUnitSystemChange("imperial") }
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = t("Amerikan (lb, inç)", "Imperial (lb, in)"),
                                color = if (unitSystem == "imperial") Color.White else TextPrimaryLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Support & Feedback Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, BorderSand),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = SoftGold,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = t("Destek & Öneri", "Support & Feedback"),
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = t(
                            "Geri bildirimleriniz uygulamayı geliştirmemize yardımcı olur. Sorularınızı veya önerilerinizi bize iletebilirsiniz.",
                            "Your feedback helps us improve the app. Please send us your questions or suggestions."
                        ),
                        color = TextPrimaryLight,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(1.dp, BorderSand, RoundedCornerShape(12.dp))
                            .clickable {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:support@anesthesiabriefs.com")
                                    putExtra(Intent.EXTRA_SUBJECT, t("AnesthesiaBriefs Destek / Öneri", "AnesthesiaBriefs Support / Feedback"))
                                }
                                try {
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    // Fallback
                                }
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = t("İletişim E-postası", "Contact Email"),
                                fontSize = 11.sp,
                                color = TextSecondaryLight,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "support@anesthesiabriefs.com",
                                fontSize = 14.sp,
                                color = ClinicalTeal,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        Text(
                            text = t("E-posta Gönder", "Send Email"),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy,
                            modifier = Modifier
                                .border(1.dp, DeepNavy, RoundedCornerShape(6.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }

        }
    }
}
