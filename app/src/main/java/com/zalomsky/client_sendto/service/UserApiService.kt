package com.zalomsky.client_sendto.service

import com.zalomsky.client_sendto.domain.models.LoginResponse
import com.zalomsky.client_sendto.domain.models.LoginRequest
import com.zalomsky.client_sendto.domain.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("auth/refresh")
    suspend fun refreshToken(
        @Header("Authorization") token: String,
    ): Response<LoginResponse>

    @GET("/auth/get_user_info")
    suspend fun getUserInfo(): User? //todo: fix request

    @POST("/registration")
    suspend fun registration(@Body user: User): Response<User>
}