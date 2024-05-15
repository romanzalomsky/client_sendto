package com.zalomsky.client_sendto.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Task (

    @SerializedName("id") val id: String,
    @SerializedName("taskName") val taskName: String,
    @SerializedName("description") val description: String,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("status") val status: Boolean,
    @SerializedName("userId") val userId: String
)