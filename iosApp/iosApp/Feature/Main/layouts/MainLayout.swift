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
    
    var body: some View {        
        
        let products = viewModelWrapper.state.products
        
        ScrollView {
            
            VStack (spacing: 10) {
                
                ForEach(products.indices, id: \.self) { index in
                    
                    ProductView(product: products[index].product)
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
    }
}
