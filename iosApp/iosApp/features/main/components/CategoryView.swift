//
//  CategoryView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-07.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryView: View {
    
    var selectCategory: CategoryModel
    var categories: [CategoryModel]
    var categoryClick: (CategoryModel) -> ()
    
    var body: some View {
        ScrollView (.horizontal, showsIndicators: false) {
            HStack (spacing: 10) {
                Spacer(minLength: 6)
                ForEach(categories, id: \.self) { category in
                    CategoryItem(selectCategory: selectCategory, category: category){category in
                        categoryClick(category)
                    }
                }
                Spacer(minLength: 6)
            }
        }
    }
}

struct CategoryItem: View {

    var selectCategory: CategoryModel
    let category: CategoryModel
    var categoryClick: (CategoryModel) -> ()

    var body: some View {
        
        let foregroundColor = selectCategory.url == category.url ? Color.white : Color.black
        let backgrondColor = LinearGradient(gradient: Gradient(colors: selectCategory.url == category.url ? [Color(hex: "F73000"), Color(hex: "FD7A00")] : [Color.white]), startPoint: .leading, endPoint: .trailing)
        
        Button {
            categoryClick(category)
        } label: {
            Text(category.name ?? "")
                .font(.custom("Qanelas-Light", size: 16))
                .fontWeight(.regular)
                .padding(.horizontal, 18)
                .padding(.vertical, 13)
                .foregroundColor(foregroundColor)
        }
        .background(backgrondColor.cornerRadius(25))
    }
}
