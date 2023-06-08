package com.mobile.fairless.android.features.additional.Faq

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.FaqViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun FaqScreen(viewModelWrapper: ViewModelWrapper<FaqViewModel> = get(named("FaqViewModel"))) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.faq),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        LazyColumn {
            item {
                Column (Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                    Text(
                        text = stringResource(id = R.string.common_info),
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = colors.black,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.padding(bottom = 16.dp),
                    )

                    FaqItem(
                        title = stringResource(R.string.how_work_title),
                        expanded = state.value.howWork
                    ) {
                        viewModelWrapper.viewModel.howWorkClick()
                    }
                    if (state.value.howWork) {
                        FaqExpandBlock(description = stringResource(id = R.string.how_work_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = "Кто может размещать скидки",
                        expanded = false
                    ) {

                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = "Модерируются ли и редактируются скидки?",
                        expanded = false
                    ) {

                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = "Как FAIR LESS зарабатывает деньги?",
                        expanded = false
                    ) {

                    }

                    Text(
                        text = "Мой аккаунт и регистрация",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = colors.black,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.padding(top = 24.dp),
                    )

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = "Почему я должен зарегистрироваться на FAIR LESS?",
                        expanded = false
                    ) {

                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = "Является ли FAIR LESS бесплатным в использовании?",
                        expanded = false
                    ) {

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FaqItem(modifier: Modifier = Modifier, title: String, expanded: Boolean, onClick: () -> Unit) {
    Card(
         modifier = modifier
            .fillMaxWidth()
            .background(colors.white, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() },
        onClick = { onClick() },
        elevation = if (expanded) 0.dp else 1.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically) {
            Text(
                if (expanded) "-" else "+", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = colors.orangeMain
                )
            )
            Text(
                title, style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = colors.black
                ), modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Composable
fun FaqExpandBlock(description: String) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 10.dp)) {
        Text(
            text = description,
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = colors.black,
                textAlign = TextAlign.Start
            ),
        )
    }
}
