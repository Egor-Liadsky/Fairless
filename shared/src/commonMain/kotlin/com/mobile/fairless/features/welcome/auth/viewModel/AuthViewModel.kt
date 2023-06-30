package com.mobile.fairless.features.welcome.auth.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.welcome.models.UserAuthResponse
import com.mobile.fairless.features.welcome.auth.service.AuthService
import com.mobile.fairless.features.welcome.auth.state.AuthState
import com.mobile.fairless.features.welcome.models.UserReceive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface AuthViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<AuthState>

    fun emailChanged(email: String)
    fun passwordChanged(password: String)
    fun authUser(userAuthResponse: UserAuthResponse)
    fun navigateToMenu()
}

class AuthViewModelImpl(override val navigator: Navigator) : KoinComponent, KmpViewModelImpl(),
    AuthViewModel {

    private val authService: AuthService by inject()
    private val errorService: ErrorService by inject()
    private val prefService: PrefService by inject()

    private val _state = MutableStateFlow(AuthState())
    override val state: StateFlow<AuthState> = _state.asStateFlow()


    override fun emailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun passwordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun authUser(userAuthResponse: UserAuthResponse) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(isLoading = true) }
                    val data = authService.authUser(userAuthResponse)
                    _state.update { it.copy(user = data) }
                    onBackButtonClick()
                },
                failureBlock = {
                    errorService.showError("Логин или email неверны")
                },
                completionBlock = {
                    _state.update { it.copy(isLoading = false) }
                    if (_state.value.user?.user != null){
                        prefService.setUserInfo(_state.value.user ?: UserReceive())
                    }
                }
            )
        }
    }

    override fun navigateToMenu() {
        navigator.navigateToMenu()
    }
}

