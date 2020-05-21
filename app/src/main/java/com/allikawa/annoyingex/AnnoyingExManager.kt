package com.allikawa.annoyingex

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class AnnoyingExManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingEx() {
        val workRequest = OneTimeWorkRequestBuilder<SendMsgWorker>()
//            .setInitialDelay(5000, TimeUnit.MILLISECONDS)
            .build()

        workManager.enqueue(workRequest)
    }
}