//
//  FeedbackView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-24.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FeedbackView: View {
    
    @StateObject var viewModelWrapper: ViewModelWrapper<FeedbackViewModelImpl> = ViewModelFactory.getFeedbackViewModel()
    
    var body: some View {
        VStack {
            AdditionalTopBar(
                onBackClick: { viewModelWrapper.viewModel.onBackButtonClick() },
                title: "Обратная связь"
            )
            VStack(alignment: .center) {
                Image("ic_logo")
                    .resizable()
                    .frame(width: 146, height: 103)
                
                Text("Версия 1.0")
                    .font(.custom("Qanelas-Semibold", size: 12))
                    .foregroundColor(Color.black)
                    .padding(.top, 30)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct FeedbackView_Previews: PreviewProvider {
    static var previews: some View {
        FeedbackView()
    }
}
