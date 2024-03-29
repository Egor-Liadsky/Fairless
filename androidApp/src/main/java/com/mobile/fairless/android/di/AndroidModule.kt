package com.mobile.fairless.android.di

import com.mobile.fairless.android.navigation.AndroidNavigator
import com.mobile.fairless.android.navigation.NavigatorImpl
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.navigation.ScreenRoute
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.AboutAppViewModel
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.AboutAppViewModelImpl
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.AboutFairLessViewModel
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.AboutFairLessViewModelImpl
import com.mobile.fairless.features.additional.faq.viewModel.FaqViewModel
import com.mobile.fairless.features.additional.faq.viewModel.FaqViewModelImpl
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.FeedbackViewModel
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.FeedbackViewModelImpl
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.RulesViewModel
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.RulesViewModelImpl
import com.mobile.fairless.features.additional.faq.state.FaqState
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import com.mobile.fairless.features.document.viewModel.DocumentViewModelImpl
import com.mobile.fairless.features.main.state.MainState
import com.mobile.fairless.features.main.viewModel.MainViewModel
import com.mobile.fairless.features.main.viewModel.MainViewModelImpl
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModel
import com.mobile.fairless.features.mainNavigation.viewModel.MainNavigationViewModelImpl
import com.mobile.fairless.features.menu.state.MenuState
import com.mobile.fairless.features.menu.viewModel.MenuViewModel
import com.mobile.fairless.features.menu.viewModel.MenuViewModelImpl
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel
import com.mobile.fairless.features.profile.viewModel.ProfileViewModelImpl
import com.mobile.fairless.features.search.state.SearchState
import com.mobile.fairless.features.search.viewModel.SearchViewModel
import com.mobile.fairless.features.search.viewModel.SearchViewModelImpl
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel
import com.mobile.fairless.features.shop.viewModel.ShopViewModelImpl
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModel
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModelImpl
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModelImpl
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

    viewModel(named("AuthViewModel")) {
        ViewModelWrapper<AuthViewModel>(AuthViewModelImpl(get()))
    }

    viewModel(named("AboutFairLessViewModel")) {
        ViewModelWrapper<AboutFairLessViewModel>(AboutFairLessViewModelImpl(get()))
    }

    viewModel(named("RulesViewModel")) {
        ViewModelWrapper<RulesViewModel>(RulesViewModelImpl(get()))
    }


    viewModel(named("FeedbackViewModel")) {
        ViewModelWrapper<FeedbackViewModel>(FeedbackViewModelImpl(get()))
    }

    viewModel(named("FaqViewModel")) {
        StatefulViewModelWrapper<FaqViewModel, FaqState>(FaqViewModelImpl(get()))
    }

    viewModel(named("AboutAppViewModel")) {
        ViewModelWrapper<AboutAppViewModel>(AboutAppViewModelImpl(get()))
    }

    viewModel(named("RegisterViewModel")) {
        StatefulViewModelWrapper<RegisterViewModel, RegisterState>(RegisterViewModelImpl(get()))
    }

    viewModel(named("SearchViewModel")) {
        StatefulViewModelWrapper<SearchViewModel, SearchState>(SearchViewModelImpl(get()))
    }

    viewModel(named("MainViewModel")) {
        StatefulViewModelWrapper<MainViewModel, MainState>(MainViewModelImpl(get()))
    }

    viewModel(named("ProfileViewModel")) {
        StatefulViewModelWrapper<ProfileViewModel, ProfileState>(ProfileViewModelImpl(get()))
    }

    viewModel(named("ShopViewModel")) {
        StatefulViewModelWrapper<ShopViewModel, ShopState>(ShopViewModelImpl(get()))
    }

    viewModel(named("MenuViewModel")) {
        StatefulViewModelWrapper<MenuViewModel, MenuState>(MenuViewModelImpl(get()))
    }

    viewModel(named("DocumentViewModel")) {
        StatefulViewModelWrapper<DocumentViewModel, DocumentState>(DocumentViewModelImpl(get()))
    }
}
