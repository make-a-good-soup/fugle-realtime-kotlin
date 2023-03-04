package com.example.fugle_realtime_kotlin_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.fugle_realtime_kotlin_sample.ui.MainScreen
import com.example.fugle_realtime_kotlin_sample.ui.theme.FuglerealtimekotlinsampleTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuglerealtimekotlinsampleTheme {
                MainScreen()
            }
        }
    }
}