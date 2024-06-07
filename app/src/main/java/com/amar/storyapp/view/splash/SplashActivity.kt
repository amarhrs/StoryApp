package com.amar.storyapp.view.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

import com.amar.storyapp.R
import com.amar.storyapp.data.LoginPreferences
import com.amar.storyapp.data.dataStore
import com.amar.storyapp.view.main.MainActivity
import com.amar.storyapp.view.onboarding.OnBoardingActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        val content = findViewById<View>(android.R.id.content)
        @Suppress("UNUSED_EXPRESSION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            content.viewTreeObserver.addOnDrawListener { false }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val userLogin = runBlocking {
                LoginPreferences.getInstance(this@SplashActivity.dataStore).getLoginStatus().first()
            }
            if (userLogin == true) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(applicationContext, OnBoardingActivity::class.java))
                finish()
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, 1500L)
    }
}