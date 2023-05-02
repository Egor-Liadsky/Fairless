package com.mobile.fairless.android.features.views.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

data class CommonButtonParams(
    val title: String,
    val titleColor: Color = colors.black,
    val background: Color = colors.white,
)

@Composable
fun CommonButton(
    commonButtonParams: CommonButtonParams,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .width(320.dp)
            .height(60.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = commonButtonParams.background
        )
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(20.dp),
                color = colors.white
            )
        } else {
            Text(
                text = commonButtonParams.title,
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = commonButtonParams.titleColor
                )
            )
        }
    }
}
