package com.mobile.fairless.android.features.welcome.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.welcome.auth.layouts.AuthLayout
import com.mobile.fairless.android.features.welcome.register.RegisterScreen
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthScreen(viewModelWrapper: ViewModelWrapper<AuthViewModel> = get(named("AuthViewModel"))) {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val sheetStateSelectCity = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()

    BackHandler {
        if (sheetState.isVisible) {
            scope.launch { sheetState.hide() }
        } else if (sheetStateSelectCity.isVisible) {
            scope.launch { sheetStateSelectCity.hide() }
        } else {
            viewModelWrapper.viewModel.onBackButtonClick()
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {
            RegisterScreen(sheetState = sheetState, sheetStateSelectCity = sheetStateSelectCity)
        },
    ) {
        Box(Modifier.fillMaxSize()) {
            AuthLayout(viewModelWrapper, sheetState)
        }
    }
}
