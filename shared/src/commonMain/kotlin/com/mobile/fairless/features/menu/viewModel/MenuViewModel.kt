package com.mobile.fairless.features.menu.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.menu.state.MenuState
import com.mobile.fairless.features.welcome.dto.UserReceive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MenuViewModel : StatefulKmpViewModel<MenuState>, SubScreenViewModel {

    fun navigateToProfile()
    fun navigateToSettings()
    fun getProfile()
    fun exitUser()
}

class MenuViewModelImpl(override val navigator: Navigator) : StatefulKmpViewModelImpl<MenuState>(), KoinComponent,
    MenuViewModel {

    private val prefService: PrefService by inject()
    private val errorService: ErrorService by inject()

    private val _state = MutableStateFlow(MenuState())
    override val state: StateFlow<MenuState> = _state.asStateFlow()

    override fun navigateToProfile() {
        navigator.navigateToProfile()
    }

    override fun getProfile() {
        _state.update { it.copy(user = prefService.getUserInfo() ?: UserReceive()) }
    }

    override fun navigateToSettings() {
        navigator.navigateToSettings()
    }

    override fun exitUser() {
        scope.launch {
            exceptionHandleable(
                executionBlock = { prefService.setUserInfo(UserReceive(jwt = "", user = null)) },
                failureBlock = { errorService.showError("Произошла ошибка") },
                completionBlock = { navigator.navigateToMain() }
            )
        }
    }
}

