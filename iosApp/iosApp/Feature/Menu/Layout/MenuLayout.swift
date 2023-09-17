//
//  MenuLayout.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-16.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MenuLayout: View {
    var body: some View {
        VStack {
            ScrollView {
                ProfileView()
                    .padding(EdgeInsets(top: 20, leading: 16, bottom: 0, trailing: 16))
                
                TestView()
                    .padding(EdgeInsets(top: 20, leading: 16, bottom: 0, trailing: 16))
                
                AdditionalView()
                    .padding(EdgeInsets(top: 20, leading: 16, bottom: 0, trailing: 16))

                Spacer()
            }
        }
    }
}

struct MenuLayout_Previews: PreviewProvider {
    static var previews: some View {
        MenuLayout()
    }
}
