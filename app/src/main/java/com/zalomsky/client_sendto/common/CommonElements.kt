package com.zalomsky.client_sendto.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.domain.models.Book
import com.zalomsky.client_sendto.features.sends.Send
import com.zalomsky.client_sendto.domain.models.Client
import com.zalomsky.client_sendto.domain.models.Task


@Composable
fun SendToTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textId: Int,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(whiteColor)
            .border(1.dp, systemColor, RoundedCornerShape(8.dp)),
        singleLine = true,
        label = { Text(text = stringResource(id = textId)) }
    )
}

@Composable
fun SendToButton(
    modifier: Modifier,
    onClick: () -> Unit,
    textId: Int,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .padding(horizontal = 30.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = systemColor
        ),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = textId),
            color = whiteColor,
            fontFamily = rubikMedium,
            fontSize = 18.sp
        )
    }
}

@Composable
fun SendToTwoListItem(
    client: Client,
) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(1.dp, systemColor),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(5.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(5.dp),
                text = client.email,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(horizontal = 5.dp),
                text = client.phone,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)

            )
        }
    }
}

@Composable
fun SendToTwoListItemNew(
    book: Book,
    onClick: () -> Unit
) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(1.dp, systemColor),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(5.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(5.dp),
                text = book.bookName,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(horizontal = 5.dp),
                text = book.bookType,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)

            )
        }
    }
}

@Composable
fun SendToTaskListItem(
    task: Task,
    onTaskEdit: () -> Unit,
) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, systemColor),
        modifier = Modifier
            .padding(5.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clickable(onClick = onTaskEdit)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = task.taskName,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { /* Обработчик изменения состояния */ },
                    modifier = Modifier.padding(5.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = systemColor,
                        uncheckedColor = systemColor,
                        checkmarkColor = whiteColor
                    )
                )
            }
        }
    }
}

@Composable
fun SendListItem(
    send: Send,
) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(1.dp, systemColor),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(5.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.naming) + ": " + send.name,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.kind) + ": " + send.kind,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(horizontal = 5.dp),
                text = stringResource(id = R.string.type) + ": " + send.type,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(horizontal = 5.dp),
                text = stringResource(id = R.string.utils) + ": " + send.util,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(horizontal = 5.dp),
                text = stringResource(id = R.string.getters) + ": " + send.getters,
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(horizontal = 5.dp),
                text = stringResource(id = R.string.countOfGetters) + ": " + send.countOfGetters.toString(),
                color = textColor,
                fontFamily = rubiklight,
                style = TextStyle(fontSize = 16.sp)

            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetState: ModalBottomSheetState,
    onSendAdd: () -> Unit,
    toAddressBook: () -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Настройки",
                    style = TextStyle(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.spacedBy(60.dp)
                    ) {
                        BottomSheetElement(onClick = onSendAdd, textId = R.string.add, icon = plus)
                        BottomSheetElement(onClick = {}, textId = R.string.sort, icon = sort)
                        BottomSheetElement(onClick = {}, textId = R.string.update, icon = reload)
                    }
                    Row(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.spacedBy(80.dp)
                    ) {
                        BottomSheetElement(onClick = toAddressBook, textId = R.string.addressBook, icon = address_book)
                        BottomSheetElement(onClick = {}, textId = R.string.templates, icon = template)
                        BottomSheetElement(onClick = {}, textId = R.string.clients, icon = clients)
                    }
                }
            }
        },
        sheetState = sheetState,
        content = {}
    )
}

@Composable
fun BottomSheetElement(
    onClick: () -> Unit,
    textId: Int,
    icon: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(floatingButtonColor)
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(painter = painterResource(id = icon), contentDescription = "")
            }
        }
        Text(
            text = stringResource(id = textId),
            fontSize = 12.sp
        )
    }
}

@Composable
fun DropDownItem(
    expanded: MutableState<Boolean>,
    selectedItem: MutableState<String>,
    dropdownItems: List<String>
) {
    Box(
        modifier = Modifier
            .clickable { expanded.value = true }
            .border(1.dp, systemColor, shape = RoundedCornerShape(10.dp))
            .padding(8.dp)
            .padding(horizontal = 10.dp)
            .padding(top = 5.dp)
    ) {
        Text(selectedItem.value)
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            dropdownItems.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem.value = item
                        expanded.value = false
                    }
                ) {
                    Text(item)
                }
            }
        }
    }
}