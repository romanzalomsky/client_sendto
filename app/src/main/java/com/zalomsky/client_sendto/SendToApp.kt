package com.zalomsky.client_sendto

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zalomsky.client_sendto.common.BottomNavigationBar
import com.zalomsky.client_sendto.features.auth.AuthScreen
import com.zalomsky.client_sendto.ui.theme.Client_sendtoTheme
import com.zalomsky.client_sendto.utils.connection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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

fun sendGetRequest(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    val response: Response = client.newCall(request).execute()
    return response.body?.string() ?: ""
}

