package com.zalomsky.client_sendto.repository

import com.zalomsky.client_sendto.service.ClientApiService
import com.zalomsky.client_sendto.domain.models.Client
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val clientApiService: ClientApiService
) {

    suspend fun getClientApiInterface() = clientApiService.getClients()

    suspend fun addClient(client: Client) = clientApiService.createClient(client = client)
}