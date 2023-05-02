package com.mobile.fairless.android.features.views.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

data class CommonTextFieldParams(
    val textState: String = "",
    val placeholder: String = "",
    val isPassword: Boolean = false
)

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    commonTextFieldParams: CommonTextFieldParams,
    onValueChanged: (String) -> Unit
) {
    var text by remember {
        mutableStateOf(commonTextFieldParams.textState)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxWidth()) {
        Text(
            text = commonTextFieldParams.placeholder,
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Light,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                color = colors.black
            )
        )

        Row(
            modifier = Modifier
                .width(320.dp)
                .padding(top = 10.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    onValueChanged(newText)
                },
                textStyle = TextStyle(
                    fontFamily = fontQanelas,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = colors.black,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = if (commonTextFieldParams.isPassword) KeyboardType.Password else KeyboardType.Text),
                singleLine = true,
                modifier = Modifier
                    .background(colors.white)
                    .clip(RoundedCornerShape(5.dp))
                    .border(0.5.dp, colors.black, RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .width(320.dp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        innerTextField.invoke()
                    }
                }
            )
        }
    }
}
