package com.learning.journey.base.core.constant

import com.learning.journey.BuildConfig

object AppConstants {

    var baseUrl = ""

    init {
        baseUrl = when (BuildConfig.BUILD_TYPE) {
            "release" -> {
                "https://my-json-server.typicode.com/cyclewala/posts-data/"
            }
            "uat" -> {
                "https://my-json-server.typicode.com/cyclewala/posts-data/"
            }
            else -> {
                "https://my-json-server.typicode.com/cyclewala/posts-data/"
            }
        }
    }

}