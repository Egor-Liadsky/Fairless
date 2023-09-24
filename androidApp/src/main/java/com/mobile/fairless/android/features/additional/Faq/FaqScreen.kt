package com.mobile.fairless.android.features.additional.Faq

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.additional.faq.state.FaqState
import com.mobile.fairless.features.additional.faq.viewModel.FaqViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun FaqScreen(viewModelWrapper: StatefulViewModelWrapper<FaqViewModel, FaqState> = get(named("FaqViewModel"))) {

    val state = viewModelWrapper.viewModel.state

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.white)) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.faq),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        LazyColumn {
            item {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
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
                        title = stringResource(R.string.common_info_1_title),
                        expanded = state.value.common_info1
                    ) {
                        viewModelWrapper.viewModel.common_info1()
                    }
                    if (state.value.common_info1) {
                        FaqExpandBlock(description = stringResource(id = R.string.common_info_1_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.common_info_2_title),
                        expanded = state.value.common_info2
                    ) {
                        viewModelWrapper.viewModel.common_info2()
                    }

                    if (state.value.common_info2) {
                        FaqExpandBlock(description = stringResource(id = R.string.common_info_2_description))
                    }

                    Text(
                        text = stringResource(id = R.string.publish_info),
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
                        title = stringResource(R.string.publish_info_1_title),
                        expanded = state.value.publish_info1
                    ) {
                        viewModelWrapper.viewModel.publish_info1()
                    }

                    if (state.value.publish_info1) {
                        FaqExpandBlock(description = stringResource(id = R.string.publish_info_1_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.publish_info_2_title),
                        expanded = state.value.publish_info2
                    ) {
                        viewModelWrapper.viewModel.publish_info2()
                    }

                    if (state.value.publish_info2) {
                        FaqExpandBlock(description = stringResource(id = R.string.publish_info_2_description))
                    }

                    Text(
                        text = stringResource(id = R.string.conf_info),
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
                        title = stringResource(R.string.conf_info_1_title),
                        expanded = state.value.conf_info1
                    ) {
                        viewModelWrapper.viewModel.conf_info1()
                    }

                    if (state.value.conf_info1) {
                        FaqExpandBlock(description = stringResource(id = R.string.conf_info_1_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.conf_info_2_title),
                        expanded = state.value.conf_info2
                    ) {
                        viewModelWrapper.viewModel.conf_info2()
                    }

                    if (state.value.conf_info2) {
                        FaqExpandBlock(description = stringResource(id = R.string.conf_info_2_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.conf_info_3_title),
                        expanded = state.value.conf_info3
                    ) {
                        viewModelWrapper.viewModel.conf_info3()
                    }

                    if (state.value.conf_info3) {
                        FaqExpandBlock(description = stringResource(id = R.string.conf_info_3_description))
                    }

                    Text(
                        text = stringResource(id = R.string.search_info),
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
                        title = stringResource(R.string.search_info_1_title),
                        expanded = state.value.search_info1
                    ) {
                        viewModelWrapper.viewModel.search_info1()
                    }

                    if (state.value.search_info1) {
                        FaqExpandBlock(description = stringResource(id = R.string.search_info_1_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.search_info_2_title),
                        expanded = state.value.search_info2
                    ) {
                        viewModelWrapper.viewModel.search_info2()
                    }

                    if (state.value.search_info2) {
                        FaqExpandBlock(description = stringResource(id = R.string.search_info_2_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.search_info_3_title),
                        expanded = state.value.search_info3
                    ) {
                        viewModelWrapper.viewModel.search_info3()
                    }

                    if (state.value.search_info3) {
                        FaqExpandBlock(description = stringResource(id = R.string.search_info_3_description))
                    }

                    Text(
                        text = stringResource(id = R.string.participation_info),
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
                        title = stringResource(R.string.participation_info_1_title),
                        expanded = state.value.participation_info1
                    ) {
                        viewModelWrapper.viewModel.participation_info1()
                    }

                    if (state.value.participation_info1) {
                        FaqExpandBlock(description = stringResource(id = R.string.participation_info_1_description))
                    }

//                    FaqItem(
//                        modifier = Modifier.padding(top = 16.dp),
//                        title = stringResource(R.string.participation_info_2_title),
//                        expanded = state.value.participation_info2
//                    ) {
//                        viewModelWrapper.viewModel.participation_info2()
//                    }
//
//                    if (state.value.participation_info2) {
//                        FaqExpandBlock(description = stringResource(id = R.string.search_info_2_description))
//                    }

                    Text(
                        text = stringResource(id = R.string.mobile_info),
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
                        title = stringResource(R.string.mobile_info_title),
                        expanded = state.value.mobile_info
                    ) {
                        viewModelWrapper.viewModel.mobile_info()
                    }

                    if (state.value.mobile_info) {
                        FaqExpandBlock(description = stringResource(id = R.string.mobile_info_description))
                    }

                    Text(
                        text = stringResource(id = R.string.throwable_info),
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
                        title = stringResource(R.string.throwable_info_1_title),
                        expanded = state.value.throwable_info1
                    ) {
                        viewModelWrapper.viewModel.throwable_info1()
                    }

                    if (state.value.throwable_info1) {
                        FaqExpandBlock(description = stringResource(id = R.string.throwable_info_1_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.throwable_info_2_title),
                        expanded = state.value.throwable_info2
                    ) {
                        viewModelWrapper.viewModel.throwable_info2()
                    }

                    if (state.value.throwable_info2) {
                        FaqExpandBlock(description = stringResource(id = R.string.throwable_info_2_description))
                    }

                    Text(
                        text = stringResource(id = R.string.soc_info),
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
                        title = stringResource(R.string.soc_info_1_title),
                        expanded = state.value.soc_info1
                    ) {
                        viewModelWrapper.viewModel.soc_info1()
                    }

                    if (state.value.soc_info1) {
                        FaqExpandBlock(description = stringResource(id = R.string.soc_info_1_description))
                    }

                    FaqItem(
                        modifier = Modifier.padding(top = 16.dp),
                        title = stringResource(R.string.soc_info_2_title),
                        expanded = state.value.soc_info2
                    ) {
                        viewModelWrapper.viewModel.soc_info2()
                    }

                    if (state.value.soc_info2) {
                        FaqExpandBlock(description = stringResource(id = R.string.soc_info_2_description))
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
            .clip(RoundedCornerShape(10.dp))
            .border(0.5.dp, Color(0xFFE1E1E1), RoundedCornerShape(10.dp)) ,
        backgroundColor = colors.white,
        onClick = { onClick() }
    ) {
        Row(
            Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            .padding(top = 10.dp)
    ) {
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
