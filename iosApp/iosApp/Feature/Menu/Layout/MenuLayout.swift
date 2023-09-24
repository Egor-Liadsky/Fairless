//
//  MenuLayout.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MenuLayout: View {
    
    let viewModelWrapper: StatefulViewModelWrapper<MenuViewModelImpl, MenuState>
    
    @EnvironmentObject var navigator: NavigatorImpl
    @Environment(\.viewFactory) var viewFactory
    @State private var isActive: Bool = false

    
    var body: some View {
        ZStack {
            VStack {
                ScrollView {
                    ProfileView()
                        .padding(EdgeInsets(top: 20, leading: 16, bottom: 0, trailing: 16))
                    
                    TestView()
                        .padding(EdgeInsets(top: 20, leading: 16, bottom: 0, trailing: 16))
                    
                    AdditionalView(viewModelWrapper: viewModelWrapper, action: { isActive = true })
                        .padding(EdgeInsets(top: 20, leading: 16, bottom: 0, trailing: 16))

                    Spacer()
                }
            }
        }
        .background(
            NavigationLink(
                destination: viewFactory.makeView(for: navigator.currentScreen),
                isActive: $isActive,
                label: { EmptyView() }
            )
        )
    }
}

