package com.mobile.fairless.android.features.menu.layouts

import androidx.compose.runtime.Composable
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.menu.components.AdditionalView
import com.mobile.fairless.android.features.menu.components.ProfileView
import com.mobile.fairless.android.features.menu.components.TestView
import com.mobile.fairless.features.menu.state.MenuState
import com.mobile.fairless.features.menu.viewModel.MenuViewModel

@Composable
fun MenuLayout(viewModelWrapper: StatefulViewModelWrapper<MenuViewModel, MenuState>) {
    val state = viewModelWrapper.state.value
    ProfileView(state) {
        viewModelWrapper.viewModel.navigateToProfile()
    }
    TestView {

    }
    AdditionalView(viewModelWrapper)
}
