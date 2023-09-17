package com.mobile.fairless.features.search.state

import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.state.MainState
import com.mobile.fairless.features.main.viewModel.ProductModel
import com.mobile.fairless.features.search.models.PopularFilter
import com.mobile.fairless.features.search.models.Sort

data class SearchState(
    val pagingData: PagingData<ProductModel> = PagingData(),
    val products: List<ProductModel> = listOf(),
    val refreshable: Boolean = false,
    val searchString: String = "",
    val selectedPopularFilter: PopularFilter = PopularFilter("По дате создания", Sort.CREATE),
    val selectedFilters: String = "",
    val selectCategory: CategoryModel = CategoryModel(url = "all"),
    val selectType: Type = Type("Промокоды и скидки", ProductStockType.ALL),
    val popularFilterOpen: Boolean = false,
    val filtersOpen: Boolean = false,
    val productsLoading: LoadingState = LoadingState.Loading,
    val categoriesLoading: Boolean = false,
    val isAppending:Boolean = false,
    ) {
    companion object {
        fun getInstance() = SearchState()
    }
}
