package com.mobile.fairless.features.mainNavigation.state

import com.mobile.fairless.common.navigation.ScreenRoute

data class MainNavigationState(
    val screenRoute: ScreenRoute,
) {
    companion object {
        fun getInstance(startDestination: ScreenRoute) = MainNavigationState(startDestination)
    }
}
