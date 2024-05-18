package com.zalomsky.client_sendto.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Book(

    @SerializedName("id") val id: String,
    @SerializedName("bookName") val bookName: String,
    @SerializedName("bookType") val bookType: String,
    @SerializedName("userId") val userId: String
)
