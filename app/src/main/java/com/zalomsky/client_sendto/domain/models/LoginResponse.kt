package com.zalomsky.client_sendto.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(

    val token: String,
)
