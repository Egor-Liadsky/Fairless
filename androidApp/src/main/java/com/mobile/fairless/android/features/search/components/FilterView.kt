package com.mobile.fairless.android.features.search.components

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.search.state.SearchState

@Composable
fun FilterView(
    modifier: Modifier = Modifier,
    selectPopularsFilter: String,
    popularFilterOpen: Boolean,
    filtersOpen: Boolean,
    popularFilterClick: () -> Unit,
    popularFilterItemClick: (String) -> Unit,
    filterClick: () -> Unit,
    filterItemClick: (String) -> Unit
) {
    val popularList = listOf(
        "По популярности",
        "По рейтингу",
        "По возрастанию цены",
        "По убыванию цены",
        "По скидке"
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
                    text = selectPopularsFilter, style = TextStyle(
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
                onCloseClick = { popularFilterClick() }) {
                popularFilterItemClick(it)
            }
        }

        Button(
            onClick = { filterClick() },
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
        }
    }
}

@Composable
fun FiltersDropDownMenu(
    expanded: Boolean,
    list: List<String>,
    onCloseClick: () -> Unit,
    onClick: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
        expanded = expanded,
        onDismissRequest = { onCloseClick() },
    ) {
        list.forEach {
            DropdownMenuItem(onClick = { onClick(it) }) {
                Text(
                    text = it, style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = colors.gray
                    )
                )
            }
        }
    }
}
