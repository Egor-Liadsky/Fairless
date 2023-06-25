package com.mobile.fairless.android.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.layouts.MainLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.main.viewModel.MainViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun MainScreen(viewModelWrapper: ViewModelWrapper<MainViewModel> = getViewModel(named("MainViewModel"))) {

    DisposableEffect(key1 = viewModelWrapper, effect = {
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    })

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.white)) {
        MainLayout(viewModelWrapper = viewModelWrapper)
    }
}
