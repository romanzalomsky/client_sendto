package com.zalomsky.client_sendto.api

import com.zalomsky.client_sendto.domain.models.Client
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientApi {

    @GET("/clients")
    suspend fun getClients(): List<Client>

    @POST("/clients/add")
    suspend fun createClient(@Body client: Client): Response<Client>
}