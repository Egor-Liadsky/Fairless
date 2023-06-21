package com.mobile.fairless.features.search.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.SearchProduct
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.search.models.SearchProductResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface SearchRepository {
    suspend fun getCategories(): List<Category>
    suspend fun searchProducts(page: Int, name: String): SearchProductResponse
}

class SearchRepositoryImpl : SearchRepository, BaseRepository() {

    override suspend fun getCategories(): List<Category> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }

    override suspend fun searchProducts(page: Int, name: String): SearchProductResponse {
        val params = HashMap<String, String>()
        params["_start"] = page.toString()
        params["_limit"] = "30"
        params["_sort"] = "createdAt:DESC"
        params["name_contains"] = name
        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks"
        )
        println("sdfsdfd " + response)
        val list = Json.decodeFromString<List<ProductData>>(response)

        return SearchProductResponse(list = list ?: emptyList())
    }
}
