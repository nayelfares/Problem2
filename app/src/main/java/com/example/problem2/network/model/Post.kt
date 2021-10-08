package com.example.problem2.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
        @SerializedName("userId")
        var userId: Int,

        @SerializedName("id")
        var id: Int,

        @SerializedName("title")
        var title: String,

        @SerializedName("body")
        var body: String
): Parcelable