package com.mobile.fairless.features.shop.service

import com.mobile.fairless.common.pagination.PaginatedResult
import com.mobile.fairless.common.pagination.PagingDataSourceMain
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.search.models.Sort
import com.mobile.fairless.features.shop.repository.ShopRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ShopService : PagingDataSourceMain<ProductData> {

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

class ShopServiceImpl : ShopService, KoinComponent {

    private val shopRepository: ShopRepository by inject()

    override suspend fun getShops(): List<Shop> =
        shopRepository.getShops()

    override suspend fun getProductsByCategory(
        page: Int,
        category: String,
        type: ProductStockType,
        sort: Sort,
        shop: Shop,
    ): ProductResponse =
        shopRepository.getProductsByCategory(page, category, type, sort, shop)

    override suspend fun getCategories(): List<CategoryModel> = shopRepository.getCategories()

    override suspend fun getShop(code: String): List<Shop> = shopRepository.getShop(code)

    override suspend fun getPage(
        page: Int,
        name: String,
        type: ProductStockType,
        sort: Sort,
        shop: Shop?
    ): PaginatedResult<ProductData> {
        return getProductsByCategory(page, name, type, sort, shop ?: Shop())
    }
}

