package com.mobile.fairless.features.main.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.main.models.Category
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface MainRepository {

    suspend fun getCategories(): List<Category>
}

class MainRepositoryImpl : MainRepository, BaseRepository() {

    override suspend fun getCategories(): List<Category> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "categories",
        )
        return Json.decodeFromString(response)
    }
}
