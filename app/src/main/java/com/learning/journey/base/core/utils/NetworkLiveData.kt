package com.learning.journey.base.core.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

object NetworkLiveData : LiveData<Boolean>() {

    private lateinit var application: Application

    private lateinit var networkRequest: NetworkRequest

    private var connected: Int = 0

    override fun onActive() {
        super.onActive()
        if (connected <= 0) {
            postValue(false)
        }
    }

    fun init(application: Application) {
        this.application = application
        createNetworkRequestAndRegister()
    }

    private fun createNetworkRequestAndRegister() {
        networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
            .build()
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.registerNetworkCallback(networkRequest, netWorkCallback)
    }

    fun isInternetAvailable(): Boolean {
        return connected > 0
    }

    private val netWorkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            connected++
            postValue(true)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            connected--
            postValue(false)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            connected--
            postValue(false)
        }
    }

    override fun postValue(value: Boolean?) {
        if (connected > 0) {
            super.postValue(true)
        } else {
            super.postValue(false)
        }
    }

}