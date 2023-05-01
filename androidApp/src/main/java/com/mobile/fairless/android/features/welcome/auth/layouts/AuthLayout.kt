package com.mobile.fairless.android.features.welcome.auth.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.mobile.fairless.features.welcome.auth.viewModel.AuthViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun AuthLayout(viewModelWrapper: ViewModelWrapper<AuthViewModel> = get(named("AuthViewModel"))) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
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
            modifier = Modifier.padding(top = 130.dp)
        )

        CommonTextField(
            modifier = Modifier.padding(top = 40.dp),
            commonTextFieldParams = CommonTextFieldParams(
                textState = viewModelWrapper.viewModel.state.value.email ?: "",
                placeholder = "Введите свой логин или E-mail"
            )
        ) {
            viewModelWrapper.viewModel.emailChanged(it)
        }

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

        CommonButton(
            commonButtonParams = CommonButtonParams(
                title = "Войти",
                titleColor = colors.white,
                background = colors.orangeMain
            ),
            modifier = Modifier.padding(top = 20.dp)
        ) {

        }
    }
}
