package com.allikawa.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class AnnoyingExNotificationWorker(
    private val context: Context,
    workParams: WorkerParameters
): Worker(context, workParams)  {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    override fun doWork(): Result {
        Log.i("allikawa", "notification")
        getMsgs()
        return Result.success()
    }

    private fun postMsg(contentMsg: String) {
        val msgIntent = Intent(context, MsgActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("notification", contentMsg)
        }
        val pendingMsgIntent = PendingIntent.getActivity(context, 0, msgIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var notification = NotificationCompat.Builder(context, EX_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            .setContentTitle("Annoying Ex")
            .setContentText(contentMsg)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingMsgIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Annoying Notifications"
            val descriptionText = "Messages from a certain someone"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(EX_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    private fun getMsgs() {
        val queue = Volley.newRequestQueue(context)
        val url: String = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        var stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var strMsgs = response.toString()
                val msgObj: JSONObject = JSONObject(strMsgs)
                val msgArray: JSONArray = msgObj.getJSONArray("messages")
                val msg = msgObj.getJSONArray("messages")[Random.nextInt(msgArray.length())]
                Log.i("allikawa", "$msg")
                postMsg(msg as String)
            },
            Response.ErrorListener {
                Log.i("allikawa", "whoops")
                postMsg("Unable to retrieve message")
            })
        queue.add(stringReq)
    }

    companion object {
        const val EX_CHANNEL_ID = "EX_CHANNEL_ID"
    }
}