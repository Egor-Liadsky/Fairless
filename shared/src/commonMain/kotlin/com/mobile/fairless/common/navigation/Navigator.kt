package com.mobile.fairless.common.navigation

import com.mobile.fairless.features.main.models.Product


enum class ScreenRoute(val isMain: Boolean) {
    //NavBar
    Main(true),
    Notification(true),
    Message(true),
    Menu(true),

    Document(false),
    Profile(false),
    Auth(false),
    Register(false),
    Settings(false),
    ProfileEdit(false)
}

interface Navigator {
    fun navigateBack()

    fun navigateToMain()
    fun navigateToProfile()
    fun navigateToNotification()
    fun navigateToMessage()
    fun navigateToMenu()
    fun navigateToAuth()
    fun navigateToRegister()
    fun navigateToSettings()
    fun navigateToProfileEdit()
    fun navigateToDocument(product: String)
}
