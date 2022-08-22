package com.learning.journey.base.core.data.remote

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.learning.journey.BuildConfig
import com.learning.journey.base.core.data.Resource
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Response
import java.io.IOException


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    if (BuildConfig.printNetworkLog) {
                        Log.d(
                            "##ApiCall##",
                            "API Header : " + Gson().toJson(response.raw().request.headers.toMultimap())
                        )
                        Log.d(
                            "##ApiCall##",
                            "API Request : " + Gson().toJson(response.raw().request.url.toUri()
                                .toString())
                        )
                        val bodyString = response.raw().request.body?.let { bodyToString(it) }
                        Log.d(
                            "##ApiCall##",
                            "API Request Body : $bodyString"
                        )
                        Log.d("##ApiCall##", "API Response : " + Gson().toJson(body))
                    }
                    return Resource.success(body)
                }
            }
            return error(response)
        } catch (e: Exception) {
            Log.e("BaseDataSource", e.localizedMessage ?: "Network error null")
            return error(null)
        }
    }

    private fun <T> error(response: Response<T>?): Resource<T> {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        response?.let {
            when (response.code()) {
                403 -> {
                    var errorMessage = "Network error : 403"
                    try {
                        val errorBody = gson.toJson(response.errorBody())
                        errorMessage = errorBody ?: response.message()
                    } catch (e: Exception) {
                        Log.e("BaseDataSource", e.localizedMessage ?: "")
                    }
                    return Resource.logoutError("Network logout error : for a following reason: $errorMessage")
                }
                else -> {
                    val errorMessage = response.errorBody() ?: response.message()
                    return Resource.error("Network call has failed for a following reason: $errorMessage")
                }
            }
        } ?: run {
            val errorMessage = "Network error code null"
            Log.d("BaseDataSource", errorMessage)
            return Resource.error("Technical error Please Try Again", null)
        }

    }

    private fun bodyToString(request: RequestBody): String? {
        return try {
            val buffer = Buffer()
            request.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }

}