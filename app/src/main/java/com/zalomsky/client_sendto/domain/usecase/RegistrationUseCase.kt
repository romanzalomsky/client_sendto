package com.zalomsky.client_sendto.domain.usecase

import com.zalomsky.client_sendto.repository.UserDataSource
import com.zalomsky.client_sendto.domain.models.User
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend operator fun invoke(user: User) = userDataSource.registration(user = user)
}