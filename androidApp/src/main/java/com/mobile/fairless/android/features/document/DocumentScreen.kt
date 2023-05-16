package com.mobile.fairless.android.features.document

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.document.components.DocumentTopBar
import com.mobile.fairless.android.features.document.layouts.DocumentLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun DocumentScreen(product: String, viewModelWrapper: ViewModelWrapper<DocumentViewModel> = get(named("DocumentViewModel"))) {

    Log.e("q'lw;keqwe", product)

    viewModelWrapper.viewModel.decodeProduct(product)
    val state = viewModelWrapper.viewModel.state.collectAsState()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome)) {
        item {
            DocumentTopBar(product = state.value.product, viewModelWrapper = viewModelWrapper)
            DocumentLayout(product = state.value.product)
        }
    }
}

