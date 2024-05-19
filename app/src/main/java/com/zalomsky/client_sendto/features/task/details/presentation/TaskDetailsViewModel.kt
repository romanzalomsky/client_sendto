package com.zalomsky.client_sendto.features.task.details.presentation

import androidx.lifecycle.ViewModel
import com.zalomsky.client_sendto.shared.task.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<TaskDetailsState> = MutableStateFlow(TaskDetailsState())
    val state: StateFlow<TaskDetailsState> = _state.asStateFlow()

    fun setupInitialValues(task: Task) {
        _state.value = TaskDetailsState(
            name = task.taskName,
            description = task.description,
            date = task.date,
            time = task.time
        )
    }

    fun changeName(newValue: String) {
        _state.update {
            it.copy(name = newValue)
        }
    }

    fun changeDescription(newValue: String) {
        _state.update {
            it.copy(description = newValue)
        }
    }

    fun changeDate(newValue: String) {
        _state.update {
            it.copy(date = newValue)
        }
    }

    fun changeTime(newValue: String) {
        _state.update {
            it.copy(time = newValue)
        }
    }
}