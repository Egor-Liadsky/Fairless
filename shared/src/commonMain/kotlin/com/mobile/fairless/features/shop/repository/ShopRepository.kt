package com.mobile.fairless.features.shop.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.main.models.response.ProductResponse
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
        shop: String
    ): ProductResponse
    suspend fun getCategories(): List<Category>
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
        shop: String
    ): ProductResponse {
        val params = HashMap<String, String>()
        params["_sort"] = "createdAt:DESC"
        params["stock_type"] = type.name.lowercase()
        params["page"] = page.toString()
        params["shops"] = shop

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks/stocks-index"
        )

        val list = Json.decodeFromString<Product>(response).data
        val total = Json.decodeFromString<Product>(response).count?.div(30.0)
            ?.roundToInt() // Получение количества страниц для пагинации

        return ProductResponse(list = list ?: emptyList(), total = total ?: 1)
    }

    override suspend fun getCategories(): List<Category> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }
}