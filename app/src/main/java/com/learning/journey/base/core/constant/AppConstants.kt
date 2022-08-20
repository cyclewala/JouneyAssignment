package com.learning.journey.base.core.constant

import com.learning.journey.BuildConfig

object AppConstants {

    var baseUrl = ""

    init {
        if (BuildConfig.BUILD_TYPE == "release") {
            baseUrl = "https://my-json-server.typicode.com/cyclewala/posts-data/"
        } else if (BuildConfig.BUILD_TYPE == "uat") {
            baseUrl = "https://my-json-server.typicode.com/cyclewala/posts-data/"
        } else {
            baseUrl = "https://my-json-server.typicode.com/cyclewala/posts-data/"
        }
    }

    object API_URL {
        const val NEWS_FEED_URL =
            "https://newsapi.org/v2/top-headlines?q=India&apiKey=2ba311b4691347efbef2c107609bb8c8"
    }

}