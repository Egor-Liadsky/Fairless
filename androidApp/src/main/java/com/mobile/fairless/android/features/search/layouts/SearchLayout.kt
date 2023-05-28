package com.mobile.fairless.android.features.search.layouts

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.topBars.SearchTopBar
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.search.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun SearchLayout(
    viewModelWrapper: ViewModelWrapper<SearchViewModel>,
    errorService: ErrorService = get()
) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    val startLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val result = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            viewModelWrapper.viewModel.searchChanged(result?.get(0) ?: "")
        }
    }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
            SearchTopBar(
                searchString = state.value.search ?: "",
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
                state = state,
                onSearchChange = { viewModelWrapper.viewModel.searchChanged(it) }
            )
        }
    }
}
