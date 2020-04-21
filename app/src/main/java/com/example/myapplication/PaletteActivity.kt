package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import kotlinx.android.synthetic.main.activity_palette.*

class PaletteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette)
        Palette.from(image_palette.drawable.toBitmap()).generate {
            it?.getVibrantColor(Color.WHITE)?.let {color ->
                text_vibrant.setBackgroundColor(color)
            }
            it?.getLightVibrantColor(Color.WHITE)?.let {color ->
                text_light_vibrant.setBackgroundColor(color)
            }
            it?.getDarkVibrantColor(Color.WHITE)?.let {color ->
                text_dark_vibrant.setBackgroundColor(color)
            }
            it?.getMutedColor(Color.WHITE)?.let {color ->
                text_muted.setBackgroundColor(color)
            }
            it?.getLightMutedColor(Color.WHITE)?.let {color ->
                text_light_muted.setBackgroundColor(color)
            }
            it?.getDarkMutedColor(Color.WHITE)?.let {color ->
                text_dark_muted.setBackgroundColor(color)
            }
            it?.getDominantColor(Color.WHITE)?.let {color ->
                text_dominant.setBackgroundColor(color)
            }
        }
    }
}
