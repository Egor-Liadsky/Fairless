package com.mobile.fairless.features.main.service

import com.mobile.fairless.common.pagination.PagingDataSourceMain
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.main.repository.MainRepository
import com.mobile.fairless.features.search.models.Sort

interface MainService : PagingDataSourceMain<ProductData> {
    suspend fun getCategories(): List<CategoryModel>
    suspend fun getProductsByCategory(
        page: Int,
        category: String,
        type: ProductStockType
    ): ProductResponse
}

class MainServiceImpl(private val mainRepository: MainRepository) : MainService {

    override suspend fun getPage(
        page: Int, name: String, type: ProductStockType, sort: Sort,
        shop: Shop?
    ): ProductResponse {
        return getProductsByCategory(page, name, type)
    }

    override suspend fun getCategories(): List<CategoryModel> = mainRepository.getCategories()
    override suspend fun getProductsByCategory(
        page: Int,
        category: String,
        type: ProductStockType
    ): ProductResponse =
        mainRepository.getProductsByCategory(page = page, category = category, type = type)
}
