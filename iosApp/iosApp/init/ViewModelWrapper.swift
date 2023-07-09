//
//  ViewModelWrapper.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-07-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class ViewModelWrapper<T: KmpViewModel>: ObservableObject {
    let viewModel: T
    
    init (viewModel: T) {
        self.viewModel = viewModel
        viewModel.onCleared()
    }
}
