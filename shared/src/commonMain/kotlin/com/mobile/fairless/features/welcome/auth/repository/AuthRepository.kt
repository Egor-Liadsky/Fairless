package com.mobile.fairless.features.welcome.auth.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.welcome.models.UserReceive
import com.mobile.fairless.features.welcome.models.UserAuthResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface AuthRepository {

    suspend fun authUser(userAuthResponse: UserAuthResponse): UserReceive
}

class AuthRepositoryImpl : AuthRepository, BaseRepository() {

    override suspend fun authUser(userAuthResponse: UserAuthResponse): UserReceive {
        val body = """
                {
                    "identifier": "${userAuthResponse.identifier}",
                    "password": "${userAuthResponse.password}"
                }
            """.trimIndent()
        val response = executeCall(
            type = HttpMethod.Post,
            path = "auth/local",
            headers = mapOf("Content-Type" to "application/json"),
            body = body
        )
        return Json.decodeFromString(response)
    }
}

