package com.example.fugle_realtime_kotlin_sample.data

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.makeagoodsoup.fugle_realtime_lib.core.entities.stringify
import net.makeagoodsoup.fugle_realtime_lib.core.repository.FugleHttpRepository
import net.makeagoodsoup.fugle_realtime_lib.core.repository.successOr

sealed class HttpData : RequestDelegate {

    companion object {
        @DelicateCoroutinesApi
        fun values(): List<HttpData> = listOf(Meta, Quote, Chart, Dealts, Volumes)
    }

    @DelicateCoroutinesApi
    object Meta : HttpData() {
        override fun name(): String = "Meta"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val result = FugleHttpRepository().getMeta(symbolId, token)
                callback.invoke("${result.successOr(null)?.stringify()}")
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
                callback.invoke("${result.successOr(null)?.stringify()}")
            }
        }
    }

    @DelicateCoroutinesApi
    object Chart : HttpData() {
        override fun name(): String = "Chart"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val result = FugleHttpRepository().getChart(symbolId, token)
                callback.invoke("${result.successOr(null)?.stringify()}")
            }
        }
    }

    @DelicateCoroutinesApi
    object Dealts : HttpData() {
        override fun name(): String = "Dealts"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val result = FugleHttpRepository().getDealts(symbolId, token)
                callback.invoke("${result.successOr(null)?.stringify()}")
            }
        }
    }

    @DelicateCoroutinesApi
    object Volumes : HttpData() {
        override fun name(): String = "Volumes"

        override fun start(symbolId: String, token: String, callback: (String) -> Unit) {
            callback.invoke("InProgress")
            GlobalScope.launch {
                val result = FugleHttpRepository().getVolumes(symbolId, token)
                callback.invoke("${result.successOr(null)?.stringify()}")
            }
        }
    }

}