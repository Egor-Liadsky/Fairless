package com.mobile.fairless.android.features.views.topBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun CommonTopBar(
    title: String,
    backClick: () -> Unit? = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundWelcome)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SquareButton(icon = painterResource(id = R.drawable.ic_back_button), iconSize = 15.dp) {
            backClick()
        }

        Text(
            text = title,
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize =  20.sp,
                color = colors.black,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}
