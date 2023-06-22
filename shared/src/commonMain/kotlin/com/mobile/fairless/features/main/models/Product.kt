package com.mobile.fairless.features.main.models

import com.mobile.fairless.features.welcome.dto.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


enum class ProductStockType {
    SALE,
    FREE,
    PROMOCODE,
    ALL
}

@Serializable
data class Product(
    val data: List<ProductData>? = null,
    val count: Int? = null
)

@Serializable
data class SearchProduct(
    val data: List<ProductData>? = null,
)

@Serializable
data class ProductData(
    val count_likes: String? = null,
    val count_dislikes: String? = null,
    val count_views: String? = null,
    val _id: String? = null,
    val date_start: String? = null,
    val date_end: String? = null,
    val name: String? = null,
    val code: String? = null,
    val sale_price: Int? = null,
    val sale_url: String? = null,
    val sale_old_price: Int? = null,
    val description: String? = null,
    val published_at: String? = null,
    val SEO: Seo? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val category: Category? = null,
    val city: City? = null,
    val image: Image? = null,
    val shop: Shop? = null,
    val stock_type: StockType? = null,
    val users_permissions_user: UsersPermissionsUser? = null,
    val id: String? = null,
    val myLike: List<MyLike>? = null,
    val count_comments: Int? = 0,
    val promo_code: String? = null, //
    val promo_price: Int? = null, //
    val free_text: String? = null
)

@Serializable
data class Seo(
    val _id: String,
    val title: String,
    val meta_name: String,
    val meta_description: String,
    val __v: Int,
    val id: String,
)

@Serializable
data class MyLike(
    val _id: String? = null,
    val like: Boolean? = null,
    val user_id: String? = null,
    val stock_id: String? = null,
    val published_at: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val id: String? = null
)

@Serializable
data class Image(
    val _id: String? = null,
    val name: String? = null,
    val alternativeText: String? = null,
    val caption: String? = null,
    val hash: String? = null,
    val ext: String? = null,
    val mime: String? = null,
    val size: Double? = null,
    val width: Int? = null,
    val height: Int? = null,
    val url: String? = null,
    val formats: Formats? = null,
    val provider: String? = null,
    val related: List<String>? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val id: String? = null,
)

@Serializable
data class ImageX(
    val _id: String? = null,
    val name: String? = null,
    val alternativeText: String? = null,
    val caption: String? = null,
    val hash: String? = null,
    val ext: String? = null,
    val mime: String? = null,
    val size: Double? = null,
    val width: Int? = null,
    val height: Int? = null,
    val url: String? = null,
    val provider: String? = null,
    val related: List<String>? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val id: String? = null,
)

@Serializable
data class Shop(
    val top: Boolean? = null,
    val _id: String? = null,
    @SerialName("Name")
    val Name2: String? = null, //
    val name: String? = null,
    val icon: String? = null,
    val code: String? = null,
    val sort: Int? = null,
    val published_at: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val image: Image? = null,
    val seo_text: String? = null,
    val id: String? = null,
)

@Serializable
data class StockType(
    val _id: String? = null,
    val code: String? = null,
    val name: String? = null,
    val published_at: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val id: String? = null,
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
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Int? = null,
    val city: String? = null,
    val cityCode: String? = null, //
    val role: String? = null,
    val id: String? = null,
)

@Serializable
data class Formats(
    val thumbnail: Thumbnail? = null,
    val large: Large? = null,//
    val medium: Medium? = null,//
    val small: Small? = null//
)

@Serializable
data class Large(
    val ext: String? = null,
    val hash: String? = null,
    val height: Int? = null,
    val mime: String? = null,
    val name: String? = null,
    val path: String? = null,
    val size: Double? = null,
    val url: String? = null,
    val width: Int? = null
)

@Serializable
data class Medium(
    val ext: String? = null,
    val hash: String? = null,
    val height: Int? = null,
    val mime: String? = null,
    val name: String? = null,
    val path: String? = null,
    val size: Double? = null,
    val url: String? = null,
    val width: Int? = null
)

@Serializable
data class Small(
    val ext: String? = null,
    val hash: String? = null,
    val height: Int? = null,
    val mime: String? = null,
    val name: String? = null,
    val path: String? = null,
    val size: Double? = null,
    val url: String? = null,
    val width: Int? = null
)

@Serializable
data class Thumbnail(
    val name: String? = null,
    val hash: String? = null,
    val ext: String? = null,
    val mime: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val size: Double? = null,
    val path: String? = null,
    val url: String? = null,
)


