package com.zalomsky.client_sendto.service

import com.zalomsky.client_sendto.domain.models.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskApiService {

    @GET("/auth/tasks")
    suspend fun getTasks(): List<Task>

    @GET("/auth/tasks/{taskId}")
    suspend fun getTaskById(@Path("taskId") taskId: String): Task?

    @PUT("/auth/tasks/{taskId}")
    suspend fun updateTask(
        @Path("taskId") taskId: String,
        @Body task: Task
    ): Response<Task>

    @POST("/auth/tasks/add")
    suspend fun createTask(@Body task: Task): Response<Task>

    @DELETE("/auth/tasks/{taskId}")
    suspend fun deleteTask(@Path("taskId") taskId: String)
}