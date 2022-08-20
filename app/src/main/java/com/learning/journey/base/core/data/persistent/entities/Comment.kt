package com.learning.journey.base.core.data.persistent.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comment")
data class Comment(
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("body") val body: String,
    @SerializedName("postId") val postId: String,
)