package com.anesthesiaclinic.anesthesiabriefs

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class AnesthesiaBriefsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            // Safe initialize Firebase so that if google-services.json is missing,
            // the app still launches perfectly and falls back to offline mode.
            FirebaseApp.initializeApp(this)
            Log.d("AnesthesiaBriefsApp", "Firebase initialized successfully.")
        } catch (e: Exception) {
            Log.w("AnesthesiaBriefsApp", "Firebase initialization failed. Running in offline/fallback mode: ${e.message}")
        }
    }
}
