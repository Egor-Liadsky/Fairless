//
//  Navigator.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import Combine

@MainActor class NavigatorImpl: Navigator, ObservableObject {
    
    static let shared: NavigatorImpl = NavigatorImpl(startScreen: .main)
    
    var didSendRequest: AnyPublisher<Void, Never> {
           subject.eraseToAnyPublisher()
       }
    
    private let subject = PassthroughSubject<Void, Never>()
    
    @Environment(\.presentationMode) var presentationMode
    @Published var currentTab: ScreenRoute
    @Published var currentScreen: ScreenRoute
    
    @Published var documentNavigation: DocumentNavigation?
    
    init(startScreen: ScreenRoute){
        self.currentScreen = startScreen
        if (startScreen.isMain){
            self.currentTab = startScreen
        } else{
            self.currentTab = .main
        }
    }
    
    func navigateBack() {
        subject.send()
    }
    
    func navigateToAboutApp() {
        currentScreen = .aboutapp
    }
    
    func navigateToAboutFairLess() {
        currentScreen = .aboutfairless
    }
    
    nonisolated func navigateToAuth() {
        
    }
    
    func navigateToDocument(product: String) {
        currentScreen = .document
        documentNavigation = .init(product: product)
    }
    
    func navigateToFaq() {
        currentScreen = .faq
    }
    
    func navigateToFeedback() {
        currentScreen = .feedback
    }
    
    func navigateToMain() {
        currentScreen = .main
        currentTab = .main
    }
    
    func navigateToMenu() {
        currentScreen = .menu
        currentTab = .menu
    }
    
    nonisolated func navigateToMessage() {
        
    }
    
    nonisolated func navigateToNotification() {
        
    }
    
    nonisolated func navigateToProfile() {
        
    }
    
    func navigateToRules() {
        currentScreen = .rules
    }
    
    func navigateToSearch() {
        currentScreen = .search
        currentTab = .search
    }
    
    nonisolated func navigateToShop(shop_ shop: String) {
        
    }
}
