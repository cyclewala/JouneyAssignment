package com.learning.journey.base.application

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.Configuration
import com.learning.journey.base.core.utils.NetworkLiveData
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider, LifecycleObserver {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        init()

    }


    fun getContext(): Context {
        return applicationContext
    }

    private fun init() {
        initProcessLifecycleOwner()
        initNetworkLiveData()
    }

    private fun initProcessLifecycleOwner() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun initNetworkLiveData() {
        NetworkLiveData.init(this)
    }

}
