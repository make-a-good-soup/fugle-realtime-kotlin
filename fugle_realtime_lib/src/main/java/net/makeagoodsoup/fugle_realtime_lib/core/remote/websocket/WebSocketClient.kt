package net.makeagoodsoup.fugle_realtime_lib.core.remote.websocket

import kotlinx.coroutines.channels.Channel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class WebSocketClient : FugleWebSocketClient {
    companion object {
        private const val BASE_URL = "wss://api.fugle.tw/realtime/v0.3"
        const val NORMAL_CLOSURE_STATUS = 1000
    }

    private var webSocket: WebSocket? = null
    private var webSocketListener: WebSocketListener? = null

    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(39, TimeUnit.SECONDS)
        .hostnameVerifier { _, _ -> true }
        .build()

    /**
     * start websocket
     * */

    private fun start(webSocketUrl: String, webSocketListener: WebSocketListener) {
        this.webSocketListener = webSocketListener
        this.webSocket = client.newWebSocket(
            Request.Builder().url(webSocketUrl).build(),
            webSocketListener
        )
        client.dispatcher.executorService.shutdown()
    }

    /**
     * stop websocket
     * */
    fun stop() {
        try {
            webSocket?.close(NORMAL_CLOSURE_STATUS, null)
            webSocket = null
            //webSocketListener?.socketEventChannel?.close()
            webSocketListener = null
        } catch (_: Exception) {
        }
    }

    override fun quote(symbolId: String, apiToken: String): Channel<String> = with(WebSocketListener()) {
        start("$BASE_URL/intraday/quote?symbolId=$symbolId&apiToken=$apiToken", this)
        this.socketEventChannel
    }
}