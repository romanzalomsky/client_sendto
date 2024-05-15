package com.zalomsky.client_sendto.repository

import com.zalomsky.client_sendto.domain.models.Task
import com.zalomsky.client_sendto.service.TaskApiService
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskApiService: TaskApiService
) {

    suspend fun getTaskApiInterface() = taskApiService.getTasks()

    suspend fun getTaskById(taskId: String) = taskApiService.getTaskById(taskId)

    suspend fun updateTask(taskId: String, task: Task) = taskApiService.updateTask(taskId, task)

    suspend fun deleteTask(taskId: String) = taskApiService.deleteTask(taskId)

    suspend fun addTask(task: Task) = taskApiService.createTask(task)
}