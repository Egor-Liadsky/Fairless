package com.mobile.fairless.android.features.views.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    title: String,
    titleBackground: Color = colors.black,
    background: Color = colors.white,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        contentPadding = PaddingValues(),
    ) {
        Box(Modifier.fillMaxWidth()) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 16.dp)
                        .size(20.dp),
                    color = colors.orangeMain
                )
            } else {
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = titleBackground
                    ), modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 16.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "ic_next",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(width = 5.dp, height = 11.dp),
                    tint = colors.black
                )
            }
        }
    }
}
