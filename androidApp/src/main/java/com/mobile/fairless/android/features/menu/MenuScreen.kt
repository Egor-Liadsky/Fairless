package com.mobile.fairless.android.features.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.menu.layouts.MenuLayout
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.menu.state.MenuState
import com.mobile.fairless.features.menu.viewModel.MenuViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun MenuScreen(viewModelWrapper: StatefulViewModelWrapper<MenuViewModel, MenuState> = get(named("MenuViewModel"))) {
    viewModelWrapper.viewModel.getProfile()
    Column(Modifier.fillMaxSize()) {
        MenuLayout(viewModelWrapper)
    }
}
