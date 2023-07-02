package com.mobile.fairless.features.main.models.response

import com.mobile.fairless.common.pagination.PaginatedResult
import com.mobile.fairless.features.main.models.ProductData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    override val page: Int? = 1,
    @SerialName("count")
    override val total: Int? = 1,
    @SerialName("data")
    override val list: List<ProductData>,
): PaginatedResult<ProductData>

