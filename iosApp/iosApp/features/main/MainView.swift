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
    
        ZStack (alignment: .top) {
            
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
                
                switch viewModelWrapper.state.productsLoading {

                case LoadingState.Loading():
                    ProgressView()
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                        .frame(alignment: .center)

                case LoadingState.Success():
                    Spacer()
                    MainLayout(viewModelWrapper: viewModelWrapper)
                        .frame(maxWidth: .infinity)
                        .background(Color.white)

                case LoadingState.Empty():
                    Text("Empty")
                    
                default:
                    Text("Error")
                }
            }
            .onAppear { viewModelWrapper.viewModel.onViewShown()}
            .onDisappear{ viewModelWrapper.viewModel.onViewHidden() }
        }
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
