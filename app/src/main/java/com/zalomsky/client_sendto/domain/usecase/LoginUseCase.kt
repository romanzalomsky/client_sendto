package com.zalomsky.client_sendto.domain.usecase

import com.zalomsky.client_sendto.repository.UserRepository
import com.zalomsky.client_sendto.domain.models.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(loginRequest: LoginRequest) = userRepository.getLogin(loginRequest = loginRequest)
}