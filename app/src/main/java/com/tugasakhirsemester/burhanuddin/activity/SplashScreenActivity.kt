package com.tugasakhirsemester.burhanuddin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.tugasakhirsemester.burhanuddin.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        /*
        * Activity ini adalah SplashScreen yang di sengaja dibuat untuk  menyimpan info tentang
        * aplikasi ini, dan activity ini yang akan di jadikan  sebagai launcher aplikasi
        * dengan durasi yang di tentukan 3000 ms atau 0,5 min dan utnuk menentukan
        * nya sebagai launcher aplikasi  di setting pada AndroidManifest
        */
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, IndonesiaDataActivity::class.java))
            finish()
        }, 3000)
    }
}