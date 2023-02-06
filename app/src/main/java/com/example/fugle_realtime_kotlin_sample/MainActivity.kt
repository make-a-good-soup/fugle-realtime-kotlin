package com.example.fugle_realtime_kotlin_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fugle_realtime_kotlin_sample.ui.theme.FuglerealtimekotlinsampleTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.makeagoodsoup.fugle_realtime_lib.core.repository.FugleHttpRepository
import net.makeagoodsoup.fugle_realtime_lib.core.repository.successOr

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Body()
        }
    }
}

val resultText = mutableStateOf("")

@Composable()
fun Body() {
    return FuglerealtimekotlinsampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                SendHttpRequestButton()
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                )
                TerminalText()
            }
        }
    }
}

@Composable
fun SendHttpRequestButton() {
    return Button(onClick = {
        resultText.value = "InProgress"
        GlobalScope.launch {
            val result = FugleHttpRepository().getMeta("2884", "demo")
            println("get meta: ${result.successOr(null)}")
            resultText.value = "${result.successOr(null)}"
        }
    }) {
        Text(text = "Get Meta")
    }
}

@Composable
fun TerminalText() {
    val text by resultText
    return Text(
        text = text, color = Color.White, fontSize = 16.sp, modifier = Modifier
            .background(color = Color.Black)
            .fillMaxHeight()
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Body()
}