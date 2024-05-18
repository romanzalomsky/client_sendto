package com.zalomsky.client_sendto.features.task.ui

import android.annotation.SuppressLint
import com.zalomsky.client_sendto.features.task.presentation.TasksState.Initial
import com.zalomsky.client_sendto.features.task.presentation.TasksState.Loading
import com.zalomsky.client_sendto.features.task.presentation.TasksState.Content
import com.zalomsky.client_sendto.features.task.presentation.TasksState.Error
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.rubiklight
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.textColor
import com.zalomsky.client_sendto.common.whiteColor
import com.zalomsky.client_sendto.features.task.presentation.TaskViewModel
import com.zalomsky.client_sendto.features.task.presentation.TasksState
import kotlinx.coroutines.launch

// EXAMPLE
@Composable
fun TasksScreen() {
    val viewModel: TaskViewModel = hiltViewModel()
    val state by viewModel.state.observeAsState() // TODO: replace with collectAsStateWithLifecycle (подтянуть зависимость надо)

    when (state!!) { // !! уберутся когда заменишь на collectAsStateWithLifecycle

        Initial, Loading -> { TODO() }

        is Content       -> { TODO() }

        is Error         -> { TODO() }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen( // Todo: divide into small, reusable elements
    onTaskAdd: () -> Unit,
    onTaskEdit: (String?) -> Unit
) {

    val viewModel: TaskViewModel = hiltViewModel()
    val tasks = viewModel.tasks.observeAsState(listOf()).value

    val checkedState = remember { mutableStateMapOf<String, Boolean>() } // TODO: Move to TasksState
    val scope = rememberCoroutineScope() // TODO: Delete

    LaunchedEffect(Unit) {
        viewModel.loadTasks() // 👍
    }

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = onTaskAdd,
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = plus), contentDescription = "")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val companyName = " \"ЭйчТиСофт\""

            Text(
                text = stringResource(id = R.string.tasks) + companyName,
                fontSize = 15.sp,
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                modifier = Modifier.padding(top = 20.dp),
            ) {
                items(tasks) { task ->
                    Card(
                        elevation = 0.dp,
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, systemColor),
                        modifier = Modifier
                            .padding(5.dp)
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .height(70.dp)
                            .clickable{
                                scope.launch {
                                    onTaskEdit(task.id)
                                }
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
                                    checked = checkedState[task.id] ?: false,
                                    onCheckedChange = { isChecked ->
                                        checkedState[task.id] = isChecked
                                        if (isChecked) {
                                            viewModel.deleteTask(task.id) {}
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable {
                                            val currentState = checkedState[task.id] ?: false
                                            checkedState[task.id] = !currentState
                                        },
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
            }
        }
    }
}