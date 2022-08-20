package com.learning.journey.base.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.learning.journey.base.core.ui.BaseAppCompatActivity
import java.util.*


object StaticUtils {

    private const val TAG = "StaticUtils"

    fun getDefaultTimeZoneId(): String? {
        return TimeZone.getDefault().id
    }

    fun getDeviceManufacturer(): String? {
        return Build.MANUFACTURER
    }

    fun getDeviceModel(): String? {
        return Build.MODEL
    }

    fun getDeviceOS(): String? {
        return Build.VERSION.RELEASE
    }

    val getSDKVersionNumber: String = Build.VERSION.SDK_INT.toString()

    fun showGlobalSnackBar(message: String) {
        Handler(Looper.getMainLooper()).post {
            try {
                var parentView =
                    BaseAppCompatActivity.activity.findViewById<View>(android.R.id.content)
                var snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG)
                snackbar.show()
            } catch (e: Exception) {
                Log.e(TAG, Log.getStackTraceString(e))
            }
        }
    }

    fun showGlobalSnackBarWithActionListener(
        message: String,
        action: String,
        clickListener: View.OnClickListener,
    ) {
        Handler(Looper.getMainLooper()).post {
            try {
                var parentView =
                    BaseAppCompatActivity.activity.findViewById<View>(android.R.id.content)
                var snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG)
                snackbar.setAction(action, clickListener)
                snackbar.show()
            } catch (e: Exception) {
                Log.e(TAG, Log.getStackTraceString(e))
            }
        }

    }

    fun showGlobalLongToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            var parentView = BaseAppCompatActivity.activity.findViewById<View>(android.R.id.content)
            Toast.makeText(
                parentView.context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun openURLInBrowser(context: Context, url: String?) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        } catch (e: ActivityNotFoundException) {
            Log.d(TAG, e.message ?: "")
        } catch (e: java.lang.Exception) {
            Log.d(TAG, e.message ?: "")
        }
    }


}