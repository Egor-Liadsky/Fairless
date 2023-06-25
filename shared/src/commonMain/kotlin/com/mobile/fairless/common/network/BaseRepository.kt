package com.mobile.fairless.common.network

import com.mobile.fairless.common.config.ConfigService
import com.mobile.fairless.common.errors.ApiError
import com.mobile.fairless.common.errors.AppError
import com.mobile.fairless.common.errors.Code
import com.mobile.fairless.common.exception.ServerException
import com.mobile.fairless.common.exception.SocketException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import io.ktor.util.StringValues
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository : KoinComponent {

    private val httpClient: HttpClient by inject()
    protected val json: Json by inject()

    private val configService: ConfigService by inject()

    private val defaultHeaders = StringValues.build {

    }

    protected suspend fun executeCall(
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        body: String? = null
    ): String {
        try {
            return execute(type, path, parameters, headers, body)
        } catch (e: SocketException) {
            throw AppError(code = Code.SERVER_ERROR, description = e.message)
        } catch (e: ServerException){
            throw AppError(code = Code.SERVER_ERROR, description = e.message)
        }
    }

    private suspend fun execute(
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        body: String? = null
    ): String {
        val response: HttpResponse
        try {
            response = httpClient.request(configService.getBaseUrl()) {
                url {
                    appendPathSegments(path)
                    parameters?.forEach { this.parameters.append(it.key, it.value) }
                }
                method = type
                headers?.forEach { this.headers.append(it.key, it.value) }
                this.headers.appendAll(defaultHeaders)
                body?.let { setBody(it) }
            }
        } catch (e: SocketTimeoutException) {
            throw SocketException()
        } catch (e: IOException){
            throw SocketException()
        }
        if (response.status.value !in 200..299){
            throw ServerException(response.status.value, response.status.description)
        }
        return response.body()
    }
}
