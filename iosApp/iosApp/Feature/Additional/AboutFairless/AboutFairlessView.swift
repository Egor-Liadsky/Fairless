//
//  AboutFairlessView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-24.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutFairlessView: View {
    
    @StateObject var viewModelWrapper: ViewModelWrapper<AboutFairLessViewModelImpl> = ViewModelFactory.getAboutFairlessViewModel()
    
    var body: some View {
        VStack {
            AdditionalTopBar(
                onBackClick: { viewModelWrapper.viewModel.onBackButtonClick() },
                title: "О нашем сообществе"
            )
            ScrollView {
                VStack {
                    
                    VStack {
                        HStack {
                            Text("Центр всех скидок и промокодов онлайн и оффлайн магазинов")
                                .font(.custom("Qanelas-Semibold", size: 16))
                                .foregroundColor(Color.white)
                            
                            Spacer()
                        }
                        .frame(maxWidth: .infinity)
                        
                        HStack {
                            Text("Fair Less был основан в 2017 и является частью независимой группы и крупнейшего в мире сообщества любителей скидок. Здесь вы не увидите ни спама, ни рекламы, а только настоящие скидки, добавленные реальными людьми вроде вас")
                                .font(.custom("Qanelas-Regular", size: 12))
                                .foregroundColor(Color.white)
                                .padding(.top, 6)
                            
                            Spacer()
                        }
                        .frame(maxWidth: .infinity)
                        
                    }
                    .padding(.horizontal, 16)
                    .padding(.vertical, 30)
                    .background(LinearGradient(gradient: Gradient(colors: [.AboutFairless.orangeGradient1, .AboutFairless.orangeGradient2]), startPoint: .top, endPoint: .bottom))
                    .cornerRadius(5)
                    .padding(.horizontal, 16)
                    .padding(.top, 20)
                    
                    
                    VStack (alignment: .leading) {
                        Text("История")
                            .font(.custom("Qanelas-Semibold", size: 16))
                            .foregroundColor(Color.black)
                        
                        Text("Всё начиналось в не столь далёком 2017 году, когда основатель сообщества «Халявщики» создал телеграм-канал и начал размещать там скидки и акции, которые он находил интересными для подписчиков. Понемногу канал начал расти, люди делились крутыми скидками со своими друзьями. На тот момент даже 10 тыс. подписчиков казалось чем-то недостижимым, и мы не могли предположить, что со временем у нас будет два крупнейших Телеграм-канала, посвящённых скидкам и халяве, с суммарным числом подписчиков почти 300 тыс. Но ограничиваться только телеграм-каналом мы не хотели, была поставлена цель, создать такую платформу, где каждый человек сможет поделиться своими находками, скидками и промокодами с другими людьми, участвовать в обсуждении скидок, оценивать их, делать обзоры на товары и т.д. Мы хотели создать полноценное сообщество любителей скидок, большую семью, где каждый помогает друг другу экономить на своих покупках. По-моему, у нас получилось.")
                            .font(.custom("Qanelas-Regular", size: 12))
                            .foregroundColor(Color.black)
                            .padding(.top, 6)
                    }
                    .padding(.horizontal, 16)
                    .padding(.top, 30)
                    
                    Spacer()
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct AboutFairlessView_Previews: PreviewProvider {
    static var previews: some View {
        AboutFairlessView()
    }
}
