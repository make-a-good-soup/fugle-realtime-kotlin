package net.makeagoodsoup.fugle_realtime_lib.core.entities

data class Quote(
    val isCurbing: Boolean?, // 最近一次更新是否為瞬間價格穩定措施
    val isCurbingRise: Boolean?, // 最近一次更新是否為暫緩撮合且瞬間趨漲
    val isCurbingFall: Boolean?, // 最近一次更新是否為暫緩撮合且瞬間趨跌
    val tradingCurb: TradingCurb?, // 最近一次更新瞬間價格趨勢
    val isTrial: Boolean?, // 最近一次更新是否為試算
    val isOpenDelayed: Boolean?, // 最近一次更新是否為延後開盤狀態
    val isCloseDelayed: Boolean?, // 最近一次更新是否為延後收盤狀態
    val isHalting: Boolean?, // 最近一次更新是否為暫停交易
    val isClosed: Boolean?, // 當日是否為已收盤
    val isDealt: Boolean?, // 最近一次更新是否包含最新成交(試撮)價
    val total: Total?,
    val trial: Trial?,
    val trade: Trade?,
    val order: Order?,
    val priceHigh: Price?, // 當日之最高價
    val priceLow: Price?, // 當日之最低價
    val priceOpen: Price?, // 當日之開盤價
    val priceAvg: Price?, // 當日之成交均價(當日最後一筆成交時間)
    val sbl: Sbl?, // 借券賣出可用餘額
    val change: Double?, // 當日股價之漲跌
    val changePercent: Double?, // 當日股價之漲跌幅
    val amplitude: Double?, // 當日股價之振幅
    val priceLimit: Double?, // 0 = 正常；1 = 跌停；2 = 漲停
)

data class TradingCurb(
    val priceLimit: Double?, // 0 = 正常；1 = 趨跌；2 = 趨漲
    val isCurbed: Boolean, // 是否進入瞬間價格穩定措施
    val at: String, // 進入瞬間價格穩定措施或恢復逐筆交易時間
)

data class Total(
    val at: String, // 最新一筆成交時間
    val transaction: Double, // 總成交筆數
    val tradeValue: Double, // 總成交金額
    val tradeVolume: Double, // 總成交數量
    val tradeVolumeAtBid: Double, // 個股內盤成交量
    val tradeVolumeAtAsk: Double, // 個股外盤成交量
    val bidOrders: Double?, // 總委買筆數 (僅加權、櫃買指數)
    val askOrders: Double?, // 總委賣筆數 (僅加權、櫃買指數)
    val bidVolume: Double?, // 總委買數量 (僅加權、櫃買指數
    val askVolume: Double?, // 總委賣數量 (僅加權、櫃買指數)
    val serial: Double, // 最新一筆成交之序號
)

data class Trial(
    val at: String, // 最新一筆試撮時間
    val bid: Double?, // 最新一筆試撮買進價
    val ask: Double?, // 最新一筆試撮賣出價
    val price: Double?, // 最新一筆試撮成交價
    val volume: Double?, // 最新一筆試撮成交量
)

data class Trade(
    val at: String, // 最新一筆試撮時間
    val bid: Double?, // 最新一筆試撮買進價
    val ask: Double?, // 最新一筆試撮賣出價
    val price: Double?, // 最新一筆試撮成交價
    val volume: Double?, // 最新一筆試撮成交量
    val serial: String?, // 最新一筆成交之序號
)

data class Order(
    val at: String,// 最新一筆最佳五檔更新時間
    val bids: List<BidAsk>,
    val asks: List<BidAsk>,
)

data class BidAsk(
    val price: Double,
    val volume: Double,
)

data class Price(
    val price: Double,
    val at: String,
)

data class Sbl(
    val availableVolume: Double, // 借券賣出可用餘額(股)
    val at: String,
)
