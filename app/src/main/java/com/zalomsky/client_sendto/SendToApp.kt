package com.zalomsky.client_sendto

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.zalomsky.client_sendto.common.BottomNavigationBar
import com.zalomsky.client_sendto.ui.theme.Client_sendtoTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun SendToApp() {
    Client_sendtoTheme {

        val appState = rememberSendToAppState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {

            },
            bottomBar = {
                if(appState.shouldShowBottomBar){
                    BottomNavigationBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                    TabRowDefaults.Divider(
                        color = Color.Black.copy(alpha = 0.25f),
                        thickness = 1.dp
                    )
                }
            }
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = MainDestinations.START_ROUTE
            ){
                sendToNavGraph(
                    navController = appState.navController,
                    upPress = appState::upPress
                )
            }
        }
    }
}

