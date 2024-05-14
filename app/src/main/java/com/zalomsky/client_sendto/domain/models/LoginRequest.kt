package com.zalomsky.client_sendto.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(

    val email: String,
    val password: String
)
