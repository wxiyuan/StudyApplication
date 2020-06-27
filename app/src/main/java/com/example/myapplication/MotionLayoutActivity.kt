package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.motion_05.*


class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @LayoutRes val layoutId = intent.getIntExtra("layout_res_id", 0)
        setContentView(layoutId)
        if (layoutId == R.layout.motion_05) {
            setupMotion05Toolbar()
        }
    }

    private fun setupMotion05Toolbar() {
        setSupportActionBar(toolbar_05)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Settings")
        return true
    }
}
