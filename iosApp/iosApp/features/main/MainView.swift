//
//  MainView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-07.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainView: View {
    
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<MainViewModelImpl, MainState> = ViewModelFactory.getMainViewModel()
    
    var body: some View {
        
        ZStack {
            
            Color(hex: "F4F6F7").edgesIgnoringSafeArea(.all)
            VStack {
                MainTopBar()
                VStack {
                        Text(viewModelWrapper.state.categories?.description ?? "wasd")
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Color.white)
            }
        }
        .onAppear {
            viewModelWrapper.viewModel.onViewShown()
        }
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}

struct MainTopBar: View {
    
    var body: some View {
        
        VStack (alignment: .leading) {
            Text("Выберите нужную вам категорию")
                .font(.custom("Qanelas-Semibold", size: 24))
                .fontWeight(.semibold)
                .frame(maxWidth: .infinity, alignment: .leading)
            
            
        }
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 16)
    }
}
