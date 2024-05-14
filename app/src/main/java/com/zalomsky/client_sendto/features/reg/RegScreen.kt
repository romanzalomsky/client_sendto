package com.zalomsky.client_sendto.features.reg

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToButton
import com.zalomsky.client_sendto.common.SendToTextField
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.textColor
import com.zalomsky.client_sendto.domain.models.RoleModel
import com.zalomsky.client_sendto.domain.models.User
import com.zalomsky.client_sendto.features.base.BaseViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(
    toAuth: () -> Unit,
    onEnter: () -> Unit,
) {

    val viewModel: RegistrationViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 70.dp)
        ) {

            val paddingHorizontal = Modifier.padding(horizontal = 30.dp)

            var username by remember { mutableStateOf("") }
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
                value = username,
                onValueChange = { newText ->
                    username = newText
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
                onClick = {
                    if(password.equals(password2)){
                        coroutineScope.launch {
                            val user = User(
                                id = "",
                                username = username,
                                email = mail,
                                password = password,
                                companyName = companyName,
                                role = RoleModel.USER
                            )
                            viewModel.createNewUser(user, onSuccess = onEnter)
                        }
                    } else {
                        Log.d("Error epta", password2)
                    }
                },
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
                        text = stringResource(id = R.string.name3),
                        color = textColor,
                        fontFamily = rubikMedium
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        text = stringResource(id = R.string.enter),
                        color = systemColor,
                        fontFamily = rubikMedium
                    )
                }
            }
        }
    }
}