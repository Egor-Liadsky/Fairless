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
    
    var viewModelWrapper: StatefulViewModelWrapper<MainViewModelImpl, MainState>
    
    var body: some View {
        let data = viewModelWrapper.state.pagingData.data as! [ProductData]
        List {
            ForEach(data, id: \.self) { product in
                ProductView(product: product)
            }
        }
    }
}
