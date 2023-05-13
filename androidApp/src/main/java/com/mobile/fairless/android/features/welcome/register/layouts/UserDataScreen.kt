package com.mobile.fairless.android.features.welcome.register.layouts

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
import com.mobile.fairless.android.features.welcome.register.components.SelectCityAlertDialog
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel

@Composable
fun UserDataScreen(viewModelWrapper: ViewModelWrapper<RegisterViewModel>) {

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
            modifier = Modifier.padding(top = 80.dp)
        )

        CommonTextField(
            modifier = Modifier.padding(top = 40.dp),
            commonTextFieldParams = CommonTextFieldParams(
                textState = viewModelWrapper.viewModel.state.value.email ?: "",
                placeholder = "Введите свой E-mail"
            )
        ) {
            viewModelWrapper.viewModel.emailChanged(it)
        }

        CommonTextField(
            modifier = Modifier.padding(top = 20.dp),
            commonTextFieldParams = CommonTextFieldParams(
                textState = viewModelWrapper.viewModel.state.value.password ?: "",
                placeholder = "Введите свой логин",
            )
        ) {
            viewModelWrapper.viewModel.loginChanged(it)
        }

        CommonButton(
            commonButtonParams = CommonButtonParams(
                title = if (state.value.city == null) "Выберите свой город" else state.value.city!!.name,
                titleColor = colors.black,
                background = colors.white,
                progressBarColor = colors.black
            ),
            isLoading = state.value.isLoading,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            viewModelWrapper.viewModel.selectCityClick()
        }

        if (state.value.alertDialogOpen && state.value.cities != null) {
            SelectCityAlertDialog(
                cities = state.value.cities,
                isOpen = state.value.alertDialogOpen,
                cityChanged = { viewModelWrapper.viewModel.cityChanged(it) },
                viewModelWrapper = viewModelWrapper
            ) {
                viewModelWrapper.viewModel.selectCityClick()
            }
        }

        CommonButton(
            commonButtonParams = CommonButtonParams(
                title = "Далее",
                titleColor = colors.white,
                background = colors.orangeMain
            ),
            modifier = Modifier.padding(top = 20.dp)
        ) {

            //TODO Включить валидацию
//            if (state.value.email == null || state.value.login == null || state.value.city == null ) {
//                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
//            } else {
//                viewModelWrapper.viewModel.onNextClick()
//            }

            viewModelWrapper.viewModel.onNextClick()

        }
    }
}
