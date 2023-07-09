//
//  MainView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-07-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainView: View {
    
//    @StateObject var viewModelWrapper: ViewModelWrapper<MainViewModelImpl> = ViewModelFactory.getMainViewModelWrapper()
    
    var body: some View {
        Text("Hello, Main!")
    }
    
    struct StatefulMainLayout: View {
//        @ObservedObject var viewModelWrapper: ViewModelWrapper<MainViewModelImpl> = ViewModelFactory.getMainViewModelWrapper()
        
        var body: some View {
            Text("asd")
        }
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
