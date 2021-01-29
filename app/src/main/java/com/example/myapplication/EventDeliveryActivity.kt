package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityEventDeliveryBinding
import io.reactivex.disposables.CompositeDisposable

/**
 * LiveData，具有生命周期关联特性、粘性特性，如果Activity/Fragment在onCreate里面监听它，
 * 那么当liveData有value存在时，在Activity/Fragment onStart的时候会立即触发回调事件，
 * 大多情况下，这种现象是不被期望的；并且这种粘性特质貌似很难被禁止，所以视具体情况考虑是否将
 * liveData用于全局事件传递.
 *
 * PublishRelay，不具备生命周期关联特性和粘性特性，随时发随时收，它有一个致命缺点，如果app中设
 * 置了RxJavaPlugins来统一处理RxJava的UndeliverableException错误，那么当处理PublishRelay
 * 回调事件的过程中发生错误时，该错误会被RxJavaPlugins捕获，并且此时PublishRelay会挂掉，而后
 * 当PublishRelay中有新的事件被accept时，订阅该PublishRelay的地方不会再被回调并且全程不会抛出
 * 任何异常使得开发者/用户无法感知该错误，这样就会导致程序不能执行引起app行为异常而开发者却完全不知道；
 * 但是基于PublishRelay不存在粘性特性的原因，它应该会适合绝大多数业务场景，所以需要注意，当使用
 * PublishRelay时，我们应该避免使用RxJavaPlugins来统一处理UndeliverableException，这样如
 * 果回调处理中有错误就会暴漏出来，而不是被无感知的隐藏.
 */
class EventDeliveryActivity : AppCompatActivity() {

    private lateinit var bind: ActivityEventDeliveryBinding
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityEventDeliveryBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initListeners()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun initListeners() {
        AppModel.liveBus.observe(this, Observer { msg -> showToast(msg) })
        disposables.add(AppModel.rxBus.subscribe { msg -> handleRxEvent(msg) })
        bind.liveEvent.setOnClickListener { AppModel.liveBus.value = "Correct live event" }
        bind.rxEvent.setOnClickListener { AppModel.rxBus.accept("Correct rx event") }
    }

    private fun handleRxEvent(msg: String) {
        msg as Int // 制造Crash，log里面warning level已经记录到了crash信息
        showToast(msg)
    }

    private fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}