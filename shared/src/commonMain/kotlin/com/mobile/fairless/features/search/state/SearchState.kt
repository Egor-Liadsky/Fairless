package com.mobile.fairless.features.search.state

import com.mobile.fairless.features.main.models.ProductData

data class SearchState(
    val products: List<ProductData>? = null,
    val searchString: String = "",
    val selectedPopularFilter: String = "По популярности",
    val selectedFilters: String = "",

    val popularFilterOpen: Boolean = false,
    val filtersOpen: Boolean = false,
    val productsLoading: Boolean = false
)
