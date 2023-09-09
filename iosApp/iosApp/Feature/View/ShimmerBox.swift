//
//  ShimmerBox.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-09.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import SkeletonUI

struct ShimmerBox: View {
    
    let width: Int
    let height: Int
    let fillMaxWidth: Bool
    
    init(width: Int = 0, height: Int, fillMaxWidth: Bool = false) {
        self.width = width
        self.height = height
        self.fillMaxWidth = fillMaxWidth
    }
    
    var body: some View {
        VStack{
            Rectangle()
                .skeleton(
                    with: true,
                    animation: .pulse(speed: 2.0),
                    appearance: .solid(color: Color.white.opacity(0.1), background: Color.black.opacity(0.1)),
                    shape: .rectangle
                )
                .cornerRadius(10)
        }
        .frame(height: CGFloat(height))
        .frame(maxWidth: .infinity)
        .if(!fillMaxWidth, transform: { view in
            view.frame(width: CGFloat(width))
        })
    }
}

