package com.mobile.fairless.features.search.service

import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.search.repository.SearchRepository

interface SearchService {
    suspend fun getCategories(): List<Category>
    suspend fun searchProducts(name: String): List<ProductData>
}

class SearchServiceImpl(private val searchRepository: SearchRepository) : SearchService {
    override suspend fun getCategories(): List<Category> = searchRepository.getCategories()
    override suspend fun searchProducts(name: String): List<ProductData> = searchRepository.searchProducts(name)
}