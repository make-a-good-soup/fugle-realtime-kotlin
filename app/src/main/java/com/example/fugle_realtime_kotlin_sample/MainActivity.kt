package com.example.fugle_realtime_kotlin_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fugle_realtime_kotlin_sample.ui.theme.FuglerealtimekotlinsampleTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.makeagoodsoup.fugle_realtime_lib.core.repository.FugleHttpRepository
import net.makeagoodsoup.fugle_realtime_lib.core.repository.successOr

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuglerealtimekotlinsampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
                Button(onClick = {
                    GlobalScope.launch {
                        val result = FugleHttpRepository().getMeta("2884", "demo")
                        println("get meta: ${result.successOr(null)}")
                    }
                }) {
                    Text(text = "測試")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FuglerealtimekotlinsampleTheme {
        Greeting("Android")
    }
}