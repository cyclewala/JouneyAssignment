package com.learning.journey.base.core.data.model

import com.google.gson.annotations.SerializedName

abstract class BaseAPIResult {

    @SerializedName("status")
    var status = 0

    @SerializedName("error")
    var baseAPIError: BaseAPIError? = null

    @SerializedName("executionTimeInMS")
    var executionTimeInMS: Long = 0

    @SerializedName("serverTime")
    var serverTime: String? = null
}