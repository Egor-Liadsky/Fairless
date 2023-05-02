package com.mobile.fairless.features.welcome.dto

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
    val jwt: String = "",
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
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val city: City,
    val role: Role,
    val stocks: List<String>,
    val id: String
)

@Serializable
data class City(
    val _id: String,
    val name: String,
    val code: String,
    val published_at: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val sort: Int,
    val id: String,
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
