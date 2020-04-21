package com.example.myapplication.request.wanandroid

import retrofit2.Response
import retrofit2.http.GET

interface WanApi {

    @GET("/banner/json")
    suspend fun getBannersAsync(): Response<BannerData>
}