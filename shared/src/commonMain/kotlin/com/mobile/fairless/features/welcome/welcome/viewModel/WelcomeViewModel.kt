package com.mobile.fairless.features.welcome.welcome.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import org.koin.core.component.KoinComponent

interface WelcomeViewModel : KmpViewModel, SubScreenViewModel {
    fun registerClick()
    fun authClick()
}

class WelcomeViewModelImpl(
    override val navigator: Navigator
): KoinComponent, KmpViewModelImpl(), WelcomeViewModel {

    override fun registerClick() {
        navigator.navigateToRegister()
    }

    override fun authClick() {
        navigator.navigateToAuth()
    }
}
