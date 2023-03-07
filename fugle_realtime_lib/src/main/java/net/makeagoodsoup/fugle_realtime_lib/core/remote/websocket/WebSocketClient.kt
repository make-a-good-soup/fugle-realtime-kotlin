package net.makeagoodsoup.fugle_realtime_lib.core.remote.websocket

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.makeagoodsoup.fugle_realtime_lib.core.entities.ChartData
import net.makeagoodsoup.fugle_realtime_lib.core.entities.Intraday
import net.makeagoodsoup.fugle_realtime_lib.core.entities.QuoteData
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@DelicateCoroutinesApi
class WebSocketClient : FugleWebSocketClient {
    companion object {
        private const val BASE_URL = "wss://api.fugle.tw/realtime/v0.3"
        const val NORMAL_CLOSURE_STATUS = 1000
    }

    private var webSocket: WebSocket? = null

    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(39, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    /**
     * start websocket
     * */

    private fun connect(webSocketUrl: String): Flow<String> {
        val socketFlow = MutableSharedFlow<String>()
        val request = Request.Builder().url(webSocketUrl).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                GlobalScope.launch {
                    socketFlow.emit(text)
                }
            }
        })
        return socketFlow
    }

    /**
     * stop websocket
     * */
    fun disconnect() {
        try {
            webSocket?.close(NORMAL_CLOSURE_STATUS, null)
            webSocket = null
        } catch (_: Exception) {
        }
    }

    override fun connectQuote(symbolId: String, apiToken: String): Flow<QuoteData> {

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<Intraday<QuoteData>> = moshi.adapter(
            Types.newParameterizedType(Intraday::class.java, QuoteData::class.java)
        )
        return connect("$BASE_URL/intraday/quote?symbolId=$symbolId&apiToken=$apiToken").map { json ->
            adapter.fromJson(json)?.data ?: throw IllegalStateException("Invalid JSON: $json")
        }
    }

    override fun connectChart(symbolId: String, apiToken: String): Flow<ChartData> {

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<Intraday<ChartData>> = moshi.adapter(
            Types.newParameterizedType(Intraday::class.java, ChartData::class.java)
        )
        return connect("$BASE_URL/intraday/chart?symbolId=$symbolId&apiToken=$apiToken").map { json ->
            adapter.fromJson(json)?.data ?: throw IllegalStateException("Invalid JSON: $json")
        }
    }
}