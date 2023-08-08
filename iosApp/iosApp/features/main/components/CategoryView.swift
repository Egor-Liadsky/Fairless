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
    
    var categoryList: [CategoryModel]
    
    
    var body: some View {
        Text("HelloWorld")
    }
    
    var category: some View {
        Text("")
    }
}

struct CategoryItem: View {
    
    let category: CategoryModel
    
    var body: some View {
        Text(category.description())
    }
}

struct CategoryView_Previews: PreviewProvider {
    static var previews: some View {
        CategoryView(categoryList: [])
    }
}
