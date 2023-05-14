package com.mobile.fairless.android.features.welcome.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.topBars.StageBar
import com.mobile.fairless.android.features.welcome.register.layouts.CheckEmailScreen
import com.mobile.fairless.android.features.welcome.register.layouts.PasswordDataScreen
import com.mobile.fairless.android.features.welcome.register.layouts.UserDataScreen
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun RegisterScreen(viewModelWrapper: ViewModelWrapper<RegisterViewModel> = get(named("RegisterViewModel"))) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome), horizontalAlignment = Alignment.CenterHorizontally) {
        StageBar(modifier = Modifier.padding(top = 30.dp), number = state.value.stage, count = 3)
        when (state.value.stage) {
            1 -> UserDataScreen(viewModelWrapper)
            2 -> PasswordDataScreen(viewModelWrapper)
            3 -> CheckEmailScreen(viewModelWrapper)
        }
    }
}
