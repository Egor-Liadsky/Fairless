package com.mobile.fairless.features.search.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.welcome.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface SearchViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<RegisterState>

    fun searchChanged(search: String)
    fun onDeleteSearchClick()
}

class SearchViewModelImpl(override val navigator: Navigator) : KoinComponent, KmpViewModelImpl(),
    SearchViewModel {

    private val _state = MutableStateFlow(RegisterState())
    override val state: StateFlow<RegisterState> = _state.asStateFlow()

    override fun searchChanged(search: String) {
        _state.update { it.copy(search = search) }
    }

    override fun onDeleteSearchClick() {
        searchChanged("")
    }
}
