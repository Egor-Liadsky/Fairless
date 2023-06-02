package com.mobile.fairless.android.features.menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun TestView(onClick: () -> Unit) {
    val orangeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF51B00),
            Color(0xFFFF8D00)
        )
    )
    Column {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 35.dp),
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
            contentPadding = PaddingValues()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(orangeGradient),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fire),
                    contentDescription = "ic_fire",
                    modifier = Modifier.padding(start = 10.dp).size(width = 42.dp, height = 50.dp).align(
                        Alignment.Bottom),
                    tint = Color(0xFFFC7E00)
                )
                Text(
                    text = "Пройти тест!", style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = colors.white
                    ),
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}

