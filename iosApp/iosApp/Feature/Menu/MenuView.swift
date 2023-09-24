//
//  MenuView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MenuView: View {
    
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<MenuViewModelImpl, MenuState> = ViewModelFactory.getMenuViewModel()
    
    var body: some View {
        VStack {
            MenuLayout(viewModelWrapper: viewModelWrapper)
        }
    }
}

struct MenuView_Previews: PreviewProvider {
    static var previews: some View {
        MenuView()
    }
}
