package com.zalomsky.client_sendto.features.task

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions
import com.zalomsky.client_sendto.features.task.ui.AddTaskScreen
import com.zalomsky.client_sendto.features.task.ui.EditTaskScreen

// TODO: store route names in corresponding packages/modules
const val TASKS_ROUTE = "tasks"
const val TASK_ADD_ROUTE = "task_add"
const val TASK_EDIT_ROUTE = "task_edit"

fun NavController.navigateToAddTaskScreen() =
    navigate(MainDestinations.ADD_TASK_ROUTE, defaultNavOptions())

fun NavController.navigateToEditTaskScreen(taskId: String) =
    navigate("${MainDestinations.EDIT_TASK_ROUTE}/$taskId", defaultNavOptions())

fun NavGraphBuilder.addTaskScreen(upPress: () -> Unit){
    composable(MainDestinations.ADD_TASK_ROUTE){
        AddTaskScreen(
            onBackPressed = upPress
        )
    }
}

fun NavGraphBuilder.editTaskScreen(upPress: () -> Unit){
    composable(
        MainDestinations.EDIT_TASK_ROUTE + "/{id}",
        arguments = listOf(navArgument("id"){type = NavType.StringType})
    ){
        EditTaskScreen(
            taskId = it.arguments?.getString("id"),
            onBackPressed = upPress
        )
    }
}