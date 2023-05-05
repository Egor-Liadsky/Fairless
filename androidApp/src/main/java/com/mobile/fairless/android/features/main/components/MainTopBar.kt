package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
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
            .padding(horizontal = 20.dp, vertical = 20.dp),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Выберите нужную вам категорию",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colors.black
                ),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(24.dp)
                    .border(width = 0.5.dp, color = colors.black, shape = CircleShape)
                    .background(colors.white, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "ic_user",
                    tint = colors.black,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        CategoriesView(
            categories = state.value.categories,
            isLoading = state.value.categoriesLoading
        ) {

        }
    }
}
