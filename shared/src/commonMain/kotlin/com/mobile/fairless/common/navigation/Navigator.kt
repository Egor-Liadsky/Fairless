package com.mobile.fairless.common.navigation


enum class ScreenRoute(val isMain: Boolean) {
    Main(true),
    Profile(false),
    Notification(false),
    Message(false),
    Menu(false),
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
