//
//  DocumentView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-24.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DocumentView: View {
    
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<DocumentViewModelImpl, DocumentState> = ViewModelFactory.getDocumentViewModelWrapper()
    
    @EnvironmentObject var navigator: NavigatorImpl
    
    var body: some View {
        VStack {

            DocumentInternalView(
                viewModelWrapper: ViewModelFactory.getDocumentViewModelWrapper()
            )
        }
        .onAppear {
            viewModelWrapper.viewModel.getNameProduct(productName: navigator.documentNavigation?.product.removingPercentEncoding ?? "", ios: true)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct DocumentInternalView: View {
    
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<DocumentViewModelImpl, DocumentState>
    
    @EnvironmentObject var navigator: NavigatorImpl
    @Environment(\.dismiss) var dismiss
    @Environment(\.viewFactory) var viewFactory
    
    var body: some View {
        
        let state = viewModelWrapper.state
        VStack{
            AsyncImage(url: URL(string: .base_url + (state.product.image?.formats?.thumbnail?.url ?? ""))) { image in
                image
                    .resizable()
                    .scaledToFit()
                    .frame(width: 90, height: 120)
                    .scaledToFit()
            } placeholder: {
                ShimmerBox(width: 90, height: 120)
                    .cornerRadius(16)
            }
            Text(state.product.name)
            Text(state.product.description_)
        }
        .onAppear { viewModelWrapper.viewModel.onViewShown() }
        .onDisappear { viewModelWrapper.viewModel.onViewHidden() }
    }
}

struct DocumentView_Previews: PreviewProvider {
    static var previews: some View {
        DocumentView()
    }
}
