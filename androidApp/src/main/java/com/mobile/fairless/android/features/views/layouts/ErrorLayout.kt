package com.mobile.fairless.android.features.views.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobile.fairless.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun ErrorLayout(background: Color = colors.white,onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error), contentDescription = "ic_error",
                modifier = Modifier.size(width = 83.dp, height = 110.dp),
                tint = Color(0xFFA7ACAF)
            )

            Text(
                text = "Что-то пошло не так",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "Попробуйте обновить страницу",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Normal,
                    color = colors.black,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.padding(top = 15.dp)
            )

            TextButton(
                modifier = Modifier.padding(top = 20.dp),
                onClick = { onClick() }) {
                Text(
                    text = "Обновить",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFE74017),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}
