package net.makeagoodsoup.fugle_realtime_lib.core.entities

data class Chart(
    val averagePrices: List<Number>?, // 當日個股於此分鐘的成交均價
    val openingPrices: List<Number>?, // 此分鐘的開盤價
    val highestPrices: List<Number>?, // 此分鐘的最高價
    val lowestPrices: List<Number>?, // 此分鐘的最低價
    val closingPrices: List<Number>?, // 此分鐘的收盤價
    val volumes: List<Number>?, // 此分鐘的成交量 (指數：金額；個股：張數；興櫃股票及零股：股數)
    val times: List<Number>?, // Unix timestamp (每分鐘單位)
)