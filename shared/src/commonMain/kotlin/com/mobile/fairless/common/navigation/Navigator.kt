package com.mobile.fairless.common.navigation


enum class ScreenRoute(val isMain: Boolean) {
    Main(true),
    Profile(false),
    Notification(false),
    Message(false),
    Menu(false)
}

interface Navigator {
    fun navigateBack()

    fun navigateToMain()
    fun navigateToProfile()
    fun navigateToNotification()
    fun navigateToMessage()
    fun navigateToMenu()
}
