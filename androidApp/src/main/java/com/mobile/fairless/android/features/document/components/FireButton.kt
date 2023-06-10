package com.mobile.fairless.android.features.document.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.state.Period
import com.mobile.fairless.features.main.models.DateFilter

@Composable
fun FireButton(
    modifier: Modifier = Modifier,
    state: State<DocumentState>,
    periodOpen: Boolean,
    periodClick: () -> Unit,
    onClick: (DateFilter) -> Unit
) {
    val periodList = mutableListOf(
        Period("Неделя", DateFilter.WEEK),
        Period("Месяц", DateFilter.MONTH)
    )
    if (!state.value.todayNull) {
        periodList.add(0, Period("Сегодня", DateFilter.TODAY))
    }

    Column(modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
                .background(colors.navBar, shape = RoundedCornerShape(5.dp))
                .border(0.5.dp, Color(0xFFA7ACAF), RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    periodClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = state.value.selectFirePeriod.title, style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFFA7ACAF)
                ), modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_bottom),
                    contentDescription = "ic_arrow_bottom",
                    modifier = Modifier.size(10.dp),
                    tint = Color(0xFFA7ACAF)
                )
            }

            PeriodDropDownMenu(
                expanded = periodOpen,
                list = periodList,
                onCloseClick = { periodClick() },
                onClick = {
                    onClick(it)
                    periodClick()
                })
        }
    }
}

@Composable
fun PeriodDropDownMenu(
    expanded: Boolean,
    list: List<Period>,
    onCloseClick: () -> Unit,
    onClick: (DateFilter) -> Unit
) {
    Box(modifier = Modifier, contentAlignment = Alignment.TopEnd) {
        DropdownMenu(
            modifier = Modifier.width(225.dp).clip(RoundedCornerShape(5.dp)),
            expanded = expanded,
            onDismissRequest = { onCloseClick() },
        ) {
            list.forEach {
                DropdownMenuItem(onClick = { onClick(it.period) }) {
                    Text(
                        text = it.title, style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = colors.black
                        )
                    )
                }
            }
        }
    }
}

