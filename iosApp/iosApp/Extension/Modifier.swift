//
//  Modifier.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-09.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension View {
    
    @ViewBuilder func `if`<Content: View>(_ condition: Bool, transform: (Self) -> Content) -> some View {
        if condition {
            transform(self)
        } else {
            self
        }
    }
}
