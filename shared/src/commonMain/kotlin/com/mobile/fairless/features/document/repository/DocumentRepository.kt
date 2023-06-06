package com.mobile.fairless.features.document.repository

import com.mobile.fairless.common.network.BaseRepository
import com.mobile.fairless.features.document.model.Comment
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.welcome.dto.User
import com.mobile.fairless.features.welcome.dto.UserReceive
import com.soywiz.klock.DateTimeSpan
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.ISO8601
import com.soywiz.klock.MonthSpan
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface DocumentRepository {

    suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData>
    suspend fun getComments(documentId: String): List<Comment>
    suspend fun sendComment(user: UserReceive, text: String, documentId: String)
    suspend fun reactionDocument(like: Boolean, documentId: String, user: UserReceive)
    suspend fun getDocument(name: String): List<ProductData>
}

class DocumentRepositoryImpl : DocumentRepository, BaseRepository() {

    override suspend fun getFireProducts(limit: Int, last: DateFilter): List<ProductData> {

        var date = DateTimeTz.nowLocal()

        date = when (last) {
            DateFilter.TODAY -> date.minus(DateTimeSpan(days = 1))
            DateFilter.WEEK -> date.minus(DateTimeSpan(weeks = 1))
            DateFilter.MONTH -> date.minus(MonthSpan(1))
        }

        val params = HashMap<String, String>()
        params["_limit"] = limit.toString()
        params["_sort"] = "count_likes:DESC"
        params["createdAt_gte"] =
            "${date.format(ISO8601.DATE_CALENDAR_COMPLETE)}T00:00:01"//.format(ISO8601.DATE_CALENDAR_COMPLETE)

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks"
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getComments(documentId: String): List<Comment> {
        val params = HashMap<String, String>()
        params["_sort"] = "createdAt:DESC"
        params["stock_id"] = documentId

        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stockcomments"
        )
        return Json.decodeFromString(response)
    }

    override suspend fun sendComment(user: UserReceive, text: String, documentId: String) {
        val body = """
            {
                "text": "$text",
                "users_permissions_user": {
                    "confirmed": "${user.user?.confirmed}",
                    "blocked": "${user.user?.blocked}",
                    "avatar": [],
                    "_id": "${user.user?._id}",
                    "email": "${user.user?.email}",
                    "username": "${user.user?.username}",
                    "provider": "${user.user?.provider}",
                    "createdAt": "${user.user?.createdAt}",
                    "updatedAt": "${user.user?.updatedAt}",
                    "__v": ${user.user?.__v},
                    "city": "${user.user?.city}",
                    "role": {
                        "_id": "${user.user?.role?._id}",
                        "name": "${user.user?.role?.name}",
                        "description": "${user.user?.role?.description}",
                        "type": "${user.user?.role?.type}",
                        "__v": ${user.user?.role?.__v},
                        "id": "${user.user?.role?.id}"
                    },
                    "id": "${user.user?.id}"
                },
                "stock_id": "$documentId"
            }
        """.trimIndent()
        executeCall(
            type = HttpMethod.Post,
            headers = mapOf(
                "Content-Type" to "application/json",
                "authorization" to "Bearer ${user.jwt}",
            ),
            path = "stockcomments",
            body = body
        )
    }

    override suspend fun reactionDocument(like: Boolean, documentId: String, user: UserReceive) {
        val body = """
            {
                "like": $like,
                "user_id": "${user.user?.id}",
                "stock_id": "$documentId"
            }
        """.trimIndent()
        executeCall(
            type = HttpMethod.Post,
            headers = mapOf(
                "Content-Type" to "application/json",
                "authorization" to "Bearer ${user.jwt}",
            ),
            path = "likes",
            body = body
        )
    }

    override suspend fun getDocument(name: String): List<ProductData> {
        val params = HashMap<String, String>()
        params["_limit"] = "1"
        params["name_contains"] = name
        val response = executeCall(
            type = HttpMethod.Get,
            parameters = params,
            headers = mapOf("Content-Type" to "application/json"),
            path = "stocks"
        )
        return Json.decodeFromString(response)
    }
}
