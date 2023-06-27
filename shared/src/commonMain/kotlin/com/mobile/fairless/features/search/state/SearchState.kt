package com.mobile.fairless.features.search.state

import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.viewModel.ProductModel

data class SearchState(
    val pagingData: PagingData<ProductModel> = PagingData(),

    val products: List<ProductData>? = null,
    val categories: List<Category>? = null,

    val refreshable: Boolean = false,

    val searchString: String = "",
    val selectedPopularFilter: String = "По популярности",
    val selectedFilters: String = "",
    val selectCategory: Category = Category(url = "all"),
    val selectType: Type = Type("Промокоды и скидки", ProductStockType.ALL),

    val popularFilterOpen: Boolean = false,
    val filtersOpen: Boolean = false,
    val productsLoading: Boolean = false,
    val categoriesLoading: Boolean = false,
)
