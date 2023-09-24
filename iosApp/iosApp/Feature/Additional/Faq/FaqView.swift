//
//  FaqView.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-09-24.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FaqInfo: Identifiable {
    let id: Int
    let title: String
    let faqs: [FaqItemInfo]
}

struct FaqItemInfo: Identifiable {
     
    let id: Int
    let title: String
    let description: String
    let expanded: Bool
    let onClick: () -> Void
}

struct FaqView: View {
    
    @StateObject var viewModelWrapper: StatefulViewModelWrapper<FaqViewModelImpl, FaqState> = ViewModelFactory.getFaqViewModel()
    
    var body: some View {
        
        let state = viewModelWrapper.state
        
        let faqInfoList = [
            FaqInfo(
                id: 1,
                title: "Общая информация о FAIRLESS",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "Что такое Fairless.ru?",
                        description: "Fairless.ru – это онлайн-сервис, который предлагает актуальные скидки, промокоды и специальные предложения от различных онлайн и офлайн магазинов. Наша цель – помочь пользователям экономить на покупках, предоставляя самую свежую информацию о доступных скидках.",
                        expanded: state.common_info1,
                        onClick: { viewModelWrapper.viewModel.common_info1() }
                    ),
                    
                    FaqItemInfo(
                        id: 2,
                        title: "Как я могу зарегистрироваться на сайте Fairless.ru?",
                        description: "Чтобы зарегистрироваться на сайте, воспользуйтесь кнопкой \"Авторизация\" и следуйте предложенным инструкциям. После регистрации вы сможете участвовать в обсуждениях и делиться скидками и промокодами, которые вы нашли.",
                        expanded: state.common_info2,
                        onClick: { viewModelWrapper.viewModel.common_info2() }
                    )
                ]
            ),
            
            FaqInfo(
                id: 2,
                title: "Публикация скидок и промокодов",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "Как я могу опубликовать скидку или промокод?",
                        description: "После регистрации на сайте вы можете нажать на кнопку \"Добавить\" и, следуя инструкциям, опубликовать скидку или промокод.",
                        expanded: state.publish_info1,
                        onClick: { viewModelWrapper.viewModel.publish_info1() }),
                    
