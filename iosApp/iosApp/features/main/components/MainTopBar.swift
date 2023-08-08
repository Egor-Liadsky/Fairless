//
//  MainTopBar.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainTopBar: View {
    
    let categoriesLoading: LoadingState
    let selectCategory: CategoryModel
    let categories: [CategoryModel]
    let categoryClick: (CategoryModel) -> ()
    let selectType: Type
    let types: [Type]
    let typeClick: (Type) -> ()
    
    var body: some View {
        
        VStack (alignment: .leading) {
                        
            Text("Выберите нужную вам категорию")
                .font(.custom("Qanelas-Semibold", size: 24))
                .fontWeight(.semibold)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.horizontal, 16)
                .padding(.top, 16)
            
            switch categoriesLoading {
                
            case LoadingState.Loading():
                ProgressView()
                    .frame(maxWidth: .infinity, alignment: .center)
                
            case LoadingState.Success():
                VStack(spacing: 10) {
                    CategoryView(
                        selectCategory: selectCategory,
                        categories: categories
                    ) { category in
                        categoryClick(category)
                    }
                    
                    TypeView(
                        selectType: selectType,
                        types: types
                    ){ type in
                        typeClick(type)
                    }
                }
                .padding(4)
                
            case LoadingState.Empty():
                Text("Пусто")
                
            default:
                Text("Ошибка")
                
            }
        }
        .frame(maxWidth: .infinity)
    }
}
