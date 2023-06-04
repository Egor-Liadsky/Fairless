package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.main.viewModel.MainViewModel

@Composable
fun MainTopBar(viewModelWrapper: ViewModelWrapper<MainViewModel>) {
    val state = viewModelWrapper.viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .background(colors.backgroundWelcome)
            .padding(vertical = 20.dp)
            .height(110.dp),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Выберите нужную вам категорию",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colors.black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
        CategoriesView(
            categories = state.value.categories,
            isLoading = state.value.categoriesLoading,
            categoryOpened = state.value.selectCategory,
            modifier = Modifier.padding(top = 10.dp),
        ) {
            viewModelWrapper.viewModel.selectCategory(it)
        }
    }
}
