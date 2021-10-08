package com.example.problem2.network.model

import com.google.gson.annotations.SerializedName

data class PublishPostReq(
        @SerializedName("email")
        var email: String,

        @SerializedName("publisher_type")
        var publisher_type: String,

        @SerializedName("is_joke")
        var is_joke: Int,

        @SerializedName("description")
        var description: String

)