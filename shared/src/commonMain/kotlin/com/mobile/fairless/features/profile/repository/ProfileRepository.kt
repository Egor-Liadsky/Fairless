package com.mobile.fairless.features.profile.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.welcome.dto.City
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface ProfileRepository {

    suspend fun getCities(): List<City>
}

class ProfileRepositoryImpl : ProfileRepository, BaseRepository() {

    override suspend fun getCities(): List<City> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "cities",
        )
        return Json.decodeFromString(response)
    }
}
