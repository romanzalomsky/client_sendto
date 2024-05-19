package com.zalomsky.client_sendto.features.task.edit.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.back
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.whiteColor
import com.zalomsky.client_sendto.design.CircularProgressLoadingScreen
import com.zalomsky.client_sendto.design.ErrorScreen
import com.zalomsky.client_sendto.features.task.details.presentation.TaskDetailsViewModel
import com.zalomsky.client_sendto.features.task.details.ui.TaskDetails
import com.zalomsky.client_sendto.features.task.edit.presentation.EditTaskState
import com.zalomsky.client_sendto.features.task.edit.presentation.EditTaskViewModel

@Composable
fun EditTaskScreen(
    taskId: String?,
    onBackPressed: () -> Unit
) {
    val viewModel: EditTaskViewModel = hiltViewModel()
    val taskDetailsViewModel: TaskDetailsViewModel = hiltViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()
    val taskDetailsState by taskDetailsViewModel.state.collectAsStateWithLifecycle()

    var preloadTaskSetUp by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(taskId) {
        viewModel.preloadTask(taskId.orEmpty())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.editTask),
                        color = whiteColor
                    )
                },
                backgroundColor = systemColor,
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed
                    ) {
                        Icon(
                            painter = painterResource(id = back),
                            tint = whiteColor,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val taskFieldsState = taskDetailsViewModel.state.value

                    viewModel.updateTask(
                        name = taskFieldsState.name,
                        description = taskFieldsState.description,
                        date = taskFieldsState.date,
                        time = taskFieldsState.time
                    )
                },
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = plus), contentDescription = "")
            }
        }
    ) {
        when (val currentState = state) {
            EditTaskState.Initial, EditTaskState.Loading -> CircularProgressLoadingScreen()

            is EditTaskState.Content -> {
                LaunchedEffect(currentState) {
                    if (!preloadTaskSetUp) {
                        taskDetailsViewModel.setupInitialValues(currentState.task)
                        preloadTaskSetUp = true
                    }
                }

                TaskDetails(
                    state = taskDetailsState,
                    modifier = Modifier.padding(it),
                    onNameChanged = taskDetailsViewModel::changeName,
                    onDescriptionChanged = taskDetailsViewModel::changeDescription,
                    onDateChanged = taskDetailsViewModel::changeDate,
                    onTimeChanged = taskDetailsViewModel::changeTime
                )
            }

            is EditTaskState.Error -> ErrorScreen(
                message = currentState.message,
                onReloadClicked = { viewModel.preloadTask(taskId.orEmpty()) }
            )
        }
    }
}