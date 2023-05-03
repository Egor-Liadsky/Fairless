package com.mobile.fairless.android.features.welcome.register.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.textFields.CommonTextField
import com.mobile.fairless.android.features.views.textFields.CommonTextFieldParams
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.dto.City
import com.mobile.fairless.features.welcome.dto.UserRegisterResponse
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel

@Composable
fun PasswordDataScreen(viewModelWrapper: ViewModelWrapper<RegisterViewModel>) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModelWrapper.viewModel.state.collectAsState()

        Text(
            text = "Регистрация",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = colors.black
            ),
            modifier = Modifier.padding(top = 130.dp)
        )

        CommonTextField(
            modifier = Modifier.padding(top = 20.dp),
            commonTextFieldParams = CommonTextFieldParams(
                textState = viewModelWrapper.viewModel.state.value.password ?: "",
                placeholder = "Введите свой пароль",
                isPassword = true
            )
        ) {
            viewModelWrapper.viewModel.passwordChanged(it)
        }

        CommonTextField(
            modifier = Modifier.padding(top = 20.dp),
            commonTextFieldParams = CommonTextFieldParams(
                textState = viewModelWrapper.viewModel.state.value.password ?: "",
                placeholder = "Повторите пароль",
                isPassword = true
            )
        ) {
            viewModelWrapper.viewModel.passwordRetryChanged(it)
        }


        CommonButton(
            commonButtonParams = CommonButtonParams(
                title = "Зарегистрироваться",
                titleColor = colors.white,
                background = colors.orangeMain
            ),
            isLoading = state.value.isLoading,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            viewModelWrapper.viewModel.registerUser(
                UserRegisterResponse(
                    email = state.value.email ?: "",
                    username = state.value.login ?: "",
                    password = state.value.password ?: "",
                    city = City(
                        _id = "61b0edeef2d07d28d43c5d02",
                        name = "Ростов-на-Дону",
                        code = "rostov-on-don",
                        published_at = "2021-12-08T17:40:00.254Z",
                        createdAt = "2021-12-08T17:39:58.010Z",
                        updatedAt = "2022-01-14T09:03:58.234Z",
                        __v = 0,
                        sort = 200,
                        id = "61b0edeef2d07d28d43c5d02"
                    )
                )
            )
        }
    }
}
