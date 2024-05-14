package com.zalomsky.client_sendto.features.sends.addressBook

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions

fun NavController.navigateToABScreen() =
    navigate(MainDestinations.AB_ROUTE, defaultNavOptions())

fun NavGraphBuilder.aBScreen(upPress: () -> Unit){
    composable(MainDestinations.AB_ROUTE){
        AddressBookScreen(
            onBackPressed = upPress
        )
    }
}