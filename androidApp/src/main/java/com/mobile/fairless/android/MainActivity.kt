package com.mobile.fairless.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mobile.fairless.android.features.mainNavigation.MainNavigationScreen
import com.mobile.fairless.android.navigation.AndroidNavigator
import com.mobile.fairless.common.navigation.ScreenRoute
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    private val startDestination: ScreenRoute = defineStartDestination()
    private val rootNavigation: AndroidNavigator by inject { parametersOf(startDestination) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            rootNavigation.init(navController)
            MyApplicationTheme {
                MainNavigationScreen(
                    navController,
                    startDestination
                )
            }
        }
    }

    private fun defineStartDestination(): ScreenRoute {
//        if (state.value.user?.user != null)
            return ScreenRoute.Main
//        else
//            return ScreenRoute.Welcome
    }
}
