package net.makeagoodsoup.fugle_realtime_lib.core.remote.websocket

import kotlinx.coroutines.flow.Flow
import net.makeagoodsoup.fugle_realtime_lib.core.entities.ChartData
import net.makeagoodsoup.fugle_realtime_lib.core.entities.QuoteData

interface FugleWebSocketClient {
    fun connectQuote(symbolId: String, apiToken: String): Flow<QuoteData>
    fun connectChart(symbolId: String, apiToken: String): Flow<ChartData>
}