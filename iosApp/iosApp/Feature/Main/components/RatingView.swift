//
//  RatingView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-10.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RatingView: View {
    
    let boxSize: CGFloat = 10
    let likes: String
    let dislikes: String
    
    var body: some View {
        let rating: Int = Int(calculateAverage(likes: Int(likes) ?? 0, dislikes: Int(dislikes) ?? 0))
        
        let colors: [Color] = [
            .Rating.ratingOrange1,
            .Rating.ratingOrange2,
            .Rating.ratingOrange3,
            .Rating.ratingOrange4,
            .Rating.ratingOrange5,
            .Rating.ratingOrange6
        ]
        
        HStack(
            alignment: .center,
            spacing: 1
        ) {
            ForEach(0..<colors.count, id: \.self) { index in
                HStack() {
                    Rectangle()
                        .fill(index < rating ? colors[index] : Color.clear)
                        .frame(width: boxSize, height: boxSize)
                        .cornerRadius(boxSize == 10 ? 1 : 2)
                }
                .padding(.horizontal, boxSize == 10 ? 0.1 : 0.5)
                .padding(.vertical, 1)
            }
        }
        .padding(.horizontal, 1)
        .border(Color.black, width: 0.2)
        .cornerRadius(2)
    }
}

func calculateAverage(likes: Int, dislikes: Int) -> Double {
    let totalVotes = likes + dislikes
    if totalVotes != 0 {
        return Double(likes) / (Double(likes) + Double(dislikes)) * 6.0
    } else {
        return 0.0
    }
}
