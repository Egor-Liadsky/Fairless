package com.mobile.fairless.android.features.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.theme.colors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Refreshable(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })
    Box(
        Modifier.pullRefresh(pullRefreshState)
    ) {
        content()
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
            contentColor = colors.orangeMain,
        )
    }
}
