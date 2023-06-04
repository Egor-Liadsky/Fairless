package com.mobile.fairless.features.document.model

import com.mobile.fairless.common.utils.DateTimeSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val _id: String? = null,
    val text: String? = null,
    val stock_id: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val users_permissions_user: UsersPermissionsUser? = null,
    val id: String? = null
)

@Serializable
data class UsersPermissionsUser(
    val confirmed: Boolean? = null,
    val blocked: Boolean? = null,
    val avatar: List<String>? = null,
    val _id: String? = null,
    val email: String? = null,
    val username: String? = null,
    val provider: String? = null,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: DateTimeTz? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val city: String? = null,
    val role: String? = null,
    val id: String? = null
){
    private val date = createdAt?.format("dd.MM.yyyy")
    val dateTime: String = date ?: ""
}
