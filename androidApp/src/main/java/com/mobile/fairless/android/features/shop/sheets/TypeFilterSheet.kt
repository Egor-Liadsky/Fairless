package com.mobile.fairless.android.features.shop.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.main.components.CategoriesView
import com.mobile.fairless.android.features.shop.components.TypeDropDownMenu
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TypeFilterSheet(
    sheetState: ModalBottomSheetState,
    selectCategoryClick: (Category) -> Unit,
    selectShopSheetState: ModalBottomSheetState,
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState>
) {
    val scope = rememberCoroutineScope()
    val state = viewModelWrapper.state

    val filterListType = listOf(
        Type("Промокоды и скидки", ProductStockType.ALL),
        Type("Скидки", ProductStockType.SALE),
        Type("Промокоды", ProductStockType.PROMOCODE),
        Type("Бесплатно", ProductStockType.FREE)
    )

    Column(
        Modifier
            .fillMaxWidth()
            .background(colors.white)
            .padding(start = 16.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Divider(
            Modifier
                .width(30.dp)
                .clip(RoundedCornerShape(2.dp))
                .padding(top = 10.dp),
            color = colors.navBar,
            thickness = 3.dp
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Тип", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black,
                    fontSize = 16.sp
                )
            )
            Button(
                modifier = Modifier.width(225.dp),
                onClick = { viewModelWrapper.viewModel.typeFilterOpen() },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                ),
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colors.navBar)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.value.selectType.title, style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(0xFFA7ACAF)
                        )
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "ic_arrow_down",
                        modifier = Modifier.size(10.dp),
                        tint = Color(0xFFA7ACAF)
                    )
                }
                TypeDropDownMenu(
                    expanded = state.value.typeFilterOpen,
                    list = filterListType,
                    isSelect = state.value.selectType,
                    onCloseClick = { viewModelWrapper.viewModel.typeFilterOpen() },
                    onClick = {
                        viewModelWrapper.viewModel.selectType(it)
                        viewModelWrapper.viewModel.typeFilterOpen()
                    }
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Магазин", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black,
                    fontSize = 16.sp
                )
            )
            Button(
                modifier = Modifier.width(225.dp),
                onClick = { scope.launch { selectShopSheetState.show() } },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                ),
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colors.navBar)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.value.shop?.get(0)?.name ?: "", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(0xFFA7ACAF)
                        )
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "ic_arrow_down",
                        modifier = Modifier.size(10.dp),
                        tint = Color(0xFFA7ACAF)
                    )
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            when (state.value.categoriesLoading) {

                LoadingState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(20.dp),
                        color = colors.orangeMain
                    )
                }

                LoadingState.Success -> {
                    Text(
                        text = "Категория", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.black,
                            fontSize = 16.sp
                        ), modifier = Modifier.padding(top = 16.dp)
                    )
                    Column(Modifier.width(240.dp)) {
                        CategoriesView(
                            categories = state.value.categories,
                            categoryOpened = state.value.selectCategory,
                            isPadding = false,
                        ) {
                            selectCategoryClick(it)
                        }

                    }
                }

                is LoadingState.Error -> {
                    Text(
                        text = "Ошибка", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = colors.black,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.fillMaxWidth()
                    )
                }

                else -> {}
            }
        }
    }
}
