package com.orafaelsc.cstvfuze

import android.app.Application

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Don't initialize Koin in tests
    }
}
