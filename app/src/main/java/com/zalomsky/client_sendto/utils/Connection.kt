package com.zalomsky.client_sendto.utils

import androidx.compose.runtime.mutableStateOf
import com.zalomsky.client_sendto.sendGetRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun connection() {

    val serverUrl = "http://192.168.0.101:8080"
    val responseState = mutableStateOf("")

    runCatching {
        val response = withContext(Dispatchers.IO) {
            sendGetRequest(serverUrl)
        }
        responseState.value = response
    }
}