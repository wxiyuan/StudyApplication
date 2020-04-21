package com.example.myapplication.request.wanandroid

class WanModel {

    private var bannerListCache : List<WanBanner>? = null

    fun setBannerCache(banners : BannerData?) {
        if (banners is BannerData) {
            bannerListCache = banners.data.toMutableList()
        }
    }

    fun getBannerCache() : List<WanBanner>? {
        return bannerListCache
    }

    /**
     * Call this method when remote banner list is changed.
     */
    fun invalidateBannerCache() {
        bannerListCache = null
    }
}