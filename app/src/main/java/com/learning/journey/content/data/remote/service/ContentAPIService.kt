package com.learning.journey.content.data.remote.service

import com.learning.journey.content.data.remote.response.ContentAPIResponse
import retrofit2.Response
import retrofit2.http.GET

interface ContentAPIService {

    @GET("db")
    suspend fun fetchContent(): Response<ContentAPIResponse>

}