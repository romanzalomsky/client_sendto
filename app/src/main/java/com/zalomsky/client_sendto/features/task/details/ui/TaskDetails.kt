package com.zalomsky.client_sendto.features.task.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToTextField
import com.zalomsky.client_sendto.features.task.details.presentation.TaskDetailsState
import com.zalomsky.client_sendto.features.task.domain.Task

@Composable
fun TaskDetails(
    state: TaskDetailsState,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onDateChanged: (String) -> Unit,
    onTimeChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SendToTextField(
            value = state.name,
            onValueChange = onNameChanged,
            textId = R.string.task
        )

        SendToTextField(
            value = state.description,
            onValueChange = onDescriptionChanged,
            textId = R.string.description
        )

        SendToTextField(
            value = state.date,
            onValueChange = onDateChanged,
            textId = R.string.date
        )

        SendToTextField(
            value = state.time,
            onValueChange = onTimeChanged,
            textId = R.string.time
        )
    }
}