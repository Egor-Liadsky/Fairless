//
//  TestView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TestView: View {
    var body: some View {
        Button {
            
        } label: {
            VStack {
                HStack {
                    VStack {
                        Spacer()
                        Image("ic_fire")
                            .resizable()
                            .frame(width: 39, height: 45)
                            .padding(.leading, 10)
                    }
                    
                    Text("Пройти тест!")
                        .font(.custom("Qanelas-Heavy", size: 20))
                        .foregroundColor(Color.white)
                        .padding(.leading, 33)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
            }
            .frame(height: 70)
            .frame(maxWidth: .infinity)
            .background(LinearGradient(gradient: Gradient(colors: [.Menu.Test.orangeGradient1, .Menu.Test.orangeGradient2]), startPoint: .leading, endPoint: .trailing))
            .cornerRadius(5)
        }
    }
}
