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

@MainActor class NavigatorImpl: Navigator, ObservableObject {
    
    static let shared = NavigatorImpl()
    let startDestination = ScreenRoute.main
    
    nonisolated func navigateBack() {
        
    }
    
    nonisolated func navigateToAboutApp() {
        
    }
    
    nonisolated func navigateToAboutFairLess() {
        
    }
    
    nonisolated func navigateToAuth() {
        
    }
    
    nonisolated func navigateToDocument(product: String) {
        
    }
    
    nonisolated func navigateToFaq() {
        
    }
    
    nonisolated func navigateToFeedback() {
        
    }
    
    nonisolated func navigateToMain() {
        
    }
    
    nonisolated func navigateToMenu() {
        
    }
    
    nonisolated func navigateToMessage() {
        
    }
    
    nonisolated func navigateToNotification() {
        
    }
    
    nonisolated func navigateToProfile() {
        
    }
    
    nonisolated func navigateToRules() {
        
    }
    
    nonisolated func navigateToSearch() {
        
    }
    
    nonisolated func navigateToShop(shop_ shop: String) {
        
    }
}
