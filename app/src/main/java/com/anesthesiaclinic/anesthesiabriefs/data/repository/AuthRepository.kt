package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AuthRepository {

    private val _currentUserProfile = MutableStateFlow<UserProfile?>(null)
    val currentUserProfile: StateFlow<UserProfile?> = _currentUserProfile.asStateFlow()

    init {
        // Automatically start as guest for offline-first simplicity
        loginAsGuest()
    }

    fun loginAsGuest() {
        _currentUserProfile.value = UserProfile(
            id = "guest_user_id",
            fullName = "Misafir Doktor",
            email = "misafir@anesthesiaclinic.com",
            specialty = "Anesteziyoloji ve Reanimasyon Uzmanı",
            institution = "Klinik Eğitim Platformu",
            isVerifiedClinician = true,
            isPremium = false,
            language = "tr",
            disclaimerAcceptedAt = null
        )
    }

    fun registerUser(
        fullName: String,
        email: String,
        specialty: String,
        institution: String,
        diplomaNumber: String?
    ): Boolean {
        _currentUserProfile.value = UserProfile(
            id = "user_${System.currentTimeMillis()}",
            fullName = fullName,
            email = email,
            specialty = specialty,
            institution = institution,
            diplomaNumber = diplomaNumber,
            isVerifiedClinician = true,
            isPremium = false,
            language = "tr",
            disclaimerAcceptedAt = null
        )
        return true
    }

    fun acceptDisclaimer() {
        val current = _currentUserProfile.value ?: return
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        _currentUserProfile.value = current.copy(
            disclaimerAcceptedAt = dateFormat.format(Date())
        )
    }

    fun setDisclaimerAccepted(dateStr: String) {
        val current = _currentUserProfile.value ?: return
        _currentUserProfile.value = current.copy(
            disclaimerAcceptedAt = dateStr
        )
    }

    fun loginUser(email: String, name: String = "Dr. Ahmet Yılmaz", specialty: String = "Anesteziyoloji Uzmanı", institution: String = "Kartal Lütfi Kırdar Şehir Hastanesi"): Boolean {
        _currentUserProfile.value = UserProfile(
            id = "user_mock_123",
            fullName = name,
            email = email,
            specialty = specialty,
            institution = institution,
            isVerifiedClinician = true,
            isPremium = true,
            language = "tr",
            disclaimerAcceptedAt = "2026-05-25 12:00:00"
        )
        return true
    }

    fun togglePremiumStatus(status: Boolean) {
        val current = _currentUserProfile.value ?: return
        _currentUserProfile.value = current.copy(
            isPremium = status,
            premiumUntil = if (status) "2027-12-31 23:59:59" else null
        )
    }

    fun updateLanguage(lang: String) {
        val current = _currentUserProfile.value ?: return
        _currentUserProfile.value = current.copy(language = lang)
    }

    fun logout() {
        // Return to default guest mode on logout
        loginAsGuest()
    }
}
