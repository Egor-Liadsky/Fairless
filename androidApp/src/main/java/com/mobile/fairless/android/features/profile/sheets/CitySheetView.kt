package com.mobile.fairless.android.features.profile.sheets

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.profile.components.ProfileSelectCitySearchTopBar
import com.mobile.fairless.android.features.welcome.register.components.CityItem
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CitySheetView(
    sheetState: ModalBottomSheetState,
    viewModelWrapper: StatefulViewModelWrapper<ProfileViewModel, ProfileState>,
    errorService: ErrorService = get()
) {
    viewModelWrapper.viewModel.getCities()
    val state = viewModelWrapper.state

    val scope = rememberCoroutineScope()

    val cities = state.value.cities

    val startLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val result = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            viewModelWrapper.viewModel.searchChanged(result?.get(0) ?: "")
        }
    }
    Column(Modifier.fillMaxSize()) {
        ProfileSelectCitySearchTopBar(
            state = state,
            placeholder = "Введите название города",
            searchString = state.value.search,
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
            onSearchClick = {},
            onSearchChange = { viewModelWrapper.viewModel.searchChanged(it) },
        )
        Spacer(modifier = Modifier.padding(bottom = 15.dp))

        if (state.value.isLoading) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = colors.orangeMain
                )
            }
        } else {
            LazyColumn {
                if (cities != null) {
                    items(items = cities.map {
                        if (it.name.lowercase()
                                .contains(state.value.search.lowercase())
                        ) it else null
                    }) { item ->
                        if (item != null) {
                            CityItem(city = item.name) {
                                viewModelWrapper.viewModel.cityChanged(item)
                                scope.launch { sheetState.hide() }
                            }
                        }
                    }
                }
            }
        }
    }
}
