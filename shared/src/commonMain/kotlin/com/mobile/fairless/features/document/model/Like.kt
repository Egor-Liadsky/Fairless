package com.mobile.fairless.features.document.model

import kotlinx.serialization.Serializable

@Serializable
data class Like(
    val _id: String? = null,
    val like: Boolean? = null,
    val user_id: String? = null,
    val stock_id: String? = null,
    val published_at: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val id: String? = null,
)