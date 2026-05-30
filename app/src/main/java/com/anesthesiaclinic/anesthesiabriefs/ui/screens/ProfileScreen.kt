package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.anesthesiaclinic.anesthesiabriefs.data.repository.AuthRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentLanguage: String,
    onBackClick: () -> Unit
) {
    val isTurkish = currentLanguage == "tr"
    fun t(tr: String, en: String): String = if (isTurkish) tr else en

    val userProfileState = AuthRepository.currentUserProfile.collectAsState()
    val userProfile = userProfileState.value
    val isGuest = userProfile == null || userProfile.email == "misafir@anesthesiaclinic.com"

    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var nameInput by remember { mutableStateOf("") }
    var specialtyInput by remember { mutableStateOf("") }
    var institutionInput by remember { mutableStateOf("") }
    var showRegisterMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(t("Kullanıcı Profili", "User Profile"), style = MaterialTheme.typography.titleLarge, color = Color.White) },
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
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isGuest) {
                // Mock Authentication Panel
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = if (showRegisterMode) t("Yeni Hesap Oluştur", "Create New Account") else t("Giriş Yap", "Log In"),
                            style = MaterialTheme.typography.titleLarge,
                            color = DeepNavy,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = t(
                                "Uygulama verilerini yedeklemek ve klinik kaydınızı doğrulamak için hesap oluşturun veya giriş yapın. Bu modül Firebase entegrasyonu için hazır durumdadır.",
                                "Create an account or log in to backup application data and verify your clinician status. This module is ready for Firebase integration."
                            ),
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = TextSecondaryLight
                        )
                        
                        Spacer(modifier = Modifier.height(6.dp))

                        if (showRegisterMode) {
                            OutlinedTextField(
                                value = nameInput,
                                onValueChange = { nameInput = it },
                                label = { Text(t("Tam Ad Soyad", "Full Name")) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = TextPrimaryLight,
                                    unfocusedTextColor = TextPrimaryLight,
                                    focusedBorderColor = ClinicalTeal,
                                    unfocusedBorderColor = BorderSand
                                )
                            )
                            
                            OutlinedTextField(
                                value = specialtyInput,
                                onValueChange = { specialtyInput = it },
                                label = { Text(t("Uzmanlık Alanı", "Specialty")) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = TextPrimaryLight,
                                    unfocusedTextColor = TextPrimaryLight,
                                    focusedBorderColor = ClinicalTeal,
                                    unfocusedBorderColor = BorderSand
                                )
                            )

                            OutlinedTextField(
                                value = institutionInput,
                                onValueChange = { institutionInput = it },
                                label = { Text(t("Çalıştığınız Kurum", "Institution")) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = TextPrimaryLight,
                                    unfocusedTextColor = TextPrimaryLight,
                                    focusedBorderColor = ClinicalTeal,
                                    unfocusedBorderColor = BorderSand
                                )
                            )
                        }

                        OutlinedTextField(
                            value = emailInput,
                            onValueChange = { emailInput = it },
                            label = { Text(t("E-posta Adresi", "Email Address")) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = TextPrimaryLight,
                                unfocusedTextColor = TextPrimaryLight,
                                focusedBorderColor = ClinicalTeal,
                                unfocusedBorderColor = BorderSand
                            )
                        )

                        OutlinedTextField(
                            value = passwordInput,
                            onValueChange = { passwordInput = it },
                            label = { Text(t("Şifre", "Password")) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = TextPrimaryLight,
                                unfocusedTextColor = TextPrimaryLight,
                                focusedBorderColor = ClinicalTeal,
                                unfocusedBorderColor = BorderSand
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                if (showRegisterMode) {
                                    AuthRepository.registerUser(
                                        fullName = nameInput.ifBlank { "Dr. Ahmet Yılmaz" },
                                        email = emailInput.ifBlank { "ahmet@anesthesiaclinic.com" },
                                        specialty = specialtyInput.ifBlank { "Anestezi Uzmanı" },
                                        institution = institutionInput.ifBlank { "Şehir Hastanesi" },
                                        diplomaNumber = "99834"
                                    )
                                } else {
                                    AuthRepository.loginUser(
                                        email = emailInput.ifBlank { "ahmet@anesthesiaclinic.com" },
                                        name = "Dr. Ahmet Yılmaz"
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (showRegisterMode) t("Kayıt Ol", "Register") else t("Giriş Yap", "Log In"),
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        TextButton(
                            onClick = { showRegisterMode = !showRegisterMode },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = if (showRegisterMode) {
                                    t("Zaten hesabınız var mı? Giriş Yapın", "Already have an account? Log In")
                                } else {
                                    t("Hesabınız yok mu? Şimdi Kayıt Olun", "Don't have an account? Register now")
                                },
                                color = ClinicalTeal,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            } else {
                // Logged-in Profile View
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // User summary header
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = null,
                                tint = DeepNavy,
                                modifier = Modifier.size(54.dp)
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = userProfile?.fullName ?: "Doktor",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = DeepNavy,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = null,
                                        tint = ClinicalTeal,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = t("Doğrulanmış Hekim", "Verified Clinician"),
                                        fontSize = 11.sp,
                                        color = ClinicalTeal,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        HorizontalDivider(color = BorderSand.copy(alpha = 0.5f))

                        // Info fields
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            ProfileInfoRow(label = t("E-posta", "Email"), value = userProfile?.email ?: "")
                            ProfileInfoRow(label = t("Uzmanlık", "Specialty"), value = userProfile?.specialty ?: "")
                            ProfileInfoRow(label = t("Kurum", "Institution"), value = userProfile?.institution ?: "")
                        }

                        HorizontalDivider(color = BorderSand.copy(alpha = 0.5f))

                        // Premium simulation controls
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = t("Premium Lisans Durumu", "Premium License Status"),
                                        fontWeight = FontWeight.Bold,
                                        color = DeepNavy,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = if (userProfile?.isPremium == true) {
                                            t("Sınırsız AI ve gelişmiş modüller aktif.", "Unlimited AI and advanced modules active.")
                                        } else {
                                            t("AI limitli sürüm aktif, yükseltmek için simüle edin.", "AI limited version active, simulate to upgrade.")
                                        },
                                        fontSize = 11.sp,
                                        color = TextSecondaryLight
                                    )
                                }
                                
                                Switch(
                                    checked = userProfile?.isPremium == true,
                                    onCheckedChange = { AuthRepository.togglePremiumStatus(it) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = ClinicalTeal,
                                        uncheckedThumbColor = BorderSand,
                                        uncheckedTrackColor = Color.White
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { AuthRepository.logout() },
                            colors = ButtonDefaults.buttonColors(containerColor = CriticalRed),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(t("Güvenli Çıkış Yap", "Log Out Safely"), color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = TextSecondaryLight, fontSize = 12.sp)
        Text(text = value, color = DeepNavy, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
    }
}
