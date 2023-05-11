package com.mobile.fairless.features.profileEdit.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.profileEdit.state.ProfileEditState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent


interface ProfileEditViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<ProfileEditState>
}

class ProfileEditViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent, ProfileEditViewModel{

    private val _state = MutableStateFlow(ProfileEditState())
    override val state: StateFlow<ProfileEditState> = _state.asStateFlow()
}
