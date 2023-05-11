package com.mobile.fairless.android.features.menu.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.menu.components.CategoryColumn
import com.mobile.fairless.android.features.views.buttons.RectangleButtonParams
import com.mobile.fairless.features.menu.viewModel.MenuViewModel

@Composable
fun MenuLayout(viewModelWrapper: ViewModelWrapper<MenuViewModel>) {

    val settingsList = listOf(
        RectangleButtonParams(
            title = "Уведомления",
            painter = painterResource(id = R.drawable.ic_notification),
            onClick = { viewModelWrapper.viewModel.navigateToSettings() }
        ),
        RectangleButtonParams(
            title = "Выйти",
            painter = painterResource(id = R.drawable.ic_notification),
            onClick = { viewModelWrapper.viewModel.exitUser() }
        )
    )

    val supportList = listOf(
        RectangleButtonParams(
            title = "О нас",
            painter = painterResource(id = R.drawable.ic_feedback),
            onClick = { }
        ),
        RectangleButtonParams(
            title = "Правила поведения",
            painter = painterResource(id = R.drawable.ic_feedback),
            onClick = { }
        ),
        RectangleButtonParams(
            title = "Обратная связь",
            painter = painterResource(id = R.drawable.ic_feedback),
            onClick = { }
        ),
        RectangleButtonParams(
            title = "FAQ",
            painter = painterResource(id = R.drawable.ic_feedback),
            onClick = { }
        ),
        RectangleButtonParams(
            title = "О приложении",
            isPadding = true,
            onClick = { }
        ),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { CategoryColumn(title = "Настройки", subcategories = settingsList) }
        item { CategoryColumn(title = "Поддержка", subcategories = supportList) }
    }
}
