package com.zalomsky.client_sendto.features.reg

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.zalomsky.client_sendto.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(
    toAuth: () -> Unit,
    onEnter: () -> Unit,
) {

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Button(
                    onClick = toAuth
                ) {
                    Text(text = stringResource(id = R.string.auth))
                }
                Button(
                    onClick = onEnter
                ) {
                    Text(text = stringResource(id = R.string.enter))
                }
            }
        }
    }
}