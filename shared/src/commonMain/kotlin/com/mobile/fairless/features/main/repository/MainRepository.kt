package com.mobile.fairless.features.main.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.response.ProductResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.inject

interface MainRepository {

    suspend fun getCategories(): List<Category>
    suspend fun getProductsByCategory(page: Int, limit: Int, category: String): ProductResponse
}

class MainRepositoryImpl : MainRepository, BaseRepository() {

    override suspend fun getCategories(): List<Category> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getProductsByCategory(page: Int, limit: Int, category: String): ProductResponse {

        val params = HashMap<String, String>()
        params["_sort"] = "createdAt:DESC"
        params["stock_type"] = "all"
        params["category"] = category
        params["page"] = page.toString()
//        params["limit"] = limit.toString()

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks/stocks-index"
        )
        return Json.decodeFromString(ProductResponse.serializer(), response)
    }
}

