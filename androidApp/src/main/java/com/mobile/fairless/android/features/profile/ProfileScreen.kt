package com.mobile.fairless.android.features.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.profile.layouts.ProfileLayout
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun ProfileScreen(viewModelWrapper: StatefulViewModelWrapper<ProfileViewModel, ProfileState> = getViewModel(named("ProfileViewModel"))) {
    viewModelWrapper.viewModel.getProfile()

    Column(Modifier.fillMaxSize()) {
        ProfileLayout(viewModelWrapper = viewModelWrapper)
    }
}
