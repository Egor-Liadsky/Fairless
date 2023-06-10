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

    override fun navigateToSearch() {
        navigateToNavBarDestination(ScreenRoute.Search)
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

    override fun navigateToAuth() {
        navController.navigate(ScreenRoute.Auth.name)
    }

    override fun navigateToRegister() {
        navController.navigate(ScreenRoute.Register.name)
    }

    override fun navigateToDocument(product: String) {
        navController.navigate("${ScreenRoute.Document.name}/$product")
    }

    override fun navigateToAboutFairLess() {
        navController.navigate(ScreenRoute.AboutFairLess.name)
    }

    override fun navigateToRules() {
        navController.navigate(ScreenRoute.Rules.name)
    }

    override fun navigateToFeedback() {
        navController.navigate(ScreenRoute.Feedback.name)
    }

    override fun navigateToFaq() {
        navController.navigate(ScreenRoute.Faq.name)
    }

    override fun navigateToAboutApp() {
        navController.navigate(ScreenRoute.AboutApp.name)
    }

    private fun navigateToNavBarDestination(root: ScreenRoute) {
        navController.navigate(root.name) {
            popUpTo(navController.graph.id) {
                saveState = root.isMain
            }
            launchSingleTop = root.isMain
            restoreState = root.isMain
        }.also { currentMainStack = root }
    }
}
