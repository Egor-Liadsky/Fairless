package com.mobile.fairless.android.features.shop.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.shop.components.ShopTopBar
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel

@Composable
fun ShopLayout(viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState>) {
    Column(Modifier.fillMaxSize()) {
        ShopTopBar(shopTitle = "Яндекс", viewModelWrapper = viewModelWrapper)
    }
}