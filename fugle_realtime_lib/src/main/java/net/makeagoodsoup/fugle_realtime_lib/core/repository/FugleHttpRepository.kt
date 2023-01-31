package net.makeagoodsoup.fugle_realtime_lib.core.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.makeagoodsoup.fugle_realtime_lib.core.entities.ChartData
import net.makeagoodsoup.fugle_realtime_lib.core.entities.DealtsData
import net.makeagoodsoup.fugle_realtime_lib.core.entities.MetaData
import net.makeagoodsoup.fugle_realtime_lib.core.entities.VolumesData
import net.makeagoodsoup.fugle_realtime_lib.core.remote.ApiClient

class FugleHttpRepository {

    suspend fun getMeta(symbolId: String, apiToken: String): Result<MetaData> {
        return withContext(Dispatchers.IO) {
            val response = ApiClient.makeIntradayService().getMeta(symbolId = symbolId, apiToken = apiToken)
            val metaData = response.body()?.data
            if (metaData == null) {
                Result.Error(NullPointerException("Response failed: ${response.message()}"))
            } else {
                Result.Success(metaData)
            }
        }
    }

    suspend fun getChart(symbolId: String, apiToken: String): Result<ChartData> {
        return withContext(Dispatchers.IO) {
            val response = ApiClient.makeIntradayService().getChart(symbolId = symbolId, apiToken = apiToken)
            val chartData = response.body()?.data
            if (chartData == null) {
                Result.Error(NullPointerException("Response failed: ${response.message()}"))
            } else {
                Result.Success(chartData)
            }
        }
    }

    suspend fun getDealts(symbolId: String, apiToken: String, limit: Int = 50, offset: Int = 0, oddLot: Boolean = false): Result<DealtsData> {
        return withContext(Dispatchers.IO) {
            val response = ApiClient.makeIntradayService().getDealts(symbolId = symbolId, apiToken = apiToken, limit = limit, offset = offset, oddLot = oddLot)
            val dealtsData = response.body()?.data
            if (dealtsData == null) {
                Result.Error(NullPointerException("Response failed: ${response.message()}"))
            } else {
                Result.Success(dealtsData)
            }
        }
    }

    suspend fun getVolumes(symbolId: String, apiToken: String): Result<VolumesData> {
        return withContext(Dispatchers.IO) {
            val response = ApiClient.makeIntradayService().getVolumes(symbolId = symbolId, apiToken = apiToken)
            val volumesData = response.body()?.data
            if (volumesData == null) {
                Result.Error(NullPointerException("Response failed: ${response.message()}"))
            } else {
                Result.Success(volumesData)
            }
        }
    }
}