//
//  ViewModelFactory.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-07-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct ViewModelFactory {
    
    @MainActor static func getMainViewModelWrapper() ->
    ViewModelWrapper<MainViewModelImpl> {
        return ViewModelWrapper <MainViewModelImpl>(
            viewModel: MainViewModelImpl(
                navigator: NavigatorImpl.shared
            )
        )
    }
}
