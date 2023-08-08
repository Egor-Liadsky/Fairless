package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.state.MainState
import com.mobile.fairless.features.main.viewModel.MainViewModel

@Composable
fun MainTopBar(viewModelWrapper: StatefulViewModelWrapper<MainViewModel, MainState>) {
    val state = viewModelWrapper.state

    Column(
        modifier = Modifier
            .background(colors.backgroundWelcome)
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Выберите нужную вам категорию",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colors.black
                ),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            IconButton(
                onClick = { viewModelWrapper.viewModel.onProfileClick() },
                modifier = Modifier
                    .size(24.dp)
                    .border(width = 0.5.dp, color = colors.black, shape = CircleShape)
                    .background(colors.white, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "ic_user",
                    tint = colors.black,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        when (state.value.categoriesLoading) {

            LoadingState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = colors.black
                    )
                }
            }
            LoadingState.Success -> {

                Column {
                    CategoriesView(
                        categories = state.value.categories,
                        categoryOpened = state.value.selectCategory,
                        modifier = Modifier.padding(top = 15.dp),
                    ) {
                        viewModelWrapper.viewModel.selectCategory(it)
                    }

                    TypeView(
                       modifier = Modifier.padding(top = 10.dp) ,
                        types = state.value.types,
                        typeOpened = state.value.selectType,
                        selectType = { viewModelWrapper.viewModel.selectType(it) }
                    )
                }
            }

            LoadingState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Пусто", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.black,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            is LoadingState.Error -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ошибка", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.black,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            else -> {}

        }
    }
}
