package com.mobile.fairless.android.features.welcome.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.welcome.register.layouts.RegisterLayout
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun RegisterScreen(viewModelWrapper: ViewModelWrapper<RegisterViewModel> = get(named("RegisterViewModel"))) {

    BackHandler {
        viewModelWrapper.viewModel.navigateToWelcome()
    }

    Column(Modifier.fillMaxSize()) {
        RegisterLayout(viewModelWrapper)
    }
}
