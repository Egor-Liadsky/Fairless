package com.mobile.fairless.android.features.additional.AboutApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.additional.AboutFairLess.AboutBlockView
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.AboutAppViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun AboutAppScreen(viewModelWrapper: ViewModelWrapper<AboutAppViewModel> = get(named("AboutAppViewModel"))) {
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.about_app),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "ic_logo"
            )

            Text(
                text = "1.0", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = colors.black
                ), modifier = Modifier.padding(top = 20.dp)
            )
        }

    }
}
