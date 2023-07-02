package com.mobile.fairless.android.features.shop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.search.models.PopularFilter
import com.mobile.fairless.features.main.models.Type

@Composable
fun TypeDropDownMenu(
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
        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
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
