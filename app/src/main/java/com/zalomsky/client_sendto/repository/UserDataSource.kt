package com.zalomsky.client_sendto.repository

import com.zalomsky.client_sendto.domain.models.User
import com.zalomsky.client_sendto.service.UserApi
import com.zalomsky.client_sendto.domain.models.LoginRequest
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserApi,
) {

    suspend fun login(loginRequest: LoginRequest) = userApi.login(loginRequest)

    suspend fun registration(user: User) = userApi.registration(user)
}