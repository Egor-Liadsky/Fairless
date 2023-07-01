package com.mobile.fairless.android.features.shop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.shop.layouts.ShopLayout
import com.mobile.fairless.android.features.shop.sheets.TypeFilterSheet
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShopScreen(
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState> = getViewModel(
        named("ShopViewModel")
    ),
    shop: String
) {
    viewModelWrapper.viewModel.getShop(shop)

    DisposableEffect(key1 = viewModelWrapper, effect = {
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    })

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    BackHandler {
        if (sheetState.isVisible){
            scope.launch { sheetState.hide() }
        } else {
            viewModelWrapper.viewModel.onBackButtonClick()
        }
    }

    val state = viewModelWrapper.state

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {
            TypeFilterSheet(
                sheetState = sheetState,
                selectCategoryClick = { viewModelWrapper.viewModel.selectCategory(it) },
                viewModelWrapper = viewModelWrapper
            )
        },
    ) {
        Column {
            ShopLayout(viewModelWrapper, sheetState)
        }
    }
}