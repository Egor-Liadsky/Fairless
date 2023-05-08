package com.mobile.fairless.android.features.profile.layouts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel

@Composable
fun ProfileLayout(viewModelWrapper: ViewModelWrapper<ProfileViewModel>) {
    viewModelWrapper.viewModel.getProfile()

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Text(text = state.value.user.toString())
}
