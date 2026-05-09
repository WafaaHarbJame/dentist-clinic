package com.dentalcare.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DentalCareApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // App initialization can be added here
    }
}
