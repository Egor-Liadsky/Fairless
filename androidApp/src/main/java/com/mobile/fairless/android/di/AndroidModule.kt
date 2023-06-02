package com.mobile.fairless.android.di

import com.mobile.fairless.android.navigation.AndroidNavigator
import com.mobile.fairless.android.navigation.NavigatorImpl
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import com.mobile.fairless.features.document.viewModel.DocumentViewModelImpl
import com.mobile.fairless.features.main.viewModel.MainViewModel
import com.mobile.fairless.features.main.viewModel.MainViewModelImpl
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModel
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModelImpl
import com.mobile.fairless.features.menu.state.MenuState
import com.mobile.fairless.features.menu.viewModel.MenuViewModel
import com.mobile.fairless.features.menu.viewModel.MenuViewModelImpl
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel
import com.mobile.fairless.features.profile.viewModel.ProfileViewModelImpl
import com.mobile.fairless.features.profileEdit.viewModel.ProfileEditViewModel
import com.mobile.fairless.features.profileEdit.viewModel.ProfileEditViewModelImpl
import com.mobile.fairless.features.search.state.SearchState
import com.mobile.fairless.features.search.viewModel.SearchViewModel
import com.mobile.fairless.features.search.viewModel.SearchViewModelImpl
import com.mobile.fairless.features.settings.viewModel.SettingsViewModel
import com.mobile.fairless.features.settings.viewModel.SettingsViewModelImpl
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModel
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModelImpl
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModelImpl
import com.mobile.fairless.features.welcome.welcome.viewModel.WelcomeViewModel
import com.mobile.fairless.features.welcome.welcome.viewModel.WelcomeViewModelImpl
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

    viewModel(named("WelcomeViewModel")) {
        ViewModelWrapper<WelcomeViewModel>(WelcomeViewModelImpl(get()))
    }

    viewModel(named("AuthViewModel")) {
        ViewModelWrapper<AuthViewModel>(AuthViewModelImpl(get()))
    }

    viewModel(named("RegisterViewModel")) {
        StatefulViewModelWrapper<RegisterViewModel, RegisterState>(RegisterViewModelImpl(get()))
    }

    viewModel(named("SearchViewModel")) {
        StatefulViewModelWrapper<SearchViewModel, SearchState>(SearchViewModelImpl(get()))
    }

    viewModel(named("MainViewModel")) {
        ViewModelWrapper<MainViewModel>(MainViewModelImpl(get()))
    }

    viewModel(named("ProfileViewModel")) {
        ViewModelWrapper<ProfileViewModel>(ProfileViewModelImpl(get()))
    }

    viewModel(named("SettingsViewModel")) {
        ViewModelWrapper<SettingsViewModel>(SettingsViewModelImpl(get()))
    }

    viewModel(named("MenuViewModel")) {
        StatefulViewModelWrapper<MenuViewModel, MenuState>(MenuViewModelImpl(get()))
    }

    viewModel(named("ProfileEditViewModel")) {
        ViewModelWrapper<ProfileEditViewModel>(ProfileEditViewModelImpl(get()))
    }
    viewModel(named("DocumentViewModel")) {
        ViewModelWrapper<DocumentViewModel>(DocumentViewModelImpl(get()))
    }
}
