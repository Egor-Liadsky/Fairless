package com.mobile.fairless.features.search.models

import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.search.pagination.SearchPaginatedResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchProductResponse(
    override val page: Int? = 1,
    @SerialName("data")
    override val list: List<ProductData>,
): SearchPaginatedResult<ProductData>