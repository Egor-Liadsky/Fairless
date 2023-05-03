package com.mobile.fairless.android.features.welcome.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.welcome.register.layouts.CheckEmailScreen
import com.mobile.fairless.android.features.welcome.register.layouts.PasswordDataScreen
import com.mobile.fairless.android.features.welcome.register.layouts.RegisterLayout
import com.mobile.fairless.android.features.welcome.register.layouts.UserDataScreen
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun RegisterScreen(viewModelWrapper: ViewModelWrapper<RegisterViewModel> = get(named("RegisterViewModel"))) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    BackHandler {
        viewModelWrapper.viewModel.navigateToWelcome()
    }

    Column(Modifier.fillMaxSize()) {
        when(state.value.stage){
            1 -> UserDataScreen(viewModelWrapper)
            2 -> PasswordDataScreen(viewModelWrapper)
            3 -> CheckEmailScreen(viewModelWrapper)
        }
    }
}
