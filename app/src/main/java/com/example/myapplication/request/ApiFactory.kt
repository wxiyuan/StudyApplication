package com.example.myapplication.request

import com.example.myapplication.request.wanandroid.WanApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val BASE_URL = "https://www.wanandroid.com"
    private const val TIMEOUT : Long = 30

    private val wanClient = OkHttpClient()
        .newBuilder().callTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(wanClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val WAN_API : WanApi = retrofit().create(
        WanApi::class.java)
}