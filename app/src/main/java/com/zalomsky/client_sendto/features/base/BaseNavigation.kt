package com.zalomsky.client_sendto.features.base

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions

fun NavController.navigateToAddClientScreen() =
    navigate(MainDestinations.ADD_CLIENT_ROUTE, defaultNavOptions())

fun NavGraphBuilder.addClientScreen(upPress: () -> Unit){
    composable(MainDestinations.ADD_CLIENT_ROUTE){
        AddClientScreen(
            onBackPressed = upPress
        )
    }
}