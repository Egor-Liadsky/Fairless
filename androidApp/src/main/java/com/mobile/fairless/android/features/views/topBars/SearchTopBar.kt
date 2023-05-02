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
import androidx.compose.ui.draw.shadow
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
    searchTopBarParams: SearchTopBarParams
) {
    var searchState by remember {
        mutableStateOf(searchTopBarParams.searchString)
    }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .height(60.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        BasicTextField(
            value = searchState,
            onValueChange = { newText ->
                searchState = newText
                searchTopBarParams.onValueChanged(newText)
            },
            textStyle = TextStyle(
                fontFamily = fontQanelas,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.black,
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 18.dp)
                ) {
                    if (searchTopBarParams.searchString.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchState = ""
                                searchTopBarParams.onClearText()
                            },
                            modifier = Modifier.size(24.dp)
                                .background(colors.grayMain)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = "ic_close",
                                tint = colors.black,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    } else {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "ic_search",
                            tint = colors.black,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Box {
                        if (searchTopBarParams.searchString.isEmpty()) {
                            Text(
                                text = "Введите название города",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colors.grayMain,
                                )
                            )
                        }
                        innerTextField()
                    }
                    IconButton(
                        onClick = { searchTopBarParams.onMicClick() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mic),
                            contentDescription = "ic_mic",
                            tint = colors.black
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                searchTopBarParams.onSearchClick()
            }),
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(colors.white, shape = RoundedCornerShape(10.dp))
        )
    }
}
