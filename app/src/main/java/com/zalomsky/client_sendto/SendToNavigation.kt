package com.zalomsky.client_sendto

import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.zalomsky.client_sendto.features.account.AccountScreen
import com.zalomsky.client_sendto.features.auth.AuthScreen
import com.zalomsky.client_sendto.features.auth.navigateToAuthScreen
import com.zalomsky.client_sendto.features.auth.navigateToBaseScreen
import com.zalomsky.client_sendto.features.auth.navigateToRegistrationScreen
import com.zalomsky.client_sendto.features.base.BaseScreen
import com.zalomsky.client_sendto.features.reg.RegistrationScreen
import com.zalomsky.client_sendto.features.sends.SendsScreen
import com.zalomsky.client_sendto.features.tasks.TaskScreen

fun NavGraphBuilder.sendToNavGraph(
    navController: NavHostController,
    upPress: () -> Unit
){
    navigation(
        route = MainDestinations.START_ROUTE,
        startDestination = MainDestinations.AUTH_ROUTE,
    ){
        otherRoutes(
            navController = navController
        )
        bottomRoutes(
            navController = navController
        )
    }
}

fun NavGraphBuilder.otherRoutes(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    composable(MainDestinations.REGISTRATION_ROUTE) {
        RegistrationScreen(
            toAuth = {
                navController.navigateToAuthScreen()
            },
            onEnter = {
                navController.navigateToBaseScreen()
            }
        )
    }
    composable(MainDestinations.AUTH_ROUTE) {
        AuthScreen(
            toRegistration = {
                navController.navigateToRegistrationScreen()
            },
            onEnter = {
                navController.navigateToBaseScreen()
            }
        )
    }
}

fun NavGraphBuilder.bottomRoutes(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    composable(BaseSections.BASE.route) {
        BaseScreen()
    }
    composable(BaseSections.TASKS.route) {
        TaskScreen()
    }
    composable(BaseSections.SENDS.route) {
        SendsScreen()
    }
    composable(BaseSections.ACCOUNT.route) {
        AccountScreen()
    }
}


fun defaultNavOptions(popUp: String? = null, inclusive: Boolean = false) = navOptions {
    launchSingleTop = true

    popUp?.let {
        restoreState = true

        popUpTo(popUp) {
            this.inclusive = inclusive

            saveState = true
        }
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}