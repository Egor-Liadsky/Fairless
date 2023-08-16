//
//  BottomBar.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BottomNavigationItem: Identifiable {
    let id = UUID()
    let route: ScreenRoute
    let icon: String
    let label: String
}

let bottomNavigationItems = [
    BottomNavigationItem(
        route: ScreenRoute.main,
        icon: "ic_exit",
        label: "Главная"
    ),
    BottomNavigationItem(
        route: ScreenRoute.search,
        icon: "rubrics",
        label: "Поиск"
    ),
    BottomNavigationItem(
        route: ScreenRoute.menu,
        icon: "search",
        label: "Меню"
    )
]

struct BottomBar: View {
    
    let currentRoute: ScreenRoute
    let onButtonClick: (ScreenRoute) -> Void
    
    var body: some View {
        
        TabView {
            HStack {
                ForEach(bottomNavigationItems) { item in
                    Button {
                        onButtonClick(item.route)
                    } label: {
                        VStack {
                            Image(item.icon)
                                .resizable()
                                .frame(width: 24, height: 24)
                            Text(item.label)
                        }
                    }
                }
            }
        }
    }
}

