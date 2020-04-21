package com.example.myapplication.request.wanandroid

data class WanBanner (
    val desc : String,
    val id : Int,
    val imagePath : String,
    val isVisible :Int,
    val order : Int,
    val title : String,
    val type : Int,
    val url : String
)

data class BannerData (
    val data : List<WanBanner>
)