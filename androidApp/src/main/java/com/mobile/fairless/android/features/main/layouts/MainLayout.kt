package com.mobile.fairless.android.features.main.layouts

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.components.MainTopBar
import com.mobile.fairless.android.features.main.components.ProductItem
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.main.viewModel.MainViewModel


@Composable
fun MainLayout(viewModelWrapper: ViewModelWrapper<MainViewModel>) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column {
        MainTopBar(viewModelWrapper = viewModelWrapper)
        Log.e("qwklejqkwe", state.value.productsLoading.toString())

        if (state.value.productsLoading){
            Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .size(40.dp),
                    color = colors.orangeMain,
                )
            }
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items = state.value.products.data ?: emptyList()) { product ->
                    Column(Modifier.padding(horizontal = 16.dp)) {
                        ProductItem(product = product) {
                            viewModelWrapper.viewModel.onDocumentClick(product.name ?: "")
                        }
                    }
                }
            }
        }
    }
}


