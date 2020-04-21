package com.example.resource

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResProvider @Inject constructor(private val appContext: Context) {

    fun getString(@StringRes stringId : Int) : String {
        return appContext.getString(stringId)
    }

    fun getString(@StringRes stringId : Int, vararg formatArgs : Any) : String {
        return appContext.getString(stringId, formatArgs)
    }

    fun getColorInt(@ColorRes colorId : Int) : Int {
        return ContextCompat.getColor(appContext, colorId)
    }
}