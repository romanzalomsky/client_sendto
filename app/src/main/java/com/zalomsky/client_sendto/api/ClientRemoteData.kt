package com.zalomsky.client_sendto.api

import javax.inject.Inject

class ClientRemoteData @Inject constructor(
    private val clientApi: ClientApi
) {

    suspend fun getClientApiInterface() = clientApi.getClients()

}