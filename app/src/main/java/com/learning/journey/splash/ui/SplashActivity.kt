package com.learning.journey.splash.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.learning.journey.R
import com.learning.journey.content.data.viewmodel.ContentViewModel
import com.learning.journey.databinding.ActivitySplashBinding
import com.learning.journey.posts.ui.PostsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel: ContentViewModel by lazy {
        ViewModelProvider(this)[ContentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        init()
    }

    private fun init() {
        fetchContent()
        navigateToScreen()
    }

    private fun fetchContent() {
        viewModel.sendContentLoadRequest()
    }

    private fun navigateToScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToActivity()
            finish()
        }, 500)
    }

    private fun navigateToActivity() {
        val navigationIntent = Intent(this@SplashActivity, PostsActivity::class.java)
        startActivity(navigationIntent)
        finish()
    }

}