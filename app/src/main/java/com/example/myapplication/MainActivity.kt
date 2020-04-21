package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.room.UserDatabase
import com.example.myapplication.room.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userDatabase: UserDatabase

    private var insertTestIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        userDatabase = Room.inMemoryDatabaseBuilder(
            applicationContext,
            UserDatabase::class.java
        ).let {
            it.allowMainThreadQueries()
            it.build()
        }

        with(binding) {
            btnInsert.setOnClickListener { insertUserTest() }
            btnLoad.setOnClickListener { loadLastUserTest() }
            btnCamera.setOnClickListener { startCameraxActivity() }
            btnNotification.setOnClickListener { startNotificationActivity() }
            btnAnimator.setOnClickListener { startAnimatorActivity() }
            btnPalette.setOnClickListener { startPaletteActivity() }
            btnMotionLayout.setOnClickListener { startMotionLayoutActivity() }
            btnRetrofit.setOnClickListener { startRetrofitActivity() }
        }
    }

    private fun insertUserTest() {
        insertTestIndex++
        with(UserEntity()) {
            id = insertTestIndex.toString()
            firstName = "firstName$insertTestIndex"
            lastName = "lastName$insertTestIndex"
            address = "address$insertTestIndex"
            userDatabase.userDao().insertUsers(this)
        }
    }

    private fun loadLastUserTest() {
        val user = userDatabase.userDao().loadUserById(insertTestIndex.toString())
        val userInfo = "Last User:\n    id: ${user.id}\n" +
                "    name: ${user.firstName} ${user.lastName}\n    address: ${user.address}"
        binding.textView.text = userInfo
    }

    private fun startCameraxActivity() {
        startActivity(Intent(this, CameraxActivity::class.java))
    }

    private fun startNotificationActivity() {
        startActivity(Intent(this, NotificationActivity::class.java))
    }

    private fun startAnimatorActivity() {
        startActivity(Intent(this, AnimatorActivity::class.java))
    }

    private fun startPaletteActivity() {
        startActivity(Intent(this, PaletteActivity::class.java))
    }

    private fun startMotionLayoutActivity() {
        startActivity(Intent(this, MotionActivity::class.java))
    }

    private fun startRetrofitActivity() {
        startActivity(Intent(this, RetrofitActivity::class.java))
    }
}
