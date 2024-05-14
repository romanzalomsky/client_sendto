package com.zalomsky.client_sendto.features.sends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions

fun NavController.navigateToAddSendsScreen() =
    navigate(MainDestinations.ADD_SENDS_ROUTE, defaultNavOptions())

fun NavGraphBuilder.addSendsScreen(upPress: () -> Unit){
    composable(MainDestinations.ADD_SENDS_ROUTE){
        SendsAddScreen(
            onBackPressed = upPress
        )
    }
}