package com.mobile.fairless.android.features.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.settings.layouts.SettingsLayout
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.features.settings.viewModel.SettingsViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun SettingsScreen(viewModelWrapper: ViewModelWrapper<SettingsViewModel> = get(named("SettingsViewModel"))) {
    Column(Modifier.fillMaxSize()) {
        CommonTopBar(title = "Настройки") {
            viewModelWrapper.viewModel.onBackPressed()
        }

        SettingsLayout()
    }
}
