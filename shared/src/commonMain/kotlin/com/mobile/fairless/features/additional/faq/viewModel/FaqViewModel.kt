package com.mobile.fairless.features.additional.aboutFairLess.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

interface FaqViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<MainNavigationState>
}

class FaqViewModelImpl(
    override val navigator: Navigator
) : KoinComponent, KmpViewModelImpl(), FaqViewModel {

    override val state: StateFlow<MainNavigationState>
        get() = TODO("Not yet implemented")

}

