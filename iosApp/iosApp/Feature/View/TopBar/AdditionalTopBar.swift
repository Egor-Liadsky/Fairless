//
//  AdditionalTopBar.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-20.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AdditionalTopBar: View {
    
    @Environment(\.dismiss) var dismiss
    @EnvironmentObject var navigator: NavigatorImpl

    let onBackClick: () -> Void
    let title: String
    
    var body: some View {
        VStack {
            HStack {
                Button {
                    onBackClick()
                } label: {
                    Image("ic_arrow_left")
                        .resizable()
                        .foregroundColor(Color.black)
                        .frame(width: 26, height: 26)
                        .padding(7)
                        .background(Color.white).cornerRadius(5)
                }
                
                Text(title)
                    .font(.custom("Qanelas-Semibold", size: 20))
                    .foregroundColor(Color.black)
                    .padding(.leading, 10)
                
                Spacer()
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 16)
        }
        .onReceive(navigator.didSendRequest) { _ in dismiss() }
        .frame(maxWidth: .infinity)
        .background(Color.AdditionalTopBar.topBarBackground)//.edgesIgnoringSafeArea(.top)
    }
}

struct AdditionalTopBar_Previews: PreviewProvider {
    static var previews: some View {
        AdditionalTopBar(onBackClick: {}, title: "О приложении")
    }
}
