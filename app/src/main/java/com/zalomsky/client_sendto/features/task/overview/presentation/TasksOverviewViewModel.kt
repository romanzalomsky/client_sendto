package com.zalomsky.client_sendto.features.task.overview.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.features.task.domain.Task
import com.zalomsky.client_sendto.features.task.overview.presentation.TasksOverviewState.Initial
import com.zalomsky.client_sendto.features.task.domain.usecase.GetAllTasksUseCase
import com.zalomsky.client_sendto.features.task.domain.usecase.GetTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksOverviewViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskByIdUseCase: GetTaskByIdUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<TasksOverviewState> = MutableStateFlow(Initial)
    val state: StateFlow<TasksOverviewState> = _state

    fun loadTasks() {
        _state.value = TasksOverviewState.Loading

        viewModelScope.launch {
            runCatching {
                getAllTasksUseCase()
            }.onSuccess {
                _state.value = TasksOverviewState.Content(tasks = it)
            }.onFailure {
                Log.e(TAG, "loadTasks error: ", it)
                _state.value = TasksOverviewState.Error(message = it.message.orEmpty())
            }
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            runCatching {
                deleteTaskByIdUseCase(taskId)
            }.onSuccess {
                Log.d(TAG, "task with id $taskId deleted!")
            }.onFailure {
                Log.e(TAG, "deleteTask error", it)
            }
        }
    }
}