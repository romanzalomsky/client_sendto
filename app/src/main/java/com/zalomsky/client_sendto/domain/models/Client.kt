package com.zalomsky.client_sendto.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Client(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("bookId") val bookId: String
)
