package com.mobile.fairless.android.features.welcome.welcome.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.welcome.viewModel.WelcomeViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun WelcomeLayout(viewModelWrapper: ViewModelWrapper<WelcomeViewModel> = getViewModel(named("WelcomeViewModel"))) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background_welcome),
            contentDescription = "background_welcome",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 130.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "ic_logo"
            )

            Text(
                text = "Добро\nпожаловать!",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    color = colors.white
                ),
                modifier = Modifier.padding(top = 40.dp)
            )
            Text(
                text = "Выгодные скидки и промокоды",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = colors.white
                ),
                modifier = Modifier.padding(top = 5.dp)
            )

            CommonButton(
                commonButtonParams = CommonButtonParams(title = "Регистрация"),
                modifier = Modifier.padding(top = 40.dp)
            ) {
                viewModelWrapper.viewModel.registerClick()
            }
            CommonButton(
                commonButtonParams = CommonButtonParams(title = "Вход"),
                modifier = Modifier.padding(top = 15.dp)
            ) {
                viewModelWrapper.viewModel.authClick()
            }
        }
    }
}
