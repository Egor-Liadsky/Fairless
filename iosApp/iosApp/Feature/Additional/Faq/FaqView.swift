//
//  FaqView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-24.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FaqView: View {
    
    @StateObject var viewModelWrapper: ViewModelWrapper<FaqViewModelImpl> = ViewModelFactory.getFaqViewModel()
    
    var body: some View {
        VStack {
            AdditionalTopBar(
                onBackClick: { viewModelWrapper.viewModel.onBackButtonClick() },
                title: "FAQ. Ответы на часто задаваемые вопросы"
            )
            VStack(alignment: .center) {
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct FaqView_Previews: PreviewProvider {
    static var previews: some View {
        FaqView()
    }
}
