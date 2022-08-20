package com.learning.journey.base.core.data.model

import com.google.gson.annotations.SerializedName

data class BaseHeaderValues(
    @SerializedName("timezone") val timezone: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("appId") val appId: String?,
    @SerializedName("installationId") val installationId: String = "",
    @SerializedName("userId") val userId: String = "",
    @SerializedName("bearerToken") val bearerToken: String = "",
    @SerializedName("latitude") val latitude: Double = 0.0,
    @SerializedName("longitude") val longitude: Double = 0.0,
    @SerializedName("deviceId") val deviceId: String,
)