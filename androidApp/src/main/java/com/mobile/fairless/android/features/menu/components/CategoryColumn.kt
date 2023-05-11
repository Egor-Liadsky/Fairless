package com.mobile.fairless.android.features.menu.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.features.views.buttons.RectangleButton
import com.mobile.fairless.android.features.views.buttons.RectangleButtonParams

@Composable
fun CategoryColumn(
    title: String?,
    subcategories: List<RectangleButtonParams>,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (title != null) {
            Text(
                text = title,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 14.dp
                ),
            )
        } else {
            Spacer(modifier = Modifier.padding(top = 30.dp))
        }
        Column {
            subcategories.forEach { subcategories ->
                RectangleButton(
                    RectangleButtonParams(
                        title = subcategories.title,
                        painter = subcategories.painter,
                        isPadding = subcategories.isPadding,
                        isSwitching = subcategories.isSwitching,
                        contentDescription = subcategories.contentDescription,
                        color = subcategories.color,
                    )
                ) {
                    subcategories.onClick()
                }
            }
        }
    }
}
