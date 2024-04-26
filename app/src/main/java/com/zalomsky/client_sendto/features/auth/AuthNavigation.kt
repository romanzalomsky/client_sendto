package com.zalomsky.client_sendto.features.auth

import androidx.navigation.NavController
import com.zalomsky.client_sendto.BaseSections
import com.zalomsky.client_sendto.MainDestinations
import com.zalomsky.client_sendto.defaultNavOptions

fun NavController.navigateToRegistrationScreen() =
    navigate(MainDestinations.REGISTRATION_ROUTE, defaultNavOptions())

fun NavController.navigateToAuthScreen() =
    navigate(MainDestinations.AUTH_ROUTE, defaultNavOptions())

fun NavController.navigateToBaseScreen() =
    navigate(BaseSections.BASE.route, defaultNavOptions())