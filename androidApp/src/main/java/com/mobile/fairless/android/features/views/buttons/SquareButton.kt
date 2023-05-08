package com.mobile.fairless.android.features.views.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.theme.colors

@Composable
fun SquareButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    iconSize: Dp = 20.dp,
    onClick: () -> Unit
) {

    Button(
        onClick = { onClick() },
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = colors.white)
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = "ic_square_button",
                modifier = Modifier.size(iconSize),
                tint = colors.black
            )
        }
    }
}
