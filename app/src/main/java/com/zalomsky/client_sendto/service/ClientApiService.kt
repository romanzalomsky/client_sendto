package com.zalomsky.client_sendto.service

import com.zalomsky.client_sendto.domain.models.Client
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientApiService {

    @GET("/auth/clients")
    suspend fun getClients(): List<Client>

    @POST("/auth/clients/add")
    suspend fun createClient(@Body client: Client): Response<Client>
}