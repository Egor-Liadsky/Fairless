package com.mobile.fairless.features.search.service

import com.mobile.fairless.common.pagination.PagingDataSourceMain
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.search.models.Sort
import com.mobile.fairless.features.search.repository.SearchRepository

interface SearchService : PagingDataSourceMain<ProductData> {
    suspend fun getCategories(): List<CategoryModel>
    suspend fun searchProducts(
        page: Int,
        name: String,
        type: ProductStockType,
        sort: Sort
    ): ProductResponse
}

class SearchServiceImpl(private val searchRepository: SearchRepository) : SearchService {

    override suspend fun getPage(
        page: Int,
        name: String,
        type: ProductStockType,
        sort: Sort,
        shop: Shop?
    ): ProductResponse {
        return searchProducts(page, name, type, sort)
    }

    override suspend fun getCategories(): List<CategoryModel> = searchRepository.getCategories()
    override suspend fun searchProducts(
        page: Int,
        name: String,
        type: ProductStockType,
        sort: Sort
    ): ProductResponse =
        searchRepository.searchProducts(page, name.trim(), type, sort)
}
