package com.mobile.fairless.features.profile.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.welcome.dto.UserReceive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ProfileViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<ProfileState>

    fun navigateToProfileEdit()
    fun getProfile()
}

class ProfileViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    ProfileViewModel {

    private val prefService: PrefService by inject()

    private val _state = MutableStateFlow(ProfileState())
    override val state: StateFlow<ProfileState> = _state.asStateFlow()

    override fun navigateToProfileEdit() {
        navigator.navigateToProfileEdit()
    }

    override fun getProfile() {
        _state.update { it.copy(user = prefService.getUserInfo() ?: UserReceive()) }
    }
}

