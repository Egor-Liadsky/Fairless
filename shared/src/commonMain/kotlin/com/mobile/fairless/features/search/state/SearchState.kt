package com.mobile.fairless.features.search.state

import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData

data class SearchState(
    val products: List<ProductData>? = null,
    val categories: List<Category>? = null,

    val searchString: String = "",
    val selectedPopularFilter: String = "По популярности",
    val selectedFilters: String = "",
    val selectCategory: Category = Category(url = "all"),

    val popularFilterOpen: Boolean = false,
    val filtersOpen: Boolean = false,
    val productsLoading: Boolean = false,
    val categoriesLoading: Boolean = false,
)
