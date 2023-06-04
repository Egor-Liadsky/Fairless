package com.mobile.fairless.common.navigation


enum class ScreenRoute(val isMain: Boolean) {
    //NavBar
    Main(true),
    Notification(true),
    Message(true),
    Menu(true),
    Search(true),

    Document(false),
    Profile(false),
    Auth(false),
    Register(false),
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
}
