package com.mobile.fairless.features.welcome.models

import com.mobile.fairless.common.utils.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthResponse(
    val identifier: String,
    val password: String
)

@Serializable
data class UserRegisterResponse(
    val email: String,
    val username: String,
    val password: String,
    val city: City
)

@Serializable
data class UserReceive(
    val jwt: String? = "",
    val user: User? = null
)

@Serializable
data class User(
    val confirmed: Boolean,
    val blocked: Boolean,
    val avatar: List<String>,
    val _id: String,
    val email: String,
    val username: String,
    val provider: String,
    @Serializable(with = DateTimeTzSerializer::class)
    val createdAt: DateTimeTz,
    val updatedAt: String,
    val __v: Int,
    val city: City,
    val role: Role,
    val stocks: List<String>,
    val id: String
) {
//    private val date = if (createdAt.isToday()) "Сегодня"
//    else if (createdAt.isYesterday()) "Вчера"
//    else createdAt.format("dd.MM.yyyy")
    private val date = createdAt.format("dd.MM.yyyy")
    val dateTime: String = date
}

@Serializable
data class City(
    val _id: String? = null,
    val name: String? = null,
    val code: String? = null,
    val published_at: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val sort: Int? = null,
    val id: String? = null,
)

@Serializable
data class Role(
    val _id: String,
    val name: String,
    val description: String,
    val type: String,
    val __v: Int,
    val id: String,
)
