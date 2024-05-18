package com.zalomsky.client_sendto.features.task.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.features.task.domain.Task
import com.zalomsky.client_sendto.features.task.domain.usecase.AddTaskUseCase
import com.zalomsky.client_sendto.features.task.domain.usecase.DeleteTaskUseCase
import com.zalomsky.client_sendto.features.task.domain.usecase.GetTaskByIdUseCase
import com.zalomsky.client_sendto.features.task.domain.usecase.GetAllTasksUseCase
import com.zalomsky.client_sendto.features.task.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Divide view models - AddTaskViewModel, EditTaskViewModel, TasksViewModel (можно сделать одну общую для add и edit если логика пересекается)
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    // TODO: Single state of the screen

    private val _state: MutableLiveData<TasksState> = MutableLiveData(TasksState.Initial)
    val state: LiveData<TasksState> = _state

    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?>
        get() = _task

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    fun exampleLoadTasks() {
        _state.value = TasksState.Loading

        viewModelScope.launch {
            val tasks = getAllTasksUseCase()

            _state.value = TasksState.Content(tasks = tasks)
        }

        // or

        viewModelScope.launch {
            runCatching {
                getAllTasksUseCase()
            }.onSuccess {
                _state.value = TasksState.Content(tasks = it)
            }.onFailure {
                _state.value = TasksState.Error(message = it.message.orEmpty())
            }
        }
    }

    fun exampleLoadTaskById(id: String) {
        _state.value = TasksState.Loading

        viewModelScope.launch {
            val task = getTaskByIdUseCase(id)

            val content = _state.value as? TasksState.Content ?: return@launch
            // _state.value = content.copy(selectedTask = task)
        }
    }

    fun loadTasks() {
        viewModelScope.launch {
            // TODO: Add exception handler (maybe smth like BaseViewModel?) to remove try-catch or use run catching at least..
            try {
                val tasks = getAllTasksUseCase()
                _tasks.postValue(tasks)
            } catch (e: Exception) {
                // TODO: Maybe, additionally show on snackbar or on UI?
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun loadTaskById(taskId: String) {
        viewModelScope.launch {
            try {
                val task = getTaskByIdUseCase(taskId)
                _task.value = task
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun addTask(task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                addTaskUseCase(task)
                onSuccess()
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun updateTask(taskId: String, task: Task, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                updateTaskUseCase(taskId, task)
                onSuccess()
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun deleteTask(taskId: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                deleteTaskUseCase(taskId)
                onSuccess()
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }
}