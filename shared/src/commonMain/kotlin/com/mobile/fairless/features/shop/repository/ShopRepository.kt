package com.mobile.fairless.features.shop.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.search.models.Sort
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

interface ShopRepository {

    suspend fun getShops(): List<Shop>
    suspend fun getProductsByCategory(
        page: Int,
        category: String,
        type: ProductStockType,
        sort: Sort,
        shop: Shop
    ): ProductResponse

    suspend fun getCategories(): List<CategoryModel>
    suspend fun getShop(code: String): List<Shop>
}

class ShopRepositoryImpl() : ShopRepository, BaseRepository() {

    override suspend fun getShops(): List<Shop> {
        val response = executeCall(
            type = HttpMethod.Get,
            headers = mapOf("Content-Type" to "application/json"),
            path = "shops"
        )
        return Json.decodeFromString<List<Shop>>(response)
    }

    override suspend fun getProductsByCategory(
        page: Int,
        category: String,
        type: ProductStockType,
        sort: Sort,
        shop: Shop
    ): ProductResponse {

        val sorted = when (sort) {
            Sort.CREATE -> "createdAt:DESC"
            Sort.LIKES -> "count_likes:DESC"
            Sort.VIEWS -> "count_views:DESC"
            Sort.SALE_ASCENDING -> "sale_price:ASC"
            Sort.SALE_DESCENDING -> "sale_price:DESC"
            else -> "createdAt:DESC"
        }

        val params = HashMap<String, String>()
        params["_sort"] = sorted
        params["stock_type"] = type.name.lowercase()
        params["page"] = page.toString()
        params["shops"] = shop.code ?: "aliexpress"

        if (category != "all") {
            params["category"] = category
        }

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks/stocks-index"
        )

        val list = Json.decodeFromString<Product>(response).data
        var total = Json.decodeFromString<Product>(response).count?.div(40.0)
            ?.roundToInt() // Получение количества страниц для пагинации

        if (total == 0) total += 1

        return ProductResponse(list = list ?: emptyList(), total = total ?: 1)
    }

    override suspend fun getCategories(): List<CategoryModel> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getShop(code: String): List<Shop> {
        val params = HashMap<String, String>()
        params["code"] = code
        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            path = "shops",
        )
        return Json.decodeFromString(response)
    }
}