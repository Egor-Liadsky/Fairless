package com.mobile.fairless.android.features.views.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.features.views.switchs.DefaultSwitch
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas


data class RectangleButtonParams(
    val title: String = "",
    val isPadding: Boolean = false,
    val isSwitching: Boolean? = null,
    val painter: Painter? = null,
    val contentDescription: String = "",
    val color: Color = colors.white,
    val onClick: () -> Unit = {  }
)

@Composable
fun RectangleButton(rectangleButtonParams: RectangleButtonParams, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = rectangleButtonParams.color),
        shape = RectangleShape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 11.dp
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (rectangleButtonParams.painter != null) {
                Icon(
                    painter = rectangleButtonParams.painter,
                    contentDescription = rectangleButtonParams.contentDescription,
                    tint = colors.black.copy(0.6f),
                    modifier = Modifier.size(19.dp)
                )
            }
            Text(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .then(
                        if (rectangleButtonParams.painter != null) Modifier.padding(start = 32.dp)
                        else Modifier
                    )
                    .then(
                        if (rectangleButtonParams.isPadding) Modifier
                            .padding(start = 19.dp)
                            .padding(start = 32.dp)
                        else Modifier
                    )
                    .weight(1F),
                text = rectangleButtonParams.title,
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black,
                    fontSize = 15.sp
                )
            )
            if (rectangleButtonParams.isSwitching != null) {
                DefaultSwitch(checked = rectangleButtonParams.isSwitching) {
                    onClick()
                }
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "ic_next",
                    tint = colors.orangeMain,
                    modifier = Modifier
                        .size(15.dp)
                )
            }
        }
    }
}
