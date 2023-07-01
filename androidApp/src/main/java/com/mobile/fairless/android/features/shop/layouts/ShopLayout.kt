package com.mobile.fairless.android.features.shop.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.shop.components.ShopTopBar
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShopLayout(
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState>,
    sheetState: ModalBottomSheetState
) {
    val state = viewModelWrapper.state

    Column(Modifier.fillMaxSize()) {
        ShopTopBar(
            shopTitle = state.value.shop?.name ?: "Aliexpress", viewModelWrapper = viewModelWrapper,
            sheetState = sheetState
        )
    }
}