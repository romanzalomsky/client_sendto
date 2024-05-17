package com.zalomsky.client_sendto.features.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.textColor
import com.zalomsky.client_sendto.features.base.BaseViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountScreen(

) {

    val viewModel: AccountScreenViewModel = hiltViewModel()
    val user by viewModel.user.collectAsState()

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 20.dp)
        ) {

            val username = user?.username ?: ""
            val email = user?.email ?: ""
            val companyName = user?.companyName ?: ""

            Text(
                text = stringResource(id = R.string.account),
                fontSize = 15.sp,
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Имя пользователя: " + "${username}",
                fontSize = 15.sp,
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = Modifier.padding(horizontal = 20.dp).padding(top = 10.dp)
            )
            Text(
                text = "Почта: " + "${email}",
                fontSize = 15.sp,
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = Modifier.padding(horizontal = 20.dp).padding(top = 10.dp)
            )
            Text(
                text = "Название компании: " + "${companyName}",
                fontSize = 15.sp,
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = Modifier.padding(horizontal = 20.dp).padding(top = 10.dp)
            )
        }
    }
}