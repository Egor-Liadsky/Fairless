package com.mobile.fairless.android.di

import com.mobile.fairless.android.navigation.AndroidNavigator
import com.mobile.fairless.android.navigation.NavigatorImpl
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModel
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun androidModule() = module {
    single<AndroidNavigator> { (start: ScreenRoute) -> NavigatorImpl(start) }
    single<Navigator> { get<AndroidNavigator>() }

    viewModel(named("MainNavigationViewModel")) { (start: ScreenRoute) ->
        StatefulViewModelWrapper<MainNavigationViewModel, MainNavigationState>(
            MainNavigationViewModelImpl(start, get())
        )
    }

//    viewModel(named("MainViewModel")) {
//        StatefulViewModelWrapper<MainViewModel, MainState>(
//            MainViewModelImpl(get())
//        )
//    }
}
