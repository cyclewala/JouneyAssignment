package com.learning.journey.content.data.remote.datasource

import com.learning.journey.base.core.data.remote.BaseDataSource
import com.learning.journey.content.data.remote.service.ContentAPIService
import javax.inject.Inject

class ContentDataSource @Inject constructor(
    private val dbAPIService: ContentAPIService,
) : BaseDataSource() {

    suspend fun fetchContent() =
        getResult {
            dbAPIService.fetchContent()
        }

}