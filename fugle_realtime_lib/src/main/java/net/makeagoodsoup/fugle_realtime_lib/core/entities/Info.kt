package net.makeagoodsoup.fugle_realtime_lib.core.entities

data class Info(
    val date: String,
    val type: String,
    val exchange: String,
    val market: String,
    val symbolId: String,
    val countryCode: String,
    val timeZone: String,
    val lastUpdatedAt: String?,
)