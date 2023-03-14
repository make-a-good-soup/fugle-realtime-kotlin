package com.example.fugle_realtime_kotlin_sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fugle_realtime_kotlin_sample.data.RequestDelegate
import com.example.fugle_realtime_kotlin_sample.data.WebSocketData
import com.example.fugle_realtime_kotlin_sample.ui.components.ActionButton
import kotlinx.coroutines.DelicateCoroutinesApi

private const val testSymbolId = "2884"
private const val testToken = "demo"

/**
 * @param title 頁面標題
 * @param actions 要執行的行為  @see HttpData
 * */
@DelicateCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun FugleActionScreen(title: String, actions: List<RequestDelegate>) {
    var terminalTextContent by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppBarSection(title) },
        bottomBar = {
            BottomNavigationSection(
                actions = actions,
                actionCallback = { terminalTextContent = it },
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            TerminalTextSection(text = terminalTextContent)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun AppBarSection(title: String) {
    TopAppBar(
        title = { Text(title) }
    )
}

@DelicateCoroutinesApi
@Composable
private fun BottomNavigationSection(actions: List<RequestDelegate>, actionCallback: ((String) -> Unit) = {}) {
    val scrollState = rememberScrollState()

    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(scrollState),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(actions.size) {
                if (it == 0 || it == actions.size - 1) {
                    Box(modifier = Modifier.width(5.dp))
                }
                ActionButton(
                    title = actions[it].name(),
                    onClick = {
                        // closed all websocket
                        if (actions[it] is WebSocketData) {
                            @Suppress("UNCHECKED_CAST")
                            (actions as List<WebSocketData>).forEach { e -> e.webSocketClient.disconnect() }
                        }

                        actions[it].start(
                            symbolId = testSymbolId,
                            token = testToken,
                            callback = { result -> actionCallback.invoke(result) },
                        )
                    })
                Box(modifier = Modifier.width(5.dp))
            }
        }
    }
}

@Composable
private fun TerminalTextSection(text: String) {
    val scroll = rememberScrollState(0)
    Text(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
            .verticalScroll(scroll),
        text = text,
        color = Color.White,
        fontSize = 16.sp,
    )
}

@DelicateCoroutinesApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun FugleHttpScreenPreview() {
    FugleActionScreen("Test Preview", actions = listOf())
}
