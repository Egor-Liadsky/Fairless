package com.mobile.fairless.android.features.views.topBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mobile.fairless.android.R
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.utils.Constants
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Composable
fun CollapsingToolbar(
    imageUrl: String,
    state: CollapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState(),
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val minImageSize = 100
    val maxImageSize = 200

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundWelcome),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val imageSize =
                (minImageSize + (maxImageSize - minImageSize) * state.toolbarState.progress).dp
            Box(
                modifier = Modifier
                    .background(colors.white, shape = RoundedCornerShape(15.dp))
                    .height(300.dp)
                    .fillMaxWidth()
                    .pin()
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(colors.white, shape = RoundedCornerShape(15.dp))
                    .shadow(if (imageSize == 100.dp) 0.8.dp else 0.dp)
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .pin()
                ) {
                    SquareButton(
                        icon = painterResource(id = R.drawable.ic_back_button),
                        backgroundColor = colors.backgroundWelcome, iconSize = 15.dp
                    ) {
                        onBackClick()
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .defaultMinSize(minHeight = 50.dp)
                        .road(Alignment.Center, Alignment.TopStart)
                ) {
                    AsyncImage(
                        model = Constants.BASE_URL + imageUrl,
                        contentDescription = "ic_image",
                        modifier = Modifier
                            .size(imageSize)
                            .clip(RoundedCornerShape(15.dp))
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .pin()
                ) {
                    SquareButton(
                        icon = painterResource(id = R.drawable.ic_link_share),
                        backgroundColor = colors.backgroundWelcome,
                        iconColor = colors.orangeMain
                    ) {
                        onShareClick()
                    }
                }
            }
        }
    ) {
        content()
    }
}