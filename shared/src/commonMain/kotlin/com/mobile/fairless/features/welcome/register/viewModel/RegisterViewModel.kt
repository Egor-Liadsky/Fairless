package com.mobile.fairless.features.welcome.register.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.welcome.auth.service.AuthService
import com.mobile.fairless.features.welcome.dto.UserRegisterResponse
import com.mobile.fairless.features.welcome.register.service.RegisterService
import com.mobile.fairless.features.welcome.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RegisterViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<RegisterState>

    fun navigateToWelcome()
    fun emailChanged(email: String)
    fun loginChanged(login: String)
    fun passwordChanged(password: String)
    fun passwordRetryChanged(passwordRetry: String)
    fun registerUser(userRegisterResponse: UserRegisterResponse)
}

class RegisterViewModelImpl(override val navigator: Navigator) : KoinComponent, KmpViewModelImpl(),
    RegisterViewModel {

    private val registerService: RegisterService by inject()
    private val errorService: ErrorService by inject()

    private val _state = MutableStateFlow(RegisterState())
    override val state: StateFlow<RegisterState> = _state.asStateFlow()

    override fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }

    override fun emailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun loginChanged(login: String) {
        _state.update { it.copy(login = login) }
    }

    override fun passwordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun passwordRetryChanged(passwordRetry: String) {
        _state.update { it.copy(passwordRetry = passwordRetry) }
    }

    override fun registerUser(userRegisterResponse: UserRegisterResponse) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(isLoading = true) }
                    val data = registerService.registerUser(userRegisterResponse)
                    _state.update { it.copy(user = data) }
                },
                failureBlock = {
                    errorService.showError("Некорректные данные")
                },
                completionBlock = {
                    _state.update { it.copy(isLoading = false) }
                }
            )
        }
    }
}
