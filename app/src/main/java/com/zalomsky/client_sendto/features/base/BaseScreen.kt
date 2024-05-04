package com.zalomsky.client_sendto.features.base

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToButton
import com.zalomsky.client_sendto.common.SendToTwoListItem
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.pdfIcon
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.textColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun BaseScreen(
    toAddClientScreen: () -> Unit,
) {

    val headerText = " \"ЭйчТиСофт\""

    val viewModel: BaseViewModel = hiltViewModel()
    val clients = viewModel.clients.observeAsState(listOf()).value

    LaunchedEffect(Unit) {
       viewModel.getClientsList()
    }

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = pdfIcon), contentDescription = "")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.clientBase) + headerText,
                fontSize = 15.sp,
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                items(clients) { client ->
                    SendToTwoListItem(client = client)
                }
            }

            SendToButton(modifier = Modifier, onClick = toAddClientScreen, textId = R.string.addClient)
        }
    }
}
