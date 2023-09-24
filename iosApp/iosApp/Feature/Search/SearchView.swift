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
            SearchTopBar(viewModelWrapper: viewModelWrapper)
            
            VStack {
                switch state.productsLoading {
                    
                case LoadingState.Loading():
                    ProgressView()
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                        .frame(alignment: .center)
                    
                case LoadingState.Success():
                    SearchLayout(viewModelWrapper: viewModelWrapper)
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                    
                case LoadingState.Empty():
                    if state.searchString.isEmpty {
                        SearchEmptyLayout()
                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                    } else {
                        EmptyLayout()
                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                    }
                    
                default:
                    ErrorLayout(onClick: { viewModelWrapper.viewModel.onRefresh() })
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
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
