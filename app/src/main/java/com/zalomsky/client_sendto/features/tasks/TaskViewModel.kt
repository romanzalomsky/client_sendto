package com.zalomsky.client_sendto.features.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.domain.models.Task
import com.zalomsky.client_sendto.domain.usecase.task.AddTaskUseCase
import com.zalomsky.client_sendto.domain.usecase.task.DeleteTaskUseCase
import com.zalomsky.client_sendto.domain.usecase.task.GetTaskByIdUseCase
import com.zalomsky.client_sendto.domain.usecase.task.GetTaskUseCase
import com.zalomsky.client_sendto.domain.usecase.task.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?>
        get() = _task

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    fun getTasksList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val tasks = getTaskUseCase()
                _tasks.postValue(tasks)
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun getTaskById(taskId: String) {
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