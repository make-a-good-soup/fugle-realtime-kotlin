package net.makeagoodsoup.fugle_realtime_lib.core.entities

data class Volume(
    val price: Double, // 成交價
    val volume: Double, // 成交量
    val volumeAtBid: Double, // 內盤成交量
    val volumeAtAsk: Double // 外盤成交量
)