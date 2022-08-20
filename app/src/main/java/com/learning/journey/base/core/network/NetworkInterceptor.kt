package com.learning.journey.base.core.network

import android.util.Log
import com.learning.journey.base.core.utils.BaseHeaderUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(baseHeaderUtil: BaseHeaderUtil) : Interceptor {

    private var localBaseHeaderUtil: BaseHeaderUtil? = null

    init {
        this.localBaseHeaderUtil = baseHeaderUtil
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        try {
            if (localBaseHeaderUtil != null && request.url.toUri().toString().contains(".zip")
                    .not()
            ) {
                val headers = this.localBaseHeaderUtil?.prepareHeaderMap()
                var requestBuilder: Request.Builder = request.newBuilder()
                if (headers != null) {
                    for (header in headers) {
                        requestBuilder.addHeader(header.key, header.value)
                    }
                }
                return chain.proceed(requestBuilder.build())
            }
            return chain.proceed(request.newBuilder().build())
        } catch (e: Exception) {
            Log.e("NetworkInteraceptor", e.localizedMessage)
        }
        return chain.proceed(request.newBuilder().build())
    }
}