package com.slrp.rmm.module.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.slrp.rmm.R
import com.slrp.rmm.databinding.ActivitySplashScreenBinding
import com.slrp.rmm.module.login.Login

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    companion object{
        val SPLASH_DELAY = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSplashDelay()
    }

    private fun setSplashDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigate()
        }, SPLASH_DELAY)
    }

    fun navigate() {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
        finish()
    }
}