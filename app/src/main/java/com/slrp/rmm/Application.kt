package com.slrp.rmm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RmmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}