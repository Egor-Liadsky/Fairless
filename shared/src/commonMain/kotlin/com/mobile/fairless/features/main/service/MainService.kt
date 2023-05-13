package com.mobile.fairless.features.main.service

import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.repository.MainRepository

interface MainService {
    suspend fun getCategories(): List<Category>
    suspend fun getProductsByCategory(category: String): Product
}

class MainServiceImpl(private val mainRepository: MainRepository) : MainService {

    override suspend fun getCategories(): List<Category> = mainRepository.getCategories()
    override suspend fun getProductsByCategory(category: String): Product = mainRepository.getProductsByCategory(category)
}
