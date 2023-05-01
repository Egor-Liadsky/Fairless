package com.mobile.fairless.features.welcome.auth.repository

import com.mobile.fairless.features.welcome.auth.dto.UserReceive
import com.mobile.fairless.features.welcome.auth.dto.UserResponse
import com.mobile.fairless.features.welcome.auth.state.AuthState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface AuthRepository {
    suspend fun authUser(userResponse: UserResponse): UserReceive
}

class AuthRepositoryImpl(private val httpClient: HttpClient) : AuthRepository {

    override suspend fun authUser(userResponse: UserResponse): UserReceive {
        val response = httpClient.post("https://api.fairless.ru/auth/local") {
            contentType(ContentType.Application.Json)
            val body = """
                {
                    "identifier": "${userResponse.identifier}",
                    "password": "${userResponse.password}"
                }
            """.trimIndent()
            setBody(body)
        }
        return Json.decodeFromString(response.body())
    }
}
