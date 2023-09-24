//
//  ViewFactory.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

extension EnvironmentValues {
    var viewFactory: ViewFactory {
        get {
            return self[ViewFactory.self]
        }
        set {
            self[ViewFactory.self] = newValue
        }
    }
}

final class ViewFactory: EnvironmentKey {
    
    static let defaultValue: ViewFactory = ViewFactory()
    
    @ViewBuilder func makeView(for screenRoute: ScreenRoute) -> some View {
        switch screenRoute {
        case .main: MainView()
        case .menu: MenuView()
        case .search: SearchView()
        case .aboutapp: AboutAppView()
        case .aboutfairless: AboutFairlessView()
        case .rules: RulesView()
        case .feedback: FeedbackView()
        case .faq: FaqView()
        default:
            Text("error navigation")
        }
    }
}
