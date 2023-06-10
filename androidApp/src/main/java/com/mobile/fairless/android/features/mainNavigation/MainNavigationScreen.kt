package com.mobile.fairless.android.features.mainNavigation

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.additional.AboutApp.AboutAppScreen
import com.mobile.fairless.android.features.additional.AboutFairLess.AboutFairLess
import com.mobile.fairless.android.features.additional.Faq.FaqScreen
import com.mobile.fairless.android.features.additional.Feedback.FeedbackScreen
import com.mobile.fairless.android.features.additional.Rules.RulesScreen
import com.mobile.fairless.android.features.document.DocumentScreen
import com.mobile.fairless.android.features.main.MainScreen
import com.mobile.fairless.android.features.menu.MenuScreen
import com.mobile.fairless.android.features.message.MessageScreen
import com.mobile.fairless.android.features.notification.NotificationScreen
import com.mobile.fairless.android.features.profile.ProfileScreen
import com.mobile.fairless.android.features.search.SearchScreen
import com.mobile.fairless.android.features.views.snackbar.DefaultSnackbar
import com.mobile.fairless.android.features.welcome.auth.AuthScreen
import com.mobile.fairless.android.features.welcome.register.RegisterScreen
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Composable
fun MainNavigationScreen(
    navController: NavHostController = rememberNavController(),
    startDestination: ScreenRoute,
    errorService: ErrorService = get(),
    viewModelWrapper: StatefulViewModelWrapper<MainNavigationViewModel, MainNavigationState> =
        getViewModel(qualifier = named("MainNavigationViewModel")) { parametersOf(startDestination) },
) {
    val backStackState = navController.currentBackStackEntryAsState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val currentRoute = backStackState.value
        ?.destination?.route ?: startDestination.name

    Log.e("currentScreenTAGTAG", currentRoute)

    DisposableEffect(key1 = viewModelWrapper) {
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    }

    LaunchedEffect(key1 = Unit) {
        errorService.state.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(it)
        }
    }

    val context = LocalContext.current
    val activity = LocalContext.current as? Activity

    val doubleBackPressed = remember {
        mutableStateOf(false)
    }

    BackHandler(navController.previousBackStackEntry == null) {
        if (doubleBackPressed.value) {
            activity?.finish()
        }
        doubleBackPressed.value = true
        Toast.makeText(context, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackPressed.value = false
        }, 3000)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            DefaultSnackbar(snackbarHostState = it)
        },
        bottomBar = {
            val isMainScreen = bottomNavigationItems.any { it.route.name == currentRoute }
            if (isMainScreen)
                BottomBar(ScreenRoute.valueOf(currentRoute)) { route: ScreenRoute ->
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
            composable(route = ScreenRoute.Search.name){
                SearchScreen()
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
            composable(route = ScreenRoute.Auth.name) {
                AuthScreen()
            }
            composable(route = ScreenRoute.Register.name) {
                RegisterScreen()
            }
            composable(route = ScreenRoute.AboutFairLess.name){
                AboutFairLess()
            }
            composable(route = ScreenRoute.Rules.name){
                RulesScreen()
            }
            composable(route = ScreenRoute.Faq.name){
                FaqScreen()
            }
            composable(route = ScreenRoute.Feedback.name){
                FeedbackScreen()
            }
            composable(route = ScreenRoute.AboutApp.name){
                AboutAppScreen()
            }
            composable(
                route = "${ScreenRoute.Document.name}/{product}",
                arguments = listOf(navArgument("product") { type = NavType.StringType })
            ) {
                val product = it.arguments?.getString("product")
                DocumentScreen(product = product ?: "")
            }
        }
    }
}
