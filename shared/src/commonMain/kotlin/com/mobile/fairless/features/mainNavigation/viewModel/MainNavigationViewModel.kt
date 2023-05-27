package com.mobile.fairless.features.mainNavigation.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent

interface MainNavigationViewModel : StatefulKmpViewModel<MainNavigationState> {
    val startDestination: ScreenRoute
    override val state: StateFlow<MainNavigationState>
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

    override fun onBottomBarButtonClick(route: ScreenRoute) {
        mutableState.update { it.copy(screenRoute = route) }
        when (route) {
            ScreenRoute.Main -> navigator.navigateToMain()
            ScreenRoute.Profile -> navigator.navigateToProfile()
            ScreenRoute.Notification -> navigator.navigateToNotification()
            ScreenRoute.Message -> navigator.navigateToMessage()
            ScreenRoute.Menu -> navigator.navigateToMenu()
            else -> {}
        }
    }
}
