package com.mobile.fairless.features.search.service

import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.search.models.SearchProductResponse
import com.mobile.fairless.features.search.pagination.SearchPagingDataSource
import com.mobile.fairless.features.search.repository.SearchRepository

interface SearchService : SearchPagingDataSource<ProductData> {
    suspend fun getCategories(): List<Category>
    suspend fun searchProducts(page: Int, name: String): SearchProductResponse
}

class SearchServiceImpl(private val searchRepository: SearchRepository) : SearchService {

    override suspend fun getPage(page: Int, name: String): SearchProductResponse {
        return searchProducts(page, name)
    }

    override suspend fun getCategories(): List<Category> = searchRepository.getCategories()
    override suspend fun searchProducts(page: Int, name: String): SearchProductResponse =
        searchRepository.searchProducts(page, name)
}
