package com.mobile.fairless.features.document.state

import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData

data class DocumentState(
    val product: ProductData = ProductData(),
    val fireProduct: List<ProductData> = mutableListOf()
)
