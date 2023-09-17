//
//  SearchView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchView: View {
    
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<SearchViewModelImpl, SearchState> = ViewModelFactory.getSearchViewModelWrapper()
    
    var body: some View {
        
        let state = viewModelWrapper.state
        
        VStack {
    
            SearchTopBar(
                searchString: state.searchString) { it in
                    viewModelWrapper.viewModel.searchChanged(search: it)
                } onClearText: {
                    viewModelWrapper.viewModel.onDeleteSearchClick()
                }
            
            VStack {
                switch state.productsLoading {
                    
                case LoadingState.Loading():
                    ProgressView()
                        .frame(alignment: .center)
                    
                case LoadingState.Success():
                    SearchLayout(viewModelWrapper: viewModelWrapper)
                    
                    
                case LoadingState.Empty():
                    EmptyView()
//                        .frame(minWidth: .infinity, minHeight: .infinity)
                    
                default:
                    ErrorView(onClick: { viewModelWrapper.viewModel.onRefresh() })
                }
            }
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}
