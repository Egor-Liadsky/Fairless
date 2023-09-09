package com.mobile.fairless.android.features.mainNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.common.navigation.ScreenRoute

data class BottomNavigationItem(
    val route: ScreenRoute,
    val iconId: Int,
    val iconContentDescription: String,
    val label: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        ScreenRoute.Main,
        R.drawable.ic_home,
        "ic_home",
        "Главная"
    ),
    BottomNavigationItem(
        ScreenRoute.Search,
        R.drawable.ic_search,
        "ic_search",
        "Поиск"
    ),
    BottomNavigationItem(
        ScreenRoute.Menu,
        R.drawable.ic_menu,
        "ic_menu",
        "Меню"
    ),
)

@Composable
fun BottomBar(
    currentRoute: ScreenRoute,
    onButtonClick: (ScreenRoute) -> Unit
) {
    val color = colors.grayMain
    val selectColor = colors.orangeMain
    BottomNavigation(
        elevation = 3.dp,
        modifier = Modifier
            .height(60.dp)
            .navigationBarsPadding(),
        backgroundColor = colors.navBar
    ) {
        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(item.iconId),
                            contentDescription = item.iconContentDescription,
                            tint = if (currentRoute == item.route) selectColor else color,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                selected = currentRoute == item.route,
                onClick = {
                    onButtonClick(item.route)
                },
                label = {
                    Text(
                        text = item.label,
                        style = TextStyle(
                            fontSize = 10.sp,
                        ),
                        overflow = TextOverflow.Ellipsis,
                        color = if (currentRoute == item.route) selectColor else color
                    )
                },
                selectedContentColor = selectColor,
                unselectedContentColor = color
            )
        }
    }
}
