package com.mobile.fairless.android.features.search.layouts

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.main.components.ProductItem
import com.mobile.fairless.android.features.search.components.FilterView
import com.mobile.fairless.android.features.search.components.SearchTopBar
import com.mobile.fairless.android.features.views.Refreshable
import com.mobile.fairless.android.features.views.layouts.EmptyLayout
import com.mobile.fairless.android.features.views.layouts.ErrorLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.search.state.SearchState
import com.mobile.fairless.features.search.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SearchLayout(
    viewModelWrapper: StatefulViewModelWrapper<SearchViewModel, SearchState>,
    errorService: ErrorService = get()
) {

    val state = viewModelWrapper.state
    val statePaging = viewModelWrapper.viewModel.statePaging.collectAsState()

    val scope = rememberCoroutineScope()

    val startLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val result = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            viewModelWrapper.viewModel.searchChanged(result?.get(0) ?: "")
        }
    }

    val lazyColumnState = rememberLazyListState()
    val needAppend = remember {
        derivedStateOf {
            lazyColumnState.layoutInfo.totalItemsCount >= 10 &&
                    (lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                        ?: Int.MIN_VALUE) >= lazyColumnState.layoutInfo.totalItemsCount - 5
        }
    }

    LaunchedEffect(needAppend.value) {
        if (needAppend.value) viewModelWrapper.viewModel.onAppend()
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(colors.backgroundWelcome)
            .padding(top = 20.dp)
    ) {
        Column(Modifier.padding(start = 20.dp, end = 20.dp)) {
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
                onSearchChange = {
                    viewModelWrapper.viewModel.searchChanged(it)
                    scope.launch { lazyColumnState.scrollToItem(0) }
                }
            )
            Column {
                FilterView(
                    modifier = Modifier.padding(vertical = 5.dp),
                    popularFilterOpen = state.value.popularFilterOpen,
                    typeFilterOpen = state.value.filtersOpen,
                    selectPopularsFilter = state.value.selectedPopularFilter,
                    selectTypeFilter = state.value.selectType,
                    popularFilterClick = {
                        viewModelWrapper.viewModel.popularFilterOpen()
                    },
                    typeFilterClick = {
                        viewModelWrapper.viewModel.filtersOpen()
                    },
                    popularFilterItemClick = {
                        viewModelWrapper.viewModel.selectPopularFilter(it)
                        viewModelWrapper.viewModel.popularFilterOpen()
                    },
                    typeFilterItemClick = {
                        viewModelWrapper.viewModel.selectType(it)
                        viewModelWrapper.viewModel.filtersOpen()
                    }
                )
            }
        }

        Refreshable(
            isRefreshing = state.value.refreshable,
            onRefresh = { viewModelWrapper.viewModel.onRefresh() }
        ) {
            when (statePaging.value.pagingData.loadingState) {
                LoadingState.Loading -> {
                    LoadingLayout(background = colors.backgroundWelcome)
                }

                LoadingState.Success -> {
                    LazyColumn(
                        state = lazyColumnState,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(
                            items = statePaging.value.pagingData.data ?: emptyList()
                        ) { product ->
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                ProductItem(product = product.product) {
                                    viewModelWrapper.viewModel.onDocumentClick(
                                        product.product.name ?: ""
                                    )
                                }
                            }
                        }

                        if (statePaging.value.pagingData.isAppending) {
                            item {
                                CircularProgressIndicator(
                                    color = colors.orangeMain,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .size(24.dp)
                                )
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        }
                    }
                }

                LoadingState.Empty -> {
                    if (state.value.searchString != "") {
                        scope.launch { delay(100) }
                        EmptyLayout(background = colors.backgroundWelcome)
                    } else SearchEmptyLayout()
                }

                is LoadingState.Error -> {
                    ErrorLayout (background = colors.backgroundWelcome){
                        viewModelWrapper.viewModel.onRefresh()
                    }
                }
            }
        }
    }
}
