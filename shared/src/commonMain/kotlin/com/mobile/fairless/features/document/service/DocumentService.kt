package com.mobile.fairless.features.document.service

import com.mobile.fairless.features.document.repository.DocumentRepository
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData

interface DocumentService {
    suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData>
}

class DocumentServiceImpl(private val documentRepository: DocumentRepository) : DocumentService {

    override suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData> =
        documentRepository.getFireProducts(limit, last)
}
