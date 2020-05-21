package com.allikawa.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val annoyingExApp = (application as AnnoyingExApp)
        val annoyingExManager = annoyingExApp.annoyingExManager
        val annoyingExNotificationManager = annoyingExApp.annoyingExNotificationManager

        btnEx.setOnClickListener {
            annoyingExManager.startAnnoyingEx()
            annoyingExNotificationManager.postMsg()
        }

        btnStop.setOnClickListener {
            annoyingExManager.stopWork()
        }
    }
}
