package com.example.fugle_realtime_kotlin_sample.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fugle_realtime_kotlin_sample.ui.theme.Purple40
import com.example.fugle_realtime_kotlin_sample.ui.theme.Purple80

@Composable
fun ActionButton(title: String, onClick: (() -> Unit) = {}) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(50))
            .border(border = BorderStroke(1.dp, Purple40), shape = RoundedCornerShape(50))
            .background(Purple80)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            title,
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ActionButton(title = "test")
}