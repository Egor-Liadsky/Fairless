package com.mobile.fairless.features.main.state

import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.viewModel.ProductModel
import com.mobile.fairless.features.welcome.models.UserReceive

data class MainState(
    val pagingData: PagingData<ProductModel> = PagingData(),
    val categories: List<Category>? = null,

    val refreshable: Boolean = false,
    val authDialogOpen: Boolean = false,
    val categoriesLoading: LoadingState = LoadingState.Loading,
    val productsLoading: Boolean = false,

    val user: UserReceive? = UserReceive(),
    val products: Product = Product(),

    val selectCategory: Category = Category(url = "news", type = "news"),
    val selectType: Type = Type("Промокоды и скидки", ProductStockType.ALL),
){
    companion object {
        fun getInstance() = MainState()
    }
}
