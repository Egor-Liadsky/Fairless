package com.mobile.fairless.android.features.main.layouts

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.components.MainTopBar
import com.mobile.fairless.android.features.main.components.ProductItem
import com.mobile.fairless.android.features.views.Refreshable
import com.mobile.fairless.android.features.views.layouts.EmptyLayout
import com.mobile.fairless.android.features.views.layouts.ErrorLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.viewModel.MainViewModel


@Composable
fun MainLayout(viewModelWrapper: ViewModelWrapper<MainViewModel>) {

    val state = viewModelWrapper.viewModel.state.collectAsState()
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

    Column {
        MainTopBar(viewModelWrapper = viewModelWrapper)

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
                        
                        item {
                            Column(verticalArrangement = Arrangement.Center) {
                                Button(onClick = { viewModelWrapper.viewModel.selectType(ProductStockType.ALL) }) {
                                    Text(text = "Промокоды и скидки")
                                }

                                Button(onClick = { viewModelWrapper.viewModel.selectType(ProductStockType.PROMOCODE) }) {
                                    Text(text = "Промокоды")
                                }

                                Button(onClick = { viewModelWrapper.viewModel.selectType(ProductStockType.SALE) }) {
                                    Text(text = "Скидки")
                                }

                                Button(onClick = { viewModelWrapper.viewModel.selectType(ProductStockType.FREE) }) {
                                    Text(text = "Бесплатно")
                                }
                            }
                            Log.e("asdasdasdasdads", state.value.selectType.toString())
                        }
                        
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


