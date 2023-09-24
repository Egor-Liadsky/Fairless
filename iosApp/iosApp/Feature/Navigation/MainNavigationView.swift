//
//  NavigationView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-14.
//  Copyright © 2023 orgName. All rights reserved.
//


import SwiftUI
import shared

struct MainNavigationView: View {
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<MainNavigationViewModelImpl, MainNavigationState> = ViewModelFactory.getMainNavigationViewModelWrapper(startDestination: ScreenRoute.main)
    
    var body: some View {
        BottomBar()
            .onAppear { viewModelWrapper.viewModel.onViewShown() }
            .onDisappear { viewModelWrapper.viewModel.onViewHidden() }
    }
}
