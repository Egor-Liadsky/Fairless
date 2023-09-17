//
//  SearchEmptyLayout.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-17.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SearchEmptyLayout: View {
    var body: some View {
        VStack {
            Image("ic_enter_query_search")
                .resizable()
                .foregroundColor(Color(hex: "A7ACAF"))
                .frame(width: 83, height: 83)
            
            Text("Введите запрос для поиска")
                .font(.custom("Qanelas-Semibold", size: 20))
                .multilineTextAlignment(.center)
                .foregroundColor(Color(hex: "A7ACAF"))
                .padding()
        }
    }
}

struct SearchEmptyLayout_Previews: PreviewProvider {
    static var previews: some View {
        SearchEmptyLayout()
    }
}
