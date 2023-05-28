package com.mobile.fairless.features.document.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface DocumentRepository {

    suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData>
}

class DocumentRepositoryImpl : DocumentRepository, BaseRepository() {

    override suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData> {
        val params = HashMap<String, String>()
        params["_limit"] = limit.toString()
        params["_sort"] = "count_likes%3ADESC"
//        params["createdAt_gte"] = "2023-05-27T00%3A00%3A01"

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks"
        )
        return Json.decodeFromString(response)
    }
}
