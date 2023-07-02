package com.mobile.fairless.android.features.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.search.models.PopularFilter
import com.mobile.fairless.features.search.models.Sort

@Composable
fun FilterView(
    modifier: Modifier = Modifier,
    selectPopularsFilter: PopularFilter,
    selectTypeFilter: Type,
    popularFilterOpen: Boolean,
    typeFilterOpen: Boolean,
    popularFilterClick: () -> Unit,
    popularFilterItemClick: (PopularFilter) -> Unit,
    typeFilterClick: () -> Unit,
    typeFilterItemClick: (Type) -> Unit
) {
    val popularList = listOf(
        PopularFilter("По дате создания", Sort.CREATE),
        PopularFilter("По популярности", Sort.LIKES),
        PopularFilter("По просмотрам", Sort.VIEWS),
        PopularFilter("По возрастанию цены", Sort.SALE_ASCENDING),
        PopularFilter("По убыванию цены", Sort.SALE_DESCENDING)
    )

    val filterListType = listOf(
        Type("Промокоды и скидки", ProductStockType.ALL),
        Type("Скидки", ProductStockType.SALE),
        Type("Промокоды", ProductStockType.PROMOCODE),
        Type("Бесплатно", ProductStockType.FREE)
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { popularFilterClick() },
            elevation = ButtonDefaults.elevation(pressedElevation = 0.dp, defaultElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(end = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_popular),
                    contentDescription = "ic_popular",
                    tint = colors.gray,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = selectPopularsFilter.title, style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.gray,
                        fontSize = 12.sp
                    ), modifier = Modifier.padding(start = 10.dp)
                )
            }

            FiltersDropDownMenu(
                expanded = popularFilterOpen,
                list = popularList,
                isSelect = selectPopularsFilter,
                onCloseClick = { popularFilterClick() }) {
                popularFilterItemClick(it)
            }
        }

        Button(
            onClick = { typeFilterClick() },
            elevation = ButtonDefaults.elevation(pressedElevation = 0.dp, defaultElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(end = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filters),
                    contentDescription = "ic_filters",
                    tint = colors.gray,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = "Фильтры", style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.gray,
                        fontSize = 12.sp
                    ), modifier = Modifier.padding(start = 10.dp)
                )
            }
            TypeFilterDropDownMenu(
                expanded = typeFilterOpen,
                list = filterListType,
                isSelect = selectTypeFilter,
                onCloseClick = { typeFilterClick() },
            ) {
                typeFilterItemClick(it)
            }
        }
    }
}

@Composable
fun FiltersDropDownMenu(
    expanded: Boolean,
    list: List<PopularFilter>,
    isSelect: PopularFilter,
    onCloseClick: () -> Unit,
    onClick: (PopularFilter) -> Unit
) {
    val orangeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF51B00),
            Color(0xFFFF8D00)
        )
    )
    DropdownMenu(
        modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(colors.white),
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
                    text = it.title,
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

@Composable
fun TypeFilterDropDownMenu(
    expanded: Boolean,
    list: List<Type>,
    isSelect: Type,
    onCloseClick: () -> Unit,
    onClick: (Type) -> Unit
) {
    val orangeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF51B00),
            Color(0xFFFF8D00)
        )
    )
    DropdownMenu(
        modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(colors.white),
        expanded = expanded,
        onDismissRequest = { onCloseClick() },
    ) {
        list.forEach {
            val isSelected: Boolean = isSelect.type == it.type
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
                    text = it.title,
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

