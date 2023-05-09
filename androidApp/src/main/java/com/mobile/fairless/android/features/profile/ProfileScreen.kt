package com.mobile.fairless.android.features.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.profile.layouts.ProfileLayout
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun ProfileScreen(viewModelWrapper: ViewModelWrapper<ProfileViewModel> = get(named("ProfileViewModel"))) {

    viewModelWrapper.viewModel.getProfile()

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {

        CommonTopBar(
            title = state.value.user?.user?.username ?: "",
            description = "На сервисе с ${state.value.user?.user?.dateTime}",
            isBack = true,
            isEdit = true,
            backClick = {
                viewModelWrapper.viewModel.navigateToMenu()
            },
            editClick = {
                viewModelWrapper.viewModel.navigateToSettings()
            })

        ProfileLayout(viewModelWrapper = viewModelWrapper)
    }
}
