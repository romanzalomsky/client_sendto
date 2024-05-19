package com.zalomsky.client_sendto.features.task.edit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.shared.task.domain.usecase.GetTaskByIdUseCase
import com.zalomsky.client_sendto.shared.task.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<EditTaskState> = MutableStateFlow(EditTaskState.Initial)
    val state: StateFlow<EditTaskState> = _state.asStateFlow()

    fun preloadTask(taskId: String) {
        _state.value = EditTaskState.Loading

        viewModelScope.launch {
            runCatching {
                getTaskByIdUseCase(taskId)
            }.onSuccess {
                _state.value = EditTaskState.Content(task = it)
            }.onFailure {
                _state.value = EditTaskState.Error(message = it.message.orEmpty())
            }
        }
    }

    fun updateTask(name: String, description: String, date: String, time: String) {
        val content = _state.value as? EditTaskState.Content ?: return
        val updatedTask = content.task.copy(
            taskName = name,
            description = description,
            date = date,
            time = time
        )

        viewModelScope.launch {
            runCatching {
                updateTaskUseCase(content.task.id, updatedTask)
            }
            // TODO: Show result on UI
        }
    }
}