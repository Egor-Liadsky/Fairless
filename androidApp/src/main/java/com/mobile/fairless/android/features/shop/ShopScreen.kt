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
import com.mobile.fairless.android.features.shop.sheets.SelectShopSheet
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
    val state = viewModelWrapper.state

    DisposableEffect(key1 = viewModelWrapper, effect = {
        if (state.value.shop == null) {
            viewModelWrapper.viewModel.getMainShop(shop)
        }
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    })

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val selectShopSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    BackHandler {
        if (sheetState.isVisible && !selectShopSheetState.isVisible) {
            scope.launch { sheetState.hide() }
        } else if (selectShopSheetState.isVisible) {
            scope.launch { selectShopSheetState.hide() }
        } else {
            viewModelWrapper.viewModel.onBackButtonClick()
        }
    }


    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = selectShopSheetState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {
            SelectShopSheet(
                sheetState = selectShopSheetState,
                selectShopClick = {
                    viewModelWrapper.viewModel.getShop(it)
                },
                viewModelWrapper = viewModelWrapper
            )
        },
    ) {
        ModalBottomSheetLayout(
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
            sheetContent = {
                TypeFilterSheet(
                    sheetState = sheetState,
                    selectShopSheetState = selectShopSheetState,
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
}