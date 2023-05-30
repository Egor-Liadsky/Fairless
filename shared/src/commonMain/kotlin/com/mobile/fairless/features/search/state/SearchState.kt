package com.mobile.fairless.features.search.state

data class SearchState(
    val searchString: String = "",
    val selectedPopularFilter: String = "По популярности",
    val selectedFilters: String = "",

    val popularFilterOpen: Boolean = false,
    val filtersOpen: Boolean = false
)
