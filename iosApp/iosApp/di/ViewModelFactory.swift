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
}
