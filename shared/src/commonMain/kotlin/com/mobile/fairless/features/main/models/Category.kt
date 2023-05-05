package com.mobile.fairless.features.main.models

import kotlinx.serialization.Serializable


@Serializable
data class Category(
    val main_category: Boolean? = true,
    val _id: String? = null,
    val name: String? = null,
    val url: String? = null,
    val sort: Int? = null,
    val icon: String? = null,
    val published_at: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val type: String? = null,
    val svg: String? = null,
    val cat_page_description: String? = null,
    val seo_text: String? = null,
    val id: String? = null
)
