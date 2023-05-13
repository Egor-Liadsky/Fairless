package com.mobile.fairless.android.features.main.layouts

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.components.MainTopBar
import com.mobile.fairless.features.main.viewModel.MainViewModel


@Composable
fun MainLayout(viewModelWrapper: ViewModelWrapper<MainViewModel>) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column {
        MainTopBar(viewModelWrapper = viewModelWrapper)
    }

    LazyColumn {
        item {
            Text(text = viewModelWrapper.viewModel.state.value.products.toString())
        }
    }


    Log.e("TAGTAGCATEGORY", state.value.categories.toString())
}


