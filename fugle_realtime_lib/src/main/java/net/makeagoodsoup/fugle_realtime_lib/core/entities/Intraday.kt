package net.makeagoodsoup.fugle_realtime_lib.core.entities

data class Intraday<T>(val apiVersion: String, val data: T)

data class MetaData(val info: Info, val meta: Meta)

data class QuoteData(val info: Info, val quote: Quote)

data class ChartData(val info: Info, val chart: Chart)

data class DealtsData(val info: Info, val dealts: List<Dealts>)

data class VolumesData(val info: Info, val volumes: List<Volume>)

// todo: 其他 data model