package com.zalomsky.client_sendto.shared.task.data

import com.zalomsky.client_sendto.shared.task.domain.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskApi {

    @GET("/auth/tasks")
    suspend fun tasks(): List<Task>

    @GET("/auth/tasks/{taskId}")
    suspend fun getById(@Path("taskId") taskId: String): Task

    @PUT("/auth/tasks/{taskId}")
    suspend fun update(
        @Path("taskId") taskId: String,
        @Body task: Task
    ): Response<Task>

    @POST("/auth/tasks/add")
    suspend fun add(@Body task: Task): Response<Task>

    @DELETE("/auth/tasks/{taskId}")
    suspend fun delete(@Path("taskId") taskId: String)
}