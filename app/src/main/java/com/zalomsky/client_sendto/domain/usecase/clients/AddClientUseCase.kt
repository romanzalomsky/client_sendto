package com.zalomsky.client_sendto.domain.usecase.clients

import com.zalomsky.client_sendto.repository.ClientDataSource
import com.zalomsky.client_sendto.domain.models.Client
import javax.inject.Inject

class AddClientUseCase@Inject constructor(
    private val clientDataSource: ClientDataSource
) {

    suspend operator fun invoke(client: Client) = clientDataSource.add(client = client)
}