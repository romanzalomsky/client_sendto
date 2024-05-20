package com.zalomsky.client_sendto.features.task.add

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.shared.task.domain.Task
import com.zalomsky.client_sendto.shared.task.domain.usecase.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
) : ViewModel() {

    fun createTask(name: String, description: String, date: String, time: String) {
        viewModelScope.launch {
            val taskToAdd = Task(
                id = UUID.randomUUID().toString(),
                taskName = name,
                description = description,
                date = date,
                time = time,
                status = false,
                userId = "" // так и должно быть????
            )

            runCatching {
                addTaskUseCase(taskToAdd)
            }.onSuccess {
                Log.d(TAG, "task $name added!")
            }.onFailure {
                Log.e(TAG, "createTask error: ", it)
            }
        }
    }
}