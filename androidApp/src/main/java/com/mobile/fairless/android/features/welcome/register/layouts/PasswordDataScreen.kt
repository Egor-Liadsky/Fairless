package com.mobile.fairless.android.features.welcome.register.layouts

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.textFields.CommonTextField
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.models.UserRegisterResponse
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel

@Composable
fun PasswordDataScreen(viewModelWrapper: StatefulViewModelWrapper<RegisterViewModel, RegisterState>) {

    BackHandler {
        viewModelWrapper.viewModel.onBackAction()
    }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModelWrapper.viewModel.state

        Text(
            text = "Регистрация",
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
            modifier = Modifier.padding(top = 20.dp),
            textState = viewModelWrapper.viewModel.state.value.password ?: "",
            placeholder = "Введите свой пароль",
            isPassword = true
        ) {
            viewModelWrapper.viewModel.passwordChanged(it)
        }

        CommonTextField(
            modifier = Modifier.padding(top = 20.dp),
            textState = viewModelWrapper.viewModel.state.value.password ?: "",
            placeholder = "Повторите пароль",
            isPassword = true
        ) {
            viewModelWrapper.viewModel.passwordRetryChanged(it)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            CommonButton(
                title = "Далее",
                isLoading = state.value.isLoading,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                focusManager.clearFocus()

                if (state.value.password == null || state.value.passwordRetry == null) {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                } else {

                    if (state.value.password != state.value.passwordRetry) {
                        Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModelWrapper.viewModel.registerUser(
                            UserRegisterResponse(
                                email = state.value.email ?: "",
                                username = state.value.login ?: "",
                                password = state.value.password ?: "",
                                city = state.value.city ?: City(
                                    _id = "", name = "", code = "", published_at = "",
                                    createdAt = "", updatedAt = "", __v = 0, sort = 0, id = ""
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}
