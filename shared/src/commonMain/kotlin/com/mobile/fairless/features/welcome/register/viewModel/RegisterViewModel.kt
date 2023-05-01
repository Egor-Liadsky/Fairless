package com.mobile.fairless.features.welcome.register.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.welcome.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface RegisterViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<RegisterState>
    fun emailChanged(email: String)
    fun login(login: String)
    fun passwordChanged(password: String)
    fun passwordRetryChanged(passwordRetry: String)
}

class RegisterViewModelImpl(override val navigator: Navigator) : KoinComponent, KmpViewModelImpl(),
    RegisterViewModel {
    private val _state = MutableStateFlow(RegisterState())
    override val state: StateFlow<RegisterState> = _state.asStateFlow()

    override fun emailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun login(login: String) {
        _state.update { it.copy(login = login) }
    }

    override fun passwordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun passwordRetryChanged(passwordRetry: String) {
        _state.update { it.copy(passwordRetry = passwordRetry) }
    }
}
