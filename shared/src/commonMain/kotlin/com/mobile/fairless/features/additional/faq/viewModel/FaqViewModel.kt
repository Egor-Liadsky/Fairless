package com.mobile.fairless.features.additional.aboutFairLess.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.additional.faq.state.FaqState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface FaqViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<FaqState>

    fun howWorkClick()
}

class FaqViewModelImpl(
    override val navigator: Navigator
) : KoinComponent, KmpViewModelImpl(), FaqViewModel {

    private val _state = MutableStateFlow(FaqState())
    override val state: StateFlow<FaqState> = _state.asStateFlow()

    override fun howWorkClick() {
        _state.update { it.copy(howWork = !it.howWork) }
    }
}

