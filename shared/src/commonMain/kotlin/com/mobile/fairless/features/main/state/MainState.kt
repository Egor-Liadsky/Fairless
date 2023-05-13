package com.mobile.fairless.features.main.state

import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.welcome.dto.UserReceive

data class MainState(
    val categories: List<Category>? = null,
    val categoriesLoading: Boolean = false,

    val user: UserReceive? = UserReceive(),
    val products: Product = Product()
)
