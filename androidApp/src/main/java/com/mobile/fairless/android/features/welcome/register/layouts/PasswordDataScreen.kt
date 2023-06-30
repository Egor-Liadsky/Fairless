package com.mobile.fairless.android.features.welcome.register.layouts

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
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
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.features.views.textFields.CommonTextField
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.models.UserRegisterResponse
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun PasswordDataScreen(
    viewModelWrapper: StatefulViewModelWrapper<RegisterViewModel, RegisterState>,
    errorService: ErrorService = get()
) {
    val state = viewModelWrapper.viewModel.state
    val scope = rememberCoroutineScope()

    BackHandler {
        viewModelWrapper.viewModel.onBackAction()
    }

    val focusManager = LocalFocusManager.current

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
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
                    .padding(top = 20.dp)
                    .focusRequester(focusRequester),
                textState = viewModelWrapper.viewModel.state.value.password ?: "",
                placeholder = "Введите свой пароль",
                isPassword = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
            ) {
                viewModelWrapper.viewModel.passwordChanged(it)
            }

            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                textState = viewModelWrapper.viewModel.state.value.password ?: "",
                placeholder = "Повторите пароль",
                isPassword = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                onDone = {
                    focusManager.clearFocus()
                    if ((state.value.password == null || state.value.password == "")
                        || state.value.passwordRetry == null || state.value.passwordRetry == ""
                    ) {
                        scope.launch { errorService.showError("Заполните все поля") }
                    } else {

                        if (state.value.password != state.value.passwordRetry) {
                            scope.launch { errorService.showError("Пароли не совпадают") }
                        } else {
                            viewModelWrapper.viewModel.registerUser(
                                UserRegisterResponse(
                                    email = state.value.email ?: "",
                                    username = state.value.login ?: "",
                                    password = state.value.password ?: "",
                                    city = state.value.city ?: City()
                                )
                            )
                        }
                    }
                }
            ) {
                viewModelWrapper.viewModel.passwordRetryChanged(it)
            }
        }

        Column(Modifier.align(Alignment.BottomCenter)) {
            GradientButton(
                Modifier.fillMaxWidth(),
                title = "Завершить",
                isLoading = state.value.isLoading,
            ) {
                focusManager.clearFocus()
                if ((state.value.password == null || state.value.password == "")
                    || state.value.passwordRetry == null || state.value.passwordRetry == ""
                ) {
                    scope.launch { errorService.showError("Заполните все поля") }
                } else {

                    if (state.value.password != state.value.passwordRetry) {
                        scope.launch { errorService.showError("Пароли не совпадают") }
                    } else {
                        viewModelWrapper.viewModel.registerUser(
                            UserRegisterResponse(
                                email = state.value.email ?: "",
                                username = state.value.login ?: "",
                                password = state.value.password ?: "",
                                city = state.value.city ?: City()
                            )
                        )
                    }
                }
            }
        }
    }
}
