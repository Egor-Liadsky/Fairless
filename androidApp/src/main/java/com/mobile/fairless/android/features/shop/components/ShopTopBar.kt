package com.mobile.fairless.android.features.shop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel

@Composable
fun ShopTopBar(
    shopTitle: String,
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState>
) {
    val state = viewModelWrapper.state

    Column(Modifier.fillMaxWidth().background(colors.backgroundWelcome)) {
        CommonTopBar(title = shopTitle) {
            viewModelWrapper.viewModel.onBackButtonClick()
        }
        ShopFilterView(
            Modifier.padding(horizontal = 16.dp),
            selectPopularsFilter = state.value.selectedPopularFilter,
            popularFilterOpen = state.value.popularFilterOpen,
            popularFilterClick = { viewModelWrapper.viewModel.popularFilterOpen() },
            popularFilterItemClick = { viewModelWrapper.viewModel.selectPopularFilter(it) },
            typeFilterClick = { viewModelWrapper.viewModel.popularFilterOpen() },
        )
    }
}