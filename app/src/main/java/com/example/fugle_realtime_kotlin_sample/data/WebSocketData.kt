package com.example.fugle_realtime_kotlin_sample.data

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.makeagoodsoup.fugle_realtime_lib.core.entities.stringify
import net.makeagoodsoup.fugle_realtime_lib.core.remote.websocket.WebSocketClient
import kotlin.time.Duration.Companion.seconds

@DelicateCoroutinesApi
sealed class WebSocketData : RequestDelegate {

    companion object {
        fun values(): List<RequestDelegate> = listOf(Meta, Quote, Chart)
        private const val defaultSymbolId = "2884"
        private const val apiToken = "demo"

    }

    val webSocketClient = WebSocketClient()

    @OptIn(DelicateCoroutinesApi::class)
    object Meta : WebSocketData() {

        override fun name(): String = "Meta"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val flow = webSocketClient.connectMeta(defaultSymbolId, apiToken)
                flow.collect {
                    callback.invoke(it.stringify())
                }
                delay(5.seconds)
                webSocketClient.disconnect()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    object Quote : WebSocketData() {

        override fun name(): String = "Quote"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val flow = webSocketClient.connectQuote(defaultSymbolId, apiToken)
                flow.collect {
                    callback.invoke(it.stringify())
                }
                delay(5.seconds)
                webSocketClient.disconnect()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    object Chart : WebSocketData() {

        override fun name(): String = "Chart"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val flow = webSocketClient.connectChart(defaultSymbolId, apiToken)
                flow.collect {
                    callback.invoke(it.stringify())
                }
                delay(5.seconds)
                webSocketClient.disconnect()
            }
        }
    }
}