                    FaqItemInfo(
                        id: 2,
                        title: "Что такое \"Огненные предложения\"?",
                        description: "\"Огненные предложения\" – это наиболее популярные скидки и промокоды, выбранные нашим сообществом. Вы можете просмотреть \"Огненные предложения\" за сегодня, неделю или месяц.",
                        expanded: state.publish_info2,
                        onClick: { viewModelWrapper.viewModel.publish_info2() })
                ]
            ),
            
            FaqInfo(
                id: 3,
                title: "Конфиденциальность и безопасность",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "Как вы обрабатываете мои данные?",
                        description: "Мы уважаем вашу конфиденциальность и строго соблюдаем все правила и законы, касающиеся обработки и хранения персональных данных. Более подробную информацию о том, как мы обрабатываем ваши данные, вы можете найти в нашей Политике конфиденциальности.",
                        expanded: state.conf_info1,
                        onClick: { viewModelWrapper.viewModel.conf_info1() }
                    ),
                    
                    FaqItemInfo(
                        id: 2,
                        title: "Как я могу связаться с вами?",
                        description: "Если у вас есть вопросы или предложения, вы можете связаться с нами через форму обратной связи на сайте или через наши страницы в социальных сетях.",
                        expanded: state.conf_info2,
                        onClick: { viewModelWrapper.viewModel.conf_info2() }
                    ),
                ]
            ),
            
            FaqInfo(
                id: 4,
                title: "Поиск и навигация",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "Как я могу найти купоны и скидки для определенного магазина?",
                        description: "Вы можете воспользоваться функцией поиска на нашем сайте, введя название магазина в соответствующее поле. Также вы можете просмотреть все предложения в выбранной категории, нажав на интересующую вас категорию в меню сайта.",
                        expanded: state.search_info1,
                        onClick: { viewModelWrapper.viewModel.search_info1() }
                    ),
                    
                    FaqItemInfo(
                        id: 2,
                        title: "Как работают фильтры на сайте?",
                        description: "Фильтры на сайте позволяют отсортировать и отобрать предложения по различным параметрам, таким как тип предложения (скидки, промокоды), магазин и т.д. Это помогает быстро найти нужный вам промокод, скидку или купон.",
                        expanded: state.search_info2,
                        onClick: { viewModelWrapper.viewModel.search_info2() }
                    ),
                    
                    FaqItemInfo(
                        id: 3,
                        title: "Как мне быстрее найти наиболее подходящие предложения?",
                        description: "Вы можете воспользоваться функцией фильтрации, доступной на нашем сайте. Фильтры позволяют вам сортировать и отбирать предложения по различным параметрам: по типу предложения (скидки, промокоды), по магазину и так далее. Это помогает быстрее находить наиболее подходящие для вас предложения.",
                        expanded: state.search_info3,
                        onClick: { viewModelWrapper.viewModel.search_info3() }
                    )
                ]
            ),
            
            FaqInfo(
                id: 5,
                title: "Участие в сообществе",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "Как я могу участвовать в обсуждениях на сайте?",
                        description: "После успешной регистрации на сайте вы сможете оставлять свои комментарии под предложениями и обсуждать скидки с другими пользователями. Мы очень ценим активное участие наших пользователей в жизни сообщества!",
                        expanded: state.participation_info1,
                        onClick: { viewModelWrapper.viewModel.participation_info1() }
                    ),
                    
                    FaqItemInfo(
                        id: 2,
                        title: "Что такое \"Огненные предложения\"?",
                        description: "\"Огненные предложения\" – это наиболее актуальные и выгодные скидки и промокоды, выбранные нашим сообществом. Это отличный способ быстро найти наиболее интересные и выгодные предложения.",
                        expanded: state.participation_info2,
                        onClick: { viewModelWrapper.viewModel.participation_info2() }
                    )
                ]
            ),
            
            FaqInfo(
                id: 6,
                title: "Мобильное приложение",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "У вас есть мобильное приложение?",
                        description: "На данный момент мы используем Android платформу для взаимодействия с пользователями, также в разработке находится IOS приложение, о релизе мы сообщим в официальных источниках. Однако вы всегда можете использовать браузер вашего мобильного устройства для доступа к нашему сайту.",
                        expanded: state.mobile_info,
                        onClick: { viewModelWrapper.viewModel.mobile_info() }
                    )
                ]
            ),
            
            FaqInfo(
                id: 7,
                title: "Проблемы и поддержка",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "Что делать, если я столкнулся с проблемой на сайте?",
                        description: "Если у вас возникли проблемы при использовании нашего сайта, пожалуйста, свяжитесь с нашей службой поддержки через форму обратной связи на сайте.",
                        expanded: state.throwable_info1,
                        onClick: { viewModelWrapper.viewModel.throwable_info1() }),
                    
                    FaqItemInfo(
                        id: 2,
                        title: "Я обнаружил неверную информацию или неработающий купон. Как мне сообщить об этом?",
                        description: "Если вы обнаружили неверную информацию или неработающий купон, сообщите нам об этом через форму обратной связи на сайте. Мы постараемся исправить ошибку в кратчайшие сроки.",
                        expanded: state.throwable_info2,
                        onClick: { viewModelWrapper.viewModel.throwable_info2() })
                ]
            ),
            
            FaqInfo(
                id: 8,
                title: "Социальные сети",
                faqs: [
                    FaqItemInfo(
                        id: 1,
                        title: "Есть ли у вас страницы в социальных сетях?",
                        description: "Да, мы активно ведем страницы в социальных сетях. Присоединяйтесь к нам, чтобы быть в курсе последних новостей и получать эксклюзивные предложения.",
                        expanded: state.soc_info1,
                        onClick: { viewModelWrapper.viewModel.soc_info1() }
                    ),
                    
                    FaqItemInfo(
                        id: 2,
                        title: "Могу ли я поделиться купоном в социальных сетях?",
                        description: "Да, мы предлагаем простой и удобный способ поделиться любым купоном или промокодом в социальных сетях. Просто нажмите на соответствующую кнопку поделиться на странице купона или промокода.",
                        expanded: state.soc_info1,
                        onClick: { viewModelWrapper.viewModel.soc_info1() }
                    )
                ]
            )
        ]
        
        VStack {
            AdditionalTopBar(
                onBackClick: { viewModelWrapper.viewModel.onBackButtonClick() },
                title: "FAQ. Ответы на часто задаваемые вопросы"
            )
            
            ScrollView {
                VStack(alignment: .leading) {
                    
                    ForEach(faqInfoList, id: \.id) { faq in
                        
                        Text(faq.title)
                            .font(.custom("Qanelas-Semibold", size: 16))
                            .foregroundColor(Color.black)
                            .padding(.top, 20)
                        
                        ForEach(faq.faqs, id: \.id) { faqItem in
                            FaqItem(
                                title: faqItem.title,
                                expanded: faqItem.expanded,
                                onClick: { faqItem.onClick() }
                            )
                                .padding(.top, 16)
                            if faqItem.expanded {
                                Text(faqItem.description)
                                    .font(.custom("Qanelas-Regular", size: 12))
                                    .foregroundColor(Color.black)
                                    .padding(.horizontal, 16)
                            }
                        }
                    }
                }
                .padding(.bottom, 20)
                .padding(.horizontal, 16)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct FaqItem: View {
    
    let title: String
    let expanded: Bool
    let onClick: () -> Void
    
    var body: some View {
        
        Button {
            onClick()
        } label: {
            HStack {
                Text(expanded ? "-" : "+")
                    .font(.custom("Qanelas-Semibold", size: 14))
                    .foregroundColor(Color.orangePrimary)
                
                Text(title)
                    .font(.custom("Qanelas-Semibold", size: 14))
                    .foregroundColor(Color.black)
                    .multilineTextAlignment(.leading)
                    .padding(.leading, 15)
                Spacer()
            }
            .frame(maxWidth: .infinity)
            .padding(.vertical, 24)
            .padding(.horizontal, 16)
            .overlay {
                RoundedRectangle(cornerRadius: 10)
                    .stroke(Color.FAQ.faqItemBorder, lineWidth: 1)
            }
        }
    }
}

struct FaqView_Previews: PreviewProvider {
    static var previews: some View {
        FaqView()
    }
}
