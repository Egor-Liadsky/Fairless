//
//  TypeView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TypeView: View {
    
    var selectType: Type
    var types: [Type]
    var typeClick: (Type) -> ()
    
    var body: some View {
        ScrollView (.horizontal, showsIndicators: false) {
            HStack (spacing: 10) {
                Spacer(minLength: 6)
                ForEach(types, id: \.self) { type in
                    TypeItem(selectType: selectType, type: type) {
                        typeClick($0)
                    }
                }
                Spacer(minLength: 6)
            }
        }
    }
}

struct TypeItem: View {

    var selectType: Type
    let type: Type
    var typeClick: (Type) -> ()

    var body: some View {
        
        let isSelectType = selectType.type == type.type
        let foregroundColor = isSelectType ? Color.white : Color.black
        let backgrondColor = LinearGradient(gradient: Gradient(colors: isSelectType ? [Color(hex: "F73000"), Color(hex: "FD7A00")] : [Color.white]), startPoint: .leading, endPoint: .trailing)
        
        Button {
            typeClick(type)
        } label: {
            Text(type.title)
                .font(.custom("Qanelas-Light", size: 16))
                .fontWeight(.regular)
                .padding(.horizontal, 18)
                .padding(.vertical, 13)
                .foregroundColor(foregroundColor)
        }
        .background(backgrondColor.cornerRadius(25))
    }
}
