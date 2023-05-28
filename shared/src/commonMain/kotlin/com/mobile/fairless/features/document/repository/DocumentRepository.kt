package com.mobile.fairless.features.document.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.common.utils.isToday
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeSpan
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.ISO8601
import com.soywiz.klock.KlockLocale
import com.soywiz.klock.MonthSpan
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.weeks
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface DocumentRepository {

    suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData>
}

class DocumentRepositoryImpl : DocumentRepository, BaseRepository() {

    override suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData> {

        var date = DateTimeTz.nowLocal()

        date = when (last){
            DateFilter.TODAY -> date.minus(DateTimeSpan(days = 1))
            DateFilter.WEEK -> date.minus(DateTimeSpan(weeks = 1))
            DateFilter.MONTH -> date.minus(MonthSpan(1))
        }

        val params = HashMap<String, String>()
        params["_limit"] = limit.toString()
        params["_sort"] = "count_likes:DESC"
        params["createdAt_gte"] = "${date.format(ISO8601.DATE_CALENDAR_COMPLETE)}T00:00:01"//.format(ISO8601.DATE_CALENDAR_COMPLETE)

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks"
        )
        return Json.decodeFromString(response)
    }
}
