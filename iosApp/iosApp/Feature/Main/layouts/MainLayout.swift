//
//  MainLayout.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainLayout: View {
    
    @ObservedObject var viewModelWrapper: StatefulViewModelWrapper<MainViewModelImpl, MainState>
    
    init(viewModelWrapper: StatefulViewModelWrapper<MainViewModelImpl, MainState>){
        self.viewModelWrapper = viewModelWrapper
    }
    
    @Environment(\.viewFactory) var viewFactory
    @EnvironmentObject var navigator: NavigatorImpl
    @State private var isActive = false

    
    var body: some View {        
        
        let products = viewModelWrapper.state.products
        
        ScrollView {
            VStack (spacing: 10) {
                
                ForEach(products.indices, id: \.self) { index in
                    
                    ProductView(product: products[index].product){
                        isActive = true
                        viewModelWrapper.viewModel.onDocumentClick(product: products[index].product.name?.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed) ?? "", ios: true)
                    }
                        .overlay(
                            RoundedRectangle(cornerRadius: 5)
                                .inset(by: 0.25)
                                .stroke(Color.Background.borderGray, lineWidth: 0.5)
                        )
                        .padding(.horizontal, 16)
                    //                        .onAppear {
                    //                            if index == products.count - 2 {
                    //                                viewModelWrapper.viewModel.onAppend()
                    //                            }
                    //                        }
                }
                if (viewModelWrapper.state.pagingData.isAppending) {
                    HStack {
                        Spacer()
                        ProgressView()
                            .padding(.vertical, 10)
                        Spacer()
                    }
                }
            }
            .padding(.vertical, 10)
        }
        .background(
            NavigationLink(
                destination: viewFactory.makeView(for: .document),
                isActive: $isActive,
                label: { EmptyView() }
            )
        )
        .refreshable { viewModelWrapper.viewModel.onRefresh() }
        .onAppear {
            UIRefreshControl.appearance().tintColor = UIColor(Color.orangePrimary)
        }
    }
}
