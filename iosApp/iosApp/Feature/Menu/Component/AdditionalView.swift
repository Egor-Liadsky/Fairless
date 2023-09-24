//
//  AdditionalView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-17.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AdditionalInfo: Identifiable {
    
    let id: Int
    let title: String
    let onClick: () -> Void
}

struct AdditionalView: View {
    
    let viewModelWrapper: StatefulViewModelWrapper<MenuViewModelImpl, MenuState>
    let action: () -> Void
    
    var body: some View {
        
        let additionalList = [
            AdditionalInfo(id: 1, title: "О нас", onClick: {
                viewModelWrapper.viewModel.navigateToAboutFairLess()
                action()
            }),
            AdditionalInfo(id: 2, title: "Правила поведения", onClick: {
                viewModelWrapper.viewModel.navigateToRules()
                action()
            }),
            AdditionalInfo(id: 3, title: "Обратная связь", onClick: {
                viewModelWrapper.viewModel.navigateToFeedback()
                action()
            }),
            AdditionalInfo(id: 4, title: "FAQ", onClick: {
                viewModelWrapper.viewModel.navigateToFaq()
                action()
            }),
            AdditionalInfo(id: 5, title: "О приложении", onClick: {
                viewModelWrapper.viewModel.navigateToAboutApp()
                action()
            })
        ]
        
        VStack {
            
            VStack {
                HStack {
                    Text("Дополнительно")
                        .font(.custom("Qanelas-Semibold", size: 20))
                        .foregroundColor(Color.black)
                    Spacer()
                }
                
                VStack {
                    ForEach(additionalList, id: \.id) { additional in
                        AdditionalItem(title: additional.title, onClick: additional.onClick)
                    }
                }
                .padding(.top, 20)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 20)
        }
        .overlay(
               RoundedRectangle(cornerRadius: 5)
                .stroke(Color.Menu.Account.border, lineWidth: 1)
           )
    }
}

struct AdditionalItem : View {
    
    let title: String
    let onClick: () -> Void
    
    var body: some View {
        Button {
            onClick()
        } label: {
            HStack {
                Image("ic_square")
                    .resizable()
                    .foregroundColor(Color(hex: "A8ACAF"))
                    .frame(width: 18, height: 20)
                
                Text(title)
                    .font(.custom("Qanelas-Semibold", size: 16))
                    .foregroundColor(Color.black)
                    .padding(.leading, 18)
                
                Spacer()
                
                Image("ic_next")
                    .resizable()
                    .foregroundColor(Color(hex: "F73300"))
                    .frame(width: 5, height: 11)
            }
        }
    }
}
