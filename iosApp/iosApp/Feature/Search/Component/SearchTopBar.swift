//
//  SearchTopBar.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-09.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SearchTopBar: View {
    
    let searchString: String
    let onSearchChange: (String) -> Void
    let onClearText: () -> Void
    
    @State private var searchState = ""
    @State private var isSearching = false
    @State private var voiceText = ""
    @State private var isRecording = false
    
    var body: some View {
        
        ZStack {
            Color.SearchTopBar.topBarBackground.edgesIgnoringSafeArea(.top)
            VStack{
                HStack {
                    Group{
                        if searchState.isEmpty {
                            Image("ic_search")
                                .frame(width: 24, height: 24)
                                .foregroundColor(Color.SearchTopBar.textFieldBackground)
                                .padding(.horizontal, 16)
                        } else {
                            Button {
                                onClearText()
                                searchState = ""
                            } label: {
                                Image("ic_close")
                                    .frame(width: 24, height: 24)
                                    .foregroundColor(Color.gray)
                                    .padding(.horizontal, 16)
                            }
                        }
                        
                        TextField(
                            "", text: $searchState,
                            onEditingChanged: {isEditing in
                                isSearching = true
                            },
                            onCommit: {
                                onSearchChange(searchState)
                                isSearching = false
                            }
                        )
                        .frame(height: 50)
                        .placeholder(when: searchState.isEmpty) {
                            Text("Введите название товара")
                                .foregroundColor(Color.black)
                        }
                        .disableAutocorrection(true)
                        Spacer()
                        
                        Button {
                            
                        } label: {
                            Image("ic_mic")
                                .frame(width: 24, height: 24)
                                .foregroundColor(Color.gray)
                                .padding(.horizontal, 16)
                        }
                    }
                }
                .background(Color.SearchTopBar.textFieldBackground.cornerRadius(16))
                .padding(.horizontal, 16)
                
                HStack {
                    Button {
                        
                    } label: {
                        Text("По популярности")
                    }
                    
                    Spacer()
                    
                    Button {
                        
                    } label: {
                        Text("Фильтры")
                    }
                }
                .padding(.horizontal, 16)
            }
            .padding(.top, 30)
            .padding(.bottom, 10)
        }
        .frame(height: 156)
    }
}
