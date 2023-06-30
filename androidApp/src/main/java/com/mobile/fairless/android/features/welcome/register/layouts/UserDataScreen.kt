package com.mobile.fairless.android.features.welcome.register.layouts

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.features.views.textFields.CommonTextField
import com.mobile.fairless.android.features.welcome.register.RegisterScreen
import com.mobile.fairless.android.features.welcome.register.components.SelectCityAlertDialog
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserDataScreen(
    viewModelWrapper: StatefulViewModelWrapper<RegisterViewModel, RegisterState>,
    sheetState: ModalBottomSheetState,
    errorService: ErrorService = get(),
    sheetStateSelectCity: ModalBottomSheetState
) {
    val state = viewModelWrapper.state
    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = sheetState.isVisible) {
        if (sheetState.isVisible) focusRequester.requestFocus()
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Column(
            Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Регистрация",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    color = colors.black
                )
            )

            CommonTextField(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .focusRequester(focusRequester),
                textState = viewModelWrapper.viewModel.state.value.email ?: "",
                placeholder = "Введите свой E-mail",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
            ) {
                viewModelWrapper.viewModel.emailChanged(it)
            }

            CommonTextField(
                modifier = Modifier.padding(top = 25.dp),
                textState = viewModelWrapper.viewModel.state.value.login ?: "",
                placeholder = "Введите свой логин",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                onDone = { focusManager.clearFocus() },
            ) {
                viewModelWrapper.viewModel.loginChanged(it)
            }

            CommonButton(
                title = state.value.city?.name ?: "Выберите свой город",
                isLoading = state.value.isLoadingCity,
                modifier = Modifier.padding(top = 15.dp)
            ) {
                focusManager.clearFocus()
                viewModelWrapper.viewModel.getCity()
                scope.launch { sheetStateSelectCity.show() }
            }
        }

        Column(Modifier.align(Alignment.BottomCenter)) {
            GradientButton(
                modifier = Modifier.fillMaxWidth(), title = "Далее",
                isLoading = state.value.isLoading
            ) {
                if ((state.value.email == null || state.value.email == "")
                    || (state.value.login == null || state.value.login == "")
                    || (state.value.city == null)
                ) {
                    scope.launch { errorService.showError("Заполните все поля") }
                } else {
                    viewModelWrapper.viewModel.onNextClick()
                }
            }
        }
    }
}
