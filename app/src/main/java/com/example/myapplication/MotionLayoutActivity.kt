package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intent.getIntExtra("layout_res_id", 0))
    }
}
