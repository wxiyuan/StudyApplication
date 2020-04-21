package com.example.myapplication.request.wanandroid

import com.example.myapplication.request.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class WanRepository(private val api : WanApi) : BaseRepository() {

    private val wanModel: WanModel = WanModel()

    suspend fun getBanners(): Flow<List<WanBanner>> {
        wanModel.getBannerCache()?.let {
            return flow {
                emit(it)
            }
        }
        return safeApiCall(call = { api.getBannersAsync() })
            .onEach { wanModel.setBannerCache(it) }
            .map { it!!.data }
    }
}