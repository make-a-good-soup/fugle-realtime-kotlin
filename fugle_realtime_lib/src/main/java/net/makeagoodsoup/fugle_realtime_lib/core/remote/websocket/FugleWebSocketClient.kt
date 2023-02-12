package net.makeagoodsoup.fugle_realtime_lib.core.remote.websocket

import kotlinx.coroutines.channels.Channel

interface FugleWebSocketClient {
    fun quote(symbolId: String, apiToken: String): Channel<String>
}