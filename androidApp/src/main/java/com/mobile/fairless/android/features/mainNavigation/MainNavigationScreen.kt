package com.mobile.fairless.android.features.mainNavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.main.MainScreen
import com.mobile.fairless.android.features.menu.MenuScreen
import com.mobile.fairless.android.features.message.MessageScreen
import com.mobile.fairless.android.features.notification.NotificationScreen
import com.mobile.fairless.android.features.profile.ProfileScreen
import com.mobile.fairless.android.features.welcome.auth.AuthScreen
import com.mobile.fairless.android.features.welcome.register.RegisterScreen
import com.mobile.fairless.android.features.welcome.welcome.WelcomeScreen
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Composable
fun MainNavigationScreen(
    navController: NavHostController = rememberNavController(),
    startDestination: ScreenRoute,
    viewModelWrapper: StatefulViewModelWrapper<MainNavigationViewModel, MainNavigationState> =
        getViewModel(qualifier = named("MainNavigationViewModel")) { parametersOf(startDestination) },
) {
    val backStackState = navController.currentBackStackEntryAsState()
    val currentRoute = backStackState.value
        ?.destination?.route?.substringBefore("/") ?: startDestination

    DisposableEffect(key1 = viewModelWrapper) {
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    }

    Scaffold(
        bottomBar = {
            val isMainScreen = bottomNavigationItems.any { it.route.name == currentRoute }
            if (isMainScreen)
                BottomBar(ScreenRoute.valueOf(currentRoute.toString())) { route: ScreenRoute ->
                    viewModelWrapper.viewModel.onBottomBarButtonClick(route)
                }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = ScreenRoute.Main.name) {
                MainScreen()
            }
            composable(route = ScreenRoute.Profile.name) {
                ProfileScreen()
            }
            composable(route = ScreenRoute.Notification.name) {
                NotificationScreen()
            }
            composable(route = ScreenRoute.Message.name) {
                MessageScreen()
            }
            composable(route = ScreenRoute.Menu.name) {
                MenuScreen()
            }
            composable(route = ScreenRoute.Welcome.name) {
                WelcomeScreen()
            }
            composable(route = ScreenRoute.Auth.name) {
                AuthScreen()
            }
            composable(route = ScreenRoute.Register.name) {
                RegisterScreen()
            }
        }
    }
}
