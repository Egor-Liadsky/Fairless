//
//  AppRouting.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

extension EnvironmentValues {
    
    var appRoutingState: AppRoutingState {
        get {
            return self[AppRoutingState.self]
        }
        set {
            self[AppRoutingState.self] = newValue
        }
    }
}

class AppRoutingState: ObservableObject, EnvironmentKey {
    
    static let defaultValue: AppRoutingState = AppRoutingState()
    
    @Published var currentScreen: ScreenRoute?
}

struct ScreenNavigation: Identifiable, Equatable, Hashable {
    let id: ScreenRoute
}
