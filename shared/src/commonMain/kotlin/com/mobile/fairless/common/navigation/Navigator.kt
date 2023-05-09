package com.mobile.fairless.common.navigation


enum class ScreenRoute(val isMain: Boolean) {
    //NavBar
    Main(true),
    Notification(true),
    Message(true),
    Menu(true),

    Profile(false),
    Welcome(false),
    Auth(false),
    Register(false),
    Settings(false)
}

interface Navigator {
    fun navigateBack()

    fun navigateToMain()
    fun navigateToProfile()
    fun navigateToNotification()
    fun navigateToMessage()
    fun navigateToMenu()
    fun navigateToWelcome()
    fun navigateToAuth()
    fun navigateToRegister()
    fun navigateToSettings()
}
