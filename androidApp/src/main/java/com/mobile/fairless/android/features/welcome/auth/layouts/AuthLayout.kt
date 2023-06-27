package com.mobile.fairless.android.features.welcome.auth.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.features.views.textFields.CommonTextField
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.models.UserAuthResponse
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModel

@Composable
fun AuthLayout(viewModelWrapper: ViewModelWrapper<AuthViewModel>) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
    ) {
        val state = viewModelWrapper.viewModel.state.collectAsState()
        val focusManager = LocalFocusManager.current

        SquareButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            icon = painterResource(id = R.drawable.ic_back_button),
            backgroundColor = colors.white,
            iconSize = 15.dp
        ) {
            viewModelWrapper.viewModel.onBackButtonClick()
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Войдите в\nсвой профиль",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    color = colors.black
                ),
                modifier = Modifier.padding(top = 80.dp)
            )

            CommonTextField(
                modifier = Modifier.padding(top = 40.dp),
                textState = viewModelWrapper.viewModel.state.value.email ?: "",
                placeholder = "Введите свой логин или E-mail"
            ) {
                viewModelWrapper.viewModel.emailChanged(it)
            }

            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                textState = viewModelWrapper.viewModel.state.value.password ?: "",
                placeholder = "Введите свой пароль",
                isPassword = true
            ) {
                viewModelWrapper.viewModel.passwordChanged(it)
            }

            CommonButton(
                commonButtonParams = CommonButtonParams(
                    title = "Войти",
                    titleColor = colors.white,
                    background = colors.orangeMain,
                ),
                isLoading = state.value.isLoading,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                focusManager.clearFocus()
                viewModelWrapper.viewModel.authUser(
                    UserAuthResponse(
                        identifier = state.value.email?.filter { !it.isWhitespace() } ?: "",
                        password = state.value.password ?: ""
                    )
                )
            }
            if (state.value.user != null) {
                viewModelWrapper.viewModel.onBackButtonClick()
            }

            CommonButton(
                commonButtonParams = CommonButtonParams(
                    title = "Регистрация",
                    titleColor = colors.black,
                    background = colors.white
                ),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                viewModelWrapper.viewModel.navigateToRegister()
            }
        }
    }
}
