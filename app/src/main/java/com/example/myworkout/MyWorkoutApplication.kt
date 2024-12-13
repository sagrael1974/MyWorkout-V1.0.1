package com.example.myworkout

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class MyWorkoutApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialisierungen in Coroutines verschieben
        CoroutineScope(Dispatchers.Default).launch {
            // Schwere Initialisierungen hier
        }
    }
} 