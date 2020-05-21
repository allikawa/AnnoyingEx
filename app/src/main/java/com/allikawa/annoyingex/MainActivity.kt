package com.allikawa.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val TAG = "allikawa"

//    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        queue = Volley.newRequestQueue(this)
//
//        btnEx.setOnClickListener {
//            queue.add(stringRequest)
//        }

        btnEx.setOnClickListener {
            Log.i(TAG, "test")
            (application as AnnoyingExApp).annoyingExManager.startAnnoyingEx()
        }
    }

//    private fun makeHTTPRequest() {
//        val annoyingJSONSTring = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
//        val stringRequest = StringRequest(Request.Method.GET, annoyingJSONSTring,
//            Response.Listener<String> { response ->
//                // Do something with the response
//                val jsonObject = JSONObject(response)
//                val msg = jsonObject.getString("messages")
//                Log.i(TAG, "$msg")
//            },
//            Response.ErrorListener { error ->
//                // Handle error=
//            })
//        val firstMsg = (annoyingJSONSTring)
//        Log.i(TAG, "$firstMsg")
//    }
}
