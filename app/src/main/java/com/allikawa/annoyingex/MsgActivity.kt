package com.allikawa.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_msg.*

class MsgActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg)

        val msgFromNotif = intent.getStringExtra("notification")

        tvMsg.text = msgFromNotif
    }
}
