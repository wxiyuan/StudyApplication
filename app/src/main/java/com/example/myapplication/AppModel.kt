package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import com.jakewharton.rxrelay2.PublishRelay

object AppModel {
    val liveBus = MutableLiveData<String>()
    val rxBus: PublishRelay<String> = PublishRelay.create()
}