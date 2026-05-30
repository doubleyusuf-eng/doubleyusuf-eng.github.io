package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*

@Composable
fun DisclaimerScreen(
    currentLanguage: String = "tr",
    onLanguageChange: (String) -> Unit = {},
    onAccept: () -> Unit
) {
    var accepted by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val isTurkish = currentLanguage == "tr"
    fun t(tr: String, en: String): String = if (isTurkish) tr else en

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmSand)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 150.dp)
        ) {
            // Header
            Spacer(modifier = Modifier.height(24.dp))
            AppLogo(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                textSizeSp = 30,
                iconSizeDp = 44
            )
            Text(
                text = t("Klinik Referans & Eğitim Platformu", "Clinical Reference & Education Platform"),
                style = MaterialTheme.typography.labelLarge,
                color = ClinicalTeal,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 12.dp)
            )

            // Language Selector Row
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Linen)
                    .border(1.dp, BorderSand, RoundedCornerShape(20.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("tr" to "TÜRKÇE", "en" to "ENGLISH").forEach { (langCode, label) ->
                    val isSelected = currentLanguage == langCode
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) DeepNavy else Color.Transparent
                        ),
                        modifier = Modifier
                            .width(100.dp)
                            .height(36.dp)
                            .clickable { onLanguageChange(langCode) }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else DeepNavy
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Warning Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFDF2F2))
                    .border(1.dp, CriticalRed.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = t("⚠️ ÖNEMLİ TIBBİ UYARI", "⚠️ IMPORTANT MEDICAL WARNING"),
                        style = MaterialTheme.typography.titleLarge,
                        color = CriticalRed,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = t(
                            "Bu uygulama YALNIZCA lisanslı sağlık profesyonellerinin eğitimi ve hızlı klinik referans kullanımı için tasarlanmıştır.",
                            "This application is designed ONLY for the education and quick clinical reference use of licensed healthcare professionals."
                        ),
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextPrimaryLight,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Terms scrollable content
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Linen)
                    .border(1.dp, BorderSand, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = t("Kullanım Şartları ve Sorumluluk Sınırları:", "Terms of Use and Limitations of Liability:"),
                        style = MaterialTheme.typography.titleLarge,
                        color = DeepNavy,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    BulletPoint(
                        t(
                            "Uygulama tanı koyma veya doğrudan tedavi uygulama aracı değildir. Yalnızca klinik akıl yürütmeye yardımcı olması için hazırlanmış eğitimsel bir referanstır.",
                            "The application is not a tool for diagnosis or direct treatment. It is solely an educational reference prepared to assist in clinical reasoning."
                        )
                    )
                    BulletPoint(
                        t(
                            "Hesaplayıcılar, doz tabloları ve acil durum algoritmaları bağımsız klinik değerlendirmenin yerine geçemez. Tüm çıktılar uygulanmadan önce hekim tarafından kontrol edilmelidir.",
                            "Calculators, dosage tables, and emergency algorithms cannot replace independent clinical judgment. All outputs must be verified by a physician before implementation."
                        )
                    )
                    BulletPoint(
                        t(
                            "Yapay zeka (AI Klinik Asistanı) asla doğrudan tanı koyamaz, tedavi emri veremez veya doz üretemez. Tıbbi verileri yalnızca klinik eğitime destek amaçlı yapılandırır.",
                            "The AI Clinical Assistant can never directly diagnose, order treatments, or generate dosages. It structures medical data solely to support clinical training."
                        )
                    )
                    BulletPoint(
                        t(
                            "Hastaların gizliliğini korumak amacıyla asistan alanına isim, T.C. kimlik numarası, telefon veya dosya numarası gibi hastayı tanımlayıcı hiçbir bilgi girilmemelidir. Sistem otomatik algılama ve maskeleme katmanına sahiptir.",
                            "To protect patient privacy, no patient-identifiable information such as name, national ID, phone, or file number should be entered into the assistant field. The system features an automatic detection and masking layer."
                        )
                    )
                    BulletPoint(
                        t(
                            "Acil durumlarda uygulamaya bağımlı kalmaksızın derhal kıdemli hekim desteği çağırmalı ve lokal hastane protokollerini izlemelisiniz.",
                            "In emergencies, you must immediately call for senior physician support and follow local hospital protocols without relying on the application."
                        )
                    )
                    BulletPoint(
                        t(
                            "Bu uygulamayı kullanarak tüm klinik kararların nihai sorumluluğunun kendinize ait olduğunu kabul etmiş olursunuz.",
                            "By using this application, you agree that the ultimate responsibility for all clinical decisions lies solely with you."
                        )
                    )
                }
            }
        }

        // Accept Controls
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(WarmSand)
                .padding(vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = accepted,
                    onCheckedChange = { accepted = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = DeepNavy,
                        uncheckedColor = DeepNavy.copy(alpha = 0.5f)
                    )
                )
                Text(
                    text = t(
                        "Sağlık profesyoneliyim ve yukarıdaki şartları kabul ediyorum.",
                        "I am a healthcare professional and I accept the terms above."
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = TextPrimaryLight,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onAccept,
                enabled = accepted,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DeepNavy,
                    disabledContainerColor = DeepNavy.copy(alpha = 0.3f),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = t("Kabul Et ve Başla", "Accept and Proceed"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(text = "• ", fontWeight = FontWeight.Bold, color = ClinicalTeal, fontSize = 18.sp)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondaryLight
        )
    }
}
