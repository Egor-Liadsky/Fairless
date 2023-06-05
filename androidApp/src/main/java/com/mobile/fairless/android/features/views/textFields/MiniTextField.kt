package com.mobile.fairless.android.features.views.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun MiniTextField(
    modifier: Modifier = Modifier,
    textState: String = "",
    placeholder: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isPassword: Boolean = false,
    onValueChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    var text by remember {
        mutableStateOf(textState)
    }

    val isVisible = remember {
        mutableStateOf(!isPassword)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = placeholder, style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color(0xFFA7ACAF)
            ))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    onValueChanged(newText)
                },
                visualTransformation = if (isVisible.value) visualTransformation else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    fontFamily = fontQanelas,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.clearFocus()
                }),
                singleLine = true,
                modifier = Modifier
                    .background(colors.navBar, shape = RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth(),
                decorationBox = { innerTextField ->
                    Column(verticalArrangement = Arrangement.Center) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(end = 5.dp)
                        ) {
                            Box(modifier = Modifier.weight(1F).padding(start = 16.dp)) {
                                innerTextField()
                            }
                            if (isPassword) {
                                IconButton(onClick = { isVisible.value = isVisible.value.not() }) {
                                    Icon(
                                        painter = painterResource(if (isVisible.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off), // TODO change icon
                                        contentDescription = "EyeIcon",
                                        tint = colors.black,
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
                    }
                }
            )
        }
    }
}
