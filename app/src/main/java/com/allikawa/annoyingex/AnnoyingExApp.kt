package com.allikawa.annoyingex

import android.app.Application

class AnnoyingExApp: Application() {

    lateinit var annoyingExManager: AnnoyingExManager
        private set

    lateinit var annoyingExNotificationManager: AnnoyingExNotificationManager
        private set

    override fun onCreate() {
        super.onCreate()

        annoyingExManager = AnnoyingExManager(this)
        annoyingExNotificationManager = AnnoyingExNotificationManager(this)
    }
}