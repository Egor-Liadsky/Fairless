package com.mobile.fairless.android.features.welcome.register.layouts

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.mobile.fairless.features.welcome.dto.City
import com.mobile.fairless.features.welcome.dto.UserAuthResponse
import com.mobile.fairless.features.welcome.dto.UserRegisterResponse
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun CheckEmailScreen(
    viewModelWrapperRegister: ViewModelWrapper<RegisterViewModel>,
    viewModelWrapperAuth: ViewModelWrapper<AuthViewModel> = get(named("AuthViewModel"))
) {

    BackHandler {
        viewModelWrapperRegister.viewModel.onBackAction()
    }

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModelWrapperRegister.viewModel.state.collectAsState()

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
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = colors.black
            ),
            modifier = Modifier.padding(top = 5.dp)
        )

        CommonButton(commonButtonParams = CommonButtonParams("Далее")) {
            viewModelWrapperRegister.viewModel.checkUser(
                UserAuthResponse(
                    identifier = state.value.email.toString(),
                    password = state.value.password.toString()
                )
            )
        }
    }
}
