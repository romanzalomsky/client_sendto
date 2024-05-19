package com.zalomsky.client_sendto.features.task.overview.presentation

import com.zalomsky.client_sendto.features.task.domain.Task

sealed interface TasksOverviewState {

    data object Initial : TasksOverviewState

    data object Loading : TasksOverviewState

    data class Content(val tasks: List<Task>) : TasksOverviewState
}