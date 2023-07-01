package com.mobile.fairless.android.features.shop.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.main.components.ProductItem
import com.mobile.fairless.android.features.shop.components.ShopTopBar
import com.mobile.fairless.android.features.views.Refreshable
import com.mobile.fairless.android.features.views.layouts.EmptyLayout
import com.mobile.fairless.android.features.views.layouts.ErrorLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShopLayout(
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState>,
    sheetState: ModalBottomSheetState
) {
    val state = viewModelWrapper.state
    val statePaging = viewModelWrapper.viewModel.statePaging.collectAsState()

    val lazyColumnState = rememberLazyListState()
    val needAppend = remember {
        derivedStateOf {
            lazyColumnState.layoutInfo.totalItemsCount >= 10 &&
                    (lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                        ?: Int.MIN_VALUE) >= lazyColumnState.layoutInfo.totalItemsCount - 5
        }
    }

    LaunchedEffect(needAppend.value) {
        if (needAppend.value) viewModelWrapper.viewModel.onAppend()
    }

    Column(Modifier.fillMaxSize()) {
        ShopTopBar(
            shopTitle = state.value.shop?.name ?: "Без названия",
            viewModelWrapper = viewModelWrapper,
            sheetState = sheetState
        )

        Refreshable(
            isRefreshing = state.value.refreshable,
            onRefresh = { viewModelWrapper.viewModel.onRefresh() }
        ) {
            LazyColumn(
                state = lazyColumnState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {

                when (statePaging.value.pagingData.loadingState) {
                    LoadingState.Loading -> {
                        item {
                            LoadingLayout()
                        }
                    }

                    LoadingState.Success -> {
                        items(
                            items = statePaging.value.pagingData.data ?: emptyList()
                        ) { product ->
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                ProductItem(product = product.product) {
                                    viewModelWrapper.viewModel.onDocumentClick(
                                        product.product.name ?: ""
                                    )
                                }
                            }
                        }
                    }

                    LoadingState.Empty -> {
                        item {
                            EmptyLayout()
                        }
                    }

                    is LoadingState.Error -> {
                        item {
                            ErrorLayout {
                                viewModelWrapper.viewModel.onRefreshClick()
                            }
                        }
                    }

                    else -> {}
                }

                if (statePaging.value.pagingData.isAppending)
                    item {
                        CircularProgressIndicator(
                            color = colors.orangeMain,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(24.dp)
                        )
                    }

                item {
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }
            }
        }
    }
}