package com.mobile.fairless.features.search.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.search.state.SearchState
import com.mobile.fairless.features.welcome.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface SearchViewModel : StatefulKmpViewModel<SearchState>, SubScreenViewModel {
    override val state: StateFlow<SearchState>

    fun searchChanged(search: String)
    fun onDeleteSearchClick()
}

class SearchViewModelImpl(override val navigator: Navigator) : KoinComponent, StatefulKmpViewModelImpl<SearchState>(),
    SearchViewModel {

    private val _state = MutableStateFlow(SearchState())
    override val state: StateFlow<SearchState> = _state.asStateFlow()

    override fun searchChanged(search: String) {
        _state.update { it.copy(searchString = search) }
    }

    override fun onDeleteSearchClick() {
        searchChanged("")
    }
}
