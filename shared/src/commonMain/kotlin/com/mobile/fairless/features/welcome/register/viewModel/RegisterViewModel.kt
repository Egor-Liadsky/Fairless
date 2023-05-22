package com.mobile.fairless.features.welcome.register.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.welcome.auth.service.AuthService
import com.mobile.fairless.features.welcome.dto.City
import com.mobile.fairless.features.welcome.dto.UserAuthResponse
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

    fun emailChanged(email: String)
    fun loginChanged(login: String)
    fun cityChanged(city: City)
    fun passwordChanged(password: String)
    fun passwordRetryChanged(passwordRetry: String)
    fun searchChanged(search: String)
    fun onDeleteSearchClick()
    fun registerUser(userRegisterResponse: UserRegisterResponse)
    fun selectCityClick()
    fun onNextClick()
    fun onBackAction()
    fun checkUser(userAuthResponse: UserAuthResponse)
}

class RegisterViewModelImpl(override val navigator: Navigator) : KoinComponent, KmpViewModelImpl(),
    RegisterViewModel {

    private val registerService: RegisterService by inject()
    private val authService: AuthService by inject()
    private val errorService: ErrorService by inject()

    private val _state = MutableStateFlow(RegisterState())
    override val state: StateFlow<RegisterState> = _state.asStateFlow()

    override fun emailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun loginChanged(login: String) {
        _state.update { it.copy(login = login) }
    }

    override fun cityChanged(city: City) {
        _state.update { it.copy(city = city) }
    }

    override fun passwordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun passwordRetryChanged(passwordRetry: String) {
        _state.update { it.copy(passwordRetry = passwordRetry) }
    }

    override fun searchChanged(search: String) {
        _state.update { it.copy(search = search) }
    }

    override fun onDeleteSearchClick() {
        searchChanged("")
    }

    override fun registerUser(userRegisterResponse: UserRegisterResponse) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(isLoading = true) }
                    val data = registerService.registerUser(userRegisterResponse)
                    _state.update { it.copy(user = data) }
                    navigator.navigateToMain()
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

    override fun selectCityClick() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(isLoading = true) }
                    if (_state.value.cities == null) {
                        _state.update { it.copy(cities = registerService.getCities()) }
                    }
                },
                failureBlock = {
                    errorService.showError("Ошибка при получении данных")
                },
                completionBlock = {
                    _state.update {
                        it.copy(
                            alertDialogOpen = !it.alertDialogOpen,
                            isLoading = false
                        )
                    }
                }
            )
        }
    }

    override fun onNextClick() {
        if (_state.value.stage < 3) {
            _state.update { it.copy(stage = _state.value.stage + 1) }
        } else {
            scope.launch {
                //TODO вставить функцию сохранения пользователя на устройство
                navigator.navigateToMain()
            }
        }
    }

    override fun onBackAction() {
        if(_state.value.stage > 1)
            _state.update { it.copy(stage = _state.value.stage - 1) }
        else
            navigator.navigateBack()
    }

    override fun checkUser(userAuthResponse: UserAuthResponse) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(isLoading = true) }
                    val data = authService.authUser(userAuthResponse)
                    _state.update { it.copy(user = data) }
                },
                failureBlock = {
                    errorService.showError("Подтвердите аккаунт")
                },
                completionBlock = {
                    _state.update { it.copy(isLoading = false) }
                },
            )
        }
    }
}
