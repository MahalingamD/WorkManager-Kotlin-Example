package com.example.simplework.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.simplework.R

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

     /*   var p : String? = null
        p?.let { Log.e("let","p is $p") } ?: run {
            Log.e("run","p was null. Setting default value to: ") }

        Log.e("final",p)*/
    }
}
