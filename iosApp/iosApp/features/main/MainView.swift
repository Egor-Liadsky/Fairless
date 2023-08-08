//
//  MainView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-07.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainView: View {
    
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<MainViewModelImpl, MainState> = ViewModelFactory.getMainViewModel()
    
    var body: some View {
        
        let state = viewModelWrapper.state
        let statePaging = viewModelWrapper.state.pagingData
        
        ZStack {
            Color(hex: "F4F6F7").edgesIgnoringSafeArea(.all)
            
            VStack {
                MainTopBar(
                    categoriesLoading: state.categoriesLoading,
                    selectCategory: state.selectCategory,
                    categories: state.categories ?? [],
                    categoryClick: { category in viewModelWrapper.viewModel.selectCategory(category: category)
                    },
                    selectType: state.selectType,
                    types: state.types,
                    typeClick: { type in
                        viewModelWrapper.viewModel.selectType(type: type)
                    }
                )
                VStack {
//                    switch state.pagingData.loadingState {
//
//                    case LoadingState.Loading():
//                        ProgressView()
//
//                    case LoadingState.Success():
//                        MainLayout(viewModelWrapper: viewModelWrapper)
//
//                    case LoadingState.Empty():
//                        Text("Empty")
//
//                    default:
//                        Text("Error")
//                    }
                    MainLayout(viewModelWrapper: viewModelWrapper)
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Color.white)
            }
            .onAppear {
                viewModelWrapper.viewModel.onViewShown()
                print(statePaging.data.description)
            }
        }
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}