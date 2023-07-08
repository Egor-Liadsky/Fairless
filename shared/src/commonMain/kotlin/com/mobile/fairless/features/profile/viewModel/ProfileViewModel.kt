package com.mobile.fairless.features.profile.viewModel

import com.mobile.fairless.common.analytics.appmetrica.AppMetricaService
import com.mobile.fairless.common.analytics.appmetrica.LogEvent
import com.mobile.fairless.common.analytics.appmetrica.LogEventParam
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.profile.service.ProfileService
import com.mobile.fairless.features.profile.state.ProfileButton
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.models.UserReceive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ProfileViewModel : StatefulKmpViewModel<ProfileState>, SubScreenViewModel {

    fun getProfile()
    fun navigateToMain()
    fun selectButton(profileButton: ProfileButton)
    fun onDeleteSearchClick()
    fun searchChanged(search: String)
    fun cityChanged(city: City)
    fun getCities()
    fun exitUser()
    fun openExitAlertDialog()
}

class ProfileViewModelImpl(override val navigator: Navigator) : StatefulKmpViewModelImpl<ProfileState>(), KoinComponent,
    ProfileViewModel {

    private val prefService: PrefService by inject()
    private val errorService: ErrorService by inject()
    private val profileService: ProfileService by inject()
    private val appMetricaService: AppMetricaService by inject()

    private val _state = MutableStateFlow(ProfileState())
    override val state: StateFlow<ProfileState> = _state.asStateFlow()

    override fun onViewShown() {
        super.onViewShown()
        appMetricaService.sendEvent(
            LogEvent.OPEN_SCREEN, mapOf(
                LogEventParam.SCREEN_NAME to "Профиль",
                LogEventParam.SCREEN_CLASS to "ProfileScreen",
            )
        )
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

    override fun onDeleteSearchClick() {
        searchChanged("")
    }

    override fun searchChanged(search: String) {
        _state.update { it.copy(search = search) }
    }

    override fun cityChanged(city: City) {
        _state.update { it.copy(city = city) }
    }

    override fun getCities() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    if (_state.value.cities == null) {
                        _state.update { it.copy(cities = profileService.getCities()) }
                    }
                    _state.update { it.copy(isLoadingCity = LoadingState.Success) }
                },
                failureBlock = {
                    errorService.showError("Ошибка при получении данных")
                    _state.update { it.copy(isLoadingCity = LoadingState.Error(it.toString())) }
                },
            )
        }
    }

    override fun exitUser() {
        scope.launch {
            exceptionHandleable(
                executionBlock = { prefService.setUserInfo(UserReceive(jwt = "", user = null)) },
                failureBlock = { errorService.showError("Произошла ошибка") },
            )
        }
    }

    override fun openExitAlertDialog() {
        _state.update { it.copy(exitAlertDialogIsOpen = !it.exitAlertDialogIsOpen) }
    }
}
