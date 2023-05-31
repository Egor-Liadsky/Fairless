package com.mobile.fairless.features.search.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface SearchRepository {
    suspend fun getCategories(): List<Category>
    suspend fun searchProducts(name: String): List<ProductData>
}

class SearchRepositoryImpl : SearchRepository, BaseRepository() {

    override suspend fun getCategories(): List<Category> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }
    override suspend fun searchProducts(name: String): List<ProductData> {
        val params = HashMap<String, String>()
        params["_limit"] = "20"
        params["_sort"] = "createdAt:DESC"
        params["name_contains"] = name
        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks"
        )
        return Json.decodeFromString(response)
    }
}
