package com.example.fugle_realtime_kotlin_sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fugle_realtime_kotlin_sample.data.HttpData


@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    // 這個變數用來追蹤當前選中的項目
    var selectedTabIndex by remember { mutableStateOf(0) }


    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(weight = 1f)) {
            when (selectedTabIndex) {
                0 -> FugleActionScreen(title = "Http Sample", actions = HttpData.values())
                1 -> FugleActionScreen(title = "WebSocket Sample", actions = listOf())
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(Color.Cyan)
        ) {
            BottomTabItem(
                text = "Http",
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTabIndex = 0 }
            )
            BottomTabItem(
                text = "WebSocket",
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTabIndex = 1 }
            )
        }
    }
}


@Composable
private fun BottomTabItem(text: String, modifier: Modifier) {
    Text(
        text,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        textAlign = TextAlign.Center,
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
