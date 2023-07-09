//
//  Navigator.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-07-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import shared

@MainActor class NavigatorImpl: Navigator, ObservableObject {
    
    static let shared = NavigatorImpl()
    
    let startDestination = ScreenRoute.main
    
    func navigateBack() {
        // TODO
    }
    
    func navigateToAboutApp() {
        // TODO
    }
    
    func navigateToAboutFairLess() {
        // TODO
    }
    
    func navigateToAuth() {
        // TODO
    }
    
    func navigateToDocument(product: String) {
        // TODO
    }
    
    func navigateToFaq() {
        // TODO
    }
    
    func navigateToFeedback() {
        // TODO
    }
    
    func navigateToMain() {
        // TODO
    }
    
    func navigateToMenu() {
        // TODO
    }
    
    func navigateToMessage() {
        // TODO
    }
    
    func navigateToNotification() {
        // TODO
    }
    
    func navigateToProfile() {
        // TODO
    }
    
    func navigateToRules() {
        // TODO
    }
    
    func navigateToSearch() {
        // TODO
    }
    
    func navigateToShop(shop_ shop: String) {
        // TODO
    }
}
