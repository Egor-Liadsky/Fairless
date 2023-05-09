package com.mobile.fairless.android.features.menu.layouts

import androidx.compose.runtime.Composable
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.menu.viewModel.MenuViewModel

@Composable
fun MenuLayout(viewModelWrapper: ViewModelWrapper<MenuViewModel>) {
    CommonButton(
        commonButtonParams = CommonButtonParams(
            title = "Профиль",
            titleColor = colors.black,
            background = colors.white
        ),
    ) {
        viewModelWrapper.viewModel.navigateToProfile()
    }
}
