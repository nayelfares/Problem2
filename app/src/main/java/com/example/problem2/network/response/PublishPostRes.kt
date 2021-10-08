package com.example.problem2.network.response

import com.google.gson.annotations.SerializedName

data class PublishPostRes(
    @SerializedName("id")
    var id: Int,

    @SerializedName("email")
    var email: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("publisher_type")
    var publisher_type: String,

    @SerializedName("is_joke")
    var is_joke: String,

    @SerializedName("description")
    var description: String
)