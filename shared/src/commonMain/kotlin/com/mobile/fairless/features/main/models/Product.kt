package com.mobile.fairless.features.main.models

import com.mobile.fairless.features.welcome.dto.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val data: List<Data>? = null,
    val count: Int = 0
)

@Serializable
data class Data(
    val count_likes: String,
    val count_dislikes: String,
    val count_views: String,
    val _id: String,
    val date_start: String? = null,
    val date_end: String? = null,
    val name: String,
    val code: String,
    val sale_price: Int,
    val sale_url: String,
    val sale_old_price: Int,
    val description: String? = null,
    val published_at: String,
    val SEO: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val category: Category,
    val city: City,
    val image: Image,
    val shop: Shop,
    val stock_type: StockType,
    val users_permissions_user: UsersPermissionsUser,
    val id: String,
    val myLike: String? = null,
    val count_comments: Int,
    val promo_code: String? = null, //
    val promo_price: Int? = null, //
)

@Serializable
data class Image(
    val _id: String,
    val name: String,
    val alternativeText: String,
    val caption: String,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val width: Int,
    val height: Int,
    val url: String,
    val formats: Formats,
    val provider: String,
    val related: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val id: String,
)

@Serializable
data class ImageX(
    val _id: String,
    val name: String,
    val alternativeText: String,
    val caption: String,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val width: Int,
    val height: Int,
    val url: String,
    val provider: String,
    val related: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val id: String,
)

@Serializable
data class Shop(
    val top: Boolean,
    val _id: String,
    @SerialName("Name")
    val Name2: String? = null, //
    val name: String,
    val icon: String,
    val code: String,
    val sort: Int,
    val published_at: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val image: ImageX,
    val seo_text: String? = null,
    val id: String,
)

@Serializable
data class StockType(
    val _id: String,
    val code: String,
    val name: String,
    val published_at: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val id: String,
)

@Serializable
data class UsersPermissionsUser(
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
    val city: String,
    val cityCode: String? = null, //
    val role: String,
    val id: String,
)

@Serializable
data class Formats(
    val thumbnail: Thumbnail,
    val large: Large? = null,//
    val medium: Medium? = null,//
    val small: Small? = null//
)

@Serializable
data class Large(
    val ext: String,
    val hash: String,
    val height: Int,
    val mime: String,
    val name: String,
    val path: String? = null,
    val size: Double,
    val url: String,
    val width: Int
)

@Serializable
data class Medium(
    val ext: String,
    val hash: String,
    val height: Int,
    val mime: String,
    val name: String,
    val path: String? = null,
    val size: Double,
    val url: String,
    val width: Int
)

@Serializable
data class Small(
    val ext: String,
    val hash: String,
    val height: Int,
    val mime: String,
    val name: String,
    val path: String? = null,
    val size: Double,
    val url: String,
    val width: Int
)

@Serializable
data class Thumbnail(
    val name: String,
    val hash: String,
    val ext: String,
    val mime: String,
    val width: Int,
    val height: Int,
    val size: Double,
    val path: String? = null,
    val url: String,
)


