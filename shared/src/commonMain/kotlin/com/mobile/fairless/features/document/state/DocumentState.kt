package com.mobile.fairless.features.document.state

import com.mobile.fairless.features.document.model.Comment
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData

data class DocumentState(
    val commentText: String? = "",
    val product: ProductData = ProductData(),
    val fireProduct: List<ProductData> = mutableListOf(),
    val selectFirePeriod: DateFilter = DateFilter.TODAY,
    val todayNull: Boolean = false,
    val fireProductsLoading: Boolean = false,
    val comments: List<Comment>? = null,
    val authUser: Boolean = false
)
