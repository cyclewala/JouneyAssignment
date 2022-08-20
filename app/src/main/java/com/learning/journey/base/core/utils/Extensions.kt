package com.learning.journey.base.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.fragment.app.Fragment
import androidx.lifecycle.*

/** 
 * Extension method to check network availability 
 */
fun Context.isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN) -> true
            else -> false
        }
    } else {
        //Deprecated Network Info class to check below Marshmallow
        connectivityManager.activeNetworkInfo?.run {
            result = when (type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_ETHERNET -> true
                else -> false
            }

        }
    }
    return result
}

/** 
 * Extension method to get shared view model instance
 */
fun <T : ViewModel> Fragment.obtainViewModel(
    owner: ViewModelStoreOwner,
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory,
) =
    ViewModelProvider(owner, viewModelFactory).get(viewModelClass)

fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R,
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }
    return result
}
