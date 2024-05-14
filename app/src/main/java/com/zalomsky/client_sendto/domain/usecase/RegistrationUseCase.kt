package com.zalomsky.client_sendto.domain.usecase

import com.zalomsky.client_sendto.repository.UserRepository
import com.zalomsky.client_sendto.domain.models.User
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) = userRepository.getRegistration(user = user)
}