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
        let productsState = viewModelWrapper.state.products
                
        if let products = productsState {
            ScrollView {
                VStack (spacing: 10) {
                    ForEach(products, id: \.self) { product in
                        ProductView(product: product.product)
                            .overlay(
                                RoundedRectangle(cornerRadius: 5)
                                    .inset(by: 0.25)
                                    .stroke(Color(red: 0.88, green: 0.88, blue: 0.88), lineWidth: 0.5)
                            )
                            .padding(.horizontal, 16)
                    }
                }
                .padding(.vertical, 10)
            }
        }
    }
}
