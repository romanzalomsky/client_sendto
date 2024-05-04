package com.zalomsky.client_sendto.features.base

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToTextField
import com.zalomsky.client_sendto.common.back
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.whiteColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddClientScreen(
    onBackPressed: () -> Unit
) {

    val baseViewModel: BaseViewModel

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.addClient),
                        color = whiteColor
                    )
                },
                backgroundColor = systemColor,
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed
                    ){
                        Icon(painter = painterResource(id = back),
                            tint = whiteColor,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = plus), contentDescription = "")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
        ) {

            val padding = Modifier.padding(horizontal = 30.dp)

            var mail by remember { mutableStateOf("") }
            var phone by remember { mutableStateOf("") }

            SendToTextField(
                value = mail,
                onValueChange = { newText ->
                    mail = newText
                },
                modifier = padding.padding(top = 20.dp),
                textId = R.string.mail
            )
            SendToTextField(
                value = phone,
                onValueChange = { newText ->
                    phone = newText
                },
                modifier = padding.padding(top = 20.dp),
                textId = R.string.phone
            )
        }
    }
}