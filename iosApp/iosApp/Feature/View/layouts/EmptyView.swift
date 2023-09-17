//
//  EmptyView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct EmptyView: View {
    var body: some View {
        VStack {
            Image("ic_empty")
                .resizable()
                .frame(width: 83, height: 110)
            
            Text("По вашему запросу ничего\nне найдено")
                .font(.custom("Qanelas-Semibold", size: 20))
                .multilineTextAlignment(.center)
                .foregroundColor(Color(hex: "A7ACAF"))
                .padding()
        }
    }
}

struct EmptyView_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
