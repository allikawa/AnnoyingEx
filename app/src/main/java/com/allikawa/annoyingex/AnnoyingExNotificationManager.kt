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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.random.Random

class AnnoyingExNotificationManager(
    private val context: Context
) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    fun postMsg() {
        getMsgs()
        val msgIntent = Intent(context, MsgActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("notification", "world")
        }
        val pendingMsgIntent = PendingIntent.getActivity(context, 0, msgIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var notification = NotificationCompat.Builder(context, EX_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            .setContentTitle("Annoying Ex")
            .setContentText("please come back to me / this would be json stuff")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingMsgIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(123, notification)
//        notificationManagerCompat. (index of list, notification)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Annoying Notifications"
            val descriptionText = "Messages from a certain someone"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(EX_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    fun getMsgs() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url: String = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var strMsgs = response.toString()
                Log.i("allikawa", "$msg")
            },
            Response.ErrorListener {
                Log.i("allikawa", "whoops")
            })
        queue.add(stringReq)
    }

    companion object {
        const val EX_CHANNEL_ID = "EX_CHANNEL_ID"
    }
}