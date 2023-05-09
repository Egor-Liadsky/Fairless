package com.mobile.fairless.android.features.views.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun ShapeButton(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = colors.white),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .width(320.dp)
            .height(70.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title, style = TextStyle(
                        color = colors.black,
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = if (description == null) 15.sp else 10.sp
                    )
                )

                if (description != null) {
                    Text(
                        text = description, style = TextStyle(
                            color = colors.black,
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_next), contentDescription = "ic_next",
                tint = colors.orangeMain,
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
