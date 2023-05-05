package com.mobile.fairless.android.features.main.layouts

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.components.CategoriesView
import com.mobile.fairless.android.features.main.components.MainTopBar
import com.mobile.fairless.features.main.viewModel.MainViewModel


@Composable
fun MainLayout(viewModelWrapper: ViewModelWrapper<MainViewModel>) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column {
        MainTopBar(viewModelWrapper = viewModelWrapper)
    }

    Log.e("TAGTAGCATEGORY", state.value.categories.toString())
}
