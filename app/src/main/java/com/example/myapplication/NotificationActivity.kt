package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID : String = "1"
    }

    private lateinit var notificationManagerCompat: NotificationManagerCompat
    private var index : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        notificationManagerCompat = NotificationManagerCompat.from(this)
        btn_simple_notification.setOnClickListener { sendSimpleNotification() }
    }

    private fun sendSimpleNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Simple notification $index")
            .setContentText("This is a simple notification $index")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        createNotificationChannel()

        notificationManagerCompat.notify(1000, builder.build())

        index++
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "my notification channel"
            val descriptionText = "my notification description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }
}
