//
//  AccountView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ProfileView: View {
    var body: some View {
        Button {
            
        } label: {
            VStack{
                HStack {
                    Image("avatarka")
                        .resizable()
                        .frame(width: 60, height: 60)
                        .clipShape(Circle())
                    
                    VStack {
                        Text("Иван Иванов")
                            .font(.custom("Qanelas-Heavy", size: 20))
                            .foregroundColor(Color.black)
                            .frame(maxWidth: .infinity, alignment: .leading)
                        Text("На сервисе с 10.09.2020")
                            .font(.custom("Qanelas-Semibold", size: 12))
                            .foregroundColor(Color.black)
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    .padding(.leading, 16)
                    
                    Spacer()
                    
                    Button {
                        
                    } label: {
                        Image("ic_next")
                            .resizable()
                            .frame(width: 6, height: 12)
                    }
                }
                .padding()
            }
            .overlay(
                   RoundedRectangle(cornerRadius: 5)
                    .stroke(Color.Menu.Account.border, lineWidth: 2)
               )
        }
    }
}
