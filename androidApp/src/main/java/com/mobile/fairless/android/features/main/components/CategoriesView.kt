package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.main.models.CategoryModel

@Composable
fun CategoriesView(
    categories: List<CategoryModel>?,
    modifier: Modifier = Modifier,
    categoryOpened: CategoryModel,
    isPadding: Boolean = true,
    selectCategory: (CategoryModel) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
        ) {
            if (categories != null) {
                item { Spacer(modifier = Modifier.padding(start = if (isPadding) 6.dp else 0.dp)) }
                items(items = categories) { category ->
                    CategoryItem(
                        category = category,
                        selected = categoryOpened.url == category.url,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        selectCategory(category)
                    }
                }
                item { Spacer(modifier = Modifier.padding(end = 6.dp)) }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: CategoryModel,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val orangeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF51B00),
            Color(0xFFFF8D00)
        )
    )
    val whiteGradient = Brush.horizontalGradient(
        colors = listOf(
            colors.white,
            colors.white
        )
    )

    Row(modifier = modifier.border(1.dp, colors.navBar, RoundedCornerShape(20.dp))) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(if (selected) orangeGradient else whiteGradient)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = category.name ?: "",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = if (selected) colors.white else colors.black
                    )
                )
            }
        }
    }
}
