package com.zalomsky.client_sendto.features.task.data

import com.zalomsky.client_sendto.features.task.domain.Task
import retrofit2.Response
import javax.inject.Inject

class TaskDataSource @Inject constructor(
    private val taskApi: TaskApi
) {

    suspend fun get(): List<Task> = taskApi.tasks()

    suspend fun getById(id: String): Task? = taskApi.getById(id)

    suspend fun update(taskId: String, task: Task): Response<Task> = taskApi.update(taskId, task)

    suspend fun delete(taskId: String) {
        taskApi.delete(taskId)
    }

    suspend fun addTask(task: Task): Response<Task> = taskApi.add(task)
}