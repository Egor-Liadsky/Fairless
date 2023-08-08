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
    
    @MainActor static func getMainViewModel() ->
    StatefulViewModelWrapper<MainViewModelImpl, MainState> {
        return StatefulViewModelWrapper<MainViewModelImpl, MainState> (
            state: MainState.companion.getInstance(),
            viewModel: MainViewModelImpl(navigator: NavigatorImpl.shared)
        )
    }
}
