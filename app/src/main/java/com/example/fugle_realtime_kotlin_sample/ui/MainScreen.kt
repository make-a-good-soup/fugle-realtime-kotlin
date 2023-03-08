package com.example.fugle_realtime_kotlin_sample.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Webhook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fugle_realtime_kotlin_sample.data.HttpData
import com.example.fugle_realtime_kotlin_sample.ui.components.FTabItem
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
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
        ) {
            FTabItem(
                title = "Http",
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTabIndex = 0 },
                imageVector = Icons.Default.Public,
            )
            FTabItem(
                title = "WebSocket",
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTabIndex = 1 },
                imageVector = Icons.Default.Webhook,
            )
        }
    }
}

@DelicateCoroutinesApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
