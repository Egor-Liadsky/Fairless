package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.main.models.Category

@Composable
fun CategoriesView(
    categories: List<Category>?,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp).fillMaxWidth().padding(vertical = 10.dp),
                color = colors.black,
            )
        } else {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
            ) {
                if (categories != null) {
                    items(items = categories) { category ->
                        CategoryItem(
                            name = category.name.toString(),
                            modifier = Modifier.padding(horizontal = 3.dp)
                        ) {
                            onClick()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {

    Row(modifier = modifier) {
        Card(
            backgroundColor = colors.white,
            modifier = Modifier
                .height(35.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = colors.black
                    )
                )
            }
        }
    }
}