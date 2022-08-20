package com.learning.journey.base.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseAppCompatActivity : AppCompatActivity() {

    val tag = "BaseAppCompatActivity"

    companion object {
        lateinit var activity: AppCompatActivity
        private var activityStackCount: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        init()
        super.onCreate(savedInstanceState)
    }

    private fun init() {
        activityStackCount++
    }

    override fun onResume() {
        super.onResume()
        activity = this
    }

    override fun onDestroy() {
        activityStackCount--
        super.onDestroy()
    }

}