package com.mobile.fairless.features.mainNavigation.state

import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.features.welcome.dto.UserReceive

data class MainNavigationState(
    val screenRoute: ScreenRoute,
    val user: UserReceive? = null
) {
    companion object {
        fun getInstance(startDestination: ScreenRoute) = MainNavigationState(startDestination)
    }
}
