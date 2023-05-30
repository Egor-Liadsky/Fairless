package com.mobile.fairless.android.features.search.layouts

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.search.components.FilterView
import com.mobile.fairless.android.features.search.components.SearchTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.search.state.SearchState
import com.mobile.fairless.features.search.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun SearchLayout(
    viewModelWrapper: StatefulViewModelWrapper<SearchViewModel, SearchState>,
    errorService: ErrorService = get()
) {

    val state = viewModelWrapper.state

    val startLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val result = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            viewModelWrapper.viewModel.searchChanged(result?.get(0) ?: "")
        }
    }

    Log.e("sdfkjsdf", state.value.searchString)

    Column(Modifier.fillMaxSize()) {

        Column(
            Modifier
                .fillMaxWidth()
                .background(colors.backgroundWelcome)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
        ) {
            SearchTopBar(
                state = state,
                placeholder = "Введите название товара",
                searchString = state.value.searchString,
                onClearText = { viewModelWrapper.viewModel.onDeleteSearchClick() },
                onMicClick = {
                    try {
                        startLauncher.launch(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH))
                    } catch (e: ActivityNotFoundException) {
                        CoroutineScope(Dispatchers.Main).launch {
                            errorService.showError("Нет сервиса распознавания речи")
                        }
                    }
                },
                onSearchClick = { /*TODO*/ },
                onSearchChange = { viewModelWrapper.viewModel.searchChanged(it) }
            )
            FilterView(
                modifier = Modifier.padding(vertical = 5.dp),
                popularFilterOpen = state.value.popularFilterOpen,
                filtersOpen = state.value.filtersOpen,
                selectPopularsFilter = state.value.selectedPopularFilter,
                popularFilterClick = {
                    viewModelWrapper.viewModel.popularFilterOpen()
                },
                filterClick = {
                    viewModelWrapper.viewModel.filtersOpen()
                },
                popularFilterItemClick = {
                    viewModelWrapper.viewModel.selectPopularFilter(it)
                    viewModelWrapper.viewModel.popularFilterOpen()
                },
                filterItemClick = {
                    viewModelWrapper.viewModel.selectFilters(it)
                    viewModelWrapper.viewModel.filtersOpen()
                }
            )
        }
    }
}
