package com.mobile.fairless.features.menu.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import org.koin.core.component.KoinComponent

interface MenuViewModel : KmpViewModel, SubScreenViewModel {

    fun navigateToProfile()
    fun navigateToSettings()
}

class MenuViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    MenuViewModel {

    override fun navigateToProfile() {
        navigator.navigateToProfile()
    }

    override fun navigateToSettings() {
        navigator.navigateToSettings()
    }
}

