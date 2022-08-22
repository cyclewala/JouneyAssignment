package com.learning.journey.base.application

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(), LifecycleObserver {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        init()

    }

    private fun init() {
        initProcessLifecycleOwner()
    }

    private fun initProcessLifecycleOwner() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

}
