package com.zalomsky.client_sendto.features.reg

import androidx.navigation.NavController
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions

fun NavController.navigateToAuthScreen() =
    navigate(MainDestinations.AUTH_ROUTE, defaultNavOptions())