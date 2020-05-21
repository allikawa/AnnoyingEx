package com.allikawa.annoyingex

import android.app.DownloadManager
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SendMsgWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    override fun doWork(): Result {
        Log.i("allikawa", "do you still love me")

//        val queue = Volley.newRequestQueue(this)
//        val url = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            Response.Listener<String> { response ->
//                // Display the first 500 characters of the response string.
//            },
//            Response.ErrorListener {
//
//            })
//        queue.add(stringRequest)

        return Result.success()
    }

}