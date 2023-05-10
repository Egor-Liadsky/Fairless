package com.mobile.fairless.features.settings.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import org.koin.core.component.KoinComponent

interface SettingsViewModel : KmpViewModel, SubScreenViewModel {
}

class SettingsViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    SettingsViewModel {

}

