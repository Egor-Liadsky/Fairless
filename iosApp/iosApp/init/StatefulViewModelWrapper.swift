//
//  StatefulViewModelWrapper.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-07-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

@MainActor class StatefulViewModelWrapper<T: StatefulKmpViewModelImpl<S>, S>: ObservableObject {
    
    @Published var state: S
    let viewModel: T

    init(
        state: S,
        viewModel: T
    ){
        self.state = state
        self.viewModel = viewModel
        viewModel.onChange { newState in
            self.state = newState ?? state
        }
    }
}

