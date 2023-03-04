package com.example.fugle_realtime_kotlin_sample.data

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.makeagoodsoup.fugle_realtime_lib.core.repository.FugleHttpRepository
import net.makeagoodsoup.fugle_realtime_lib.core.repository.successOr

sealed class HttpData : RequestDelegate {

    companion object {
        fun values(): List<HttpData> = HttpData::class.sealedSubclasses.mapNotNull { it.objectInstance }
    }

    @DelicateCoroutinesApi
    object Meta : HttpData() {
        override fun name(): String = "Meta"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val result = FugleHttpRepository().getMeta(symbolId, token)
                callback.invoke("${result.successOr(null)}")
            }
        }
    }

    @DelicateCoroutinesApi
    object Quote : HttpData() {
        override fun name(): String = "Quote"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val result = FugleHttpRepository().getQuote(symbolId, token)
                callback.invoke("${result.successOr(null)}")
            }
        }
    }
    // TODO: more Http
}