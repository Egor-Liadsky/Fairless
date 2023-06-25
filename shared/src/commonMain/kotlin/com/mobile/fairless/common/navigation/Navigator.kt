package com.mobile.fairless.common.navigation


enum class ScreenRoute(val isMain: Boolean) {
    //NavBar
    Main(true),
    Notification(true),
    Message(true),
    Menu(true),
    Search(true),

    Shop(false),
    Document(false),
    Profile(false),
    Auth(false),
    Register(false),
    AboutFairLess(false),
    Rules(false),
    Feedback(false),
    Faq(false),
    AboutApp(false)
}

interface Navigator {
    fun navigateBack()

    fun navigateToMain()
    fun navigateToSearch()
    fun navigateToProfile()
    fun navigateToNotification()
    fun navigateToMessage()
    fun navigateToMenu()
    fun navigateToAuth()
    fun navigateToRegister()
    fun navigateToDocument(product: String)
    fun navigateToAboutFairLess()
    fun navigateToRules()
    fun navigateToFeedback()
    fun navigateToFaq()
    fun navigateToAboutApp()
    fun navigateToShop()
}
