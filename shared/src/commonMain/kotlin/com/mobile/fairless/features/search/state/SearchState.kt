package com.mobile.fairless.features.search.state

import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.viewModel.ProductModel
import com.mobile.fairless.features.search.pagination.SearchPagingData
import com.mobile.fairless.features.search.viewModel.SearchProductModel

data class SearchState(
    val pagingData: SearchPagingData<SearchProductModel> = SearchPagingData(),

    val products: List<ProductData>? = null,
    val categories: List<Category>? = null,

    val refreshable: Boolean = false,

    val searchString: String = "",
    val selectedPopularFilter: String = "По популярности",
    val selectedFilters: String = "",
    val selectCategory: Category = Category(url = "all"),

    val popularFilterOpen: Boolean = false,
    val filtersOpen: Boolean = false,
    val productsLoading: Boolean = false,
    val categoriesLoading: Boolean = false,
)
