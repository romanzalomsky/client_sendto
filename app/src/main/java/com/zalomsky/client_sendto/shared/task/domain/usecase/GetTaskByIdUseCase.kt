package com.zalomsky.client_sendto.shared.task.domain.usecase

import com.zalomsky.client_sendto.shared.task.domain.Task
import com.zalomsky.client_sendto.shared.task.data.TaskDataSource
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    taskDataSource: TaskDataSource
) : suspend (String) -> Task by taskDataSource::getById