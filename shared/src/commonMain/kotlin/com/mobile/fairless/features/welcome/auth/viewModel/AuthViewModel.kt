package com.mobile.fairless.features.welcome.auth.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.welcome.auth.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface AuthViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<AuthState>

    fun navigateToWelcome()
    fun emailChanged(email: String)
    fun passwordChanged(password: String)
}

class AuthViewModelImpl(override val navigator: Navigator) : KoinComponent, KmpViewModelImpl(),
    AuthViewModel {
    private val _state = MutableStateFlow(AuthState())
    override val state: StateFlow<AuthState> = _state.asStateFlow()

    override fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }

    override fun emailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun passwordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }
}

