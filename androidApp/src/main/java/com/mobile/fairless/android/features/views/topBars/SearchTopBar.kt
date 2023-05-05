package com.mobile.fairless.android.features.views.topBars

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

data class SearchTopBarParams(
    val searchString: String,
    val onClearText: () -> Unit,
    val onMicClick: () -> Unit,
    val onSearchClick: () -> Unit,
    val onValueChanged: (String) -> Unit
)


@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    searchString: String,
    onClearText: () -> Unit,
    onMicClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSearchChange: (String) -> Unit
) {
    val searchState = remember {
        mutableStateOf(searchString)
    }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = searchState.value) {
        onSearchChange(searchState.value)
    }
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        BasicTextField(
            value = searchState.value,
            onValueChange = {
                searchState.value = it
            },
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = fontQanelas,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.black,
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
                                searchState.value = ""
                                onClearText()
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = "ic_close",
                                tint = colors.black.copy(alpha = 0.6f),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    else
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "ic_search",
                            tint = colors.black.copy(alpha = 0.6f),
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
                                text = "Введите название города",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colors.grayMain,
                                )
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
                            tint = colors.black.copy(alpha = 0.6f)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                onSearchClick()
            }),
            modifier = Modifier
                .fillMaxSize()
                .background(colors.white)
        )
    }
}
