package com.allikawa.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEx.setOnClickListener {
//            Log.i(TAG, "test")
            (application as AnnoyingExApp).annoyingExManager.startAnnoyingEx()
        }
    }
}
