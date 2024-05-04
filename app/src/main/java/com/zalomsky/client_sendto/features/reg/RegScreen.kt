package com.zalomsky.client_sendto.features.reg

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToButton
import com.zalomsky.client_sendto.common.SendToTextField
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.textColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(
    toAuth: () -> Unit,
    onEnter: () -> Unit,
) {
    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 70.dp)
        ) {

            val paddingHorizontal = Modifier.padding(horizontal = 30.dp)

            var login by remember { mutableStateOf("") }
            var mail by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var password2 by remember { mutableStateOf("") }
            var companyName by remember { mutableStateOf("") }

            Text(
                text = stringResource(id = R.string.name),
                fontSize = 40.sp,
                fontWeight = FontWeight(900),
                color = systemColor,
                fontFamily = rubikMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.createAcc),
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = paddingHorizontal
                    .padding(top = 80.dp)
            )
            SendToTextField(
                value = login,
                onValueChange = { newText ->
                    login = newText
                },
                modifier = paddingHorizontal.padding(top = 20.dp),
                textId = R.string.login
            )
            SendToTextField(
                value = mail,
                onValueChange = { newText ->
                    mail = newText
                },
                modifier = paddingHorizontal.padding(top = 20.dp),
                textId = R.string.mail
            )
            SendToTextField(
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                modifier = paddingHorizontal.padding(top = 20.dp),
                textId = R.string.password
            )
            SendToTextField(
                value = password2,
                onValueChange = { newText ->
                    password2 = newText
                },
                modifier = paddingHorizontal.padding(top = 20.dp),
                textId = R.string.password2
            )
            SendToTextField(
                value = companyName,
                onValueChange = { newText ->
                    companyName = newText
                },
                modifier = paddingHorizontal.padding(top = 20.dp),
                textId = R.string.companyName
            )
            SendToButton(
                modifier = Modifier,
                onClick = onEnter,
                textId = R.string.enter
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp)
            ) {
                TextButton(
                    onClick = toAuth
                ) {
                    Text(
                        text = stringResource(id = R.string.name2),
                        color = textColor,
                        fontFamily = rubikMedium
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        text = stringResource(id = R.string.create),
                        color = systemColor,
                        fontFamily = rubikMedium
                    )
                }
            }
        }
    }
}