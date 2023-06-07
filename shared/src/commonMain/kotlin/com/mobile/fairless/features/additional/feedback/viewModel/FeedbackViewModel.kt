package com.mobile.fairless.features.additional.aboutFairLess.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.mainNavigation.state.MainNavigationState
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

interface FeedbackViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<MainNavigationState>
}

class FeedbackViewModelImpl(
    override val navigator: Navigator
) : KoinComponent, KmpViewModelImpl(), FeedbackViewModel {

    override val state: StateFlow<MainNavigationState>
        get() = TODO("Not yet implemented")

}

