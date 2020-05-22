package com.allikawa.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class AnnoyingExManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingEx() {
        if (!isRunning()) {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<AnnoyingExNotificationWorker>(20, TimeUnit.MINUTES)
                .setInitialDelay(1000, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .addTag(EX_WORK_REQUEST_TAG)
                .build()

            workManager.enqueue(workRequest)
        }
    }

    private fun isRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(EX_WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(EX_WORK_REQUEST_TAG)
        Log.i("alli", "work has been stopped")
    }

    companion object {
        const val EX_WORK_REQUEST_TAG = "EX_WORK_REQUEST_TAG"
    }
}