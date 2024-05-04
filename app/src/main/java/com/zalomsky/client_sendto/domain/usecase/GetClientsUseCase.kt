package com.zalomsky.client_sendto.domain.usecase

import com.zalomsky.client_sendto.api.ClientRemoteData
import com.zalomsky.client_sendto.domain.models.Client
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
    private val clientRemoteData: ClientRemoteData
) {

    suspend operator fun invoke(): List<Client> = clientRemoteData.getClientApiInterface()
}