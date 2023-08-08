//
//  DocumentItem.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProductView: View {
    
    let product: ProductData
    
    var body: some View {
        Text(product.name ?? "")
    }
}
