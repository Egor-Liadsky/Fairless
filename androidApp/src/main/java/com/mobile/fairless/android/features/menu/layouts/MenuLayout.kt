package com.mobile.fairless.android.features.menu.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.buttons.ShapeButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.menu.viewModel.MenuViewModel

@Composable
fun MenuLayout(viewModelWrapper: ViewModelWrapper<MenuViewModel>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShapeButton(title = "Профиль") {
            viewModelWrapper.viewModel.navigateToProfile()
        }
        ShapeButton(title = "Настройки", modifier = Modifier.padding(top = 20.dp)) {
            viewModelWrapper.viewModel.navigateToSettings()
        }
    }
}
