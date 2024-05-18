package com.zalomsky.client_sendto.features.sends.book

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToTextField
import com.zalomsky.client_sendto.common.back
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.plus
import com.zalomsky.client_sendto.common.systemColor
import com.zalomsky.client_sendto.common.whiteColor
import com.zalomsky.client_sendto.domain.models.Book
import com.zalomsky.client_sendto.domain.models.Task

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditBookScreen(
    bookId: String?,
    onBackPressed: () -> Unit
) {
    val viewModel: BookViewModel = hiltViewModel()
    val book by viewModel.book.collectAsState()

    var bookName by remember { mutableStateOf("") }
    var bookType by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getBookById(bookId?: "")
    }

    if (book != null) {
        bookName = book?.bookName?: ""
        bookType = book?.bookType?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.editBook),
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
                onClick = {
                    val bookUpdated = Book(
                        id = "",
                        bookName = bookName,
                        bookType = bookType,
                        userId = ""
                    )
                    viewModel.updateBook(bookId?: "", bookUpdated, onBackPressed)
                },
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

            SendToTextField(
                value = bookName,
                onValueChange = { newText ->
                    bookName = newText
                },
                modifier = padding.padding(top = 20.dp),
                textId = R.string.task
            )
            SendToTextField(
                value = bookType,
                onValueChange = { newText ->
                    bookType = newText
                },
                modifier = padding.padding(top = 20.dp),
                textId = R.string.description
            )
        }
    }
}