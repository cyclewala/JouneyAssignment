package com.learning.journey.base.core.data.persistent.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "post")
data class Post(
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val urlToImage: String,
) : Parcelable