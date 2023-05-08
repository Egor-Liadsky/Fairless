package com.mobile.fairless.features.mainNavigation.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import com.mobile.fairless.features.welcome.dto.UserReceive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface MainNavigationViewModel : StatefulKmpViewModel<MainNavigationState> {
    val startDestination: ScreenRoute
    override val state: StateFlow<MainNavigationState>
    fun onRouteChange(route: ScreenRoute)
    fun onBottomBarButtonClick(route: ScreenRoute)
}

class MainNavigationViewModelImpl(
    override val startDestination: ScreenRoute,
    private val navigator: Navigator,
) : KoinComponent, StatefulKmpViewModelImpl<MainNavigationState>(), MainNavigationViewModel {

    private val mutableState = MutableStateFlow(
        MainNavigationState(startDestination)
    )
    override val state: StateFlow<MainNavigationState>
        get() = mutableState

    override fun onRouteChange(route: ScreenRoute) {
        if (route.isMain) mutableState.update { it.copy(screenRoute = route) }
    }

    override fun onBottomBarButtonClick(route: ScreenRoute) {
        mutableState.update { it.copy(screenRoute = route) }
        when (route) {
            ScreenRoute.Main -> navigator.navigateToMain()
            ScreenRoute.Notification -> navigator.navigateToNotification()
            ScreenRoute.Message -> navigator.navigateToMessage()
            ScreenRoute.Menu -> navigator.navigateToMenu()
            else -> {}
        }
    }
}
