package com.zalomsky.client_sendto.features.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToTaskListItem
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.textColor

data class Task(
    val id: Int,
    val name: String,
    val date: String,
    val time: String,
    val status: Boolean
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    onTaskAdd: () -> Unit,
    onTaskEdit: () -> Unit
) {

    val taskList = mutableListOf<Task>()
    taskList.add(Task(1, "Сделать коммит", "30.04.2024", "15.00", true))
    taskList.add(Task(1, "Выполнить указания", "30.04.2024", "15.00", true))
    taskList.add(Task(1, "Нанять сотрудника", "30.04.2024", "15.00", false))


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
                items(taskList) { task ->
                    SendToTaskListItem(task = task, onTaskEdit = onTaskEdit)
                }
            }
        }
    }
}