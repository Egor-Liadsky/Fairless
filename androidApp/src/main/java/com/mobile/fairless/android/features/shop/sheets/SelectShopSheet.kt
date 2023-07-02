package com.mobile.fairless.android.features.shop.sheets

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.shop.components.SearchShopTopBar
import com.mobile.fairless.android.features.views.layouts.ErrorLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.features.welcome.register.components.CityItem
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectShopSheet(
    sheetState: ModalBottomSheetState,
    selectShopClick: (Shop) -> Unit,
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState>,
    errorService: ErrorService = get()
) {
    val scope = rememberCoroutineScope()
    val state = viewModelWrapper.state

    viewModelWrapper.viewModel.getShops()

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
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Divider(
            Modifier
                .width(30.dp)
                .clip(RoundedCornerShape(2.dp))
                .padding(top = 10.dp, bottom = 10.dp),
            color = colors.navBar,
            thickness = 3.dp
        )

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            SearchShopTopBar(
                state = state,
                searchString = state.value.searchString,
                onClearText = { viewModelWrapper.viewModel.onDeleteSearchClick() },
                placeholder = "Введите название магазина",
                onMicClick = {
                    try {
                        startLauncher.launch(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH))
                    } catch (e: ActivityNotFoundException) {
                        CoroutineScope(Dispatchers.Main).launch {
                            errorService.showError("Нет сервиса распознавания речи")
                        }
                    }
                },
                onSearchChange = { viewModelWrapper.viewModel.searchChanged(it) }
            )

            Spacer(modifier = Modifier.padding(bottom = 15.dp))

            when (state.value.shopsLoadingState) {
                LoadingState.Loading -> LoadingLayout()

                LoadingState.Success -> {
                    LazyColumn {
                        if (state.value.shopList != null) {
                            item { Spacer(modifier = Modifier.padding(top = 12.5.dp)) }
                            items(items = state.value.shopList!!.map {
                                if (it.name?.lowercase()
                                        ?.contains(
                                            state.value.searchString.lowercase().trim()
                                        ) == true
                                ) it else null
                            }) { item ->
                                if (item != null) {
                                    CityItem(city = item.name!!) {
                                        selectShopClick(item)
                                        scope.launch { sheetState.hide() }
                                    }
                                }
                            }
                            item { Spacer(modifier = Modifier.padding(bottom = 12.5.dp)) }
                        }
                    }
                }

                is LoadingState.Error -> ErrorLayout {
                    viewModelWrapper.viewModel.getShops()
                }

                else -> {}
            }
        }
    }
}