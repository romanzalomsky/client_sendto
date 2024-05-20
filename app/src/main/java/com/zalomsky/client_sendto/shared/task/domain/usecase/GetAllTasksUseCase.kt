package com.zalomsky.client_sendto.shared.task.domain.usecase

import com.zalomsky.client_sendto.shared.task.domain.Task
import com.zalomsky.client_sendto.shared.task.data.TaskDataSource
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    taskDataSource: TaskDataSource
) : suspend () -> List<Task> by taskDataSource::get