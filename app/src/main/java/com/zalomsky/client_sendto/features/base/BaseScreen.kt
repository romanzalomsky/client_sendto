package com.zalomsky.client_sendto.features.base

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.property.TextAlignment
import com.zalomsky.client_sendto.R
import com.zalomsky.client_sendto.common.SendToButton
import com.zalomsky.client_sendto.common.SendToTwoListItem
import com.zalomsky.client_sendto.common.floatingButtonColor
import com.zalomsky.client_sendto.common.pdfIcon
import com.zalomsky.client_sendto.common.rubikMedium
import com.zalomsky.client_sendto.common.textColor
import com.zalomsky.client_sendto.domain.models.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun BaseScreen(
    toAddClientScreen: () -> Unit,
) {

    val viewModel: BaseViewModel = hiltViewModel()
    val clients = viewModel.clients.observeAsState(listOf()).value

    val scope = rememberCoroutineScope()

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
                onClick = {
                    scope.launch(Dispatchers.Main) {
                        downloadPdf(clients = clients)
                    }
                },
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
                text = stringResource(id = R.string.clientBase),
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

            SendToButton(
                modifier = Modifier,
                onClick = toAddClientScreen,
                textId = R.string.addClient
            )
        }
    }
}

fun downloadPdf(
    clients: List<Client>,
) {
    try {
        val fileName = "clients.pdf"
        val downloadDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val filePath = File(downloadDir, fileName)
        val pdfDocument = PdfDocument(PdfWriter(filePath.absolutePath))
        val document = Document(pdfDocument)
        document.add(Paragraph("Clients list").apply {
            setTextAlignment(TextAlignment.CENTER)
            setFontSize(16f)
            setBold()
        })

        for (client in clients) {
            val clientInfo =
                "ID: ${client.id}" + "\n" + "Email: ${client.email}" + "\n" + "Phone: ${client.phone}"
            document.add(Paragraph(clientInfo))
        }

        document.close()
    } catch (e: Exception) {
        Log.e("lkjhg", "${e.localizedMessage}")
        e.printStackTrace()
    }
}