package com.mobile.fairless.android.features.views.topBars

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.theme.colors

@Composable
fun StageBar(modifier: Modifier = Modifier, number: Int, count: Int) {
    val state = rememberLazyListState()

    val width = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.toDp() }

    LazyRow(
        state = state,
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentPadding = PaddingValues(horizontal = (width)),
        horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { iteration ->
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text = (iteration + 1).toString(), modifier = Modifier
                            .background(
                                color = if (number == iteration + 1) colors.orangeMain else colors.white,
                                shape = CircleShape
                            )
                            .size(25.dp),
                        style = TextStyle(
                            color = if (number == iteration + 1) Color.White else Color(0xFFD9D9D9),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(700)
                        )
                    )
                    if (iteration + 1 != count)
                        Spacer(
                            modifier = Modifier
                                .size(16.dp, 1.dp)
                                .background(
                                    if (number == iteration + 1) colors.orangeMain else colors.white,
                                    RoundedCornerShape(5.dp)
                                )
                        )
                }
            }
        }
    }
}

