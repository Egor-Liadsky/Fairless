package com.mobile.fairless.features.document.service

import com.mobile.fairless.features.document.model.Comment
import com.mobile.fairless.features.document.model.Like
import com.mobile.fairless.features.document.repository.DocumentRepository
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.welcome.models.UserReceive

interface DocumentService {
    suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData>
    suspend fun getComments(documentId: String): List<Comment>
    suspend fun sendComment(user: UserReceive, text: String, documentId: String)
    suspend fun reactionDocument(like: Boolean, documentId: String, user: UserReceive)
    suspend fun getDocument(name: String): List<ProductData>
    suspend fun checkLike(documentId: String, userId: String): List<Like>
}

class DocumentServiceImpl(private val documentRepository: DocumentRepository) : DocumentService {

    override suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData> =
        documentRepository.getFireProducts(limit, last)

    override suspend fun getComments(documentId: String): List<Comment> =
        documentRepository.getComments(documentId)

    override suspend fun sendComment(user: UserReceive, text: String, documentId: String) {
        documentRepository.sendComment(user, text, documentId)
    }

    override suspend fun reactionDocument(like: Boolean, documentId: String, user: UserReceive) {
        documentRepository.reactionDocument(like, documentId, user)
    }

    override suspend fun getDocument(name: String): List<ProductData> =
        documentRepository.getDocument(name)

    override suspend fun checkLike(documentId: String, userId: String): List<Like> =
        documentRepository.checkLike(documentId, userId)
}
