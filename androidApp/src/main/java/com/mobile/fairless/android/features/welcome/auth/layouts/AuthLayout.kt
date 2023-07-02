package com.mobile.fairless.android.features.welcome.auth.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.profile.components.DefaultButton
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.features.views.textFields.CommonTextField
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModel
import com.mobile.fairless.features.welcome.models.UserAuthResponse
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthLayout(viewModelWrapper: ViewModelWrapper<AuthViewModel>, sheetState: ModalBottomSheetState) {
    val scope = rememberCoroutineScope()
    val state = viewModelWrapper.viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
    ) {
        SquareButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            icon = painterResource(id = R.drawable.ic_back_button),
            backgroundColor = colors.white,
            iconSize = 15.dp
        ) {
            viewModelWrapper.viewModel.onBackButtonClick()
        }

        Box(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Войдите в\nсвой профиль",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        color = colors.black
                    ),
                )

                CommonTextField(
                    modifier = Modifier
                        .padding(top = 40.dp),
                    textState = viewModelWrapper.viewModel.state.value.email ?: "",
                    placeholder = "Введите свой логин или E-mail",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    enabled = !sheetState.isVisible,
                    onValueChanged = { viewModelWrapper.viewModel.emailChanged(it) }
                )

                CommonTextField(
                    modifier = Modifier.padding(top = 20.dp),
                    textState = viewModelWrapper.viewModel.state.value.password ?: "",
                    placeholder = "Введите свой пароль",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    enabled = !sheetState.isVisible,
                    onDone = {
                        focusManager.clearFocus()
                        viewModelWrapper.viewModel.authUser(
                            UserAuthResponse(
                                identifier = state.value.email?.filter { !it.isWhitespace() } ?: "",
                                password = state.value.password ?: ""
                            )
                        )
                    },
                    isPassword = true
                ) {
                    viewModelWrapper.viewModel.passwordChanged(it)
                }

                Box(modifier = Modifier.padding(top = 25.dp)) {
                    GradientButton(
                        modifier = Modifier.fillMaxWidth(), title = "Войти",
                        isLoading = state.value.isLoading
                    ) {
                        focusManager.clearFocus()
                        viewModelWrapper.viewModel.authUser(
                            UserAuthResponse(
                                identifier = state.value.email?.filter { !it.isWhitespace() } ?: "",
                                password = state.value.password ?: ""
                            )
                        )
                    }
                }
                Box(modifier = Modifier.padding(top = 15.dp)) {
                    DefaultButton(
                        background = colors.white,
                        title = "Регистрация"
                    ) {
                        scope.launch { sheetState.show() }
                    }
                }
            }
        }
    }
}
