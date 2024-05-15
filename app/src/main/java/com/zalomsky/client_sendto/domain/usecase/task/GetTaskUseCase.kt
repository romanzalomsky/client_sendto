package com.zalomsky.client_sendto.domain.usecase.task

import com.zalomsky.client_sendto.domain.models.Task
import com.zalomsky.client_sendto.repository.TaskRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(): List<Task> = taskRepository.getTaskApiInterface()
}