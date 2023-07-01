package com.mobile.fairless.android.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.search.layouts.SearchLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.search.state.SearchState
import com.mobile.fairless.features.search.viewModel.SearchViewModel
import com.mobile.fairless.features.search.viewModel.SearchViewModelImpl
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun SearchScreen(
    viewModelWrapper: StatefulViewModelWrapper<SearchViewModel, SearchState> = getViewModel(
        named("SearchViewModel")
    )
) {
    DisposableEffect(key1 = viewModelWrapper, effect = {
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    })
    Column(
        Modifier
            .fillMaxSize()
            .background(colors.white)
    ) {
        SearchLayout(viewModelWrapper = viewModelWrapper)
    }
}
