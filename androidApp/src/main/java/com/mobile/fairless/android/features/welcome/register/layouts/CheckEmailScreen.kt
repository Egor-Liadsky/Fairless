package com.mobile.fairless.android.features.welcome.register.layouts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.models.UserAuthResponse
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel

@Composable
fun CheckEmailScreen(
    viewModelWrapper: StatefulViewModelWrapper<RegisterViewModel, RegisterState>,
) {
    val state = viewModelWrapper.state

    BackHandler {
        viewModelWrapper.viewModel.onBackAction()
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
                text = "Успешно",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = colors.black
                ),
            )

            Text(
                text = "Для того, чтобы завершить процесс регистрации, вам необходимо подтвердить свой аккаунт." +
                        " Для этого, пожалуйста, перейдите по ссылке, которую мы отправили на вашу электронную почту.",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = colors.black
                ),
                modifier = Modifier.padding(top = 35.dp)
            )
        }

        Column(Modifier.align(Alignment.BottomCenter)) {
            GradientButton(
                Modifier.fillMaxWidth(),
                title = "Далее",
                isLoading = state.value.isLoading,
            ) {
                viewModelWrapper.viewModel.checkUser(
                    UserAuthResponse(
                        identifier = state.value.email.toString(),
                        password = state.value.password.toString()
                    )
                )
            }
        }
    }
}
