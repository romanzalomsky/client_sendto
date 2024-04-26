package com.zalomsky.client_sendto

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun SendToApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val serverUrl = "http://192.168.0.101:8080"
        val responseState = remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        scope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                sendGetRequest(serverUrl)
            }
            responseState.value = response
        }
        Text(text = responseState.value)
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
