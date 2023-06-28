package com.mobile.fairless.features.shop.state

import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.viewModel.ProductModel
import com.mobile.fairless.features.search.models.PopularFilter
import com.mobile.fairless.features.search.models.Sort

data class ShopState(
    val pagingData: PagingData<ProductModel> = PagingData(),
    val products: List<ProductData>? = null,
    val categories: List<Category>? = null,
    val refreshable: Boolean = false,
    val selectedPopularFilter: PopularFilter = PopularFilter("По дате создания", Sort.CREATE),
    val selectedFilters: String = "",
    val selectCategory: Category = Category(url = "all"),
    val selectType: Type = Type("Промокоды и скидки", ProductStockType.ALL),
    val popularFilterOpen: Boolean = false,
    val filtersOpen: Boolean = false,
    val productsLoading: Boolean = false,
    val categoriesLoading: Boolean = false,
)