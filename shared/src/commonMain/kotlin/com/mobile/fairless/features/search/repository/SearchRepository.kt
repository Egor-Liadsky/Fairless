package com.mobile.fairless.features.search.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.search.models.Sort
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

interface SearchRepository {
    suspend fun getCategories(): List<CategoryModel>
    suspend fun searchProducts(
        page: Int,
        name: String,
        type: ProductStockType,
        sort: Sort
    ): ProductResponse
}

class SearchRepositoryImpl : SearchRepository, BaseRepository() {

    override suspend fun getCategories(): List<CategoryModel> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }

    override suspend fun searchProducts(
        page: Int,
        name: String,
        type: ProductStockType,
        sort: Sort
    ): ProductResponse {
        val params = HashMap<String, String>()

        val sorted = when (sort) {
            Sort.CREATE -> "createdAt:DESC"
            Sort.LIKES -> "count_likes:DESC"
            Sort.VIEWS -> "count_views:DESC"
            Sort.SALE_ASCENDING -> "sale_price:ASC"
            Sort.SALE_DESCENDING -> "sale_price:DESC"
            else -> "createdAt:DESC"
        }

        params["_sort"] = sorted
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
        var total = Json.decodeFromString<Product>(response).count?.div(40.0)
            ?.roundToInt() // Получение количества страниц для пагинации

        if (total == 0)  total += 1

        return ProductResponse(list = list ?: emptyList(), total = total)
    }
}
