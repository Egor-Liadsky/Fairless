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
                Text("У вас есть вопрос, предложение или жалоба?\nНапишите нам!")
                    .font(.custom("Qanelas-Semibold", size: 16))
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color.black)
                
                Button {
                    let email = "igeg91999@gmail.com"
                    let url = URL(string: "mailto:\(email)")
                    if UIApplication.shared.canOpenURL(url!) {
                        UIApplication.shared.open(url!)
                    }
                } label: {
                    Text("Написать")
                        .font(.custom("Qanelas-Semibold", size: 16))
                        .foregroundColor(Color.white)
                        .padding(.vertical, 12)
                        .padding(.horizontal, 24)
                        .background(LinearGradient(gradient: Gradient(colors: [.AboutFairless.orangeGradient1, .AboutFairless.orangeGradient2]), startPoint: .trailing, endPoint: .leading))
                        .cornerRadius(5)
                }
                .padding(.top, 20)
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
