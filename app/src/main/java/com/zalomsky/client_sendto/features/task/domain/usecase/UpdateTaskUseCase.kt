package com.zalomsky.client_sendto.features.task.domain.usecase

import com.zalomsky.client_sendto.features.task.domain.Task
import com.zalomsky.client_sendto.features.task.data.TaskDataSource
import retrofit2.Response
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    taskDataSource: TaskDataSource
) : suspend (String, Task) -> Response<Task> by taskDataSource::update