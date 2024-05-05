package com.zalomsky.client_sendto.api

import com.zalomsky.client_sendto.domain.models.Client
import javax.inject.Inject

class ClientRemoteData @Inject constructor(
    private val clientApi: ClientApi
) {

    suspend fun getClientApiInterface() = clientApi.getClients()

    suspend fun addClient(client: Client) = clientApi.createClient(client = client)
}