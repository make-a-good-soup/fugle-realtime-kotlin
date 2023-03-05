package net.makeagoodsoup.fugle_realtime_lib.core.entities

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun MetaData.stringify(): String = stringify(this, MetaData::class.java)
fun QuoteData.stringify(): String = stringify(this, QuoteData::class.java)
fun ChartData.stringify(): String = stringify(this, ChartData::class.java)
fun DealtsData.stringify(): String = stringify(this, DealtsData::class.java)
fun VolumesData.stringify(): String = stringify(this, VolumesData::class.java)

private fun <T> stringify(value: T, type: Class<T>): String {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    // format json
    val jsonAdapter = moshi.adapter(type)
    return jsonAdapter.indent("  ").toJson(value)
}