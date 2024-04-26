package com.zalomsky.client_sendto.features.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.sendGetRequest
import com.zalomsky.client_sendto.utils.connection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    toRegistration: () -> Unit,
    onEnter: () -> Unit,
) {

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface {

            val serverUrl = "http://192.168.0.101:8080"
            val responseState = remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Button(
                    onClick = toRegistration
                ) {
                    Text(text = stringResource(id = R.string.registration))
                }
                Button(
                    onClick = onEnter
                ) {
                    Text(text = stringResource(id = R.string.enter))
                }
                LaunchedEffect(Unit) {
                    runCatching {
                        val response = withContext(Dispatchers.IO) {
                            sendGetRequest(serverUrl)
                        }
                        responseState.value = response
                    }
                }
                Text(text = responseState.value)
            }
        }
    }
}