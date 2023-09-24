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
        icon: "ic_home",
        label: "Главная"
    ),
    BottomNavigationItem(
        route: ScreenRoute.search,
        icon: "ic_search",
        label: "Поиск"
    ),
    BottomNavigationItem(
        route: ScreenRoute.menu,
        icon: "ic_menu",
        label: "Меню"
    )
]

struct BottomBar: View {
    
    @Environment(\.viewFactory) var view
    @StateObject var navigator: NavigatorImpl = NavigatorImpl.shared
//    @State var title = MR.strings().shop_title.desc().localized()
    
//    let currentRoute: ScreenRoute
//    let onButtonClick: (ScreenRoute) -> Void
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor(Color.Navigation.tabViewBackground)
        UITabBar.appearance().barTintColor = UIColor(Color(hex: "A7ACAF"))
        UITabBar.appearance().unselectedItemTintColor = UIColor(Color.Navigation.unselectColor)
    }

    
    var body: some View {
        
        Group {
            if #available(iOS 16.0, *) {
                NavigationStack { tabBar }
            }
            else {
                NavigationView { tabBar }
            }
        }
        .environmentObject(navigator)
    }
    
    var tabBar: some View {
        TabView(selection: $navigator.currentTab) {
            ForEach(bottomNavigationItems) { item in
                view.makeView(for: item.route)
                    .tag(item.route)
                    .tabItem {
                        Label(
                            title: {
                                Text(item.label)
                            },
                            icon: {
                                Image(item.icon)
                                    .resizable()
                                    .renderingMode(.template)
                                    .aspectRatio(contentMode: .fit)
                                    .frame(width: 20, height: 20)
                            }
                        )
                    }
            }
        }
        .tint(Color.Navigation.selectColor)
    }
}

