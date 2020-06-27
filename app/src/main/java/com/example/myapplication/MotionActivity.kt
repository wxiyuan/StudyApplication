package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import kotlinx.android.synthetic.main.activity_motion.*

class MotionActivity : AppCompatActivity() {

    private val onClickListener = View.OnClickListener { view -> onClick(view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion)
        btn_motion_01.setOnClickListener(onClickListener)
        btn_motion_02.setOnClickListener(onClickListener)
        btn_motion_03.setOnClickListener(onClickListener)
        btn_motion_04.setOnClickListener(onClickListener)
        btn_motion_05.setOnClickListener(onClickListener)
    }

    private fun onClick(view : View) {
        when (view.id) {
            R.id.btn_motion_01 -> startMotionLayoutActivity(R.layout.motion_01)
            R.id.btn_motion_02 -> startMotionLayoutActivity(R.layout.motion_02)
            R.id.btn_motion_03 -> startMotionLayoutActivity(R.layout.motion_03)
            R.id.btn_motion_04 -> startMotionLayoutActivity(R.layout.motion_04)
            R.id.btn_motion_05 -> startMotionLayoutActivity(R.layout.motion_05)
            else -> {}
        }
    }

    private fun startMotionLayoutActivity(@LayoutRes layoutResId : Int) {
        Intent(this, MotionLayoutActivity::class.java).let {
            it.putExtra("layout_res_id", layoutResId)
            startActivity(it)
        }
    }
}
