package com.mobile.fairless.features.profile.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.profile.state.ProfileButton
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.welcome.dto.UserReceive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ProfileViewModel : StatefulKmpViewModel<ProfileState>, SubScreenViewModel {

    fun navigateToProfileEdit()
    fun getProfile()
    fun navigateToMain()
    fun selectButton(profileButton: ProfileButton)
}

class ProfileViewModelImpl(override val navigator: Navigator) : StatefulKmpViewModelImpl<ProfileState>(), KoinComponent,
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

    override fun navigateToMain() {
        navigator.navigateToMain()
    }

    override fun selectButton(profileButton: ProfileButton) {
        _state.update { it.copy(selectButton = profileButton) }
    }
}

