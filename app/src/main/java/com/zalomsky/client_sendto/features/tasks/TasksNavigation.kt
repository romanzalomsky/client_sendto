package com.zalomsky.client_sendto.features.tasks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions
import com.zalomsky.client_sendto.features.tasks.add.AddTaskScreen
import com.zalomsky.client_sendto.features.tasks.edit.EditTaskScreen

fun NavController.navigateToAddTaskScreen() =
    navigate(MainDestinations.ADD_TASK_ROUTE, defaultNavOptions())

fun NavController.navigateToEditTaskScreen() =
    navigate(MainDestinations.EDIT_TASK_ROUTE, defaultNavOptions())

fun NavGraphBuilder.addTaskScreen(upPress: () -> Unit){
    composable(MainDestinations.ADD_TASK_ROUTE){
        AddTaskScreen(
            onBackPressed = upPress
        )
    }
}

fun NavGraphBuilder.editTaskScreen(upPress: () -> Unit){
    composable(MainDestinations.EDIT_TASK_ROUTE){
        EditTaskScreen(
            onBackPressed = upPress
        )
    }
}