package com.zalomsky.client_sendto.domain.usecase

import com.zalomsky.client_sendto.repository.UserDataSource
import com.zalomsky.client_sendto.domain.models.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend operator fun invoke(loginRequest: LoginRequest) = userDataSource.login(loginRequest = loginRequest)
}