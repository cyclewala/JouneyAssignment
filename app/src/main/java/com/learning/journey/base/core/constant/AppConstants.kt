package com.learning.journey.base.core.constant

import com.learning.journey.BuildConfig

object AppConstants {

    var baseUrl = ""

    init {
        if (BuildConfig.BUILD_TYPE == "release") {
            baseUrl = "https://api.edukite.info/"
        } else if (BuildConfig.BUILD_TYPE == "uat") {
            baseUrl = "https://api.edukite-uat.info/"
        } else {
            baseUrl = "https://api.edukite-dev.info/"
        }
    }

    object API_URL {
        const val NEWS_FEED_URL =
            "https://newsapi.org/v2/top-headlines?q=India&apiKey=2ba311b4691347efbef2c107609bb8c8"
    }

}