//
//  ViewFactory.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ViewFactory: EnvironmentKey {
    
    @Environment(\.appRoutingState) var appRoutingState: AppRoutingState
    
    static let defaultValue: ViewFactory = ViewFactory()
        
    private var cachedViews = [ScreenRoute:AnyView]()

    private func getCachedView(for screenIdentifier: ScreenRoute) -> AnyView? {
        cachedViews[screenIdentifier]
    }
    
    @ViewBuilder func makeView(for screenRoute: ScreenRoute) -> some View {
        switch screenRoute {
        case .main: MainView()
        case .menu: MenuView()
        default:
            Text("error navigation")
        }
    }
    
    private mutating func cachedView(for screenRoute: ScreenRoute) -> AnyView {
            if let cachedView = cachedViews[screenRoute] {
                return cachedView
            } else {
                let newView = AnyView(makeView(for: screenRoute))
                cachedViews[screenRoute] = newView
                return newView
            }
        }
}
