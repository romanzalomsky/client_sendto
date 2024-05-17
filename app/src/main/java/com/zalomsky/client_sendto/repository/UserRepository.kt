package com.zalomsky.client_sendto.repository

import com.zalomsky.client_sendto.domain.models.User
import com.zalomsky.client_sendto.service.UserApiService
import com.zalomsky.client_sendto.domain.models.LoginRequest
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApiService: UserApiService,
) {

    suspend fun getLogin(loginRequest: LoginRequest) = userApiService.login(loginRequest = loginRequest)

    suspend fun getUser() = userApiService.getUserInfo()

    suspend fun getRegistration(user: User) = userApiService.registration(user = user)
}