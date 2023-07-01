package com.mobile.fairless.android.features.profile.sheets

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.views.layouts.ErrorLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.features.views.topBars.SelectCityTopBar
import com.mobile.fairless.android.features.welcome.register.components.CityItem
import com.mobile.fairless.android.features.welcome.register.components.SelectCitySearchTopBarProfile
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel
import com.mobile.fairless.features.welcome.models.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun CitySheetView(
    viewModelWrapper: StatefulViewModelWrapper<ProfileViewModel, ProfileState>,
    cities: List<City>?,
    errorService: ErrorService = get(),
    cityChanged: (City) -> Unit,
    onValueChanged: () -> Unit,
) {
    viewModelWrapper.viewModel.getCities()
    val state = viewModelWrapper.state

    val startLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val result = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            viewModelWrapper.viewModel.searchChanged(result?.get(0) ?: "")
        }
    }
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SelectCitySearchTopBarProfile(
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

        when (state.value.isLoadingCity) {
            LoadingState.Loading -> LoadingLayout()

            LoadingState.Success -> {
                LazyColumn {
                    if (cities != null) {
                        item { Spacer(modifier = Modifier.padding(top = 12.5.dp)) }
                        items(items = cities.map {
                            if (it.name?.lowercase()
                                    ?.contains(state.value.search.toString().lowercase()) == true
                            ) it else null
                        }) { item ->
                            if (item != null) {
                                CityItem(city = item.name!!) {
                                    cityChanged(item)
                                    onValueChanged()
                                }
                            }
                        }
                        item { Spacer(modifier = Modifier.padding(bottom = 12.5.dp)) }
                    }
                }
            }

            is LoadingState.Error -> ErrorLayout {
                viewModelWrapper.viewModel.getCities()
            }

            else -> {}
        }
    }
}
