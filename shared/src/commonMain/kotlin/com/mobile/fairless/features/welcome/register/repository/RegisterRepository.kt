package com.mobile.fairless.features.welcome.register.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.models.UserReceive
import com.mobile.fairless.features.welcome.models.UserRegisterResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface RegisterRepository {

    suspend fun registerUser(userRegisterResponse: UserRegisterResponse): UserReceive
    suspend fun getCities(): List<City>
}

class RegisterRepositoryImpl : RegisterRepository, BaseRepository() {

    override suspend fun registerUser(userRegisterResponse: UserRegisterResponse): UserReceive {
        val body = """
        {
            "email": "${userRegisterResponse.email}",
            "username": "${userRegisterResponse.username}",
            "password": "${userRegisterResponse.password}",
            "city" : {
                "_id": "${userRegisterResponse.city._id}",
                "name": "${userRegisterResponse.city.name}",
                "code": "${userRegisterResponse.city.code}",
                "published_at": "${userRegisterResponse.city.published_at}",
                "createdAt": "${userRegisterResponse.city.createdAt}",
                "updatedAt": "${userRegisterResponse.city.updatedAt}",
                "__v": ${userRegisterResponse.city.__v},
                "sort": ${userRegisterResponse.city.sort},
                "id": "${userRegisterResponse.city.id}"
            }
        }
        """.trimIndent()
        val response = executeCall(
            type = HttpMethod.Post,
            path = "auth/local/register",
            headers = mapOf("Content-Type" to "application/json"),
            body = body
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getCities(): List<City> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "cities",
        )
        return Json.decodeFromString(response)
    }
}
