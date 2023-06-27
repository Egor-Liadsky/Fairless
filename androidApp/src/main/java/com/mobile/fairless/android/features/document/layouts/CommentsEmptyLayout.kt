package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun CommentsEmptyLayout() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_empty_comments), contentDescription = "ic_empty",
                modifier = Modifier.size(width = 83.dp, height = 83.dp),
                tint = Color(0xFFA7ACAF)
            )

            Text(
                text = "Поделитесь мнением, оставьте\nпервый комментарий!",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFA7ACAF),
                    textAlign = TextAlign.Center
                ), modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}