package com.mobile.fairless.features.main.state

import com.mobile.fairless.features.main.models.Category

data class MainState(
    val categories: List<Category>? = null,
    val categoriesLoading: Boolean = false
)
