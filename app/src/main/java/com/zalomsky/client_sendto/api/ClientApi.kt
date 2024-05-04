package com.zalomsky.client_sendto.api

import com.zalomsky.client_sendto.domain.models.Client
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientApi {

    @GET("/clients")
    suspend fun getClients(): List<Client>

    @POST("add_client")
    suspend fun createClient(): Client
}