//
//  ViewModelFactory.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct ViewModelFactory {
    
    @MainActor static func getMainNavigationViewModelWrapper(startDestination: ScreenRoute) ->
    StatefulViewModelWrapper<MainNavigationViewModelImpl, MainNavigationState> {
        return StatefulViewModelWrapper<MainNavigationViewModelImpl, MainNavigationState>(
            state: MainNavigationState.companion.getInstance(startDestination: startDestination),
            viewModel: MainNavigationViewModelImpl(
                startDestination: startDestination,
                navigator: NavigatorImpl.shared
            )
        )
    }
    
    @MainActor static func getMainViewModelWrapper() ->
    StatefulViewModelWrapper<MainViewModelImpl, MainState> {
        return StatefulViewModelWrapper<MainViewModelImpl, MainState>(
            state: MainState.companion.getInstance(),
            viewModel: MainViewModelImpl(
                navigator: NavigatorImpl.shared
            )
        )
    }
    
    @MainActor static func getDocumentViewModelWrapper() ->
    StatefulViewModelWrapper<DocumentViewModelImpl, DocumentState> {
        return StatefulViewModelWrapper<DocumentViewModelImpl, DocumentState>(
            state: DocumentState.companion.getInstance(),
            viewModel: DocumentViewModelImpl(
                navigator: NavigatorImpl.shared
            )
        )
    }
    
    @MainActor static func getSearchViewModelWrapper() ->
    StatefulViewModelWrapper<SearchViewModelImpl, SearchState> {
        return StatefulViewModelWrapper<SearchViewModelImpl, SearchState>(
            state: SearchState.companion.getInstance(),
            viewModel: SearchViewModelImpl(
                navigator: NavigatorImpl.shared
            )
        )
    }
    
    @MainActor static func getMenuViewModel() ->
    StatefulViewModelWrapper<MenuViewModelImpl, MenuState> {
        return StatefulViewModelWrapper<MenuViewModelImpl, MenuState>(
            state: MenuState.companion.getInstance(),
            viewModel: MenuViewModelImpl(navigator: NavigatorImpl.shared)
        )
    }
    
    @MainActor static func getAboutAppViewModel() ->
    ViewModelWrapper<AboutAppViewModelImpl> {
        return ViewModelWrapper(viewModel: AboutAppViewModelImpl(navigator: NavigatorImpl.shared))
    }
    
    @MainActor static func getRulesViewModel() ->
    ViewModelWrapper<RulesViewModelImpl> {
        return ViewModelWrapper(viewModel: RulesViewModelImpl(navigator: NavigatorImpl.shared))
    }
    
    @MainActor static func getFeedbackViewModel() ->
    ViewModelWrapper<FeedbackViewModelImpl> {
        return ViewModelWrapper(viewModel: FeedbackViewModelImpl(navigator: NavigatorImpl.shared))
    }
    
    @MainActor static func getAboutFairlessViewModel() ->
    ViewModelWrapper<AboutFairLessViewModelImpl> {
        return ViewModelWrapper(viewModel: AboutFairLessViewModelImpl(navigator: NavigatorImpl.shared))
    }
    
    @MainActor static func getFaqViewModel() ->
    StatefulViewModelWrapper<FaqViewModelImpl, FaqState> {
        return StatefulViewModelWrapper<FaqViewModelImpl, FaqState>(
            state: FaqState.companion.getInstance(),
            viewModel: FaqViewModelImpl(navigator: NavigatorImpl.shared)
        )
    }
}
