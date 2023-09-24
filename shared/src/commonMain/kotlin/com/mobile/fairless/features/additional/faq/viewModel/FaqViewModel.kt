package com.mobile.fairless.features.additional.faq.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.additional.faq.state.FaqState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface FaqViewModel : StatefulKmpViewModel<FaqState>, SubScreenViewModel {

    fun common_info1()
    fun common_info2()

    fun publish_info1()
    fun publish_info2()

    fun conf_info1()
    fun conf_info2()
    fun conf_info3()


    fun search_info1()
    fun search_info2()
    fun search_info3()

    fun participation_info1()
    fun participation_info2()

    fun mobile_info()

    fun throwable_info1()
    fun throwable_info2()

    fun soc_info1()
    fun soc_info2()
}

class FaqViewModelImpl(
    override val navigator: Navigator
) : KoinComponent, StatefulKmpViewModelImpl<FaqState>(), FaqViewModel {

    private val _state = MutableStateFlow(FaqState())
    override val state: StateFlow<FaqState> = _state.asStateFlow()

    override fun common_info1() {
        _state.update { it.copy(common_info1 = !it.common_info1) }
    }

    override fun common_info2() {
        _state.update { it.copy(common_info2 = !it.common_info2) }
    }

    override fun publish_info1() {
        _state.update { it.copy(publish_info1 = !it.publish_info1) }
    }

    override fun publish_info2() {
        _state.update { it.copy(publish_info2 = !it.publish_info2) }
    }

    override fun conf_info1() {
        _state.update { it.copy(conf_info1 = !it.conf_info1) }
    }

    override fun conf_info2() {
        _state.update { it.copy(conf_info2 = !it.conf_info2) }
    }

    override fun conf_info3() {
        _state.update { it.copy(conf_info3 = !it.conf_info3) }
    }

    override fun search_info1() {
        _state.update { it.copy(search_info1 = !it.search_info1) }
    }

    override fun search_info2() {
        _state.update { it.copy(search_info2 = !it.search_info2) }
    }

    override fun search_info3() {
        _state.update { it.copy(search_info3 = !it.search_info3) }
    }

    override fun participation_info1() {
        _state.update { it.copy(participation_info1 = !it.participation_info1) }
    }

    override fun participation_info2() {
        _state.update { it.copy(participation_info2 = !it.participation_info2) }
    }

    override fun mobile_info() {
        _state.update { it.copy(mobile_info = !it.mobile_info) }
    }

    override fun throwable_info1() {
        _state.update { it.copy(throwable_info1 = !it.throwable_info1) }
    }

    override fun throwable_info2() {
        _state.update { it.copy(throwable_info2 = !it.throwable_info2) }
    }

    override fun soc_info1() {
        _state.update { it.copy(soc_info1 = !it.soc_info1) }
    }

    override fun soc_info2() {
        _state.update { it.copy(soc_info2 = !it.soc_info2) }
    }
}

