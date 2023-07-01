package com.mobile.fairless.android.features.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun ExitButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
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
            contentPadding = PaddingValues(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(orangeGradient)
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit),
                    contentDescription = "ic_exit",
                    modifier = Modifier.size(24.dp),
                    tint = colors.white
                )
                Text(
                    text = "Выход", style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = colors.white
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}
