package com.mobile.fairless.features.shop.service

import com.mobile.fairless.common.pagination.PaginatedResult
import com.mobile.fairless.common.pagination.PagingDataSourceMain
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.search.models.Sort
import com.mobile.fairless.features.shop.repository.ShopRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ShopService: PagingDataSourceMain<ProductData> {

    suspend fun getShops(): List<Shop>
    suspend fun getProductsByCategory(
        page: Int,
        category: String,
        type: ProductStockType,
        shop: String
    ): ProductResponse
    suspend fun getCategories(): List<Category>
}

class ShopServiceImpl: ShopService, KoinComponent {

    private val shopRepository: ShopRepository by inject()

    override suspend fun getShops(): List<Shop> =
        shopRepository.getShops()

    override suspend fun getProductsByCategory(
        page: Int,
        category: String,
        type: ProductStockType,
        shop: String,
    ): ProductResponse =
        shopRepository.getProductsByCategory(page, category, type, shop)

    override suspend fun getCategories(): List<Category> = shopRepository.getCategories()


    override suspend fun getPage(
        page: Int,
        name: String,
        type: ProductStockType,
        sort: Sort,
        shop: String?
    ): PaginatedResult<ProductData> {
        return getProductsByCategory(page, name, type, shop ?: "aliexpress")
    }
}

