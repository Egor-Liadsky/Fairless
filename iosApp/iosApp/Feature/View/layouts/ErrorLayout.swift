//
//  ErrorView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ErrorLayout: View {
    
    let onClick: () -> Void
    
    var body: some View {
        VStack {
            Image("ic_exit")
                .resizable()
                .frame(width: 83, height: 83)
            
            Text("Что-то пошло не так")
                .font(.custom("Qanelas-Semibold", size: 20))
                .foregroundColor(Color.black)
                .padding()
            
            Text("Попробуйте обновить страницу")
                .font(.custom("Qanelas-Regular", size: 16))
                .foregroundColor(Color.black)
            
            Button {
                onClick()
            } label: {
                Text("Повторить")
                    .font(.custom("Qanelas-Semibold", size: 16))
                    .foregroundColor(Color.orangePrimary)
            }
            .padding(.top, 20)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
