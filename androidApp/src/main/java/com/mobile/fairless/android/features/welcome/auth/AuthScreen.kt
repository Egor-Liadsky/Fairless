package com.mobile.fairless.android.features.welcome.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.welcome.auth.layouts.AuthLayout
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun AuthScreen(viewModelWrapper: ViewModelWrapper<AuthViewModel> = get(named("AuthViewModel"))) {
    Column(Modifier.fillMaxSize()) {
        AuthLayout(viewModelWrapper)
    }
}
