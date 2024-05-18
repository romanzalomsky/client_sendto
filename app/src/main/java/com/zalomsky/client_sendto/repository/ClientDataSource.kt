package com.zalomsky.client_sendto.repository

import com.zalomsky.client_sendto.service.ClientApi
import com.zalomsky.client_sendto.domain.models.Client
import retrofit2.Response
import javax.inject.Inject

class ClientDataSource @Inject constructor(
    private val clientApi: ClientApi
) {

    suspend fun get(): List<Client> = clientApi.clients()

    suspend fun add(client: Client): Response<Client> = clientApi.add(client)
}