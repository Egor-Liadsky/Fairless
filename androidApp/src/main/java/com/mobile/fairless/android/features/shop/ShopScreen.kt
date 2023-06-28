package com.mobile.fairless.android.features.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.shop.layouts.ShopLayout
import com.mobile.fairless.features.search.state.SearchState
import com.mobile.fairless.features.search.viewModel.SearchViewModel
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel
import com.mobile.fairless.features.shop.viewModel.ShopViewModelImpl
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun ShopScreen(
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState> = getViewModel(
        named("ShopViewModel")
    )
) {
    Column {
        ShopLayout(viewModelWrapper)
    }
}