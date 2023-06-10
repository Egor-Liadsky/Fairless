package com.mobile.fairless.android.features.additional.AboutFairLess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.AboutFairLessViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun AboutFairLess(viewModelWrapper: ViewModelWrapper<AboutFairLessViewModel> = get(named("AboutFairLessViewModel"))) {
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.about),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        AboutBlockView(
            Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 16.dp)
        )

        Column(Modifier.padding(horizontal = 16.dp, vertical = 30.dp)) {
            Text(
                text = stringResource(id = R.string.history_title),
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colors.black,
                    textAlign = TextAlign.Start
                ),
            )

            Text(text = stringResource(id = R.string.history_description),
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = colors.black,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.padding(top = 15.dp)
            )
        }
    }
}

@Composable
fun AboutBlockView(modifier: Modifier = Modifier) {
    val orangeGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFF8D00),
            Color(0xFFF51B00),
        )
    )

    Column(
        modifier
            .background(orangeGradient, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 16.dp, vertical = 30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.about_block_title),
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = colors.white,
                textAlign = TextAlign.Start
            )
        )
        Text(
            text = stringResource(id = R.string.about_block_description),
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = colors.white,
                textAlign = TextAlign.Start
            )
        )

    }
}
