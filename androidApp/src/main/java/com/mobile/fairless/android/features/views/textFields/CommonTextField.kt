package com.mobile.fairless.android.features.views.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas


@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    textState: String = "",
    placeholder: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onDone: (() -> Unit)? = null,
    focusManager: FocusManager = LocalFocusManager.current,
    onValueChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focus = remember { mutableStateOf(false) }

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
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = colors.black,
                textAlign = TextAlign.Center
            ), modifier = Modifier.fillMaxWidth()
        )

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
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }, onDone = { onDone?.invoke() }),
                singleLine = true,
                modifier = Modifier
                    .background(colors.white, shape = RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .onFocusChanged { focus.value = it.isFocused },
                decorationBox = { innerTextField ->
                    Column(verticalArrangement = Arrangement.Center) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(end = 5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1F)
                                    .padding(start = 16.dp)
                            ) {
                                innerTextField()
                            }
                            if (isPassword) {
                                IconButton(onClick = { isVisible.value = isVisible.value.not() }) {
                                    Icon(
                                        painter = painterResource(if (isVisible.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off), // TODO change icon
                                        contentDescription = "EyeIcon",
                                        tint = colors.black,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        )
                    }
                }
            )
        }
    }
}
