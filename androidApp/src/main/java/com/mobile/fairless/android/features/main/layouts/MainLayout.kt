package com.mobile.fairless.android.features.main.layouts

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.components.MainTopBar
import com.mobile.fairless.android.features.main.components.ProductItem
import com.mobile.fairless.features.main.viewModel.MainViewModel


@Composable
fun MainLayout(viewModelWrapper: ViewModelWrapper<MainViewModel>) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column {
        MainTopBar(viewModelWrapper = viewModelWrapper)

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = state.value.products.data ?: emptyList()) { product ->
                Column {
                    ProductItem(product = product)
                }
            }
        }
    }

    state.value.products.data?.forEach { item -> }

//    if (state.value.products.data != null)
//        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
//            items(items = state.value.products.data ?: emptyList()) { product ->
//                Column {
//                    ProductItem(product = product)
//                }
//            }
//        }


    Log.e("TAGTAGCATEGORY", state.value.categories.toString())
}


