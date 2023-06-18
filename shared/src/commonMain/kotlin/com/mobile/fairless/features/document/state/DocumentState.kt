package com.mobile.fairless.features.document.state

import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.document.model.Comment
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData

data class Period(
    val title: String,
    val period: DateFilter
)

data class DocumentState(
    val loadingState: LoadingState = LoadingState.Loading,
    val loadingStateComment: LoadingState = LoadingState.Loading,
    val loadingStateFire: LoadingState = LoadingState.Loading,
    val commentText: String? = "",
    val product: ProductData = ProductData(),
    val fireProduct: List<ProductData> = mutableListOf(),
    val selectFirePeriod: Period = Period("Сегодня", DateFilter.TODAY),
    val todayNull: Boolean = false,
    val comments: List<Comment>? = null,
    val authUser: Boolean = false,
    val productName: String = "",

    val periodMenuOpen: Boolean = false,
    val isLoading: Boolean = false
)
