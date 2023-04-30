package com.mobile.fairless.android.di

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import kotlinx.coroutines.launch

class ViewModelWrapper<T : KmpViewModel>(
    val viewModel: T
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        viewModel.onCleared()
    }
}

class StatefulViewModelWrapper<T : StatefulKmpViewModel<S>, S>(
    val viewModel: T
) : ViewModel() {
    val state = mutableStateOf(viewModel.state.value)

    init {
        viewModelScope.launch {
            viewModel.state.collect {
                state.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModel.onCleared()
    }
}
