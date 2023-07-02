package com.mobile.fairless.android.features.additional.Rules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.additional.AboutFairLess.AboutBlockView
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.RulesViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun RulesScreen(viewModelWrapper: ViewModelWrapper<RulesViewModel> = get(named("RulesViewModel"))) {
    Column(Modifier.fillMaxSize().background(colors.white)) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.rules),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        LazyColumn {
                item {
                    Column(Modifier.padding(horizontal = 16.dp)) {
                        Text(text = stringResource(id = R.string.rule1),
                            style = TextStyle(
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = colors.black,
                                textAlign = TextAlign.Start
                            ), modifier = Modifier.padding(top = 16.dp)
                        )

                        Text(
                            text = "Зарещено:",
                            style = TextStyle(
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = colors.black,
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.padding(top = 16.dp)
                        )

                        Text(text = stringResource(id = R.string.rule2),
                            style = TextStyle(
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = colors.black,
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.padding(top = 10.dp, bottom = 16.dp)
                        )
                    }
                }
        }

    }
}
