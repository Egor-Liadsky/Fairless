package com.mobile.fairless.features.main.service

import com.mobile.fairless.common.pagination.PagingDataSource
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.response.ProductResponse
import com.mobile.fairless.features.main.repository.MainRepository

interface MainService : PagingDataSource<ProductData> {
    suspend fun getCategories(): List<Category>
    suspend fun getProductsByCategory(page: Int, category: String): ProductResponse
}

class MainServiceImpl(private val mainRepository: MainRepository) : MainService {

    override suspend fun getPage(page: Int): ProductResponse {
        return getProductsByCategory(page, "all")
    }

    override suspend fun getCategories(): List<Category> = mainRepository.getCategories()
    override suspend fun getProductsByCategory(page: Int, category: String): ProductResponse =
        mainRepository.getProductsByCategory(page = page, 10, category)
}
