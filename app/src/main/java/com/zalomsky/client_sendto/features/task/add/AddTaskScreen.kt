package com.zalomsky.client_sendto.features.task.add

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToTextField
import com.zalomsky.client_sendto.common.back
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.whiteColor
import com.zalomsky.client_sendto.features.task.details.presentation.TaskDetailsViewModel
import com.zalomsky.client_sendto.features.task.details.ui.TaskDetails
import com.zalomsky.client_sendto.features.task.presentation.TaskViewModel
import com.zalomsky.client_sendto.features.task.domain.Task
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddTaskScreen(onBackPressed: () -> Unit) {
    val taskDetailsViewModel: TaskDetailsViewModel = hiltViewModel()
    val viewModel: AddTaskViewModel = hiltViewModel()

    val taskDetailsState by taskDetailsViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.addTask),
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
                    viewModel.createTask(
                        name = taskDetailsState.name,
                        description = taskDetailsState.description,
                        date = taskDetailsState.date,
                        time = taskDetailsState.time
                    )
                },
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = plus), contentDescription = "")
            }
        }
    ) {
        TaskDetails(
            state = taskDetailsState,
            onNameChanged = taskDetailsViewModel::changeName,
            onDescriptionChanged = taskDetailsViewModel::changeDescription,
            onDateChanged = taskDetailsViewModel::changeDate,
            onTimeChanged = taskDetailsViewModel::changeTime
        )
    }
}