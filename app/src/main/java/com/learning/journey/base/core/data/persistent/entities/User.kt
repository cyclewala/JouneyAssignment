package com.learning.journey.base.core.data.persistent.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @SerializedName("_id") @PrimaryKey val _id: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("freeTrialUnit") var freeTrialUnit: String?,
    @SerializedName("updatedAt") var updatedAt: String,
    @SerializedName("firstName") var firstName: String?,
    @SerializedName("lastName") var lastName: String?,
    @SerializedName("emailAddress") var emailAddress: String?,
    @SerializedName("inviteCode") var inviteCode: String?,
    @SerializedName("role") var role: String?,
    @SerializedName("bizType") var bizType: String?,
    @SerializedName("onboardingCompleted") var onboardingCompleted: Boolean,
    @SerializedName("isMobileVerified") var isMobileVerified: Boolean,
    @SerializedName("grade") var grade: Int?,
    @SerializedName("cellPhone") var cellPhone: String?,
    @SerializedName("countryCode") var countryCode: String?,
    @SerializedName("referrerCode") var referrerCode: String?,
    @SerializedName("profilePicture") var profilePicture: String?,
    @SerializedName("parentName") var parentName: String?,
    @SerializedName("dateOfBirth") var dateOfBirth: String?,
    @SerializedName("gender") var gender: String?,
    @SerializedName("schoolName") var schoolName: String?,
    @SerializedName("city") var city: String?,
    @SerializedName("state") var state: String?,
    @SerializedName("parentCountryCode") var parentCountryCode: String?,
    @SerializedName("parentCellPhone") var parentCellPhone: String?,
    @SerializedName("parentEmailAddress") var parentEmailAddress: String?,
    @SerializedName("credits") var credits: Int? = 0,
    @SerializedName("userGrade") var userGrade: String?,
    @SerializedName("installReferrer") var installReferrer: String?,
    @SerializedName("username") var username: String?,
    @SerializedName("organization") var organization: String?,
    @SerializedName("isEmailVerified") var isEmailVerified: Boolean,
)