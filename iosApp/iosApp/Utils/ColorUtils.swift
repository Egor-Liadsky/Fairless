//
//  Utils.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-07-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ColorUtils {
 
    private func getColorFromHex(hex: Int64)->Color{
        let opacity = Double((hex & 0xff000000) >> 24) / 255.0
        let red = Double((hex & 0xff0000) >> 16) / 255.0
        let green = Double((hex & 0xff00) >> 8) / 255.0
        let blue = Double((hex & 0xff) >> 0) / 255.0
        return Color(.sRGB, red: red, green: green, blue: blue, opacity: opacity)
    }
    
}
