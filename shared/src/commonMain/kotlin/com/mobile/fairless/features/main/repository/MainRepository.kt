package com.mobile.fairless.features.main.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface MainRepository {

    suspend fun getCategories(): List<Category>
    suspend fun getProductsByCategory(category: String): Product
}

class MainRepositoryImpl : MainRepository, BaseRepository() {

    override suspend fun getCategories(): List<Category> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getProductsByCategory(category: String): Product {

        val params = HashMap<String, String>()
        params["category"] = category
        params["page"] = "1"
        params["limit"] = "20"
        params["_sort"] = "createdAt:DESC"

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks/stocks-index"
        )
        return Json.decodeFromString(response)
    }
}
