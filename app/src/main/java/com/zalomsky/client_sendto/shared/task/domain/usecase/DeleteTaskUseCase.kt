package com.zalomsky.client_sendto.shared.task.domain.usecase

import com.zalomsky.client_sendto.shared.task.data.TaskDataSource
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    taskDataSource: TaskDataSource
) : suspend (String) -> Unit by taskDataSource::delete