package com.mobile.fairless.android.navigation

import androidx.navigation.NavHostController
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.navigation.ScreenRoute

interface AndroidNavigator : Navigator {
    fun init(navHostController: NavHostController)
}

class NavigatorImpl(
    startDestination: ScreenRoute,
) : AndroidNavigator {
    private var currentMainStack = startDestination
    private lateinit var navController: NavHostController

    override fun init(navHostController: NavHostController) {
        navController = navHostController
    }

    override fun navigateBack() {
        navController.navigateUp()
    }

    override fun navigateToMain() {
        navigateToNavBarDestination(ScreenRoute.Main)
    }

    override fun navigateToProfile() {
        navController.navigate(ScreenRoute.Profile.name)
    }

    override fun navigateToNotification() {
        navigateToNavBarDestination(ScreenRoute.Notification)
    }

    override fun navigateToMessage() {
        navigateToNavBarDestination(ScreenRoute.Message)
    }

    override fun navigateToMenu() {
        navigateToNavBarDestination(ScreenRoute.Menu)
    }

    override fun navigateToWelcome() {
        navigateToNavBarDestination(ScreenRoute.Welcome)
    }

    override fun navigateToAuth() {
        navigateToNavBarDestination(ScreenRoute.Auth)
    }

    override fun navigateToRegister() {
        navigateToNavBarDestination(ScreenRoute.Register)
    }

    override fun navigateToSettings() {
        navController.navigate(ScreenRoute.Settings.name)
    }

    override fun navigateToProfileEdit() {
        navController.navigate(ScreenRoute.ProfileEdit.name)
    }

    private fun navigateToNavBarDestination(root: ScreenRoute) {
        navController.navigate(root.name) {
            popUpTo(navController.graph.id) {
                if (root != currentMainStack) saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }.also { currentMainStack = root }
    }
}
