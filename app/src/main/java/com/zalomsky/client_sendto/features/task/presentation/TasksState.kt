package com.zalomsky.client_sendto.features.task.presentation

import com.zalomsky.client_sendto.features.task.domain.Task

sealed class TasksState { // example

    data object Initial : TasksState()

    data object Loading : TasksState()

    data class Content(val tasks: List<Task> = emptyList()) : TasksState()

    data class Error(val message: String) : TasksState() // not necessary
}

/**
 * USAGE EXAMPLE
 *
    when (state)  {

        Initial, Loading -> LoadingIndicator()

        Content          -> Content(state.tasks)
 */