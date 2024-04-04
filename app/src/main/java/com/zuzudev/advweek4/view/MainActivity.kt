package com.zuzudev.advweek4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.zuzudev.advweek4.R
import com.zuzudev.advweek4.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.zuzudev.advweek4.util.createNotificationChannel


class MainActivity : AppCompatActivity() {
    init { //bagian code yg dipanggil pertama kali sebelum onCreate / init = constructor
        instance = this
    }
    companion object {
        private var instance:MainActivity? = null

        fun showNotification(title: String, content: String, icon: Int) {
            val channelId =
                "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val builder =
                NotificationCompat.Builder(instance!!.applicationContext,
                    channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext,Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
                return
            }
            manager.notify(1001, builder.build()) //untuk menghasilkan notifikasi ke layar

        }



    }

    private lateinit var binding:ActivityMainBinding
//    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this,
        NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
        getString(R.string.app_name), "App notification channel.")

}
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("permission", "granted")
//                    createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, "Notif Channel",
//                        "Notification channel for creating new student")
                    createNotificationChannel(this,
                        NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                        getString(R.string.app_name), "App notification channel.")
                } else {
                    Log.d("permission", "deny")
                }
//                return
            }
        }

    }

}