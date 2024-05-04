package com.zalomsky.client_sendto.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int,
    val email: String,
    val phone: String
)