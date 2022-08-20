package com.learning.journey.content.data.remote.response

import com.google.gson.annotations.SerializedName
import com.learning.journey.base.core.data.persistent.entities.Comment
import com.learning.journey.base.core.data.persistent.entities.Post

data class ContentAPIResponse(
    @SerializedName("posts") val posts: List<Post>,
    @SerializedName("comments") val comments: List<Comment>,
)
