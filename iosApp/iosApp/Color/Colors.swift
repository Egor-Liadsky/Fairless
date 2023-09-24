//
//  Color.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-10.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension Color {
    
    public static let orangePrimary = Color(hex: "E74017")
    public static let white = Color(hex: "FFFFFF")
    public static let black = Color(hex: "2A2F33")
    
    struct Rating {
        public static let ratingOrange1 = Color(hex: "F28C16")
        public static let ratingOrange2 = Color(hex: "F07E14")
        public static let ratingOrange3 = Color(hex: "EE7013")
        public static let ratingOrange4 = Color(hex: "EB6116")
        public static let ratingOrange5 = Color(hex: "E95116")
        public static let ratingOrange6 = Color(hex: "E74017")
    }
    
    struct Background {
        public static let topBar = Color(hex: "F4F6F7")
        public static let borderGray = Color(hex: "E1E1E1")
    }
    
    struct SearchTopBar {
        public static let topBarBackground = Color(hex: "F4F6F7")
        public static let textFieldBackground = Color(hex: "FFFFFF")
        public static let placeholder = Color(hex: "979797")
        public static let buttonColor = Color(hex: "979797")
        public static let filters = Color(hex: "979797")
    }
    
    struct AdditionalTopBar {
        public static let topBarBackground = Color(hex: "F1F3F5")
    }
    
    struct Navigation {
        public static let tabViewBackground = Color(hex: "F1F3F5")
        public static let selectColor = Color(hex: "F73B00")
        public static let unselectColor = Color(hex: "A8ADB0")
    }
    
    struct Menu {
        
        struct Account {
            public static let border = Color(hex: "F1F3F5")
        }
        
        struct Test {
            public static let orangeGradient1 = Color(hex: "F73000")
            public static let orangeGradient2 = Color(hex: "FD7A00")
        }
        
        struct Additional {
            public static let nextButton = Color("F73300")
            public static let square = Color("A8ACAF")
        }
    }
}
