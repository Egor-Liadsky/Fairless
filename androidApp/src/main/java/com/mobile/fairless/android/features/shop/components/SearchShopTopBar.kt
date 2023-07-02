package com.mobile.fairless.android.features.shop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.shop.state.ShopState

@Composable
fun SearchShopTopBar(
    modifier: Modifier = Modifier,
    state: State<ShopState>,
    placeholder: String? = "",
    searchString: String,
    onClearText: () -> Unit,
    onMicClick: () -> Unit,
    onSearchChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .height(48.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(size = 10.dp)
            )
    ) {
        BasicTextField(
            value = state.value.searchString,
            onValueChange = {
                onSearchChange(it)
            },
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = fontQanelas,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = colors.black,
                textDecoration = TextDecoration.None
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 12.dp
                        )
                        .background(colors.white)
                ) {
                    if (searchString.isNotEmpty())
                        IconButton(
                            onClick = {
                                onClearText()
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = "ic_close",
                                tint = colors.gray,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    else
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "ic_search",
                            tint = colors.gray,
                            modifier = Modifier.size(24.dp)
                        )

                    Box(
                        modifier = Modifier
                            .padding(
                                start = 24.dp,
                                end = 16.dp
                            )
                            .weight(1F)
                    ) {
                        if (searchString.isEmpty())
                            Text(
                                text = placeholder ?: "",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W500,
                                    color = colors.gray,
                                ),
                            )
                        innerTextField()
                    }
                    IconButton(
                        onClick = { onMicClick() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mic),
                            contentDescription = "ic_mic",
                            tint = colors.gray,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
            }),
            modifier = Modifier
                .fillMaxSize()
                .background(colors.white)
        )
    }
}
