package com.mobile.fairless.android.features.shop.sheets

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.main.components.CategoriesView
import com.mobile.fairless.android.features.search.components.FiltersDropDownMenu
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.search.models.PopularFilter
import com.mobile.fairless.features.shop.state.ShopState
import com.mobile.fairless.features.shop.viewModel.ShopViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TypeFilterSheet(
    sheetState: ModalBottomSheetState,
    selectCategoryClick: (Category) -> Unit,
    viewModelWrapper: StatefulViewModelWrapper<ShopViewModel, ShopState>
) {
    val scope = rememberCoroutineScope()
    val state = viewModelWrapper.state

    Column(
        Modifier
            .fillMaxWidth()
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
        CategoryDropDownMenu(
            expanded = state.value.categoryOpen,
            list = state.value.categories ?: emptyList(),
            isSelect = state.value.selectCategory,
            onCloseClick = { viewModelWrapper.viewModel.categoryDropDownMenuOpen() }) {
            viewModelWrapper.viewModel.selectCategory(it)
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Категория", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black,
                    fontSize = 16.sp
                )
            )
            Button(
                modifier = Modifier.width(225.dp),
                onClick = { /*TODO*/ },
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
                        text = "Выберите категорию", style = TextStyle(
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
                onClick = { /*TODO*/ },
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
                        text = "Выберите магазин", style = TextStyle(
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
            Text(
                text = "Тип", style = TextStyle(
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
    }
}

@Composable
fun CategoryDropDownMenu(
    expanded: Boolean,
    list: List<Category>,
    isSelect: Category,
    onCloseClick: () -> Unit,
    onClick: (Category) -> Unit
) {
    val orangeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF51B00),
            Color(0xFFFF8D00)
        )
    )
    DropdownMenu(
        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
        expanded = expanded,
        onDismissRequest = { onCloseClick() },
    ) {
        list.forEach {
            val isSelected: Boolean = isSelect.sort == it.sort
            DropdownMenuItem(
                modifier = Modifier
                    .background(
                        brush = if (isSelected) orangeGradient
                        else Brush.horizontalGradient(listOf(colors.white, colors.white)),
                        shape = RoundedCornerShape(size = 5.dp)
                    ),
                onClick = { onClick(it) }
            ) {
                Text(
                    text = it.name ?: "",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = if (isSelected) colors.white else colors.gray
                    ),
                )
            }
        }
    }
}