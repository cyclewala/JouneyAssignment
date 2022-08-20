package com.learning.journey.base.core.data.model

import com.google.gson.annotations.SerializedName

class BaseAPIError {
    @SerializedName("errorCode")
    var errorCode = 0

    @SerializedName("errorMessage")
    var errorMessage: String? = null

    @SerializedName("data")
    var data: Map<String, Any>? = null
}