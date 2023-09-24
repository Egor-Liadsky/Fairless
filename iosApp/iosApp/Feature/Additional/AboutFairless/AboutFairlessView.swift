//
//  AboutFairlessView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-24.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutFairlessView: View {
    
    @StateObject var viewModelWrapper: ViewModelWrapper<AboutFairLessViewModelImpl> = ViewModelFactory.getAboutFairlessViewModel()
    
    var body: some View {
        VStack {
            AdditionalTopBar(
                onBackClick: { viewModelWrapper.viewModel.onBackButtonClick() },
                title: "О нас"
            )
            VStack(alignment: .center) {
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct AboutFairlessView_Previews: PreviewProvider {
    static var previews: some View {
        AboutFairlessView()
    }
}
