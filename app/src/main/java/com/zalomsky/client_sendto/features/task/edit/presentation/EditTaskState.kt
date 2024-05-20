package com.zalomsky.client_sendto.features.task.edit.presentation

import com.zalomsky.client_sendto.shared.task.domain.Task

sealed interface EditTaskState {

    data object Initial : EditTaskState

    data object Loading : EditTaskState

    data class Content(val task: Task) : EditTaskState

    data class Error(val message: String) : EditTaskState
}