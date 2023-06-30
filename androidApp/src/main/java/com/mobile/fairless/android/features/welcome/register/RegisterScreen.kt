package com.mobile.fairless.android.features.welcome.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.views.topBars.StageBar
import com.mobile.fairless.android.features.welcome.register.components.SelectCityView
import com.mobile.fairless.android.features.welcome.register.layouts.CheckEmailScreen
import com.mobile.fairless.android.features.welcome.register.layouts.PasswordDataScreen
import com.mobile.fairless.android.features.welcome.register.layouts.UserDataScreen
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterScreen(
    viewModelWrapper: StatefulViewModelWrapper<RegisterViewModel, RegisterState> = get(named("RegisterViewModel")),
    sheetState: ModalBottomSheetState,
    sheetStateSelectCity: ModalBottomSheetState
) {

    val state = viewModelWrapper.state
    val scope = rememberCoroutineScope()

    BackHandler {
        if (sheetStateSelectCity.isVisible) {
            scope.launch { sheetStateSelectCity.hide() }
        } else {
            scope.launch { sheetState.hide() }
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetStateSelectCity,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {
            SelectCityView(
                viewModelWrapper,
                cities = state.value.cities,
                cityChanged = {
                    viewModelWrapper.viewModel.cityChanged(it)
                    scope.launch { sheetStateSelectCity.hide() }
                },
                onValueChanged = { viewModelWrapper.viewModel.getCities() }
            )
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(colors.backgroundWelcome),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StageBar(
                modifier = Modifier.padding(top = 30.dp),
                number = state.value.stage,
                count = 3
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp, bottom = 45.dp)
            ) {
                when (state.value.stage) {
                    1 -> UserDataScreen(
                        viewModelWrapper,
                        sheetState = sheetState,
                        sheetStateSelectCity = sheetStateSelectCity
                    )

                    2 -> PasswordDataScreen(viewModelWrapper)
                    3 -> CheckEmailScreen(viewModelWrapper)
                }
            }
        }
    }
}
