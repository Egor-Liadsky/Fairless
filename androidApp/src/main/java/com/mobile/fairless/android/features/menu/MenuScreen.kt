package com.mobile.fairless.android.features.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.menu.layouts.MenuLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.menu.state.MenuState
import com.mobile.fairless.features.menu.viewModel.MenuViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun MenuScreen(viewModelWrapper: StatefulViewModelWrapper<MenuViewModel, MenuState> = get(named("MenuViewModel"))) {
    viewModelWrapper.viewModel.getProfile()
    DisposableEffect(key1 = viewModelWrapper, effect = {
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    })
    Column(Modifier.fillMaxSize().background(colors.white)) {
        MenuLayout(viewModelWrapper)
    }
}
