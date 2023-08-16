//
//  NavigationView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-14.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainNavigation: View {
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<MainNavigationViewModelImpl, MainNavigationState> = ViewModelFactory.getMainNavigationViewModelWrapper(startDestination: ScreenRoute.main)
    
    @State private var currentTab = ScreenRoute.main
    @State private var resetMain = UUID()
    @State private var resetSearch = UUID()
    @State private var resetMenu = UUID()
    
    var body: some View {
        VStack(spacing: 0) {
            TabView(selection: $currentTab) {
                Group {
                    MainView()
                        .tag(ScreenRoute.main)
                        .id(resetMain)

                    SearchView()
                        .tag(ScreenRoute.search)
                        .id(resetSearch)

                    MenuView()
                        .tag(ScreenRoute.menu)
                        .id(resetMenu)
                }
            }
            
            BottomBar(currentRoute: viewModelWrapper.state.screenRoute) { route in
                viewModelWrapper.viewModel.onBottomBarButtonClick(route: route)
                if currentTab == route {
                    switch route {
                    case .main: resetMain = UUID()
                    case .search: resetSearch = UUID()
                    case .menu: resetMenu = UUID()
                    default:
                        resetMain = UUID()
                    }
                }
                currentTab = route
            }
        }
    }
}
