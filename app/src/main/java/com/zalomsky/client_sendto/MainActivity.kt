package com.zalomsky.client_sendto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zalomsky.client_sendto.ui.theme.Client_sendtoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Client_sendtoTheme {
                SendToApp()
            }
        }
    }
}

