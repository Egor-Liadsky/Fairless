package com.mobile.fairless.android.features.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.layouts.MainLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.main.viewModel.MainViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun MainScreen(viewModelWrapper: ViewModelWrapper<MainViewModel> = get(named("MainViewModel"))) {

    viewModelWrapper.viewModel.getCategories()
    viewModelWrapper.viewModel.getProfile()

    Column(Modifier.fillMaxSize().background(colors.white)) {
        MainLayout(viewModelWrapper = viewModelWrapper)
    }

    Log.e("kajsldkjaklsjd", viewModelWrapper.viewModel.state.value.user.toString())
}
