package com.mobile.fairless.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mobile.fairless.android.features.mainNavigation.MainNavigationScreen
import com.mobile.fairless.android.navigation.AndroidNavigator
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.common.storage.PrefService
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    private val prefService: PrefService by inject()

    private val startDestination: ScreenRoute = ScreenRoute.Main
    private val rootNavigation: AndroidNavigator by inject { parametersOf(startDestination) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.FirelessTheme)
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
}
