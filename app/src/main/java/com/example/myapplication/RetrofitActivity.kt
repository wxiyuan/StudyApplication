package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.request.wanandroid.WanBanner
import com.example.myapplication.viewmodel.WanViewModel
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class RetrofitActivity : AppCompatActivity() {

    private lateinit var wanViewModel : WanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        wanViewModel = ViewModelProvider(this).get(WanViewModel::class.java)
        initObservers()
        btn_request.setOnClickListener { wanViewModel.fetchBanners() }
        // lifecycleScope.launchWhen*** {  } // Use lifecycleScope launch coroutines
    }

    private fun initObservers() {
        Observer<MutableList<WanBanner>> {
            showLongToast(it.toExtensionString())
        }.apply { wanViewModel.bannersLiveData.observe(this@RetrofitActivity, this)}

        Observer<Throwable> {
            showLongToast(it.message)
        }.apply { wanViewModel.errorsLiveData.observe(this@RetrofitActivity, this) }
    }

    private fun showLongToast(msg : String?) {
        Toast.makeText(this@RetrofitActivity, msg, Toast.LENGTH_SHORT).show()
    }
}
