package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.request.ApiFactory
import com.example.myapplication.request.wanandroid.WanRepository
import com.example.myapplication.request.wanandroid.WanBanner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WanViewModel : ViewModel() {

    private val wanRepository = WanRepository(ApiFactory.WAN_API)

    val bannersLiveData : MutableLiveData<MutableList<WanBanner>> by lazy {
        MutableLiveData<MutableList<WanBanner>>()
    }

    val errorsLiveData : MutableLiveData<Throwable> by lazy {
        MutableLiveData<Throwable>()
    }

    @ExperimentalCoroutinesApi
    fun fetchBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            wanRepository.getBanners()
                .catch { e -> errorsLiveData.postValue(e) }
                .collect { result ->
                    bannersLiveData.postValue(result.toMutableList())
                }
        }
    }
}