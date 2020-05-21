package com.allikawa.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SendMsgWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    override fun doWork(): Result {
        Log.i("allikawa", "do you still love me")

        return Result.success()
    }

}