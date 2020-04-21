package com.example.myapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Path
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import kotlinx.android.synthetic.main.activity_animator.*
import kotlin.math.abs

class AnimatorActivity : AppCompatActivity() {

    private lateinit var vectorAnimation : AnimatedVectorDrawable

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(e1: MotionEvent?,
                             e2: MotionEvent?,
                             velocityX: Float,
                             velocityY: Float): Boolean {
            if (abs(velocityX) > abs(velocityY)) {
                FlingAnimation(image_view, DynamicAnimation.TRANSLATION_X).apply {
                    setStartVelocity(velocityX)
                    setMinValue(0f)
                    setMaxValue(300f)
                    friction = 1.1f
                    start()
                }
            } else {
                FlingAnimation(image_view, DynamicAnimation.TRANSLATION_Y).apply {
                    setStartVelocity(velocityY)
                    setMinValue(0f)
                    setMaxValue(1000f)
                    friction = 1.1f
                    start()
                }
            }
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        vectorAnimation = image_view.background as AnimatedVectorDrawable
        btn_vector.setOnClickListener { vectorAnimation.start() }
        btn_customized_path.setOnClickListener { startCustomizedPathAnim() }
        btn_fling.setOnClickListener { startFlingAnimation() }
        btn_spring.setOnClickListener { startSpringAnimation() }
        btn_change.setOnClickListener { changeLayout() }
        btn_share_ui.setOnClickListener { startShareUiActivity() }
        btn_soft_spring.setOnClickListener { startSoftSpringAnimation() }

        GestureDetector(this, gestureListener).apply {
            image_view.setOnTouchListener { _, event ->
                onTouchEvent(event)
            }
        }
    }

    private fun startCustomizedPathAnim() {
        val path = Path().apply {
            arcTo(0f, 0f, 300f, 1000f, -90f, 359f, true)
        }
        ObjectAnimator.ofFloat(image_view, View.X, View.Y, path).apply {
            duration = 2000
            start()
        }
    }

    private fun startFlingAnimation() {
        Toast.makeText(this, "Please fling the image view", Toast.LENGTH_LONG).show()
    }

    private fun startSpringAnimation() {
        SpringAnimation(image_view, DynamicAnimation.TRANSLATION_Y, 700f).apply {
            setStartVelocity(1000f)
            spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            start()
        }
    }

    private fun changeLayout() {
        when (image_change.visibility) {
            View.VISIBLE -> image_change.visibility = View.GONE
            View.GONE -> image_change.visibility = View.VISIBLE
        }
    }

    private fun startShareUiActivity() {
        val intent = Intent(this, ShareUiActivity::class.java)
        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="shared"
        val options = ActivityOptions
            .makeSceneTransitionAnimation(this, image_change, "share_image")
        // Start new activity
        startActivity(intent, options.toBundle())
    }

    private fun startSoftSpringAnimation() {
        val translationAnim =
            ObjectAnimator.ofFloat(image_change, "translationY", 0f, -200f, -100f).apply {
                duration = 500
            }
        val scale1Anim =
            ObjectAnimator.ofFloat(image_change, View.SCALE_Y, 0.85f, 0.7f, 0.85f, 1.0f, 0.85f, 0.9f, 1.0f).apply {
                duration = 600
                image_change.pivotY = image_change.height.toFloat()
            }
        val scale2Anim =
            ObjectAnimator.ofFloat(image_change, View.SCALE_X, 1.1f, 1.2f, 1.1f, 1.0f, 1.1f, 1.05f, 1.0f).apply {
                duration = 600
            }
        val scaleAnim = AnimatorSet().apply {
            play(scale1Anim).with(scale2Anim)
            interpolator = DecelerateInterpolator()
        }
        AnimatorSet().apply {
            play(translationAnim).before(scaleAnim)
            start()
        }
    }
}
