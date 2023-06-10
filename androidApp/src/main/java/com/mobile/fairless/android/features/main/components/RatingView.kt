package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingView(
    width: Dp = 68.dp,
    height: Dp = 13.dp,
    boxSize: Dp = 10.dp,
    likes: Int,
    dislikes: Int
) {
    val rating: Int = calculateAverage(likes, dislikes).toInt()

    val colors = listOf(
        Color(0xFFF28C16),
        Color(0xFFF07E14),
        Color(0xFFEE7013),
        Color(0xFFEB6116),
        Color(0xFFE95116),
        Color(0xFFE74017),
    )

    Row(
        Modifier
            .width(width)
            .height(height)
            .border(1.dp, Color(0xFFDDDDDD), shape = RoundedCornerShape(2.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(1.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(rating){ item ->
                Column(Modifier.padding(horizontal = 0.5.dp)) {
                    Box(
                        modifier = Modifier
                            .background(colors[item], shape = RoundedCornerShape(2.dp))
                            .size(boxSize)
                    )
                }
            }
        }
    }
}

fun calculateAverage(likes: Int, dislikes: Int): Double {
    val totalVotes = likes + dislikes
    return if (totalVotes != 0) {
        likes.toDouble() / (likes.toDouble() + dislikes.toDouble()) * 6.0
    } else {
        0.0
    }
}
