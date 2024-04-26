package com.zalomsky.client_sendto.features.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountScreen() {

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(text = "Account")
        }
    }
}