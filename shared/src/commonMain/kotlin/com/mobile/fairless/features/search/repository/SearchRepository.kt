package com.mobile.fairless.features.search.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.response.ProductResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

interface SearchRepository {
    suspend fun getCategories(): List<Category>
    suspend fun searchProducts(page: Int, name: String, type: ProductStockType): ProductResponse
}

class SearchRepositoryImpl : SearchRepository, BaseRepository() {

    override suspend fun getCategories(): List<Category> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }

    override suspend fun searchProducts(
        page: Int,
        name: String,
        type: ProductStockType
    ): ProductResponse {
        val params = HashMap<String, String>()
        params["_sort"] = "createdAt:DESC"
        params["stock_type"] = type.name.lowercase()
        params["page"] = page.toString()
        params["limit"] = "40"
        params["search_text"] = name
        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks/stocks-index"
        )

        val list = Json.decodeFromString<Product>(response).data
        val total = Json.decodeFromString<Product>(response).count?.div(40.0)
            ?.roundToInt() // Получение количества страниц для пагинации

        println("asdajsdhajkshd   " + ProductResponse(list = list ?: emptyList(), total = total))

        return ProductResponse(list = list ?: emptyList(), total = total)
    }
}
