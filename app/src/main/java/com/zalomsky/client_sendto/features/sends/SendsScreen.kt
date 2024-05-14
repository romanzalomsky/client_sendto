package com.zalomsky.client_sendto.features.sends

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.BottomSheet
import com.zalomsky.client_sendto.common.SendListItem
import com.zalomsky.client_sendto.common.address_book
import com.zalomsky.client_sendto.common.clients
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.reload
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.settings
import com.zalomsky.client_sendto.common.sort
import com.zalomsky.client_sendto.common.template
import com.zalomsky.client_sendto.common.textColor
import kotlinx.coroutines.launch

data class Send(
    val id: Int,
    val name: String,
    val kind: String,
    val type: String,
    val util: String,
    val getters: String,
    val countOfGetters: Int,
)

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SendsScreen(
    onSendsAdd: () -> Unit,
    toAddressBook: () -> Unit
) {

    val sendsList = mutableListOf<Send>()
    sendsList.add(Send(1, "20% скидки", "реклама", "Email", "Шаблон Скидки", "Клиенты магазина", 20))
    sendsList.add(Send(2, "Акция", "оповещение", "Email", "Шаблон Скидки", "Клиенты", 45))

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val showBottomSheet = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val isBottomSheetVisible = remember { mutableStateOf(true) }
    val isFabVisible = remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        showBottomSheet.value = true
                        sheetState.show()
                    }
                },
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = settings), contentDescription = "")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val companyName = " \"ЭйчТиСофт\""

            Text(
                text = stringResource(id = R.string.sends) + companyName,
                fontSize = 15.sp,
                fontWeight = FontWeight(300),
                color = textColor,
                fontFamily = rubikMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                modifier = Modifier.padding(top = 20.dp),
            ) {
                items(sendsList) { send ->
                    SendListItem(send = send)
                }
            }
        }
        BottomSheet(
            sheetState = sheetState,
            onSendAdd = onSendsAdd,
            toAddressBook = toAddressBook
        )
    }
}