//
//  SearchTopBar.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-09.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Speech
import shared
struct SearchTopBar: View {
    
    @ObservedObject var viewModelWrapper: StatefulViewModelWrapper<SearchViewModelImpl, SearchState>
    
    @State private var searchState = ""
    @State private var isSearching = false
    @State private var voiceText = ""
    @State private var isRecording = false
    
    @ObservedObject var speechRec = SpeechRec()
    
    var body: some View {
        let state = viewModelWrapper.state
        
        let sortList = [
            PopularFilter(title: "По популярности", sort: Sort.likes),
            PopularFilter(title: "По просмотрам", sort: Sort.views),
            PopularFilter(title: "По дате создания", sort: Sort.create),
            PopularFilter(title: "По возрастанию цены", sort: Sort.saleAscending),
            PopularFilter(title: "По убыванию цены", sort: Sort.saleDescending),
        ]
        
        let filterList = [
            Type(title: "Промокоды и скидки", type: ProductStockType.all),
            Type(title: "Только скидки", type: ProductStockType.sale),
            Type(title: "Только промокоды", type: ProductStockType.promocode),
            Type(title: "Бесплатно", type: ProductStockType.free)
        ]
        
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
                                viewModelWrapper.viewModel.onDeleteSearchClick()
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
                                viewModelWrapper.viewModel.searchChanged(search: searchState)
                                isSearching = false
                            }
                        )
                        .foregroundColor(Color.black)
                        .font(.custom("Qanelas-Regular", size: 16))
                        .frame(height: 50)
                        .placeholder(when: searchState.isEmpty) {
                            Text("Введите название товара")
                                .font(.custom("Qanelas-Regular", size: 16))
                                .foregroundColor(Color.SearchTopBar.placeholder)
                        }
                        .disableAutocorrection(true)
                        .lineLimit(1)
                        .keyboardType(.webSearch)
                        Spacer()
                        
                        Button {
                            isRecording.toggle()
                            if isRecording {
                                speechRec.start()
                                searchState = speechRec.recognizedText
                            } else {
                                searchState = speechRec.recognizedText == "" ? "" : speechRec.recognizedText
                                speechRec.stop()
                                if speechRec.recognizedText != "" {
                                    viewModelWrapper.viewModel.searchChanged(search: searchState)
                                }
                                UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
                            }
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
                    HStack {
                        Image("ic_sort")
                            .resizable()
                            .frame(width: 24, height: 24)
                            .foregroundColor(Color(hex: "979797"))
                        
                        Menu(state.selectedPopularFilter.title) {
                            ForEach(sortList, id: \.self){ item in
                                Button {
                                    viewModelWrapper.viewModel.selectPopularFilter(popularFilter: item)
                                } label: {
                                    Text(item.title)
                                }
                            }
                        }
                        .font(.custom("Qanelas-Regular", size: 12))
                        .foregroundColor(Color.SearchTopBar.filters)
                        .padding(.leading, 5)
                    }
                    
                    Spacer()
                    
                    HStack {
                        Image("ic_filter")
                            .resizable()
                            .frame(width: 24, height: 24)
                            .foregroundColor(Color(hex: "979797"))
                        Menu("Фильтры") {
                            ForEach(filterList, id: \.self){ item in
                                Button {
                                    viewModelWrapper.viewModel.selectType(type: item)
                                } label: {
                                    Text(item.title)
                                }
                            }
                        }
                        .font(.custom("Qanelas-Regular", size: 12))
                        .foregroundColor(Color.SearchTopBar.filters)
                        .padding(.leading, 5)
                    }
                }
                .padding(.horizontal, 16)
                .padding(.vertical, 12)
            }
            .padding(.top, 30)
        }
        .frame(height: 156)
    }
}

class SpeechRec: ObservableObject {
    
    @Published private(set) var recognizedText = ""
    
    
    let speechRecognizer = SFSpeechRecognizer(locale: Locale(identifier: "ru-RU"))
    var recognitionRequest: SFSpeechAudioBufferRecognitionRequest?
    var recognitionTask: SFSpeechRecognitionTask?
    let audioEngine = AVAudioEngine()
    
    func start() {
        self.recognizedText = ""
        SFSpeechRecognizer.requestAuthorization { status in
            self.startRecognition()
        }
    }
    
    func startRecognition() {
        do {
            recognitionRequest = SFSpeechAudioBufferRecognitionRequest()
            guard let recognitionRequest = recognitionRequest else { return }
            
            recognitionTask = speechRecognizer?.recognitionTask(with: recognitionRequest) { result, error in
                if let result = result {
                    self.recognizedText = result.bestTranscription.formattedString
                }
            }
            
            let recordingFormat = audioEngine.inputNode.outputFormat(forBus: 0)
            audioEngine.inputNode.installTap(onBus: 0, bufferSize: 1024, format: recordingFormat) { buffer, _ in
                recognitionRequest.append(buffer)
            }
            
            audioEngine.prepare()
            try audioEngine.start()
        }
        
        catch {
            
        }
    }
    
    
    func stop() {
        SFSpeechRecognizer.requestAuthorization { status in
            self.stopRecording()
        }
    }
    
    func stopRecording() {
        audioEngine.stop()
        audioEngine.inputNode.removeTap(onBus: 0)
        recognitionRequest?.endAudio()
        recognitionTask?.cancel()
        recognitionTask = nil
        recognitionRequest = nil
    }
}
