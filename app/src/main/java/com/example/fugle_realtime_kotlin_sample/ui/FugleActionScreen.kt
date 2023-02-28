package com.example.fugle_realtime_kotlin_sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fugle_realtime_kotlin_sample.ui.components.ActionButton

val terminalTextContent = mutableStateOf("")

@ExperimentalMaterial3Api
@Composable
fun FugleActionScreen(title: String) {
    Scaffold(
        topBar = { AppBarSection(title) },
        bottomBar = { BottomNavigationSection() }
    ) {
        Box(modifier = Modifier.padding(it)) {
            TerminalTextSection(text = terminalTextContent.value)
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

@Composable
private fun BottomNavigationSection() {
    val scrollState = rememberScrollState()

    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(scrollState),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(10) {
                ActionButton(title = "test")
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

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun FugleHttpScreenPreview() {
    FugleActionScreen("Test Preview")
}
