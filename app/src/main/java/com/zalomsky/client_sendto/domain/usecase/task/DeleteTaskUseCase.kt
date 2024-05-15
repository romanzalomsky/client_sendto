package com.zalomsky.client_sendto.domain.usecase.task

import com.zalomsky.client_sendto.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(taskId: String) = taskRepository.deleteTask(taskId)
}