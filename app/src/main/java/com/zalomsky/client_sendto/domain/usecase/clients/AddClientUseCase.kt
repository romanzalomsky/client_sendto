package com.zalomsky.client_sendto.domain.usecase.clients

import com.zalomsky.client_sendto.repository.ClientRepository
import com.zalomsky.client_sendto.domain.models.Client
import javax.inject.Inject

class AddClientUseCase@Inject constructor(
    private val clientRepository: ClientRepository
) {

    suspend operator fun invoke(client: Client) = clientRepository.addClient(client = client)
}