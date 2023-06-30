package com.mobile.fairless.android.features.welcome.register.layouts

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.models.UserAuthResponse
import com.mobile.fairless.features.welcome.register.state.RegisterState
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel

@Composable
fun CheckEmailScreen(
    viewModelWrapper: StatefulViewModelWrapper<RegisterViewModel, RegisterState>,
) {

    BackHandler {
        viewModelWrapper.viewModel.onBackAction()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModelWrapper.state

        Text(
            text = "Успешно",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = colors.black
            ),
            modifier = Modifier.padding(top = 80.dp)
        )

        Text(
            text = "Для того, чтобы завершить процесс регистрации, вам необходимо подтвердить свой аккаунт." +
                    " Для этого, пожалуйста, перейдите по ссылке, которую мы отправили на вашу электронную почту.",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = colors.black
            ),
            modifier = Modifier.padding(top = 30.dp, start = 16.dp, end = 16.dp)
        )
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 40.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            CommonButton(
               title = "Завершить",
                modifier = Modifier.padding(top = 30.dp)
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
