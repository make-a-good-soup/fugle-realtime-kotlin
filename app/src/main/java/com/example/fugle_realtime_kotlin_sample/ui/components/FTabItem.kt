package com.example.fugle_realtime_kotlin_sample.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Webhook
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FTabItem(title: String, modifier: Modifier, imageVector: ImageVector = Icons.Default.Star) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector,
            contentDescription = title,
        )
        Text(
            text = title,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    FTabItem(
        title = "WebSocket",
        modifier = Modifier
            .height(56.dp)
            .width(150.dp),
        imageVector = Icons.Default.Webhook,
    )
}