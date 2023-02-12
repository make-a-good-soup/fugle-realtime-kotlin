package net.makeagoodsoup.fugle_realtime_lib.core.remote.websocket

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket

class WebSocketListener : okhttp3.WebSocketListener() {
    val socketEventChannel: Channel<String> = Channel(10)

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
//        GlobalScope.launch {
//            socketEventChannel.send(SocketUpdate(exception = SocketAbortedException()))
//        }
        webSocket.close(WebSocketClient.NORMAL_CLOSURE_STATUS, null)
        socketEventChannel.close()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
//        GlobalScope.launch {
//            socketEventChannel.send(SocketUpdate(exception = t))
//        }
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        GlobalScope.launch() {
            socketEventChannel.send(text)
        }
    }
}

