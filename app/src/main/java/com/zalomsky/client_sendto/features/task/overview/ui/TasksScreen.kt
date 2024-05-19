package com.zalomsky.client_sendto.features.task.overview.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.rubiklight
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.textColor
import com.zalomsky.client_sendto.common.whiteColor
import com.zalomsky.client_sendto.design.CircularProgressLoadingScreen
import com.zalomsky.client_sendto.design.ErrorScreen
import com.zalomsky.client_sendto.shared.task.domain.Task
import com.zalomsky.client_sendto.features.task.overview.presentation.TasksOverviewViewModel
import com.zalomsky.client_sendto.features.task.overview.presentation.TasksOverviewState.Initial
import com.zalomsky.client_sendto.features.task.overview.presentation.TasksOverviewState.Loading
import com.zalomsky.client_sendto.features.task.overview.presentation.TasksOverviewState.Content
import com.zalomsky.client_sendto.features.task.overview.presentation.TasksOverviewState.Error

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    navigateToAddTask: () -> Unit,
    navigateToEditTask: (String?) -> Unit
) {
    val viewModel: TasksOverviewViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddTask,
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = plus), contentDescription = "")
            }
        },
        topBar = {
            val companyName = " \"ЭйчТиСофт\""

            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.tasks, companyName) + companyName,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(300),
                        color = textColor,
                        fontFamily = rubikMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                },
                backgroundColor = Color.Transparent,
                elevation = 1.dp
            )
        }
    ) {
        when (val currentState = state) {

            Initial, Loading -> CircularProgressLoadingScreen()

            is Content -> Content(
                tasks = currentState.tasks,
                onTaskClicked = navigateToEditTask,
                onTaskChecked = viewModel::deleteTask,
            )

            is Error -> ErrorScreen(
                message = currentState.message,
                onReloadClicked = viewModel::loadTasks
            )
        }
    }
}

@Composable
private fun Content(
    tasks: List<Task>,
    onTaskClicked: (String?) -> Unit,
    onTaskChecked: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 20.dp),
        ) {
            items(tasks) { task ->
                TaskItem(task = task, onClick = onTaskClicked, onCheck = onTaskChecked)
            }
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onClick: (String) -> Unit,
    onCheck: (String) -> Unit,
) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, systemColor),
        modifier = Modifier
            .padding(5.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clickable {
                onClick(task.id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = task.taskName,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = {
                        onCheck(task.id)
                    },
                    modifier = Modifier.padding(5.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = systemColor,
                        uncheckedColor = systemColor,
                        checkmarkColor = whiteColor
                    )
                )
            }
        }
    }
}
