package com.zalomsky.client_sendto.features.sends

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.DropDownItem
import com.zalomsky.client_sendto.common.SendToButton
import com.zalomsky.client_sendto.common.SendToTextField
import com.zalomsky.client_sendto.common.back
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.textColor
import com.zalomsky.client_sendto.common.whiteColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SendsAddScreen(
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.addSends),
                        color = whiteColor
                    )
                },
                backgroundColor = systemColor,
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed
                    ) {
                        Icon(
                            painter = painterResource(id = back),
                            tint = whiteColor,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                backgroundColor = floatingButtonColor
            ) {
                Icon(painter = painterResource(id = plus), contentDescription = "")
            }
        }
    ) {

        var naming by remember { mutableStateOf("") }
        var text by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                elevation = 0.dp,
                border = BorderStroke(1.dp, systemColor),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(5.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = stringResource(id = R.string.add1),
                        color = textColor,
                        fontFamily = rubikMedium,
                        style = TextStyle(fontSize = 16.sp)
                    )
                    SendToTextField(
                        value = naming,
                        onValueChange = {},
                        modifier = Modifier.padding(horizontal = 10.dp),
                        textId = R.string.naming
                    )
                    SendToTextField(
                        value = text,
                        onValueChange = {},
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(top = 5.dp),
                        textId = R.string.sendsText
                    )
                    val itemsType = listOf("Email", "Sms")
                    val itemsKind = listOf("Реклама", "Оповещение")
                    val selectedItem = remember { mutableStateOf(itemsType[0]) }
                    val selectedItem1 = remember { mutableStateOf(itemsKind[0]) }
                    val expanded = remember { mutableStateOf(false) }
                    val expanded1 = remember { mutableStateOf(false) }
                    DropDownItem(expanded = expanded, selectedItem = selectedItem, dropdownItems = itemsType)
                    DropDownItem(expanded = expanded1, selectedItem = selectedItem1, dropdownItems = itemsKind)
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = systemColor
                        ),
                        modifier = Modifier
                            .width(160.dp)
                            .padding(top = 5.dp)
                            .padding(horizontal = 10.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(2.dp)),
                    ) {
                        Text(
                            text = stringResource(id = R.string.send),
                            color = whiteColor,
                            fontFamily = rubikMedium,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            Card(
                elevation = 0.dp,
                border = BorderStroke(1.dp, systemColor),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(5.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = stringResource(id = R.string.add2),
                        color = textColor,
                        fontFamily = rubikMedium,
                        style = TextStyle(fontSize = 16.sp)
                    )
                    SendToTextField(
                        value = naming,
                        onValueChange = {},
                        modifier = Modifier.padding(horizontal = 10.dp),
                        textId = R.string.naming
                    )

                    //TODO: доделать выпадающие списки
                    val itemsType = listOf("Email", "Sms")
                    val itemsKind = listOf("Реклама", "Оповещение")
                    val selectedItem = remember { mutableStateOf(itemsType[0]) }
                    val selectedItem1 = remember { mutableStateOf(itemsKind[0]) }
                    val expanded = remember { mutableStateOf(false) }
                    val expanded1 = remember { mutableStateOf(false) }
                    DropDownItem(expanded = expanded, selectedItem = selectedItem, dropdownItems = itemsType)
                    DropDownItem(expanded = expanded1, selectedItem = selectedItem1, dropdownItems = itemsKind)
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = systemColor
                        ),
                        modifier = Modifier
                            .width(160.dp)
                            .padding(top = 5.dp)
                            .padding(horizontal = 10.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(2.dp)),
                    ) {
                        Text(
                            text = stringResource(id = R.string.send),
                            color = whiteColor,
                            fontFamily = rubikMedium,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}