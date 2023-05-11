package com.mobile.fairless.features.menu.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.welcome.dto.UserReceive
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MenuViewModel : KmpViewModel, SubScreenViewModel {

    fun navigateToProfile()
    fun navigateToSettings()
    fun exitUser()
}

class MenuViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    MenuViewModel {

    private val prefService: PrefService by inject()
    private val errorService: ErrorService by inject()

    override fun navigateToProfile() {
        navigator.navigateToProfile()
    }

    override fun navigateToSettings() {
        navigator.navigateToSettings()
    }

    override fun exitUser() {
        scope.launch {
            exceptionHandleable(
                executionBlock = { prefService.setUserInfo(UserReceive(jwt = "", user = null)) },
                failureBlock = { errorService.showError("Произошла ошибка") },
                completionBlock = { navigator.navigateToWelcome() }
            )
        }
    }
}

